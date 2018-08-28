/**
 * 
 */
package com.hanyun.platform.pay.service;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.vo.trade.TradeQueryReq;
import com.hanyun.platform.pay.vo.trade.TradeQueryRes;
import com.hanyun.platform.pay.vo.trade.TradeExtraInfoQueryReq;
import com.hanyun.platform.pay.vo.trade.TradeExtraInfoQueryRes;

/**
 * 支付交易查询的服务
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午5:12:35
 */
public interface PayTradeQueryService {

    /**
     * 支付单查询
     * 
     * @param req
     *            请求参数
     * @return 响应结果
     */
    public HttpResponse<TradeQueryRes> query(TradeQueryReq req) throws Exception;

    /**
     * 
     * 通过orderId获取额外信息
     */
    public HttpResponse<TradeExtraInfoQueryRes> getExtByOrderId(TradeExtraInfoQueryReq req) throws Exception;

}
