package com.hzcf.core.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.sales.DirectorAllowanceStandard;
import com.hzcf.pojo.insurance.sales.InsSalesPreGrade;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.insurance.sales.SalesMonthlyCommission;

/**
 *保险部门管理
 */
public  interface SalesDaysCommissionMapper {

	void addSalesDaysCommission(List<SalesDaysCommission> salesDaysCommission);

	List<Map<String, Object>> getCommissionPage(Map<String, Object> params);

	long getSalesDaysCommissionListSize(Map<String, Object> params);

	List<Object> findMapList(Map<String, Object> params);

	List<InsSalesPreGrade> findByTjr(Long salerId);

	List<InsSalesPreGrade> findByTjrs(Map<String, Object> map);

	BigDecimal sumSalesFYC(Map<String, Object> map);

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

	void addSalesMonthlyCommission(List<SalesMonthlyCommission> salesMonthlyCommission);

	List<Map<String, Object>> getMonthlyCommissionPage(Map<String, Object> params);

	long getSalesMonthlyCommissionListSize(Map<String, Object> params);

	Map<String, Object> findDetail(Map<String, Object> params);

	Map<String, Object> findMDetail(Map<String, Object> params);

	void checkStatus(Map<String, Object> params);

	List<Object> findMonthlyCommissions(Map<String, Object> params);

	void updateMonthlyCommissions(List<SalesMonthlyCommission> params);

	BigDecimal sumSalesZYJT(Map<String, Object> map);

	List<InsSalesPreGrade> quitInsuranceSalesList(Map<String, Object> map);

    List<SalesDaysCommission> findSaleCommission(Map<String, Object> par);

    BigDecimal sumSalesFYCByMonth(Map<String, Object> par);

    List<Object> findSettleableMapList(Map<String, Object> params);

	List<SalesDaysCommission> findDaysCommission(Map<String, Object> params);

	List<Map<String, Object>> findMonthCommission(Map<String, Object> params);

	List<Map<String,Object>>  getSalesCommissionDetail(Map<String, Object> map);

	long getSalesCommissionDetailSize(Map<String, Object> map);

    void batchRelease(Map<String, Object> map);

    void updateSalesDaysSettlementStatus(Map<String,Object> params);

    List<Map<String,Object>> getSalerRelation(Map<String, Object> par);

	List<BigDecimal> sumSalesFYCByMonthForEveryOne(Map<String, Object> par);

    List<String> getGroupManpowerByMonth(Map<String, Object> par);
}
