/**
 * 
 */
package com.hanyun.platform.pay.consts;

import java.util.HashSet;
import java.util.Set;

/**
 * 易宝支付单相关常量
 * @author litao@hanyun.com
 * @date 2016年7月17日 下午7:33:54
 */
public abstract class PaymentNewConsts {
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
    public static final String PAYTYPE_CASH = "YEE_CASH";
    /** 支付方式－账户余额支付 */
    public static final String YB_PAYTYPE_BALANCE = "BALANCE";
    /** 支付方式－账户绑卡支付 */
    public static final String YB_PAYTYPE_BINDCARD = "BINDCARD";
    /** 支付方式－易宝一键支付 */
    public static final String YB_PAYTYPE_YEEPAYCASHIER = "YEEPAYCASHIER";
    /** 支付方式－网银个人支付 */
    public static final String YB_PAYTYPE_SALESB2C = "SALESB2C";
    /** 支付方式－网银企业支付 */
    public static final String YB_PAYTYPE_SALESB2B = "SALESB2B";
    /** 支付方式－微信商户扫码 */
    public static final String YB_PAYTYPE_WECHAT_MICROPAY = "WECHAT_MICROPAY";
    /** 支付方式  - 微信H5支付*/
    public static final String YB_PAYTYPE_WECHATAPP = "WECHATAPP";
    /** 支付方式 - 微信公众号支付*/
    public static final String YB_PAYTYPE_WECHATOFFICIAL = "WECHATOFFICIAL";
    /** 支付方式 - 微信用户扫码支付*/
    public static final String YB_PAYTYPE_WECHATSCAN = "WECHATSCAN";
    /** 支付方式－微信APP支付 */
    public static final String YB_PAYTYPE_WECHATSDK = "WECHATSDK";
    /** 支付方式－支付宝用户扫码 */
    public static final String PAYTYPE_ALIPAYSCAN = "ALIPAYSCAN";
    /** 支付方式－支付宝商户扫码 */
    public static final String PAYTYPE_ALIPAY_MICROPAY = "ALIPAY_MICROPAY";
    /** 支付方式－支付宝H5支付 */
    public static final String PAYTYPE_ALIPAYAPP = "ALIPAYAPP";
    /** 支付方式－支付宝APP支付 */
    public static final String PAYTYPE_ALIPAYSDK = "ALIPAYSDK";
    /** 支付方式－企业账户支付 */
    public static final String PAYTYPE_ACCOUNT_PAY = "ACCOUNT_PAY";
    /**支付方式-POS刷卡*/
    public static final String PAYTYPE_POS_PAY = "POS";
    /** 支付方式集合-需要先创建预支付单的 */
    public static final Set<String> PAYTYPE_SET_NEED_PRECREATE = new HashSet<>();
    static{
        PAYTYPE_SET_NEED_PRECREATE.add(PAYTYPE_POS_PAY);
     //   PAYTYPE_SET_NEED_PRECREATE.add(PAYTYPE_WXPAY_USC);
     //   PAYTYPE_SET_NEED_PRECREATE.add(PAYTYPE_WXPAY_JS);
      //  PAYTYPE_SET_NEED_PRECREATE.add(PAYTYPE_ALIPAY_USC);
    }
    
    /** 支付方式分类－现金 */
    public static final String PAYTYPE_CATEGORY_CASH = "YEE_CASH";
    /** 支付方式分类－储值余额 */
    public static final String PAYTYPE_CATEGORY_STOREDVALUE = "STOREDVALUE";
    /** 支付方式分类－POS刷卡 */
    public static final String PAYTYPE_CATEGORY_BANKCARD = "BANKCARD";
    /** 支付方式分类－微信 */
    public static final String PAYTYPE_CATEGORY_WEIXIN = "YBWEIXIN";
    /** 支付方式分类－支付宝 */
    public static final String PAYTYPE_CATEGORY_ALIPAY = "YBALIPAY";
    /** 支付方式分类－商场收银 */
    public static final String PAYTYPE_CATEGORY_SHOPPINGMALL = "SHOPPINGMALL";

    /**支付通道 兴业银行 */
    public static final String PAY_CHANNEL_CIB = "CIB";
    /**支付通道 联动优势 */
    public static final String PAY_CHANNEL_UMPAY = "UMPAY";
    /**支付通道 易宝支付*/
    public static final String PAY_CHANNEL_YBPAY = "YBPAY";
    /**退款模式-有单整单退款  */
    public static final int REFUND_MODE_WHOLE = 1;
    /**退款模式-有单部分退款-指定支付方式原路退回 */
    public static final int REFUND_MODE_PART_PAYTPYE = 2;
    /**退款模式-有单部分退款-不依赖原支付方式，在线下以现金方式退回 */
    public static final int REFUND_MODE_PART_OFFLINE = 3;
    /**退款模式-无单退款 */
    public static final int REFUND_MODE_NOORDER = 4;
}
