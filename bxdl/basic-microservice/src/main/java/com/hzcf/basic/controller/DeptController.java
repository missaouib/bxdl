package com.hzcf.basic.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.basic.service.DeptService;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.Department;

@RestController
@RequestMapping("/dept")
public class DeptController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DeptService deptService;


	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	@RequestMapping("/getDeptList")
	public List<Map<String, Object>> getDeptList(@RequestParam Map<String, Object> paramsCondition) {
		return deptService.getDeptList(paramsCondition);
	}
	
	/**
	 *新增部门
	 * 
	 * @return
	 */
	@RequestMapping("/saveDepartment")
	@ResponseBody
	public void saveDepartment(@RequestBody Department department) {
		 deptService.saveDepartment(department);
	}
	/**
	 * 加载部门tree
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDeptTree")
	public List<TreeView> getDeptTree(){
		try {
			return deptService.getDeptTree();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	 /**
	   * 修改回显
	   * @param List<Map<String, Object>>
	   */
	  @ResponseBody
	  @RequestMapping(value = "/getDepartmentById", method = RequestMethod.GET)
	  public Department getDepartmentById(@RequestParam("did") int did){
		return deptService.getDepartmentById(did);
		  
	  }
	  /**
	   * 修改部门
	   * @param department
	   */
	  @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
	  public void updateDepartment(@RequestBody Department department){
		  deptService.updateDepartment(department);
	  }
}
