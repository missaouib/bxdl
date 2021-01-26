package com.hzcf.pojo.cal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 直辖部管理津贴历史参数表
 */
public class CalHisDirectlyUnderDept implements Serializable {
	private static final long serialVersionUID = -789747663819619029L;
	/** 主键 */
	private Long id;
	/** 销售机构id */
	private Long orgId;
	/** 上限标记符号（0：小于，1：小于等于） */
	private String maxSign;
	/** 上限值 */
	private BigDecimal maxValue;
	/** 下限标记符号（0：大于，1：大于等于） */
	private String minSign;
	/** 下限值 */
	private BigDecimal minValue;
	/** 津贴比例 */
	private BigDecimal allowanceRatio;
	/** 额外津贴比例（rule_type=51，该字段填充业务总监部直辖室实发FYC，津贴比例（目前只有山西和江苏）） */
	private BigDecimal extraRatio;
	/** 规则（50：区间设定值；51：固定数值）
 */
	private String ruleType;
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
	/** 基本法参数版本表主键 */
	private Long versionId;

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
	
	public void setAllowanceRatio(BigDecimal allowanceRatio) {
		this.allowanceRatio = allowanceRatio;
	}
	
	public BigDecimal getAllowanceRatio() {
		return this.allowanceRatio;
	}
	
	public void setExtraRatio(BigDecimal extraRatio) {
		this.extraRatio = extraRatio;
	}
	
	public BigDecimal getExtraRatio() {
		return this.extraRatio;
	}
	
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	
	public String getRuleType() {
		return this.ruleType;
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
	
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	
	public Long getVersionId() {
		return this.versionId;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        CalHisDirectlyUnderDept that = (CalHisDirectlyUnderDept) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "CalHisDirectlyUnderDept{" +
				"id=" + id +
						",orgId='" + orgId + "'" + 
						",maxSign='" + maxSign + "'" + 
						",maxValue='" + maxValue + "'" + 
						",minSign='" + minSign + "'" + 
						",minValue='" + minValue + "'" + 
						",allowanceRatio='" + allowanceRatio + "'" + 
						",extraRatio='" + extraRatio + "'" + 
						",ruleType='" + ruleType + "'" + 
						",createBy='" + createBy + "'" + 
						",createTime='" + createTime + "'" + 
						",updateBy='" + updateBy + "'" + 
						",updateTime='" + updateTime + "'" + 
						",isNormal='" + isNormal + "'" + 
						",versionId='" + versionId + "'" + 
		                '}';
    }
	
}