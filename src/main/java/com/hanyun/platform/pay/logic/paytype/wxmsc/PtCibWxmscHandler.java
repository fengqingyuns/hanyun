/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.wxmsc;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.weixin.CibWeiXinMicroPayAdapter;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.MicroPayReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.MicroPayRes;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderAttachData;
import com.hanyun.platform.pay.adapt.cib.weixin.util.OrderAttachDataUtil;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.consts.TaskMessageType;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibMerchantStoreDao;
import com.hanyun.platform.pay.dao.CibWeixinOrderDao;
import com.hanyun.platform.pay.domain.CibMerchantStore;
import com.hanyun.platform.pay.domain.CibWeixinOrder;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.logic.paytype.base.PtCibWxBaseHandler;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.task.submit.TaskSubmitter;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePayBaseReq;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePayWeixinExtData;

/**
 * 兴业银行微信商户扫码处理
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月31日 上午11:52:57
 */
@Component
public class PtCibWxmscHandler extends PtCibWxBaseHandler {

    @Resource
    private CibWeixinOrderDao cibWeixinOrderDao;
    @Resource
    private CibMerchantStoreDao cibMerchantStoreDao;
    @Resource
    private CibWeiXinMicroPayAdapter cibWeiXinMicroPayAdapter;
    @Resource
    private TaskSubmitter taskSubmitter;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;

    @Override
    public String getPayType() {
        return PaymentConsts.PAYTYPE_WXPAY_MSC;
    }

    @Override
    public Object doPreCreate(TradePreCreateReq req) {
        Payment payment = PayProcessContext.getPayment();
        PayTransaction trans = PayProcessContext.getTransation();

        getCibWeixinOrder(payment, trans, req);

        return super.doPreCreate(req);
    }

    @Override
    public Object doPay(TradePayReq req) {
        Payment payment = PayProcessContext.getPayment();
        PayTransaction trans = PayProcessContext.getTransation();

        CibWeixinOrder wxorder = getCibWeixinOrder(payment, trans, req);

        MicroPayReq mpreq = new MicroPayReq();
        mpreq.setOut_trade_no(wxorder.getOutTradeNo());
        mpreq.setBody(wxorder.getBody());
        mpreq.setAttach(wxorder.getAttach());
        mpreq.setTotal_fee(wxorder.getTotalFee());
        mpreq.setFee_type(wxorder.getFeeType());
        mpreq.setLimit_pay(wxorder.getLimitPay());
        mpreq.setDevice_info(wxorder.getDeviceInfo());
        mpreq.setSpbill_create_ip(wxorder.getSpbillCreateIp());
        mpreq.setAuth_code(req.getAuthCode());

        MicroPayRes mpres = null;
        try {
            mpres = cibWeiXinMicroPayAdapter.request(mpreq);
        } catch (BizException e) {
            if (!BizResCode.CIBWEIXIN_ERROR_REQ.equals(e.getErrorMsg())
                    && !BizResCode.CIBWEIXIN_ERROR_RETURNCODE.equals(e.getErrorMsg())) {
                throw e;
            }
        }

        TradePayWeixinExtData extData = null;
        if (mpres != null && CibWeiXinConsts.RESULT_CODE_SUCCESS.equals(mpres.getResult_code())) {
            extData = new TradePayWeixinExtData();
            extData.setWxOrderId(mpres.getTransaction_id());
            PayProcessContext.setExtData(extData);

            super.doPay(req);
        } else {
            // 支付结果未知的情况，比如等待用户输入密码等，需要主动去查询
            Date now = new Date();
            trans.setStatus(PaymentConsts.TRANS_STATUS_PROCESS);
            trans.setFinishTime(now);
            getPayTransactionDao().updateStatusByTransId(trans);

            TaskQueue task = new TaskQueue();
            task.setMessageType(TaskMessageType.WXPAY_MSC_QUERY_ORDER);
            task.setMessage(trans.getTransId());
            task.setPayId(payment.getPayId());
            task.setTransId(trans.getTransId());
            taskSubmitter.submit(task);
        }

        return extData;
    }

    /**
     * 获取微信订单
     * 
     * @param payment
     * @param trans
     * @param req
     * @return
     */
    private CibWeixinOrder getCibWeixinOrder(Payment payment, PayTransaction trans, TradePayBaseReq req) {
        CibWeixinOrder wxorder = cibWeixinOrderDao.selectByOutTradeNo(trans.getTransId());
        if (wxorder == null) {
            wxorder = new CibWeixinOrder();
            wxorder.setBrandId(payment.getBrandId());
            wxorder.setStoreId(payment.getStoreId());
            wxorder.setOutTradeNo(trans.getTransId());
            wxorder.setDeviceInfo(req.getTerminalDeviceNo());
            wxorder.setBody(req.getOrderDesc());
            wxorder.setTotalFee(req.getCurPayAmount());
            wxorder.setSpbillCreateIp(req.getTerminalIp());
            wxorder.setTradeType(CibWeiXinConsts.TRADE_TYPE_MICROPAY);
            // 附加数据
            CibMerchantStore mchStore = cibMerchantStoreDao.getByStoreId(payment.getStoreId());
            if (mchStore == null) {
                throw BizException.build(BizResCode.CIB_MERCHANT_STORE_EMPTY);
            }
            OrderAttachData attachData = new OrderAttachData();
            attachData.setStoreAppid(mchStore.getCibStoreAppid());
            attachData.setStoreName(mchStore.getCibStoreName());
            wxorder.setAttach(OrderAttachDataUtil.format(attachData));

            cibWeixinOrderDao.insert(wxorder);
        }
        return wxorder;
    }
}
