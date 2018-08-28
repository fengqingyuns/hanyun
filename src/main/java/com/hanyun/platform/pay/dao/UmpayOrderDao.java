package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.UmpayOrder;

import org.springframework.stereotype.Repository;

@Repository
public interface UmpayOrderDao {

    public int insertSelective(UmpayOrder record);

    public UmpayOrder selectByTransId(String transId);
    
    public UmpayOrder selectByOutTradeNo(String outTradeNo);

    public int updateByTransIdSelective(UmpayOrder record);
    
    public UmpayOrder selectBySettleDateAndRefNo(UmpayOrder record);
}