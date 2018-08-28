package com.hanyun.platform.pay.adapt.yeepay.protocol;

/**
 * @author litao@hanyun.com
 * @date 2018年8月8日
 * @apiDefine Common 易宝回调返回实体
 */
public class YeeResultNotifyRes {
    //订单状态
    private String status;
    //需支付金额
    private String fundAmount;
    //订单类型
    private String orderType;
    //卡号后四位
    private String cardLast;
    //返回码
    private String code;
    //银行卡类别
    private String cardType;
    //银行编码
    private String bankCode;
    //返回消息
    private String message;
    //原支付请求号
    private String requestNo;
    //支付方式
    private String payTool;
    //订单金额
    private String orderAmount;
    //已付金额
    private String paidAmount;
    //商户用户标识
    private String merchantUserId;
    //营销信息
    private String couponInfo;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCardLast() {
        return cardLast;
    }

    public void setCardLast(String cardLast) {
        this.cardLast = cardLast;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getPayTool() {
        return payTool;
    }

    public void setPayTool(String payTool) {
        this.payTool = payTool;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(String merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }
}
