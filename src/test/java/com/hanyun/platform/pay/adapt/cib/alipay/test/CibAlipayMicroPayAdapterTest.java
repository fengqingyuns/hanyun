/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanyun.platform.pay.adapt.cib.alipay.CibAlipayMicroPayAdapter;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayMicroPayReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayMicroPayRes;
import com.hanyun.platform.pay.context.PayProcessContext;

/**
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年1月3日 下午8:09:00
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class CibAlipayMicroPayAdapterTest {
    @Resource
    private CibAlipayMicroPayAdapter cibAlipayMicroPayAdapter;
    
    @Test
    public void testRequest(){
        AlipayMicroPayReq mpreq = new AlipayMicroPayReq();
        mpreq.setOutTradeNo("999999");
        mpreq.setBody("朝阳大悦城店");
        mpreq.setAttach("888888");
        mpreq.setTotalFee(1L);
        mpreq.setFeeType(null);
        mpreq.setDeviceInfo("aaaaaaaaaa");
        mpreq.setAuthCode("283296335660611165");
        mpreq.setStoreAppid("s20160610000000406");
        
        PayProcessContext.setBrandId("79ce8cbf-c4e0-4966-8326-9a7206eb496d");
        
        AlipayMicroPayRes res = cibAlipayMicroPayAdapter.request(mpreq);
        
        Assert.assertNotNull(res);
    }
}
