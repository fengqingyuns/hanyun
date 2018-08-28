/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 支付单查询
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午3:29:34
 */
public class TradeQueryReq {
    // 支付单编号 必填
    private String payId;
    // 订单编号 必填
    private String orderId;
    
    public String getPayId() {
        return payId;
    }
    public void setPayId(String payId) {
        this.payId = payId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
}
