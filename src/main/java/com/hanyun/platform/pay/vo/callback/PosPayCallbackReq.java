/**
 * 
 */
package com.hanyun.platform.pay.vo.callback;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POS刷卡支付回调信息
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年5月9日 下午4:19:44
 */
public class PosPayCallbackReq {
    /** 支付金额，单位分 pay_fee=1 */
    @JsonProperty(value = "pay_fee")
    private Long payFee;
    
    /** 订单总金额，单位分 total_fee=1 */
    @JsonProperty(value = "total_fee")
    private Long totalFee;
    
    /** 商户订单号 out_trade_no=110201705091211037594081087 */
    @JsonProperty(value = "out_trade_no")
    private String outTradeNo;
    
    /** 第三方流水号，银行卡支付时为参考号 thirdSerialNo=091211157085 */
    @JsonProperty(value = "thirdSerialNo")
    private String thirdSerialNo;
    
    /** 批次 batchNo=010170 */
    @JsonProperty(value = "batchNo")
    private String batchNo;
    
    /** 付款方式 pay_type=1006 */
    @JsonProperty(value = "pay_type")
    private String payType;
    
    /** 银行卡类型 cardType=借记卡 */
    @JsonProperty(value = "cardType")
    private String cardType;
    
    /** 交易状态 trade_status=PAY */
    @JsonProperty(value = "trade_status")
    private String tradeStatus;
    
    /** 交易状态说明 pay_info=支付成功 */
    @JsonProperty(value = "pay_info")
    private String payInfo;
    
    /** 付款用户信息，银行卡支付时为银行卡号 buyer=6217000010043648888 */
    @JsonProperty(value = "buyer")
    private String buyer;
    
    /** 订单创建时间 time_create=20170509121110 */
    @JsonProperty(value = "time_create")
    private String timeCreate;
    
    /** 支付时间 time_end=20170509121124 */
    @JsonProperty(value = "time_end")
    private String timeEnd;
    
    /** 收银订单号 cashier_trade_no=10001828572017050900000050 */
    @JsonProperty(value = "cashier_trade_no")
    private String cashierTradeNo;
    
    /** 旺POS设备En号 device_en=369de6cc */
    @JsonProperty(value = "device_en")
    private String deviceEn;
    
    /** 旺POS店铺编号 mcode=182857 */
    @JsonProperty(value = "mcode")
    private String mcode;
    
    /** 收银操作员 operator_name=匿名用户 */
    @JsonProperty(value = "operator_name")
    private String operatorName;
    
    /** bp_id=576b5ce1fa0bab1b0cfd1217 */
    @JsonProperty(value = "bp_id")
    private String bpId;
    
    /** input_charset=UTF-8 */
    @JsonProperty(value = "input_charset")
    private String inputCharset;
    
    /** 商家在支付宝、微信等渠道优惠金额，钱由商家自己付出，单位分 thirdMDiscount=0 */
    @JsonProperty(value = "thirdMDiscount")
    private String thirdMDiscount;
    
    /** 渠道优惠金额，钱会补贴给商家，单位分 thirdDiscount=0 */
    @JsonProperty(value = "thirdDiscount")
    private String thirdDiscount;
    
    /** 旺POS会员优惠金额 check_fee=0 */
    @JsonProperty(value = "check_fee")
    private String checkFee;
    
    /** sign_type=MD5 */
    @JsonProperty(value = "sign_type")
    private String signType;
    
    /** 随机字符串 nonce=jHSldW8uynuVU8V8 */
    @JsonProperty(value = "nonce")
    private String nonce;
    
    /** sign=4b39a627def2e2a516a625fa4f3a9107 */
    @JsonProperty(value = "sign")
    private String sign;
    
    /** 下单时传入的参数原样返回 attach=369de6cc */
    @JsonProperty(value = "attach")
    private String attach;
   

    public Long getPayFee() {
        return payFee;
    }

    public void setPayFee(Long payFee) {
        this.payFee = payFee;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBpId() {
        return bpId;
    }

    public void setBpId(String bpId) {
        this.bpId = bpId;
    }

    public String getThirdSerialNo() {
        return thirdSerialNo;
    }

    public void setThirdSerialNo(String thirdSerialNo) {
        this.thirdSerialNo = thirdSerialNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getThirdMDiscount() {
        return thirdMDiscount;
    }

    public void setThirdMDiscount(String thirdMDiscount) {
        this.thirdMDiscount = thirdMDiscount;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getThirdDiscount() {
        return thirdDiscount;
    }

    public void setThirdDiscount(String thirdDiscount) {
        this.thirdDiscount = thirdDiscount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCashierTradeNo() {
        return cashierTradeNo;
    }

    public void setCashierTradeNo(String cashierTradeNo) {
        this.cashierTradeNo = cashierTradeNo;
    }

    public String getDeviceEn() {
        return deviceEn;
    }

    public void setDeviceEn(String deviceEn) {
        this.deviceEn = deviceEn;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getCheckFee() {
        return checkFee;
    }

    public void setCheckFee(String checkFee) {
        this.checkFee = checkFee;
    }

}
