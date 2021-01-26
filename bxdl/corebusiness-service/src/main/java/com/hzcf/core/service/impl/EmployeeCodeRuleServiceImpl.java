package com.hzcf.core.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzcf.core.mapper.EmployeeCodeRuleMapper;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.service.EmployeeCodeRuleService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.basic.EmployeeCodeRule;
import com.hzcf.pojo.basic.EmployeeCodeRulePojo;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>员工工号规则接口实现</p>
 *
 * @author kongqingbao
 */
@Service
public class EmployeeCodeRuleServiceImpl implements EmployeeCodeRuleService {

    @Autowired
    private EmployeeCodeRuleMapper employeeCodeRuleMapper;
    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;

    private String[] stringArray = new String[]{"0","1","2","3","5","6","7","8","9"};

    @Override
    public void saveEmployeeCodeRule(EmployeeCodeRule employeeCodeRule) {
        String code = employeeCodeRule.getCode();
        employeeCodeRule.setCode(getRandomString(code));
        employeeCodeRuleMapper.insertEmployeeCodeRule(employeeCodeRule);
    }

    private String getRandomString(String code) {
        final int randomNumber = (int) (Math.random() * 9);
        char[] chars = code.toCharArray();
        String tmp = "";
        for(int i = 0; i < chars.length;  i++){
            String s = chars[i] + "";
            if("4".equals(s)){
                s = stringArray[randomNumber];
            }
            tmp = tmp + s;
        }
        return tmp;
    }

    @Override
    public void updateEmployeeCodeRule(EmployeeCodeRule employeeCodeRule) {
        String code = employeeCodeRule.getCode();
        employeeCodeRule.setCode(getRandomString(code));
        employeeCodeRuleMapper.updateEmployeeCodeRule(employeeCodeRule);
    }

    @Override
    public PageModel findEmployeeCodeRulePage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("rows", model.getPageSize());

        long totalCount = employeeCodeRuleMapper.queryEmployeeCodeRuleTotal(params);
        if (totalCount > 0) {
            List<EmployeeCodeRule> employeeCodeRules = employeeCodeRuleMapper.queryEmployeeCodeRulePage(params);
            if (!employeeCodeRules.isEmpty()) {
                ArrayList<EmployeeCodeRulePojo> result = setSaleOrgName(employeeCodeRules);
                model.setList(result);
                model.setTotalRecords(totalCount);
            } else {
                model.setTotalRecords(0L);
                model.setList(Collections.emptyList());
            }
        } else {
            model.setTotalRecords(0L);
            model.setList(Collections.emptyList());
        }
        return model;
    }

    private ArrayList<EmployeeCodeRulePojo> setSaleOrgName(List<EmployeeCodeRule> employeeCodeRules) {
        ArrayList<EmployeeCodeRulePojo> result = Lists.newArrayListWithExpectedSize(employeeCodeRules.size());
        employeeCodeRules.forEach(a -> {
            EmployeeCodeRulePojo pojo = new EmployeeCodeRulePojo();
            BeanUtils.copyProperties(a, pojo);
            if (StringUtils.isNotBlank(a.getMapId()) && 2 == a.getType()) {
                Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
                map.put("salesOrgId", a.getMapId());
                SalesOrgInfo salesOrgInfo = salesOrgInfoMapper.selectById(map);
                if (null != salesOrgInfo) {
                    pojo.setMapName(salesOrgInfo.getSalesOrgName());
                }
            }
            result.add(pojo);
        });
        return result;
    }

    @Override
    public List<EmployeeCodeRulePojo> findEmployeeCodeRuleList(Map<String, Object> params) {
        List<EmployeeCodeRule> list = employeeCodeRuleMapper.queryEmployeeCodeRuleList(params);
        if (!list.isEmpty()) {
            return setSaleOrgName(list);
        }
        return Collections.emptyList();
    }

    @Override
    public EmployeeCodeRulePojo findEmployeeCodeRule(String mapId, byte type) {
        EmployeeCodeRule employeeCodeRule = employeeCodeRuleMapper.queryEmployeeCodeRuleByTypeAndMapId(mapId, type);
        if (null != employeeCodeRule) {
            return getOrgEmployeeCodeRulePojo(employeeCodeRule);
        }
        return null;
    }

    @Override
    public boolean findExistEmployeeCodeRuleList(Map<String, Object> params) {
        List<EmployeeCodeRule> employeeCodeRules = employeeCodeRuleMapper.queryExistEmployeeCodeRuleList(params);
        return !employeeCodeRules.isEmpty();
    }

    @Override
    public EmployeeCodeRulePojo findEmployeeCodeRule(int id) {
        EmployeeCodeRule employeeCodeRule = employeeCodeRuleMapper.queryEmployeeCodeRule(id);
        if (null != employeeCodeRule) {
            return getOrgEmployeeCodeRulePojo(employeeCodeRule);
        }
        return null;
    }

    private EmployeeCodeRulePojo getOrgEmployeeCodeRulePojo(EmployeeCodeRule employeeCodeRule) {
        EmployeeCodeRulePojo pojo = new EmployeeCodeRulePojo();
        BeanUtils.copyProperties(employeeCodeRule, pojo);
        if (StringUtils.isNotBlank(employeeCodeRule.getMapId()) && 2 == employeeCodeRule.getType()) {
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
            map.put("salesOrgId", employeeCodeRule.getMapId());
            SalesOrgInfo salesOrgInfo = salesOrgInfoMapper.selectById(map);
            if (null != salesOrgInfo) {
                pojo.setMapName(salesOrgInfo.getSalesOrgName());
            }
        }
        return pojo;
    }
}
