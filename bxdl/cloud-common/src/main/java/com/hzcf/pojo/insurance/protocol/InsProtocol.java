package com.hzcf.pojo.insurance.protocol;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsProtocol {
    private Long protocolId;

    private String protocolCode;

    private String protocolName;

    private Long insuranceCompanyId;

    private Long insuranceOrgId;

    private Long salesOrgId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date signingDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date effectiveDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date terminationDate;

    private String protocolStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;

    private String updatedBy;

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolCode() {
        return protocolCode;
    }

    public void setProtocolCode(String protocolCode) {
        this.protocolCode = protocolCode == null ? null : protocolCode.trim();
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName == null ? null : protocolName.trim();
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public Long getInsuranceOrgId() {
        return insuranceOrgId;
    }

    public void setInsuranceOrgId(Long insuranceOrgId) {
        this.insuranceOrgId = insuranceOrgId;
    }

    public Long getSalesOrgId() {
        return salesOrgId;
    }

    public void setSalesOrgId(Long salesOrgId) {
        this.salesOrgId = salesOrgId;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getProtocolStatus() {
        return protocolStatus;
    }

    public void setProtocolStatus(String protocolStatus) {
        this.protocolStatus = protocolStatus == null ? null : protocolStatus.trim();
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }
}