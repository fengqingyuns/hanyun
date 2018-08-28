/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 预创建订单微信附加数据
 * @author liyinglong@hanyun.com
 * @date 2016年9月7日 上午9:48:32
 */
public class TradePreCreateWeixinExtData {
    // wx usc use
    private String codeUrl;
    // wx h5 use
    private String jsapiAppid;
    private String jsapiTimestamp;
    private String jsapiNoncestr;
    private String jsapiPackage;
    private String jsapiSigntype;
    private String jsapiPaysign;

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getJsapiAppid() {
        return jsapiAppid;
    }

    public void setJsapiAppid(String jsapiAppid) {
        this.jsapiAppid = jsapiAppid;
    }

    public String getJsapiTimestamp() {
        return jsapiTimestamp;
    }

    public void setJsapiTimestamp(String jsapiTimestamp) {
        this.jsapiTimestamp = jsapiTimestamp;
    }

    public String getJsapiNoncestr() {
        return jsapiNoncestr;
    }

    public void setJsapiNoncestr(String jsapiNoncestr) {
        this.jsapiNoncestr = jsapiNoncestr;
    }

    public String getJsapiPackage() {
        return jsapiPackage;
    }

    public void setJsapiPackage(String jsapiPackage) {
        this.jsapiPackage = jsapiPackage;
    }

    public String getJsapiSigntype() {
        return jsapiSigntype;
    }

    public void setJsapiSigntype(String jsapiSigntype) {
        this.jsapiSigntype = jsapiSigntype;
    }

    public String getJsapiPaysign() {
        return jsapiPaysign;
    }

    public void setJsapiPaysign(String jsapiPaysign) {
        this.jsapiPaysign = jsapiPaysign;
    }
}
