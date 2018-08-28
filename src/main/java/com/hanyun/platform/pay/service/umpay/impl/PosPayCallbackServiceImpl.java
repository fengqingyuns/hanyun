/**
 * 
 */
package com.hanyun.platform.pay.service.umpay.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.platform.pay.adapt.umpay.consts.UmWposConsts;
import com.hanyun.platform.pay.adapt.umpay.util.UmPayBizUtils;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.dao.UmpayOrderDao;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.UmpayOrder;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.service.umpay.PosPayCallbackService;
import com.hanyun.platform.pay.util.CardTypeUtil;
import com.hanyun.platform.pay.vo.callback.PosPayCallbackReq;

/**
 * POS刷卡支付回调
 * @author liyinglong@hanyun.com
 * @date 2017年5月9日 下午4:05:05
 */
@Service
public class PosPayCallbackServiceImpl implements PosPayCallbackService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PosPayCallbackServiceImpl.class);
    
    @Resource
    private UmpayOrderDao umpayOrderDao;
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;

    @Override
    @Transactional
    public boolean dealPayResultCallback(String reqparam) {
        PosPayCallbackReq req = parseReqParam(reqparam);
        if(req == null || StringUtils.isEmpty(req.getOutTradeNo())){
            LOGGER.error("wpos callback req param error: {}", reqparam);
            return false;
        }
        if(!UmWposConsts.TRADE_STATUS_PAY.equals(req.getTradeStatus())){
            LOGGER.error("wpos callback trade status not finish: {}", reqparam);
            return false;
        }
        
        UmpayOrder umorder = umpayOrderDao.selectByTransId(UmPayBizUtils.getTransIdFromOutTradeNo(req.getOutTradeNo()));
        if(umorder == null){
            LOGGER.error("umpay wpos order not found: {}", req.getOutTradeNo());
            return false;
        }
        PayTransaction trans = payTransactionDao.selectByTransId(umorder.getTransId());
        if(PaymentConsts.TRANS_STATUS_FINISH.equals(trans.getStatus())){
            return false;
        }
        Payment payment = paymentDao.selectByPayId(trans.getPayId());
        // 更新联动优势订单
        UmpayOrder umorderupd = new UmpayOrder();
        umorderupd.setTransId(trans.getTransId());
        umorderupd.setWposCashierTradeNo(req.getCashierTradeNo());
        umorderupd.setWposRefNo(req.getThirdSerialNo());
        umpayOrderDao.updateByTransIdSelective(umorderupd);
        // 更新支付方式及费率
        String payTypeDetail = CardTypeUtil.getCardTypeByChnType(req.getCardType());
        if(StringUtils.isNotBlank(payTypeDetail)){
            tradeServiceAssistLogic.updateTransPayTypeDetailInfo(trans, payTypeDetail);
        }
        // 更新支付流水状态
        tradeServiceAssistLogic.updatePayTransWhenPayFinish(trans);
        // 更新支付单状态
        tradeServiceAssistLogic.updatePaymentWhenPayFinish(payment, trans);
        // 通知订单系统
        tradeServiceAssistLogic.notifyOrderWhenPayFinish(payment, trans, null);
        
        return true;
    }

    /**
     * 解析请求参数
     * @param reqparam
     * @return
     */
    private PosPayCallbackReq parseReqParam(String reqparam){
        PosPayCallbackReq req = null;
        try {
            String jsonstr = reqparam;
            jsonstr =  StringUtils.replace(jsonstr, "=", "\":\"");
            jsonstr = StringUtils.replace(jsonstr, "&", "\",\"");
            jsonstr = "{\"" + jsonstr + "\"}";
            
            req = JsonUtil.fromJson(jsonstr, PosPayCallbackReq.class);
        } catch (Exception e) {
            LOGGER.error("wpos callback req param parse error: " + reqparam, e);
        }
        
        return req;
    }
}
