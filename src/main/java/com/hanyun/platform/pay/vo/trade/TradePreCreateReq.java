/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 预创建交易支付单
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午3:19:49
 */
public class TradePreCreateReq extends TradePayBaseReq{
    
    // 微信支付-商户微信公众账号ID，选填
    private String wxAppid;
    // 微信支付-用户在商户appid下的唯一标识，选填
    private String wxOpenid;
    
    public String getWxAppid() {
        return wxAppid;
    }
    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }
    public String getWxOpenid() {
        return wxOpenid;
    }
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }
    
}
