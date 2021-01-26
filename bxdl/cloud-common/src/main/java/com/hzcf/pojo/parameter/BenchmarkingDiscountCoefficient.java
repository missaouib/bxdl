package com.hzcf.pojo.parameter;

import java.io.Serializable;
import java.util.Date;

public class BenchmarkingDiscountCoefficient implements Serializable{
    /**
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String insuranceTypeId;

    private String status;

    private String maximum;

    private String minimum;

    private String minSign;

    private String maxSign;

    private String indexingCoefficient;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private String isNormal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuranceTypeId() {
        return insuranceTypeId;
    }

    public void setInsuranceTypeId(String insuranceTypeId) {
        this.insuranceTypeId = insuranceTypeId == null ? null : insuranceTypeId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }


    public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	public String getMinimum() {
		return minimum;
	}

	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}

	public String getMinSign() {
        return minSign;
    }

    public void setMinSign(String minSign) {
        this.minSign = minSign == null ? null : minSign.trim();
    }

    public String getMaxSign() {
        return maxSign;
    }

    public void setMaxSign(String maxSign) {
        this.maxSign = maxSign == null ? null : maxSign.trim();
    }

    public String getIndexingCoefficient() {
        return indexingCoefficient;
    }

    public void setIndexingCoefficient(String indexingCoefficient) {
        this.indexingCoefficient = indexingCoefficient == null ? null : indexingCoefficient.trim();
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

    public String getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(String isNormal) {
        this.isNormal = isNormal == null ? null : isNormal.trim();
    }
}