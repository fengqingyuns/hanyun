package com.hanyun.platform.pay.web;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.domain.PayChnBillCheckInfo;
import com.hanyun.platform.pay.service.PayChnBillCheckHisService;
import com.hanyun.platform.pay.service.PayChnBillCheckInfoService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.base.PageResData;
import com.hanyun.platform.pay.vo.paymode.PayChnBillHisReq;
import com.hanyun.platform.pay.vo.paymode.PayChnBillReq;
/** 
* @Description: 支付通道对账信息
* @author wangjie@hanyun.com
* @date 2017年1月12日 下午8:57:33
*/
@Controller
@RequestMapping("/paychnbill")
public class PayChnBillCheckController {
    private static Logger LOGGER = LoggerFactory.getLogger(PayChnBillCheckController.class);
    @Resource
    private PayChnBillCheckInfoService payChnBillCheckInfoService;
    @Resource
    private PayChnBillCheckHisService payChnBillCheckHisService;

    /**
     * 
    * @Title: getPayChnBillCheckInfo 
    * @Description: 查询支付通道对账信息 
    * @param  
    * @return 
    * @throws
     */
    @RequestMapping(value="/paychnbillcheckinfo", method = {RequestMethod.POST} )
    @ResponseBody
	public HttpResponse<Object> getPayChnBillCheckInfo(){
        HttpResponse<Object>  httpResponse = null;
    	try {
			List<PayChnBillCheckInfo>  payChnBillCheckInfoList = payChnBillCheckInfoService.getPayChnBillCheckInfo();  
            httpResponse = BizResUtil.succ(payChnBillCheckInfoList);
		} catch (Exception e) {
			LOGGER.error("查询支付通道对账信息",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}
		return httpResponse;
	}
    
    /**
     * 
    * @Title: payChnBillUpdateStataus 
    * @Description: 更新支付通道对账信息状态 
    * @param  
    * @return HttpResponse<Object>   
    * @throws
     */
    @RequestMapping(value="/paychnbillupdatestataus", method = {RequestMethod.POST} )
    @ResponseBody    
    public HttpResponse<Object> payChnBillUpdateStataus(@RequestBody PayChnBillReq payChnBillReq){
    	HttpResponse<Object> httpResponse = null;
    	//判断传入的参数不为空
    	if(StringUtils.isNotBlank(payChnBillReq.getBillClass()) &&
    			payChnBillReq.getAvailStatus() !=null){
    		//更新通道状态
    		httpResponse = payChnBillCheckInfoService.payChnBillUpdateStataus(payChnBillReq);
    		
    	}else{
    		httpResponse = BizResUtil.fail(BizResCode.PARAMERROR);
    	}
    	return httpResponse;
    }
    
    /**
     * 
    * @Title: payChnBillCheckHis 
    * @Description: 查询支付通道信息历史信息 
    * @param  
    * @return HttpResponse<Object>   
    * @throws
     */
    @RequestMapping(value="/paychnbillcheckhis", method = {RequestMethod.POST} )
    @ResponseBody
	public HttpResponse<PageResData> payChnBillCheckHis(@RequestBody PayChnBillHisReq payChnBillHisReq){
        HttpResponse<PageResData> httpResponse = payChnBillCheckHisService.getPayChnBillCheckHis(payChnBillHisReq);  
		return httpResponse;
	}
       
}
