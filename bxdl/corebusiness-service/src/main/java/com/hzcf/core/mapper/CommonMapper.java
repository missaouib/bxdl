package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.parameter.BenchmarkingDiscountCoefficient;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommonMapper {
    /**
     * 查询所有未推送待同步汇康的数量
     * @return
     */
    int getSalesNoticyHKLogsSize(Map<String, Object> params);

    List<InsuranceSalesInfo> getInsuranceSalesInfos(Map<String, Object> params);

    int getSaleOrgsNoticyHKLogsSize(Map<String, Object> params);
}