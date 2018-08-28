package com.hanyun.platform.pay.vo.paymode;

import com.hanyun.platform.pay.domain.PayMode;
/**
 * 
 * 支付通道详情响应
 * 
 * @author wangximin@hanyun.com
 * 
 * @date 2016年8月9日 下午2:13:28
 *
 */
public class PayModeRes extends PayMode{
	
	private String channel;
	
	private String chnName;
	
	private Integer srvStatus;
	
    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getSrvStatus() {
		return srvStatus;
	}

	public void setSrvStatus(Integer srvStatus) {
		this.srvStatus = srvStatus;
	}

	
}
