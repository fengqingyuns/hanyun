/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;

/**
 * 支付方式处理器工厂
 * @author liyinglong@hanyun.com
 * @date 2016年7月18日 下午9:05:57
 */
public abstract class PayTypeHandlerFactory {
    private static final Map<String, PayTypeHandler> HANDLER_MAP = Maps.newHashMap();
    public static final String DEFAULT_HANDLER_KEY = "default";
    
    /**
     * 获取处理器
     * @param payType 支付方式
     * @return 处理器
     */
    public static PayTypeHandler getHandler(String payType){
        PayTypeHandler handler = HANDLER_MAP.get(payType);
        if(handler == null){
            handler = HANDLER_MAP.get(DEFAULT_HANDLER_KEY);
        }
        return handler;
    }
    
    /**
     * 注册处理器
     * @param payType 支付方式
     * @param handler 处理器
     */
    public static void register(String payType, PayTypeHandler handler){
        if(StringUtils.isNotBlank(payType)){
            HANDLER_MAP.put(payType, handler);
        }
    }
}
