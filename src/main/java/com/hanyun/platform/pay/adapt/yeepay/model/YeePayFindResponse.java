package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-13 下午3:47
 */
public class YeePayFindResponse extends  YeepayResponseBase {
    //已付金额
    private String paidAmount;
    //商户用户标识
    private String merchantUserId;
    //支付方式
    private String payTool;
    //卡号后四位
    private String cardLast;
    //银行卡类别
    private String cardType;
    //银行编码
    private String bankCode;
    //营销信息
    private String couponInfo;
    //小额免密超限支付密码
    private String needPassword;
    //输入支付密码URL
    private String redirectUrl;

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
    public String getRedirectUrl() {
        return redirectUrl;
    }

    @Override
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}

