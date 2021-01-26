package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.PersonalContinuityRateBonus;

public interface PersonalContinuityRateBonusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PersonalContinuityRateBonus record);

    int insertSelective(PersonalContinuityRateBonus record);

    PersonalContinuityRateBonus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonalContinuityRateBonus record);

    int updateByPrimaryKey(PersonalContinuityRateBonus record);

	void delPersonalContinuityRateBonus(String[] split);

	List<PersonalContinuityRateBonus> getPersonalContinuityRateBonusList();

	List<Map<String, Object>> selectPersonalContinuityRateBonusList(Map<String, Object> params);

	long selectPersonalContinuityRateBonusListSize(Map<String, Object> params);

	int checkPersonalBonusSize(Map<String, Object> params);
}