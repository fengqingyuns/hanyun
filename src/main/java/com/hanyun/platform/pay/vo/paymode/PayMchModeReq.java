package com.hanyun.platform.pay.vo.paymode;
/**
 * 商户支付方式请求
 * 
 * @author wangximin@hanyun.com
 * 
 * @date 2016年8月18日 下午8:32:40
 *
 */
public class PayMchModeReq {
    
    private String brandId;

    private String payType;
    
    private Integer mchFeeMax;
    
    private Integer mchFeeRate;
    
    private Integer availStatus;
    
    public Integer getMchFeeRate() {
        return mchFeeRate;
    }

    public void setMchFeeRate(Integer mchFeeRate) {
        this.mchFeeRate = mchFeeRate;
    }

    public Integer getMchFeeMax() {
        return mchFeeMax;
    }

    public void setMchFeeMax(Integer mchFeeMax) {
        this.mchFeeMax = mchFeeMax;
    }

    
    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getAvailStatus() {
        return availStatus;
    }

    public void setAvailStatus(Integer availStatus) {
        this.availStatus = availStatus;
    }
    
    
 
}
