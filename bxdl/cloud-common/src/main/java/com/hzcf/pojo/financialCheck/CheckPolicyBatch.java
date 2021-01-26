package com.hzcf.pojo.financialCheck;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by qin lina on 2020/12/10.
 */
public class CheckPolicyBatch implements Serializable{
    private Long id;
	/** 批次号 */
	private String batchNum;
	/** 批次名称 */
	private String batchName;
	/** 对账月份 */
	private String checkMonth;
	/** 组织机构名称 */
	private String salesOrgName;
	/** 组织机构id */
	private String salesOrgId;
	/** 保险公司名称 */
	private String companyOrgName;
	/** 保险公司id */
	private String companyOrgId;
	/** 对账类型 */
	private String batchType;
	/** 状态 0未对账 1对账完成 2部分完成 */
	private String status;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	/** 修改人 */
	private String createBy;
	/** 创建人姓名 为了前端页面显示方便 */
	private String createByName;
	/** 修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date updateTime;
	/** 修改人 */
	private String updateBy;
	/** 备注 */
	private String remark;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getBatchNum() {
		return this.batchNum;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatchName() {
		return this.batchName;
	}

	public void setCheckMonth(String checkMonth) {
		this.checkMonth = checkMonth;
	}

	public String getCheckMonth() {
		return this.checkMonth;
	}

	public void setSalesOrgName(String salesOrgName) {
		this.salesOrgName = salesOrgName;
	}

	public String getSalesOrgName() {
		return this.salesOrgName;
	}

	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public String getSalesOrgId() {
		return this.salesOrgId;
	}

	public void setCompanyOrgName(String companyOrgName) {
		this.companyOrgName = companyOrgName;
	}

	public String getCompanyOrgName() {
		return this.companyOrgName;
	}

	public String getCompanyOrgId() {
		return companyOrgId;
	}

	public void setCompanyOrgId(String companyOrgId) {
		this.companyOrgId = companyOrgId;
	}

	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}

	public String getBatchType() {
		return this.batchType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateByName() {
		return this.createByName;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        CheckPolicyBatch that = (CheckPolicyBatch) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CheckPolicyBatch{" +
				"id=" + id +
						",batchNum='" + batchNum + "'" +
						",batchName='" + batchName + "'" +
						",checkMonth='" + checkMonth + "'" +
						",salesOrgName='" + salesOrgName + "'" +
						",salesOrgId='" + salesOrgId + "'" +
						",companyOrgName='" + companyOrgName + "'" +
				        ",companyOrgId='" + companyOrgId + "'" +
						",batchType='" + batchType + "'" +
						",status='" + status + "'" +
						",createTime='" + createTime + "'" +
						",createBy='" + createBy + "'" +
						",createByName='" + createByName + "'" +
						",updateTime='" + updateTime + "'" +
						",updateBy='" + updateBy + "'" +
						",remark='" + remark + "'" +
		                '}';
    }

}
