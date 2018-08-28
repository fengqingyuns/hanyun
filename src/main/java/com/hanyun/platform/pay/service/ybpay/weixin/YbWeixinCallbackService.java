package com.hanyun.platform.pay.service.ybpay.weixin;

import com.hanyun.platform.pay.adapt.yeepay.protocol.YeeResultNotifyRes;

/**
 * 
 * @date 2018年8月8日
 * 
 * @apiDefine Common 微信支付回调
 * @author litao@hanyun.com
 */
public interface YbWeixinCallbackService {
	 /**
     * 处理支付结果回调
     * @param req
     * @return
     */
    public void dealPayResultCallback(YeeResultNotifyRes req);

}
