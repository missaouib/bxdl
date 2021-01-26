package com.hzcf.pojo.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IntegralAccountOwn implements Serializable {
    private static final long serialVersionUID = -7331057606553491115L;
    private Integer id;

    private Integer userInfoId;

    private BigDecimal totalIntegral;

    private BigDecimal availableIntegral;

    private BigDecimal usedIntegral;

    private BigDecimal frozenIntegral;

    private String accountStatus;

    private Date createTime;

    private Date updateTime;

    public IntegralAccountOwn(Integer id, Integer userInfoId, BigDecimal totalIntegral, BigDecimal availableIntegral, BigDecimal usedIntegral, BigDecimal frozenIntegral, String accountStatus, Date createTime, Date updateTime) {
        this.id = id;
        this.userInfoId = userInfoId;
        this.totalIntegral = totalIntegral;
        this.availableIntegral = availableIntegral;
        this.usedIntegral = usedIntegral;
        this.frozenIntegral = frozenIntegral;
        this.accountStatus = accountStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public IntegralAccountOwn() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public BigDecimal getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(BigDecimal totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public BigDecimal getAvailableIntegral() {
        return availableIntegral;
    }

    public void setAvailableIntegral(BigDecimal availableIntegral) {
        this.availableIntegral = availableIntegral;
    }

    public BigDecimal getUsedIntegral() {
        return usedIntegral;
    }

    public void setUsedIntegral(BigDecimal usedIntegral) {
        this.usedIntegral = usedIntegral;
    }

    public BigDecimal getFrozenIntegral() {
        return frozenIntegral;
    }

    public void setFrozenIntegral(BigDecimal frozenIntegral) {
        this.frozenIntegral = frozenIntegral;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
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