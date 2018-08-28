/**
 * 
 */
package com.hanyun.platform.bill.service.test;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanyun.ground.util.network.LocalHost;
import com.hanyun.platform.pay.service.DistributeLockService;

/**
 * 
* @Description: 获取锁测试
* @author wangjie@hanyun.com
* @date 2016年9月6日 下午9:48:48
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class DistributeLockTest {
    @Resource
    private DistributeLockService distributeLockService;
    
    private String operator = UUID.randomUUID().toString();//抢占者标示
    private String ip = LocalHost.getLocalIP();//服务器IP
    private String  business_key = "payment.weixinstatementGenerate";//业务标示
    private int effectiveTime = 3;
    /**
     * 
    * @Title: testDistributeLock
    * @Description: 获取分布式锁 
    * @param  
    * @return void   
    * @throws
     */
    @Test
    public void testDistributeLock(){
    	boolean	getFlag = distributeLockService.receiveDistributeLock(business_key,operator,ip,effectiveTime);
        Assert.assertNotNull(getFlag);
    	boolean freeFlag =distributeLockService.freedDistributeLock(business_key,operator);
    	Assert.assertNotNull(freeFlag);
    }
    
/*    
    *//**
     * 
    * @Title: testDistributeLockHis 
    * @Description: 释放分布式锁 
    * @param  
    * @return void   
    * @throws
     *//*
    @Test
    public void testDistributeLockHis(){
    	boolean flag =distributeLockService.freedDistributeLock(business_key,operator,ip);
    }*/
    
}
