/**
 * 
 */
package com.hanyun.platform.pay.web;

import java.net.URLDecoder;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.service.umpay.PosPayCallbackService;
import com.hanyun.platform.pay.util.BizResUtil;

/**
 * POS刷卡支付回调
 * @author liyinglong@hanyun.com
 * @date 2017年5月9日 上午11:16:44
 */
@Controller
@RequestMapping(value = "/callback/umpay")
public class PosPayCallbackControoler {
    private static Logger LOGGER = LoggerFactory.getLogger(PosPayCallbackControoler.class);
    private static final String DEFAULT_CHARSET = "UTF-8";
    
    @Resource
    private PosPayCallbackService posPayCallbackService;
    
    @RequestMapping(value = "/wpos", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<String> wposNotify(@RequestBody String bodyData){
        try {
            LOGGER.info("wpos callback req origin: {}", bodyData);
            bodyData = URLDecoder.decode(bodyData, DEFAULT_CHARSET);
            LOGGER.info("wpos callback req decode: {}", bodyData);
            
            posPayCallbackService.dealPayResultCallback(bodyData);
            
        } catch (Exception e) {
            LOGGER.error("wpos callback deal error", e);
            return BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
        
        return BizResUtil.succ("SUCCESS");
    }
}
