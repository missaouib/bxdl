package com.hzcf.plantform.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.util.TreeView;
import com.hzcf.pojo.basic.Department;



/**
*<dl>
*<dt>类名：DeptFeignClient.java</dt>
*<dd>描述: 部门管理业务逻辑实现</dd>
*<dd>创建时间： 2018年10月23日 下午3:45:29</dd>
*<dd>创建人：tie</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2018年10月23日 下午3:45:29 tie 1.0 1.0 Version
* </pre>
*</dl>
*/
@FeignClient(name = "basic-microservice", fallback =FeignDisableHystrixConfiguration.class)
public interface  DeptFeignClient {

	
	/**
	 * 查询所有部门
	 * @param paramsCondition 
	 * @return
	 */
	  @RequestMapping(value = "/dept/getDeptList", method = RequestMethod.POST)
	  public List<Map<String,Object>> getDeptList(@RequestParam Map<String, Object> paramsCondition);

	  /**
	   * 新增部门
	   * @param department
	   */
	  @RequestMapping(value = "/dept/saveDepartment", method = RequestMethod.POST)
	  public void saveDepartment(Department department);
	  /**
	   * 查询部门tree
	   * @param department
	   */
	  @RequestMapping(value = "/dept/getDeptTree", method = RequestMethod.GET)
	  public List<TreeView> getDeptTree();
	  /**
	   * 修改回显
	   * @param List<Map<String, Object>>
	   */
	  @RequestMapping(value = "/dept/getDepartmentById", method = RequestMethod.GET)
	  public Department getDepartmentById(@RequestParam("did") int did);
	  /**
	   * 修改部门
	   * @param department
	   */
	  @RequestMapping(value = "/dept/updateDepartment", method = RequestMethod.POST)
	  public void updateDepartment(@RequestBody Department department);
}
