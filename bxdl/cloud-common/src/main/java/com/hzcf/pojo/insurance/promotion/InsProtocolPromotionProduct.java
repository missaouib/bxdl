package com.hzcf.pojo.insurance.promotion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * <p>业务推动与机构关系</p>
 * @author kongqingbao
 */
public class InsProtocolPromotionProduct implements Serializable {

    private static final long serialVersionUID = 136988420807347570L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 业务推动id
     */
    private Long promotionId;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 产品code
     */
    private String productCode;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

        InsProtocolPromotionProduct that = (InsProtocolPromotionProduct) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(promotionId, that.promotionId)
                .append(productId, that.productId)
                .append(productCode, that.productCode)
                .append(deleted, that.deleted)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(promotionId)
                .append(productId)
                .append(productCode)
                .append(deleted)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InsProtocolPromotionProduct{" +
                "id=" + id +
                ", promotionId=" + promotionId +
                ", productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
