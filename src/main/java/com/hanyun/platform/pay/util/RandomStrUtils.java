/**
 * 
 */
package com.hanyun.platform.pay.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 生成随机字符串工具
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午12:02:37
 */
public abstract class RandomStrUtils {
    /**
     * 生成支付交互过程中的动态随机串<br>
     * 19位随机串 ＋ 13位当前毫秒数
     * @return 随机串
     */
    public static String genNonceStr(){
        return RandomStringUtils.randomAlphanumeric(19) + String.valueOf(System.currentTimeMillis());
    }
    
    /**
     * 生成商户密钥
     * @return 密钥
     */
    public static String genMerchantSecKey(){
        return RandomStringUtils.randomAlphanumeric(32).toUpperCase();
    }
}
