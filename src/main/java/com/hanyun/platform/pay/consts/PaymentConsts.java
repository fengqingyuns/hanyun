/**
 * 
 */
package com.hanyun.platform.pay.consts;

import java.util.HashSet;
import java.util.Set;

/**
 * 支付单相关常量
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午7:33:54
 */
public abstract class PaymentConsts {
    /** 费率存储使用的乘数(倍数) */
    public static final long FEE_RATE_MULTIPLIER = 1000000L;
    
    /** 支付状态－初始 */
    public static final Integer PAY_STATUS_INIT = 0;
    /** 支付状态－处理中 */
    public static final Integer PAY_STATUS_PROCESS = 10;
    /** 支付状态－已部分支付 */
    public static final Integer PAY_STATUS_PAY_PART = 15;
    /** 支付状态－已完成 */
    public static final Integer PAY_STATUS_FINISH = 20;
    /** 支付状态－已取消 */
    public static final Integer PAY_STATUS_CANCEL = 30;
    /** 支付状态－退款处理中 */
    public static final Integer PAY_STATUS_REF_PROCESS = 40;
    /** 支付状态－已部分退款 */
    public static final Integer PAY_STATUS_REF_PART = 41;
    /** 支付状态－已全部退款 */
    public static final Integer PAY_STATUS_REF_ALL = 42;
    /** 支付状态－失败 */
    public static final Integer PAY_STATUS_FAIL = 50;
    
    /** 支付流水状态－初始 */
    public static final Integer TRANS_STATUS_INIT = 0;
    /** 支付流水状态－处理中 */
    public static final Integer TRANS_STATUS_PROCESS = 10;
    /** 支付流水状态－已完成 */
    public static final Integer TRANS_STATUS_FINISH = 20;
    /** 支付流水状态－已取消 */
    public static final Integer TRANS_STATUS_CANCEL = 30;
    /** 支付流水状态－失败 */
    public static final Integer TRANS_STATUS_FAIL = 50;
    
    /** 支付流水退款状态－初始 */
    public static final Integer TRANS_REFUND_STATUS_INIT = 0;
    /** 支付流水退款状态－处理中 */
    public static final Integer TRANS_REFUND_STATUS_PROCESS = 10;
    /** 支付流水退款状态－部分退款完成 */
    public static final Integer TRANS_REFUND_STATUS_PART = 15;
    /** 支付流水退款状态－已完成 */
    public static final Integer TRANS_REFUND_STATUS_FINISH = 20;
    /** 支付流水退款状态－失败 */
    public static final Integer TRANS_REFUND_STATUS_FAIL = 50;
    
    /** 支付流水操作类型－扣款 */
    public static final Integer TRANS_OPERATE_TYPE_PAY = 1;
    /** 支付流水操作类型－退款 */
    public static final Integer TRANS_OPERATE_TYPE_REF = 2;
    
    /** 可用状态-可用 */
    public static final Integer AVAIL_STATUS_Y = 0;
    /** 可用状态-不可用 */
    public static final Integer AVAIL_STATUS_N = 1;
    
    /** 服务状态-常规 */
    public static final Integer SRV_STATUS_OUT = 0;
    /** 服务状态-服务中 */
    public static final Integer SRV_STATUS_IN = 1;
    
    /** 线上结算-是 */
    public static final Integer SETTLE_TYPE_ON = 0;
    /** 线上结算-否 */
    public static final Integer SETTLE_TYPE_OFF = 1;
    
    /** 支付通道－线下 */
    public static final String PAYCHANNEL_OFFLINE = "OFFLINE";
    /** 支付通道－储值 */
    public static final String PAYCHANNEL_STOREDVALUE = "STOREDVALUE";
    
    /** 支付方式－现金 */
    public static final String PAYTYPE_CASH = "CASH";
    /** 支付方式－离线pos刷卡 */
    public static final String PAYTYPE_BANKCARD_OFFLINE = "BANKCARD_OFFLINE";
    /** 支付方式－储值余额 */
    public static final String PAYTYPE_STOREDVALUE = "STOREDVALUE";
    /** 支付方式－pos刷卡 */
    public static final String PAYTYPE_BANKCARD = "BANKCARD";
    /** 支付方式－微信用户扫码 */
    public static final String PAYTYPE_WXPAY_USC = "WXPAY_USC";
    /** 支付方式－微信商户扫码 */
    public static final String PAYTYPE_WXPAY_MSC = "WXPAY_MSC";
    /** 支付方式－微信公众号H5 */
    public static final String PAYTYPE_WXPAY_JS = "WXPAY_JS";
    /** 支付方式－支付宝用户扫码 */
    public static final String PAYTYPE_ALIPAY_USC = "ALIPAY_USC";
    /** 支付方式－支付宝商户扫码 */
    public static final String PAYTYPE_ALIPAY_MSC = "ALIPAY_MSC";
    /** 支付方式－商场收银 */
    public static final String PAYTYPE_SHOPPINGMALL = "SHOPPINGMALL";
    
    /** 支付方式集合-需要先创建预支付单的 */
    public static final Set<String> PAYTYPE_SET_NEED_PRECREATE = new HashSet<>();
    static{
        PAYTYPE_SET_NEED_PRECREATE.add(PAYTYPE_BANKCARD);
        PAYTYPE_SET_NEED_PRECREATE.add(PAYTYPE_WXPAY_USC);
        PAYTYPE_SET_NEED_PRECREATE.add(PAYTYPE_WXPAY_JS);
        PAYTYPE_SET_NEED_PRECREATE.add(PAYTYPE_ALIPAY_USC);
    }
    
    /** 支付方式分类－现金 */
    public static final String PAYTYPE_CATEGORY_CASH = "CASH";
    /** 支付方式分类－储值余额 */
    public static final String PAYTYPE_CATEGORY_STOREDVALUE = "STOREDVALUE";
    /** 支付方式分类－POS刷卡 */
    public static final String PAYTYPE_CATEGORY_BANKCARD = "BANKCARD";
    /** 支付方式分类－微信 */
    public static final String PAYTYPE_CATEGORY_WEIXIN = "WEIXIN";
    /** 支付方式分类－支付宝 */
    public static final String PAYTYPE_CATEGORY_ALIPAY = "ALIPAY";
    /** 支付方式分类－商场收银 */
    public static final String PAYTYPE_CATEGORY_SHOPPINGMALL = "SHOPPINGMALL";
    
    /** 差异类型-金额不一致 */
    public static final Integer DIFF_TYPE_INCONSISTENT_AMOUNT = 1;
    /** 差异类型-状态不一致*/
    public static final Integer DIFF_TYPE_INCONSISTENT_STATE = 2;
    /** 差异类型-其他*/
    public static final Integer DIFF_TYPE_OTHER = 3;
    
    /** 差异来源-商户提报 */
    public static final Integer DIFF_SRC_MERCHANT_REPORT = 1;
    /** 差异来源-系统对账 */
    public static final Integer DIFF_SRC_SYSTEM_REPORT = 2;
    /** 差异来源-其他 */
    public static final Integer DIFF_SRC_OTHER = 3;
    
    /**对账文件下载状态 -未开始*/
    public static final Integer DL_STATUS_NOSTART = 0;
    /**对账文件下载状态-完成 */
    public static final Integer DL_STATUS_FINISH = 10;
    /**对账文件下载状态 -下载失败*/
    public static final Integer DL_STATUS_DOWNLOADFAILED = 20;
    
    /**对账状态 -未开始*/
    public static final Integer BILL_STATUS_NOSTART = 0;
    /**对账状态-完成 */
    public static final Integer BILL_STATUS_FINISH = 10;
    /**对账状态 -对账失败*/
    public static final Integer BILL_STATUS_BILLFAILED = 20;
   
    /**兴业银行微信对账 */
    public static final String CIB_WEIXIN_STATEMENT_JOB = "payment.weixinstatementGenerate";
    /**兴业银行支付宝*/
    public static final String CIB_ALIPAY_STATEMENT_JOB = "payment.alipaystatementGenerate";
    /**联动优势银行卡*/
    public static final String UMPA_BANK_CARD_STATEMENT_JOB = "payment.umpaystatementGenerate";
    
    /**支付通道 兴业银行 */
    public static final String PAY_CHANNEL_CIB = "CIB";
    /**支付通道 联动优势 */
    public static final String PAY_CHANNEL_UMPAY = "UMPAY";
    
    /**退款模式-有单整单退款  */
    public static final int REFUND_MODE_WHOLE = 1;
    /**退款模式-有单部分退款-指定支付方式原路退回 */
    public static final int REFUND_MODE_PART_PAYTPYE = 2;
    /**退款模式-有单部分退款-不依赖原支付方式，在线下以现金方式退回 */
    public static final int REFUND_MODE_PART_OFFLINE = 3;
    /**退款模式-无单退款 */
    public static final int REFUND_MODE_NOORDER = 4;
}
