package com.hzcf.pojo.empWorkGroup;

import java.util.Objects;

public class EmpWorkGroupPojo {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 员工id
     */
    private Integer insuranceSalesId;

    /**
     * 部门id
     */
    private Integer salesOrgId;

    /**
     * 选中状态：选中： true ，不选中： false
     */
    private String checkedStatus;

    /**
     * 半选状态： 半选: true ，不半选: false
     */
    private String halfStatus;


    public EmpWorkGroupPojo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInsuranceSalesId() {
        return insuranceSalesId;
    }

    public void setInsuranceSalesId(Integer insuranceSalesId) {
        this.insuranceSalesId = insuranceSalesId;
    }

    public Integer getSalesOrgId() {
        return salesOrgId;
    }

    public void setSalesOrgId(Integer salesOrgId) {
        this.salesOrgId = salesOrgId;
    }

    public String getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(String checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public String getHalfStatus() {
        return halfStatus;
    }

    public void setHalfStatus(String halfStatus) {
        this.halfStatus = halfStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpWorkGroupPojo that = (EmpWorkGroupPojo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(insuranceSalesId, that.insuranceSalesId) &&
                Objects.equals(salesOrgId, that.salesOrgId) &&
                Objects.equals(checkedStatus, that.checkedStatus) &&
                Objects.equals(halfStatus, that.halfStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, insuranceSalesId, salesOrgId, checkedStatus, halfStatus);
    }

    @Override
    public String toString() {
        return "EmpWorkGroupPojo{" +
                "id=" + id +
                ", insuranceSalesId=" + insuranceSalesId +
                ", salesOrgId=" + salesOrgId +
                ", checkedStatus='" + checkedStatus + '\'' +
                ", halfStatus='" + halfStatus + '\'' +
                '}';
    }


}
