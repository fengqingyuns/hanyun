package com.hanyun.platform.pay.domain;

import java.util.Date;

public class UmpayOrder {

    private Long id;

    private String brandId;

    private String storeId;

    private String transId;

    private Date createTime;

    private Date updateTime;

    private String outTradeNo;

    private String wposDeviceEn;

    private String wposCashierTradeNo;

    private String wposVoucherNo;

    private String wposRefNo;

    private String outMchNo;

    private String terminalNo;

    private String terminalTradeNo;

    private String refNo;

    private Date payTime;

    private Date settleDate;

    private Long payAmt;

    private Long settleAmt;

    private Long chargeFee;

    private String tradeType;

    private String settleType;

    private String businessType;

    private String bankCardNo;

    private String bankCardType;

    private String bankChannelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getWposDeviceEn() {
        return wposDeviceEn;
    }

    public void setWposDeviceEn(String wposDeviceEn) {
        this.wposDeviceEn = wposDeviceEn;
    }

    public String getWposCashierTradeNo() {
        return wposCashierTradeNo;
    }

    public void setWposCashierTradeNo(String wposCashierTradeNo) {
        this.wposCashierTradeNo = wposCashierTradeNo;
    }

    public String getWposVoucherNo() {
        return wposVoucherNo;
    }

    public void setWposVoucherNo(String wposVoucherNo) {
        this.wposVoucherNo = wposVoucherNo;
    }

    public String getWposRefNo() {
        return wposRefNo;
    }

    public void setWposRefNo(String wposRefNo) {
        this.wposRefNo = wposRefNo;
    }

    public String getOutMchNo() {
        return outMchNo;
    }

    public void setOutMchNo(String outMchNo) {
        this.outMchNo = outMchNo;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getTerminalTradeNo() {
        return terminalTradeNo;
    }

    public void setTerminalTradeNo(String terminalTradeNo) {
        this.terminalTradeNo = terminalTradeNo;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Long getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(Long payAmt) {
        this.payAmt = payAmt;
    }

    public Long getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(Long settleAmt) {
        this.settleAmt = settleAmt;
    }

    public Long getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(Long chargeFee) {
        this.chargeFee = chargeFee;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getBankChannelId() {
        return bankChannelId;
    }

    public void setBankChannelId(String bankChannelId) {
        this.bankChannelId = bankChannelId;
    }
}