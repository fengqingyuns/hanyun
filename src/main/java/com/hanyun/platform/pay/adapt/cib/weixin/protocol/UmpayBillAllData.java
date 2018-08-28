/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;


/**
 * 对账单数据，单据类型为 ALL
 * @author wangjie@hanyun.com
 * @date 2016年12月18日 下午18:31:26
 */
public class UmpayBillAllData {
    //机构号
    private String organizationNumber;
    //外部商户号
    private String outMerId;
    //商户名称
    private String MerName;
    //结算日期
    private String settlementDate;
    //终端号
    private String terminalNumber;
    //交易类型
    private String tradeType;
    //脱敏卡号
    private String cardNumber;
    //支付日期时间
    private String payDate;
    //商户订单号
    private String merOrderId;
    //终端流水号
    private String terminalSerialNumber;
    //参考号
    private String referenceNo;
    //交易流水号
    private String transactionSerialNumber;
    //支付金额（元）
    private Integer payAmount;
    //结算金额（元）
    private Integer  settlementAmount;
    //手续费（元）
    private Integer  fee; 
    //代理商级别
    private String agencyLevel;
    // 当前代理商
    private String currentAgent;
    // 当前代理商名称
    private String currentAgentName;
    //上级代理商
    private String higherLevelAgents;
    // 上级代理商名称
    private String higherLevelAgentsName;
    // 上上级代理商
    private String higherHigherLevelAgents;
    // 上上级代理商名称
    private String higherHigherLevelName;
    // 结算类型
    private String settlementType;
    // 增值业务类型
    private String valueAddedServiceType;
    // 卡类型
    private String cardType;
    //通道 ID
    private String channelId;
    // 预授权码
    private String authorizationCode;
	public String getOrganizationNumber() {
		return organizationNumber;
	}
	public void setOrganizationNumber(String organizationNumber) {
		this.organizationNumber = organizationNumber;
	}
	public String getOutMerId() {
		return outMerId;
	}
	public void setOutMerId(String outMerId) {
		this.outMerId = outMerId;
	}
	public String getMerName() {
		return MerName;
	}
	public void setMerName(String merName) {
		MerName = merName;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public String getTerminalNumber() {
		return terminalNumber;
	}
	public void setTerminalNumber(String terminalNumber) {
		this.terminalNumber = terminalNumber;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getTerminalSerialNumber() {
		return terminalSerialNumber;
	}
	public void setTerminalSerialNumber(String terminalSerialNumber) {
		this.terminalSerialNumber = terminalSerialNumber;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getTransactionSerialNumber() {
		return transactionSerialNumber;
	}
	public void setTransactionSerialNumber(String transactionSerialNumber) {
		this.transactionSerialNumber = transactionSerialNumber;
	}
	public Integer getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	public Integer getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(Integer settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public String getAgencyLevel() {
		return agencyLevel;
	}
	public void setAgencyLevel(String agencyLevel) {
		this.agencyLevel = agencyLevel;
	}
	public String getCurrentAgent() {
		return currentAgent;
	}
	public void setCurrentAgent(String currentAgent) {
		this.currentAgent = currentAgent;
	}
	public String getCurrentAgentName() {
		return currentAgentName;
	}
	public void setCurrentAgentName(String currentAgentName) {
		this.currentAgentName = currentAgentName;
	}
	public String getHigherLevelAgents() {
		return higherLevelAgents;
	}
	public void setHigherLevelAgents(String higherLevelAgents) {
		this.higherLevelAgents = higherLevelAgents;
	}
	public String getHigherLevelAgentsName() {
		return higherLevelAgentsName;
	}
	public void setHigherLevelAgentsName(String higherLevelAgentsName) {
		this.higherLevelAgentsName = higherLevelAgentsName;
	}
	public String getHigherHigherLevelAgents() {
		return higherHigherLevelAgents;
	}
	public void setHigherHigherLevelAgents(String higherHigherLevelAgents) {
		this.higherHigherLevelAgents = higherHigherLevelAgents;
	}
	public String getHigherHigherLevelName() {
		return higherHigherLevelName;
	}
	public void setHigherHigherLevelName(String higherHigherLevelName) {
		this.higherHigherLevelName = higherHigherLevelName;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	public String getValueAddedServiceType() {
		return valueAddedServiceType;
	}
	public void setValueAddedServiceType(String valueAddedServiceType) {
		this.valueAddedServiceType = valueAddedServiceType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}  
    
}
