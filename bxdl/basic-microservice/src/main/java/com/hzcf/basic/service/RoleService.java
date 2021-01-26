package com.hzcf.basic.service;

import java.util.List;
import java.util.Map;

import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.TreeView;
import com.hzcf.basic.util.ViewTree;
import com.hzcf.pojo.basic.Role;


public interface RoleService {
	/**
	 * 分页查询所有角色
	 * @param paramsCondition
	 * @return
	 */
	PageModel getRoleList(Map<String, Object> paramsCondition);
	/**
	 * 根据角色编码校验是否有重复
	 * @param trim
	 * @return
	 */
	Role checkRoleCodeIsRepeat(Map<String, Object> map);
	/**
	 * 添加角色
	 */
	void saveRole(Role role,int employeeId);
	/**
	 * 修改回显
	 * @param id
	 * @return
	 */
	Role quertRoleById(int id);
	/**
	 * 修改角色
	 * @param role
	 */
	void updateRole(Role role,int employeeId);
	/**
	 * 删除菜单
	 * @param role
	 */
	void deleteRole(Role role,int employeeId);
	/**
	 * 加载权限菜单
	 * @param rid
	 * @return
	 */
	List<TreeView> getViewTree(int rid);
	/**
	 * 修改角色权限
	 * @param rid
	 * @param menuIds
	 * @return
	 */
	void updateRoleAuth(int rid, String menuIds,int employeeId);
	/**
	 * 获取当前用户角色
	 * @param role
	 */
	List<Integer> getRoleByEmployeeId(int employeeId);
}
