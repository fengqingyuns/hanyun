package com.hanyun.platform.pay.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @Description: 卡类型转换
 * @author wangjie@hanyun.com
 * @date 2016年9月3日 下午1:29:12
 */
public class CardTypeUtil {

	/** 卡类型转换 **/
	public static final Map<String, String> cardTypeMap = new HashMap<String, String>();
	public static final Map<String, String> CHN_CARD_TYPE_MAP = new HashMap<String, String>();

	static {
		cardTypeMap.put("00", "BANKCARD_DEBIT");// 借记卡
		cardTypeMap.put("01", "BANKCARD_CREDIT");// 信用卡
		
		CHN_CARD_TYPE_MAP.put("借记卡", "BANKCARD_DEBIT");
		CHN_CARD_TYPE_MAP.put("贷记卡", "BANKCARD_CREDIT");
	}

	/**
	 * 
	 * @Title: cardTypeMap
	 * @Description:
	 * @param 
	 * @return string 
	 * @throws
	 */
	public static String getCardTypeTransfer(String cardType) {
		if (StringUtils.isNotEmpty(cardType)) {
			return cardTypeMap.get(cardType);
		}
		return "";
	}
	
	/**
	 * 根据中文类型获取系统内卡类型
	 * @param chnType
	 * @return
	 */
	public static String getCardTypeByChnType(String chnType){
	    return CHN_CARD_TYPE_MAP.get(chnType);
	}
}
