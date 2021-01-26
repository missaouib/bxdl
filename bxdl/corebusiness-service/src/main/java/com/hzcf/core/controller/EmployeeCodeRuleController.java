package com.hzcf.core.controller;

import com.hzcf.core.service.EmployeeCodeRuleService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.basic.EmployeeCodeRule;
import com.hzcf.pojo.basic.EmployeeCodeRulePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>员工工号规则Controller</p>
 *
 * @author kongqingbao
 */
@RestController
public class EmployeeCodeRuleController {

    @Autowired
    private EmployeeCodeRuleService employeeCodeRuleService;

    /**
     * <p>新增序号规则</p>
     * @param employeeCodeRule
     */
    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.POST)
    public void saveEmployeeCodeRule(@RequestBody EmployeeCodeRule employeeCodeRule){
        employeeCodeRuleService.saveEmployeeCodeRule(employeeCodeRule);
    }

    /**
     * <p>更新序号规则</p>
     * @param employeeCodeRule
     */
    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.PUT)
    public void updateEmployeeCodeRule(@RequestBody EmployeeCodeRule employeeCodeRule){
        employeeCodeRuleService.updateEmployeeCodeRule(employeeCodeRule);
    }

    /**
     * <p>分页查询序号规则</p>
     * @param params
     * @return
     */
    @RequestMapping(value = "/employeeCodeRule/page", method = RequestMethod.GET)
    public PageModel findEmployeeCodeRulePage(@RequestParam Map<String, Object> params){
        return employeeCodeRuleService.findEmployeeCodeRulePage(params);
    }


    /**
     * <p>查询序号规则List</p>
     * @param params
     * @return
     */
    @RequestMapping(value = "/employeeCodeRules", method = RequestMethod.GET)
    public List<EmployeeCodeRulePojo> findEmployeeCodeRuleList(@RequestParam Map<String, Object> params){
        return employeeCodeRuleService.findEmployeeCodeRuleList(params);
    }

    /**
     * <p>根据类型和关联id查询</p>
     * @param mapId
     * @param type
     * @return
     */
    @RequestMapping(value = "/empCodeRule", method = RequestMethod.GET)
    public EmployeeCodeRulePojo findEmployeeCodeRule(@RequestParam("mapId") String mapId, @RequestParam("type") byte type){
        return employeeCodeRuleService.findEmployeeCodeRule(mapId, type);
    }

    /**
     * <p>查询序号规则是否存在</p>
     * @param params
     * @return
     */
    @RequestMapping(value = "/employeeCodeRules/exist", method = RequestMethod.GET)
    public boolean findExistEmployeeCodeRuleList(@RequestParam Map<String, Object> params){
        return employeeCodeRuleService.findExistEmployeeCodeRuleList(params);
    }

    /**
     * <p>根据id查询序号规则</p>
     * @param id
     * @return
     */
    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.GET)
    public EmployeeCodeRulePojo findEmployeeCodeRule(@RequestParam("ruleId") int id){
        return employeeCodeRuleService.findEmployeeCodeRule(id);

    }

}
