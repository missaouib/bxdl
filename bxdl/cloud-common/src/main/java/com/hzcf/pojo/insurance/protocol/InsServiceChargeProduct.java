package com.hzcf.pojo.insurance.protocol;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsServiceChargeProduct {
	
    private Long serviceProductId;


    private Long protocolId;

    private Long productId;

    private String productCode;

    private BigDecimal firstYearCost;

    private BigDecimal secondYearCost;

    private BigDecimal thirdYearCost;

    private BigDecimal fourYearCost;

    private BigDecimal fiveYearCost;

    private BigDecimal sixYearCost;
    
    private String rateType;

    private String settlementInterval;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;

    private String updateBy;

    public Long getServiceProductId() {
        return serviceProductId;
    }

    public void setServiceProductId(Long serviceProductId) {
        this.serviceProductId = serviceProductId;
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

    public BigDecimal getFirstYearCost() {
        return firstYearCost;
    }

    public void setFirstYearCost(BigDecimal firstYearCost) {
        this.firstYearCost = firstYearCost;
    }

    public BigDecimal getSecondYearCost() {
        return secondYearCost;
    }

    public void setSecondYearCost(BigDecimal secondYearCost) {
        this.secondYearCost = secondYearCost;
    }

   

    public BigDecimal getThirdYearCost() {
		return thirdYearCost;
	}

	public void setThirdYearCost(BigDecimal thirdYearCost) {
		this.thirdYearCost = thirdYearCost;
	}

	public BigDecimal getFourYearCost() {
        return fourYearCost;
    }

    public void setFourYearCost(BigDecimal fourYearCost) {
        this.fourYearCost = fourYearCost;
    }

    public BigDecimal getFiveYearCost() {
        return fiveYearCost;
    }

    public void setFiveYearCost(BigDecimal fiveYearCost) {
        this.fiveYearCost = fiveYearCost;
    }

    public BigDecimal getSixYearCost() {
        return sixYearCost;
    }

    public void setSixYearCost(BigDecimal sixYearCost) {
        this.sixYearCost = sixYearCost;
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

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getSettlementInterval() {
		return settlementInterval;
	}

	public void setSettlementInterval(String settlementInterval) {
		this.settlementInterval = settlementInterval;
	}
    
    
}