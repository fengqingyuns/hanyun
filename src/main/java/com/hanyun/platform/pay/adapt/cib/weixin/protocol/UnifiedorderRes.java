/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 统一支付接口响应结果
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月14日 下午5:55:57
 */
@XmlRootElement(name = "xml")
public class UnifiedorderRes extends BaseResponse {
    // 交易类型 trade_type
    private String trade_type;
    // 预支付 ID prepay_id
    private String prepay_id;
    // 二维码链接 code_url
    private String code_url;
    // JSAPI 支付公众号ID jsapi_appid
    private String jsapi_appid;
    // JSAPI 支付时间戳 jsapi_timestamp
    private String jsapi_timestamp;
    // JSAPI 支付随机字符串 jsapi_noncestr
    private String jsapi_noncestr;
    // JSAPI 订单详情扩展字符串 jsapi_package
    private String jsapi_package;
    // JSAPI 签名方式 jsapi_signtype
    private String jsapi_signtype;
    // JSAPI 签名 jsapi_paysign
    private String jsapi_paysign;

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getJsapi_appid() {
        return jsapi_appid;
    }

    public void setJsapi_appid(String jsapi_appid) {
        this.jsapi_appid = jsapi_appid;
    }

    public String getJsapi_timestamp() {
        return jsapi_timestamp;
    }

    public void setJsapi_timestamp(String jsapi_timestamp) {
        this.jsapi_timestamp = jsapi_timestamp;
    }

    public String getJsapi_noncestr() {
        return jsapi_noncestr;
    }

    public void setJsapi_noncestr(String jsapi_noncestr) {
        this.jsapi_noncestr = jsapi_noncestr;
    }

    public String getJsapi_package() {
        return jsapi_package;
    }

    public void setJsapi_package(String jsapi_package) {
        this.jsapi_package = jsapi_package;
    }

    public String getJsapi_signtype() {
        return jsapi_signtype;
    }

    public void setJsapi_signtype(String jsapi_signtype) {
        this.jsapi_signtype = jsapi_signtype;
    }

    public String getJsapi_paysign() {
        return jsapi_paysign;
    }

    public void setJsapi_paysign(String jsapi_paysign) {
        this.jsapi_paysign = jsapi_paysign;
    }

}
