package com.hzcf.pojo.insurance.promotion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * <p>寿险协议-业务推动-费率</p>
 *
 * @author kongqingbao
 */
public class InsProtocolPromotionRate implements Serializable {

    private static final long serialVersionUID = -1196525800800371372L;

    /**
     * 费率类型1 规保 2 标保
     */
    private Byte rateType;
    /**
     * 周期：0月 1季度 2半年 3年
     */
    private Byte cycle;
    /**
     * 计算公式
     */
    private String allowanceFormula;
    /**
     * 费率
     */
    private Double rate;

    public Byte getRateType() {
        return rateType;
    }

    public void setRateType(Byte rateType) {
        this.rateType = rateType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Byte getCycle() {
        return cycle;
    }

    public void setCycle(Byte cycle) {
        this.cycle = cycle;
    }

    public String getAllowanceFormula() {
        return allowanceFormula;
    }

    public void setAllowanceFormula(String allowanceFormula) {
        this.allowanceFormula = allowanceFormula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        InsProtocolPromotionRate that = (InsProtocolPromotionRate) o;

        return new EqualsBuilder()
                .append(rateType, that.rateType)
                .append(cycle, that.cycle)
                .append(allowanceFormula, that.allowanceFormula)
                .append(rate, that.rate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(rateType)
                .append(cycle)
                .append(allowanceFormula)
                .append(rate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InsProtocolPromotionRate{" +
                "rateType=" + rateType +
                ", cycle=" + cycle +
                ", allowanceFormula='" + allowanceFormula + '\'' +
                ", rate=" + rate +
                '}';
    }
}
