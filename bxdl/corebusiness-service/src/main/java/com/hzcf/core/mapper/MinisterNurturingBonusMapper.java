package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.MinisterNurturingBonus;

public interface MinisterNurturingBonusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MinisterNurturingBonus record);

    int insertSelective(MinisterNurturingBonus record);

    MinisterNurturingBonus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MinisterNurturingBonus record);

    int updateByPrimaryKey(MinisterNurturingBonus record);

	List<Map<String, Object>> selectMinisterNurturingBonusList(Map<String, Object> params);

	long selectMinisterNurturingBonusListSize(Map<String, Object> params);

	void delMinisterNurturingBonus(String[] split);

	List<MinisterNurturingBonus> getMinisterNurturingList();

	int checkListSize(Map<String, Object> params);
}