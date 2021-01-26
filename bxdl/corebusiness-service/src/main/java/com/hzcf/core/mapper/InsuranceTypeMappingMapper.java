package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.pojo.product.InsuranceTypeMapping;
import com.hzcf.pojo.product.ProductsCommissionRatio;

public interface InsuranceTypeMappingMapper {
    int deleteByPrimaryKey(Long insuranceTypeId);

    int insert(InsuranceTypeMapping record);

    int insertSelective(InsuranceTypeMapping record);

    InsuranceTypeMapping selectByPrimaryKey(Long insuranceTypeId);

    int updateByPrimaryKeySelective(InsuranceTypeMapping record);

    int updateByPrimaryKey(InsuranceTypeMapping record);

	List<ProductsCommissionRatio> selectByAll();

	List<Map<String, Object>> selectInsuranceTypeList(Map<String, Object> params);

	long selectInsuranceTypeListSize(Map<String, Object> params);

	void delInsuranceType(String[] split);

	void addErrorData(@Param("insuranceTypeId") String insuranceTypeId,@Param("sys") String sys);

	void insertSelectiveReturnKey(InsuranceTypeMapping insuranceTypeMapping);

	InsuranceTypeMapping selectInsuranceTypeByid(Map<String, Object> params);

	List<InsuranceTypeMapping> findInsuranceTypeMapping(Map<String, Object> params);
}