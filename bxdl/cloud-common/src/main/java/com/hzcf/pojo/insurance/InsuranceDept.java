package com.hzcf.pojo.insurance;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InsuranceDept implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private String updatedTime;

    /**
     * 主键
     */
    private Long insuranceCompanyId;

    /**
     * 保险公司名称 (!总公司)
     */
    private String insuranceCompanyName;
    
    /**
     * 保险公司编码 (!总公司)
     */
    private String insuranceCode;

    /**
     * 保险公司简称 (!总公司)
     */
    private String insuranceCompanyNameLess;

    /**
     * 保险公司英文名 (!总公司)
     */
    private String insuranceCompanyEnName;

    /**
     * 保险公司英文简称 (!总公司)
     */
    private String insuranceCompanyEnNameLess;

    /**
     * 保险公司代码 (!总公司)
     */
    private String insuranceCompanyCode;

    /**
     * 组织机构级别 总公司/省级分公司/市级
     */
    private String orgLevel;

    /**
     * 上级组织机构
     */
    private String parentCompanyCode;
    
    /**
     * 上级组织机构
     */
    private String parentCompany;


    /**
     * 保险公司财寿标志
     */
    private String businessType;

    /**
     * 成立日期
     */
    private String dateOfEstablishment;

    /**
     * 注册资本（万元）
     */
    private BigDecimal registeredCapital;

    /**
     * 公司传真
     */
    private String fax;

    /**
     * 公司电话
     */
    private String tel;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 所在地区编码
     */
    private String areaCode;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 公司网址
     */
    private String webSite;

    /**
     * 主营业务
     */
    private String mainBusiness;

    /**
     * 经营状态
     */
    private String businessStatus;

    /**
     * 合作类型 0直营  1加盟
     */
    private String cooperationType;

    private List<InsuranceDept> orgChildren;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getInsuranceCompanyNameLess() {
        return insuranceCompanyNameLess;
    }

    public void setInsuranceCompanyNameLess(String insuranceCompanyNameLess) {
        this.insuranceCompanyNameLess = insuranceCompanyNameLess;
    }

    public String getInsuranceCompanyEnName() {
        return insuranceCompanyEnName;
    }

    public void setInsuranceCompanyEnName(String insuranceCompanyEnName) {
        this.insuranceCompanyEnName = insuranceCompanyEnName;
    }

    public String getInsuranceCompanyEnNameLess() {
        return insuranceCompanyEnNameLess;
    }

    public void setInsuranceCompanyEnNameLess(String insuranceCompanyEnNameLess) {
        this.insuranceCompanyEnNameLess = insuranceCompanyEnNameLess;
    }

    public String getInsuranceCompanyCode() {
        return insuranceCompanyCode;
    }

    public void setInsuranceCompanyCode(String insuranceCompanyCode) {
        this.insuranceCompanyCode = insuranceCompanyCode;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getParentCompanyCode() {
        return parentCompanyCode;
    }

    public void setParentCompanyCode(String parentCompanyCode) {
        this.parentCompanyCode = parentCompanyCode;
    }
    
    public String getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(String parentCompany) {
        this.parentCompany = parentCompany;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(String dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(String cooperationType) {
        this.cooperationType = cooperationType;
    }

    public List<InsuranceDept> getOrgChildren() {
        return orgChildren;
    }

    public void setOrgChildren(List<InsuranceDept> orgChildren) {
        this.orgChildren = orgChildren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        InsuranceDept that = (InsuranceDept) o;

        return new EqualsBuilder()
                .append(createdBy, that.createdBy)
                .append(createdTime, that.createdTime)
                .append(updatedBy, that.updatedBy)
                .append(updatedTime, that.updatedTime)
                .append(insuranceCompanyId, that.insuranceCompanyId)
                .append(insuranceCompanyName, that.insuranceCompanyName)
                .append(insuranceCode, that.insuranceCode)
                .append(insuranceCompanyNameLess, that.insuranceCompanyNameLess)
                .append(insuranceCompanyEnName, that.insuranceCompanyEnName)
                .append(insuranceCompanyEnNameLess, that.insuranceCompanyEnNameLess)
                .append(insuranceCompanyCode, that.insuranceCompanyCode)
                .append(orgLevel, that.orgLevel)
                .append(parentCompanyCode, that.parentCompanyCode)
                .append(parentCompany, that.parentCompany)
                .append(businessType, that.businessType)
                .append(dateOfEstablishment, that.dateOfEstablishment)
                .append(registeredCapital, that.registeredCapital)
                .append(fax, that.fax)
                .append(tel, that.tel)
                .append(postCode, that.postCode)
                .append(areaCode, that.areaCode)
                .append(address, that.address)
                .append(webSite, that.webSite)
                .append(mainBusiness, that.mainBusiness)
                .append(businessStatus, that.businessStatus)
                .append(cooperationType, that.cooperationType)
                .append(orgChildren, that.orgChildren)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(createdBy)
                .append(createdTime)
                .append(updatedBy)
                .append(updatedTime)
                .append(insuranceCompanyId)
                .append(insuranceCompanyName)
                .append(insuranceCode)
                .append(insuranceCompanyNameLess)
                .append(insuranceCompanyEnName)
                .append(insuranceCompanyEnNameLess)
                .append(insuranceCompanyCode)
                .append(orgLevel)
                .append(parentCompanyCode)
                .append(parentCompany)
                .append(businessType)
                .append(dateOfEstablishment)
                .append(registeredCapital)
                .append(fax)
                .append(tel)
                .append(postCode)
                .append(areaCode)
                .append(address)
                .append(webSite)
                .append(mainBusiness)
                .append(businessStatus)
                .append(cooperationType)
                .append(orgChildren)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InsuranceDept{" +
                "createdBy='" + createdBy + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", insuranceCompanyId=" + insuranceCompanyId +
                ", insuranceCompanyName='" + insuranceCompanyName + '\'' +
                ", insuranceCode='" + insuranceCode + '\'' +
                ", insuranceCompanyNameLess='" + insuranceCompanyNameLess + '\'' +
                ", insuranceCompanyEnName='" + insuranceCompanyEnName + '\'' +
                ", insuranceCompanyEnNameLess='" + insuranceCompanyEnNameLess + '\'' +
                ", insuranceCompanyCode='" + insuranceCompanyCode + '\'' +
                ", orgLevel='" + orgLevel + '\'' +
                ", parentCompanyCode='" + parentCompanyCode + '\'' +
                ", parentCompany='" + parentCompany + '\'' +
                ", businessType='" + businessType + '\'' +
                ", dateOfEstablishment='" + dateOfEstablishment + '\'' +
                ", registeredCapital=" + registeredCapital +
                ", fax='" + fax + '\'' +
                ", tel='" + tel + '\'' +
                ", postCode='" + postCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", address='" + address + '\'' +
                ", webSite='" + webSite + '\'' +
                ", mainBusiness='" + mainBusiness + '\'' +
                ", businessStatus='" + businessStatus + '\'' +
                ", cooperationType='" + cooperationType + '\'' +
                ", orgChildren=" + orgChildren +
                '}';
    }
}
