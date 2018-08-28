package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * 
 * @date 2018年8月13日
 * 
 * @apiDefine Common 主动查询支付
 * @author litao@hanyun.com
 */
public class YeeQueryOrderRes extends YeepayResponseBase {
	// 商户用户标识
	private String merchantUserId; 
	// 需支付金额
	private String fundAmount; 
	// 已付金额
	private String paidAmount; 
	// 支付方式
	private String payTool; 
	// 卡号后四位
	private String cardLast;
	// 银行卡类别
	private String cardType; 
	// 银行编码
	private String bankCode; 
	// 营销信息
	private String couponInfo; 
	//支付密码
	private String needPassword;
	public String getMerchantUserId() {
		return merchantUserId;
	}
	public void setMerchantUserId(String merchantUserId) {
		this.merchantUserId = merchantUserId;
	}
	public String getFundAmount() {
		return fundAmount;
	}
	public void setFundAmount(String fundAmount) {
		this.fundAmount = fundAmount;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getPayTool() {
		return payTool;
	}
	public void setPayTool(String payTool) {
		this.payTool = payTool;
	}
	public String getCardLast() {
		return cardLast;
	}
	public void setCardLast(String cardLast) {
		this.cardLast = cardLast;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getCouponInfo() {
		return couponInfo;
	}
	public void setCouponInfo(String couponInfo) {
		this.couponInfo = couponInfo;
	}
	public String getNeedPassword() {
		return needPassword;
	}
	public void setNeedPassword(String needPassword) {
		this.needPassword = needPassword;
	}
	@Override
	public String toString() {
		return "YeeQueryOrderRes [merchantUserId=" + merchantUserId + ", fundAmount=" + fundAmount + ", paidAmount="
				+ paidAmount + ", payTool=" + payTool + ", cardLast=" + cardLast + ", cardType=" + cardType
				+ ", bankCode=" + bankCode + ", couponInfo=" + couponInfo + ", needPassword=" + needPassword + "]";
	}

	

}
