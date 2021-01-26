package com.hzcf.pojo.insurance;

import java.io.Serializable;
import java.util.Date;

public class SalesGrade implements Serializable {
	
 	private static final long serialVersionUID = 1L;

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

    /**
    * 主键
    */
    private Long salesGradeId;

    /**
    * 职级名称
    */
    private String salesGradeName;

    /**
    * 职级编码
    */
    private String salesGradeCode;

    /**
    * 所属职级序列
    */
    private Long rankSequenceId;

    /**
    * 所属职级序列
    */
    private String rankSequenceName;

    /**
    * 职级状态
    */
    private String salesGradeStatus;

    public SalesGrade() {
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

	public Long getSalesGradeId() {
		return salesGradeId;
	}

	public void setSalesGradeId(Long salesGradeId) {
		this.salesGradeId = salesGradeId;
	}

	public String getSalesGradeName() {
		return salesGradeName;
	}

	public void setSalesGradeName(String salesGradeName) {
		this.salesGradeName = salesGradeName;
	}

	public String getSalesGradeCode() {
		return salesGradeCode;
	}

	public void setSalesGradeCode(String salesGradeCode) {
		this.salesGradeCode = salesGradeCode;
	}

	public Long getRankSequenceId() {
		return rankSequenceId;
	}

	public void setRankSequenceId(Long rankSequenceId) {
		this.rankSequenceId = rankSequenceId;
	}

	public String getRankSequenceName() {
		return rankSequenceName;
	}

	public void setRankSequenceName(String rankSequenceName) {
		this.rankSequenceName = rankSequenceName;
	}

	public String getSalesGradeStatus() {
		return salesGradeStatus;
	}

	public void setSalesGradeStatus(String salesGradeStatus) {
		this.salesGradeStatus = salesGradeStatus;
	}

	@Override
	public String toString() {
		return "SalesGrade [createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", salesGradeId=" + salesGradeId + ", salesGradeName="
				+ salesGradeName + ", salesGradeCode=" + salesGradeCode + ", rankSequenceId=" + rankSequenceId
				+ ", rankSequenceName=" + rankSequenceName + ", salesGradeStatus=" + salesGradeStatus + "]";
	}

	public SalesGrade(String createdBy, Date createdTime, String updatedBy, Date updatedTime, Long salesGradeId,
			String salesGradeName, String salesGradeCode, Long rankSequenceId, String rankSequenceName,
			String salesGradeStatus) {
		super();
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.salesGradeId = salesGradeId;
		this.salesGradeName = salesGradeName;
		this.salesGradeCode = salesGradeCode;
		this.rankSequenceId = rankSequenceId;
		this.rankSequenceName = rankSequenceName;
		this.salesGradeStatus = salesGradeStatus;
	}	   
}