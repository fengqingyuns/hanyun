/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

import org.apache.commons.lang.StringUtils;

/**
 * 交易支付基础参数
 * @author liyinglong@hanyun.com
 * @date 2017年6月15日 下午3:20:08
 */
public class TradePayBaseReq extends TradeBaseReq{
    public static final String ORDER_DESC_DEFAULT = "商品服务消费";
    
    public static final String YEE_PRODUCT_NAME = "日用百货";
    // 订单金额 必填
    private Long orderAmount;
    // 订单需付金额 必填
    private Long payAmount;
    // 支付方式 必填
    private String payType;
    
    // 本次支付金额 必填
    private Long curPayAmount;
    // 本次优惠金额，比如抹零抹掉的金额 选填
    private Long curDiscountAmount;
    // 订单描述，最大32个字符，格式：门店名-门店类型或商品类型
    private String orderDesc;

    @Override
    public boolean checkCommonParam() {
        if(!super.checkCommonParam()){
            return false;
        }
        if(StringUtils.isBlank(getOrderDocumentId())
                || StringUtils.isBlank(payType)
                || StringUtils.isBlank(orderDesc)){
            return false;
        }
        if(orderAmount == null
                || orderAmount <= 0
                || payAmount == null
                || payAmount <= 0
                || curPayAmount == null
                || curPayAmount <= 0){
            return false;
        }
        return true;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getCurPayAmount() {
        return curPayAmount;
    }

    public void setCurPayAmount(Long curPayAmount) {
        this.curPayAmount = curPayAmount;
    }

    public String getOrderDesc() {
        // 暂统一，有长度限制
        return ORDER_DESC_DEFAULT;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Long getCurDiscountAmount() {
        return curDiscountAmount;
    }

    public void setCurDiscountAmount(Long curDiscountAmount) {
        this.curDiscountAmount = curDiscountAmount;
    }
}
