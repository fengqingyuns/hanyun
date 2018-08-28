/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 查询额外信息
 * @author wangjie@hanyun.com
 * @date 2017年01月04日 下午3:36:16
 */
public class TradeExtraInfoQueryReq {
    private String orderId;
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
