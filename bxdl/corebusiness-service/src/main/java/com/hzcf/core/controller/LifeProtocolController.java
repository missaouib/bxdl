package com.hzcf.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hzcf.constant.ProtocolConstant;
import com.hzcf.core.service.InsProtocolCostService;
import com.hzcf.core.service.InsProtocolPromotionService;
import com.hzcf.core.service.LifeProtocolService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.ProtocolTreeView;
import com.hzcf.pojo.insurance.protocol.InsProtocol;
import com.hzcf.pojo.insurance.protocol.InsProtocolImage;
import com.hzcf.pojo.insurance.protocol.InsProtocolProduct;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjust;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjustParamWithBLOBs;
import com.hzcf.pojo.insurance.protocol.InsProtocolSubsidy;
import com.hzcf.pojo.insurance.protocol.InsServiceChargeProduct;

@Controller
@RequestMapping("/lifeProtocol")
public class LifeProtocolController {

	@Autowired
	private LifeProtocolService LifeProcotolService;
	
	@Autowired
	private InsProtocolCostService insProtocolCostService;
	
	@Autowired
	private InsProtocolPromotionService insProtocolPromotionService;

	/**
	 * 查询代理协议列表
	 */
	@ResponseBody
	@RequestMapping("/getLifeProtocolList")
	public PageModel getLifeProtocolList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getLifeProtocolList(paramsCondition);
	}

	/**
	 * 查询一级保险公司名称
	 */
	@RequestMapping("/findFirstInsuranceCompany")
	@ResponseBody
	public List<Map<String, Object>> findFirstInsuranceCompany() {
		return LifeProcotolService.findFirstInsuranceCompany();
	}

	/**
	 * 查询一级保险公司下的子级tree
	 */
	@RequestMapping("/getSonCompanyTree")
	@ResponseBody
	public List<ProtocolTreeView> getSonCompanyTree(@RequestParam("insuranceCompanyId") int insuranceCompanyId) {
		return LifeProcotolService.getSonCompanyTree(insuranceCompanyId);
	}

	@RequestMapping("/getSonDeptList")
	@ResponseBody
	public List<String> getSonDeptList(@RequestParam("insuranceCompanyId") int insuranceCompanyId){
		return  LifeProcotolService.getSonDeptList(insuranceCompanyId);
	}

	/**
	 * 查询销售组织机构tree
	 */
	@RequestMapping("/getSaleTree")
	@ResponseBody
	public List<ProtocolTreeView> getSaleTree(@RequestParam Map<String, Object> currentOrgInfoMap) {
		return LifeProcotolService.getSaleTree(currentOrgInfoMap);
	}

	/**
	 * 查询所有子产品列表
	 */
	@ResponseBody
	@RequestMapping("/getCheckProductList")
	public PageModel getCheckProductList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getCheckProductList(paramsCondition);
	}

	/**
	 * 查询协议是否重复
	 */
	@ResponseBody
	@RequestMapping("/checkForDuplicates")
	public Map<String, Object> checkForDuplicates(@RequestBody InsProtocol protocol) {
		return LifeProcotolService.checkForDuplicates(protocol);
	}
	/**
	 * 修改 查询协议是否重复
	 */
	@ResponseBody
	@RequestMapping("/checkUpdateForDuplicates")
	public Map<String, Object> checkUpdateForDuplicates(@RequestBody InsProtocol protocol) {
		return LifeProcotolService.checkUpdateForDuplicates(protocol);
	}

	/**
	 * 添加协议基本信息
	 * 
	 * @param protocol
	 */
	@ResponseBody
	@RequestMapping("/addProtocolBasicInfo")
	public long addProtocolBasicInfo(@RequestBody InsProtocol protocol) {
		return LifeProcotolService.addProtocolBasicInfo(protocol);
	}
	/**
	 * 修改协议基本信息
	 */
	@ResponseBody
	@RequestMapping("/updateProtocolBasicInfo")
	public void updateProtocolBasicInfo(@RequestBody InsProtocol protocol) {
		LifeProcotolService.updateProtocolBasicInfo(protocol);
	}
	/**
	 * 协议绑定产品
	 * 
	 * @param list
	 * @param protocolId
	 * @return
	 */
	@RequestMapping("/productBindingProtocol")
	@ResponseBody
	public void productBindingProtocol(@RequestBody String[] list, @RequestParam("protocolId") String protocolId,
			@RequestParam("name") String name) {
		LifeProcotolService.productBindingProtocol(list, protocolId, name);
	}

	/**
	 * 查询产品管理列表
	 */
	@ResponseBody
	@RequestMapping("/getProductManageList")
	public PageModel getProductManageList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getProductManageList(paramsCondition);
	}

	@RequestMapping("/deleteProduct")
	@ResponseBody
	public void deleteProduct(@RequestParam("prorocolProductId") String prorocolProductId,
			@RequestParam("name") String name) {
		LifeProcotolService.deleteProduct(prorocolProductId, name);
	}

	@RequestMapping("/deleteImage")
	@ResponseBody
	public void deleteImage(@RequestParam("prorocolImageId") String prorocolImageId,
			@RequestParam("name") String name) {
		LifeProcotolService.deleteImage(prorocolImageId, name);
	}

	/**
	 * 查询图像列表
	 */
	@ResponseBody
	@RequestMapping("/getImageManageList")
	public PageModel getImageManageList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getImageManageList(paramsCondition);
	}

	/**
	 * 添加图像
	 */
	@ResponseBody
	@RequestMapping("/insertProtocolImage")
	public void insertProtocolImage(@RequestBody InsProtocolImage image) {
		LifeProcotolService.insertProtocolImage(image);
	}

	/**
	 * 查询手续费列表
	 */
	@ResponseBody
	@RequestMapping("/getProtocolServiceList")
	public PageModel getProtocolServiceList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getProtocolServiceList(paramsCondition);
	}
	
	/**
	 * 查询折标率列表
	 */
	@ResponseBody
	@RequestMapping("/getProtocolStandardList")
	public PageModel getProtocolStandardList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getProtocolStandardList(paramsCondition);
	}
	
	/**
	 * 查询内部折标率列表
	 */
	@ResponseBody
	@RequestMapping("/getProtocolInsideStandardList")
	public PageModel getProtocolInsideStandardList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getProtocolInsideStandardList(paramsCondition);
	}

	/**
	 * 添加手续费
	 * 
	 * @param map
	 */
	@RequestMapping("/addProtocolService")
	@ResponseBody
	public void addProtocolService(@RequestBody List<InsServiceChargeProduct> list) {
		LifeProcotolService.addProtocolService(list);
	}

	/**
	 * 添加折标率
	 * 
	 * @param map
	 */
	@RequestMapping("/addProtocolStandard")
	@ResponseBody
	public void addProtocolStandard(@RequestBody Map<Object, Object> map) {
		LifeProcotolService.addProtocolStandard(map);
	}

	/**
	 * 添加内部折标率
	 * 
	 * @param map
	 */
	@RequestMapping("/addProtocolInsideStandard")
	@ResponseBody
	public void addProtocolInsideStandard(@RequestBody Map<Object, Object> map) {
		LifeProcotolService.addProtocolInsideStandard(map);
	}

	/**
	 * 查询协议的所有产品
	 */
	@RequestMapping("/selectProductListByProtocolId")
	@ResponseBody
	public List<InsProtocolProduct> selectProductListByProtocolId(@RequestParam("protocolId") String protocolId) {
		return LifeProcotolService.selectProductListByProtocolId(protocolId);
	}

	/**
	 * 添加续年度津贴 非例外
	 */
	@RequestMapping("/insertProtocolSubsidy")
	@ResponseBody
	public void insertBatchProtocolSubsidy(@RequestBody InsProtocolSubsidy subsidy) {
		LifeProcotolService.insertProtocolSubsidy(subsidy);
	}

	/**
	 * 续年度津贴 非例外产品回显
	 */
	@RequestMapping("/findProtocolSubsidyByProtocolId")
	@ResponseBody
	public InsProtocolSubsidy findProtocolSubsidyByProtocolId(@RequestParam("protocolId") String protocolId) {
		return LifeProcotolService.findProtocolSubsidyByProtocolId(protocolId);
	}

	/**
	 * 查询续年度添加页面- 例外产品列表
	 */
	@ResponseBody
	@RequestMapping("/getAddSubsidyExceptionList")
	public PageModel getAddSubsidyExceptionList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getAddSubsidyExceptionList(paramsCondition);
	}

	/**
	 * 续年度服务费 ：添加例外产品
	 */
	@RequestMapping("/updateSubsidyStatus")
	@ResponseBody
	public void updateSubsidyStatus(@RequestBody String[] list, @RequestParam("name") String name,
			@RequestParam("rateType") String rateType,@RequestParam("settlementInterval") String settlementInterval) {
		List<InsProtocolSubsidy> pList = new ArrayList<InsProtocolSubsidy>();
		buildEpSubsidyList(list, name, rateType, settlementInterval, pList);
		LifeProcotolService.updateSubsidyStatus(pList);
	}

	public void buildEpSubsidyList(String[] list, String name, String rateType, String settlementInterval,
			List<InsProtocolSubsidy> pList) {
		for (int i = 0; i < list.length; i++) {
			InsProtocolSubsidy subSidy = new InsProtocolSubsidy();
			subSidy.setUpdateBy(name);
			subSidy.setIsException(ProtocolConstant.IS_EXCEPTION_0);
			subSidy.setProtocolId(Long.valueOf(list[i].split(":")[0]));
			subSidy.setProductId(Long.valueOf(list[i].split(":")[1]));
			subSidy.setRateType(rateType);
			subSidy.setSettlementInterval(settlementInterval);
			pList.add(subSidy);
		}
	}

	/**
	 * 删除例外产品
	 * 
	 * @param subsidyId
	 * @param name
	 */
	@RequestMapping("/deleteSubsidyEp")
	@ResponseBody
	public void deleteSubsidyEp(@RequestParam("subsidyId") String subsidyId) {
		LifeProcotolService.deleteSubsidyEp(subsidyId);
	}
	
	/**
	 * 例外产品--配置回显
	 * @param subsidyId
	 * @param name
	 */
	@RequestMapping("/getSubsidyJsonById")
	@ResponseBody
	public InsProtocolSubsidy getSubsidyJsonById(@RequestParam("subsidyId") String subsidyId) {
		return LifeProcotolService.getSubsidyJsonById(subsidyId);
	}
	

	/**
	 * 续年度服务津贴-例外产品-配置
	 */
	@RequestMapping("/updateSubsidyConfig")
	@ResponseBody
	public void updateSubsidyConfig(@RequestBody Map<String,Object> map) {
		LifeProcotolService.updateSubsidyConfig(map);
	}
	
	/**
	 * 查询协议基本信息 回显
	 */
	@RequestMapping("/findProtocolInfoById")
	@ResponseBody
	public Map<String, Object> findProtocolInfoById(@RequestParam("protocolId") String protocolId) {
		return LifeProcotolService.findProtocolInfoById(protocolId);
	}
	
	/**
	 * 修改协议状态
	 */
	@RequestMapping("/updateProtocolStatus")
	@ResponseBody
	public void updateProtocolStatus(@RequestBody InsProtocol protocol) {
		LifeProcotolService.updateProtocolStatus(protocol);
	}
	
	/**
	 * 查询费率调节列表
	 */
	@ResponseBody
	@RequestMapping("/getProtocolRateAdjustList")
	public PageModel getProtocolRateAdjustList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getProtocolRateAdjustList(paramsCondition);
	}
	
	/**
	 * 添加费率调节基本信息
	 */
	@RequestMapping("/addProtocolRateAdjustBasic")
	@ResponseBody
	public long addProtocolRateAdjust(@RequestBody InsProtocolRateAdjust rateAdjust) {
		return LifeProcotolService.addProtocolRateAdjustBasic(rateAdjust);
	}
	
	/**
	 * 费率调节 新增例外产品
	 */
	@RequestMapping("/addAdjustEpProduct")
	@ResponseBody
	public void addAdjustEpProduct(@RequestBody String[] list, @RequestParam("protocolId") String protocolId,
			@RequestParam("name") String name, @RequestParam("adjustId") String adjustId) {
		LifeProcotolService.addAdjustEpProduct(list, protocolId,name, adjustId);
	}

	/**
	 * 查询调节费率-产品列表
	 */
	@ResponseBody
	@RequestMapping("/getEpAdjustList")
	public PageModel getEpAdjustList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return LifeProcotolService.getEpAdjustList(paramsCondition);
	}
	
	/**
	 * 修改固定调节费率
	 */
	@RequestMapping("/updateAdjustFixedRate")
	@ResponseBody
	public void updateAdjustFixedRate(@RequestBody List<InsProtocolRateAdjustParamWithBLOBs> list) {
		LifeProcotolService.updateAdjustFixedRate(list);
	}
	
	/**
	 * 查询费率调节参数
	 */
	@RequestMapping("/findParamById")
	@ResponseBody
	public Map<String,Object>  findParamById( @RequestParam("adjustParamId") String adjustParamId) {
		return LifeProcotolService.findParamById(adjustParamId); 
	}
	
	/**
	 * 修改变动费率/附加费率
	 */
	@RequestMapping("/updateAdjustChangeRate")
	@ResponseBody
	public void updateAdjustChangeRate(@RequestBody InsProtocolRateAdjustParamWithBLOBs param) {
		LifeProcotolService.updateAdjustChangeRate(param);
	}
	
	/**
	 * 费率调节 删除产品
	 */
	@RequestMapping("/deleteChangeRate")
	@ResponseBody
	public void deleteChangeRate(@RequestParam("adjustParamId") String adjustParamId) {
		LifeProcotolService.deleteChangeRate(adjustParamId);
	}
	
	/**
	 * 查询费率调节记录
	 */
	@RequestMapping("/findParamByAdjustId")
	@ResponseBody
	public Map<String, Object> findParamByAdjustId(@RequestParam("adjustId") String adjustId) {
		return LifeProcotolService.findParamByAdjustId(adjustId);
	}
	/**
	 * 删除费率调节 记录
	 */
	@RequestMapping("/deleteProtocolRate")
	@ResponseBody
	public void deleteProtocolRate(@RequestParam("adjustId") String adjustId) {
		LifeProcotolService.deleteProtocolRate(adjustId);
	};
	
	/**
	 * 校验费率调节是否重复
	 */
	@RequestMapping("/getAdjustIsRepeat")
	@ResponseBody
	public List<Map<String,Object>> getAdjustIsRepeat(@RequestBody String[] list,@RequestParam("protocolId") String protocolId) {
		return LifeProcotolService.getAdjustIsRepeat(list, protocolId);
	}

	/**
	 * 校验同一部门协议 产品是否重复
	 */
	@RequestMapping("/checkSalesProductIsRepeat")
	@ResponseBody
	public List<Map<String,Object>> checkSalesProductIsRepeat(@RequestBody String[] list,@RequestParam("salesOrgId") String salesOrgId) {
		return LifeProcotolService.checkSalesProductIsRepeat(list, salesOrgId);
	}
	
	/**
	 * 查询协议费用列表
	 */
	@ResponseBody
	@RequestMapping("/protocolCostList")
	public PageModel protocolCostList(@RequestParam Map<String, Object> paramsCondition) {
		paramsCondition.put("pageNo", Integer.valueOf((String) paramsCondition.get("pageNo")));
		paramsCondition.put("pageSize", Integer.valueOf((String) paramsCondition.get("pageSize")));
		return insProtocolCostService.getProtocolCostList(paramsCondition);
	}
	
	@RequestMapping("/addProtocolCost")
	@ResponseBody
	public void addProtocolCost(@RequestParam("date") String date, @RequestParam("type") String type) {
		try {
			if ("SERVICECHARGE".equals(type) || "ALL".equals(type)) {
				// 手续费
				insProtocolCostService.getInsProtocolCost(date, "SERVICECHARGE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if ("RATEADJUST".equals(type) || "ALL".equals(type)) {
				// 费率调节
				insProtocolCostService.getInsProtocolCost(date, "RATEADJUST");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if ("PROMOTION".equals(type) || "ALL".equals(type)) {
				// 业务推动
				insProtocolPromotionService.testCalculatePromoteCost(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/changeLifeProtocolEffect")
	@ResponseBody
	public void changeLifeProtocolEffect(@RequestParam("date") String date) {
		LifeProcotolService.changeProtocolStatusTask(date);
	}

	@RequestMapping(value = "/updateProrocolProduct", method = RequestMethod.POST)
	@ResponseBody
	public void updateProrocolProduct(@RequestBody InsProtocolProduct insProtocolProduct) {
		LifeProcotolService.updateProrocolProduct(insProtocolProduct);
	}
}
