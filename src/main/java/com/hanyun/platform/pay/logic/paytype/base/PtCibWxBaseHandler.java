/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.base;

import javax.annotation.Resource;

import com.hanyun.platform.pay.adapt.cib.weixin.CibWeiXinRefundAdapter;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderRefundReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderRefundRes;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibWeixinOrderDao;
import com.hanyun.platform.pay.dao.CibWeixinRefundDao;
import com.hanyun.platform.pay.domain.CibWeixinOrder;
import com.hanyun.platform.pay.domain.CibWeixinRefund;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundWeixinExtData;

/**
 * 兴业微信基本处理类
 * @author liyinglong@hanyun.com
 * @date 2016年9月11日 下午12:19:41
 */
public class PtCibWxBaseHandler extends PayTypeDefaultHandler {
    @Resource
    private CibWeixinOrderDao cibWeixinOrderDao;
    @Resource
    private CibWeixinRefundDao cibWeixinRefundDao;
    @Resource
    private CibWeiXinRefundAdapter cibWeiXinRefundAdapter;
    
    @Override
    public Object doRefund(TradeRefundReq req) {
        PayTransaction trans = PayProcessContext.getTransation();
        PayTransaction reftrans = PayProcessContext.getRefundTransation();
        
        CibWeixinOrder wxorder = cibWeixinOrderDao.selectByOutTradeNo(trans.getTransId());
        OrderRefundReq refreq = new OrderRefundReq();
        refreq.setDevice_info(wxorder.getDeviceInfo());
        refreq.setOut_trade_no(wxorder.getOutTradeNo());
        refreq.setTransaction_id(wxorder.getTransactionId());
        refreq.setPass_trade_no(wxorder.getPassTradeNo());
        refreq.setOut_refund_no(reftrans.getTransId());
        refreq.setTotal_fee(trans.getAmount());
        refreq.setRefund_fee(reftrans.getAmount());
        
        OrderRefundRes refres = cibWeiXinRefundAdapter.request(refreq);
        
        CibWeixinRefund wxrefund = cibWeixinRefundDao.selectByOutRefundNo(reftrans.getTransId());
        if(wxrefund == null){
            wxrefund = new CibWeixinRefund();
            wxrefund.setBrandId(wxorder.getBrandId());
            wxrefund.setStoreId(wxorder.getStoreId());
            wxrefund.setOutTradeNo(wxorder.getOutTradeNo());
            wxrefund.setOutRefundNo(reftrans.getTransId());
            
            wxrefund.setRefundId(refres.getRefund_id());
            wxrefund.setPassRefundNo(refres.getPass_refund_no());
            wxrefund.setRefundFee(refres.getRefund_fee());
            wxrefund.setRefundChannel(refres.getRefund_channel());
            
            cibWeixinRefundDao.insert(wxrefund);
        }else{
            wxrefund.setRefundId(refres.getRefund_id());
            wxrefund.setPassRefundNo(refres.getPass_refund_no());
            wxrefund.setRefundFee(refres.getRefund_fee());
            wxrefund.setRefundChannel(refres.getRefund_channel());
            
            cibWeixinRefundDao.updateByOutRefundNo(wxrefund);
        }
        
        super.doRefund(req);
        
        TradeRefundWeixinExtData extdata = new TradeRefundWeixinExtData();
        extdata.setWxOrderRefundId(refres.getRefund_id());
        
        return extdata;
    }
}
