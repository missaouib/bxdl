package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.IncreaseAllowance;

public interface IncreaseAllowanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IncreaseAllowance record);

    int insertSelective(IncreaseAllowance record);

    IncreaseAllowance selectByPrimaryKey(Long id);
    
    List<Map<String, Object>> selectIncreaseAllowanceList(Map<String, Object> params);

	long selectIncreaseAllowanceListSize(Map<String, Object> params);

    int updateByPrimaryKeySelective(IncreaseAllowance record);

    int updateByPrimaryKey(IncreaseAllowance record);

	List<IncreaseAllowance> getIncreaseAllowanceList();

	int checkIncreaseAllowanceSize(Map<String, Object> params);

	
}