/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.ReverseOrderReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.ReverseOrderRes;

/**
 * 撤消订单
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午5:39:25
 */
@Component
public class CibWeiXinReverseOrderAdapter extends CibWeiXinBaseAdapter<ReverseOrderReq, ReverseOrderRes> {

    @Override
    protected void doPreProcessRequest(ReverseOrderReq req) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void doPostProcessResponse(ReverseOrderReq req, ReverseOrderRes res) {
        // TODO Auto-generated method stub

    }

    @Override
    protected CibWeiXinMethodType getMethodType() {
        return CibWeiXinMethodType.reverse;
    }

}
