package com.hanyun.platform.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.adapt.yeepay.YeepaySecretKeyConfig;

/**
 * 
 * @date 2018年8月8日
 * 
 * @apiDefine Common 支付秘钥dao
 * @author litao@hanyun.com
 */
@Repository
public interface YeepaySecretKeyConfigDao {

	public List<YeepaySecretKeyConfig> getYeepaySecretKeyConfig();

}
