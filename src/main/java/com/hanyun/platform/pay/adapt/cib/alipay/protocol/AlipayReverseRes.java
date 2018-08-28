/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay.protocol;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 撤消订单接口响应结果
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午7:40:51
 */
@XmlRootElement(name = "xml")
public class AlipayReverseRes extends AlipayBaseResponse {
    // 是否重调 recall
    @XmlElement(name="recall")
    private String recall;

    public String getRecall() {
        return recall;
    }
    public void setRecall(String recall) {
        this.recall = recall;
    }
}
