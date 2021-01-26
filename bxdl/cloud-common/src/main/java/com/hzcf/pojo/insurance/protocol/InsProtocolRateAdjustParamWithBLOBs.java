package com.hzcf.pojo.insurance.protocol;

public class InsProtocolRateAdjustParamWithBLOBs extends InsProtocolRateAdjustParam {
    private String changeRate;

    private String changeSubjoinRate;

    private String allChangeRate;

    private String allChangeSubjoinRate;

    public String getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(String changeRate) {
        this.changeRate = changeRate == null ? null : changeRate.trim();
    }

    public String getChangeSubjoinRate() {
        return changeSubjoinRate;
    }

    public void setChangeSubjoinRate(String changeSubjoinRate) {
        this.changeSubjoinRate = changeSubjoinRate == null ? null : changeSubjoinRate.trim();
    }

    public String getAllChangeRate() {
        return allChangeRate;
    }

    public void setAllChangeRate(String allChangeRate) {
        this.allChangeRate = allChangeRate == null ? null : allChangeRate.trim();
    }

    public String getAllChangeSubjoinRate() {
        return allChangeSubjoinRate;
    }

    public void setAllChangeSubjoinRate(String allChangeSubjoinRate) {
        this.allChangeSubjoinRate = allChangeSubjoinRate == null ? null : allChangeSubjoinRate.trim();
    }
}