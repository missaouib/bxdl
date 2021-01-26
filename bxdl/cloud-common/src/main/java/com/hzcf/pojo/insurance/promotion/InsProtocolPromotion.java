package com.hzcf.pojo.insurance.promotion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>寿险协议-业务推动</p>
 *
 * @author kongqingbao
 */
public class InsProtocolPromotion implements Serializable {

    private static final long serialVersionUID = 1256132443841066235L;

    /**
     * 业务推动id
     */
    private Long id;
    /**
     * 方案名称
     */
    private String promotionName;
    /**
     * 机构范围
     */
    private Byte orgScope;
    /**
     * 产品范围
     */
    private Byte productScope;
    /**
     * 协议id
     */
    private Long protocolId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 费率类型1 规保 2 标保
     */
    private Byte rateType;
    /**
     * 结算周期	0月
     */
    private Byte settlementCycle;
    /**
     * 通算方式 1季度 2半年 3年
     */
    private Byte commonCycle;
    /**
     * 结算周期费率
     */
    private String settlementCycleRate;
    /**
     * 通算周期费率
     */
    private String commonCycleRate;
    /**
     * 状态 0失效 1有效
     */
    private Byte state;
    /**
     * 删除 0未删除 1已删除
     */
    private Byte deleted;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 最后修改人
     */
    private String lastModifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getRateType() {
        return rateType;
    }

    public void setRateType(Byte rateType) {
        this.rateType = rateType;
    }

    public Byte getSettlementCycle() {
        return settlementCycle;
    }

    public void setSettlementCycle(Byte settlementCycle) {
        this.settlementCycle = settlementCycle;
    }

    public Byte getCommonCycle() {
        return commonCycle;
    }

    public void setCommonCycle(Byte commonCycle) {
        this.commonCycle = commonCycle;
    }

    public String getSettlementCycleRate() {
        return settlementCycleRate;
    }

    public void setSettlementCycleRate(String settlementCycleRate) {
        this.settlementCycleRate = settlementCycleRate;
    }

    public String getCommonCycleRate() {
        return commonCycleRate;
    }

    public void setCommonCycleRate(String commonCycleRate) {
        this.commonCycleRate = commonCycleRate;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Byte getOrgScope() {
        return orgScope;
    }

    public void setOrgScope(Byte orgScope) {
        this.orgScope = orgScope;
    }

    public Byte getProductScope() {
        return productScope;
    }

    public void setProductScope(Byte productScope) {
        this.productScope = productScope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        InsProtocolPromotion that = (InsProtocolPromotion) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(promotionName, that.promotionName)
                .append(orgScope, that.orgScope)
                .append(productScope, that.productScope)
                .append(protocolId, that.protocolId)
                .append(startTime, that.startTime)
                .append(endTime, that.endTime)
                .append(rateType, that.rateType)
                .append(settlementCycle, that.settlementCycle)
                .append(commonCycle, that.commonCycle)
                .append(settlementCycleRate, that.settlementCycleRate)
                .append(commonCycleRate, that.commonCycleRate)
                .append(state, that.state)
                .append(deleted, that.deleted)
                .append(createdTime, that.createdTime)
                .append(updatedTime, that.updatedTime)
                .append(lastModifier, that.lastModifier)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(promotionName)
                .append(orgScope)
                .append(productScope)
                .append(protocolId)
                .append(startTime)
                .append(endTime)
                .append(rateType)
                .append(settlementCycle)
                .append(commonCycle)
                .append(settlementCycleRate)
                .append(commonCycleRate)
                .append(state)
                .append(deleted)
                .append(createdTime)
                .append(updatedTime)
                .append(lastModifier)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InsProtocolPromotion{" +
                "id=" + id +
                ", promotionName='" + promotionName + '\'' +
                ", orgScope=" + orgScope +
                ", productScope=" + productScope +
                ", protocolId=" + protocolId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rateType=" + rateType +
                ", settlementCycle=" + settlementCycle +
                ", commonCycle=" + commonCycle +
                ", settlementCycleRate='" + settlementCycleRate + '\'' +
                ", commonCycleRate='" + commonCycleRate + '\'' +
                ", state=" + state +
                ", deleted=" + deleted +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", lastModifier='" + lastModifier + '\'' +
                '}';
    }
}
