/**
 * 
 */
package com.hanyun.platform.pay.vo.paymode;

/**
 * 
 * @author wangjie@hanyun.com
 * @date 2017年01月15日 上午11:00:08
 */
public class PayChnBillReq {
    private String billClass;
    private Integer availStatus;
    

    public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public Integer getAvailStatus() {
        return availStatus;
    }
    public void setAvailStatus(Integer availStatus) {
        this.availStatus = availStatus;
    }
}
