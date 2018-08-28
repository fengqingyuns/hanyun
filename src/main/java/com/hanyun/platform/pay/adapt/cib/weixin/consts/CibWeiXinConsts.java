/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.consts;

import java.util.HashSet;
import java.util.Set;

/**
 * 兴业银行微信接口相关常量
 * @author liyinglong@hanyun.com
 * @date 2016年8月29日 下午7:49:09
 */
public abstract class CibWeiXinConsts {
    /** 订单过期时间-小时 */
    public static final int ORDER_EXPIRE_HOURS = 24;
    /** 条件判断-是 */
    public static final String CONDITION_Y = "Y";
    /** 条件判断-否 */
    public static final String CONDITION_N = "N";
    
    /** 通信结果-成功 */
    public static final String RETURN_CODE_SUCCESS = "SUCCESS";
    /** 通信结果-失败 */
    public static final String RETURN_CODE_FAIL = "FAIL";
    
    /** 业务结果-成功 */
    public static final String RESULT_CODE_SUCCESS = "SUCCESS";
    /** 业务结果-失败 */
    public static final String RESULT_CODE_FAIL = "FAIL";
    
    /** 交易状态-支付成功 */
    public static final String TRADE_STATE_SUCCESS = "SUCCESS";
    /** 交易状态-转入退款 */
    public static final String TRADE_STATE_REFUND = "REFUND";
    /** 交易状态-未支付 */
    public static final String TRADE_STATE_NOTPAY = "NOTPAY";
    /** 交易状态-已关闭 */
    public static final String TRADE_STATE_CLOSED = "CLOSED";
    /** 交易状态-已撤销(刷卡支付) */
    public static final String TRADE_STATE_REVOKED = "REVOKED";
    /** 交易状态-用户支付中 */
    public static final String TRADE_STATE_USERPAYING = "USERPAYING";
    /** 交易状态-未支付(确认支付超时) */
    public static final String TRADE_STATE_NOPAY = "NOPAY";
    /** 交易状态-支付失败(其他原因,如银行返回失败) */
    public static final String TRADE_STATE_PAYERROR = "PAYERROR";
    
    /** 交易类型-公众号 */
    public static final String TRADE_TYPE_JSAPI = "JSAPI";
    /** 交易类型-用户扫码 */
    public static final String TRADE_TYPE_NATIVE = "NATIVE";
    /** 交易类型-刷卡 */
    public static final String TRADE_TYPE_MICROPAY = "MICROPAY";
    /** 交易类型-APP */
    public static final String TRADE_TYPE_APP = "APP";
    
    /** 货币类型-人民币 */
    public static final String FEE_TYPE_CNY = "CNY";
    
    /** 退款渠道-原路退款 */
    public static final String REFUND_CHANNEL_ORIGINAL = "ORIGINAL";
    /** 退款渠道-退回到余额 */
    public static final String REFUND_CHANNEL_BALANCE = "BALANCE";
    
    /** 退款状态-退款成功 */
    public static final String REFUND_STATUS_SUCCESS = "SUCCESS";
    /** 退款状态-退款失败 */
    public static final String REFUND_STATUS_FAIL = "FAIL";
    /** 退款状态-退款处理中 */
    public static final String REFUND_STATUS_PROCESSING = "PROCESSING";
    /** 退款状态-未确定,需要商户原退款单号重新发起 */
    public static final String REFUND_STATUS_NOTSURE = "NOTSURE";
    /** 退款状态-转入代发,退款到银行发现用户的卡作废或者冻结了,导致原路退款银行卡失败 */
    public static final String REFUND_STATUS_CHANGE = "CHANGE";
    
    /** 账单类型-当日所有订单信息, 默认值 */
    public static final String BILL_TYPE_ALL = "ALL";
    /** 账单类型-当日 成功支付的订单 */
    public static final String BILL_TYPE_SUCCESS = "SUCCESS";
    /** 账单类型-当日退款订单 */
    public static final String BILL_TYPE_REFUND = "REFUND";
    /** 账单类型-已撤销的订单 */
    public static final String BILL_TYPE_REVOKED = "REVOKED";
    
    /** 刷卡错误码－接口返回错误 */
    public static final String MICROPAY_ERRORCODE_SYSTEMERROR = "SYSTEMERROR";
    /** 刷卡错误码－银行系统异常 */
    public static final String MICROPAY_ERRORCODE_BANKERROR = "BANKERROR";
    /** 刷卡错误码－用户支付中,需要输入密码 */
    public static final String MICROPAY_ERRORCODE_USERPAYING = "USERPAYING";
    /** 刷卡错误码－需要主动查询的错误码集合 */
    public static final Set<String> MICROPAY_ERRORCODE_NEEDQUERY_SET = new HashSet<>();
    static{
        MICROPAY_ERRORCODE_NEEDQUERY_SET.add(MICROPAY_ERRORCODE_SYSTEMERROR);
        MICROPAY_ERRORCODE_NEEDQUERY_SET.add(MICROPAY_ERRORCODE_BANKERROR);
        MICROPAY_ERRORCODE_NEEDQUERY_SET.add(MICROPAY_ERRORCODE_USERPAYING);
    }
    
    /** 通信返回信息-账单不存在，当前商户号没有已成交的订单，不生成对账单 */
    public static final String RETURN_MSG_NOBILLEXIST = "No Bill Exist";
    /** 通信返回信息-退款余额不足 */
    public static final String RETURN_MSG_REFUND_NO_MONEY= "余额不足，不能发起退款！";
}
