package com.hzcf.pojo.insurance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
*  sales_org_info
* @author yaolong 2019-06-17
*/
public class SalesOrgInfo implements Serializable {
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
    * 主键
    */
    private Long salesOrgId;

    /**
    * 组织机构代码
    */
    private String salesOrgCode;

    /**
    * 组织机构中文名
    */
    private String salesOrgName;

    /**
    * 组织机构简称
    */
    private String salesOrgNameLess;

    /**
    * 组织机构英文名
    */
    private String salesOrgEnName;

    /**
    * 组织机构英文简称
    */
    private String salesOrgEname;

    /**
    * 机构级别
    */
    private String salesOrgLevel;

    /**
    * 是否核算机构
    */
    private String accountingOrg;

    /**
    * 上级组织机构代码
    */
    private String topParentSalesOrgCode;    
    
    /**
    * 上级组织机构代码
    */
    private String parentSalesOrgCode;

    /**
    * 所属核算机构编码
    */
    private String belongAccountingOrgCode;

    /**
    * 所属核算机构名称
    */
    private String belongAccountingOrgName;

    /**
    * 所在省市编码
    */
    private String areaCode;

    /**
    * 联系地址
    */
    private String address;

    /**
    * 公司传真
    */
    private String fax;

    /**
    * 公司电话
    */
    private String phone;

    /**
    * 邮政编码
    */
    private String postCode;
    
    /**
    * 是否挂牌
    */
    private String haveListed;

    /**
    * 营业执照编号
    */
    private String businessLicense;

    /**
    * 开票机构
    */
    private String invoiceOrgCode;

    /**
    * 营业税纳税类型
    */
    private String businessPayTaxesType;

    /**
    * 营业税纳税率(%)
    */
    private String businessTaxRate;

    /**
    * 所得税纳税类型
    */
    private String incomePayTaxesType;

    /**
    * 所得税纳税率(%)
    */
    private String incomeTaxRate;

    /**
    * 注册地址
    */
    private String regAddr;

    /**
    * 成立时间
    */
    private String dateOfEstablishment;

    /**
    * 许可证地址
    */
    private String licenseAddr;

    /**
    * 发证机关
    */
    private String licensingAuthority;

    /**
    * 联系人手机号
    */
    private String contactsTel;

    /**
    * 负责人姓名
    */
    private String liableName;

    /**
    * 员工数量
    */
    private Integer employeesNum;

    /**
    * 社保人数
    */
    private Integer socialSecurityNum;

    /**
    * 银行开户许可证
    */
    private String bankAccountLicense;

    /**
    * 营业执照
    */
    private String businessLicensePic;

    /**
    * 信用代码证
    */
    private String creditCodePic;

    /**
    * 业务许可证
    */
    private String carryOutBusinessPic;

    /**
    * 合作类型 0直营1加盟
    */
    private String cooperationType;

    /**
    * 总监津贴标准
    */
    private String orgStatus;

    /**
    * 自留树编码
    */
    private String treeCode;
    
    /**
    * 自留树编码
    */
    private SalesOrgDetail salesOrgDetail;
    
    private List<SalesOrgInfo> orgChildrens;

    private List<SalesTeamInfo> orgTeamInfos;

    /**
     * 是否默认基本法（0：默认；1:自定义）
     */
    private String isDefaultCal;

    /**
     * 使用默认基本法总/分公司机构id
     */
    private Long calOrgId;

    public SalesOrgInfo() {
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

    public Long getSalesOrgId() {
        return salesOrgId;
    }

    public void setSalesOrgId(Long salesOrgId) {
        this.salesOrgId = salesOrgId;
    }

    public String getSalesOrgCode() {
        return salesOrgCode;
    }

    public void setSalesOrgCode(String salesOrgCode) {
        this.salesOrgCode = salesOrgCode;
    }

    public String getSalesOrgName() {
        return salesOrgName;
    }

    public void setSalesOrgName(String salesOrgName) {
        this.salesOrgName = salesOrgName;
    }

    public String getSalesOrgNameLess() {
        return salesOrgNameLess;
    }

    public void setSalesOrgNameLess(String salesOrgNameLess) {
        this.salesOrgNameLess = salesOrgNameLess;
    }

    public String getSalesOrgEnName() {
        return salesOrgEnName;
    }

    public void setSalesOrgEnName(String salesOrgEnName) {
        this.salesOrgEnName = salesOrgEnName;
    }

    public String getSalesOrgEname() {
        return salesOrgEname;
    }

    public void setSalesOrgEname(String salesOrgEname) {
        this.salesOrgEname = salesOrgEname;
    }

    public String getSalesOrgLevel() {
        return salesOrgLevel;
    }

    public void setSalesOrgLevel(String salesOrgLevel) {
        this.salesOrgLevel = salesOrgLevel;
    }

    public String getAccountingOrg() {
        return accountingOrg;
    }

    public void setAccountingOrg(String accountingOrg) {
        this.accountingOrg = accountingOrg;
    }
    
    public String getTopParentSalesOrgCode() {
		return topParentSalesOrgCode;
	}

	public void setTopParentSalesOrgCode(String topParentSalesOrgCode) {
		this.topParentSalesOrgCode = topParentSalesOrgCode;
	}

	public String getParentSalesOrgCode() {
        return parentSalesOrgCode;
    }

    public void setParentSalesOrgCode(String parentSalesOrgCode) {
        this.parentSalesOrgCode = parentSalesOrgCode;
    }

    public String getBelongAccountingOrgCode() {
        return belongAccountingOrgCode;
    }

    public void setBelongAccountingOrgCode(String belongAccountingOrgCode) {
        this.belongAccountingOrgCode = belongAccountingOrgCode;
    }

    public String getBelongAccountingOrgName() {
        return belongAccountingOrgName;
    }

    public void setBelongAccountingOrgName(String belongAccountingOrgName) {
        this.belongAccountingOrgName = belongAccountingOrgName;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
    public String getHaveListed() {
		return haveListed;
	}

	public void setHaveListed(String haveListed) {
		this.haveListed = haveListed;
	}

	public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getInvoiceOrgCode() {
        return invoiceOrgCode;
    }

    public void setInvoiceOrgCode(String invoiceOrgCode) {
        this.invoiceOrgCode = invoiceOrgCode;
    }

    public String getBusinessPayTaxesType() {
        return businessPayTaxesType;
    }

    public void setBusinessPayTaxesType(String businessPayTaxesType) {
        this.businessPayTaxesType = businessPayTaxesType;
    }

    public String getBusinessTaxRate() {
        return businessTaxRate;
    }

    public void setBusinessTaxRate(String businessTaxRate) {
        this.businessTaxRate = businessTaxRate;
    }

    public String getIncomePayTaxesType() {
        return incomePayTaxesType;
    }

    public void setIncomePayTaxesType(String incomePayTaxesType) {
        this.incomePayTaxesType = incomePayTaxesType;
    }

    public String getIncomeTaxRate() {
        return incomeTaxRate;
    }

    public void setIncomeTaxRate(String incomeTaxRate) {
        this.incomeTaxRate = incomeTaxRate;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public String getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(String dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public String getLicenseAddr() {
        return licenseAddr;
    }

    public void setLicenseAddr(String licenseAddr) {
        this.licenseAddr = licenseAddr;
    }

    public String getLicensingAuthority() {
        return licensingAuthority;
    }

    public void setLicensingAuthority(String licensingAuthority) {
        this.licensingAuthority = licensingAuthority;
    }

    public String getContactsTel() {
        return contactsTel;
    }

    public void setContactsTel(String contactsTel) {
        this.contactsTel = contactsTel;
    }

    public String getLiableName() {
        return liableName;
    }

    public void setLiableName(String liableName) {
        this.liableName = liableName;
    }

    public Integer getEmployeesNum() {
        return employeesNum;
    }

    public void setEmployeesNum(Integer employeesNum) {
        this.employeesNum = employeesNum;
    }

    public Integer getSocialSecurityNum() {
        return socialSecurityNum;
    }

    public void setSocialSecurityNum(Integer socialSecurityNum) {
        this.socialSecurityNum = socialSecurityNum;
    }

    public String getBankAccountLicense() {
        return bankAccountLicense;
    }

    public void setBankAccountLicense(String bankAccountLicense) {
        this.bankAccountLicense = bankAccountLicense;
    }

    public String getBusinessLicensePic() {
        return businessLicensePic;
    }

    public void setBusinessLicensePic(String businessLicensePic) {
        this.businessLicensePic = businessLicensePic;
    }

    public String getCreditCodePic() {
        return creditCodePic;
    }

    public void setCreditCodePic(String creditCodePic) {
        this.creditCodePic = creditCodePic;
    }

    public String getCarryOutBusinessPic() {
        return carryOutBusinessPic;
    }

    public void setCarryOutBusinessPic(String carryOutBusinessPic) {
        this.carryOutBusinessPic = carryOutBusinessPic;
    }

    public String getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(String cooperationType) {
        this.cooperationType = cooperationType;
    }

    public String getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus;
    }
    
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}
	
	public SalesOrgDetail getSalesOrgDetail() {
		return salesOrgDetail;
	}

	public void setSalesOrgDetail(SalesOrgDetail salesOrgDetail) {
		this.salesOrgDetail = salesOrgDetail;
	}

	public List<SalesOrgInfo> getOrgChildrens() {
		return orgChildrens;
	}

	public void setOrgChildrens(List<SalesOrgInfo> orgChildrens) {
		this.orgChildrens = orgChildrens;
	}

    public String getIsDefaultCal() {
        return isDefaultCal;
    }

    public void setIsDefaultCal(String isDefaultCal) {
        this.isDefaultCal = isDefaultCal;
    }

    public Long getCalOrgId() {
        return calOrgId;
    }

    public void setCalOrgId(Long calOrgId) {
        this.calOrgId = calOrgId;
    }

    public List<SalesTeamInfo> getOrgTeamInfos() {
        return orgTeamInfos;
    }

    public void setOrgTeamInfos(List<SalesTeamInfo> orgTeamInfos) {
        this.orgTeamInfos = orgTeamInfos;
    }

    @Override
	public String toString() {
		return "SalesOrgInfo [createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", salesOrgId=" + salesOrgId + ", salesOrgCode=" + salesOrgCode
				+ ", salesOrgName=" + salesOrgName + ", salesOrgNameLess=" + salesOrgNameLess + ", salesOrgEnName="
				+ salesOrgEnName + ", salesOrgEname=" + salesOrgEname + ", salesOrgLevel=" + salesOrgLevel
				+ ", accountingOrg=" + accountingOrg + ", topParentSalesOrgCode=" + topParentSalesOrgCode
				+ ", parentSalesOrgCode=" + parentSalesOrgCode + ", belongAccountingOrgCode=" + belongAccountingOrgCode
				+ ", belongAccountingOrgName=" + belongAccountingOrgName + ", areaCode=" + areaCode + ", address="
				+ address + ", fax=" + fax + ", phone=" + phone + ", postCode=" + postCode + ", haveListed="
				+ haveListed + ", businessLicense=" + businessLicense + ", invoiceOrgCode=" + invoiceOrgCode
				+ ", businessPayTaxesType=" + businessPayTaxesType + ", businessTaxRate=" + businessTaxRate
				+ ", incomePayTaxesType=" + incomePayTaxesType + ", incomeTaxRate=" + incomeTaxRate + ", regAddr="
				+ regAddr + ", dateOfEstablishment=" + dateOfEstablishment + ", licenseAddr=" + licenseAddr
				+ ", licensingAuthority=" + licensingAuthority + ", contactsTel=" + contactsTel + ", liableName="
				+ liableName + ", employeesNum=" + employeesNum + ", socialSecurityNum=" + socialSecurityNum
				+ ", bankAccountLicense=" + bankAccountLicense + ", businessLicensePic=" + businessLicensePic
				+ ", creditCodePic=" + creditCodePic + ", carryOutBusinessPic=" + carryOutBusinessPic
				+ ", cooperationType=" + cooperationType + ", orgStatus=" + orgStatus
				+ ", treeCode=" + treeCode + ", salesOrgDetail=" + salesOrgDetail + ", orgChildrens=" + orgChildrens
				+ "]";
	}

	public SalesOrgInfo(String createdBy, Date createdTime, String updatedBy, Date updatedTime, Long salesOrgId,
			String salesOrgCode, String salesOrgName, String salesOrgNameLess, String salesOrgEnName,
			String salesOrgEname, String salesOrgLevel, String accountingOrg, String topParentSalesOrgCode,
			String parentSalesOrgCode, String belongAccountingOrgCode, String belongAccountingOrgName, String areaCode,
			String address, String fax, String phone, String postCode, String haveListed, String businessLicense,
			String invoiceOrgCode, String businessPayTaxesType, String businessTaxRate, String incomePayTaxesType,
			String incomeTaxRate, String regAddr, String dateOfEstablishment, String licenseAddr,
			String licensingAuthority, String contactsTel, String liableName, Integer employeesNum,
			Integer socialSecurityNum, String bankAccountLicense, String businessLicensePic, String creditCodePic,
			String carryOutBusinessPic, String cooperationType, String orgStatus, String treeCode,
			SalesOrgDetail salesOrgDetail, List<SalesOrgInfo> orgChildrens) {
		super();
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.salesOrgId = salesOrgId;
		this.salesOrgCode = salesOrgCode;
		this.salesOrgName = salesOrgName;
		this.salesOrgNameLess = salesOrgNameLess;
		this.salesOrgEnName = salesOrgEnName;
		this.salesOrgEname = salesOrgEname;
		this.salesOrgLevel = salesOrgLevel;
		this.accountingOrg = accountingOrg;
		this.topParentSalesOrgCode = topParentSalesOrgCode;
		this.parentSalesOrgCode = parentSalesOrgCode;
		this.belongAccountingOrgCode = belongAccountingOrgCode;
		this.belongAccountingOrgName = belongAccountingOrgName;
		this.areaCode = areaCode;
		this.address = address;
		this.fax = fax;
		this.phone = phone;
		this.postCode = postCode;
		this.haveListed = haveListed;
		this.businessLicense = businessLicense;
		this.invoiceOrgCode = invoiceOrgCode;
		this.businessPayTaxesType = businessPayTaxesType;
		this.businessTaxRate = businessTaxRate;
		this.incomePayTaxesType = incomePayTaxesType;
		this.incomeTaxRate = incomeTaxRate;
		this.regAddr = regAddr;
		this.dateOfEstablishment = dateOfEstablishment;
		this.licenseAddr = licenseAddr;
		this.licensingAuthority = licensingAuthority;
		this.contactsTel = contactsTel;
		this.liableName = liableName;
		this.employeesNum = employeesNum;
		this.socialSecurityNum = socialSecurityNum;
		this.bankAccountLicense = bankAccountLicense;
		this.businessLicensePic = businessLicensePic;
		this.creditCodePic = creditCodePic;
		this.carryOutBusinessPic = carryOutBusinessPic;
		this.cooperationType = cooperationType;
		this.orgStatus = orgStatus;
		this.treeCode = treeCode;
		this.salesOrgDetail = salesOrgDetail;
		this.orgChildrens = orgChildrens;
	}

}
