/**
 * 
 */
package com.hanyun.platform.pay.vo.trade;

import org.apache.commons.lang.StringUtils;

/**
 * 交易基础通用参数
 * @author liyinglong@hanyun.com
 * @date 2017年6月15日 上午11:23:42
 */
public class TradeBaseReq {
    // 品牌编号 必填
    private String brandId;
    // 门店编号 必填
    private String storeId;
    // 源订单编号 必填
    private String orderId;
    // 源订单显示编号 必填
    private String orderDocumentId;
    // 源订单时间 必填
    private String orderTime;
        
    // 来源类型 必填
    private String sourceType;
    // 终端设备号 必填
    private String terminalDeviceNo;
    // 终端IP地址 必填
    private String terminalIp;
    // 操作用户信息 必填
    private String operateUser;
    
    /**
     * 检查通用参数
     * @return
     */
    public boolean checkCommonParam(){
        if(StringUtils.isBlank(brandId)
                || StringUtils.isBlank(storeId)
                || StringUtils.isBlank(orderId)
                || StringUtils.isBlank(orderTime)
                || StringUtils.isBlank(sourceType)
                || StringUtils.isBlank(terminalDeviceNo)
                || StringUtils.isBlank(terminalIp)
                || StringUtils.isBlank(operateUser)){
            return false;
        }
        
        return true;
    }
    
    public String getBrandId() {
        return brandId;
    }
    
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderDocumentId() {
        return orderDocumentId;
    }
    public void setOrderDocumentId(String orderDocumentId) {
        this.orderDocumentId = orderDocumentId;
    }
    public String getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSourceType() {
        return sourceType;
    }
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
    public String getTerminalDeviceNo() {
        return terminalDeviceNo;
    }
    public void setTerminalDeviceNo(String terminalDeviceNo) {
        this.terminalDeviceNo = terminalDeviceNo;
    }
    public String getTerminalIp() {
        return terminalIp;
    }
    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }
    public String getOperateUser() {
        return operateUser;
    }
    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }
    
    
}
