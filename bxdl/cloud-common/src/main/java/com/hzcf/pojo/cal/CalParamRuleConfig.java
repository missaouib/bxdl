package com.hzcf.pojo.cal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class CalParamRuleConfig implements Serializable {
	private static final long serialVersionUID = 1797030972844815179L;
	/** 主键 */
	private Long id;
	/** 省级/总部销售机构id */
	private Long orgId;
	/** 参数类型（0：展业津贴；1：增员津贴；2：直辖组管理津贴；3：直属部管理津贴；4：业务经理育成奖；5：业务总监育成奖；6：业务总监绩效奖；7：业务推动津贴） */
	private String paramType;
	/** 规则（规则类型参照对应的参数类型表） */
	private String ruleType;
	/** 是否包含组活力人数（针对于直辖组中规则） */
	private String activeSalerFlag;
	/** 创建人 */
	private Long createBy;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	/** 修改人 */
	private Long updateBy;
	/** 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date updateTime;
	/** 是否正常（0：正常；1：删除） */
	private String isNormal;
	/** 直辖组参数是否排除部长直辖组（0：不排除；1：排除） */
	private String excludeDirectlyGroupFlag;
	/** 直辖部参数是否包含部长直辖组（0：不包含；1：包含） */
	private String includeDirectlyGroupFlag;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return this.orgId;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamType() {
		return this.paramType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleType() {
		return this.ruleType;
	}

	public void setActiveSalerFlag(String activeSalerFlag) {
		this.activeSalerFlag = activeSalerFlag;
	}

	public String getActiveSalerFlag() {
		return this.activeSalerFlag;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}

	public String getIsNormal() {
		return this.isNormal;
	}

	public void setExcludeDirectlyGroupFlag(String excludeDirectlyGroupFlag) {
		this.excludeDirectlyGroupFlag = excludeDirectlyGroupFlag;
	}

	public String getExcludeDirectlyGroupFlag() {
		return this.excludeDirectlyGroupFlag;
	}

	public void setIncludeDirectlyGroupFlag(String includeDirectlyGroupFlag) {
		this.includeDirectlyGroupFlag = includeDirectlyGroupFlag;
	}

	public String getIncludeDirectlyGroupFlag() {
		return this.includeDirectlyGroupFlag;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) {return false;}
		CalParamRuleConfig that = (CalParamRuleConfig) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(id);
	}

	@Override
	public String toString() {
		return "CalParamRuleConfig{" +
				"id=" + id +
				",orgId='" + orgId + "'" +
				",paramType='" + paramType + "'" +
				",ruleType='" + ruleType + "'" +
				",activeSalerFlag='" + activeSalerFlag + "'" +
				",createBy='" + createBy + "'" +
				",createTime='" + createTime + "'" +
				",updateBy='" + updateBy + "'" +
				",updateTime='" + updateTime + "'" +
				",isNormal='" + isNormal + "'" +
				",excludeDirectlyGroupFlag='" + excludeDirectlyGroupFlag + "'" +
				",includeDirectlyGroupFlag='" + includeDirectlyGroupFlag + "'" +
				'}';
	}

}