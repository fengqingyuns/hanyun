/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

import java.util.Date;

/**
 * 支付单查询
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午3:29:21
 */
public class TradeQueryRes {
    // 支付单编号
    private String payId;
    // 品牌编号
    private String brandId;
    // 门店编号
    private String storeId;
    // 订单编号
    private String orderId;
    // 订单时间
    private Date orderTime;
    // 订单金额
    private Long orderAmount;
    // 需付金额
    private Long payAmount;
    // 已付金额
    private Long hadPayAmount;
    // 已成功支付次数
    private Integer hadPayCount;
    // 已优惠金额
    private Long hadDiscountAmount;
    // 已退款金额
    private Long refundAmount;
    // 支付方式
    private String payType;
    // 支付状态
    private Integer payStatus;
    // 支付时间
    private Date payTime;
    
    public String getPayId() {
        return payId;
    }
    public void setPayId(String payId) {
        this.payId = payId;
    }
    public String getBrandId() {
        return brandId;
    }
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Date getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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
    public Integer getHadPayCount() {
        return hadPayCount;
    }
    public void setHadPayCount(Integer hadPayCount) {
        this.hadPayCount = hadPayCount;
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
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public Integer getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
    public Date getPayTime() {
        return payTime;
    }
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
}
