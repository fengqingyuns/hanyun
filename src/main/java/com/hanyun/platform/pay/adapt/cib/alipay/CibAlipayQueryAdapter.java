/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayMethodType;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayQueryReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayQueryRes;
import com.hanyun.platform.pay.dao.CibAlipayOrderDao;
import com.hanyun.platform.pay.domain.CibAlipayOrder;

/**
 * 查询订单
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午8:24:47
 */
@Component
public class CibAlipayQueryAdapter extends CibAlipayBaseAdapter<AlipayQueryReq, AlipayQueryRes> {
    @Resource
    private CibAlipayOrderDao cibAlipayOrderDao;
    
    @Override
    protected void doPreProcessRequest(AlipayQueryReq req) {
        
    }

    @Override
    protected void doPostProcessResponse(AlipayQueryReq req, AlipayQueryRes res) {
        CibAlipayOrder aliorder = new CibAlipayOrder();
        
        aliorder.setOutTradeNo(res.getOutTradeNo());
        aliorder.setTransactionId(res.getTransactionId());
        aliorder.setPassTradeNo(res.getPassTradeNo());
        aliorder.setOpenid(res.getOpenid());
        aliorder.setTimeEnd(res.getTimeEnd());
        aliorder.setBuyerLogonId(res.getBuyerLogonId());
        aliorder.setFundBillList(res.getFundBillList());
        aliorder.setTradeState(res.getTradeState());
        aliorder.setTradeType(res.getTradeType());
        
        cibAlipayOrderDao.updateByOutTradeNo(aliorder);
    }

    @Override
    protected String getMethodType() {
        return CibAlipayMethodType.QUERY;
    }

}
