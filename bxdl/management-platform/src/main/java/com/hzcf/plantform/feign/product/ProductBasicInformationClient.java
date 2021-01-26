package com.hzcf.plantform.feign.product;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.product.InsuranceProductDetailWithBLOBs;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.pojo.product.ProductsCommissionRatio;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface ProductBasicInformationClient {
	
    @RequestMapping(value = "/insurance_product/do_insurance_product",method = RequestMethod.POST)
    public PageModel getProductList(@RequestParam Map<String,Object> paras);

    @RequestMapping(value = "/insurance_product/add_insurance_product",method = RequestMethod.POST)
	public Long addInsuranceProduct(@RequestBody InsuranceProductInfo insuranceProductInfo);
	
	@RequestMapping(value = "/insurance_product/update_insurance_product",method = RequestMethod.POST)
	public void updateInsuranceProduct(@RequestBody InsuranceProductInfo insuranceProductInfo);
	
	@RequestMapping(value = "/insurance_product/update_insurance_product_status",method = RequestMethod.POST)
	public void updateInsuranceProductStatus(@RequestParam Map<String, Object> paras);
	
	@RequestMapping(value = "/insurance_product/select_insurance_product_detail",method = RequestMethod.POST)
	public InsuranceProductInfo selectInsuranceBasicProduct(@RequestParam Map<String, Object> paras);
	
	@RequestMapping(value = "/insurance_product/add_insurance_product_detail",method = RequestMethod.POST)
	public void addInsuranceProductDetail(@RequestBody InsuranceProductDetailWithBLOBs insuranceProductDetailWithBLOBs);

	@RequestMapping(value = "/insurance_product/add_renew_period",method = RequestMethod.POST)
	public void addRenewPeriod(@RequestBody ProductsCommissionRatio productsCommissionRatio);

	@RequestMapping(value = "/insurance_product/update_renew_period",method = RequestMethod.POST)
	public void updateRenewPeriod(@RequestBody ProductsCommissionRatio productsCommissionRatio);
	
	@RequestMapping(value = "/insurance_product/select_insurance_product_info",method = RequestMethod.POST)
	public InsuranceProductInfo selectInsuranceProductInfo(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/insurance_product/select_insurance_product_detail_withBLOBs",method = RequestMethod.POST)
	public InsuranceProductDetailWithBLOBs selectInsuranceProductDetailWithBLOBs(@RequestParam Map<String, Object> paras);
	
	@RequestMapping(value = "/insurance_product/select_products_commission_ratio_list",method = RequestMethod.POST)
	public List<ProductsCommissionRatio> getProductsCommissionRatioList(@RequestParam Map<String, Object> paras);
	
	@RequestMapping(value = "/insurance_product/update_insurance_product_detail_withBLOBs",method = RequestMethod.POST)
	public void updateInsuranceProductDetailWithBLOBs(@RequestBody InsuranceProductDetailWithBLOBs detailWithBLOBs);

	@RequestMapping(value = "/insurance_product/delete_insurance_products_commission_ratio",method = RequestMethod.POST)
	public void deleteInsuranceProductsCommissionRatio(@RequestParam Map<String, Object> paras);
	
	@RequestMapping(value = "/insurance_product/update_renew_period_status",method = RequestMethod.POST)
	public void updateRenewPeriodStatus(@RequestParam  Map<String, Object> paras);

	@RequestMapping(value = "/insurance_product/check_renew_period_size",method = RequestMethod.POST)
	public int checkRenewPeriodSize(@RequestParam  Map<String, Object> params);
	
	@RequestMapping(value = "/insurance_product/seleck_ins_company_org",method = RequestMethod.POST)
	public InsuranceDept selectInsuranceCompanyOrg(@RequestParam   Map<String, Object> paras);

	@RequestMapping(value = "/insurance_product/findInsurProducts",method = RequestMethod.POST)
	public List<InsuranceProductInfo> findInsurProducts( Map<String, Object> paras);

	@RequestMapping(value = "/insurance_product/findAllInsurProductSub",method = RequestMethod.POST)
	List<ProductsCommissionRatio> findAllInsurProductSub(@RequestParam Map<String,Object> paras);
	@RequestMapping(value = "/insurance_product/select_insurance_basic_product_son",method = RequestMethod.POST)
	InsuranceProductInfo selectInsuranceBasicProductSon(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/insurance_product/findByInsuranceCompanyIdAndProductName",method = RequestMethod.POST)
	boolean findByInsuranceCompanyIdAndProductName(@RequestParam(value="insuranceCompanyId", required = false) Long insuranceCompanyId, @RequestParam("productName")String productName);

	@RequestMapping(value = "/insurance_product/findByProductCode",method = RequestMethod.POST)
	boolean findByProductCode(@RequestParam(value = "productCode") String productCode);

	@RequestMapping(value = "/insurance_product/findRatioByInsuranceCompanyIdAndProductsName",method = RequestMethod.POST)
	boolean findRatioByInsuranceCompanyIdAndProductsName(@RequestParam Map<String,Object> params);

	@RequestMapping(value = "/insurance_product/findRatioProductsCode",method = RequestMethod.POST)
	boolean findRatioProductsCode(@RequestParam(value = "productsCode")String productsCode);

	@RequestMapping(value ="/insurance_product/findByPrimaryKey",method =RequestMethod.POST)
	InsuranceProductInfo findByPrimaryKey(@RequestParam(value = "productId") Long productId);

	@RequestMapping(value ="/insurance_product/findRatioProductKey",method =RequestMethod.POST)
	ProductsCommissionRatio findRatioProductKey(@RequestParam(value = "productsRatioId") Long productsRatioId);
}
