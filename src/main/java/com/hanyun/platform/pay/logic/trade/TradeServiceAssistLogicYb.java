/**
 * 
 */
package com.hanyun.platform.pay.logic.trade;

import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.vo.trade.TradePayBaseReq;

/**
 * 交易服务辅助逻辑
 * 
 * @author litao@hanyun.com
 * @date 2017年3月28日 下午2:57:31
 */
public interface TradeServiceAssistLogicYb {

    /**
     * 创建支付流水，需要配置独立事务 REQUIRES_NEW
     * 
     * @param paymentNew
     * @return
     */
    public PayTransactionNew createPayTrans(PaymentNew paymentNew, MchActualPayModeInfo payChnMode, 
            TradePayBaseReq req, Integer status);

    /**
     * 创建支付单，需要配置独立事务 REQUIRES_NEW
     * 
     * @param req
     * @return
     */
    public PaymentNew createPayment(TradePayBaseReq req);
    
    /**
     * 更新交易流水的支付方式明细信息
     * @param originTransNew
     * @param payTypeDetail
     */
    public boolean updateTransPayTypeDetailInfo(PayTransactionNew originTransNew, String payTypeDetail);
    
    /**
     * 支付完成时更新支付流水
     * @param paytransNew
     */
    public void updatePayTransWhenPayFinish(PayTransactionNew paytransNew);
    
    /**
     * 支付失败时更新支付流水
     * @param paytransNew
     */
    public void updatePayTransWhenPayFail(PayTransactionNew paytransNew);
    
    /**
     * 支付完成时更新支付单
     * @param paymentNew
     * @param paytransNew
     */
    public void updatePaymentWhenPayFinish(PaymentNew paymentNew, PayTransactionNew paytransNew);
    
    /**
     * 支付完成时通知订单系统
     * @param paymentNew
     * @param paytransNew
     */
    public void notifyOrderWhenPayFinish(PaymentNew paymentNew, PayTransactionNew paytransNew, Object extData);
    
    
}
