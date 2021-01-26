package com.hzcf.pojo.insurance.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalesDaysCommission implements Serializable {

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
     * 销售团队
     */
    private Long salesTeamId;

    /**
     * 员工职级
     */
    private Long salerGrade;

    /**
     * 初年度佣金
     */
    private BigDecimal salerFyc;

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
     * 合计佣金
     */
    private BigDecimal totalCommission;

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
     * 佣金加扣
     */
    private BigDecimal cutCommission;

    /**
     * 冻结税额
     */
    private BigDecimal freezingTaxes;

    /**
     * 发放金额
     */
    private BigDecimal issuedAmount;
    /**
     * 是否发放
     */
    private String settlementStatus;
    /**
     * 结算月(格式 yyyyMM,例如：202004)
     */
    private String settlementMonth;

    public SalesDaysCommission() {

    }


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


    public BigDecimal getSalerFyc() {
        return salerFyc;
    }


    public void setSalerFyc(BigDecimal salerFyc) {
        this.salerFyc = salerFyc;
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

    public BigDecimal getTotalCommission() {
        return totalCommission;
    }


    public void setTotalCommission(BigDecimal totalCommission) {
        this.totalCommission = totalCommission;
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

    public BigDecimal getCutCommission() {
        return cutCommission;
    }

    public void setCutCommission(BigDecimal cutCommission) {
        this.cutCommission = cutCommission;
    }

    public BigDecimal getFreezingTaxes() {
        return freezingTaxes;
    }

    public void setFreezingTaxes(BigDecimal freezingTaxes) {
        this.freezingTaxes = freezingTaxes;
    }

    public BigDecimal getIssuedAmount() {
        return issuedAmount;
    }

    public void setIssuedAmount(BigDecimal issuedAmount) {
        this.issuedAmount = issuedAmount;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getSettlementMonth() {
        return settlementMonth;
    }

    public void setSettlementMonth(String settlementMonth) {
        this.settlementMonth = settlementMonth;
    }

    @Override
    public String toString() {
        return "SalesDaysCommission{" +
                "commissionId=" + commissionId +
                ", salerId=" + salerId +
                ", salesOrgId=" + salesOrgId +
                ", salesTeamId=" + salesTeamId +
                ", salerGrade=" + salerGrade +
                ", salerFyc=" + salerFyc +
                ", initialAnnualCommission=" + initialAnnualCommission +
                ", exhibitionAllowance=" + exhibitionAllowance +
                ", increaseAllowance=" + increaseAllowance +
                ", continuousAllowance=" + continuousAllowance +
                ", continuationRateBonus=" + continuationRateBonus +
                ", annualBonus=" + annualBonus +
                ", serviceAward=" + serviceAward +
                ", groupRunAllowance=" + groupRunAllowance +
                ", directorCultureAward=" + directorCultureAward +
                ", depRunAllowance=" + depRunAllowance +
                ", ministerCultureAward=" + ministerCultureAward +
                ", chiefInspectorAllowance=" + chiefInspectorAllowance +
                ", totalCommission=" + totalCommission +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", cutCommission=" + cutCommission +
                ", freezingTaxes=" + freezingTaxes +
                ", issuedAmount=" + issuedAmount +
                ", settlementStatus='" + settlementStatus + '\'' +
                ", settlementMonth='" + settlementMonth + '\'' +
                '}';
    }

    public SalesDaysCommission(Long commissionId, Long salerId, Long salesOrgId, Long salesTeamId, Long salerGrade,
                               BigDecimal salerFyc, BigDecimal initialAnnualCommission, BigDecimal exhibitionAllowance,
                               BigDecimal increaseAllowance, BigDecimal continuousAllowance, BigDecimal continuationRateBonus,
                               BigDecimal annualBonus, BigDecimal serviceAward, BigDecimal groupRunAllowance,
                               BigDecimal directorCultureAward, BigDecimal depRunAllowance, BigDecimal ministerCultureAward,
                               BigDecimal chiefInspectorAllowance, BigDecimal totalCommission, String createdBy, Date createdTime,
                               String updatedBy, Date updatedTime) {
        super();
        this.commissionId = commissionId;
        this.salerId = salerId;
        this.salesOrgId = salesOrgId;
        this.salesTeamId = salesTeamId;
        this.salerGrade = salerGrade;
        this.salerFyc = salerFyc;
        this.initialAnnualCommission = initialAnnualCommission;
        this.exhibitionAllowance = exhibitionAllowance;
        this.increaseAllowance = increaseAllowance;
        this.continuousAllowance = continuousAllowance;
        this.continuationRateBonus = continuationRateBonus;
        this.annualBonus = annualBonus;
        this.serviceAward = serviceAward;
        this.groupRunAllowance = groupRunAllowance;
        this.directorCultureAward = directorCultureAward;
        this.depRunAllowance = depRunAllowance;
        this.ministerCultureAward = ministerCultureAward;
        this.chiefInspectorAllowance = chiefInspectorAllowance;
        this.totalCommission = totalCommission;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

}
