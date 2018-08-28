package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.Payment;

import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao {

    public int insert(Payment record);

    public Payment selectByOrderId(String orderId);
    
    public Payment selectByPayId(String payId);

    public int updatePayByPayId(Payment record);
    
    public int updateRefundByPayId(Payment record);
}