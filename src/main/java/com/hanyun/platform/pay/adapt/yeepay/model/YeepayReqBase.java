package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * Created with IntelliJ IDEA. Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-07 上午10:12
 */
public class YeepayReqBase extends YeepayConfigureBase{
	
	//支付请求号
	private String requestNo;
	//商户用户标识,在用户账户注册时由商户生成的用户会员编号，使用余额支付或绑卡支付时必填 【可为空】
	private String merchantUserId;
	//订单金额
	private String orderAmount;
	//需支付金额
	private String fundAmount;
	//指定支付方式
	private String payTool;
	//支付下单时间,格式：yyyy-MM-dd HH:mm:ss
	private String merchantOrderDate;
	//订单有效时间，单位：分钟
	private int merchantExpireTime;
	//风控参数
	private String trxExtraInfo;
	//后台服务通知地址
	private String serverCallbackUrl;
	//前段页面通知地址，成功后页面返回地址，必填
	private String webCallbackUrl;
	//行业类别
	private String mcc;
	//产品类别，与mcc填写相同的值
	private String productCatalog;
	//商品名称
	private String productName;
	//商品描述
	private String productDesc;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	//ip
	private String ip;

	//商户业务类型，默认不传IGNORE_PASSWORD-小额免密
	private String merchantBizType;
	//分账规则，
	private String divideRuleType;
	//分账详情
	private String divideDetail;
	//分账成功回调地址
	private String divideCallbackUrl;
	//付款方商编
	private String accountPayMerchantNo;
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getMerchantUserId() {
		return merchantUserId;
	}
	public void setMerchantUserId(String merchantUserId) {
		this.merchantUserId = merchantUserId;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getFundAmount() {
		return fundAmount;
	}
	public void setFundAmount(String fundAmount) {
		this.fundAmount = fundAmount;
	}
	public String getPayTool() {
		return payTool;
	}
	public String getMerchantOrderDate() {
		return merchantOrderDate;
	}
	public void setMerchantOrderDate(String merchantOrderDate) {
		this.merchantOrderDate = merchantOrderDate;
	}
	public int getMerchantExpireTime() {
		return merchantExpireTime;
	}
	public void setMerchantExpireTime(int merchantExpireTime) {
		this.merchantExpireTime = merchantExpireTime;
	}
	public String getTrxExtraInfo() {
		return trxExtraInfo;
	}
	public void setTrxExtraInfo(String trxExtraInfo) {
		this.trxExtraInfo = trxExtraInfo;
	}
	public String getServerCallbackUrl() {
		return serverCallbackUrl;
	}
	public void setServerCallbackUrl(String serverCallbackUrl) {
		this.serverCallbackUrl = serverCallbackUrl;
	}
	public String getWebCallbackUrl() {
		return webCallbackUrl;
	}
	public void setWebCallbackUrl(String webCallbackUrl) {
		this.webCallbackUrl = webCallbackUrl;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getProductCatalog() {
		return productCatalog;
	}
	public void setProductCatalog(String productCatalog) {
		this.productCatalog = productCatalog;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getMerchantBizType() {
		return merchantBizType;
	}
	public void setMerchantBizType(String merchantBizType) {
		this.merchantBizType = merchantBizType;
	}
	public String getDivideRuleType() {
		return divideRuleType;
	}
	public void setDivideRuleType(String divideRuleType) {
		this.divideRuleType = divideRuleType;
	}
	public String getDivideDetail() {
		return divideDetail;
	}
	public void setDivideDetail(String divideDetail) {
		this.divideDetail = divideDetail;
	}
	public String getDivideCallbackUrl() {
		return divideCallbackUrl;
	}
	public void setDivideCallbackUrl(String divideCallbackUrl) {
		this.divideCallbackUrl = divideCallbackUrl;
	}
	public String getAccountPayMerchantNo() {
		return accountPayMerchantNo;
	}
	public void setAccountPayMerchantNo(String accountPayMerchantNo) {
		this.accountPayMerchantNo = accountPayMerchantNo;
	}
	public void setPayTool(String payTool) {
		this.payTool = payTool;
	}
}
