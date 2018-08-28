/**
 * 
 */
package com.hanyun.platform.pay.logic.trade;

import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.vo.trade.TradePayBaseReq;

/**
 * 交易服务辅助逻辑
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年3月28日 下午2:57:31
 */
public interface TradeServiceAssistLogic {

    /**
     * 创建支付流水，需要配置独立事务 REQUIRES_NEW
     * 
     * @param payment
     * @return
     */
    public PayTransaction createPayTrans(Payment payment, MchActualPayModeInfo payChnMode, 
            TradePayBaseReq req, Integer status);

    /**
     * 创建支付单，需要配置独立事务 REQUIRES_NEW
     * 
     * @param req
     * @return
     */
    public Payment createPayment(TradePayBaseReq req);
    
    /**
     * 更新交易流水的支付方式明细信息
     * @param originTrans
     * @param payTypeDetail
     */
    public boolean updateTransPayTypeDetailInfo(PayTransaction originTrans, String payTypeDetail);
    
    /**
     * 支付完成时更新支付流水
     * @param paytrans
     */
    public void updatePayTransWhenPayFinish(PayTransaction paytrans);
    
    /**
     * 支付失败时更新支付流水
     * @param paytrans
     */
    public void updatePayTransWhenPayFail(PayTransaction paytrans);
    
    /**
     * 支付完成时更新支付单
     * @param payment
     * @param paytrans
     */
    public void updatePaymentWhenPayFinish(Payment payment, PayTransaction paytrans);
    
    /**
     * 支付完成时通知订单系统
     * @param payment
     * @param paytrans
     */
    public void notifyOrderWhenPayFinish(Payment payment, PayTransaction paytrans, Object extData);
    
    
}
