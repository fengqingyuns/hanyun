package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.CibAlipayOrder;

import org.springframework.stereotype.Repository;

@Repository
public interface CibAlipayOrderDao {

    public int insert(CibAlipayOrder record);

    public CibAlipayOrder selectByOutTradeNo(String outTradeNo);

    public int updateByOutTradeNo(CibAlipayOrder record);
}