package com.hzcf.plantform.controller.parameter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.pojo.basic.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzcf.plantform.feign.parameter.PartnershipCommissionClient;
import com.hzcf.plantform.feign.product.ProductBasicInformationClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.PartnershipCommissionSet;
import com.hzcf.pojo.parameter.PartnershipCommissionSub;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.pojo.product.ProductsCommissionRatio;
import com.hzcf.util.StringUtil;


/**
 * 合伙组织机构佣金率信息
 * */
@Controller
@RequestMapping("/partnership_commission_manager")
public class PartnershipCommissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private PartnershipCommissionClient partnershipCommissionClient;
    @Autowired
    private ProductBasicInformationClient productBasicInformationClient;
    @Autowired
	EmployeeFeignClient employeeFeignClient;
    
    /**
     * 跳转到合伙组织机构佣金率列表
     * */
    @RequestMapping("to_partnership_list")
    public String toPartnershipCommission(){
    	return "partnershipCommission/PartnershipCommissionList";
    }
    
    /**
     * 分页查询
     * */
	@RequestMapping("partnership_commission_list")
	//  @RequiresPermissions("partnership:list")//权限管理;
	@ResponseBody
	public DataMsg selectPartnershipList(HttpServletRequest request,DataMsg dataMsg){
	    try{
	        Map<String,Object> paras = new HashMap<>();
	        String pageNo = request.getParameter("pageNo");
	        if (StringUtil.isNotBlank(pageNo)) {
	            paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
	        }
	        String pageSize = request.getParameter("pageSize");
	        if (StringUtil.isNotBlank(pageSize)) {
	            paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
	        }
	        
	        String insuranceCompanyName = request.getParameter("insuranceCompanyName") ;//公司名称
	        if (StringUtil.isNotBlank(insuranceCompanyName)) {
	            paras.put("insuranceCompanyName",request.getParameter("insuranceCompanyName"));
	        }
	        String enterprisEorganizationName = request.getParameter("enterprisEorganizationName") ;//组织机构名称
	        if (StringUtil.isNotBlank(enterprisEorganizationName)) {
	            paras.put("enterprisEorganizationName",request.getParameter("enterprisEorganizationName"));
	        }
	        String computationalTerm = request.getParameter("computationalTerm") ;//公司名称
	        if (StringUtil.isNotBlank(computationalTerm)) {
	            paras.put("computationalTerm", request.getParameter("computationalTerm"));
	        }
	        
	        String insuranceCompanyCode = request.getParameter("insuranceCompanyCode") ;//保险公司代码
	        if (StringUtil.isNotBlank(insuranceCompanyCode)) {
	            paras.put("insuranceCompanyCode", request.getParameter("insuranceCompanyCode"));
	        }
	        String companyInsuranceCode = request.getParameter("companyInsuranceCode") ;//保险公司险种代码
	        if (StringUtil.isNotBlank(companyInsuranceCode)) {
	            paras.put("companyInsuranceCode", request.getParameter("companyInsuranceCode"));
	        }
	        
	        String productNmae = request.getParameter("productNmae") ;//产品名称
	        if (StringUtil.isNotBlank(productNmae)) {
	            paras.put("productNmae", request.getParameter("productNmae"));
	        }
	        String productCode = request.getParameter("productCode") ;//产品名称
	        if (StringUtil.isNotBlank(productCode)) {
	            paras.put("productCode", request.getParameter("productCode"));
	        }
	        String minCreateTime = request.getParameter("minCreateTime") ;//产品状态
	        if (StringUtil.isNotBlank(minCreateTime)) {
	            paras.put("minCreateTime", request.getParameter("minCreateTime"));
	        }
	        String maxCreateTime = request.getParameter("maxCreateTime");//产品状态
	        if (StringUtil.isNotBlank(maxCreateTime)) {
	            paras.put("maxCreateTime",request.getParameter("maxCreateTime"));
	        }

			//数据权限相关查询条件
			Subject subject = SecurityUtils.getSubject();
			Employee employeeShiro = (Employee) subject.getPrincipal();
			employeeShiro = employeeFeignClient.getEmployeeById(employeeShiro.getEmployeeId());
			paras.put("isAdmin", employeeShiro.getEmployeeNo());
			paras.put("myDeptNo", employeeShiro.getDeptNo());
	        
	        PageModel pageModel =partnershipCommissionClient.getPartnershipCommissionSetList(paras);
	        List list = pageModel.getList();
	        dataMsg.setTotal(pageModel.getTotalRecords());
	        dataMsg.setRows(pageModel.getList());
	        dataMsg.setMessageCode("200");
	    }catch (Exception e){
	        dataMsg.setMessageCode("400");
	        e.printStackTrace();
	        logger.error("佣金管理[查询]异常异常");
	    }
	    return  dataMsg;
	 }
	 /**
     * 跳转添加页面
     *
     * */
	@RequestMapping("/to_partnership_add")
    public String toAddPath(){
		return "partnershipCommission/PartnershipCommissionAdd";
	}
	/**
     *增加
     *
     * */
	//@RequiresPermissions("partnership:add")//权限管理;
    @RequestMapping("add_partnership_commission")
    @ResponseBody
    public DataMsg addPartnership(PartnershipCommissionSet partnershipCommissionSet,DataMsg msg,HttpServletRequest request){
	    try{
	    	PartnershipCommissionSet pcs=new PartnershipCommissionSet();
	    	PartnershipCommissionSub parsub=new PartnershipCommissionSub();
	        pcs.setCreatedTime(new Date());
	    	pcs.setSalesOrgId(request.getParameter("salesOrgId"));
	    	pcs.setEnterprisEorganizationName(request.getParameter("salesOrgName"));
	    	pcs.setCountingRules(request.getParameter("countingRules"));
	    	pcs.setInsProductId(Long.parseLong(request.getParameter("insProductId")));
	    	pcs.setInsuranceCompanyName(request.getParameter("insuranceCompanyNameValue"));
	    	pcs.setInsuranceCompanyId(Long.parseLong( request.getParameter("insuranceCompanyId")));
	    	pcs.setComputationalTerm(partnershipCommissionSet.getComputationalTerm());
	    	pcs.setProductName(request.getParameter("productNameValue"));
	    	pcs.setProductCode(request.getParameter("productCode"));
	    	
	    	//新增返回主键
	    	Long partnershipId= partnershipCommissionClient.addPartnershipCommission(pcs);
	    	
	    	String json=request.getParameter("stringJson");
	    	JSONArray dataArray=JSONArray.parseArray(json);
	    	for(int i =0 ;i<dataArray.size(); i++){
	    		JSONObject jsonData=dataArray.getJSONObject(i);
	    		parsub.setPartnershipId(partnershipId);
	    		parsub.setProductsCode(jsonData.getString("PRODUCTS_CODE"));
	    		parsub.setProductsName(jsonData.getString("PRODUCTS_NAME"));
	    		parsub.setRenewPeriodMax(jsonData.getString("RENEW_PERIOD_MAX"));
	    		parsub.setRenewPeriodMin(jsonData.getString("RENEW_PERIOD_MIN"));
	    		parsub.setInsurancePeriodMin(jsonData.getString("INSURANCE_PERIOD_MIN"));
	    		parsub.setInsurancePeriodMax(jsonData.getString("INSURANCE_PERIOD_MAX"));
	    		
	    		parsub.setFirstAnnualRate(jsonData.getString("FIRST_ANNUAL_RATE"));
	    		parsub.setSecondAnnualRate(jsonData.getString("SECOND_ANNUAL_RATE"));
	    		parsub.setThirdAnnualRate(jsonData.getString("THIRD_ANNUAL_RATE"));
	    		parsub.setFourthAnnualRate(jsonData.getString("FOURTH_ANNUAL_RATE"));
	    		parsub.setFifthAnnualRate(jsonData.getString("FIFTH_ANNUAL_RATE"));
	    		parsub.setSixthAnnualRate(jsonData.getString("SIXTH_ANNUAL_RATE"));
	    		partnershipCommissionClient.addPartnershipCommissionSub(parsub);
	    	}
	    	logger.info(partnershipId.toString());
	        msg.setMessageCode("200");
	        logger.info("佣金管理[新增]成功！");
        
	    }catch (Exception e){
	        msg.setMessageCode("400");
	        e.printStackTrace();
	        logger.info("佣金管理[新增]异常！");
	    }
	    return msg;
	}
    /**
     * 跳转到修改
     * */
    @RequestMapping("toPartnershipEditPage")
    public String toPartnershipEditPage(Model model,HttpServletRequest request,DataMsg msg){
	    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  //查询详情带到前端页面
	    try{
	    	String id = request.getParameter("id");
			Map<String,Object> paras = new HashMap<>();
			paras.put("partnershipId",id);
			//查询主表信息
			PartnershipCommissionSet partnershipDetail = partnershipCommissionClient.selectPartnershipCommissionSet(paras);
			//查询子产品list
			List<PartnershipCommissionSub> partnershipSub = partnershipCommissionClient.getPartnershipCommissionSubList(paras);
			
			paras.clear();
			paras.put("productId",partnershipDetail.getInsProductId());
			List<ProductsCommissionRatio> pcrs = productBasicInformationClient.getProductsCommissionRatioList(paras);
			if(pcrs!=null && pcrs.size()>0){
				for(int i=0;i<pcrs.size();i++){
					Boolean flag = false;
					for(int j =0;j<partnershipSub.size();j++){
						if(pcrs.get(i).getProductsCode().equals(partnershipSub.get(j).getProductsCode()) && pcrs.get(i).getProductsName().equals(partnershipSub.get(j).getProductsName())){
							flag = true;
							break;
						}
					}
					if(!flag){
						PartnershipCommissionSub parsub=new PartnershipCommissionSub();
						parsub.setPartnershipId(partnershipDetail.getPartnershipId());
			    		parsub.setProductsCode(pcrs.get(i).getProductsCode());
			    		parsub.setProductsName(pcrs.get(i).getProductsName());
			    		parsub.setRenewPeriodMax(pcrs.get(i).getRenewPeriodMax());
			    		parsub.setRenewPeriodMin(pcrs.get(i).getRenewPeriodMin());
			    		parsub.setInsurancePeriodMin(pcrs.get(i).getInsurancePeriodMin());
			    		parsub.setInsurancePeriodMax(pcrs.get(i).getInsurancePeriodMax());
			    		parsub.setFirstAnnualRate("0");
			    		parsub.setSecondAnnualRate("0");
			    		parsub.setThirdAnnualRate("0");
			    		parsub.setFourthAnnualRate("0");
			    		parsub.setFifthAnnualRate("0");
			    		parsub.setSixthAnnualRate("0");
			    		partnershipCommissionClient.addPartnershipCommissionSub(parsub);
					}
				}
			}
			
			paras.clear();
			paras.put("partnershipId",id);
			partnershipSub = partnershipCommissionClient.getPartnershipCommissionSubList(paras);
			
			model.addAttribute("partnershipDetail", partnershipDetail);
			model.addAttribute("partnershipSub", partnershipSub);
			 
	    }catch (Exception e){
	        msg.setMessageCode("400");
	        e.printStackTrace();
	        logger.info("查询保险产品[]异常！");
	    }
    	return "partnershipCommission/PartnershipCommissionEdit";
    }  
	    
    /**
     * 跳转到详情
     * */
    @RequestMapping("toPartnershipDetailPage")
    public String toPartnershipDetailPage(Model model,HttpServletRequest request){
       //查询详情带到前端页面
    	String id = request.getParameter("id");
		Map<String,Object> paras = new HashMap<>();
		paras.put("partnershipId",id);
		//查询主表信息
		PartnershipCommissionSet partnershipDetail=	partnershipCommissionClient.selectPartnershipCommissionSet(paras);
		//查询子产品list
		List<PartnershipCommissionSub> partnershipSub=partnershipCommissionClient.getPartnershipCommissionSubList(paras);
		model.addAttribute("partnershipDetail", partnershipDetail);
		model.addAttribute("partnershipSub", partnershipSub);
    	return "partnershipCommission/PartnershipCommissionDetail";
    }  
    /**
     *修改修改产品
     *
     * */
    //    @RequiresPermissions("partnership:update")//权限管理;
    @RequestMapping("update_partnership_commission")
    @ResponseBody
    public DataMsg updateInsuranceProduct(PartnershipCommissionSet partnershipCommissionSet,DataMsg msg,HttpServletRequest request){
    	try{
    		SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
	    	PartnershipCommissionSub parsubUpdate=new PartnershipCommissionSub();
	    	Long partnershipId=	partnershipCommissionSet.getPartnershipId();
	    	//partnershipCommissionClient.updatePartnershipCommission(pcsUpdate);
	    	//新增返回主键
	    	String json=request.getParameter("stringJson");
	    	JSONArray dataArray=JSONArray.parseArray(json);
	    	for(int i =0 ;i<dataArray.size(); i++){
	    		JSONObject jsonData = dataArray.getJSONObject(i);
		    	parsubUpdate.setPartnershipId(partnershipId);
		    	parsubUpdate.setPartnershipSubId(Long.valueOf(jsonData.getString("PRODUCTS_RATIO_ID")));
		    	parsubUpdate.setProductsCode(jsonData.getString("PRODUCTS_CODE"));
		    	parsubUpdate.setProductsName(jsonData.getString("PRODUCTS_NAME"));
		    	parsubUpdate.setRenewPeriodMax(jsonData.getString("RENEW_PERIOD_MAX"));
		    	parsubUpdate.setRenewPeriodMin(jsonData.getString("RENEW_PERIOD_MIN"));
		    	parsubUpdate.setInsurancePeriodMin(jsonData.getString("INSURANCE_PERIOD_MIN"));
		    	parsubUpdate.setInsurancePeriodMax(jsonData.getString("INSURANCE_PERIOD_MAX"));
		    	parsubUpdate.setFirstAnnualRate(jsonData.getString("FIRST_ANNUAL_RATE"));
		    	parsubUpdate.setSecondAnnualRate(jsonData.getString("SECOND_ANNUAL_RATE"));
		    	parsubUpdate.setThirdAnnualRate(jsonData.getString("THIRD_ANNUAL_RATE"));
		    	parsubUpdate.setFourthAnnualRate(jsonData.getString("FOURTH_ANNUAL_RATE"));
		    	parsubUpdate.setFifthAnnualRate(jsonData.getString("FIFTH_ANNUAL_RATE"));
		    	parsubUpdate.setSixthAnnualRate(jsonData.getString("SIXTH_ANNUAL_RATE"));
	    		partnershipCommissionClient.updatePartnershipCommissionSub(parsubUpdate);
	    	}
    		
            msg.setMessageCode("200");
            logger.info("佣金管理[修改]成功");
        }catch (Exception e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("佣金管理[修改]异常");
        }
        return msg;
    }
    
    //编辑查询子产品
    @RequestMapping("partnership_commission_list_sub_edit")
	//  @RequiresPermissions("partnership:list")//权限管理;
	@ResponseBody
	public DataMsg selectPartnershipListSubEdit(HttpServletRequest request,DataMsg dataMsg){
	    try{
	        Map<String,Object> paras = new HashMap<>();
	        String paramet = request.getParameter("paramet");
	        if (StringUtil.isNotBlank(paramet)) {
	        	  paras.put("paramet", request.getParameter("paramet"));
	        }
	        String pageNo = request.getParameter("pageNo");
	        if (StringUtil.isNotBlank(pageNo)) {
	            paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
	        }
	        String pageSize = request.getParameter("pageSize");
	        if (StringUtil.isNotBlank(pageSize)) {
	            paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
	        }
	      
	        String productId = request.getParameter("productId") ;//产品名称
	        if (StringUtil.isNotBlank(productId)) {
	            paras.put("productId", Integer.valueOf(request.getParameter("productId")));
	        }
	        
	        String partnershipId = request.getParameter("partnershipId") ;
	        if(StringUtil.isNotBlank(partnershipId)){
	        	paras.put("partnershipId", Integer.valueOf(request.getParameter("partnershipId")));
	        }
	        
	        PageModel pageModel =partnershipCommissionClient.getPartnershipListSubEdit(paras);
	        List list = pageModel.getList();
	        dataMsg.setTotal(pageModel.getTotalRecords());
	        dataMsg.setRows(pageModel.getList());
	        dataMsg.setMessageCode("200");
	    }catch (Exception e){
	        dataMsg.setMessageCode("400");
	        e.printStackTrace();
	        logger.error("佣金管理[查询]异常异常");
	    }
	    return  dataMsg;
	} 
    /**
     * 分页查询子产品列表 
     * */
	@RequestMapping("partnership_commission_list_sub")
	//  @RequiresPermissions("partnership:list")//权限管理;
	@ResponseBody
	public DataMsg selectPartnershipListSub(HttpServletRequest request,DataMsg dataMsg){
	    try{
	        Map<String,Object> paras = new HashMap<>();
	        String paramet = request.getParameter("paramet");
	        if (StringUtil.isNotBlank(paramet)) {
	        	  paras.put("paramet", request.getParameter("paramet"));
	        }
	        String pageNo = request.getParameter("pageNo");
	        if (StringUtil.isNotBlank(pageNo)) {
	            paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
	        }
	        String pageSize = request.getParameter("pageSize");
	        if (StringUtil.isNotBlank(pageSize)) {
	            paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
	        }
	      
	        String productId = request.getParameter("productId") ;//产品名称
	        if (StringUtil.isNotBlank(productId)) {
	            paras.put("productId", Integer.valueOf(request.getParameter("productId")));
	            PageModel pageModel =partnershipCommissionClient.getPartnershipListSub(paras);
		        List list = pageModel.getList();
		        dataMsg.setTotal(pageModel.getTotalRecords());
		        dataMsg.setRows(pageModel.getList());
		        dataMsg.setMessageCode("200");
	        }else{
	        	dataMsg.setMessageCode("500");
	        }
	        
	    }catch (Exception e){
	        dataMsg.setMessageCode("400");
	        e.printStackTrace();
	        logger.error("佣金管理[查询]异常异常");
	    }
	    return  dataMsg;
	} 
    
    /**
    *
    *查询父产品List，不分页  根据机构和保险公司查询，排除已经在组织机构佣金率里的
    * */
	@RequestMapping("/findInsurProducts")
	@ResponseBody
	public DataMsg findInsurProducts(HttpServletRequest request,DataMsg dataMsg){
	   try {
	       Map<String,Object> paras = new HashMap<>();
	       String companyId = request.getParameter("companyId");
	       String salesOrgId = request.getParameter("salesOrgId");
           String computationalTerm  = request.getParameter("computationalTerm");
           paras.put("companyId", companyId);
	       paras.put("salesOrgId", salesOrgId);
	       paras.put("computationalTerm",computationalTerm);
	       List<PartnershipCommissionSet> pcss = partnershipCommissionClient.findPartnershipCommissionSet(paras);
	       String outProIds = "";
	       if(pcss!=null && pcss.size()>0){
	    	   for(PartnershipCommissionSet pcs:pcss){
	    		   outProIds = outProIds + pcs.getInsProductId()+",";
	    	   }
	       }
	       if (outProIds.endsWith(",")) {
	    	   outProIds = outProIds.substring(0, outProIds.length() - 1);
	       }
	       if(StringUtil.isNotBlank(outProIds)){
	       	   paras.put("outProIds", outProIds);
	   	   }
	       List<InsuranceProductInfo> list = productBasicInformationClient.findInsurProducts(paras);
	       dataMsg.setRows(list);
	       dataMsg.setMessageCode("200");
		} catch (Exception e) {
	       dataMsg.setMessageCode("400");
	       e.printStackTrace();
	       logger.error("产品[查询]异常异常");
		}
		return dataMsg;
	}
}
