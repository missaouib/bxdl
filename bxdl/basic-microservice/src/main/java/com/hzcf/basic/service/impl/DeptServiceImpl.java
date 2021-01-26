package com.hzcf.basic.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.basic.mapper.DepartmentMapper;
import com.hzcf.basic.service.DeptService;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.Department;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	protected final Log logger = LogFactory.getLog(getClass());

	
	@Override
	public List<Map<String, Object>> getDeptList( Map<String, Object> paramsCondition) {
		return departmentMapper.findAllRetMapByPage(paramsCondition);
	}


	@Override
	public void saveDepartment(Department department) {
		department.setCreateTime(new Date());
		if(0 == (department.getParentId())){
			department.setLevels(1);
		}else{
			//查询上级level +1
			Department dept = departmentMapper.findLevelsByDeptId(department.getParentId());
			department.setLevels(dept.getLevels() +1);
		}
		 departmentMapper.insert(department);
	}

	@Override
	public List<TreeView> getDeptTree() {
		List<TreeView>tree=new ArrayList<TreeView>();
		//根据角色 查询出所拥有的部门用于回显
		List<Integer>list=new ArrayList<>();
		//查询出所有的父级部门
		List<Map<String, Object>> plist = departmentMapper.getParentDeptList();//父部门
		for (Map<String, Object> map : plist) {
			TreeView parent=new TreeView();
			parent.setText(String.valueOf(map.get("deptName")));
			parent.setHref(String.valueOf(""));
			parent.setIcon(String.valueOf(""));
			parent.setId(Integer.parseInt((map.get("deptId").toString())));
			//根据父id查询子部门
			List<TreeView>childList=new ArrayList<TreeView>();
		    digui(childList,list, String.valueOf(map.get("deptId")));
			parent.setNodes(childList);
			tree.add(parent);
		}
		return tree;
	}


	private List<TreeView> digui(List<TreeView>childList ,List<Integer> list, String menuId) {
		List<Department>clist=departmentMapper.queryChildDeptByPidType(menuId);//子部门
		   if (null != clist && clist.size()>0) {  
				   for (Department c : clist) {
					   TreeView child=new TreeView();
						   child.setText(String.valueOf(c.getDeptName()));
						   child.setHref("");
						   child.setIcon("");
						   child.setId(c.getDeptId());
						   child.setPid(c.getParentId());
						   List<Department>dlist=departmentMapper.queryChildDeptByPidType(c.getDeptId().toString());//子部门
						   if(dlist.size()>0){
							   List<TreeView> dhildList=new ArrayList<TreeView>();
							   for (Department d : dlist) {
								   TreeView dhild=new TreeView();
								   dhild.setText(String.valueOf(d.getDeptName()));
								   dhild.setHref("");
								   dhild.setIcon("");
								   dhild.setId(d.getDeptId());
								   dhild.setPid(d.getParentId());
								   gTree(dhild,d.getDeptId().toString());
								   dhildList.add(dhild);
							   }
							   child.setNodes(dhildList);
						   }
						childList.add(child);
				   }
		}
		return childList;
	}
	private TreeView gTree(TreeView child, String menuId) {
		List<Department>dlist=departmentMapper.queryChildDeptByPidType(menuId);//子部门
		if(dlist.size()>0){
			   List<TreeView> dhildList=new ArrayList<TreeView>();
			   for (Department d : dlist) {
				   TreeView dhild=new TreeView();
				   dhild.setText(String.valueOf(d.getDeptName()));
				   dhild.setHref("");
				   dhild.setIcon("");
				   dhild.setId(d.getDeptId());
				   dhild.setPid(d.getParentId());
				   gTree(dhild,d.getDeptId().toString());
				   dhildList.add(dhild);
			   }
			   child.setNodes(dhildList);
		   }
		return child;
	}


	@Override
	public Department getDepartmentById(int did) {
		return departmentMapper.getDepartmentById(did);
	}


	@Override
	public void updateDepartment(Department department) {
		departmentMapper.updateDepartment(department);
	}
}
