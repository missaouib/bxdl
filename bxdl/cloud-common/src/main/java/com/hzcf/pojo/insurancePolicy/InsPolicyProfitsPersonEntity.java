package com.hzcf.pojo.insurancePolicy;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class InsPolicyProfitsPersonEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long profitsPersonId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更改时间
     */
    private Date updateTime;

    /**
     * 更改人
     */
    private String updateBy;

    /**
     * 受益人姓名
     */
    private String name;

    /**
     * 受益人证件类型
     */
    private String cardType;

    /**
     * 受益人证件号
     */
    private String cardNo;

    /**
     * 受益人性别
     */
    private String sex;

    /**
     * 受益人出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private String birthday;

    /**
     * 受益人基本-第一受益人-第二受益人等
     */
    private String level;

    /**
     * 受益比例
     */
    private String benefitRatio;

    /**
     * 与被保护人的关系
     */
    private String relationship;

    /**
     * 投保单主键
     */
    private String policyId;


    public InsPolicyProfitsPersonEntity() {
    }

    public Long getProfitsPersonId() {
        return profitsPersonId;
    }

    public void setProfitsPersonId(Long profitsPersonId) {
        this.profitsPersonId = profitsPersonId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBenefitRatio() {
        return benefitRatio;
    }

    public void setBenefitRatio(String benefitRatio) {
        this.benefitRatio = benefitRatio;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsPolicyProfitsPersonEntity that = (InsPolicyProfitsPersonEntity) o;
        return Objects.equals(profitsPersonId, that.profitsPersonId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(name, that.name) &&
                Objects.equals(cardType, that.cardType) &&
                Objects.equals(cardNo, that.cardNo) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(level, that.level) &&
                Objects.equals(benefitRatio, that.benefitRatio) &&
                Objects.equals(relationship, that.relationship) &&
                Objects.equals(policyId, that.policyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profitsPersonId, createTime, createBy, updateTime, updateBy, name, cardType, cardNo, sex, birthday, level, benefitRatio, relationship, policyId);
    }

    @Override
    public String toString() {
        return "InsPolicyProfitsPersonEntity{" +
                "profitsPersonId=" + profitsPersonId +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", level='" + level + '\'' +
                ", benefitRatio='" + benefitRatio + '\'' +
                ", relationship='" + relationship + '\'' +
                ", policyId='" + policyId + '\'' +
                '}';
    }
}
