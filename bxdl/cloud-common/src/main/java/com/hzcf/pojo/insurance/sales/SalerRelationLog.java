package com.hzcf.pojo.insurance.sales;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

public class SalerRelationLog implements Serializable {
    //"主键"
    private Long id;

    //"销售人员id"
    private Long salerId;

    //"变更前担保人"
    private Long dbBeforeSalesId;

    //"变更后担保人"
    private Long dbAfterSalesId;

    //"变更后推荐人"
    private Long tjBeforeSalesId;

    //"变更后推荐人"
    private Long tjAfterSalesId;

    //"变更前上级管理人"
    private Long sjBeforeSalesId;

    //"变更后上级管理人"
    private Long sjAfterSalesId;

    //"变更前继承人"
    private Long jcBeforeSalesId;

    //"变更后继承人"
    private Long jcAfterSalesId;

    //"变更前辅导人"
    private Long fdBeforeSalesId;

    //"变更后辅导人"
    private Long fdAfterSalesId;

    //"变更前一代处育成人"
    private Long ycBeforeCFirstGener;

    //"变更后一代处育成人"
    private Long ycAfterCFirstGener;

    //"变更前二代处育成人"
    private Long ycBeforeCSecondGener;

    //"变更后二代处育成人"
    private Long ycAfterCSecondGener;

    //"变更前一代部育成人"
    private Long ycBeforeBFirstGener;

    //"变更后一代部育成人"
    private Long ycAfterBFirstGener;

    //"变更前二代部育成人"
    private Long ycBeforeBSecondGener;

    //"变更后二代部育成人"
    private Long ycAfterBSecondGener;

    //"变更前直属组处长"
    private Long directBeforeGroupC;

    //"变更后直属组处长"
    private Long directAfterGroupC;

    //"变更前直属部部长"
    private Long directBeforeDeptB;

    //"变更后直属部部长"
    private Long directAfterDeptB;

    /**
     * 变更前担保人确立时间"
     *
     */
    private String dbBeforeSalesDate;

    //"变更前推荐人确立时间"
    private String tjBeforeSalesDate;

    //"变更前上级管理人确立时间"
    private String sjBeforeSalesDate;

    //"变更前继承人确立时间"
    private String jcBeforeSalesDate;

    //"变更前辅导人确立时间"
    private String fdBeforeSalesDate;

    //"变更前一代处育成人确立时间"
    private String establishBeforeTime;

    //"变更前二代处育成人确立时间"
    private String establishBeforeTime1;

    //"变更前一代部育成人确立时间"
    private String establishBeforeTime2;

    //"变更前二代部育成人确立时间"
    private String establishBeforeTime3;

    //"变更前直属组处长确立时间"
    private String establishBeforeTime4;

    //"变更前直属部部长确立时间"
    private String establishBeforeTime5;

    //"变更后担保人确立时间"
    private String dbAfterSalesDate;

    //"变更后推荐人确立时间"
    private String tjAfterSalesDate;

    //"变更后上级管理人确立时间"
    private String sjAfterSalesDate;

    //"变更后继承人确立时间"
    private String jcAfterSalesDate;

    //"变更后辅导人确立时间"
    private String fdAfterSalesDate;

    //"变更后一代处育成人确立时间"
    private String establishAfterTime;

    //"变更后二代处育成人确立时间"
    private String establishAfterTime1;

    //"变更后一代部育成人确立时间"
    private String establishAfterTime2;

    //"变更后二代部育成人确立时间"
    private String establishAfterTime3;

    //"变更后直属组处长确立时间"
    private String establishAfterTime4;

    //"变更后直属部部长确立时间"
    private String establishAfterTime5;

    //"创建时间"
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    //"操作人"
    private String createBy;

    //"生效标记（1：立即生效；2：次月生效）"
    private String effectTab;

    //"操作标记（0：新增；1：修改；2：批量：3：定时器置为失效）"
    private String operationTab;


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

    public String getDbBeforeSalesDate() {
        return dbBeforeSalesDate;
    }

    public void setDbBeforeSalesDate(String dbBeforeSalesDate) {
        this.dbBeforeSalesDate = dbBeforeSalesDate;
    }

    public String getTjBeforeSalesDate() {
        return tjBeforeSalesDate;
    }

    public void setTjBeforeSalesDate(String tjBeforeSalesDate) {
        this.tjBeforeSalesDate = tjBeforeSalesDate;
    }

    public String getSjBeforeSalesDate() {
        return sjBeforeSalesDate;
    }

    public void setSjBeforeSalesDate(String sjBeforeSalesDate) {
        this.sjBeforeSalesDate = sjBeforeSalesDate;
    }

    public String getJcBeforeSalesDate() {
        return jcBeforeSalesDate;
    }

    public void setJcBeforeSalesDate(String jcBeforeSalesDate) {
        this.jcBeforeSalesDate = jcBeforeSalesDate;
    }

    public String getFdBeforeSalesDate() {
        return fdBeforeSalesDate;
    }

    public void setFdBeforeSalesDate(String fdBeforeSalesDate) {
        this.fdBeforeSalesDate = fdBeforeSalesDate;
    }

    public String getEstablishBeforeTime() {
        return establishBeforeTime;
    }

    public void setEstablishBeforeTime(String establishBeforeTime) {
        this.establishBeforeTime = establishBeforeTime;
    }

    public String getEstablishBeforeTime1() {
        return establishBeforeTime1;
    }

    public void setEstablishBeforeTime1(String establishBeforeTime1) {
        this.establishBeforeTime1 = establishBeforeTime1;
    }

    public String getEstablishBeforeTime2() {
        return establishBeforeTime2;
    }

    public void setEstablishBeforeTime2(String establishBeforeTime2) {
        this.establishBeforeTime2 = establishBeforeTime2;
    }

    public String getEstablishBeforeTime3() {
        return establishBeforeTime3;
    }

    public void setEstablishBeforeTime3(String establishBeforeTime3) {
        this.establishBeforeTime3 = establishBeforeTime3;
    }

    public String getEstablishBeforeTime4() {
        return establishBeforeTime4;
    }

    public void setEstablishBeforeTime4(String establishBeforeTime4) {
        this.establishBeforeTime4 = establishBeforeTime4;
    }

    public String getEstablishBeforeTime5() {
        return establishBeforeTime5;
    }

    public void setEstablishBeforeTime5(String establishBeforeTime5) {
        this.establishBeforeTime5 = establishBeforeTime5;
    }

    public String getDbAfterSalesDate() {
        return dbAfterSalesDate;
    }

    public void setDbAfterSalesDate(String dbAfterSalesDate) {
        this.dbAfterSalesDate = dbAfterSalesDate;
    }

    public String getTjAfterSalesDate() {
        return tjAfterSalesDate;
    }

    public void setTjAfterSalesDate(String tjAfterSalesDate) {
        this.tjAfterSalesDate = tjAfterSalesDate;
    }

    public String getSjAfterSalesDate() {
        return sjAfterSalesDate;
    }

    public void setSjAfterSalesDate(String sjAfterSalesDate) {
        this.sjAfterSalesDate = sjAfterSalesDate;
    }

    public String getJcAfterSalesDate() {
        return jcAfterSalesDate;
    }

    public void setJcAfterSalesDate(String jcAfterSalesDate) {
        this.jcAfterSalesDate = jcAfterSalesDate;
    }

    public String getFdAfterSalesDate() {
        return fdAfterSalesDate;
    }

    public void setFdAfterSalesDate(String fdAfterSalesDate) {
        this.fdAfterSalesDate = fdAfterSalesDate;
    }

    public String getEstablishAfterTime() {
        return establishAfterTime;
    }

    public void setEstablishAfterTime(String establishAfterTime) {
        this.establishAfterTime = establishAfterTime;
    }

    public String getEstablishAfterTime1() {
        return establishAfterTime1;
    }

    public void setEstablishAfterTime1(String establishAfterTime1) {
        this.establishAfterTime1 = establishAfterTime1;
    }

    public String getEstablishAfterTime2() {
        return establishAfterTime2;
    }

    public void setEstablishAfterTime2(String establishAfterTime2) {
        this.establishAfterTime2 = establishAfterTime2;
    }

    public String getEstablishAfterTime3() {
        return establishAfterTime3;
    }

    public void setEstablishAfterTime3(String establishAfterTime3) {
        this.establishAfterTime3 = establishAfterTime3;
    }

    public String getEstablishAfterTime4() {
        return establishAfterTime4;
    }

    public void setEstablishAfterTime4(String establishAfterTime4) {
        this.establishAfterTime4 = establishAfterTime4;
    }

    public String getEstablishAfterTime5() {
        return establishAfterTime5;
    }

    public void setEstablishAfterTime5(String establishAfterTime5) {
        this.establishAfterTime5 = establishAfterTime5;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEffectTab() {
        return effectTab;
    }

    public void setEffectTab(String effectTab) {
        this.effectTab = effectTab;
    }

    public String getOperationTab() {
        return operationTab;
    }

    public void setOperationTab(String operationTab) {
        this.operationTab = operationTab;
    }
}