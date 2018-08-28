/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 下载对账单响应
 * @author liyinglong@hanyun.com
 * @date 2016年12月15日 下午4:39:15
 */
@XmlRootElement(name = "I8583")
public class UmDlPayBillRes extends UmDlPayBillBase{
    // 交易时间,格式为 hhmmss,其中 hh 为小时,mm 为分,ss 为秒
    @XmlElement(name="F12")
    private UmDataField tradeTime;
    // 交易日期, 格式为 MMDD,其中 MM 为月份,DD 为日
    @XmlElement(name="F13")
    private UmDataField tradeDate;
    // 应答码,4 个字节的定长字符域
    @XmlElement(name="F39")
    private UmDataField responseCode;
    // 错误信息中文说明,最大 99 个字节
    @XmlElement(name="F44")
    private UmDataField errorMsg;
    // 对账文件名称,以此文件的后缀文件名为解密后的原始对账文件格式
    @XmlElement(name="DZFileName")
    private UmDataField dzFileName;
    // 对账文件数据,对账文件内容,为公钥 RSA 加密数据,数据格式为 base64 编码,且没有回车符号
    @XmlElement(name="DZFile")
    private UmDataField dzFile;
    
    public UmDataField getTradeTime() {
        return tradeTime;
    }
    public void setTradeTime(UmDataField tradeTime) {
        this.tradeTime = tradeTime;
    }
    public UmDataField getTradeDate() {
        return tradeDate;
    }
    public void setTradeDate(UmDataField tradeDate) {
        this.tradeDate = tradeDate;
    }
    public UmDataField getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(UmDataField responseCode) {
        this.responseCode = responseCode;
    }
    public UmDataField getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(UmDataField errorMsg) {
        this.errorMsg = errorMsg;
    }
    public UmDataField getDzFileName() {
        return dzFileName;
    }
    public void setDzFileName(UmDataField dzFileName) {
        this.dzFileName = dzFileName;
    }
    public UmDataField getDzFile() {
        return dzFile;
    }
    public void setDzFile(UmDataField dzFile) {
        this.dzFile = dzFile;
    }
    
}
