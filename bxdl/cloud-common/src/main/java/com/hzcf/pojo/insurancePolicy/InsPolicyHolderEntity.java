package com.hzcf.pojo.insurancePolicy;

import java.math.BigDecimal;
import java.util.Objects;

public class InsPolicyHolderEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long policyHolderId;

    /**
     * 类型（1-投保单、2-保单）
     */
    private String type;

    /**
     * 投保单/保单编号
     */
    private String policyId;

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
     * 性别
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
     * 付款方式（字典）
     */
    private String payType;

    /**
     * 开户人姓名
     */
    private String accountHolder;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String bankAccount;

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


    public InsPolicyHolderEntity() {
    }

    public Long getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(Long policyHolderId) {
        this.policyHolderId = policyHolderId;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
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

    @Override
    public String toString() {
        return "InsPolicyHolderEntity{" +
                "policyHolderId=" + policyHolderId +
                ", type='" + type + '\'' +
                ", policyId='" + policyId + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", annualIncome=" + annualIncome +
                ", homeAddress='" + homeAddress + '\'' +
                ", payType='" + payType + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsPolicyHolderEntity that = (InsPolicyHolderEntity) o;
        return Objects.equals(policyHolderId, that.policyHolderId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(policyId, that.policyId) &&
                Objects.equals(cardType, that.cardType) &&
                Objects.equals(cardNo, that.cardNo) &&
                Objects.equals(name, that.name) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(annualIncome, that.annualIncome) &&
                Objects.equals(homeAddress, that.homeAddress) &&
                Objects.equals(payType, that.payType) &&
                Objects.equals(accountHolder, that.accountHolder) &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(bankAccount, that.bankAccount) &&
                Objects.equals(eduBackground, that.eduBackground) &&
                Objects.equals(maritalStatus, that.maritalStatus) &&
                Objects.equals(companyAddress, that.companyAddress) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(homePostalCode, that.homePostalCode) &&
                Objects.equals(domicileAddress, that.domicileAddress) &&
                Objects.equals(debts, that.debts) &&
                Objects.equals(occupationCode, that.occupationCode) &&
                Objects.equals(occupation, that.occupation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyHolderId, type, policyId, cardType, cardNo, name, mobile, sex, birthday, annualIncome, homeAddress, payType, accountHolder, bankName, bankAccount, eduBackground, maritalStatus, companyAddress, telephone, email, homePostalCode, domicileAddress, debts, occupationCode, occupation);
    }
}
