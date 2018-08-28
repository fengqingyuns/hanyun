package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-10 上午9:25
 */
public class CardYeePayReq {
    //绑卡ID,当商户传入的payTool=BINDCARD时，此项必传
    private String bindCardId;
    //指定银行
    private String bankCode;
}
