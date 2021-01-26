package com.hzcf.pojo.insurance.protocol;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsProtocolImage {
    private Long protocolImageId;

    private Long protocolId;

    private String imageName;

    private String fastUrl;

    private String imageStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;

    private String updateBy;

    public Long getProtocolImageId() {
        return protocolImageId;
    }

    public void setProtocolImageId(Long protocolImageId) {
        this.protocolImageId = protocolImageId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }

    public String getFastUrl() {
        return fastUrl;
    }

    public void setFastUrl(String fastUrl) {
        this.fastUrl = fastUrl == null ? null : fastUrl.trim();
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus == null ? null : imageStatus.trim();
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