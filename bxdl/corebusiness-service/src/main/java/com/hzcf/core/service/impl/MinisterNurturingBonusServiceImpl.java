package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.MinisterNurturingBonusMapper;
import com.hzcf.core.service.MinisterNurturingBonusService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.MinisterNurturingBonus;
@Service
public class MinisterNurturingBonusServiceImpl implements MinisterNurturingBonusService   {

	 @Autowired
	 private MinisterNurturingBonusMapper ministerNurturingBonusMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectMinisterNurturingBonus(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = ministerNurturingBonusMapper.selectMinisterNurturingBonusList(params);
        long size = ministerNurturingBonusMapper.selectMinisterNurturingBonusListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updateMinisterNurturingBonus(MinisterNurturingBonus ministerNurturingBonus) {
		ministerNurturingBonusMapper.updateByPrimaryKeySelective(ministerNurturingBonus);
	}

	@Override
	public void delMinisterNurturingBonus(String ids) {
		String[] split = ids.split(",");
		ministerNurturingBonusMapper.delMinisterNurturingBonus(split);
	}

	@Override
	public void addMinisterNurturingBonus(MinisterNurturingBonus ministerNurturingBonus) {

		ministerNurturingBonusMapper.insertSelective(ministerNurturingBonus);
	}

	@Override
	public List<MinisterNurturingBonus> getMinisterNurturingList() {
		return ministerNurturingBonusMapper.getMinisterNurturingList();
	}

	@Override
	public int checkMinisterSize(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return ministerNurturingBonusMapper.checkListSize(params);
	}
	
	
	
}

