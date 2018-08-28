package com.hanyun.platform.pay.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.hanyun.platform.pay.adapt.yeepay.model.YeeRefundRes;
import com.hanyun.platform.pay.logic.trade.YbTradeRefundServiceAssistLogic;

/**
 * 
 * @date 2018年8月11日
 * 
 * @apiDefine Common 易宝退款回调controller
 * @author litao@hanyun.com
 */
@Controller
@RequestMapping("/ybrefund")
public class YeeRefundCallbackController {

	 private static final Logger LOGGER = LoggerFactory.getLogger(YeeRefundCallbackController.class);

	 @Resource
	 private YbTradeRefundServiceAssistLogic ybTradeRefundServiceAssistLogic;
	 
	 @RequestMapping(value = "/yee/callback" ,method = {RequestMethod.POST})
	 public void yeeRefundCallback(@RequestBody String bodyData){
		 
		 YeeRefundRes req = null;
		 try {
			 LOGGER.info(" Yb refund req: {}", bodyData);
			 req = JSON.parseObject(bodyData,YeeRefundRes.class);
				
			 ybTradeRefundServiceAssistLogic.doRefundCallback(req);
		} catch (Exception e) {
			LOGGER.error("Yb refund error !", e);
		}
		
	 }
}
