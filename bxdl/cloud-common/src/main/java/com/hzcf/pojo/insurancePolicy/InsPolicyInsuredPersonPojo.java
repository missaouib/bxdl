package com.hzcf.pojo.insurancePolicy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class InsPolicyInsuredPersonPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * insured_person_id
     */
    private Long insuredPersonId;

    /**
     * 类型（1-投保单、2-保单）
     */
    private String type;

    /**
     * 投保单/保单编号
     */
    private String policyId;

    /**
     * 与投保人关系
     */
    private String relationship;

    /**
     * 证件类型
     */
    private String cardType;

    /**
     * 证件号
     */
    private String cardNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * sex
     */
    private String sex;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 年收入（万元）
     */
    private BigDecimal annualIncome;

    /**
     * 家庭住址
     */
    private String homeAddress;

    /**
     * 是否多次投保
     */
    private String isMultipleInsure;

    /**
     * 是否健康告知
     */
    private String isInformHealth;

    /**
     * 学历
     */
    private String eduBackground;

    /**
     * 婚姻状态
     */
    private String maritalStatus;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 办公/家庭电话
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 家庭地址邮编
     */
    private String homePostalCode;

    /**
     * 户籍地址
     */
    private String domicileAddress;

    /**
     * 负债（万元）
     */
    private BigDecimal debts;

    /**
     * 职业编号
     */
    private String occupationCode;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * insure
     */
    private Date insure;

    /**
     * 结算月
     */
    private String statistMonth;


    public InsPolicyInsuredPersonPojo() {
    }

    public Long getInsuredPersonId() {
        return insuredPersonId;
    }

    public void setInsuredPersonId(Long insuredPersonId) {
        this.insuredPersonId = insuredPersonId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getIsMultipleInsure() {
        return isMultipleInsure;
    }

    public void setIsMultipleInsure(String isMultipleInsure) {
        this.isMultipleInsure = isMultipleInsure;
    }

    public String getIsInformHealth() {
        return isInformHealth;
    }

    public void setIsInformHealth(String isInformHealth) {
        this.isInformHealth = isInformHealth;
    }

    public String getEduBackground() {
        return eduBackground;
    }

    public void setEduBackground(String eduBackground) {
        this.eduBackground = eduBackground;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePostalCode() {
        return homePostalCode;
    }

    public void setHomePostalCode(String homePostalCode) {
        this.homePostalCode = homePostalCode;
    }

    public String getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(String domicileAddress) {
        this.domicileAddress = domicileAddress;
    }

    public BigDecimal getDebts() {
        return debts;
    }

    public void setDebts(BigDecimal debts) {
        this.debts = debts;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getInsure() {
        return insure;
    }

    public void setInsure(Date insure) {
        this.insure = insure;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStatistMonth() {
        return statistMonth;
    }

    public void setStatistMonth(String statistMonth) {
        this.statistMonth = statistMonth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsPolicyInsuredPersonPojo that = (InsPolicyInsuredPersonPojo) o;
        return Objects.equals(insuredPersonId, that.insuredPersonId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(policyId, that.policyId) &&
                Objects.equals(relationship, that.relationship) &&
                Objects.equals(cardType, that.cardType) &&
                Objects.equals(cardNo, that.cardNo) &&
                Objects.equals(name, that.name) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(annualIncome, that.annualIncome) &&
                Objects.equals(homeAddress, that.homeAddress) &&
                Objects.equals(isMultipleInsure, that.isMultipleInsure) &&
                Objects.equals(isInformHealth, that.isInformHealth) &&
                Objects.equals(eduBackground, that.eduBackground) &&
                Objects.equals(maritalStatus, that.maritalStatus) &&
                Objects.equals(companyAddress, that.companyAddress) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(homePostalCode, that.homePostalCode) &&
                Objects.equals(domicileAddress, that.domicileAddress) &&
                Objects.equals(debts, that.debts) &&
                Objects.equals(occupationCode, that.occupationCode) &&
                Objects.equals(occupation, that.occupation) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(insure, that.insure) &&
                Objects.equals(statistMonth, that.statistMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insuredPersonId, type, policyId, relationship, cardType, cardNo, name, mobile, sex, birthday, annualIncome, homeAddress, isMultipleInsure, isInformHealth, eduBackground, maritalStatus, companyAddress, telephone, email, homePostalCode, domicileAddress, debts, occupationCode, occupation, createTime, createBy, updateTime, updateBy, insure, statistMonth);
    }

    @Override
    public String toString() {
        return "InsPolicyInsuredPersonPojo{" +
                "insuredPersonId=" + insuredPersonId +
                ", type='" + type + '\'' +
                ", policyId='" + policyId + '\'' +
                ", relationship='" + relationship + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", annualIncome=" + annualIncome +
                ", homeAddress='" + homeAddress + '\'' +
                ", isMultipleInsure='" + isMultipleInsure + '\'' +
                ", isInformHealth='" + isInformHealth + '\'' +
                ", eduBackground='" + eduBackground + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", homePostalCode='" + homePostalCode + '\'' +
                ", domicileAddress='" + domicileAddress + '\'' +
                ", debts=" + debts +
                ", occupationCode='" + occupationCode + '\'' +
                ", occupation='" + occupation + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", insure=" + insure +
                ", statistMonth='" + statistMonth + '\'' +
                '}';
    }
}
