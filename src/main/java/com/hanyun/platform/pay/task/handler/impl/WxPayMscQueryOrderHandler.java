/**
 * 
 */
package com.hanyun.platform.pay.task.handler.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.platform.pay.adapt.cib.weixin.CibWeiXinOrderQueryAdapter;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderQueryReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderQueryRes;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.consts.TaskMessageType;
import com.hanyun.platform.pay.consts.TaskQueueConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.PaymentDao;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.task.handler.HandleResult;
import com.hanyun.platform.pay.task.handler.TaskBaseHandler;
import com.hanyun.platform.pay.vo.trade.TradePayWeixinExtData;

/**
 * 微信商户扫码支付主动查询订单
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年11月22日 下午5:59:27
 */
@Component
public class WxPayMscQueryOrderHandler extends TaskBaseHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayMscQueryOrderHandler.class);
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private CibWeiXinOrderQueryAdapter cibWeiXinOrderQueryAdapter;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;

    @Override
    public Integer getMessageType() {
        return TaskMessageType.WXPAY_MSC_QUERY_ORDER;
    }

    @Override
    public HandleResult handle(TaskQueue task) {
        HandleResult rst = new HandleResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);

        try {
            String transId = task.getTransId();
            PayTransaction trans = payTransactionDao.selectByTransId(transId);
            Payment payment = paymentDao.selectByPayId(trans.getPayId());

            PayProcessContext.setBrandId(trans.getBrandId());

            OrderQueryReq qreq = new OrderQueryReq();
            qreq.setOut_trade_no(transId);

            OrderQueryRes qres = cibWeiXinOrderQueryAdapter.request(qreq);
            
            // 检查交易流水状态
            if(PaymentConsts.TRANS_STATUS_FINISH.equals(trans.getStatus()))   {
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_SUCC);
                return rst;
            }
            
            String tradeState = qres.getTrade_state();
            if (CibWeiXinConsts.TRADE_STATE_USERPAYING.equals(tradeState)) {
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);

            } else if (CibWeiXinConsts.TRADE_STATE_SUCCESS.equals(tradeState)
                    || CibWeiXinConsts.TRADE_STATE_REFUND.equals(tradeState)) {
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_SUCC);
                // 更新支付流水状态
                tradeServiceAssistLogic.updatePayTransWhenPayFinish(trans);
                // 更新支付单状态
                tradeServiceAssistLogic.updatePaymentWhenPayFinish(payment, trans);
                doSubmitNotifyOrderTask(payment, trans, qres);
            } else if(CibWeiXinConsts.TRADE_STATE_PAYERROR.equals(tradeState)
                    || CibWeiXinConsts.TRADE_STATE_NOTPAY.equals(tradeState)){
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_DELAY_TRY);
                rst.setDelayTime(30);
                // 更新支付流水状态
                tradeServiceAssistLogic.updatePayTransWhenPayFail(trans);
                doSubmitNotifyOrderTask(payment, trans, qres);
            } else if(CibWeiXinConsts.TRADE_STATE_CLOSED.equals(tradeState)
                    || CibWeiXinConsts.TRADE_STATE_REVOKED.equals(tradeState)){
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_NO_TRY);
                // 更新支付流水状态
                tradeServiceAssistLogic.updatePayTransWhenPayFail(trans);
                doSubmitNotifyOrderTask(payment, trans, qres);
            }else{
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);
                // 更新支付流水状态
                tradeServiceAssistLogic.updatePayTransWhenPayFail(trans);
                doSubmitNotifyOrderTask(payment, trans, qres);
            }

            rst.setResultInfo(JsonUtil.toJson(qres));
        } catch (Exception e) {
            rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);
            LOGGER.error("WxPayMscQueryOrderHandler handle error!", e);
        }

        return rst;
    }

    /**
     * 提交通知订单系统的任务
     * 
     * @param payment
     * @param trans
     * @param qres
     */
    private void doSubmitNotifyOrderTask(Payment payment, PayTransaction trans, OrderQueryRes qres) {
        TradePayWeixinExtData extdata = new TradePayWeixinExtData();
        extdata.setWxOrderId(qres.getTransaction_id());
        tradeServiceAssistLogic.notifyOrderWhenPayFinish(payment, trans, extdata);
    }

}
