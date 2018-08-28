/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 兴业支付宝适配器配置
 * @author liyinglong@hanyun.com
 * @date 2017年1月3日 上午10:55:00
 */
@Component
public class CibAlipayAdapterConfig {
    @Value("${cib.alipay.version}")
    private String version;
    
    @Value("${cib.url.base}")
    private String baseUrl;
    @Value("${cib.alipay.url.gateway}")
    private String gatewayUrlPath;
    
    @Value("${cib.notify.url.base}")
    private String notifyBaseUrl;
    @Value("${cib.alipay.notify.url}")
    private String notifyAlipayUrlPath;
    
    @Value("${cib.alipay.bill.dir}")
    private String alipayBillDir;
    
    private String gatewayUrl;
    private String notifyAlipayUrl;

    @PostConstruct
    public void init(){
        gatewayUrl = baseUrl + gatewayUrlPath;
        notifyAlipayUrl = notifyBaseUrl + notifyAlipayUrlPath;
    }
    
    public String getGatewayUrl(){
        return gatewayUrl;
    }
    
    public String getNotifyAlipayUrl(){
        return notifyAlipayUrl;
    }
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getGatewayUrlPath() {
        return gatewayUrlPath;
    }

    public void setGatewayUrlPath(String gatewayUrlPath) {
        this.gatewayUrlPath = gatewayUrlPath;
    }

    public String getNotifyBaseUrl() {
        return notifyBaseUrl;
    }

    public void setNotifyBaseUrl(String notifyBaseUrl) {
        this.notifyBaseUrl = notifyBaseUrl;
    }

    public String getNotifyAlipayUrlPath() {
        return notifyAlipayUrlPath;
    }

    public void setNotifyAlipayUrlPath(String notifyAlipayUrlPath) {
        this.notifyAlipayUrlPath = notifyAlipayUrlPath;
    }

    public String getAlipayBillDir() {
        return alipayBillDir;
    }

    public void setAlipayBillDir(String alipayBillDir) {
        this.alipayBillDir = alipayBillDir;
    }
    
    
}
