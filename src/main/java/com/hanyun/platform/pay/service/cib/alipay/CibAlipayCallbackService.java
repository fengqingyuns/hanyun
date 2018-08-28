/**
 * 
 */
package com.hanyun.platform.pay.service.cib.alipay;

import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayResultNotifyReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayResultNotifyRes;

/**
 * 兴业支付宝回调处理
 * @author liyinglong@hanyun.com
 * @date 2017年1月5日 上午11:28:13
 */
public interface CibAlipayCallbackService {
    /**
     * 处理支付结果回调
     * @param req
     * @return
     */
    public AlipayResultNotifyRes dealPayResultCallback(AlipayResultNotifyReq req);
}
