/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 退款查询接口响应结果
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午7:31:12
 */
@XmlRootElement(name = "xml")
public class AlipayRefundQueryRes extends AlipayBaseResponse {
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
    // 通道退款单号 pass_refund_no
    @XmlElement(name="pass_refund_no")
    private String passRefundNo;
    // 退款金额 refund_fee
    @XmlElement(name="refund_fee")
    private Long refundFee;
    // 货币类型 fee_type
    @XmlElement(name="fee_type")
    private String feeType;
    // 资金变化标识 refund_status
    @XmlElement(name="refund_status")
    private String refundStatus;
    // 退款支付时间 gmt_refund_pay
    @XmlElement(name="gmt_refund_pay")
    private String gmtRefundPay;
    // 退款使用的资金渠道 refund_detail_item_list
    @XmlElement(name="refund_detail_item_list")
    private String refundDetailItemList;
    
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
    public String getPassRefundNo() {
        return passRefundNo;
    }
    public void setPassRefundNo(String passRefundNo) {
        this.passRefundNo = passRefundNo;
    }
    public Long getRefundFee() {
        return refundFee;
    }
    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }
    public String getFeeType() {
        return feeType;
    }
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    public String getRefundStatus() {
        return refundStatus;
    }
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    public String getGmtRefundPay() {
        return gmtRefundPay;
    }
    public void setGmtRefundPay(String gmtRefundPay) {
        this.gmtRefundPay = gmtRefundPay;
    }
    public String getRefundDetailItemList() {
        return refundDetailItemList;
    }
    public void setRefundDetailItemList(String refundDetailItemList) {
        this.refundDetailItemList = refundDetailItemList;
    }
    
}
