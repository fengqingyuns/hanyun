/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.consts;

/**
 * 兴业支付宝方法类型
 * @author liyinglong@hanyun.com
 * @date 2017年1月3日 下午6:31:47
 */
public abstract class CibAlipayMethodType {
    /** 商户扫码交易接口 */
    public static final String MICROPAY = "dcorepay.alipay.micropay";
    /** 用户扫码下单接口 */
    public static final String NATIVE = "dcorepay.alipay.native";
    /** JS支付下单接口 */
    public static final String JSPAY = "dcorepay.alipay.create";
    /** 订单查询接口 */
    public static final String QUERY = "dcorepay.alipay.query";
    /** 撤消订单接口 */
    public static final String REVERSE = "dcorepay.alipay.reverse";
    /** 退款接口 */
    public static final String REFUND = "dcorepay.alipay.refund";
    /** 退款查询接口 */
    public static final String REFUNDQUERY = "dcorepay.alipay.refundquery";
    /** 下载对账单接口 */
    public static final String BILL = "dcorepay.alipay.bill";
}
