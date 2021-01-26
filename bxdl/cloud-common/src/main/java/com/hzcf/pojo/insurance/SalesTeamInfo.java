package com.hzcf.pojo.insurance;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
*  sales_org_detail
* @author yaolong 2019-06-18
*/
public class SalesTeamInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
     private String createdBy;

     /**
     * 创建时间
     */
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
     private Date createdTime;

     /**
     * 更新人
     */
     private String updatedBy;

     /**
     * 更新时间
     */
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
     private Date updatedTime;

     /**
     * 销售团队主键
     */
     private Long salesTeamId;

     /**
     * 销售团队名称
     */
     private String salesTeamName;

     /**
     * 销售团队简称
     */
     private String salesTeamNameLess;

     /**
     * 所属销售组织
     */
     private Long salesOrgId;

     /**
     * 销售团队代码
     */
     private String salesTeamCode;

     /**
     * 上级团队代码
     */
     private String parentSalesTeamCode;

     /**
     * 销售团队类型
     */
     private String salesTeamType;

     /**
     * 成立时间
     */
     private String dateOfEstablishment;

     /**
     * 职场电话
     */
     private String tel;

     /**
     * 职场传真
     */
     private String fax;

     /**
     * 职场地址
     */
     private String address;

     /**
     * 职场邮编
     */
     private String postCode;
     

     /**
     * 职场邮编
     */
     private String treeCode;
     
     /**
     * 职场邮编
     */
     private String teamStatus;
     
     /**
     * 职场邮编
     */
     private String teamLevel;

     private List<SalesTeamInfo> childTeamInfoList;
     

     public SalesTeamInfo() {
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

	public Long getSalesTeamId() {
		return salesTeamId;
	}

	public void setSalesTeamId(Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	public String getSalesTeamName() {
		return salesTeamName;
	}

	public void setSalesTeamName(String salesTeamName) {
		this.salesTeamName = salesTeamName;
	}

	public String getSalesTeamNameLess() {
		return salesTeamNameLess;
	}

	public void setSalesTeamNameLess(String salesTeamNameLess) {
		this.salesTeamNameLess = salesTeamNameLess;
	}

	public Long getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(Long salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public String getSalesTeamCode() {
		return salesTeamCode;
	}

	public void setSalesTeamCode(String salesTeamCode) {
		this.salesTeamCode = salesTeamCode;
	}

	public String getParentSalesTeamCode() {
		return parentSalesTeamCode;
	}

	public void setParentSalesTeamCode(String parentSalesTeamCode) {
		this.parentSalesTeamCode = parentSalesTeamCode;
	}

	public String getSalesTeamType() {
		return salesTeamType;
	}

	public void setSalesTeamType(String salesTeamType) {
		this.salesTeamType = salesTeamType;
	}

	public String getDateOfEstablishment() {
		return dateOfEstablishment;
	}

	public void setDateOfEstablishment(String dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}
	
	public String getTeamStatus() {
		return teamStatus;
	}

	public void setTeamStatus(String teamStatus) {
		this.teamStatus = teamStatus;
	}

	public String getTeamLevel() {
		return teamLevel;
	}

	public void setTeamLevel(String teamLevel) {
		this.teamLevel = teamLevel;
	}

	public List<SalesTeamInfo> getChildTeamInfoList() {
		return childTeamInfoList;
	}

	public void setChildTeamInfoList(List<SalesTeamInfo> childTeamInfoList) {
		this.childTeamInfoList = childTeamInfoList;
	}

	@Override
	public String toString() {
		return "SalesTeamInfo [createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", salesTeamId=" + salesTeamId + ", salesTeamName=" + salesTeamName
				+ ", salesTeamNameLess=" + salesTeamNameLess + ", salesOrgId=" + salesOrgId + ", salesTeamCode="
				+ salesTeamCode + ", parentSalesTeamCode=" + parentSalesTeamCode + ", salesTeamType=" + salesTeamType
				+ ", dateOfEstablishment=" + dateOfEstablishment + ", tel=" + tel + ", fax=" + fax + ", address="
				+ address + ", postCode=" + postCode + ", treeCode=" + treeCode + ", teamStatus=" + teamStatus
				+ ", teamLevel=" + teamLevel + "]";
	}

	public SalesTeamInfo(String createdBy, Date createdTime, String updatedBy, Date updatedTime, Long salesTeamId,
			String salesTeamName, String salesTeamNameLess, Long salesOrgId, String salesTeamCode,
			String parentSalesTeamCode, String salesTeamType, String dateOfEstablishment, String tel, String fax,
			String address, String postCode, String treeCode, String teamStatus, String teamLevel) {
		super();
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.salesTeamId = salesTeamId;
		this.salesTeamName = salesTeamName;
		this.salesTeamNameLess = salesTeamNameLess;
		this.salesOrgId = salesOrgId;
		this.salesTeamCode = salesTeamCode;
		this.parentSalesTeamCode = parentSalesTeamCode;
		this.salesTeamType = salesTeamType;
		this.dateOfEstablishment = dateOfEstablishment;
		this.tel = tel;
		this.fax = fax;
		this.address = address;
		this.postCode = postCode;
		this.treeCode = treeCode;
		this.teamStatus = teamStatus;
		this.teamLevel = teamLevel;
	}

}
