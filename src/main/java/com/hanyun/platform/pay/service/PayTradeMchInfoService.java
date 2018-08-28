/**
 * 
 */
package com.hanyun.platform.pay.service;

import java.util.Map;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.domain.PayMchMode;
import com.hanyun.platform.pay.vo.trade.TradeMchPayModeReq;
import com.hanyun.platform.pay.vo.trade.TradeMchPayModeRes;

/**
 * 支付交易商户信息的服务
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午5:12:17
 */
public interface PayTradeMchInfoService {

    /**
     * 查询商户所有可用且在线的支付方式
     * 
     * @param req
     *            请求参数
     * @return 响应结果
     * @throws Exception
     */
    public HttpResponse<TradeMchPayModeRes> getTradeMchPayMode(TradeMchPayModeReq req) throws Exception;

    /**
     * 查询商户费率
     * 
     * @param brandId
     * @return
     * @throws Exception
     */
    public HttpResponse<Map<String, PayMchMode>> getTradeMchFeeRate(String brandId) throws Exception;

}
