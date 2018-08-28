package com.hanyun.platform.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.domain.PayChnMode;
import com.hanyun.platform.pay.vo.paymode.PayChannelDetailRes;
import com.hanyun.platform.pay.vo.paymode.PayChnModeReq;
import com.hanyun.platform.pay.vo.paymode.PayChannelDetailReq;
import com.hanyun.platform.pay.vo.paymode.PayModeDetailReq;
import com.hanyun.platform.pay.vo.paymode.PayModeDetailRes;

@Repository
public interface PayChnModeDao {

    public int updateBychannel(PayChnMode payChnMode);

    public int updateByMode(PayChnMode payChnMode);
    
    public List<PayChannelDetailRes> selectPayChannelDetailByChannel(PayChannelDetailReq payChannelDetailTReq);
    
    public List<PayModeDetailRes> selectPayModeDetailByMode(PayModeDetailReq payModeDetailReq);
   
    public int offlineAllByPayType(String payType);

    public int updateOneServStatus(PayChnModeReq payChnModeReq);

    public int updateOneFeeRate(PayChnModeReq payChnModeReq);
    
    
    
}