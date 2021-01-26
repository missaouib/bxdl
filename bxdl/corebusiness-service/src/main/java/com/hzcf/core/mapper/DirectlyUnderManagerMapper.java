package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.DirectlyUnderManager;

public interface DirectlyUnderManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DirectlyUnderManager record);

    int insertSelective(DirectlyUnderManager record);

    DirectlyUnderManager selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DirectlyUnderManager record);

    int updateByPrimaryKey(DirectlyUnderManager record);

	List<DirectlyUnderManager> getDirectlyUnderManagerList();

	int checkDirectlyUnderSize(Map<String, Object> params);

	List<Map<String, Object>> selectDirectlyUnderManagerList(Map<String, Object> params);

	long selectDirectlyUnderManagerListSize(Map<String, Object> params);
}