package com.hzcf.basic.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.basic.service.LoginService;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.Menu;
import com.hzcf.pojo.basic.Role;

@RestController
@RequestMapping("/login")
public class LoginController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginService loginService;
	

	/**
	   * 根据员工ID查询角色信息
	   * @param employeeId
	   * @return
	   */
	 @RequestMapping("/findRoleByEmployeeId/{employeeId}")
	  public List<Role> findRoleByEmployeeId(@PathVariable int employeeId) {
		  return loginService.findRoleByEmployeeId(employeeId);
	  }
	  
	  /**
	   * 根据员工角色id 查询权限
	   * @param employeeId
	   * @return
	   */
	  @RequestMapping("/permissionListRoleId/{employeeId}")
	  public List<Menu> permissionListRoleId(@PathVariable int employeeId) {
		  return loginService.permissionListRoleId(employeeId);
	  }
	  
	  
	  /**
	   * 通过员工编号查询员工信息
	   * @param employeeId
	   * @return
	   */
	  @RequestMapping("/getEmployeeByEmployeeNo/{employeeNo}")
	  public Employee getEmployeeByEmployeeNo(@PathVariable String employeeNo) {
		  return loginService.getEmployeeByEmployeeNo(employeeNo);
	  }
	  
	 
}
