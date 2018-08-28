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
public class CibMerchantStore {

    private Long id;

    private String brandId;

    private String storeId;

    private String cibMchId;

    private String cibStoreAppid;

    private String cibStoreName;

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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCibMchId() {
        return cibMchId;
    }

    public void setCibMchId(String cibMchId) {
        this.cibMchId = cibMchId;
    }

    public String getCibStoreAppid() {
        return cibStoreAppid;
    }

    public void setCibStoreAppid(String cibStoreAppid) {
        this.cibStoreAppid = cibStoreAppid;
    }

    public String getCibStoreName() {
        return cibStoreName;
    }

    public void setCibStoreName(String cibStoreName) {
        this.cibStoreName = cibStoreName;
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
        return "CibMerchantStore{" +
                "id=" + id +
                ", brandId='" + brandId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", cibMchId='" + cibMchId + '\'' +
                ", cibStoreAppid='" + cibStoreAppid + '\'' +
                ", cibStoreName='" + cibStoreName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}