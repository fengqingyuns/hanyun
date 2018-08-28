/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 联动优势配置
 * @author liyinglong@hanyun.com
 * @date 2016年12月19日 下午8:55:40
 */
@Component
public class UmPayConfig {
    // 地址
    @Value("${umpay.api.url}")
    private String apiUrl;
    // 机构号
    @Value("${umpay.instid}")
    private String instId;
    // 文件存放目录
    @Value("${umpay.bill.dir}")
    private String umpayBillDir;
    
    // 通知url
    @Value("${umpay.notify.url.base}")
    private String notifyUrlBase;
    // 通知url路径
    @Value("${umpay.notify.url.path}")
    private String notifyUrlPath;
    
    public String getNotifyUrl(){
        if(StringUtils.isBlank(notifyUrlBase) || StringUtils.isBlank(notifyUrlPath)){
            return StringUtils.EMPTY;
        }
        return notifyUrlBase + notifyUrlPath;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getUmpayBillDir() {
        return umpayBillDir;
    }

    public void setUmpayBillDir(String umpayBillDir) {
        this.umpayBillDir = umpayBillDir;
    }

    public String getNotifyUrlBase() {
        return notifyUrlBase;
    }

    public void setNotifyUrlBase(String notifyUrlBase) {
        this.notifyUrlBase = notifyUrlBase;
    }

    public String getNotifyUrlPath() {
        return notifyUrlPath;
    }

    public void setNotifyUrlPath(String notifyUrlPath) {
        this.notifyUrlPath = notifyUrlPath;
    }
    
}
