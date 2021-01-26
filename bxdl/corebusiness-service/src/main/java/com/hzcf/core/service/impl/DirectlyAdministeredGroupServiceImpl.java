package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.DirectlyAdministeredGroupMapper;
import com.hzcf.core.service.DirectlyAdministeredGroupService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.DirectlyAdministeredGroup;
@Service
public class DirectlyAdministeredGroupServiceImpl implements DirectlyAdministeredGroupService   {

	 @Autowired
	 private DirectlyAdministeredGroupMapper directlyAdministeredGroupMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectDirectlyAdministeredGroup(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = directlyAdministeredGroupMapper.selectDirectlyAdministeredGroupList(params);
        long size = directlyAdministeredGroupMapper.selectDirectlyAdministeredGroupListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updateDirectlyAdministeredGroup(DirectlyAdministeredGroup directlyAdministeredGroup) {
		directlyAdministeredGroupMapper.updateByPrimaryKeySelective(directlyAdministeredGroup);
	}


	@Override
	public void addDirectlyAdministeredGroup(DirectlyAdministeredGroup directlyAdministeredGroup) {

		directlyAdministeredGroupMapper.insertSelective(directlyAdministeredGroup);
	}

	@Override
	public List<DirectlyAdministeredGroup> getDirectlyAdministeredGroupList() {
		return directlyAdministeredGroupMapper.getDirectlyAdministeredGroupList();
	}

	@Override
	public int checkPersonalBonusSize(Map<String, Object> params) {
		
		return directlyAdministeredGroupMapper.checkListSize(params);
	}
	
	
	
}

