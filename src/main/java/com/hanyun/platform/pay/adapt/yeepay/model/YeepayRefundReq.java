package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-11 下午4:03
 */
public class YeepayRefundReq  extends YeepayConfigureBase{

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    //支付类型
    private String payType;
    //商户请求号
    private String requestNo;
    //原支付请求号
    private String trxRequestNo;
    //退款金额
    private String refundAmount;
    //退回方式
    private String refundWay;
    //指定退券
    private String couponNos;
    //后台服务通知地址
    private String serverCallbackUrl;
    //前端页面通知地址
    private String webCallbackUrl;
    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getTrxRequestNo() {
        return trxRequestNo;
    }

    public void setTrxRequestNo(String trxRequestNo) {
        this.trxRequestNo = trxRequestNo;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(String refundWay) {
        this.refundWay = refundWay;
    }

    public String getCouponNos() {
        return couponNos;
    }

    public void setCouponNos(String couponNos) {
        this.couponNos = couponNos;
    }

    public String getServerCallbackUrl() {
        return serverCallbackUrl;
    }

    public void setServerCallbackUrl(String serverCallbackUrl) {
        this.serverCallbackUrl = serverCallbackUrl;
    }

    public String getWebCallbackUrl() {
        return webCallbackUrl;
    }

    public void setWebCallbackUrl(String webCallbackUrl) {
        this.webCallbackUrl = webCallbackUrl;
    }
}
