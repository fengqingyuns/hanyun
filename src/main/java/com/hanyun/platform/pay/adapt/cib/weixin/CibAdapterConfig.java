/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;

/**
 * 兴业微信适配器配置
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午3:49:06
 */
@Component
public class CibAdapterConfig {
    @Value("${cib.weixin.version}")
    private String version;
    
    @Value("${cib.url.base}")
    private String baseUrl;
    @Value("${cib.weixin.url.unifiedorder}")
    private String unifiedorderUrlPath;
    @Value("${cib.weixin.url.orderquery}")
    private String orderqueryUrlPath;
    @Value("${cib.weixin.url.closeorder}")
    private String closeorderUrlPath;
    @Value("${cib.weixin.url.refund}")
    private String refundUrlPath;
    @Value("${cib.weixin.url.refundquery}")
    private String refundqueryUrlPath;
    @Value("${cib.weixin.url.downloadbill}")
    private String downloadbillUrlPath;
    @Value("${cib.weixin.url.micropay}")
    private String micropayUrlPath;
    @Value("${cib.weixin.url.reverse}")
    private String reverseUrlPath;
    
    @Value("${cib.notify.url.base}")
    private String notifyBaseUrl;
    @Value("${cib.weixin.notify.url}")
    private String notifyWeixinUrlPath;
    
    @Value("${cib.weixin.bill.dir}")
    private String weixinBillDir;
    
    private Map<CibWeiXinMethodType, String> methodUrlMap = new HashMap<>();
    private String notifyWeixinUrl;
    
    @PostConstruct
    public void init(){
        methodUrlMap.put(CibWeiXinMethodType.unifiedorder, baseUrl + unifiedorderUrlPath);
        methodUrlMap.put(CibWeiXinMethodType.orderquery, baseUrl + orderqueryUrlPath);
        methodUrlMap.put(CibWeiXinMethodType.closeorder, baseUrl + closeorderUrlPath);
        methodUrlMap.put(CibWeiXinMethodType.refund, baseUrl + refundUrlPath);
        methodUrlMap.put(CibWeiXinMethodType.refundquery, baseUrl + refundqueryUrlPath);
        methodUrlMap.put(CibWeiXinMethodType.micropay, baseUrl + micropayUrlPath);
        methodUrlMap.put(CibWeiXinMethodType.reverse, baseUrl + reverseUrlPath);
        methodUrlMap.put(CibWeiXinMethodType.downloadbill, baseUrl + downloadbillUrlPath);
        
        notifyWeixinUrl = notifyBaseUrl + notifyWeixinUrlPath;
    }
    
    /**
     * 获取方法URL
     * @param type
     * @return
     */
    public String getMethodUrl(CibWeiXinMethodType type){
        return methodUrlMap.get(type);
    }
    
    public String getNotifyWeixinUrl(){
        return notifyWeixinUrl;
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
    public String getUnifiedorderUrlPath() {
        return unifiedorderUrlPath;
    }
    public void setUnifiedorderUrlPath(String unifiedorderUrlPath) {
        this.unifiedorderUrlPath = unifiedorderUrlPath;
    }
    public String getOrderqueryUrlPath() {
        return orderqueryUrlPath;
    }
    public void setOrderqueryUrlPath(String orderqueryUrlPath) {
        this.orderqueryUrlPath = orderqueryUrlPath;
    }
    public String getCloseorderUrlPath() {
        return closeorderUrlPath;
    }
    public void setCloseorderUrlPath(String closeorderUrlPath) {
        this.closeorderUrlPath = closeorderUrlPath;
    }
    public String getRefundUrlPath() {
        return refundUrlPath;
    }
    public void setRefundUrlPath(String refundUrlPath) {
        this.refundUrlPath = refundUrlPath;
    }
    public String getRefundqueryUrlPath() {
        return refundqueryUrlPath;
    }
    public void setRefundqueryUrlPath(String refundqueryUrlPath) {
        this.refundqueryUrlPath = refundqueryUrlPath;
    }
    public String getDownloadbillUrlPath() {
        return downloadbillUrlPath;
    }
    public void setDownloadbillUrlPath(String downloadbillUrlPath) {
        this.downloadbillUrlPath = downloadbillUrlPath;
    }
    public String getMicropayUrlPath() {
        return micropayUrlPath;
    }
    public void setMicropayUrlPath(String micropayUrlPath) {
        this.micropayUrlPath = micropayUrlPath;
    }
    public String getReverseUrlPath() {
        return reverseUrlPath;
    }
    public void setReverseUrlPath(String reverseUrlPath) {
        this.reverseUrlPath = reverseUrlPath;
    }

    public String getWeixinBillDir() {
        return weixinBillDir;
    }

    public void setWeixinBillDir(String weixinBillDir) {
        this.weixinBillDir = weixinBillDir;
    }
    
}
