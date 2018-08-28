package com.hanyun.platform.pay.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.hanyun.platform.pay.adapt.yeepay.protocol.YeeResultNotifyRes;
import com.hanyun.platform.pay.adapt.yeepay.util.UsualTools;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.hanyun.platform.pay.service.ybpay.alipay.YbAlipayCallbackService;
import com.hanyun.platform.pay.service.ybpay.weixin.YbWeixinCallbackService;
import com.yeepay.g3.sdk.yop.client.YopClient;

import java.net.URLDecoder;

/**
 * @author litao@hanyun.com
 * @date 2018年8月8日
 * @apiDefine Common 支付回调
 */
@Controller
@RequestMapping(value = "/yeecallback")
public class YbPayCallbackController {
	 private static final String DEFAULT_CHARSET = "UTF-8";
	 private static final String DEFAULT_CONTENTTYPE = "text/html;charset=utf-8";

	 private static final Logger LOGGER = LoggerFactory.getLogger(YbPayCallbackController.class);
	 
	 @Resource
	 private YbAlipayCallbackService ybAlipayCallbackService;
	 
	 @Resource
	 private YbWeixinCallbackService ybWeixinCallbackService;

	
	 
	 @RequestMapping(value = "/yee/alipay/{key}", method = { RequestMethod.POST })
	 public void yeeAlipayNotify(@PathVariable("key") String key,@RequestParam("response") String response,HttpServletResponse res){
		 
		 YeeResultNotifyRes req = null;
		 try {
			  LOGGER.info("yeealipay callback req orgin: {}", response);
             String secretKey = (String) YeepayChildChannelRegGlobal.getChildChannel(key).get("appKey");
             LOGGER.info("========>>>>>   yeealipay callback secretKey: {}", secretKey);
			 String resObject = YopClient.acceptNotificationAsJson(secretKey,response);
             LOGGER.info("========>>>>>   yeealipay callback resObject: {}", resObject);
			  req = JSON.parseObject(resObject, YeeResultNotifyRes.class);
			  LOGGER.info("yeealipay callback req jsonToObject: {}", req);
			 //处理支付结果回调
			 ybAlipayCallbackService.dealPayResultCallback(req);
			 
		} catch (Exception e) {
			 LOGGER.error("yeealipay callback deal error!", e);
		}
		 try {
			 LOGGER.info("yeealipay callback res: {}", req);
			 res.getOutputStream().write("SUCCESS".getBytes());
		} catch (Exception e) {
			  LOGGER.error("=======>  yeealipay callback output error!", e);
		}
		
	 }

	 @RequestMapping(value = "/yee/weixin/{key}", method = { RequestMethod.POST })
	 public void yeeWeixinNotify(@PathVariable("key") String key, @RequestParam("response") String response, HttpServletResponse res){
		 
		 YeeResultNotifyRes req = null;
		 try {
			  LOGGER.info("yeeweixin callback req orgin: {}", response);
			  try {
				  String secretKey = (String) YeepayChildChannelRegGlobal.getChildChannel(key).get("appKey");
				  LOGGER.info("========>>>>>   yeeweixin callback secretKey: {}", secretKey);
				  String resObject = YopClient.acceptNotificationAsJson(secretKey,response);
				  LOGGER.info("========>>>>>   yeeweixin callback resObject: {}", resObject);
				  req = JSON.parseObject(resObject, YeeResultNotifyRes.class);
			} catch (Exception e) {
				 LOGGER.error("yeeweixin callback key error!", "解密失败");
			}
			  //处理支付结果回调
			  ybWeixinCallbackService.dealPayResultCallback(req);
			 
		} catch (Exception e) {
			 LOGGER.error("yeeweixin callback deal error!", e);
		}
		try {
			LOGGER.info("yeeweixinpay callback res: {}", req);
			 res.getOutputStream().write("SUCCESS".getBytes());
		} catch (Exception e) {
			  LOGGER.error("=====>  yeeweixinpay callback output error!", e);
		}
	 }

}
