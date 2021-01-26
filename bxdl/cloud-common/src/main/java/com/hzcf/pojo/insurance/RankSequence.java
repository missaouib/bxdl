package com.hzcf.pojo.insurance;

import java.io.Serializable;
import java.util.Date;

public class RankSequence implements Serializable {
	
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
    private Long sequenceId;

    /**
    * 序列编码
    */
    private String sequenceCode;

    /**
    * 职级序列名称
    */
    private String sequenceName;

    /**
    * 描述
    */
    private String remark;

    /**
    * 状态
    */
    private String sequenceStatus;

    public RankSequence() {
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

	public Long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSequenceStatus() {
		return sequenceStatus;
	}

	public void setSequenceStatus(String sequenceStatus) {
		this.sequenceStatus = sequenceStatus;
	}

	@Override
	public String toString() {
		return "RankSequence [createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", sequenceId=" + sequenceId + ", sequenceCode=" + sequenceCode
				+ ", sequenceName=" + sequenceName + ", remark=" + remark + ", sequenceStatus=" + sequenceStatus + "]";
	}

	public RankSequence(String createdBy, Date createdTime, String updatedBy, Date updatedTime, Long sequenceId,
			String sequenceCode, String sequenceName, String remark, String sequenceStatus) {
		super();
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.sequenceId = sequenceId;
		this.sequenceCode = sequenceCode;
		this.sequenceName = sequenceName;
		this.remark = remark;
		this.sequenceStatus = sequenceStatus;
	}
}