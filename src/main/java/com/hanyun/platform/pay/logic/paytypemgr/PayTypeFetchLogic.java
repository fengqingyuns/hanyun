/**
 * 
 */
package com.hanyun.platform.pay.logic.paytypemgr;

import com.hanyun.platform.pay.domain.MchActualPayModeInfo;

/**
 * 支付方式获取逻辑
 * @author liyinglong@hanyun.com
 * @date 2016年8月4日 下午8:13:27
 */
public interface PayTypeFetchLogic {
    /**
     * 获取在线支付方式信息
     * @param payType 支付方式
     * @param 品牌编号
     * @return 支付方式信息
     */
    public MchActualPayModeInfo fetchOnlinePayType(String payType, String brandId);
    
}
