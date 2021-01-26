package com.hzcf.pojo.product;

import java.io.Serializable;
import java.util.Date;

public class InsuranceTypeMapping implements Serializable{
	private static final long serialVersionUID = 3729217062294414425L;

	private Long insuranceTypeId;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Long insuranceCompanyId;

    private String insuranceTypeName;

    private String insuranceTypeCode;

    private Long parentInsuranceTypeId;

    private String sysParameter;
    
    private String isNormal;
    

    public String getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}

	public Long getInsuranceTypeId() {
        return insuranceTypeId;
    }

    public void setInsuranceTypeId(Long insuranceTypeId) {
        this.insuranceTypeId = insuranceTypeId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public String getInsuranceTypeName() {
        return insuranceTypeName;
    }

    public void setInsuranceTypeName(String insuranceTypeName) {
        this.insuranceTypeName = insuranceTypeName == null ? null : insuranceTypeName.trim();
    }

    public String getInsuranceTypeCode() {
        return insuranceTypeCode;
    }

    public void setInsuranceTypeCode(String insuranceTypeCode) {
        this.insuranceTypeCode = insuranceTypeCode == null ? null : insuranceTypeCode.trim();
    }

    public Long getParentInsuranceTypeId() {
        return parentInsuranceTypeId;
    }

    public void setParentInsuranceTypeId(Long parentInsuranceTypeId) {
        this.parentInsuranceTypeId = parentInsuranceTypeId;
    }

    public String getSysParameter() {
        return sysParameter;
    }

    public void setSysParameter(String sysParameter) {
        this.sysParameter = sysParameter == null ? null : sysParameter.trim();
    }
}