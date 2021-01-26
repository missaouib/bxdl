package com.hzcf.pojo.insurance;

import java.io.Serializable;
import java.util.Date;

/**
*  SalesTeamLeaders
* @author yaolong 2019-06-18
*/
public class SalesTeamLeaders implements Serializable {
   
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long teamLeaderId;

    /**
    * 所属团队
    */
    private Long salesTeamId;

    /**
    * 主管工号
    */
    private String salerNo;

    /**
    * 主管姓名
    */
    private String salerName;

    /**
    * 证件类型
    */
    private String cardType;

    /**
    * 证件号码
    */
    private String cardNo;

    /**
    * 主管类型
    */
    private String leaderType;

    /**
    * 生效日期
    */
    private String effectDate;

    /**
    * 失效日期
    */
    private String stopDate;

    /**
    * 状态 0正常1失效2终止
    */
    private String leaderStatus;

    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
    private Date createdTime;

    /**
    * 更新人
    */
    private String updatedBy;

    /**
    * 更新时间
    */
    private Date updatedTime;


    public SalesTeamLeaders() {
    }

    public Long getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(Long teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    public Long getSalesTeamId() {
        return salesTeamId;
    }

    public void setSalesTeamId(Long salesTeamId) {
        this.salesTeamId = salesTeamId;
    }

    public String getSalerNo() {
        return salerNo;
    }

    public void setSalerNo(String salerNo) {
        this.salerNo = salerNo;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getLeaderType() {
        return leaderType;
    }

    public void setLeaderType(String leaderType) {
        this.leaderType = leaderType;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getLeaderStatus() {
        return leaderStatus;
    }

    public void setLeaderStatus(String leaderStatus) {
        this.leaderStatus = leaderStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

	@Override
	public String toString() {
		return "SalesTeamLeaders [teamLeaderId=" + teamLeaderId + ", salesTeamId=" + salesTeamId + ", salerNo="
				+ salerNo + ", salerName=" + salerName + ", cardType=" + cardType + ", cardNo=" + cardNo
				+ ", leaderType=" + leaderType + ", effectDate=" + effectDate + ", stopDate=" + stopDate
				+ ", leaderStatus=" + leaderStatus + ", createdBy=" + createdBy + ", createdTime=" + createdTime
				+ ", updatedBy=" + updatedBy + ", updatedTime=" + updatedTime + "]";
	}

	public SalesTeamLeaders(Long teamLeaderId, Long salesTeamId, String salerNo, String salerName, String cardType,
			String cardNo, String leaderType, String effectDate, String stopDate, String leaderStatus, String createdBy,
			Date createdTime, String updatedBy, Date updatedTime) {
		super();
		this.teamLeaderId = teamLeaderId;
		this.salesTeamId = salesTeamId;
		this.salerNo = salerNo;
		this.salerName = salerName;
		this.cardType = cardType;
		this.cardNo = cardNo;
		this.leaderType = leaderType;
		this.effectDate = effectDate;
		this.stopDate = stopDate;
		this.leaderStatus = leaderStatus;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}
	
}
