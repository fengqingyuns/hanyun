/**
 * 
 */
package com.hanyun.platform.pay.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandler;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandlerFactory;
import com.hanyun.platform.pay.logic.paytypemgr.PayTypeFetchLogic;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.service.PayTradeService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.util.PaymentTradeUtils;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateRes;
import com.hanyun.platform.pay.vo.trade.TradePayBaseReq;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePayRes;

/**
 * 支付交易服务
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年6月15日 下午2:52:16
 */
@Service
public class PayTradeServiceImpl implements PayTradeService {
    private static Logger LOGGER = LoggerFactory.getLogger(PayTradeServiceImpl.class);

    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private PayTypeFetchLogic payTypeFetchLogic;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;

    @Override
    @Transactional
    public HttpResponse<TradePreCreateRes> preCreate(TradePreCreateReq req) throws Exception {
        LOGGER.info("payment pre create, param: {}", JsonUtil.toJson(req));

        checkBaseCommonReqParam(req);

        MchActualPayModeInfo payChnMode = getPayChnMode(req);

        Payment payment = getPayment(req, true);
        if (!validPaymentStatusAllowPay(payment)) {
            return BizResUtil.fail(BizResCode.PAY_NOT_ALLOW);
        }
        if(!validPaymentAmountLimit(payment, req)){
            return BizResUtil.fail(BizResCode.PAY_AMOUNT_ERROR);
        }

        PayTransaction trans = getPayTrans(payment, payChnMode, req, true);
        if (!validTransStatusAllowPay(trans)) {
            return BizResUtil.fail(BizResCode.PAY_TYPE_TRANS_NOT_ALLOW);
        }
        if(!validPayTransAmountLimit(trans, req)){
            return BizResUtil.fail(BizResCode.PAY_AMOUNT_NOT_CHANGE);
        }

        initPayProcessContext(payment, trans);
        PayTypeHandler handler = PayTypeHandlerFactory.getHandler(req.getPayType());
        Object extdata = handler.doPreCreate(req);

        TradePreCreateRes data = new TradePreCreateRes();
        PaymentTradeUtils.fillTradePayRes(data, payment, trans, extdata);

        LOGGER.info("payment pre create success, param: {}", JsonUtil.toJson(req));

        return BizResUtil.succ(data);
    }

    @Override
    @Transactional
    public HttpResponse<TradePayRes> pay(TradePayReq req) throws Exception {
        LOGGER.info("payment pay, param: {}", JsonUtil.toJson(req));

        checkBaseCommonReqParam(req);

        MchActualPayModeInfo payChnMode = getPayChnMode(req);

        Payment payment = null;
        PayTransaction trans = null;
        if (PaymentConsts.PAYTYPE_SET_NEED_PRECREATE.contains(req.getPayType())) {
            // 需要先创建预支付单的
            payment = getPayment(req, false);
            if (payment == null) {
                return BizResUtil.fail(BizResCode.PAY_NEED_PRE_CREATE);
            }
            if (!validPaymentStatusAllowPay(payment)) {
                return BizResUtil.fail(BizResCode.PAY_NOT_ALLOW);
            }
            if(!validPaymentAmountLimit(payment, req)){
                return BizResUtil.fail(BizResCode.PAY_AMOUNT_ERROR);
            }
            
            trans = getPayTrans(payment, payChnMode, req, false);
            if (trans == null) {
                return BizResUtil.fail(BizResCode.PAY_NEED_PRE_CREATE);
            }
        } else {
            payment = getPayment(req, true);
            if (!validPaymentStatusAllowPay(payment)) {
                return BizResUtil.fail(BizResCode.PAY_NOT_ALLOW);
            }
            if(!validPaymentAmountLimit(payment, req)){
                return BizResUtil.fail(BizResCode.PAY_AMOUNT_ERROR);
            }
            
            trans = getPayTrans(payment, payChnMode, req, true);
        }
        
        if (!validTransStatusAllowPay(trans)) {
            return BizResUtil.fail(BizResCode.PAY_TYPE_TRANS_NOT_ALLOW);
        }
        if(!validPayTransAmountLimit(trans, req)){
            return BizResUtil.fail(BizResCode.PAY_AMOUNT_NOT_CHANGE);
        }

        initPayProcessContext(payment, trans);
        PayTypeHandler handler = PayTypeHandlerFactory.getHandler(req.getPayType());
        Object extdata = handler.doPay(req);

        TradePayRes data = new TradePayRes();
        PaymentTradeUtils.fillTradePayRes(data, payment, trans, extdata);

        LOGGER.info("payment pay success, param: {}", JsonUtil.toJson(req));

        return BizResUtil.succ(data);
    }

    /**
     * 初始化流程上下文
     * 
     * @param payment
     * @param trans
     */
    private void initPayProcessContext(Payment payment, PayTransaction trans) {
        PayProcessContext.init();
        PayProcessContext.setBrandId(payment.getBrandId());
        PayProcessContext.setStoreId(payment.getStoreId());
        PayProcessContext.setPayment(payment);
        PayProcessContext.setTransation(trans);
    }

    /**
     * 获取支付交易流水
     * 
     * @param payment
     * @param req
     * @param createNewWhenNotExist
     * @return
     */
    private PayTransaction getPayTrans(Payment payment, MchActualPayModeInfo payChnMode, TradePayBaseReq req,
            boolean createNewWhenNotExist) {
        PayTransaction ptparam = new PayTransaction();
        ptparam.setPayId(payment.getPayId());
        ptparam.setPayType(req.getPayType());
        PayTransaction trans = payTransactionDao.selectPayByPayIdAndPayType(ptparam);

        if (trans == null && createNewWhenNotExist) {
            trans = tradeServiceAssistLogic.createPayTrans(payment, payChnMode, req, PaymentConsts.TRANS_STATUS_INIT);
        }
        return trans;
    }

    /**
     * 获取支付单
     * 
     * @param req
     * @param createNewWhenNotExist
     * @return
     */
    private Payment getPayment(TradePayBaseReq req, boolean createNewWhenNotExist) {
        Payment payment = paymentDao.selectByOrderId(req.getOrderId());
        if (payment == null && createNewWhenNotExist) {
            payment = tradeServiceAssistLogic.createPayment(req);
        }
        return payment;
    }

    /**
     * 检查支付单状态是否允许支付
     * 
     * @param payment
     * @return
     */
    private boolean validPaymentStatusAllowPay(Payment payment) {
        // 检查状态是否允许
        if (PaymentConsts.PAY_STATUS_INIT.equals(payment.getPayStatus())
                || PaymentConsts.PAY_STATUS_PROCESS.equals(payment.getPayStatus())
                || PaymentConsts.PAY_STATUS_PAY_PART.equals(payment.getPayStatus())) {
            return true;
        }
        return false;
    }

    /**
     * 检查支付流水状态是否允许支付
     * 
     * @param trans
     * @return
     */
    private boolean validTransStatusAllowPay(PayTransaction trans) {
        // 检查状态是否允许
        if (PaymentConsts.TRANS_STATUS_INIT.equals(trans.getStatus())
                || PaymentConsts.TRANS_STATUS_PROCESS.equals(trans.getStatus())) {
            return true;
        }
        return false;
    }

    /**
     * 检查支付单支付金额是否允许
     * 
     * @param payment
     * @param req
     */
    private boolean validPaymentAmountLimit(Payment payment, TradePayBaseReq req) {
        // 支付金额不能大于 还未支付的金额
        long availAmount = payment.getPayAmount() - payment.getHadPayAmount() - payment.getHadDiscountAmount();
        long curDiscountAmount = req.getCurDiscountAmount()==null ? 0 : req.getCurDiscountAmount();
        long curWillSubAmount = req.getCurPayAmount() + curDiscountAmount;
        if (availAmount >= curWillSubAmount) {
            return true;
        }
        return false;
    }
    
    /**
     * 检查支付流水支付金额限制
     * @param trans
     * @param req
     */
    private boolean validPayTransAmountLimit(PayTransaction trans, TradePayBaseReq req){
        // 如果已存在交易流水，则不能改变支付金额
        if(trans.getAmount().equals(req.getCurPayAmount())){
            return true;
        }
        return false;
    }

    /**
     * 获取实际的支付方式信息
     * 
     * @param req
     * @return
     */
    private MchActualPayModeInfo getPayChnMode(TradePayBaseReq req) {
        MchActualPayModeInfo payChnMode = payTypeFetchLogic.fetchOnlinePayType(req.getPayType(), req.getBrandId());
        if (payChnMode == null) {
            throw BizException.build(BizResCode.PAYTYPENOTAVAIL);
        }

        return payChnMode;
    }

    /**
     * 检查通用请求参数
     * 
     * @param req
     * @return
     */
    private void checkBaseCommonReqParam(TradePayBaseReq req) {
        if (!req.checkCommonParam()) {
            LOGGER.error(req.getClass().getSimpleName() + " param error: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PARAMERROR);
        }
    }

}
