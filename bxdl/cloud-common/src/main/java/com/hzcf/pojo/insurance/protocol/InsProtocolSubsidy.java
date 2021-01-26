package com.hzcf.pojo.insurance.protocol;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsProtocolSubsidy {
    private Long subsidyId;

    private Long protocolId;

    private Long productId;

    private String rateType;

    private String settlementInterval;

    private String isException;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date updateTime;

    private String updateBy;

    private String subsidyJson;

    public Long getSubsidyId() {
        return subsidyId;
    }

    public void setSubsidyId(Long subsidyId) {
        this.subsidyId = subsidyId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    public String getSettlementInterval() {
        return settlementInterval;
    }

    public void setSettlementInterval(String settlementInterval) {
        this.settlementInterval = settlementInterval == null ? null : settlementInterval.trim();
    }

    public String getIsException() {
        return isException;
    }

    public void setIsException(String isException) {
        this.isException = isException == null ? null : isException.trim();
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getSubsidyJson() {
        return subsidyJson;
    }

    public void setSubsidyJson(String subsidyJson) {
        this.subsidyJson = subsidyJson == null ? null : subsidyJson.trim();
    }
}