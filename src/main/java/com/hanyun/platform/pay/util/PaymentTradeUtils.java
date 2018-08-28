/**
 * 
 */
package com.hanyun.platform.pay.util;

import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.vo.trade.TradePayRes;

/**
 * 支付单相关工具类
 * @author liyinglong@hanyun.com
 * @date 2016年11月24日 下午6:52:54
 */
public class PaymentTradeUtils {
    /**
     * 根据支付单填充支付流水基础信息
     * @param payment
     * @param trans
     */
    public static void fillTransFromPayment(Payment payment, PayTransaction trans){
        
        trans.setPayId(payment.getPayId());
        trans.setOrderId(payment.getOrderId());
        trans.setOrderDocumentId(payment.getOrderDocumentId());
        trans.setBrandId(payment.getBrandId());
        trans.setStoreId(payment.getStoreId());
        
    }
    
    /**
     * 根据易寶支付单填充支付流水基础信息
     * @param paymentNew
     * @param transNew
     */
    public static void fillTransNewFromPaymentNew(PaymentNew payment, PayTransactionNew trans){
        
        trans.setPayId(payment.getPayId());
        trans.setOrderId(payment.getOrderId());
        trans.setOrderDocumentId(payment.getOrderDocumentId());
        trans.setBrandId(payment.getBrandId());
        trans.setStoreId(payment.getStoreId());
        
    }
    
    /**
     * 根据易宝支付单填充支付流水基础信息
     * @param paymentNew
     * @param transNew
     */
    public static void fillTransFromPaymentNew(PaymentNew paymentNew, PayTransactionNew transNew){
        
        transNew.setPayId(paymentNew.getPayId());
        transNew.setOrderId(paymentNew.getOrderId());
        transNew.setOrderDocumentId(paymentNew.getOrderDocumentId());
        transNew.setBrandId(paymentNew.getBrandId());
        transNew.setStoreId(paymentNew.getStoreId());
        
    }
    
    /**
     * 支付流水填充支付方式信息
     * @param trans
     * @param payChnMode
     */
    public static void fillPayTransFromPayModeInfo(PayTransaction trans, MchActualPayModeInfo payChnMode) {
        trans.setPayType(payChnMode.getPayType());
        trans.setTypeCategory(payChnMode.getTypeCategory());
        trans.setChannel(payChnMode.getChannel());
        trans.setSettleType(payChnMode.getSettleType());
        trans.setChnFeeMax(payChnMode.getChnFeeMax());
        trans.setChnFeeRate(payChnMode.getChnFeeRate());
        Integer mchFeeMax = (payChnMode.getMchFeeMax() == null) ? payChnMode.getMchFeeMaxDef()
                : payChnMode.getMchFeeMax();
        Integer mchFeeRate = (payChnMode.getMchFeeRate() == null) ? payChnMode.getMchFeeRateDef()
                : payChnMode.getMchFeeRate();
        trans.setMchFeeMax(mchFeeMax);
        trans.setMchFeeRate(mchFeeRate);
    }
    
    /**
     * 支付流水填充支付方式信息
     * @param transNew
     * @param payChnMode
     */
    public static void fillPayTransNewFromPayModeInfo(PayTransactionNew transNew, MchActualPayModeInfo payChnMode) {
        transNew.setPayType(payChnMode.getPayType());
        transNew.setTypeCategory(payChnMode.getTypeCategory());
        transNew.setChannel(payChnMode.getChannel());
        transNew.setSettleType(payChnMode.getSettleType());
        transNew.setChnFeeMax(payChnMode.getChnFeeMax());
        transNew.setChnFeeRate(payChnMode.getChnFeeRate());
        Integer mchFeeMax = (payChnMode.getMchFeeMax() == null) ? payChnMode.getMchFeeMaxDef()
                : payChnMode.getMchFeeMax();
        Integer mchFeeRate = (payChnMode.getMchFeeRate() == null) ? payChnMode.getMchFeeRateDef()
                : payChnMode.getMchFeeRate();
        transNew.setMchFeeMax(mchFeeMax);
        transNew.setMchFeeRate(mchFeeRate);
    }
    
    /**
     * 获取支付返回结果
     * 
     * @param payment
     * @param trans
     * @param extdata
     * @return
     */
    public static void fillTradePayRes(TradePayRes data, Payment payment, PayTransaction trans, Object extdata) {
        data.setOrderId(payment.getOrderId());
        data.setPayId(payment.getPayId());
        data.setPayStatus(payment.getPayStatus());
        data.setOrderAmount(payment.getOrderAmount());
        data.setPayAmount(payment.getPayAmount());
        data.setHadPayAmount(payment.getHadPayAmount());
        data.setHadDiscountAmount(payment.getHadDiscountAmount());
        if(trans != null){
            data.setCurPayAmount(trans.getAmount());
            data.setCurDiscountAmount(trans.getDiscountAmount());
            data.setTransId(trans.getTransId());
            data.setTransStatus(trans.getStatus());
            data.setPayType(trans.getPayType());
        }
        data.setExtdata(extdata);

    }
    
    /**
     * 获取支付返回结果
     * 
     * @param paymentNew
     * @param transNew
     * @param extdata
     * @return
     */
    public static void fillTradePayYbRes(TradePayRes data, PaymentNew paymentNew, PayTransactionNew transNew, Object extdata) {
        data.setOrderId(paymentNew.getOrderId());
        data.setPayId(paymentNew.getPayId());
        data.setPayStatus(paymentNew.getPayStatus());
        data.setOrderAmount(paymentNew.getOrderAmount());
        data.setPayAmount(paymentNew.getPayAmount());
        data.setHadPayAmount(paymentNew.getHadPayAmount());
        data.setHadDiscountAmount(paymentNew.getHadDiscountAmount());
        if(transNew != null){
            data.setCurPayAmount(transNew.getAmount());
            data.setCurDiscountAmount(transNew.getDiscountAmount());
            data.setTransId(transNew.getTransId());
            data.setTransStatus(transNew.getStatus());
            data.setPayType(transNew.getPayType());
        }
        data.setExtdata(extdata);

    }
}
