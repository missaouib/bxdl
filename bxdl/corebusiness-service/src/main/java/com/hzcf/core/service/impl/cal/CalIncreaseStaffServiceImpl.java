package com.hzcf.core.service.impl.cal;

import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.core.mapper.cal.CalHisIncreaseStaffMapper;
import com.hzcf.core.mapper.cal.CalIncreaseStaffMapper;
import com.hzcf.core.service.cal.CalIncreaseStaffService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalHisIncreaseStaff;
import com.hzcf.pojo.cal.CalIncreaseStaff;
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
public class CalIncreaseStaffServiceImpl implements CalIncreaseStaffService {
    @Autowired
    private CalIncreaseStaffMapper calIncreaseStaffMapper;
    @Autowired
    private CalHisIncreaseStaffMapper calHisIncreaseStaffMapper;
    @Override
    public List<CalIncreaseStaff> getCalIncreaseStaffByOrgId(Map<String, Object> par) {
        return calIncreaseStaffMapper.getCalIncreaseStaffByOrgId(par);
    }

    @Override
    public int updateIncreaseState(CalIncreaseStaff calIncreaseStaff) {

        return calIncreaseStaffMapper.updateIgnoreNull(calIncreaseStaff);
    }

    @Override
    public int insertIgnoreNull(CalIncreaseStaff calIncreaseStaff) {

        return calIncreaseStaffMapper.insertIgnoreNull(calIncreaseStaff);

    }


    @Override
    public PageModel getCalHisIncreasePage(Map<String, Object> params) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        pageModel.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", pageModel.getStartIndex());
        params.put("endIndex", pageModel.getEndIndex());
        // 查询数据
        List<Map<String, Object>> list = calHisIncreaseStaffMapper.getCalHisIncreaseByVersionId(params);
        // 查询条数
        Long count =calHisIncreaseStaffMapper.getCalHisIncreaseCount(params);
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list) {
            Map<String,Object> newMap = new HashMap<String,Object>();
            String ruleTypeVal = (String) map.get("rule_type");//计算规则选择
            BigDecimal minValue = (BigDecimal) map.get("min_value");//下限值
            BigDecimal maxValue = (BigDecimal) map.get("max_value");//上限值
            String minSign = (String) map.get("min_sign");//下限标记符号（0：大于，1：大于等于）
            String maxSign = (String) map.get("max_sign");//上限标记符号（0：小于，1：小于等于）
            if("0".equals(minSign)){
                minSign = "&lt;";  // html 中 转义  <
            }else if ("1".equals(minSign)){
                minSign = "<=";
            }
            if("0".equals(maxSign)){
                maxSign = "&lt;";  // html 中 转义  <
            }else if ("1".equals(maxSign)){
                maxSign = "<=";
            }
            String ruleType = "";
            StringBuffer intervalRangeFyc = new StringBuffer();//FYC区间设定值
            StringBuffer allowanceCalPromote = new StringBuffer();//津贴
            if (CalParamRuleEnum.RULE_IN_0.getCode().equals(ruleTypeVal)) {//计算规则选择,判断 区间设定值
                ruleType =CalParamRuleEnum.RULE_IN_0.getDesc();
                intervalRangeFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元");
                allowanceCalPromote.append(new BigDecimal(map.get("allowance_ratio")+"").multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
            }else if (CalParamRuleEnum.RULE_IN_1.getCode().equals(ruleTypeVal)){//计算规则选择,判断 固定数值
                ruleType =CalParamRuleEnum.RULE_IN_1.getDesc();
               // intervalRangeFyc.append(minValue.stripTrailingZeros().toPlainString()).append("元").append(minSign).append("FYC").append(maxSign).append(maxValue.stripTrailingZeros().toPlainString()).append("元");
                allowanceCalPromote.append(new BigDecimal(map.get("allowance_ratio")+"").multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
            }
            newMap.put("set_item","总价值佣金FYC（元）");
            newMap.put("ruleType",ruleType);
            if(CalParamRuleEnum.RULE_IN_0.getCode().equals(ruleTypeVal)){ //区间设定值的时候展示
                 newMap.put("intervalRangeFyc",intervalRangeFyc.toString());
            }
            newMap.put("allowanceCalIncrease",allowanceCalPromote.toString());
            newList.add(newMap);
        }
        pageModel.setList(newList);
        pageModel.setTotalRecords(count);
        return pageModel;
    }

    @Override
    public List<CalHisIncreaseStaff> getFirstCalHisIncreaseByVersionId(Map<String, Object> params) {
        return calHisIncreaseStaffMapper.getFirstCalHisIncreaseByVersionId(params);
    }


}
