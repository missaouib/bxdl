package com.hzcf.core.service;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesOrgDetail;
import com.hzcf.pojo.insurance.SalesOrgInfo;

import java.util.List;
import java.util.Map;

public interface SalesOrgInfoService{

    /**
     *新增组织机构
     * */
    int addSalesOrgInfo(SalesOrgInfo salesOrgInfo);
	
    /**
     *获取列表
     * @param salesOrgInfoList 
     * */
    PageModel getSalesOrgInfoList(Map<String, Object> params);
	

	void addSalesOrgDetail(SalesOrgDetail salesOrgDetail);

    /**
     *获取前置编码
     * @param
     * */
    int findMaxTreeCode(Map<String, Object> params);

    /**
     *获取列表
     * @param findSalesOrgs 
     * */
    List<SalesOrgInfo> findSalesOrgs(Map<String, Object> params);

    List<SalesOrgInfo> findSalesOrgsByNames(Map<String, Object> params);

    /**
     *获取
     * @param  
     * */
    SalesOrgInfo selectById(Map<String, Object> params);

	 /**
    *
    *根据条件查询父辈组织机构
    * */
     List<SalesOrgInfo> getParents(Map<String, Object> params);

    /**
     * 修改基础信息
     * */
    void updateSalesOrgInfo(SalesOrgInfo salesOrgInfo,String isNoticy);
    /**
     * 修改详细信息
     * */
    void updateSalesOrgDetail(SalesOrgDetail salesOrgDetail);
	/**
	 * 查看员工部门 a->b->c ...
	 * */
    String selectDeptNamesByOrgId(Map<String, Object> params);

	/**
	 * <p>根据id查询机构及下属机构</p>
	 * @param salesOrgId	机构id
	 * @return
	 */
	List<SalesOrgInfo> findSubordinateOrgListById(Long salesOrgId);

    /**
     * 获取内勤人员所属and下级机构id
     * @param deptNo
     * @return
     */
    String findEmployeeAllOrgs(String  deptNo);

    /**
     * 通过saleOrgIds获取销售机构信息
     * @param saleOrgIds
     * @return
     */
    List<Map<String, Object>> getSaleOrgInfoListBySaleOrgIds(String saleOrgIds);

    void getEmpAllOrgNameInfo(List list);

    int getTopOrgNum(Map<String, Object> params);

    /**
     * 获取总/省份公司机构信息集合
     * @return
     */
    List<Map<String, Object>> getTopAndSFOrgsList();

    /**
     * 异步获取组织机构信息，以及相关的逻辑判断，用来返回前台信息
     */
    List<SalesOrgInfo> queryOrgInfo(Map<String, Object> params);

    /**
     * 修改是否默认基本法
     * */
    void updateIsDefaultCal(SalesOrgInfo salesOrgInfo);

    /**
     * 通过treeCode查询销售机构信息
     * @param treeCode
     * @return
     */
    SalesOrgInfo getSalesOrgInfoByTreeCode(String treeCode);

    /**
     * 通过条件查询基本机构id
     * @param params
     * @return
     */
    Map<String, String> getCalOrgsByCondition(Map<String, Object> params);

    Map<String, String> getCalOrgsBySalesOrgInfo(SalesOrgInfo salesOrgInfo);
}
