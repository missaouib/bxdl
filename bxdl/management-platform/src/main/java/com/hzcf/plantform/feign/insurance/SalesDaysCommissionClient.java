package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.sales.DirectorAllowanceStandard;
import com.hzcf.pojo.insurance.sales.InsSalesPreGrade;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.insurance.sales.SalesMonthlyCommission;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "corebusiness-service", fallback =FeignDisableHystrixConfiguration.class)
public interface SalesDaysCommissionClient {

	@RequestMapping(value = "/salesDaysCommission/getCommissionPage",method = RequestMethod.POST)
	PageModel getCommissionPage(@RequestParam Map<String, Object> params);
	
	@RequestMapping(value = "/salesDaysCommission/addSalesDaysCommission",method = RequestMethod.POST)
	void addSalesDaysCommission(List<SalesDaysCommission> salesDaysCommissions);

	@RequestMapping(value = "/salesDaysCommission/findMapList",method = RequestMethod.POST)
	List<Object> findMapList(@RequestParam Map<String, Object> params);

	@RequestMapping(value = "/salesDaysCommission/findByTjr",method = RequestMethod.POST)
	List<InsSalesPreGrade> findByTjr(@RequestParam("salerId") Long salerId);

	@RequestMapping(value = "/salesDaysCommission/findByTjrs",method = RequestMethod.POST)
	List<InsSalesPreGrade> findByTjrs(@RequestParam("salerIds") String salerIds);

	@RequestMapping(value = "/salesDaysCommission/sumSalesFYC",method = RequestMethod.POST)
	BigDecimal sumSalesFYC(@RequestParam("salerIds") String salerIds);

	@RequestMapping(value = "/salesDaysCommission/getNumBySj",method = RequestMethod.POST)
	int getNumBySj(@RequestParam("salerId") Long salerId);
	
	@RequestMapping(value = "/salesDaysCommission/findBySj",method = RequestMethod.POST)
	List<InsSalesPreGrade> findBySj(@RequestParam("salerId") Long salerId);

	@RequestMapping(value = "/salesDaysCommission/findYcgx",method = RequestMethod.POST)
	List<InsSalesPreGrade> findYcgx(@RequestParam Map<String, Object> map);

	@RequestMapping(value = "/salesDaysCommission/insuranceSalesList",method = RequestMethod.POST)
	List<InsSalesPreGrade> insuranceSalesList(@RequestParam Map<String, Object> params);

	@RequestMapping(value = "/salesDaysCommission/getZJZXBNum",method = RequestMethod.POST)
	int getZJZXBNum(@RequestParam("salerId") Long salerId);

	@RequestMapping(value = "/salesDaysCommission/getBZZXBNum",method = RequestMethod.POST)
	int getBZZXBNum(@RequestParam("salerId") Long salerId);

	@RequestMapping(value = "/salesDaysCommission/findZJZXB",method = RequestMethod.POST)
	List<InsSalesPreGrade> findZJZXB(@RequestParam("salerId") Long salerId);

	@RequestMapping(value = "/salesDaysCommission/findBZZXB",method = RequestMethod.POST)
	List<InsSalesPreGrade> findBZZXB(@RequestParam("salerId") Long salerId);

	@RequestMapping(value = "/salesDaysCommission/findZJJTXS",method = RequestMethod.POST)
	List<DirectorAllowanceStandard> findZJJTXS(@RequestParam Map<Object, String> mapp);

	@RequestMapping(value = "/salesDaysCommission/updateSalesDaysCommission",method = RequestMethod.POST)
	void updateSalesDaysCommission(List<SalesDaysCommission> salesDaysCommissions);

	@RequestMapping(value = "/salesDaysCommission/addSalesMonthlyCommission",method = RequestMethod.POST)
	void addSalesMonthlyCommission(List<SalesMonthlyCommission> smcs);

	@RequestMapping(value = "/salesDaysCommission/getMonthlyCommissionPage",method = RequestMethod.POST)
	PageModel getMonthlyCommissionPage(@RequestParam Map<String, Object> params);

	@RequestMapping(value = "/salesDaysCommission/findDetail",method = RequestMethod.POST)
	Map<String, Object> findDetail(@RequestParam Map<String, Object> params);

	@RequestMapping(value = "/salesDaysCommission/findMDetail",method = RequestMethod.POST)
	Map<String, Object> findMDetail(@RequestParam Map<String, Object> params);

	@RequestMapping(value = "/salesDaysCommission/checkStatus",method = RequestMethod.POST)
	void checkStatus(@RequestParam Map<String, Object> params);

	@RequestMapping(value = "/salesDaysCommission/findMonthlyCommissions",method = RequestMethod.POST)
	List<Object> findMonthlyCommissions(@RequestParam Map<String, Object> params);

	@RequestMapping(value = "/salesDaysCommission/updateMonthlyCommissions",method = RequestMethod.POST)
	void updateMonthlyCommissions(List<SalesMonthlyCommission> cutCellList);

	@RequestMapping(value = "/salesDaysCommission/sumSalesZYJT",method = RequestMethod.POST)
	BigDecimal sumSalesZYJT(@RequestParam("salerIds") String salerIds);

	@RequestMapping(value = "/salesDaysCommission/quitInsuranceSalesList",method = RequestMethod.POST)
	List<InsSalesPreGrade> quitInsuranceSalesList(@RequestParam Map<String, Object> params);

   @RequestMapping(value = "/salesDaysCommission/getSalesCommissionDetail",method = RequestMethod.POST)
    PageModel getSalesCommissionDetail(@RequestParam Map<String, Object> params);

   @RequestMapping(value = "/salesDaysCommission/batchRelease",method = RequestMethod.POST)
   void batchRelease(@RequestParam Map<String, Object> map);
}
