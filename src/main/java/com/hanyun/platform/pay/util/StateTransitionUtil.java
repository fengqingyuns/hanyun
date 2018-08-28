package com.hanyun.platform.pay.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
* @Description: 字典
* @author wangjie@hanyun.com
* @date 2016年9月3日 下午1:29:12
 */
public class StateTransitionUtil {
    
	/** 微信状态  **/
    public static final Map<String, Integer> payTypeMap = new HashMap<String, Integer>();
    
    static{
    	payTypeMap.put("SUCCESS", 20);//成功交易状态或者退款成功状态	
    	payTypeMap.put("REVOKED", 30);//撤销	
    	payTypeMap.put("PROCESSING", 20);//正在退款中	
    }
	
	/**
	 * 
	* @Title: wxStatusTransferMap 
	* @Description: 把从微信获取的支付状态值SUCCESS 转化为 汉云 pay_transaction 中status  
	* @param  
	* @return int   
	* @throws
	 */
	public static int getWxStatusTransfer(String pareStatus) {
		if (StringUtils.isNotEmpty(pareStatus)) {
			return payTypeMap.get(pareStatus);
		}
		return 0;
	}
}
