/**
 * 
 */
package com.hanyun.platform.pay.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.dao.UmpayOrderDao;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.UmpayOrder;
import com.hanyun.platform.pay.logic.paytypemgr.PayTypeFetchLogic;
import com.hanyun.platform.pay.service.PayTradeQueryService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.trade.TradeQueryReq;
import com.hanyun.platform.pay.vo.trade.TradeQueryRes;
import com.hanyun.platform.pay.vo.trade.TradeExtraInfoQueryReq;
import com.hanyun.platform.pay.vo.trade.TradeExtraInfoQueryRes;

/**
 * 支付交易查询的服务
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午5:20:22
 */
@Service
public class PayTradeQueryServiceImpl implements PayTradeQueryService {
    private static Logger LOGGER = LoggerFactory.getLogger(PayTradeQueryServiceImpl.class);

    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private PayTypeFetchLogic payTypeFetchLogic;
    @Resource
    private UmpayOrderDao umpayOrderDao;

    @Override
    @Transactional
    public HttpResponse<TradeQueryRes> query(TradeQueryReq req) throws Exception {
        LOGGER.info("payment inner query, param: {}", JsonUtil.toJson(req));
        Payment payment = paymentDao.selectByPayId(req.getPayId());
        if (payment == null) {
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }
        TradeQueryRes data = new TradeQueryRes();
        data.setPayId(payment.getPayId());
        data.setBrandId(payment.getBrandId());
        data.setStoreId(payment.getStoreId());
        data.setOrderId(payment.getOrderId());
        data.setOrderTime(payment.getOrderTime());
        data.setOrderAmount(payment.getOrderAmount());
        data.setPayAmount(payment.getPayAmount());
        data.setHadPayAmount(payment.getHadPayAmount());
        data.setHadPayCount(payment.getHadPayCount());
        data.setHadDiscountAmount(payment.getHadDiscountAmount());
        data.setRefundAmount(payment.getRefundAmount());
        data.setPayStatus(payment.getPayStatus());
        data.setPayTime(payment.getPayTime());

        LOGGER.info("payment inner query success");

        return BizResUtil.succ(data);
    }

    /**
     * 通过订单号查询额外信息
     * 
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public HttpResponse<TradeExtraInfoQueryRes> getExtByOrderId(TradeExtraInfoQueryReq req) throws Exception {
        if (req == null || StringUtils.isBlank(req.getOrderId())) {
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }
        TradeExtraInfoQueryRes data = new TradeExtraInfoQueryRes();

        Payment payment = paymentDao.selectByOrderId(req.getOrderId());
        if (payment == null) {
            return BizResUtil.fail(BizResCode.PAYMENT_NOT_EXIST);
        }
        // 查询银行卡支付的信息
        PayTransaction ptparam = new PayTransaction();
        ptparam.setPayId(payment.getPayId());
        ptparam.setPayType(PaymentConsts.PAYTYPE_BANKCARD);
        PayTransaction payTrans = payTransactionDao.selectPayByPayIdAndPayType(ptparam);
        if (payTrans == null) {
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }
        // 通过明细流水来查询刷卡信息表
        UmpayOrder umpayOrder = umpayOrderDao.selectByTransId(payTrans.getTransId());
        if (umpayOrder == null) {
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }
        data.setWposCashierTradeNo(umpayOrder.getWposCashierTradeNo());

        return BizResUtil.succ(data);
    }

}
