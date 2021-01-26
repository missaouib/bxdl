package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.pojo.product.InsuranceProductInfo;

public interface InsuranceProductInfoMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(InsuranceProductInfo record);

    int insertSelective(InsuranceProductInfo record);

    InsuranceProductInfo selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(InsuranceProductInfo record);

    int updateByPrimaryKey(InsuranceProductInfo record);
    
    /**
     * 分页查询产品
     * */
    List<Map<String,Object>> getInsuranceProductList(Map<String,Object> params);
    /**
     * 查询结果size
     * */
    Long getInsuranceProductSize (Map<String,Object> params);
    
    /**
     * 查看详情
     * */
    InsuranceProductInfo selectInsuranceProductDetail(Map<String,Object> params);

    /**
     * 更新产品状态
     * @param params
     */
	void updateInsuranceProductStatus(Map<String, Object> params);

	/**
	 * 查询详情传入map
	 * @param params
	 * @return
	 */
	InsuranceProductInfo selectInsuranceProductInfoDetail(Map<String, Object> params);

	/**
	 * 插入日志
	 * @param string
	 * @param sys
	 */
	void addErrorData(@Param("productId") String productId,@Param("sys") String sys);
	InsuranceProductInfo selectByPrimaryKeyFormat(Long productId);

	/**
	 * 新增返回主键
	 * @param insuranceProduct
	 */
	void insertSelectiveReturnKey(InsuranceProductInfo insuranceProduct);

	/**
	 * 查询父产品和子产品列表
	 * @return
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	List<Map<String, Object>> findCompanyRatioByPage(Map<String, Object> paramsCondition);

	Long findCompanyRatioPageCount(Map<String, Object> paramsCondition);

	//不分页查询产品列表
	List<InsuranceProductInfo> findInsurProducts(Map<String, Object> params);

    InsuranceProductInfo selectInsuranceBasicProductSon(Map<String, Object> paras);

    //同一保险公司下不能有相同的产品名称
	List<InsuranceProductInfo> selectByInsuranceCompanyIdAndProductName(@Param("insuranceCompanyId")Long insuranceCompanyId, @Param("productName")String productName);

	//查询是否有相同产品代码
	List<InsuranceProductInfo> selectByProductCode(@Param("productCode")String productCode);


}