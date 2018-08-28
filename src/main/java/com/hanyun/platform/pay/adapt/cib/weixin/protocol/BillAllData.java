/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;


/**
 * 对账单数据，单据类型为 ALL
 * @author liyinglong@hanyun.com
 * @date 2016年8月16日 下午4:31:26
 */
public class BillAllData {
    // 交易时间
    private String tradeTime;
    // 应用 ID appid
    private String appid;
    // 商户号 mch_id
    private String mchId;
    // 设备号 device_info
    private String deviceInfo;
    // 微信支付订单号 transaction_id
    private String transactionId;
    // 商户订单号 out_trade_no
    private String outTradeNo;
    // 用户标识 openid
    private String openid;
    // 交易类型 trade_type
    private String tradeType;
    // 交易状态 trade_state
    private String tradeState;
    // 付款银行 bank_type
    private String bankType;
    // 货币种类 fee_type
    private String feeType;
    // 总金额 total_fee
    private Integer totalFee;
    // 代金券或立减优惠金额 coupon_fee
    private Integer couponFee;
    // 微信退款单号 refund_id
    private String refundId;
    // 商户退款单号 out_refund_no
    private String outRefundNo;
    // 退款金额 refund_fee
    private Integer refundFee;
    // 代金券或立减优惠退款金额 coupon_refund_fee
    private Integer couponRefundFee;
    // 退款类型 refund_type
    private String refundType;
    // 退款状态 refund_status_0
    private String refundStatus;
    // 商家描述 goods body
    private String goodsBody;
    // 商家数据包 attach
    private String attach;
    // 手续费 charges
    private Long charges;
    // 费率 charges_rate
    private Long chargesRate;
    
    public String getTradeTime() {
        return tradeTime;
    }
    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMchId() {
        return mchId;
    }
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    public String getDeviceInfo() {
        return deviceInfo;
    }
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getTradeType() {
        return tradeType;
    }
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    public String getTradeState() {
        return tradeState;
    }
    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }
    public String getBankType() {
        return bankType;
    }
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    public String getFeeType() {
        return feeType;
    }
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    public Integer getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }
    public Integer getCouponFee() {
        return couponFee;
    }
    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }
    public String getRefundId() {
        return refundId;
    }
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
    public String getOutRefundNo() {
        return outRefundNo;
    }
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
    public Integer getRefundFee() {
        return refundFee;
    }
    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }
    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }
    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }
    public String getRefundType() {
        return refundType;
    }
    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }
    public String getRefundStatus() {
        return refundStatus;
    }
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    public String getGoodsBody() {
        return goodsBody;
    }
    public void setGoodsBody(String goodsBody) {
        this.goodsBody = goodsBody;
    }
    public String getAttach() {
        return attach;
    }
    public void setAttach(String attach) {
        this.attach = attach;
    }
	public Long getCharges() {
		return charges;
	}
	public void setCharges(Long charges) {
		this.charges = charges;
	}
	public Long getChargesRate() {
		return chargesRate;
	}
	public void setChargesRate(Long chargesRate) {
		this.chargesRate = chargesRate;
	}
}
