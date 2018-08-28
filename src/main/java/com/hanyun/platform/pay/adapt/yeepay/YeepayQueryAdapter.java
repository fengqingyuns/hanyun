package com.hanyun.platform.pay.adapt.yeepay;

import com.hanyun.platform.pay.adapt.yeepay.model.YeePayQueryReq;
import com.hanyun.platform.pay.adapt.yeepay.model.YeePayFindResponse;
import com.hanyun.platform.pay.adapt.yeepay.model.YeeQueryOrderRes;
import com.hanyun.platform.pay.adapt.yeepay.util.UsualTools;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.yeepay.g3.sdk.yop.client.YopClient;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt
 * @Author: dewen.li
 * @Date: 2018-08-14 上午9:35
 */

@Component
public class YeepayQueryAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(YeepayAlipayAdapter.class);
    @Resource
    private YeepayConfig yeepayConfig;

    public YeeQueryOrderRes findPaymentByRequestNo(YeePayQueryReq request) throws IOException {
        YeeQueryOrderRes yeeQueryOrderRes = new YeeQueryOrderRes();
        String appNo = null;
        String key = null;
        String url = yeepayConfig.getBaseUrl();
        String storeNo = request.getStoreId();
        Map<String,String> channel = YeepayChildChannelRegGlobal.getChildChannel(storeNo);
        if(null == channel){
            appNo = yeepayConfig.getAppKey();
            key = yeepayConfig.getSecreKey();
        }else {
            appNo = channel.get("appNo");
            key = channel.get("appKey");
        }
        //建立支付连接
        YopRequest yopRequest = new YopRequest(appNo,key,url);
        yopRequest.setEncrypt(true);
        yopRequest.setSignRet(true);
        yopRequest.setSignAlg("sha-256");
        yopRequest.addParam("trxRequestNo",request.getRequestNo());
        YopResponse yopResponse = YopClient.post(yeepayConfig.getQueryPath(),yopRequest);
        LOGGER.info("Fine Pay : = >"+yopResponse);
        Map<String, Object>  rst = (Map<String, Object>)yopResponse.getResult();
        String status = rst.get("status").toString();
        LOGGER.info("Fine status : = >"+status);
        LOGGER.info("Fine rst : = >"+rst.toString());
        yeeQueryOrderRes.setStatus(status);
        yeeQueryOrderRes.setRequestNo(yopResponse.getRequestId());
        if(yopResponse.isSuccess()) {
            yeeQueryOrderRes.setStatus("0");
        }
        return yeeQueryOrderRes;
    }
}
