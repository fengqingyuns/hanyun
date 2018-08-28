package com.hanyun.platform.pay.adapt.yeepay;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.ground.util.http.HttpClient;
import com.hanyun.ground.util.http.HttpClientPost;
import com.hanyun.ground.util.http.HttpClientResponse;
import com.hanyun.platform.pay.adapt.yeepay.model.UnpayYeePayReq;
import com.hanyun.platform.pay.consts.BizResCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay
 * @Author: dewen.li
 * @Date: 2018-08-02 下午10:28
 */

@Component
public class YeepayUnpayAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(YeepayUnpayAdapter.class);
    /** 连接超时时间，毫秒 */
    protected static final int HTTP_CONN_TIME_OUT = 10000;
    /** 读取超时时间，毫秒 */
    protected static final int HTTP_SOCKET_TIME_OUT = 20000;

    @Resource
    private YeepayConfig yeepayConfig;



    /**
     * pos刷卡支付请求
     *
     * @param unpayYeePayReq
     * @return
     */
    protected HttpClientResponse doRequest(UnpayYeePayReq unpayYeePayReq) {
        HttpClientResponse hcres = null;
        try {
            String url = yeepayConfig.getBaseUrl();
            LOGGER.info("request url:{}", url);

            HttpClientPost hcpost = HttpClient.post(url);
            hcpost.timeout(HTTP_CONN_TIME_OUT, HTTP_SOCKET_TIME_OUT);
            hcpost.json(unpayYeePayReq);
            hcres = hcpost.action();
        } catch (Exception e) {
            LOGGER.error("UMPAY_ERROR_REQ", e);
            throw BizException.build(BizResCode.UMPAY_ERROR_REQ);
        }
        return hcres;
    }

}
