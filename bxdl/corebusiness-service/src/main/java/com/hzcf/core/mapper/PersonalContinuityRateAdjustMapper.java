package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.parameter.PersonalContinuityRateAdjust;

public interface PersonalContinuityRateAdjustMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PersonalContinuityRateAdjust record);

    int insertSelective(PersonalContinuityRateAdjust record);

    PersonalContinuityRateAdjust selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonalContinuityRateAdjust record);

    int updateByPrimaryKey(PersonalContinuityRateAdjust record);

	List<Map<String, Object>> selectPersonalContinuityRateAdjustList(Map<String, Object> params);

	long selectPersonalContinuityRateAdjustListSize(Map<String, Object> params);

	void delPersonalContinuityRateAdjust(String[] split);

	List<PersonalContinuityRateAdjust> getPersonalContinuityRateAdjustList();

	int checkListSize(Map<String, Object> params);
}