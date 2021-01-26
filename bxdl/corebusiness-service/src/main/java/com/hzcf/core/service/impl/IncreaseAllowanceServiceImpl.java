package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.IncreaseAllowanceMapper;
import com.hzcf.core.service.IncreaseAllowanceService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.IncreaseAllowance;
@Service
public class IncreaseAllowanceServiceImpl implements IncreaseAllowanceService   {

	 @Autowired
	 private IncreaseAllowanceMapper increaseAllowanceMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectIncreaseAllowance(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = increaseAllowanceMapper.selectIncreaseAllowanceList(params);
        long size = increaseAllowanceMapper.selectIncreaseAllowanceListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updateIncreaseAllowance(IncreaseAllowance increaseAllowance) {
		increaseAllowanceMapper.updateByPrimaryKeySelective(increaseAllowance);
	}


	@Override
	public void addIncreaseAllowance(IncreaseAllowance increaseAllowance) {

		increaseAllowanceMapper.insertSelective(increaseAllowance);
	}

	@Override
	public List<IncreaseAllowance> getIncreaseAllowanceList() {
		return increaseAllowanceMapper.getIncreaseAllowanceList();
	}

	@Override
	public int checkIncreaseAllowanceSize(Map<String, Object> params) {
		return increaseAllowanceMapper.checkIncreaseAllowanceSize(params);
	}


	
	
}

