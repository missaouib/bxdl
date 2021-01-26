package com.hzcf.plantform.controller.protocol;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzcf.plantform.feign.EmployeeFeignClient;
import com.hzcf.plantform.feign.insurance.InsuranceDeptClient;
import com.hzcf.pojo.insurance.protocol.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.github.tobato.fastdfs.domain.StorePath;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hzcf.constant.ProtocolConstant;
import com.hzcf.plantform.controller.Insurance.SalesDaysCommissionTask;
import com.hzcf.plantform.fastdfs.FdfsClient;
import com.hzcf.plantform.feign.protocol.LifeProcotolFeignClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.plantform.util.ProtocolTreeView;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.insurance.protocol.InsProtocol;
import com.hzcf.pojo.insurance.protocol.InsProtocolImage;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjust;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjustParamWithBLOBs;
import com.hzcf.pojo.insurance.protocol.InsProtocolSubsidy;
import com.hzcf.pojo.insurance.protocol.InsServiceChargeProduct;
import com.hzcf.util.StringUtil;

import net.sf.json.JSONObject;


/**
 * <dl>
 * <dd>描述:寿险协议管理</dd>
 * </dl>
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/lifeProtocol")
public class LifeProtocolController extends ProtocolBaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LifeProcotolFeignClient lifeProcotolFeignClient;
	@Autowired
	private FdfsClient fdfsClient;
	
	@Autowired
	private SalesDaysCommissionTask salesDaysCommissionTask;
	
	@Value("${fdfs.ip}")
	private String fastInnerUrl;
	
	@RequiresPermissions("lifeProtocol:list")
	@RequestMapping("/goList")
	public String goList() {
		return "lifeProtocol/protocolBasic/lifeProtocolList";
	}

	/**
	 * 查询代理协议列表
	 */
	@ResponseBody
	@RequestMapping("/getLifeProtocolList")
	public DataMsg getLifeProtocolList(HttpServletRequest request, DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String INSURANCE_COMPANY_NAME = StringUtil.trim(request.getParameter("INSURANCE_COMPANY_NAME"));// 保险公司
			if (StringUtil.isNotBlank(INSURANCE_COMPANY_NAME)) {
				paramsCondition.put("INSURANCE_COMPANY_NAME", INSURANCE_COMPANY_NAME);
			}
			String INSURANCE_ORG_NAME = StringUtil.trim(request.getParameter("INSURANCE_ORG_NAME"));// 机构
			if (StringUtil.isNotBlank(INSURANCE_ORG_NAME)) {
				paramsCondition.put("INSURANCE_ORG_NAME", INSURANCE_ORG_NAME);
			}
			String PROTOCOL_NAME = StringUtil.trim(request.getParameter("PROTOCOL_NAME"));// 协议名称
			if (StringUtil.isNotBlank(PROTOCOL_NAME)) {
				paramsCondition.put("PROTOCOL_NAME", PROTOCOL_NAME);
			}
			String PROTOCOL_CODE = StringUtil.trim(request.getParameter("PROTOCOL_CODE"));// 协议编号
			if (StringUtil.isNotBlank(PROTOCOL_CODE)) {
				paramsCondition.put("PROTOCOL_CODE", PROTOCOL_CODE);
			}
			String EFFECTIVE_DATE = StringUtil.trim(request.getParameter("EFFECTIVE_DATE"));// 生效日
			if (StringUtil.isNotBlank(EFFECTIVE_DATE)) {
				paramsCondition.put("EFFECTIVE_DATE", EFFECTIVE_DATE);
			}
			String TERMINATION_DATE = StringUtil.trim(request.getParameter("TERMINATION_DATE"));// 终止日
			if (StringUtil.isNotBlank(TERMINATION_DATE)) {
				paramsCondition.put("TERMINATION_DATE", TERMINATION_DATE);
			}
			String PROTOCOL_STATUS = StringUtil.trim(request.getParameter("PROTOCOL_STATUS"));// 状态
			if (StringUtil.isNotBlank(PROTOCOL_STATUS)) {
				paramsCondition.put("PROTOCOL_STATUS", PROTOCOL_STATUS);
			}
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));

			//数据权限相关查询条件
			Employee employee = getEmployeeDBInfo();
			paramsCondition.put("isAdmin", employee.getEmployeeNo());
			paramsCondition.put("myDeptNo", employee.getDeptNo());

			PageModel pageModel = lifeProcotolFeignClient.getLifeProtocolList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}

	/**
	 * 跳转到添加协议基本信息页面
	 * @return
	 */
	@RequestMapping("/toAddProtocol_basic")
	public String toAddProtocolBasic(HttpServletRequest request) {
		String protocolIds = request.getParameter("id");
		String protocolId = null ;
		if(StringUtil.isNotEmpty(protocolIds)){
			 protocolId = checkIsLook(request, protocolIds);
			if(StringUtil.isNotEmpty(protocolId)){
				Map<String,Object> protocol = lifeProcotolFeignClient.findProtocolInfoById(protocolId);
				request.setAttribute("protocol", protocol);
				request.setAttribute("INSURANCE_COMPANY_ID", protocol.get("INSURANCE_COMPANY_ID"));
			}
		}
		request.setAttribute("lookProtocolIds", protocolIds);
		request.setAttribute("protocolId", protocolId);
		return "lifeProtocol/protocolBasic/addLifeProtocolBasic";
	}

	
	
	/**
	 * 查询一级保险公司名称
	 */
	@RequestMapping("/findFirstInsuranceCompany")
	@ResponseBody
	public List<Map<String,Object>> findFirstInsuranceCompany() {
		try {
			return lifeProcotolFeignClient.findFirstInsuranceCompany();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	

	/**
	 * 查询一级保险公司下的子级tree
	 */
	@RequestMapping("/getSonCompanyTree")
	@ResponseBody
	public List<ProtocolTreeView> getSonCompanyTree(@RequestParam("INSURANCE_COMPANY_ID")int insuranceCompanyId){
		try {
			return lifeProcotolFeignClient.getSonCompanyTree(insuranceCompanyId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 查询销售组织机构tree
	 */
	@RequestMapping("/getSaleTree")
	@ResponseBody
	public List<ProtocolTreeView> getSaleTree(){
		//数据权限相关查询条件
		Map<String, Object> currentOrgInfoMap = new HashMap<>();

		Employee employee = getEmployeeDBInfo();
		if("admin".equals(employee.getEmployeeNo())) {
			currentOrgInfoMap.put("isAdmin", employee.getEmployeeNo());
		}
		currentOrgInfoMap.put("myDeptNo", employee.getDeptNo());
		currentOrgInfoMap.put("salesOrgId", employee.getDeptId());
		try {
			return lifeProcotolFeignClient.getSaleTree(currentOrgInfoMap);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	/**
	 * 查询所有子产品列表
	 */
	@ResponseBody
	@RequestMapping("/getCheckProductList")
	public DataMsg getCheckProductList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			String insuranceOrgId = request.getParameter("insuranceOrgId");
			String insuranceCompanyId = request.getParameter("insuranceCompanyId");
			paramsCondition.put("insuranceOrgId",insuranceCompanyId);
			PageModel pageModel = lifeProcotolFeignClient.getCheckProductList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	/**
	 * 添加协议基本信息
	 * @return
	 */
	@RequestMapping("/addProtocolBasicInfo")
	@ResponseBody
	public Map<String,String> addProtocolBasicInfo(InsProtocol protocol,
			@RequestParam("cSigningDate")String cSigningDate,
			@RequestParam("cEffectiveDate")String cEffectiveDate,
			@RequestParam("cTerminationDate")String cTerminationDate){
			Map<String,String> data = new HashMap<String,String>();
		try {
			//校验协议是否重复
			Map<String,Object> map = lifeProcotolFeignClient.checkForDuplicates(protocol);
			if(null != map){
				data.put("result", "1");
				return data;
			}
			buildAddParam(protocol, cSigningDate, cEffectiveDate, cTerminationDate);
			long protocolId =  lifeProcotolFeignClient.addProtocolBasicInfo(protocol);
			data.put("result", "2");
			data.put("protocolId", protocolId+"");
			return data;
		} catch (Exception e) {
			logger.error(e.getMessage());
			data.put("result", "3");
			return data;
		}
	}

	/**
	 * 修改协议基本信息
	 * @return
	 */
	@RequestMapping("/updateProtocolBasicInfo")
	@ResponseBody
	public Map<String,String> updateProtocolBasicInfo(InsProtocol protocol,
			@RequestParam("cSigningDate")String cSigningDate,
			@RequestParam("cEffectiveDate")String cEffectiveDate,
			@RequestParam("cTerminationDate")String cTerminationDate){
			Map<String,String> data = new HashMap<String,String>();
		try {
			//校验协议是否重复
			Map<String,Object> map = lifeProcotolFeignClient.checkUpdateForDuplicates(protocol);
			if(null != map){
				data.put("result", "1");
				return data;
			}
			buildAddParam(protocol, cSigningDate, cEffectiveDate, cTerminationDate);
			lifeProcotolFeignClient.updateProtocolBasicInfo(protocol);
			data.put("result", "2");
			return data;
		} catch (Exception e) {
			logger.error(e.getMessage());
			data.put("result", "3");
			return data;
		}
	}
	private void buildAddParam(InsProtocol protocol, String cSigningDate, String cEffectiveDate,
			String cTerminationDate) throws ParseException {
		Employee employee = getEmployeeDBInfo();
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 
		protocol.setUpdateTime(new Date());
		protocol.setCreateTime(new Date());
		protocol.setProtocolStatus("0");
		protocol.setEffectiveDate(date.parse(cEffectiveDate));
		protocol.setSigningDate(date.parse(cSigningDate));   
		protocol.setTerminationDate(date.parse(cTerminationDate));
		protocol.setUpdatedBy(employee.getName());
	}
	
	
	@RequestMapping("/productBindingProtocol")
	@ResponseBody
    public Map<String,Object> productBindingProtocol(@RequestParam(value = "list[]") String[] list ,
    		@RequestParam("protocolId") String protocolId,
    		@RequestParam("salesOrgId") String salesOrgId) {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			//校验同一部门协议 产品是否重复
			List<Map<String,Object>> map = lifeProcotolFeignClient.checkSalesProductIsRepeat(salesOrgId,list);
			if(null != map && map.size()>0){
				data.put("result", false);
				data.put("saleName", map.get(0).get("SALES_ORG_NAME"));
				data.put("productName", map.get(0).get("PRODUCTS_NAME"));
			}else{
				data.put("result", true);
				Employee employee = getEmployeeDBInfo();
				lifeProcotolFeignClient.productBindingProtocol(list,protocolId,employee.getName());
			}
			return data;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
    }
	/**
	 * 查询产品管理列表
	 */
	@ResponseBody
	@RequestMapping("/getProductManageList")
	public DataMsg getProductManageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			String protocolId = StringUtil.trim(request.getParameter("protocolId"));// 协议id
			if (StringUtil.isNotBlank(protocolId)) {
				paramsCondition.put("protocolId", protocolId);
			}
			PageModel pageModel = lifeProcotolFeignClient.getProductManageList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	
	/**
	 * 删除绑定产品
	 * @param prorocolProductId
	 * @return
	 */
	@RequestMapping("/deleteProduct")
	@ResponseBody
    public boolean deleteProduct(@RequestParam("prorocolProductId") String prorocolProductId) {
		try {
			Employee employee = getEmployeeDBInfo();
			lifeProcotolFeignClient.deleteProduct(prorocolProductId,employee.getName());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
    }
	
	
	/**
	 * 删除影像
	 * @param prorocolProductId
	 * @return
	 */
	@RequestMapping("/deleteImage")
	@ResponseBody									
    public boolean deleteImage(@RequestParam(value="imageId",required=false) String imageId) {
		try {
			Employee employee = getEmployeeDBInfo();
			lifeProcotolFeignClient.deleteImage(imageId,employee.getName());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
    }
	
	
	/**
	 * 查询图像管理列表
	 */
	@ResponseBody
	@RequestMapping("/getImageManageList")
	public DataMsg getImageManageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			String protocolId = StringUtil.trim(request.getParameter("protocolId"));// 协议id
			if (StringUtil.isNotBlank(protocolId)) {
				paramsCondition.put("protocolId", protocolId);
			}
			PageModel pageModel = lifeProcotolFeignClient.getImageManageList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	
	/**
	 * 影像图片上传
	 */
	@RequestMapping("/uploadImage")
	@ResponseBody
	public boolean uploadImage(MultipartFile file,@RequestParam("protocolId") String protocolId) {
		try {
			InsProtocolImage  image = new InsProtocolImage();
			buildImage(file, protocolId, image);
			lifeProcotolFeignClient.insertProtocolImage(image);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage()); 
			return false;
		}
	}

	public String buildImage(MultipartFile file, String protocolId, InsProtocolImage image) throws IOException {
		String fileName = (file).getOriginalFilename();
		StorePath storePath = fdfsClient.uploadFile(file);
		Employee employee = getEmployeeDBInfo();
		String fastPath = storePath.getFullPath();
		image.setCreateTime(new Date());
		image.setFastUrl(fastPath);
		image.setImageName(fileName);
		image.setImageStatus("0");
		image.setProtocolId(Long.valueOf(protocolId));
		image.setUpdateBy(employee.getName());
		return fastPath;
	}
	
	
	/**
	 * 影像图片下载
	 */
	@RequestMapping("/downLoadImage")
	public void downLoadImage(HttpServletRequest request,HttpServletResponse response) {
		try {
			String url = request.getParameter("url");
			String imageName = request.getParameter("imageName");
			fdfsClient.downLoadFile(fastInnerUrl+url, response, imageName);
		} catch (Exception e) {
			logger.error(e.getMessage()); 
		}
	}
	
	
	/**
	 * 跳转到设置协议手续费页面
	 * @return
	 */
	@RequestMapping("/toAddServiceCharge")
	public String toAddServiceCharge(HttpServletRequest request) {
		String protocolIds = request.getParameter("id");
		String protocolId = checkIsLook(request, protocolIds);
		request.setAttribute("protocolId", protocolId);
		request.setAttribute("service_look_protocolId", protocolIds);
		return "lifeProtocol/serviceCharge/addLifeProtocolService";
	}
	/**
	 * 查询手续费列表
	 */
	@ResponseBody
	@RequestMapping("/getProtocolServiceList")
	public DataMsg getProtocolServiceList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			String protocolId = StringUtil.trim(request.getParameter("protocolId"));// 协议id
			if (StringUtil.isNotBlank(protocolId)) {
				paramsCondition.put("protocolId", protocolId);
			}
			PageModel pageModel = lifeProcotolFeignClient.getProtocolServiceList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	
	/**
	 * 查询折标率列表
	 */
	@ResponseBody
	@RequestMapping("/getProtocolStandardList")
	public DataMsg getProtocolStandardList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			String protocolId = StringUtil.trim(request.getParameter("protocolId"));// 协议id
			if (StringUtil.isNotBlank(protocolId)) {
				paramsCondition.put("protocolId", protocolId);
			}
			PageModel pageModel = lifeProcotolFeignClient.getProtocolStandardList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	
	/**
	 * 查询内部折标率列表
	 */
	@ResponseBody
	@RequestMapping("/getProtocolInsideStandardList")
	public DataMsg getProtocolInsideStandardList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			String protocolId = StringUtil.trim(request.getParameter("protocolId"));// 协议id
			if (StringUtil.isNotBlank(protocolId)) {
				paramsCondition.put("protocolId", protocolId);
			}
			PageModel pageModel = lifeProcotolFeignClient.getProtocolInsideStandardList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	/**
	 * 添加协议手续费
	 * @param rows
	 * @param rateType
	 * @param settlementInterval
	 * @param protocolId
	 */
	@ResponseBody
	@RequestMapping("/addProtocolService")
	public boolean addProtocolService(String rows,String rateType,String settlementInterval,String protocolId){
		try {
			Employee employee = getEmployeeDBInfo();
			List<Map<String,String>> data = (List<Map<String,String>>) JSONArray.parse(rows);
			List<InsServiceChargeProduct> list  = buildServiceList(employee.getName(), rateType,settlementInterval,protocolId, data);
			lifeProcotolFeignClient.addProtocolService(list);
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
        
	}

	private List<InsServiceChargeProduct> buildServiceList(String name, String rateType, String settlementInterval, String protocolId,
			List<Map<String, String>> data) {
			List<InsServiceChargeProduct> list = new ArrayList<InsServiceChargeProduct>();
			for (Map<String, String> map : data) {
				InsServiceChargeProduct product = new InsServiceChargeProduct();
				product.setProtocolId(Long.valueOf(protocolId));
				product.setProductId(Long.valueOf(String.valueOf(map.get("PRODUCT_ID"))));
				product.setProductCode(String.valueOf(map.get("PRODUCTS_CODE")));
				product.setRateType(rateType);
				product.setSettlementInterval(settlementInterval);
				product.setFirstYearCost(new BigDecimal(String.valueOf(map.get("FIRST_YEAR_COST"))));
				product.setSecondYearCost(new BigDecimal(String.valueOf(map.get("SECOND_YEAR_COST"))));
				product.setThirdYearCost(new BigDecimal(String.valueOf(map.get("THIRD_YEAR_COST"))));
				product.setFourYearCost(new BigDecimal(String.valueOf(map.get("FOUR_YEAR_COST"))));
				product.setFiveYearCost(new BigDecimal(String.valueOf(map.get("FIVE_YEAR_COST"))));
				product.setSixYearCost(new BigDecimal(String.valueOf(map.get("SIX_YEAR_COST"))));
				product.setCreateTime(new Date());
				product.setUpdateBy(name);
				list.add(product);
			}	
			return list;
	}

	/**
	 * 跳转到设置折标率页面
	 * @return
	 */
	@RequestMapping("/toAddStandard")
	public String toAddStandard(HttpServletRequest request) {
		String protocolIds = request.getParameter("id");
		String protocolId = checkIsLook(request, protocolIds);
		request.setAttribute("standard_look_protocolId", protocolIds);
		request.setAttribute("protocolId", protocolId);
		return "lifeProtocol/standardRate/addLifeProtocolStandard";
	}
	
	
	/**
	 * 添加折标率
	 * @param rows
	 * @param protocolId
	 */
	@ResponseBody
	@RequestMapping("/addProtocolStandard")
	public boolean addProtocolStandard(String rows,String protocolId){
		try {
			Employee employee = getEmployeeDBInfo();
			List<Map<String,String>> data = (List<Map<String,String>>) JSONArray.parse(rows);
			Map<Object,Object> map = new HashMap<Object,Object>();
			buildStandardMap(employee.getName(),protocolId,data, map);
			lifeProcotolFeignClient.addProtocolStandard(map);
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
        
	}

	private void buildStandardMap(String name, String protocolId, List<Map<String, String>> data,
			Map<Object, Object> map) {
		map.put("protocolId",protocolId);
		map.put("name", name);
		map.put("list",data);
	}

	/**
	 * 跳转到设置内部折标率页面
	 * @return
	 */
	@RequestMapping("/toAddInsideStandard")
	public String toAddInsideStandard(HttpServletRequest request) {
		String protocolIds = request.getParameter("id");
		String protocolId = checkIsLook(request, protocolIds);
		request.setAttribute("protocolId", protocolId);
		request.setAttribute("in_standard_look_protocolId", protocolIds);
		return "lifeProtocol/insideStandardRate/addLifeProtocolInsideStandard";
	}
	/**
	 * 添加内部折标率
	 * @param rows
	 * @param protocolId
	 */
	@ResponseBody
	@RequestMapping("/addProtocolInsideStandard")
	public boolean addProtocolInsideStandard(String rows,String protocolId){
		try {
			Employee employee = getEmployeeDBInfo();
			List<Map<String,String>> data = (List<Map<String,String>>) JSONArray.parse(rows);
			Map<Object,Object> map = new HashMap<Object,Object>();
			buildInsideStandardMap(employee.getName(),protocolId,data, map);
			lifeProcotolFeignClient.addProtocolInsideStandard(map);
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
        
	}

	private void buildInsideStandardMap(String name, String protocolId, List<Map<String, String>> data,
			Map<Object, Object> map) {
		map.put("protocolId",protocolId);
		map.put("name", name);
		map.put("list",data);
	}
	
	/**
	 * 跳转到续年度服务津贴 添加-修改同页面
	 */
	@RequestMapping("/toAddSubsidy")
	public String toAddSubsidy(HttpServletRequest request) {
		List<Integer> asList = buildArray();
		String protocolIds= request.getParameter("id");
		String protocolId = checkIsLook(request, protocolIds);
		InsProtocolSubsidy subsidy  = new InsProtocolSubsidy(); 
		subsidy = lifeProcotolFeignClient.findProtocolSubsidyByProtocolId(protocolId);
		if (null != subsidy && StringUtil.isNotEmpty(subsidy.getSubsidyJson())) {
			String subsidyJson = subsidy.getSubsidyJson();
			List<Map> list = jsonToList(subsidyJson);
			request.setAttribute("flag", "0");
			request.setAttribute("list", list);
			request.setAttribute("rateType", subsidy.getRateType());
			request.setAttribute("setInterval", subsidy.getSettlementInterval());
		} else {
			request.setAttribute("flag", "1");
			request.setAttribute("array", asList);
		}
		request.setAttribute("subsidy_look_protocolId", protocolIds);
		request.setAttribute("protocolId", protocolId);
		return "lifeProtocol/subsidy/addLifeProtocolSubsidy";
	}
	
	/**
	 * 添加续年度服务津贴
	 * @param rows
	 * @param protocolId
	 */
	@ResponseBody
	@RequestMapping("/addProtocolSubsidy")
	public boolean addProtocolSubsidy(String datas,String rateType,String settlementInterval,String protocolId){
		try {
			Employee employee = getEmployeeDBInfo();
			InsProtocolSubsidy  subsidy = buildSubsidyList(datas, rateType, settlementInterval, protocolId,employee);
			lifeProcotolFeignClient.insertProtocolSubsidy(subsidy);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
        
	}

	public InsProtocolSubsidy buildSubsidyList(String datas, String rateType, String settlementInterval,
			String protocolId, Employee employee) {
		InsProtocolSubsidy subsidy = new InsProtocolSubsidy();
		subsidy.setIsException("1");
		subsidy.setProtocolId(Long.valueOf(protocolId));
		subsidy.setRateType(rateType);
		subsidy.setSettlementInterval(settlementInterval);
		subsidy.setSubsidyJson(datas);
		subsidy.setCreateTime(new Date());
		subsidy.setUpdateBy(employee.getName());
		return subsidy;
	}
	
	
	
	/**
	 * 查询续年度添加页面 例外产品列表
	 */
	@ResponseBody
	@RequestMapping("/getAddSubsidyExceptionList")
	public DataMsg getAddSubsidyExceptionList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			String protocolId = StringUtil.trim(request.getParameter("protocolId"));// 协议id
			if (StringUtil.isNotBlank(protocolId)) {
				paramsCondition.put("protocolId", protocolId);
			}
			PageModel pageModel = lifeProcotolFeignClient.getAddSubsidyExceptionList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	
	/**
	 * 添加例外产品
	 */
	@RequestMapping("/updateSubsidyStatus")
	@ResponseBody
	public boolean updateSubsidyStatus(@RequestParam(value = "list[]") String[] list,String rateType,String settlementInterval) {
		try {
			Employee employee = getEmployeeDBInfo();
			lifeProcotolFeignClient.updateSubsidyStatus(list, employee.getName(),rateType, settlementInterval);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	/**
	 * 删除例外产品
	 * @return
	 */
	@RequestMapping("/deleteSubsidyEp")
	@ResponseBody
    public boolean deleteSubsidyEp(@RequestParam("subsidyId") String subsidyId) {
		try {
			lifeProcotolFeignClient.deleteSubsidyEp(subsidyId);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
    }

	/**
	 * 跳转到 续年度服务津贴 例外产品--配置
	 */
	@RequestMapping("/toConfigSubsidy")
	public String toConfigSubsidy(HttpServletRequest request) {
		List<Integer> asList = buildArray();
		String protocolIds = request.getParameter("id");
		String subsidyId = null;
		String lookProtocolIds=null;
		boolean look = isLook(protocolIds);
		if(look){
			subsidyId = protocolIds.split(":")[2];
			lookProtocolIds = protocolIds.substring(0,protocolIds.indexOf(":",protocolIds.indexOf(":")+1 ));
			request.setAttribute("isLook", "yes");
		}else{
			subsidyId = protocolIds.split(":")[1];
			lookProtocolIds =protocolIds.split(":")[0];
		}
		
		InsProtocolSubsidy subsidy = new InsProtocolSubsidy();
		subsidy = lifeProcotolFeignClient.getSubsidyJsonById(subsidyId);
		if (null != subsidy && StringUtil.isNotEmpty(subsidy.getSubsidyJson())) {
			String subsidyJson = subsidy.getSubsidyJson();
			List<Map> list = jsonToList(subsidyJson);
			request.setAttribute("flag", "0");
			request.setAttribute("list", list);
		} else {
			request.setAttribute("flag", "1");
			request.setAttribute("array", asList);
		}
		request.setAttribute("subsidyId", subsidyId);
		request.setAttribute("ep_subsidy_look_protocolId", lookProtocolIds);
		return "lifeProtocol/subsidy/lifeProtocolSubsidyConfig";
	}

	public List<Map> jsonToList(String json) {
		Gson gson = new Gson();
		List<Map> list = new ArrayList<Map>();
		list = gson.fromJson(json, new TypeToken<List<Map>>() {
		}.getType());
		return list;
	}

	public List<Integer> buildArray() {
		int[] src = { 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5,
				4, 3, 2 };
		Integer[] is = ArrayUtils.toObject(src);
		List<Integer> asList = Arrays.asList(is);
		return asList;
	}
	
	/**
	 * 续年度服务津贴-例外产品-配置
	 */
	@RequestMapping("/updateSubsidyConfig")
	@ResponseBody
	public boolean updateSubsidyConfig(String datas,String subsidyId) {
		try {
			Map<String,Object> map= buildSubsidyConfig(datas, subsidyId);
			lifeProcotolFeignClient.updateSubsidyConfig(map);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public Map<String,Object> buildSubsidyConfig(String datas, String subsidyId) {
		Map<String,Object> map= new HashMap<String,Object>();
		Employee employee = getEmployeeDBInfo();
		map.put("datas", datas);
		map.put("name", employee.getName());
		map.put("subsidyId", subsidyId);
		return map;
	}
	
	/**
	 *修改协议状态
	 */
	@RequestMapping("/updateProtocolStatus")
	@ResponseBody
	public boolean updateProtocolStatus(String protocolId,String status) {
		try {
			InsProtocol protocol =  buildUpdateProtocol(protocolId, status);
			lifeProcotolFeignClient.updateProtocolStatus(protocol);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	private InsProtocol buildUpdateProtocol(String protocolId, String status) {
		Employee employee = getEmployeeDBInfo();
		InsProtocol protocol = new InsProtocol();
		protocol.setUpdateTime(new Date());
		protocol.setUpdatedBy(employee.getName());
		protocol.setProtocolStatus(status);
		protocol.setProtocolId(Long.valueOf(protocolId));
		return protocol;
	}
	
	/**
	 * 跳转到费率调节列表页面
	 * @return
	 */
	@RequestMapping("/goRateAdjustList")
	public String goRateAdjustList(HttpServletRequest request) {
		String protocolIds = request.getParameter("id");
		String protocolId = checkIsLook(request, protocolIds);
		request.setAttribute("protocolId", protocolId);
		request.setAttribute("adjust_look_protocolId", protocolIds);
		return "lifeProtocol/rateAdjust/lifeProtocolRateAdjustList";
	}
	
	
	/**
	 * 查询费率调节列表
	 */
	@ResponseBody
	@RequestMapping("/getProtocolRateAdjustList")
	public DataMsg getProtocolRateAdjustList(HttpServletRequest request, DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String protocolId = StringUtil.trim(request.getParameter("PROTOCOL_ID"));
			if (StringUtil.isNotBlank(protocolId)) {
				paramsCondition.put("protocolId", protocolId);
			}
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			PageModel pageModel = lifeProcotolFeignClient.getProtocolRateAdjustList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	
	/**
	 * 跳转到添加费率调节页面
	 * @return
	 */
	@RequestMapping("/toAddRateAdjust")
	public String toAddRateAdjust(HttpServletRequest request) {
		String  ids = request.getParameter("id");
		boolean look = isLook(ids);
		String adjustId = null;
		String protocolId;
		String lookProtocolIds;
		if(ids.split(":").length == 1){
			protocolId =ids;
		}else{
			if(look){
				adjustId = ids.split(":")[2];
				protocolId =ids.split(":")[1];
				request.setAttribute("isLook", "yes");
				lookProtocolIds = ids.substring(0,ids.indexOf(":",ids.indexOf(":")+1 ));
			}else{
				adjustId = ids.split(":")[1];
				protocolId =ids.split(":")[0];
				lookProtocolIds =ids.split(":")[0];
			}
			Map<String,Object> adjust = lifeProcotolFeignClient.findParamByAdjustId(adjustId);
			request.setAttribute("adjustId", adjustId);
			request.setAttribute("adjust_look_protocolId", lookProtocolIds);
			JSONObject jsonObject = JSONObject.fromObject(adjust);
			request.setAttribute("adjust", jsonObject);
		}
		request.setAttribute("protocolId", protocolId);
		return "lifeProtocol/rateAdjust/addProtocolRateAdjustBasic";
	}

	/**
	 * 添加费率调节基本信息
	 */
	@RequestMapping("/addProtocolRateAdjustBasic")
	@ResponseBody
	public Map<String,Object> addProtocolRateAdjust(InsProtocolRateAdjust rateAdjust) {
			Map<String,Object> data = new HashMap<String,Object>();
		try {
			Employee employee = getEmployeeDBInfo();
			rateAdjust.setUpdateBy(employee.getName());
			rateAdjust.setCreateTime(new Date());
			rateAdjust.setAdjustType("0");
			long adjustId = lifeProcotolFeignClient.addProtocolRateAdjustBasic(rateAdjust);
			data.put("result", true);
			data.put("adjustId", adjustId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			data.put("result", false);
		}
		return data;
	}

	/**
	 * 费率调节 新增例外产品
	 */
	@RequestMapping("/addAdjustEpProduct")
	@ResponseBody
	public Map<String,Object> addAdjustEpProduct(@RequestParam(value = "list[]") String[] list,
			@RequestParam("adjustId") String adjustId, @RequestParam("protocolId") String protocolId) {
		try {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			// 校验是否有重复数据
			List<Map<String, Object>> data = lifeProcotolFeignClient.getAdjustIsRepeat(protocolId, list);
			if (data != null && data.size()>0) {
				String productName = (String) data.get(0).get("PRODUCTS_NAME");
				responseMap.put("result", false);
				responseMap.put("productName", productName);
			} else {
				responseMap.put("result", true);
				Employee employee = getEmployeeDBInfo();
				lifeProcotolFeignClient.addAdjustEpProduct(list, protocolId, employee.getName(), adjustId);
			}
			return responseMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 查询调节费率-产品列表
	 */
	@ResponseBody
	@RequestMapping("/getEpAdjustList")
	public DataMsg getEpAdjustList(HttpServletRequest request, DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String protocolId = StringUtil.trim(request.getParameter("protocolId"));// 协议id
			if (StringUtil.isNotBlank(protocolId)) {
				paramsCondition.put("protocolId", protocolId);
			}
			String adjustId = StringUtil.trim(request.getParameter("adjustId"));// 费率调节主表id
			if (StringUtil.isNotBlank(adjustId)) {
				paramsCondition.put("adjustId", adjustId);
			}
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			PageModel pageModel = lifeProcotolFeignClient.getEpAdjustList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataMsg;
	}
	/**
	 *  修改固定调节费率
	 */
	@ResponseBody
	@RequestMapping("/updateAdjustFixedRate")
	public boolean updateAdjustFixedRate(String rows,String protocolId){
		try {
			Employee employee = getEmployeeDBInfo();
			List<Map<String,Object>> data = (List<Map<String,Object>>) JSONArray.parse(rows);
			List<InsProtocolRateAdjustParamWithBLOBs> list  = buildAdjustList(data,employee,protocolId);
			lifeProcotolFeignClient.updateAdjustFixedRate(list);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
        
	}

	private List<InsProtocolRateAdjustParamWithBLOBs> buildAdjustList(List<Map<String, Object>> data, Employee employee,String protocolId) {
		List<InsProtocolRateAdjustParamWithBLOBs> list = new ArrayList<InsProtocolRateAdjustParamWithBLOBs>();
		for (Map<String, Object> map : data) {
			InsProtocolRateAdjustParamWithBLOBs adjust = new InsProtocolRateAdjustParamWithBLOBs();
			adjust.setAdjustId(Long.valueOf(String.valueOf(map.get("ADJUST_ID"))));
			adjust.setpProductId(Long.valueOf(String.valueOf(map.get("P_PRODUCT_ID"))));
			adjust.setsProductId(Long.valueOf(String.valueOf(map.get("S_PRODUCT_ID"))));
			adjust.setChangeRate(String.valueOf(map.get("CHANGE_RATE")));
			adjust.setUpdateBy(employee.getName());
			adjust.setCreateTime(new Date());
			adjust.setProtocolId(Long.valueOf(protocolId));
			list.add(adjust);
		}	
		return list;
	}
	
	/**
	 * 跳转到设置 单/全产品 - 变动费率页面
	 * @return
	 */
	@RequestMapping("/toAddSingleRateAdjust")
	public String toAddSingleRateAdjust(HttpServletRequest request) {
		String ids = request.getParameter("id");
		String adjustParamId;
		boolean look = isLook(ids);
		if(look){
			request.setAttribute("isLook", "yes");
			adjustParamId =  ids.split(":")[1];
		}else{
			adjustParamId = ids;
		}
		List<Map> changeRatelist = new ArrayList<Map>();
		net.sf.json.JSONArray  subjoinRatelist = null;
		Gson gson = new Gson();
		Map<String,Object> data = lifeProcotolFeignClient.findParamById(adjustParamId);
		if (null != data) {
			String changeRate = (String)data.get("CHANGE_RATE");
			changeRatelist = buildDataList(changeRate);
			String changeSubjoinRate = (String)data.get("CHANGE_SUBJOIN_RATE");
			subjoinRatelist = getJsonArrayList(changeSubjoinRate);
			// 回显下拉框
			String subjoinType = (String)data.get("SUBJOIN_TYPE");
			if (StringUtil.isNotEmpty(subjoinType)) {
				net.sf.json.JSONArray subjoinList = buildSubjoinTypeList(subjoinType);
				request.setAttribute("subjoinList", subjoinList);
			}
		}
		request.setAttribute("changeRateList", changeRatelist);
		request.setAttribute("subjoinRatelist", subjoinRatelist);
		request.setAttribute("adjustParamId", adjustParamId);
		return "lifeProtocol/rateAdjust/addSingleAdjustChangelRate";
	}

	public List<Map> buildDataList(String changeRate) {
		List<Map> changeRatelist;
		if (StringUtil.isNotEmpty(changeRate)) {
			changeRatelist = jsonToList(changeRate);
		} else {
			changeRatelist = buildNullList();
		}
		return changeRatelist;
	}

	/**
	 * 跳转到设置 混合产品 - 变动费率页面
	 * @return
	 */
	@RequestMapping("/toAddBlendRateAdjust")
	public String toAddBlendRateAdjust(HttpServletRequest request) {
		String ids = request.getParameter("id");
		String adjustParamId;
		boolean look = isLook(ids);
		if(look){
			request.setAttribute("isLook", "yes");
			adjustParamId =  ids.split(":")[1];
		}else{
			adjustParamId = ids;
		}
		List<Map> changeRatelist = new ArrayList<Map>();
		net.sf.json.JSONArray  subjoinRatelist = null;
		net.sf.json.JSONArray  allChangeRatelist = null;
		net.sf.json.JSONArray  allChangeSubjoinRateList = null;
		Gson gson = new Gson();
		Map<String,Object> data = lifeProcotolFeignClient.findParamById(adjustParamId);
		if (null != data) {
			String changeRate = (String)data.get("CHANGE_RATE");
			changeRatelist = buildDataList(changeRate);
			
			String changeSubjoinRate =(String)data.get("CHANGE_SUBJOIN_RATE");
			subjoinRatelist = getJsonArrayList(changeSubjoinRate);
			
			String allChangeRate = (String)data.get("ALL_CHANGE_RATE");
			allChangeRatelist = getJsonArrayList(allChangeRate);
			
			String allChangeSubjoinRate =(String)data.get("ALL_CHANGE_SUBJOIN_RATE");
			allChangeSubjoinRateList = getJsonArrayList(allChangeSubjoinRate);
			// 回显下拉框
			String subjoinType = (String)data.get("SUBJOIN_TYPE");
			if (StringUtil.isNotEmpty(subjoinType)) {
				net.sf.json.JSONArray subjoinList = buildSubjoinTypeList(subjoinType);
				request.setAttribute("subjoinList", subjoinList);
			}
		}
		request.setAttribute("changeRateList", changeRatelist);//单产品变动费率
		request.setAttribute("subjoinRatelist", subjoinRatelist);//单产品变动附加费率
		request.setAttribute("allChangeRatelist", allChangeRatelist);//全产品变动费率
		request.setAttribute("allChangeSubjoinRateList", allChangeSubjoinRateList);//全产品变动附加费率
		request.setAttribute("adjustParamId", adjustParamId);
		return "lifeProtocol/rateAdjust/addBlendAdjustChangelRate";
	}

	public net.sf.json.JSONArray getJsonArrayList(String data) {
		net.sf.json.JSONArray subjoinRatelist;
		if (StringUtil.isNotEmpty(data)) {
			subjoinRatelist = net.sf.json.JSONArray.fromObject(jsonToList(data));
		} else {
			subjoinRatelist = net.sf.json.JSONArray.fromObject(buildNullList());
		}
		return subjoinRatelist;
	}
	public net.sf.json.JSONArray buildSubjoinTypeList(String subjoinType) {
		String[] split = subjoinType.split(",");
		List<Map<String,Object>> subjoinList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < split.length; i++) {
			Map<String,Object> subjoinMap = new HashMap<String,Object>();
			if(split[i].equals(ProtocolConstant.S_TYPE_0_KEY)){
				subjoinMap.put("subKey", ProtocolConstant.S_TYPE_0_VALUE);
				subjoinMap.put("subValue", ProtocolConstant.S_TYPE_0_VALUE);
			}
			if(split[i].equals(ProtocolConstant.S_TYPE_1_KEY)){
				subjoinMap.put("subKey", ProtocolConstant.S_TYPE_1_VALUE);
				subjoinMap.put("subValue", ProtocolConstant.S_TYPE_1_VALUE);
			}
			if(split[i].equals(ProtocolConstant.S_TYPE_2_KEY)){
				subjoinMap.put("subKey", ProtocolConstant.S_TYPE_2_VALUE);
				subjoinMap.put("subValue", ProtocolConstant.S_TYPE_2_VALUE);
			}
			if(split[i].equals(ProtocolConstant.S_TYPE_3_KEY)){
				subjoinMap.put("subKey", ProtocolConstant.S_TYPE_3_VALUE);
				subjoinMap.put("subValue", ProtocolConstant.S_TYPE_3_VALUE);
			}
			subjoinList.add(subjoinMap);
		}
		net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(subjoinList);
		return json;
	}
	
	/**
	 * 修改变动费率/附加费率
	 */
	@RequestMapping("/updateAdjustChangeRate")
	@ResponseBody
	public boolean updateAdjustChangeRate(String changeRate,String adjustParamId,
			String changeSubjoinRate,String allChangeRateArr,String allSubjoinRateArr){
		try {
			Employee employee = getEmployeeDBInfo();
			InsProtocolRateAdjustParamWithBLOBs  param = buildAdjustParam(changeRate, adjustParamId, changeSubjoinRate,employee,allChangeRateArr,allSubjoinRateArr);
			lifeProcotolFeignClient.updateAdjustChangeRate(param);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
        
	}

	private InsProtocolRateAdjustParamWithBLOBs buildAdjustParam(String changeRate, String adjustParamId,
			String changeSubjoinRate, Employee employee, String allChangeRateArr, String allSubjoinRateArr) {
		InsProtocolRateAdjustParamWithBLOBs param = new InsProtocolRateAdjustParamWithBLOBs();
		param.setAdjustParamId(Long.valueOf(adjustParamId));
		param.setChangeRate(changeRate);
		param.setChangeSubjoinRate(changeSubjoinRate);
		param.setAllChangeRate(allChangeRateArr);
		param.setAllChangeSubjoinRate(allSubjoinRateArr);
		param.setUpdateTime(new Date());
		param.setUpdateBy(employee.getName());
		return param;
	}

	public List<Map> buildNullList(){
		Map<String,Object> map = new HashMap();
		map.put("test", "test");
		List<Map> list = new ArrayList<Map>();
		list.add(map);
		return list;
	}
	
	/**
	 * 删除费率调节产品
	 */
	@RequestMapping("/deleteChangeRate")
	@ResponseBody
	public boolean deleteChangeRate(String adjustParamId){
		try {
			lifeProcotolFeignClient.deleteChangeRate(adjustParamId);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	/**
	 * 删除费率调节记录
	 */
	@RequestMapping("/deleteProtocolRate")
	@ResponseBody
	public boolean deleteProtocolRate(String adjustId){
		try {
			lifeProcotolFeignClient.deleteProtocolRate(adjustId);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 协议费用页面
	 * @return
	 */
	@RequestMapping("/protocolCost")
	public String protocolCost(HttpServletRequest request) {
		return "lifeProtocol/protocolCost";
	}
	
	@RequestMapping("/protocolCostPage")
    @ResponseBody
    public DataMsg protocolCostPage(HttpServletRequest request, DataMsg dataMsg) {

        try {
            Map<String, Object> paras = new HashMap<>();
            String pageNo = request.getParameter("pageNumber");
            if (StringUtil.isNotBlank(pageNo)) {
                paras.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            } else {
                paras.put("pageNo", 1);
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            } else {
                paras.put("pageSize", 10);
            }
            String selectType = request.getParameter("selectType");
            if (StringUtil.isNotBlank(selectType)) {
                paras.put("selectType", selectType);
            }
            String startTime = request.getParameter("startTime");
            if (StringUtil.isNotBlank(startTime)) {
                paras.put("startTime", startTime);
                
                String endTime = request.getParameter("endTime");
                if (StringUtil.isNotBlank(endTime)) {
                    paras.put("endTime", endTime);
                } else {
                	DateFormat date = new SimpleDateFormat("yyyy-MM"); 
                	paras.put("endTime", date.format(new Date()));
                }
            }
            String selectKeys = request.getParameter("selectKeys");
            if (StringUtil.isNotBlank(selectKeys)) {
                paras.put("selectKeys", selectKeys);
            }
            PageModel pageModel = lifeProcotolFeignClient.getProtocolCostList(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("保险组织架构[查询]异常异常",e);
        }
        return dataMsg;
    }
	
	@RequestMapping("/addProtocolCost")
	public void addProtocolCost(String date) {
		lifeProcotolFeignClient.addProtocolCost(date, "ALL");
	}
	
	@RequestMapping("/addSalesDaysCommission")
	public void addSalesDaysCommission() {
		salesDaysCommissionTask.addSalesDaysCommission();
	}

	@RequestMapping("updateProrocolProduct")
	@ResponseBody
	public DataMsg updateHesitationPeriod(
			DataMsg dataMsg,
			@RequestParam("prorocolProductId") Long prorocolProductId,
			@RequestParam("hesitationPeriod") Integer hesitationPeriod
			) {
		try{
			Subject subject = SecurityUtils.getSubject();
			Employee employee = (Employee) subject.getPrincipal();

			InsProtocolProduct insProtocolProduct = new InsProtocolProduct();
			insProtocolProduct.setProtocolProductId(prorocolProductId);
			insProtocolProduct.setHesitationPeriod(hesitationPeriod);
			insProtocolProduct.setUpdateBy(String.valueOf(employee.getEmployeeId()));
			insProtocolProduct.setUpdateTime(new Date());
			lifeProcotolFeignClient.updateProrocolProduct(insProtocolProduct);
			dataMsg.setMessageCode("200");
		}catch (Exception e){
			logger.error("更新产品犹豫期失败", e);
			dataMsg.setMessageCode("500");
		}
		return dataMsg;
	}


	@Autowired
	EmployeeFeignClient employeeFeignClient;

	private Employee getEmployeeDBInfo() {
		Subject subject = SecurityUtils.getSubject();
		Employee employee = (Employee) subject.getPrincipal();
		employee  = employeeFeignClient.getEmployeeById(employee.getEmployeeId());

		return employee;
	}
}
