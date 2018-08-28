/**
 * 
 */
package com.hanyun.platform.pay.logic.trade.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandler;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandlerFactory;
import com.hanyun.platform.pay.logic.trade.TradeRefundLogic;
import com.hanyun.platform.pay.logic.trade.TradeRefundServiceAssistLogic;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;

/**
 * 交易退款逻辑
 * @author liyinglong@hanyun.com
 * @date 2017年6月2日 下午6:10:57
 */
@Component
public class TradeRefundLogicImpl implements TradeRefundLogic {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeRefundLogicImpl.class);
    
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private TradeRefundServiceAssistLogic tradeRefundServiceAssistLogic;

    @Override
    public void dealRefundForWhole(TradeRefundReq req) {
        
        Payment payment = paymentDao.selectByOrderId(req.getOrderId());
        if(payment == null){
            LOGGER.error("Whole Refund error, payment not found: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PAYMENT_NOT_EXIST);
        }
        if(PaymentConsts.PAY_STATUS_REF_ALL.equals(payment.getPayStatus())){
            PayProcessContext.setPayment(payment);
            // 整单退款，如果已全部退款，直接返回成功。
            return;
        }
        
        checkPaymentIsAllowRefundForWhole(payment);
        
        List<PayTransaction> payTransList = payTransactionDao.selectPayByPayId(payment.getPayId());
        for(PayTransaction paytrans : payTransList){
            if(!PaymentConsts.TRANS_STATUS_FINISH.equals(paytrans.getStatus())
                    || PaymentConsts.TRANS_REFUND_STATUS_FINISH.equals(paytrans.getRefundStatus())){
                continue;
            }
            
            PayTransaction reftrans = getRefProcessByPayId(payment.getPayId(), paytrans.getPayType());
            if(reftrans == null){
                reftrans = tradeRefundServiceAssistLogic.createRefundTransFromPayTrans(paytrans, req);
            }
            doPayTypeHandle(payment, paytrans, reftrans, req);
        }
    }

    @Override
    public void dealRefundForPartPayType(TradeRefundReq req) {
        if(!checkReqParamForPartPayType(req)){
            LOGGER.error("PartPayType Refund error, param not invalid: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PARAMERROR);
        }
        Payment payment = paymentDao.selectByOrderId(req.getOrderId());
        if(payment == null){
            LOGGER.error("PartPayType Refund error, payment not found: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PAYMENT_NOT_EXIST);
        }
        checkPaymentIsAllowRefundForPart(payment);
        checkPaymentRefundAmountLimit(payment, req.getRefundAmount());
        
        PayTransaction paytrans = null;
        PayTransaction reftrans = getRefProcessByPayId(payment.getPayId(), req.getPayType());
        if(reftrans == null){
            // 获取原支付流水
            PayTransaction paytransParam = new PayTransaction();
            paytransParam.setPayId(payment.getPayId());
            paytransParam.setPayType(req.getPayType());
            paytrans = payTransactionDao.selectPayByPayIdAndPayType(paytransParam);
            if(paytrans == null 
                    || !PaymentConsts.TRANS_STATUS_FINISH.equals(paytrans.getStatus()) ){
                LOGGER.error("PartPayType Refund error, paytrans not found/finished or not case refund: {}",
                        JsonUtil.toJson(req));
                throw BizException.build(BizResCode.PAY_TRANS_NOT_EXIST);
            }
            checkPayTransRefundAmountLimit(paytrans, req.getRefundAmount());
            
            reftrans = tradeRefundServiceAssistLogic.createRefundTransFromPayTrans(paytrans, req);
            
        }
        
        doPayTypeHandle(payment, paytrans, reftrans, req);
    }
    
    @Override
    public void dealRefundForPartOffline(TradeRefundReq req) {
        if(!checkReqParamForPartOffline(req)){
            LOGGER.error("PartOffline Refund error, param not invalid: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PARAMERROR);
        }
        Payment payment = paymentDao.selectByOrderId(req.getOrderId());
        if(payment == null){
            LOGGER.error("PartOffline Refund error, payment not found: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PAYMENT_NOT_EXIST);
        }
        checkPaymentIsAllowRefundForPart(payment);
        // checkPaymentRefundAmountLimit(payment, req.getRefundAmount());
        
        PayTransaction reftrans = tradeRefundServiceAssistLogic.createRefundTransForNoOriginTransRefund(payment, req);
        
        doPayTypeHandle(payment, null, reftrans, req);
    }

    @Override
    public void dealRefundForNoOrder(TradeRefundReq req) {
        if(!checkReqParamForNoOrder(req)){
            LOGGER.error("Noorder Refund error, param not invalid: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PARAMERROR);
        }
        Payment payment = paymentDao.selectByOrderId(req.getOrderId());
        if(payment != null){
            checkPaymentIsAllowRefundForNoOrder(payment);
        }else{
            payment = tradeRefundServiceAssistLogic.createPaymentForNoOriginOrderRefund(req);
        }
        
        PayTransaction reftrans = getRefProcessByPayId(payment.getPayId(), req.getPayType());
        if(reftrans == null){
            reftrans = tradeRefundServiceAssistLogic.createRefundTransForNoOriginTransRefund(payment, req);
        }
        
        doPayTypeHandle(payment, null, reftrans, req);
    }
    
    /**
     * 按支付方式处理
     * @param payment
     * @param reftrans
     * @param req
     * @return
     */
    private Object doPayTypeHandle(Payment payment, PayTransaction paytrans, 
            PayTransaction reftrans, TradeRefundReq req){
        PayProcessContext.init();
        PayProcessContext.setBrandId(payment.getBrandId());
        PayProcessContext.setStoreId(payment.getStoreId());
        PayProcessContext.setPayment(payment);
        PayProcessContext.setRefundTransation(reftrans);
        PayProcessContext.setTransation(paytrans);
        PayTypeHandler handler = PayTypeHandlerFactory.getHandler(reftrans.getPayType());
        Object extdata = handler.doRefund(req);
        
        return extdata;
    }
    
    /**
     * 通用参数校验
     * @param req
     * @return
     */
    public boolean checkCommonReqParam(TradeRefundReq req){
        if(req == null
                || req.getRefundMode() == null
                || req.getRefundAmount() == null
                || req.getRefundAmount() <= 0){
            return false;
        }
        
        if(StringUtils.isBlank(req.getBrandId()) 
                || StringUtils.isBlank(req.getStoreId())
                || StringUtils.isBlank(req.getOrderId())
                || StringUtils.isBlank(req.getOrderTime())){
            return false;
        }
        
        if(StringUtils.isBlank(req.getSourceType())
                || StringUtils.isBlank(req.getTerminalDeviceNo())
                || StringUtils.isBlank(req.getTerminalIp())
                || StringUtils.isBlank(req.getOperateUser())){
            return false;
        }
        
        return true;
    }
    
    /**
     * 参数校验:部分退款-指定支付方式原路退回
     * @param req
     * @return
     */
    private boolean checkReqParamForPartPayType(TradeRefundReq req){
        if(StringUtils.isBlank(req.getPayType())){
            return false;
        }
        
        return true;
    }
    
    /**
     * 参数校验:部分退款-不依赖原支付方式，在线下以现金方式退回
     * @param req
     * @return
     */
    private boolean checkReqParamForPartOffline(TradeRefundReq req){
        if(StringUtils.isBlank(req.getPayType())
                || !PaymentConsts.PAYTYPE_CASH.equals(req.getPayType())){
            return false;
        }
        
        return true;
    }
    
    /**
     * 无单退款参数校验
     * @param req
     */
    private boolean checkReqParamForNoOrder(TradeRefundReq req){
        if(!PaymentConsts.PAYTYPE_CASH.equals(req.getPayType())){
            return false;
        }
        
        return true;
    }
    
    /**
     * 根据支付单号、支付方式获取退款中的流水
     * @param payId
     * @param payType
     * @return
     */
    private PayTransaction getRefProcessByPayId(String payId, String payType){
        PayTransaction reftrans = null;
        PayTransaction ptparam = new PayTransaction();
        ptparam.setPayId(payId);
        ptparam.setPayType(payType);
        List<PayTransaction> transLst = payTransactionDao.selectRefProcessByPayIdAndPayType(ptparam);
        if (transLst != null && !transLst.isEmpty()) {
            reftrans = transLst.get(0);
        } 
        return reftrans;
    }
    
    /**
     * 检查支付单的退款金额限制
     * @param payment
     * @param refundAmount
     */
    private void checkPaymentRefundAmountLimit(Payment payment, long refundAmount){
        long availAmt = payment.getHadPayAmount() - payment.getRefundAmount();
        if (refundAmount > availAmt) {
            LOGGER.error("Refund amount error, payid:{}, refundamount:{}", payment.getPayId(), refundAmount);
            throw BizException.build(BizResCode.REFUNDAMOUNTERROR);
        }
    }
    
    /**
     * 检查支付流水的退款金额限制
     * @param paytrans
     * @param refundAmount
     */
    private void checkPayTransRefundAmountLimit(PayTransaction paytrans, long refundAmount){
        long availAmt = paytrans.getAmount() - paytrans.getHadRefundAmount();
        if (refundAmount > availAmt) {
            LOGGER.error("Refund amount error, transId:{}, refundamount:{}", paytrans.getTransId(), refundAmount);
            throw BizException.build(BizResCode.REFUND_TRANS_AMOUNTERROR);
        }
    }
    
    /**
     * 检查支付单是否允许整单退款
     * @param payment
     */
    private void checkPaymentIsAllowRefundForWhole(Payment payment){
        if (!PaymentConsts.PAY_STATUS_FINISH.equals(payment.getPayStatus())
                && !PaymentConsts.PAY_STATUS_PAY_PART.equals(payment.getPayStatus())
                && !PaymentConsts.PAY_STATUS_REF_PROCESS.equals(payment.getPayStatus())
                && !PaymentConsts.PAY_STATUS_REF_PART.equals(payment.getPayStatus())) {
            LOGGER.error("Whole Refund not allow, payid:{}, status:{}", payment.getPayId(), payment.getPayStatus());
            throw BizException.build(BizResCode.REFUNDNOTALLOWED);
        }
    }
    
    /**
     * 检查支付单是否允许部分退款
     * @param payment
     */
    private void checkPaymentIsAllowRefundForPart(Payment payment){
        if (!PaymentConsts.PAY_STATUS_FINISH.equals(payment.getPayStatus())
                && !PaymentConsts.PAY_STATUS_PAY_PART.equals(payment.getPayStatus())
                && !PaymentConsts.PAY_STATUS_REF_PROCESS.equals(payment.getPayStatus())
                && !PaymentConsts.PAY_STATUS_REF_PART.equals(payment.getPayStatus())) {
            LOGGER.error("Part Refund not allow, payid:{}, status:{}", payment.getPayId(), payment.getPayStatus());
            throw BizException.build(BizResCode.REFUNDNOTALLOWED);
        }
    }
    
    /**
     * 检查支付单是否允许无单退款
     * @param payment
     */
    private void checkPaymentIsAllowRefundForNoOrder(Payment payment){
        if (!PaymentConsts.PAY_STATUS_REF_PROCESS.equals(payment.getPayStatus())) {
            LOGGER.error("noorder Refund not allow, payid:{}, status:{}", payment.getPayId(), payment.getPayStatus());
            throw BizException.build(BizResCode.REFUNDNOTALLOWED);
        }
    }
    
}
