package com.hzcf.core.mapper;

import java.util.Map;

import com.hzcf.pojo.product.InsuranceProductDetail;
import com.hzcf.pojo.product.InsuranceProductDetailWithBLOBs;

public interface InsuranceProductDetailMapper {
    int deleteByPrimaryKey(Long infoId);

    int insert(InsuranceProductDetailWithBLOBs record);

    int insertSelective(InsuranceProductDetailWithBLOBs record);

    InsuranceProductDetailWithBLOBs selectByPrimaryKey(Long infoId);

    int updateByPrimaryKeySelective(InsuranceProductDetailWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InsuranceProductDetailWithBLOBs record);

    int updateByPrimaryKey(InsuranceProductDetail record);

	InsuranceProductDetailWithBLOBs selectProductDetailByProductId(Map<String, Object> params);
}