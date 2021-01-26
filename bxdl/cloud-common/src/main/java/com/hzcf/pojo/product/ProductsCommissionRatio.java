package com.hzcf.pojo.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductsCommissionRatio implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6286700689190557543L;

	private Long productsRatioId;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Long insuranceProductId;

    private String insuranceProductCode;

    private String insurancePeriodMin;

    private String insurancePeriodMax;

    private String renewPeriodMin;

    private String renewPeriodMax;

    private BigDecimal valueCommissionCoefficient;

    private BigDecimal inStandardCommissionCoefficient;

    private BigDecimal outStandardCommissionCoefficient;
    
    private BigDecimal indexingCoefficient;

    private String productsName;

    private String productsCode;

    private String productsStatus;

    public Long getProductsRatioId() {
        return productsRatioId;
    }

    public void setProductsRatioId(Long productsRatioId) {
        this.productsRatioId = productsRatioId;
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

    public Long getInsuranceProductId() {
        return insuranceProductId;
    }

    public void setInsuranceProductId(Long insuranceProductId) {
        this.insuranceProductId = insuranceProductId;
    }

    public String getInsuranceProductCode() {
        return insuranceProductCode;
    }

    public void setInsuranceProductCode(String insuranceProductCode) {
        this.insuranceProductCode = insuranceProductCode == null ? null : insuranceProductCode.trim();
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

    public BigDecimal getValueCommissionCoefficient() {
        return valueCommissionCoefficient;
    }

    public void setValueCommissionCoefficient(BigDecimal valueCommissionCoefficient) {
        this.valueCommissionCoefficient = valueCommissionCoefficient;
    }

    public BigDecimal getInStandardCommissionCoefficient() {
        return inStandardCommissionCoefficient;
    }

    public void setInStandardCommissionCoefficient(BigDecimal inStandardCommissionCoefficient) {
        this.inStandardCommissionCoefficient = inStandardCommissionCoefficient;
    }

    public BigDecimal getOutStandardCommissionCoefficient() {
        return outStandardCommissionCoefficient;
    }

    public void setOutStandardCommissionCoefficient(BigDecimal outStandardCommissionCoefficient) {
        this.outStandardCommissionCoefficient = outStandardCommissionCoefficient;
    }

    public BigDecimal getIndexingCoefficient() {
		return indexingCoefficient;
	}

	public void setIndexingCoefficient(BigDecimal indexingCoefficient) {
		this.indexingCoefficient = indexingCoefficient;
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

    public String getProductsStatus() {
        return productsStatus;
    }

    public void setProductsStatus(String productsStatus) {
        this.productsStatus = productsStatus == null ? null : productsStatus.trim();
    }
}