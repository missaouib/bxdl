package com.hzcf.plantform.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.Role;

/**
 *<dl>
 *<dt>类名：EmployeeFeignClient.java</dt>
 *<dd>描述: 员工管理逻辑实现</dd> 
 *<dd>创建时间： 2018年8月13日 下午5:21:53</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2018年8月13日 下午5:21:53    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@FeignClient(name = "basic-microservice", configuration=FeignDisableHystrixConfiguration.class)
public interface  EmployeeFeignClient {

	
    /**
	 * 分页查询所有员工
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	 @RequestMapping(value = "/employee/getEmployeeList", method = RequestMethod.POST)
	 public PageModel getEmployeeList(@RequestParam Map<String, Object> paramsCondition);

    /**
	 * 查询员工拥有的角色
	 * @param id
	 * @return
	 */
	 @RequestMapping(value = "/employee/getRole", method = RequestMethod.POST)
	 public List<Role> getRoleList();

     /**
	 * 查询员工拥有的角色
	 * @param id
	 * @return
	 */
	 @RequestMapping(value = "/employee/getRoleList", method = RequestMethod.POST)
	 public List<Integer> getRoleByEmployeeId(@RequestParam("employeeId")int employeeId);
    /**
	 * 校验原始密码是否正确
	 * @return
	 */
	 @RequestMapping(value = "/employee/checkOldPwd", method = RequestMethod.POST)
	 public boolean checkOldPwd(@RequestParam("employeeId")int employeeId,@RequestParam("oldPwd") String oldPwd,@RequestParam("newPs") String newPs);
	 
    /**
	 * 修改用户密码
	 * @return
	 */
	 @RequestMapping(value = "/employee/updatePwd", method = RequestMethod.POST)
	public void updatePwd(@RequestParam("employeeId")int employeeId, @RequestParam("newPwd")String newPwd,@RequestParam("newPs")String newPs);

    /**
	 * 修改员工角色
	 * @param merchantId
	 * @param rids
	 * @return
	 */
	@RequestMapping(value = "/employee/updateEmployeeRole", method = RequestMethod.POST)
	public void updateEmployeeRole(@RequestParam("employeeId")int employeeId, @RequestParam("rid")String rids);
	
	/**
	 * 添加员工
	 * @return
	 */
	@RequestMapping(value = "/employee/saveEmployee", method = RequestMethod.POST)
	public void saveEmployee(@RequestBody Employee employee,@RequestParam("newPs")String newPs,@RequestParam("uuid")String uuid);

	/**
	 * 修改员工回显
	 * @return
	 */
	@RequestMapping(value = "/employee/getEmployeeById", method = RequestMethod.POST)
	public Employee getEmployeeById(@RequestParam("employeeId")int employeeId);
	/**
	 * 修改员工信息
	 * @return
	 */
	@RequestMapping(value = "/employee/updateEmployee", method = RequestMethod.POST)
	public void updateEmployee(@RequestBody Employee employee);

	/**
	 * 更改员工状态（1：启用 2：删除 3：禁用）
	 * @param activatedState 
	 * @param merchant
	 * @return
	 */
	@RequestMapping(value = "/employee/deleteEmployee", method = RequestMethod.POST)
	public void deleteEmployee(@RequestBody Employee employee, @RequestParam("activatedState")String activatedState);
	/**
	 * 修改员工信息
	 * @return
	 */
	@RequestMapping(value = "/employee/getEmployeeByEmployeeNo", method = RequestMethod.POST)
	public Employee getEmployeeByEmployeeNo(@RequestParam Map<String, Object> emap);

	@RequestMapping(value = "/employee/getEmployeeInfoByEmployeeIds", method = RequestMethod.POST)
    List<Map<String,Object>> getEmployeeInfoByEmployeeIds(@RequestParam("employeeIds")  String employeeIds);
}
