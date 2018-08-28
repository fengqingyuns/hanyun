package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.PayChnBillCheckInfo;
import com.hanyun.platform.pay.vo.paymode.PayChnBillReq;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PayChnBillCheckInfoDao {

    public int insertSelective(PayChnBillCheckInfo record);

    public PayChnBillCheckInfo select(PayChnBillCheckInfo record);

    public int updateSelective(PayChnBillCheckInfo record);
    
    public List<PayChnBillCheckInfo> getPayChnBillCheckInfo();
    
    public int payChnBillUpdateStataus(PayChnBillCheckInfo record);
}