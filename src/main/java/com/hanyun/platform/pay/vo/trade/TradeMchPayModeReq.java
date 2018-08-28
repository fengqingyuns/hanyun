/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 查询商户所有可用且在线的支付方式请求信息
 * @author liyinglong@hanyun.com
 * @date 2017年2月21日 下午4:14:15
 */
public class TradeMchPayModeReq {
    private String brandId;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
