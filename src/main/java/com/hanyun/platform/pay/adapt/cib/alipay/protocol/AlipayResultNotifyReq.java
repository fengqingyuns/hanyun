/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 支付结果通知请求参数(类似调用第三方接口的响应结果)
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午6:30:02
 */
@XmlRootElement(name = "xml")
public class AlipayResultNotifyReq extends AlipayBaseResponse {
    // 用户标识 openid
    @XmlElement(name="openid")
    private String openid;
    // 支付宝交易号 transaction_id
    @XmlElement(name="transaction_id")
    private String transactionId;
    // 通道订单号 pass_trade_no
    @XmlElement(name="pass_trade_no")
    private String passTradeNo;
    // 商户订单号 out_trade_no
    @XmlElement(name="out_trade_no")
    private String outTradeNo;
    // 总金额 total_fee
    @XmlElement(name="total_fee")
    private Long totalFee;
    // 货币种类 fee_type
    @XmlElement(name="fee_type")
    private String feeType;
    // 代金券或立减优惠金额 coupon_fee
    @XmlElement(name="coupon_fee")
    private String couponFee;
    // 商家数据包 attach
    @XmlElement(name="attach")
    private String attach;
    // 支付完成时间 time_end
    @XmlElement(name="time_end")
    private String timeEnd;
    // 买家支付宝账号 buyer_logon_id
    @XmlElement(name="buyer_logon_id")
    private String buyerLogonId;
    // 支付金额信息 fund_bill_list
    @XmlElement(name="fund_bill_list")
    private String fundBillList;
    
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
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
    public Long getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }
    public String getFeeType() {
        return feeType;
    }
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    public String getCouponFee() {
        return couponFee;
    }
    public void setCouponFee(String couponFee) {
        this.couponFee = couponFee;
    }
    public String getAttach() {
        return attach;
    }
    public void setAttach(String attach) {
        this.attach = attach;
    }
    public String getTimeEnd() {
        return timeEnd;
    }
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
    public String getBuyerLogonId() {
        return buyerLogonId;
    }
    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }
    public String getFundBillList() {
        return fundBillList;
    }
    public void setFundBillList(String fundBillList) {
        this.fundBillList = fundBillList;
    }
    
}
