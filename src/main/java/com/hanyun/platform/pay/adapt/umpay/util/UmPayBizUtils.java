/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay.util;

/**
 * 联动优势业务工具类
 * @author liyinglong@hanyun.com
 * @date 2017年6月21日 下午4:52:24
 */
public abstract class UmPayBizUtils {
    /**
     * 生成传给wpos的商户订单号，每次重新发起刷卡支付都要变更<br>
     * 规则：交易流水号transid + “-” + 序号
     * @param oldOutTradeNo
     * @return
     */
    public static String genNewOutTradeNo(String oldOutTradeNo){
        String[] fields = oldOutTradeNo.split("-");
        String transId = fields[0];
        int seq = 1;
        if(fields.length > 1){
            int tmpseq = Integer.valueOf(fields[1]);
            seq = tmpseq + 1;
        }
        String newOutTradeNo = transId + "-" + seq;
        
        return newOutTradeNo;
    }
    
    /**
     * 从wpos的商户订单号中获取交易流水号transId
     * @param outTradeNo
     * @return
     */
    public static String getTransIdFromOutTradeNo(String outTradeNo){
        String[] fields = outTradeNo.split("-");
        
        return fields[0];
    }
}
