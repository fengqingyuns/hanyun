/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 统一支付接口请求参数
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月14日 下午3:18:30
 */
@XmlRootElement(name = "xml")
public class UnifiedorderReq extends BaseRequest {
    // 商品描述 body
    private String body;
    // 商品详情 detail
    private String detail;
    // 附加数据 attach
    private String attach;
    // 商户订单号 out_trade_no
    private String out_trade_no;
    // 货币类型 fee_type
    private String fee_type;
    // 总金额 total_fee
    private Long total_fee;
    // 终端 IP spbill_create_ip
    private String spbill_create_ip;
    // 交易起始时间 time_start
    private String time_start;
    // 交易结束时间 time_expire
    private String time_expire;
    // 商品标记 goods_tag
    private String goods_tag;
    // 通知地址 notify_url
    private String notify_url;
    // 交易类型 trade_type
    private String trade_type;
    // 用户标识 openid
    private String openid;
    // 商品 ID product_id
    private String product_id;
    // 指定支付方式 limit_pay
    private String limit_pay;

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

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

}
