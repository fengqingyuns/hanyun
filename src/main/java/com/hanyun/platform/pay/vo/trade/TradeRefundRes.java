/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;


/**
 * 退款
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午3:30:55
 */
public class TradeRefundRes {
    // 订单编号
    private String orderId;
    // 支付单编号
    private String payId;
    // 支付单状态
    private Integer payStatus;
    // 订单金额
    private Long orderAmount;
    // 订单需付金额
    private Long payAmount;
    // 订单已付金额
    private Long hadPayAmount;
    // 订单已优惠金额
    private Long hadDiscountAmount;
    // 订单已退款金额
    private Long refundAmount;
    // 扩展数据
    private Object extdata;
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getPayId() {
        return payId;
    }
    public void setPayId(String payId) {
        this.payId = payId;
    }
    public Integer getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
    public Long getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }
    public Long getPayAmount() {
        return payAmount;
    }
    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }
    public Long getHadPayAmount() {
        return hadPayAmount;
    }
    public void setHadPayAmount(Long hadPayAmount) {
        this.hadPayAmount = hadPayAmount;
    }
    public Long getHadDiscountAmount() {
        return hadDiscountAmount;
    }
    public void setHadDiscountAmount(Long hadDiscountAmount) {
        this.hadDiscountAmount = hadDiscountAmount;
    }
    public Long getRefundAmount() {
        return refundAmount;
    }
    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }
    public Object getExtdata() {
        return extdata;
    }
    public void setExtdata(Object extdata) {
        this.extdata = extdata;
    }
    
}
