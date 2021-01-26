package com.hzcf.plantform.constants;

public class Constants {
	public static final String CHECK_CODE ="check_code";//验证码key值
	public static final String MD5KEY="SJK*)(bxdxa?a^%#)";
	//保单
	public static final String POLICY_TYPT_2="2";//保单
	public static final String POLICY_TYPT_1="1";//投保单
	//新契约作业订单导入类型

	public static  final String IMPL_TYPE_POLICY="1";
	public static  final String IMPL_TYPE_NOTE_OR_ENTRY="0";
	public static  final String IMPL_TYPE_NEW_POLICY="2";

	public static final String POLICY_STATUS_0000="0000";//暂存状态

	public static final String POLICY_STATUS_1="1";//待审核

	public static final String POLICY_STATUS_2="2";//审核成功

	public static final String POLICY_STATUS_3="3";//审核失败

	public static final String POLICY_STATUS_4="4";//待移交--作废

	public static final String POLICY_STATUS_5="5";//已移交

	public static final String POLICY_STATUS_6="6";//待发放

	public static final String POLICY_STATUS_7="7";//已发放

	public static final String POLICY_STATUS_8="8";//已回复

	public static final String POLICY_STATUS_9="9";//已回销

	public static final String POLICY_STATUS_10="10";//异常回销

	public static final String POLICY_STATUS_11="11";//已录入

	public static final String INSURANCE_POLICY_STATUS_1001="1001";//保单以录入 状态

	public static final String INSURANCE_POLICY_STATUS_1002="1002";//审核通过 保单状态

	public static final String INSURANCE_POLICY_STATUS_1003="1003";//审核不通过 保单状态

	public static final String INSURANCE_POLICY_STATUS_1004="1004";//已下发 保单状态

	public static final String INSURANCE_POLICY_STATUS_1005="1005";//已接收

	public static final String INSURANCE_POLICY_STATUS_1006="1006";//回执已录入

	public static final String INSURANCE_POLICY_STATUS_1007="1007";//回执审核通过

	public static final String INSURANCE_POLICY_STATUS_1008="1008";//回执未通过

	public static final String INSURANCE_POLICY_STATUS_1009="1009";//已下发

	public static final String INSURANCE_POLICY_STATUS_1010="1010";//以回访

	public static final String INSURANCE_POLICY_STATUS_1011="1011";//撤保

	public static final String INSURANCE_POLICY_STATUS_1012="1012";//退保

	public static final String INSURANCE_POLICY_STATUS_1013="1013";//终止

	public static final String INSURANCE_POLICY_STATUS_1014="1014";//中止

	public static final String POLICY_ATT_TYPE_1="1";//投保单影像录入

	public static final String POLICY_ATT_TYPE_2="2";//保单影像录入

	public static final String POLICY_ATT_TYPE_3="3";//

	public static final String MAINN = "0";//主

	public static final String YES = "是";//是1

	public static final String NO = "否";//否2

	public static final String YESCODE = "1";//是1

	public static final String NOCODE = "2";//否2



	 public static final String STATE_START_CONTENT = "启用";
     public static final String STATE_PAUSE_CONTENT = "暂停";

     public static final String DATA_FORMAT_NUMBER = "^\\d+(\\.\\d+)?$";//数字
	 public static final String DATA_FORMAT_LETTER = "^[a-zA-Z]+$";//英文
     public static final String DATA_FORMAT_FAX = "^(\\d{3,4}-)?\\d{7,8}$";	//传真
	 public static final String DATA_FORMAT_MOBLIE = "^1[3456789]\\d{9}$";//手机号
	 public static final String DATA_FORMAT_IDCARD = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";//身份证
	 public static final String DATA_FORMAT_HKCARD ="^([A-Z]\\d{6,10}(\\(\\w{1}\\))?)$";//港澳台
	 public static final String DATA_FORMAT_PASSPORTCARD ="^([a-zA-z]|[0-9]){5,17}$";//护照
	 public static final String DATA_FORMAT_OFFICERCARD ="^[\\u4E00-\\u9FA5](字第)([0-9a-zA-Z]{4,8})(号?)$";//军官证

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

	public static final String RELATION_SALE_NAME_DB = "担保人";
	public static final String RELATION_SALE_NAME_TJ = "推荐人";
	public static final String RELATION_SALE_NAME_CYCF = "一级处育成人";
	public static final String RELATION_SALE_NAME_CYCS = "二级处育成人";
	public static final String RELATION_SALE_NAME_BYCF= "一级部育成人";
	public static final String RELATION_SALE_NAME_BYCS= "二级部育成人";
	public static final String RELATION_SALE_NAME_SJ = "上级管理人";
	public static final String RELATION_SALE_NAME_JC = "继承人";
	public static final String RELATION_SALE_NAME_FD = "辅导人";
	public static final String RELATION_SALE_NAME_CZ = "直辖处处长";
	public static final String RELATION_SALE_NAME_BZ = "直辖部部长";



	//立即生效
    public static final String  EFFECT_TAB_1 = "1";
    //次月生效
    public static final String  EFFECT_TAB_2 = "2";

    //新增员工关系
    public static final String  OPERATION_TAB_0 = "0";
    //修改员工关系
    public static final String  OPERATION_TAB_1 = "1";
    //批量导入
    public static final String  OPERATION_TAB_2 = "2";
    //定时器置为失效
    public static final String  OPERATION_TAB_3 = "3";

    /*********以下为字典类型*********/
    //性别
    public static final String DICT_TYPE_SEX = "SEX";
       //证件类型
    public static final String DICT_TYPE_CARD = "CARD";
       //婚姻状况
    public static final String DICT_TYPE_MARITAL_STATUS = "MARITAL_STATUS";
       //客户状态
    public static final String DICT_TYPE_CUSTOMER_STATUS = "CUSTOMER_STATUS";
       //银行
    public static final String DICT_TYPE_BANK = "BANK";
       //支付方式
    public static final String DICT_TYPE_PAY_TYPE = "PAY_TYPE";
       //关系
    public static final String DICT_TYPE_RELATIONSHIP = "RELATIONSHIP";
       //是否
    public static final String DICT_TYPE_YESORNO = "YESORNO";
       //审核结果
    public static final String DICT_TYPE_AUDITRESULTS = "AUDITRESULTS";
       //缴费方式
    public static final String DICT_TYPE_JFFS = "JFFS";
       //购买产品缴费方式
    public static final String DICT_TYPE_JFTYPE = "JFTYPE";
       //学历
    public static final String DICT_TYPE_EDU = "EDU";
       //政治面貌
    public static final String DICT_TYPE_FACE = "FACE";
       //学位
    public static final String DICT_TYPE_DEGREE = "DEGREE";


    //销售人员状态
    public static final String SALES_STATUS_0 = "0";//试用
    public static final String SALES_STATUS_1 = "1";//正式
    public static final String SALES_STATUS_2 = "2";//离职
    public static final String SALES_STATUS_3 = "3";//黑名单

	//基本法参数规则前缀
	public static final String RULE_TYPE_PREFIX = "rule";


//对账批次相关
    public static final String BATCH_TYPE_0 = "0"; //手续费对账
    public static final String BATCH_TYPE_1 = "1";//技术服务费对账
    public static final String BATCH_TYPE_2 = "2";//广告服务费对账

    public static final String BATCH_STATUS_0 = "0"; //新建
    public static final String BATCH_STATUS_1 = "1";//进行中
    public static final String BATCH_STATUS_2 = "2";//处理完成

	   public static final String CHECK_STATUS_0 = "0"; //未核对
    public static final String CHECK_STATUS_1 = "1";//已核对
    public static final String CHECK_STATUS_2 = "2";//延迟核对
	 public static final String CHECK_STATUS_3 = "3";//单独存在
	 public static final String CHECK_STATUS_4 = "4";//数据不一致




}
