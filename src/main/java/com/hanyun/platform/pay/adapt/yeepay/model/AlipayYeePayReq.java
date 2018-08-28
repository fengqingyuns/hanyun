package com.hanyun.platform.pay.adapt.yeepay.model;
/**
 * Created with IntelliJ IDEA.
 * Description:支付宝被扫
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-07 下午1:48
 */
public class AlipayYeePayReq extends YeepayReqBase {
    //授权码
    private String payEmpowerNo;

    public String getMerchantStoreNo() {
        return merchantStoreNo;
    }

    public void setMerchantStoreNo(String merchantStoreNo) {
        this.merchantStoreNo = merchantStoreNo;
    }

    //门店编号
    private String merchantStoreNo;

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

}
