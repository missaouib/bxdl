package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.PersonalContinuityRateBonus;

public interface PersonalContinuityRateBonusService {

	PageModel selectPersonalContinuityRateBonus(Map<String, Object> paras);

	void updatePersonalContinuityRateBonus(PersonalContinuityRateBonus personalContinuityRateBonus);

	void delPersonalContinuityRateBonus(String ids);

	void addPersonalContinuityRateBonus(PersonalContinuityRateBonus personalContinuityRateBonus);

	List<PersonalContinuityRateBonus> getPersonalContinuityRateBonusList();

	int checkPersonalBonusSize(Map<String, Object> params);
	
}
