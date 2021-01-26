package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.SalesOrgDetail;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *保险部门管理
 */
public  interface SalesOrgInfoMapper {
	/*
     * 添加部门
     * */
    int addSalesOrgInfo(SalesOrgInfo salesOrgInfo);

	void addSalesOrgDetail(SalesOrgDetail salesOrgDetail);

	List<Map<String, Object>> getSalesOrgInfoList(Map<String, Object> params);

	long getSalesOrgInfoListSize(Map<String, Object> params);

	int findPreCode(Map<String, Object> params);

	List<SalesOrgInfo> findSalesOrgs(Map<String, Object> params);

	SalesOrgInfo selectById(Map<String, Object> params);

	SalesOrgDetail selectDetailById(Map<String, Object> params);

	List<SalesOrgInfo> getParents(Map<String, Object> params);

	void updateSalesOrgInfo(SalesOrgInfo salesOrgInfo);

	void updateSalesOrgDetail(SalesOrgDetail salesOrgDetail);

	List<Map<String, Object>> getParentSaleList();

	List<SalesOrgInfo> queryChildSaleByPcode(@Param("saleOrgCode") String saleOrgCode);

	/**
	 * <p>根据销售机构code查询</p>
	 * @param salesOrgCode
	 * @return
	 */
	SalesOrgInfo querySaleOrgByCode(String salesOrgCode);

    String selectDeptNamesByOrgId(Map<String, Object> params);

	/**
	 * 获取内勤人员所属and下级机构id
	 * @param deptNo
	 * @return
	 */
	String findEmployeeAllOrgs(String deptNo);

	/**
	 * 通过saleOrgIds获取销售机构信息
	 * @param saleOrgIds
	 * @return
	 */
	List<Map<String, Object>> getSaleOrgInfoListBySaleOrgIds(String saleOrgIds);

	/**
	 * 通过部门编号查询所属机构中文从总->分
	 * @param deptId
	 * @return
	 */
    String getEmpAllOrgNameInfoByDeptId(String deptId);

	/**
	 * 通过部门id查询下级机构的id
	 * @param salesOrgId
	 * @return
	 */
    String getChildOrgsBySaleOrgId(String salesOrgId);

    List<SalesOrgInfo> getAccessHkSaleOrgInfos(Map<String, Object> params);

	/**
	 * 团队级别选择团队时，查询所有机构
	 * @param params
	 * @return
	 */
    List<SalesOrgInfo> findSalesOrgsByTeamLevel(Map<String, Object> params);

	/**
	 * 获取顶级团队数量
	 * @param params
	 * @return
	 */
    int getTopOrgNum(Map<String, Object> params);

    List<SalesOrgInfo> findSalesOrgsByNames(Map<String, Object> params);

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

	SalesOrgInfo getSalesOrgInfoByTreeCode(String treeCode);

	String getCalOrgsSalesOrgId(String salesOrgId);
}
