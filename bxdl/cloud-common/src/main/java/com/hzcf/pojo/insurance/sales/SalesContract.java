package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

public class SalesContract implements Serializable{
	private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long contractId;

    /**
    * 员工主键
    */
    private Long salesId;

    /**
    * 合同编号
    */
    private String contractNo;

    /**
    * 合同类型
    */
    private String contractType;

    /**
    * 商业协议
    */
    private String businessAgreementFlag;

    /**
    * 保密协议
    */
    private String secretAgreementFlag;

    /**
    * 签订日期
    */
    private String writeDate;

    /**
    * 试用期结束日期
    */
    private String probationEnd;

    /**
    * 合同生效日期
    */
    private String contractEffectDate;

    /**
    * 合同终止日期
    */
    private String contractStopDate;

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
    
    private String deleteFlag;

    public SalesContract() {
    }

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getBusinessAgreementFlag() {
		return businessAgreementFlag;
	}

	public void setBusinessAgreementFlag(String businessAgreementFlag) {
		this.businessAgreementFlag = businessAgreementFlag;
	}

	public String getSecretAgreementFlag() {
		return secretAgreementFlag;
	}

	public void setSecretAgreementFlag(String secretAgreementFlag) {
		this.secretAgreementFlag = secretAgreementFlag;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getProbationEnd() {
		return probationEnd;
	}

	public void setProbationEnd(String probationEnd) {
		this.probationEnd = probationEnd;
	}

	public String getContractEffectDate() {
		return contractEffectDate;
	}

	public void setContractEffectDate(String contractEffectDate) {
		this.contractEffectDate = contractEffectDate;
	}

	public String getContractStopDate() {
		return contractStopDate;
	}

	public void setContractStopDate(String contractStopDate) {
		this.contractStopDate = contractStopDate;
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

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "SalesContract [contractId=" + contractId + ", salesId=" + salesId + ", contractNo=" + contractNo
				+ ", contractType=" + contractType + ", businessAgreementFlag=" + businessAgreementFlag
				+ ", secretAgreementFlag=" + secretAgreementFlag + ", writeDate=" + writeDate + ", probationEnd="
				+ probationEnd + ", contractEffectDate=" + contractEffectDate + ", contractStopDate=" + contractStopDate
				+ ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
				+ ", updatedTime=" + updatedTime + ", deleteFlag=" + deleteFlag + "]";
	}

	public SalesContract(Long contractId, Long salesId, String contractNo, String contractType,
			String businessAgreementFlag, String secretAgreementFlag, String writeDate, String probationEnd,
			String contractEffectDate, String contractStopDate, String createdBy, Date createdTime, String updatedBy,
			Date updatedTime, String deleteFlag) {
		super();
		this.contractId = contractId;
		this.salesId = salesId;
		this.contractNo = contractNo;
		this.contractType = contractType;
		this.businessAgreementFlag = businessAgreementFlag;
		this.secretAgreementFlag = secretAgreementFlag;
		this.writeDate = writeDate;
		this.probationEnd = probationEnd;
		this.contractEffectDate = contractEffectDate;
		this.contractStopDate = contractStopDate;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.deleteFlag = deleteFlag;
	}
}
