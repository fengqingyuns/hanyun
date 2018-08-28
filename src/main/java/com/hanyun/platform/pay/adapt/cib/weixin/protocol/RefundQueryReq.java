/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 退款查询接口请求参数
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午8:09:59
 */
@XmlRootElement(name = "xml")
public class RefundQueryReq extends BaseRequest {
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

}
