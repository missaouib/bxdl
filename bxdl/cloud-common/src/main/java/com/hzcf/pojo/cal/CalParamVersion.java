package com.hzcf.pojo.cal;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class CalParamVersion implements Serializable{
	private static final long serialVersionUID = -1386255026081122264L;
	/** 主键 */
	private Long id;
	/** 销售机构id */
	private Long orgId;
	/** 参数类型（0：展业津贴；1：增员津贴；2：业务经理育成奖；3：业务总监育成奖；4：直辖组管理津贴；5：直属部管理津贴；6：业务总监绩效奖；7：业务推动津贴） */
	private String paramType;
	/** 版本备注 */
	private String remark;
	/** 主版本号 */
	private Integer majorVersion;
	/** 次级版本号 */
	private Integer minorVersion;
	/** 临界版本号 */
	private Integer criticalVersion;
	/** 创建者 */
	private Long createBy;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

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

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setMajorVersion(Integer majorVersion) {
		this.majorVersion = majorVersion;
	}

	public Integer getMajorVersion() {
		return this.majorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	public Integer getMinorVersion() {
		return this.minorVersion;
	}

	public void setCriticalVersion(Integer criticalVersion) {
		this.criticalVersion = criticalVersion;
	}

	public Integer getCriticalVersion() {
		return this.criticalVersion;
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


	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) {return false;}
		CalParamVersion that = (CalParamVersion) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(id);
	}

	@Override
	public String toString() {
		return "CalParamVersion{" +
				"id=" + id +
				",orgId='" + orgId + "'" +
				",paramType='" + paramType + "'" +
				",remark='" + remark + "'" +
				",majorVersion='" + majorVersion + "'" +
				",minorVersion='" + minorVersion + "'" +
				",criticalVersion='" + criticalVersion + "'" +
				",createBy='" + createBy + "'" +
				",createTime='" + createTime + "'" +
				'}';
	}

}