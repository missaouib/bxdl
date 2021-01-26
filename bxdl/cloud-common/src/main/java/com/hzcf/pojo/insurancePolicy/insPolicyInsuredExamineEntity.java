package com.hzcf.pojo.insurancePolicy;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class insPolicyInsuredExamineEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 保单号
     */
    private String policyId;

    /**
     * 审核结果
     */
    private String auditresults;

    /**
     * 审核意见
     */
    private String auditopinions;

    /**
     * examine_person
     */
    private String examinePerson;

    /**
     * examine_time
     */
    private Date examineTime;


    public insPolicyInsuredExamineEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getAuditresults() {
        return auditresults;
    }

    public void setAuditresults(String auditresults) {
        this.auditresults = auditresults;
    }

    public String getAuditopinions() {
        return auditopinions;
    }

    public void setAuditopinions(String auditopinions) {
        this.auditopinions = auditopinions;
    }

    public String getExaminePerson() {
        return examinePerson;
    }

    public void setExaminePerson(String examinePerson) {
        this.examinePerson = examinePerson;
    }

    public Date getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        insPolicyInsuredExamineEntity that = (insPolicyInsuredExamineEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(policyId, that.policyId) &&
                Objects.equals(auditresults, that.auditresults) &&
                Objects.equals(auditopinions, that.auditopinions) &&
                Objects.equals(examinePerson, that.examinePerson) &&
                Objects.equals(examineTime, that.examineTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, policyId, auditresults, auditopinions, examinePerson, examineTime);
    }

    @Override
    public String toString() {
        return "insPolicyInsuredExamineEntity{" +
                "id=" + id +
                ", policyId='" + policyId + '\'' +
                ", auditresults='" + auditresults + '\'' +
                ", auditopinions='" + auditopinions + '\'' +
                ", examinePerson='" + examinePerson + '\'' +
                ", examineTime=" + examineTime +
                '}';
    }
}
