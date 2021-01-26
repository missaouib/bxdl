package com.hzcf.core.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.sales.DirectorAllowanceStandard;
import com.hzcf.pojo.insurance.sales.InsSalesPreGrade;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.insurance.sales.SalesMonthlyCommission;

public interface SalesDaysCommissionService{

	void addSalesDaysCommission(List<SalesDaysCommission> salesDaysCommission);

	PageModel getCommissionPage(Map<String, Object> params);

	List<Object> findMapList(Map<String, Object> params);

	List<InsSalesPreGrade> findByTjr(Long salerId);

	List<InsSalesPreGrade> findByTjrs(String salerIds);

	BigDecimal sumSalesFYC(String salerIds);

	List<InsSalesPreGrade> findBySj(Long salerId);

	int getNumBySj(Long salerId);

	List<InsSalesPreGrade> findYcgx(Map<String, Object> map);

	List<InsSalesPreGrade> insuranceSalesList(Map<String, Object> map);

	int getZJZXBNum(Long salerId);

	int getBZZXBNum(Long salerId);

	List<InsSalesPreGrade> findZJZXB(Long salerId);

	List<InsSalesPreGrade> findBZZXB(Long salerId);

	List<DirectorAllowanceStandard> findZJJTXS(Map<String, Object> map);

	void updateSalesDaysCommission(List<SalesDaysCommission> salesDaysCommission);

	void addSalesMonthlyCommission(List<SalesMonthlyCommission> salesMonthlyCommission,List<SalesDaysCommission> salesDaysCommissionList);

	PageModel getMonthlyCommissionPage(Map<String, Object> params);

	Map<String, Object> findDetail(Map<String, Object> params);

	Map<String, Object> findMDetail(Map<String, Object> params);

	void checkStatus(Map<String, Object> params);

	List<Object> findMonthlyCommissions(Map<String, Object> params);

	void updateMonthlyCommissions(List<SalesMonthlyCommission> params);

	BigDecimal sumSalesZYJT(String salerIds);

	List<InsSalesPreGrade> quitInsuranceSalesList(Map<String, Object> map);

	/**
	 *@描述 查找指定员工 指定月份的佣金明细
	 *@创建人 qin lina
	 *@创建时间 2020/4/28
	 */
	List<SalesDaysCommission> findSaleCommission(Map<String, Object> par);

	/**
	 *@描述 根据员工id 和 计算月 获取这些员工的初年度FYC
	 *@创建人 qin lina
	 *@创建时间 2020/4/29
	 */
	BigDecimal sumSalesFYCByMonth(Map<String, Object> par);

	/**
	 *@描述  查询 计算可结算佣金所需字段
	 *@创建人 qin lina
	 *@创建时间 2020/5/6
	 */
	List<Object> findSettleableMapList(Map<String, Object> params);

	/**
	 *@描述 找到给定条件下的佣金明细
	 *@创建人 qin lina
	 *@创建时间 2020/5/6
	 */
	List<SalesDaysCommission> findDaysCommission(Map<String, Object> params);

	/**
	 *@描述 从佣金发放列表中获取 佣金发放数据
	 *@创建人 qin lina
	 *@创建时间 2020/5/6
	 */
	List<Map<String, Object>> findMonthCommission(Map<String, Object> params);

	/**
	 *@描述 获取佣金明细
	 *@创建人 qin lina
	 *@创建时间 2020/5/7
	 */
	PageModel getSalesCommissionDetail(Map<String, Object> map);

    void batchRelease(Map<String, Object> map);

	void handleFYC(List<SalesDaysCommission> salesDaysCommission_add_list, List<SalesDaysCommission> salesDaysCommission_update_list, List<Long> settlement_policy_id_list);

    /**
     *@描述  从销售人员表中获取人员关系
     *@创建人 qin lina
     *@创建时间 2020/5/12
     */
	List<Map<String,Object>> getSalerRelation(Map<String, Object> par);

	List<BigDecimal> sumSalesFYCByMonthForEveryOne(Map<String, Object> par);

	/**
	 * 查询组活动人力数
	 * @param par
	 * @return
	 */
    List<String> getGroupManpowerByMonth(Map<String, Object> par);
}
