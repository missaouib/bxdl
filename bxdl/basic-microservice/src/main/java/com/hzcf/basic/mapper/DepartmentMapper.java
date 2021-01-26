package com.hzcf.basic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.pojo.basic.Department;
import com.hzcf.pojo.basic.DepartmentExample;

public interface DepartmentMapper {
    int countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer deptId);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer deptId);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    /**
     * 查询所有部门
     * @param paramsCondition 
     * @return
     */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Department findLevelsByDeptId(Integer parentId);
	/**
	 * 查询所有pid为0的部门
	 * @return
	 */
	List<Map<String, Object>> getParentDeptList();
	/**
	 * 根据父id查询子部门
	 * @param object
	 * @return
	 */
	List<Department> queryChildDeptByPidType(String pid);
	
	public Department getDepartmentById(@Param("did")int did);
	public void updateDepartment(Department department);
}