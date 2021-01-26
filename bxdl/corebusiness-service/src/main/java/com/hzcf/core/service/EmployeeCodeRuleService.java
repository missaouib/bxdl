package com.hzcf.core.service;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.basic.EmployeeCodeRule;
import com.hzcf.pojo.basic.EmployeeCodeRulePojo;

import java.util.List;
import java.util.Map;

/**
 * <p>员工工号规则接口</p>
 *
 * @author kongqingbao
 */
public interface EmployeeCodeRuleService {

    /**
     * <p>新增序号规则</p>
     * @param employeeCodeRule
     */
    void saveEmployeeCodeRule(EmployeeCodeRule employeeCodeRule);

    /**
     * <p>更新序号规则</p>
     * @param employeeCodeRule
     */
    void updateEmployeeCodeRule(EmployeeCodeRule employeeCodeRule);

    /**
     * <p>分页查询序号规则</p>
     * @param params
     * @return
     */
    PageModel findEmployeeCodeRulePage(Map<String, Object> params);


    /**
     * <p>查询序号规则List</p>
     * @param params
     * @return
     */
    List<EmployeeCodeRulePojo> findEmployeeCodeRuleList(Map<String, Object> params);

    /**
     * <p>根据类型和关联id查询</p>
     * @param mapId 关联id
     * @param type  类型
     * @return
     */
    EmployeeCodeRulePojo findEmployeeCodeRule(String mapId, byte type);

    /**
     * <>查询除本身以外是否存在相同记录</>
     * @param params
     * @return  true 存在 false 不存在
     */
    boolean findExistEmployeeCodeRuleList(Map<String, Object> params);

    /**
     * <p>根据id查询序号规则</p>
     * @param id
     * @return
     */
    EmployeeCodeRulePojo findEmployeeCodeRule(int id);
}
