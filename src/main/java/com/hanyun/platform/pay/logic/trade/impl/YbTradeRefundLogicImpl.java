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
import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.PayTransactionNewDao;
import com.hanyun.platform.pay.dao.PaymentNewDao;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandler;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandlerFactory;
import com.hanyun.platform.pay.logic.trade.YbTradeRefundLogic;
import com.hanyun.platform.pay.logic.trade.YbTradeRefundServiceAssistLogic;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;

/**
 * 交易退款逻辑
 * @author liyinglong@hanyun.com
 * @date 2017年6月2日 下午6:10:57
 */
@Component
public class YbTradeRefundLogicImpl implements YbTradeRefundLogic {
    private static final Logger LOGGER = LoggerFactory.getLogger(YbTradeRefundLogicImpl.class);
    
    @Resource
    private PaymentNewDao paymentNewDao;
    @Resource
    private PayTransactionNewDao payTransactionNewDao;
    @Resource
    private YbTradeRefundServiceAssistLogic ybTradeRefundServiceAssistLogic;

    @Override
    public void dealRefundForWhole(TradeRefundReq req) {
        
        PaymentNew payment = paymentNewDao.selectByOrderId(req.getOrderId());
        if(payment == null){
            LOGGER.error("Yb Whole Refund error, payment not found: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PAYMENT_NOT_EXIST);
        }
        if(PaymentNewConsts.PAY_STATUS_REF_ALL.equals(payment.getPayStatus())){
            PayProcessContext.setPaymentNew(payment);
            // 整单退款，如果已全部退款，直接返回成功。
            return;
        }
        
        checkPaymentIsAllowRefundForWhole(payment);
        
        List<PayTransactionNew> payTransList = payTransactionNewDao.selectPayByPayId(payment.getPayId());
        for(PayTransactionNew paytrans : payTransList){
            if(!PaymentNewConsts.TRANS_STATUS_FINISH.equals(paytrans.getStatus())
                    || PaymentNewConsts.TRANS_REFUND_STATUS_FINISH.equals(paytrans.getRefundStatus())){
                continue;
            }
            
            PayTransactionNew reftrans = getRefProcessByPayId(payment.getPayId(), paytrans.getPayType());
            if(reftrans == null){
                reftrans = ybTradeRefundServiceAssistLogic.createRefundTransFromPayTrans(paytrans, req);
            }
            doPayTypeHandle(payment, paytrans, reftrans, req);
        }
    }

    @Override
    public void dealRefundForPartPayType(TradeRefundReq req) {
        if(!checkReqParamForPartPayType(req)){
            LOGGER.error("Yb PartPayType Refund error, param not invalid: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PARAMERROR);
        }
        PaymentNew payment = paymentNewDao.selectByOrderId(req.getOrderId());
        if(payment == null){
            LOGGER.error("Yb PartPayType Refund error, payment not found: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PAYMENT_NOT_EXIST);
        }
        checkPaymentIsAllowRefundForPart(payment);
        checkPaymentRefundAmountLimit(payment, req.getRefundAmount());
        
        PayTransactionNew paytrans = null;
        PayTransactionNew reftrans = getRefProcessByPayId(payment.getPayId(), req.getPayType());
        if(reftrans == null){
            // 获取原支付流水
            PayTransactionNew paytransParam = new PayTransactionNew();
            paytransParam.setPayId(payment.getPayId());
            paytransParam.setPayType(req.getPayType());
            paytrans = payTransactionNewDao.selectPayByPayIdAndPayType(paytransParam);
            if(paytrans == null 
                    || !PaymentNewConsts.TRANS_STATUS_FINISH.equals(paytrans.getStatus()) ){
                LOGGER.error("Yb PartPayType Refund error, paytrans not found/finished or not case refund: {}",
                        JsonUtil.toJson(req));
                throw BizException.build(BizResCode.PAY_TRANS_NOT_EXIST);
            }
            checkPayTransRefundAmountLimit(paytrans, req.getRefundAmount());
            
            reftrans = ybTradeRefundServiceAssistLogic.createRefundTransFromPayTrans(paytrans, req);
            
        }
        
        doPayTypeHandle(payment, paytrans, reftrans, req);
    }
    
    @Override
    public void dealRefundForPartOffline(TradeRefundReq req) {
        if(!checkReqParamForPartOffline(req)){
            LOGGER.error("Yb PartOffline Refund error, param not invalid: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PARAMERROR);
        }
        PaymentNew payment = paymentNewDao.selectByOrderId(req.getOrderId());
        if(payment == null){
            LOGGER.error("Yb PartOffline Refund error, payment not found: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PAYMENT_NOT_EXIST);
        }
        checkPaymentIsAllowRefundForPart(payment);
        // checkPaymentRefundAmountLimit(payment, req.getRefundAmount());
        
        PayTransactionNew reftrans = ybTradeRefundServiceAssistLogic.createRefundTransForNoOriginTransRefund(payment, req);
        
        doPayTypeHandle(payment, null, reftrans, req);
    }

    @Override
    public void dealRefundForNoOrder(TradeRefundReq req) {
        if(!checkReqParamForNoOrder(req)){
            LOGGER.error("Yb Noorder Refund error, param not invalid: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PARAMERROR);
        }
        PaymentNew payment = paymentNewDao.selectByOrderId(req.getOrderId());
        if(payment != null){
            checkPaymentIsAllowRefundForNoOrder(payment);
        }else{
            payment = ybTradeRefundServiceAssistLogic.createPaymentForNoOriginOrderRefund(req);
        }
        
        PayTransactionNew reftrans = getRefProcessByPayId(payment.getPayId(), req.getPayType());
        if(reftrans == null){
            reftrans = ybTradeRefundServiceAssistLogic.createRefundTransForNoOriginTransRefund(payment, req);
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
    private Object doPayTypeHandle(PaymentNew payment, PayTransactionNew paytrans, 
            PayTransactionNew reftrans, TradeRefundReq req){
        PayProcessContext.init();
        PayProcessContext.setBrandId(payment.getBrandId());
        PayProcessContext.setStoreId(payment.getStoreId());
        PayProcessContext.setPaymentNew(payment);
        PayProcessContext.setRefundTransationNew(reftrans);
        PayProcessContext.setTransationNew(paytrans);
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
                || !PaymentNewConsts.PAYTYPE_CASH.equals(req.getPayType())){
            return false;
        }
        
        return true;
    }
    
    /**
     * 无单退款参数校验
     * @param req
     */
    private boolean checkReqParamForNoOrder(TradeRefundReq req){
        if(!PaymentNewConsts.PAYTYPE_CASH.equals(req.getPayType())){
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
    private PayTransactionNew getRefProcessByPayId(String payId, String payType){
        PayTransactionNew reftrans = null;
        PayTransactionNew ptparam = new PayTransactionNew();
        ptparam.setPayId(payId);
        ptparam.setPayType(payType);
        List<PayTransactionNew> transLst = payTransactionNewDao.selectRefProcessByPayIdAndPayType(ptparam);
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
    private void checkPaymentRefundAmountLimit(PaymentNew payment, long refundAmount){
        long availAmt = payment.getHadPayAmount() - payment.getRefundAmount();
        if (refundAmount > availAmt) {
            LOGGER.error("Yb Refund amount error, payid:{}, refundamount:{}", payment.getPayId(), refundAmount);
            throw BizException.build(BizResCode.REFUNDAMOUNTERROR);
        }
    }
    
    /**
     * 检查支付流水的退款金额限制
     * @param paytrans
     * @param refundAmount
     */
    private void checkPayTransRefundAmountLimit(PayTransactionNew paytrans, long refundAmount){
        long availAmt = paytrans.getAmount() - paytrans.getHadRefundAmount();
        if (refundAmount > availAmt) {
            LOGGER.error("Yb Refund amount error, transId:{}, refundamount:{}", paytrans.getTransId(), refundAmount);
            throw BizException.build(BizResCode.REFUND_TRANS_AMOUNTERROR);
        }
    }
    
    /**
     * 检查支付单是否允许整单退款
     * @param payment
     */
    private void checkPaymentIsAllowRefundForWhole(PaymentNew payment){
        if (!PaymentNewConsts.PAY_STATUS_FINISH.equals(payment.getPayStatus())
                && !PaymentNewConsts.PAY_STATUS_PAY_PART.equals(payment.getPayStatus())
                && !PaymentNewConsts.PAY_STATUS_REF_PROCESS.equals(payment.getPayStatus())
                && !PaymentNewConsts.PAY_STATUS_REF_PART.equals(payment.getPayStatus())) {
            LOGGER.error("Yb Whole Refund not allow, payid:{}, status:{}", payment.getPayId(), payment.getPayStatus());
            throw BizException.build(BizResCode.REFUNDNOTALLOWED);
        }
    }
    
    /**
     * 检查支付单是否允许部分退款
     * @param payment
     */
    private void checkPaymentIsAllowRefundForPart(PaymentNew payment){
        if (!PaymentNewConsts.PAY_STATUS_FINISH.equals(payment.getPayStatus())
                && !PaymentNewConsts.PAY_STATUS_PAY_PART.equals(payment.getPayStatus())
                && !PaymentNewConsts.PAY_STATUS_REF_PROCESS.equals(payment.getPayStatus())
                && !PaymentNewConsts.PAY_STATUS_REF_PART.equals(payment.getPayStatus())) {
            LOGGER.error("Yb Part Refund not allow, payid:{}, status:{}", payment.getPayId(), payment.getPayStatus());
            throw BizException.build(BizResCode.REFUNDNOTALLOWED);
        }
    }
    
    /**
     * 检查支付单是否允许无单退款
     * @param payment
     */
    private void checkPaymentIsAllowRefundForNoOrder(PaymentNew payment){
        if (!PaymentNewConsts.PAY_STATUS_REF_PROCESS.equals(payment.getPayStatus())) {
            LOGGER.error("Yb noorder Refund not allow, payid:{}, status:{}", payment.getPayId(), payment.getPayStatus());
            throw BizException.build(BizResCode.REFUNDNOTALLOWED);
        }
    }
    
}
