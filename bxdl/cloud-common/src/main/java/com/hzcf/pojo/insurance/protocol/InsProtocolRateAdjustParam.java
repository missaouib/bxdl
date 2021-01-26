package com.hzcf.pojo.insurance.protocol;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsProtocolRateAdjustParam {
	
	private Long adjustParamId;

    private Long adjustId;

    private Long protocolId;

    private Long pProductId;

    private Long sProductId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;
  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;

    private String updateBy;

    public Long getAdjustParamId() {
        return adjustParamId;
    }

    public void setAdjustParamId(Long adjustParamId) {
        this.adjustParamId = adjustParamId;
    }

    public Long getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Long adjustId) {
        this.adjustId = adjustId;
    }


    public Long getpProductId() {
        return pProductId;
    }

    public void setpProductId(Long pProductId) {
        this.pProductId = pProductId;
    }

    public Long getsProductId() {
        return sProductId;
    }

    public void setsProductId(Long sProductId) {
        this.sProductId = sProductId;
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

	public Long getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(Long protocolId) {
		this.protocolId = protocolId;
	}
    
}