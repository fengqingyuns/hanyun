/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.base;

import javax.annotation.Resource;

import com.hanyun.platform.pay.adapt.cib.alipay.CibAlipayRefundAdapter;
import com.hanyun.platform.pay.adapt.cib.alipay.CibAlipayRefundQueryAdapter;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundQueryReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundQueryRes;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundRes;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibAlipayOrderDao;
import com.hanyun.platform.pay.dao.CibAlipayRefundDao;
import com.hanyun.platform.pay.domain.CibAlipayOrder;
import com.hanyun.platform.pay.domain.CibAlipayRefund;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;

/**
 * 兴业支付宝基本处理类
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年1月3日 下午7:28:13
 */
public class PtCibAliBaseHandler extends PayTypeDefaultHandler {
    @Resource
    private CibAlipayOrderDao cibAlipayOrderDao;
    @Resource
    private CibAlipayRefundDao cibAlipayRefundDao;
    @Resource
    private CibAlipayRefundAdapter cibAlipayRefundAdapter;
    @Resource
    private CibAlipayRefundQueryAdapter cibAlipayRefundQueryAdapter;

    @Override
    public Object doRefund(TradeRefundReq req) {
        PayTransaction trans = PayProcessContext.getTransation();
        PayTransaction reftrans = PayProcessContext.getRefundTransation();

        CibAlipayOrder aliorder = cibAlipayOrderDao.selectByOutTradeNo(trans.getTransId());
        AlipayRefundReq refreq = new AlipayRefundReq();
        refreq.setDeviceInfo(aliorder.getDeviceInfo());
        refreq.setOutTradeNo(aliorder.getOutTradeNo());
        refreq.setTransactionId(aliorder.getTransactionId());
        refreq.setPassTradeNo(aliorder.getPassTradeNo());
        refreq.setOutRefundNo(reftrans.getTransId());
        refreq.setRefundFee(reftrans.getAmount());

        AlipayRefundRes refres = cibAlipayRefundAdapter.request(refreq);
        
        if(refres == null || CibAlipayConsts.ERRCODE_TRADE_FINISHED.equals(refres.getErrCode())){
            AlipayRefundQueryReq refqryreq = new AlipayRefundQueryReq();
            refqryreq.setOutTradeNo(aliorder.getOutTradeNo());
            refqryreq.setOutRefundNo(reftrans.getTransId());
            AlipayRefundQueryRes refqryres = cibAlipayRefundQueryAdapter.request(refqryreq);
            if(refres == null){
                refres = new AlipayRefundRes();
            }
            refres.setPassRefundNo(refqryres.getPassRefundNo());
            refres.setRefundFee(refqryres.getRefundFee());
            refres.setGmtRefundPay(refqryres.getGmtRefundPay());
            refres.setRefundDetailItemList(refqryres.getRefundDetailItemList());
        }

        CibAlipayRefund alirefund = cibAlipayRefundDao.selectByOutRefundNo(reftrans.getTransId());
        if (alirefund == null) {
            alirefund = new CibAlipayRefund();
            alirefund.setBrandId(aliorder.getBrandId());
            alirefund.setStoreId(aliorder.getStoreId());
            alirefund.setOutTradeNo(aliorder.getOutTradeNo());
            alirefund.setOutRefundNo(reftrans.getTransId());

            alirefund.setPassRefundNo(refres.getPassRefundNo());
            alirefund.setRefundFee(refres.getRefundFee());
            alirefund.setFundChange(refres.getFundChange());
            alirefund.setGmtRefundPay(refres.getGmtRefundPay());
            alirefund.setRefundDetailItemList(refres.getRefundDetailItemList());

            cibAlipayRefundDao.insert(alirefund);
        } else {
            alirefund.setPassRefundNo(refres.getPassRefundNo());
            alirefund.setRefundFee(refres.getRefundFee());
            alirefund.setFundChange(refres.getFundChange());
            alirefund.setGmtRefundPay(refres.getGmtRefundPay());
            alirefund.setRefundDetailItemList(refres.getRefundDetailItemList());

            cibAlipayRefundDao.updateByOutRefundNo(alirefund);
        }

        super.doRefund(req);

        return null;
    }
}
