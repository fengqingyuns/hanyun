/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderQueryReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderQueryRes;
import com.hanyun.platform.pay.dao.CibWeixinOrderDao;
import com.hanyun.platform.pay.domain.CibWeixinOrder;

/**
 * 查询订单
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午5:14:15
 */
@Component
public class CibWeiXinOrderQueryAdapter extends CibWeiXinBaseAdapter<OrderQueryReq, OrderQueryRes> {
    @Resource
    private CibWeixinOrderDao cibWeixinOrderDao;
    
    @Override
    protected void doPreProcessRequest(OrderQueryReq req) {

    }

    @Override
    protected void doPostProcessResponse(OrderQueryReq req, OrderQueryRes res) {
        
        CibWeixinOrder wxorder = cibWeixinOrderDao.selectByOutTradeNo(req.getOut_trade_no());
        
        wxorder.setVersion(res.getVersion());
        wxorder.setDeviceInfo(res.getDevice_info());
        wxorder.setTransactionId(res.getTransaction_id());
        wxorder.setPassTradeNo(res.getPass_trade_no());
        wxorder.setTradeType(res.getTrade_type());
        wxorder.setOpenid(res.getOpenid());
        wxorder.setIsSubscribe(res.getIs_subscribe());
        wxorder.setBankType(res.getBank_type());
        wxorder.setTimeEnd(res.getTime_end());
        wxorder.setTradeState(res.getTrade_state());
        
        cibWeixinOrderDao.updateByOutTradeNo(wxorder);
    }

    @Override
    protected CibWeiXinMethodType getMethodType() {
        return CibWeiXinMethodType.orderquery;
    }

}
