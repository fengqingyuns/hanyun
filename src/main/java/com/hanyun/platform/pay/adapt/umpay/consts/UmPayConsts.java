/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay.consts;

/**
 * 联动优势相常量
 * @author liyinglong@hanyun.com
 * @date 2016年12月15日 下午5:36:45
 */
public abstract class UmPayConsts {
    /** xml编码 */
    public static final String XML_ENCODING = "GB2312";
    
    /** 消息类型-下载对账文件 */
    public static final String MESSAGE_TYPE_DL_PAYBILL = "9000";
    
    /** 交易处理码-下载对账文件 */
    public static final String PROCESS_CODE_DL_PAYBILL = "930000";
    
    /** 对账文件类型-机构 */
    public static final String DZ_FILE_TYPE_INST = "0";
    /** 对账文件类型-商户 */
    public static final String DZ_FILE_TYPE_MERCHANT = "1";
    
    /** 应答码-交易成功 */
    public static final String RESPONSE_CODE_SUCESS = "0000";
    /** 应答码-对账文件不存在,(可能是定时任务没有执行,可以联系运营,稍后重新下载) */
    public static final String RESPONSE_CODE_FILE_NOT_EXIST = "01";
    /** 应答码-签名非法 */
    public static final String RESPONSE_CODE_SIGN_INVALID = "02";
    /** 应答码-上送参数非法 */
    public static final String RESPONSE_CODE_PARAM_INVALID = "03";
    /** 应答码-系统异常 */
    public static final String RESPONSE_CODE_SYS_ERROR = "96";
    /** 应答码-签名错误 */
    public static final String RESPONSE_CODE_SIGN_ERROR = "TA";
    
    /** 交易类型-消费 */
    public static final String TRADE_TYPE_CONSUME = "1";
    /** 交易类型-缴费 */
    public static final String TRADE_TYPE_UTILITY = "51";
    /** 交易类型-退货 */
    public static final String TRADE_TYPE_REFUND = "9";
    
    /** 结算类型-实时结算 */
    public static final String SETTLE_TYPE_REALTIME = "0";
    /** 结算类型-非实时结算 */
    public static final String SETTLE_TYPE_NOT_REALTIME = "1";
    
    /** 业务类型-POS 订单消费 */
    public static final String BUSINESS_TYPE = "3101";
    
    /** 卡类型-借记卡 */
    public static final String BANKCARD_TYPE_DEBIT = "00";
    /** 卡类型-贷记卡 */
    public static final String BANKCARD_TYPE_CREDIT = "01";
    
}
