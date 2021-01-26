package com.hzcf.pojo.insurance.protocol;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsProtocolProduct implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8238288062643504747L;

	private Long protocolProductId;

    private Long protocolId;

    private Long productId;

    private String productCode;

    private String relationStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;

    private String updateBy;
    /**
     * 犹豫期
     */
    private Integer hesitationPeriod;

    public Long getProtocolProductId() {
        return protocolProductId;
    }

    public void setProtocolProductId(Long protocolProductId) {
        this.protocolProductId = protocolProductId;
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

    public String getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(String relationStatus) {
        this.relationStatus = relationStatus == null ? null : relationStatus.trim();
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

    public Integer getHesitationPeriod() {
        return hesitationPeriod;
    }

    public void setHesitationPeriod(Integer hesitationPeriod) {
        this.hesitationPeriod = hesitationPeriod;
    }
}