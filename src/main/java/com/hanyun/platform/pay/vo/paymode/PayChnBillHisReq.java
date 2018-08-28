/**
 * 
 */
package com.hanyun.platform.pay.vo.paymode;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanyun.platform.pay.vo.base.PageRequest;

/**
 * 
 * @author wangjie@hanyun.com
 * @date 2017年01月16日 下午17:00:08
 */
public class PayChnBillHisReq extends PageRequest {
	private String channel;
    private String billClass;
	@JsonFormat(pattern="yyyy-MM-dd")	
    private Date checkDate;
    
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
}
