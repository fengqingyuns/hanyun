package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.PayTransactionNew;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PayTransactionNewDao {

    public int insert(PayTransactionNew record);

    public PayTransactionNew selectByTransId(String transId);
    
    public List<PayTransactionNew> selectPayByPayId(String payId);
    
    public PayTransactionNew selectPayByPayIdAndPayType(PayTransactionNew ptparam);
    
    public List<PayTransactionNew> selectRefProcessByPayIdAndPayType(PayTransactionNew ptparam);

    public int updateStatusByTransId(PayTransactionNew record);
    
    public int updatePayTransRefundInfoByTransId(PayTransactionNew record);
    
    public int updatePayTypeInfoByTransId(PayTransactionNew record);
    
}