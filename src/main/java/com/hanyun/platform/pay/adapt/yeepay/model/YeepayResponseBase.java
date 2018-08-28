package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-07 下午1:48
 */
public class YeepayResponseBase {
    //商户请求号
    private String requestNo;
    //支付回调地址
    private String redirectUrl;
    //订单金额
    private String orderAmount;
    //需支付金额
    private String fundAmount;
    //状态
    private String status;
    //返回码
    private String code;
    //返回消息
    private String message;
    //实时分账状态
    private String divideCheck;
    //实时分账信息
    private String divideErrorMassage;

    

    public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDivideCheck() {
        return divideCheck;
    }

    public void setDivideCheck(String divideCheck) {
        this.divideCheck = divideCheck;
    }

    public String getDivideErrorMassage() {
        return divideErrorMassage;
    }

    public void setDivideErrorMassage(String divideErrorMassage) {
        this.divideErrorMassage = divideErrorMassage;
    }

	@Override
	public String toString() {
		return "YeepayResponseBase [requestNo=" + requestNo + ", redirectUrl=" + redirectUrl + ", orderAmount="
				+ orderAmount + ", fundAmount=" + fundAmount + ", status=" + status + ", code=" + code + ", message="
				+ message + ", divideCheck=" + divideCheck + ", divideErrorMassage=" + divideErrorMassage + "]";
	}
    
    
}
