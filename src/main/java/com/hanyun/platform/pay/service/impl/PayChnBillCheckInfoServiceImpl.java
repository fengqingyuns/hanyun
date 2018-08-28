package com.hanyun.platform.pay.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.PayModeConsts;
import com.hanyun.platform.pay.dao.PayChnBillCheckInfoDao;
import com.hanyun.platform.pay.domain.PayChnBillCheckInfo;
import com.hanyun.platform.pay.service.PayChnBillCheckInfoService;
import com.hanyun.platform.pay.vo.paymode.PayChnBillReq;

/** 
* @Description: 查询支付通道对账信息表
* @author wangjie@hanyun.com
* @date 2017年1月10日 上午11:41:10
*/
@Service
public class PayChnBillCheckInfoServiceImpl implements PayChnBillCheckInfoService {
	private static Logger LOGGER=LoggerFactory.getLogger(PayChnBillCheckInfoServiceImpl.class);
	@Resource
	private PayChnBillCheckInfoDao payChnBillCheckInfoDao;

	/**
	* Title: checkPayChnBillInfo
	* Description: 通过账单类别来查询基本信息 
	* @param billClass
	* @return 
	*/
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean checkPayChnBillInfo(String channel,String billClass) {
		PayChnBillCheckInfo record = new PayChnBillCheckInfo();
		record.setChannel(channel);
		record.setBillClass(billClass);
		PayChnBillCheckInfo  payChnBillCheckInfo= payChnBillCheckInfoDao.select(record);
		if(payChnBillCheckInfo == null){
			return false;
		}else{
			int status = payChnBillCheckInfo.getAvailStatus();//判断通过账单类别来查询基本信息 状态
			if(status == 0){
				return true;
			}else{
				return false;
			}
		}
	}

	/**
	* Title: updatePayChnBillInfo
	*  Description: 更新 对账信息
	* @param channel
	* @param billClass
	* @return 
	*/
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean updatePayChnBillInfo(PayChnBillCheckInfo payChnBillCheckInfo) {
		int count = payChnBillCheckInfoDao.updateSelective(payChnBillCheckInfo);
		if(count>0){
			return true;	
		}else{
			return false;
		}
		
	}

	/**
	* Title: getPayChnBillCheckInfo
	* Description: 获取所有的支付通道对账信息表
	* @return 
	*/
	@Override
	public List<PayChnBillCheckInfo> getPayChnBillCheckInfo() {
		List<PayChnBillCheckInfo> payChnBillCheckInfoList = payChnBillCheckInfoDao.getPayChnBillCheckInfo();
		return payChnBillCheckInfoList;
	}

	/**
	* Title: payChnBillUpdateStataus
	* Description: 
	* @param payChnBillReq
	* @return 
	*/
	@Override
	public HttpResponse<Object> payChnBillUpdateStataus(PayChnBillReq payChnBillReq) {
		//判断状态
		HttpResponse<Object> httpResponse = null;
		PayChnBillCheckInfo payChnBillCheckInfo = new PayChnBillCheckInfo(); 
		if(PayModeConsts.PAY_CHN_BILL_STATUS_ON.equals(payChnBillReq.getAvailStatus())){//如果是可用的，则改变为不可用的
			payChnBillCheckInfo.setBillClass(payChnBillReq.getBillClass());
			payChnBillCheckInfo.setAvailStatus(PayModeConsts.PAY_CHN_BILL_STATUS_OFF);
		    payChnBillCheckInfoDao.payChnBillUpdateStataus(payChnBillCheckInfo);
		}else{//如果是不可用的，则改变为可用的
			payChnBillCheckInfo.setBillClass(payChnBillReq.getBillClass());
			payChnBillCheckInfo.setAvailStatus(PayModeConsts.PAY_CHN_BILL_STATUS_ON);
		    payChnBillCheckInfoDao.payChnBillUpdateStataus(payChnBillCheckInfo);
		}
		return httpResponse;
	}
	
}
