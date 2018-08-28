package com.hanyun.platform.pay.consts;

/**
 * 任务类型常量
 */
public class TaskMessageType{
    /** 支付完成后通知订单 */
    public static final Integer PAY_NOTIFY_ORDER = 1001; 
    /** 微信商户扫码支付主动查询订单 */
    public static final Integer WXPAY_MSC_QUERY_ORDER = 1101; 
    /** 支付宝商户扫码支付主动查询订单 */
    public static final Integer ALIPAY_MSC_QUERY_ORDER = 1121; 

}
