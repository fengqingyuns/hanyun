package com.hanyun.platform.pay.adapt.yeepay;

/**
 * 
 * @date 2018年8月8日
 * 
 * @apiDefine Common 支付相关
 * @author litao@hanyun.com
 */
public class YeepaySecretKeyConfig {
	
	private String id;
	//平台ID
	private String platformId;
	//品牌ID
	private String brandId;
	//门店ID
	private String storeId;
	//支付商编
	private String payNo;
	//支付秘钥
	private String payKey;
	//创建时间
	private String createTime;
	//修改时间
	private String modifyTime;
	//状态：0-正常，1-停用
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
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
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getPayKey() {
		return payKey;
	}
	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
