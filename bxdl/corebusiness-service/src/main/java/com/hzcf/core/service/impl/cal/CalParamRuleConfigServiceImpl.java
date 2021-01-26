package com.hzcf.core.service.impl.cal;

import com.hzcf.core.mapper.cal.CalParamRuleConfigMapper;
import com.hzcf.core.service.cal.CalParamRuleConfigService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalParamRuleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 基本法-规则参数实现类
 */
@SuppressWarnings("ALL")
@Service
public class CalParamRuleConfigServiceImpl implements CalParamRuleConfigService {

    @Autowired
    CalParamRuleConfigMapper calParmRuleConfigMapper;

    @Override
    public PageModel getListByPage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex",model.getPageSize());

        List<Map<String,Object>> list = calParmRuleConfigMapper.getListByPage(params);
        Long totalSize = calParmRuleConfigMapper.getTotalSize(params);
        model.setList(list);
        model.setTotalRecords(totalSize);
        return model;
    }

    @Override
    public List<CalParamRuleConfig> getCalParamRuleList(Map<String, Object> params) {
        return calParmRuleConfigMapper.getCalParamRuleList(params);
    }

    @Override
    public Integer saveCalParamRuleConfig(CalParamRuleConfig calParamRuleConfig) {
        Long id = calParamRuleConfig.getId();
        if(id == null || id == 0){
            return calParmRuleConfigMapper.insertIgnoreNull(calParamRuleConfig);
        }
        return calParmRuleConfigMapper.updateIgnoreNull(calParamRuleConfig);
    }

    @Override
    public Long getCalParamRuleCountByCondition(Map<String, Object> params) {
        return calParmRuleConfigMapper.getTotalSize(params);
    }

    @Override
    public CalParamRuleConfig getCalParamRuleById(Long id) {
        return calParmRuleConfigMapper.getById(id);
    }


}
