package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.BenchmarkingDiscountCoefficientMapper;
import com.hzcf.core.service.BenchmarkingDiscountCoefficientService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.BenchmarkingDiscountCoefficient;
@Service
public class BenchmarkingDiscountCoefficientServiceImpl implements BenchmarkingDiscountCoefficientService   {

	 @Autowired
	 private BenchmarkingDiscountCoefficientMapper benchmarkingDiscountCoefficientMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectBenchmarkingDiscountCoefficient(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = benchmarkingDiscountCoefficientMapper.selectBenchmarkingDiscountCoefficientList(params);
        long size = benchmarkingDiscountCoefficientMapper.selectBenchmarkingDiscountCoefficientListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public void updateBenchmarkingDiscountCoefficient(BenchmarkingDiscountCoefficient benchmarkingDiscountCoefficient) {
		benchmarkingDiscountCoefficientMapper.updateByPrimaryKeySelective(benchmarkingDiscountCoefficient);
	}


	@Override
	public void addBenchmarkingDiscountCoefficient(BenchmarkingDiscountCoefficient benchmarkingDiscountCoefficient) {

		benchmarkingDiscountCoefficientMapper.insertSelective(benchmarkingDiscountCoefficient);
	}

	@Override
	public List<BenchmarkingDiscountCoefficient> getBenchmarkingDiscountCoefficientList() {
		return benchmarkingDiscountCoefficientMapper.getBenchmarkingDiscountCoefficientList();
	}

	@Override
	public int checkPersonalBonusSize(Map<String, Object> params) {
		
		return benchmarkingDiscountCoefficientMapper.checkListSize(params);
	}
	
	
	
}

