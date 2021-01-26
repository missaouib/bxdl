package com.hzcf.pojo.insurance.promotion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>寿险协议-业务推动pojo</p>
 *
 * @author kongqingbao
 */
public class InsProtocolPromotionPojo implements Serializable {

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
     * 机构id
     */
    private List<InsProtocolPromotionOrg> orgScope;
    /**
     * 机构范围字符串
     */
    private String orgScopeStr;
    /**
     * 产品范围
     * 产品id
     */
    private List<InsProtocolPromotionProduct> productScope;
    /**
     * 产品范围字符串
     */
    private String productScopeStr;
    /**
     * 协议id
     */
    private Long protocolId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 开始时间字符串
     */
    private String startTimeStr;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 结束时间字符串
     */
    private String endTimeStr;
    /**
     * 费率类型0 规保 1 标保
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
    private List<InsProtocolPromotionRate> settlementRate;
    /**
     * 通算周期费率
     */
    private List<InsProtocolPromotionRate> commonRate;
    /**
     * 最后修改人
     */
    private String lastModifier;

    /**
     * 状态 0失效 1有效
     */
    private Byte state;
    /**
     * 删除 0未删除 1已删除
     */
    private Byte deleted;


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

    public List<InsProtocolPromotionOrg> getOrgScope() {
        return orgScope;
    }

    public void setOrgScope(List<InsProtocolPromotionOrg> orgScope) {
        this.orgScope = orgScope;
    }

    public String getOrgScopeStr() {
        return orgScopeStr;
    }

    public void setOrgScopeStr(String orgScopeStr) {
        this.orgScopeStr = orgScopeStr;
    }

    public List<InsProtocolPromotionProduct> getProductScope() {
        return productScope;
    }

    public void setProductScope(List<InsProtocolPromotionProduct> productScope) {
        this.productScope = productScope;
    }

    public String getProductScopeStr() {
        return productScopeStr;
    }

    public void setProductScopeStr(String productScopeStr) {
        this.productScopeStr = productScopeStr;
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

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
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

    public List<InsProtocolPromotionRate> getSettlementRate() {
        return settlementRate;
    }

    public void setSettlementRate(List<InsProtocolPromotionRate> settlementRate) {
        this.settlementRate = settlementRate;
    }

    public List<InsProtocolPromotionRate> getCommonRate() {
        return commonRate;
    }

    public void setCommonRate(List<InsProtocolPromotionRate> commonRate) {
        this.commonRate = commonRate;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        InsProtocolPromotionPojo that = (InsProtocolPromotionPojo) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(promotionName, that.promotionName)
                .append(orgScope, that.orgScope)
                .append(orgScopeStr, that.orgScopeStr)
                .append(productScope, that.productScope)
                .append(productScopeStr, that.productScopeStr)
                .append(protocolId, that.protocolId)
                .append(startTime, that.startTime)
                .append(startTimeStr, that.startTimeStr)
                .append(endTime, that.endTime)
                .append(endTimeStr, that.endTimeStr)
                .append(rateType, that.rateType)
                .append(settlementCycle, that.settlementCycle)
                .append(commonCycle, that.commonCycle)
                .append(settlementRate, that.settlementRate)
                .append(commonRate, that.commonRate)
                .append(lastModifier, that.lastModifier)
                .append(state, that.state)
                .append(deleted, that.deleted)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(promotionName)
                .append(orgScope)
                .append(orgScopeStr)
                .append(productScope)
                .append(productScopeStr)
                .append(protocolId)
                .append(startTime)
                .append(startTimeStr)
                .append(endTime)
                .append(endTimeStr)
                .append(rateType)
                .append(settlementCycle)
                .append(commonCycle)
                .append(settlementRate)
                .append(commonRate)
                .append(lastModifier)
                .append(state)
                .append(deleted)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InsProtocolPromotionPojo{" +
                "id=" + id +
                ", promotionName='" + promotionName + '\'' +
                ", orgScope=" + orgScope +
                ", orgScopeStr='" + orgScopeStr + '\'' +
                ", productScope=" + productScope +
                ", productScopeStr='" + productScopeStr + '\'' +
                ", protocolId=" + protocolId +
                ", startTime=" + startTime +
                ", startTimeStr='" + startTimeStr + '\'' +
                ", endTime=" + endTime +
                ", endTimeStr='" + endTimeStr + '\'' +
                ", rateType=" + rateType +
                ", settlementCycle=" + settlementCycle +
                ", commonCycle=" + commonCycle +
                ", settlementRate=" + settlementRate +
                ", commonRate=" + commonRate +
                ", lastModifier='" + lastModifier + '\'' +
                ", state=" + state +
                ", deleted=" + deleted +
                '}';
    }
}
