package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.DirectlyAdministeredGroup;

public interface DirectlyAdministeredGroupMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(DirectlyAdministeredGroup record);

    int insertSelective(DirectlyAdministeredGroup record);

    DirectlyAdministeredGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DirectlyAdministeredGroup record);

    int updateByPrimaryKey(DirectlyAdministeredGroup record);

	List<Map<String, Object>> selectDirectlyAdministeredGroupList(Map<String, Object> params);

	long selectDirectlyAdministeredGroupListSize(Map<String, Object> params);

	List<DirectlyAdministeredGroup> getDirectlyAdministeredGroupList();

	int checkListSize(Map<String, Object> params);
}