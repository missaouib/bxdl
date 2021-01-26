package com.hzcf.pojo.user;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable{
    private static final long serialVersionUID = 4645991719224965264L;
    private Integer id;

    private String userNo;

    private String userName;

    private String channel;

    private String customerStatus;

    private Date registerTime;

    private Integer realNameInfoId;

    private Date createTime;

    private Date updateTime;

    public UserInfo(Integer id, String userNo, String userName, String channel, String customerStatus, Date registerTime, Integer realNameInfoId, Date createTime, Date updateTime) {
        this.id = id;
        this.userNo = userNo;
        this.userName = userName;
        this.channel = channel;
        this.customerStatus = customerStatus;
        this.registerTime = registerTime;
        this.realNameInfoId = realNameInfoId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus == null ? null : customerStatus.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getRealNameInfoId() {
        return realNameInfoId;
    }

    public void setRealNameInfoId(Integer realNameInfoId) {
        this.realNameInfoId = realNameInfoId;
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