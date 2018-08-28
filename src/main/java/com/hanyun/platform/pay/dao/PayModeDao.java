package com.hanyun.platform.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.domain.PayMode;
import com.hanyun.platform.pay.vo.paymode.PayModeReq;
import com.hanyun.platform.pay.vo.paymode.PayModeRes;

@Repository
public interface PayModeDao {
    
	public List<PayModeRes> selectModList();
    
	public PayMode getPayModeByPayType(String payType);
        
	public int updateAvailStatusByMode(PayModeReq payModeReq);

	public int updateByMode(PayModeReq payModeReq);
}