/**
 * 
 */
package com.hanyun.platform.pay.service.cib.weixin.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.PayResultNotifyReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.PayResultNotifyRes;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.dao.CibWeixinOrderDao;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.domain.CibWeixinOrder;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.service.cib.weixin.CibWeixinCallbackService;
import com.hanyun.platform.pay.vo.trade.TradePayWeixinExtData;

/**
 * 兴业微信回调处理
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年9月7日 上午10:02:15
 */
@Service
public class CibWeixinCallbackServiceImpl implements CibWeixinCallbackService {
    @Resource
    private CibWeixinOrderDao cibWeixinOrderDao;
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;

    @Override
    public PayResultNotifyRes dealPayResultCallback(PayResultNotifyReq req) {
        PayResultNotifyRes prnres = new PayResultNotifyRes();
        prnres.setReturn_code(CibWeiXinConsts.RETURN_CODE_SUCCESS);

        PayTransaction trans = payTransactionDao.selectByTransId(req.getOut_trade_no());
        if (PaymentConsts.TRANS_STATUS_FINISH.equals(trans.getStatus())) {
            return prnres;
        }

        Payment payment = paymentDao.selectByPayId(trans.getPayId());
        CibWeixinOrder wxorder = cibWeixinOrderDao.selectByOutTradeNo(req.getOut_trade_no());

        wxorder.setVersion(req.getVersion());
        wxorder.setDeviceInfo(req.getDevice_info());
        wxorder.setTransactionId(req.getTransaction_id());
        wxorder.setPassTradeNo(req.getPass_trade_no());
        wxorder.setTradeType(req.getTrade_type());
        wxorder.setOpenid(req.getOpenid());
        wxorder.setIsSubscribe(req.getIs_subscribe());
        wxorder.setBankType(req.getBank_type());
        wxorder.setTimeEnd(req.getTime_end());

        cibWeixinOrderDao.updateByOutTradeNo(wxorder);

        // 更新支付流水状态
        tradeServiceAssistLogic.updatePayTransWhenPayFinish(trans);
        // 更新支付单状态
        tradeServiceAssistLogic.updatePaymentWhenPayFinish(payment, trans);
        // 通知订单系统
        TradePayWeixinExtData extdata = new TradePayWeixinExtData();
        extdata.setWxOrderId(wxorder.getTransactionId());
        tradeServiceAssistLogic.notifyOrderWhenPayFinish(payment, trans, extdata);

        return prnres;
    }

}
