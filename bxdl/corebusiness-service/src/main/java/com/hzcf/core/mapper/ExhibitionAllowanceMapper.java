package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.ExhibitionAllowance;

public interface ExhibitionAllowanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExhibitionAllowance record);

    int insertSelective(ExhibitionAllowance record);

    ExhibitionAllowance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExhibitionAllowance record);

    int updateByPrimaryKey(ExhibitionAllowance record);

	List<Map<String, Object>> selectExhibitionAllowanceList(Map<String, Object> params);

	long selectExhibitionAllowanceListSize(Map<String, Object> params);

	List<ExhibitionAllowance> getExhibitionAllowanceList();

	int checkExhibitionAllowanceSize(Map<String, Object> params);
}