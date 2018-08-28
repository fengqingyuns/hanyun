package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.PayChnBillCheckHis;
import com.hanyun.platform.pay.vo.paymode.PayChnBillHisReq;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PayChnBillCheckHisDao {

    public int insertSelective(PayChnBillCheckHis record);

    public PayChnBillCheckHis select(PayChnBillCheckHis record);

    public int updateSelective(PayChnBillCheckHis record);

    public int updateDownloadBillSelective(PayChnBillCheckHis record);

    public int updateCheckBillSelective(PayChnBillCheckHis record);
    
    public List<PayChnBillCheckHis> getPayChnBillCheckHis(PayChnBillHisReq payChnBillHisReq);
    
    public int getPayChnBillCheckHisCount(PayChnBillHisReq payChnBillHisReq);
    
}