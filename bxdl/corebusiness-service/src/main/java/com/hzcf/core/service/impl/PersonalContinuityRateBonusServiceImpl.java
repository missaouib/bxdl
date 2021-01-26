package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.PersonalContinuityRateBonusMapper;
import com.hzcf.core.service.PersonalContinuityRateBonusService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.PersonalContinuityRateBonus;
@Service
public class PersonalContinuityRateBonusServiceImpl implements PersonalContinuityRateBonusService   {

	 @Autowired
	 private PersonalContinuityRateBonusMapper personalContinuityRateBonusMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectPersonalContinuityRateBonus(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = personalContinuityRateBonusMapper.selectPersonalContinuityRateBonusList(params);
        long size = personalContinuityRateBonusMapper.selectPersonalContinuityRateBonusListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updatePersonalContinuityRateBonus(PersonalContinuityRateBonus personalContinuityRateBonus) {
		personalContinuityRateBonusMapper.updateByPrimaryKeySelective(personalContinuityRateBonus);
	}

	@Override
	public void delPersonalContinuityRateBonus(String ids) {
		String[] split = ids.split(",");
		personalContinuityRateBonusMapper.delPersonalContinuityRateBonus(split);
	}

	@Override
	public void addPersonalContinuityRateBonus(PersonalContinuityRateBonus personalContinuityRateBonus) {

		personalContinuityRateBonusMapper.insertSelective(personalContinuityRateBonus);
	}

	@Override
	public List<PersonalContinuityRateBonus> getPersonalContinuityRateBonusList() {
		return personalContinuityRateBonusMapper.getPersonalContinuityRateBonusList();
	}

	@Override
	public int checkPersonalBonusSize(Map<String, Object> params) {
		
		return personalContinuityRateBonusMapper.checkPersonalBonusSize(params);
	}
	
	
	
}

