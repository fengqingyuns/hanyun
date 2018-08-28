/**
 * 
 */
package com.hanyun.platform.pay.adapt.yeepay.model;

import com.hanyun.platform.pay.adapt.cib.weixin.protocol.BaseRequest;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Created with IntelliJ IDEA.
 * Description:支付宝被扫
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-07 下午1:48
 */
public class WechatYeePayReq extends YeepayReqBase {
    //授权码
    private String payEmpowerNo;

    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    //微信ID
    private String openId;
    public String getPayEmpowerNo() {
        return payEmpowerNo;
    }

    public void setPayEmpowerNo(String payEmpowerNo) {
        this.payEmpowerNo = payEmpowerNo;
    }

    public String getMerchantTerminalId() {
        return merchantTerminalId;
    }

    public void setMerchantTerminalId(String merchantTerminalId) {
        this.merchantTerminalId = merchantTerminalId;
    }
    //设备号
    private String merchantTerminalId;

    public String getMerchantStoreNo() {
        return merchantStoreNo;
    }

    public void setMerchantStoreNo(String merchantStoreNo) {
        this.merchantStoreNo = merchantStoreNo;
    }

    //门店编号
    private String merchantStoreNo;
}
