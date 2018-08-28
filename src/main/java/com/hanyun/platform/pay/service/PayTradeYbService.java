package com.hanyun.platform.pay.service;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePayRes;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateRes;

/**
 * 
 * @date 2018年8月1日
 * 
 * @apiDefine common 易宝支付相关
 * @author litao@hanyun.com
 */
public interface PayTradeYbService {

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
