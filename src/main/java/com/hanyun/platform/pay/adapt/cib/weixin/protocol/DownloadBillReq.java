/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 下载对账单接口请求参数
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午8:31:20
 */
@XmlRootElement(name = "xml")
public class DownloadBillReq extends BaseRequest {
    // 对账单日起 bill_date
    private String bill_date;
    // 账单类型 bill_type
    private String bill_type;

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

}
