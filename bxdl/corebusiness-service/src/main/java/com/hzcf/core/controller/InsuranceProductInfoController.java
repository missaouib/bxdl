package com.hzcf.core.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hzcf.core.service.InsuranceProductInfoService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.product.InsuranceProductDetailWithBLOBs;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.pojo.product.ProductsCommissionRatio;

/**
 * 保险产品管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/insurance_product")
public class InsuranceProductInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Autowired
    private InsuranceProductInfoService insuranceProductInfoService;
    
    /**
     * 分页查询保险产品
     * */	
    @RequestMapping("/do_insurance_product")
    @ResponseBody
    public PageModel doInsuranceProduct(@RequestParam Map<String,Object> params){
    
    return insuranceProductInfoService.getInsuranceProductList(params);
    }
    /**
     * 添加产品
     * */
    @RequestMapping("/add_insurance_product")
    @ResponseBody
    public Long addInsuranceProduct(@RequestBody InsuranceProductInfo insuranceProduct){
    	
    	insuranceProductInfoService.addInsuranceProduct(insuranceProduct);
    	return insuranceProduct.getProductId();
    	
    }
    
    /**
     * 添加产品详情
     * */
    @RequestMapping("/add_insurance_product_detail")
    @ResponseBody
    public void addInsuranceProductDetail(@RequestBody InsuranceProductDetailWithBLOBs insuranceProductDetailWithBLOBs){
    	
    	insuranceProductInfoService.addInsuranceProductDetail(insuranceProductDetailWithBLOBs);
    
    }
    
    /**
     * 添加产品佣金系数
     * */
    @RequestMapping("/add_renew_period")
    @ResponseBody
    public void addRenewPeriod(@RequestBody ProductsCommissionRatio productsCommissionRatio){
    	
    	insuranceProductInfoService.addRenewPeriod(productsCommissionRatio);
    }
    
    /**
     * 查询产品信息
     */
    @RequestMapping("/select_insurance_product_info")
    @ResponseBody
    public InsuranceProductInfo selectInsuranceProductInfo(@RequestParam Map<String,Object> params){
    
    return insuranceProductInfoService.selectInsuranceProductInfo(params);
    }
    
    
    /**
     * 查询产品详情
     */
    @RequestMapping("/select_insurance_product_detail_withBLOBs")
    @ResponseBody
    public InsuranceProductDetailWithBLOBs selectInsuranceProductDetailWithBLOBs(@RequestParam Map<String,Object> params){
    
    return insuranceProductInfoService.selectInsuranceProductDetailWithBLOBs(params);
    }
    
    /**
     * 查询对应产品佣金系数类表
     */
    @RequestMapping("/select_products_commission_ratio_list")
    @ResponseBody
    public List<ProductsCommissionRatio> getProductsCommissionRatioList(@RequestParam Map<String,Object> params){
    
    return insuranceProductInfoService.getProductsCommissionRatioList(params);
    }
    
    /**
     * 修改产品
     * */
    @RequestMapping("/update_insurance_product")
    @ResponseBody
    public void updateInsuranceProduct(@RequestBody InsuranceProductInfo insuranceProduct){
    	insuranceProductInfoService.updateInsuranceProduct(insuranceProduct);
    }
    
    /**
     * 修改产品详情
     * */
    @RequestMapping("/update_insurance_product_detail_withBLOBs")
    @ResponseBody
    public void updateInsuranceProductDetailWithBLOBs(@RequestBody InsuranceProductDetailWithBLOBs productDetailWithBLOBs){
    	insuranceProductInfoService.updateInsuranceProductDetailWithBLOBs(productDetailWithBLOBs);
    }
    
    /**
     * 删除子产品佣金系数
     * */
    @RequestMapping("/delete_insurance_products_commission_ratio")
    @ResponseBody
    public void deleteInsuranceProductsCommissionRatio(@RequestParam Map<String,Object> params){
    	insuranceProductInfoService.deleteInsuranceProductsCommissionRatio(params);
    }
    
    /**
     * 更新子产品
     */
    @RequestMapping("/update_renew_period")
    @ResponseBody
    public void updateRenewPeriod(@RequestBody ProductsCommissionRatio productsCommissionRatio){
    	insuranceProductInfoService.updateRenewPeriod(productsCommissionRatio);
    }
    
    /**
     * 修改产品状态
     * */
    @RequestMapping("/update_insurance_product_status")
    @ResponseBody
    public void updateInsuranceProductStatus(@RequestParam Map<String,Object> params){
    	insuranceProductInfoService.updateInsuranceProductStatus(params);
    }
    
    /**
     * 删除修改子产品状态
     * */
    @RequestMapping("/update_renew_period_status")
    @ResponseBody
    public void updateRenewPeriodStatus(@RequestParam Map<String,Object> params){
    	insuranceProductInfoService.updateRenewPeriodStatus(params);
    } 
    
    
    /**
     * 查看详情
     * */
    @RequestMapping("/select_insurance_product_detail")
    @ResponseBody
    public InsuranceProductInfo selectInsuranceBasicProduct(@RequestParam Map<String,Object> params){
       return insuranceProductInfoService.selectInsuranceProductDetail(params);
    }
    
    
    /**
 	 * 删除子产品时只保留一条
 	 * @return
 	 */
    @RequestMapping(value = "/check_renew_period_size")
    @ResponseBody
    public int checkRenewPeriodSize(@RequestParam Map<String,Object> params) {
       
        return  insuranceProductInfoService.checkRenewPeriodSize(params);
    }
    
    /**
     * 根据保险公司id查询公司机构
     */
    @RequestMapping("/seleck_ins_company_org")
    @ResponseBody
    public InsuranceDept selectInsuranceCompanyOrg(@RequestParam Map<String,Object> params){
       return insuranceProductInfoService.selectInsuranceCompanyOrg(params);
    }
    
    /**
     * 不分页查询父产品
     * */
    @RequestMapping("/findInsurProducts")
    @ResponseBody
    public List<InsuranceProductInfo> findInsurProducts(@RequestBody Map<String,Object> params){
    	List<InsuranceProductInfo> InsuranceProducts=insuranceProductInfoService.findInsurProducts(params);
    	return InsuranceProducts;
    			
    }
    /**
     * 不分页查询子产品产品
     * */
    @ResponseBody
    @RequestMapping(value = "/findAllInsurProductSub",method = RequestMethod.POST)
    List<ProductsCommissionRatio> findAllInsurProductSub(@RequestParam Map<String,Object> paras){
        return insuranceProductInfoService.findAllInsurProductSub(paras);
    }
    /**
     *查询子产品详情
     */
    @RequestMapping(value = "/select_insurance_basic_product_son",method = RequestMethod.POST)
    @ResponseBody
    InsuranceProductInfo selectInsuranceBasicProductSon(@RequestParam Map<String, Object> paras){
        return insuranceProductInfoService.selectInsuranceBasicProductSon(paras);
    }
    /**
    * @Description: 查询同一保险公司下是否有相同产品名称产品
    * @Param: [insuranceCompanyId, productName]
    * @return: boolean
    * @Author: liuweichen
    * @Date: 2020/4/22 0022
    */
    @RequestMapping(value = "/findByInsuranceCompanyIdAndProductName",method = RequestMethod.POST)
    @ResponseBody
    public boolean findByInsuranceCompanyIdAndProductName(@RequestParam(value="insuranceCompanyId", required = false) Long insuranceCompanyId, @RequestParam("productName")String productName){
        return insuranceProductInfoService.findByInsuranceCompanyIdAndProductName(insuranceCompanyId,productName);
    }

    /**
    * @Description:  查询是否有相同产品代码
    * @Param: [productCode]
    * @return: boolean
    * @Author: liuweichen
    * @Date: 2020/4/22 0022
    */
    @RequestMapping(value ="/findByProductCode",method =RequestMethod.POST)
    @ResponseBody
    public boolean findByProductCode(@RequestParam(value = "productCode") String productCode){
        return  insuranceProductInfoService.findByProductCode(productCode);
    }

    @RequestMapping(value ="/findByPrimaryKey",method =RequestMethod.POST)
    @ResponseBody
    public InsuranceProductInfo findByPrimaryKey(@RequestParam(value = "productId") Long productId){
        return insuranceProductInfoService.findByPrimaryKey(productId);
    }

    @RequestMapping(value ="/findRatioProductKey",method =RequestMethod.POST)
    @ResponseBody
    public ProductsCommissionRatio findRatioProductKey(@RequestParam(value = "productsRatioId") Long productsRatioId) {
        return insuranceProductInfoService.findRatioProductKey(productsRatioId);
    }

    /**
    * @Description: 查询子产品在同一保险公司下是否有相同产品名称产品
    * @Param: [params]
    * @return: boolean
    * @Author: liuweichen
    * @Date: 2020/4/22 0022
    */
    @RequestMapping(value ="/findRatioByInsuranceCompanyIdAndProductsName",method = RequestMethod.POST)
    @ResponseBody
    public boolean findRatioByInsuranceCompanyIdAndProductsName(@RequestParam Map<String, Object> params){
        return insuranceProductInfoService.findRatioByInsuranceCompanyIdAndProductsName(params);
    }


    @RequestMapping(value = "/findRatioProductsCode",method = RequestMethod.POST)
    @ResponseBody
    public boolean findRatioProductsCode(@RequestParam(value = "productsCode")String productsCode){
        return insuranceProductInfoService.findRatioProductsCode(productsCode);
    }

}
