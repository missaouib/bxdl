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
import org.springframework.transaction.annotation.Transactional;

import com.hzcf.basic.mapper.EmployeeRoleRelationMapper;
import com.hzcf.basic.mapper.MenuMapper;
import com.hzcf.basic.mapper.RoleMapper;
import com.hzcf.basic.mapper.RoleMenuRelationMapper;
import com.hzcf.basic.service.RoleService;
import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.TreeView;
import com.hzcf.basic.util.Trees;
import com.hzcf.basic.util.ViewTree;
import com.hzcf.pojo.basic.Menu;
import com.hzcf.pojo.basic.Role;
import com.hzcf.pojo.basic.RoleMenuRelation;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMenuRelationMapper  roleMenuRelationMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private EmployeeRoleRelationMapper employeeRoleRelationMapper;
	protected final Log logger = LogFactory.getLog(getClass());

	
	@Override
	public PageModel getRoleList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer)paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer)paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex",pageModel.getEndIndex());
		List<Map<String, Object>> data = roleMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = roleMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}


	@Override
	public Role checkRoleCodeIsRepeat(Map<String, Object> map) {
		return roleMapper.checkRoleCodeIsRepeat(map);
	}


	@Override
	public void saveRole(Role role,int employeeId) {
		role.setCreatorId(employeeId);
		role.setUpdateTime(new Date());
		role.setCreateTime(new Date());
		role.setRoleStatus("1");
		roleMapper.insert(role);
	}


	@Override
	public Role quertRoleById(int id) {
		return roleMapper.selectByPrimaryKey(id);
	}


	@Override
	public void updateRole(Role updateRole ,int employeeId) {
		updateRole.setCreatorId(employeeId);
		updateRole.setUpdateTime(new Date());
		updateRole.setRoleStatus("1");
		roleMapper.updateByPrimaryKeySelective(updateRole);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteRole(Role role,int employeeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", role.getId());
		map.put("creatorId", employeeId);
		map.put("roleStatus", "2");
		roleMapper.deleteRoleById(map);
		roleMenuRelationMapper.deleteMenuIdByRoleId(role.getId());
		employeeRoleRelationMapper.deleteEmployeeRoleRelationByRoleId(role.getId());
	}


	@Override
	public List<TreeView> getViewTree(int rid) {
		List<TreeView>tree=new ArrayList<TreeView>();
		//根据角色 查询出所拥有的菜单用于回显
		List<Integer>list=roleMenuRelationMapper.queryMenuIdListByRoleId(rid);
		//查询出所有的父级菜单
		List<Map<String, Object>> plist = menuMapper.getParentMenuList();//父菜单
		for (Map<String, Object> map : plist) {
			TreeView parent=new TreeView();
			parent.setText(String.valueOf(map.get("menuName")));
			parent.setHref(String.valueOf(map.get("menuUrl")));
			parent.setId(Integer.parseInt(map.get("menuId").toString()));
			parent.setPid(Integer.parseInt(map.get("parentId").toString()));
			parent.setIcon(String.valueOf(map.get("menuIcon")));
			for(Integer k:list){
				if(map.get("menuId").equals(k)){
					Trees tt=new Trees();
					tt.setChecked(true);
					parent.setState(tt);
				}
			}
			//根据父id查询子菜单
			List<TreeView>childList=new ArrayList<TreeView>();
			recursion(childList,list, String.valueOf(map.get("menuId")));
			parent.setNodes(childList);
			tree.add(parent);
		}
		return tree;
	}


	private List<TreeView> recursion(List<TreeView>childList ,List<Integer> list, String menuId) {
		List<Menu>clist=menuMapper.queryChildMenuByPid(menuId);//子菜单
		   if (null != clist && clist.size()>0) {  
				   for (Menu c : clist) {
					   TreeView child=new TreeView();
						   child.setText(String.valueOf(c.getNameZh()));
						   child.setHref(c.getMenuUrl());
						   child.setIcon(c.getMenuIcon());
						   child.setId(c.getMenuId());
						   child.setPid(c.getParentId());
						   for(Integer k:list){
								if(c.getMenuId().equals(k)){
									Trees tt=new Trees();
									tt.setChecked(true);
									child.setState(tt);
								}
							}
						   List<Menu>dlist=menuMapper.queryChildMenuByPid(c.getMenuId().toString());//子菜单
						   if(dlist.size()>0){
							   List<TreeView> dhildList=new ArrayList<TreeView>();
							   for (Menu d : dlist) {
								   TreeView dhild=new TreeView();
								   dhild.setText(String.valueOf(d.getNameZh()));
								   dhild.setHref(String.valueOf(d.getMenuUrl()));
								   dhild.setIcon(d.getMenuIcon());
								   dhild.setId(d.getMenuId());
								   dhild.setPid(d.getParentId());
								   for(Integer k:list){
										if(d.getMenuId().equals(k)){
											Trees tt=new Trees();
											tt.setChecked(true);
											dhild.setState(tt);
										}
									}
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
	private TreeView gTree(TreeView child, String menuId,List<Integer>list) {
		List<Menu>dlist=menuMapper.queryChildMenuByPid(menuId);//子菜单
		if(dlist.size()>0){
			   List<TreeView> dhildList=new ArrayList<TreeView>();
			   for (Menu d : dlist) {
				   TreeView dhild=new TreeView();
				   dhild.setText(String.valueOf(d.getNameZh()));
				   dhild.setHref(String.valueOf(d.getMenuUrl()));
				   dhild.setIcon(d.getMenuIcon());
				   dhild.setId(d.getMenuId());
				   dhild.setPid(d.getParentId());
				   for(Integer k:list){
						if(d.getMenuId().equals(k)){
							Trees tt=new Trees();
							tt.setChecked(true);
							dhild.setState(tt);
						}
					}
				   gTree(dhild,d.getMenuId().toString(),list);
				   dhildList.add(dhild);
			   }
			   child.setNodes(dhildList);
		   }
		return child;
	}

	@Transactional
	@Override
	public void updateRoleAuth(int rid, String menuIds,int employeeId) {
		//根据角色id删除原有的菜单id
		roleMenuRelationMapper.deleteMenuIdByRoleId(rid);
		List<RoleMenuRelation>  list = new ArrayList<RoleMenuRelation> ();
		String[] split = menuIds.split(",");
		for (int i = 0; i < split.length; i++) {
			RoleMenuRelation relation = new RoleMenuRelation();
			relation.setUpdateTime(new Date());
			relation.setCreatorId(employeeId);
			relation.setMenuId(Integer.valueOf(split[i]));
			relation.setCreateTime(new Date());
			relation.setRoleId(rid);
			list.add(relation);
		}
		roleMenuRelationMapper.insertMenuIds(list);
	}


	@Override
	public List<Integer> getRoleByEmployeeId(int employeeId){
		return employeeRoleRelationMapper.getRoleByEmployeeId(employeeId);
	}


}
