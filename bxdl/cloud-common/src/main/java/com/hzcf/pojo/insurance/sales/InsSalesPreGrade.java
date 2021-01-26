package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsSalesPreGrade implements Serializable{
	
		private static final long serialVersionUID = 1L;

	    /**
	    * 主键
	    */
	    private Long insuranceSalesId;

	    /**
	    * 姓名
	    */
	    private String insuranceSaler;

	    /**
	    * 性别
	    */
	    private String sex;

	    /**
	    * 工号
	    */
	    private String insuranceSalerNo;
	    
	    /**
	    * 直营/加盟
	    */	    
	    private String cooperationType;

	    /**
	    * 所属组织机构
	    */
	    private Long salesOrgId;

	    /**
	    * 所属营销团队
	    */
	    private Long salesTeamId;
	    
	    /**
	    * 所属组织机构
	    */
	    private String salesOrgName;

	    /**
	    * 所属营销团队
	    */
	    private String salesTeamName;

	    /**
	    * 职级序列
	    */
	    private Long rankSequenceId;

	    /**
	    * 当前职级
	    */
	    private Long salesGradeId;

	    /**
	    * 证件类型
	    */
	    private String cardType;

	    /**
	    * 证件号码
	    */
	    private String cardNo;

	    /**
	    * 手机号码
	    */
	    private String mobile;

	    /**
	    * 入司时间
	    */
	    private String joinDate;

	    /**
	    * 开户银行
	    */
	    private String bankChannel;

	    /**
	    * 银行账号
	    */
	    private String bankCardNo;

	    /**
	    * 出生日期
	    */
	    private String birthday;

	    /**
	    * 国籍
	    */
	    private String country;

	    /**
	    * 民族
	    */
	    private String nation;

	    /**
	    * 政治面貌
	    */
	    private String politicalAppearance;

	    /**
	    * 党派
	    */
	    private String party;

	    /**
	    * 地区编码
	    */
	    private String areaCode;

	    /**
	    * 家庭住址
	    */
	    private String address;

	    /**
	    * 籍贯
	    */
	    private String nativePlace;

	    /**
	    * 户口所在地
	    */
	    private String householdRegPlace;

	    /**
	    * 档案所在地
	    */
	    private String archivesPreservationPlace;

	    /**
	    * 家庭地址邮编
	    */
	    private String postCode;

	    /**
	    * 住宅电话
	    */
	    private String cellPhone;

	    /**
	    * 兼职/全职
	    */
	    private String dutyType;

	    /**
	    * 邮箱
	    */
	    private String email;

	    /**
	    * qq号
	    */
	    private String qqNumber;

	    /**
	    * 微信号
	    */
	    private String wechatNumber;

	    /**
	    * 学位
	    */
	    private String academicDegree;

	    /**
	    * 学历
	    */
	    private String educationalBg;

	    /**
	    * 外语水平
	    */
	    private String foreignLanguageAbility;

	    /**
	    * 婚否
	    */
	    private String maritalStatus;

	    /**
	    * 结婚日期
	    */
	    private String marriageDate;

	    /**
	    * 上家公司名称
	    */
	    private String preCompany;

	    /**
	    * 上家公司职业
	    */
	    private String preOccupation;

	    /**
	    * 是否有待业证
	    */
	    private String leavingCertificate;

	    /**
	    * 执业证书编号
	    */
	    private String certificateNumber;

	    /**
	    * 执业证书状态
	    */
	    private String certificateStatus;

	    /**
	    * 执业证书有效期起
	    */
	    private String certificateStartDate;

	    /**
	    * 执业证书有效期止
	    */
	    private String certificateEndDate;

	    /**
	    * 是否有工作室
	    */
	    private String havingWorkGroup;

	    /**
	    * 工作室电话
	    */
	    private String workGroupPhone;

	    /**
	    * 是否内部推荐
	    */
	    private String internalReferral;

	    /**
	    * 兴趣爱好
	    */
	    private String interest;

	    /**
	    * 备注
	    */
	    private String remark;

	    /**
	    * 担保人
	    */
	    private Long dbSalesId;

	    /**
	    * 推荐人
	    */
	    private Long tjSalesId;

	    /**
	    * 育成人
	    */
	    private Long ycSalesId;

	    /**
	    * 上级管理人
	    */
	    private Long sjSalesId;

	    /**
	    * 继承人
	    */
	    private Long jcSalesId;

	    /**
	    * 辅导人
	    */
	    private Long fdSalesId;
	    
	    /**
	    * 担保人
	    */
	    private String dbSalesDate;

	    /**
	    * 推荐人
	    */
	    private String tjSalesDate;

	    /**
	    * 育成人
	    */
	    private String ycSalesDate;

	    /**
	    * 上级管理人
	    */
	    private String sjSalesDate;

	    /**
	    * 继承人
	    */
	    private String jcSalesDate;

	    /**
	    * 辅导人
	    */
	    private String fdSalesDate;

	    /**
	    * 当前关系网编码
	    */
	    private String nowTreeCode;

	    /**
	    * 原关系网编码
	    */
	    private String nextTreeCode;

	    /**
	    * 当前关系网生效时间
	    */
	    private String nextTreeCodeIdate;
	    
	    /**
	    * 创建人
	    */
	    private String salesStatus;
	    
	    private Long preMonthTjr;
	    
	    private Long preMonthYcr;
	    
	    private Long preMonthOrg;
	    
	    private Long preMonthTeam;
	    
	    private Long preMonthGrade;

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

	    public InsSalesPreGrade() {
	    }

		public Long getInsuranceSalesId() {
			return insuranceSalesId;
		}

		public void setInsuranceSalesId(Long insuranceSalesId) {
			this.insuranceSalesId = insuranceSalesId;
		}

		public String getInsuranceSaler() {
			return insuranceSaler;
		}

		public void setInsuranceSaler(String insuranceSaler) {
			this.insuranceSaler = insuranceSaler;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getInsuranceSalerNo() {
			return insuranceSalerNo;
		}

		public void setInsuranceSalerNo(String insuranceSalerNo) {
			this.insuranceSalerNo = insuranceSalerNo;
		}
		
		public String getCooperationType() {
			return cooperationType;
		}

		public void setCooperationType(String cooperationType) {
			this.cooperationType = cooperationType;
		}

		public Long getSalesOrgId() {
			return salesOrgId;
		}

		public void setSalesOrgId(Long salesOrgId) {
			this.salesOrgId = salesOrgId;
		}

		public Long getSalesTeamId() {
			return salesTeamId;
		}

		public void setSalesTeamId(Long salesTeamId) {
			this.salesTeamId = salesTeamId;
		}

		public String getSalesOrgName() {
			return salesOrgName;
		}

		public void setSalesOrgName(String salesOrgName) {
			this.salesOrgName = salesOrgName;
		}

		public String getSalesTeamName() {
			return salesTeamName;
		}

		public void setSalesTeamName(String salesTeamName) {
			this.salesTeamName = salesTeamName;
		}

		public Long getRankSequenceId() {
			return rankSequenceId;
		}

		public void setRankSequenceId(Long rankSequenceId) {
			this.rankSequenceId = rankSequenceId;
		}

		public Long getSalesGradeId() {
			return salesGradeId;
		}

		public void setSalesGradeId(Long salesGradeId) {
			this.salesGradeId = salesGradeId;
		}

		public String getCardType() {
			return cardType;
		}

		public void setCardType(String cardType) {
			this.cardType = cardType;
		}

		public String getCardNo() {
			return cardNo;
		}

		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getJoinDate() {
			return joinDate;
		}

		public void setJoinDate(String joinDate) {
			this.joinDate = joinDate;
		}

		public String getBankChannel() {
			return bankChannel;
		}

		public void setBankChannel(String bankChannel) {
			this.bankChannel = bankChannel;
		}

		public String getBankCardNo() {
			return bankCardNo;
		}

		public void setBankCardNo(String bankCardNo) {
			this.bankCardNo = bankCardNo;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getNation() {
			return nation;
		}

		public void setNation(String nation) {
			this.nation = nation;
		}

		public String getPoliticalAppearance() {
			return politicalAppearance;
		}

		public void setPoliticalAppearance(String politicalAppearance) {
			this.politicalAppearance = politicalAppearance;
		}

		public String getParty() {
			return party;
		}

		public void setParty(String party) {
			this.party = party;
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

		public String getNativePlace() {
			return nativePlace;
		}

		public void setNativePlace(String nativePlace) {
			this.nativePlace = nativePlace;
		}

		public String getHouseholdRegPlace() {
			return householdRegPlace;
		}

		public void setHouseholdRegPlace(String householdRegPlace) {
			this.householdRegPlace = householdRegPlace;
		}

		public String getArchivesPreservationPlace() {
			return archivesPreservationPlace;
		}

		public void setArchivesPreservationPlace(String archivesPreservationPlace) {
			this.archivesPreservationPlace = archivesPreservationPlace;
		}

		public String getPostCode() {
			return postCode;
		}

		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}

		public String getCellPhone() {
			return cellPhone;
		}

		public void setCellPhone(String cellPhone) {
			this.cellPhone = cellPhone;
		}

		public String getDutyType() {
			return dutyType;
		}

		public void setDutyType(String dutyType) {
			this.dutyType = dutyType;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getQqNumber() {
			return qqNumber;
		}

		public void setQqNumber(String qqNumber) {
			this.qqNumber = qqNumber;
		}

		public String getWechatNumber() {
			return wechatNumber;
		}

		public void setWechatNumber(String wechatNumber) {
			this.wechatNumber = wechatNumber;
		}

		public String getAcademicDegree() {
			return academicDegree;
		}

		public void setAcademicDegree(String academicDegree) {
			this.academicDegree = academicDegree;
		}

		public String getEducationalBg() {
			return educationalBg;
		}

		public void setEducationalBg(String educationalBg) {
			this.educationalBg = educationalBg;
		}

		public String getForeignLanguageAbility() {
			return foreignLanguageAbility;
		}

		public void setForeignLanguageAbility(String foreignLanguageAbility) {
			this.foreignLanguageAbility = foreignLanguageAbility;
		}

		public String getMaritalStatus() {
			return maritalStatus;
		}

		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}

		public String getMarriageDate() {
			return marriageDate;
		}

		public void setMarriageDate(String marriageDate) {
			this.marriageDate = marriageDate;
		}

		public String getPreCompany() {
			return preCompany;
		}

		public void setPreCompany(String preCompany) {
			this.preCompany = preCompany;
		}

		public String getPreOccupation() {
			return preOccupation;
		}

		public void setPreOccupation(String preOccupation) {
			this.preOccupation = preOccupation;
		}

		public String getLeavingCertificate() {
			return leavingCertificate;
		}

		public void setLeavingCertificate(String leavingCertificate) {
			this.leavingCertificate = leavingCertificate;
		}

		public String getCertificateNumber() {
			return certificateNumber;
		}

		public void setCertificateNumber(String certificateNumber) {
			this.certificateNumber = certificateNumber;
		}

		public String getCertificateStatus() {
			return certificateStatus;
		}

		public void setCertificateStatus(String certificateStatus) {
			this.certificateStatus = certificateStatus;
		}

		public String getCertificateStartDate() {
			return certificateStartDate;
		}

		public void setCertificateStartDate(String certificateStartDate) {
			this.certificateStartDate = certificateStartDate;
		}

		public String getCertificateEndDate() {
			return certificateEndDate;
		}

		public void setCertificateEndDate(String certificateEndDate) {
			this.certificateEndDate = certificateEndDate;
		}

		public String getHavingWorkGroup() {
			return havingWorkGroup;
		}

		public void setHavingWorkGroup(String havingWorkGroup) {
			this.havingWorkGroup = havingWorkGroup;
		}

		public String getWorkGroupPhone() {
			return workGroupPhone;
		}

		public void setWorkGroupPhone(String workGroupPhone) {
			this.workGroupPhone = workGroupPhone;
		}

		public String getInternalReferral() {
			return internalReferral;
		}

		public void setInternalReferral(String internalReferral) {
			this.internalReferral = internalReferral;
		}

		public String getInterest() {
			return interest;
		}

		public void setInterest(String interest) {
			this.interest = interest;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Long getDbSalesId() {
			return dbSalesId;
		}

		public void setDbSalesId(Long dbSalesId) {
			this.dbSalesId = dbSalesId;
		}

		public Long getTjSalesId() {
			return tjSalesId;
		}

		public void setTjSalesId(Long tjSalesId) {
			this.tjSalesId = tjSalesId;
		}

		public Long getYcSalesId() {
			return ycSalesId;
		}

		public void setYcSalesId(Long ycSalesId) {
			this.ycSalesId = ycSalesId;
		}

		public Long getSjSalesId() {
			return sjSalesId;
		}

		public void setSjSalesId(Long sjSalesId) {
			this.sjSalesId = sjSalesId;
		}

		public Long getJcSalesId() {
			return jcSalesId;
		}

		public void setJcSalesId(Long jcSalesId) {
			this.jcSalesId = jcSalesId;
		}

		public Long getFdSalesId() {
			return fdSalesId;
		}

		public void setFdSalesId(Long fdSalesId) {
			this.fdSalesId = fdSalesId;
		}

		public String getDbSalesDate() {
			return dbSalesDate;
		}

		public void setDbSalesDate(String dbSalesDate) {
			this.dbSalesDate = dbSalesDate;
		}

		public String getTjSalesDate() {
			return tjSalesDate;
		}

		public void setTjSalesDate(String tjSalesDate) {
			this.tjSalesDate = tjSalesDate;
		}

		public String getYcSalesDate() {
			return ycSalesDate;
		}

		public void setYcSalesDate(String ycSalesDate) {
			this.ycSalesDate = ycSalesDate;
		}

		public String getSjSalesDate() {
			return sjSalesDate;
		}

		public void setSjSalesDate(String sjSalesDate) {
			this.sjSalesDate = sjSalesDate;
		}

		public String getJcSalesDate() {
			return jcSalesDate;
		}

		public void setJcSalesDate(String jcSalesDate) {
			this.jcSalesDate = jcSalesDate;
		}

		public String getFdSalesDate() {
			return fdSalesDate;
		}

		public void setFdSalesDate(String fdSalesDate) {
			this.fdSalesDate = fdSalesDate;
		}

		public String getNowTreeCode() {
			return nowTreeCode;
		}

		public void setNowTreeCode(String nowTreeCode) {
			this.nowTreeCode = nowTreeCode;
		}

		public String getNextTreeCode() {
			return nextTreeCode;
		}

		public void setNextTreeCode(String nextTreeCode) {
			this.nextTreeCode = nextTreeCode;
		}

		public String getNextTreeCodeIdate() {
			return nextTreeCodeIdate;
		}

		public void setNextTreeCodeIdate(String nextTreeCodeIdate) {
			this.nextTreeCodeIdate = nextTreeCodeIdate;
		}

		public String getSalesStatus() {
			return salesStatus;
		}

		public void setSalesStatus(String salesStatus) {
			this.salesStatus = salesStatus;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public String getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Date getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}

		public Date getUpdatedTime() {
			return updatedTime;
		}

		public void setUpdatedTime(Date updatedTime) {
			this.updatedTime = updatedTime;
		}

		public Long getPreMonthGrade() {
			return preMonthGrade;
		}

		public void setPreMonthGrade(Long preMonthGrade) {
			this.preMonthGrade = preMonthGrade;
		}

		public Long getPreMonthTjr() {
			return preMonthTjr;
		}

		public void setPreMonthTjr(Long preMonthTjr) {
			this.preMonthTjr = preMonthTjr;
		}

		public Long getPreMonthYcr() {
			return preMonthYcr;
		}

		public void setPreMonthYcr(Long preMonthYcr) {
			this.preMonthYcr = preMonthYcr;
		}

		public Long getPreMonthOrg() {
			return preMonthOrg;
		}

		public void setPreMonthOrg(Long preMonthOrg) {
			this.preMonthOrg = preMonthOrg;
		}

		public Long getPreMonthTeam() {
			return preMonthTeam;
		}

		public void setPreMonthTeam(Long preMonthTeam) {
			this.preMonthTeam = preMonthTeam;
		}
		
}
