/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 查询商户所有可用且在线的支付方式响应结果
 * @author liyinglong@hanyun.com
 * @date 2017年2月21日 下午4:14:26
 */
public class TradeMchPayModeRes {
    private Map<String, Set<String>> payTypeInfo = new HashMap<>();
    
    /**
     * 添加支付方式
     * @param typeCategory
     * @param payType
     */
    public void addPayType(String typeCategory, String payType){
        Set<String> tmpTypeSet = payTypeInfo.get(typeCategory);
        if(tmpTypeSet == null){
            tmpTypeSet = new HashSet<>();
            payTypeInfo.put(typeCategory, tmpTypeSet);
        }
        tmpTypeSet.add(payType);
    }

    public Map<String, Set<String>> getPayTypeInfo() {
        return payTypeInfo;
    }

    public void setPayTypeInfo(Map<String, Set<String>> payTypeInfo) {
        this.payTypeInfo = payTypeInfo;
    }
}
