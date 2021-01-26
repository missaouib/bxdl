package com.hzcf.plantform.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.util.PageModel;
import com.hzcf.plantform.util.TreeView;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.Menu;
import com.hzcf.pojo.basic.Role;


/**
 * 
 *<dl>
 *<dt>类名：RoleFeignClient.java</dt>
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
public interface  RoleFeignClient {

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
	  @RequestMapping(value = "/login/permissionListRoleId/{employeeId}", method = RequestMethod.GET)
	  public List<Menu> permissionListRoleId(@PathVariable("employeeId") int employeeId);
	  
	  
	  /**
	   * 通过员工编号查询员工信息
	   * @param employeeId
	   * @return
	   */
	  @RequestMapping(value = "/login/getEmployeeByEmployeeNo/{employeeNo}", method = RequestMethod.GET)
	  public Employee getEmployeeByEmployeeNo(@PathVariable("employeeNo") String employeeNo);

	    /**
		 * 分页查询所有角色
		 * @param pageNumber
		 * @param pageSize
		 * @return
		 */
	   @RequestMapping(value = "/role/getRoleList", method = RequestMethod.POST)
	   public PageModel getRoleList(@RequestParam Map<String, Object> paramsCondition);
	    /**
		 * 添加角色
		 * @param role
		 * @return
		 */
	   @RequestMapping(value = "/role/saveRole", method = RequestMethod.POST)
	   public void saveRole(@RequestBody Role role,@RequestParam("employeeId") int employeeId);

	   /**
		 * 校验角色编码是否存在
		 * @param menu
		 * @return
		 */
	   @RequestMapping(value = "/role/checkRoleCode", method = RequestMethod.POST)
	   public Role checkRoleCodeIsRepeat(@RequestParam Map<String, Object> map);
	   
	    /**
		 * 修改回显
		 */
	   @RequestMapping(value = "/role/getRoleById", method = RequestMethod.POST)
	   public Role quertRoleById(@RequestParam("rid") int id);

	   /**
		 * 修改角色
		 * @param role
		 * @return
		 */
	   @RequestMapping(value = "/role/updateRole", method = RequestMethod.POST)
		public void updateRole(@RequestBody Role role,@RequestParam("employeeId") int employeeId);

	   /**
		 * 删除菜单
		 * @param role
		 * @return
		 */
	   @RequestMapping(value = "/role/deleteRole", method = RequestMethod.POST)
	   public void deleteRole(@RequestBody Role role,@RequestParam("employeeId") int employeeId);
	   /**
		 * 加载权限菜单
		 * @param rid
		 * @return
		 */
	   @RequestMapping(value = "/role/viewTree", method = RequestMethod.POST)
	   public List<TreeView> getViewTree(@RequestParam("rid") int rid);
	   /**
		 * 修改角色权限
		 */
	   @RequestMapping(value = "/role/updateRoleAuth", method = RequestMethod.POST)
	   public void updateRoleAuth(@RequestParam("rid")int rid,@RequestParam("menuIds")String menuIds,@RequestParam("employeeId") int employeeId);

	  
	  
	  
}
