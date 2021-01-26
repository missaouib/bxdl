package com.hzcf.core.service;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;

import java.util.List;
import java.util.Map;

public interface InsuranceDeptService{
    /**
     * 分页查询保险部门
     * */
	PageModel getInsuranceDeptList(Map<String, Object> params);
    /**
     *新增保险部门
     * */
	void addInsuranceDept(InsuranceDept insuranceDept);
    /**
     * 修改保险部门
     * */
	void updateInsuranceDept(InsuranceDept insuranceDept);
	/**
	 * 删除部部门
	 * */
	void deleteInsuranceDept(String params);
	/**
	 * 查看详情
	 * */
	InsuranceDept selectInsuranceBasicDept(Map<String, Object> params);
	
	/**
	 * 不分页查询保险部门
	 * */
	List<InsuranceDept> findInsurCompanys(Map<String, Object> params);
	/**
	 * 根据部门ID，查询该部门子集
	 * */
	InsuranceDept selectSubsetById(String id);
	/**
	 * 根据部门ID，查询该部门子集
	 * */
	InsuranceDept selectSubsetOrgById(String id);

	void deleteInsuranceOrg(String params);

    List<Map<String, Object>> selectAllOrg();

	Map<String, Object> selectOrg(Map<String, Object> pra);

	/**
	 * <p>按照名称或代码查询已经存在的保险公司</p>
	 * @param name  名称
	 * @param code  代码
	 * @return true 存在， false 不存在
	 */
	boolean findExistOrgByNameOrCode(Long id , String name, String code);

	/**
	 *@描述 批量导入保险公司信息
	 *@参数
	 *@返回值
	 *@创建人 qin lina
	 *@创建时间 2020/3/5
	 */
    void insertImportList(Map<String, Object> p);
}
