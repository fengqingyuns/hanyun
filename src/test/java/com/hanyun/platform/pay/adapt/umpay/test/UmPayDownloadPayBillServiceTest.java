/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.service.umpay.UmPayDownloadPayBillService;

/**
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月20日 下午2:08:45
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class UmPayDownloadPayBillServiceTest {
    @Resource
    private UmPayDownloadPayBillService umPayDownloadPayBillService;
    
    @Test
    public void testDownloadPayBill(){
        Date date = DateFormatUtil.parseDateNoSep("20170111");
        
        umPayDownloadPayBillService.downloadPayBill(date);
    }
}
