package com.hzcf.core.mapper;

import com.hzcf.pojo.parameter.BenchmarkingDiscountCoefficient;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BenchmarkingDiscountCoefficientMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(BenchmarkingDiscountCoefficient record);

    int insertSelective(BenchmarkingDiscountCoefficient record);

    BenchmarkingDiscountCoefficient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BenchmarkingDiscountCoefficient record);

    int updateByPrimaryKey(BenchmarkingDiscountCoefficient record);

	List<Map<String, Object>> selectBenchmarkingDiscountCoefficientList(Map<String, Object> params);

	long selectBenchmarkingDiscountCoefficientListSize(Map<String, Object> params);

	List<BenchmarkingDiscountCoefficient> getBenchmarkingDiscountCoefficientList();

	int checkListSize(Map<String, Object> params);

    /**
     * <p>根据产品id查询标保折标系数</p>
     * @param productId 产品id
     * @return
     */
    String queryCoefficientByProductId(@Param("productId") long productId);
}