package com.hzcf.core.constants;

/**
 * Created by qin lina on 2020/4/23.
 */
public class Constants {

    public static final String INSURANCE_POLICY_STATUS_1010="1010";//以回访

    /*可结算审核状态*/
    // 未审核
    public static final String AUDIT_STATUS_0 = "0";
    // 审核通过
    public static final String AUDIT_STATUS_1 = "1";
    // 审核不通过
    public static final String AUDIT_STATUS_2 = "2";
    // 挂起
    public static final String AUDIT_STATUS_3 = "3";
    // 中止
    public static final String AUDIT_STATUS_4 = "4";


    /*可结算发放状态*/
    //未结算settlement_status
    public static final String SETTLEMENT_STATUS_0 = "0";
    //已结算
     public static final String SETTLEMENT_STATUS_1 = "1";
    //已发放
     public static final String SETTLEMENT_STATUS_2 = "2";



     /*佣金审核列表*/
     //待审核
     public static final String COMMISSION_STATUS_0 = "0";
     //审核不通过
     public static final String COMMISSION_STATUS_NO_1 = "-1";
     //审核通过 待发放
     public static final String COMMISSION_STATUS_1 = "1";
     //已发放
     public static final String COMMISSION_STATUS_2 = "2";



     //"总监级"
	public static final String SALES_GRADE_NAME_1 = "1";
	//"部长级"
	public static final String SALES_GRADE_NAME_2 = "2";
	//"处长级"
	public static final String SALES_GRADE_NAME_3 = "3";
	//"科长级"
	public static final String SALES_GRADE_NAME_4 = "4";
	//"考察期科长"
	public static final String SALES_GRADE_NAME_5 = "5";
	//"观察期处长"
	public static final String SALES_GRADE_NAME_6 = "6";
	//"观察期部长"
	public static final String SALES_GRADE_NAME_7 = "7";

	//汇康同步类型和状态
    public static final String NOTICY_TYPE_SALES = "0";//销售人员
    public static final String NOTICY_TYPE_ORG = "1";//组织机构

    public static final String NOTICY_RESULT_FAIL ="0";//未推送成功
    public static final String NOTICY_RESULT_SUCC = "1";//推送成功_


    //销售人员状态
    public static final String SALES_STATUS_0 = "0";//试用
    public static final String SALES_STATUS_1 = "1";//正式
    public static final String SALES_STATUS_2 = "2";//离职
    public static final String SALES_STATUS_3 = "3";//黑名单



    public static final String STR_KEY_HK = "hkjm2020dt";//秘钥



    //育成年数
    public static final String YC_YEAR_1 = "1";//育成第一年
    public static final String YC_YEAR_2 = "2";//育成第二年

     //育成代数
    public static final String SENIORITY_TYPE_1 = "1";//一代育成
    public static final String SENIORITY_TYPE_2 = "2";//二代育成


    //组活动人力数和FYC比例 条件关系
    public static final String RELATION_TYPE_0 = "0";//或
    public static final String RELATION_TYPE_1= "1";//且

    //直辖部参数是否包含部长直辖组
    public static final String INCLUDE_DIRECTLY_GROUP_FLAG_0 = "0";//不包含
    public static final String INCLUDE_DIRECTLY_GROUP_FLAG_1 = "1";//包含

    //直辖组参数是否排除部长直辖组
    public static final String EXCLUDE_DIRECTLY_GROUP_FLAG_0 = "0";//不排除
    public static final String EXCLUDE_DIRECTLY_GROUP_FLAG_1 = "1";//排除


     //直辖组参数是否包含组活动人力
    public static final String ACTIVE_SALER_FLAG_0 = "0";//不包含
    public static final String ACTIVE_SALER_FLAG_1 = "1";//包含
















}
