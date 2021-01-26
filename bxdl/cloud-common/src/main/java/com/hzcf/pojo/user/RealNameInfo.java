package com.hzcf.pojo.user;

import java.io.Serializable;
import java.util.Date;

public class RealNameInfo implements Serializable{
    private static final long serialVersionUID = 196313985634840915L;
    private Integer id;

    private String customerNo;

    private String crmMobile;

    private String customerName;

    private String sex;

    private Date brithday;

    private String cardType;

    private String cardNo;

    private String maritalStatus;

    private Date createTime;

    private Date updateTime;

    public RealNameInfo(Integer id, String customerNo, String crmMobile, String customerName, String sex, Date brithday, String cardType, String cardNo, String maritalStatus, Date createTime, Date updateTime) {
        this.id = id;
        this.customerNo = customerNo;
        this.crmMobile = crmMobile;
        this.customerName = customerName;
        this.sex = sex;
        this.brithday = brithday;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.maritalStatus = maritalStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public RealNameInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    public String getCrmMobile() {
        return crmMobile;
    }

    public void setCrmMobile(String crmMobile) {
        this.crmMobile = crmMobile == null ? null : crmMobile.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
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