/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 关闭订单接口请求参数
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午7:42:45
 */
@XmlRootElement(name = "xml")
public class CloseOrderReq extends BaseRequest {
    // 商户订单号 out_trade_no
    private String out_trade_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

}
