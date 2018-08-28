/**
 * 
 */
package com.hanyun.platform.pay.web;

import java.util.Map;

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
import com.hanyun.platform.pay.domain.PayMchMode;
import com.hanyun.platform.pay.service.PayTradeMchInfoService;
import com.hanyun.platform.pay.service.PayTradeQueryService;
import com.hanyun.platform.pay.service.PayTradeService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.trade.TradeMchFeeRateReq;
import com.hanyun.platform.pay.vo.trade.TradeMchPayModeReq;
import com.hanyun.platform.pay.vo.trade.TradeMchPayModeRes;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePayRes;

/**
 * 支付面向外部的控制器
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午2:11:28
 */
@Controller
@RequestMapping(value = "/trade")
public class PayTradeController {
    private static Logger LOGGER = LoggerFactory.getLogger(PayTradeController.class);
    @Resource
    private PayTradeMchInfoService payTradeMchInfoService;
    @Resource
    private PayTradeService payTradeService;
    @Resource
    private PayTradeQueryService payTradeQueryService;

    /**
     * 支付
     */
    @RequestMapping(value = "/pay", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<TradePayRes> pay(@RequestBody TradePayReq req) {
        LOGGER.info("trade pay req: {}", JsonUtil.toJson(req));

        HttpResponse<TradePayRes> res = null;
        try {
            res = payTradeService.pay(req);
            LOGGER.info("trade pay res: {}", JsonUtil.toJson(res));
        } catch (Exception e) {
            LOGGER.error("pay error !", e);
            res = BizResUtil.fail(e, null);
        }

        return res;
    }

    /**
     * 查询商户所有可用且在线的支付方式
     */
    @RequestMapping(value = "/mchpaytype", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<TradeMchPayModeRes> getTradeMchPayMode(@RequestBody TradeMchPayModeReq req) {
        LOGGER.info("trade getTradeMchPayMode req: {}", JsonUtil.toJson(req));
        if (req == null || StringUtils.isBlank(req.getBrandId())) {
            LOGGER.error("trade getTradeMchPayMode param error! req: {}", JsonUtil.toJson(req));
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }

        HttpResponse<TradeMchPayModeRes> res = null;
        try {
            res = payTradeMchInfoService.getTradeMchPayMode(req);
            LOGGER.info("trade getTradeMchPayMode res: {}", JsonUtil.toJson(res));
        } catch (Exception e) {
            LOGGER.error("getTradeMchPayMode error !", e);
            res = BizResUtil.fail(e, null);
        }

        return res;
    }

    /**
     * 查询商户费率
     */
    @RequestMapping(value = "/mchfeerate", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<Map<String, PayMchMode>> getTradeMchFeeRate(@RequestBody TradeMchFeeRateReq req) {
        LOGGER.info("trade getTradeMchFeeRate req: {}", JsonUtil.toJson(req));
        if (req == null || StringUtils.isBlank(req.getBrandId())) {
            LOGGER.error("trade getTradeMchFeeRate param error! req: {}", JsonUtil.toJson(req));
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }

        HttpResponse<Map<String, PayMchMode>> res = null;
        try {
            res = payTradeMchInfoService.getTradeMchFeeRate(req.getBrandId());
            LOGGER.info("trade getTradeMchFeeRate res: {}", JsonUtil.toJson(res));
        } catch (Exception e) {
            LOGGER.error("getTradeMchFeeRate error !", e);
            res = BizResUtil.fail(e, null);
        }

        return res;
    }

}
