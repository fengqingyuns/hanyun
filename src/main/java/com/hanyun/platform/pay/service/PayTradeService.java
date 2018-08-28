/**
 * 
 */
package com.hanyun.platform.pay.service;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateRes;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePayRes;

/**
 * 支付交易服务
 * @author liyinglong@hanyun.com
 * @date 2017年6月15日 下午2:49:51
 */
public interface PayTradeService {
    /**
     * 预创建交易支付单
     * @param req 请求参数
     * @return 响应结果
     */
    public HttpResponse<TradePreCreateRes> preCreate(TradePreCreateReq req) throws Exception;
    
    /**
     * 支付
     * 
     * @param req
     *            请求参数
     * @return 响应结果
     */
    public HttpResponse<TradePayRes> pay(TradePayReq req) throws Exception;
}
