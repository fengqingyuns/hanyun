/**
 * 
 */
package com.hanyun.platform.pay.consts;

import com.hanyun.ground.util.protocol.MessageId;
import com.hanyun.ground.util.protocol.Project;

/**
 * 业务结果码
 * @author liyinglong@hanyun.com
 * @date 2016年8月10日 下午6:33:15
 */
public interface BizResCode {
    MessageId SYSTEMERROR = MessageId.create(Project.PAYMENT_API, 1, "系统错误");
    MessageId PARAMERROR = MessageId.create(Project.PAYMENT_API, 2, "参数错误");
    
    MessageId SIGNERROR = MessageId.create(Project.PAYMENT_API, 11, "签名错误");
    
    MessageId PAYTYPENOTAVAIL = MessageId.create(Project.PAYMENT_API, 101, "支付方式不可用");
    MessageId PAYTYPENOTCHANGE = MessageId.create(Project.PAYMENT_API, 102, "己处理支付，支付方式不可变更");
    MessageId REFUNDNOTALLOWED = MessageId.create(Project.PAYMENT_API, 103, "当前状态不允许退款，未完成支付或已退款完成");
    MessageId REFUNDEXISTPROCESS = MessageId.create(Project.PAYMENT_API, 104, "存在处理中的退款单");
    MessageId REFUNDAMOUNTERROR = MessageId.create(Project.PAYMENT_API, 105, "支付单可退款金额不足");
    MessageId PAY_NOT_ALLOW = MessageId.create(Project.PAYMENT_API, 106, "不允许再支付，支付单已完成或已撤消");
    MessageId PAY_AMOUNT_ERROR = MessageId.create(Project.PAYMENT_API, 107, "支付金额不正确");
    MessageId PAY_NEED_PRE_CREATE = MessageId.create(Project.PAYMENT_API, 108, "需要先创建预支付单");
    MessageId PAY_AMOUNT_NOT_CHANGE = MessageId.create(Project.PAYMENT_API, 109, "支付金额不可改变");
    MessageId PAY_TYPE_TRANS_NOT_ALLOW = MessageId.create(Project.PAYMENT_API, 110, "不允许再支付，该支付方式已支付完成或已撤消");
    MessageId REFUND_TRANS_AMOUNTERROR = MessageId.create(Project.PAYMENT_API, 111, "支付流水可退款金额不足");
    MessageId PAYMENT_NOT_EXIST = MessageId.create(Project.PAYMENT_API, 112, "支付单不存在");
    MessageId PAY_TRANS_NOT_EXIST = MessageId.create(Project.PAYMENT_API, 113, "支付流水不存在");
   
    MessageId CIBWEIXIN_ERROR = MessageId.create(Project.PAYMENT_API, 300, "兴业微信接口错误");
    MessageId CIBWEIXIN_ERROR_PARAM = MessageId.create(Project.PAYMENT_API, 301, "兴业微信接口参数生成错误");
    MessageId CIBWEIXIN_ERROR_REQ = MessageId.create(Project.PAYMENT_API, 302, "兴业微信接口请求错误");
    MessageId CIBWEIXIN_ERROR_STATUS = MessageId.create(Project.PAYMENT_API, 303, "兴业微信接口请求状态错误");
    MessageId CIBWEIXIN_ERROR_PARSE = MessageId.create(Project.PAYMENT_API, 304, "兴业微信接口结果解析错误");
    MessageId CIBWEIXIN_ERROR_RETURNCODE = MessageId.create(Project.PAYMENT_API, 305, "兴业微信接口结果返回码错误");
    MessageId CIBWEIXIN_ERROR_SIGN = MessageId.create(Project.PAYMENT_API, 306, "兴业微信签名错误");
    MessageId CIBWEIXIN_ERROR_MERCHANT = MessageId.create(Project.PAYMENT_API, 307, "兴业微信获取商户信息失败");
    MessageId CIBWEIXIN_ERROR_RESULTCODE = MessageId.create(Project.PAYMENT_API, 308, "兴业微信接口业务结果码错误");
    MessageId CIBWEIXIN_ERROR_RESULEMPTY = MessageId.create(Project.PAYMENT_API, 309, "兴业微信接口返回结果为空");
    
    MessageId CIB_ALIPAY_ERROR = MessageId.create(Project.PAYMENT_API, 330, "兴业支付宝接口错误");
    MessageId CIB_ALIPAY_ERROR_PARAM = MessageId.create(Project.PAYMENT_API, 331, "兴业支付宝接口参数生成错误");
    MessageId CIB_ALIPAY_ERROR_REQ = MessageId.create(Project.PAYMENT_API, 332, "兴业支付宝接口请求错误");
    MessageId CIB_ALIPAY_ERROR_STATUS = MessageId.create(Project.PAYMENT_API, 333, "兴业支付宝接口请求状态错误");
    MessageId CIB_ALIPAY_ERROR_PARSE = MessageId.create(Project.PAYMENT_API, 334, "兴业支付宝接口结果解析错误");
    MessageId CIB_ALIPAY_ERROR_RETURNCODE = MessageId.create(Project.PAYMENT_API, 335, "兴业支付宝接口结果返回码错误");
    MessageId CIB_ALIPAY_ERROR_SIGN = MessageId.create(Project.PAYMENT_API, 336, "兴业支付宝签名错误");
    MessageId CIB_ALIPAY_ERROR_MERCHANT = MessageId.create(Project.PAYMENT_API, 337, "兴业支付宝获取商户信息失败");
    MessageId CIB_ALIPAY_ERROR_RESULTCODE = MessageId.create(Project.PAYMENT_API, 338, "兴业支付宝接口业务结果码错误");
    MessageId CIB_ALIPAY_ERROR_RESULEMPTY = MessageId.create(Project.PAYMENT_API, 339, "兴业支付宝接口返回结果为空");
    MessageId CIB_ALIPAY_FAIL_REFUND_STATUS = MessageId.create(Project.PAYMENT_API, 340, "兴业支付宝退款查询退款失败");
    
    MessageId CIB_MERCHANT_EMPTY = MessageId.create(Project.PAYMENT_API, 350, "兴业银行商户未配置");
    MessageId CIB_MERCHANT_STORE_EMPTY = MessageId.create(Project.PAYMENT_API, 351, "兴业银行商户门店未配置");
    
    MessageId UMPAY_ERROR_PARAM = MessageId.create(Project.PAYMENT_API, 401, "联动优势接口参数生成错误");
    MessageId UMPAY_ERROR_REQ = MessageId.create(Project.PAYMENT_API, 402, "联动优势接口请求错误");
    MessageId UMPAY_ERROR_STATUS = MessageId.create(Project.PAYMENT_API, 403, "联动优势接口请求状态错误");
    MessageId UMPAY_ERROR_PARSE = MessageId.create(Project.PAYMENT_API, 404, "联动优势接口结果解析错误");
    MessageId UMPAY_ERROR_DECRYPT = MessageId.create(Project.PAYMENT_API, 405, "联动优势接口结果解密错误");
    MessageId UMPAY_ERROR_RESPONSECODE = MessageId.create(Project.PAYMENT_API, 406, "联动优势接口结果码错误");
    
    MessageId PAY_BANKCARD_PARAM_ERROR = MessageId.create(Project.PAYMENT_API, 501, "银行卡支付参数错误");
    
    MessageId REFUND_NOT_ALLOW_NO_MONEY = MessageId.create(Project.PAYMENT_API, 531, "余额不足，不能发起退款");
    
    MessageId QUERY_YEE_ORDER_ERROR = MessageId.create(Project.PAYMENT_API, 601, "主动查询易宝支付单失败");
}
