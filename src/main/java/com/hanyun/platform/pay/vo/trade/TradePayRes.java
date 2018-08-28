/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 支付
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午3:36:00
 */
public class TradePayRes {
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
    // 支付单订单已优惠金额
    private Long hadDiscountAmount;
    // 本次支付金额
    private Long curPayAmount;
    // 本次优惠金额
    private Long curDiscountAmount;
    // 支付流水编号
    private String transId;
    // 支付流水状态
    private Integer transStatus;
    // 支付方式
    private String payType;
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
    public Long getCurPayAmount() {
        return curPayAmount;
    }
    public void setCurPayAmount(Long curPayAmount) {
        this.curPayAmount = curPayAmount;
    }
    public String getTransId() {
        return transId;
    }
    public void setTransId(String transId) {
        this.transId = transId;
    }
    public Integer getTransStatus() {
        return transStatus;
    }
    public void setTransStatus(Integer transStatus) {
        this.transStatus = transStatus;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public Object getExtdata() {
        return extdata;
    }
    public void setExtdata(Object extdata) {
        this.extdata = extdata;
    }
    public Long getHadDiscountAmount() {
        return hadDiscountAmount;
    }
    public void setHadDiscountAmount(Long hadDiscountAmount) {
        this.hadDiscountAmount = hadDiscountAmount;
    }
    public Long getCurDiscountAmount() {
        return curDiscountAmount;
    }
    public void setCurDiscountAmount(Long curDiscountAmount) {
        this.curDiscountAmount = curDiscountAmount;
    }
    
}
