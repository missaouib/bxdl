package com.hzcf.pojo.insurance.sales;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>员工考核条件</p>
 *
 * @author kongqingbao
 */
public class SalesAssess implements Serializable {
    private static final long serialVersionUID = 7314384174835134400L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 员工id
     */
    private Long insuranceSalesId;
    /**
     * 职级id
     */
    private Long salesGradeId;
    /**
     * 职级名称
     */
    private String salesGradeName;
    /**
     * 条件1  新人岗前培训是否通过
     */
    private String condition1;
    /**
     * 条件2  日常会议及培训出勤率是否不低于70%
     */
    private String condition2;
    /**
     * 条件3  晋级考核是否通过
     */
    private String condition3;
    /**
     * 条件4  处长考核是否通过
     */
    private String condition4;
    /**
     * 条件5  是否认同公司文化
     */
    private String condition5;
    /**
     * 条件6 总经理面谈是否通过
     */
    private String condition6;
    /**
     * 条件7  部长考核是否通过
     */
    private String condition7;
    /**
     * 条件8  代理制保险营销员品质考核是否通过
     */
    private String condition8;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInsuranceSalesId() {
        return insuranceSalesId;
    }

    public void setInsuranceSalesId(Long insuranceSalesId) {
        this.insuranceSalesId = insuranceSalesId;
    }

    public Long getSalesGradeId() {
        return salesGradeId;
    }

    public void setSalesGradeId(Long salesGradeId) {
        this.salesGradeId = salesGradeId;
    }

    public String getSalesGradeName() {
        return salesGradeName;
    }

    public void setSalesGradeName(String salesGradeName) {
        this.salesGradeName = salesGradeName;
    }

    public String getCondition1() {
        return condition1;
    }

    public void setCondition1(String condition1) {
        this.condition1 = condition1;
    }

    public String getCondition2() {
        return condition2;
    }

    public void setCondition2(String condition2) {
        this.condition2 = condition2;
    }

    public String getCondition3() {
        return condition3;
    }

    public void setCondition3(String condition3) {
        this.condition3 = condition3;
    }

    public String getCondition4() {
        return condition4;
    }

    public void setCondition4(String condition4) {
        this.condition4 = condition4;
    }

    public String getCondition5() {
        return condition5;
    }

    public void setCondition5(String condition5) {
        this.condition5 = condition5;
    }

    public String getCondition6() {
        return condition6;
    }

    public void setCondition6(String condition6) {
        this.condition6 = condition6;
    }

    public String getCondition7() {
        return condition7;
    }

    public void setCondition7(String condition7) {
        this.condition7 = condition7;
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

    public String getCondition8() {
        return condition8;
    }

    public void setCondition8(String condition8) {
        this.condition8 = condition8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        SalesAssess that = (SalesAssess) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(insuranceSalesId, that.insuranceSalesId)
                .append(salesGradeId, that.salesGradeId)
                .append(salesGradeName, that.salesGradeName)
                .append(condition1, that.condition1)
                .append(condition2, that.condition2)
                .append(condition3, that.condition3)
                .append(condition4, that.condition4)
                .append(condition5, that.condition5)
                .append(condition6, that.condition6)
                .append(condition7, that.condition7)
                .append(condition8, that.condition8)
                .append(createdBy, that.createdBy)
                .append(createdTime, that.createdTime)
                .append(updatedBy, that.updatedBy)
                .append(updatedTime, that.updatedTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(insuranceSalesId)
                .append(salesGradeId)
                .append(salesGradeName)
                .append(condition1)
                .append(condition2)
                .append(condition3)
                .append(condition4)
                .append(condition5)
                .append(condition6)
                .append(condition7)
                .append(condition8)
                .append(createdBy)
                .append(createdTime)
                .append(updatedBy)
                .append(updatedTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "SalesAssess{" +
                "id=" + id +
                ", insuranceSalesId=" + insuranceSalesId +
                ", salesGradeId=" + salesGradeId +
                ", salesGradeName='" + salesGradeName + '\'' +
                ", condition1='" + condition1 + '\'' +
                ", condition2='" + condition2 + '\'' +
                ", condition3='" + condition3 + '\'' +
                ", condition4='" + condition4 + '\'' +
                ", condition5='" + condition5 + '\'' +
                ", condition6='" + condition6 + '\'' +
                ", condition7='" + condition7 + '\'' +
                ", condition8='" + condition8 + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
