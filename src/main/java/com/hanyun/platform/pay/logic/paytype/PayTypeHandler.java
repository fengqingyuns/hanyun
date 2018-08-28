/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype;

import javax.annotation.PostConstruct;

import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradePayReq;

/**
 * 支付方式外部接口处理器
 * @author liyinglong@hanyun.com
 * @date 2016年7月18日 下午9:11:24
 */
public abstract class PayTypeHandler {
    @PostConstruct
    public void init() {
        PayTypeHandlerFactory.register(getPayType(), this);
    }
    
    /**
     * 获取支付方式
     * @return 支付方式
     */

    public abstract String getPayType();
    
    /**
     * 处理预创建订单
     * @param req 请求信息
     * @return 扩展业务数据
     */
    public abstract Object doPreCreate(TradePreCreateReq req);
    
    /**
     * 处理支付
     * @param req 请求信息
     * @param 扩展业务数据
     */
    public abstract Object doPay(TradePayReq req) throws Exception;
    
    /**
     * 处理退款
     * @param req 请求信息
     * @param 扩展业务数据
     */
    public abstract Object doRefund(TradeRefundReq req);
}
