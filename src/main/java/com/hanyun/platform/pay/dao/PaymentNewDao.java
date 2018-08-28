package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.PaymentNew;

import org.springframework.stereotype.Repository;

@Repository
public interface PaymentNewDao {

    public int insert(PaymentNew record);

    public PaymentNew selectByOrderId(String orderId);
    
    public PaymentNew selectByPayId(String payId);

    public int updatePayByPayId(PaymentNew record);
    
    public int updateRefundByPayId(PaymentNew record);
}