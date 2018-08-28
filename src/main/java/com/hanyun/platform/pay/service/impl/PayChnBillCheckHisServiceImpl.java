package com.hanyun.platform.pay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.dao.PayChnBillCheckHisDao;
import com.hanyun.platform.pay.domain.PayChnBillCheckHis;
import com.hanyun.platform.pay.service.PayChnBillCheckHisService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.base.PageResData;
import com.hanyun.platform.pay.vo.paymode.PayChnBillHisReq;

/** 
* @Description: 支付通道对账检查表记录
* @author wangjie@hanyun.com
* @date 2017年1月10日 下午12:02:40
*/
@Service("PayChnBillCheckHisService")
public class PayChnBillCheckHisServiceImpl implements PayChnBillCheckHisService {
	private static Logger LOGGER=LoggerFactory.getLogger(PayChnBillCheckHisServiceImpl.class);
	@Resource
	private PayChnBillCheckHisDao payChnBillCheckHisDao; 

	/**
	* Title: insertPayChnBillCheckHis
	* Description: 新增支付通道对账检查记录
	* @param payChnBillCheckHis
	* @return 
	*/
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean insertPayChnBillCheckHis(PayChnBillCheckHis payChnBillCheckHis) {
		int count  = payChnBillCheckHisDao.insertSelective(payChnBillCheckHis);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	* Title: updatePayChnBillCheckHis
	* Description: 修改支付通道对账检查记录
	* @param payChnBillCheckHis
	* @return 
	*/
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean updatePayChnBillCheckHis(PayChnBillCheckHis payChnBillCheckHis) {
		int count = payChnBillCheckHisDao.updateSelective(payChnBillCheckHis);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	* Title: selecPayChnBillCheckHis
	* Description: 查询支付通道对账检查记录
	* @param payChnBillCheckHis
	* @return 
	*/
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public PayChnBillCheckHis selectPayChnBillCheckHis(PayChnBillCheckHis payChnBillCheckHis) {
		PayChnBillCheckHis payChnBillCheckHisRecord = payChnBillCheckHisDao.select(payChnBillCheckHis);
		return payChnBillCheckHisRecord;
	}

	/**
	* Title: updateDownloadBillSelective
	* Description: 更新下载对账文件更新记录
	* @param payChnBillCheckHis
	* @return 
	*/
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean updateDownloadBillSelective(PayChnBillCheckHis payChnBillCheckHis) {
		int count = payChnBillCheckHisDao.updateDownloadBillSelective(payChnBillCheckHis);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	* Title: updateCheckBillSelective
	* Description: 更新对账文件更新记录
	* @param payChnBillCheckHis
	* @return 
	*/
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean updateCheckBillSelective(PayChnBillCheckHis payChnBillCheckHis) {
		int count = payChnBillCheckHisDao.updateCheckBillSelective(payChnBillCheckHis);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	* Title: getPayChnBillCheckHis
	* Description: 查询所有的记录 
	* @return 
	*/
	@Override
	public HttpResponse<PageResData> getPayChnBillCheckHis(PayChnBillHisReq payChnBillHisReq) {
		 HttpResponse<PageResData> httpResponse = null;
         List<PayChnBillCheckHis> payChnBillCheckHisList = payChnBillCheckHisDao.getPayChnBillCheckHis(payChnBillHisReq);
         int count  = payChnBillCheckHisDao.getPayChnBillCheckHisCount(payChnBillHisReq);
         httpResponse = BizResUtil.succPageList(count, payChnBillCheckHisList);
         return httpResponse;
	}


}
