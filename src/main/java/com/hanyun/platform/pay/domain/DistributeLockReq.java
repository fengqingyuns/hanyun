package com.hanyun.platform.pay.domain;

import java.util.Date;

/** 
* @Description: 分布式锁中增加有效时间
* @author wangjie@hanyun.com
* @date 2016年9月9日 下午2:25:05
*/
public class DistributeLockReq extends DistributeLock {
	private Date effectiveTime;

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

}
