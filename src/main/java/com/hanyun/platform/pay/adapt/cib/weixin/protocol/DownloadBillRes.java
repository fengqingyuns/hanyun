/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 下载对账单接口响应结果
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午8:31:33
 */
@XmlRootElement(name = "xml")
public class DownloadBillRes extends BaseResponse {
    // 账单明细数据
    private String billdata;

    public String getBilldata() {
        return billdata;
    }

    public void setBilldata(String billdata) {
        this.billdata = billdata;
    }
}
