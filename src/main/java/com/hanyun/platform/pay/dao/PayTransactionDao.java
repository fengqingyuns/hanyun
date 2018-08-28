package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.PayTransaction;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionDao {

    public int insert(PayTransaction record);

    public PayTransaction selectByTransId(String transId);
    
    public List<PayTransaction> selectPayByPayId(String payId);
    
    public PayTransaction selectPayByPayIdAndPayType(PayTransaction ptparam);
    
    public List<PayTransaction> selectRefProcessByPayIdAndPayType(PayTransaction ptparam);

    public int updateStatusByTransId(PayTransaction record);
    
    public int updatePayTransRefundInfoByTransId(PayTransaction record);
    
    public int updatePayTypeInfoByTransId(PayTransaction record);
    
}