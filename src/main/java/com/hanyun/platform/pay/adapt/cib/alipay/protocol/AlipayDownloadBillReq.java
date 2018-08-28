/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 下载对账单接口请求参数
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午7:36:34
 */
@XmlRootElement(name = "xml")
public class AlipayDownloadBillReq extends AlipayBaseRequest {
    // 对账单日起 bill_date
    @XmlElement(name="bill_date")
    private String billDate;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
    
}
