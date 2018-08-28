package com.hanyun.platform.pay.adapt.yeepay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.yeepay.model.WechatYeePayReq;
import com.hanyun.platform.pay.adapt.yeepay.model.YeepayResponseBase;
import com.hanyun.platform.pay.adapt.yeepay.util.UsualTools;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;
import com.yeepay.g3.sdk.yop.client.YopClient;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:微信支付处理类
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay
 * @Author: dewen.li
 * @Date: 2018-08-02 下午10:28
 */

@Component
public class YeepayWechatPayAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(YeepayWechatPayAdapter.class);
    /** 连接超时时间，毫秒 */
    protected static final int HTTP_CONN_TIME_OUT = 10000;
    /** 读取超时时间，毫秒 */
    protected static final int HTTP_SOCKET_TIME_OUT = 20000;

    @Resource
    private YeepayConfig yeepayConfig;



    /**
     * 微信支付请求
     *
     * @param wechatYeePayReq
     * @return
     */
    public YeepayResponseBase doRequest(WechatYeePayReq wechatYeePayReq) throws TimeoutException {
        YeepayResponseBase result = new YeepayResponseBase();
        String appNo = null;
        String key = null;
            String url = yeepayConfig.getBaseUrl();
            String storeNo = wechatYeePayReq.getStoreId();
            Map<String,String> channel = YeepayChildChannelRegGlobal.getChildChannel(storeNo);
            if(null == channel){
                key = yeepayConfig.getSecreKey();
                appNo = yeepayConfig.getAppKey();
            }else {
                appNo = channel.get("appNo");
                key = channel.get("appKey");
            }
            LOGGER.info("获取商编信息： appNO="+appNo+",SecretKey="+key);
            //建立支付连接
            YopRequest yopRequest = new YopRequest(appNo,key,url);
            yopRequest.setEncrypt(true);
            yopRequest.setSignRet(true);
            yopRequest.setSignAlg("sha-256");
            yopRequest.addParam("payEmpowerNo",wechatYeePayReq.getPayEmpowerNo());
            yopRequest.addParam("merchantTerminalId",wechatYeePayReq.getMerchantTerminalId());
            //支付服务ip
            yopRequest.addParam("ip",wechatYeePayReq.getIp());
            //门店编号
            yopRequest.addParam("merchantStoreNo",wechatYeePayReq.getStoreId());
            //唯一支付流水号（交易请求号）
            yopRequest.addParam("requestNo",wechatYeePayReq.getRequestNo());
            yopRequest.addParam("orderAmount",wechatYeePayReq.getOrderAmount());
            yopRequest.addParam("fundAmount",wechatYeePayReq.getFundAmount());
            yopRequest.addParam("openId",wechatYeePayReq.getOpenId());
            //支付类型
            yopRequest.addParam("payTool",wechatYeePayReq.getPayTool());
            yopRequest.addParam("merchantOrderDate",wechatYeePayReq.getMerchantOrderDate());
            yopRequest.addParam("merchantExpireTime",wechatYeePayReq.getMerchantExpireTime());
            yopRequest.addParam("trxExtraInfo",wechatYeePayReq.getTrxExtraInfo());
            //后台回调
            yopRequest.addParam("serverCallbackUrl",wechatYeePayReq.getServerCallbackUrl()+"/"+wechatYeePayReq.getStoreId());
            yopRequest.addParam("webCallbackUrl",wechatYeePayReq.getWebCallbackUrl());
            yopRequest.addParam("mcc",wechatYeePayReq.getMcc());
            yopRequest.addParam("productCatalog",wechatYeePayReq.getProductCatalog());
            yopRequest.addParam("productName",wechatYeePayReq.getProductName());
            yopRequest.addParam("productDesc",wechatYeePayReq.getProductDesc());
            yopRequest.addParam("merchantBizType",wechatYeePayReq.getMerchantBizType());
            yopRequest.addParam("divideRuleType",wechatYeePayReq.getDivideRuleType());
            yopRequest.addParam("divideDetail",wechatYeePayReq.getDivideDetail());
            yopRequest.addParam("divideCallbackUrl",wechatYeePayReq.getDivideCallbackUrl());
            yopRequest.addParam("accountPayMerchantNo",wechatYeePayReq.getAccountPayMerchantNo());
            LOGGER.info("Yeepay_ serverCallbackUrl: = >"+yopRequest.getParam("serverCallbackUrl"));
            YopResponse yopResponse = null;
        try {
             yopResponse = YopClient.post(yeepayConfig.getPaypath(),yopRequest);
        } catch (Exception e) {
            throw new TimeoutException();
        }
            LOGGER.info("Yeepay_resp : = >"+yopResponse);
            if(null == yopResponse){
                throw new NullPointerException("Result null");
            }
            if(yopResponse.isSuccess()) {

                //获取成功参数
                Map<String,Object> resp = (Map<String, Object>) yopResponse.getResult();
                if(resp.get("code").equals("1")){
                    result.setCode(resp.get("code").toString());
                    result.setStatus((String)resp.get("status"));
                    result.setMessage(resp.get("message").toString());
                }
            }

        return result;
    }

}
