package com.hzcf.pojo.insurance.sales;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SalerRelationChange {
    private Long id;

    private Long salerId;

    private String relationMonth;

    private Long dbBeforeSalesId;

    private Long dbAfterSalesId;

    private Long tjBeforeSalesId;

    private Long tjAfterSalesId;

    private Long sjBeforeSalesId;

    private Long sjAfterSalesId;

    private Long jcBeforeSalesId;

    private Long jcAfterSalesId;

    private Long fdBeforeSalesId;

    private Long fdAfterSalesId;

    private Long ycBeforeCFirstGener;

    private Long ycAfterCFirstGener;

    private Long ycBeforeCSecondGener;

    private Long ycAfterCSecondGener;

    private Long ycBeforeBFirstGener;

    private Long ycAfterBFirstGener;

    private Long ycBeforeBSecondGener;

    private Long ycAfterBSecondGener;

    private Long directBeforeGroupC;

    private Long directAfterGroupC;

    private Long directBeforeDeptB;

    private Long directAfterDeptB;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    private String isFinished;

    private String dbSalesDate;

    private String tjSalesDate;

    private String sjSalesDate;

    private String jcSalesDate;

    private String fdSalesDate;

    private String establishTime;

    private String establishTime1;

    private String establishTime2;

    private String establishTime3;

    private String establishTime4;

    private String establishTime5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getDbBeforeSalesId() {
        return dbBeforeSalesId;
    }

    public void setDbBeforeSalesId(Long dbBeforeSalesId) {
        this.dbBeforeSalesId = dbBeforeSalesId;
    }

    public Long getDbAfterSalesId() {
        return dbAfterSalesId;
    }

    public void setDbAfterSalesId(Long dbAfterSalesId) {
        this.dbAfterSalesId = dbAfterSalesId;
    }

    public Long getTjBeforeSalesId() {
        return tjBeforeSalesId;
    }

    public void setTjBeforeSalesId(Long tjBeforeSalesId) {
        this.tjBeforeSalesId = tjBeforeSalesId;
    }

    public Long getTjAfterSalesId() {
        return tjAfterSalesId;
    }

    public void setTjAfterSalesId(Long tjAfterSalesId) {
        this.tjAfterSalesId = tjAfterSalesId;
    }

    public Long getSjBeforeSalesId() {
        return sjBeforeSalesId;
    }

    public void setSjBeforeSalesId(Long sjBeforeSalesId) {
        this.sjBeforeSalesId = sjBeforeSalesId;
    }

    public Long getSjAfterSalesId() {
        return sjAfterSalesId;
    }

    public void setSjAfterSalesId(Long sjAfterSalesId) {
        this.sjAfterSalesId = sjAfterSalesId;
    }

    public Long getJcBeforeSalesId() {
        return jcBeforeSalesId;
    }

    public void setJcBeforeSalesId(Long jcBeforeSalesId) {
        this.jcBeforeSalesId = jcBeforeSalesId;
    }

    public Long getJcAfterSalesId() {
        return jcAfterSalesId;
    }

    public void setJcAfterSalesId(Long jcAfterSalesId) {
        this.jcAfterSalesId = jcAfterSalesId;
    }

    public Long getFdBeforeSalesId() {
        return fdBeforeSalesId;
    }

    public void setFdBeforeSalesId(Long fdBeforeSalesId) {
        this.fdBeforeSalesId = fdBeforeSalesId;
    }

    public Long getFdAfterSalesId() {
        return fdAfterSalesId;
    }

    public void setFdAfterSalesId(Long fdAfterSalesId) {
        this.fdAfterSalesId = fdAfterSalesId;
    }

    public Long getYcBeforeCFirstGener() {
        return ycBeforeCFirstGener;
    }

    public void setYcBeforeCFirstGener(Long ycBeforeCFirstGener) {
        this.ycBeforeCFirstGener = ycBeforeCFirstGener;
    }

    public Long getYcAfterCFirstGener() {
        return ycAfterCFirstGener;
    }

    public void setYcAfterCFirstGener(Long ycAfterCFirstGener) {
        this.ycAfterCFirstGener = ycAfterCFirstGener;
    }

    public Long getYcBeforeCSecondGener() {
        return ycBeforeCSecondGener;
    }

    public void setYcBeforeCSecondGener(Long ycBeforeCSecondGener) {
        this.ycBeforeCSecondGener = ycBeforeCSecondGener;
    }

    public Long getYcAfterCSecondGener() {
        return ycAfterCSecondGener;
    }

    public void setYcAfterCSecondGener(Long ycAfterCSecondGener) {
        this.ycAfterCSecondGener = ycAfterCSecondGener;
    }

    public Long getYcBeforeBFirstGener() {
        return ycBeforeBFirstGener;
    }

    public void setYcBeforeBFirstGener(Long ycBeforeBFirstGener) {
        this.ycBeforeBFirstGener = ycBeforeBFirstGener;
    }

    public Long getYcAfterBFirstGener() {
        return ycAfterBFirstGener;
    }

    public void setYcAfterBFirstGener(Long ycAfterBFirstGener) {
        this.ycAfterBFirstGener = ycAfterBFirstGener;
    }

    public Long getYcBeforeBSecondGener() {
        return ycBeforeBSecondGener;
    }

    public void setYcBeforeBSecondGener(Long ycBeforeBSecondGener) {
        this.ycBeforeBSecondGener = ycBeforeBSecondGener;
    }

    public Long getYcAfterBSecondGener() {
        return ycAfterBSecondGener;
    }

    public void setYcAfterBSecondGener(Long ycAfterBSecondGener) {
        this.ycAfterBSecondGener = ycAfterBSecondGener;
    }

    public Long getDirectBeforeGroupC() {
        return directBeforeGroupC;
    }

    public void setDirectBeforeGroupC(Long directBeforeGroupC) {
        this.directBeforeGroupC = directBeforeGroupC;
    }

    public Long getDirectAfterGroupC() {
        return directAfterGroupC;
    }

    public void setDirectAfterGroupC(Long directAfterGroupC) {
        this.directAfterGroupC = directAfterGroupC;
    }

    public Long getDirectBeforeDeptB() {
        return directBeforeDeptB;
    }

    public void setDirectBeforeDeptB(Long directBeforeDeptB) {
        this.directBeforeDeptB = directBeforeDeptB;
    }

    public Long getDirectAfterDeptB() {
        return directAfterDeptB;
    }

    public void setDirectAfterDeptB(Long directAfterDeptB) {
        this.directAfterDeptB = directAfterDeptB;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished == null ? null : isFinished.trim();
    }

    public String getDbSalesDate() {
        return dbSalesDate;
    }

    public void setDbSalesDate(String dbSalesDate) {
        this.dbSalesDate = dbSalesDate;
    }

    public String getTjSalesDate() {
        return tjSalesDate;
    }

    public void setTjSalesDate(String tjSalesDate) {
        this.tjSalesDate = tjSalesDate;
    }

    public String getSjSalesDate() {
        return sjSalesDate;
    }

    public void setSjSalesDate(String sjSalesDate) {
        this.sjSalesDate = sjSalesDate;
    }

    public String getJcSalesDate() {
        return jcSalesDate;
    }

    public void setJcSalesDate(String jcSalesDate) {
        this.jcSalesDate = jcSalesDate;
    }

    public String getFdSalesDate() {
        return fdSalesDate;
    }

    public void setFdSalesDate(String fdSalesDate) {
        this.fdSalesDate = fdSalesDate;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public String getEstablishTime1() {
        return establishTime1;
    }

    public void setEstablishTime1(String establishTime1) {
        this.establishTime1 = establishTime1;
    }

    public String getEstablishTime2() {
        return establishTime2;
    }

    public void setEstablishTime2(String establishTime2) {
        this.establishTime2 = establishTime2;
    }

    public String getEstablishTime3() {
        return establishTime3;
    }

    public void setEstablishTime3(String establishTime3) {
        this.establishTime3 = establishTime3;
    }

    public String getEstablishTime4() {
        return establishTime4;
    }

    public void setEstablishTime4(String establishTime4) {
        this.establishTime4 = establishTime4;
    }

    public String getEstablishTime5() {
        return establishTime5;
    }

    public void setEstablishTime5(String establishTime5) {
        this.establishTime5 = establishTime5;
    }
}