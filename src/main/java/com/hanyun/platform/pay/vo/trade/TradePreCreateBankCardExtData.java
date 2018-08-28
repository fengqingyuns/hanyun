/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 预创建订单POS刷卡附加数据
 * @author liyinglong@hanyun.com
 * @date 2016年12月21日 下午3:43:09
 */
public class TradePreCreateBankCardExtData {
    private String outTradeNo;
    private String notifyUrl;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
