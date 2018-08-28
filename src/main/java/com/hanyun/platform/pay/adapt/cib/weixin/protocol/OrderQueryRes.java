/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订单查询接口响应结果
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月14日 下午6:34:07
 */
@XmlRootElement(name = "xml")
public class OrderQueryRes extends BaseResponse {
    // 交易状态 trade_state
    private String trade_state;
    // 交易状态描述 trade_state_desc
    private String trade_state_desc;
    // 设备号 device_info
    private String device_info;
    // 用户标识 openid
    private String openid;
    // 是否关注公众号 is_subscribe
    private String is_subscribe;
    // 交易类型 trade_type
    private String trade_type;
    // 付款银行 bank_type
    private String bank_type;
    // 货币种类 fee_type
    private String fee_type;
    // 总金额 total_fee
    private Long total_fee;
    // 代金券或立减优惠金额 coupon_fee
    private String coupon_fee;
    // 微信订单号 transaction_id
    private String transaction_id;
    // 通道订单号 pass_trade_no
    private String pass_trade_no;
    // 商户订单号 out_trade_no
    private String out_trade_no;
    // 商家数据包 attach
    private String attach;
    // 支付完成时间 time_end
    private String time_end;

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    @Override
    public String getDevice_info() {
        return device_info;
    }

    @Override
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

}
