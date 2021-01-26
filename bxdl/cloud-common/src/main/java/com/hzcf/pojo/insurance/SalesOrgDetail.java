package com.hzcf.pojo.insurance;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
*  sales_org_detail
* @author yaolong 2019-06-18
*/
public class SalesOrgDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createdTime;

    /**
    * 更新人
    */
    private String updatedBy;

    /**
    * 更新时间
    */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updatedTime;

    /**
    * 销售组织主键
    */
    private Long salesOrgId;

    /**
    * 公司发展历程
    */
    private String developmentHistory;

    /**
    * 公司重要资质
    */
    private String qualifications;

    /**
    * 公司近年经营指标
    */
    private String operatingIndicators;

    /**
    * 人员优势
    */
    private String personnelAdvantage;

    /**
    * 项目优势
    */
    private String projectAdvantage;

    public SalesOrgDetail() {
    }

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Long getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(Long salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public String getDevelopmentHistory() {
		return developmentHistory;
	}

	public void setDevelopmentHistory(String developmentHistory) {
		this.developmentHistory = developmentHistory;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getOperatingIndicators() {
		return operatingIndicators;
	}

	public void setOperatingIndicators(String operatingIndicators) {
		this.operatingIndicators = operatingIndicators;
	}

	public String getPersonnelAdvantage() {
		return personnelAdvantage;
	}

	public void setPersonnelAdvantage(String personnelAdvantage) {
		this.personnelAdvantage = personnelAdvantage;
	}

	public String getProjectAdvantage() {
		return projectAdvantage;
	}

	public void setProjectAdvantage(String projectAdvantage) {
		this.projectAdvantage = projectAdvantage;
	}

	

	@Override
	public String toString() {
		return "SalesOrgDetail [createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", salesOrgId=" + salesOrgId + ", developmentHistory="
				+ developmentHistory + ", qualifications=" + qualifications + ", operatingIndicators="
				+ operatingIndicators + ", personnelAdvantage=" + personnelAdvantage + ", projectAdvantage="
				+ projectAdvantage + "]";
	}

	public SalesOrgDetail(String createdBy, Date createdTime, String updatedBy, Date updatedTime, Long salesOrgId,
			String developmentHistory, String qualifications, String operatingIndicators, String personnelAdvantage,
			String projectAdvantage) {
		super();
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.salesOrgId = salesOrgId;
		this.developmentHistory = developmentHistory;
		this.qualifications = qualifications;
		this.operatingIndicators = operatingIndicators;
		this.personnelAdvantage = personnelAdvantage;
		this.projectAdvantage = projectAdvantage;
	}

}
