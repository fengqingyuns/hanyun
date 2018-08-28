package com.hanyun.platform.pay.domain;

import java.util.Date;

public class PayChnBillCheckHis {

    private Long id;

    private String channel;

    private String billClass;

    private Date checkDate;

    private Integer fileDlStatus;

    private Integer fileDlTimes;

    private Date fileDlBeginTime;

    private Date fileDlFinishTime;

    private Integer billCheckStatus;

    private Integer billCheckTimes;

    private Date billCheckBeginTime;

    private Date billCheckFinishTime;

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

    public String getBillClass() {
        return billClass;
    }

    public void setBillClass(String billClass) {
        this.billClass = billClass;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getFileDlStatus() {
        return fileDlStatus;
    }

    public void setFileDlStatus(Integer fileDlStatus) {
        this.fileDlStatus = fileDlStatus;
    }

    public Integer getFileDlTimes() {
        return fileDlTimes;
    }

    public void setFileDlTimes(Integer fileDlTimes) {
        this.fileDlTimes = fileDlTimes;
    }

    public Date getFileDlBeginTime() {
        return fileDlBeginTime;
    }

    public void setFileDlBeginTime(Date fileDlBeginTime) {
        this.fileDlBeginTime = fileDlBeginTime;
    }

    public Date getFileDlFinishTime() {
        return fileDlFinishTime;
    }

    public void setFileDlFinishTime(Date fileDlFinishTime) {
        this.fileDlFinishTime = fileDlFinishTime;
    }

    public Integer getBillCheckStatus() {
        return billCheckStatus;
    }

    public void setBillCheckStatus(Integer billCheckStatus) {
        this.billCheckStatus = billCheckStatus;
    }

    public Integer getBillCheckTimes() {
        return billCheckTimes;
    }

    public void setBillCheckTimes(Integer billCheckTimes) {
        this.billCheckTimes = billCheckTimes;
    }

    public Date getBillCheckBeginTime() {
        return billCheckBeginTime;
    }

    public void setBillCheckBeginTime(Date billCheckBeginTime) {
        this.billCheckBeginTime = billCheckBeginTime;
    }

    public Date getBillCheckFinishTime() {
        return billCheckFinishTime;
    }

    public void setBillCheckFinishTime(Date billCheckFinishTime) {
        this.billCheckFinishTime = billCheckFinishTime;
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