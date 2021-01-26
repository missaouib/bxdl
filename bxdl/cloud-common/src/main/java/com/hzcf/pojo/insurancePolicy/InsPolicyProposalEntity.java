package com.hzcf.pojo.insurancePolicy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class InsPolicyProposalEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 投保单号
     */
    private String proposalId;

    /**
     * 保险公司id（insurance_company_org表主键）
     */
    private Long insuranceCompanyId;

    /**
     * 总保费（元）
     */
    private BigDecimal totalPremium;

    /**
     * 投保日期
     */
    private String proposeDate;

    /**
     * 代理人id（员工表主键）
     */
    private Long agentId;


    public InsPolicyProposalEntity() {
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

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public BigDecimal getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(BigDecimal totalPremium) {
        this.totalPremium = totalPremium;
    }

    public String getProposeDate() {
        return proposeDate;
    }

    public void setProposeDate(String proposeDate) {
        this.proposeDate = proposeDate;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsPolicyProposalEntity that = (InsPolicyProposalEntity) o;
        return Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(updatedTime, that.updatedTime) &&
                Objects.equals(proposalId, that.proposalId) &&
                Objects.equals(insuranceCompanyId, that.insuranceCompanyId) &&
                Objects.equals(totalPremium, that.totalPremium) &&
                Objects.equals(proposeDate, that.proposeDate) &&
                Objects.equals(agentId, that.agentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, createdTime, updatedBy, updatedTime, proposalId, insuranceCompanyId, totalPremium, proposeDate, agentId);
    }

    @Override
    public String toString() {
        return "InsPolicyProposalEntity{" +
                "createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", proposalId='" + proposalId + '\'' +
                ", insuranceCompanyId=" + insuranceCompanyId +
                ", totalPremium=" + totalPremium +
                ", proposeDate='" + proposeDate + '\'' +
                ", agentId=" + agentId +
                '}';
    }
}
