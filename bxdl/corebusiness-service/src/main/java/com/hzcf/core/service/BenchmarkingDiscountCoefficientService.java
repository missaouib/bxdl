package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.BenchmarkingDiscountCoefficient;

public interface BenchmarkingDiscountCoefficientService {

	PageModel selectBenchmarkingDiscountCoefficient(Map<String, Object> paras);

	void updateBenchmarkingDiscountCoefficient(BenchmarkingDiscountCoefficient benchmarkingDiscountCoefficient);

	void addBenchmarkingDiscountCoefficient(BenchmarkingDiscountCoefficient benchmarkingDiscountCoefficient);

	List<BenchmarkingDiscountCoefficient> getBenchmarkingDiscountCoefficientList();

	int checkPersonalBonusSize(Map<String, Object> params);
	
}
