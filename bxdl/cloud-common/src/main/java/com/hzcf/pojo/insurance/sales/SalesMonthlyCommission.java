package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalesMonthlyCommission  implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long commissionId;

    /**
    * 员工主键
    */
    private Long salerId;

    /**
    * 组织机构
    */
    private Long salesOrgId;

    /**
    * 营销团队
    */
    private Long salesTeamId;

    /**
    * 员工职级
    */
    private Long salerGrade;

    /**
    * 佣金月
    */
    private String commissionMonth;

    /**
    * 佣金收入
    */
    private BigDecimal totalCommission;

    /**
    * 佣金加扣
    */
    private BigDecimal cutCommission;

    /**
    * 佣金加扣原因
    */
    private String cutReason;

    /**
    * 冻结税额
    */
    private BigDecimal freezingTaxes;

    /**
    * 税后佣金
    */
    private BigDecimal afterTaxCommission;

    /**
    * 状态（审核、发放）0 待审核 -1 审核不通过 1审核通过 待发放 2已发放
    */
    private String commissionStatus;

    /**
    * 审核备注（原因）
    */
    private String checkMark;

    /**
    * 审核时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date checkTime;

    /**
    * 发放时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date pushTime;

    /**
    * 初年度佣金
    */
    private BigDecimal initialAnnualCommission;

    /**
    * 展业津贴
    */
    private BigDecimal exhibitionAllowance;

    /**
    * 增员津贴
    */
    private BigDecimal increaseAllowance;

    /**
    * 续年度佣金
    */
    private BigDecimal continuousAllowance;

    /**
    * 个人继续率奖金
    */
    private BigDecimal continuationRateBonus;

    /**
    * 个人年终奖
    */
    private BigDecimal annualBonus;

    /**
    * 长期客服奖
    */
    private BigDecimal serviceAward;

    /**
    * 直辖组管理津贴
    */
    private BigDecimal groupRunAllowance;

    /**
    * 处长育成奖
    */
    private BigDecimal directorCultureAward;

    /**
    * 直辖部管理津贴
    */
    private BigDecimal depRunAllowance;

    /**
    * 部育成奖
    */
    private BigDecimal ministerCultureAward;

    /**
    * 总监津贴
    */
    private BigDecimal chiefInspectorAllowance;

    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createdTime;

    /**
    * 更新人
    */
    private String updatedBy;

    /**
    * 更新时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updatedTime;

    /**
	 * 备注
	 */
    private String remark;

	public Long getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(Long commissionId) {
		this.commissionId = commissionId;
	}

	public Long getSalerId() {
		return salerId;
	}

	public void setSalerId(Long salerId) {
		this.salerId = salerId;
	}

	public Long getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(Long salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public Long getSalesTeamId() {
		return salesTeamId;
	}

	public void setSalesTeamId(Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	public Long getSalerGrade() {
		return salerGrade;
	}

	public void setSalerGrade(Long salerGrade) {
		this.salerGrade = salerGrade;
	}

	public String getCommissionMonth() {
		return commissionMonth;
	}

	public void setCommissionMonth(String commissionMonth) {
		this.commissionMonth = commissionMonth;
	}

	public BigDecimal getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(BigDecimal totalCommission) {
		this.totalCommission = totalCommission;
	}

	public BigDecimal getCutCommission() {
		return cutCommission;
	}

	public void setCutCommission(BigDecimal cutCommission) {
		this.cutCommission = cutCommission;
	}

	public String getCutReason() {
		return cutReason;
	}

	public void setCutReason(String cutReason) {
		this.cutReason = cutReason;
	}

	public BigDecimal getFreezingTaxes() {
		return freezingTaxes;
	}

	public void setFreezingTaxes(BigDecimal freezingTaxes) {
		this.freezingTaxes = freezingTaxes;
	}

	public BigDecimal getAfterTaxCommission() {
		return afterTaxCommission;
	}

	public void setAfterTaxCommission(BigDecimal afterTaxCommission) {
		this.afterTaxCommission = afterTaxCommission;
	}

	public String getCommissionStatus() {
		return commissionStatus;
	}

	public void setCommissionStatus(String commissionStatus) {
		this.commissionStatus = commissionStatus;
	}

	public String getCheckMark() {
		return checkMark;
	}

	public void setCheckMark(String checkMark) {
		this.checkMark = checkMark;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public BigDecimal getInitialAnnualCommission() {
		return initialAnnualCommission;
	}

	public void setInitialAnnualCommission(BigDecimal initialAnnualCommission) {
		this.initialAnnualCommission = initialAnnualCommission;
	}

	public BigDecimal getExhibitionAllowance() {
		return exhibitionAllowance;
	}

	public void setExhibitionAllowance(BigDecimal exhibitionAllowance) {
		this.exhibitionAllowance = exhibitionAllowance;
	}

	public BigDecimal getIncreaseAllowance() {
		return increaseAllowance;
	}

	public void setIncreaseAllowance(BigDecimal increaseAllowance) {
		this.increaseAllowance = increaseAllowance;
	}

	public BigDecimal getContinuousAllowance() {
		return continuousAllowance;
	}

	public void setContinuousAllowance(BigDecimal continuousAllowance) {
		this.continuousAllowance = continuousAllowance;
	}

	public BigDecimal getContinuationRateBonus() {
		return continuationRateBonus;
	}

	public void setContinuationRateBonus(BigDecimal continuationRateBonus) {
		this.continuationRateBonus = continuationRateBonus;
	}

	public BigDecimal getAnnualBonus() {
		return annualBonus;
	}

	public void setAnnualBonus(BigDecimal annualBonus) {
		this.annualBonus = annualBonus;
	}

	public BigDecimal getServiceAward() {
		return serviceAward;
	}

	public void setServiceAward(BigDecimal serviceAward) {
		this.serviceAward = serviceAward;
	}

	public BigDecimal getGroupRunAllowance() {
		return groupRunAllowance;
	}

	public void setGroupRunAllowance(BigDecimal groupRunAllowance) {
		this.groupRunAllowance = groupRunAllowance;
	}

	public BigDecimal getDirectorCultureAward() {
		return directorCultureAward;
	}

	public void setDirectorCultureAward(BigDecimal directorCultureAward) {
		this.directorCultureAward = directorCultureAward;
	}

	public BigDecimal getDepRunAllowance() {
		return depRunAllowance;
	}

	public void setDepRunAllowance(BigDecimal depRunAllowance) {
		this.depRunAllowance = depRunAllowance;
	}

	public BigDecimal getMinisterCultureAward() {
		return ministerCultureAward;
	}

	public void setMinisterCultureAward(BigDecimal ministerCultureAward) {
		this.ministerCultureAward = ministerCultureAward;
	}

	public BigDecimal getChiefInspectorAllowance() {
		return chiefInspectorAllowance;
	}

	public void setChiefInspectorAllowance(BigDecimal chiefInspectorAllowance) {
		this.chiefInspectorAllowance = chiefInspectorAllowance;
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

	public String getRemark() {
		return remark;
	}

	public void setRemakr(String remark) {
		this.remark = remark;
	}
}
