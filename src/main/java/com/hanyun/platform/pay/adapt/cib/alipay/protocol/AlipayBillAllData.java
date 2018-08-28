package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import java.util.Date;

/** 
* @Description: 支付宝对账单数据
* @author wangjie@hanyun.com
* @date 2017年1月5日 下午5:54:53
*/
public class AlipayBillAllData {
    // 商户号 mch_id
    private String mchId;
    // 支付宝支付订单号 transaction_id
    private String transactionId;
    // 商户订单号 out_trade_no
    private String outTradeNo;    
    // 业务类型 business_type
    private String businessType;
    // 商品名称 productName
    private String productName;
    // 创建时间 
    private Date createTime;
    // 完成时间
    private Date finishTime;
    //门店编号  storeId
    private String storeId;   
    //门店名称
    private String storeName;
    //操作员  operator
    private String operator;
    // 设备号 device_info
    private String deviceInfo;    
    // 对方账户 other_Account
    private String otherAccount;
    // 订单金额 order_amount
    private Long orderAmount;
    // 商家实收金额 商家实收（元）
    private Long mchAmount;
    //支付宝红包（元）
    private Long alipayRed;
    //集分宝（元）
    private Long jifenbao;
    // 支付宝优惠 代金券或立减优惠金额 coupon_fee
    private Long couponFee;
    //商家优惠（元）   
    private Long mchCoupon;
    //券核销金额（元）   
    private Long certificates;
    //券名称       
    private String ticketName;
    //商家红包消费金额（元
    private Long mchRed;
    //卡消费金额（元）   
    private Long cardConsumption;
    //退款批次号 refundBatchNumber
    private String refundBatchNumber;    
    //手续费 charges
    private Long charges;
    //费率 charges_rate
    private Long chargesRate;
    //实收净额  Net receipts
    private Long netReceipts;
    // 交易方式 trade_type
    private String tradeType;
    //备注 Remarks
    private String remarks;
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
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
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getOtherAccount() {
		return otherAccount;
	}
	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}
	public Long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Long getMchAmount() {
		return mchAmount;
	}
	public void setMchAmount(Long mchAmount) {
		this.mchAmount = mchAmount;
	}
	public Long getAlipayRed() {
		return alipayRed;
	}
	public void setAlipayRed(Long alipayRed) {
		this.alipayRed = alipayRed;
	}
	public Long getJifenbao() {
		return jifenbao;
	}
	public void setJifenbao(Long jifenbao) {
		this.jifenbao = jifenbao;
	}
	public Long getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(Long couponFee) {
		this.couponFee = couponFee;
	}
	public Long getMchCoupon() {
		return mchCoupon;
	}
	public void setMchCoupon(Long mchCoupon) {
		this.mchCoupon = mchCoupon;
	}
	public Long getCertificates() {
		return certificates;
	}
	public void setCertificates(Long certificates) {
		this.certificates = certificates;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public Long getMchRed() {
		return mchRed;
	}
	public void setMchRed(Long mchRed) {
		this.mchRed = mchRed;
	}
	public Long getCardConsumption() {
		return cardConsumption;
	}
	public void setCardConsumption(Long cardConsumption) {
		this.cardConsumption = cardConsumption;
	}
	public String getRefundBatchNumber() {
		return refundBatchNumber;
	}
	public void setRefundBatchNumber(String refundBatchNumber) {
		this.refundBatchNumber = refundBatchNumber;
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
	public Long getNetReceipts() {
		return netReceipts;
	}
	public void setNetReceipts(Long netReceipts) {
		this.netReceipts = netReceipts;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
