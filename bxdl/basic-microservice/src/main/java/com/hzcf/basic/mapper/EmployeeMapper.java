package com.hzcf.basic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.EmployeeExample;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer employeeId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer employeeId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    /**
  	 * 根据用户登陆账号查询用户信息
  	 */
	Employee getEmployeeByEmployeeNo(Map<String, Object> map);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	/**
	 * 更改员工状态（1：启用 2：删除 3：禁用）
	 * @param map
	 */
	void deleteEmployee(Map<String, Object> map);

    /**
     * 通过employeeIds获取系统用户信息
     * @param employeeIds
     * @return
     */
    List<Map<String,Object>> getEmployeeInfoByEmployeeIds(String employeeIds);
}