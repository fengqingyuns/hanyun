package com.hanyun.platform.pay.service.impl;

import java.util.Date;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import com.hanyun.platform.pay.adapt.yeepay.YeepayCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.adapt.yeepay.model.YeeCommonResCode;
import com.hanyun.platform.pay.adapt.yeepay.model.YeeQueryOrderRes;
import com.hanyun.platform.pay.adapt.yeepay.model.YeepayResponseBase;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.consts.PaymentNewConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.PayTransactionNewDao;
import com.hanyun.platform.pay.dao.PaymentNewDao;
import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.PaymentNew;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandler;
import com.hanyun.platform.pay.logic.paytype.PayTypeHandlerFactory;
import com.hanyun.platform.pay.logic.paytype.yeepay.YeePayQueryHandler;
import com.hanyun.platform.pay.logic.paytypemgr.PayTypeFetchLogic;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogicYb;
import com.hanyun.platform.pay.service.PayTradeYbService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.util.PaymentTradeUtils;
import com.hanyun.platform.pay.vo.trade.TradePayBaseReq;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePayRes;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateRes;
import com.hanyun.platform.pay.vo.trade.YbTradePayAlipayExtData;

/**
 * 
 * @date 2018年8月1日
 * 
 * @apiDefine common 易宝支付相关
 * @author litao@hanyun.com
 */
@Service
public class PayTradeServiceYbImpl implements PayTradeYbService{
	private static Logger LOGGER = LoggerFactory.getLogger(PayTradeServiceYbImpl.class);

    @Autowired
    private PaymentNewDao paymentNewDao;
    @Autowired
    private PayTransactionNewDao payTransactionNewDao;
    @Autowired
    private PayTypeFetchLogic payTypeFetchLogic;
    @Autowired
    private TradeServiceAssistLogicYb tradeServiceAssistLogicYb;
    
    @Resource
    private YeePayQueryHandler yeePayQueryHandler;
	@Override
	@Transactional
	public HttpResponse<TradePreCreateRes> preCreate(TradePreCreateReq req) throws Exception {
		 LOGGER.info("Yb  paymentNew pre create, param: {}", JsonUtil.toJson(req));
		 //检查通用请求参数
		checkBaseCommonReqParam(req);
		//获取实际支付方式信息
		MchActualPayModeInfo payChnMode = getPayChnMode(req);
		//获取易宝支付单
		PaymentNew paymentNew = getPaymentNew(req, true);
		//检查易宝支付单状态是否允许支付
		if (!validPaymentStatusAllowPay(paymentNew)) {
	            return BizResUtil.fail(BizResCode.PAY_NOT_ALLOW);
	    }
		//检查支付金额是否允许支付
		if(!validPaymentAmountLimit(paymentNew, req)){
            return BizResUtil.fail(BizResCode.PAY_AMOUNT_ERROR);
        }
		//获取易宝交易流水
        PayTransactionNew transNew = getPayTransNew(paymentNew, payChnMode, req, true);
        //检查易宝支付流水支付状态是否允许支付
        if (!validTransStatusAllowPay(transNew)) {
            return BizResUtil.fail(BizResCode.PAY_TYPE_TRANS_NOT_ALLOW);
        }
        //检查易宝支付流水金额限制
        if(!validPayTransAmountLimit(transNew, req)){
            return BizResUtil.fail(BizResCode.PAY_AMOUNT_NOT_CHANGE);
        }
        //初始化流程上下文
        initPayProcessContext(paymentNew, transNew);
        
        PayTypeHandler handler = PayTypeHandlerFactory.getHandler(req.getPayType());
        Object extdata = handler.doPreCreate(req);
        
        TradePreCreateRes data = new TradePreCreateRes();
        //获取支付返回结果
        PaymentTradeUtils.fillTradePayYbRes(data, paymentNew, transNew, extdata);

        LOGGER.info(" Yb   paymentNew pre create success, param: {}", JsonUtil.toJson(req));

        return BizResUtil.succ(data);
	}

	@Override
	@Transactional
	public HttpResponse<TradePayRes> pay(TradePayReq req) throws Exception {
		LOGGER.info("Yb paymentNew pay, param: {}", JsonUtil.toJson(req));
		
		 //检查必填项
		 checkBaseCommonReqParam(req);
		 //获取实际支付方式信息
		 MchActualPayModeInfo payChnMode = getPayChnMode(req);
		 
		 PaymentNew paymentNew = null;
	     PayTransactionNew transNew = null;
		 //获取支付单
	     if (PaymentConsts.PAYTYPE_SET_NEED_PRECREATE.contains(req.getPayType())) {
	            // 需要先创建预支付单的
	            paymentNew = getPaymentNew(req, false);
	            if (paymentNew == null) {
	                return BizResUtil.fail(BizResCode.PAY_NEED_PRE_CREATE);
	            }
	            if (!validPaymentStatusAllowPay(paymentNew)) {
	                return BizResUtil.fail(BizResCode.PAY_NOT_ALLOW);
	            }
	            if(!validPaymentAmountLimit(paymentNew, req)){
	                return BizResUtil.fail(BizResCode.PAY_AMOUNT_ERROR);
	            }
	            
	            transNew = getPayTransNew(paymentNew, payChnMode, req, false);
	            if (transNew == null) {
	                return BizResUtil.fail(BizResCode.PAY_NEED_PRE_CREATE);
	            }
	        } else {
	            paymentNew = getPaymentNew(req, true);
	            if (!validPaymentStatusAllowPay(paymentNew)) {
	                return BizResUtil.fail(BizResCode.PAY_NOT_ALLOW);
	            }
	            if(!validPaymentAmountLimit(paymentNew, req)){
	                return BizResUtil.fail(BizResCode.PAY_AMOUNT_ERROR);
	            }
	            
	            transNew = getPayTransNew(paymentNew, payChnMode, req, true);
	        }
	        
	        if (!validTransStatusAllowPay(transNew)) {
	            return BizResUtil.fail(BizResCode.PAY_TYPE_TRANS_NOT_ALLOW);
	        }
	        if(!validPayTransAmountLimit(transNew, req)){
	            return BizResUtil.fail(BizResCode.PAY_AMOUNT_NOT_CHANGE);
	        }

         initPayProcessContext(paymentNew, transNew);
         
         PayTypeHandler handler = PayTypeHandlerFactory.getHandler(req.getPayType());
         Object extdata = null;
         YeepayResponseBase res = null;
        try {
        	extdata = handler.doPay(req);
        	if(extdata !=null){
        		res = (YeepayResponseBase)extdata;
        	}
        	LOGGER.info("============> extdata =====>"+res);
        		if(res.getStatus().equals(YeepayCommon.RES_STATUS)){
        			// 更新支付流水状态
        	        tradeServiceAssistLogicYb.updatePayTransWhenPayFinish(transNew);
        	        // 更新支付单状态
        	        tradeServiceAssistLogicYb.updatePaymentWhenPayFinish(paymentNew, transNew);
        		}
        		
			
	        //  通知订单系统
	      //  YbTradePayAlipayExtData extdatas = new YbTradePayAlipayExtData();
		  //  extdatas.setYbAliOrderId(transNew.getTransId());
	     //   tradeServiceAssistLogicYb.notifyOrderWhenPayFinish(paymentNew, transNew, extdatas);
//        	if(null!=extdata) {
//        	    LOGGER.info("extdata"+extdata);
//                YeepayResponseBase yeepayResponseBase = (YeepayResponseBase) extdata;
//                LOGGER.info("yeepayResponseBase----->"+JsonUtil.toJson(yeepayResponseBase));
//                if(yeepayResponseBase.getCode().equals(YeepayCommon.RES_CODE)){
//                    LOGGER.info("getStatus:"+yeepayResponseBase.getCode());
//                    LOGGER.info("transNew.getTransId():"+transNew.getTransId());
//                    //设置主动查询次数：延迟时间ms
//                    int m = 5,k=2000;
//                     transNew = payTransactionNewDao.selectByTransId(transNew.getTransId());
//                    LOGGER.info("transNew.getpapyId:"+transNew.getPayId());
//                     paymentNew = paymentNewDao.selectByPayId(transNew.getPayId());
//                    if(!transNew.getStatus().equals(PaymentNewConsts.TRANS_STATUS_FINISH)){
//                        LOGGER.info("主动查询订单，支付成功通知订单 transNWew:"+transNew);
//                        LOGGER.info("主动查询订单，支付成功通知订单 paymentNew:"+paymentNew);
//                        Thread.sleep(1000);
//                        for(int i = m ;i>0; i--){
//                            try {
//                                if(payTransactionNewDao.selectByTransId(transNew.getTransId()).getStatus().equals(PaymentNewConsts.TRANS_STATUS_FINISH)){
//                                    tradeServiceAssistLogicYb.notifyOrderWhenPayFinish(paymentNew, transNew, extdata);
//                                    break;
//                                }
//                                Thread.sleep(k);
//
//                            } catch (Exception exc) {
//                                exc.getStackTrace();
//                            }
//                        }
//                    }else{
//                        //通知订单
//                        LOGGER.info("支付成功通知订单-else");
//                        tradeServiceAssistLogicYb.notifyOrderWhenPayFinish(paymentNew, transNew, extdata);
//                    }
//                }
//            }


		} catch (TimeoutException e) {
			 LOGGER.info("========> Yb  paymentNew catch doPay res, param: {}",e);
		}
         TradePayRes data = new TradePayRes();
         PaymentTradeUtils.fillTradePayYbRes(data, paymentNew, transNew, extdata);

         LOGGER.info("=====> Yb paymentNew pay success, param: {}", JsonUtil.toJson(data));

         return BizResUtil.succ(data);
	}
	/**
     * 初始化流程上下文
     * 
     * @param paymentNew
     * @param transNew
     */
    private void initPayProcessContext(PaymentNew paymentNew, PayTransactionNew transNew) {
        PayProcessContext.init();
        PayProcessContext.setBrandId(paymentNew.getBrandId());
        PayProcessContext.setStoreId(paymentNew.getStoreId());
        PayProcessContext.setPaymentNew(paymentNew);
        PayProcessContext.setTransationNew(transNew);
        PayProcessContext.setYbNotifyUrl(null);
    }
	
	/**
     * 检查易宝支付流水支付金额限制
     * @param transNew
     * @param req
     */
    private boolean validPayTransAmountLimit(PayTransactionNew transNew, TradePayBaseReq req){
        // 如果已存在交易流水，则不能改变支付金额
        if(transNew.getAmount().equals(req.getCurPayAmount())){
            return true;
        }
        return false;
    }
	
	/**
     * 检查易宝支付流水状态是否允许支付
     * 
     * @param transNew
     * @return
     */
    private boolean validTransStatusAllowPay(PayTransactionNew transNew) {
        // 检查状态是否允许
        if (PaymentNewConsts.TRANS_STATUS_INIT.equals(transNew.getStatus())
                || PaymentNewConsts.TRANS_STATUS_PROCESS.equals(transNew.getStatus())) {
            return true;
        }
        return false;
    }
	
	/**
     * 获取易宝支付交易流水
     * 
     * @param paymentNew
     * @param req
     * @param createNewWhenNotExist
     * @return
     */
    private PayTransactionNew getPayTransNew(PaymentNew paymentNew, MchActualPayModeInfo payChnMode, TradePayBaseReq req,
            boolean createNewWhenNotExist) {
        PayTransactionNew ptparam = new PayTransactionNew();
        ptparam.setPayId(paymentNew.getPayId());
        ptparam.setPayType(req.getPayType());
        PayTransactionNew transNew = payTransactionNewDao.selectPayByPayIdAndPayType(ptparam);

        if (transNew == null && createNewWhenNotExist) {
            transNew = tradeServiceAssistLogicYb.createPayTrans(paymentNew, payChnMode, req, PaymentNewConsts.TRANS_STATUS_INIT);
        }
        return transNew;
    }
    
	/**
     * 检查易宝支付单支付金额是否允许
     * 
     * @param paymentNew
     * @param req
     */
    private boolean validPaymentAmountLimit(PaymentNew paymentNew, TradePayBaseReq req) {
        // 支付金额不能大于 还未支付的金额
        long availAmount = paymentNew.getPayAmount() - paymentNew.getHadPayAmount() - paymentNew.getHadDiscountAmount();
        long curDiscountAmount = req.getCurDiscountAmount()==null ? 0 : req.getCurDiscountAmount();
        long curWillSubAmount = req.getCurPayAmount() + curDiscountAmount;
        if (availAmount >= curWillSubAmount) {
            return true;
        }
        return false;
    }
    
	/**
     * 检查易宝支付单状态是否允许支付
     * 
     * @param paymentNew
     * @return
     */
    private boolean validPaymentStatusAllowPay(PaymentNew paymentNew) {
        // 检查状态是否允许
        if (PaymentNewConsts.PAY_STATUS_INIT.equals(paymentNew.getPayStatus())
                || PaymentNewConsts.PAY_STATUS_PROCESS.equals(paymentNew.getPayStatus())
                || PaymentNewConsts.PAY_STATUS_PAY_PART.equals(paymentNew.getPayStatus())) {
            return true;
        }
        return false;
    }
    
	/**
     * 获取易宝支付单
     * 
     * @param req
     * @param createNewWhenNotExist
     * @return
     */
    private PaymentNew getPaymentNew(TradePayBaseReq req, boolean createNewWhenNotExist) {
        PaymentNew paymentNew = paymentNewDao.selectByOrderId(req.getOrderId());
        if (paymentNew == null && createNewWhenNotExist) {
            paymentNew = tradeServiceAssistLogicYb.createPayment(req);
        }
        return paymentNew;
    	
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
            LOGGER.error(req.getClass().getSimpleName() + "Yb param error: {}", JsonUtil.toJson(req));
            throw BizException.build(BizResCode.PARAMERROR);
        }
    }
    
    /**
     * 如果支付返回结果异常主动查询订单
     * 
     * @param res
     * @return
     */
	public void excPay(YeepayResponseBase res){
		
	}
}
