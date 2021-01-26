package com.hzcf.basic.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.basic.service.MenuService;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.Menu;
import com.hzcf.util.StringUtil;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MenuService menuService;
	
	   /**
	   * 通过员工id查询菜单
	   * @param employeeId
	   * @return
	   */
	  @RequestMapping("/getMenuByEmployeeId/{employeeId}")
	  public List<Menu> getEmployeeById(@PathVariable int employeeId) {
		  return menuService.getMenuByEmployeeId(employeeId);
	  }
	  
	  /**
		 * 分页查询所有
		 * @param pageNumber
		 * @param pageSize
		 * @return
		 */
		@RequestMapping("/getMenuList")
		public List<Map<String, Object>> getMenuList() {
			List<Map<String, Object>> list = null;
			try {
		    	    list = menuService.getMenuList();
			} catch (Exception e) {
			    e.printStackTrace();
			}
				return list;
		}
		
		/**
		 * 查询所有pid为0的菜单
		 * @return map
		 */
		@RequestMapping("/getParentMenuList")
		public List<Map<String,Object>> getParentMenuList(){
				return menuService.getParentMenuList();
		}
	        
		/**
		 * 添加菜单
		 * @param menu
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/saveMenu")
		public void saveMenu(@RequestBody Menu menu,@RequestParam("employeeId") int employeeId){
				menuService.saveMenu(menu,employeeId);
		}
		
		/**ToTo
		 * 校验菜单名称是否存在
		 * @param menu   
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/checkMenuName")
		public Menu checkMenuName(@RequestParam Map<String, Object> map){
			
				String nameZh = StringUtil.trim((String)map.get("nameZh"));
				if (StringUtil.isNotBlank(nameZh)) {
					map.put("nameZh", nameZh);
				}
				
				String menuId = StringUtil.trim((String)map.get("menuId"));
				if (StringUtil.isNotBlank(menuId)) {
					map.put("menuId", menuId);
				}
				Menu menu = menuService.checkMenuNameIsRepeat(map);
				return menu;
		}
		
		/**ToTo
		 * 修改回显
		 */
		@ResponseBody
		@RequestMapping("/getMenuById")
		public Menu getMenuById(@RequestParam("mid")int mid){
			try {
				Menu menu=menuService.quertMenuById(mid);
				return menu;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		/**
		 * 查询
		 */
		@ResponseBody
		@RequestMapping("/getMenuByPermission")
		public Menu getMenuByPermission(@RequestParam Map<String, Object> map){
			try {
				String permission = StringUtil.trim((String)map.get("permission"));
				Menu menu = menuService.getMenuByPermission(permission);
				return menu;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		/**ToTo
		 * 修改菜单
		 * @param menuto_insurance_policy_examine
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/updateMenu")
		public void updateMenu(@RequestBody Menu menu,@RequestParam("employeeId") int employeeId){
				menuService.updateMenu(menu,employeeId);
		}
		
		/**
		 * 删除菜单
		 * @param menu
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/deleteMenu")
		public void deleteMenu(@RequestBody Menu menu,@RequestParam("employeeId") int employeeId){
			
				menuService.deleteMenu(menu,employeeId);
		}
		/**
		 * 查询所有的菜单
		 * @return map
		 */
		@ResponseBody
		@RequestMapping("/getMenuDataList")
		public List<Map<String,Object>> getMenuDataList(){
			try {
				return menuService.getMenuDataList();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		/**
		 * 加载菜单
		 * @param type 1加载菜单，2菜单管理
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/getMenuTree")
		public List<TreeView> getMenuTree(@RequestParam("employeeId") int employeeId,@RequestParam("employeeNo") String employeeNo){
			try {
				return menuService.getViewTree(employeeId , employeeNo);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		/**
		 * 菜单管理查询菜单Tree
		 * @return map
		 */
		@RequestMapping(value = "/getManageMenuTree")
		public List<TreeView> getManageMenuTree(@RequestParam("employeeId") int employeeId){
			return menuService.getManageMenuTree(employeeId);
		}
}
