package com.hzcf.pojo.parameter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PartnershipCommissionSub {
    private Long partnershipSubId;

    private Long partnershipId;

    private String insurancePeriodMin;

    private String insurancePeriodMax;

    private String renewPeriodMin;

    private String renewPeriodMax;

    private String productsName;

    private String productsCode;

    private String firstAnnualRate;

    private String secondAnnualRate;

    private String thirdAnnualRate;

    private String fourthAnnualRate;

    private String fifthAnnualRate;

    private String sixthAnnualRate;

    private String createdBy;
    
    private Long salesOrgId;
    
    public Long getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(Long salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createdTime;

    private String updatedBy;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updatedTime;

    public Long getPartnershipSubId() {
        return partnershipSubId;
    }

    public void setPartnershipSubId(Long partnershipSubId) {
        this.partnershipSubId = partnershipSubId;
    }

    public Long getPartnershipId() {
        return partnershipId;
    }

    public void setPartnershipId(Long partnershipId) {
        this.partnershipId = partnershipId;
    }

    public String getInsurancePeriodMin() {
        return insurancePeriodMin;
    }

    public void setInsurancePeriodMin(String insurancePeriodMin) {
        this.insurancePeriodMin = insurancePeriodMin == null ? null : insurancePeriodMin.trim();
    }

    public String getInsurancePeriodMax() {
        return insurancePeriodMax;
    }

    public void setInsurancePeriodMax(String insurancePeriodMax) {
        this.insurancePeriodMax = insurancePeriodMax == null ? null : insurancePeriodMax.trim();
    }

    public String getRenewPeriodMin() {
        return renewPeriodMin;
    }

    public void setRenewPeriodMin(String renewPeriodMin) {
        this.renewPeriodMin = renewPeriodMin == null ? null : renewPeriodMin.trim();
    }

    public String getRenewPeriodMax() {
        return renewPeriodMax;
    }

    public void setRenewPeriodMax(String renewPeriodMax) {
        this.renewPeriodMax = renewPeriodMax == null ? null : renewPeriodMax.trim();
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName == null ? null : productsName.trim();
    }

    public String getProductsCode() {
        return productsCode;
    }

    public void setProductsCode(String productsCode) {
        this.productsCode = productsCode == null ? null : productsCode.trim();
    }

    public String getFirstAnnualRate() {
        return firstAnnualRate;
    }

    public void setFirstAnnualRate(String firstAnnualRate) {
        this.firstAnnualRate = firstAnnualRate == null ? null : firstAnnualRate.trim();
    }

    public String getSecondAnnualRate() {
        return secondAnnualRate;
    }

    public void setSecondAnnualRate(String secondAnnualRate) {
        this.secondAnnualRate = secondAnnualRate == null ? null : secondAnnualRate.trim();
    }

    public String getThirdAnnualRate() {
        return thirdAnnualRate;
    }

    public void setThirdAnnualRate(String thirdAnnualRate) {
        this.thirdAnnualRate = thirdAnnualRate == null ? null : thirdAnnualRate.trim();
    }

    public String getFourthAnnualRate() {
        return fourthAnnualRate;
    }

    public void setFourthAnnualRate(String fourthAnnualRate) {
        this.fourthAnnualRate = fourthAnnualRate == null ? null : fourthAnnualRate.trim();
    }

    public String getFifthAnnualRate() {
        return fifthAnnualRate;
    }

    public void setFifthAnnualRate(String fifthAnnualRate) {
        this.fifthAnnualRate = fifthAnnualRate == null ? null : fifthAnnualRate.trim();
    }

    public String getSixthAnnualRate() {
        return sixthAnnualRate;
    }

    public void setSixthAnnualRate(String sixthAnnualRate) {
        this.sixthAnnualRate = sixthAnnualRate == null ? null : sixthAnnualRate.trim();
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
}