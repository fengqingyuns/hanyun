/**
 * 
 */
package com.hanyun.platform.pay.util;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import com.hanyun.ground.util.date.DateFormatUtil;

/**
 * 业务ID工具类
 * @author liyinglong@hanyun.com
 * @date 2016年7月14日 下午3:53:46
 */
public abstract class BusinessIdUtils {
    /** 支付单号前缀 **/
    public static final String PAY_ID_PREFIX = "100";
    /** 交易流水号前缀 **/
    public static final String TRANS_ID_PREFIX = "110";
    /** 交易账户流水号前缀 **/
    public static final String TRADE_ACC_SEQ_ID_PREFIX = "200";
    /** 任务消息ID前缀 **/
    public static final String TASK_MESSAGE_ID_PREFIX = "900";
        
    /**
     * 生成商户编号<br>
     * 格式：13位当前毫秒数＋5位随机数
     * @return
     */
    public static String genMechantId(){
        String main  = String.valueOf(System.currentTimeMillis());
        String random = RandomStringUtils.randomNumeric(5);
        return main + random;
    }
    
    /**
     * 生成支付单号<br>
     * 格式：3位业务前缀 + 17位时间串(到毫秒) + 7位随机数
     * @return
     */
    public static String genPayId(){
        return generateBusinessId(PAY_ID_PREFIX);
    }
    
    /**
     * 生成支付流水号<br>
     * 格式：3位业务前缀 + 17位时间串(到毫秒) + 7位随机数
     * @return
     */
    public static String genTransId(){
        return generateBusinessId(TRANS_ID_PREFIX);
    }
    
    /**
     * 生成交易账户流水号<br>
     * 格式：3位业务前缀 + 17位时间串(到毫秒) + 7位随机数
     * @return
     */
    public static String genTradeAccSeqId(){
        return generateBusinessId(TRADE_ACC_SEQ_ID_PREFIX);
    }
    
    /**
     * 生成任务消息ID号<br>
     * 格式：3位业务前缀 + 17位时间串(到毫秒) + 7位随机数
     * @return
     */
    public static String genTaskMessageId(){
        return generateBusinessId(TASK_MESSAGE_ID_PREFIX);
    }
    
    /**
     * 生成业务ID<br>
     * 格式：3位业务前缀 + 17位时间串(到毫秒) + 7位随机数
     * @param prefix
     * @return
     */
    private static String generateBusinessId(String prefix){
        StringBuilder sb = new StringBuilder(32);
        sb.append(prefix);
        sb.append(DateFormatUtil.formatDateTimeMillsNoSep(new Date()));
        sb.append(RandomStringUtils.randomNumeric(7));
        
        return sb.toString();
    }

}
