/**
 * 
 */
package com.hanyun.platform.pay.service.cib.alipay.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayResultNotifyReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayResultNotifyRes;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.dao.CibAlipayOrderDao;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.domain.CibAlipayOrder;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.service.cib.alipay.CibAlipayCallbackService;
import com.hanyun.platform.pay.vo.trade.TradePayAlipayExtData;

/**
 * 兴业支付宝回调处理
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年1月5日 上午11:29:46
 */
@Service
public class CibAlipayCallbackServiceImpl implements CibAlipayCallbackService {
    @Resource
    private CibAlipayOrderDao cibAlipayOrderDao;
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;

    @Override
    public AlipayResultNotifyRes dealPayResultCallback(AlipayResultNotifyReq req) {
        AlipayResultNotifyRes prnres = new AlipayResultNotifyRes();
        prnres.setReturnCode(CibAlipayConsts.RETURN_CODE_SUCCESS);

        PayTransaction trans = payTransactionDao.selectByTransId(req.getOutTradeNo());
        if (PaymentConsts.TRANS_STATUS_FINISH.equals(trans.getStatus())) {
            return prnres;
        }

        Payment payment = paymentDao.selectByPayId(trans.getPayId());
        CibAlipayOrder aliorder = cibAlipayOrderDao.selectByOutTradeNo(req.getOutTradeNo());

        aliorder.setVersion(req.getVersion());
        aliorder.setTransactionId(req.getTransactionId());
        aliorder.setPassTradeNo(req.getPassTradeNo());
        aliorder.setOpenid(req.getOpenid());
        aliorder.setTimeEnd(req.getTimeEnd());
        aliorder.setBuyerLogonId(req.getBuyerLogonId());
        aliorder.setFundBillList(req.getFundBillList());

        cibAlipayOrderDao.updateByOutTradeNo(aliorder);

        // 更新支付流水状态
        tradeServiceAssistLogic.updatePayTransWhenPayFinish(trans);
        // 更新支付单状态
        tradeServiceAssistLogic.updatePaymentWhenPayFinish(payment, trans);
        // 通知订单系统
        TradePayAlipayExtData extdata = new TradePayAlipayExtData();
        extdata.setAliOrderId(aliorder.getTransactionId());
        tradeServiceAssistLogic.notifyOrderWhenPayFinish(payment, trans, extdata);

        return prnres;
    }

}
