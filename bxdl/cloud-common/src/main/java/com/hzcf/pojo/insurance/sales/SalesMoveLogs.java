package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalesMoveLogs implements Serializable{
	
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long moveId;

    /**
    * 员工工号
    */
    private Long salerId;

    /**
    * 员工姓名
    */
    private String salerName;

    /**
    * 原组织机构
    */
    private String preOrgId;

    /**
    * 原销售团队
    */
    private String preTeamId;

    /**
    * 原职级序列
    */
    private String preRankSeqId;

    /**
    * 原职级
    */
    private String preSalesGradeId;

    /**
    * 新组织机构
    */
    private String nextOrgId;

    /**
    * 新销售团队
    */
    private String nextTeamId;

    /**
    * 新职级序列
    */
    private String nextRankSeqId;

    /**
    * 新销售职级
    */
    private String nextSalesGradeId;
    
    /**
    * 异动前育成人
    */
    private Long ycSalerId;
    
    /**
    * 异动前推荐人
    */
    private Long tjSalerId;

    /**
    * 转移资料
    */
    private String moveFile;
    
    /**
    * 转移资料
    */
    private String moveMark;

    /**
    * 调整时间
    */
    private String moveDate;

    /**
    * 是否执行
    */
    private String checkStatus;
    
    /**
     * 是否执行
     */
     private String checkMark;
    
    /**
    * 是否执行
    */
    private String changeFlag;

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

    public SalesMoveLogs() {
    	
    }

	public Long getMoveId() {
		return moveId;
	}

	public void setMoveId(Long moveId) {
		this.moveId = moveId;
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

	public String getPreOrgId() {
		return preOrgId;
	}

	public void setPreOrgId(String preOrgId) {
		this.preOrgId = preOrgId;
	}

	public String getPreTeamId() {
		return preTeamId;
	}

	public void setPreTeamId(String preTeamId) {
		this.preTeamId = preTeamId;
	}

	public String getPreRankSeqId() {
		return preRankSeqId;
	}

	public void setPreRankSeqId(String preRankSeqId) {
		this.preRankSeqId = preRankSeqId;
	}

	public String getPreSalesGradeId() {
		return preSalesGradeId;
	}

	public void setPreSalesGradeId(String preSalesGradeId) {
		this.preSalesGradeId = preSalesGradeId;
	}

	public String getNextOrgId() {
		return nextOrgId;
	}

	public void setNextOrgId(String nextOrgId) {
		this.nextOrgId = nextOrgId;
	}

	public String getNextTeamId() {
		return nextTeamId;
	}

	public void setNextTeamId(String nextTeamId) {
		this.nextTeamId = nextTeamId;
	}

	public String getNextRankSeqId() {
		return nextRankSeqId;
	}

	public void setNextRankSeqId(String nextRankSeqId) {
		this.nextRankSeqId = nextRankSeqId;
	}

	public String getNextSalesGradeId() {
		return nextSalesGradeId;
	}

	public void setNextSalesGradeId(String nextSalesGradeId) {
		this.nextSalesGradeId = nextSalesGradeId;
	}

	public Long getYcSalerId() {
		return ycSalerId;
	}

	public void setYcSalerId(Long ycSalerId) {
		this.ycSalerId = ycSalerId;
	}

	public Long getTjSalerId() {
		return tjSalerId;
	}

	public void setTjSalerId(Long tjSalerId) {
		this.tjSalerId = tjSalerId;
	}

	public String getMoveFile() {
		return moveFile;
	}

	public void setMoveFile(String moveFile) {
		this.moveFile = moveFile;
	}

	public String getMoveMark() {
		return moveMark;
	}

	public void setMoveMark(String moveMark) {
		this.moveMark = moveMark;
	}

	public String getMoveDate() {
		return moveDate;
	}

	public void setMoveDate(String moveDate) {
		this.moveDate = moveDate;
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

	public String getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
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
		return "SalesMoveLogs [moveId=" + moveId + ", salerId=" + salerId + ", salerName=" + salerName + ", preOrgId="
				+ preOrgId + ", preTeamId=" + preTeamId + ", preRankSeqId=" + preRankSeqId + ", preSalesGradeId="
				+ preSalesGradeId + ", nextOrgId=" + nextOrgId + ", nextTeamId=" + nextTeamId + ", nextRankSeqId="
				+ nextRankSeqId + ", nextSalesGradeId=" + nextSalesGradeId + ", ycSalerId=" + ycSalerId + ", tjSalerId="
				+ tjSalerId + ", moveFile=" + moveFile + ", moveMark=" + moveMark + ", moveDate=" + moveDate
				+ ", checkStatus=" + checkStatus + ", checkMark=" + checkMark + ", changeFlag=" + changeFlag
				+ ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + "]";
	}

	public SalesMoveLogs(Long moveId, Long salerId, String salerName, String preOrgId, String preTeamId,
			String preRankSeqId, String preSalesGradeId, String nextOrgId, String nextTeamId, String nextRankSeqId,
			String nextSalesGradeId, Long ycSalerId, Long tjSalerId, String moveFile, String moveMark, String moveDate,
			String checkStatus, String checkMark, String changeFlag, String createdBy, Date createdTime,
			String updatedBy, Date updatedTime) {
		super();
		this.moveId = moveId;
		this.salerId = salerId;
		this.salerName = salerName;
		this.preOrgId = preOrgId;
		this.preTeamId = preTeamId;
		this.preRankSeqId = preRankSeqId;
		this.preSalesGradeId = preSalesGradeId;
		this.nextOrgId = nextOrgId;
		this.nextTeamId = nextTeamId;
		this.nextRankSeqId = nextRankSeqId;
		this.nextSalesGradeId = nextSalesGradeId;
		this.ycSalerId = ycSalerId;
		this.tjSalerId = tjSalerId;
		this.moveFile = moveFile;
		this.moveMark = moveMark;
		this.moveDate = moveDate;
		this.checkStatus = checkStatus;
		this.checkMark = checkMark;
		this.changeFlag = changeFlag;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}	
	
}
