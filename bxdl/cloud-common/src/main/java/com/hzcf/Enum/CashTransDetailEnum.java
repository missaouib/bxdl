package com.hzcf.Enum;
/**
 *<dl>
 *<dt>类名：com.hzcf.enum</dt>
 *<dd>描述: 结算单管理</dd>
 *<dd>创建时间：上午 10:30 2018/10/24 0024 </dd>
 *<dd>创建人：朱广伟</dd>
 *<dt>版本历史: </dt>
 *</dl>
 */
public enum CashTransDetailEnum {
    RECHARGE("1", "充值"),
    POSTAL("2", "提现"),
    Lend("3", "出借"),
    CASH_BACK("4", "回款"),
    REWARD("5", "奖励");

    private String code;
    private String desc;

    CashTransDetailEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static void main(String[] args) {
        System.out.println("args = [" + CashTransDetailEnum.RECHARGE.getCode() + "]");

    }
}