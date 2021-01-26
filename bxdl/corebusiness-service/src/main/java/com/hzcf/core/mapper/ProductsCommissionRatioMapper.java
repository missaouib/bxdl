package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.PartnershipCommissionSet;
import com.hzcf.pojo.product.InsuranceProductInfo;
import org.apache.ibatis.annotations.Param;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.product.ProductsCommissionRatio;

public interface ProductsCommissionRatioMapper {
	int deleteByPrimaryKey(Long productsRatioId);

    int insert(ProductsCommissionRatio record);

    int insertSelective(ProductsCommissionRatio record);

    ProductsCommissionRatio selectByPrimaryKey(Long productsRatioId);

    int updateByPrimaryKeySelective(ProductsCommissionRatio record);

    int updateByPrimaryKey(ProductsCommissionRatio record);

	List<ProductsCommissionRatio> selectProductsCommissionRatioListByProductId(Map<String, Object> params);

	void deleteByProduct(Map<String, Object> params);

	List<ProductsCommissionRatio> getSonProductList(@Param("productId") Long productId);

	void updateRenewPeriodStatus(Map<String, Object> params);

	int checkRenewPeriodSize(Map<String, Object> params);

	List<Map<String, Object>> getPartnershipListSub(Map<String, Object> params);

	long getPartnershipListSize(Map<String, Object> params);

	List<ProductsCommissionRatio> findAllInsurProductSub(Map<String, Object> paras);

	List<ProductsCommissionRatio> selectRatioByInsuranceCompanyIdAndProductsName(Map<String,Object > params);

	List<ProductsCommissionRatio> selectRatioProductsCode(@Param("productsCode")String productsCode);

}