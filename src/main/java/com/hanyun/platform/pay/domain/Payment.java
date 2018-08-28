package com.hanyun.platform.pay.domain;

import java.util.Date;

public class Payment {

    private Long id;

    private String payId;

    private String brandId;

    private String storeId;
    private String businessType;

    private String orderId;
    private String orderDocumentId;

    private Date orderTime;
    // 订单金额
    private Long orderAmount;
    // 订单需付金额
    private Long payAmount;
    // 已支付金额
    private Long hadPayAmount;
    // 已成功付款次数
    private Integer hadPayCount;
    // 已优惠金额，比如抹零抹掉的金额
    private Long hadDiscountAmount;
    // 已退款金额
    private Long refundAmount;

    private Integer payStatus;

    private Date payTime;
    
    private Date refundTime;
    
    private String payType;
    
    private String typeCategory;
    
    private String sourceType;
    private String terminalDeviceNo;
    private String terminalIp;
    private String operateUser;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDocumentId() {
        return orderDocumentId;
    }

    public void setOrderDocumentId(String orderDocumentId) {
        this.orderDocumentId = orderDocumentId;
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

//    public String getPayType() {
//        return payType;
//    }
//
//    public void setPayType(String payType) {
//        this.payType = payType;
//    }
//
//    public String getTypeCategory() {
//        return typeCategory;
//    }
//
//    public void setTypeCategory(String typeCategory) {
//        this.typeCategory = typeCategory;
//    }
//
//    public String getChannel() {
//        return channel;
//    }
//
//    public void setChannel(String channel) {
//        this.channel = channel;
//    }
//
//    public Integer getSettleType() {
//        return settleType;
//    }
//
//    public void setSettleType(Integer settleType) {
//        this.settleType = settleType;
//    }
//
//    public Integer getChnFeeRate() {
//        return chnFeeRate;
//    }
//
//    public void setChnFeeRate(Integer chnFeeRate) {
//        this.chnFeeRate = chnFeeRate;
//    }
//
//    public Integer getChnFeeMax() {
//        return chnFeeMax;
//    }
//
//    public void setChnFeeMax(Integer chnFeeMax) {
//        this.chnFeeMax = chnFeeMax;
//    }
//
//    public Integer getMchFeeRate() {
//        return mchFeeRate;
//    }
//
//    public void setMchFeeRate(Integer mchFeeRate) {
//        this.mchFeeRate = mchFeeRate;
//    }
//
//    public Integer getMchFeeMax() {
//        return mchFeeMax;
//    }
//
//    public void setMchFeeMax(Integer mchFeeMax) {
//        this.mchFeeMax = mchFeeMax;
//    }

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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getTerminalDeviceNo() {
        return terminalDeviceNo;
    }

    public void setTerminalDeviceNo(String terminalDeviceNo) {
        this.terminalDeviceNo = terminalDeviceNo;
    }

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getHadPayCount() {
        return hadPayCount;
    }

    public void setHadPayCount(Integer hadPayCount) {
        this.hadPayCount = hadPayCount;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }
}