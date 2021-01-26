package com.hzcf.plantform.feign.parameter;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.BenchmarkingDiscountCoefficient;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface BenchmarkingDiscountCoefficientClient {
    @RequestMapping(value = "/benchmarking_discount_manager/select_benchmarking_discount",method = RequestMethod.POST)
    public PageModel selectBenchmarkingDiscountCoefficient(@RequestParam Map<String,Object> paras);
    @RequestMapping(value = "/benchmarking_discount_manager/add_benchmarking_discount",method = RequestMethod.POST)
    void addBenchmarkingDiscountCoefficient(@RequestBody BenchmarkingDiscountCoefficient benchmarkingDiscount);
    @RequestMapping(value = "/benchmarking_discount_manager/update_benchmarking_discount",method = RequestMethod.POST)
    void updateBenchmarkingDiscountCoefficient(@RequestBody BenchmarkingDiscountCoefficient benchmarkingDiscount);

    @RequestMapping(value = "/benchmarking_discount_manager/benchmarking_discount_list",method = RequestMethod.POST)
	public List<BenchmarkingDiscountCoefficient> getBenchmarkingDiscountCoefficientList();
    
    @RequestMapping(value = "/benchmarking_discount_manager/benchmarking_discount_size",method = RequestMethod.POST)
	public int checkBenchmarkingDiscountSize(@RequestParam Map<String, Object> params);
    
    
}
