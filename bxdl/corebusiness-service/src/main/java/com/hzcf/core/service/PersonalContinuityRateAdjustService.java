package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.PersonalContinuityRateAdjust;

public interface PersonalContinuityRateAdjustService {

	PageModel selectPersonalContinuityRateAdjust(Map<String, Object> paras);

	void updatePersonalContinuityRateAdjust(PersonalContinuityRateAdjust personalContinuityRateAdjust);

	void delPersonalContinuityRateAdjust(String ids);

	void addPersonalContinuityRateAdjust(PersonalContinuityRateAdjust personalContinuityRateAdjust);

	List<PersonalContinuityRateAdjust> getPersonalContinuityRateAdjustList();

	int checkPersonalAdjustSize(Map<String, Object> params);
	
}