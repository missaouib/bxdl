package com.hzcf.plantform.controller.Insurance;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.constants.Constants;
import com.hzcf.plantform.feign.insurance.SalesDaysCommissionClient;
import com.hzcf.plantform.feign.parameter.DirectlyAdministeredGroupClient;
import com.hzcf.plantform.feign.parameter.DirectlyUnderManagerClient;
import com.hzcf.plantform.feign.parameter.DirectorNurtureBonusClient;
import com.hzcf.plantform.feign.parameter.ExhibitionAllowanceClient;
import com.hzcf.plantform.feign.parameter.IncreaseAllowanceClient;
import com.hzcf.plantform.feign.parameter.MinisterNurturingBonusClient;
import com.hzcf.plantform.util.NumberFormat;
import com.hzcf.pojo.insurance.sales.DirectorAllowanceStandard;
import com.hzcf.pojo.insurance.sales.InsSalesPreGrade;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.insurance.sales.SalesMonthlyCommission;
import com.hzcf.pojo.parameter.DirectlyAdministeredGroup;
import com.hzcf.pojo.parameter.DirectlyUnderManager;
import com.hzcf.pojo.parameter.DirectorNurtureBonus;
import com.hzcf.pojo.parameter.ExhibitionAllowance;
import com.hzcf.pojo.parameter.IncreaseAllowance;
import com.hzcf.pojo.parameter.MinisterNurturingBonus;
import com.hzcf.util.StringUtil;

/**
 * 职级管理
 * @author yl
 *
 */
@Component
public class SalesDaysCommissionTask {

    @Autowired
    private SalesDaysCommissionClient salesDaysCommissionClient; 
    @Autowired
    private ExhibitionAllowanceClient exhibitionAllowanceClient;
    @Autowired
    private IncreaseAllowanceClient increaseAllowanceClient;
    @Autowired
    private DirectlyAdministeredGroupClient directlyAdministeredGroupClient;
    @Autowired
    private DirectlyUnderManagerClient directlyUnderManagerClient;
	@Autowired
	private DirectorNurtureBonusClient directorNurtureBonusClient;
	@Autowired
	private MinisterNurturingBonusClient ministerNurturingBonusClient;
	
	/**
	 * 添加
	 * @param(salesDaysCommission)
	 * @return
	 */
	//@Scheduled(cron = "0 30 0 * * ?")
	@ResponseBody
	public boolean addSalesDaysCommission(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			if(calendar.get(Calendar.DAY_OF_MONTH) <= 31){			
				List<SalesDaysCommission> salesDaysCommissions = new ArrayList<SalesDaysCommission>();
				Map<String, Object> params = new HashMap<String, Object>();
				/*获取正常员工列表*/
				params.put("effective","effective");
				List<InsSalesPreGrade> sales = salesDaysCommissionClient.insuranceSalesList(params);//试用、正常员工
				//上月离职、黑名单员工
				params.clear();
				String qdStart = "";	 
				String qdEnd = "";
		        calendar.add(Calendar.MONTH,-1);
		        calendar.set(Calendar.DAY_OF_MONTH, 1); 
		        qdStart = format.format(calendar.getTime());//上月第一天
		        params.put("qdStart",qdStart);
		        calendar = Calendar.getInstance();
		        calendar.set(Calendar.DAY_OF_MONTH, 1);
		        qdEnd = format.format(calendar.getTime());//本月第一天
		        params.put("qdEnd",qdEnd);
				List<InsSalesPreGrade> quitSales = salesDaysCommissionClient.quitInsuranceSalesList(params);
				if(quitSales!=null && quitSales.size()>0){
					sales.addAll(quitSales);
				}

				calendar = Calendar.getInstance();
				
				if(sales!=null && sales.size()>0){
					for(InsSalesPreGrade saler:sales){
						SalesDaysCommission sdc = calculationCommission(saler);					
						salesDaysCommissions.add(sdc);
					}
				}						
				salesDaysCommissionClient.addSalesDaysCommission(salesDaysCommissions);
				
				List<SalesMonthlyCommission> smcs = new ArrayList<>();
				
				if(sales!=null && sales.size()>0){
					for(InsSalesPreGrade saler:sales){
						BigDecimal zyjt = calculationZYJT(saler);//增员津贴
						BigDecimal zxzgljt = BigDecimal.valueOf(0.00);//直辖组管理津贴  楚科长外所有职级都有直辖组
						if(!(saler.getPreMonthGrade()==4) && !(saler.getPreMonthGrade()==5)){
							zxzgljt = calculationZXZGLJT(saler);
						}
						BigDecimal czycj = BigDecimal.valueOf(0.00);//处长育成奖 处长育成处长
						if(saler.getPreMonthGrade()==3 || saler.getPreMonthGrade()==6){
							czycj = calculationCZYCJ(saler);
						}
						BigDecimal zxbgljt = BigDecimal.valueOf(0.00);//直辖部管理津贴
						if(saler.getPreMonthGrade()==1 || saler.getPreMonthGrade()==2 || saler.getPreMonthGrade()==7){
							zxbgljt = calculationZXBGLJT(saler);
						}	
						BigDecimal bycj = BigDecimal.valueOf(0.00);//部育成奖
						if(saler.getPreMonthGrade()==1 || saler.getPreMonthGrade()==2 || saler.getPreMonthGrade()==7){
							bycj = calculationBYCJ(saler);
						}
						BigDecimal zjjt = BigDecimal.valueOf(0.00);//总监津贴
						if(saler.getPreMonthGrade()==1){
							zjjt = calculationZJJT(saler);
						}	
						
						zyjt = NumberFormat.bigDecimalDownTwoDecimal(zyjt);
						zxzgljt = NumberFormat.bigDecimalDownTwoDecimal(zxzgljt);
						czycj = NumberFormat.bigDecimalDownTwoDecimal(czycj);
						zxbgljt = NumberFormat.bigDecimalDownTwoDecimal(zxbgljt);
						bycj = NumberFormat.bigDecimalDownTwoDecimal(bycj);
						zjjt = NumberFormat.bigDecimalDownTwoDecimal(zjjt);
						
						for(int i =0 ;i<salesDaysCommissions.size();i++){
							if(salesDaysCommissions.get(i).getSalerId()==saler.getInsuranceSalesId()){
								salesDaysCommissions.get(i).setIncreaseAllowance(zyjt);
								salesDaysCommissions.get(i).setGroupRunAllowance(zxzgljt);
								salesDaysCommissions.get(i).setDirectorCultureAward(czycj);
								salesDaysCommissions.get(i).setDepRunAllowance(zxbgljt);
								salesDaysCommissions.get(i).setMinisterCultureAward(bycj);
								salesDaysCommissions.get(i).setChiefInspectorAllowance(zjjt);
								BigDecimal cndyj = salesDaysCommissions.get(i).getInitialAnnualCommission();
								BigDecimal zhanyejt = salesDaysCommissions.get(i).getExhibitionAllowance();
								BigDecimal xndyj = BigDecimal.valueOf(0.00);
								BigDecimal jxljj = BigDecimal.valueOf(0.00);
								BigDecimal nzj = BigDecimal.valueOf(0.00);
								BigDecimal cqkf = BigDecimal.valueOf(0.00);
								BigDecimal total_commission = cndyj.add(zhanyejt).add(zyjt).add(xndyj).add(jxljj).add(nzj).add(cqkf).add(zxzgljt).add(czycj).add(zxbgljt).add(bycj).add(zjjt);
								total_commission = NumberFormat.bigDecimalDownTwoDecimal(total_commission);
								salesDaysCommissions.get(i).setTotalCommission(total_commission);
								
								if(calendar.get(Calendar.DAY_OF_MONTH) == 16){
									SalesMonthlyCommission smc = new SalesMonthlyCommission();
									smc.setSalerId(salesDaysCommissions.get(i).getSalerId());
									smc.setSalesOrgId(salesDaysCommissions.get(i).getSalesOrgId());
									smc.setSalesTeamId(salesDaysCommissions.get(i).getSalesTeamId());
									smc.setSalerGrade(salesDaysCommissions.get(i).getSalerGrade());
									calendar.set(Calendar.DAY_OF_MONTH, 1);									
									smc.setCommissionMonth(format.format(calendar.getTime()));
									calendar = Calendar.getInstance();
									smc.setTotalCommission(salesDaysCommissions.get(i).getTotalCommission());
									smc.setCutCommission(BigDecimal.valueOf(0.00));
									smc.setCutReason("");
									smc.setFreezingTaxes(BigDecimal.valueOf(0.00));
									smc.setAfterTaxCommission(total_commission.subtract(smc.getCutCommission()).subtract(smc.getFreezingTaxes()));
									smc.setCommissionStatus("0");
									smc.setCheckMark("");
									smc.setInitialAnnualCommission(salesDaysCommissions.get(i).getInitialAnnualCommission());
									smc.setExhibitionAllowance(salesDaysCommissions.get(i).getExhibitionAllowance());
									smc.setIncreaseAllowance(salesDaysCommissions.get(i).getIncreaseAllowance());
									smc.setGroupRunAllowance(salesDaysCommissions.get(i).getGroupRunAllowance());
									smc.setDirectorCultureAward(salesDaysCommissions.get(i).getDirectorCultureAward());
									smc.setDepRunAllowance(salesDaysCommissions.get(i).getDepRunAllowance());
									smc.setMinisterCultureAward(salesDaysCommissions.get(i).getMinisterCultureAward());
									smc.setChiefInspectorAllowance(salesDaysCommissions.get(i).getChiefInspectorAllowance());
									smc.setCreatedTime(new Date());
									smcs.add(smc);
								}
								
								break;
							}
						}
					}
				}				
				salesDaysCommissionClient.updateSalesDaysCommission(salesDaysCommissions);
				
				if(calendar.get(Calendar.DAY_OF_MONTH) == 16){
					salesDaysCommissionClient.addSalesMonthlyCommission(smcs);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public SalesDaysCommission calculationCommission(InsSalesPreGrade saler){
		Map<String, Object> params = new HashMap<String, Object>();
		SalesDaysCommission sdc = new SalesDaysCommission();		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			/*统计周期*/
	        Calendar calendar = Calendar.getInstance();
	        //	        开始时间
			String uwStart = "";	        
	        calendar.add(Calendar.MONTH,-1);
	        calendar.set(Calendar.DAY_OF_MONTH, 1); 
	        uwStart = format.format(calendar.getTime())+" 00:00:00";//上月第一天
	        //	        结束时间
	        calendar = Calendar.getInstance();
	        String uwEnd = "";
	        if(calendar.get(Calendar.DAY_OF_MONTH) > 15){
		        calendar.add(Calendar.MONTH, 0);
		        calendar.set(Calendar.DAY_OF_MONTH, 1); 
		        uwEnd = format.format(calendar.getTime())+" 00:00:00";//当月第一天	        	
	        }else{
		        calendar.add(Calendar.DATE, -15);
		        uwEnd = format.format(calendar.getTime())+" 00:00:00";
	        }

			/*查询统计周期内员工完成的保单*/
			params.put("agentId",saler.getInsuranceSalesId());
			params.put("type","2");
			params.put("uwStart",uwStart);
			params.put("uwEnd",uwEnd);
			params.put("insState", Constants.INSURANCE_POLICY_STATUS_1010);
			List<Object> commissionData = salesDaysCommissionClient.findMapList(params);
			/*获取标保折标系数信息*/
			//List<BenchmarkingDiscountCoefficient> zbxss = benchmarkingDiscountCoefficientClient.getBenchmarkingDiscountCoefficientList();
			/*获取展业津贴信息*/
			List<ExhibitionAllowance> zyjts = exhibitionAllowanceClient.getExhibitionAllowanceList();
			
			BigDecimal cndyj = BigDecimal.valueOf(0.00);
			BigDecimal zjzyj = BigDecimal.valueOf(0.00);
			BigDecimal zhyjt = BigDecimal.valueOf(0.00);
			BigDecimal zyjt = BigDecimal.valueOf(0.00);
			BigDecimal xndyj = BigDecimal.valueOf(0.00);
			BigDecimal jxljj = BigDecimal.valueOf(0.00);
			BigDecimal nzj = BigDecimal.valueOf(0.00);
			BigDecimal cqkf = BigDecimal.valueOf(0.00);
			BigDecimal zxzgljt = BigDecimal.valueOf(0.00);
			BigDecimal czycj = BigDecimal.valueOf(0.00);
			BigDecimal zxbgljt = BigDecimal.valueOf(0.00);
			BigDecimal bycj = BigDecimal.valueOf(0.00);
			BigDecimal zjjt = BigDecimal.valueOf(0.00);
			BigDecimal zhanyejt_Fyc = BigDecimal.valueOf(0.00);
			
			for(Object ocd:commissionData){
				/*计算初年度佣金	初年度佣金=标保佣金+价值佣金
				标保佣金 = 内部标保佣金系数（产品中定义） * 标保折标系数（参数管理） * 规模保费 
				价值佣金FYC = 价值佣金系数（产品中定义）* 标保折标系数（参数管理） * 规模保费*/
				//规模保费
				BigDecimal totalPremium = new BigDecimal(net.sf.json.JSONObject.fromObject(ocd).get("PREMIUM")+"");//规模保费
		        System.out.println(totalPremium);
		        //价值佣金系数
				BigDecimal jzyjxs = (new BigDecimal(net.sf.json.JSONObject.fromObject(ocd).get("VALUE_COMMISSION_COEFFICIENT")+"")).divide(BigDecimal.valueOf(100.00));//价值佣金系数
		        System.out.println(jzyjxs);
		        //内部标保佣金系数
				BigDecimal nbbbyjxs = (new BigDecimal(net.sf.json.JSONObject.fromObject(ocd).get("IN_STANDARD_COMMISSION_COEFFICIENT")+"")).divide(BigDecimal.valueOf(100.00));//内部标保佣金系数
		        System.out.println(nbbbyjxs);
		        //标保折标系数
				BigDecimal bbzbxs = (new BigDecimal(net.sf.json.JSONObject.fromObject(ocd).get("INDEXING_COEFFICIENT")+"")).divide(BigDecimal.valueOf(100.00));
				//初年度佣金FYC = (价值佣金系数+内部标保佣金系数)*规模保费*标保折标系数
				cndyj = cndyj.add((nbbbyjxs.multiply(totalPremium).multiply(bbzbxs).multiply(BigDecimal.valueOf(0.13))).add(jzyjxs.multiply(totalPremium).multiply(bbzbxs).multiply(BigDecimal.valueOf(0.2))));
                //险种类别 区分年金险和健康险
				String insuranceType = net.sf.json.JSONObject.fromObject(ocd).get("INSURANCE_TYPE")+"";
				if(StringUtil.isNotEmpty(insuranceType) && insuranceType.equals("jk001")){
					//健康险= 内部标保佣金系数*规模保费*标保折标系数*0.13+价值佣金系数*规模保费*标保折标系数*0.2
					zhanyejt_Fyc = zhanyejt_Fyc.add((nbbbyjxs.multiply(totalPremium).multiply(bbzbxs).multiply(BigDecimal.valueOf(0.13))).add(jzyjxs.multiply(totalPremium).multiply(bbzbxs).multiply(BigDecimal.valueOf(0.2))));
				}else{
					//年金险 = 价值佣金系数*规模保费*标保折标系数*0.2
					zhanyejt_Fyc = zhanyejt_Fyc.add((jzyjxs.multiply(totalPremium).multiply(bbzbxs)).multiply(BigDecimal.valueOf(0.2)));//总价值佣金
				}
				zjzyj = zhanyejt_Fyc;//总价值佣金
			}
			
			/*计算展业津贴 =总价值佣金对应展业津贴（参数）*/
			for(ExhibitionAllowance ea:zyjts){
				Boolean formula_x = true;
				Boolean formula_y = true;
				if(ea.getMinSign().equals("0")){
					formula_x = (zhanyejt_Fyc.compareTo(new BigDecimal(ea.getMinimum())) < 0);
				}else if(ea.getMinSign().equals("1")){
					formula_x = (zhanyejt_Fyc.compareTo(new BigDecimal(ea.getMinimum())) == 0);									
				}else if(ea.getMinSign().equals("2")){
					formula_x = !(zhanyejt_Fyc.compareTo(new BigDecimal(ea.getMinimum())) > 0);
				}
				if(ea.getMaxSign().equals("0")){
					formula_y = (zhanyejt_Fyc.compareTo(new BigDecimal(ea.getMaximum())) > 0);
				}else if(ea.getMaxSign().equals("1")){
					formula_y = (zhanyejt_Fyc.compareTo(new BigDecimal(ea.getMaximum())) == 0);									
				}else if(ea.getMaxSign().equals("2")){
					formula_y = !(zhanyejt_Fyc.compareTo(new BigDecimal(ea.getMaximum())) < 0);
				}								
				if(formula_x && formula_y){
					zhyjt = new BigDecimal(ea.getAllowance());
					//如果递增
					if(ea.getIsNotIncrease().equals("1")){
						BigDecimal addPart = BigDecimal.valueOf(0.00);
						//增加的部分 =math.floor（（展业津贴FYC-系数上限）/ 步长）* 额外增加津贴
						addPart = new BigDecimal((int)Math.floor(((zhanyejt_Fyc.subtract(new BigDecimal(ea.getMaximum()))).divide(new BigDecimal(ea.getStep()))).doubleValue())).multiply(new BigDecimal(ea.getAdditional()));
						zhyjt = zhyjt.add(addPart);
					}
					break;
				}
			}	
			
			sdc.setSalerId(saler.getInsuranceSalesId());
			sdc.setSalesOrgId(saler.getPreMonthOrg());
			sdc.setSalesTeamId(saler.getPreMonthTeam());
			sdc.setSalerGrade(saler.getPreMonthGrade());
			//初年度佣金FYC 初年度佣金FYC = (价值佣金系数+内部标保佣金系数)*规模保费*标保折标系数
			sdc.setInitialAnnualCommission(NumberFormat.bigDecimalDownTwoDecimal(cndyj));
			//初年度佣金FYC 这个是总价值佣金 区分年金险和健康险
			sdc.setSalerFyc(NumberFormat.bigDecimalDownTwoDecimal(zjzyj));
			//展业津贴
			sdc.setExhibitionAllowance(NumberFormat.bigDecimalDownTwoDecimal(zhyjt));
			//增员津贴
			sdc.setIncreaseAllowance(NumberFormat.bigDecimalDownTwoDecimal(zyjt));
			//续年度佣金
			sdc.setContinuousAllowance(NumberFormat.bigDecimalDownTwoDecimal(xndyj));
			//个人继续率佣金
			sdc.setContinuationRateBonus(NumberFormat.bigDecimalDownTwoDecimal(jxljj));
			//个人年终奖
			sdc.setAnnualBonus(NumberFormat.bigDecimalDownTwoDecimal(nzj));
			//长期客服奖
			sdc.setServiceAward(NumberFormat.bigDecimalDownTwoDecimal(cqkf));
			//直辖组管理津贴
			sdc.setGroupRunAllowance(NumberFormat.bigDecimalDownTwoDecimal(zxzgljt));
			//处长育成
			sdc.setDirectorCultureAward(NumberFormat.bigDecimalDownTwoDecimal(czycj));
			//直辖部管理津贴
			sdc.setDepRunAllowance(NumberFormat.bigDecimalDownTwoDecimal(zxbgljt));
			//部长育成
			sdc.setMinisterCultureAward(NumberFormat.bigDecimalDownTwoDecimal(bycj));
			//总监津贴
			sdc.setChiefInspectorAllowance(NumberFormat.bigDecimalDownTwoDecimal(zjjt));
			sdc.setCreatedTime(new Date());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sdc;		
	}

	/*增员津贴计算*/
	public BigDecimal calculationZYJT(InsSalesPreGrade saler){
		BigDecimal zyjt = BigDecimal.valueOf(0.00);
		
		//增员津贴系数
        List<IncreaseAllowance> zyjtxs = increaseAllowanceClient.getIncreaseAllowanceList();
		
		/*计算增员津贴*///--所有人员的相关数据计算完毕后计算
		List<InsSalesPreGrade> firstAdd = salesDaysCommissionClient.findByTjr(saler.getInsuranceSalesId());//一代增员
		String salerIds = "";
		if(firstAdd!=null && firstAdd.size()>0){
			for(InsSalesPreGrade fsaler:firstAdd){
				salerIds = salerIds + fsaler.getInsuranceSalesId() + ",";
			}
		}
        if (salerIds.endsWith(",")) {
        	salerIds = salerIds.substring(0, salerIds.length() - 1);
        }
        if(!salerIds.equals("")){
        	
	        BigDecimal firstAddFYC = salesDaysCommissionClient.sumSalesFYC(salerIds);//一代增员总价值佣金
	        
			for(IncreaseAllowance ea:zyjtxs){
				if(ea.getSettings().equals("1")){
					Boolean formula_x = true;
					Boolean formula_y = true;
					if(ea.getMinSign().equals("0")){
						formula_x = (firstAddFYC.compareTo(new BigDecimal(ea.getMinimum())) < 0);
					}else if(ea.getMinSign().equals("1")){
						formula_x = (firstAddFYC.compareTo(new BigDecimal(ea.getMinimum())) == 0);									
					}else if(ea.getMinSign().equals("2")){
						formula_x = !(firstAddFYC.compareTo(new BigDecimal(ea.getMinimum())) > 0);
					}
					if(ea.getMaxSign().equals("0")){
						formula_y = (firstAddFYC.compareTo(new BigDecimal(ea.getMaximum())) > 0);
					}else if(ea.getMaxSign().equals("1")){
						formula_y = (firstAddFYC.compareTo(new BigDecimal(ea.getMaximum())) == 0);									
					}else if(ea.getMaxSign().equals("2")){
						formula_y = !(firstAddFYC.compareTo(new BigDecimal(ea.getMaximum())) < 0);
					}								
					if(formula_x && formula_y){
						zyjt = zyjt.add(firstAddFYC.multiply((new BigDecimal(ea.getExhibitionAllowanceProportion())).divide(BigDecimal.valueOf(100.00))));
						break;
					}
				}
			}
	        
	        List<InsSalesPreGrade> secAdd = salesDaysCommissionClient.findByTjrs(salerIds);//二代增员
			String salerIdss = "";
			if(secAdd!=null && secAdd.size()>0){
				for(InsSalesPreGrade ssaler:secAdd){
					salerIdss = salerIdss + ssaler.getInsuranceSalesId() + ",";
				}
			}
	        if(salerIdss.endsWith(",")){
	        	salerIdss = salerIdss.substring(0, salerIdss.length() - 1);
	        }
	        
	        if(!salerIdss.equals("")){
		        BigDecimal secAddFYC = salesDaysCommissionClient.sumSalesFYC(salerIdss);//二代增员总价值佣金
		        
		        for(IncreaseAllowance ea:zyjtxs){
					if(ea.getSettings().equals("2")){
						Boolean formula_x = true;
						Boolean formula_y = true;
						if(ea.getMinSign().equals("0")){
							formula_x = (secAddFYC.compareTo(new BigDecimal(ea.getMinimum())) < 0);
						}else if(ea.getMinSign().equals("1")){
							formula_x = (secAddFYC.compareTo(new BigDecimal(ea.getMinimum())) == 0);									
						}else if(ea.getMinSign().equals("2")){
							formula_x = !(secAddFYC.compareTo(new BigDecimal(ea.getMinimum())) > 0);
						}
						if(ea.getMaxSign().equals("0")){
							formula_y = (secAddFYC.compareTo(new BigDecimal(ea.getMaximum())) > 0);
						}else if(ea.getMaxSign().equals("1")){
							formula_y = (secAddFYC.compareTo(new BigDecimal(ea.getMaximum())) == 0);									
						}else if(ea.getMaxSign().equals("2")){
							formula_y = !(secAddFYC.compareTo(new BigDecimal(ea.getMaximum())) < 0);
						}								
						if(formula_x && formula_y){
							zyjt = zyjt.add(secAddFYC.multiply((new BigDecimal(ea.getExhibitionAllowanceProportion())).divide(BigDecimal.valueOf(100.00))));
							break;
						}
					}
				}
	        }
        }
        
		return zyjt;
	}

	/*直辖组管理津贴*/
	public BigDecimal calculationZXZGLJT(InsSalesPreGrade saler){
		/*直辖组管理津贴系数*/
		List<DirectlyAdministeredGroup> zxzgljtxs = directlyAdministeredGroupClient.getDirectlyAdministeredGroupList();		
		BigDecimal zxzgljt = BigDecimal.valueOf(0.00);
		String salerIds = saler.getInsuranceSalesId()+",";
		int branchNum = salesDaysCommissionClient.getNumBySj(saler.getInsuranceSalesId());
		if(branchNum>0){
			List<InsSalesPreGrade> sales = salesDaysCommissionClient.findBySj(saler.getInsuranceSalesId());
			for(InsSalesPreGrade salertemp:sales){
				salerIds = salerIds + salertemp.getInsuranceSalesId()+",";
				int branchNums = salesDaysCommissionClient.getNumBySj(salertemp.getInsuranceSalesId());
				if(branchNums>0){
					salerIds = findBranchs(salertemp,salerIds);
				}
			}
		}
        if (salerIds.endsWith(",")) {
        	salerIds = salerIds.substring(0, salerIds.length() - 1);
        }
        
        if(!salerIds.equals("")){
			BigDecimal zxzFYC = salesDaysCommissionClient.sumSalesFYC(salerIds);//直辖组总价值佣金（含自己）
			
			for(DirectlyAdministeredGroup ea:zxzgljtxs){
				Boolean formula_x = true;
				Boolean formula_y = true;
				if(ea.getMinSign().equals("0")){
					formula_x = (zxzFYC.compareTo(new BigDecimal(ea.getMinimum())) < 0);
				}else if(ea.getMinSign().equals("1")){
					formula_x = (zxzFYC.compareTo(new BigDecimal(ea.getMinimum())) == 0);									
				}else if(ea.getMinSign().equals("2")){
					formula_x = !(zxzFYC.compareTo(new BigDecimal(ea.getMinimum())) > 0);
				}
				if(ea.getMaxSign().equals("0")){
					formula_y = (zxzFYC.compareTo(new BigDecimal(ea.getMaximum())) > 0);
				}else if(ea.getMaxSign().equals("1")){
					formula_y = (zxzFYC.compareTo(new BigDecimal(ea.getMaximum())) == 0);									
				}else if(ea.getMaxSign().equals("2")){
					formula_y = !(zxzFYC.compareTo(new BigDecimal(ea.getMaximum())) < 0);
				}								
				if(formula_x && formula_y){
					zxzgljt = zxzgljt.add(zxzFYC.multiply((new BigDecimal(ea.getAllowance())).divide(BigDecimal.valueOf(100.00))));
					break;
				}
			}
        }
        
		return zxzgljt;
	}
	
	/*直辖组员工主键串*/
	public String findBranchs(InsSalesPreGrade saler,String salerIds){
		List<InsSalesPreGrade> sales = salesDaysCommissionClient.findBySj(saler.getInsuranceSalesId());
		for(InsSalesPreGrade salertemp:sales){
			salerIds = salerIds + salertemp.getInsuranceSalesId()+",";
			int branchNums = salesDaysCommissionClient.getNumBySj(salertemp.getInsuranceSalesId());
			if(branchNums>0){
				salerIds = findBranchs(salertemp,salerIds);
			}
		}
		return salerIds;
	}
	
	/*直辖部管理津贴*/
	public BigDecimal calculationZXBGLJT(InsSalesPreGrade saler){
		/*直辖部管理津贴系数*/
		List<DirectlyUnderManager> zxbgljtxs = directlyUnderManagerClient.getDirectlyUnderManagerList();		
		BigDecimal zxbgljt = BigDecimal.valueOf(0.00);
		
		String salerIds = saler.getInsuranceSalesId()+",";
		int branchNum = 0;
		if(saler.getPreMonthGrade()==1){
			branchNum = salesDaysCommissionClient.getZJZXBNum(saler.getInsuranceSalesId());
		}else{
			branchNum = salesDaysCommissionClient.getBZZXBNum(saler.getInsuranceSalesId());
		}
		if(branchNum>0){
			List<InsSalesPreGrade> sales = null;
			if(saler.getPreMonthGrade()==1){
				sales = salesDaysCommissionClient.findZJZXB(saler.getInsuranceSalesId());
			}else{
				sales = salesDaysCommissionClient.findBZZXB(saler.getInsuranceSalesId());
			}
			for(InsSalesPreGrade salertemp:sales){
				salerIds = salerIds + salertemp.getInsuranceSalesId()+",";
				int branchNums = 0;
				if(saler.getPreMonthGrade()==1){
					branchNums = salesDaysCommissionClient.getZJZXBNum(saler.getInsuranceSalesId());
				}else{
					branchNums = salesDaysCommissionClient.getBZZXBNum(saler.getInsuranceSalesId());
				}
				if(branchNums>0){
					salerIds = findZXBBranchs(salertemp,salerIds);
				}
			}
		}
        if (salerIds.endsWith(",")) {
        	salerIds = salerIds.substring(0, salerIds.length() - 1);
        }
		BigDecimal zxbFYC = salesDaysCommissionClient.sumSalesFYC(salerIds);//直辖组总价值佣金（含自己）
		
		for(DirectlyUnderManager ea:zxbgljtxs){
			Boolean formula_x = true;
			Boolean formula_y = true;
			if(ea.getMinSign().equals("0")){
				formula_x = (zxbFYC.compareTo(new BigDecimal(ea.getMinimum())) < 0);
			}else if(ea.getMinSign().equals("1")){
				formula_x = (zxbFYC.compareTo(new BigDecimal(ea.getMinimum())) == 0);									
			}else if(ea.getMinSign().equals("2")){
				formula_x = !(zxbFYC.compareTo(new BigDecimal(ea.getMinimum())) > 0);
			}
			if(ea.getMaxSign().equals("0")){
				formula_y = (zxbFYC.compareTo(new BigDecimal(ea.getMaximum())) > 0);
			}else if(ea.getMaxSign().equals("1")){
				formula_y = (zxbFYC.compareTo(new BigDecimal(ea.getMaximum())) == 0);									
			}else if(ea.getMaxSign().equals("2")){
				formula_y = !(zxbFYC.compareTo(new BigDecimal(ea.getMaximum())) < 0);
			}								
			if(formula_x && formula_y){
				zxbgljt = zxbgljt.add(zxbFYC.multiply(new BigDecimal(ea.getAllowance()).divide(BigDecimal.valueOf(100.00))));
				break;
			}
		}
		
		return zxbgljt;
	}
	
	/*直辖部员工主键串*/
	public String findZXBBranchs(InsSalesPreGrade saler,String salerIds){
		List<InsSalesPreGrade> sales = null; 
		if(saler.getPreMonthGrade()==1){
			sales = salesDaysCommissionClient.findZJZXB(saler.getInsuranceSalesId());
		}else{
			sales = salesDaysCommissionClient.findBZZXB(saler.getInsuranceSalesId());
		}
		for(InsSalesPreGrade salertemp:sales){
			salerIds = salerIds + salertemp.getInsuranceSalesId()+",";
			int branchNums = 0;
			if(saler.getPreMonthGrade()==1){
				branchNums = salesDaysCommissionClient.getZJZXBNum(saler.getInsuranceSalesId());
			}else{
				branchNums = salesDaysCommissionClient.getBZZXBNum(saler.getInsuranceSalesId());
			}			
			if(branchNums>0){
				salerIds = findZXBBranchs(salertemp,salerIds);
			}
		}
		return salerIds;
	}

	//处长育成奖
	public BigDecimal calculationCZYCJ(InsSalesPreGrade saler) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		BigDecimal czycj = BigDecimal.valueOf(0.00);
		List<DirectorNurtureBonus> dnbs = directorNurtureBonusClient.getDirectorNurtureBonusList();
		Map<String,Object> map = new HashMap<>();
		map.put("ycSalesId",saler.getInsuranceSalesId());
		map.put("salesGradeId","3");//处长
		List<InsSalesPreGrade> ydycs = salesDaysCommissionClient.findYcgx(map);//直接育成人
		if(ydycs!=null && ydycs.size()>0){
			String yczSalesId = "";
			BigDecimal ydYczsFYC = BigDecimal.valueOf(0.00);
			for(InsSalesPreGrade bycr:ydycs){
				yczSalesId = yczSalesId + bycr.getInsuranceSalesId() + ",";
				int branchNums = salesDaysCommissionClient.getNumBySj(bycr.getInsuranceSalesId());//直接育成组
				if(branchNums>0){
					yczSalesId = findBranchs(bycr,yczSalesId);
				}
		        if (yczSalesId.endsWith(",")) {
		        	yczSalesId = yczSalesId.substring(0, yczSalesId.length() - 1);
		        }
				ydYczsFYC = salesDaysCommissionClient.sumSalesFYC(yczSalesId);//直接育成组FYC
				BigDecimal czycjxs = BigDecimal.valueOf(0.00);
				//判断直接育成处长年限
				calendar = Calendar.getInstance();
		        String uwEnd = "";
		        calendar.add(Calendar.MONTH, 0);
		        calendar.set(Calendar.DAY_OF_MONTH, 1); 
		        uwEnd = format.format(calendar.getTime())+"";//当月第一天
		        String uwStart = bycr.getJoinDate();
		        long days = (format.parse(uwEnd).getTime()-format.parse(uwStart).getTime())/(1000 * 60 * 60 * 24);
		        if(days<366){
		        	for(DirectorNurtureBonus dnbxs:dnbs){
		        		if(dnbxs.getGenerativeAlgebra().equals("直接育成奖金率")){
		        			czycjxs = new BigDecimal(dnbxs.getFastYear());
		        			break;
		        		}
		        	}
		        }else{
		        	for(DirectorNurtureBonus dnbxs:dnbs){
		        		if(dnbxs.getGenerativeAlgebra().equals("直接育成奖金率")){
		        			czycjxs = new BigDecimal(dnbxs.getFollowingYearAndBeyond());
		        			break;
		        		}
		        	}		        	
		        }
		        czycj = czycj.add(ydYczsFYC.multiply((czycjxs).divide(BigDecimal.valueOf(100.00))));
				ydYczsFYC = BigDecimal.valueOf(0.00);//重置组FYC
			}

			String jjycSalesId = "";
			for(InsSalesPreGrade bycr:ydycs){
				map.clear();
				map.put("ycSalesId",bycr.getInsuranceSalesId());
				map.put("salesGradeId","3");//处长
				List<InsSalesPreGrade> jjycs = salesDaysCommissionClient.findYcgx(map);//间接育成人
				if(jjycs!=null && jjycs.size()>0){
					BigDecimal edYczsFYC = BigDecimal.valueOf(0.00);
					for(InsSalesPreGrade jjbycr:jjycs){
						jjycSalesId = jjycSalesId + jjbycr.getInsuranceSalesId()+",";
						int branchNums = salesDaysCommissionClient.getNumBySj(jjbycr.getInsuranceSalesId());//间接育成组
						if(branchNums>0){
							jjycSalesId = findBranchs(jjbycr,jjycSalesId);
						}
				        if (jjycSalesId.endsWith(",")) {
				        	jjycSalesId = jjycSalesId.substring(0, jjycSalesId.length() - 1);
				        }
				        
				        if(!jjycSalesId.equals("")){
					        edYczsFYC = salesDaysCommissionClient.sumSalesFYC(jjycSalesId);//直接育成组FYC
							BigDecimal czycjxs = BigDecimal.valueOf(0.00);
							//判断直接育成处长年限
							calendar = Calendar.getInstance();
					        String uwEnd = "";
					        calendar.add(Calendar.MONTH, 0);
					        calendar.set(Calendar.DAY_OF_MONTH, 1); 
					        uwEnd = format.format(calendar.getTime())+"";//当月第一天
					        String uwStart = jjbycr.getJoinDate();
					        long days = (format.parse(uwEnd).getTime()-format.parse(uwStart).getTime())/(1000 * 60 * 60 * 24);
					        if(days<366){
					        	for(DirectorNurtureBonus dnbxs:dnbs){
					        		if(dnbxs.getGenerativeAlgebra().equals("第二代育成奖金率")){
					        			czycjxs = new BigDecimal(dnbxs.getFastYear());
					        			break;
					        		}
					        	}
					        }else{
					        	for(DirectorNurtureBonus dnbxs:dnbs){
					        		if(dnbxs.getGenerativeAlgebra().equals("第二代育成奖金率")){
					        			czycjxs = new BigDecimal(dnbxs.getFollowingYearAndBeyond());
					        			break;
					        		}
					        	}		        	
					        }
					        czycj = czycj.add(edYczsFYC.multiply((czycjxs).divide(BigDecimal.valueOf(100.00))));
					        edYczsFYC = BigDecimal.valueOf(0.00);//重置组FYC
				        }
						/*map.clear();
						map.put("ycSalesId",jjbycr.getInsuranceSalesId());
						map.put("salesGradeId","3");//处长
						List<InsSalesPreGrade> xhycs = salesDaysCommissionClient.findYcgx(map);//循环育成人
						if(xhycs!=null && xhycs.size()>0){
							jjycSalesId = findXHYCR(xhycs,jjycSalesId);
						}*/
					}
				}
			}
		}

		return czycj;
	}
	
	//部长育成奖
	public BigDecimal calculationBYCJ(InsSalesPreGrade saler) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		BigDecimal bycj = BigDecimal.valueOf(0.00);
		List<MinisterNurturingBonus> dnbs = ministerNurturingBonusClient.getMinisterNurturingList();
		Map<String,Object> map = new HashMap<>();
		map.put("ycSalesId",saler.getInsuranceSalesId());
		map.put("salesGradeId","2");//部长
		List<InsSalesPreGrade> ydycs = salesDaysCommissionClient.findYcgx(map);//直接育成人
		if(ydycs!=null && ydycs.size()>0){
			String yczSalesId = "";
			BigDecimal ydYczsFYC = BigDecimal.valueOf(0.00);
			for(InsSalesPreGrade bycr:ydycs){
				yczSalesId = yczSalesId + bycr.getInsuranceSalesId() + ",";
				int branchNums = salesDaysCommissionClient.getBZZXBNum(bycr.getInsuranceSalesId());//直接育成部
				if(branchNums>0){
					yczSalesId = findZXBBranchs(bycr,yczSalesId);
				}
		        if (yczSalesId.endsWith(",")) {
		        	yczSalesId = yczSalesId.substring(0, yczSalesId.length() - 1);
		        }
				ydYczsFYC = salesDaysCommissionClient.sumSalesFYC(yczSalesId);//直接育成组FYC
				BigDecimal czycjxs = BigDecimal.valueOf(0.00);
				//判断直接育成处长年限
				calendar = Calendar.getInstance();
		        String uwEnd = "";
		        calendar.add(Calendar.MONTH, 0);
		        calendar.set(Calendar.DAY_OF_MONTH, 1); 
		        uwEnd = format.format(calendar.getTime())+"";//当月第一天
		        String uwStart = bycr.getJoinDate();
		        long days = (format.parse(uwEnd).getTime()-format.parse(uwStart).getTime())/(1000 * 60 * 60 * 24);
		        if(days<366){
		        	for(MinisterNurturingBonus dnbxs:dnbs){
		        		if(dnbxs.getGenerativeAlgebra().equals("直接育成奖金率")){
		        			czycjxs = new BigDecimal(dnbxs.getFastYear());
		        			break;
		        		}
		        	}
		        }else{
		        	for(MinisterNurturingBonus dnbxs:dnbs){
		        		if(dnbxs.getGenerativeAlgebra().equals("直接育成奖金率")){
		        			czycjxs = new BigDecimal(dnbxs.getFollowingYearAndBeyond());
		        			break;
		        		}
		        	}		        	
		        }
		        bycj = bycj.add(ydYczsFYC.multiply((czycjxs).divide(BigDecimal.valueOf(100.00))));
				ydYczsFYC = BigDecimal.valueOf(0.00);//重置组FYC
			}

			String jjycSalesId = "";
			for(InsSalesPreGrade bycr:ydycs){
				map.clear();
				map.put("ycSalesId",bycr.getInsuranceSalesId());
				map.put("salesGradeId","2");//部长
				List<InsSalesPreGrade> jjycs = salesDaysCommissionClient.findYcgx(map);//间接育成人
				if(jjycs!=null && jjycs.size()>0){
					BigDecimal edYczsFYC = BigDecimal.valueOf(0.00);
					for(InsSalesPreGrade jjbycr:jjycs){
						jjycSalesId = jjycSalesId + jjbycr.getInsuranceSalesId()+",";
						int branchNums = salesDaysCommissionClient.getBZZXBNum(jjbycr.getInsuranceSalesId());//间接育成组
						if(branchNums>0){
							jjycSalesId = findZXBBranchs(jjbycr,jjycSalesId);
						}
				        if (jjycSalesId.endsWith(",")) {
				        	jjycSalesId = jjycSalesId.substring(0, jjycSalesId.length() - 1);
				        }
				        edYczsFYC = salesDaysCommissionClient.sumSalesFYC(jjycSalesId);//直接育成组FYC
						BigDecimal czycjxs = BigDecimal.valueOf(0.00);
						//判断直接育成处长年限
						calendar = Calendar.getInstance();
				        String uwEnd = "";
				        calendar.add(Calendar.MONTH, 0);
				        calendar.set(Calendar.DAY_OF_MONTH, 1); 
				        uwEnd = format.format(calendar.getTime())+"";//当月第一天
				        String uwStart = jjbycr.getJoinDate();
				        long days = (format.parse(uwEnd).getTime()-format.parse(uwStart).getTime())/(1000 * 60 * 60 * 24);
				        if(days<366){
				        	for(MinisterNurturingBonus dnbxs:dnbs){
				        		if(dnbxs.getGenerativeAlgebra().equals("第二代育成奖金率")){
				        			czycjxs = new BigDecimal(dnbxs.getFastYear());
				        			break;
				        		}
				        	}
				        }else{
				        	for(MinisterNurturingBonus dnbxs:dnbs){
				        		if(dnbxs.getGenerativeAlgebra().equals("第二代育成奖金率")){
				        			czycjxs = new BigDecimal(dnbxs.getFollowingYearAndBeyond());
				        			break;
				        		}
				        	}		        	
				        }
				        bycj = bycj.add(edYczsFYC.multiply((czycjxs).divide(BigDecimal.valueOf(100.00))));
				        edYczsFYC = BigDecimal.valueOf(0.00);//重置组FYC
					}
				}
			}
		}

		return bycj;
	}

	//查找间接育成人
	public String findXHYCR(List<InsSalesPreGrade> sales,String salerIds){
		Map<String,Object> map = new HashMap<>();
		for(InsSalesPreGrade salertemp:sales){
			salerIds = salerIds + salertemp.getInsuranceSalesId()+",";
			map.put("ycSalesId",salertemp.getInsuranceSalesId());
			map.put("salesGradeId","3");//处长
			List<InsSalesPreGrade> xhycs = salesDaysCommissionClient.findYcgx(map);//循环育成人
			if(xhycs!=null && xhycs.size()>0){
				salerIds = findXHYCR(xhycs,salerIds);
			}
		}
		return salerIds;
	}


	//总监津贴
	public BigDecimal calculationZJJT(InsSalesPreGrade saler) throws ParseException{
		List<DirectorAllowanceStandard> zjjtxss = new ArrayList<>();
		Map<Object,String> mapp =new HashMap<>();
		String cooperationType = saler.getCooperationType();
		if(cooperationType.equals("0")){
			mapp.clear();
			mapp.put("salesOrgId",saler.getSalesOrgId()+"");
			zjjtxss = salesDaysCommissionClient.findZJJTXS(mapp);
		}else{
			mapp.clear();
			mapp.put("insSalesId",saler.getInsuranceSalesId()+"");
			zjjtxss = salesDaysCommissionClient.findZJJTXS(mapp);			
		}
		BigDecimal zjjt = BigDecimal.valueOf(0.00);
		String salerIds = saler.getInsuranceSalesId()+",";
		List<InsSalesPreGrade> zjtjrs = salesDaysCommissionClient.findZJZXB(saler.getInsuranceSalesId());
		if(zjtjrs!=null && zjtjrs.size()>0){
			for(InsSalesPreGrade zjtjr:zjtjrs){
				salerIds = salerIds + zjtjr.getInsuranceSalesId() + ",";
				int tjsnums = salesDaysCommissionClient.getBZZXBNum(zjtjr.getInsuranceSalesId());
				if(tjsnums>0){
					salerIds = findZJTD(zjtjr,salerIds);
				}
			}
		}
        if (salerIds.endsWith(",")) {
        	salerIds = salerIds.substring(0, salerIds.length() - 1);
        }
		BigDecimal zjtdFYC = salesDaysCommissionClient.sumSalesFYC(salerIds);
		
		if(zjjtxss!=null && zjjtxss.size()>0){
			for(DirectorAllowanceStandard zjjtxs:zjjtxss){
				String formula = zjjtxs.getAllowanceFormula();
				formula = StringEscapeUtils.unescapeXml(formula);
				String[] formula_arr = formula.split("FYC");
				
				Boolean formula_head = false;
				Boolean formula_last = false;
				
				String formula_max = formula_arr[0];
				String maxFYC_str = formula_max.replace(">","").replace("=","");
				BigDecimal maxFYC = new BigDecimal(maxFYC_str);
				String maxFYC_fh = formula_max.substring(maxFYC_str.length(),formula_max.length());
				if(maxFYC_fh.equals(">")){
					formula_head = (maxFYC.compareTo(zjtdFYC) > 0);
				}else if(maxFYC_fh.equals(">=")){
					formula_head = (maxFYC.compareTo(zjtdFYC) >= 0);
				}else{
					formula_head = (maxFYC.compareTo(zjtdFYC) < 0);
				}
				
				String formula_min = formula_arr[1];
				String minFYC_str = formula_min.replace(">","").replace("=","");
				BigDecimal minFYC = new BigDecimal(minFYC_str);
				String minFYC_fh = formula_min.substring(0,formula_min.length()-minFYC_str.length());
				if(minFYC_fh.equals(">")){
					formula_last = (minFYC.compareTo(zjtdFYC) < 0);
				}else if(minFYC_fh.equals(">=")){
					formula_last = (minFYC.compareTo(zjtdFYC) <= 0);
				}else{
					formula_last = (minFYC.compareTo(zjtdFYC) > 0);
				}
				
				if(formula_head && formula_last){
					BigDecimal zjjt_xs = new BigDecimal(Long.valueOf(zjjtxs.getAllowanceRatio()));
					zjjt = zjtdFYC.multiply(zjjt_xs.divide(BigDecimal.valueOf(100.00)));
					break;
				}
			}
		}
		
		return zjjt;
	}
	
	//总监团队
	public String findZJTD(InsSalesPreGrade saler,String salerIds){
		List<InsSalesPreGrade> zjtjrs = salesDaysCommissionClient.findZJZXB(saler.getInsuranceSalesId());
		if(zjtjrs!=null && zjtjrs.size()>0){
			for(InsSalesPreGrade zjtjr:zjtjrs){
				salerIds = salerIds + zjtjr.getInsuranceSalesId() + ",";
				int tjsnums = salesDaysCommissionClient.getBZZXBNum(zjtjr.getInsuranceSalesId());
				if(tjsnums>0){
					salerIds = findZJTD(zjtjr,salerIds);
				}
			}
		}		
		return salerIds;
	}
}
