package com.hzcf.pojo.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IntegralTransDetail implements Serializable {
    private static final long serialVersionUID = 4912147939665581961L;
    private Integer id;

    private String detailOddNo;

    private Date buildTime;

    private String integralType;

    private String detailType;

    private BigDecimal integralNum;

    private String status;

    private Integer customerInfoId;

    private Integer realNameInfoId;

    private Date updateTime;

    private String transNo;

    private String channel;

    public IntegralTransDetail(Integer id, String detailOddNo, Date buildTime, String integralType, String detailType, BigDecimal integralNum, String status, Integer customerInfoId, Integer realNameInfoId, Date updateTime, String transNo, String channel) {
        this.id = id;
        this.detailOddNo = detailOddNo;
        this.buildTime = buildTime;
        this.integralType = integralType;
        this.detailType = detailType;
        this.integralNum = integralNum;
        this.status = status;
        this.customerInfoId = customerInfoId;
        this.realNameInfoId = realNameInfoId;
        this.updateTime = updateTime;
        this.transNo = transNo;
        this.channel = channel;
    }

    public IntegralTransDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetailOddNo() {
        return detailOddNo;
    }

    public void setDetailOddNo(String detailOddNo) {
        this.detailOddNo = detailOddNo == null ? null : detailOddNo.trim();
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getIntegralType() {
        return integralType;
    }

    public void setIntegralType(String integralType) {
        this.integralType = integralType == null ? null : integralType.trim();
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType == null ? null : detailType.trim();
    }

    public BigDecimal getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(BigDecimal integralNum) {
        this.integralNum = integralNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getCustomerInfoId() {
        return customerInfoId;
    }

    public void setCustomerInfoId(Integer customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    public Integer getRealNameInfoId() {
        return realNameInfoId;
    }

    public void setRealNameInfoId(Integer realNameInfoId) {
        this.realNameInfoId = realNameInfoId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }
}