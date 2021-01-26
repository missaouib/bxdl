package com.hzcf.pojo.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CashTransDetail implements Serializable{
	
	private static final long serialVersionUID = 204658186892506093L;

    private Integer id;

    private Integer cashAccountId;

    private String detailOddNo;

    private Date buildTime;

    private String detailType;

    private BigDecimal amount;

    private String transStatus;

    private Date createTime;

    private Date updateTime;

    public CashTransDetail(Integer id, Integer cashAccountId, String detailOddNo, Date buildTime, String detailType, BigDecimal amount, String transStatus, Date createTime, Date updateTime) {
        this.id = id;
        this.cashAccountId = cashAccountId;
        this.detailOddNo = detailOddNo;
        this.buildTime = buildTime;
        this.detailType = detailType;
        this.amount = amount;
        this.transStatus = transStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public CashTransDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCashAccountId() {
        return cashAccountId;
    }

    public void setCashAccountId(Integer cashAccountId) {
        this.cashAccountId = cashAccountId;
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

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType == null ? null : detailType.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus == null ? null : transStatus.trim();
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