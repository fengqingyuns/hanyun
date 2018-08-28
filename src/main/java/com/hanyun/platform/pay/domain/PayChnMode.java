package com.hanyun.platform.pay.domain;

import java.util.Date;

public class PayChnMode {

	private Long id;

	private String payType;

	private String channel;

	private Integer settleType;

    private Integer chnFeeRate;

    private Integer chnFeeMax;

	private Integer srvStatus;

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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getSettleType() {
		return settleType;
	}

	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}

	public Integer getChnFeeRate() {
        return chnFeeRate;
    }

    public void setChnFeeRate(Integer chnFeeRate) {
        this.chnFeeRate = chnFeeRate;
    }

    public Integer getChnFeeMax() {
        return chnFeeMax;
    }

    public void setChnFeeMax(Integer chnFeeMax) {
        this.chnFeeMax = chnFeeMax;
    }

    public Integer getSrvStatus() {
		return srvStatus;
	}

	public void setSrvStatus(Integer srvStatus) {
		this.srvStatus = srvStatus;
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


	

}