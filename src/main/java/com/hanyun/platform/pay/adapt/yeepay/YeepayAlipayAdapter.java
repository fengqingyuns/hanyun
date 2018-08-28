package com.hanyun.platform.pay.adapt.yeepay;

import com.hanyun.platform.pay.adapt.yeepay.model.AlipayYeePayReq;
import com.hanyun.platform.pay.adapt.yeepay.model.YeepayResponseBase;
import com.hanyun.platform.pay.adapt.yeepay.util.UsualTools;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;
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
 * Description:支付宝支付处理累
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay
 * @Author: dewen.li
 * @Date: 2018-08-02 下午10:28
 */
@Component
public class YeepayAlipayAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(YeepayAlipayAdapter.class);
    /** 连接超时时间，毫秒 */
    protected static final int HTTP_CONN_TIME_OUT = 10000;
    /** 读取超时时间，毫秒 */
    protected static final int HTTP_SOCKET_TIME_OUT = 20000;

    @Resource
    private YeepayConfig yeepayConfig;



    /**
     * 支付宝支付请求
     *
     * @param alipayYeePayReq
     * @return
     */
    public YeepayResponseBase doRequest(AlipayYeePayReq alipayYeePayReq) throws TimeoutException {
        YeepayResponseBase result = null;
        String appNo = null;
        String key = null;

            String url = yeepayConfig.getBaseUrl();
            String storeNo = alipayYeePayReq.getStoreId();
            Map<String,String> channel = YeepayChildChannelRegGlobal.getChildChannel(storeNo);
            if(null == channel){
                appNo = yeepayConfig.getAppKey();
                key = yeepayConfig.getSecreKey();
            }else {
                appNo = channel.get("appNo");
                key = channel.get("appKey");
            }
            LOGGER.info("获取商编信息： appNO="+appNo+",SecretKey="+key);
            //建立支付连接
            YopRequest yopRequest = new YopRequest(appNo,key,url);
            if(null == yopRequest){
                throw new TimeoutException();
            }
            yopRequest.setEncrypt(true);
            yopRequest.setSignRet(true);
            yopRequest.setSignAlg("sha-256");

            //支付服务ip
            yopRequest.addParam("ip",alipayYeePayReq.getIp());
            //支付宝付款码
            yopRequest.addParam("payEmpowerNo",alipayYeePayReq.getPayEmpowerNo());
            //用户ID【商户用户】
            yopRequest.addParam("merchantTerminalId",alipayYeePayReq.getMerchantTerminalId());

            //门店编号
             yopRequest.addParam("merchantStoreNo",alipayYeePayReq.getStoreId());

            //唯一支付流水号（交易请求号）
            yopRequest.addParam("requestNo",alipayYeePayReq.getRequestNo());
            //订单金额
            yopRequest.addParam("orderAmount",alipayYeePayReq.getOrderAmount());
            //实际金额
            yopRequest.addParam("fundAmount",alipayYeePayReq.getFundAmount());
            //支付类型
            yopRequest.addParam("payTool",alipayYeePayReq.getPayTool());
            //订单时间
            yopRequest.addParam("merchantOrderDate",alipayYeePayReq.getMerchantOrderDate());
            yopRequest.addParam("merchantExpireTime",alipayYeePayReq.getMerchantExpireTime());
            yopRequest.addParam("trxExtraInfo",alipayYeePayReq.getTrxExtraInfo());
            //后台回调
            yopRequest.addParam("serverCallbackUrl",alipayYeePayReq.getServerCallbackUrl()+"/"+alipayYeePayReq.getStoreId());
            //回调返回页面
            yopRequest.addParam("webCallbackUrl",alipayYeePayReq.getWebCallbackUrl());
            //行业码
            yopRequest.addParam("mcc",alipayYeePayReq.getMcc());
            yopRequest.addParam("productCatalog",alipayYeePayReq.getProductCatalog());
            //商品名
            yopRequest.addParam("productName",alipayYeePayReq.getProductName());
            yopRequest.addParam("productDesc",alipayYeePayReq.getProductDesc());
            yopRequest.addParam("merchantBizType",alipayYeePayReq.getMerchantBizType());
            yopRequest.addParam("divideRuleType",alipayYeePayReq.getDivideRuleType());
            yopRequest.addParam("divideDetail",alipayYeePayReq.getDivideDetail());
            yopRequest.addParam("divideCallbackUrl",alipayYeePayReq.getDivideCallbackUrl());
            yopRequest.addParam("accountPayMerchantNo",alipayYeePayReq.getAccountPayMerchantNo());
            YopResponse yopResponse = null;
            try {
                 yopResponse = YopClient.post(yeepayConfig.getPaypath(), yopRequest);
            }catch (IOException e){
                throw new TimeoutException();
            }
            LOGGER.info("Yeepay : = >"+yopResponse);

            if(null!=yopResponse) {
                if (yopResponse.isSuccess()) {
                     Map<String, Object> rsp = (Map<String, Object>) yopResponse.getResult();
                    if (rsp.get("code").equals("1")) {
                        result.setCode("1");
                        result.setStatus("SUCCESS");
                        result.setMessage(rsp.get("message").toString());
                    }
                }
            }
        return result;
    }

}
