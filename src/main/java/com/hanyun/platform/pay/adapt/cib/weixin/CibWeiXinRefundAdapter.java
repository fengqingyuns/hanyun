/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderRefundReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderRefundRes;

/**
 * 申请退款
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午5:25:32
 */
@Component
public class CibWeiXinRefundAdapter extends CibWeiXinBaseAdapter<OrderRefundReq, OrderRefundRes> {

    @Override
    protected void doPreProcessRequest(OrderRefundReq req) {
        req.setOp_user_id(req.getMch_id());

    }

    @Override
    protected void doPostProcessResponse(OrderRefundReq req, OrderRefundRes res) {
        // TODO Auto-generated method stub

    }

    @Override
    protected CibWeiXinMethodType getMethodType() {
        return CibWeiXinMethodType.refund;
    }

}
