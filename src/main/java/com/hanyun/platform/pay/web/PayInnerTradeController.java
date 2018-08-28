/**
 * 
 */
package com.hanyun.platform.pay.web;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.service.PayRefundTradeService;
import com.hanyun.platform.pay.service.PayTradeQueryService;
import com.hanyun.platform.pay.service.PayTradeService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateRes;
import com.hanyun.platform.pay.vo.trade.TradeQueryReq;
import com.hanyun.platform.pay.vo.trade.TradeQueryRes;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundRes;
import com.hanyun.platform.pay.vo.trade.TradeExtraInfoQueryReq;
import com.hanyun.platform.pay.vo.trade.TradeExtraInfoQueryRes;


/**
 * 支付面向内部的控制器
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午2:11:39
 */
@Controller
@RequestMapping(value = "/inner/trade")
public class PayInnerTradeController {
    private static Logger LOGGER = LoggerFactory.getLogger(PayInnerTradeController.class);
    @Resource
    private PayTradeQueryService payTradeQueryService;
    @Resource
    private PayTradeService payTradeService;
    @Resource
    private PayRefundTradeService payRefundTradeService;

    /**
     * 预创建支付单
     */
    @RequestMapping(value = "/precreate", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<TradePreCreateRes> preCreate(@RequestBody TradePreCreateReq req) {
        LOGGER.info("inner trade precreate req: {}", JsonUtil.toJson(req));
        
        HttpResponse<TradePreCreateRes> res = null;
        try {
            res = payTradeService.preCreate(req);
            LOGGER.info("inner trade precreate res: {}", JsonUtil.toJson(res));
        } catch (Exception e) {
            LOGGER.error("inner preCreate error !", e);
            res = BizResUtil.fail(e, null);
        }

        return res;
    }

    /**
     * 支付单查询
     */
    @RequestMapping(value = "/query", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<TradeQueryRes> query(@RequestBody TradeQueryReq req) {
        LOGGER.info("inner trade query req: {}", JsonUtil.toJson(req));
        if(StringUtils.isBlank(req.getPayId()) ||StringUtils.isBlank(req.getOrderId())){
            LOGGER.error("inner trade query param error! req: {}", JsonUtil.toJson(req));
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }
        
        HttpResponse<TradeQueryRes> res = null;
        try {
            res = payTradeQueryService.query(req);
            LOGGER.info("inner trade query res: {}", JsonUtil.toJson(res));
        } catch (Exception e) {
            LOGGER.error("inner query error !", e);
            res = BizResUtil.fail(e, null);
        }

        return res;
    }

    /**
     * 退款
     */
    @RequestMapping(value = "/refund", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<TradeRefundRes> refund(@RequestBody TradeRefundReq req) {
        LOGGER.info("inner trade refund req: {}", JsonUtil.toJson(req));
        
        HttpResponse<TradeRefundRes> res = null;
        try {
            res = payRefundTradeService.refund(req);
            LOGGER.info("inner trade refund res: {}", JsonUtil.toJson(res));
        } catch (Exception e) {
            LOGGER.error("inner refund error !", e);
            res = BizResUtil.fail(e, null);
        }

        return res;
    }
    
    /**
     * 提供订单系统查询额外信息比如刷卡方面的 旺POS收银体系订单号
     */
    @RequestMapping(value = "/getextrainfo", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<TradeExtraInfoQueryRes> getExtraInfo(@RequestBody TradeExtraInfoQueryReq req) {
        LOGGER.info("get extra info req: {}", JsonUtil.toJson(req));
        if(StringUtils.isBlank(req.getOrderId())){
            LOGGER.error("get extra info req param error! req: {}", JsonUtil.toJson(req));
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }
        
        HttpResponse<TradeExtraInfoQueryRes> res = null;
        try {
    		res =  payTradeQueryService.getExtByOrderId(req);
    		LOGGER.info("get extra info  res: {}", JsonUtil.toJson(res));
        } catch (Exception e) {
            LOGGER.error("get extra info  error !", e);
            res = BizResUtil.fail(e, null);
        }

        return res;
    }     

}
