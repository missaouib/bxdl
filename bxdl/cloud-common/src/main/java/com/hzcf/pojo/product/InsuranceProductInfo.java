package com.hzcf.pojo.product;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *  保险产品基本信息
 * @author 2019-05-29
 */
public class InsuranceProductInfo implements Serializable {
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
     * 产品id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品简称
     */
    private String productNameLess;

    /**
     * 产品英文名
     */
    private String productEnName;

    /**
     * 产品代码
     */
    private String productCode;

    /**
     * 保险公司
     */
    private Long insuranceCompanyId;

    /**
     * 保险公司险种代码
     */
    private String companyInsuranceCode;

    /**
     * 险种类别
     */
    private String insuranceType;

    /**
     * 保监委险种类别
     */
    private String circInsuranceType;

    /**
     * 主附险标记
     */
    private String mainOrAdditional;

    /**
     * 保险期间方式
     */
    private String insurancePeriodType;

    /**
     * 缴费期间方式
     */
    private String renewalPeriodType;

    /**
     * 卡单产品标记
     */
    private String cardProType;

    /**
     * 长短险标记
     */
    private String longShortRiskType;

    /**
     * 特殊授权标记
     */
    private String specialAuthorization;

    /**
     * 是否需要回执
     */
    private String needReceipt;

    /**
     * 生效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date takeEffectDate;

    /**
     * 失效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date invalidDate;

    /**
     * 团个险标记
     */
    private String groupOrPersonal;

    /**
     * 保监会备案代码
     */
    private String circRecordCode;

    /**
     * 缴费方式
     */
    private String payMode;

    /**
     * 产品状态
     */
    private String productStatus;


    /**
     * 销售类型 0线下1线上2二者皆可
     */
    private String saleType;
    
    /**
     * 自动续保标记（0否 1是）
     */
    private String autoPaySign;

    /**
     * 最大续保年限
     */
    private Integer maxRenewalYears;

    private String companyInsuranceType;
    

    public String getCompanyInsuranceType() {
		return companyInsuranceType;
	}

	public void setCompanyInsuranceType(String companyInsuranceType) {
		this.companyInsuranceType = companyInsuranceType;
	}

	public InsuranceProductInfo() {
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameLess() {
        return productNameLess;
    }

    public void setProductNameLess(String productNameLess) {
        this.productNameLess = productNameLess;
    }

    public String getProductEnName() {
        return productEnName;
    }

    public void setProductEnName(String productEnName) {
        this.productEnName = productEnName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public String getCompanyInsuranceCode() {
        return companyInsuranceCode;
    }

    public void setCompanyInsuranceCode(String companyInsuranceCode) {
        this.companyInsuranceCode = companyInsuranceCode;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getCircInsuranceType() {
        return circInsuranceType;
    }

    public void setCircInsuranceType(String circInsuranceType) {
        this.circInsuranceType = circInsuranceType;
    }

    public String getMainOrAdditional() {
        return mainOrAdditional;
    }

    public void setMainOrAdditional(String mainOrAdditional) {
        this.mainOrAdditional = mainOrAdditional;
    }

    public String getInsurancePeriodType() {
        return insurancePeriodType;
    }

    public void setInsurancePeriodType(String insurancePeriodType) {
        this.insurancePeriodType = insurancePeriodType;
    }

    public String getRenewalPeriodType() {
        return renewalPeriodType;
    }

    public void setRenewalPeriodType(String renewalPeriodType) {
        this.renewalPeriodType = renewalPeriodType;
    }

    public String getCardProType() {
        return cardProType;
    }

    public void setCardProType(String cardProType) {
        this.cardProType = cardProType;
    }

    public String getLongShortRiskType() {
        return longShortRiskType;
    }

    public void setLongShortRiskType(String longShortRiskType) {
        this.longShortRiskType = longShortRiskType;
    }

    public String getSpecialAuthorization() {
        return specialAuthorization;
    }

    public void setSpecialAuthorization(String specialAuthorization) {
        this.specialAuthorization = specialAuthorization;
    }

    public String getNeedReceipt() {
        return needReceipt;
    }

    public void setNeedReceipt(String needReceipt) {
        this.needReceipt = needReceipt;
    }

    public Date getTakeEffectDate() {
        return takeEffectDate;
    }

    public void setTakeEffectDate(Date takeEffectDate) {
        this.takeEffectDate = takeEffectDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getGroupOrPersonal() {
        return groupOrPersonal;
    }

    public void setGroupOrPersonal(String groupOrPersonal) {
        this.groupOrPersonal = groupOrPersonal;
    }

    public String getCircRecordCode() {
        return circRecordCode;
    }

    public void setCircRecordCode(String circRecordCode) {
        this.circRecordCode = circRecordCode;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
  
    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }
    
    public String getAutoPaySign() {
        return autoPaySign;
    }

    public void setAutoPaySign(String autoPaySign) {
        this.autoPaySign = autoPaySign == null ? null : autoPaySign.trim();
    }

    public Integer getMaxRenewalYears() {
        return maxRenewalYears;
    }

    public void setMaxRenewalYears(Integer maxRenewalYears) {
        this.maxRenewalYears = maxRenewalYears;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceProductInfo that = (InsuranceProductInfo) o;
        return Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(updatedTime, that.updatedTime) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productNameLess, that.productNameLess) &&
                Objects.equals(productEnName, that.productEnName) &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(insuranceCompanyId, that.insuranceCompanyId) &&
                Objects.equals(companyInsuranceCode, that.companyInsuranceCode) &&
                Objects.equals(insuranceType, that.insuranceType) &&
                Objects.equals(circInsuranceType, that.circInsuranceType) &&
                Objects.equals(mainOrAdditional, that.mainOrAdditional) &&
                Objects.equals(insurancePeriodType, that.insurancePeriodType) &&
                Objects.equals(renewalPeriodType, that.renewalPeriodType) &&
                Objects.equals(cardProType, that.cardProType) &&
                Objects.equals(longShortRiskType, that.longShortRiskType) &&
                Objects.equals(specialAuthorization, that.specialAuthorization) &&
                Objects.equals(needReceipt, that.needReceipt) &&
                Objects.equals(takeEffectDate, that.takeEffectDate) &&
                Objects.equals(invalidDate, that.invalidDate) &&
                Objects.equals(groupOrPersonal, that.groupOrPersonal) &&
                Objects.equals(circRecordCode, that.circRecordCode) &&
                Objects.equals(payMode, that.payMode) &&
                Objects.equals(productStatus, that.productStatus) &&
                Objects.equals(companyInsuranceType, that.companyInsuranceType) &&
                Objects.equals(saleType, that.saleType)&&
        Objects.equals(autoPaySign, that.autoPaySign)&&
        Objects.equals(maxRenewalYears, that.maxRenewalYears);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, createdTime, updatedBy, updatedTime, productId, productName, productNameLess, productEnName, productCode, insuranceCompanyId, companyInsuranceCode, insuranceType, circInsuranceType, mainOrAdditional, insurancePeriodType, renewalPeriodType, cardProType, longShortRiskType, specialAuthorization, needReceipt, takeEffectDate, invalidDate, groupOrPersonal, circRecordCode, payMode, productStatus, companyInsuranceType, saleType,autoPaySign,maxRenewalYears);
    }

    @Override
    public String toString() {
        return "InsuranceProductInfo{" +
                "createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productNameLess='" + productNameLess + '\'' +
                ", productEnName='" + productEnName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", insuranceCompanyId=" + insuranceCompanyId +
                ", companyInsuranceCode='" + companyInsuranceCode + '\'' +
                ", insuranceType='" + insuranceType + '\'' +
                ", circInsuranceType='" + circInsuranceType + '\'' +
                ", mainOrAdditional='" + mainOrAdditional + '\'' +
                ", insurancePeriodType='" + insurancePeriodType + '\'' +
                ", renewalPeriodType='" + renewalPeriodType + '\'' +
                ", cardProType='" + cardProType + '\'' +
                ", longShortRiskType='" + longShortRiskType + '\'' +
                ", specialAuthorization='" + specialAuthorization + '\'' +
                ", needReceipt='" + needReceipt + '\'' +
                ", takeEffectDate=" + takeEffectDate +
                ", invalidDate=" + invalidDate +
                ", groupOrPersonal='" + groupOrPersonal + '\'' +
                ", circRecordCode='" + circRecordCode + '\'' +
                ", payMode='" + payMode + '\'' +
                ", productStatus='" + productStatus + '\'' +
                ", companyInsuranceType=" + companyInsuranceType +
                ", saleType='" + saleType + '\'' +
                  ", autoPaySign='" + autoPaySign + '\'' +
                    ", maxRenewalYears='" + maxRenewalYears + '\'' +
                '}';
    }
}
