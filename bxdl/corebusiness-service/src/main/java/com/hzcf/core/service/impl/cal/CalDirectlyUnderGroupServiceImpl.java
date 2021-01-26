package com.hzcf.core.service.impl.cal;

import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.core.mapper.cal.CalDirectlyUnderGroupMapper;
import com.hzcf.core.mapper.cal.CalHisDirectlyUnderGroupMapper;
import com.hzcf.core.service.cal.CalDirectlyUnderGroupService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalDirectlyUnderGroup;
import com.hzcf.pojo.cal.CalHisDirectlyUnderGroup;
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
public class CalDirectlyUnderGroupServiceImpl implements CalDirectlyUnderGroupService {
    @Autowired
    private CalDirectlyUnderGroupMapper calDirectlyUnderGroupMapper;
    @Autowired
    private CalHisDirectlyUnderGroupMapper calHisDirectlyUnderGroupMapper;

    @Override
    public List<CalDirectlyUnderGroup> getCalDirectlyUnderGroupByOrgId(Map<String, Object> param) {
        return calDirectlyUnderGroupMapper.getCalDirectlyUnderGroupByOrgId(param);
    }

    @Override
    public int insertIgnoreNull(CalDirectlyUnderGroup calDirectlyUnderGroup) {
        return calDirectlyUnderGroupMapper.insertIgnoreNull(calDirectlyUnderGroup);
    }

    @Override
    public int updateIncreaseState(CalDirectlyUnderGroup calDirectlyUnderGroup) {
        return calDirectlyUnderGroupMapper.updateIgnoreNull(calDirectlyUnderGroup);
    }

    @Override
    public PageModel getCalHisDirectlyUnderGroupPage(Map<String, Object> params) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        pageModel.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", pageModel.getStartIndex());
        params.put("endIndex", pageModel.getEndIndex());
        // 查询数据
        List<Map<String, Object>> list = calHisDirectlyUnderGroupMapper.getCalHisDirectlyUnderGroup(params);
        // 查询条数
        Long count =calHisDirectlyUnderGroupMapper.getCalHisDirectlyUnderGroupCount(params);
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
            StringBuffer underGroupFyc = new StringBuffer();//FYC区间设定值
            StringBuffer allowanceCalPromote = new StringBuffer();//津贴
            StringBuffer activeSaler = new StringBuffer();//活动人力
            if (CalParamRuleEnum.RULE_D_GROUP_0.getCode().equals(ruleTypeVal)) {//计算规则选择,判断 区间设定值
                ruleType =CalParamRuleEnum.RULE_D_GROUP_0.getDesc();
                underGroupFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元");
                allowanceCalPromote.append(new BigDecimal(map.get("allowance_ratio")+"").multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
            }else if (CalParamRuleEnum.RULE_D_GROUP_1.getCode().equals(ruleTypeVal)){//计算规则选择,判断 固定数值
                ruleType =CalParamRuleEnum.RULE_D_GROUP_1.getDesc();
               // intervalRangeFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元");
                allowanceCalPromote.append(new BigDecimal(map.get("allowance_ratio")+"").multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
            }else if(CalParamRuleEnum.RULE_D_GROUP_2.getCode().equals(ruleTypeVal)){//有活动人力
                ruleType =CalParamRuleEnum.RULE_D_GROUP_2.getDesc();
                String activeSalerSign = (String) map.get("active_saler_sign"); //组活力人数标记符号
                if("0".equals(activeSalerSign)){
                    activeSalerSign = "&lt;";
                }else if ("1".equals(activeSalerSign)){
                    activeSalerSign = ">";
                }else if ("2".equals(activeSalerSign)){
                    activeSalerSign = "<=";
                }else if ("3".equals(activeSalerSign)){
                    activeSalerSign = ">=";
                }
                Long activeSalerNum = (Long) map.get("active_saler_num");       //组活力人数
                String relationType = (String) map.get("relation_type");        //组活力人数与FYC设置值关系
                if("0".equals(relationType)){
                    relationType = "或";
                }else if("1".equals(relationType)){
                    relationType = "且";
                }
                underGroupFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元");
                allowanceCalPromote.append(new BigDecimal(map.get("allowance_ratio")+"").multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
                activeSaler.append(activeSalerSign).append(activeSalerNum);
                newMap.put("intervalRangeFyc",underGroupFyc.toString());
                newMap.put("activeSaler",activeSaler.toString());
                newMap.put("relationType",relationType);
            }
            newMap.put("set_item","总价值佣金FYC（元）");
            newMap.put("ruleType",ruleType);
            if(CalParamRuleEnum.RULE_D_GROUP_0.getCode().equals(ruleTypeVal)){ //区间设定值的时候展示
                 newMap.put("intervalRangeFyc",underGroupFyc.toString());
            }
            newMap.put("allowanceCalGroup",allowanceCalPromote.toString());
            newList.add(newMap);

        }
        pageModel.setList(newList);
        pageModel.setTotalRecords(count);
        return pageModel;
    }

    @Override
    public List<CalHisDirectlyUnderGroup> getFirstCalDirectlyUnderGroupByVersionId(Map<String, Object> params) {
        return calHisDirectlyUnderGroupMapper.getFirstCalDirectlyUnderGroupByVersionId(params);
    }
}
