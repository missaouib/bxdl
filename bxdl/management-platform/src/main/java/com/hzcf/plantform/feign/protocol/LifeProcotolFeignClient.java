package com.hzcf.plantform.feign.protocol;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.plantform.util.ProtocolTreeView;
import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionListPojo;
import com.hzcf.pojo.insurance.promotion.InsProtocolPromotionPojo;
import com.hzcf.pojo.insurance.protocol.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface LifeProcotolFeignClient {

    @RequestMapping(value = "/lifeProtocol/getLifeProtocolList", method = RequestMethod.POST)
    PageModel getLifeProtocolList(@RequestParam Map<String, Object> paramsCondition);

    @RequestMapping(value = "/lifeProtocol/findFirstInsuranceCompany", method = RequestMethod.POST)
    List<Map<String, Object>> findFirstInsuranceCompany();

    @RequestMapping(value = "/lifeProtocol/getSonCompanyTree", method = RequestMethod.POST)
    List<ProtocolTreeView> getSonCompanyTree(@RequestParam("insuranceCompanyId") int insuranceCompanyId);

    @RequestMapping(value = "/lifeProtocol/getCheckProductList", method = RequestMethod.POST)
    PageModel getCheckProductList(@RequestParam Map<String, Object> paramsCondition);

    @RequestMapping(value = "/lifeProtocol/checkForDuplicates", method = RequestMethod.POST)
    Map<String, Object> checkForDuplicates(@RequestBody InsProtocol protocol);

    @RequestMapping(value = "/lifeProtocol/addProtocolBasicInfo", method = RequestMethod.POST)
    long addProtocolBasicInfo(@RequestBody InsProtocol protocol);

    @RequestMapping(value = "/lifeProtocol/getSaleTree", method = RequestMethod.POST)
    List<ProtocolTreeView> getSaleTree(@RequestParam Map<String, Object> currentOrgInfoMap);

    @RequestMapping(value = "/lifeProtocol/productBindingProtocol", method = RequestMethod.POST)
    void productBindingProtocol(@RequestBody String[] list, @RequestParam("protocolId") String protocolId, @RequestParam("name") String name);

    @RequestMapping(value = "/lifeProtocol/getProductManageList", method = RequestMethod.POST)
    PageModel getProductManageList(@RequestParam Map<String, Object> paramsCondition);

    @RequestMapping(value = "/lifeProtocol/deleteProduct", method = RequestMethod.POST)
    void deleteProduct(@RequestParam("prorocolProductId") String prorocolProductId, @RequestParam("name") String name);
   
    @RequestMapping(value = "/lifeProtocol/getImageManageList", method = RequestMethod.POST)
    PageModel getImageManageList(@RequestParam Map<String, Object> paramsCondition);

    @RequestMapping(value = "/lifeProtocol/insertProtocolImage", method = RequestMethod.POST)
    void insertProtocolImage(@RequestBody InsProtocolImage image);

    @RequestMapping(value = "/lifeProtocol/deleteImage", method = RequestMethod.POST)
    void deleteImage(@RequestParam("prorocolImageId") String prorocolImageId, @RequestParam("name") String name);

    @RequestMapping(value = "/lifeProtocol/getProtocolServiceList", method = RequestMethod.POST)
    PageModel getProtocolServiceList(@RequestParam Map<String, Object> paramsCondition);
   
    @RequestMapping(value = "/lifeProtocol/addProtocolService", method = RequestMethod.POST)
    void addProtocolService(@RequestBody List<InsServiceChargeProduct> list);

    @RequestMapping(value = "/lifeProtocol/addProtocolStandard", method = RequestMethod.POST)
    void addProtocolStandard(@RequestBody Map<Object, Object> map);

    @RequestMapping(value = "/lifeProtocol/insProtocolPromotionProducts", method = RequestMethod.GET)
    List<Map> findProductsByProtocolId(@RequestParam("protocolId") Long protocolId);

    @RequestMapping(value = "/lifeProtocol/insProtocolPromotion", method = RequestMethod.POST)
    void saveInsProtocolPromotion(@RequestBody InsProtocolPromotionPojo insProtocolPromotionPojo);

    @RequestMapping(value = "/lifeProtocol/insProtocolPromotion", method = RequestMethod.GET)
    InsProtocolPromotionPojo queryPromotion(@RequestParam("id")Long id);

    @RequestMapping(value = "/lifeProtocol/addProtocolInsideStandard", method = RequestMethod.POST)
    void addProtocolInsideStandard(@RequestBody Map<Object, Object> map);

    @RequestMapping(value = "/lifeProtocol/insProtocolPromotion/state", method = RequestMethod.POST)
    boolean updatePromotionState(@RequestParam("state") Byte state, @RequestParam("lastModifier") String lastModifier, @RequestParam("id") Long id);

    @RequestMapping(value = "/lifeProtocol/insProtocolPromotion", method = RequestMethod.DELETE)
    boolean deletePromotion(@RequestParam("lastModifier") String lastModifier, @RequestParam("id") Long id);

    @RequestMapping(value = "/lifeProtocol/insProtocolPromotion", method = RequestMethod.PUT)
    boolean updateInsProtocolPromotion(@RequestBody InsProtocolPromotionPojo insProtocolPromotionPojo);

    @RequestMapping(value = "/lifeProtocol/insProtocolPromotions", method = RequestMethod.GET)
    List<InsProtocolPromotionListPojo> queryPromotions(@RequestParam("protocolId") Long protocolId);

    @RequestMapping(value = "/lifeProtocol/findProtocolSubsidyByProtocolId", method = RequestMethod.POST)
	InsProtocolSubsidy findProtocolSubsidyByProtocolId(@RequestParam("protocolId") String protocolId);

    @RequestMapping(value = "/lifeProtocol/getAddSubsidyExceptionList", method = RequestMethod.POST)
	PageModel getAddSubsidyExceptionList(@RequestParam Map<String, Object> paramsCondition);

    @RequestMapping(value = "/lifeProtocol/updateSubsidyStatus", method = RequestMethod.POST)
	void updateSubsidyStatus(@RequestBody String[] list, @RequestParam("name") String name, @RequestParam("rateType") String rateType, @RequestParam("settlementInterval") String settlementInterval);

    @RequestMapping(value = "/lifeProtocol/deleteSubsidyEp", method = RequestMethod.POST)
	void deleteSubsidyEp(@RequestParam("subsidyId") String subsidyId);
   
    @RequestMapping(value = "/lifeProtocol/getSubsidyJsonById", method =  RequestMethod.POST)
	InsProtocolSubsidy getSubsidyJsonById(@RequestParam("subsidyId") String subsidyId);

    @RequestMapping(value = "/lifeProtocol/updateSubsidyConfig", method =  RequestMethod.POST)
	void updateSubsidyConfig(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/lifeProtocol/findProtocolInfoById", method =  RequestMethod.POST)
	Map<String, Object> findProtocolInfoById(@RequestParam("protocolId") String protocolId);
   
    @RequestMapping(value = "/lifeProtocol/getProtocolStandardList", method = RequestMethod.POST)
	PageModel getProtocolStandardList(@RequestParam Map<String, Object> paramsCondition);
   
    @RequestMapping(value = "/lifeProtocol/getProtocolInsideStandardList", method = RequestMethod.POST)
	PageModel getProtocolInsideStandardList(@RequestParam Map<String, Object> paramsCondition);

	@RequestMapping(value = "/lifeProtocol/insertProtocolSubsidy", method = RequestMethod.POST)
	void insertProtocolSubsidy(@RequestBody InsProtocolSubsidy subsidy);

	@RequestMapping(value = "/lifeProtocol/updateProtocolStatus", method = RequestMethod.POST)
	void updateProtocolStatus(@RequestBody InsProtocol protocol);
    
	@RequestMapping(value = "/lifeProtocol/checkUpdateForDuplicates", method = RequestMethod.POST)
	Map<String, Object> checkUpdateForDuplicates(@RequestBody InsProtocol protocol);

	@RequestMapping(value = "/lifeProtocol/updateProtocolBasicInfo", method = RequestMethod.POST)
	void updateProtocolBasicInfo(@RequestBody InsProtocol protocol);
	
	@RequestMapping(value = "/lifeProtocol/getProtocolRateAdjustList", method = RequestMethod.POST)
	PageModel getProtocolRateAdjustList(@RequestParam Map<String, Object> paramsCondition);
	
	@RequestMapping(value = "/lifeProtocol/addProtocolRateAdjustBasic", method = RequestMethod.POST)
	long addProtocolRateAdjustBasic(@RequestBody InsProtocolRateAdjust rateAdjust);
	
	@RequestMapping(value = "/lifeProtocol/addAdjustEpProduct", method = RequestMethod.POST)
	void addAdjustEpProduct(@RequestBody String[] list, @RequestParam("protocolId") String protocolId,@RequestParam("name") String name ,@RequestParam("adjustId") String adjustId);
	
	@RequestMapping(value = "/lifeProtocol/getEpAdjustList", method = RequestMethod.POST)
	PageModel getEpAdjustList(@RequestParam Map<String, Object> paramsCondition);

	@RequestMapping(value = "/lifeProtocol/updateAdjustFixedRate", method = RequestMethod.POST)
	void updateAdjustFixedRate(@RequestBody List<InsProtocolRateAdjustParamWithBLOBs> list);
	
	@RequestMapping(value = "/lifeProtocol/findParamById", method = RequestMethod.POST)
	Map<String,Object> findParamById(@RequestParam("adjustParamId") String adjustParamId);
	
	@RequestMapping(value = "/lifeProtocol/updateAdjustChangeRate", method = RequestMethod.POST)
	void updateAdjustChangeRate(@RequestBody InsProtocolRateAdjustParamWithBLOBs param);

	@RequestMapping(value = "/lifeProtocol/deleteChangeRate", method = RequestMethod.POST)
	void deleteChangeRate(@RequestParam("adjustParamId") String adjustParamId);
	
	@RequestMapping(value = "/lifeProtocol/findParamByAdjustId", method = RequestMethod.POST)
	Map<String, Object> findParamByAdjustId(@RequestParam("adjustId") String adjustId);
	
	@RequestMapping(value = "/lifeProtocol/deleteProtocolRate", method = RequestMethod.POST)
	void deleteProtocolRate(@RequestParam("adjustId") String adjustId);

	@RequestMapping(value = "/lifeProtocol/getAdjustIsRepeat", method = RequestMethod.POST)
	List<Map<String, Object>> getAdjustIsRepeat(@RequestParam("protocolId") String protocolId, @RequestBody String[] list);

	@RequestMapping(value = "/lifeProtocol/checkSalesProductIsRepeat", method = RequestMethod.POST)
	List<Map<String, Object>> checkSalesProductIsRepeat(@RequestParam("salesOrgId") String salesOrgId, @RequestBody String[] list);

    @RequestMapping(value = "/lifeProtocol/getSalesOrgId", method = RequestMethod.GET)
    Long findSalesOrgIdByProtocolId(@RequestParam("protocolId") Long protocolId);
    
    @RequestMapping(value = "/lifeProtocol/protocolCostList",method = RequestMethod.POST)
    PageModel getProtocolCostList(@RequestParam Map<String, Object> paras);
    
    @RequestMapping(value = "/lifeProtocol/addProtocolCost",method = RequestMethod.POST)
    void addProtocolCost(@RequestParam("date") String date, @RequestParam("type") String type);
    
    @RequestMapping(value = "/lifeProtocol/changeLifeProtocolEffect",method = RequestMethod.POST)
    void changeLifeProtocolEffect(@RequestParam("date") String date);

    @RequestMapping(value = "/lifeProtocol/getSonDeptList",method = RequestMethod.POST)
    List<String> getSonDeptList(@RequestParam("insuranceCompanyId") int insuranceCompanyId);

    @RequestMapping(value = "/lifeProtocol/updateProrocolProduct",method = RequestMethod.POST)
    void updateProrocolProduct(@RequestBody InsProtocolProduct insProtocolProduct);
}
