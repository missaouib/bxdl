package com.hzcf.plantform.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.util.TreeView;
import com.hzcf.pojo.basic.Menu;


/**
 *<dl>
 *<dt>类名：MenuFeignClient.java</dt>
 *<dd>描述: 菜单管理FeignClient</dd> 
 *<dd>创建时间： 2018年8月13日 下午2:10:37</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2018年8月13日 下午2:10:37    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@FeignClient(name = "basic-microservice", fallback =FeignDisableHystrixConfiguration.class)
public interface  MenuFeignClient {


	/**
	 * 根据员工id获取菜单
	 * getMenuByEmployeeId是服务提供者中的方法名
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/menu/getMenuByEmployeeId/{employeeId}", method = RequestMethod.GET)
	public List<Menu> getMenuByEmployeeId(@PathVariable("employeeId") int employeeId);
	  /**
		 * 查询所有菜单
		 * @return
		 */
	  
	  @RequestMapping(value = "/menu/getMenuList", method = RequestMethod.POST)
	  public List<Map<String,Object>> getMenuList();

	/**
	 * 查询所有pid为0的菜单
	 * @return map
	 */
	@RequestMapping(value = "/menu/getParentMenuList", method = RequestMethod.POST)
	public List<Map<String, Object>> getParentMenuList();

	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/menu/saveMenu", method = RequestMethod.POST)
	public void saveMenu(@RequestBody Menu menu,@RequestParam("employeeId") int employeeId);


	/**
	 * 校验菜单名称是否存在
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/menu/checkMenuName", method = RequestMethod.POST)
	public Menu checkMenuNameIsRepeat(@RequestParam Map<String, Object> map);


	/**
	 * 修改回显
	 */
	@RequestMapping(value = "/menu/getMenuById", method = RequestMethod.GET)
	public Menu quertMenuById(@RequestParam("mid")int mid);

	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/menu/updateMenu", method = RequestMethod.POST)
	public void updateMenu(@RequestBody Menu menu,@RequestParam("employeeId") int employeeId);

	/**
	 * 删除菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/menu/deleteMenu", method = RequestMethod.POST)
	public void deleteMenu(@RequestBody Menu menu,@RequestParam("employeeId") int employeeId);

	/**
	 * 查询所有菜单
	 * @return map
	 */
	@RequestMapping(value = "/menu/getMenuDataList", method = RequestMethod.POST)
	public List<Map<String, Object>> getMenuDataList(); 
	/**
	 * 查询所有菜单Tree
	 * @return map
	 */
	@RequestMapping(value = "/menu/getMenuTree", method = RequestMethod.POST)
	public List<TreeView> getMenuTree(@RequestParam("employeeId") int employeeId,@RequestParam("employeeNo") String employeeNo);
	/**
	 * 菜单管理查询菜单Tree
	 * @return map
	 */
	@RequestMapping(value = "/menu/getManageMenuTree", method = RequestMethod.POST)
	public List<TreeView> getManageMenuTree(@RequestParam("employeeId") int employeeId);
	
	@RequestMapping(value = "/menu/getMenuByPermission", method = RequestMethod.POST)
	public Menu getMenuByPermission(@RequestParam Map<String, Object> map); 

}
