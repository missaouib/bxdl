package com.hzcf.core.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.core.service.SalesDaysCommissionService;
import com.hzcf.core.service.SalesGradeService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesGrade;
import com.hzcf.pojo.insurance.sales.DirectorAllowanceStandard;
import com.hzcf.pojo.insurance.sales.InsSalesPreGrade;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.insurance.sales.SalesMonthlyCommission;

/**
 * 佣金
 * @author yl
 *
 */
@Controller
@RequestMapping("/salesDaysCommission")
public class SalesDaysCommissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SalesDaysCommissionService salesDaysCommissionService;
    
    /**
     * 统计入库
     * */
    @RequestMapping("/addSalesDaysCommission")
    @ResponseBody
    public void addSalesDaysCommission(@RequestBody List<SalesDaysCommission> salesDaysCommission){
    	salesDaysCommissionService.addSalesDaysCommission(salesDaysCommission);
    }
    
    /**
     * 统计入库
     * */
    @RequestMapping("/checkStatus")
    @ResponseBody
    public void checkStatus(@RequestParam Map<String,Object> params){
    	salesDaysCommissionService.checkStatus(params);
    }
    
    /**
     * 统计入库
     * */
    @RequestMapping("/addSalesMonthlyCommission")
    @ResponseBody
    public void addSalesMonthlyCommission(@RequestBody List<SalesMonthlyCommission> salesMonthlyCommission){
    	salesDaysCommissionService.addSalesMonthlyCommission(salesMonthlyCommission,null);
    }
    
    /**
     * 统计入库
     * */
    @RequestMapping("/updateSalesDaysCommission")
    @ResponseBody
    public void updateSalesDaysCommission(@RequestBody List<SalesDaysCommission> salesDaysCommission){
    	salesDaysCommissionService.updateSalesDaysCommission(salesDaysCommission);
    }
    
    /**
     * 导入加扣+税额
     * */
    @RequestMapping("/updateMonthlyCommissions")
    @ResponseBody
    public void updateMonthlyCommissions(@RequestBody List<SalesMonthlyCommission> params){
    	salesDaysCommissionService.updateMonthlyCommissions(params);
    }
    
    /**
     * 分页查询
     * */
    @RequestMapping("/getCommissionPage")
    @ResponseBody
    public PageModel getCommissionPage(@RequestParam Map<String,Object> params){
    	return salesDaysCommissionService.getCommissionPage(params);
    }  
    
    /**
     * 查询明细
     * */
    @RequestMapping("/findDetail")
    @ResponseBody
    public Map<String, Object> findDetail(@RequestParam Map<String,Object> params){
    	return salesDaysCommissionService.findDetail(params);
    }
    
    /**
     * 查询明细
     * */
    @RequestMapping("/findMDetail")
    @ResponseBody
    public Map<String, Object> findMDetail(@RequestParam Map<String,Object> params){
    	return salesDaysCommissionService.findMDetail(params);
    }
    
    /**
     * 分页查询
     * */
    @RequestMapping("/getMonthlyCommissionPage")
    @ResponseBody
    public PageModel getMonthlyCommissionPage(@RequestParam Map<String,Object> params){
    	return salesDaysCommissionService.getMonthlyCommissionPage(params);
    }
    
	 /**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/findMapList")
    @ResponseBody
    public List<Object> findMapList(@RequestParam Map<String,Object> params){
    	return salesDaysCommissionService.findMapList(params);
    }
    
	 /**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/findMonthlyCommissions")
    @ResponseBody
    public List<Object> findMonthlyCommissions(@RequestParam Map<String,Object> params){
    	return salesDaysCommissionService.findMonthlyCommissions(params);
    }

	 /**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/findByTjr")
    @ResponseBody
    public List<InsSalesPreGrade> findByTjr(@RequestParam(value="salerId") Long salerId){
    	return salesDaysCommissionService.findByTjr(salerId);
    }

	 /**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/findByTjrs")
    @ResponseBody
    public List<InsSalesPreGrade> findByTjrs(@RequestParam(value="salerIds") String salerIds){
    	return salesDaysCommissionService.findByTjrs(salerIds);
    }
    
	 /**
    *
    *根据条件统计
    * */
    @RequestMapping("/sumSalesFYC")
    @ResponseBody
    public BigDecimal sumSalesFYC(@RequestParam(value="salerIds") String salerIds){
    	return salesDaysCommissionService.sumSalesFYC(salerIds);
    }
    
	 /**
    *
    *根据条件统计
    * */
    @RequestMapping("/sumSalesZYJT")
    @ResponseBody
    public BigDecimal sumSalesZYJT(@RequestParam(value="salerIds") String salerIds){
    	return salesDaysCommissionService.sumSalesZYJT(salerIds);
    }
    
	/**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/findBySj")
    @ResponseBody
    public List<InsSalesPreGrade> findBySj(@RequestParam(value="salerId") Long salerId){
    	return salesDaysCommissionService.findBySj(salerId);
    }

    /**
     *
     *根据条件查不分页列表
     * */
    @RequestMapping("/findYcgx")
    @ResponseBody
    public List<InsSalesPreGrade> findYcgx(@RequestParam Map<String,Object> map){
        return salesDaysCommissionService.findYcgx(map);
    }
    
	/**
    *
    *
    * */
    @RequestMapping("/getNumBySj")
    @ResponseBody
    public int getNumBySj(@RequestParam(value="salerId") Long salerId){
    	return salesDaysCommissionService.getNumBySj(salerId);
    }
    
    
	/**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/insuranceSalesList")
    @ResponseBody
    public List<InsSalesPreGrade> insuranceSalesList(@RequestParam Map<String,Object> map){
    	return salesDaysCommissionService.insuranceSalesList(map);
    }
    
	/**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/quitInsuranceSalesList")
    @ResponseBody
    public List<InsSalesPreGrade> quitInsuranceSalesList(@RequestParam Map<String,Object> map){
    	return salesDaysCommissionService.quitInsuranceSalesList(map);
    }
    
	/**
    *
    *
    * */
    @RequestMapping("/getZJZXBNum")
    @ResponseBody
    public int getZJZXBNum(@RequestParam(value="salerId") Long salerId){
    	return salesDaysCommissionService.getZJZXBNum(salerId);
    }
    
	/**
    *
    *
    * */
    @RequestMapping("/getBZZXBNum")
    @ResponseBody
    public int getBZZXBNum(@RequestParam(value="salerId") Long salerId){
    	return salesDaysCommissionService.getBZZXBNum(salerId);
    }
    
	/**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/findZJZXB")
    @ResponseBody
    public List<InsSalesPreGrade> findZJZXB(@RequestParam(value="salerId") Long salerId){
    	return salesDaysCommissionService.findZJZXB(salerId);
    }
	/**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/findBZZXB")
    @ResponseBody
    public List<InsSalesPreGrade> findBZZXB(@RequestParam(value="salerId") Long salerId){
    	return salesDaysCommissionService.findBZZXB(salerId);
    }
    
	/**
    *
    *根据条件查不分页列表
    * */
    @RequestMapping("/findZJJTXS")
    @ResponseBody
    public List<DirectorAllowanceStandard> findZJJTXS(@RequestParam Map<String,Object> map){
    	return salesDaysCommissionService.findZJJTXS(map);
    }

    /**
     *@描述  查看佣金明细详情
     *@创建人 qin lina
     *@创建时间 2020/5/7
     */
    @RequestMapping("/getSalesCommissionDetail")
    @ResponseBody
    public PageModel getSalesCommissionDetail(@RequestParam Map<String,Object> map){
        return  salesDaysCommissionService.getSalesCommissionDetail(map);
    }


    /**
     *@描述  批量发放
     *@创建人 qin lina
     *@创建时间 2020/5/8
     */
    @RequestMapping("/batchRelease")
    @ResponseBody
    public void batchRelease(@RequestParam Map<String,Object> map){
          salesDaysCommissionService.batchRelease(map);
    }
}
