package com.hzcf.core.service.cal;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalParamRuleConfig;

import java.util.List;
import java.util.Map;

public interface CalParamRuleConfigService {

    PageModel getListByPage(Map<String, Object> params);

    List<CalParamRuleConfig> getCalParamRuleList(Map<String, Object> params);

    Integer saveCalParamRuleConfig(CalParamRuleConfig calParamRuleConfig);

    Long getCalParamRuleCountByCondition(Map<String, Object> params);

    CalParamRuleConfig getCalParamRuleById(Long id);
}
