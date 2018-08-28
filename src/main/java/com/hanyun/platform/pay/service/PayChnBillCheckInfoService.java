package com.hanyun.platform.pay.service;

import java.util.List;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.domain.PayChnBillCheckInfo;
import com.hanyun.platform.pay.vo.paymode.PayChnBillReq;

/** 
* @Description: 查询支付通道对账信息表
* @author wangjie@hanyun.com
* @date 2017年1月9日 下午5:47:36
*/
public interface PayChnBillCheckInfoService {
	/**
	 * 
	* @Title: checkPayChnBillInfo 
	* @Description: 通过账单类别来查询基本信息 
	* @param  
	* @return boolean   
	* @throws
	 */
	public boolean checkPayChnBillInfo(String channel,String billClass);

	
	/**
	 * 
	* @Title: checkPayChnBillInfo 
	* @Description: 通过账单类别来查询基本信息 
	* @param  
	* @return boolean   
	* @throws
	 */
	public boolean updatePayChnBillInfo(PayChnBillCheckInfo payChnBillCheckInfo);
	
	/**
	 * 
	* @Title: getPayChnBillCheckInfo 
	* @Description: 获取所有的 支付通道对账信息表
	* @param  
	* @return List<PayChnBillCheckInfo>   
	* @throws
	 */
	public List<PayChnBillCheckInfo> getPayChnBillCheckInfo();
	
	/**
	 * 
	* @Title: payChnBillUpdateStataus 
	* @Description: 改变支付通道对账信息的可用性 
	* @param  
	* @return HttpResponse<Object>   
	* @throws
	 */
	public HttpResponse<Object> payChnBillUpdateStataus(PayChnBillReq payChnBillReq); 
}
