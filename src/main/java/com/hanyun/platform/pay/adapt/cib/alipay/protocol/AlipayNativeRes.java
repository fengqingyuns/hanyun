/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用户扫码接口响应结果
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午6:10:07
 */
@XmlRootElement(name = "xml")
public class AlipayNativeRes extends AlipayBaseResponse {
    // 二维码链接 code_url
    @XmlElement(name="code_url")
    private String codeUrl;

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
    
}
