package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

public class SalesTitles implements Serializable{
	
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long titleId;

    /**
    * 员工id
    */
    private Long salesId;

    /**
    * 职称名称
    */
    private String title;

    /**
    * 证书编号
    */
    private String certificateNo;

    /**
    * 发证机关
    */
    private String awardOrg;

    /**
    * 获取日期
    */
    private String gotDate;

    /**
    * 生效日期
    */
    private String effectiveDate;

    /**
    * 失效日期
    */
    private String invalidDate;

    /**
    * 有效状态 0 有效 1 失效
    */
    private String titleStatus;

    /**
    * 备注
    */
    private String remark;

    /**
    * 类型 0 职称 1证书
    */
    private String titleType;

    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
    private Date createdTime;

    /**
    * 更新人
    */
    private String updatedBy;

    /**
    * 更新时间
    */
    private Date updatedTime;
    
    private String deleteFlag;

    public SalesTitles() {
    }

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getAwardOrg() {
		return awardOrg;
	}

	public void setAwardOrg(String awardOrg) {
		this.awardOrg = awardOrg;
	}

	public String getGotDate() {
		return gotDate;
	}

	public void setGotDate(String gotDate) {
		this.gotDate = gotDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getTitleStatus() {
		return titleStatus;
	}

	public void setTitleStatus(String titleStatus) {
		this.titleStatus = titleStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
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

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "SalesTitles [titleId=" + titleId + ", salesId=" + salesId + ", title=" + title + ", certificateNo="
				+ certificateNo + ", awardOrg=" + awardOrg + ", gotDate=" + gotDate + ", effectiveDate=" + effectiveDate
				+ ", invalidDate=" + invalidDate + ", titleStatus=" + titleStatus + ", remark=" + remark + ", titleType="
				+ titleType + ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", deleteFlag=" + deleteFlag + "]";
	}

	public SalesTitles(Long titleId, Long salesId, String title, String certificateNo, String awardOrg, String gotDate,
			String effectiveDate, String invalidDate, String titleStatus, String remark, String titleType, String createdBy,
			Date createdTime, String updatedBy, Date updatedTime, String deleteFlag) {
		super();
		this.titleId = titleId;
		this.salesId = salesId;
		this.title = title;
		this.certificateNo = certificateNo;
		this.awardOrg = awardOrg;
		this.gotDate = gotDate;
		this.effectiveDate = effectiveDate;
		this.invalidDate = invalidDate;
		this.titleStatus = titleStatus;
		this.remark = remark;
		this.titleType = titleType;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.deleteFlag = deleteFlag;
	}

}
