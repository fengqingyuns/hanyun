package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * 
 * @date 2018年8月11日
 * 
 * @apiDefine Common 易宝退款返回
 * @author litao@hanyun.com
 */
public class YeeRefundRes {
	// 原分账请求号
	private String requestNo;
	// 返回码
	private String code;
	// 返回消息
	private String message;
	// 状态
	private String status;
	// 原支付请求号
	private String trxRequestNo;
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrxRequestNo() {
		return trxRequestNo;
	}
	public void setTrxRequestNo(String trxRequestNo) {
		this.trxRequestNo = trxRequestNo;
	}
	
	

}
