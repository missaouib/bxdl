package com.hzcf.Enum;
/**
 *<dl>
 *<dt>类名：com.hzcf.enum</dt>
 *<dd>描述: 基本法规则参数枚举类</dd>
 *<dd>创建时间：上午 10:30 2018/10/24 0024 </dd>
 *<dd>创建人：朱广伟</dd>
 *<dt>版本历史: </dt>
 *</dl>
 */
public enum CalParamRuleEnum {

    /**
     * 是否默认基本法
     */
    CAL_DEFAULT("0", "默认基本法"),
    CAL_CUSTOM("1", "自定义基本法"),
    /**
     * 系统字典枚举
     */
    DICT_PARAM("CAL_PARAM_TYPE","基本法参数字典"),
    DICT_RULE("CAL_RULE_TYPE","基本法规则字典"),

    /**
     * 基本法参数
     */
    PARAM_ZY("0", "展业津贴"),
    PARAM_IN("1", "增员津贴"),
    PARAM_C_YC("2", "处育成津贴"),
    PARAM_B_YC("3", "部育成津贴"),
    PARAM_D_GROUP("4", "直辖组管理津贴"),
    PARAM_D_DEPT("5", "直属部管理津贴"),
    /**
     * 展业津贴-规则
     */
    RULE_ZY_0("00", "区间设定值"),
    RULE_ZY_1("01", "FYC百位取整"),
    RULE_ZY_2("02", "FYC乘以比例参数"),
    RULE_ZY_3("03", "有额外的递增"),
    /**
     * 增员津贴-规则
     */
    RULE_IN_0("10", "区间设定值"), //通过FYC范围 配置比例参数
    RULE_IN_1("11", "固定数值"), //固定FYC * 比例参数
    /**
     * 直辖组管理津贴-规则
     */
    RULE_D_GROUP_0("40", "区间设定值"), //通过FYC范围 配置比例参数
    RULE_D_GROUP_1("41", "固定数值"), //固定FYC * 比例参数(江苏)
    RULE_D_GROUP_2("42", "活动人力+区间"), //组活力人数 || FYC范围（山西）
    /**
     * 直辖部管理津贴-规则
     */
    RULE_D_DEPT_0("50", "区间设定值"), //通过FYC范围 配置比例参数
    RULE_D_DEPT_1("51", "固定数值"); //固定FYC * 比例参数

    private String code;
    private String desc;

    CalParamRuleEnum(String code, String desc){
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

}