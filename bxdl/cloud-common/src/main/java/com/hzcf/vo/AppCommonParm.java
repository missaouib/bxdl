package com.hzcf.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <dl>
 * <dt>类名：com.hzcf.vo</dt>
 * <dd>描述: 请求参数公共类</dd>
 * <dd>创建时间：下午 13:48 2018/10/22 0022 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
public class AppCommonParm implements Serializable{
    private boolean modifyMobileFlag;
    private boolean modifyUserStatusFlag;
    private String newUserName;
    private String userName;
    private String crmMobile;
    private String channel; //渠道标识
    private String customerName; //客户真实姓名
    private String cardNo; //证件号
    private String maritalStatus; //婚姻状况（0：未婚；1：已婚）
    private String customerNo; //客户编号
    private String userNo; //用户编号
    private String userStatus; //用户状态
    private String integralType; // 积分类型（1:通用积分；2自有积分）
    private String integralCount; //积分数量
    private String detailType; //积分增加或消耗标志（1：新增；2：消耗）
    private String detailOddNo;//明细单号
    private String transResult; //交易结果（0：失败；1成功）
    private String transFlowNo; //渠道交易流水号
    private Date buildTime; //交易明细创建时间
    private BigDecimal amount; //交易金额


    public boolean ismodifyMobileFlag() {
        return modifyMobileFlag;
    }

    public void setModifyMobileFlag(boolean modifyMobileFlag) {
        this.modifyMobileFlag = modifyMobileFlag;
    }



    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCrmMobile() {
        return crmMobile;
    }

    public void setCrmMobile(String crmMobile) {
        this.crmMobile = crmMobile;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getIntegralType() {
        return integralType;
    }

    public void setIntegralType(String integralType) {
        this.integralType = integralType;
    }

    public String getIntegralCount() {
        return integralCount;
    }

    public void setIntegralCount(String integralCount) {
        this.integralCount = integralCount;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getDetailOddNo() {
        return detailOddNo;
    }

    public void setDetailOddNo(String detailOddNo) {
        this.detailOddNo = detailOddNo;
    }

    public String getTransResult() {
        return transResult;
    }

    public void setTransResult(String transResult) {
        this.transResult = transResult;
    }

    public boolean isModifyUserStatusFlag() {
        return modifyUserStatusFlag;
    }

    public void setModifyUserStatusFlag(boolean modifyUserStatusFlag) {
        this.modifyUserStatusFlag = modifyUserStatusFlag;
    }

    public String getTransFlowNo() {
        return transFlowNo;
    }

    public void setTransFlowNo(String transFlowNo) {
        this.transFlowNo = transFlowNo;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
