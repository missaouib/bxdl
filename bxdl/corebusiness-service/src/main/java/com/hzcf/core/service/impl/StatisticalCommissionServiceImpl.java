package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.StatisticalCommissionMapper;
import com.hzcf.core.service.StatisticalCommissionService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.StatisticalCommission;
@Service
public class StatisticalCommissionServiceImpl implements StatisticalCommissionService   {

	 @Autowired
	 private StatisticalCommissionMapper statisticalCommissionMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectStatisticalCommission(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = statisticalCommissionMapper.selectStatisticalCommissionList(params);
        long size = statisticalCommissionMapper.selectStatisticalCommissionListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updateStatisticalCommission(StatisticalCommission directlyUnderManager) {
		statisticalCommissionMapper.updateByPrimaryKeySelective(directlyUnderManager);
	}


	@Override
	public void addStatisticalCommission(StatisticalCommission directlyUnderManager) {

		statisticalCommissionMapper.insertSelective(directlyUnderManager);
	}

	@Override
	public List<StatisticalCommission> getStatisticalCommissionList() {
		return statisticalCommissionMapper.getStatisticalCommissionList();
	}

	@Override
	public List<StatisticalCommission> getStatisticalCommissionListNow() {
		 return statisticalCommissionMapper.getStatisticalCommissionListNow();
	}

	
}

