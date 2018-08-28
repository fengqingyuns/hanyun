/**
 * 
 */
package com.hanyun.platform.pay.domain;

/**
 * 商户实际支付方式信息
 * @author liyinglong@hanyun.com
 * @date 2016年8月22日 下午5:36:32
 */
public class MchActualPayModeInfo {
    
    private String brandId;
    
    private String payType;
    
    private String typeCategory;

    private String channel;

    private Integer settleType;

    private Integer chnFeeRate;

    private Integer chnFeeMax;

    private Integer mchFeeRate;

    private Integer mchFeeMax;
    
    private Integer mchFeeRateDef;

    private Integer mchFeeMaxDef;

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

    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getChnFeeRate() {
        return chnFeeRate;
    }

    public void setChnFeeRate(Integer chnFeeRate) {
        this.chnFeeRate = chnFeeRate;
    }

    public Integer getChnFeeMax() {
        return chnFeeMax;
    }

    public void setChnFeeMax(Integer chnFeeMax) {
        this.chnFeeMax = chnFeeMax;
    }

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

    public Integer getMchFeeRateDef() {
        return mchFeeRateDef;
    }

    public void setMchFeeRateDef(Integer mchFeeRateDef) {
        this.mchFeeRateDef = mchFeeRateDef;
    }

    public Integer getMchFeeMaxDef() {
        return mchFeeMaxDef;
    }

    public void setMchFeeMaxDef(Integer mchFeeMaxDef) {
        this.mchFeeMaxDef = mchFeeMaxDef;
    }
    
    
}
