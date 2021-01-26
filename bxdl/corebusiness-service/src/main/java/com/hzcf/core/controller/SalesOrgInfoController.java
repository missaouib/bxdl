package com.hzcf.core.controller;


import com.hzcf.core.service.SalesOrgInfoService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesOrgDetail;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 保险部门管理
 * @author sxm
 *
 */
@Controller
@RequestMapping("/salesOrgInfo")
public class SalesOrgInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SalesOrgInfoService salesOrgInfoService;
    
    /**
     * 添加部门
     * */
    @RequestMapping("/addSalesOrgInfo")
    @ResponseBody
    public int addSalesOrgInfo(@RequestBody SalesOrgInfo salesOrgInfo){
    	salesOrgInfoService.addSalesOrgInfo(salesOrgInfo);
    	return salesOrgInfo.getSalesOrgId().intValue();
    }
    
    /**
     * 添加部门
     * */
    @RequestMapping("/addSalesOrgDetail")
    @ResponseBody
    public void addSalesOrgDetail(@RequestBody SalesOrgDetail salesOrgDetail){
    	salesOrgInfoService.addSalesOrgDetail(salesOrgDetail);   	
    }  
    
    /**
     * 修改基础信息
     * */
    @RequestMapping("/updateSalesOrgInfo")
    @ResponseBody
    public void updateSalesOrgInfo(@RequestBody SalesOrgInfo salesOrgInfo,@RequestParam(value = "isNoticy") String isNoticy){
    	salesOrgInfoService.updateSalesOrgInfo(salesOrgInfo,isNoticy);
    }
    
    /**
     * 修改详细信息
     * */
    @RequestMapping("/updateSalesOrgDetail")
    @ResponseBody
    public void updateSalesOrgDetail(@RequestBody SalesOrgDetail salesOrgDetail){
    	salesOrgInfoService.updateSalesOrgDetail(salesOrgDetail);   	
    } 
    
    /**
     * 分页查询保险部门
     * */
    @RequestMapping("/getSalesOrgInfoList")
    @ResponseBody
    public PageModel getSalesOrgInfoList(@RequestParam Map<String,Object> params){
    	return salesOrgInfoService.getSalesOrgInfoList(params);
    }
    
    /**
     * 查找前置编码
     * */
    @RequestMapping("/findMaxTreeCode")
    @ResponseBody
    public int findMaxTreeCode(@RequestParam Map<String,Object> params){
    	return salesOrgInfoService.findMaxTreeCode(params);
    } 
    
    /**
     * 不分页查询保险部门
     * */
    @RequestMapping("/selectById")
    @ResponseBody
    public SalesOrgInfo selectById(@RequestParam Map<String,Object> params){
    	return salesOrgInfoService.selectById(params);
    }    
    
    /**
     * 不分页查询保险部门
     * */
    @RequestMapping("/findSalesOrgs")
    @ResponseBody
    public List<SalesOrgInfo> findSalesOrgs(@RequestParam Map<String,Object> params){
    	return salesOrgInfoService.findSalesOrgs(params);
    }
    /**
    * @Description: 查询是否有相同组织机构名称
    * @Param: [params]
    * @return: java.util.List<com.hzcf.pojo.insurance.SalesOrgInfo>
    * @Author: liuweichen
    * @Date: 2020/9/15 0015
    */
    @RequestMapping("/findSalesOrgsByNames")
    @ResponseBody
    public List<SalesOrgInfo> findSalesOrgsByNames(@RequestParam Map<String,Object> params) {
        return salesOrgInfoService.findSalesOrgsByNames(params);
    }
    
	 /**
    *
    *根据条件查询父辈组织机构
    * */
    @RequestMapping("/getParents")
    @ResponseBody
    public List<SalesOrgInfo> getParents(@RequestParam Map<String,Object> params){
    	return salesOrgInfoService.getParents(params);
    }
    /**
     * 查看员工部门 a->b->c ...
     * */
    @RequestMapping(value = "/selectDeptNamesByOrgId",method = RequestMethod.POST)
    @ResponseBody
    String selectDeptNamesByOrgId(@RequestParam Map<String, Object> params){
       return salesOrgInfoService.selectDeptNamesByOrgId(params);
    }

    /**
     * <p>根据id查询机构及下属机构</p>
     * @param salesOrgId
     * @return
     */
    @RequestMapping("/findSubordinateOrg")
    @ResponseBody
    public List<SalesOrgInfo> findSubordinateOrgList(@RequestParam("salesOrgId") Long salesOrgId){
        return salesOrgInfoService.findSubordinateOrgListById(salesOrgId);
    }

    /**
     * 获取内勤人员所属and下级机构id
     * @param deptNo
     * @return
     */
    @RequestMapping("/findEmployeeAllOrgs")
    @ResponseBody
    public String findEmployeeAllOrgs(@RequestParam("deptNo") String deptNo){
        return salesOrgInfoService.findEmployeeAllOrgs(deptNo);
    }

    /**
     * 通过saleOrgIds获取销售机构信息
     * @param saleOrgIds
     * @return
     */
    @RequestMapping("/getSaleOrgInfoListBySaleOrgIds")
    @ResponseBody
    public List<Map<String, Object>> getSaleOrgInfoListBySaleOrgIds(@RequestParam(value = "saleOrgIds", required = false) String saleOrgIds){
        if(StringUtil.isBlanks(saleOrgIds)){
            return null;
        }
        return salesOrgInfoService.getSaleOrgInfoListBySaleOrgIds(saleOrgIds);
    }

    /**
     * 查询内勤人员所属机构（从总公司一直到分公司例子： 总公司->省级分公司->...）
     * @param list
     * @return
     */
    @RequestMapping("/getEmpAllOrgNameInfo")
    @ResponseBody
    public List<Map<String, Object>> getEmpAllOrgNameInfo(@RequestBody List<Map<String, Object>> list){
        salesOrgInfoService.getEmpAllOrgNameInfo(list);
        return list;
    }

    @RequestMapping(value = "/getTopOrgNum")
    @ResponseBody
    public int getTopOrgNum(@RequestParam Map<String, Object> params){
        return salesOrgInfoService.getTopOrgNum(params);
    }

    /**
     * 获取总/省份公司机构信息集合
     * @return
     */
    @RequestMapping(value = "/getTopAndSFOrgsList")
    @ResponseBody
    public List<Map<String, Object>> getTopAndSFOrgsList(){
        return salesOrgInfoService.getTopAndSFOrgsList();
    }


    /**
     *异步获取组织机构信息，以及相关的逻辑判断，用来返回前台信息
     * */
    @RequestMapping("/queryOrgInfo")
    @ResponseBody
    public List<SalesOrgInfo> queryOrgInfo(@RequestParam Map<String,Object> params){
        return salesOrgInfoService.queryOrgInfo(params);
    }

    /**
     * 修改是否默认基本法
     * */
    @RequestMapping("/updateIsDefaultCal")
    @ResponseBody
    public void updateIsDefaultCal(@RequestBody SalesOrgInfo salesOrgInfo){
        salesOrgInfoService.updateIsDefaultCal(salesOrgInfo);
    }


    /**
     * 通过treeCode查询销售机构信息
     * @param treeCode
     * @return
     */
    @RequestMapping("/getSalesOrgInfoByTreeCode")
    @ResponseBody
    public SalesOrgInfo getSalesOrgInfoByTreeCode(@RequestParam String treeCode){
        return salesOrgInfoService.getSalesOrgInfoByTreeCode(treeCode);
    }

    /**
     * 通过条件查询基本机构id
     * @param params
     * @return
     */
    @RequestMapping("/getCalOrgsByCondition")
    @ResponseBody
    public Map<String, String> getCalOrgsByCondition(@RequestParam Map<String,Object> params){
        return salesOrgInfoService.getCalOrgsByCondition(params);
    }

    @RequestMapping("/getCalOrgsBySalesOrgInfo")
    @ResponseBody
    public Map<String, String> getCalOrgsBySalesOrgInfo(@RequestBody SalesOrgInfo salesOrgInfo){
        return salesOrgInfoService.getCalOrgsBySalesOrgInfo(salesOrgInfo);
    }

}
