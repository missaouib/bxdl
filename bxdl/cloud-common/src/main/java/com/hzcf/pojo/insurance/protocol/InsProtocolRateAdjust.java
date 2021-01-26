package com.hzcf.pojo.insurance.protocol;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsProtocolRateAdjust {
    private Long adjustId;

    private Long protocolId;
    
    private String adjustName;

    private String rateType;

    private String subjoinType;

    private String settlementInterval;

    private String productType;

    private String rateAdjustType;

    private String adjustType;
   
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;

    private String updateBy;

    public Long getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Long adjustId) {
        this.adjustId = adjustId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    public String getSubjoinType() {
        return subjoinType;
    }

    public void setSubjoinType(String subjoinType) {
        this.subjoinType = subjoinType == null ? null : subjoinType.trim();
    }

    public String getSettlementInterval() {
        return settlementInterval;
    }

    public void setSettlementInterval(String settlementInterval) {
        this.settlementInterval = settlementInterval == null ? null : settlementInterval.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getRateAdjustType() {
        return rateAdjustType;
    }

    public void setRateAdjustType(String rateAdjustType) {
        this.rateAdjustType = rateAdjustType == null ? null : rateAdjustType.trim();
    }

    public String getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType == null ? null : adjustType.trim();
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

	public String getAdjustName() {
		return adjustName;
	}

	public void setAdjustName(String adjustName) {
		this.adjustName = adjustName;
	}
    
}