/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 下载对账单接口响应结果
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午7:36:56
 */
@XmlRootElement(name = "xml")
public class AlipayDownloadBillRes extends AlipayBaseResponse {
    // 账单明细数据
    private String billdata;

    public String getBilldata() {
        return billdata;
    }

    public void setBilldata(String billdata) {
        this.billdata = billdata;
    }
}
