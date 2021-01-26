package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalerBlackInfo implements Serializable{

	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long blackId;

    /**
    * 员工工号
    */
    private Long salerId;

    /**
    * 员工姓名
    */
    private String salerName;

    /**
    * 加入日期
    */
    private String blackDate;

    /**
    * 加入原因
    */
    private String blackReason;

    /**
    * 加黑备注
    */
    private String blackMark;

    /**
    * 当前状态
    */
    private String blackStatus;

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


    public SalerBlackInfo() {
    }

    public Long getBlackId() {
        return blackId;
    }

    public void setBlackId(Long blackId) {
        this.blackId = blackId;
    }

    public Long getSalerId() {
        return salerId;
    }

    public void setSalerId(Long salerId) {
        this.salerId = salerId;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getBlackDate() {
        return blackDate;
    }

    public void setBlackDate(String blackDate) {
        this.blackDate = blackDate;
    }

    public String getBlackReason() {
        return blackReason;
    }

    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason;
    }

    public String getBlackMark() {
        return blackMark;
    }

    public void setBlackMark(String blackMark) {
        this.blackMark = blackMark;
    }

    public String getBlackStatus() {
        return blackStatus;
    }

    public void setBlackStatus(String blackStatus) {
        this.blackStatus = blackStatus;
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
		return "SalerBlackInfo [blackId=" + blackId + ", salerId=" + salerId + ", salerName=" + salerName
				+ ", blackDate=" + blackDate + ", blackReason=" + blackReason + ", blackMark=" + blackMark
				+ ", blackStatus=" + blackStatus + ", createdBy=" + createdBy + ", createdTime=" + createdTime
				+ ", updatedBy=" + updatedBy + ", updatedTime=" + updatedTime + "]";
	}

	public SalerBlackInfo(Long blackId, Long salerId, String salerName, String blackDate, String blackReason,
			String blackMark, String blackStatus, String createdBy, Date createdTime, String updatedBy,
			Date updatedTime) {
		super();
		this.blackId = blackId;
		this.salerId = salerId;
		this.salerName = salerName;
		this.blackDate = blackDate;
		this.blackReason = blackReason;
		this.blackMark = blackMark;
		this.blackStatus = blackStatus;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}	
    
}
