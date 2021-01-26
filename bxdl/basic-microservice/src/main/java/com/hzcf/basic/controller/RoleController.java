package com.hzcf.basic.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.basic.service.RoleService;
import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.Role;

/**
 * 角色管理
 * 
 * @author tie
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleService roleService;

	/**
	 * 跳转到角色列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/goRolePage")
	public String goRolePage() {
		return "roleList";
	}

	/**
	 * 分页查询所有角色
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRoleList")
	public PageModel getRoleList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return roleService.getRoleList(paramsCondition);
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRole")
	public void saveRole(@RequestBody Role role, @RequestParam("employeeId") int employeeId) {

		roleService.saveRole(role, employeeId);
	}

	/**
	 * 校验角色编码是否存在
	 * 
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkRoleCode")
	public Role checkRoleCode(@RequestParam Map<String, Object> map) {
		return roleService.checkRoleCodeIsRepeat(map);

	}

	/**
	 * 修改回显
	 */

	@ResponseBody
	@RequestMapping("/getRoleById")
	public Role getRoleById(@RequestParam("rid") int id, Model model) {
			return  roleService.quertRoleById(id);
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateRole")
	public void updateRole(@RequestBody Role role, @RequestParam("employeeId") int employeeId) {
		roleService.updateRole(role, employeeId);
	}

	/**
	 * 删除菜单
	 * 
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRole")
	public void deleteRole(@RequestBody Role role, @RequestParam("employeeId") int employeeId) {
		roleService.deleteRole(role, employeeId);

	}

	/**
	 * 加载权限菜单
	 * 
	 * @param rid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/viewTree")
	public List<TreeView> getRoleTree(@RequestParam("rid") int rid) {
			return roleService.getViewTree(rid);
	}

	/**
	 * 修改角色权限
	 */

	@ResponseBody
	@RequestMapping("/updateRoleAuth")
	public void updateRoleAuth(@RequestParam("rid") int rid, @RequestParam("menuIds") String menuIds,
			@RequestParam("employeeId") int employeeId) {
		roleService.updateRoleAuth(rid, menuIds, employeeId);
	}

}
