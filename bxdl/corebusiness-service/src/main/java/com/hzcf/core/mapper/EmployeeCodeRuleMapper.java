package com.hzcf.core.mapper;

import com.hzcf.pojo.basic.EmployeeCodeRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeCodeRuleMapper {


    /**
     * <p>新增序号规则</p>
     * @param employeeCodeRule
     */
    void insertEmployeeCodeRule(EmployeeCodeRule employeeCodeRule);

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
    List<EmployeeCodeRule> queryEmployeeCodeRulePage(Map params);

    /**
     * <p>查询序号规则数量</p>
     * @param params
     * @return
     */
    long queryEmployeeCodeRuleTotal(Map params);

    /**
     * <p>查询序号规则List</p>
     * @param params
     * @return
     */
    List<EmployeeCodeRule> queryEmployeeCodeRuleList(Map params);

    /**
     * <p>查询除本身以外是否存在相同记录</p>
     * @param params
     * @return
     */
    List<EmployeeCodeRule> queryExistEmployeeCodeRuleList(Map params);

    /**
     * <p>根据类型和关联id查询</p>
     * @param mapId 关联id
     * @param type  类型
     * @return
     */
    EmployeeCodeRule queryEmployeeCodeRuleByTypeAndMapId(@Param("mapId")String mapId, @Param("type")byte type);


    /**
     * <p>根据id查询序号规则</p>
     * @param id
     * @return
     */
    EmployeeCodeRule queryEmployeeCodeRule(@Param("id")int id);


}
