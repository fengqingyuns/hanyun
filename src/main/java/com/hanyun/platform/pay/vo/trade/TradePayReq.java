/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 支付
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午3:35:40
 */
public class TradePayReq extends TradePayBaseReq{
    // 扫码支付授权码, 设备读取用户展示的条码或者二维码信息
    private String authCode;
    
    // 刷卡POS交易单号
    private String posCashierTradeNo;
    // 刷卡POS凭证号
    private String posVoucherNo;
    // 刷卡POS参考号
    private String posRefNo;
    // 刷卡POS卡类型
    private String cardType;
    
    public String getAuthCode() {
        return authCode;
    }
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    public String getPosCashierTradeNo() {
        return posCashierTradeNo;
    }
    public void setPosCashierTradeNo(String posCashierTradeNo) {
        this.posCashierTradeNo = posCashierTradeNo;
    }
    public String getPosVoucherNo() {
        return posVoucherNo;
    }
    public void setPosVoucherNo(String posVoucherNo) {
        this.posVoucherNo = posVoucherNo;
    }
    public String getPosRefNo() {
        return posRefNo;
    }
    public void setPosRefNo(String posRefNo) {
        this.posRefNo = posRefNo;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
}
