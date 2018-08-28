/**
 * 
 */
package com.hanyun.platform.pay.vo.paymode;

/**
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月9日 上午11:07:08
 */
public class PayChannelReq {
    private String channel;
    private Integer availStatus;
    
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public Integer getAvailStatus() {
        return availStatus;
    }
    public void setAvailStatus(Integer availStatus) {
        this.availStatus = availStatus;
    }
}
