/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.RefundQueryReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.RefundQueryRes;

/**
 * 退款查询
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午5:27:03
 */
@Component
public class CibWeiXinRefundQueryAdapter extends CibWeiXinBaseAdapter<RefundQueryReq, RefundQueryRes> {

    @Override
    protected void doPreProcessRequest(RefundQueryReq req) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void doPostProcessResponse(RefundQueryReq req, RefundQueryRes res) {
        // TODO Auto-generated method stub

    }

    @Override
    protected CibWeiXinMethodType getMethodType() {
        return CibWeiXinMethodType.refundquery;
    }

}
