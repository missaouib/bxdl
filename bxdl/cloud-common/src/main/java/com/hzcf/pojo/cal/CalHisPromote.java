package com.hzcf.pojo.cal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 展业津贴参数
 */
public class CalHisPromote implements Serializable {
	private static final long serialVersionUID = 7109944844877075732L;
	/** 主键 */
	private Long id;
	/** 销售机构id */
	private Long orgId;
	/** 基本法参数版本表主键 */
	private Long versionId;
	/** 上限标记符号（0：小于，1：小于等于） */
	private String maxSign;
	/** 上限值 */
	private BigDecimal maxValue;
	/** 下限标记符号（0：大于，1：大于等于） */
	private String minSign;
	/** 下限值 */
	private BigDecimal minValue;
	/** 津贴或津贴比例（当rule_type=3,输入津贴比例） */
	private BigDecimal allowance;
	/** 规则（00：区间固定值；01：FYC百位取整；02：FYC乘以比例参数；03：有额外的递增） */
	private String ruleType;
	/** 步长:每增加 */
	private BigDecimal step;
	/** 每步长额外津贴 */
	private BigDecimal extraAllowance;
	/** 创建人id */
	private Long createBy;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	/** 修改人id */
	private Long updateBy;
	/** 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date updateTime;
	/** 0正常，1删除 */
	private String isNormal;

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
	
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	
	public Long getVersionId() {
		return this.versionId;
	}
	
	public void setMaxSign(String maxSign) {
		this.maxSign = maxSign;
	}
	
	public String getMaxSign() {
		return this.maxSign;
	}
	
	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}
	
	public BigDecimal getMaxValue() {
		return this.maxValue;
	}
	
	public void setMinSign(String minSign) {
		this.minSign = minSign;
	}
	
	public String getMinSign() {
		return this.minSign;
	}
	
	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}
	
	public BigDecimal getMinValue() {
		return this.minValue;
	}
	
	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}
	
	public BigDecimal getAllowance() {
		return this.allowance;
	}
	
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	
	public String getRuleType() {
		return this.ruleType;
	}
	
	public void setStep(BigDecimal step) {
		this.step = step;
	}
	
	public BigDecimal getStep() {
		return this.step;
	}
	
	public void setExtraAllowance(BigDecimal extraAllowance) {
		this.extraAllowance = extraAllowance;
	}
	
	public BigDecimal getExtraAllowance() {
		return this.extraAllowance;
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
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        CalHisPromote that = (CalHisPromote) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "CalHisPromote{" +
				"id=" + id +
						",orgId='" + orgId + "'" + 
						",versionId='" + versionId + "'" + 
						",maxSign='" + maxSign + "'" + 
						",maxValue='" + maxValue + "'" + 
						",minSign='" + minSign + "'" + 
						",minValue='" + minValue + "'" + 
						",allowance='" + allowance + "'" + 
						",ruleType='" + ruleType + "'" + 
						",step='" + step + "'" + 
						",extraAllowance='" + extraAllowance + "'" + 
						",createBy='" + createBy + "'" + 
						",createTime='" + createTime + "'" + 
						",updateBy='" + updateBy + "'" + 
						",updateTime='" + updateTime + "'" + 
						",isNormal='" + isNormal + "'" + 
		                '}';
    }
	
}