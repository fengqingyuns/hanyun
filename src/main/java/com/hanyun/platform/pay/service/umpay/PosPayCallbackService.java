/**
 * 
 */
package com.hanyun.platform.pay.service.umpay;

/**
 * POS刷卡支付回调
 * @author liyinglong@hanyun.com
 * @date 2017年5月9日 下午3:05:23
 */
public interface PosPayCallbackService {
    /**
     * 处理支付结果回调
     * @param reqparam
     */
    public boolean dealPayResultCallback(String reqparam);
}
