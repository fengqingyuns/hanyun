package com.hanyun.platform.pay.vo.paymode;

public class PayModeReq {
    
    private String payType;
    
    private Integer mchFeeRateDef;

    private Integer mchFeeMaxDef;

    private Integer availStatus;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public Integer getAvailStatus() {
        return availStatus;
    }

    public void setAvailStatus(Integer availStatus) {
        this.availStatus = availStatus;
    }
    
    
    
    
}
