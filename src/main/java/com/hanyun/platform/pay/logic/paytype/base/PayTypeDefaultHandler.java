/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.base;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PayTransactionNewDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.dao.PaymentNewDao;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandler;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandlerFactory;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradePayReq;

/**
 * 默认处理器
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月19日 上午10:16:23
 */
@Component
public class PayTypeDefaultHandler extends PayTypeHandler {
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;
    
    @Resource
    private PaymentNewDao paymentNewDao;
    
    @Resource
    private PayTransactionNewDao payTransactionNewDao;
    @Override
    public String getPayType() {
        return PayTypeHandlerFactory.DEFAULT_HANDLER_KEY;
    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public PayTransactionDao getPayTransactionDao() {
        return payTransactionDao;
    }

    @Override
    public Object doPreCreate(TradePreCreateReq req) {

        return null;
    }

    @Override
    public Object doPay(TradePayReq req) {
        Payment payment = PayProcessContext.getPayment();
        PayTransaction trans = PayProcessContext.getTransation();
        Object extData = PayProcessContext.getExtData();

        // 更新支付流水状态
        tradeServiceAssistLogic.updatePayTransWhenPayFinish(trans);
        // 更新支付单状态
        tradeServiceAssistLogic.updatePaymentWhenPayFinish(payment, trans);
        // 通知订单系统，避免订单系统请求支付系统超时状态不一致
        tradeServiceAssistLogic.notifyOrderWhenPayFinish(payment, trans, extData);

        return null;
    }

    @Override
    public Object doRefund(TradeRefundReq req) {
    	
    	if(!req.getPayType().equals(PaymentNewConsts.PAYTYPE_CASH)){
    		Payment payment = PayProcessContext.getPayment();
            PayTransaction reftrans = PayProcessContext.getRefundTransation();
            PayTransaction paytrans = PayProcessContext.getTransation();
            Date now = new Date();

            reftrans.setStatus(PaymentConsts.TRANS_STATUS_FINISH);
            reftrans.setFinishTime(now);
            getPayTransactionDao().updateStatusByTransId(reftrans);
            
            if(paytrans != null){
                paytrans.setHadRefundAmount(paytrans.getHadRefundAmount() + reftrans.getAmount());
                if(paytrans.getAmount() <= paytrans.getHadRefundAmount()){
                    paytrans.setRefundStatus(PaymentConsts.TRANS_REFUND_STATUS_FINISH);
                }else{
                    paytrans.setRefundStatus(PaymentConsts.TRANS_REFUND_STATUS_PART);
                }
                getPayTransactionDao().updatePayTransRefundInfoByTransId(paytrans);
            }

            payment.setRefundAmount(payment.getRefundAmount() + reftrans.getAmount());
            if (payment.getHadPayAmount() <= payment.getRefundAmount()) {
                payment.setPayStatus(PaymentConsts.PAY_STATUS_REF_ALL);
            } else {
                payment.setPayStatus(PaymentConsts.PAY_STATUS_REF_PART);
            }
            getPaymentDao().updateRefundByPayId(payment);
    	}else{
    		PaymentNew payment = PayProcessContext.getPaymentNew();
            PayTransactionNew reftrans = PayProcessContext.getRefundTransationNew();
            PayTransactionNew paytrans = PayProcessContext.getTransationNew();
            Date now = new Date();

            reftrans.setStatus(PaymentNewConsts.TRANS_STATUS_FINISH);
            reftrans.setFinishTime(now);
            payTransactionNewDao.updateStatusByTransId(reftrans);
            
            if(paytrans != null){
                paytrans.setHadRefundAmount(paytrans.getHadRefundAmount() + reftrans.getAmount());
                if(paytrans.getAmount() <= paytrans.getHadRefundAmount()){
                    paytrans.setRefundStatus(PaymentNewConsts.TRANS_REFUND_STATUS_FINISH);
                }else{
                    paytrans.setRefundStatus(PaymentNewConsts.TRANS_REFUND_STATUS_PART);
                }
                payTransactionNewDao.updatePayTransRefundInfoByTransId(paytrans);
            }

            payment.setRefundAmount(payment.getRefundAmount() + reftrans.getAmount());
            if (payment.getHadPayAmount() <= payment.getRefundAmount()) {
                payment.setPayStatus(PaymentNewConsts.PAY_STATUS_REF_ALL);
            } else {
                payment.setPayStatus(PaymentNewConsts.PAY_STATUS_REF_PART);
            }
            paymentNewDao.updateRefundByPayId(payment);
    	}
        

        return null;
    }
}
