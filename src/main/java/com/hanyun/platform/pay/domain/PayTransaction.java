package com.hanyun.platform.pay.domain;

import java.util.Date;


public class PayTransaction {

    private Long id;

    private String transId;

    private String payId;

    private String brandId;

    private String storeId;

    private String orderId;
    private String orderDocumentId;

    private Integer operateType;
    
    private String payType;
    private String payTypeDetail;
    private String typeCategory;
    private Long amount;
    private Long chnFee;
    private Long mchFee;

    private Integer status;

    private Date finishTime;
    
    private String channel;

    private Integer settleType;

    private Integer chnFeeRate;

    private Integer chnFeeMax;

    private Integer mchFeeRate;

    private Integer mchFeeMax;
    
    private String sourceType;
    private String terminalDeviceNo;
    private String terminalIp;
    private String operateUser;

    private Date createTime;

    private Date updateTime;
    
    private String srcPayTransId;
    private Integer refundStatus;
    private Long hadRefundAmount;
    private Long discountAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
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

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayTypeDetail() {
        return payTypeDetail;
    }

    public void setPayTypeDetail(String payTypeDetail) {
        this.payTypeDetail = payTypeDetail;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getChnFee() {
        return chnFee;
    }

    public void setChnFee(Long chnFee) {
        this.chnFee = chnFee;
    }

    public Long getMchFee() {
        return mchFee;
    }

    public void setMchFee(Long mchFee) {
        this.mchFee = mchFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getChnFeeRate() {
        return chnFeeRate;
    }

    public void setChnFeeRate(Integer chnFeeRate) {
        this.chnFeeRate = chnFeeRate;
    }

    public Integer getChnFeeMax() {
        return chnFeeMax;
    }

    public void setChnFeeMax(Integer chnFeeMax) {
        this.chnFeeMax = chnFeeMax;
    }

    public Integer getMchFeeRate() {
        return mchFeeRate;
    }

    public void setMchFeeRate(Integer mchFeeRate) {
        this.mchFeeRate = mchFeeRate;
    }

    public Integer getMchFeeMax() {
        return mchFeeMax;
    }

    public void setMchFeeMax(Integer mchFeeMax) {
        this.mchFeeMax = mchFeeMax;
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

    public String getSrcPayTransId() {
        return srcPayTransId;
    }

    public void setSrcPayTransId(String srcPayTransId) {
        this.srcPayTransId = srcPayTransId;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Long getHadRefundAmount() {
        return hadRefundAmount;
    }

    public void setHadRefundAmount(Long hadRefundAmount) {
        this.hadRefundAmount = hadRefundAmount;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }
}