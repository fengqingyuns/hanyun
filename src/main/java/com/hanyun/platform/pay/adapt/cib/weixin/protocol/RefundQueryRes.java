/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 退款查询接口响应结果
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午8:10:15
 */
@XmlRootElement(name = "xml")
public class RefundQueryRes extends BaseResponse {
    // 微信订单号 transaction_id
    private String transaction_id;
    // 通道订单号 pass_trade_no
    private String pass_trade_no;
    // 商户订单号 out_trade_no
    private String out_trade_no;
    // 退款笔数 refund_count
    private Integer refund_count;
    // 商户退款单号 out_refund_no_0
    private String out_refund_no_0;
    // 通道退款单号 pass_refund_no_0
    private String pass_refund_no_0;
    // 微信退款单号 refund_id_0
    private String refund_id_0;
    // 退款渠道 refund_channel_0
    private String refund_channel_0;
    // 退款金额 refund_fee_0
    private Long refund_fee_0;
    // 货币类型 fee_type_0
    private String fee_type_0;
    // 代金券或立减优惠退款金额 coupon_refund_fee_0
    private String coupon_refund_fee_0;
    // 退款状态 refund_status_0
    private String refund_status_0;

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

    public Integer getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(Integer refund_count) {
        this.refund_count = refund_count;
    }

    public String getOut_refund_no_0() {
        return out_refund_no_0;
    }

    public void setOut_refund_no_0(String out_refund_no_0) {
        this.out_refund_no_0 = out_refund_no_0;
    }

    public String getPass_refund_no_0() {
        return pass_refund_no_0;
    }

    public void setPass_refund_no_0(String pass_refund_no_0) {
        this.pass_refund_no_0 = pass_refund_no_0;
    }

    public String getRefund_id_0() {
        return refund_id_0;
    }

    public void setRefund_id_0(String refund_id_0) {
        this.refund_id_0 = refund_id_0;
    }

    public String getRefund_channel_0() {
        return refund_channel_0;
    }

    public void setRefund_channel_0(String refund_channel_0) {
        this.refund_channel_0 = refund_channel_0;
    }

    public Long getRefund_fee_0() {
        return refund_fee_0;
    }

    public void setRefund_fee_0(Long refund_fee_0) {
        this.refund_fee_0 = refund_fee_0;
    }

    public String getFee_type_0() {
        return fee_type_0;
    }

    public void setFee_type_0(String fee_type_0) {
        this.fee_type_0 = fee_type_0;
    }

    public String getCoupon_refund_fee_0() {
        return coupon_refund_fee_0;
    }

    public void setCoupon_refund_fee_0(String coupon_refund_fee_0) {
        this.coupon_refund_fee_0 = coupon_refund_fee_0;
    }

    public String getRefund_status_0() {
        return refund_status_0;
    }

    public void setRefund_status_0(String refund_status_0) {
        this.refund_status_0 = refund_status_0;
    }

}
