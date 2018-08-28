/**
 * 
 */
package com.hanyun.platform.pay.logic.paytypemgr.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.dao.PayMchModeDao;
import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.logic.paytypemgr.PayTypeFetchLogic;

/**
 * 支付方式获取逻辑
 * @author liyinglong@hanyun.com
 * @date 2016年8月4日 下午8:14:31
 */
@Component
public class PayTypeFetchLogicImpl implements PayTypeFetchLogic {
    /** 线下通道的支付方式，可直接获取，不需查数据库 */
    private static final Map<String, MchActualPayModeInfo> OFFLINE_PAYMODE = new HashMap<String, MchActualPayModeInfo>();
    
    @Resource
    private PayMchModeDao payMchModeDao;
    
    static{
        MchActualPayModeInfo cash = new MchActualPayModeInfo();
        cash.setPayType(PaymentConsts.PAYTYPE_CASH);
        cash.setTypeCategory(PaymentConsts.PAYTYPE_CATEGORY_CASH);
        cash.setChannel(PaymentConsts.PAYCHANNEL_OFFLINE);
        cash.setSettleType(PaymentConsts.SETTLE_TYPE_OFF);
        cash.setChnFeeMax(0);
        cash.setChnFeeRate(0);
        cash.setMchFeeMax(0);
        cash.setMchFeeRate(0);
        cash.setMchFeeMaxDef(0);
        cash.setMchFeeRateDef(0);
        //易宝现金支付
        MchActualPayModeInfo yeeCash = new MchActualPayModeInfo();
        cash.setPayType(PaymentNewConsts.PAYTYPE_CASH);
        cash.setTypeCategory(PaymentNewConsts.PAYTYPE_CATEGORY_CASH);
        cash.setChannel(PaymentNewConsts.PAYCHANNEL_OFFLINE);
        cash.setSettleType(PaymentNewConsts.SETTLE_TYPE_OFF);
        cash.setChnFeeMax(0);
        cash.setChnFeeRate(0);
        cash.setMchFeeMax(0);
        cash.setMchFeeRate(0);
        cash.setMchFeeMaxDef(0);
        cash.setMchFeeRateDef(0);
        
        MchActualPayModeInfo posOffline = new MchActualPayModeInfo();
        posOffline.setPayType(PaymentConsts.PAYTYPE_BANKCARD_OFFLINE);
        posOffline.setTypeCategory(PaymentConsts.PAYTYPE_CATEGORY_BANKCARD);
        posOffline.setChannel(PaymentConsts.PAYCHANNEL_OFFLINE);
        posOffline.setSettleType(PaymentConsts.SETTLE_TYPE_OFF);
        posOffline.setChnFeeMax(0);
        posOffline.setChnFeeRate(0);
        posOffline.setMchFeeMax(0);
        posOffline.setMchFeeRate(0);
        posOffline.setMchFeeMaxDef(0);
        posOffline.setMchFeeRateDef(0);
        
        MchActualPayModeInfo shoppingMall = new MchActualPayModeInfo();
        shoppingMall.setPayType(PaymentConsts.PAYTYPE_SHOPPINGMALL);
        shoppingMall.setTypeCategory(PaymentConsts.PAYTYPE_CATEGORY_SHOPPINGMALL);
        shoppingMall.setChannel(PaymentConsts.PAYCHANNEL_OFFLINE);
        shoppingMall.setSettleType(PaymentConsts.SETTLE_TYPE_OFF);
        shoppingMall.setChnFeeMax(0);
        shoppingMall.setChnFeeRate(0);
        shoppingMall.setMchFeeMax(0);
        shoppingMall.setMchFeeRate(0);
        shoppingMall.setMchFeeMaxDef(0);
        shoppingMall.setMchFeeRateDef(0);
        
        OFFLINE_PAYMODE.put(PaymentNewConsts.PAYTYPE_CASH,yeeCash);
        OFFLINE_PAYMODE.put(PaymentConsts.PAYTYPE_CASH, cash);
        OFFLINE_PAYMODE.put(PaymentConsts.PAYTYPE_BANKCARD_OFFLINE, posOffline);
        OFFLINE_PAYMODE.put(PaymentConsts.PAYTYPE_SHOPPINGMALL, shoppingMall);
    }

    @Override
    public MchActualPayModeInfo fetchOnlinePayType(String payType, String brandId) {
        MchActualPayModeInfo payChnMode = OFFLINE_PAYMODE.get(payType);
        if(payChnMode == null){
            MchActualPayModeInfo query = new MchActualPayModeInfo();
            query.setBrandId(brandId);
            query.setPayType(payType);
            payChnMode = payMchModeDao.selectOnlinePayType(query);
        }
        
        return payChnMode;
    }

}
