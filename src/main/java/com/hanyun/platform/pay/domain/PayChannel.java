package com.hanyun.platform.pay.domain;

import java.util.Date;
import java.util.List;

public class PayChannel {

    private Long id;

    private String channel;

    private String chnName;

    private String chnDesc;

    private Integer availStatus;

    private Date createTime;

    private Date updateTime;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getChnDesc() {
        return chnDesc;
    }

    public void setChnDesc(String chnDesc) {
        this.chnDesc = chnDesc;
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
		return "PayChannel [id=" + id + ", channel=" + channel + ", chnName=" + chnName + ", chnDesc=" + chnDesc
				+ ", availStatus=" + availStatus + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
    
}