package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsuranceSalesInfo implements Serializable{
	
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
	    * 入司时间
	    */
	    private String passDate;

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
		 * 一代处育成人
		 */
		private Long ycCFirstGener;

		/**
		 * 二代处育成人
		 */
		private Long ycCSecondGener;

		/**
		 * 一代部育成人
		 */
		private Long ycBFirstGener;

		/**
		 * 二代部育成人
		 */
		private Long ycBSecondGener;

		/**
		 * 直属组处长
		 */
		private Long directGroupC;

		/**
		 * 直属部部长
		 */
		private Long directDeptB;
		/**
		 * 一代处育成人确立时间
		 */
//		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
		private String establishTime;
		/**
		 * 二代处育成人确立时间
		 */
//		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
		private String establishTime1;
		/**
		 * 一代部育成人确立时间
		 */
//		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
		private String establishTime2;
		/**
		 * 二代部育成人确立时间
		 */
//		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
		private String establishTime3;
		/**
		 * 直属组处长确立时间
		 */
//		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
		private String establishTime4;
		/**
		 * 直属部部长确立时间
		 */
//		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
		private String establishTime5;

		private String openBank;

		/**
		 * 离职日期
		 */
		private String quitDate;

	    public InsuranceSalesInfo() {
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

		public String getPassDate() {
			return passDate;
		}

		public void setPassDate(String passDate) {
			this.passDate = passDate;
		}

	public String getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}

	public String getEstablishTime1() {
		return establishTime1;
	}

	public void setEstablishTime1(String establishTime1) {
		this.establishTime1 = establishTime1;
	}

	public String getEstablishTime2() {
		return establishTime2;
	}

	public void setEstablishTime2(String establishTime2) {
		this.establishTime2 = establishTime2;
	}

	public String getEstablishTime3() {
		return establishTime3;
	}

	public void setEstablishTime3(String establishTime3) {
		this.establishTime3 = establishTime3;
	}

	public String getEstablishTime4() {
		return establishTime4;
	}

	public void setEstablishTime4(String establishTime4) {
		this.establishTime4 = establishTime4;
	}

	public String getEstablishTime5() {
		return establishTime5;
	}

	public void setEstablishTime5(String establishTime5) {
		this.establishTime5 = establishTime5;
	}

	@Override
		public String toString() {
			return "InsuranceSalesInfo [insuranceSalesId=" + insuranceSalesId + ", insuranceSaler=" + insuranceSaler
					+ ", sex=" + sex + ", insuranceSalerNo=" + insuranceSalerNo + ", cooperationType=" + cooperationType
					+ ", salesOrgId=" + salesOrgId + ", salesTeamId=" + salesTeamId + ", salesOrgName=" + salesOrgName
					+ ", salesTeamName=" + salesTeamName + ", rankSequenceId=" + rankSequenceId + ", salesGradeId="
					+ salesGradeId + ", cardType=" + cardType + ", cardNo=" + cardNo + ", mobile=" + mobile
					+ ", joinDate=" + joinDate + ", passDate=" + passDate + ", bankChannel=" + bankChannel
					+",openBank="+openBank +", bankCardNo=" + bankCardNo + ", birthday=" + birthday + ", country="
					+ country + ", nation="
					+ nation + ", politicalAppearance=" + politicalAppearance + ", party=" + party + ", areaCode="
					+ areaCode + ", address=" + address + ", nativePlace=" + nativePlace + ", householdRegPlace="
					+ householdRegPlace + ", archivesPreservationPlace=" + archivesPreservationPlace + ", postCode="
					+ postCode + ", cellPhone=" + cellPhone + ", dutyType=" + dutyType + ", email=" + email
					+ ", qqNumber=" + qqNumber + ", wechatNumber=" + wechatNumber + ", academicDegree=" + academicDegree
					+ ", educationalBg=" + educationalBg + ", foreignLanguageAbility=" + foreignLanguageAbility
					+ ", maritalStatus=" + maritalStatus + ", marriageDate=" + marriageDate + ", preCompany="
					+ preCompany + ", preOccupation=" + preOccupation + ", leavingCertificate=" + leavingCertificate
					+ ", certificateNumber=" + certificateNumber + ", certificateStatus=" + certificateStatus
					+ ", certificateStartDate=" + certificateStartDate + ", certificateEndDate=" + certificateEndDate
					+ ", havingWorkGroup=" + havingWorkGroup + ", workGroupPhone=" + workGroupPhone
					+ ", internalReferral=" + internalReferral + ", interest=" + interest + ", remark=" + remark
					+ ", dbSalesId=" + dbSalesId + ", tjSalesId=" + tjSalesId + ", ycSalesId=" + ycSalesId
					+ ", sjSalesId=" + sjSalesId + ", jcSalesId=" + jcSalesId + ", fdSalesId=" + fdSalesId
					+ ", dbSalesDate=" + dbSalesDate + ", tjSalesDate=" + tjSalesDate + ", ycSalesDate=" + ycSalesDate
					+ ", sjSalesDate=" + sjSalesDate + ", jcSalesDate=" + jcSalesDate + ", fdSalesDate=" + fdSalesDate
					+ ", nowTreeCode=" + nowTreeCode + ", nextTreeCode=" + nextTreeCode + ", nextTreeCodeIdate="
					+ nextTreeCodeIdate + ", salesStatus=" + salesStatus + ", createdBy=" + createdBy + ", createdTime="
					+ createdTime + ", updatedBy=" + updatedBy + ", updatedTime=" + updatedTime + "]";
		}

		public InsuranceSalesInfo(Long insuranceSalesId, String insuranceSaler, String sex, String insuranceSalerNo,
				String cooperationType, Long salesOrgId, Long salesTeamId, String salesOrgName, String salesTeamName,
				Long rankSequenceId, Long salesGradeId, String cardType, String cardNo, String mobile, String joinDate,
				String passDate, String bankChannel, String bankCardNo, String birthday, String country, String nation,
				String politicalAppearance, String party, String areaCode, String address, String nativePlace,
				String householdRegPlace, String archivesPreservationPlace, String postCode, String cellPhone,
				String dutyType, String email, String qqNumber, String wechatNumber, String academicDegree,
				String educationalBg, String foreignLanguageAbility, String maritalStatus, String marriageDate,
				String preCompany, String preOccupation, String leavingCertificate, String certificateNumber,
				String certificateStatus, String certificateStartDate, String certificateEndDate,
				String havingWorkGroup, String workGroupPhone, String internalReferral, String interest, String remark,
				Long dbSalesId, Long tjSalesId, Long ycSalesId, Long sjSalesId, Long jcSalesId, Long fdSalesId,
				String dbSalesDate, String tjSalesDate, String ycSalesDate, String sjSalesDate, String jcSalesDate,
				String fdSalesDate, String nowTreeCode, String nextTreeCode, String nextTreeCodeIdate,
				String salesStatus, String createdBy, Date createdTime, String updatedBy, Date updatedTime) {
			super();
			this.insuranceSalesId = insuranceSalesId;
			this.insuranceSaler = insuranceSaler;
			this.sex = sex;
			this.insuranceSalerNo = insuranceSalerNo;
			this.cooperationType = cooperationType;
			this.salesOrgId = salesOrgId;
			this.salesTeamId = salesTeamId;
			this.salesOrgName = salesOrgName;
			this.salesTeamName = salesTeamName;
			this.rankSequenceId = rankSequenceId;
			this.salesGradeId = salesGradeId;
			this.cardType = cardType;
			this.cardNo = cardNo;
			this.mobile = mobile;
			this.joinDate = joinDate;
			this.passDate = passDate;
			this.bankChannel = bankChannel;
			this.bankCardNo = bankCardNo;
			this.birthday = birthday;
			this.country = country;
			this.nation = nation;
			this.politicalAppearance = politicalAppearance;
			this.party = party;
			this.areaCode = areaCode;
			this.address = address;
			this.nativePlace = nativePlace;
			this.householdRegPlace = householdRegPlace;
			this.archivesPreservationPlace = archivesPreservationPlace;
			this.postCode = postCode;
			this.cellPhone = cellPhone;
			this.dutyType = dutyType;
			this.email = email;
			this.qqNumber = qqNumber;
			this.wechatNumber = wechatNumber;
			this.academicDegree = academicDegree;
			this.educationalBg = educationalBg;
			this.foreignLanguageAbility = foreignLanguageAbility;
			this.maritalStatus = maritalStatus;
			this.marriageDate = marriageDate;
			this.preCompany = preCompany;
			this.preOccupation = preOccupation;
			this.leavingCertificate = leavingCertificate;
			this.certificateNumber = certificateNumber;
			this.certificateStatus = certificateStatus;
			this.certificateStartDate = certificateStartDate;
			this.certificateEndDate = certificateEndDate;
			this.havingWorkGroup = havingWorkGroup;
			this.workGroupPhone = workGroupPhone;
			this.internalReferral = internalReferral;
			this.interest = interest;
			this.remark = remark;
			this.dbSalesId = dbSalesId;
			this.tjSalesId = tjSalesId;
			this.ycSalesId = ycSalesId;
			this.sjSalesId = sjSalesId;
			this.jcSalesId = jcSalesId;
			this.fdSalesId = fdSalesId;
			this.dbSalesDate = dbSalesDate;
			this.tjSalesDate = tjSalesDate;
			this.ycSalesDate = ycSalesDate;
			this.sjSalesDate = sjSalesDate;
			this.jcSalesDate = jcSalesDate;
			this.fdSalesDate = fdSalesDate;
			this.nowTreeCode = nowTreeCode;
			this.nextTreeCode = nextTreeCode;
			this.nextTreeCodeIdate = nextTreeCodeIdate;
			this.salesStatus = salesStatus;
			this.createdBy = createdBy;
			this.createdTime = createdTime;
			this.updatedBy = updatedBy;
			this.updatedTime = updatedTime;
		}

	public Long getYcCFirstGener() {
		return ycCFirstGener;
	}

	public void setYcCFirstGener(Long ycCFirstGener) {
		this.ycCFirstGener = ycCFirstGener;
	}

	public Long getYcCSecondGener() {
		return ycCSecondGener;
	}

	public void setYcCSecondGener(Long ycCSecondGener) {
		this.ycCSecondGener = ycCSecondGener;
	}

	public Long getYcBFirstGener() {
		return ycBFirstGener;
	}

	public void setYcBFirstGener(Long ycBFirstGener) {
		this.ycBFirstGener = ycBFirstGener;
	}

	public Long getYcBSecondGener() {
		return ycBSecondGener;
	}

	public void setYcBSecondGener(Long ycBSecondGener) {
		this.ycBSecondGener = ycBSecondGener;
	}

	public Long getDirectGroupC() {
		return directGroupC;
	}

	public void setDirectGroupC(Long directGroupC) {
		this.directGroupC = directGroupC;
	}

	public Long getDirectDeptB() {
		return directDeptB;
	}

	public void setDirectDeptB(Long directDeptB) {
		this.directDeptB = directDeptB;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
	}
}
