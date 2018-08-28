/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay.consts;

/**
 * 旺POS相关常量
 * @author liyinglong@hanyun.com
 * @date 2017年5月9日 下午6:07:41
 */
public interface UmWposConsts {
    
    /** 交易状态-待支付 */
    public static final String TRADE_STATUS_WAIT_PAY = "WAIT_PAY";
    /** 交易状态-支付中 */
    public static final String TRADE_STATUS_PAYING = "PAYING";
    /** 交易状态-部分支付 */
    public static final String TRADE_STATUS_PART_PAY = "PART_PAY";
    /** 交易状态-已支付 */
    public static final String TRADE_STATUS_PAY = "PAY";
    /** 交易状态-退款中 */
    public static final String TRADE_STATUS_REFUNDING = "REFUNDING";
    /** 交易状态-已退款 */
    public static final String TRADE_STATUS_REFUND = "REFUND";
    /** 交易状态-已关闭 */
    public static final String TRADE_STATUS_CLOSED = "CLOSED";
    
    /** 支付方式-银行卡 */
    public static final String PAY_TYPE_BANKCARD = "1006";
}