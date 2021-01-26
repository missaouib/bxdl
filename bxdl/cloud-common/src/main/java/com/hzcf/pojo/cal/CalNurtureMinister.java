package com.hzcf.pojo.cal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 部长育成奖金参数表
 */
public class CalNurtureMinister implements Serializable {
	private static final long serialVersionUID = 8683791534673879611L;
	/** 主键 */
	private Long id;
	/** 机构id */
	private Integer orgId;
	/** 育成代数(0:直接育成；2：二代育成) */
	private String generativeAlgebra;
	/** 第一年 */
	private BigDecimal fastYear;
	/** 第二年及以后 */
	private BigDecimal followingYearAndBeyond;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createdTime;
	/** 更新人 */
	private String updatedBy;
	/** 更新时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date updatedTime;
	/** 0正常，1删除 */
	private String isNormal;

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Integer getOrgId() {
		return this.orgId;
	}
	
	public void setGenerativeAlgebra(String generativeAlgebra) {
		this.generativeAlgebra = generativeAlgebra;
	}
	
	public String getGenerativeAlgebra() {
		return this.generativeAlgebra;
	}
	
	public void setFastYear(BigDecimal fastYear) {
		this.fastYear = fastYear;
	}
	
	public BigDecimal getFastYear() {
		return this.fastYear;
	}
	
	public void setFollowingYearAndBeyond(BigDecimal followingYearAndBeyond) {
		this.followingYearAndBeyond = followingYearAndBeyond;
	}
	
	public BigDecimal getFollowingYearAndBeyond() {
		return this.followingYearAndBeyond;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public Date getCreatedTime() {
		return this.createdTime;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getUpdatedBy() {
		return this.updatedBy;
	}
	
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public Date getUpdatedTime() {
		return this.updatedTime;
	}
	
	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}
	
	public String getIsNormal() {
		return this.isNormal;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        CalNurtureMinister that = (CalNurtureMinister) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "CalNurtureMinister{" +
				"id=" + id +
						",orgId='" + orgId + "'" + 
						",generativeAlgebra='" + generativeAlgebra + "'" + 
						",fastYear='" + fastYear + "'" + 
						",followingYearAndBeyond='" + followingYearAndBeyond + "'" + 
						",createdBy='" + createdBy + "'" + 
						",createdTime='" + createdTime + "'" + 
						",updatedBy='" + updatedBy + "'" + 
						",updatedTime='" + updatedTime + "'" + 
						",isNormal='" + isNormal + "'" + 
		                '}';
    }
	
}