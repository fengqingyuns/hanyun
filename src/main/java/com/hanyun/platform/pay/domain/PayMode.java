package com.hanyun.platform.pay.domain;

import java.util.Date;

public class PayMode {

    private Long id;

    private String payType;

    private String typeName;

    private String typeDesc;
    
    private String typeCategory;

    private Integer settleType;
    
    private Integer mchFeeRateDef;

    private Integer mchFeeMaxDef;

    private Integer availStatus;

    private Date createTime;

    private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }

    public Integer getSettleType() {
		return settleType;
	}

	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	
    public Integer getMchFeeRateDef() {
        return mchFeeRateDef;
    }

    public void setMchFeeRateDef(Integer mchFeeRateDef) {
        this.mchFeeRateDef = mchFeeRateDef;
    }

    public Integer getMchFeeMaxDef() {
        return mchFeeMaxDef;
    }

    public void setMchFeeMaxDef(Integer mchFeeMaxDef) {
        this.mchFeeMaxDef = mchFeeMaxDef;
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
		return "PayMode [id=" + id + ", payType=" + payType + ", typeName=" + typeName + ", typeDesc=" + typeDesc
				+ ", settleType=" + settleType + ", availStatus=" + availStatus + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

    
    
}
