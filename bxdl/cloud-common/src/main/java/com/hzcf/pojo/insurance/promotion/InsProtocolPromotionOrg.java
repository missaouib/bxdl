package com.hzcf.pojo.insurance.promotion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * <p>业务推动与机构关系</p>
 * @author kongqingbao
 */
public class InsProtocolPromotionOrg implements Serializable {

    private static final long serialVersionUID = -7373475091279431679L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 业务推动id
     */
    private Long promotionId;
    /**
     * 机构id
     */
    private Long salesOrgId;
    /**
     * 删除状态 0未删除 1已删除
     */
    private Byte deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Long getSalesOrgId() {
        return salesOrgId;
    }

    public void setSalesOrgId(Long salesOrgId) {
        this.salesOrgId = salesOrgId;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public InsProtocolPromotionOrg() {
    }

    public InsProtocolPromotionOrg(Long id, Long promotionId, Long salesOrgId, Byte deleted) {
        this.id = id;
        this.promotionId = promotionId;
        this.salesOrgId = salesOrgId;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        InsProtocolPromotionOrg that = (InsProtocolPromotionOrg) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(promotionId, that.promotionId)
                .append(salesOrgId, that.salesOrgId)
                .append(deleted, that.deleted)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(promotionId)
                .append(salesOrgId)
                .append(deleted)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InsProtocolPromotionOrg{" +
                "id=" + id +
                ", promotionId=" + promotionId +
                ", salesOrgId=" + salesOrgId +
                ", deleted=" + deleted +
                '}';
    }
}
