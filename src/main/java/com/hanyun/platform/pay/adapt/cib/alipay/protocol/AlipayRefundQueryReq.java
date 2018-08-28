/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 退款查询接口请求参数
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午7:30:49
 */
@XmlRootElement(name = "xml")
public class AlipayRefundQueryReq extends AlipayBaseRequest {
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
    
}
