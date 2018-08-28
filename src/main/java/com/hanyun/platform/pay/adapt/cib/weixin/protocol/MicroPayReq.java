/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 刷卡支付接口请求参数
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午9:19:26
 */
@XmlRootElement(name = "xml")
public class MicroPayReq extends BaseRequest {
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
    // 商品标记 goods_tag
    private String goods_tag;
    // 指定支付方式 limit_pay
    private String limit_pay;
    // 授权码 auth_code
    private String auth_code;

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

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

}
