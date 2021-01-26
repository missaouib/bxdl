package com.hzcf.plantform.controller.product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.product.ProductBasicInformationClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.product.InsuranceProductDetailWithBLOBs;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.pojo.product.ProductsCommissionRatio;
import com.hzcf.util.StringUtil;


/**
 * 保险产品基本信息
 * */
@Controller
@RequestMapping("/product_basic_information")
public class ProductBasicInformationController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ProductBasicInformationClient productBasicInformationClient;
    /**
     * 跳转到产品列表
     * */
    @RequestMapping("to_product_page")
    public String toProductBasicInformation(){
    	
    	return "insuranceProductPage/InsuranceProductList";}
    /**
     * 分页查询
     * */
	@RequestMapping("select_product_list")
	//  @RequiresPermissions("insProduct:list")//权限管理;
	@ResponseBody
	public DataMsg selectProductList(HttpServletRequest request,DataMsg dataMsg){
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
	        
	        String insuranceCompanyName = request.getParameter("insuranceCompanyName") ;//组织机构
	        if (StringUtil.isNotBlank(insuranceCompanyName)) {
	            paras.put("insuranceCompanyName",insuranceCompanyName);
	        }
	        
	        String insuranceCompanyCode = request.getParameter("insuranceCompanyCode") ;//组织机构代码
	        if (StringUtil.isNotBlank(insuranceCompanyCode)) {
	            paras.put("companyInsuranceCode",insuranceCompanyCode);
	        }
	        String topCompanyCode = request.getParameter("topCompanyCode") ;//保险公司
	        if (StringUtil.isNotBlank(topCompanyCode)) {
	            paras.put("topCompanyCode",topCompanyCode);
	        }
	        String topCompanyName = request.getParameter("topCompanyName") ;//保险公司
	        if (StringUtil.isNotBlank(topCompanyName)) {
	            paras.put("topCompanyName",topCompanyName);
	        }	        
	        String productNmae = request.getParameter("productNmae") ;//产品名称
	        if (StringUtil.isNotBlank(productNmae)) {
	            paras.put("productNmae",productNmae);
	        }
	        String insuranceType = request.getParameter("insuranceType") ;//险种类别
	        if (StringUtil.isNotBlank(insuranceType)) {
	            paras.put("insuranceType",insuranceType);
	        }
	        String productStatus = request.getParameter("productStatus") ;//产品状态
	        if (StringUtil.isNotBlank(productStatus)) {
	            paras.put("productStatus",productStatus);
	        }
	        String saleType = request.getParameter("saleType") ;//产品状态
	        if (StringUtil.isNotBlank(saleType)) {
	            paras.put("saleType",saleType);
	        }
	        String minCreateTime = request.getParameter("minCreateTime") ;//产品状态
	        if (StringUtil.isNotBlank(minCreateTime)) {
	            paras.put("minCreateTime",minCreateTime);
	        }
	        String maxCreateTime = request.getParameter("maxCreateTime");//产品状态
	        if (StringUtil.isNotBlank(maxCreateTime)) {
	            paras.put("maxCreateTime",maxCreateTime);
	        }
	        
	        PageModel pageModel =productBasicInformationClient.getProductList(paras);
	        List list = pageModel.getList();
	        dataMsg.setTotal(pageModel.getTotalRecords());
	        dataMsg.setRows(pageModel.getList());
	        dataMsg.setMessageCode("200");
	    }catch (Exception e){
	        dataMsg.setMessageCode("400");
	        e.printStackTrace();
	        logger.error("保险产品[查询]异常异常",e);
	    }
	    return  dataMsg;
	}
	
	 /**
     * 跳转添加页面
     *
     * */
	@RequestMapping("/toAddPath")
    public String toAddPath(){return "insuranceProductPage/InsuranceProductAdd";}
	
	/**
     *增加产品
     *
     * */
//    @RequiresPermissions("insProduct:add")//权限管理;
	    @RequestMapping("add_insurance_product")
	    @ResponseBody
	    public DataMsg addInsuranceProduct(InsuranceProductInfo insuranceProductInfo,
								    		InsuranceProductDetailWithBLOBs insuranceProductDetailWithBLOBs,
								    		DataMsg msg,
								    		HttpServletRequest request,
								    		String[] insurancePeriodMin,
								    		String[] insurancePeriodMax,
								    		String[] renewPeriodMin,
								    		String[] renewPeriodMax,
								    		String[] valueCommissionCoefficient,
								    		String[] inStandardCommissionCoefficient,
								    		String[] outStandardCommissionCoefficient,
								    		String[] indexingCoefficient,
								    		String[] productsName,
								    		String[] productsCode
								    		){
	    	
	    try{
	    	Map<String,Object> params = new HashMap<String,Object>();
	    	SimpleDateFormat sdfs = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
	    	Date invalidDate = sdfs.parse(insuranceProductInfo.getInvalidDate().toString());
	    	Date takeEffectDate = sdfs.parse(insuranceProductInfo.getTakeEffectDate().toString());
	    	
	    	insuranceProductInfo.setInvalidDate(invalidDate);
			insuranceProductInfo.setTakeEffectDate(takeEffectDate);
//			insuranceProductInfo.setCreatedTime(new Date());
			boolean result = productBasicInformationClient.findByInsuranceCompanyIdAndProductName(insuranceProductInfo.getInsuranceCompanyId(),insuranceProductInfo.getProductName());
			if (result) {
                msg.setMessageCode("200001");
                msg.setData("当前保险公司已有此产品名称");
                return msg;
            }
			boolean codeResult = productBasicInformationClient.findByProductCode(insuranceProductInfo.getProductCode());
			if (codeResult) {
				msg.setMessageCode("200002");
				 msg.setData("产品编码已存在");
                return msg;
			}
			int x=insurancePeriodMin.length;
			Long insuranceCompanyId = insuranceProductInfo.getInsuranceCompanyId();
			List<String> oldRatioNames = new ArrayList<String>();
			List<String> oldproductsCodes =new ArrayList<String>();
			for(int i=0;i<x;i++){
				 String ratioproductsName = productsName[i];
				 params.put("insuranceCompanyId",insuranceCompanyId);
				 params.put("productsName",ratioproductsName);
				 boolean ratioResult = productBasicInformationClient.findRatioByInsuranceCompanyIdAndProductsName(params);
				 if (ratioResult) {
					msg.setMessageCode("200003");
					msg.setData("当前保险公司已有此子产品名称");
					return msg;
           	 	}
				 boolean ratioCodeResult = productBasicInformationClient.findRatioProductsCode(productsCode[i]);
				 if (ratioCodeResult) {
					msg.setMessageCode("200004");
					msg.setData("子产品编码已存在");
					return msg;
				}
				if (oldRatioNames.contains(ratioproductsName)||oldproductsCodes.contains(productsCode[i])) {
					msg.setMessageCode("200005");
					msg.setData("子产品名称或者子产品编码已存在");
					return msg;
				}
				oldRatioNames.add(ratioproductsName);
				oldproductsCodes.add(productsCode[i]);
			}
	    	//返回产品主键ID
	    	Long productId=productBasicInformationClient.addInsuranceProduct(insuranceProductInfo);
	    	System.out.println(productId);
	    	
	    	//保存产品详情
	    	insuranceProductDetailWithBLOBs.setInsuranceProductId(productId);
//	    	insuranceProductDetailWithBLOBs.setCreatedTime(formatDateTime.parse((formatDateTime.format(new Date()))));
	    	productBasicInformationClient.addInsuranceProductDetail(insuranceProductDetailWithBLOBs);
	    	
	    	//保存产品期间和续费期间
			 ProductsCommissionRatio productsCommissionRatio = new ProductsCommissionRatio();
	    	 for(int i=0;i<x;i++)       
	         {
	         	 productsCommissionRatio.setInsuranceProductId(productId);
	    		 productsCommissionRatio.setRenewPeriodMin(renewPeriodMin[i]);
	    		 productsCommissionRatio.setRenewPeriodMax(renewPeriodMax[i]);
	    		 productsCommissionRatio.setInsurancePeriodMin(insurancePeriodMin[i]);
	    		 productsCommissionRatio.setInsurancePeriodMax(insurancePeriodMax[i]);
//	    		 productsCommissionRatio.setCreatedTime(formatDateTime.parse((formatDateTime.format(new Date()))));
//		    	 productsCommissionRatio.setUpdatedTime(formatDateTime.parse((formatDateTime.format(new Date()))));
	    		 //productsCommissionRatio.setValueCommissionCoefficient( new BigDecimal(valueCommissionCoefficient[i]));
	    		// productsCommissionRatio.setInStandardCommissionCoefficient(new BigDecimal(inStandardCommissionCoefficient[i]));
	    		// productsCommissionRatio.setOutStandardCommissionCoefficient(new BigDecimal(outStandardCommissionCoefficient[i]));
	    		// productsCommissionRatio.setIndexingCoefficient(new BigDecimal(indexingCoefficient[i]));
	    		 productsCommissionRatio.setProductsName(productsName[i]);
	    		 productsCommissionRatio.setProductsCode(productsCode[i]);
	    		 
	        	 productBasicInformationClient.addRenewPeriod(productsCommissionRatio);
	         }
	    	 
	        msg.setMessageCode("200");
	        logger.info("保险产品[新增]成功！");
        
	    }catch (Exception e){
	        msg.setMessageCode("400");
	        e.printStackTrace();
	        msg.setData(e.getMessage());
	        logger.error(e.getMessage(),e);
	        logger.info("保险产品[新增]异常！");
	    }
	return msg;
	}
	    
	    /**
	     * 跳转到修改
	     * */
	    @RequestMapping("toProductEditPage")
	    public String toProductEditPage(Model model,HttpServletRequest request){
	    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       //查询详情带到前端页面
	    	String id = request.getParameter("id");
			Map<String,Object> paras = new HashMap<>();
			paras.put("productId",id);
			
			//查询产品信息
	        InsuranceProductInfo insuranceProductInfo =productBasicInformationClient.selectInsuranceProductInfo(paras);
	        //根据保险公司id查询公司信息
	        paras.put("id",insuranceProductInfo.getInsuranceCompanyId());
	        InsuranceDept insuranceDept=productBasicInformationClient.selectInsuranceCompanyOrg(paras);
            String orgLevel = insuranceDept.getOrgLevel();
            switch (orgLevel){
                case "01":
                    orgLevel = "总公司";
                    break;
                case "02":
                    orgLevel = "省份公司";
                    break;
                case "03":
                    orgLevel = "地市公司";
                    break;
            }
            insuranceDept.setOrgLevel(orgLevel);
	        //查询详情
	        String invalidDates = dataFormat.format(insuranceProductInfo.getInvalidDate());
	        String takeEffectDates =  dataFormat.format(insuranceProductInfo.getTakeEffectDate());
	        
	        InsuranceProductDetailWithBLOBs insuranceProductDetail =productBasicInformationClient.selectInsuranceProductDetailWithBLOBs(paras);
	        //在查询子项目列表
	        List<ProductsCommissionRatio> productsCommissionRatio;
	        productsCommissionRatio= productBasicInformationClient.getProductsCommissionRatioList(paras);
	        Map<String,Object> paraData = new HashMap<>();
	        paraData.put("invalidDates", invalidDates);
	        paraData.put("takeEffectDates", takeEffectDates);
	        model.addAttribute("paraData",paraData);
			model.addAttribute("insuranceProductInfo",insuranceProductInfo);	
			model.addAttribute("insuranceProductDetail",insuranceProductDetail);	
			model.addAttribute("productsCommissionRatioList",productsCommissionRatio);	
			model.addAttribute("insuranceDept",insuranceDept);	
    	return "insuranceProductPage/InsuranceProductEdit";}  
	    
	    
	    /**
	     * 跳转到详情
	     * */
	    @RequestMapping("toProductDetailPage")
	    public String toProductDetailPage(Model model,HttpServletRequest request){
	    	
	       //查询详情带到前端页面
	    	String id = request.getParameter("id");
			Map<String,Object> paras = new HashMap<>();
			paras.put("productId",id);
			//查询产品信息
	        InsuranceProductInfo insuranceProductInfo =productBasicInformationClient.selectInsuranceProductInfo(paras);
	        
	        //根据保险公司id查询公司信息
	        paras.put("id",insuranceProductInfo.getInsuranceCompanyId());
	        InsuranceDept insuranceDept=productBasicInformationClient.selectInsuranceCompanyOrg(paras);
	         String orgLevel = insuranceDept.getOrgLevel();
            switch (orgLevel){
                case "01":
                    orgLevel = "总公司";
                    break;
                case "02":
                    orgLevel = "省份公司";
                    break;
                case "03":
                    orgLevel = "地市公司";
                    break;
            }
            insuranceDept.setOrgLevel(orgLevel);
	       /* insuranceProductInfo.setInvalidDate(DateUtil.getTimeNormalString(insuranceProductInfo.getInvalidDate()));
	        
	        insuranceProductInfo.setTakeEffectDate(DateUtil.getTimeNormalString(insuranceProductInfo.getTakeEffectDate()));*/
	        
	        //查询详情
	        InsuranceProductDetailWithBLOBs insuranceProductDetail =productBasicInformationClient.selectInsuranceProductDetailWithBLOBs(paras);
	        //在查询子项目列表
	        List<ProductsCommissionRatio> productsCommissionRatio;
	        productsCommissionRatio= productBasicInformationClient.getProductsCommissionRatioList(paras);
	        model.addAttribute("insuranceDept",insuranceDept);	
			model.addAttribute("insuranceProductInfo",insuranceProductInfo);	
			model.addAttribute("insuranceProductDetail",insuranceProductDetail);	
			model.addAttribute("productsCommissionRatioList",productsCommissionRatio);	
    	return "insuranceProductPage/InsuranceProductDetail";}  
    /**
     *修改修改产品
     *
     * */
//    @RequiresPermissions("insProduct:update")//权限管理;
    @RequestMapping("update_insurance_product")
    @ResponseBody
    public DataMsg updateInsuranceProduct(InsuranceProductInfo insuranceProductInfo,
    									 InsuranceProductDetailWithBLOBs detailWithBLOBs,
    									 DataMsg msg,
    									 HttpServletRequest request,
							    		 String[] insurancePeriodMin,
							    		 String[] insurancePeriodMax,
							    		 String[] renewPeriodMin,
							    		 String[] renewPeriodMax,
							    		 String[] valueCommissionCoefficient,
							    		 String[] inStandardCommissionCoefficient,
							    		 String[] outStandardCommissionCoefficient,
							    		 String[] indexingCoefficient,
							    		 String[] productsName,
								         String[] productsCode,
								         String[] productsRatioId,
								         String[] serialNumber ){
    	  Map<String,Object> paras = new HashMap<>();
    	
    	try{
    		Map<String,Object> params = new HashMap<String,Object>();
    		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String invalidDates = request.getParameter("invalidDates");
			Date invalidDateToDtae = dataFormat.parse(invalidDates+" 00:00:00");
	    	Date takeEffectDateToDtae = dataFormat.parse(request.getParameter("takeEffectDates")+" 00:00:00");
    		String productId=insuranceProductInfo.getProductId().toString();
    		paras.put("productId", productId);
			InsuranceProductInfo productInfo = productBasicInformationClient.findByPrimaryKey(insuranceProductInfo.getProductId());
			insuranceProductInfo.setTakeEffectDate(takeEffectDateToDtae);
    		insuranceProductInfo.setInvalidDate(invalidDateToDtae);
    		insuranceProductInfo.setUpdatedTime(new Date());
			if (!productInfo.getProductName().equals(insuranceProductInfo.getProductName())) {
				boolean result = productBasicInformationClient.findByInsuranceCompanyIdAndProductName(insuranceProductInfo.getInsuranceCompanyId(),insuranceProductInfo.getProductName());
				if (result) {
					msg.setMessageCode("200001");
					msg.setData("当前保险公司已有此产品名称");
					return msg;
				}
			}
			if (!productInfo.getProductCode().equals(insuranceProductInfo.getProductCode())) {
				boolean codeResult = productBasicInformationClient.findByProductCode(insuranceProductInfo.getProductCode());
				if (codeResult) {
					msg.setMessageCode("200002");
					 msg.setData("产品编码已存在");
					return msg;
				}
			}
			int x=serialNumber.length;
	    	int y=productsRatioId==null?0:productsRatioId.length;;
	    	 for(int i=0;i<y;i++){
	    	 	if(productsRatioId[i]!=null&&!productsRatioId[i].equals("")){
	    	 		 ProductsCommissionRatio ratioProduct  = productBasicInformationClient.findRatioProductKey(Long.valueOf(productsRatioId[i]));
					 String ratioproductsName = productsName[i];
	    		 	 params.put("insuranceCompanyId",insuranceProductInfo.getInsuranceCompanyId());
					 params.put("productsName",ratioproductsName);
					 if (!ratioProduct.getProductsName().equals(ratioproductsName)) {
						boolean ratioResult = productBasicInformationClient.findRatioByInsuranceCompanyIdAndProductsName(params);
						 if (ratioResult) {
							msg.setMessageCode("200003");
							msg.setData("当前保险公司已有此子产品名称");
							return msg;
						}
					 }
					 if (!ratioProduct.getProductsCode().equals(productsCode[i])) {
						boolean ratioCodeResult = productBasicInformationClient.findRatioProductsCode(productsCode[i]);
						 if (ratioCodeResult) {
							msg.setMessageCode("200004");
							msg.setData("子产品编码已存在");
							return msg;
						}
					 }
				}
			 }
    		productBasicInformationClient.updateInsuranceProduct(insuranceProductInfo);
        	//获取保险产品ID
        	detailWithBLOBs.setInsuranceProductId(insuranceProductInfo.getProductId());
//        	detailWithBLOBs.setUpdatedTime(formatDateTime.parse((formatDateTime.format(new Date()))));
        	//修改详情
        	
        	productBasicInformationClient.updateInsuranceProductDetailWithBLOBs(detailWithBLOBs);
        	//保存保险子产品佣金系数
//        	productBasicInformationClient.deleteInsuranceProductsCommissionRatio(paras);
        	
        	//保存产品期间和续费期间
        	//定义序号


	    	ProductsCommissionRatio productsCommissionRatio = new ProductsCommissionRatio();
	    	 for(int i=0;i<y;i++)       
	         {
	    		 if(productsRatioId[i]!=null&&!productsRatioId[i].equals("")){
	    		 	 productsCommissionRatio.setInsuranceProductId(insuranceProductInfo.getProductId());
		    		 productsCommissionRatio.setRenewPeriodMin(renewPeriodMin[i]);
		    		 productsCommissionRatio.setRenewPeriodMax(renewPeriodMax[i]);
		    		 productsCommissionRatio.setInsurancePeriodMin(insurancePeriodMin[i]);
		    		 productsCommissionRatio.setInsurancePeriodMax(insurancePeriodMax[i]);
		    	
//		    		productsCommissionRatio.setCreatedTime(formatDateTime.parse((formatDateTime.format(new Date()))));
//		    		productsCommissionRatio.setUpdatedTime(formatDateTime.parse((formatDateTime.format(new Date()))));
		    		// productsCommissionRatio.setValueCommissionCoefficient( new BigDecimal(valueCommissionCoefficient[i]));
		    		// productsCommissionRatio.setInStandardCommissionCoefficient(new BigDecimal(inStandardCommissionCoefficient[i]));
		    		// productsCommissionRatio.setOutStandardCommissionCoefficient(new BigDecimal(outStandardCommissionCoefficient[i]));
		    		// productsCommissionRatio.setIndexingCoefficient(new BigDecimal(indexingCoefficient[i]));
		    		 productsCommissionRatio.setProductsName(productsName[i]);
		    		 productsCommissionRatio.setProductsCode(productsCode[i]);
		    		 productsCommissionRatio.setProductsRatioId(Long.valueOf(productsRatioId[i]));
		    		 productBasicInformationClient.updateRenewPeriod(productsCommissionRatio);
	    		 }
	         }
	    	 Map<String,Object> map = new HashMap<String,Object>();
	    	 for(int i=y;i<x;i++){
	    		 if(renewPeriodMin[i]!=null&&!renewPeriodMin[i].equals("")){
	    		     String ratioproductsName = productsName[i];
	    		 	 map.put("insuranceCompanyId",insuranceProductInfo.getInsuranceCompanyId());
					 map.put("productsName",ratioproductsName);
	    		     boolean ratioResult = productBasicInformationClient.findRatioByInsuranceCompanyIdAndProductsName(map);
	    		     if (ratioResult) {
                            msg.setMessageCode("200003");
                            msg.setData("当前保险公司已有此子产品名称");
                            return msg;
	    		     }
	    		     boolean ratioCodeResult = productBasicInformationClient.findRatioProductsCode(productsCode[i]);
	    		     if (ratioCodeResult) {
                            msg.setMessageCode("200004");
                            msg.setData("子产品编码已存在");
                            return msg;
                      }
	    			 productsCommissionRatio.setInsuranceProductId(insuranceProductInfo.getProductId());
		    		 productsCommissionRatio.setRenewPeriodMin(renewPeriodMin[i]);
		    		 productsCommissionRatio.setRenewPeriodMax(renewPeriodMax[i]);
		    		 productsCommissionRatio.setInsurancePeriodMin(insurancePeriodMin[i]);
		    		 productsCommissionRatio.setInsurancePeriodMax(insurancePeriodMax[i]);
		    	
//		    		productsCommissionRatio.setCreatedTime(formatDateTime.parse((formatDateTime.format(new Date()))));
//		    		productsCommissionRatio.setUpdatedTime(formatDateTime.parse((formatDateTime.format(new Date()))));
		    		// productsCommissionRatio.setValueCommissionCoefficient( new BigDecimal(valueCommissionCoefficient[i]));
		    		// productsCommissionRatio.setInStandardCommissionCoefficient(new BigDecimal(inStandardCommissionCoefficient[i]));
		    		// productsCommissionRatio.setOutStandardCommissionCoefficient(new BigDecimal(outStandardCommissionCoefficient[i]));
		    		// productsCommissionRatio.setIndexingCoefficient(new BigDecimal(indexingCoefficient[i]));
		    		 productsCommissionRatio.setProductsName(productsName[i]);
		    		 productsCommissionRatio.setProductsCode(productsCode[i]);
	    			 productBasicInformationClient.addRenewPeriod(productsCommissionRatio);
	    		 }
	         }
        	
            msg.setMessageCode("200");
            logger.info("保险产品[修改]成功");
        }catch (Exception e){
            msg.setMessageCode("400");
            e.printStackTrace();
            msg.setData("保险产品[修改]异常");
	        logger.error(e.getMessage());
            logger.info("保险产品[修改]异常",e);
        }
        return msg;
    }

    
    /**
     *删除修改子产品状态0/1，0-正常，1-删除
     *
     * */
//    @RequiresPermissions(value = "insProduct:updateState")//权限管理;
    @RequestMapping("update_renew_period_status")
    @ResponseBody
    public DataMsg updateRenewPeriodStatus(@RequestParam("productsRatioId")String productsRatioId,DataMsg msg){
    	 Map<String,Object> paras = new HashMap<>();
    	try{
    	    String productsStatus="1";
    		paras.put("productsRatioId", productsRatioId);
    		
    		paras.put("productsStatus", productsStatus);
    		productBasicInformationClient.updateRenewPeriodStatus(paras);
            msg.setMessageCode("200");
            logger.info("保险子产品[修改状态]成功");
        }catch (Exception e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("保险子产品[修改状态]异常",e);
        }
        return msg;
    }
    
    
    /**
     *修改产品状态0/1
     *
     * */
//    @RequiresPermissions(value = "insProduct:updateState")//权限管理;
    @RequestMapping("update_insurance_product_status")
    @ResponseBody
    public DataMsg updateInsuranceProductStatus(@RequestParam("productId")String productId,@RequestParam("productStatus")String productStatus,DataMsg msg){
    	 Map<String,Object> paras = new HashMap<>();
    	try{
    		
    	
    		paras.put("productId", productId);
    		paras.put("productStatus", productStatus);
    		
    		productBasicInformationClient.updateInsuranceProductStatus(paras);
            msg.setMessageCode("200");
            logger.info("保险产品[修改状态]成功");
        }catch (Exception e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("保险产品[修改状态]异常",e);
        }
        return msg;
    }
    /**
	 * 返显子产品信息
	 * */
    @RequestMapping("/select_insurance_basic_product_son")
	@ResponseBody
	public Map<String,Object> selectInsuranceBasicProductSon(@RequestParam("productId") String productId){
		Map<String,Object> paras = new HashMap<>();
		try{
			paras.put("productId",productId);
			InsuranceProductInfo pageModel =productBasicInformationClient.selectInsuranceBasicProductSon(paras);
			String mainOrAdditional = pageModel.getMainOrAdditional();//主附险标识
			String insurancePeriodType = pageModel.getInsurancePeriodType();//保险期间方式
			String renewalPeriodType = pageModel.getRenewalPeriodType();//缴费期间
			String payMode = pageModel.getPayMode();//缴费方式
			String productCode = pageModel.getProductCode();
			String[] split = payMode.split(",");
			String insuranceType = pageModel.getInsuranceType();

			/*List<Map<String>>
			for (int s = 0;s<split.length;s++){
				String cnCodd="其他";
				switch (split[s]){
					case "0":
						cnCodd="年交";
						break;
					case "1":
						cnCodd="半年交";
						break;
					case "2":
						cnCodd="季交";
						break;
					case "3":
						cnCodd="月交";
						break;
					case "4":
						cnCodd="趸交";
						break;
					case "5":
						cnCodd="三年交";
						break;
				}*/

		//	}
			List<String> list = Arrays.asList(split);
			paras.clear();

			paras.put("insuranceType", insuranceType);
			paras.put("mainOrAdditional", mainOrAdditional);
			paras.put("insurancePeriodType",insurancePeriodType );
			paras.put("renewalPeriodType",renewalPeriodType );
			paras.put("list", list);
			paras.put("productCode", productCode);
			paras.put("data", pageModel);
			paras.put("code","200");
			logger.error("保险产品[返显子产品信息]成功");
		}catch (RuntimeException e){
			paras.put("code","400");
			e.printStackTrace();
			logger.error("保险产品[返显子产品信息]异常异常",e);
		}
		return paras;
	}
    /**
     *查看详情 基本信息详情
     *
     * */
   @RequestMapping("/select_insurance_basic_product")
    @ResponseBody
    public  Map<String,Object> selectInsuranceBasicProduct(@RequestParam("productId") String productId){
        Map<String,Object> paras = new HashMap<>();
        try {
            paras.put("productId",productId);
            InsuranceProductInfo pageModel =productBasicInformationClient.selectInsuranceBasicProduct(paras);
            paras.clear();
            paras.put("data", pageModel);
            paras.put("code","200");
            logger.error("保险产品[查看详情]成功");
        } catch (Exception e) {
            paras.put("code","400");
            e.printStackTrace();
            logger.error("保险产品[查看详情]异常异常",e);
        }
        return paras;
    }
   
   /**
 	 * 删除子产品时只保留一条
 	 * @return
 	 */
    @RequestMapping(value = "/check_renew_period_size")
    @ResponseBody
    public int checkRenewPeriodSize(@RequestParam(value = "productId", required = true)Integer productId,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("productId", productId);
            	size= productBasicInformationClient.checkRenewPeriodSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    
    /**
    *
    *查询父产品List，不分页
    * */
	@RequestMapping("/findInsurProducts")
	@ResponseBody
   public DataMsg findInsurProducts(HttpServletRequest request,DataMsg dataMsg){
	   try {
		       Map<String,Object> paras = new HashMap<>();

		  	 	String insuranceCompanyId = request.getParameter("insuranceCompanyId");
		  		 paras.put("insuranceCompanyId",insuranceCompanyId);

		      String companyId= request.getParameter("companyId");
		       paras.put("companyId", companyId);
           String productNameLike = request.getParameter("productNameLike");
            paras.put("productNameLike",productNameLike);
           List<InsuranceProductInfo> list =productBasicInformationClient.findInsurProducts(paras);
		       dataMsg.setRows(list);
		       dataMsg.setMessageCode("200");
		   } catch (Exception e) {
		       dataMsg.setMessageCode("400");
		        e.printStackTrace();
		        logger.error("产品[查询]异常异常",e);
		    }
		    return dataMsg;
	}
@RequestMapping("/findAllInsurProductSub")
@ResponseBody
public DataMsg findAllInsurProductSub(HttpServletRequest request,Model model,DataMsg dataMsg){
	try {
		Map<String,Object> paras = new HashMap<>();

		String insuranceCompanyId = request.getParameter("insuranceCompanyId");
		paras.put("insuranceCompanyId",insuranceCompanyId);

		String companyId= request.getParameter("companyId");
		paras.put("companyId", companyId);
		List<ProductsCommissionRatio> list= productBasicInformationClient.findAllInsurProductSub(paras);
		model.addAttribute("productsCommissionRatio",list);
		dataMsg.setRows(list);
		dataMsg.setMessageCode("200");
	} catch (Exception e) {
		dataMsg.setMessageCode("400");
		e.printStackTrace();
		logger.error("产品[查询]异常异常",e);
	}
	return dataMsg;
}
  //查询子产品
	@RequestMapping("/findInsurProductSub")
	@ResponseBody
   public DataMsg findInsurProductSubs(HttpServletRequest request,Model model,DataMsg dataMsg){
	   try {
		       Map<String,Object> paras = new HashMap<>();
		      String pid= request.getParameter("proId");
		      paras.put("productId", pid);
		      List<ProductsCommissionRatio> list= productBasicInformationClient.getProductsCommissionRatioList(paras);
		      
		       model.addAttribute("productsCommissionRatio",list);	
		       dataMsg.setRows(list);
		       dataMsg.setMessageCode("200");
		   } catch (Exception e) {
		       dataMsg.setMessageCode("400");
		        e.printStackTrace();
		        logger.error("产品[查询]异常异常",e);
		    }
		    return dataMsg;
	}
	
}
