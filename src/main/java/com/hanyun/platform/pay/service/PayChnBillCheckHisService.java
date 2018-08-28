package com.hanyun.platform.pay.service;

import java.util.List;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.domain.PayChnBillCheckHis;
import com.hanyun.platform.pay.vo.base.PageResData;
import com.hanyun.platform.pay.vo.paymode.PayChnBillHisReq;

/** 
* @Description: 支付通道对账检查表记录
* @author wangjie@hanyun.com
* @date 2017年1月9日 下午5:47:36
*/
public interface PayChnBillCheckHisService {
	/**
	 * 
	* @Title: insertPayChnBillCheckHis 
	* @Description: 新增支付通道对账检查记录
	* @param  
	* @return boolean   
	* @throws
	 */
	public boolean insertPayChnBillCheckHis(PayChnBillCheckHis payChnBillCheckHis);
	
	/**
	 * 
	* @Title: updatePayChnBillCheckHis 
	* @Description:修改支付通道对账检查记录
	* @param  
	* @return boolean   
	* @throws
	 */
	public boolean updatePayChnBillCheckHis(PayChnBillCheckHis payChnBillCheckHis);
	
	/**
	 * 
	* @Title: selectPayChnBillCheckHis 
	* @Description: 查询是否存在记录 
	* @param  
	* @return boolean   
	* @throws
	 */
	public PayChnBillCheckHis selectPayChnBillCheckHis(PayChnBillCheckHis payChnBillCheckHis);

	/**
	 * 
	* @Title: updateDownloadBillSelective 
	* @Description:更新下载对账文件更新记录
	* @param  
	* @return boolean   
	* @throws
	 */
	public boolean updateDownloadBillSelective(PayChnBillCheckHis payChnBillCheckHis);
	
	/**
	 * 
	* @Title: updateCheckBillSelective 
	* @Description:更新对账文件更新记录
	* @param  
	* @return boolean   
	* @throws
	 */
	public boolean updateCheckBillSelective(PayChnBillCheckHis payChnBillCheckHis);
	
	/**
	 * 
	* @Title: getPayChnBillCheckHis 
	* @Description: 查询所有的记录 
	* @param  
	* @return List<PayChnBillCheckHis>   
	* @throws
	 */
	public HttpResponse<PageResData> getPayChnBillCheckHis(PayChnBillHisReq payChnBillHisReq);
	
}
