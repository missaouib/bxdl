package com.hzcf.pojo.insurance.promotion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * <p>寿险协议业务推动列表展示pojo</p>
 *
 * @author kongqingbao
 */
public class InsProtocolPromotionListPojo implements Serializable {


    private static final long serialVersionUID = -3110216539731390306L;
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
    private String orgScope;
    /**
     * 产品范围
     */
    private String productScope;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 状态
     */
    private String state;

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

    public String getOrgScope() {
        return orgScope;
    }

    public void setOrgScope(String orgScope) {
        this.orgScope = orgScope;
    }

    public String getProductScope() {
        return productScope;
    }

    public void setProductScope(String productScope) {
        this.productScope = productScope;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        InsProtocolPromotionListPojo that = (InsProtocolPromotionListPojo) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(promotionName, that.promotionName)
                .append(orgScope, that.orgScope)
                .append(productScope, that.productScope)
                .append(startTime, that.startTime)
                .append(endTime, that.endTime)
                .append(state, that.state)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(promotionName)
                .append(orgScope)
                .append(productScope)
                .append(startTime)
                .append(endTime)
                .append(state)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InsProtocolPromotionListPojo{" +
                "id=" + id +
                ", promotionName='" + promotionName + '\'' +
                ", orgScope='" + orgScope + '\'' +
                ", productScope='" + productScope + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
