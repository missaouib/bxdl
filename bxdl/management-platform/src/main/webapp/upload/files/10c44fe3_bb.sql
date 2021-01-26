
--  注册信息
query@var $mobile$='';
query@var $realName$='';
query@var $id$='';
query@var $regDateStart$='2018-09-01 00:00:00';
query@var $regDateEnd$='2018-09-31 23:59:59';
query@var $investTimeStart$='';
query@var $investTimeEnd$='';
query@var $invest$='';
query@var $refferee$='';
query@var $relation$='';
query@var $department$='';
query@var $departmentLevel$='';
query@var $departmentName$='';


select * from (
SELECT
	tu.id AS '用户id（1.0）',
	tu.mobile AS '手机号',
	tu.realName AS '姓名',
	ta.idcard AS '身份证',
	CASE IF (length(ta.idcard)=18,cast(substring(ta.idcard,17,1) AS UNSIGNED) % 2,IF (length(ta.idcard)=15,cast(substring(ta.idcard,15,1) AS UNSIGNED) % 2,3)) WHEN 1 THEN '男' WHEN 0 THEN '女' ELSE '/' END AS '性别',
	CASE WHEN ta.idcard IS NULL THEN '/' ELSE YEAR (curdate()) - IF(length(ta.idcard)=18,substring(ta.idcard,7,4),IF(length(idcard) = 15,concat('19',substring(ta.idcard,7,2)),NULL)) END AS '年龄',
	CASE LEFT (ta.idcard, 2) WHEN '11' THEN'北京市'WHEN '12' THEN'天津市'WHEN '13' THEN'河北省'WHEN '14' THEN'山西省'WHEN '15' THEN'内蒙古自治区'WHEN '21' THEN'辽宁省'WHEN '22' THEN'吉林省'WHEN '23' THEN'黑龙江省'WHEN '31' THEN'上海市'WHEN '32' THEN'江苏省'WHEN '33' THEN'浙江省'WHEN '34' THEN'安徽省'WHEN '35' THEN'福建省'WHEN '36' THEN'江西省'WHEN '37' THEN'山东省'WHEN '41' THEN'河南省'WHEN '42' THEN'湖北省'WHEN '43' THEN'湖南省'WHEN '44' THEN'广东省'WHEN '45' THEN'广西壮族自治区'WHEN '46' THEN'海南省'WHEN '50' THEN'重庆市'WHEN '51' THEN'四川省'WHEN '52' THEN'贵州省'WHEN '53' THEN'云南省'WHEN '54' THEN'西藏自治区'WHEN '61' THEN'陕西省'WHEN '62' THEN'甘肃省'WHEN '63' THEN '青海省' WHEN '64' THEN '宁夏回族自治区' WHEN '65' THEN '新疆维吾尔自治区' WHEN '71' THEN '台湾省' WHEN '81' THEN '香港特别行政区' WHEN '82' THEN '澳门特别行政区'
ELSE '/' END AS '所在区域',
	DATE_FORMAT(tu.regDate,'%Y-%m-%d %T') AS '注册时间',
	CASE WHEN (SELECT COUNT(1) FROM hzcf_p2p.t_invest i WHERE i.flag != '2' AND i.investor = tu.id)>0 THEN '是'  ELSE '否' END AS '是否出借过',
	(SELECT DATE_FORMAT(MIN(i.investTime),'%Y-%m-%d %T') from t_invest i where i.flag != '2' and i.investor = tu.id) AS '首投时间',
	CASE tu.regSource WHEN 'web01' THEN'web线上'WHEN 'web02' THEN'web线下'WHEN 'iosApp01' THEN'ios线上'WHEN 'iosApp02' THEN'ios线下'WHEN 'androidApp01' THEN'android线上'WHEN 'androidApp02' THEN'android线下'WHEN 'weixin01' THEN'微信线上'WHEN 'weixin02' THEN'微信线下'WHEN 'dlr01' THEN'面包云线上'WHEN 'dlr02' THEN'面包云线下'WHEN 'h5androidApp01' THEN'微信h5andro线上'WHEN 'h5androidApp02' THEN'微信h5andro线下'WHEN 'h5iosApp01' THEN'微信h5ios线上'WHEN 'h5iosApp02' THEN'微信h5ios线下'WHEN '2.0' THEN'2.0'WHEN 'App' THEN 'App'
ELSE '/' END '注册来源',
	IFNULL(tu.staffId,'') AS 'StaffId',
	IFNULL(tu.refferee,'') AS '上级推荐码',
	IFNULL(a.refferee,'') AS '上上级推荐码',
	IFNULL(b.refferee,'') AS '上上上级推荐码',
	CASE tu.userType WHEN 1 THEN '出借人' WHEN 2 THEN '借款人' ELSE '/' END '用户类型',
	CASE WHEN left(tu.staffId,1) = '2' THEN '普惠' WHEN left(tu.refferee,1) = '2' THEN '普惠' WHEN left(a.refferee,1) = '2' THEN '普惠' WHEN left(tu.refferee,1) = '3' THEN '合伙人' ELSE '其他' END '归属',
	CASE WHEN tu.mobile = tu2.mobile THEN '内部员工' WHEN tu.refferee = tu3.mobile THEN '员工推荐' ELSE '其他' END AS '员工关系',
  IFNULL(tu2.oneDepartment,tu3.oneDepartment) AS '一级部门',
  IFNULL(tu2.twoDepartment,tu3.twoDepartment) AS '二级部门',
  IFNULL(tu2.threeDepartment,tu3.threeDepartment) AS '三级部门',
  IFNULL(tu2.fourDepartment,tu3.fourDepartment) AS '四级部门',
  IFNULL(tu2.fiveDepartment,tu3.fiveDepartment) AS '五级部门'
FROM
	t_user tu
LEFT JOIN t_authentication ta ON ta.userId = tu.id
LEFT JOIN t_user a ON a.mobile = tu.refferee
LEFT JOIN t_user b ON b.mobile = a.refferee
LEFT JOIN hzwd_web.t_yx_employee_summary tu2 ON tu2.mobile = tu.mobile
LEFT JOIN hzwd_web.t_yx_employee_summary tu3 ON tu3.mobile = tu.refferee
WHERE 
	tu.regDate BETWEEN $regDateStart$ AND $regDateEnd$ and tu.mobile like concat('%',$mobile$,'%') and tu.realName like concat('%',$realName$,'%')
	and tu.id like concat('%',$id$,'%')
ORDER BY tu.regDate
) userTemp where 归属 like concat('%',$refferee$,'%') and 是否出借过 like concat('%',$invest$,'%') and 员工关系 like concat('%',$relation$,'%') 


-- 回款

query@var $mobile$='';
query@var $realName$='';
query@var $userId$='';
query@var $contract$='';
query@var $refferee$=''; 
query@var $receivableStart$='2018-09-01 00:00:00';
query@var $receivableEnd$='2018-09-31 23:59:59';
query@var $receivableType$='';
query@var $lendPeriod$='';
query@var $yqPeriod$='';

SELECT * FROM (
SELECT
	tu.id AS '用户id（1.0）',
	tu.mobile AS '手机号',
	tu.realName AS '姓名',
	ta.idcard AS '身份证',
	CASE IF (length(ta.idcard)=18,cast(substring(ta.idcard,17,1) AS UNSIGNED) % 2,IF (length(ta.idcard)=15,cast(substring(ta.idcard,15,1) AS UNSIGNED) % 2,3)) WHEN 1 THEN '男' WHEN 0 THEN '女' ELSE '/' END AS '性别',
	CASE WHEN ta.idcard IS NULL THEN '/' ELSE YEAR (curdate()) - IF(length(ta.idcard)=18,substring(ta.idcard,7,4),IF(length(idcard) = 15,concat('19',substring(ta.idcard,7,2)),NULL)) END AS '年龄',
	CASE LEFT (ta.idcard, 2) WHEN '11' THEN'北京市'WHEN '12' THEN'天津市'WHEN '13' THEN'河北省'WHEN '14' THEN'山西省'WHEN '15' THEN'内蒙古自治区'WHEN '21' THEN'辽宁省'WHEN '22' THEN'吉林省'WHEN '23' THEN'黑龙江省'WHEN '31' THEN'上海市'WHEN '32' THEN'江苏省'WHEN '33' THEN'浙江省'WHEN '34' THEN'安徽省'WHEN '35' THEN'福建省'WHEN '36' THEN'江西省'WHEN '37' THEN'山东省'WHEN '41' THEN'河南省'WHEN '42' THEN'湖北省'WHEN '43' THEN'湖南省'WHEN '44' THEN'广东省'WHEN '45' THEN'广西壮族自治区'WHEN '46' THEN'海南省'WHEN '50' THEN'重庆市'WHEN '51' THEN'四川省'WHEN '52' THEN'贵州省'WHEN '53' THEN'云南省'WHEN '54' THEN'西藏自治区'WHEN '61' THEN'陕西省'WHEN '62' THEN'甘肃省'WHEN '63' THEN '青海省' WHEN '64' THEN '宁夏回族自治区' WHEN '65' THEN '新疆维吾尔自治区' WHEN '71' THEN '台湾省' WHEN '81' THEN '香港特别行政区' WHEN '82' THEN '澳门特别行政区'
ELSE '/' END AS '所在区域',
	DATE_FORMAT(tu.regDate,'%Y-%m-%d %T') AS '注册时间',
	CASE tfd.bizType WHEN '01' THEN'充值'WHEN '02' THEN'提现'WHEN '11' THEN'投资理财'WHEN '12' THEN'手工投资散标'WHEN '13' THEN'自动投资理财'WHEN '14' THEN'自动投资散标'WHEN '21' THEN'本息回款'WHEN '22' THEN'利息'WHEN '30' THEN'投资奖励'WHEN '31' THEN'推荐用户奖励'WHEN '32' THEN'活动返现'WHEN '33' THEN'活动提成'WHEN '61' THEN'提现手续费'WHEN '62' THEN'利息管理费'WHEN '63' THEN'实名认证管理费'WHEN '19' THEN'利息券'WHEN '20' THEN'返现券' WHEN '34' THEN'延期奖励' WHEN '35' THEN'延期息差' END AS '业务类型_回款类型',
	DATE_FORMAT(tfd.createTime,'%Y-%m-%d %T') AS '回款时间',
	tfd.debit AS '回款金额',
	ti.refferee AS '出借时的推荐码',
	ti.id AS '合同编号',
	ti.investAmount AS '出借金额',
	DATE_FORMAT(ti.investTime,'%Y-%m-%d %T') AS '出借日期',
	pro.product_name AS '出借产品',
	DATE_FORMAT(ti.expireDate,'%Y-%m-%d %T') AS '到期日期',
	ti.yearRate AS '出借利率',
	ti.recievedInterest AS '出借预计利息',
	DATE_FORMAT(ti.yqExpireDate,'%Y-%m-%d %T') AS '延期到期时间',
   IFNULL(ti.yqTerm,'') AS '延长授权期期数',
	ti.xcRate AS '息差利率(奖励1)',
	ti.xcAmount AS '息差金额(奖励1)',
	ti.yqRate AS '延期利率(奖励2)',
	ti.yqAmount AS '延期金额(奖励2)',
	CASE tu.regSource WHEN 'web01' THEN 'web线上' WHEN 'web02' THEN 'web线下' WHEN 'iosApp01' THEN 'ios线上' WHEN 'iosApp02' THEN 'ios线下' WHEN 'androidApp01' THEN 'android线上' WHEN 'androidApp02' THEN 'android线下' WHEN 'weixin01' THEN '微信线上' WHEN 'weixin02' THEN '微信线下' WHEN 'dlr01' THEN '面包云线上' WHEN 'dlr02' THEN '面包云线下' WHEN 'h5androidApp01' THEN '微信h5andro线上' WHEN 'h5androidApp02' THEN '微信h5andro线下' WHEN 'h5iosApp01' THEN '微信h5ios线上' WHEN 'h5iosApp02' THEN '微信h5ios线下' WHEN '2.0' THEN '2.0' WHEN 'App' THEN 'App'
ELSE '/' END AS '注册来源',
	CASE WHEN LEFT(ti.id,2)= 'MB' then 'app' when left(ti.id,2)= 'LC' then 'pc' when left(ti.id,2)= 'DL' then '面包云' else '/' end '出借端口',
	IFNULL(tu.staffId,'') AS 'StaffId',
	IFNULL(tu.refferee,'') AS '上级推荐码',
	IFNULL(a.refferee,'') AS '上上级推荐码',
	IFNULL(b.refferee,'') AS '上上上级推荐码',
	CASE tu.userType WHEN 1 THEN '出借人' WHEN 2 THEN '借款人' ELSE '/' END '用户类型',
	CASE WHEN left(tu.staffId,1) = '2' THEN '普惠' WHEN left(tu.refferee,1) = '2' THEN '普惠' WHEN left(a.refferee,1) = '2' THEN '普惠' WHEN left(tu.refferee,1) = '3' THEN '合伙人' ELSE '其他' END '归属',
	CASE WHEN tu.mobile = tu2.mobile THEN '内部员工' WHEN tu.refferee = tu3.mobile THEN '员工推荐' ELSE '其他' END AS '员工关系',
  IFNULL(tu2.oneDepartment,tu3.oneDepartment) AS '一级部门',
  IFNULL(tu2.twoDepartment,tu3.twoDepartment) AS '二级部门',
  IFNULL(tu2.threeDepartment,tu3.threeDepartment) AS '三级部门',
  IFNULL(tu2.fourDepartment,tu3.fourDepartment) AS '四级部门',
  IFNULL(tu2.fiveDepartment,tu3.fiveDepartment) AS '五级部门',
  pro.closeTime AS '出借产品期数'

FROM
	t_fd_account_log tfd
LEFT JOIN t_user tu ON tu.id = tfd.userId
LEFT JOIN t_user a ON a.mobile = tu.refferee
LEFT JOIN t_user b ON b.mobile = a.refferee
LEFT JOIN t_authentication ta ON ta.userId = tfd.userId
LEFT JOIN t_invest ti ON ti.id = tfd.bizId
LEFT JOIN t_finance_product pro ON ti.borrowId = pro.id
LEFT JOIN hzwd_web.t_yx_employee_summary tu2 ON tu2.mobile = tu.mobile
LEFT JOIN hzwd_web.t_yx_employee_summary tu3 ON tu3.mobile = tu2.mobile
WHERE 
	tfd.bizType IN ('21','22','34','35')
AND tfd.createTime BETWEEN $receivableStart$ AND $receivableEnd$ AND tu.mobile like concat('%',$mobile$,'%') AND tu.realName like concat('%',$realName$,'%') AND tu.id like concat('%',$userId$,'%') AND ti.id like concat('%',$contract$,'%') ) temp WHERE 归属 like concat('%',$refferee$,'%') AND 业务类型_回款类型 like concat('%',$receivableType$,'%') AND 出借产品期数 LIKE concat('%',$lendPeriod$,'%') AND 延长授权期期数 LIKE concat('%',$yqPeriod$,'%')  