package com.hanyun.platform.pay.logic.paytype.yeepay;


import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandler;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogicYb;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Description:线下现金支付
 *
 * @PackageName com.hanyun.platform.pay.logic.paytype.yeepay
 * @Author: dewen.li
 * @Date: 2018-07-31 上午11:33
 */
@Component
public class PayTypeYopayHandler extends PayTypeHandler {

    @Resource
    private TradeServiceAssistLogicYb tradeServiceAssistLogic;

    @Override
    public String getPayType() {
        return PaymentNewConsts.PAYTYPE_CASH;
    }

    @Override
    public Object doPreCreate(TradePreCreateReq req) {
        return null;
    }

    @Override
    public Object doPay(TradePayReq req) {

        // TODO
        PaymentNew payment = new PaymentNew();
        payment.setTerminalDeviceNo(req.getTerminalDeviceNo());
        PayTransactionNew trans = new PayTransactionNew();
        Object extData = new Object();


        // 更新支付流水状态
        tradeServiceAssistLogic.updatePayTransWhenPayFinish(trans);
        // 更新支付单状态
        tradeServiceAssistLogic.updatePaymentWhenPayFinish(payment, trans);
        // 通知订单系统，避免订单系统请求支付系统超时状态不一致
        tradeServiceAssistLogic.notifyOrderWhenPayFinish(payment, trans, extData);

        return null;
    }

    @Override
    public Object doRefund(TradeRefundReq req) {
        return null;
    }
}
