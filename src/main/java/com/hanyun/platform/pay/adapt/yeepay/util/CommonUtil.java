package com.hanyun.platform.pay.adapt.yeepay.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @date 2018年8月15日
 * 
 * @apiDefine Common 支付相关
 * @author litao@hanyun.com
 */
public class CommonUtil {

	/**
	 * 日期时间格式化-常规分隔<br>
	 * 格式: yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 *            日期
	 * @return 格式日期串
	 */
	public static String formatDateTime(String date) {
		StringBuilder sb = new StringBuilder();
		sb.append(date.substring(0, 4)).append("-");
		sb.append(date.substring(4, 6)).append("-");
		sb.append(date.substring(6, 8)).append(" ");
		sb.append(date.substring(8, 10)).append(":");
		sb.append(date.substring(10, 12)).append(":");
		sb.append(date.substring(12, 14));
		return sb.toString();
	}
}
