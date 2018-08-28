/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.CloseOrderReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.CloseOrderRes;

/**
 * 关闭订单
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午5:19:35
 */
@Component
public class CibWeiXinCloseOrderAdapter extends CibWeiXinBaseAdapter<CloseOrderReq, CloseOrderRes> {

    @Override
    protected void doPreProcessRequest(CloseOrderReq req) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void doPostProcessResponse(CloseOrderReq req, CloseOrderRes res) {
        // TODO Auto-generated method stub

    }

    @Override
    protected CibWeiXinMethodType getMethodType() {
        return CibWeiXinMethodType.closeorder;
    }

}
