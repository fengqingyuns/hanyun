package com.hanyun.platform.pay.service.ybpay.alipay;

import com.hanyun.platform.pay.adapt.yeepay.protocol.YeeResultNotifyRes;

/**
 * 
 * @date 2018年8月8日
 * 
 * @apiDefine Common 处理支付结果回调
 * @author litao@hanyun.com
 */
public interface YbAlipayCallbackService {
	 /**
     * 处理支付结果回调
     * @param req
     * @return
     */
    public void dealPayResultCallback(YeeResultNotifyRes req);

}
