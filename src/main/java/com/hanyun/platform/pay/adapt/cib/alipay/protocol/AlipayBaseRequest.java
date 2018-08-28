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
 * 基础请求参数
 * @author liyinglong@hanyun.com
 * @date 2016年12月29日 下午2:40:07
 */
@XmlType
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public class AlipayBaseRequest {
    // 接口名称 method
    @XmlElement(name="method")
    private String method;
    // 版本信息 version
    @XmlElement(name="version")
    private String version;
    // 字符集 charset 
    @XmlElement(name="charset")
    private String charset;
    // 签名方式 sign_type
    @XmlElement(name="sign_type")
    private String signType;
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
    
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
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
    
}
