package com.hanyun.platform.pay.adapt.yeepay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay
 * @Author: dewen.li
 * @Date: 2018-08-02 下午10:27
 */
@Component
public class YeepayConfig {
    @Value("${yeepay.version}")
    private String version;
    @Value("${yeepay.queryPath}")
    private String queryPath;

    @Value("${yeepay.url.serverRoot}")
    private String baseUrl;
    @Value("${yeepay.secretKey}")
    private String secreKey;
    @Value("${yeepay.appKey}")
    private String appKey;
    @Value("${yeepay.sign}")
    private String sign;

    public String getRefundServerCallbackUrl() {
        return refundServerCallbackUrl;
    }

    public void setRefundServerCallbackUrl(String refundServerCallbackUrl) {
        this.refundServerCallbackUrl = refundServerCallbackUrl;
    }

    public String getWebCallbackUrl() {
        return webCallbackUrl;
    }

    public void setWebCallbackUrl(String webCallbackUrl) {
        this.webCallbackUrl = webCallbackUrl;
    }

    public String getRefundWebCallbackUrl() {
        return refundWebCallbackUrl;
    }

    public void setRefundWebCallbackUrl(String refundWebCallbackUrl) {
        this.refundWebCallbackUrl = refundWebCallbackUrl;
    }

    @Value("${yeepay.refund.serverCallbackUrl}")
    private String refundServerCallbackUrl;

    @Value("${yeepay.pay.webCallbackUrl}")
    private String webCallbackUrl;

    public String getAlipayServerCallbackUrl() {
        return alipayServerCallbackUrl;
    }

    public void setAlipayServerCallbackUrl(String alipayServerCallbackUrl) {
        this.alipayServerCallbackUrl = alipayServerCallbackUrl;
    }

    public String getWechatServerCallbackUrl() {
        return wechatServerCallbackUrl;
    }

    public void setWechatServerCallbackUrl(String wechatServerCallbackUrl) {
        this.wechatServerCallbackUrl = wechatServerCallbackUrl;
    }

    @Value("${yeepay.refund.webCallbackUrl}")
    private String refundWebCallbackUrl;

    @Value("${yeepay.alipay.serverCallbackUrl}")
    private String alipayServerCallbackUrl;

    @Value("${yeepay.weixinpay.serverCallbackUrl}")
    private String wechatServerCallbackUrl;

    public String getQueryPath() {
        return queryPath;
    }

    public void setQueryPath(String queryPath) {
        this.queryPath = queryPath;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    @Value("platform.id")
    private String platformId;

    public String getServerCallbackUrl() {
        return serverCallbackUrl;
    }

    public void setServerCallbackUrl(String serverCallbackUrl) {
        this.serverCallbackUrl = serverCallbackUrl;
    }

    @Value("yeepay.serverCallbackUrl")
    private String serverCallbackUrl;

    public String getPaypath() {
        return paypath;
    }

    public void setPaypath(String paypath) {
        this.paypath = paypath;
    }

    @Value("${yeepay.paypath}")
    private String paypath;

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

    public String getSecreKey() {
        return secreKey;
    }

    public void setSecreKey(String secreKey) {
        this.secreKey = secreKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignalg() {
        return signalg;
    }

    public void setSignalg(String signalg) {
        this.signalg = signalg;
    }

    @Value("${yeepay.signalg}")
    private String signalg;
}
