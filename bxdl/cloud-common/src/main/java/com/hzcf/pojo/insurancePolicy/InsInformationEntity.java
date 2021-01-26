package com.hzcf.pojo.insurancePolicy;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

public class InsInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 产品表主键
     */
    private Long productId;

    /**
     * 保额
     */
    private BigDecimal insuredAmount;

    /**
     * 缴费 年期/年龄 标识（主键待定）
     */
    private String paymentPeriodSign;

    /**
     * 保险期间标识（待定主键）
     */
    private String periodOf;

    /**
     * 主附险标识 （待定主键）
     */
    private String principalDeputySign;

    /**
     * 保费
     */
    private BigDecimal premium;

    /**
     * 缴费方式
     */
    private String paymentMethod;

    /**
     * 缴费
     */
    private Integer payment;

    /**
     * 保险期间
     */
    private Integer periodOfInsurance;

    /**
     * 组合代码
     */
    private String compositeCode;

    /**
     * 投保单主键
     */
    private String policyId;

    /**
     * 份数
     */
    private String size;

    /**
     * 寿险协议id
     */
    private Long protocolId;


    public InsInformationEntity() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(BigDecimal insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public String getPaymentPeriodSign() {
        return paymentPeriodSign;
    }

    public void setPaymentPeriodSign(String paymentPeriodSign) {
        this.paymentPeriodSign = paymentPeriodSign;
    }

    public String getPeriodOf() {
        return periodOf;
    }

    public void setPeriodOf(String periodOf) {
        this.periodOf = periodOf;
    }

    public String getPrincipalDeputySign() {
        return principalDeputySign;
    }

    public void setPrincipalDeputySign(String principalDeputySign) {
        this.principalDeputySign = principalDeputySign;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getPeriodOfInsurance() {
        return periodOfInsurance;
    }

    public void setPeriodOfInsurance(Integer periodOfInsurance) {
        this.periodOfInsurance = periodOfInsurance;
    }

    public String getCompositeCode() {
        return compositeCode;
    }

    public void setCompositeCode(String compositeCode) {
        this.compositeCode = compositeCode;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        InsInformationEntity that = (InsInformationEntity) o;

        return new EqualsBuilder()
                .append(productId, that.productId)
                .append(insuredAmount, that.insuredAmount)
                .append(paymentPeriodSign, that.paymentPeriodSign)
                .append(periodOf, that.periodOf)
                .append(principalDeputySign, that.principalDeputySign)
                .append(premium, that.premium)
                .append(paymentMethod, that.paymentMethod)
                .append(payment, that.payment)
                .append(periodOfInsurance, that.periodOfInsurance)
                .append(compositeCode, that.compositeCode)
                .append(policyId, that.policyId)
                .append(size, that.size)
                .append(protocolId, that.protocolId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(productId)
                .append(insuredAmount)
                .append(paymentPeriodSign)
                .append(periodOf)
                .append(principalDeputySign)
                .append(premium)
                .append(paymentMethod)
                .append(payment)
                .append(periodOfInsurance)
                .append(compositeCode)
                .append(policyId)
                .append(size)
                .append(protocolId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InsInformationEntity{" +
                "productId=" + productId +
                ", insuredAmount=" + insuredAmount +
                ", paymentPeriodSign='" + paymentPeriodSign + '\'' +
                ", periodOf='" + periodOf + '\'' +
                ", principalDeputySign='" + principalDeputySign + '\'' +
                ", premium=" + premium +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", payment=" + payment +
                ", periodOfInsurance=" + periodOfInsurance +
                ", compositeCode='" + compositeCode + '\'' +
                ", policyId='" + policyId + '\'' +
                ", size='" + size + '\'' +
                ", protocolId=" + protocolId +
                '}';
    }
}
