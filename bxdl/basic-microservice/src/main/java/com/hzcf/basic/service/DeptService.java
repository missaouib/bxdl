package com.hzcf.basic.service;

import java.util.List;
import java.util.Map;

import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.Department;

public interface DeptService {


	/**
	 * 查询所有部门
	 * @param paramsCondition 
	 * @return
	 */

	public List<Map<String, Object>> getDeptList(Map<String, Object> paramsCondition);

	/**
	 * 新增部门
	 * @param department
	 * @return
	 */
	public void saveDepartment(Department department);
	
	public List<TreeView> getDeptTree();
	
	 public Department getDepartmentById(int did);
	 public void updateDepartment(Department department);


}
