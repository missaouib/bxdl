package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

public class SalesEduJob implements Serializable{
	
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long eduId;

    /**
    * 员工id
    */
    private Long salesId;

    /**
    * 学校
    */
    private String eduName;

    /**
    * 开始日期
    */
    private String startDate;

    /**
    * 结束日期
    */
    private String endDate;

    /**
    * 学历
    */
    private String education;

    /**
    * 学位
    */
    private String academicDegree;

    /**
    * 成绩
    */
    private String achievement;

    /**
    * 职位
    */
    private String position;

    /**
    * 备注
    */
    private String remark;

    /**
    * 类型：1教育经历 2工作经历 3培训经历
    */
    private String tableType;

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

    public SalesEduJob() {
    	
    }

	public Long getEduId() {
		return eduId;
	}

	public void setEduId(Long eduId) {
		this.eduId = eduId;
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}

	public String getEduName() {
		return eduName;
	}

	public void setEduName(String eduName) {
		this.eduName = eduName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
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
		return "SalesEduJob [eduId=" + eduId + ", salesId=" + salesId + ", eduName=" + eduName + ", startDate="
				+ startDate + ", endDate=" + endDate + ", education=" + education + ", academicDegree=" + academicDegree
				+ ", achievement=" + achievement + ", position=" + position + ", remark=" + remark + ", tableType="
				+ tableType + ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", deleteFlag=" + deleteFlag + "]";
	}

	public SalesEduJob(Long eduId, Long salesId, String eduName, String startDate, String endDate, String education,
			String academicDegree, String achievement, String position, String remark, String tableType,
			String createdBy, Date createdTime, String updatedBy, Date updatedTime, String deleteFlag) {
		super();
		this.eduId = eduId;
		this.salesId = salesId;
		this.eduName = eduName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.education = education;
		this.academicDegree = academicDegree;
		this.achievement = achievement;
		this.position = position;
		this.remark = remark;
		this.tableType = tableType;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.deleteFlag = deleteFlag;
	}
	
}
