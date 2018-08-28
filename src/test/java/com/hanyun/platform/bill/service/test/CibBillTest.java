/**
 *
 */
package com.hanyun.platform.bill.service.test;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.platform.pay.service.cib.weixin.CibWeixinBillService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wangjie@hanyun.com
 * @Description: 信业银行微信对账
 * @date 2016年9月1日 上午11:19:43
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class CibBillTest {
    @Resource
    private CibWeixinBillService cibWeixinBillService;

    @Test
    public void testCibBill() {
        Date date = new Date();
        Date date1 = DateCalcUtil.getPreDay(date);// 获取前一天的时间
    }
}
