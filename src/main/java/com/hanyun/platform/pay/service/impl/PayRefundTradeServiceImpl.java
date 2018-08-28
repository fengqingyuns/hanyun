/**
 * 
 */
package com.hanyun.platform.pay.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.logic.trade.TradeRefundLogic;
import com.hanyun.platform.pay.service.PayRefundTradeService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundRes;

/**
 * 退款交易服务
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年6月15日 下午2:45:33
 */
@Service
public class PayRefundTradeServiceImpl implements PayRefundTradeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayRefundTradeServiceImpl.class);

    @Resource
    private TradeRefundLogic tradeRefundLogic;

    @Override
    @Transactional
    public HttpResponse<TradeRefundRes> refund(TradeRefundReq req) throws Exception {
        LOGGER.info("payment refund, param: {}", JsonUtil.toJson(req));
        if (!tradeRefundLogic.checkCommonReqParam(req)) {
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }

        switch (req.getRefundMode()) {
        case PaymentConsts.REFUND_MODE_WHOLE:
            tradeRefundLogic.dealRefundForWhole(req);
            break;
        case PaymentConsts.REFUND_MODE_PART_PAYTPYE:
            tradeRefundLogic.dealRefundForPartPayType(req);
            break;
        case PaymentConsts.REFUND_MODE_PART_OFFLINE:
            tradeRefundLogic.dealRefundForPartOffline(req);
            break;
        case PaymentConsts.REFUND_MODE_NOORDER:
            tradeRefundLogic.dealRefundForNoOrder(req);
            break;
        default:
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }

        Payment payment = PayProcessContext.getPayment();

        TradeRefundRes data = new TradeRefundRes();

        data.setOrderId(payment.getOrderId());
        data.setPayId(payment.getPayId());
        data.setPayStatus(payment.getPayStatus());
        data.setOrderAmount(payment.getOrderAmount());
        data.setPayAmount(payment.getPayAmount());
        data.setHadPayAmount(payment.getHadPayAmount());
        data.setHadDiscountAmount(payment.getHadDiscountAmount());
        data.setRefundAmount(payment.getRefundAmount());
        data.setExtdata(null);

        LOGGER.info("payment refund success, param: {}", JsonUtil.toJson(req));

        return BizResUtil.succ(data);
    }

}
