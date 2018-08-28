/**
 * 
 */
package com.hanyun.platform.pay.logic.trade;

import com.hanyun.platform.pay.vo.trade.TradeRefundReq;

/**
 * 交易退款逻辑
 * @author liyinglong@hanyun.com
 * @date 2017年6月2日 下午6:10:30
 */
public interface TradeRefundLogic {
    
    /**
     * 查检通用请求参数
     * @param req
     * @return
     */
    public boolean checkCommonReqParam(TradeRefundReq req);
    
    /**
     * 处理有单整单退款
     * @param req
     */
    public void dealRefundForWhole(TradeRefundReq req);
    
    /**
     * 处理有单部分退款-指定支付方式原路退回
     * @param req
     */
    public void dealRefundForPartPayType(TradeRefundReq req);
    
    /**
     * 处理有单部分退款-不依赖原支付方式，在线下以现金方式退回
     * @param req
     */
    public void dealRefundForPartOffline(TradeRefundReq req);
    
    /**
     * 处理无单退款
     * @param req
     */
    public void dealRefundForNoOrder(TradeRefundReq req);
}
