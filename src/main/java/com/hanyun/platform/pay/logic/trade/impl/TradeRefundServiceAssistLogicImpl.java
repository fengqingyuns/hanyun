/**
 * 
 */
package com.hanyun.platform.pay.logic.trade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.logic.trade.TradeRefundServiceAssistLogic;
import com.hanyun.platform.pay.util.BusinessIdUtils;
import com.hanyun.platform.pay.util.PayChargeFeeUtils;
import com.hanyun.platform.pay.util.PaymentTradeUtils;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;

/**
 * 交易退款服务辅助逻辑
 * @author liyinglong@hanyun.com
 * @date 2017年6月23日 上午11:19:09
 */
@Component
public class TradeRefundServiceAssistLogicImpl implements TradeRefundServiceAssistLogic{
    
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public PayTransaction createRefundTransFromPayTrans(PayTransaction paytrans, TradeRefundReq req) {
        PayTransaction reftrans = new PayTransaction();
        
        reftrans.setTransId(BusinessIdUtils.genTransId());
        reftrans.setOperateType(PaymentConsts.TRANS_OPERATE_TYPE_REF);
        
        if(req.getRefundMode().equals(PaymentConsts.REFUND_MODE_WHOLE)){
            reftrans.setAmount(paytrans.getAmount() - paytrans.getHadRefundAmount());
        }else{
            reftrans.setAmount(req.getRefundAmount());
        }
        reftrans.setDiscountAmount(0L);
        
        reftrans.setPayId(paytrans.getPayId());
        reftrans.setOrderId(paytrans.getOrderId());
        reftrans.setOrderDocumentId(paytrans.getOrderDocumentId());
        reftrans.setBrandId(paytrans.getBrandId());
        reftrans.setStoreId(paytrans.getStoreId());
        reftrans.setSourceType(req.getSourceType());
        reftrans.setTerminalDeviceNo(req.getTerminalDeviceNo());
        reftrans.setTerminalIp(req.getTerminalIp());
        reftrans.setOperateUser(req.getOperateUser());
        reftrans.setSrcPayTransId(paytrans.getTransId());
        
        reftrans.setPayType(paytrans.getPayType());
        reftrans.setPayTypeDetail(paytrans.getPayTypeDetail());
        reftrans.setTypeCategory(paytrans.getTypeCategory());
        reftrans.setChannel(paytrans.getChannel());
        reftrans.setSettleType(paytrans.getSettleType());
        reftrans.setChnFeeMax(paytrans.getChnFeeMax());
        reftrans.setChnFeeRate(paytrans.getChnFeeRate());
        reftrans.setMchFeeMax(paytrans.getMchFeeMax());
        reftrans.setMchFeeRate(paytrans.getMchFeeRate());
        
        long chnFee = PayChargeFeeUtils.calcChargeFee(
                reftrans.getAmount(), reftrans.getChnFeeRate(), reftrans.getChnFeeMax());
        long mchFee = PayChargeFeeUtils.calcChargeFee(
                reftrans.getAmount(), reftrans.getMchFeeRate(), reftrans.getMchFeeMax());
        reftrans.setChnFee(chnFee);
        reftrans.setMchFee(mchFee);
        
        reftrans.setStatus(PaymentConsts.TRANS_STATUS_PROCESS);
        
        payTransactionDao.insert(reftrans);
        
        return reftrans;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public PayTransaction createRefundTransForNoOriginTransRefund(Payment payment, TradeRefundReq req) {
        PayTransaction reftrans = new PayTransaction();
        
        PaymentTradeUtils.fillTransFromPayment(payment, reftrans);
        
        reftrans.setTransId(BusinessIdUtils.genTransId());
        reftrans.setOperateType(PaymentConsts.TRANS_OPERATE_TYPE_REF);
        reftrans.setAmount(req.getRefundAmount());
        reftrans.setDiscountAmount(0L);
        
        reftrans.setSourceType(req.getSourceType());
        reftrans.setTerminalDeviceNo(req.getTerminalDeviceNo());
        reftrans.setTerminalIp(req.getTerminalIp());
        reftrans.setOperateUser(req.getOperateUser());
        
        reftrans.setPayType(PaymentConsts.PAYTYPE_CASH);
        reftrans.setTypeCategory(PaymentConsts.PAYTYPE_CATEGORY_CASH);
        reftrans.setChannel(PaymentConsts.PAYCHANNEL_OFFLINE);
        reftrans.setSettleType(PaymentConsts.SETTLE_TYPE_OFF);
        
        reftrans.setChnFeeRate(0);
        reftrans.setChnFeeMax(0);
        reftrans.setChnFee(0L);
        reftrans.setMchFeeRate(0);
        reftrans.setMchFeeMax(0);
        reftrans.setMchFee(0L);
        
        reftrans.setStatus(PaymentConsts.TRANS_STATUS_PROCESS);
        
        payTransactionDao.insert(reftrans);
        
        return reftrans;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Payment createPaymentForNoOriginOrderRefund(TradeRefundReq req) {
        Payment payment = new Payment();
        payment.setPayId(BusinessIdUtils.genPayId());
        payment.setBrandId(req.getBrandId());
        payment.setStoreId(req.getStoreId());
        payment.setOrderId(req.getOrderId());
        payment.setOrderDocumentId(req.getOrderDocumentId());
        payment.setOrderTime(DateFormatUtil.parseDateTimeNoSep(req.getOrderTime()));
        payment.setOrderAmount(0L);
        payment.setPayAmount(0L);
        payment.setHadPayAmount(0L);
        payment.setHadPayCount(0);
        payment.setRefundAmount(0L);
        payment.setHadDiscountAmount(0L);
        payment.setPayStatus(PaymentConsts.PAY_STATUS_REF_PROCESS);
        payment.setPayTime(new Date());
        
        payment.setSourceType(req.getSourceType());
        payment.setTerminalDeviceNo(req.getTerminalDeviceNo());
        payment.setTerminalIp(req.getTerminalIp());
        payment.setOperateUser(req.getOperateUser());
        
        paymentDao.insert(payment);
        
        return payment;
    }
    
}
