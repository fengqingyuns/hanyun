/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订单退款接口请求参数
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午7:15:51
 */
@XmlRootElement(name = "xml")
public class AlipayRefundReq extends AlipayBaseRequest {
    // 支付宝订单号 transaction_id
    @XmlElement(name="transaction_id")
    private String transactionId;
    // 通道订单号 pass_trade_no
    @XmlElement(name="pass_trade_no")
    private String passTradeNo;
    // 商户订单号 out_trade_no
    @XmlElement(name="out_trade_no")
    private String outTradeNo;
    // 商户退款单号 out_refund_no
    @XmlElement(name="out_refund_no")
    private String outRefundNo;
    // 退款金额 refund_fee
    @XmlElement(name="refund_fee")
    private Long refundFee;
    // 货币类型 refund_fee_type
    @XmlElement(name="refund_fee_type")
    private String refundFeeType;
    // 操作员 op_user_id
    @XmlElement(name="op_user_id")
    private String opUserId;
    
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getPassTradeNo() {
        return passTradeNo;
    }
    public void setPassTradeNo(String passTradeNo) {
        this.passTradeNo = passTradeNo;
    }
    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getOutRefundNo() {
        return outRefundNo;
    }
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
    public Long getRefundFee() {
        return refundFee;
    }
    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }
    public String getRefundFeeType() {
        return refundFeeType;
    }
    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }
    public String getOpUserId() {
        return opUserId;
    }
    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }
    
}
