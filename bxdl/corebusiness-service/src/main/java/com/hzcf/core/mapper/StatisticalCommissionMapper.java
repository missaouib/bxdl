package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.StatisticalCommission;

public interface StatisticalCommissionMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(StatisticalCommission record);

    int insertSelective(StatisticalCommission record);

    StatisticalCommission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StatisticalCommission record);

    int updateByPrimaryKey(StatisticalCommission record);

	List<Map<String, Object>> selectStatisticalCommissionList(Map<String, Object> params);

	long selectStatisticalCommissionListSize(Map<String, Object> params);

	List<StatisticalCommission> getStatisticalCommissionList();

	List<StatisticalCommission> getStatisticalCommissionListNow();

}