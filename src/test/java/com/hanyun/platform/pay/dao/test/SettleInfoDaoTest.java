/**
 * 
 */
package com.hanyun.platform.pay.dao.test;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanyun.platform.pay.dao.SettleInfoDao;
import com.hanyun.platform.pay.domain.SettleInfo;

/**
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月5日 下午3:01:35
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class SettleInfoDaoTest {
    @Resource
    private SettleInfoDao settleInfoDao;
    
    @Test
    public void testInsert(){
        SettleInfo settleinfo = new SettleInfo();
        settleinfo.setMerchantId("aaaaaaaaaaaa");
        settleinfo.setBrandId("bbbbbbbbbb");
        settleinfo.setStoreId("cccccccccc");
        settleinfo.setMerchantType(2);
        settleinfo.setSettleCircle(3);
        settleInfoDao.insert(settleinfo);
    }
}
