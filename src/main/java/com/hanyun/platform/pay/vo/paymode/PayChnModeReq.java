/**
 * 
 */
package com.hanyun.platform.pay.vo.paymode;

/**
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月9日 上午11:43:29
 */
public class PayChnModeReq {
    private String payType;

    private String channel;

    private Integer chnFeeRate;

    private Integer chnFeeMax;

    private Integer srvStatus;

    private Integer availStatus;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public Integer getSrvStatus() {
        return srvStatus;
    }

    public void setSrvStatus(Integer srvStatus) {
        this.srvStatus = srvStatus;
    }

    public Integer getAvailStatus() {
        return availStatus;
    }

    public void setAvailStatus(Integer availStatus) {
        this.availStatus = availStatus;
    }
}
