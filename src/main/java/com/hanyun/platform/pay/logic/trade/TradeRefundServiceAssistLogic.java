/**
 * 
 */
package com.hanyun.platform.pay.logic.trade;

import com.hanyun.platform.pay.adapt.yeepay.model.YeeRefundRes;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;

/**
 * 交易退款服务辅助逻辑
 * @author liyinglong@hanyun.com
 * @date 2017年6月23日 上午11:18:44
 */
public interface TradeRefundServiceAssistLogic {
    /**
     * 根据付款流水创建退款流水
     * @param paytrans
     * @param req
     * @return
     */
    public PayTransaction createRefundTransFromPayTrans(PayTransaction paytrans, TradeRefundReq req);
    
    /**
     * 为无原支付流水退款创建交易流水
     * @param payment
     * @param req
     * @return
     */
    public PayTransaction createRefundTransForNoOriginTransRefund(Payment payment, TradeRefundReq req);
    
    /**
     * 为无原支付单退款创建支付单
     * @param req
     * @return
     */
    public Payment createPaymentForNoOriginOrderRefund(TradeRefundReq req);
    
    
}
