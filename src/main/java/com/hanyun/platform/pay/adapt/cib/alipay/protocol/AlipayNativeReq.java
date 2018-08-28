/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用户扫码接口请求参数
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午6:09:40
 */
@XmlRootElement(name = "xml")
public class AlipayNativeReq extends AlipayBaseRequest {
    // 商品描述 body
    @XmlElement(name="body")
    private String body;
    // 商品详情 detail
    @XmlElement(name="detail")
    private String detail;
    // 附加数据 attach
    @XmlElement(name="attach")
    private String attach;
    // 兴业银行分配门店APPID store_appid
    @XmlElement(name="store_appid")
    private String storeAppid;
    // 操作员编号 op_user
    @XmlElement(name="op_user")
    private String opUser;
    // 商户订单号 out_trade_no
    @XmlElement(name="out_trade_no")
    private String outTradeNo;
    // 货币类型 fee_type
    @XmlElement(name="fee_type")
    private String feeType;
    // 总金额 total_fee
    @XmlElement(name="total_fee")
    private Long totalFee;
    // 交易起始时间 time_start
    @XmlElement(name="time_start")
    private String timeStart;
    // 交易结束时间 time_expire
    @XmlElement(name="time_expire")
    private String timeExpire;
    // 通知地址 notify_url
    @XmlElement(name="notify_url")
    private String notifyUrl;
    
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getAttach() {
        return attach;
    }
    public void setAttach(String attach) {
        this.attach = attach;
    }
    public String getStoreAppid() {
        return storeAppid;
    }
    public void setStoreAppid(String storeAppid) {
        this.storeAppid = storeAppid;
    }
    public String getOpUser() {
        return opUser;
    }
    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }
    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getFeeType() {
        return feeType;
    }
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    public Long getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }
    public String getTimeStart() {
        return timeStart;
    }
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
    public String getTimeExpire() {
        return timeExpire;
    }
    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }
    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    
}
