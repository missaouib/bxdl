package com.hzcf.core.service.impl;

import com.hzcf.core.mapper.SalesGradeMapper;
import com.hzcf.core.mapper.SalesTeamInfoMapper;
import com.hzcf.core.service.SalesGradeService;
import com.hzcf.core.service.SalesTeamInfoService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.insurance.SalesGrade;
import com.hzcf.pojo.insurance.SalesOrgDetail;
import com.hzcf.pojo.insurance.SalesTeamInfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SalesGradeServiceImpl implements SalesGradeService {

	@Autowired
    private SalesGradeMapper salesGradeMapper;

    @Override
    public void addSalesGrade(SalesGrade salesGrade) {
    	salesGradeMapper.addSalesGrade(salesGrade);
    } 
    
    @Override
    public PageModel getSalesGradePage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getPageSize());
        List<Map<String,Object>> list = salesGradeMapper.getSalesGradePage(params);
        long size = salesGradeMapper.getSalesGradeListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

	@Override
	public SalesGrade selectById(Map<String, Object> params) {
		SalesGrade salesGrade = salesGradeMapper.selectById(params);
		return salesGrade;
	}

	@Override
	public void updateSalesGrade(SalesGrade salesGrade) {
		salesGradeMapper.updateSalesGrade(salesGrade);
	}

	@Override
	public List<SalesGrade> getSalesGradeList(Map<String, Object> params) {
		return salesGradeMapper.getSalesGradeList(params);
	}	
	
	
	
}

