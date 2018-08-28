package com.hanyun.platform.pay.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.logic.trade.YbTradeRefundLogic;
import com.hanyun.platform.pay.service.YbPayRefundTradeService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundRes;

/**
 * 
 * @date 2018年8月2日
 * 
 * @apiDefine Common 退款交易
 * @author litao@hanyun.com
 */
@Service
public class YbPayRefundTradeServiceImpl implements YbPayRefundTradeService{
	 private static final Logger LOGGER = LoggerFactory.getLogger(YbPayRefundTradeServiceImpl.class);
	 @Resource
	 private YbTradeRefundLogic ybTradeRefundLogic;
	@Override
	public HttpResponse<TradeRefundRes> refund(TradeRefundReq req) throws Exception {
		 LOGGER.info("Yb paymentNew refund, param: {}", JsonUtil.toJson(req));
	        if (!ybTradeRefundLogic.checkCommonReqParam(req)) {
	            return BizResUtil.fail(BizResCode.PARAMERROR);
	        }

	        switch (req.getRefundMode()) {
	        case PaymentNewConsts.REFUND_MODE_WHOLE:
	            ybTradeRefundLogic.dealRefundForWhole(req);
	            break;
	        case PaymentNewConsts.REFUND_MODE_PART_PAYTPYE:
	            ybTradeRefundLogic.dealRefundForPartPayType(req);
	            break;
	        case PaymentNewConsts.REFUND_MODE_PART_OFFLINE:
	            ybTradeRefundLogic.dealRefundForPartOffline(req);
	            break;
	        case PaymentNewConsts.REFUND_MODE_NOORDER:
	            ybTradeRefundLogic.dealRefundForNoOrder(req);
	            break;
	        default:
	            return BizResUtil.fail(BizResCode.PARAMERROR);
	        }

	        PaymentNew payment = PayProcessContext.getPaymentNew();

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

	        LOGGER.info("Yb paymentNew refund success, param: {}", JsonUtil.toJson(req));

	        return BizResUtil.succ(data);
	    }	
}
	


