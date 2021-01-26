package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.DirectorNurtureBonusMapper;
import com.hzcf.core.service.DirectorNurtureBonusService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectorNurtureBonus;
@Service
public class DirectorNurtureBonusServiceImpl implements DirectorNurtureBonusService   {

	 @Autowired
	 private DirectorNurtureBonusMapper directorNurtureBonusMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectDirectorNurtureBonus(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = directorNurtureBonusMapper.selectDirectorNurtureBonusList(params);
        long size = directorNurtureBonusMapper.selectDirectorNurtureBonusListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updateDirectorNurtureBonus(DirectorNurtureBonus directorNurtureBonus) {
		directorNurtureBonusMapper.updateByPrimaryKeySelective(directorNurtureBonus);
	}

	@Override
	public void delDirectorNurtureBonus(String ids) {
		String[] split = ids.split(",");
		directorNurtureBonusMapper.delDirectorNurtureBonus(split);
	}

	@Override
	public void addDirectorNurtureBonus(DirectorNurtureBonus directorNurtureBonus) {

		directorNurtureBonusMapper.insertSelective(directorNurtureBonus);
	}

	@Override
	public List<DirectorNurtureBonus> getDirectorNurtureBonusList() {
		return directorNurtureBonusMapper.getDirectorNurtureBonusList();
	}

	@Override
	public int checkDirectorSize(Map<String, Object> params) {
		return directorNurtureBonusMapper.checkDirectorSize(params);
	}
	
	
	
}

