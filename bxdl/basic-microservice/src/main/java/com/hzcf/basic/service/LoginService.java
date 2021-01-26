package com.hzcf.basic.service;

import java.util.List;

import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.Menu;
import com.hzcf.pojo.basic.Role;

public interface LoginService {
	
	

	/**
	 * 根据员工id查询角色信息
	 * @param id
	 * @return
	 */
	public List<Role> findRoleByEmployeeId(Integer id);

	/**
	 * 根据员工角色id 查询权限
	 * @param id
	 * @return
	 */
	public List<Menu> permissionListRoleId(Integer id);

	/**
	 * 
	 * 根据登陆账号查询用户信息
	 * @param employeeNo
	 * @return
	 */
	public Employee getEmployeeByEmployeeNo(String employeeNo);


	
}
