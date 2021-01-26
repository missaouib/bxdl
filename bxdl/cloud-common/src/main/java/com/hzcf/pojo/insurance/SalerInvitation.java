package com.hzcf.pojo.insurance;

import java.io.Serializable;
import java.util.Date;

public class SalerInvitation implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long invitedId;

    /**
    * 申请人姓名
    */
    private String personName;

    /**
    * 申请人手机号
    */
    private String personMobile;

    /**
    * 推荐人工号
    */
    private String tjSalerNo;

    /**
    * 担保人工号
    */
    private String dbSalerNo;

    /**
    * 申请时间
    */
    private String applyDate;

    /**
    * 认证时间
    */
    private String confirmDate;

    /**
    * 审核时间
    */
    private String checkDate;

    /**
    * 状态
    */
    private String checkStatus;

    /**
    * 备注
    */
    private String checkMark;
    

    /**
    * 备注
    */
    private String comChannel;

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

    public SalerInvitation() {
    	
    }

	public Long getInvitedId() {
		return invitedId;
	}

	public void setInvitedId(Long invitedId) {
		this.invitedId = invitedId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonMobile() {
		return personMobile;
	}

	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}

	public String getTjSalerNo() {
		return tjSalerNo;
	}

	public void setTjSalerNo(String tjSalerNo) {
		this.tjSalerNo = tjSalerNo;
	}

	public String getDbSalerNo() {
		return dbSalerNo;
	}

	public void setDbSalerNo(String dbSalerNo) {
		this.dbSalerNo = dbSalerNo;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckMark() {
		return checkMark;
	}

	public void setCheckMark(String checkMark) {
		this.checkMark = checkMark;
	}

	public String getComChannel() {
		return comChannel;
	}

	public void setComChannel(String comChannel) {
		this.comChannel = comChannel;
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
		return "SalerInvitation [invitedId=" + invitedId + ", personName=" + personName + ", personMobile="
				+ personMobile + ", tjSalerNo=" + tjSalerNo + ", dbSalerNo=" + dbSalerNo + ", applyDate=" + applyDate
				+ ", confirmDate=" + confirmDate + ", checkDate=" + checkDate + ", checkStatus=" + checkStatus
				+ ", checkMark=" + checkMark + ", comChannel=" + comChannel + ", createdBy=" + createdBy + ", createdTime=" + createdTime
				+ ", updatedBy=" + updatedBy + ", updatedTime=" + updatedTime + "]";
	}

	public SalerInvitation(Long invitedId, String personName, String personMobile, String tjSalerNo, String dbSalerNo,
			String applyDate, String confirmDate, String checkDate, String checkStatus, String checkMark,String comChannel,
			String createdBy, Date createdTime, String updatedBy, Date updatedTime) {
		super();
		this.invitedId = invitedId;
		this.personName = personName;
		this.personMobile = personMobile;
		this.tjSalerNo = tjSalerNo;
		this.dbSalerNo = dbSalerNo;
		this.applyDate = applyDate;
		this.confirmDate = confirmDate;
		this.checkDate = checkDate;
		this.checkStatus = checkStatus;
		this.checkMark = checkMark;
		this.comChannel = comChannel;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}
	
}