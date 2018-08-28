/**
 * 
 */
package com.hanyun.platform.pay.service;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundRes;

/**
 * 退款交易服务
 * @author liyinglong@hanyun.com
 * @date 2017年6月15日 下午2:43:52
 */
public interface PayRefundTradeService {
    /**
     * 退款
     * @param req 请求参数
     * @return 响应结果
     */
    public HttpResponse<TradeRefundRes> refund(TradeRefundReq req) throws Exception;
}
