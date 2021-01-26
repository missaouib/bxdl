package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.ExhibitionAllowanceMapper;
import com.hzcf.core.service.ExhibitionAllowanceService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.ExhibitionAllowance;
@Service
public class ExhibitionAllowanceServiceImpl implements ExhibitionAllowanceService   {

	 @Autowired
	 private ExhibitionAllowanceMapper directlyUnderManagerMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectExhibitionAllowance(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = directlyUnderManagerMapper.selectExhibitionAllowanceList(params);
        long size = directlyUnderManagerMapper.selectExhibitionAllowanceListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updateExhibitionAllowance(ExhibitionAllowance directlyUnderManager) {
		directlyUnderManagerMapper.updateByPrimaryKeySelective(directlyUnderManager);
	}


	@Override
	public void addExhibitionAllowance(ExhibitionAllowance directlyUnderManager) {

		directlyUnderManagerMapper.insertSelective(directlyUnderManager);
	}

	@Override
	public List<ExhibitionAllowance> getExhibitionAllowanceList() {
		return directlyUnderManagerMapper.getExhibitionAllowanceList();
	}

	@Override
	public int checkExhibitionAllowanceSize(Map<String, Object> params) {
		return directlyUnderManagerMapper.checkExhibitionAllowanceSize(params);
	}


	
	
}

