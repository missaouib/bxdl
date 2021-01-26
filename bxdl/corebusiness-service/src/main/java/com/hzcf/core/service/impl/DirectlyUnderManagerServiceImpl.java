package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.DirectlyUnderManagerMapper;
import com.hzcf.core.service.DirectlyUnderManagerService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectlyUnderManager;
@Service
public class DirectlyUnderManagerServiceImpl implements DirectlyUnderManagerService   {

	 @Autowired
	 private DirectlyUnderManagerMapper directlyUnderManagerMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectDirectlyUnderManager(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = directlyUnderManagerMapper.selectDirectlyUnderManagerList(params);
        long size = directlyUnderManagerMapper.selectDirectlyUnderManagerListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updateDirectlyUnderManager(DirectlyUnderManager directlyUnderManager) {
		directlyUnderManagerMapper.updateByPrimaryKeySelective(directlyUnderManager);
	}


	@Override
	public void addDirectlyUnderManager(DirectlyUnderManager directlyUnderManager) {

		directlyUnderManagerMapper.insertSelective(directlyUnderManager);
	}

	@Override
	public List<DirectlyUnderManager> getDirectlyUnderManagerList() {
		return directlyUnderManagerMapper.getDirectlyUnderManagerList();
	}

	@Override
	public int checkDirectlyUnderSize(Map<String, Object> params) {
		return directlyUnderManagerMapper.checkDirectlyUnderSize(params);
	}


	
	
}

