package com.hzcf.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.BenchmarkingDiscountCoefficientService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.BenchmarkingDiscountCoefficient;

@Controller
@RequestMapping("/benchmarking_discount_manager")
public class BenchmarkingDiscountCoefficientController {
    @Autowired
    private BenchmarkingDiscountCoefficientService benchmarkingDiscountCoefficientService;

    @RequestMapping("/select_benchmarking_discount")
    @ResponseBody
    public PageModel selectBenchmarkingDiscountCoefficient(@RequestParam Map<String,Object> paras){
        return benchmarkingDiscountCoefficientService.selectBenchmarkingDiscountCoefficient(paras);
    }
    @RequestMapping("/add_benchmarking_discount")
    @ResponseBody
    public void addBenchmarkingDiscountCoefficient(@RequestBody BenchmarkingDiscountCoefficient benchmarkingDiscountCoefficient){
         benchmarkingDiscountCoefficientService.addBenchmarkingDiscountCoefficient(benchmarkingDiscountCoefficient);
    }
    @RequestMapping("/update_benchmarking_discount")
    @ResponseBody
    public void updateBenchmarkingDiscountCoefficient(@RequestBody BenchmarkingDiscountCoefficient benchmarkingDiscountCoefficient){
        benchmarkingDiscountCoefficientService.updateBenchmarkingDiscountCoefficient(benchmarkingDiscountCoefficient);
    }
    
    @RequestMapping("/benchmarking_discount_list")
    @ResponseBody
    public List<BenchmarkingDiscountCoefficient> getBenchmarkingDiscountList(){
    	return  benchmarkingDiscountCoefficientService.getBenchmarkingDiscountCoefficientList();
    }
    
    @RequestMapping("/benchmarking_discount_size")
    @ResponseBody
    public int checkPersonalBonusSize(@RequestParam Map<String, Object> params){
		return benchmarkingDiscountCoefficientService.checkPersonalBonusSize(params);
    	
    }; 
    
    
}
