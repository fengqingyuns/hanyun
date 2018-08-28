/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 支付结果通知请求参数(类似调用第三方接口的响应结果)
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月14日 下午6:10:45
 */
@XmlRootElement(name = "xml")
public class PayResultNotifyReq extends BaseResponse {
    // 用户标识 openid
    private String openid;
    // 是否关注公众账号 is_subscribe
    private String is_subscribe;
    // 交易类型 trade_type
    private String trade_type;
    // 付款银行 bank_type
    private String bank_type;
    // 总金额 total_fee
    private Integer total_fee;
    // 货币种类 fee_type
    private String fee_type;
    // 代金券或立减优惠金额 coupon_fee
    private Integer coupon_fee;
    // 微信支付订单号 transaction_id
    private String transaction_id;
    // 通道订单号 pass_trade_no
    private String pass_trade_no;
    // 商户订单号 out_trade_no
    private String out_trade_no;
    // 商家数据包 attach
    private String attach;
    // 支付完成时间 time_end
    private String time_end;

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

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Integer coupon_fee) {
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
