package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalerQuitInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long quitId;

    /**
    * 员工编号
    */
    private Long salerId;

    /**
    * 离职备注
    */
    private String quitMark;

    /**
    * 离职去向
    */
    private String quitTo;

    /**
    * 离职日期
    */
    private String quitDate;

    /**
    * 保单交接人
    */
    private String jobHandoverId;

    /**
    * 关系网交接人
    */
    private String salerTreeHandoverId;

    /**
    * 关系网交接人关系网编号
    */
    private String treeHandoverToCode;

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

    public SalerQuitInfo() {
    }

	public Long getQuitId() {
		return quitId;
	}

	public void setQuitId(Long quitId) {
		this.quitId = quitId;
	}

	public Long getSalerId() {
		return salerId;
	}

	public void setSalerId(Long salerId) {
		this.salerId = salerId;
	}

	public String getQuitMark() {
		return quitMark;
	}

	public void setQuitMark(String quitMark) {
		this.quitMark = quitMark;
	}

	public String getQuitTo() {
		return quitTo;
	}

	public void setQuitTo(String quitTo) {
		this.quitTo = quitTo;
	}

	public String getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
	}

	public String getJobHandoverId() {
		return jobHandoverId;
	}

	public void setJobHandoverId(String jobHandoverId) {
		this.jobHandoverId = jobHandoverId;
	}

	public String getSalerTreeHandoverId() {
		return salerTreeHandoverId;
	}

	public void setSalerTreeHandoverId(String salerTreeHandoverId) {
		this.salerTreeHandoverId = salerTreeHandoverId;
	}

	public String getTreeHandoverToCode() {
		return treeHandoverToCode;
	}

	public void setTreeHandoverToCode(String treeHandoverToCode) {
		this.treeHandoverToCode = treeHandoverToCode;
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

	@Override
	public String toString() {
		return "SalerQuitInfo [quitId=" + quitId + ", salerId=" + salerId + ", quitMark=" + quitMark + ", quitTo="
				+ quitTo + ", quitDate=" + quitDate + ", jobHandoverId=" + jobHandoverId + ", salerTreeHandoverId="
				+ salerTreeHandoverId + ", treeHandoverToCode=" + treeHandoverToCode + ", createdBy=" + createdBy
				+ ", createdTime=" + createdTime + ", updatedBy=" + updatedBy + ", updatedTime=" + updatedTime + "]";
	}

	public SalerQuitInfo(Long quitId, Long salerId, String quitMark, String quitTo, String quitDate,
			String jobHandoverId, String salerTreeHandoverId, String treeHandoverToCode, String createdBy,
			Date createdTime, String updatedBy, Date updatedTime) {
		super();
		this.quitId = quitId;
		this.salerId = salerId;
		this.quitMark = quitMark;
		this.quitTo = quitTo;
		this.quitDate = quitDate;
		this.jobHandoverId = jobHandoverId;
		this.salerTreeHandoverId = salerTreeHandoverId;
		this.treeHandoverToCode = treeHandoverToCode;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}

}
