package com.hzcf.basic.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.basic.service.EmployeeService;
import com.hzcf.basic.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.Role;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private EmployeeService employeeService;

	/**
	 * 修改员工 回显
	 * 
	 * @return
	 */
	@RequestMapping("/getEmployeeById")
	public Employee getEmployeeById(@RequestParam("employeeId") int employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	/**
	 * 分页查询所有员工
	 * 
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEmployeeList")
	public PageModel getEmployeeList(@RequestParam Map<String, Object> paramsCondition) {
		logger.info("***********basic服务 分页查询所有员工***********");
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));	
		return employeeService.getEmployeeList(paramsCondition);
	}
	
	/**
	 * 根据账号查询员工信息
	 * 
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEmployeeByEmployeeNo")
	public Employee getEmployeeByEmployeeNo(@RequestParam Map<String, Object> paramsCondition) {
		return employeeService.getEmployeeByEmployeeNo(paramsCondition);
	}
	
	/**
	 * 查询员工拥有的角色
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRole")
	public List<Role> getRoleList() {
		List<Role> rlist = employeeService.getRoleList();
		return rlist;
	}

	/**
	 * 查询员工拥有的角色
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRoleList")
	public List<Integer> getRoleByEmployeeId(@RequestParam("employeeId") int employeeId) {
		List<Integer> employeeRoleList = employeeService.getRoleByEmployeeId(employeeId);
		return employeeRoleList;
	}

	/**
	 * 校验商户原始密码是否正确
	 * 
	 * @return
	 */
	@RequestMapping("/checkOldPwd")
	@ResponseBody
	public boolean checkOldPwd(@RequestParam("employeeId") int employeeId, @RequestParam("oldPwd") String oldPwd,@RequestParam("newPs") String newPs) {
		return employeeService.checkOldPwd(employeeId, oldPwd,newPs);
	}

	/**
	 * 修改用户密码
	 * 
	 * @return
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public void updatePwd(@RequestParam("employeeId") int employeeId, @RequestParam("newPwd") String newPwd,@RequestParam("newPs")String newPs) {
		employeeService.updatePwd(employeeId, newPwd,newPs);

	}

	/**
	 * 修改员工角色
	 * 
	 * @param EmployeeRole
	 * @param rids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateEmployeeRole")
	public void updateEmployeeRole(@RequestParam("employeeId") int employeeId, @RequestParam("rid") String rids) {

		employeeService.updateEmployeeRole(employeeId, rids);
	}

	/**
	 * 添加员工
	 * 
	 * @return
	 */
	@RequestMapping("/saveEmployee")
	@ResponseBody
	public void saveEmployee(@RequestBody Employee employee,@RequestParam("newPs")String newPs,@RequestParam("uuid")String uuid) {
		employeeService.saveEmployee(employee,newPs,uuid);

	}

	/**
	 * 修改员工信息
	 * 
	 * @return
	 */
	@RequestMapping("/updateEmployee")
	@ResponseBody
	public void updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
	}

	/**
	 * 更改员工状态（1：启用 2：删除 3：禁用）
	 * @param merchant
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteEmployee")
	public void deleteEmployee(@RequestBody Employee employee,@RequestParam("activatedState")String activatedState) {
		
		
		employeeService.deleteEmployee(employee,activatedState);

	}

	/**
	 * 通过employeeIds获取系统用户信息
	 * @param employeeIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEmployeeInfoByEmployeeIds")
	public List<Map<String,Object>> getEmployeeInfoByEmployeeIds(
			@RequestParam("employeeIds")  String employeeIds) {

		return employeeService.getEmployeeInfoByEmployeeIds(employeeIds);

	}
}
