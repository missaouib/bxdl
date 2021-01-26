package com.hzcf.pojo.insurance.protocol;

import java.math.BigDecimal;
import java.util.Date;

public class InsProtocolCost {
    private Long protocolCostId;
 
    private Long orgId;

    private Long productId;

    private Long protocolId;

    private String costType;

    private BigDecimal costTotal;

    private Date calculationEndDate;

    private Date createTime;

    private Date updateTime;

    private String updateBy;

    public Long getProtocolCostId() {
        return protocolCostId;
    }

    public void setProtocolCostId(Long protocolCostId) {
        this.protocolCostId = protocolCostId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType == null ? null : costType.trim();
    }

    public BigDecimal getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(BigDecimal costTotal) {
        this.costTotal = costTotal;
    }

    public Date getCalculationEndDate() {
        return calculationEndDate;
    }

    public void setCalculationEndDate(Date calculationEndDate) {
        this.calculationEndDate = calculationEndDate;
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