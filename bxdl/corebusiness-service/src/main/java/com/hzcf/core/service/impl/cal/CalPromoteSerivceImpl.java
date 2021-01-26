package com.hzcf.core.service.impl.cal;

import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.core.mapper.cal.CalHisPromoteMapper;
import com.hzcf.core.mapper.cal.CalPromoteMapper;
import com.hzcf.core.service.cal.CalPromoteSerivce;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalPromote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/10/19.
 */
@Service
public class CalPromoteSerivceImpl implements CalPromoteSerivce {
    @Autowired
    CalPromoteMapper calPromoteMapper;
    @Autowired
    CalHisPromoteMapper calHisPromoteMapper;

    @Override
    public List<CalPromote> getCalPromoteByOrgId(Map<String, Object> params) {
        return calPromoteMapper.getCalPromoteByOrgId(params);
    }

    @Override
    public int insertIgnoreNull(CalPromote calPromote) {
        return calPromoteMapper.insertIgnoreNull(calPromote);
    }

    @Override
    public int updateIgnoreNull(CalPromote calPromote) {
        return calPromoteMapper.updateIgnoreNull(calPromote);
    }

    @Override
    public PageModel getCalHisPromotePage(Map<String, Object> params) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        pageModel.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", pageModel.getStartIndex());
        params.put("endIndex", pageModel.getEndIndex());
        // 查询数据
        List<Map<String, Object>> list = calHisPromoteMapper.getCalHisPromoteByVersionId(params);
         // 查询条数
        Long count =calHisPromoteMapper.getCalHisPromoteCount(params);
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list) {
            Map<String,Object> newMap = new HashMap<String,Object>();
            String ruleTypeVal = (String) map.get("rule_type");//计算规则选择
            BigDecimal minValue = (BigDecimal) map.get("min_value");//下限值
            BigDecimal maxValue = (BigDecimal) map.get("max_value");//上限值
            String minSign = (String) map.get("min_sign");//下限标记符号（0：大于，1：大于等于）
            String maxSign = (String) map.get("max_sign");//上限标记符号（0：小于，1：小于等于）
            if("0".equals(minSign)){
                minSign = "&lt;";
            }else if ("1".equals(minSign)){
                minSign = "<=";
            }
             if("0".equals(maxSign)){
                maxSign = "&lt;";
            }else if ("1".equals(maxSign)){
                maxSign = "<=";
            }
            String ruleType = "";
            StringBuffer intervalRangeFyc = new StringBuffer();//FYC区间设定值
            StringBuffer allowanceCalPromote = new StringBuffer();//津贴
            if (CalParamRuleEnum.RULE_ZY_0.getCode().equals(ruleTypeVal)) {//计算规则选择,判断 区间设定值
                ruleType =CalParamRuleEnum.RULE_ZY_0.getDesc();
                intervalRangeFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元");
                allowanceCalPromote.append((BigDecimal) map.get("allowance"));
            }else if (CalParamRuleEnum.RULE_ZY_1.getCode().equals(ruleTypeVal)){//FYC百位取整
                ruleType =CalParamRuleEnum.RULE_ZY_1.getDesc();
                intervalRangeFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元");
                allowanceCalPromote.append("FYC百位取整");
            }else if (CalParamRuleEnum.RULE_ZY_2.getCode().equals(ruleTypeVal)){//FYC乘以比例参数
                ruleType =CalParamRuleEnum.RULE_ZY_2.getDesc();
                intervalRangeFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元");
                BigDecimal allowance = (BigDecimal) map.get("allowance");
                allowance =  allowance.multiply(new BigDecimal(100));
                allowanceCalPromote.append(allowance.stripTrailingZeros().toPlainString()).append("%");
            }else if (CalParamRuleEnum.RULE_ZY_3.getCode().equals(ruleTypeVal)){//有额外的递增
                BigDecimal step = (BigDecimal) map.get("step");
                 BigDecimal allowance = (BigDecimal) map.get("allowance");
                 BigDecimal extraAllowance = (BigDecimal) map.get("extra_allowance");
                ruleType =CalParamRuleEnum.RULE_ZY_3.getDesc();
                intervalRangeFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元")
                .append("每增加").append(step.stripTrailingZeros().toPlainString()).append("元");
                allowanceCalPromote.append(allowance.stripTrailingZeros().toPlainString()).append("额外增加").append(extraAllowance.stripTrailingZeros().toPlainString());
            }
            newMap.put("set_item","总价值佣金FYC（元）");
            newMap.put("ruleType",ruleType);
            newMap.put("intervalRangeFyc",intervalRangeFyc.toString());
            newMap.put("allowanceCalPromote",allowanceCalPromote.toString());
            newList.add(newMap);
        }
        pageModel.setList(newList);
        pageModel.setTotalRecords(count);
        return pageModel;
    }
}
