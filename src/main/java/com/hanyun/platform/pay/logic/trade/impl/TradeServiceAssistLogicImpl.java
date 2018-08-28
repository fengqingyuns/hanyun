/**
 * 
 */
package com.hanyun.platform.pay.logic.trade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.consts.TaskMessageType;
import com.hanyun.platform.pay.dao.PayMchModeDao;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.task.submit.TaskSubmitter;
import com.hanyun.platform.pay.util.BusinessIdUtils;
import com.hanyun.platform.pay.util.PayChargeFeeUtils;
import com.hanyun.platform.pay.util.PaymentTradeUtils;
import com.hanyun.platform.pay.vo.trade.TradePayBaseReq;
import com.hanyun.platform.pay.vo.trade.TradePayRes;

/**
 * 交易服务辅助逻辑
 * @author liyinglong@hanyun.com
 * @date 2017年3月28日 下午3:39:57
 */
@Component
public class TradeServiceAssistLogicImpl implements TradeServiceAssistLogic {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeServiceAssistLogicImpl.class);
    
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private TaskSubmitter taskSubmitter;
    @Resource
    private PayMchModeDao payMchModeDao;

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public PayTransaction createPayTrans(Payment payment, MchActualPayModeInfo payChnMode, 
            TradePayBaseReq req, Integer status) {
        PayTransaction trans = new PayTransaction();
        
        PaymentTradeUtils.fillTransFromPayment(payment, trans);
        PaymentTradeUtils.fillPayTransFromPayModeInfo(trans, payChnMode);
        
        trans.setTransId(BusinessIdUtils.genTransId());
        trans.setOperateType(PaymentConsts.TRANS_OPERATE_TYPE_PAY);
        trans.setAmount(req.getCurPayAmount());
        if(req.getCurDiscountAmount() != null){
            trans.setDiscountAmount(req.getCurDiscountAmount());
        }else{
            trans.setDiscountAmount(0L);
        }
        
        trans.setSourceType(req.getSourceType());
        trans.setTerminalDeviceNo(req.getTerminalDeviceNo());
        trans.setTerminalIp(req.getTerminalIp());
        trans.setOperateUser(req.getOperateUser());
        
        long chnFee = PayChargeFeeUtils.calcChargeFee(
                req.getCurPayAmount(), trans.getChnFeeRate(), trans.getChnFeeMax());
        long mchFee = PayChargeFeeUtils.calcChargeFee(
                req.getCurPayAmount(), trans.getMchFeeRate(), trans.getMchFeeMax());
        trans.setChnFee(chnFee);
        trans.setMchFee(mchFee);
        trans.setStatus(status);
        payTransactionDao.insert(trans);
        
        return trans;
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Payment createPayment(TradePayBaseReq req) {
        Payment payment = new Payment();
        payment.setPayId(BusinessIdUtils.genPayId());
        payment.setBrandId(req.getBrandId());
        payment.setStoreId(req.getStoreId());
        payment.setOrderId(req.getOrderId());
        payment.setOrderDocumentId(req.getOrderDocumentId());
        payment.setOrderTime(DateFormatUtil.parseDateTimeNoSep(req.getOrderTime()));
        payment.setOrderAmount(req.getOrderAmount());
        payment.setPayAmount(req.getPayAmount());
        payment.setHadPayAmount(0L);
        payment.setHadPayCount(0);
        payment.setRefundAmount(0L);
        payment.setHadDiscountAmount(0L);
        payment.setPayStatus(PaymentConsts.PAY_STATUS_INIT);
        
        payment.setSourceType(req.getSourceType());
        payment.setTerminalDeviceNo(req.getTerminalDeviceNo());
        payment.setTerminalIp(req.getTerminalIp());
        payment.setOperateUser(req.getOperateUser());
        
        paymentDao.insert(payment);
        return payment;
    }
    
    @Override
    public boolean updateTransPayTypeDetailInfo(PayTransaction originTrans, String payTypeDetail) {
        LOGGER.info("brandId:{}, transId:{}, payTypeDetail:{}", originTrans.getBrandId(), originTrans.getTransId(),
                payTypeDetail);
        if (StringUtils.isBlank(originTrans.getBrandId()) || StringUtils.isBlank(originTrans.getTransId())
                || StringUtils.isBlank(payTypeDetail)) {
            return false;
        }

        MchActualPayModeInfo query = new MchActualPayModeInfo();
        query.setBrandId(originTrans.getBrandId());
        query.setPayType(payTypeDetail);
        MchActualPayModeInfo payChnMode = payMchModeDao.selectOnlinePayType(query);
        if (payChnMode == null) {
            return false;
        }
        PayTransaction ptparam = new PayTransaction();
        ptparam.setChnFeeMax(payChnMode.getChnFeeMax());
        ptparam.setChnFeeRate(payChnMode.getChnFeeRate());
        Integer mchFeeMax = (payChnMode.getMchFeeMax() == null) ? payChnMode.getMchFeeMaxDef()
                : payChnMode.getMchFeeMax();
        Integer mchFeeRate = (payChnMode.getMchFeeRate() == null) ? payChnMode.getMchFeeRateDef()
                : payChnMode.getMchFeeRate();
        ptparam.setMchFeeMax(mchFeeMax);
        ptparam.setMchFeeRate(mchFeeRate);

        long chnFee = PayChargeFeeUtils.calcChargeFee(originTrans.getAmount(), ptparam.getChnFeeRate(),
                ptparam.getChnFeeMax());
        long mchFee = PayChargeFeeUtils.calcChargeFee(originTrans.getAmount(), ptparam.getMchFeeRate(),
                ptparam.getMchFeeMax());
        ptparam.setChnFee(chnFee);
        ptparam.setMchFee(mchFee);

        ptparam.setPayTypeDetail(payTypeDetail);
        ptparam.setTransId(originTrans.getTransId());
        payTransactionDao.updatePayTypeInfoByTransId(ptparam);

        return true;
    }

    @Override
    public void updatePayTransWhenPayFinish(PayTransaction paytrans) {
        paytrans.setStatus(PaymentConsts.TRANS_STATUS_FINISH);
        paytrans.setFinishTime(new Date());
        payTransactionDao.updateStatusByTransId(paytrans);
        
    }
    
    @Override
    public void updatePayTransWhenPayFail(PayTransaction paytrans) {
        paytrans.setStatus(PaymentConsts.TRANS_STATUS_INIT);
        paytrans.setFinishTime(null);
        payTransactionDao.updateStatusByTransId(paytrans);
        
    }

    @Override
    public void updatePaymentWhenPayFinish(Payment payment, PayTransaction paytrans) {
        payment.setHadPayCount(payment.getHadPayCount() + 1);
        payment.setHadPayAmount(payment.getHadPayAmount() + paytrans.getAmount());
        payment.setHadDiscountAmount(payment.getHadDiscountAmount() + paytrans.getDiscountAmount());
        long canPayAmt = payment.getPayAmount() - payment.getHadPayAmount() - payment.getHadDiscountAmount();
        if(canPayAmt <= 0){
            payment.setPayStatus(PaymentConsts.PAY_STATUS_FINISH);
        }else{
            payment.setPayStatus(PaymentConsts.PAY_STATUS_PAY_PART);
        }
        // 单一支付方式支付的情况，记录支付方式
        if(payment.getPayAmount().equals(paytrans.getAmount())){
            payment.setPayType(paytrans.getPayType());
            payment.setTypeCategory(paytrans.getTypeCategory());
        }
        payment.setPayTime(new Date());
        paymentDao.updatePayByPayId(payment);
    }

    @Override
    public void notifyOrderWhenPayFinish(Payment payment, PayTransaction paytrans, Object extData) {
        TradePayRes payres = new TradePayRes();
        PaymentTradeUtils.fillTradePayRes(payres, payment, paytrans, extData);
        
        TaskQueue task = new TaskQueue();
        task.setMessageType(TaskMessageType.PAY_NOTIFY_ORDER);
        task.setMessage(JsonUtil.toJson(payres));
        task.setPayId(payment.getPayId());
        task.setTransId(paytrans.getTransId());
        taskSubmitter.submit(task);
    }
    
}
