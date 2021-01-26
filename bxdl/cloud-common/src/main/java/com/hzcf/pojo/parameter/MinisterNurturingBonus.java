package com.hzcf.pojo.parameter;

import java.io.Serializable;
import java.util.Date;

public class MinisterNurturingBonus implements Serializable{
    /**
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String generativeAlgebra;

    private String fastYear;

    private String followingYearAndBeyond;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;
    
    private String isNormal;

    public String getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenerativeAlgebra() {
        return generativeAlgebra;
    }

    public void setGenerativeAlgebra(String generativeAlgebra) {
        this.generativeAlgebra = generativeAlgebra == null ? null : generativeAlgebra.trim();
    }

    public String getFastYear() {
        return fastYear;
    }

    public void setFastYear(String fastYear) {
        this.fastYear = fastYear == null ? null : fastYear.trim();
    }

    public String getFollowingYearAndBeyond() {
        return followingYearAndBeyond;
    }

    public void setFollowingYearAndBeyond(String followingYearAndBeyond) {
        this.followingYearAndBeyond = followingYearAndBeyond == null ? null : followingYearAndBeyond.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}