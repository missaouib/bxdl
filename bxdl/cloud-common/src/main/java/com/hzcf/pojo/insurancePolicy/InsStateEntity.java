package com.hzcf.pojo.insurancePolicy;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class InsStateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态
     */
    private String state;

    /**
     * 保单号
     */
    private String policyId;

    /**
     * type
     */
    private String type;


    public InsStateEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsStateEntity that = (InsStateEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(state, that.state) &&
                Objects.equals(policyId, that.policyId) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createBy, createTime, state, policyId, type);
    }

    @Override
    public String toString() {
        return "InsStateEntity{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", state='" + state + '\'' +
                ", policyId='" + policyId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
