package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.util.Date;

public class SalesRelative implements Serializable{
	
	 private static final long serialVersionUID = 1L;

	    /**
	    * 主键
	    */
	    private Long relativeId;

	    /**
	    * 员工主键
	    */
	    private Long salesId;

	    /**
	    * 成员名称
	    */
	    private String personName;

	    /**
	    * 关系
	    */
	    private String relationShip;

	    /**
	    * 联系电话
	    */
	    private String shipCellPhone;

	    /**
	    * 联系人邮箱
	    */
	    private String shipEmail;

	    /**
	    * 联系人地址
	    */
	    private String shipAddr;

	    /**
	    * 身份证号
	    */
	    private String idCard;

	    /**
	    * 职业
	    */
	    private String shipOccupation;

	    /**
	    * 备注
	    */
	    private String remark;

	    /**
	    * 1家庭成员 2紧急联系人 3司外担保人
	    */
	    private String shipType;

	    /**
	    * 担保日期
	    */
	    private String guaranteeDate;

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

	    public SalesRelative() {
	    	
	    }

		public Long getRelativeId() {
			return relativeId;
		}

		public void setRelativeId(Long relativeId) {
			this.relativeId = relativeId;
		}

		public Long getSalesId() {
			return salesId;
		}

		public void setSalesId(Long salesId) {
			this.salesId = salesId;
		}

		public String getPersonName() {
			return personName;
		}

		public void setPersonName(String personName) {
			this.personName = personName;
		}

		public String getRelationShip() {
			return relationShip;
		}

		public void setRelationShip(String relationShip) {
			this.relationShip = relationShip;
		}

		public String getShipCellPhone() {
			return shipCellPhone;
		}

		public void setShipCellPhone(String shipCellPhone) {
			this.shipCellPhone = shipCellPhone;
		}

		public String getShipEmail() {
			return shipEmail;
		}

		public void setShipEmail(String shipEmail) {
			this.shipEmail = shipEmail;
		}

		public String getShipAddr() {
			return shipAddr;
		}

		public void setShipAddr(String shipAddr) {
			this.shipAddr = shipAddr;
		}

		public String getIdCard() {
			return idCard;
		}

		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}

		public String getShipOccupation() {
			return shipOccupation;
		}

		public void setShipOccupation(String shipOccupation) {
			this.shipOccupation = shipOccupation;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getShipType() {
			return shipType;
		}

		public void setShipType(String shipType) {
			this.shipType = shipType;
		}

		public String getGuaranteeDate() {
			return guaranteeDate;
		}

		public void setGuaranteeDate(String guaranteeDate) {
			this.guaranteeDate = guaranteeDate;
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
			return "SalesRelative [relativeId=" + relativeId + ", salesId=" + salesId + ", personName=" + personName
					+ ", relationShip=" + relationShip + ", shipCellPhone=" + shipCellPhone + ", shipEmail=" + shipEmail
					+ ", shipAddr=" + shipAddr + ", idCard=" + idCard + ", shipOccupation=" + shipOccupation
					+ ", remark=" + remark + ", shipType=" + shipType + ", guaranteeDate=" + guaranteeDate
					+ ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy
					+ ", updatedTime=" + updatedTime + ", deleteFlag=" + deleteFlag + "]";
		}

		public SalesRelative(Long relativeId, Long salesId, String personName, String relationShip,
				String shipCellPhone, String shipEmail, String shipAddr, String idCard, String shipOccupation,
				String remark, String shipType, String guaranteeDate, String createdBy, Date createdTime,
				String updatedBy, Date updatedTime, String deleteFlag) {
			super();
			this.relativeId = relativeId;
			this.salesId = salesId;
			this.personName = personName;
			this.relationShip = relationShip;
			this.shipCellPhone = shipCellPhone;
			this.shipEmail = shipEmail;
			this.shipAddr = shipAddr;
			this.idCard = idCard;
			this.shipOccupation = shipOccupation;
			this.remark = remark;
			this.shipType = shipType;
			this.guaranteeDate = guaranteeDate;
			this.createdBy = createdBy;
			this.createdTime = createdTime;
			this.updatedBy = updatedBy;
			this.updatedTime = updatedTime;
			this.deleteFlag = deleteFlag;
		}

}
