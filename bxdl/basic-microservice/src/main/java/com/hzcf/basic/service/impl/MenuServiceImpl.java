package com.hzcf.basic.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.basic.mapper.EmployeeRoleRelationMapper;
import com.hzcf.basic.mapper.MenuMapper;
import com.hzcf.basic.mapper.RoleMenuRelationMapper;
import com.hzcf.basic.service.MenuService;
import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.Menu;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private RoleMenuRelationMapper roleMenuRelationMapper;
	@Autowired
	private EmployeeRoleRelationMapper employeeRoleRelationMapper;
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public List<Menu> getMenuByEmployeeId(Integer employeeId) {
		Map<String,Object> requestMap = new HashMap<String,Object>();
		requestMap.put("employeeId", employeeId);
		requestMap.put("pid", 0);
		List<Menu>plist=menuMapper.getMenuByEmployeeId(requestMap);
		try {
			for(Menu m:plist){
				Map<String,Object>map = new HashMap<String,Object>();
				map.put("employeeId", employeeId);
				map.put("pid", m.getMenuId());
				List<Menu>clist=menuMapper.getMenuByEmployeeId(map);
				if(clist!=null){
					m.setChildren(clist);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return plist;
	}

	/**
	 *  分页查询所有
	 */
	@Override
	public List<Map<String, Object>> getMenuList() {
		return menuMapper.findAllRetMapByPage();
	}
	

	@Override
	public List<Map<String, Object>> getParentMenuList() {
		return menuMapper.getParentMenuList();
	}
	
	@Override
	public Menu checkMenuNameIsRepeat(Map<String, Object> map) {
		return menuMapper.checkMenuNameIsRepeat(map);
	}
	
	@Override
	public Menu getMenuByPermission(String permission) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("permission", permission);
		map.put("menu_status", "1");
		return menuMapper.getMenuByPermission(map);
	}	

	@Override
	public void saveMenu(Menu menu,int employeeId) {
		menu.setCreatorId(employeeId);
		menu.setUpdateTime(new Date());
		menu.setCreateTime(new Date());
		menuMapper.insert(menu);
	}

	@Override
	public Menu quertMenuById(int mid) {
		return menuMapper.selectByPrimaryKey(mid);
	}

	@Override
	public void updateMenu(Menu updateMenu,int employeeId) {
		updateMenu.setCreatorId(employeeId);
		updateMenu.setUpdateTime(new Date());
		updateMenu.setCreateTime(new Date());
		menuMapper.updateByPrimaryKeySelective(updateMenu);
	}

	@Override
	public void deleteMenu(Menu menu, int employeeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("menuId", menu.getMenuId());
		map.put("creatorId", employeeId);
		map.put("menuStatus", "3");
		menuMapper.deleteMenuByMenuId(map);
	}

	@Override
	public List<Map<String, Object>> getMenuDataList() {
		return menuMapper.getMenuDataList();
	}

	@Override
	//1加载菜单，2菜单管理
	public List<TreeView> getViewTree(int employeeId,String employeeNo) {
		List<TreeView>tree=new ArrayList<TreeView>();
		List<Integer> roleList=employeeRoleRelationMapper.getRoleByEmployeeId(employeeId);//角色集合
		List<Integer> list=roleMenuRelationMapper.queryMenuIdListByRoleIdList(roleList);//角色所拥有的菜单集合
		//查询出所有的父级菜单
		List<Map<String, Object>> plist = menuMapper.getParentMenuListByRoleId(list);//父菜单
		for (Map<String, Object> map : plist) {
			TreeView parent=new TreeView();
			parent.setText(String.valueOf(map.get("menuName")));
			parent.setHref(String.valueOf(map.get("menuUrl")));
			parent.setIcon(String.valueOf(map.get("menuIcon")));
			parent.setId(Integer.parseInt((map.get("menuId").toString())));
			parent.setPid(Integer.parseInt((map.get("parentId").toString())));
			//parent.setTags("0");
			//根据父id查询子菜单
			List<TreeView>childList=new ArrayList<TreeView>();
		    digui(childList,list, String.valueOf(map.get("menuId")), employeeNo);
			parent.setNodes(childList);
			tree.add(parent);
		}
		return tree;
	}


	private List<TreeView> digui(List<TreeView>childList ,List<Integer> list, String menuId ,String employeeNo) {
		List<Menu>clist=menuMapper.queryChildMenuByPidType(menuId,list);//子菜单
		   if (null != clist && clist.size()>0) {  
				   for (Menu c : clist) {
					   //特殊管理员superAdmin 不展示二级菜单处理
					   if("superAdmin".equals(employeeNo)){
					   	   //定时器管理
						   if("quartz/to_quartz_job_path".equals(c.getMenuUrl().trim())){ //菜单管理不展示
							   continue;
						   }else if("menu/goMenuPage".equals(c.getMenuUrl().trim())){
							   continue;
						   }
					   }
					   TreeView child=new TreeView();
						   child.setText(String.valueOf(c.getNameZh()));
						   child.setHref(c.getMenuUrl());
						//   child.setTags("4");
						   child.setIcon(c.getMenuIcon());
						   child.setId(c.getMenuId());
						   child.setPid(c.getParentId());
						   List<Menu>dlist=menuMapper.queryChildMenuByPidType(c.getMenuId().toString(),list);//子菜单
						   if(dlist.size()>0){
							   List<TreeView> dhildList=new ArrayList<TreeView>();
							   for (Menu d : dlist) {
								   TreeView dhild=new TreeView();
								   dhild.setText(String.valueOf(d.getNameZh()));
								   dhild.setHref(d.getMenuUrl());
								 //  dhild.setTags("2");
								   dhild.setIcon(d.getMenuIcon());
								   dhild.setId(d.getMenuId());
								   dhild.setPid(d.getParentId());
								   gTree(dhild,d.getMenuId().toString(),list);
								   dhildList.add(dhild);
							   }
							   child.setNodes(dhildList);
						   }
						childList.add(child);
				   }
		}
		return childList;
	}
	private TreeView gTree(TreeView child, String menuId,List<Integer> list) {
		List<Menu>dlist=menuMapper.queryChildMenuByPidType(menuId,list);//子菜单
		if(dlist.size()>0){
			   List<TreeView> dhildList=new ArrayList<TreeView>();
			   for (Menu d : dlist) {
				   TreeView dhild=new TreeView();
				   dhild.setText(String.valueOf(d.getNameZh()));
				   dhild.setHref(d.getMenuUrl());
				  // dhild.setTags("2");
				   dhild.setIcon(d.getMenuIcon());
				   dhild.setId(d.getMenuId());
				   dhild.setPid(d.getParentId());
				   gTree(dhild,d.getMenuId().toString(),list);
				   dhildList.add(dhild);
			   }
			   child.setNodes(dhildList);
		   }
		return child;
	}

	@Override
	public List<TreeView> getManageMenuTree(int employeeId) {
		List<TreeView>tree=new ArrayList<TreeView>();
		//查询出所有的父级菜单
		List<Integer> list=new ArrayList<Integer>();//角色所拥有的菜单集合
		List<Map<String, Object>> plist = menuMapper.getParentMenuList();//父菜单
		for (Map<String, Object> map : plist) {
			TreeView parent=new TreeView();
			parent.setText(String.valueOf(map.get("menuName")));
			parent.setHref(String.valueOf(map.get("menuUrl")));
			parent.setIcon(String.valueOf(map.get("menuIcon")));
			parent.setId(Integer.parseInt((map.get("menuId").toString())));
			parent.setPid(Integer.parseInt((map.get("parentId").toString())));
			//parent.setTags("0");
			//根据父id查询子菜单
			List<TreeView>childList=new ArrayList<TreeView>();
			diguiManage(childList,list, String.valueOf(map.get("menuId")));
			parent.setNodes(childList);
			tree.add(parent);
		}
		return tree;
	}

	private List<TreeView> diguiManage(List<TreeView>childList ,List<Integer> list, String menuId) {
		List<Menu>clist=menuMapper.queryChildMenuManageByPid(menuId);//子菜单
		   if (null != clist && clist.size()>0) {  
				   for (Menu c : clist) {
					   TreeView child=new TreeView();
						   child.setText(String.valueOf(c.getNameZh()));
						   child.setHref(c.getMenuUrl());
						//   child.setTags("4");
						   child.setIcon(c.getMenuIcon());
						   child.setId(c.getMenuId());
						   child.setPid(c.getParentId());
						   List<Menu>dlist=menuMapper.queryChildMenuManageByPid(c.getMenuId().toString());//子菜单
						   if(dlist.size()>0){
							   List<TreeView> dhildList=new ArrayList<TreeView>();
							   for (Menu d : dlist) {
								   TreeView dhild=new TreeView();
								   dhild.setText(String.valueOf(d.getNameZh()));
								   dhild.setHref(d.getMenuUrl());
								 //  dhild.setTags("2");
								   dhild.setIcon(d.getMenuIcon());
								   dhild.setId(d.getMenuId());
								   dhild.setPid(d.getParentId());
								   gTreeDiguiManage(dhild,d.getMenuId().toString(),list);
								   dhildList.add(dhild);
							   }
							   child.setNodes(dhildList);
						   }
						childList.add(child);
				   }
		}
		return childList;
	}
	private TreeView gTreeDiguiManage(TreeView child, String menuId,List<Integer> list) {
		List<Menu>dlist=menuMapper.queryChildMenuManageByPid(menuId);//子菜单
		if(dlist.size()>0){
			   List<TreeView> dhildList=new ArrayList<TreeView>();
			   for (Menu d : dlist) {
				   TreeView dhild=new TreeView();
				   dhild.setText(String.valueOf(d.getNameZh()));
				   dhild.setHref(d.getMenuUrl());
				  // dhild.setTags("2");
				   dhild.setIcon(d.getMenuIcon());
				   dhild.setId(d.getMenuId());
				   dhild.setPid(d.getParentId());
				   gTree(dhild,d.getMenuId().toString(),list);
				   dhildList.add(dhild);
			   }
			   child.setNodes(dhildList);
		   }
		return child;
	}
}
