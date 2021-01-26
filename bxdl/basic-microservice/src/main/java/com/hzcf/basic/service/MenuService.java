package com.hzcf.basic.service;

import java.util.List;
import java.util.Map;

import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.Menu;

	public interface MenuService {
		
		/**
		 * 根据员工id获取菜单
		 * @param id
		 * @return
		 */
		public List<Menu> getMenuByEmployeeId(Integer employeeId);

		/**
		 *  查询所有菜单
		 * @param paramsCondition
		 * @return
		 */
	    
		public List<Map<String, Object>> getMenuList();
		/**
		 * 查询所有pid为0的菜单
		 * @return map
		 */
		public List<Map<String, Object>> getParentMenuList();
		/**
		 * 校验菜单名称是否重复
		 * @param map
		 * @return
		 */
		public Menu checkMenuNameIsRepeat(Map<String, Object> map);
		/**
		 * 添加菜单
		 * @param menu
		 */
		public void saveMenu(Menu menu,int employeeId);
		/**
		 * 根据id 查询菜单 修改回显
		 * @param mid
		 * @return
		 */
		public Menu quertMenuById(int mid);
		/**
		 * 修改菜单
		 * @param menu
		 */
		public void updateMenu(Menu menu,int employeeId);
		/**
		 * 删除菜单
		 * @param menu
		 */
		public void deleteMenu(Menu menu,int employeeId);
		public List<Map<String, Object>> getMenuDataList(); 
		/**
		 * 加载权限菜单
		 * @param rid
		 * @return
		 */
		List<TreeView> getViewTree(int employeeId,String employeeNo );
		/**
		 * 加载菜单
		 * @param rid
		 * @return
		 */
		List<TreeView> getManageMenuTree(int employeeId);
		/**
		 * 查询菜单
		 * @param permission
		 * @return
		 */
		public Menu getMenuByPermission(String permission);		

}
