package com.hzcf.core.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.core.mapper.InsPolicyInsuredPersonMapper;
import com.hzcf.core.mapper.InsProtocolCostMapper;
import com.hzcf.core.mapper.InsServiceChargeProductMapper;
import com.hzcf.core.service.InsProtocolCostService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.protocol.InsProtocolCost;
import com.hzcf.util.DateUtil;

@Service
public class InsProtocolCostServiceImpl implements InsProtocolCostService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InsPolicyInsuredPersonMapper insPolicyInsuredPersonMapper;
    @Autowired
    private InsProtocolCostMapper insProtocolCostMapper;
    @Autowired
    private InsServiceChargeProductMapper insServiceChargeProductMapper;
    
    private static final String GB = "规保";
    private static final String BB = "标保";
    private static final String JXL = "继续率";

	@Override
	public void getInsProtocolCost(String dateStr, String protocolType) {
		
		BigDecimal ratio = new BigDecimal(100);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Double> costMap = new HashMap<String, Double>();
		
		String firstDayMonth = null;
		String firstDayQuarter = null;
		String firstDayHalfYear = null;
		String firstDayYear = null;
		
		String firstDay = null;
		String lastDay = null;
		try {
			Date date = sdf.parse(dateStr);
			
			Calendar instance = Calendar.getInstance();
			instance.setTime(date);
			
			int month = instance.get(Calendar.MONTH) + 1;
			instance.add(Calendar.MONTH, -1);
			Map<String, Object> day = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
            lastDay = day.get("lastDay").toString();
            
            if (1 == month) {
            	// 1月需计算 月、季、半年、年    取一年数据
            	instance.setTime(date);
                instance.add(Calendar.MONTH, -12);
    			Map<String, Object> day1 = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
    			firstDay = day1.get("firstDay").toString();
            } else if (4 == month || 10 == month) {
            	// 4月 和10月需计算 月、季   取一季度数据
            	instance.setTime(date);
                instance.add(Calendar.MONTH, -3);
    			Map<String, Object> day1 = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
    			firstDay = day1.get("firstDay").toString();
            } else if (7 == month) {
            	// 7月需计算 月、季、半年   取半年数据
            	instance.setTime(date);
                instance.add(Calendar.MONTH, -6);
    			Map<String, Object> day1 = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
    			firstDay = day1.get("firstDay").toString();
            } else {
            	// 其他月份取一个月数据
            	firstDay = day.get("firstDay").toString();
            }
            
    		//月结算
            firstDayMonth = day.get("firstDay").toString();
          
            // 季结算
            if (1 == month || 4 == month || 7 == month || 10 == month) {
	          	instance.setTime(date);
	            instance.add(Calendar.MONTH, -3);
	  			Map<String, Object> day1 = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
	  			firstDayQuarter = day1.get("firstDay").toString();
            }          
          
            // 半年结算
            if (1 == month || 7 == month) {
	          	instance.setTime(date);
	            instance.add(Calendar.MONTH, -6);
	  			Map<String, Object> day1 = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
	  			firstDayHalfYear = day1.get("firstDay").toString();
            }
          
            // 年结算
            if (1 == month) {
	          	instance.setTime(date);
	            instance.add(Calendar.MONTH, -12);
	  			Map<String, Object> day1 = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
	  			firstDayYear = day1.get("firstDay").toString();
            }
            
            // 时间范围内的保单
            long totalCount = insPolicyInsuredPersonMapper.getInsurancePolicySizeByStatishMonth(sdf.parse(firstDay), sdf.parse(lastDay));
            
            Integer pageSize = 500;
    		Integer currPage = null;
    		
    		int totalPage = (int) (totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize);// 分页公式
			STOP:
    		for (int i = totalPage; i > 0; i--) {
				currPage = i;
				
				List<Map<String, Object>> insPolicyInsuredPersonList = insPolicyInsuredPersonMapper.queryInsurancePolicyListByStatishMonth(sdf.parse(firstDay), sdf.parse(lastDay), (currPage - 1) * pageSize, pageSize);
				if (!CollectionUtils.isEmpty(insPolicyInsuredPersonList)) {
					for (Map<String, Object> insPolicyInsuredPerson : insPolicyInsuredPersonList) {
						if ("SERVICECHARGE".equals(protocolType)) {
							// 手续费计算
							costMap = insProtocolServiceChargeCost(costMap, Long.parseLong(insPolicyInsuredPerson.get("INSURED_PERSON_ID").toString()), 
									lastDay, firstDayMonth, firstDayQuarter, firstDayHalfYear, firstDayYear, ratio);
						} else if ("RATEADJUST".equals(protocolType)) {
							// 费率调节计算
							costMap = insProtocolRateAdjustCost(costMap, Long.parseLong(insPolicyInsuredPerson.get("INSURED_PERSON_ID").toString()), 
									lastDay, firstDayMonth, firstDayQuarter, firstDayHalfYear, firstDayYear, ratio);
						} else {
							break STOP;
						}
					}
				}
			}
			
			// 迭代 costMap 插入数据
	        for (Entry<String, Double> entry : costMap.entrySet()) {
	        	String[] keys = entry.getKey().split("_");
	        	
	            InsProtocolCost insProtocolCost = new InsProtocolCost();
	            if (keys.length == 3) {
	        		insProtocolCost.setOrgId(Long.parseLong(keys[0]));
		            insProtocolCost.setProductId(Long.parseLong(keys[1]));
		            insProtocolCost.setProtocolId(Long.parseLong(keys[2]));
	        	}
	            if ("SERVICECHARGE".equals(protocolType)) {
	            	insProtocolCost.setCostType("1");
				} else if ("RATEADJUST".equals(protocolType)) {
					insProtocolCost.setCostType("2");
				}
	            insProtocolCost.setCostTotal(new BigDecimal(entry.getValue()).multiply(ratio));
	            insProtocolCost.setCalculationEndDate(sdf.parse(lastDay));
	            insProtocolCost.setCreateTime(new Date());
	            
	            insProtocolCostMapper.insert(insProtocolCost);
	        }
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Double> insProtocolServiceChargeCost(Map<String, Double> costMap, Long insuredPersonId, String lastDay, 
			String firstDayMonth, String firstDayQuarter, String firstDayHalfYear, String firstDayYear, BigDecimal ratio) {
		// 根据保单查询全部保费、产品、机构、协议、外部标保佣金系数、手续费率
		List<Map<String, Object>> list = insPolicyInsuredPersonMapper
				.queryServiceChargeAndOutCommissionCoefficient(insuredPersonId, lastDay, firstDayMonth, firstDayQuarter, firstDayHalfYear, firstDayYear);
		if (!CollectionUtils.isEmpty(list)) {
			for (Map<String, Object> map : list) {
				double premium = Double.parseDouble(map.get("PREMIUM").toString());
	            double outStandardCommissionCoefficient = Double.parseDouble(map.get("OUT_STANDARD_COMMISSION_COEFFICIENT").toString());
	            BigDecimal totalPremium = BigDecimal.valueOf(premium);
				
	            // 标保
	            if (1 == Integer.parseInt(map.get("RATE_TYPE").toString())) {
	                totalPremium = totalPremium.multiply(BigDecimal.valueOf(outStandardCommissionCoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP);
	            }
	            BigDecimal divide = new BigDecimal(map.get("FIRST_YEAR_COST").toString()).divide(ratio, 4, BigDecimal.ROUND_HALF_UP);
                BigDecimal multiply = totalPremium.multiply(divide).setScale(2, BigDecimal.ROUND_HALF_UP);
                
                // 按组织、产品、协议统计
                String key = map.get("ORG_ID") + "_" + map.get("PRODUCT_ID") + "_" + map.get("PROTOCOL_ID");
                if (costMap.containsKey(key)) {
                	Double costSum = costMap.get(key);
                	costSum = new BigDecimal(costSum).add(multiply).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                	costMap.put(key, costSum);
                } else {
                	costMap.put(key, multiply.doubleValue());
                }
			}
		}
		return costMap;
	}
	
	private Map<String, Double> insProtocolRateAdjustCost(Map<String, Double> costMap, Long insuredPersonId, String lastDay, 
			String firstDayMonth, String firstDayQuarter, String firstDayHalfYear, String firstDayYear, BigDecimal ratio) {
		// 根据保单查询全部保费、产品、机构、协议、外部标保佣金系数、费率调节
		List<Map<String, Object>> list = insPolicyInsuredPersonMapper
				.queryRateAdjustAndOutCommissionCoefficient(insuredPersonId, lastDay, firstDayMonth, firstDayQuarter, firstDayHalfYear, firstDayYear);
		if (!CollectionUtils.isEmpty(list)) {
			for (Map<String, Object> map : list) {
				BigDecimal cost = new BigDecimal(0);
				double premium = Double.parseDouble(map.get("PREMIUM").toString());
	            double outStandardCommissionCoefficient = Double.parseDouble(map.get("OUT_STANDARD_COMMISSION_COEFFICIENT").toString());
	            BigDecimal totalPremium = BigDecimal.valueOf(premium);
	            
	            // 费率类型
	            if (1 == Integer.parseInt(map.get("RATE_TYPE").toString())) {
	            	// 标保
	                totalPremium = totalPremium.multiply(BigDecimal.valueOf(outStandardCommissionCoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP);
	            } else if (2 == Integer.parseInt(map.get("RATE_TYPE").toString())) {
	            	// 首年佣金和
	            	Long productId = Long.parseLong(map.get("PRODUCT_ID").toString());
	            	Long protocolId = Long.parseLong(map.get("PROTOCOL_ID").toString());
	            	totalPremium = getFirstYearServiceChargeCost(productId, protocolId, totalPremium, outStandardCommissionCoefficient);
	            }
	            
	            if (0 == Integer.parseInt(map.get("PRODUCT_TYPE").toString()) || 1 == Integer.parseInt(map.get("PRODUCT_TYPE").toString())) {
	            	// 单产品或全产品
	            	if (0 == Integer.parseInt(map.get("RATE_ADJUST_TYPE").toString())) {
	            		// 固定费率
	            		BigDecimal rate = new BigDecimal(map.get("CHANGE_RATE").toString()).divide(ratio, 4, BigDecimal.ROUND_HALF_UP);
	            		cost = totalPremium.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
	            	} else if (1 == Integer.parseInt(map.get("RATE_ADJUST_TYPE").toString())) {
	            		// 变动费率
	            		String changeRate = map.get("CHANGE_RATE").toString();
	            		BigDecimal rate = this.getRate(changeRate, premium, outStandardCommissionCoefficient).divide(ratio, 4, BigDecimal.ROUND_HALF_UP);
	            		cost = totalPremium.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
	            	}
	            } else if (2 == Integer.parseInt(map.get("PRODUCT_TYPE").toString())) {
	            	// 混合产品
	            	if (0 == Integer.parseInt(map.get("RATE_ADJUST_TYPE").toString())) {
	            		// 固定费率
	            		BigDecimal rate = new BigDecimal(map.get("CHANGE_RATE").toString()).divide(ratio, 4, BigDecimal.ROUND_HALF_UP);
	            		cost = totalPremium.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(2));
	            	} else if (1 == Integer.parseInt(map.get("RATE_ADJUST_TYPE").toString())) {
	            		// 变动费率 单产品费率基于
	            		String changeRate = map.get("CHANGE_RATE").toString();
	            		BigDecimal rate = this.getRate(changeRate, premium, outStandardCommissionCoefficient).divide(ratio, 4, BigDecimal.ROUND_HALF_UP);
	            		BigDecimal rateCost = totalPremium.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
	            		// 变动费率 全产品费率基于
	            		String allChangeRate = map.get("ALL_CHANGE_RATE").toString();
	            		BigDecimal allRate = this.getRate(allChangeRate, premium, outStandardCommissionCoefficient).divide(ratio, 4, BigDecimal.ROUND_HALF_UP);
	            		BigDecimal allRateCost = totalPremium.multiply(allRate).setScale(2, BigDecimal.ROUND_HALF_UP);
	            		
	            		cost = rateCost.add(allRateCost);
	            	}
	            }
	            
	            // 按组织、产品、协议统计
                String key = map.get("ORG_ID") + "_" + map.get("PRODUCT_ID") + "_" + map.get("PROTOCOL_ID");
                if (costMap.containsKey(key)) {
                	Double costSum = costMap.get(key);
                	costSum = new BigDecimal(costSum).add(cost).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                	costMap.put(key, costSum);
                } else {
                	costMap.put(key, cost.doubleValue());
                }
			}
		}
		return costMap;
	}
	
	private BigDecimal getFirstYearServiceChargeCost(Long productId, Long protocolId, BigDecimal premium, double outStandardCommissionCoefficient) {
		BigDecimal firstYearServiceChargeCost = new BigDecimal(0);
		// 手续费
		Map<String, Object> map = insServiceChargeProductMapper.getServiceChargeByProductAndProtocol(productId, protocolId);
		if (!map.isEmpty()) {
			// 标保
            if (1 == Integer.parseInt(map.get("RATE_TYPE").toString())) {
            	premium = premium.multiply(BigDecimal.valueOf(outStandardCommissionCoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            BigDecimal divide = new BigDecimal(map.get("FIRST_YEAR_COST").toString()).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
            firstYearServiceChargeCost = premium.multiply(divide).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		return firstYearServiceChargeCost;
	}
	
	private BigDecimal getRate(String jsonStr, double premium, double outStandardCommissionCoefficient) {
		BigDecimal decimal = new BigDecimal(0);
		List<Map> list = JSONObject.parseArray(jsonStr,  Map.class);
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
		
		for (Map map:list) {
			String rateTypeText = map.get("rateTypeText").toString();
			String summaryRule = map.get("summaryRule").toString();
			String changeRate = map.get("changeRate").toString();
			
			if (StringUtils.isBlank(changeRate)) {
				break;
			}
			
			if (GB.equals(rateTypeText)) {
				summaryRule = summaryRule.replaceAll(GB, BigDecimal.valueOf(premium).divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP).toString());
//				if (summaryRule.contains(JXL)) {
//					summaryRule = summaryRule.replaceAll(JXL, String.valueOf(persistency));
//				}
			} else if (BB.equals(rateTypeText)) {
				premium = BigDecimal.valueOf(premium).multiply(BigDecimal.valueOf(outStandardCommissionCoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				summaryRule = summaryRule.replaceAll(BB, BigDecimal.valueOf(premium).divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP).toString());
//				if (summaryRule.contains(JXL)) {
//					summaryRule = summaryRule.replaceAll(JXL, String.valueOf(persistency));
//				}
			} else if (JXL.equals(rateTypeText)) {
				
			}
			
			try {
				if (Boolean.parseBoolean(engine.eval(summaryRule).toString())) {
					decimal = new BigDecimal(changeRate);
					break;
				}
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		
		return decimal;
	}
	
	@Override
	public PageModel getProtocolCostList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> protocolCostList  = new ArrayList<Map<String, Object>>();
		protocolCostList = insProtocolCostMapper.getProtocolCostListByPage(paramsCondition);
		pageModel.setList(protocolCostList);
		Long totalRecords = insProtocolCostMapper.getProtocolCostCount(paramsCondition);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
}
