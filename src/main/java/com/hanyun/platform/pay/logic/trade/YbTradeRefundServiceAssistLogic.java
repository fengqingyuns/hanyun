/**
 * 
 */
package com.hanyun.platform.pay.logic.trade;

import com.hanyun.platform.pay.adapt.yeepay.model.YeeRefundRes;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;

/**
 * 交易退款服务辅助逻辑
 * @author litao@hanyun.com
 * @date 2017年6月23日 上午11:18:44
 */
public interface YbTradeRefundServiceAssistLogic {
    /**
     * 根据付款流水创建退款流水
     * @param paytrans
     * @param req
     * @return
     */
    public PayTransactionNew createRefundTransFromPayTrans(PayTransactionNew paytransNew, TradeRefundReq req);
    
    /**
     * 为无原支付流水退款创建交易流水
     * @param payment
     * @param req
     * @return
     */
    public PayTransactionNew createRefundTransForNoOriginTransRefund(PaymentNew paymentNew, TradeRefundReq req);
    
    /**
     * 为无原支付单退款创建支付单
     * @param req
     * @return
     */
    public PaymentNew createPaymentForNoOriginOrderRefund(TradeRefundReq req);
    
    /**
     * 易宝退款返回结果处理
     * @param res
     * @return
     */
    public void doRefundCallback(YeeRefundRes res);
}
