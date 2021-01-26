package com.hzcf.pojo.insurance.protocol;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsInsideStandardRateConfig {
    private Long insideStandardId;

    private Long protocolId;

    private Long productId;

    private String productCode;

    private BigDecimal firstStandardRate;

    private BigDecimal nextStandardRate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;

    private String updateBy;

    public Long getInsideStandardId() {
        return insideStandardId;
    }

    public void setInsideStandardId(Long insideStandardId) {
        this.insideStandardId = insideStandardId;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public BigDecimal getFirstStandardRate() {
        return firstStandardRate;
    }

    public void setFirstStandardRate(BigDecimal firstStandardRate) {
        this.firstStandardRate = firstStandardRate;
    }

    public BigDecimal getNextStandardRate() {
        return nextStandardRate;
    }

    public void setNextStandardRate(BigDecimal nextStandardRate) {
        this.nextStandardRate = nextStandardRate;
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
}