package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.SalesOrgDetail;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service", fallback =FeignDisableHystrixConfiguration.class)
public interface SalesOrgInfoClient {
	
	/**
	 *增加销售组织
	 * @param salesOrgInfo 
	 * */
	@RequestMapping(value = "/salesOrgInfo/addSalesOrgInfo",method = RequestMethod.POST)
	int addSalesOrgInfo(SalesOrgInfo salesOrgInfo);
	
	/**
	 *增加销售组织
	 * @param salesOrgInfo 
	 * */
	@RequestMapping(value = "/salesOrgInfo/addSalesOrgDetail",method = RequestMethod.POST)
	void addSalesOrgDetail(SalesOrgDetail salesOrgDetail);

	/**
	 *销售组织列表
	 * @param salesOrgInfoList 
	 * */
	@RequestMapping(value = "/salesOrgInfo/getSalesOrgInfoList",method = RequestMethod.POST)
	PageModel getSalesOrgInfoList(@RequestParam Map<String, Object> paras);

	/**
	 * 获取前置编码
	 * @param  
	 * */
	@RequestMapping(value = "/salesOrgInfo/findMaxTreeCode",method = RequestMethod.POST)
	int findMaxTreeCode(@RequestParam Map<String, Object> paras);

	/**
	 *销售组织列表不分页
	 * @param findSalesOrgs 
	 * */
	@RequestMapping(value = "/salesOrgInfo/findSalesOrgs",method = RequestMethod.POST)
	List<SalesOrgInfo> findSalesOrgs(@RequestParam Map<String, Object> paras);


	@RequestMapping(value = "/salesOrgInfo/findSalesOrgsByNames",method = RequestMethod.POST)
	List<SalesOrgInfo> findSalesOrgsByNames(@RequestParam Map<String, Object> paras);

	/**
	 *查
	 * @param selectById 
	 * */
	@RequestMapping(value = "/salesOrgInfo/selectById",method = RequestMethod.POST)
	SalesOrgInfo selectById(@RequestParam Map<String, Object> paras);

	 /**
    *
    *根据条件查询父辈组织机构
    * */
	@RequestMapping(value = "/salesOrgInfo/getParents",method = RequestMethod.POST)
	List<SalesOrgInfo> getParents(@RequestParam Map<String, Object> paras);

	 /**
    *
    *修改基础信息
    * */
	@RequestMapping(value = "/salesOrgInfo/updateSalesOrgInfo",method = RequestMethod.POST)
	void updateSalesOrgInfo(SalesOrgInfo salesOrgInfo,@RequestParam(value = "isNoticy") String isNoticy);
	 /**
    *
    *修改详细信息
    * */
	@RequestMapping(value = "/salesOrgInfo/updateSalesOrgDetail",method = RequestMethod.POST)
	void updateSalesOrgDetail(SalesOrgDetail salesOrgDetail);

	/**
	 * 查看员工部门 a->b->c ...
	 * */
	@RequestMapping(value = "/salesOrgInfo/selectDeptNamesByOrgId",method = RequestMethod.POST)
    String selectDeptNamesByOrgId(@RequestParam Map<String, Object> params);

	/**
	 * <p>根据id查询机构及下属机构</p>
	 * @param salesOrgId
	 * @return
	 */
	@RequestMapping("/salesOrgInfo/findSubordinateOrg")
	List<SalesOrgInfo> findSubordinateOrgList(@RequestParam("salesOrgId") Long salesOrgId);

	/**
	 * 获取当前登录用户的所属机构及下级机构
	 * @param deptNo
	 * @return
	 */
	@RequestMapping("/salesOrgInfo/findEmployeeAllOrgs")
	String findEmployeeAllOrgs(@RequestParam("deptNo") String deptNo);

	/**
	 * 通过saleOrgIds获取销售机构信息
	 * @return
	 */
	@RequestMapping("/salesOrgInfo/getSaleOrgInfoListBySaleOrgIds")
    List<Map<String,Object>> getSaleOrgInfoListBySaleOrgIds(@RequestParam(value = "saleOrgIds",required = false) String saleOrgIds);

	/**
	 * 查询内勤人员所属机构（从总公司一直到分公司例子： 总公司->省级分公司->...）
	 * @param list
	 */
	@RequestMapping("/salesOrgInfo/getEmpAllOrgNameInfo")
    List<Map<String, Object>> getEmpAllOrgNameInfo(List<Map<String, Object>> list);

	/**
	 * 获取顶级机构数量
	 * @param params
	 * @return
	 */
	@RequestMapping("/salesOrgInfo/getTopOrgNum")
    int getTopOrgNum(Map<String, Object> params);

	/**
	 * 获取总/省份公司机构信息集合
	 * @return
	 */
	@RequestMapping("/salesOrgInfo/getTopAndSFOrgsList")
	List<Map<String, Object>> getTopAndSFOrgsList();



	/**
	 *异步获取组织机构信息，以及相关的逻辑判断，用来返回前台信息
	 * */
	@RequestMapping(value = "/salesOrgInfo/queryOrgInfo",method = RequestMethod.POST)
	List<SalesOrgInfo> queryOrgInfo(@RequestParam Map<String, Object> params);

	/**
	 * 通过treeCode查询销售机构信息
	 * @param treeCode
	 * @return
	 */
	@RequestMapping(value = "/salesOrgInfo/getSalesOrgInfoByTreeCode",method = RequestMethod.GET)
	SalesOrgInfo getSalesOrgInfoByTreeCode(@RequestParam("treeCode") String treeCode);

	/**
	 * 通过条件查询基本机构id
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/salesOrgInfo/getCalOrgsByCondition",method = RequestMethod.GET)
	Map<String, String> getCalOrgsByCondition(@RequestParam Map<String, Object> params);

	/**
	 * 修改机构是通过机构Id查询修改前与修改后的基本法机构
	 * @param salesOrgInfo
	 * @return
	 */
	@RequestMapping(value = "/salesOrgInfo/getCalOrgsBySalesOrgInfo",method = RequestMethod.GET)
    Map<String, String> getCalOrgsBySalesOrgInfo(@RequestBody SalesOrgInfo salesOrgInfo);
}
