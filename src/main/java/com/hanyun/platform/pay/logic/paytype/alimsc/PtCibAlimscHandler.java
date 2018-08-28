/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.alimsc;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.alipay.CibAlipayMicroPayAdapter;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayMicroPayReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayMicroPayRes;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.consts.TaskMessageType;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibAlipayOrderDao;
import com.hanyun.platform.pay.domain.CibAlipayOrder;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.logic.paytype.base.PtCibAliBaseHandler;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.task.submit.TaskSubmitter;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePayAlipayExtData;
import com.hanyun.platform.pay.vo.trade.TradePayBaseReq;
import com.hanyun.platform.pay.vo.trade.TradePayReq;

/**
 * 兴业银行支付宝商户扫码处理
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年1月3日 下午7:07:23
 */
@Component
public class PtCibAlimscHandler extends PtCibAliBaseHandler {
    @Resource
    private CibAlipayOrderDao cibAlipayOrderDao;
    @Resource
    private CibAlipayMicroPayAdapter cibAlipayMicroPayAdapter;
    @Resource
    private TaskSubmitter taskSubmitter;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;

    @Override
    public String getPayType() {
        return PaymentConsts.PAYTYPE_ALIPAY_MSC;
    }

    @Override
    public Object doPreCreate(TradePreCreateReq req) {
        Payment payment = PayProcessContext.getPayment();
        PayTransaction trans = PayProcessContext.getTransation();

        getCibAlipayOrder(payment, trans, req);

        return super.doPreCreate(req);
    }

    @Override
    public Object doPay(TradePayReq req) {
        Payment payment = PayProcessContext.getPayment();
        PayTransaction trans = PayProcessContext.getTransation();

        CibAlipayOrder aliorder = getCibAlipayOrder(payment, trans, req);

        AlipayMicroPayReq mpreq = new AlipayMicroPayReq();
        mpreq.setOutTradeNo(trans.getTransId());
        mpreq.setBody(aliorder.getBody());
        mpreq.setAttach(trans.getOrderId());
        mpreq.setTotalFee(trans.getAmount());
        mpreq.setFeeType(aliorder.getFeeType());
        mpreq.setDeviceInfo(aliorder.getDeviceInfo());
        mpreq.setAuthCode(req.getAuthCode());

        AlipayMicroPayRes mpres = null;
        try {
            mpres = cibAlipayMicroPayAdapter.request(mpreq);
        } catch (BizException e) {
            if (!BizResCode.CIB_ALIPAY_ERROR_REQ.equals(e.getErrorMsg())
                    && !BizResCode.CIB_ALIPAY_ERROR_RETURNCODE.equals(e.getErrorMsg())) {
                throw e;
            }
        }

        TradePayAlipayExtData extData = null;
        if (mpres != null && CibAlipayConsts.RESULT_CODE_SUCCESS.equals(mpres.getResultCode())) {
            extData = new TradePayAlipayExtData();
            extData.setAliOrderId(mpres.getTransactionId());
            PayProcessContext.setExtData(extData);
            
            super.doPay(req);

        } else {
            // 支付结果未知的情况，比如等待用户输入密码等，需要主动去查询
            Date now = new Date();
            trans.setStatus(PaymentConsts.TRANS_STATUS_PROCESS);
            trans.setFinishTime(now);
            getPayTransactionDao().updateStatusByTransId(trans);

            TaskQueue task = new TaskQueue();
            task.setMessageType(TaskMessageType.ALIPAY_MSC_QUERY_ORDER);
            task.setMessage(trans.getTransId());
            task.setPayId(payment.getPayId());
            task.setTransId(trans.getTransId());
            taskSubmitter.submit(task);
        }

        return extData;
    }

    /**
     * 获取支付宝订单
     * 
     * @param payment
     * @param trans
     * @param req
     * @return
     */
    private CibAlipayOrder getCibAlipayOrder(Payment payment, PayTransaction trans, TradePayBaseReq req) {
        CibAlipayOrder aliorder = cibAlipayOrderDao.selectByOutTradeNo(trans.getTransId());
        if (aliorder == null) {
            aliorder = new CibAlipayOrder();
            aliorder.setBrandId(payment.getBrandId());
            aliorder.setStoreId(payment.getStoreId());
            aliorder.setOutTradeNo(trans.getTransId());
            aliorder.setDeviceInfo(req.getTerminalDeviceNo());
            aliorder.setBody(req.getOrderDesc());
            aliorder.setTotalFee(req.getCurPayAmount());
            aliorder.setTradeType(CibAlipayConsts.TRADE_TYPE_MICROPAY);

            cibAlipayOrderDao.insert(aliorder);
        }
        return aliorder;
    }
}
