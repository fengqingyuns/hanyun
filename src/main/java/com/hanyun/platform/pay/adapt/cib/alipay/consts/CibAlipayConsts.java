/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.consts;

/**
 * 兴业支付宝常量
 * @author liyinglong@hanyun.com
 * @date 2017年1月3日 下午6:32:06
 */
public abstract class CibAlipayConsts {
    /** 订单过期时间-小时 */
    public static final int ORDER_EXPIRE_HOURS = 24;
    /** 通信结果-成功 */
    public static final String RETURN_CODE_SUCCESS = "SUCCESS";
    /** 通信结果-失败 */
    public static final String RETURN_CODE_FAIL = "FAIL";
    
    /** 通信返回信息-账单不存在，当前商户号没有已成交的订单，不生成对账单 */
    public static final String RETURN_MSG_NOBILLEXIST = "账单不存在";
    
    /** 业务结果-成功 */
    public static final String RESULT_CODE_SUCCESS = "SUCCESS";
    /** 业务结果-用户支付中 */
    public static final String RESULT_CODE_PAYING = "PAYING";
    /** 业务结果-失败 */
    public static final String RESULT_CODE_FAIL = "FAIL";
    
    /** 交易状态-支付成功 */
    public static final String TRADE_STATE_SUCCESS = "SUCCESS";
    /** 交易状态-已关闭 */
    public static final String TRADE_STATE_CLOSED = "CLOSED";
    /** 交易状态-用户支付中 */
    public static final String TRADE_STATE_USERPAYING = "USERPAYING";
    
    /** 退款状态-退款成功 */
    public static final String REFUND_STATUS_SUCCESS = "SUCCESS";
    /** 退款状态-退款失败 */
    public static final String REFUND_STATUS_FAIL = "FAIL";
    /** 退款状态-退款处理中 */
    public static final String REFUND_STATUS_PROCESSING = "PROCESSING";
    
    /** 支付场景-条码付 */
    public static final String PAY_SCENE_BAR_CODE = "bar_code";
    
    /** 交易类型-JS */
    public static final String TRADE_TYPE_JSAPI = "dcorepay.alipay.create";
    /** 交易类型-用户扫码 */
    public static final String TRADE_TYPE_NATIVE = "dcorepay.alipay.native";
    /** 交易类型-刷卡 */
    public static final String TRADE_TYPE_MICROPAY = "dcorepay.alipay.micropay";
  
    /** 对账业务类型-交易*/
    public static final String BILL_BUSINESS_TYPE_SUCCESS = "交易";
    /** 对账业务类型-退款*/
    public static final String BILL_BUSINESS_TYPE_REFUND = "退款";
    
    /** 对账当中交易数据、退款数据状态都是成功交易的 */
    public static final int BILL_STATUS_SUCCESS = 20;    
    
    /** 错误码-交易已完成 */
    public static final String ERRCODE_TRADE_FINISHED = "ACQ.TRADE_HAS_FINISHED";
    /** 错误码-退款全额不足 */
    public static final String ERRCODE_REFUND_NOT_ALLOW = "ACQ.TRADE_NOT_ALLOW_REFUND";
    /** 错误码-卖家余额不足 */
    public static final String ERRCODE_SELLER_BALANCE_NOT_ENOUGH = "ACQ.SELLER_BALANCE_NOT_ENOUGH";
    
}
