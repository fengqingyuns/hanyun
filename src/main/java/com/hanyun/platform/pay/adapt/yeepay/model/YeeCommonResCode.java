package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * 
 * @date 2018年8月10日
 * 
 * @apiDefine Common 支付相关
 * @author litao@hanyun.com
 */
public class YeeCommonResCode {

	public static final String YEE_NOPAY_MERCHANT = "1"; // 成功
	public static final String YEE_NEED_SMS_CODE = "FA000002";	//需要短信验证码
	public static final String YEE_SMS_CODE_ERROR = "FA000003";	//短信验证码错误
	public static final String YEE_REPEAT_ACCOUNT = "FA2002";// 资金账户重复
	public static final String YEE_NOMEMBER_NO = "FA2003";// 无对应会员编号
	public static final String YEE_PATER_MERCHANT = "FA2004";// 无对应父级商户
	public static final String YEE_FAIL_ACCOUNT_OPERATION = "FA2005";// 资金账户操作失败
	public static final String YEE_UNSUPPORT_ROLE  = "FA2006";// 不支持的商户角色
	public static final String YEE_NOCORRESPONDING_ACCOUNT  = "FA2007";// 无对应营销账户
	public static final String YEE_NOMARKETING_ACCOUNT = "FA2008";// 未开通营销账户
	public static final String YEE_NOCORRESPONDING_DEFINITION = "FA3001";// 无对应营销券定义
	public static final String YEE_MARKETING_EXPIRED = "FA3002";// 营销券已过期
	public static final String YEE_MARKETING_UNISSUED = "FA3003";// 营销券未发放
	public static final String YEE_MARKETING_RECEIVED = "FA3004";// 营销券已被领取
	public static final String YEE_MARKING_DEFINITION_STATUS_EXCEPTION = "FA3005";// 营销券定义状态异常
	public static final String YEE_MARKING_STATUS_EXCEPTION = "FA3005";// 营销券状态异常
	public static final String YEE_MARKETING_NUMBER_EXCEEDING = "FA3006";// 营销券发送数量超限
	public static final String YEE_MARKETING_ASCRIPTION_EXCEPTION  = "FA3007";// 营销券归属异常
	public static final String YEE_SELLER_ACCOUNT_NOTMATCH = "FA3008";// 领券人营销账户不匹配
	public static final String YEE_MARKETING_NON_CLAIM_STATE = "FA3009";// 营销券为非领取状态
	public static final String YEE_SENDER_RECIPIENT_NOTMATCH = "FA3010";// 发送者与领取者不匹配
	public static final String YEE_EXPIRED_ERROR = "FA3011";// 过期时间有误
	public static final String YEE_COUPON_MISMATCH_NOTMATCH = "FA3012";// 发券方类型不匹配
	public static final String YEE_NOSUPPORT_MERCHANT_NET = "MC2001";// 不支持的商户入网
	public static final String YEE_TOKEN_NULL = "OD1001";// token为空
	public static final String YEE_REPEATING_PART = "OD1002";// 分账方重复
	public static final String YEE_PROPORTION_ACCOUNT_ERROR = "OD1003";// 分账比例有误
	public static final String YEE_PROPORTION_AMOUNT_OVERRUN = "OD1004";// 分账金额超限
	public static final String YEE_TRANS_NO_PAYMENT = "OD1005";// 交易订单无支付订单
	public static final String YEE_REPEAT_UPDATE = "OD1006";// 重复更新
	public static final String YEE_ORDER_AMOUNT_ERROR = "OD1007";// 订单金额不符
	public static final String YEE_AMOUNT_MAST_THAN_ZERO = "OD1008";// 金额需大于0
	public static final String YEE_DIVISION_ERROR = "OD1009";// 分账信息有误
	public static final String YEE_ORDER_REFUND_FINISH = "OD1010";// 订单已全部退款完成
	public static final String YEE_CONNECTION_FAIL = "OD1011";// 连接失败，请重试
	public static final String YEE_TRANS_FAIL = "OD1012";// 交易失败，请联系商户
	public static final String YEE_MERCHANT_ACCOUNT_EMPTY = "OD1013";// 查询商户账户余额为空
	public static final String YEE_CREATE_ORDER_ERROR = "OD1014";// 创建订单失败
	public static final String YEE_EXPIRED_ORDER = "OD1015";// 订单超时，请重新下单
	public static final String YEE_ORDER_PAY_SUCCESS = "OD1016";// 订单已经支付成功
	public static final String YEE_VALIDITY_OORDER_ERROR  = "OD1017";// 订单有效期有误
	public static final String YEE_REQPARAM_MODIFY = "OD1018";// 请求参数发生改变
	public static final String YEE_ORDER_PROCESS = "OD1019";// 订单处理中
	public static final String YEE_REQUEST_TIMEOUT = "OD1020";// 请求超时
	public static final String YEE_NULL_BINDING_CARD = "OD2001";// 绑卡无效，请重选
	public static final String YEE_MERCHANT_INVALID = "OD2002";// 商户无效
	public static final String YEE_NULL_LEDER = "OD2003";// 无对应分账方
	public static final String YEE_NULL_BINDING_USER = "OD2004";// 用户未绑卡
	public static final String YEE_BINDING_MISMATCH = "OD2005";// 绑卡ID不匹配
	public static final String YEE_UNSUPPORT_MCC = "OD2006";// 商户不支持此MCC
	public static final String YEE_UNSUPPORT_PAYTYPE = "OD2007";// 暂不支持该付款方式，请更换重试
	public static final String YEE_NOOPEN_PARTIAL_REFUND = "OD2008";// 未开通部分退款
	public static final String YEE_NOOPEN_DIVISION_ACCOUNT  = "OD2009";// 未开通分账
	public static final String YEE_UNSUPPORT_CARTTYPE = "OD2010";// 暂不支持此卡类型，请换卡重试
	public static final String YEE_UNSUPPORT_CREDIT_RECHARGE  = "OD2011";// 暂不支持信用卡充值，请更换储蓄卡
	public static final String YEE_UNKNOWN_CARDCOUPON_TYPE = "OD3003";// 未知卡券资金类型
	public static final String YEE_VOUCHER_FAIL = "OD3004";// 销券失败
	public static final String YEE_NULL_MAST_PARAM = "PP1001";// 必填的参数为空
	public static final String YEE_UNSUPPORT_operation = "PP1002";// 不支持该操作
	public static final String YEE_PARAM_ERROR = "PP1003";// 参数有误
	public static final String YEE_PAYAMOUNT_ERROR = "PP1004";// 金额有误
	public static final String YEE_NULL_ORDER = "PP1005";// 无对应订单/记录
	public static final String YEE_REPEAT_REQUEST = "PP1006";// 重复请求
	public static final String YEE_MERCHANT_STATE_INVALID = "PP2001";// 商户状态无效
	public static final String YEE_NOCORRESPOND_MERCHANT = "PP2002";// 无对应商户
	public static final String YEE_NOMERCHANT_RELATIONAL_MODEL = "PP2003";// 无对应商户关系模型
	public static final String YEE_NOMERCHANT_ACCOUNT = "PP2004";// 无对应商户账户
	public static final String YEE_NOCORRESPOND_USER = "PP2005";// 无对应用户
	public static final String YEE_INVALID_USER_STATE = "PP2006";// 用户状态无效
	public static final String YEE_INSUFFICIENT_ACCOUNT_BALANCE = "PP2007";// 账户余额不足
	public static final String YEE_NoCORRESPOND_MARKING_VOUCHER = "PP3001";// 无对应营销券
	public static final String YEE_MARKETING_USED = "PP3002";// 营销券已经被使用
	public static final String YEE_BUSINESS_FAIL = "PP4001";// 业务处理失败
	public static final String YEE_BUSINESS_EXCEPTION = "PP4002";// 业务处理异常
	public static final String YEE_NOCORRESPONDING_TYPE = "US2001";// 无对应类型
	public static final String YEE_TOKEN_EXPIRED = "US2002";// token失效
	public static final String YEE_NOTOKEN_RECORD = "US2003";// 无对应token记录
	public static final String YEE_NOCORRESPONDENT_ACCOUNT = "US2004";// 无对应分账方账户
	public static final String YEE_NOCORRESPONDENT_USERACCOUNT = "US2005";// 无对应用户账户
	public static final String YEE_NOCORRESPONDENT_AGENT = "US2006";// 无对应代理商账号
	public static final String YEE_MEMBERSHIP_EXCEPTION = "US2007";// 会员认证状态异常
	public static final String YEE_NOCORRESPOND_ACCOUNT_MODEL = "US2008";// 无对应分账方模型
	public static final String YEE_NOCORRESPOND_PAYMENT_PASSWORD = "US2009";// 无对应支付密码
	public static final String YEE_PAYMENT_PASSWORD_FROZEN = "US2010";// 支付密码已冻结
	public static final String YEE_MEMBER_NOREAL_NAME = "US2011";// 会员未实名
	public static final String YEE_MEMBER_UNAVAILABLE = "US2012";// 会员不可用
	public static final String YEE_MEMBER_STATE_NOTACTIVE = "US000047";// 会员状态未激活
	public static final String YEE_NOBINDING_AVAILABLE_BANKCARD = "OD000159";// 没有绑定可用提现银行卡
	public static final String YEE_UNAVAILABLE_USER_BALANCE = "OD000160";// 提现用户无可用余额

}
