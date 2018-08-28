package com.hanyun.platform.pay.adapt.yeepay.model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.model
 * @Author: dewen.li
 * @Date: 2018-08-07 下午2:24
 */
public class YeepayConfigureBase {
    //平台编号
    private String platformId;

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

    //品牌编号
    private String brandId;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    //门店编号
    private String storeId;
}
