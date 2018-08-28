/**
 * 
 */
package com.hanyun.platform.pay.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.domain.PayChannel;
import com.hanyun.platform.pay.domain.PayMchMode;
import com.hanyun.platform.pay.domain.PayMode;
import com.hanyun.platform.pay.service.PayModeManageService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.paymode.PayChannelDetailRes;
import com.hanyun.platform.pay.vo.paymode.PayChannelReq;
import com.hanyun.platform.pay.vo.paymode.PayChnModeReq;
import com.hanyun.platform.pay.vo.paymode.PayChannelDetailReq;
import com.hanyun.platform.pay.vo.paymode.PayMchModeReq;
import com.hanyun.platform.pay.vo.paymode.PayModeDetailReq;
import com.hanyun.platform.pay.vo.paymode.PayModeDetailRes;
import com.hanyun.platform.pay.vo.paymode.PayModeReq;
import com.hanyun.platform.pay.vo.paymode.PayModeRes;

/**
 * 支付通道和支付方式的控制器
 * @author wangximin@hanyun.com
 * 
 * @date 2016年8月5日 下午3:44:06
 *
 */
@Controller
@RequestMapping(value = "/paymode")
public class PayModeManageController {
    @Resource
	private PayModeManageService payModeManageService;
	/**
	 * 
	 * 功能: 查询通道列表 
	 * 时间: 2016年8月5日
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/channellist", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<List<PayChannel>> selectChannelList(){
	    
		HttpResponse<List<PayChannel>> httpResponse = payModeManageService.selectChannelList();

		return httpResponse;
	}
	/**
	 * 
	 * 功能: 通道状态操作 
	 * 时间: 2016年8月7日16:45:00
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/updatechannelstatus", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<Object>  channelStatus(@RequestBody PayChannelReq payChannelReq){
	    
	    HttpResponse<Object>  httpResponse = null;
	    //判断传入的参数是否正确
	    if(StringUtils.isNotBlank(payChannelReq.getChannel()) && 
	            payChannelReq.getAvailStatus()!=null){
    		//更新通道的状态
    		httpResponse = payModeManageService.updateChannelStatusByChannel(payChannelReq);
	    }else{
	        httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
	    }
		return httpResponse;
	}

	/**
	 * 
	 * 功能: 通过Channel查询Channel详情 
	 * 时间: 2016年8月10日
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/getchannel", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<PayChannel> getPayChannelByChannel(@RequestBody PayChannelDetailReq payChannelDetailTReq){
	    
	    HttpResponse<PayChannel> httpResponse = null ;
	    //判断参数是否正确
	    if(StringUtils.isNotBlank(payChannelDetailTReq.getChannel())){
	        httpResponse = payModeManageService.getPayChannelByChannel(payChannelDetailTReq);
	    }else{
            httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
        }
		return httpResponse;
	}

	/**
	 * 
	 * 功能: 查询通道详情 
	 * 时间: 2016年8月6日
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/channeldetail", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<List<PayChannelDetailRes>> selectChannelDetailByChannle(
	        @RequestBody PayChannelDetailReq payChannelDetailTReq){
	    HttpResponse<List<PayChannelDetailRes>> httpResponse = null ;
        //判断参数是否正确
        if(StringUtils.isNotBlank(payChannelDetailTReq.getChannel())){
            //当前通道的详情
            httpResponse = payModeManageService.selectPayChannelDetailByChannel(payChannelDetailTReq);
        }else{
            httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
        }

		return httpResponse;
	}


	/**
	 * 
	 * 功能: 查询支付方式列表 
	 * 时间: 2016年8月5日
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/modelist", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<List<PayModeRes>> selectPayModeList(){
	    
		HttpResponse<List<PayModeRes>> httpResponse = payModeManageService.selectModList();

		return httpResponse;
	}

	/**
	 * 
	 * 功能: 通过payType查询payMode详情
	 * 时间: 2016年8月10日
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/getpaymode", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<PayMode> getpayModeBypayType(@RequestBody PayModeDetailReq payModeDetailReq){
	    HttpResponse<PayMode> httpResponse = null ;
        //判断参数是否正确
        if(StringUtils.isNotBlank(payModeDetailReq.getPayType())){
            //当前通道的详情
            httpResponse = payModeManageService.getPayModeByPayType(payModeDetailReq);
        }else{
            httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
        }
		return httpResponse;
	}

	/**
	 * 
	 * 功能: 查询支付方式详情 
	 * 时间: 2016年8月6日
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/modedetail", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<List<PayModeDetailRes>> selectModeDetail(@RequestBody PayModeDetailReq payModeDetailReq){
	    HttpResponse<List<PayModeDetailRes>> httpResponse = null ;
        //判断参数是否正确
        if(StringUtils.isNotBlank(payModeDetailReq.getPayType())){
            //当前通道的详情
            httpResponse = payModeManageService.selectPayModeDetailByMode(payModeDetailReq);
        }else{
            httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
        }
		 
		return httpResponse;
	}
	/**
	 * 
	 * 功能: 支付方式状态操作 
	 * 时间: 2016年8月7日16:45:00
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/updatemodestatus", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<Object> modeStatus(@RequestBody PayModeReq payModeReq){
	    HttpResponse<Object>  httpResponse = null;
        //判断传入的参数是否正确
        if(StringUtils.isNotBlank(payModeReq.getPayType()) && 
                payModeReq.getAvailStatus()!=null){
            //更改支付方式状态
            httpResponse = payModeManageService.updateModeStatusByMode(payModeReq);
        }else{
            httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
        }

		return httpResponse;
	}

	/**
	 * 
	 * 功能: 更改服务状态
	 * 时间: 2016年8月9日
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/updateservstatus", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<Object> servStatusChange(@RequestBody PayChnModeReq payChnModeReq){
	    HttpResponse<Object> httpResponse = null;
	    //判断传入的参数是否正确
	    if(StringUtils.isNotBlank(payChnModeReq.getChannel()) && 
            StringUtils.isNotBlank(payChnModeReq.getPayType()) && 
            payChnModeReq.getSrvStatus()!=null &&
                    payChnModeReq.getAvailStatus()!=null){
		//修改中间表状态	
		httpResponse = payModeManageService.updateChnModeServStatus(payChnModeReq);
	    }else{
            httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
        }
		return httpResponse;
	}
	/**
	 * 
	 * 功能: 更改支付通道费率
	 * 时间: 2016年8月10日
	 * 作者: wangximin@hanyun.com
	 */
	@RequestMapping(value="/updatefeerate", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse<Object> updateFeeRate(@RequestBody PayChnModeReq payChnModeReq){
	    
	    HttpResponse<Object>   httpResponse = null;
        //判断传入的参数是否正确
        if(StringUtils.isNotBlank(payChnModeReq.getChannel()) &&  
            StringUtils.isNotBlank(payChnModeReq.getPayType()) ){
            //修改中间表状态	
            httpResponse = payModeManageService.updateChnModeFeeRate(payChnModeReq);
        }else{
            httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
        }
		return  httpResponse ;
	}
	
	
	
	/**
     * 
     * 功能: 通过品牌id获得商户的费率
     * 时间: 2016年8月10日
     * 作者: wangximin@hanyun.com
     */
    @RequestMapping(value="/getPayMchModeByBrandId", method = {RequestMethod.POST})
    @ResponseBody
    public HttpResponse<List<PayMchMode>> getPayMchModeByBrandId(@RequestBody PayMchModeReq payMchModeReq){
        HttpResponse<List<PayMchMode>>   httpResponse = null;
        
        httpResponse = payModeManageService.getPayMchModeByBrandId(payMchModeReq);
        return  httpResponse ;
    }
    
    /**
     * 
     * 功能: 通过品牌id和支付方式修改费率
     * 时间: 2016年8月10日
     * 作者: wangximin@hanyun.com
     */
    @RequestMapping(value="/updatePayMchMode", method = {RequestMethod.POST})
    @ResponseBody
    public HttpResponse<Object> updatePayMchMode(@RequestBody PayMchModeReq payMchModeReq){
        HttpResponse<Object>   httpResponse = null;
        
        httpResponse = payModeManageService.updatePayMchMode( payMchModeReq);
        
        return  httpResponse ;
    }
   /**
    * 
    * 功能:更改商户支付使用状态  
    * 时间: 2016年8月21日
    * 作者: wangximin@hanyun.com
    */
    @RequestMapping(value="/updatemchavailstatus", method = {RequestMethod.POST})
    @ResponseBody
    public HttpResponse<Object> updatemchavailstatus(@RequestBody PayMchModeReq payMchModeReq){
        HttpResponse<Object> httpResponse = null;
        //判断传入的参数是否正确
        if(StringUtils.isNotBlank(payMchModeReq.getPayType()) && 
            payMchModeReq.getAvailStatus()!=null){
        //修改中间表状态   
        httpResponse = payModeManageService.updatemchavailstatus(payMchModeReq);
        
        }else{
            httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
        }
        return httpResponse;
    }
     
    /**
     * 
     * 功能:更改商户支付使用状态  
     * 时间: 2016年8月21日
     * 作者: wangximin@hanyun.com
     */
     @RequestMapping(value="/updatepaymode", method = {RequestMethod.POST})
     @ResponseBody
     public HttpResponse<Object> updatePayMode(@RequestBody PayModeReq payModeReq){
         HttpResponse<Object> httpResponse = null;
         //判断传入的参数是否正确
         if(StringUtils.isNotBlank(payModeReq.getPayType()) ){
         //修改中间表状态   
         httpResponse = payModeManageService.updatePayMode(payModeReq);
         
         }else{
             httpResponse =BizResUtil.fail(BizResCode.PARAMERROR);
         }
         return httpResponse;
     }
    
}
