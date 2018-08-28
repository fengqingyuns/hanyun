package com.hanyun.platform.pay.consts;
/**
 * 支付方式相关常量
 * @author wangximin@hanyun.com
 * 
 * @date 2016年8月11日 下午9:10:38
 *
 */
public class PayModeConsts {
	/** 服务状态常量 -常规*/
	public static final Integer PAY_SERVSTATUS_OFF = 0; 
	/** 服务状态常量 -服务中*/
	public static final Integer PAY_SERVSTATUS_ON = 1; 
	
	/** 禁用状态常量 - 启用*/
	public static final Integer PAY_AVAILSTATUS_ON = 0; 
	/** 禁用状态常量 - 禁用*/
	public static final Integer PAY_AVAILSTATUS_OFF = 1;
	/**可用 */
	public static final Integer PAY_CHN_BILL_STATUS_ON = 0;
	/** 不可用 */
	public static final Integer PAY_CHN_BILL_STATUS_OFF = 1;

}
