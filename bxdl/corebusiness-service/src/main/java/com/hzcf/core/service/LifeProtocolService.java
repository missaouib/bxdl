package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.ProtocolTreeView;
import com.hzcf.pojo.insurance.protocol.InsProtocol;
import com.hzcf.pojo.insurance.protocol.InsProtocolImage;
import com.hzcf.pojo.insurance.protocol.InsProtocolProduct;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjust;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjustParamWithBLOBs;
import com.hzcf.pojo.insurance.protocol.InsProtocolSubsidy;
import com.hzcf.pojo.insurance.protocol.InsServiceChargeProduct;

public interface LifeProtocolService {
	/**
	 * 查询协议列表
	 * 
	 */
	PageModel getLifeProtocolList(Map<String, Object> paramsCondition);

	/**
	 * 查询一级保险菜单
	 * 
	 * @return
	 */
	List<Map<String, Object>> findFirstInsuranceCompany();

	/**
	 * 查询父产品下的子产品
	 * 
	 * @param insuranceCompanyId
	 * @return
	 */
	List<ProtocolTreeView> getSonCompanyTree(int insuranceCompanyId);

	public List<String> getSonDeptList(int insuranceCompanyId);

	/**
	 * 查询所有子产品列表
	 * 
	 * @param paramsCondition
	 */
	PageModel getCheckProductList(Map<String, Object> paramsCondition);

	/**
	 * 查询协议是否重复
	 * 
	 * @param protocol
	 * @return
	 */
	Map<String, Object> checkForDuplicates(InsProtocol protocol);

	/**
	 * 添加协议基本信息
	 * 
	 * @return
	 */
	long addProtocolBasicInfo(InsProtocol protocol);

	/**
	 * 查询销售人员tree
	 * 
	 * @return
	 */
	List<ProtocolTreeView> getSaleTree(Map<String, Object> currentOrgInfoMap);

	/**
	 * 协议绑定产品
	 * 
	 * @param list
	 * @param protocolId
	 * @param name
	 */
	void productBindingProtocol(String[] list, String protocolId, String name);

	/**
	 * 查询产品管理列表
	 */
	PageModel getProductManageList(Map<String, Object> paramsCondition);

	/**
	 * 删除协议绑定产品
	 * 
	 * @param prorocolProductId
	 * @param name
	 */
	void deleteProduct(String prorocolProductId, String name);

	/**
	 * 查询图像列表
	 */
	PageModel getImageManageList(Map<String, Object> paramsCondition);

	/**
	 * 添加图像
	 * 
	 * @param image
	 */
	void insertProtocolImage(InsProtocolImage image);

	/**
	 * 删除图像
	 * 
	 * @param prorocolImageId
	 */
	void deleteImage(String prorocolImageId, String name);

	/**
	 * 查询手续费列表
	 * 
	 * @param paramsCondition
	 * @return
	 */
	PageModel getProtocolServiceList(Map<String, Object> paramsCondition);

	/**
	 * 添加手续费
	 * 
	 * @param list
	 */
	void addProtocolService(List<InsServiceChargeProduct> list);

	/**
	 * 添加折标率
	 * 
	 * @param map
	 */
	void addProtocolStandard(Map<Object, Object> map);

	/**
	 * 添加内部折标率
	 * 
	 * @param map
	 */
	void addProtocolInsideStandard(Map<Object, Object> map);

	/**
	 * 查询协议下产品list
	 * 
	 * @param prorocolId
	 * @return
	 */
	List<InsProtocolProduct> selectProductListByProtocolId(String protocolId);


	/**
	 * 续年度津贴 普通产品回显
	 * 
	 * @param protocolId
	 */
	InsProtocolSubsidy findProtocolSubsidyByProtocolId(String protocolId);

	/**
	 * 查询续年度添加页面 例外产品列表
	 */
	PageModel getAddSubsidyExceptionList(Map<String, Object> paramsCondition);
	/**
	 * 续年度服务费 ：添加例外产品
	 * @param list
	 * @return
	 */
	void updateSubsidyStatus(List<InsProtocolSubsidy> pList);
	/**
	 * 删除例外产品
	 * @param subsidyId
	 * @param name
	 */
	void deleteSubsidyEp(String subsidyId);

	/**
	 * 例外产品 -- 配置回显
	 * @param subsidyId
	 * @return
	 */
	InsProtocolSubsidy getSubsidyJsonById(String subsidyId);
	/**
	 * 续年度服务津贴-例外产品-配置
	 */
	void updateSubsidyConfig(Map map);

	/**
	 * 查询协议基本信息
	 * @param protocolId
	 * @return
	 */
	Map<String, Object> findProtocolInfoById(String protocolId);
	/**
	 * 查询折标率列表
	 */
	PageModel getProtocolStandardList(Map<String, Object> paramsCondition);
	/**
	 * 查询内部折标率列表
	 */
	PageModel getProtocolInsideStandardList(Map<String, Object> paramsCondition);

	/**
	 * 添加续年度服务津贴
	 */
	void insertProtocolSubsidy(InsProtocolSubsidy subsidy);
	/**
	 * 修改协议状态
	 */
	void updateProtocolStatus(InsProtocol protocol);

	/**
	 * 修改 查询协议编码是否重复
	 * @param protocol
	 * @return
	 */
	Map<String, Object> checkUpdateForDuplicates(InsProtocol protocol);
	/**
	 * 修改协议基本信息
	 */
	void updateProtocolBasicInfo(InsProtocol protocol);
	/**
	 * 查询费率调节列表
	 */
	PageModel getProtocolRateAdjustList(Map<String, Object> paramsCondition);
	/**
	 * 添加费率调节基本信息
	 * @return 
	 */
	long addProtocolRateAdjustBasic(InsProtocolRateAdjust rateAdjust);
	/**
	 * 费率调节 新增例外产品
	 */
	void addAdjustEpProduct(String[] list, String protocolId,String name, String adjustId);
	/**
	 * 查询调节费率-产品列表
	 */
	PageModel getEpAdjustList(Map<String, Object> paramsCondition);
	/**
	 * 修改固定调节费率
	 */
	void updateAdjustFixedRate(List<InsProtocolRateAdjustParamWithBLOBs> list);
	/**
	 * 查询费率调节参数
	 */
	Map<String,Object>  findParamById(String adjustParamId);
	/**
	 * 修改变动费率/附加费率
	 */
	void updateAdjustChangeRate(InsProtocolRateAdjustParamWithBLOBs param);
	/**
	 * 费率调节 删除产品
	 */
	void deleteChangeRate(String adjustParamId);

	/**
	 * 查询费率调节
	 * @param protocolId
	 * @return
	 */
	Map<String, Object> findParamByAdjustId(String adjustId);
	/**
	 * 删除费率调节 记录
	 */
	void deleteProtocolRate(String adjustId);
	/**
	 * 校验费率调节是否重复
	 * @return 
	 */
	List<Map<String, Object>> getAdjustIsRepeat(String[] list, String protocolId);
	/**
	 * 校验同一部门协议 产品是否重复
	 */
	List<Map<String, Object>> checkSalesProductIsRepeat(String[] list, String salesOrgId);

	/**
	 * 修改协议生效状态定时任务
	 */
	void changeProtocolStatusTask(String date);

	void updateProrocolProduct(InsProtocolProduct insProtocolProduct);
}
