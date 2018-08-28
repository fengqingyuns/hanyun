package com.hanyun.platform.pay.service;

/** 
* @Description: 分布式锁
* @author wangjie@hanyun.com
* @date 2016年9月6日 上午11:42:37
*/
public interface DistributeLockService {
	
	/**
	 * 
	* @Title: receiveDistributeLock 
	* @Description: 获取分布式锁 
	* @param  
	* @return boolean   
	* @throws
	 */
	public boolean receiveDistributeLock(String businesskey,String operator,String ip,int effectiveTime);
	
	
	/**
	 * 
	* @Title: freedDistributeLock 
	* @Description: 释放分布式锁 
	* @param  
	* @return boolean   
	* @throws
	 */
	public boolean freedDistributeLock(String business_key,String operator);
	

}
