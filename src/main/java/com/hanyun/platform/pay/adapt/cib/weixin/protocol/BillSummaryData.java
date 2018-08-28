/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

/**
 * 对账单汇总数据
 * @author liyinglong@hanyun.com
 * @date 2016年8月16日 下午5:04:21
 */
public class BillSummaryData {
    // 总交易笔数
    private Integer totalTradeCount;
    // 总金额 
    private Integer totalFee;
    // 总退款金额 
    private Integer totalRefundFee;
    // 总代金券或立减优惠退款金额
    private Integer totalCouponRefundFee;
    // 总手续费 
    private Integer totalCharges;
    
    public Integer getTotalTradeCount() {
        return totalTradeCount;
    }
    public void setTotalTradeCount(Integer totalTradeCount) {
        this.totalTradeCount = totalTradeCount;
    }
    public Integer getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }
    public Integer getTotalRefundFee() {
        return totalRefundFee;
    }
    public void setTotalRefundFee(Integer totalRefundFee) {
        this.totalRefundFee = totalRefundFee;
    }
    public Integer getTotalCouponRefundFee() {
        return totalCouponRefundFee;
    }
    public void setTotalCouponRefundFee(Integer totalCouponRefundFee) {
        this.totalCouponRefundFee = totalCouponRefundFee;
    }
    public Integer getTotalCharges() {
        return totalCharges;
    }
    public void setTotalCharges(Integer totalCharges) {
        this.totalCharges = totalCharges;
    }
    
}
