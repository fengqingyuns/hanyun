package com.hanyun.platform.pay.logic.paytype.yeepay;


import com.hanyun.platform.pay.adapt.yeepay.YeepayCommon;
import com.hanyun.platform.pay.adapt.yeepay.YeepayConfig;
import com.hanyun.platform.pay.adapt.yeepay.YeepayRefundAdapter;
import com.hanyun.platform.pay.adapt.yeepay.YeepayWechatPayAdapter;
import com.hanyun.platform.pay.adapt.yeepay.model.*;
import com.hanyun.platform.pay.adapt.yeepay.util.CommonUtil;
import com.hanyun.platform.pay.adapt.yeepay.util.UsualTools;
import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandler;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.logic.paytype.yeepay
 * @Author: dewen.li
 * @Date: 2018-08-07 下午3:09
 */

@Component
public class PayWechatHandler extends PayTypeHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(PayWechatHandler.class);

    @Autowired
    private YeepayWechatPayAdapter wechatPayAdapter;

    @Autowired
    private YeepayRefundAdapter yeepayRefundAdapter;

    @Autowired
    private YeepayConfig yeepayConfig;
    @Resource
    private YeePayQueryHandler yeePayQueryHandler;

    @Override
    public String getPayType() {
        return PaymentNewConsts.YB_PAYTYPE_WECHAT_MICROPAY;
    }

    @Override
    public Object doPreCreate(TradePreCreateReq req) {
        return null;
    }

    @Override
    public Object doPay(TradePayReq req) throws TimeoutException, InterruptedException {
        WechatYeePayReq request = new WechatYeePayReq();
        request.setServerCallbackUrl(yeepayConfig.getWechatServerCallbackUrl());
        request.setWebCallbackUrl(yeepayConfig.getWebCallbackUrl());
        //TODO
        request.setOpenId("");
        request.setPayTool(req.getPayType());
        request.setBrandId(req.getBrandId());
        request.setStoreId(req.getStoreId());
        request.setIp(req.getTerminalIp());
        request.setMerchantTerminalId(req.getTerminalDeviceNo());
        request.setRequestNo(PayProcessContext.getTransationNew().getTransId());
        request.setMcc(YeepayChildChannelRegGlobal.PAY_MCC);
        request.setProductCatalog(YeepayChildChannelRegGlobal.PAY_MCC);
        request.setProductName(TradePayReq.YEE_PRODUCT_NAME);
        request.setProductDesc(TradePayReq.ORDER_DESC_DEFAULT);
        request.setPayEmpowerNo(req.getAuthCode());
        request.setMerchantOrderDate(CommonUtil.formatDateTime(req.getOrderTime()));
        request.setOrderAmount(UsualTools.tranceLongToString(req.getOrderAmount(),YeepayChildChannelRegGlobal.TRANCE_AMOUNT));
        request.setFundAmount(UsualTools.tranceLongToString(req.getPayAmount(),YeepayChildChannelRegGlobal.TRANCE_AMOUNT));
        request.setPlatformId(yeepayConfig.getPlatformId());
        YeepayResponseBase object = wechatPayAdapter.doRequest(request);
        if(!object.getStatus().equals(YeepayCommon.RES_STATUS)){
                LOGGER.info("getStatus:"+object.getCode());
                LOGGER.info("transNew.getTransId():"+PayProcessContext.getTransationNew().getTransId());
                //设置主动查询次数：延迟时间ms
                int m = 5,k=2000;
                YeeQueryOrderRes res = new YeeQueryOrderRes();
                try {
					res = yeePayQueryHandler.doFindByRequestNo(req, PayProcessContext.getTransationNew().getTransId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                LOGGER.info("-------------->res::"+res.toString());
                if(!res.getStatus().equals(YeepayCommon.RES_STATUS)){
                    Thread.sleep(1000);
                    for(int i = m ;i>0; i--){
                        try {
                            res = yeePayQueryHandler.doFindByRequestNo(req, PayProcessContext.getTransationNew().getTransId());
                            if(res.getStatus().equals(YeepayCommon.RES_STATUS)){
                                LOGGER.info("支付成功通知订单-step1");
                                return res;
                            }else{
                                Thread.sleep(k);
                                continue;
                            }


                        } catch (Exception exc) {
                            exc.getStackTrace();
                        }
                    }
                }
                    //通知订单
                    LOGGER.info("支付成功通知订单-step2");
                    return res;
        }else{
            return object;
        }
    }

    @Override
    public Object doRefund(TradeRefundReq request) {
        WechatYeepayRefundReq req = new WechatYeepayRefundReq();

        req.setBrandId(request.getBrandId());
        req.setPlatformId(yeepayConfig.getPlatformId());
        req.setStoreId(request.getStoreId());
        //金额处理
        String ramount = UsualTools.tranceLongToString(request.getRefundAmount(),YeepayChildChannelRegGlobal.TRANCE_AMOUNT);
        req.setRefundAmount(ramount);
        req.setPayType(request.getPayType());
        req.setRefundWay(PayConsts.IS_OLDWAY);
        req.setRequestNo(PayProcessContext.getRefundTransationNew().getTransId());
        req.setTrxRequestNo(PayProcessContext.getTransationNew().getTransId());
        req.setServerCallbackUrl(yeepayConfig.getRefundServerCallbackUrl());
        req.setWebCallbackUrl(yeepayConfig.getRefundWebCallbackUrl());


        return yeepayRefundAdapter.doWechatRefund(req);
    }
}
