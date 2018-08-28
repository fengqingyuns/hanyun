/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay.protocol;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 下载对账单报文基类
 * @author liyinglong@hanyun.com
 * @date 2016年12月15日 下午5:17:27
 */
@XmlType
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public class UmDlPayBillBase {
    // 消息类型,4个字节的定长数字字符域
    @XmlElement(name="F0")
    private UmDataField messageType;
    // 交易处理码,由六位数字组成,用于区分各个交易类型
    @XmlElement(name="F3")
    private UmDataField processCode;
    // 商户号,4-15 个字节的变长域
    @XmlElement(name="F42")
    private UmDataField merchantId;
    // 附加数据,最长256个字节,下载对账文件时填结算日期(8位格式YYYYMMDD)
    @XmlElement(name="F45")
    private UmDataField attach;
    // 入网机构号,最长 8 个字节的 ASCII 码
    @XmlElement(name="InstId")
    private UmDataField instId;
    // 对账文件类型,最长 1 个字节的 ASCII 码
    @XmlElement(name="DZFileType")
    private UmDataField dzFileType;
    // 签名信息
    @XmlElement(name="MerSign")
    private UmDataField merSign;
    
    public UmDataField getMessageType() {
        return messageType;
    }
    public void setMessageType(UmDataField messageType) {
        this.messageType = messageType;
    }
    public UmDataField getProcessCode() {
        return processCode;
    }
    public void setProcessCode(UmDataField processCode) {
        this.processCode = processCode;
    }
    public UmDataField getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(UmDataField merchantId) {
        this.merchantId = merchantId;
    }
    public UmDataField getAttach() {
        return attach;
    }
    public void setAttach(UmDataField attach) {
        this.attach = attach;
    }
    public UmDataField getInstId() {
        return instId;
    }
    public void setInstId(UmDataField instId) {
        this.instId = instId;
    }
    public UmDataField getDzFileType() {
        return dzFileType;
    }
    public void setDzFileType(UmDataField dzFileType) {
        this.dzFileType = dzFileType;
    }
    public UmDataField getMerSign() {
        return merSign;
    }
    public void setMerSign(UmDataField merSign) {
        this.merSign = merSign;
    }
}
