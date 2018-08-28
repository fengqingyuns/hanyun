/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.bankcard;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.umpay.UmPayConfig;
import com.hanyun.platform.pay.adapt.umpay.util.UmPayBizUtils;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.UmpayOrderDao;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.UmpayOrder;
import com.hanyun.platform.pay.logic.paytype.base.PayTypeDefaultHandler;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.util.CardTypeUtil;
import com.hanyun.platform.pay.vo.trade.TradePreCreateBankCardExtData;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePayReq;

/**
 * 银行卡刷卡处理类
 * 
 * @author wangjie@hanyun.com
 * @date 2016年11月23日 下午15:36:45
 */
@Component
public class PtBankCardHandler extends PayTypeDefaultHandler {
    @Resource
    private UmPayConfig umPayConfig;
    @Resource
    private UmpayOrderDao umpayOrderDao;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;
    
    @Override
    public String getPayType() {
        return PaymentConsts.PAYTYPE_BANKCARD;
    }

    @Override
    public Object doPreCreate(TradePreCreateReq req) {
        PayTransaction trans = PayProcessContext.getTransation();
        UmpayOrder umorder = umpayOrderDao.selectByTransId(trans.getTransId());
        String outTradeNo = null;
        if(umorder == null){
            outTradeNo = trans.getTransId();
            
            UmpayOrder umorderAdd = new UmpayOrder();
            umorderAdd.setBrandId(trans.getBrandId());
            umorderAdd.setStoreId(trans.getStoreId());
            umorderAdd.setTransId(trans.getTransId());
            umorderAdd.setOutTradeNo(outTradeNo);
            umorderAdd.setWposDeviceEn(req.getTerminalDeviceNo());
            umpayOrderDao.insertSelective(umorderAdd);
        }else{
            outTradeNo = UmPayBizUtils.genNewOutTradeNo(umorder.getOutTradeNo());
            
            UmpayOrder umorderupd = new UmpayOrder();
            umorderupd.setTransId(trans.getTransId());
            umorderupd.setOutTradeNo(outTradeNo);
            umorderupd.setWposDeviceEn(req.getTerminalDeviceNo());
            umpayOrderDao.updateByTransIdSelective(umorderupd);
        }
        TradePreCreateBankCardExtData extData = new TradePreCreateBankCardExtData();
        extData.setOutTradeNo(outTradeNo);
        extData.setNotifyUrl(umPayConfig.getNotifyUrl());
        
        return extData;
    }
    
    @Override
    public Object doPay(TradePayReq req) {
        if(StringUtils.isBlank(req.getPosCashierTradeNo()) || StringUtils.isBlank(req.getPosRefNo())){
            throw BizException.build(BizResCode.PAY_BANKCARD_PARAM_ERROR);
        }
        PayTransaction trans = PayProcessContext.getTransation();
        
        UmpayOrder umorderupd = new UmpayOrder();
        umorderupd.setTransId(trans.getTransId());
        umorderupd.setWposCashierTradeNo(req.getPosCashierTradeNo());
        umorderupd.setWposVoucherNo(req.getPosVoucherNo());
        umorderupd.setWposRefNo(req.getPosRefNo());
        umpayOrderDao.updateByTransIdSelective(umorderupd);
        
        String payTypeDetail = CardTypeUtil.getCardTypeByChnType(req.getCardType());
        if(StringUtils.isNotBlank(payTypeDetail)){
            tradeServiceAssistLogic.updateTransPayTypeDetailInfo(trans, payTypeDetail);
        }
        
        return super.doPay(req);
    }
}
