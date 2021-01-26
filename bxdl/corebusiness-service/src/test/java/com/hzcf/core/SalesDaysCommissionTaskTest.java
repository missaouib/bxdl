package com.hzcf.core;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hzcf.CorebusinessServiceApplication;
import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.core.calculation.*;
import com.hzcf.core.constants.Constants;
import com.hzcf.core.service.*;
import com.hzcf.core.service.cal.*;
import com.hzcf.core.util.NumberFormat;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.cal.*;
import com.hzcf.pojo.insurance.sales.InsSalesPreGrade;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.parameter.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by qin lina on 2020/5/6.
 */

@SpringBootTest(classes = CorebusinessServiceApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SalesDaysCommissionTaskTest {
    private Logger logger = LoggerFactory.getLogger(SalesDaysCommissionTaskTest.class);

    @Autowired
    private SalesDaysCommissionService salesDaysCommissionService;
    @Autowired
    private CalPromoteSerivce calPromoteSerivce;
    @Autowired
    private CalIncreaseStaffService calIncreaseStaffService;
    @Autowired
    private CalDirectlyUnderGroupService calDirectlyUnderGroupService;
    @Autowired
    private CalDirectlyUnderDeptService calDirectlyUnderDeptService;
	@Autowired
	private CalNurtureDirectorService calNurtureDirectorService;
	@Autowired
	private CalNurtureMinisterService calNurtureMinisterService;
	@Autowired
	private PartnershipCommissionSetService partnershipCommissionSetService;
	@Autowired
    private SalerMonthlyRelationService salerMonthlyRelationService;
	@Autowired
    private SettlementPolicyService settlementPolicyService;
	@Autowired
    private InsuranceSalesInfoService insuranceSalesInfoService;


	@Test
	public void executeInternal() throws JobExecutionException {
		logger.info("佣金明细计算定时器开始执行======");
		try {
		    logger.info("首先判断员工异动定时器是否执行");
             String lastMonth = DateUtil.format(DateUtil.offsetMonth(DateUtil.date(), -1), "yyyy-MM");
            HashMap<String, Object> map = new HashMap<>();
            map.put("relationMonth", lastMonth);
            List<Map<String, Object>> salerRelationByMoth = salerMonthlyRelationService.getSalerRelationByMoth(map);
            if (CollUtil.isEmpty(salerRelationByMoth)){
                logger.error("上个月的员工关系未生成，请先执行员工关系异动定时器========");
                return ;
            }
            logger.info("**********************第一步获取员工信息************************");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("lastMonth", DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), "yyyy-MM-dd"));
            List<Map<String,Object>> calCommSales = insuranceSalesInfoService.querySalesForCalCommission(params);
            logger.info("************第二步 计算初年度FYC********************");
            if (CollUtil.isNotEmpty(calCommSales)){
                List<List<?>> calCommSalesList = PublicUtil.splitList(calCommSales, 500);
                logger.info("分批计算初年度FYC共"+calCommSalesList.size()+"批");
                calCommSalesList.forEach(list->{
                    //需要新增的佣金明细
                    List<SalesDaysCommission> salesDaysCommission_add_list = new ArrayList<>();
                     //需要修改的佣金明细
                    List<SalesDaysCommission> salesDaysCommission_update_list = new ArrayList<>();
                     //记录所有的已结算的 列表id
                    List<Long> settlement_policy_id_list = new ArrayList<>();
                    List<Map<String,Object>> calCommSaleBatch = (List<Map<String,Object>>) list;
                    for (Map<String,Object> saleMap:calCommSaleBatch){
                        calculationCommission(saleMap,salesDaysCommission_update_list, salesDaysCommission_add_list, settlement_policy_id_list);
                    }
                     logger.info("**********************第三步 保存或修改算出来的初年度FYC明细并修改结算列表状态放到一个事务中************************");
                    salesDaysCommissionService.handleFYC(salesDaysCommission_add_list, salesDaysCommission_update_list, settlement_policy_id_list);
                });
            }


            //第四步 计算增员，育成，直辖组，直辖部
            logger.info("**********************第四步 计算增员，育成，直辖组，直辖部************************");
            if (CollUtil.isNotEmpty(calCommSales)) {
                List<List<?>> calCommSalesList = PublicUtil.splitList(calCommSales, 500);
                logger.info("分批计算增员，育成，直辖组，直辖部共"+calCommSalesList.size()+"批");
                for (List<?> list: calCommSalesList ) {
                     List<SalesDaysCommission> update_model_list = new ArrayList<>();
                     List<Map<String,Object>> calCommSaleBatch = (List<Map<String,Object>>) list;
                    for (Map<String,Object> saleMap:calCommSaleBatch) {
                        //TODO:区分上月还是本月的
                        //本月
                        String this_month = DateUtil.format(DateUtil.date(), "yyyy-MM");
                        //上个月
                        String last_month = DateUtil.format(DateUtil.offsetMonth(DateUtil.date(), -1), "yyyy-MM");
                        //计算结算月为上个月的增员，育成，直辖组，直辖部
                        calculationZYYCZX(last_month, update_model_list, saleMap);
                        //计算结算月为本月的增员，育成，直辖组，直辖部
                        calculationZYYCZX(this_month, update_model_list, saleMap);
                    }
                    //把需要修改的佣金明细 整合到update_model_list中 进行修改
                    salesDaysCommissionService.updateSalesDaysCommission(update_model_list);
                }
            }
        } catch (Exception e) {
			logger.error("佣金计算定时器执行出异常:"+e.getMessage(),e);
			e.printStackTrace();
		}
		logger.info("佣金计算定时器执行结束======");
	}
	/**
	 *@描述 计算本月和上月的增员,处育成,部育成,直属组,直辖部
	 *@创建人 qin lina
	 *@创建时间 2020/4/28
	 */

    public void calculationZYYCZX(String month,  List<SalesDaysCommission>  update_model_list, Map<String,Object> saleMap) throws Exception {
               //增员津贴
                BigDecimal zyjt = calculationZYJT(saleMap,month);

                //处育成
                BigDecimal czycj = calculationCZYCJ(saleMap,month);

                //部育成
                BigDecimal bycj =  calculationBYCJ(saleMap,month);

		        //直辖组管理津贴
                BigDecimal zxzgljt = calculationZXZGLJT(saleMap,month);

                //直辖部管理津贴
                BigDecimal zxbgljt = calculationZXBGLJT(saleMap,month);


                zyjt = NumberFormat.bigDecimalDownTwoDecimal(zyjt);
                zxzgljt = NumberFormat.bigDecimalDownTwoDecimal(zxzgljt);
                czycj = NumberFormat.bigDecimalDownTwoDecimal(czycj);
                zxbgljt = NumberFormat.bigDecimalDownTwoDecimal(zxbgljt);
                bycj = NumberFormat.bigDecimalDownTwoDecimal(bycj);

                HashMap<String, Object> map = new HashMap<>();
                map.clear();
                map.put("salerId", saleMap.get("saler_id"));
                map.put("settlementMonth", month);
                List<SalesDaysCommission> salesDaysCommissionList = salesDaysCommissionService.findSaleCommission(map);
                if (CollUtil.isEmpty(salesDaysCommissionList)){
                    throw new RuntimeException("销售人员+"+saleMap.get("INSURANCE_SALER_NO")+"结算月："+month+"佣金明细不存在");
                }
                SalesDaysCommission salesDaysCommission = salesDaysCommissionList.get(0);
                salesDaysCommission.setIncreaseAllowance(zyjt);
                salesDaysCommission.setGroupRunAllowance(zxzgljt);
                salesDaysCommission.setDirectorCultureAward(czycj);
                salesDaysCommission.setDepRunAllowance(zxbgljt);
                salesDaysCommission.setMinisterCultureAward(bycj);
                BigDecimal cndyj = salesDaysCommission.getInitialAnnualCommission();
                BigDecimal zhanyejt = salesDaysCommission.getExhibitionAllowance();
                BigDecimal total_commission = cndyj.add(zhanyejt).add(zyjt).add(zxzgljt).add(czycj).add(zxbgljt).add(bycj);
                total_commission = NumberFormat.bigDecimalDownTwoDecimal(total_commission);
                salesDaysCommission.setTotalCommission(total_commission);
                update_model_list.add(salesDaysCommission);
    }

    /**
	 *@描述  计算本月和上月的初年度FYC和展业津贴
	 *@创建人 qin lina
	 *@创建时间 2020/4/28
	 */
	public void calculationCommission( Map<String,Object> saleMap,List<SalesDaysCommission> salesDaysCommission_update_list ,List<SalesDaysCommission> salesDaysCommission_add_list
            ,List<Long> settlement_policy_id_list){
		Map<String, Object> params = new HashMap<String, Object>();
		try {
		    //本月
		    String this_month = DateUtil.format(DateUtil.date(), "yyyy-MM");
		    //上个月
		    String last_month = DateUtil.format(DateUtil.offsetMonth(DateUtil.date(), -1), "yyyy-MM");

			/*查询审核通过的未结算的上个月保单*/
			params.put("salerId",saleMap.get("saler_id").toString());
			//审核通过
			params.put("auditStatus",Constants.AUDIT_STATUS_1);
			//未结算
			params.put("settlementStatus",Constants.SETTLEMENT_STATUS_0);

			//TODO:从已审核可计算列表中获取结算月为上月和本月的未结算的保单信息（需要的数据为规模保费，结算月，产品编码，销售组织机构）
			//结算月为上月
			params.put("settlementMonth",last_month);
			List<Object> last_commissionData = salesDaysCommissionService.findSettleableMapList(params);

			//结算月为本月
			params.put("settlementMonth",this_month);
			List<Object> this_commissionData = salesDaysCommissionService.findSettleableMapList(params);
			//查询组织机构获取展业津贴系数
            params.clear();
            params.put("orgId",saleMap.get("cal_use_org_id"));
            List<CalPromote> zyjtxs = calPromoteSerivce.getCalPromoteByOrgId(params);
			//TODO: 计算上个月和本月的初年度FYC和展业津贴
            //上个月的 初年度FYC和展业津贴
            monthlycalculationCommission(last_month,last_commissionData,saleMap,zyjtxs,salesDaysCommission_update_list,salesDaysCommission_add_list);
            //本月的  初年度FYC和展业津贴
            monthlycalculationCommission(this_month,this_commissionData,saleMap,zyjtxs,salesDaysCommission_update_list,salesDaysCommission_add_list);
            //将已结算的 数据id  记录到list中
            last_commissionData.forEach(o -> {
                settlement_policy_id_list.add((Long)JSONUtil.parseObj(o).get("id"));
            });
            this_commissionData.forEach(o -> {
                settlement_policy_id_list.add((Long)JSONUtil.parseObj(o).get("id"));
            });


		} catch (Exception e) {
		    logger.error("计算本月和上月的初年度FYC和展业津贴异常"+e.getMessage(),e);
		}

	}

	/**
	 *@描述  以结算月为维度计算初年度FYC和展业津贴
	 *@创建人 qin lina
	 *@创建时间 2020/4/28
	 */
    private void monthlycalculationCommission(String month, List<Object> commissionData,Map<String,Object> saleMap, List<CalPromote> zyjts
            ,List<SalesDaysCommission> salesDaysCommission_update_list ,List<SalesDaysCommission> salesDaysCommission_add_list) {
            SalesDaysCommission sdc = null;

           //新增的初年度FYC
			BigDecimal add_cndyj = BigDecimal.valueOf(0.00);
			//本月度的初年度FYC
			BigDecimal cndyj = BigDecimal.valueOf(0.00);
			//展业津贴
			BigDecimal zhyjt = BigDecimal.valueOf(0.00);
			//定义一个参数  标注新增还是修改
            boolean update_flag = true;
            //封装计算FYC参数
			Map<String, Object> par =new HashMap<String,Object>();
                for (Object ocd : commissionData) {
                    // TODO:基础佣金率*规模保费  注意考虑产品份数为多份的情况
                    //规模保费
                    BigDecimal totalPremium = new BigDecimal(net.sf.json.JSONObject.fromObject(ocd).get("PREMIUM") + "");//规模保费
                    System.out.println(totalPremium);
                    //TODO:根据组织机构和产品获取基础佣金率
                    par.put("product_code", JSONUtil.parseObj(ocd).get("PRODUCTS_CODE"));
                    par.put("orgid", saleMap.get("SALES_ORG_ID"));
                    Map<String, Object> rate = partnershipCommissionSetService.selectCommissionRate(par);
                    BigDecimal commissionRate = BigDecimal.ZERO;
                    if (ObjectUtil.isNotEmpty(rate.get("CommissionRate"))) {
                        commissionRate = new BigDecimal(rate.get("CommissionRate").toString());
                    } else {
                        logger.error("产品编号：" + JSONUtil.parseObj(ocd).get("PRODUCTS_CODE") + ",在组织机构：" + saleMap.get("SALES_ORG_ID") +"下未设置基础佣金率");
                    }
                    //初年度佣金FYC = 规模保费 * 基础佣金率
                    add_cndyj = add_cndyj.add(totalPremium.multiply(commissionRate).divide(BigDecimal.valueOf(100.00)));
                }
                //TODO 从佣金明细中查找 该员工上月的佣金明细是否存在
                par.clear();
                par.put("salerId", saleMap.get("saler_id"));
                par.put("settlementMonth", month);
                List<SalesDaysCommission> salesDaysCommissionList = salesDaysCommissionService.findSaleCommission(par);
                //如果不存在则新建
                if (CollUtil.isEmpty(salesDaysCommissionList)) {
                    update_flag = false;
                    cndyj = add_cndyj;
                } else {
                    cndyj = salesDaysCommissionList.get(0).getInitialAnnualCommission().add(add_cndyj);
                }
               cndyj =  NumberFormat.bigDecimalDownTwoDecimal(cndyj);
                //计算展业津贴
                zhyjt = CalPromoteFormula.promoteFormula(cndyj, zyjts);
              //如果明细中已存在  则只需修改初年度fyc和展业津贴
                if (update_flag) {
                    sdc = salesDaysCommissionList.get(0);
                    sdc.setInitialAnnualCommission(cndyj);
                    sdc.setSalerFyc(cndyj);
                    sdc.setExhibitionAllowance(NumberFormat.bigDecimalDownTwoDecimal(zhyjt));
                    salesDaysCommission_update_list.add(sdc);
                } else {
                    sdc = new SalesDaysCommission();
                     sdc.setSalerId(Long.valueOf(saleMap.get("saler_id").toString()));
                    if (DateUtil.format(DateUtil.date(), "yyyy-MM").equals(month)) {
                        sdc.setSalesOrgId(Long.valueOf(saleMap.get("SALES_ORG_ID").toString()));
                        sdc.setSalesTeamId(Long.valueOf(saleMap.get("SALES_TEAM_ID").toString()));
                        sdc.setSalerGrade(Long.valueOf(saleMap.get("SALES_GRADE_ID").toString()));
                    }else {
                        sdc.setSalesOrgId(Long.valueOf(saleMap.get("PRE_ORG_ID").toString()));
                        sdc.setSalesTeamId(Long.valueOf(saleMap.get("PRE_TEAM_ID").toString()));
                        sdc.setSalerGrade(Long.valueOf(saleMap.get("PRE_SALES_GRADE_ID").toString()));
                    }
                    //初年度佣金FYC
                    sdc.setInitialAnnualCommission(cndyj);
                    //初年度佣金FYC 这个是总价值佣金
                    sdc.setSalerFyc(cndyj);
                    //展业津贴
                    sdc.setExhibitionAllowance(NumberFormat.bigDecimalDownTwoDecimal(zhyjt));
                    //结算月
                    sdc.setSettlementMonth(month);
                    //是否发放
                    sdc.setSettlementStatus(Constants.SETTLEMENT_STATUS_0);
                    salesDaysCommission_add_list.add(sdc);
                }



    }

    /**
     *@描述  增员津贴   ∑（被推荐人的首年FYC*增员津贴系数）
     *@创建人 qin lina
     *@创建时间 2020/4/30
     */
	public BigDecimal calculationZYJT( Map<String,Object> saleMap,String month){
        Map<String, Object> par = new HashMap<>();
        par.put("orgId",saleMap.get("cal_use_org_id"));
        List<CalIncreaseStaff> zyjtxs =  calIncreaseStaffService.getCalIncreaseStaffByOrgId(par);

		/*计算增员津贴*///--所有人员的相关数据计算完毕后计算
        Map<String, Object> inFycMap = calculationFYC(saleMap, month, CalParamRuleEnum.PARAM_IN.getCode());
        String salerIds = (String) inFycMap.get("salerIds");
        BigDecimal total_FYC = BigDecimal.ZERO;
        if(!salerIds.equals("")) {
            //增员总价值佣金
            par.clear();
            par.put("salerIds", salerIds);
            par.put("month", month);
            List<BigDecimal> addFYCOne = salesDaysCommissionService.sumSalesFYCByMonthForEveryOne(par);
            if (null == addFYCOne ){
                return total_FYC;
            }
            for (BigDecimal addFYC : addFYCOne) {
                BigDecimal zyjt = CalIncreaseStaffFormula.increaseStaffFormula(addFYC,zyjtxs);
                total_FYC = total_FYC.add(zyjt);
            }
        }

		return total_FYC;
	}




	/**
	 *@描述 处长育成金  ∑一代被育成处FYC（含本人）总和*处一代育成津贴系数+∑二代被育成处FYC (含本人)总和*处二代育成津贴系数
	 *@创建人 qin lina
	 *@创建时间 2020/4/29
	 */
	public BigDecimal calculationCZYCJ(Map<String,Object> saleMap,String month) throws Exception{
		BigDecimal czycj = BigDecimal.valueOf(0.00);
		Map<String, Object> paras = new HashMap<>();
		paras.put("orgId",saleMap.get("cal_use_org_id"));
		List<CalNurtureDirector> cycxs = calNurtureDirectorService.getCalNurtureDirectorByOrgId(paras);

		//一代处育成金
		BigDecimal firstczycj =calculationCYCJByType(saleMap,month,Constants.SENIORITY_TYPE_1,cycxs);
		//二代处育成金
		BigDecimal sencondczycj =calculationCYCJByType(saleMap,month,Constants.SENIORITY_TYPE_2,cycxs);
        czycj = firstczycj.add(sencondczycj);

		return czycj;
	}

	  /**
	   *@描述 部育成 育成部津贴=∑一代被育成部FYC（含本人）总和*部一代育成津贴系数+∑二代被育成部FYC (含本人)总和*部二代育成津贴系数
	   *@创建人 qin lina
	   *@创建时间 2020/4/30
	   */
	public BigDecimal calculationBYCJ(Map<String,Object> saleMap,String month) throws Exception{
		BigDecimal bycj = BigDecimal.valueOf(0.00);
        Map<String, Object> map = new HashMap<>();
        map.put("orgId",saleMap.get("cal_use_org_id"));
       List<CalNurtureMinister> bycxs = calNurtureMinisterService.getCalNurtureMinisterByOrgId(map);
        //一代部育成金
		BigDecimal firstczycj =calculationBYCJByType(saleMap,month,Constants.SENIORITY_TYPE_1,bycxs);
		//二代部育成津
		BigDecimal sencondczycj =calculationBYCJByType(saleMap,month,Constants.SENIORITY_TYPE_2,bycxs);
        bycj = firstczycj.add(sencondczycj);

		return bycj;
	}

    /**
     * 处育成津贴
     * @param saleMap
     * @param month
     * @param year
     * @return
     * @throws ParseException
     */
    private BigDecimal calculationCYCJByType(Map<String,Object> saleMap, String month, String seniorityType,List<CalNurtureDirector> cycxs) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        BigDecimal czycj = BigDecimal.valueOf(0.00);
        Map<String, Object> map = new HashMap<>();
        //TODO: 根据结算月 查人员关系表  找到结算月的人员关系 查询被育成人
          List<Map<String,Object>> ycSalesIdList = null;
        if (DateUtil.format(DateUtil.date(), "yyyy-MM").equals(month)) {
            if (Constants.SENIORITY_TYPE_1.equals(seniorityType)) {
                 map.put("ycCFirstGener", saleMap.get("saler_id"));
            }else {
                 map.put("ycCSecondGener", saleMap.get("saler_id"));
            }
            //查询 销售人员表 获取目前的关系
            ycSalesIdList = salesDaysCommissionService.getSalerRelation(map);
        } else {
            map.clear();
            if (Constants.SENIORITY_TYPE_1.equals(seniorityType)) {
                  map.put("ycCFirstGener", saleMap.get("saler_id"));
            }else {
                 map.put("ycCSecondGener", saleMap.get("saler_id"));
            }
            map.put("relationMonth", month);
            ycSalesIdList = salerMonthlyRelationService.getSalerRelationByMoth(map);
        }

        //找到了被育成人 找到各个被育成人的直辖组（因为本人包含在直辖组中 所以找到不用单独计算这个被育成人）
        if (ycSalesIdList != null && ycSalesIdList.size() > 0) {
           //计算每一个育成组的育成津贴
            for (Map<String,Object> ycSale : ycSalesIdList) {
                //定义每个育成组的FYC
                 //定义从每个育成组得到的育成津贴
                BigDecimal cycjtForOne = BigDecimal.valueOf(0.00);
                List<Map<String,Object>> yczFirstSalesIdList = null;
                Map<String, Object> yczxcFycMap = calculationFYC(ycSale, month, CalParamRuleEnum.PARAM_D_GROUP.getCode());
                BigDecimal yczxcFyc = (BigDecimal) yczxcFycMap.get("gxrFyc");
                if(yczxcFyc.compareTo(BigDecimal.ONE) > 0) {
                    //计算出 育成组FYC
                    //判断育成关系确立时间
                   String date = "";
                    if (Constants.SENIORITY_TYPE_1.equals(seniorityType)){
                         date = ycSale.get("establish_time").toString();
                    }else {
                         date = ycSale.get("establish_time1").toString();
                    }
                    DateTime parse = DateUtil.parse(date, "yyyy-MM");
                    //设置育成确立时间为当前日期，增加一年
                    calendar.setTime(parse);
                    calendar.add(Calendar.YEAR, 1);
                    Date timeAddYear = calendar.getTime();
                    //比较新增一年后的日期和结算月日期比较
                    DateTime nowDate = DateUtil.parse(month, "yyyy-MM");

                    //判读是第一年还是第二年
                    if (timeAddYear.getTime() > nowDate.getTime()) {
                        cycjtForOne = CalNurtureDirectorFormula.nurtureDirectorFormula(yczxcFyc,seniorityType, Constants.YC_YEAR_1, cycxs);
                    } else {
                       cycjtForOne = CalNurtureDirectorFormula.nurtureDirectorFormula(yczxcFyc,seniorityType,Constants.YC_YEAR_2,cycxs);
                    }
                    czycj = czycj.add(cycjtForOne);
                }
            }

        }
         return czycj;
    }

    /**
     * 部育成津贴
     * @param saleMap
     * @param month
     * @param seniorityType
     * @param bycxs
     * @return
     * @throws ParseException
     */
     private BigDecimal calculationBYCJByType(Map<String,Object> saleMap, String month, String seniorityType,List<CalNurtureMinister> bycxs) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        BigDecimal bzycj = BigDecimal.valueOf(0.00);
        Map<String, Object> map = new HashMap<>();
        //TODO: 根据结算月 查人员关系表  找到结算月的人员关系 查询被育成人
          List<Map<String,Object>> bycSalesIdList = null;
        if (DateUtil.format(DateUtil.date(), "yyyy-MM").equals(month)) {
            if (Constants.SENIORITY_TYPE_1.equals(seniorityType)){
                map.put("ycBFirstGener", saleMap.get("saler_id"));
            }else {
                map.put("ycBSecondGener", saleMap.get("saler_id"));
            }

            //查询 销售人员表 获取目前的关系
            bycSalesIdList = salesDaysCommissionService.getSalerRelation(map);
        } else {
            map.clear();
             if (Constants.SENIORITY_TYPE_1.equals(seniorityType)){
                  map.put("ycBFirstGener", saleMap.get("saler_id"));
            }else {
                  map.put("ycBSecondGener", saleMap.get("saler_id"));
             }
            map.put("relationMonth", month);
            bycSalesIdList = salerMonthlyRelationService.getSalerRelationByMoth(map);
        }

        //找到了被育成人 找到各个被育成人的直辖部（因含在直辖组中 所以找到不用单独计算这个被育成人）
        if (bycSalesIdList != null && bycSalesIdList.size() > 0) {
           //计算每一个育成组的育成津贴
            for (Map<String,Object> bycSale : bycSalesIdList) {
                 //定义从每隔育成组获取到的FYC
                BigDecimal bycjtForOne = BigDecimal.valueOf(0.00);
              //每个育成组的FYC
                Map<String, Object> yczxbFycMap = calculationFYC(bycSale, month, CalParamRuleEnum.PARAM_D_DEPT.getCode());
                BigDecimal yczxbFyc = (BigDecimal) yczxbFycMap.get("gxrFyc");
                if(yczxbFyc.compareTo(BigDecimal.ONE) > 0) {
                    //判断育成关系确立时间
                    String date = "";
                    if (Constants.SENIORITY_TYPE_1.equals(seniorityType)){
                        date = bycSale.get("establish_time2").toString();

                    }else {
                        date = bycSale.get("establish_time3").toString();
                    }
                    DateTime parse = DateUtil.parse(date, "yyyy-MM");
                    //设置育成确立时间为当前日期，增加一年
                    calendar.setTime(parse);
                    calendar.add(Calendar.YEAR, 1);
                    Date timeAddYear = calendar.getTime();
                    //比较新增一年后的日期和结算月日期比较
                    DateTime nowDate = DateUtil.parse(month, "yyyy-MM");

                    //判读部育成第一年还是第二年
                    if (timeAddYear.getTime() > nowDate.getTime()) {
                        bycjtForOne = CalNurtureMinisterFormula.nurtureMinisterFormula(yczxbFyc,seniorityType, Constants.YC_YEAR_1, bycxs);
                    } else {
                       bycjtForOne = CalNurtureMinisterFormula.nurtureMinisterFormula(yczxbFyc,seniorityType,Constants.YC_YEAR_2,bycxs);
                    }
                    bzycj = bzycj.add(bycjtForOne);
                }
            }

        }
         return bzycj;
    }



	/**
	 *@描述  直辖组管理津贴   （∑直辖组成员首年FYC）*直辖组津贴系数
	 *@创建人 qin lina
	 *@创建时间 2020/4/30
	 */
	public BigDecimal calculationZXZGLJT(Map<String,Object> saleMap,String month){
	    BigDecimal zxzgljt = BigDecimal.valueOf(0.00);
	    int manpower = 0;
	    //直辖组 山西和江苏排除部长直辖的直辖组，放在部管理津贴处计算
        if (Constants.EXCLUDE_DIRECTLY_GROUP_FLAG_1.equals(saleMap.get("exclude_directly_group_flag"))){
            //计算本月 并且本月的职级是部长级 则排除
            if ( DateUtil.format(DateUtil.date(), "yyyy-MM").equals(month)){
                if (saleMap.get("SALES_GRADE_ID").toString().equals(Constants.SALES_GRADE_NAME_2)){
                    return zxzgljt;
                }
            }else {
                if (saleMap.get("PRE_SALES_GRADE_ID").toString().equals(Constants.SALES_GRADE_NAME_2)){
                     return zxzgljt;
                }

            }
        }

		/*直辖组管理津贴系数*/
        Map<String, Object> par = new HashMap<>();
        par.put("orgId",saleMap.get("cal_use_org_id"));
        List<CalDirectlyUnderGroup> zxzglxs = calDirectlyUnderGroupService.getCalDirectlyUnderGroupByOrgId(par);

        // 计算关系人和关系人的fyc
        Map<String, Object> zxzfycMap = calculationFYC(saleMap, month, CalParamRuleEnum.PARAM_D_GROUP.getCode());
        String salerIds = (String) zxzfycMap.get("salerIds");
        //直辖组总价值佣金
        BigDecimal zxzFYC = (BigDecimal) zxzfycMap.get("gxrFyc");

        if(StrUtil.isNotBlank(salerIds)){
            if (Constants.ACTIVE_SALER_FLAG_1.equals(saleMap.get("active_saler_flag"))){
               par.clear();
               par.put("salerIds",salerIds);
               par.put("settlementMonth",month);
               par.put("auditStatus",Constants.AUDIT_STATUS_1);
               //TODO:组活动人力数 = 当月结算月保单、保单产品长短线标记为长期险
               List<String> manpowerList =  salesDaysCommissionService.getGroupManpowerByMonth(par);
               if (CollUtil.isNotEmpty(manpowerList)){
                   manpower = manpowerList.size();
                    logger.info("销售人员id"+saleMap.get("saler_id")+"的组活动人力数为"+manpower);
               }
            }
            zxzgljt = CalDirectlyUnderGroupFormula.directlyUnderGroupFormula(zxzFYC, manpower, zxzglxs);
        }

		return zxzgljt;
	}


	/**
	 *@描述  直辖部津贴  （∑直辖部成员首年FYC）*直辖部津贴系数
	 *@创建人 qin lina
	 *@创建时间 2020/4/30
	 */
	public BigDecimal calculationZXBGLJT(Map<String,Object> saleMap,String month){
        Map<String, Object> map = new HashMap<>();
        map.put("orgId",saleMap.get("cal_use_org_id"));
        List<CalDirectlyUnderDept> zxbglxs = calDirectlyUnderDeptService.getCalDirectlyUnderDeptByOrgId(map);
        BigDecimal zxbgljt = BigDecimal.valueOf(0.00);
        BigDecimal zxcgljt = BigDecimal.valueOf(0.00);
        Map<String, Object> zxbFycMap = calculationFYC(saleMap, month, CalParamRuleEnum.PARAM_D_DEPT.getCode());
        BigDecimal zxbFyc = (BigDecimal) zxbFycMap.get("gxrFyc");
        BigDecimal zxcFyc = BigDecimal.ZERO;
        if (zxbFyc.compareTo(BigDecimal.ONE) > 0){
            //如果包含直辖组 则说明是山西或江苏的 基本法 需要单独在此处计算 直辖组津贴
             if (Constants.INCLUDE_DIRECTLY_GROUP_FLAG_1.equals(saleMap.get("include_directly_group_flag"))) {
                if (DateUtil.format(DateUtil.date(), "yyyy-MM").equals(month)){
                    if (saleMap.get("SALES_GRADE_ID").toString().equals(Constants.SALES_GRADE_NAME_2)){
                         Map<String, Object> zxcFycMap = calculationFYC(saleMap, month, CalParamRuleEnum.PARAM_D_GROUP.getCode());
                      zxcFyc = (BigDecimal) zxcFycMap.get("gxrFyc");
                     zxcgljt = CalDirectlyUnderDeptFormula.directlyUnderDeptFormula(zxcFyc, Constants.INCLUDE_DIRECTLY_GROUP_FLAG_1, zxbglxs);
                    }
                }else {
                    if (saleMap.get("PRE_SALES_GRADE_ID").toString().equals(Constants.SALES_GRADE_NAME_2)){
                         Map<String, Object> zxcFycMap = calculationFYC(saleMap, month, CalParamRuleEnum.PARAM_D_GROUP.getCode());
                      zxcFyc = (BigDecimal) zxcFycMap.get("gxrFyc");
                     zxcgljt = CalDirectlyUnderDeptFormula.directlyUnderDeptFormula(zxcFyc, Constants.INCLUDE_DIRECTLY_GROUP_FLAG_1, zxbglxs);
                    }
                }

             }
            zxbFyc = zxbFyc.subtract(zxcFyc);
            zxbgljt = CalDirectlyUnderDeptFormula.directlyUnderDeptFormula(zxbFyc, Constants.INCLUDE_DIRECTLY_GROUP_FLAG_0, zxbglxs);

        }
        return zxbgljt.add(zxcgljt);
	}

    /**
     *  计算关系人增员，直辖部，直辖处 FYC
     */
    private Map<String,Object> calculationFYC(Map<String,Object> saleMap,String month,String paramType){
         Map<String, Object> map = new HashMap<>();
	    String salerIds = "";
        List<Map<String,Object>> gxrSalesList = null;
        BigDecimal gxrFyc = BigDecimal.valueOf(0.00);
        if (CalParamRuleEnum.PARAM_IN.getCode().equals(paramType)){
            map.put("tjSalesId", saleMap.get("saler_id"));
        } else if (CalParamRuleEnum.PARAM_D_GROUP.getCode().equals(paramType)){
             map.put("directGroupC", saleMap.get("saler_id"));
        } else if (CalParamRuleEnum.PARAM_D_DEPT.getCode().equals(paramType)){
             map.put("directDeptB", saleMap.get("saler_id"));
        }

        if (DateUtil.format(DateUtil.date(), "yyyy-MM").equals(month)) {
            gxrSalesList = salesDaysCommissionService.getSalerRelation(map);
        } else {
            map.put("relationMonth", month);
            gxrSalesList = salerMonthlyRelationService.getSalerRelationByMoth(map);
        }

        if (CollUtil.isNotEmpty(gxrSalesList)) {
            for (Map<String,Object> gxbsale : gxrSalesList) {
                   salerIds = salerIds + gxbsale.get("saler_id") + ",";
            }
        }
        if (salerIds.endsWith(",")) {
            salerIds = salerIds.substring(0, salerIds.length() - 1);
        }

        if (StrUtil.isNotBlank(salerIds)) {
            map.clear();
            map.put("salerIds", salerIds);
            map.put("month", month);
            gxrFyc = salesDaysCommissionService.sumSalesFYCByMonth(map);

        }
        map.clear();
        map.put("salerIds",salerIds);
        map.put("gxrFyc",gxrFyc);
        return map;
    }

    public static void main(String[] args) {
        Calendar calendar =Calendar.getInstance();
        DateTime parse = DateUtil.parse("2019-10-31", "yyyy-MM");
                    //设置育成确立时间为当前日期，增加一年
                    calendar.setTime(parse);
                    calendar.add(Calendar.YEAR, 1);
                    Date timeAddYear = calendar.getTime();
                    //比较新增一年后的日期和结算月日期比较
                    DateTime nowDate = DateUtil.parse("2020-10", "yyyy-MM");
                    if (timeAddYear.getTime() > nowDate.getTime()){
                        System.out.println("还是第一年");
                    }else {
                        System.out.println("已经是第二年");
                    }
    }




}