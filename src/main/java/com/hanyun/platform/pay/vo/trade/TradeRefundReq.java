/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

/**
 * 退款
 * @author liyinglong@hanyun.com
 * @date 2016年7月17日 下午3:30:49
 */
public class TradeRefundReq extends TradeBaseReq{
    /**
     * 退款模式, 必填：
     * 1：有单整单退款，
     * 2：有单部分退款-指定支付方式原路退回
     * 3：有单部分退款-不依赖原支付方式，在线下以现金方式退回, payType只能为"CASH"
     * 4: 无单退款, payType只能为"CASH"
     */
    private Integer refundMode;
    // 退款金额 必填
    private Long refundAmount;
    
    // 订单金额 必填, 无订单退款时填0
    private Long orderAmount;
    // 订单需付金额 必填, 无订单退款时填0
    private Long payAmount;
    // 支付方式 必填, 整单退款时可不填
    private String payType;
    
    @Override
    public boolean checkCommonParam() {
        if(!super.checkCommonParam()){
            return false;
        }
        if(orderAmount == null
                || orderAmount < 0
                || payAmount == null
                || payAmount < 0){
            return false;
        }
        return true;
    }
    
    public Integer getRefundMode() {
        return refundMode;
    }
    public void setRefundMode(Integer refundMode) {
        this.refundMode = refundMode;
    }
    public Long getRefundAmount() {
        return refundAmount;
    }
    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
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
    
}
