package com.hzcf.pojo.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CashAccount implements Serializable{
	
	private static final long serialVersionUID = 6371786758832880032L;

    private Integer id;

    private Integer realNameInfoId;

    private String bankCode;

    private String bankCardNo;

    private String openBankName;

    private String bankReserveMobile;

    private BigDecimal totalMoney;

    private BigDecimal availableMoney;

    private BigDecimal frozenMoney;

    private String accountStatus;

    private Date createTime;

    private String channel;

    private Date updateTime;

    public CashAccount(Integer id, Integer realNameInfoId, String bankCode, String bankCardNo, String openBankName, String bankReserveMobile, BigDecimal totalMoney, BigDecimal availableMoney, BigDecimal frozenMoney, String accountStatus, Date createTime, String channel, Date updateTime) {
        this.id = id;
        this.realNameInfoId = realNameInfoId;
        this.bankCode = bankCode;
        this.bankCardNo = bankCardNo;
        this.openBankName = openBankName;
        this.bankReserveMobile = bankReserveMobile;
        this.totalMoney = totalMoney;
        this.availableMoney = availableMoney;
        this.frozenMoney = frozenMoney;
        this.accountStatus = accountStatus;
        this.createTime = createTime;
        this.channel = channel;
        this.updateTime = updateTime;
    }

    public CashAccount() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRealNameInfoId() {
        return realNameInfoId;
    }

    public void setRealNameInfoId(Integer realNameInfoId) {
        this.realNameInfoId = realNameInfoId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName == null ? null : openBankName.trim();
    }

    public String getBankReserveMobile() {
        return bankReserveMobile;
    }

    public void setBankReserveMobile(String bankReserveMobile) {
        this.bankReserveMobile = bankReserveMobile == null ? null : bankReserveMobile.trim();
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(BigDecimal availableMoney) {
        this.availableMoney = availableMoney;
    }

    public BigDecimal getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}