/**
 * 
 */
package com.hanyun.platform.pay.domain;

import java.util.Date;

/**
 * 结算单明细
 * @author liyinglong@hanyun.com
 * @date 2016年7月25日 下午10:11:15
 */
public class SettlementDetail {
    private String payId;
    private String brandId;
    private String storeId;
    private String orderId;
    private Date orderTime;
    private Long orderAmount;
    private Long payAmount;
    private String payType;
    private Integer mchFeeRate;
    private Integer settleType;
    private Integer operateType;
    private Long amount;
    private Integer status;
    private Date finishTime;
    private double poundage;//手续费
    private String transId;
    private Date solveTime;
    private String diffStatus;
    private Integer mchFeeMax;
    
    
    public Integer getMchFeeMax() {
        return mchFeeMax;
    }
    public void setMchFeeMax(Integer mchFeeMax) {
        this.mchFeeMax = mchFeeMax;
    }
    public String getDiffStatus() {
        return diffStatus;
    }
    public void setDiffStatus(String diffStatus) {
        this.diffStatus = diffStatus;
    }
    public Date getSolveTime() {
        return solveTime;
    }
    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
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
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }

    
    public Integer getMchFeeRate() {
        return mchFeeRate;
    }
    public void setMchFeeRate(Integer mchFeeRate) {
        this.mchFeeRate = mchFeeRate;
    }

    public Integer getSettleType() {
		return settleType;
	}
	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	public Integer getOperateType() {
        return operateType;
    }
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
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
	public double getPoundage() {
		return poundage;
	}
	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}
    
}
