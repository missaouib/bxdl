package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.DirectorNurtureBonus;
import com.hzcf.pojo.parameter.MinisterNurturingBonus;

public interface DirectorNurtureBonusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DirectorNurtureBonus record);

    int insertSelective(DirectorNurtureBonus record);

    DirectorNurtureBonus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DirectorNurtureBonus record);

    int updateByPrimaryKey(DirectorNurtureBonus record);

	List<Map<String, Object>> selectDirectorNurtureBonusList(Map<String, Object> params);

	long selectDirectorNurtureBonusListSize(Map<String, Object> params);

	void delDirectorNurtureBonus(String[] split);

	List<DirectorNurtureBonus> getDirectorNurtureBonusList();

	int checkDirectorSize(Map<String, Object> params);
}