/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订单退款接口请求参数
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午7:51:44
 */
@XmlRootElement(name = "xml")
public class OrderRefundReq extends BaseRequest {
    // 微信订单号 transaction_id
    private String transaction_id;
    // 通道订单号 pass_trade_no
    private String pass_trade_no;
    // 商户订单号 out_trade_no
    private String out_trade_no;
    // 商户退款单号 out_refund_no
    private String out_refund_no;
    // 总金额 total_fee
    private Long total_fee;
    // 退款金额 refund_fee
    private Long refund_fee;
    // 货币类型 refund_fee_type
    private String refund_fee_type;
    // 操作员 op_user_id
    private String op_user_id;

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

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public Long getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Long refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

}
