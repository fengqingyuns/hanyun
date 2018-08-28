/**
 * 
 */
package com.hanyun.platform.pay.domain;

import java.util.Date;

/**
 * 
* @Description: 结算明细
* @author wangjie@hanyun.com
* @date 2016年9月3日 上午11:15:26
 */
public class SettlementDetailReq {
    private String transId;//汉云交易流水号
	private String wxTransId;//微信交易流水号
	private String refundId;//汉云退费流水
    private Long amount;//金额
    private Integer status;//交易状态
    private Date finishTime;
    private Long chargeFee;//手续费
    private Long chargeFeeRate;//费率
    
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getWxTransId() {
		return wxTransId;
	}
	public void setWxTransId(String wxTransId) {
		this.wxTransId = wxTransId;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
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
	public Long getChargeFee() {
		return chargeFee;
	}
	public void setChargeFee(Long chargeFee) {
		this.chargeFee = chargeFee;
	}
	public Long getChargeFeeRate() {
		return chargeFeeRate;
	}
	public void setChargeFeeRate(Long chargeFeeRate) {
		this.chargeFeeRate = chargeFeeRate;
	}

}
