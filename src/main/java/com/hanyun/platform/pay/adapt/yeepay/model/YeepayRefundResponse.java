package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-11 上午11:51
 */
public class YeepayRefundResponse {
    //退款金额
    private String refundAmount;

    //退款方式
    private String refundWay;

    //退款状态
    private String status;

    //退券列表
    private String refundCoupons;

    //返回码
    private String code;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefundCoupons() {
        return refundCoupons;
    }

    public void setRefundCoupons(String refundCoupons) {
        this.refundCoupons = refundCoupons;
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

    //返回消息
    private String  message;

}
