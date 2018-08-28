/**
 * 
 */
package com.hanyun.platform.pay.util;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hanyun.ground.util.security.MD5Util;

/**
 * 签名生成工具
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 上午10:53:40
 */
public abstract class SignatureUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(SignatureUtils.class);

    /**
     * 校验签名
     * 
     * @param obj
     *            待签名对象
     * @param secKey
     *            安全密钥
     * @param sign
     *            待校验签名
     * @return 是否有效
     */
    public static boolean check(Object obj, String secKey, String sign) {
        String genSign = sign(obj, secKey);
        return genSign.equalsIgnoreCase(sign);
    }

    /**
     * 校验签名
     * 
     * @param kvs
     *            待签名字符串键值对
     * @param secKey
     *            安全密钥
     * @param sign
     *            待校验签名
     * @return 是否有效
     */
    public static boolean check(Map<String, String> kvs, String secKey, String sign) {
        String genSign = sign(kvs, secKey);
        return genSign.equalsIgnoreCase(sign);
    }

    /**
     * 生成签名
     * 
     * @param obj
     *            待签名对象
     * @param secKey
     *            安全密钥
     * @return 签名串
     */
    public static String sign(Object obj, String secKey) {
        if (obj == null || StringUtils.isBlank(secKey)) {
            return null;
        }
        Map<String, String> kvs = null;
        try {
            kvs = getBeanProps(obj, "sign");
        } catch (Exception e) {
            LOGGER.error("get obj property error", e);
            kvs = null;
        }
        if (kvs == null || kvs.isEmpty()) {
            return null;
        }
        return sign(kvs, secKey);
    }

    /**
     * 生成签名
     * 
     * @param kvs
     *            待签名字符串键值对
     * @param secKey
     *            安全密钥
     * @return 签名串
     */
    public static String sign(Map<String, String> kvs, String secKey) {
        if (kvs == null || kvs.isEmpty() || StringUtils.isBlank(secKey)) {
            return null;
        }
        String signString = genSignOriginString(kvs, secKey, "key");
        LOGGER.debug("sign origin: {}", signString);

        String sign = MD5Util.md5Hex(signString);
        sign = sign.toUpperCase();

        return sign;
    }

    /**
     * 生成待签名字符串
     * 
     * @param kvs
     *            待签名字符串键值对
     * @param secKey
     *            安全密钥
     * @return 待签名字符串
     */
    private static String genSignOriginString(Map<String, String> kvs, String secKey, String keyFieldName) {
        StringBuilder sb = new StringBuilder(300);
        String[] keys = kvs.keySet().toArray(new String[0]);
        // 按字符自然顺序排序
        Arrays.sort(keys);
        for (String k : keys) {
            String v = kvs.get(k);
            if (StringUtils.isBlank(k) || StringUtils.isBlank(v)) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(k);
            sb.append('=');
            sb.append(v);
        }
        sb.append("&");
        sb.append(keyFieldName);
        sb.append("=");
        sb.append(secKey);

        return sb.toString();
    }

    /**
     * 获取对象属性
     * 
     * @param obj
     *            对象
     * @return 属性Ｍap
     */
    private static Map<String, String> getBeanProps(Object obj, String signFieldName) throws Exception {
        Map<String, String> props = BeanUtils.describe(obj);

        props.remove(signFieldName);
        props.remove("class");

        return props;
    }
}
