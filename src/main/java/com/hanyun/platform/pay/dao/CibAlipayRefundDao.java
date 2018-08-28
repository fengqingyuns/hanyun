package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.CibAlipayRefund;

import org.springframework.stereotype.Repository;

@Repository
public interface CibAlipayRefundDao {

    public int insert(CibAlipayRefund record);

    public CibAlipayRefund selectByOutRefundNo(String outRefundNo);

    public int updateByOutRefundNo(CibAlipayRefund record);
}