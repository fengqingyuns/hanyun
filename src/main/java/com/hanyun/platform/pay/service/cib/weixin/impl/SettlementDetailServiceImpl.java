package com.hanyun.platform.pay.service.cib.weixin.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.hanyun.platform.pay.dao.SettlementDetailDao;
import com.hanyun.platform.pay.domain.SettlementDetail;
import com.hanyun.platform.pay.domain.SettlementDetailReq;
import com.hanyun.platform.pay.service.cib.weixin.SettlementDetailService;


@Service
public class SettlementDetailServiceImpl implements SettlementDetailService {
	@Resource
	private SettlementDetailDao settlementDetailDao;

	/**
	* Title: getSettlementDetailByReq
	* Description: 
	* @param settlementDetailReq
	* @return 
	*/
	@Override
	public SettlementDetail getSettlementDetailByReq(SettlementDetailReq settlementDetailReq) {
		// TODO Auto-generated method stub
		return settlementDetailDao.getSettlementDetailByReq(settlementDetailReq);
	}
}
