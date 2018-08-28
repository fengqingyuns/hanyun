package com.hanyun.platform.pay.adapt.yeepay;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundRes;
import com.hanyun.platform.pay.adapt.yeepay.model.*;
import com.hanyun.platform.pay.adapt.yeepay.util.UsualTools;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
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
 * Description:易宝退款处理类
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay
 * @Author: dewen.li
 * @Date: 2018-08-10 下午4:24
 */
@Component
public class YeepayRefundAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(YeepayAlipayAdapter.class);
    /** 连接超时时间，毫秒 */
    protected static final int HTTP_CONN_TIME_OUT = 10000;
    /** 读取超时时间，毫秒 */
    protected static final int HTTP_SOCKET_TIME_OUT = 20000;

    @Resource
    private YeepayConfig yeepayConfig;


    public Object doRefund(YeepayRefundReq request) {
        YeepayRefundResponse result = null;
        String appNo = null;
        String key = null;

        try {
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
            //原支付流水号（原支付交易请求号）
            yopRequest.addParam("trxRequestNo",request.getTrxRequestNo());
            //退款回调地址
            yopRequest.addParam("serverCallbackUrl",request.getServerCallbackUrl());
            //唯一退款流水号
            yopRequest.addParam("requestNo",request.getRequestNo());
            //退款方式：默认原路退回
            yopRequest.addParam("refundWay",request.getRefundWay());
            //退款金额
            yopRequest.addParam("fundAmount",request.getRefundAmount());
            //支付类型
            yopRequest.addParam("webCallbackUrl",request.getWebCallbackUrl());

            YopResponse yopResponse = YopClient.post(yeepayConfig.getPaypath(),yopRequest);
            LOGGER.info("Yeepay : = >"+yopResponse);
            result.setCode(yopResponse.getError().getCode());
            result.setMessage(yopResponse.getError().getMessage());
            if(yopResponse.isSuccess()) {
                result.setStatus("0");
            }

        } catch (Exception e) {
            LOGGER.error("UMPAY_ERROR_REQ", e);
            throw BizException.build(BizResCode.UMPAY_ERROR_REQ);
        }
        return result;
    }

    public Object doAliPayRefund(AlipayYeepayRefundReq alipayYeepayRefundReq){
        YeepayRefundReq yeepayRefundReq = alipayYeepayRefundReq;
        return doRefund(yeepayRefundReq);
    }


    public Object doWechatRefund(WechatYeepayRefundReq wechatYeepayRefundReq){
        YeepayRefundReq yeepayRefundReq = wechatYeepayRefundReq;
        return doRefund(yeepayRefundReq);
    }

}
