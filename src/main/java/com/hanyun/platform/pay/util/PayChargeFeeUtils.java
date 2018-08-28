/**
 * 
 */
package com.hanyun.platform.pay.util;

import com.hanyun.platform.pay.consts.PaymentConsts;

/**
 * 支付手续费相关工具类
 * @author liyinglong@hanyun.com
 * @date 2016年10月27日 下午2:58:51
 */
public abstract class PayChargeFeeUtils {
    
    /**
     * 计算手续费
     * @param amount 交易金额
     * @param feeRate 费率
     * @param feeMax 手续费封顶
     * @return
     */
    public static long calcChargeFee(Long amount, Integer feeRate, Integer feeMax){
        if(amount == null || amount < 0 || feeRate == null || feeRate <= 0){
            return 0;
        }
        double originfee = amount * feeRate * 1.0 / PaymentConsts.FEE_RATE_MULTIPLIER;
        long fee = Math.round(originfee); // 四舍五入
        // 处理封顶
        if(feeMax != null && feeMax > 0){
            fee = (fee > feeMax) ? feeMax : fee;
        }
        
        return fee;
    }
}
