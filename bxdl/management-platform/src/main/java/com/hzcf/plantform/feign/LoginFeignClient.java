package com.hzcf.plantform.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.Menu;
import com.hzcf.pojo.basic.Role;


/**
 * 
 *<dl>
 *<dt>类名：LoginFeignClient.java</dt>
 *<dd>
 * 描述: 登录管理FeignClient	
 * 示例：该类禁用hystrix
 *</dd> 
 *<dd>创建时间： 2018年8月9日 下午2:54:54</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2018年8月9日 下午2:54:54    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@FeignClient(name = "basic-microservice", configuration=FeignDisableHystrixConfiguration.class)
public interface  LoginFeignClient {

	 /**
	 * 根据员工id获取菜单
	 * getMenuByEmployeeId是服务提供者中的方法名
	 * @param id
	 * @return
	 */
	 @RequestMapping(value = "/menu/getMenuByEmployeeId/{employeeId}", method = RequestMethod.GET)
	  public List<Menu> getMenuByEmployeeId( @PathVariable("employeeId") int employeeId);
	  
	  /**
	   * 根据员工ID查询角色信息
	   * @param employeeId
	   * @return
	   */
	  @RequestMapping(value = "/login/findRoleByEmployeeId/{employeeId}", method = RequestMethod.POST)
	  public List<Role> findRoleByEmployeeId(@PathVariable("employeeId") int employeeId);
	  
	  /**
	   * 根据员工角色id 查询权限
	   * @param employeeId
	   * @return
	   */
	  @RequestMapping(value = "/login/permissionListRoleId/{employeeId}", method = RequestMethod.POST)
	  public List<Menu> permissionListRoleId(@PathVariable("employeeId") int employeeId);
	  
	  
	  /**
	   * 通过员工编号查询员工信息
	   * @param employeeId
	   * @return
	   */
	  @RequestMapping(value = "/login/getEmployeeByEmployeeNo/{employeeNo}", method = RequestMethod.GET)
	  public Employee getEmployeeByEmployeeNo(@PathVariable("employeeNo") String employeeNo);
	  
}
