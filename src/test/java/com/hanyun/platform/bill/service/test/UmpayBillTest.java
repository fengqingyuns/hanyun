/**
 * 
 */
package com.hanyun.platform.bill.service.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.platform.pay.service.umpay.UmpayBillService;

/**
 * 
* @Description: 联动优势POS对账单
* @author wangjie@hanyun.com
* @date 2016年9月1日 上午11:19:43
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class UmpayBillTest {
    @Resource
    private UmpayBillService umpayBillService;
    
    @Test
    public void testUmpayBill(){
    	Date date = new Date();
    	Date date1 = DateCalcUtil.addDays(date, -1);// 获取前一天的时间

    }
}
