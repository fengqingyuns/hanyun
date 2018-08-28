/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订单退款接口响应结果
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午7:51:57
 */
@XmlRootElement(name = "xml")
public class OrderRefundRes extends BaseResponse {
    // 微信订单号 transaction_id
    private String transaction_id;
    // 通道订单号 pass_trade_no
    private String pass_trade_no;
    // 商户订单号 out_trade_no
    private String out_trade_no;
    // 商户退款单号 out_refund_no
    private String out_refund_no;
    // 通道退款单号 pass_refund_no
    private String pass_refund_no;
    // 微信退款单号 refund_id
    private String refund_id;
    // 退款渠道 refund_channel
    private String refund_channel;
    // 退款金额 refund_fee
    private Long refund_fee;
    // 代金券或立减优惠退款金额 coupon_refund_fee
    private String coupon_refund_fee;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getPass_trade_no() {
        return pass_trade_no;
    }

    public void setPass_trade_no(String pass_trade_no) {
        this.pass_trade_no = pass_trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getPass_refund_no() {
        return pass_refund_no;
    }

    public void setPass_refund_no(String pass_refund_no) {
        this.pass_refund_no = pass_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public Long getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Long refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(String coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

}
