package com.hzcf.pojo.parameter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PartnershipCommissionSet {
    private Long partnershipId;

    private String enterprisEorganizationName;

    private Long insuranceCompanyId;

    private Long insProductId;

    private String computationalTerm;

    private String countingRules;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createdTime;

    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updatedTime;

    private String productName;

    private String productCode;

    private String status;

    private String insuranceCompanyName;
    
    private String  salesOrgId;

    public String getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public Long getPartnershipId() {
        return partnershipId;
    }

    public void setPartnershipId(Long partnershipId) {
        this.partnershipId = partnershipId;
    }

    public String getEnterprisEorganizationName() {
        return enterprisEorganizationName;
    }

    public void setEnterprisEorganizationName(String enterprisEorganizationName) {
        this.enterprisEorganizationName = enterprisEorganizationName == null ? null : enterprisEorganizationName.trim();
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public Long getInsProductId() {
        return insProductId;
    }

    public void setInsProductId(Long insProductId) {
        this.insProductId = insProductId;
    }

    public String getComputationalTerm() {
        return computationalTerm;
    }

    public void setComputationalTerm(String computationalTerm) {
        this.computationalTerm = computationalTerm == null ? null : computationalTerm.trim();
    }

    public String getCountingRules() {
        return countingRules;
    }

    public void setCountingRules(String countingRules) {
        this.countingRules = countingRules == null ? null : countingRules.trim();
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName == null ? null : insuranceCompanyName.trim();
    }
}