package com.hanyun.platform.pay.domain;

import java.util.Date;

/**
 * <pre>
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * </pre>
 */
public class CibMerchant {

    private Long id;

    private String brandId;

    private String cibAppId;
    
    private String cibMchId;

    private String cibSecKey;
    
    private Integer availStatus;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCibAppId() {
        return cibAppId;
    }

    public void setCibAppId(String cibAppId) {
        this.cibAppId = cibAppId;
    }

    public String getCibMchId() {
        return cibMchId;
    }

    public void setCibMchId(String cibMchId) {
        this.cibMchId = cibMchId;
    }

    public String getCibSecKey() {
        return cibSecKey;
    }

    public void setCibSecKey(String cibSecKey) {
        this.cibSecKey = cibSecKey;
    }

    public Integer getAvailStatus() {
        return availStatus;
    }

    public void setAvailStatus(Integer availStatus) {
        this.availStatus = availStatus;
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

    @Override
    public String toString() {
        return "CibMerchant{" +
                "id=" + id +
                ", brandId='" + brandId + '\'' +
                ", cibMchId='" + cibMchId + '\'' +
                ", cibSecKey='" + cibSecKey + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}