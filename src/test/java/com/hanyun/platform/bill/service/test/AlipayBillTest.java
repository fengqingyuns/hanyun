/**
 *
 */
package com.hanyun.platform.bill.service.test;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.service.cib.alipay.CibAlipayBillService;
import com.hanyun.platform.pay.service.cib.alipay.CibAlipayDownloadBillService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wangjie@hanyun.com   
 * @Description:支付宝账单下载
 * @date 2016年9月1日 上午11:19:43
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class AlipayBillTest {
    @Resource
    private CibAlipayDownloadBillService cibAlipayDownloadBillService;

    @Resource
    private CibAlipayBillService cibAlipayBillService;

    @Test
    public void testDownloadAlipayBill() {
        Date date = new Date();
        Date date1 = DateCalcUtil.addDays(date, -1);// 获取前一天的时间
        String billDateStr = DateFormatUtil.formatDateNoSep(date1);

    }
}
