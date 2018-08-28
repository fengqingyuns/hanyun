package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.SettleInfo;

import org.springframework.stereotype.Repository;

@Repository
public interface SettleInfoDao {


    public int insert(SettleInfo record);

}