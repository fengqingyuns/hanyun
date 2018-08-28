/**
 * 
 */
package com.hanyun.platform.pay.logic.trade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.adapt.yeepay.model.YeeCommonResCode;
import com.hanyun.platform.pay.adapt.yeepay.model.YeeRefundRes;
import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.dao.PayTransactionNewDao;
import com.hanyun.platform.pay.dao.PaymentNewDao;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.logic.trade.YbTradeRefundServiceAssistLogic;
import com.hanyun.platform.pay.util.BusinessIdUtils;
import com.hanyun.platform.pay.util.PayChargeFeeUtils;
import com.hanyun.platform.pay.util.PaymentTradeUtils;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;

/**
 * 交易退款服务辅助逻辑
 * 
 * @author litao@hanyun.com
 * @date 2017年6月23日 上午11:19:09
 */
@Component
public class YbTradeRefundServiceAssistLogicImpl implements YbTradeRefundServiceAssistLogic {

	@Resource
	private PaymentNewDao paymentNewDao;
	@Resource
	private PayTransactionNewDao payTransactionNewDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PayTransactionNew createRefundTransFromPayTrans(PayTransactionNew paytrans, TradeRefundReq req) {
		PayTransactionNew reftrans = new PayTransactionNew();

		reftrans.setTransId(BusinessIdUtils.genTransId());
		reftrans.setOperateType(PaymentNewConsts.TRANS_OPERATE_TYPE_REF);

		if (req.getRefundMode().equals(PaymentNewConsts.REFUND_MODE_WHOLE)) {
			reftrans.setAmount(paytrans.getAmount() - paytrans.getHadRefundAmount());
		} else {
			reftrans.setAmount(req.getRefundAmount());
		}
		reftrans.setDiscountAmount(0L);

		reftrans.setPayId(paytrans.getPayId());
		reftrans.setOrderId(paytrans.getOrderId());
		reftrans.setOrderDocumentId(paytrans.getOrderDocumentId());
		reftrans.setBrandId(paytrans.getBrandId());
		reftrans.setStoreId(paytrans.getStoreId());
		reftrans.setSourceType(req.getSourceType());
		reftrans.setTerminalDeviceNo(req.getTerminalDeviceNo());
		reftrans.setTerminalIp(req.getTerminalIp());
		reftrans.setOperateUser(req.getOperateUser());
		reftrans.setSrcPayTransId(paytrans.getTransId());

		reftrans.setPayType(paytrans.getPayType());
		reftrans.setPayTypeDetail(paytrans.getPayTypeDetail());
		reftrans.setTypeCategory(paytrans.getTypeCategory());
		reftrans.setChannel(paytrans.getChannel());
		reftrans.setSettleType(paytrans.getSettleType());
		reftrans.setChnFeeMax(paytrans.getChnFeeMax());
		reftrans.setChnFeeRate(paytrans.getChnFeeRate());
		reftrans.setMchFeeMax(paytrans.getMchFeeMax());
		reftrans.setMchFeeRate(paytrans.getMchFeeRate());

		long chnFee = PayChargeFeeUtils.calcChargeFee(reftrans.getAmount(), reftrans.getChnFeeRate(),
				reftrans.getChnFeeMax());
		long mchFee = PayChargeFeeUtils.calcChargeFee(reftrans.getAmount(), reftrans.getMchFeeRate(),
				reftrans.getMchFeeMax());
		reftrans.setChnFee(chnFee);
		reftrans.setMchFee(mchFee);

		reftrans.setStatus(PaymentNewConsts.TRANS_STATUS_PROCESS);

		payTransactionNewDao.insert(reftrans);

		return reftrans;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PayTransactionNew createRefundTransForNoOriginTransRefund(PaymentNew payment, TradeRefundReq req) {
		PayTransactionNew reftrans = new PayTransactionNew();

		PaymentTradeUtils.fillTransNewFromPaymentNew(payment, reftrans);

		reftrans.setTransId(BusinessIdUtils.genTransId());
		reftrans.setOperateType(PaymentNewConsts.TRANS_OPERATE_TYPE_REF);
		reftrans.setAmount(req.getRefundAmount());
		reftrans.setDiscountAmount(0L);

		reftrans.setSourceType(req.getSourceType());
		reftrans.setTerminalDeviceNo(req.getTerminalDeviceNo());
		reftrans.setTerminalIp(req.getTerminalIp());
		reftrans.setOperateUser(req.getOperateUser());

		reftrans.setPayType(PaymentNewConsts.PAYTYPE_CASH);
		reftrans.setTypeCategory(PaymentNewConsts.PAYTYPE_CATEGORY_CASH);
		reftrans.setChannel(PaymentNewConsts.PAYCHANNEL_OFFLINE);
		reftrans.setSettleType(PaymentNewConsts.SETTLE_TYPE_OFF);

		reftrans.setChnFeeRate(0);
		reftrans.setChnFeeMax(0);
		reftrans.setChnFee(0L);
		reftrans.setMchFeeRate(0);
		reftrans.setMchFeeMax(0);
		reftrans.setMchFee(0L);

		reftrans.setStatus(PaymentNewConsts.TRANS_STATUS_PROCESS);

		payTransactionNewDao.insert(reftrans);

		return reftrans;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PaymentNew createPaymentForNoOriginOrderRefund(TradeRefundReq req) {
		PaymentNew payment = new PaymentNew();
		payment.setPayId(BusinessIdUtils.genPayId());
		payment.setBrandId(req.getBrandId());
		payment.setStoreId(req.getStoreId());
		payment.setOrderId(req.getOrderId());
		payment.setOrderDocumentId(req.getOrderDocumentId());
		payment.setOrderTime(DateFormatUtil.parseDateTimeNoSep(req.getOrderTime()));
		payment.setOrderAmount(0L);
		payment.setPayAmount(0L);
		payment.setHadPayAmount(0L);
		payment.setHadPayCount(0);
		payment.setRefundAmount(0L);
		payment.setHadDiscountAmount(0L);
		payment.setPayStatus(PaymentNewConsts.PAY_STATUS_REF_PROCESS);
		payment.setPayTime(new Date());

		payment.setSourceType(req.getSourceType());
		payment.setTerminalDeviceNo(req.getTerminalDeviceNo());
		payment.setTerminalIp(req.getTerminalIp());
		payment.setOperateUser(req.getOperateUser());

		paymentNewDao.insert(payment);

		return payment;
	}

	@Override
	public void doRefundCallback(YeeRefundRes res) {
		if (res.getCode().equals(YeeCommonResCode.YEE_ORDER_REFUND_FINISH)) {
			return;
		} else if (res.getCode().equals(YeeCommonResCode.YEE_NOPAY_MERCHANT)) {
			String reftransId = res.getTrxRequestNo();
			PayTransactionNew reftrans = payTransactionNewDao.selectByTransId(reftransId);
			PayTransactionNew paytrans = payTransactionNewDao.selectByTransId(reftrans.getSrcPayTransId());
			PaymentNew payment = paymentNewDao.selectByPayId(reftrans.getPayId());
			Date now = new Date();
			reftrans.setStatus(PaymentNewConsts.TRANS_STATUS_FINISH);
			reftrans.setFinishTime(now);
			payTransactionNewDao.updateStatusByTransId(reftrans);

			if (paytrans != null) {
				paytrans.setHadRefundAmount(paytrans.getHadRefundAmount() + reftrans.getAmount());
				if (paytrans.getAmount() <= paytrans.getHadRefundAmount()) {
					paytrans.setRefundStatus(PaymentNewConsts.TRANS_REFUND_STATUS_FINISH);
				} else {
					paytrans.setRefundStatus(PaymentNewConsts.TRANS_REFUND_STATUS_PART);
				}
				payTransactionNewDao.updatePayTransRefundInfoByTransId(paytrans);
			}

			payment.setRefundAmount(payment.getRefundAmount() + reftrans.getAmount());
			if (payment.getHadPayAmount() <= payment.getRefundAmount()) {
				payment.setPayStatus(PaymentNewConsts.PAY_STATUS_REF_ALL);
			} else {
				payment.setPayStatus(PaymentNewConsts.PAY_STATUS_REF_PART);
			}
			paymentNewDao.updateRefundByPayId(payment);
		}

	}

}
