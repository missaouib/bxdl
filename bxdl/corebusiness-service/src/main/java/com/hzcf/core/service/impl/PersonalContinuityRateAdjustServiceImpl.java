package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.PersonalContinuityRateAdjustMapper;
import com.hzcf.core.service.PersonalContinuityRateAdjustService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.PersonalContinuityRateAdjust;
@Service
public class PersonalContinuityRateAdjustServiceImpl implements PersonalContinuityRateAdjustService   {

	 @Autowired
	 private PersonalContinuityRateAdjustMapper personalContinuityRateAdjustMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectPersonalContinuityRateAdjust(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = personalContinuityRateAdjustMapper.selectPersonalContinuityRateAdjustList(params);
        long size = personalContinuityRateAdjustMapper.selectPersonalContinuityRateAdjustListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updatePersonalContinuityRateAdjust(PersonalContinuityRateAdjust personalContinuityRateAdjust) {
		personalContinuityRateAdjustMapper.updateByPrimaryKeySelective(personalContinuityRateAdjust);
	}

	@Override
	public void delPersonalContinuityRateAdjust(String ids) {
		String[] split = ids.split(",");
		personalContinuityRateAdjustMapper.delPersonalContinuityRateAdjust(split);
	}

	@Override
	public void addPersonalContinuityRateAdjust(PersonalContinuityRateAdjust personalContinuityRateAdjust) {

		personalContinuityRateAdjustMapper.insertSelective(personalContinuityRateAdjust);
	}

	@Override
	public List<PersonalContinuityRateAdjust> getPersonalContinuityRateAdjustList() {
		return personalContinuityRateAdjustMapper.getPersonalContinuityRateAdjustList();
	}

	@Override
	public int checkPersonalAdjustSize(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return personalContinuityRateAdjustMapper.checkListSize(params);
	}
	
	
	
}

