package com.hanyun.platform.pay.logic.trade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.platform.pay.adapt.yeepay.util.CommonUtil;
import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.consts.TaskMessageType;
import com.hanyun.platform.pay.dao.PayTransactionNewDao;
import com.hanyun.platform.pay.dao.PaymentNewDao;
import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogicYb;
import com.hanyun.platform.pay.task.submit.TaskSubmitter;
import com.hanyun.platform.pay.util.BusinessIdUtils;
import com.hanyun.platform.pay.util.PayChargeFeeUtils;
import com.hanyun.platform.pay.util.PaymentTradeUtils;
import com.hanyun.platform.pay.vo.trade.TradePayBaseReq;
import com.hanyun.platform.pay.vo.trade.TradePayRes;

/**
 * 
 * @date 2018年8月1日
 * 
 * @apiDefine common 支付相关
 * @author litao@hanyun.com
 */
@Service
public class TradeServiceAssistLogicYbImpl implements TradeServiceAssistLogicYb{
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(TradeServiceAssistLogicYbImpl.class);

	@Resource
	private PaymentNewDao paymentNewDao;
	
	@Resource
	private PayTransactionNewDao payTransactionNewDao;
	
	@Resource
    private TaskSubmitter taskSubmitter;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public PayTransactionNew createPayTrans(PaymentNew paymentNew, MchActualPayModeInfo payChnMode, TradePayBaseReq req,
			Integer status) {
		PayTransactionNew transNew = new PayTransactionNew();
        
        PaymentTradeUtils.fillTransFromPaymentNew(paymentNew, transNew);
        PaymentTradeUtils.fillPayTransNewFromPayModeInfo(transNew, payChnMode);
        
        transNew.setTransId(BusinessIdUtils.genTransId());
        transNew.setOperateType(PaymentNewConsts.TRANS_OPERATE_TYPE_PAY);
        transNew.setAmount(req.getCurPayAmount());
        if(req.getCurDiscountAmount() != null){
            transNew.setDiscountAmount(req.getCurDiscountAmount());
        }else{
            transNew.setDiscountAmount(0L);
        }
        
        transNew.setSourceType(req.getSourceType());
        transNew.setTerminalDeviceNo(req.getTerminalDeviceNo());
        transNew.setTerminalIp(req.getTerminalIp());
        transNew.setOperateUser(req.getOperateUser());
        
        long chnFee = PayChargeFeeUtils.calcChargeFee(
                req.getCurPayAmount(), transNew.getChnFeeRate(), transNew.getChnFeeMax());
        long mchFee = PayChargeFeeUtils.calcChargeFee(
                req.getCurPayAmount(), transNew.getMchFeeRate(), transNew.getMchFeeMax());
        transNew.setChnFee(chnFee);
        transNew.setMchFee(mchFee);
        transNew.setStatus(status);
        payTransactionNewDao.insert(transNew);
        
        return transNew;
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	public PaymentNew createPayment(TradePayBaseReq req) {
		PaymentNew paymentNew = new PaymentNew();
        paymentNew.setPayId(BusinessIdUtils.genPayId());
        paymentNew.setBrandId(req.getBrandId());
        paymentNew.setStoreId(req.getStoreId());
        paymentNew.setOrderId(req.getOrderId());
        paymentNew.setOrderDocumentId(req.getOrderDocumentId());
        paymentNew.setOrderTime(DateFormatUtil.parseDateTimeNoSep(req.getOrderTime()));
        paymentNew.setOrderAmount(req.getOrderAmount());
        paymentNew.setPayAmount(req.getPayAmount());
        paymentNew.setHadPayAmount(0L);
        paymentNew.setHadPayCount(0);
        paymentNew.setRefundAmount(0L);
        paymentNew.setHadDiscountAmount(0L);
        paymentNew.setPayStatus(PaymentNewConsts.PAY_STATUS_INIT);
       
        paymentNew.setSourceType(req.getSourceType());
        paymentNew.setTerminalDeviceNo(req.getTerminalDeviceNo());
        paymentNew.setTerminalIp(req.getTerminalIp());
        paymentNew.setOperateUser(req.getOperateUser());
        
        paymentNewDao.insert(paymentNew);
        return paymentNew;
	}

	@Override
	public boolean updateTransPayTypeDetailInfo(PayTransactionNew originTransNew, String payTypeDetail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePayTransWhenPayFinish(PayTransactionNew paytransNew) {
		paytransNew.setStatus(PaymentNewConsts.TRANS_STATUS_FINISH);
		paytransNew.setFinishTime(new Date());
		payTransactionNewDao.updateStatusByTransId(paytransNew);
		
	}

	@Override
	public void updatePayTransWhenPayFail(PayTransactionNew paytransNew) {
		 paytransNew.setStatus(PaymentNewConsts.TRANS_STATUS_INIT);
	     paytransNew.setFinishTime(null);
	     payTransactionNewDao.updateStatusByTransId(paytransNew);
		
	}

	@Override
	public void updatePaymentWhenPayFinish(PaymentNew paymentNew, PayTransactionNew paytransNew) {
		paymentNew.setHadPayCount(paymentNew.getHadPayCount() + 1);
		//已支付金额
		paymentNew.setHadPayAmount(paymentNew.getHadPayAmount() + paytransNew.getAmount());
		//已优惠金额
		paymentNew.setHadDiscountAmount(paymentNew.getHadDiscountAmount() + paytransNew.getDiscountAmount());
        //未支付金额
		long canPayAmt = paymentNew.getPayAmount() - paymentNew.getHadPayAmount() - paymentNew.getHadDiscountAmount();
        if(canPayAmt <= 0){
            paymentNew.setPayStatus(PaymentNewConsts.PAY_STATUS_FINISH);
        }else{
            paymentNew.setPayStatus(PaymentNewConsts.PAY_STATUS_PAY_PART);
        }
        // 单一支付方式支付的情况，记录支付方式
        if(paymentNew.getPayAmount().equals(paytransNew.getAmount())){
            paymentNew.setPayType(paytransNew.getPayType());
            paymentNew.setTypeCategory(paytransNew.getTypeCategory());
        }
        paymentNew.setPayTime(new Date());
        paymentNewDao.updatePayByPayId(paymentNew);
		
	}

	@Override
	public void notifyOrderWhenPayFinish(PaymentNew paymentNew, PayTransactionNew paytransNew, Object extData) {
		
		 TradePayRes payres = new TradePayRes();
		 PaymentTradeUtils.fillTradePayYbRes(payres,paymentNew, paytransNew,extData);
		 TaskQueue task = new TaskQueue();
	        task.setMessageType(TaskMessageType.PAY_NOTIFY_ORDER);
	        LOGGER.info("======> QUERTZ NOTIFY ORDER  "+JsonUtil.toJson(payres));
	        task.setMessage(JsonUtil.toJson(payres));
	        task.setPayId(paymentNew.getPayId());
	        task.setTransId(paytransNew.getTransId());
	        taskSubmitter.submit(task);
	}

	
}
