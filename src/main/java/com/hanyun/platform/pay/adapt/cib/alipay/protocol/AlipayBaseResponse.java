/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 基础响应结果
 * @author liyinglong@hanyun.com
 * @date 2016年12月29日 下午2:40:22
 */
@XmlType
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public class AlipayBaseResponse {
    // 版本信息 version
    @XmlElement(name="version")
    private String version;
    // 字符集 charset 
    @XmlElement(name="charset")
    private String charset;
    // 签名方式 sign_type
    @XmlElement(name="sign_type")
    private String signType;    
    // 返回状态码 return_code
    @XmlElement(name="return_code")
    private String returnCode;
    // 返回信息 return_msg
    @XmlElement(name="return_msg")
    private String returnMsg;
    // 应用 ID appid
    @XmlElement(name="appid")
    private String appid;
    // 商户号 mch_id
    @XmlElement(name="mch_id")
    private String mchId;
    // 设备号 device_info
    @XmlElement(name="device_info")
    private String deviceInfo;
    // 随机字符串 nonce_str
    @XmlElement(name="nonce_str")
    private String nonceStr;
    // 签名 sign
    @XmlElement(name="sign")
    private String sign;
    // 业务结果 result_code
    @XmlElement(name="result_code")
    private String resultCode;
    // 错误代码 err_code
    @XmlElement(name="err_code")
    private String errCode;
    // 错误代码描述 err_code_des
    @XmlElement(name="err_code_des")
    private String errCodeDes;
    
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getCharset() {
        return charset;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getReturnMsg() {
        return returnMsg;
    }
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
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
    public String getNonceStr() {
        return nonceStr;
    }
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getResultCode() {
        return resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    public String getErrCode() {
        return errCode;
    }
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    public String getErrCodeDes() {
        return errCodeDes;
    }
    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
    
}
