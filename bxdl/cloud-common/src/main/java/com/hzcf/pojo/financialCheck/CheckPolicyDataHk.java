package com.hzcf.pojo.financialCheck;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CheckPolicyDataHk implements Serializable {
	private Long id;
	/** 对账月份 */
	private String checkMonth;
	/** 机构名称 */
	private String salesOrgName;
	/** 销售组织id  为了权限控制方便 */
	private String salesOrgId;
	/** 保险公司名称 */
	private String companyOrgName;
	/** 保险公司id */
	private String companyOrgId;
	/** 投保单号 */
	private String policyId;
	/** 产品名称（父产品名称） */
	private String productName;
	/** 父产品id */
	private String productId;
	/** 险种类别 */
	private String insuranceType;
	/** 投保日期 */
	private String propostDate;
	/** 承保日期 */
	private String underwritingDate;
	/** 生效时间 */
	private String takeEffectDate;
	/** 规模保费 */
	private BigDecimal premium;
	/** 保险期间 */
	private String insurancePeriod;
	/** 缴费方式 */
	private String paymentMethod;
	/** 缴费期间 */
	private String paymentPeriod;
	/** 首期/续期 如果首期则填写1，续期几年填写几 续期从第二年开始 */
	private String paymentNum;
	/** 手续费 */
	private BigDecimal processCost;
	/** 推广费 */
	private BigDecimal pustCost;
	/** 合计 */
	private BigDecimal totalCost;
	/** 备注 */
	private String remark;
	/** 核对状态 0未核对 1已核对 2延迟核对 */
	private String checkStatus;
	/** 核对备注 */
	private String checkRemark;
	/** 结算状态 0未结算  1已结算 */
	private String settleStatus;
	/** 结算备注 */
	private String settleRemark;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String createBy;
	private Date updateTime;
	private String updateBy;
	/** 核对批次号 */
	private String batchNum;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setCheckMonth(String checkMonth) {
		this.checkMonth = checkMonth;
	}

	public String getCheckMonth() {
		return this.checkMonth;
	}

	public void setSalesOrgName(String salesOrgName) {
		this.salesOrgName = salesOrgName;
	}

	public String getSalesOrgName() {
		return this.salesOrgName;
	}

	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public String getSalesOrgId() {
		return this.salesOrgId;
	}

	public void setCompanyOrgName(String companyOrgName) {
		this.companyOrgName = companyOrgName;
	}

	public String getCompanyOrgName() {
		return this.companyOrgName;
	}

	public void setCompanyOrgId(String companyOrgId) {
		this.companyOrgId = companyOrgId;
	}

	public String getCompanyOrgId() {
		return this.companyOrgId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyId() {
		return this.policyId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceType() {
		return this.insuranceType;
	}

	public void setPropostDate(String propostDate) {
		this.propostDate = propostDate;
	}

	public String getPropostDate() {
		return this.propostDate;
	}

	public void setUnderwritingDate(String underwritingDate) {
		this.underwritingDate = underwritingDate;
	}

	public String getUnderwritingDate() {
		return this.underwritingDate;
	}

	public void setTakeEffectDate(String takeEffectDate) {
		this.takeEffectDate = takeEffectDate;
	}

	public String getTakeEffectDate() {
		return this.takeEffectDate;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public BigDecimal getPremium() {
		return this.premium;
	}

	public void setInsurancePeriod(String insurancePeriod) {
		this.insurancePeriod = insurancePeriod;
	}

	public String getInsurancePeriod() {
		return this.insurancePeriod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public String getPaymentPeriod() {
		return this.paymentPeriod;
	}

	public void setPaymentNum(String paymentNum) {
		this.paymentNum = paymentNum;
	}

	public String getPaymentNum() {
		return this.paymentNum;
	}

	public void setProcessCost(BigDecimal processCost) {
		this.processCost = processCost;
	}

	public BigDecimal getProcessCost() {
		return this.processCost;
	}

	public void setPustCost(BigDecimal pustCost) {
		this.pustCost = pustCost;
	}

	public BigDecimal getPustCost() {
		return this.pustCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getTotalCost() {
		return this.totalCost;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	public String getCheckRemark() {
		return this.checkRemark;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getSettleStatus() {
		return this.settleStatus;
	}

	public void setSettleRemark(String settleRemark) {
		this.settleRemark = settleRemark;
	}

	public String getSettleRemark() {
		return this.settleRemark;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getBatchNum() {
		return this.batchNum;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        CheckPolicyDataHk that = (CheckPolicyDataHk) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CheckPolicyDataHk{" +
				"id=" + id +
						",checkMonth='" + checkMonth + "'" +
						",salesOrgName='" + salesOrgName + "'" +
						",salesOrgId='" + salesOrgId + "'" +
						",companyOrgName='" + companyOrgName + "'" +
						",companyOrgId='" + companyOrgId + "'" +
						",policyId='" + policyId + "'" +
						",productName='" + productName + "'" +
						",productId='" + productId + "'" +
						",insuranceType='" + insuranceType + "'" +
						",propostDate='" + propostDate + "'" +
						",underwritingDate='" + underwritingDate + "'" +
						",takeEffectDate='" + takeEffectDate + "'" +
						",premium='" + premium + "'" +
						",insurancePeriod='" + insurancePeriod + "'" +
						",paymentMethod='" + paymentMethod + "'" +
						",paymentPeriod='" + paymentPeriod + "'" +
						",paymentNum='" + paymentNum + "'" +
						",processCost='" + processCost + "'" +
						",pustCost='" + pustCost + "'" +
						",totalCost='" + totalCost + "'" +
						",remark='" + remark + "'" +
						",checkStatus='" + checkStatus + "'" +
						",checkRemark='" + checkRemark + "'" +
						",settleStatus='" + settleStatus + "'" +
						",settleRemark='" + settleRemark + "'" +
						",createTime='" + createTime + "'" +
						",createBy='" + createBy + "'" +
						",updateTime='" + updateTime + "'" +
						",updateBy='" + updateBy + "'" +
						",batchNum='" + batchNum + "'" +
		                '}';
    }
}