/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 撤消订单接口响应结果
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午9:13:33
 */
@XmlRootElement(name = "xml")
public class ReverseOrderRes extends BaseResponse {
    // 是否重调 recall
    private String recall;

    public String getRecall() {
        return recall;
    }
    public void setRecall(String recall) {
        this.recall = recall;
    }
}
