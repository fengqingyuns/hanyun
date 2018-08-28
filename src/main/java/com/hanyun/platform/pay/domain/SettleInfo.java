package com.hanyun.platform.pay.domain;

import java.util.Date;

public class SettleInfo {

    private Long id;

    private String merchantId;

    private String brandId;

    private String storeId;
    
    private Integer merchantType;
    private Integer settleLevel;
    private Integer uniformSettle;

    private Integer settleCircle;

    private Date lastSettleTime;
    
    private Date lastStateTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public Integer getSettleLevel() {
        return settleLevel;
    }

    public void setSettleLevel(Integer settleLevel) {
        this.settleLevel = settleLevel;
    }

    public Integer getUniformSettle() {
        return uniformSettle;
    }

    public void setUniformSettle(Integer uniformSettle) {
        this.uniformSettle = uniformSettle;
    }

    public Integer getSettleCircle() {
        return settleCircle;
    }

    public void setSettleCircle(Integer settleCircle) {
        this.settleCircle = settleCircle;
    }

    public Date getLastSettleTime() {
        return lastSettleTime;
    }

    public void setLastSettleTime(Date lastSettleTime) {
        this.lastSettleTime = lastSettleTime;
    }

    public Date getLastStateTime() {
        return lastStateTime;
    }

    public void setLastStateTime(Date lastStateTime) {
        this.lastStateTime = lastStateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}