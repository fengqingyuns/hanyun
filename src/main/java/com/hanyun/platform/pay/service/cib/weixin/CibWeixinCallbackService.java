/**
 * 
 */
package com.hanyun.platform.pay.service.cib.weixin;

import com.hanyun.platform.pay.adapt.cib.weixin.protocol.PayResultNotifyReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.PayResultNotifyRes;

/**
 * 兴业微信回调处理
 * @author liyinglong@hanyun.com
 * @date 2016年9月7日 上午9:59:36
 */
public interface CibWeixinCallbackService {
    /**
     * 处理支付结果回调
     * @param req
     * @return
     */
    public PayResultNotifyRes dealPayResultCallback(PayResultNotifyReq req);
}
