/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.platform.pay.adapt.cib.weixin.CibWeiXinUnifiedorderAdapter;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.MicroPayReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.UnifiedorderReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.UnifiedorderRes;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.service.cib.weixin.CibWeiXinDownloadBillService;
import com.hanyun.platform.pay.util.SignatureUtils;

/**
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午6:15:19
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class CibWeiXinAdapterTest {
    @Resource
    private CibWeiXinUnifiedorderAdapter cibWeiXinUnifiedorderAdapter;
    @Resource
    private CibWeiXinDownloadBillService cibWeiXinDownloadBillService;
    
    @Test
    public void testDownloadBill(){
        Date date = DateCalcUtil.addDays(new Date(), -1);
    }
    
    @Test
    public void testCibWeiXinUnifiedorderAdapter(){
        Payment payment = new Payment();
        payment.setBrandId("1001");
        UnifiedorderReq req = new UnifiedorderReq();
        req.setOut_trade_no("22001");
        req.setDevice_info("111111");
        req.setBody("苏州街店-午餐");
        req.setTotal_fee(21000L);
        req.setTrade_type("NATIVE");
        
        PayProcessContext.setPayment(payment);
        UnifiedorderRes res = cibWeiXinUnifiedorderAdapter.request(req);
        
        Assert.assertNotNull(res);
    }
    
    @Test
    public void testSign(){
        MicroPayReq req = new MicroPayReq();
        req.setAppid("wxd930ea5d5a258f4f");
        req.setAuth_code("123456");
        req.setBody("test");
        req.setDevice_info("123");
        req.setMch_id("1900000109");
        req.setNonce_str("960f228109051b9969f76c82bde183ac");
        req.setOut_trade_no("1400755861");
        req.setSpbill_create_ip("127.0.0.1");
        req.setTotal_fee(1L);
        
        String sign = SignatureUtils.sign(req, "8934e7d15453e97507ef794cf7b0519d");
        
    }
}
