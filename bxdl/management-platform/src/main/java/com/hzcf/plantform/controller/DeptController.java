package com.hzcf.plantform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.DeptFeignClient;
import com.hzcf.plantform.util.TreeView;
import com.hzcf.pojo.basic.Department;
import com.hzcf.pojo.basic.Employee;

	
/**
*<dl>
*<dt>类名：MenuController.java</dt>
*<dd>描述: 部门管理业务逻辑实现</dd>
*<dd>创建时间： 2018年10月23日 上午10:58:11</dd>
*<dd>创建人：tie</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2018年10月23日 上午10:58:11 tie 1.0 1.0 Version
* </pre>
*</dl>
*/
@Controller
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	private DeptFeignClient deptFeignClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 跳转到部门列表页面
	 * 
	 * @return
	 */
	@RequiresPermissions("deptManager:list") // 权限管理;
	@RequestMapping("/goDeptPage")
	public String goMenuPage() {
		return "system/dept/deptList";
	}

	

	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDeptList")
	public List<Map<String, Object>> getMenuList(@RequestParam Map<String, Object> paramsCondition) {
		try {
			/*Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String deptName = StringUtil.trim(request.getParameter("deptName"));
			paramsCondition.put("deptName", deptName);*/
			return  deptFeignClient.getDeptList(paramsCondition);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 添加部门
	 * @return
	 */
	@RequiresPermissions("departmentManager:add")//权限管理;
	@RequestMapping("/saveDepartment")
	@ResponseBody
	public boolean saveDepartment(Department department){
		try {
			
			Subject subject = SecurityUtils.getSubject();
			Employee employee = (Employee) subject.getPrincipal();
		    department.setOperator(employee.getEmployeeId());
			deptFeignClient.saveDepartment(department);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 加载部门tree
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDeptTree")
	public List<TreeView> getDeptTree(){
		try {
			return deptFeignClient.getDeptTree();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 修改回显
	 */
	@ResponseBody
	@RequestMapping("/getDepartmentById")
	public  Map<String,Object> getDepartmentById(@RequestParam("did") int did) {
		try {
			Map<String,Object>map=new HashMap<String, Object>();
			Department department = deptFeignClient.getDepartmentById(did);
			map.put("department", department);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 修改部门
	 * @return
	 */
	@RequiresPermissions("departmentManager:update")//权限管理;
	@RequestMapping("/updateDepartment")
	@ResponseBody
	public boolean updateDepartment(Department department){
		try {
			
  			Subject subject = SecurityUtils.getSubject();
			Employee employee = (Employee) subject.getPrincipal();
		    department.setOperator(employee.getEmployeeId());
			deptFeignClient.updateDepartment(department);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
