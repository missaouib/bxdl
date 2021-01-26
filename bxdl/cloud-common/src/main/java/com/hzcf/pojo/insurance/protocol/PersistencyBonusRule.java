package com.hzcf.pojo.insurance.protocol;

import java.io.Serializable;
import java.util.Date;

public class PersistencyBonusRule implements Serializable {
    
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long ruleId;

    /**
    * 继续率奖金主键
    */
    private Long persistencyBonusId;

    /**
    * 是否公共规则
    */
    private String basicFlag;

    /**
    * 例外产品主键（子产品）
    */
    private String exitProductId;

    /**
    * 计算类型
    */
    private String computeType;

    /**
    * 继续率计算月
    */
    private String monthly;

    /**
    * 公式
    */
    private String computeFormula;

    /**
    * 奖励费率
    */
    private String bonusRatio;

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

    public PersistencyBonusRule() {
    	
    }

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getPersistencyBonusId() {
		return persistencyBonusId;
	}

	public void setPersistencyBonusId(Long persistencyBonusId) {
		this.persistencyBonusId = persistencyBonusId;
	}

	public String getBasicFlag() {
		return basicFlag;
	}

	public void setBasicFlag(String basicFlag) {
		this.basicFlag = basicFlag;
	}

	public String getExitProductId() {
		return exitProductId;
	}

	public void setExitProductId(String exitProductId) {
		this.exitProductId = exitProductId;
	}

	public String getComputeType() {
		return computeType;
	}

	public void setComputeType(String computeType) {
		this.computeType = computeType;
	}

	public String getMonthly() {
		return monthly;
	}

	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public String getComputeFormula() {
		return computeFormula;
	}

	public void setComputeFormula(String computeFormula) {
		this.computeFormula = computeFormula;
	}

	public String getBonusRatio() {
		return bonusRatio;
	}

	public void setBonusRatio(String bonusRatio) {
		this.bonusRatio = bonusRatio;
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
		return "PersistencyBonusRule [ruleId=" + ruleId + ", persistencyBonusId=" + persistencyBonusId + ", basicFlag="
				+ basicFlag + ", exitProductId=" + exitProductId + ", computeType=" + computeType + ", monthly="
				+ monthly + ", computeFormula=" + computeFormula + ", bonusRatio=" + bonusRatio + ", createdBy="
				+ createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy + ", updatedTime="
				+ updatedTime + "]";
	}

	public PersistencyBonusRule(Long ruleId, Long persistencyBonusId, String basicFlag, String exitProductId,
			String computeType, String monthly, String computeFormula, String bonusRatio, String createdBy,
			Date createdTime, String updatedBy, Date updatedTime) {
		super();
		this.ruleId = ruleId;
		this.persistencyBonusId = persistencyBonusId;
		this.basicFlag = basicFlag;
		this.exitProductId = exitProductId;
		this.computeType = computeType;
		this.monthly = monthly;
		this.computeFormula = computeFormula;
		this.bonusRatio = bonusRatio;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}

}
