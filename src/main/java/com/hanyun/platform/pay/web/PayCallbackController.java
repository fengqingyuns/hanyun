/**
 * 
 */
package com.hanyun.platform.pay.web;

import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanyun.ground.util.xml.XmlUtils;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayResultNotifyReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayResultNotifyRes;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.PayResultNotifyReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.PayResultNotifyRes;
import com.hanyun.platform.pay.service.cib.alipay.CibAlipayCallbackService;
import com.hanyun.platform.pay.service.cib.weixin.CibWeixinCallbackService;

/**
 * 支付面向第三方回调的控制器
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午2:12:16
 */
@Controller
@RequestMapping(value = "/callback")
public class PayCallbackController {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_CONTENTTYPE = "text/html;charset=utf-8";

    private static final Logger LOGGER = LoggerFactory.getLogger(PayCallbackController.class);

    @Resource
    private CibWeixinCallbackService cibWeixinCallbackService;
    @Resource
    private CibAlipayCallbackService cibAlipayCallbackService;

    @RequestMapping(value = "/cib/weixin", method = { RequestMethod.POST })
    public void cibWeixinNotify(@RequestBody String bodyData, HttpServletResponse res) {
        PayResultNotifyRes prnres = null;
        try {
            LOGGER.info("cibweixin callback req orgin: {}", bodyData);
            bodyData = URLDecoder.decode(bodyData, DEFAULT_CHARSET);
            LOGGER.info("cibweixin callback req decode: {}", bodyData);
            
            PayResultNotifyReq prnreq = XmlUtils.unmarshal(PayResultNotifyReq.class, bodyData);
            prnres = cibWeixinCallbackService.dealPayResultCallback(prnreq);
            
        } catch (Exception e) {
            LOGGER.error("cibweixin callback deal error!", e);
            prnres = new PayResultNotifyRes();
            prnres.setReturn_code(CibWeiXinConsts.RETURN_CODE_FAIL);
        }

        try {
            String rststr = XmlUtils.marshal(prnres);
            LOGGER.info("cibweixin callback res: {}", rststr);
            
            res.setContentType(DEFAULT_CONTENTTYPE);
            res.getOutputStream().write(rststr.getBytes(DEFAULT_CHARSET));
        } catch (Exception e) {
            LOGGER.error("cibweixin callback output error!", e);
        }
    }
    
    @RequestMapping(value = "/cib/alipay", method = { RequestMethod.POST })
    public void cibAlipayNotify(@RequestBody String bodyData, HttpServletResponse res) {
        AlipayResultNotifyRes prnres = null;
        try {
            LOGGER.info("cibalipay callback req orgin: {}", bodyData);
            bodyData = URLDecoder.decode(bodyData, DEFAULT_CHARSET);
            LOGGER.info("cibalipay callback req decode: {}", bodyData);
            
            AlipayResultNotifyReq prnreq = XmlUtils.unmarshal(AlipayResultNotifyReq.class, bodyData);
            prnres = cibAlipayCallbackService.dealPayResultCallback(prnreq);
            
        } catch (Exception e) {
            LOGGER.error("cibalipay callback deal error!", e);
            prnres = new AlipayResultNotifyRes();
            prnres.setReturnCode(CibAlipayConsts.RETURN_CODE_FAIL);
        }
        
        try {
            String rststr = XmlUtils.marshal(prnres);
            LOGGER.info("cibalipay callback res: {}", rststr);
            
            res.setContentType(DEFAULT_CONTENTTYPE);
            res.getOutputStream().write(rststr.getBytes(DEFAULT_CHARSET));
        } catch (Exception e) {
            LOGGER.error("cibalipay callback output error!", e);
        }
    }
}
