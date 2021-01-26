package com.hzcf.pojo.insurancePolicy;

import java.io.Serializable;
import java.util.Objects;

public class InsPolicyAttEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 类型（1-投保单、2-保单）
     */
    private String type;

    /**
     * 投保单/保单编号
     */
    private String policyId;

    /**
     * 主键id
     */
    private Long attId;

    /**
     * 附件名称
     */
    private String attName;

    /**
     * 附件地址（fastdfs）
     */
    private String attAdd;


    public InsPolicyAttEntity() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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

    public Long getAttId() {
        return attId;
    }

    public void setAttId(Long attId) {
        this.attId = attId;
    }

    public String getAttName() {
        return attName;
    }

    public void setAttName(String attName) {
        this.attName = attName;
    }

    public String getAttAdd() {
        return attAdd;
    }

    public void setAttAdd(String attAdd) {
        this.attAdd = attAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsPolicyAttEntity that = (InsPolicyAttEntity) o;
        return Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(type, that.type) &&
                Objects.equals(policyId, that.policyId) &&
                Objects.equals(attId, that.attId) &&
                Objects.equals(attName, that.attName) &&
                Objects.equals(attAdd, that.attAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, createdTime, type, policyId, attId, attName, attAdd);
    }

    @Override
    public String toString() {
        return "InsPolicyAttEntity{" +
                "createdBy='" + createdBy + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", type='" + type + '\'' +
                ", policyId='" + policyId + '\'' +
                ", attId=" + attId +
                ", attName='" + attName + '\'' +
                ", attAdd='" + attAdd + '\'' +
                '}';
    }
}
