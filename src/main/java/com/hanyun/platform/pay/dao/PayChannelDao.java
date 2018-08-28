package com.hanyun.platform.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.domain.PayChannel;
import com.hanyun.platform.pay.vo.paymode.PayModeRes;

@Repository
public interface PayChannelDao {

    public List<PayChannel> selectChannelList( );
    
    public List<PayModeRes> selectInSrvChannelList( );

    public int updateBychannel(PayChannel PayChannel);
    
    public PayChannel getPayChannelByChannel(String channel);
    
}