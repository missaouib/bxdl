package com.hzcf.pojo.product;

import java.io.Serializable;

public class InsuranceProductDetailWithBLOBs extends InsuranceProductDetail implements Serializable{

	private static final long serialVersionUID = 2284638870647756247L;

	private String insuranCoverExplain;

    private String basicResponsibilitiesExplain;

    private String polocyHolderRestrictions;

    private String exemptionInstruction;

    private String remark;

    public String getInsuranCoverExplain() {
        return insuranCoverExplain;
    }

    public void setInsuranCoverExplain(String insuranCoverExplain) {
        this.insuranCoverExplain = insuranCoverExplain == null ? null : insuranCoverExplain.trim();
    }

    public String getBasicResponsibilitiesExplain() {
        return basicResponsibilitiesExplain;
    }

    public void setBasicResponsibilitiesExplain(String basicResponsibilitiesExplain) {
        this.basicResponsibilitiesExplain = basicResponsibilitiesExplain == null ? null : basicResponsibilitiesExplain.trim();
    }

    public String getPolocyHolderRestrictions() {
        return polocyHolderRestrictions;
    }

    public void setPolocyHolderRestrictions(String polocyHolderRestrictions) {
        this.polocyHolderRestrictions = polocyHolderRestrictions == null ? null : polocyHolderRestrictions.trim();
    }

    public String getExemptionInstruction() {
        return exemptionInstruction;
    }

    public void setExemptionInstruction(String exemptionInstruction) {
        this.exemptionInstruction = exemptionInstruction == null ? null : exemptionInstruction.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}