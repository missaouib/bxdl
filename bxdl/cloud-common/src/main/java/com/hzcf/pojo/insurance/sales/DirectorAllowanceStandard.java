package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

public class DirectorAllowanceStandard implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long allowanceId;

    /**
    * 直营机构id
    */
    private Long salesOrgId;

    /**
    * 加盟总监id
    */
    private Long insSalesId;

    /**
    * 公式
    */
    private String allowanceFormula;

    /**
    * 津贴比例
    */
    private String allowanceRatio;

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
    * 是否删除 0 否 1 是
    */
    private String deleteFlag;

    public DirectorAllowanceStandard() {
    	
    }

	public Long getAllowanceId() {
		return allowanceId;
	}

	public void setAllowanceId(Long allowanceId) {
		this.allowanceId = allowanceId;
	}

	public Long getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(Long salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public Long getInsSalesId() {
		return insSalesId;
	}

	public void setInsSalesId(Long insSalesId) {
		this.insSalesId = insSalesId;
	}

	public String getAllowanceFormula() {
		return allowanceFormula;
	}

	public void setAllowanceFormula(String allowanceFormula) {
		this.allowanceFormula = allowanceFormula;
	}

	public String getAllowanceRatio() {
		return allowanceRatio;
	}

	public void setAllowanceRatio(String allowanceRatio) {
		this.allowanceRatio = allowanceRatio;
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

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "DirectorAllowanceStandard [allowanceId=" + allowanceId + ", salesOrgId=" + salesOrgId + ", insSalesId="
				+ insSalesId + ", allowanceFormula=" + allowanceFormula + ", allowanceRatio=" + allowanceRatio
				+ ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", deleteFlag=" + deleteFlag + "]";
	}
    
}
