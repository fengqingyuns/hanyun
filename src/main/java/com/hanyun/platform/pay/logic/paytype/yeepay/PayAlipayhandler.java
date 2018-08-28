package com.hanyun.platform.pay.logic.paytype.yeepay;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.adapt.yeepay.YeepayAlipayAdapter;
import com.hanyun.platform.pay.adapt.yeepay.YeepayConfig;
import com.hanyun.platform.pay.adapt.yeepay.YeepayRefundAdapter;
import com.hanyun.platform.pay.adapt.yeepay.model.AlipayYeePayReq;
import com.hanyun.platform.pay.adapt.yeepay.model.AlipayYeepayRefundReq;
import com.hanyun.platform.pay.adapt.yeepay.model.PayConsts;
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
import org.omg.CORBA.TIMEOUT;
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
public class PayAlipayhandler extends PayTypeHandler {
    @Autowired
    private YeepayAlipayAdapter yeepayAlipayAdapter;

    @Autowired
    private YeepayRefundAdapter yeepayRefundAdapter;

    @Autowired
    private YeepayConfig yeepayConfig;

    @Override
    public String getPayType() {
        return PaymentNewConsts.PAYTYPE_ALIPAY_MICROPAY;
    }

    @Override
    public Object doPreCreate(TradePreCreateReq req) {
        return null;
    }

    @Override
    public Object doPay(TradePayReq req) throws TimeoutException {
        AlipayYeePayReq request = new AlipayYeePayReq();
        request.setServerCallbackUrl(yeepayConfig.getAlipayServerCallbackUrl());
        request.setWebCallbackUrl(yeepayConfig.getWebCallbackUrl());
        request.setIp(req.getTerminalIp());
        request.setMerchantTerminalId(req.getTerminalDeviceNo());
        request.setPayTool(req.getPayType());
        request.setBrandId(req.getBrandId());
        request.setStoreId(req.getStoreId());
        request.setMcc(YeepayChildChannelRegGlobal.PAY_MCC);
        request.setProductCatalog(YeepayChildChannelRegGlobal.PAY_MCC);
        request.setProductName(TradePayReq.YEE_PRODUCT_NAME);
        request.setProductDesc(TradePayReq.ORDER_DESC_DEFAULT);
        request.setRequestNo(PayProcessContext.getTransationNew().getTransId());
        request.setPayEmpowerNo(req.getAuthCode());
        request.setMerchantOrderDate(CommonUtil.formatDateTime(req.getOrderTime()));
        request.setOrderAmount(UsualTools.tranceLongToString(req.getOrderAmount(),YeepayChildChannelRegGlobal.TRANCE_AMOUNT));
        request.setFundAmount(UsualTools.tranceLongToString(req.getPayAmount(),YeepayChildChannelRegGlobal.TRANCE_AMOUNT));
        request.setPlatformId(yeepayConfig.getPlatformId());
        Object object = yeepayAlipayAdapter.doRequest(request);
        return object;
    }

    @Override
    public Object doRefund(TradeRefundReq request) {
        AlipayYeepayRefundReq req = new AlipayYeepayRefundReq();

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


        return yeepayRefundAdapter.doAliPayRefund(req);
    }

}
