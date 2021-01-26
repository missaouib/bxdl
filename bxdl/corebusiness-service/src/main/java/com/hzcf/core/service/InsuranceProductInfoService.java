package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.product.InsuranceProductDetailWithBLOBs;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.pojo.product.ProductsCommissionRatio;

public interface InsuranceProductInfoService {

	PageModel getInsuranceProductList(Map<String, Object> params);

	void addInsuranceProduct(InsuranceProductInfo insuranceProduct);

	void updateInsuranceProduct(InsuranceProductInfo insuranceProduct);

	InsuranceProductInfo selectInsuranceProductDetail(Map<String, Object> params);

	void updateInsuranceProductStatus(Map<String, Object> params);

	void addInsuranceProductDetail(InsuranceProductDetailWithBLOBs insuranceProductDetailWithBLOBs);

	void addRenewPeriod(ProductsCommissionRatio productsCommissionRatio);

	InsuranceProductInfo selectInsuranceProductInfo(Map<String, Object> params);

	InsuranceProductDetailWithBLOBs selectInsuranceProductDetailWithBLOBs(Map<String, Object> params);

	List<ProductsCommissionRatio> getProductsCommissionRatioList(Map<String, Object> params);

	void updateInsuranceProductDetailWithBLOBs(InsuranceProductDetailWithBLOBs productDetailWithBLOBs);

	void deleteInsuranceProductsCommissionRatio(Map<String, Object> params);

	void updateRenewPeriod(ProductsCommissionRatio productsCommissionRatio);

	void updateRenewPeriodStatus(Map<String, Object> params);

	int checkRenewPeriodSize(Map<String, Object> params);

	InsuranceDept selectInsuranceCompanyOrg(Map<String, Object> params);

	List<InsuranceProductInfo> findInsurProducts(Map<String, Object> params);


	List<ProductsCommissionRatio> findAllInsurProductSub(Map<String, Object> paras);

    InsuranceProductInfo selectInsuranceBasicProductSon(Map<String, Object> paras);

    boolean findByInsuranceCompanyIdAndProductName(Long insuranceCompanyId, String productName);

    boolean findByProductCode(String productCode);

    boolean  findRatioByInsuranceCompanyIdAndProductsName(Map<String,Object > params);

    boolean findRatioProductsCode(String productsCode);

    InsuranceProductInfo findByPrimaryKey(Long productId);

    ProductsCommissionRatio findRatioProductKey(Long productsRatioId);
}
