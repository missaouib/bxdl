package com.hzcf.pojo.insurance.sales;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SalerMonthlyRelation {

    private Long salerId;

    private String relationMonth;

    private Long dbSalesId;

    private Long tjSalesId;

    private Long sjSalesId;

    private Long jcSalesId;

    private Long fdSalesId;

    private Long ycCFirstGener;

    private Long ycCSecondGener;

    private Long ycBFirstGener;

    private Long ycBSecondGener;

    private Long directGroupC;

    private Long directDeptB;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date dbSalesDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date tjSalesDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date sjSalesDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date jcSalesDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date fdSalesDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date establishTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date establishTime1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date establishTime2;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date establishTime3;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date establishTime4;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date establishTime5;

    public Long getSalerId() {
        return salerId;
    }

    public void setSalerId(Long salerId) {
        this.salerId = salerId;
    }

    public String getRelationMonth() {
        return relationMonth;
    }

    public void setRelationMonth(String relationMonth) {
        this.relationMonth = relationMonth == null ? null : relationMonth.trim();
    }

    public Long getDbSalesId() {
        return dbSalesId;
    }

    public void setDbSalesId(Long dbSalesId) {
        this.dbSalesId = dbSalesId;
    }

    public Long getTjSalesId() {
        return tjSalesId;
    }

    public void setTjSalesId(Long tjSalesId) {
        this.tjSalesId = tjSalesId;
    }

    public Long getSjSalesId() {
        return sjSalesId;
    }

    public void setSjSalesId(Long sjSalesId) {
        this.sjSalesId = sjSalesId;
    }

    public Long getJcSalesId() {
        return jcSalesId;
    }

    public void setJcSalesId(Long jcSalesId) {
        this.jcSalesId = jcSalesId;
    }

    public Long getFdSalesId() {
        return fdSalesId;
    }

    public void setFdSalesId(Long fdSalesId) {
        this.fdSalesId = fdSalesId;
    }

    public Long getYcCFirstGener() {
        return ycCFirstGener;
    }

    public void setYcCFirstGener(Long ycCFirstGener) {
        this.ycCFirstGener = ycCFirstGener;
    }

    public Long getYcCSecondGener() {
        return ycCSecondGener;
    }

    public void setYcCSecondGener(Long ycCSecondGener) {
        this.ycCSecondGener = ycCSecondGener;
    }

    public Long getYcBFirstGener() {
        return ycBFirstGener;
    }

    public void setYcBFirstGener(Long ycBFirstGener) {
        this.ycBFirstGener = ycBFirstGener;
    }

    public Long getYcBSecondGener() {
        return ycBSecondGener;
    }

    public void setYcBSecondGener(Long ycBSecondGener) {
        this.ycBSecondGener = ycBSecondGener;
    }

    public Long getDirectGroupC() {
        return directGroupC;
    }

    public void setDirectGroupC(Long directGroupC) {
        this.directGroupC = directGroupC;
    }

    public Long getDirectDeptB() {
        return directDeptB;
    }

    public void setDirectDeptB(Long directDeptB) {
        this.directDeptB = directDeptB;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDbSalesDate() {
        return dbSalesDate;
    }

    public void setDbSalesDate(Date dbSalesDate) {
        this.dbSalesDate = dbSalesDate;
    }

    public Date getTjSalesDate() {
        return tjSalesDate;
    }

    public void setTjSalesDate(Date tjSalesDate) {
        this.tjSalesDate = tjSalesDate;
    }

    public Date getSjSalesDate() {
        return sjSalesDate;
    }

    public void setSjSalesDate(Date sjSalesDate) {
        this.sjSalesDate = sjSalesDate;
    }

    public Date getJcSalesDate() {
        return jcSalesDate;
    }

    public void setJcSalesDate(Date jcSalesDate) {
        this.jcSalesDate = jcSalesDate;
    }

    public Date getFdSalesDate() {
        return fdSalesDate;
    }

    public void setFdSalesDate(Date fdSalesDate) {
        this.fdSalesDate = fdSalesDate;
    }

    public Date getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }

    public Date getEstablishTime1() {
        return establishTime1;
    }

    public void setEstablishTime1(Date establishTime1) {
        this.establishTime1 = establishTime1;
    }

    public Date getEstablishTime2() {
        return establishTime2;
    }

    public void setEstablishTime2(Date establishTime2) {
        this.establishTime2 = establishTime2;
    }

    public Date getEstablishTime3() {
        return establishTime3;
    }

    public void setEstablishTime3(Date establishTime3) {
        this.establishTime3 = establishTime3;
    }

    public Date getEstablishTime4() {
        return establishTime4;
    }

    public void setEstablishTime4(Date establishTime4) {
        this.establishTime4 = establishTime4;
    }

    public Date getEstablishTime5() {
        return establishTime5;
    }

    public void setEstablishTime5(Date establishTime5) {
        this.establishTime5 = establishTime5;
    }
}