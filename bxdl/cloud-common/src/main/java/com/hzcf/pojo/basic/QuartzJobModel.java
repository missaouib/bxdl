package com.hzcf.pojo.basic;

import java.util.Date;

/**
 * Created by qin lina on 2020/4/20.
 */
public class QuartzJobModel {
    public static final String STATE_START = "0";//启用
    public static final String STATE_PAUSE = "1";//暂停
    public static final String STATE_DELET = "2";//删除

    //任务主键
    private Integer jobId;
     // 任务名称
    private String jobName;

    // 任务类名称
    private String jobClassName;

    // cron表达式
    private String cron;

    // 状态
    private String status;

    //操作人
    private String operator;

    //接口描述
    private String jobDescribe;

    private Date createTime;

    private Date updateTime;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
