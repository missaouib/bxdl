package com.hzcf.pojo.insurance.protocol;

import java.io.Serializable;
import java.util.Date;

public class InsProtocolPersistencyBonus implements Serializable {
    
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long bonusId;

    /**
    * 协议主键
    */
    private Long protocolId;

    /**
    * 费率基于
    */
    private String bonusBases;

    /**
    * 结算周期
    */
    private String settlementCycle;

    /**
    * 通算方式
    */
    private String addSubsidyWay;

    /**
    * 年期换算系数
    */
    private String periodRatio;

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

    public InsProtocolPersistencyBonus() {
    	
    }

	public Long getBonusId() {
		return bonusId;
	}

	public void setBonusId(Long bonusId) {
		this.bonusId = bonusId;
	}

	public Long getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(Long protocolId) {
		this.protocolId = protocolId;
	}

	public String getBonusBases() {
		return bonusBases;
	}

	public void setBonusBases(String bonusBases) {
		this.bonusBases = bonusBases;
	}

	public String getSettlementCycle() {
		return settlementCycle;
	}

	public void setSettlementCycle(String settlementCycle) {
		this.settlementCycle = settlementCycle;
	}

	public String getAddSubsidyWay() {
		return addSubsidyWay;
	}

	public void setAddSubsidyWay(String addSubsidyWay) {
		this.addSubsidyWay = addSubsidyWay;
	}

	public String getPeriodRatio() {
		return periodRatio;
	}

	public void setPeriodRatio(String periodRatio) {
		this.periodRatio = periodRatio;
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
		return "InsProtocolPersistencyBonus [bonusId=" + bonusId + ", protocolId=" + protocolId + ", bonusBases="
				+ bonusBases + ", settlementCycle=" + settlementCycle + ", addSubsidyWay=" + addSubsidyWay
				+ ", periodRatio=" + periodRatio + ", createdBy=" + createdBy + ", createdTime=" + createdTime
				+ ", updatedBy=" + updatedBy + ", updatedTime=" + updatedTime + "]";
	}

	public InsProtocolPersistencyBonus(Long bonusId, Long protocolId, String bonusBases, String settlementCycle,
			String addSubsidyWay, String periodRatio, String createdBy, Date createdTime, String updatedBy,
			Date updatedTime) {
		super();
		this.bonusId = bonusId;
		this.protocolId = protocolId;
		this.bonusBases = bonusBases;
		this.settlementCycle = settlementCycle;
		this.addSubsidyWay = addSubsidyWay;
		this.periodRatio = periodRatio;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}
}
