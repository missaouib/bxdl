package com.hzcf.pojo.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IntegralAccountCurrency implements Serializable {
    private static final long serialVersionUID = -3097933019456114711L;
    private Integer id;

    private BigDecimal totalIntegral;

    private BigDecimal availableIntegral;

    private BigDecimal usedIntegral;

    private BigDecimal frozenIntegral;

    private Integer realNameInfoId;

    private Date createTime;

    private Date updateTime;

    public IntegralAccountCurrency(Integer id, BigDecimal totalIntegral, BigDecimal availableIntegral, BigDecimal usedIntegral, BigDecimal frozenIntegral, Integer realNameInfoId, Date createTime, Date updateTime) {
        this.id = id;
        this.totalIntegral = totalIntegral;
        this.availableIntegral = availableIntegral;
        this.usedIntegral = usedIntegral;
        this.frozenIntegral = frozenIntegral;
        this.realNameInfoId = realNameInfoId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public IntegralAccountCurrency() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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