package com.hzcf.core.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzcf.constant.ProtocolConstant;
import com.hzcf.core.mapper.InsInsideStandardRateConfigMapper;
import com.hzcf.core.mapper.InsProtocolImageMapper;
import com.hzcf.core.mapper.InsProtocolMapper;
import com.hzcf.core.mapper.InsProtocolProductMapper;
import com.hzcf.core.mapper.InsProtocolRateAdjustMapper;
import com.hzcf.core.mapper.InsProtocolRateAdjustParamMapper;
import com.hzcf.core.mapper.InsProtocolSubsidyMapper;
import com.hzcf.core.mapper.InsServiceChargeProductMapper;
import com.hzcf.core.mapper.InsStandardRateConfigMapper;
import com.hzcf.core.mapper.InsuranceDeptMapper;
import com.hzcf.core.mapper.InsuranceProductInfoMapper;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.service.LifeProtocolService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.ProtocolTreeView;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.protocol.InsInsideStandardRateConfig;
import com.hzcf.pojo.insurance.protocol.InsProtocol;
import com.hzcf.pojo.insurance.protocol.InsProtocolImage;
import com.hzcf.pojo.insurance.protocol.InsProtocolProduct;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjust;
import com.hzcf.pojo.insurance.protocol.InsProtocolRateAdjustParamWithBLOBs;
import com.hzcf.pojo.insurance.protocol.InsProtocolSubsidy;
import com.hzcf.pojo.insurance.protocol.InsServiceChargeProduct;
import com.hzcf.pojo.insurance.protocol.InsStandardRateConfig;
import com.hzcf.util.StringUtil;

@Service
public class LifeProtocolServiceImpl implements LifeProtocolService {

	@Autowired
	private InsProtocolMapper insProtocolMapper;
	@Autowired
	private InsuranceDeptMapper insuranceDeptMapper;
	@Autowired
	private InsuranceProductInfoMapper insuranceProductInfoMapper;
	@Autowired
	private SalesOrgInfoMapper salesOrgInfoMapper;
	@Autowired
	private InsProtocolProductMapper insProtocolProductMapper;
	@Autowired
	private InsProtocolImageMapper insProtocolImageMapper;
	@Autowired
	private InsServiceChargeProductMapper insServiceChargeProductMapper;
	@Autowired
	private InsStandardRateConfigMapper insStandardRateConfigMapper;
	@Autowired
	private InsInsideStandardRateConfigMapper insInsideStandardRateConfigMapper;
	@Autowired
	private InsProtocolSubsidyMapper insProtocolSubsidyMapper;
	@Autowired
	private InsProtocolRateAdjustMapper insProtocolRateAdjustMapper;
	@Autowired
	private InsProtocolRateAdjustParamMapper insProtocolRateAdjustParamMapper;
	@Override
	public PageModel getLifeProtocolList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());

		//数据权限相关
		String myDeptNo = "";
		if(paramsCondition.get("myDeptNo") != null){
			myDeptNo = String.valueOf(paramsCondition.get("myDeptNo"));
		}
		String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
		paramsCondition.put("myAllOrgIds", myAllOrgIds);

		List<Map<String, Object>> data = insProtocolMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = insProtocolMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public List<Map<String, Object>> findFirstInsuranceCompany() {
		return insuranceDeptMapper.findFirstInsuranceCompany();
	}

	@Override
	public List<ProtocolTreeView> getSonCompanyTree(int insuranceCompanyId) {
		Map<String, Object> map = insuranceDeptMapper.findCompanyOrgByCompanyId(insuranceCompanyId);
		List<ProtocolTreeView> tree = new ArrayList<ProtocolTreeView>();
		List<String> list = new ArrayList<>();
		ProtocolTreeView parent = new ProtocolTreeView();
		parent.setText(String.valueOf(map.get("INSURANCE_COMPANY_NAME")));
		parent.setHref(String.valueOf(""));
		parent.setIcon(String.valueOf(""));
		parent.setId(Integer.parseInt((map.get("INSURANCE_COMPANY_ID").toString())));
		// 根据上级组织机构查询子机构
		List<ProtocolTreeView> childList = new ArrayList<ProtocolTreeView>();
		companyRecursion(childList, list, String.valueOf(map.get("INSURANCE_COMPANY_CODE")));
		parent.setNodes(childList);
		tree.add(parent);
		return tree;
	}

	public List<String> getSonDeptList(int insuranceCompanyId){
		List<String> list = new ArrayList<String>();
		Map<String, Object> map = insuranceDeptMapper.findCompanyOrgByCompanyId(insuranceCompanyId);
		String insuranceCompanyCode =  String.valueOf(map.get("INSURANCE_COMPANY_CODE"));

		String allChildInsCompIds = insuranceDeptMapper.getAllChildInsCompIds(insuranceCompanyCode);
		allChildInsCompIds = StringUtil.isBlanks(allChildInsCompIds) ? "" : allChildInsCompIds;

		return Arrays.asList(allChildInsCompIds.split(","));
	}

	private List<ProtocolTreeView> companyRecursion(List<ProtocolTreeView> childList, List<String> list,
			String insuranceCompanyCode) {
		List<InsuranceDept> clist = insuranceDeptMapper.queryChildByCompanyCode(insuranceCompanyCode);// 子产品
		if (null != clist && clist.size() > 0) {
			for (InsuranceDept c : clist) {
				ProtocolTreeView child = new ProtocolTreeView();
				child.setText(c.getInsuranceCompanyName());
				child.setHref("");
				child.setIcon("");
				child.setId(c.getInsuranceCompanyId().intValue());
				child.setPid(c.getParentCompanyCode().toString());
				List<InsuranceDept> dlist = insuranceDeptMapper.queryChildByCompanyCode(c.getInsuranceCompanyCode());// 子产品
				if (dlist.size() > 0) {
					List<ProtocolTreeView> dhildList = new ArrayList<ProtocolTreeView>();
					for (InsuranceDept d : dlist) {
						ProtocolTreeView dhild = new ProtocolTreeView();
						dhild.setText(d.getInsuranceCompanyName());
						dhild.setHref("");
						dhild.setIcon("");
						dhild.setId(d.getInsuranceCompanyId().intValue());
						dhild.setPid(d.getParentCompanyCode().toString());
						companyTree(dhild, d.getInsuranceCompanyCode());
						dhildList.add(dhild);
					}
					child.setNodes(dhildList);
				}
				childList.add(child);
			}
		}
		return childList;
	}

	private ProtocolTreeView companyTree(ProtocolTreeView child, String insuranceCompanyCode) {
		List<InsuranceDept> dlist = insuranceDeptMapper.queryChildByCompanyCode(insuranceCompanyCode);// 子部门
		if (dlist.size() > 0) {
			List<ProtocolTreeView> dhildList = new ArrayList<ProtocolTreeView>();
			for (InsuranceDept d : dlist) {
				ProtocolTreeView dhild = new ProtocolTreeView();
				dhild.setText(d.getInsuranceCompanyName());
				dhild.setHref("");
				dhild.setIcon("");
				dhild.setId(d.getInsuranceCompanyId().intValue());
				dhild.setPid(d.getParentCompanyCode());
				companyTree(dhild, d.getInsuranceCompanyCode());
				dhildList.add(dhild);
			}
			child.setNodes(dhildList);
		}
		return child;
	}

	@Override
	public PageModel getCheckProductList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		String insuranceOrgId = (String) paramsCondition.get("insuranceOrgId");
		List<String> deptList = getSonDeptList(Integer.parseInt(insuranceOrgId));
		paramsCondition.put("insuranceCompanyIdList",deptList);
//		List<Map<String, Object>> data = insuranceProductInfoMapper.findAllRetMapByPage(paramsCondition);
//		Long totalRecords = insuranceProductInfoMapper.findAllByPageCount(paramsCondition);
		List<Map<String, Object>> data = insuranceProductInfoMapper.findCompanyRatioByPage(paramsCondition);
		Long totalRecords = insuranceProductInfoMapper.findCompanyRatioPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public Map<String, Object> checkForDuplicates(InsProtocol protocol) {
		return insProtocolMapper.checkForDuplicates(protocol);
	}

	@Override
	public long addProtocolBasicInfo(InsProtocol protocol) {
		insProtocolMapper.insertSelectiveAndReturnId(protocol);
		return protocol.getProtocolId();
	}

	@Override
	public List<ProtocolTreeView> getSaleTree(Map<String, Object> currentOrgInfoMap) {
		List<ProtocolTreeView> tree = new ArrayList<ProtocolTreeView>();
		List<String> list = new ArrayList<>();
		if("admin".equals(currentOrgInfoMap.get("isAdmin"))) {
			// 查询出所有的父级销售部门
			List<Map<String, Object>> plist = salesOrgInfoMapper.getParentSaleList();// 父部门
			for (Map<String, Object> map : plist) {
				ProtocolTreeView parent = new ProtocolTreeView();
				parent.setText(String.valueOf(map.get("SALES_ORG_NAME")));
				parent.setHref(String.valueOf(""));
				parent.setIcon(String.valueOf(""));
				parent.setId(Integer.valueOf((map.get("SALES_ORG_ID").toString())));
				// 根据父id查询子部门
				List<ProtocolTreeView> childList = new ArrayList<ProtocolTreeView>();
				saleRecursion(childList, list, String.valueOf(map.get("SALES_ORG_CODE")));
				parent.setNodes(childList);
				tree.add(parent);
			}
		}else {
			SalesOrgInfo salesOrgInfo = salesOrgInfoMapper.selectById(currentOrgInfoMap);
			ProtocolTreeView parent = new ProtocolTreeView();
			parent.setText(salesOrgInfo.getSalesOrgName());
			parent.setHref(String.valueOf(""));
			parent.setIcon(String.valueOf(""));
			parent.setId(Integer.valueOf(salesOrgInfo.getSalesOrgId().toString()));
			// 根据父id查询子部门
			List<ProtocolTreeView> childList = new ArrayList<ProtocolTreeView>();
			saleRecursion(childList, list, salesOrgInfo.getSalesOrgCode());
			parent.setNodes(childList);
			tree.add(parent);
		}


		return tree;
	}

	private List<ProtocolTreeView> saleRecursion(List<ProtocolTreeView> childList, List<String> list,
			String saleOrgCode) {
		List<SalesOrgInfo> clist = salesOrgInfoMapper.queryChildSaleByPcode(saleOrgCode);// 子部门
		if (null != clist && clist.size() > 0) {
			for (SalesOrgInfo c : clist) {
				ProtocolTreeView child = new ProtocolTreeView();
				child.setText(String.valueOf(c.getSalesOrgName()));
				child.setHref("");
				child.setIcon("");
				child.setId(c.getSalesOrgId().intValue());
				child.setPid(c.getParentSalesOrgCode());
				List<SalesOrgInfo> dlist = salesOrgInfoMapper.queryChildSaleByPcode(c.getSalesOrgCode());// 子部门
				if (dlist.size() > 0) {
					List<ProtocolTreeView> dhildList = new ArrayList<ProtocolTreeView>();
					for (SalesOrgInfo s : dlist) {
						ProtocolTreeView dhild = new ProtocolTreeView();
						dhild.setText(String.valueOf(s.getSalesOrgName()));
						dhild.setHref("");
						dhild.setIcon("");
						dhild.setId(s.getSalesOrgId().intValue());
						dhild.setPid(s.getParentSalesOrgCode());
						saleTree(dhild, s.getSalesOrgCode());
						dhildList.add(dhild);
					}
					child.setNodes(dhildList);
				}
				childList.add(child);
			}
		}
		return childList;
	}

	private ProtocolTreeView saleTree(ProtocolTreeView child, String salesOrgCode) {
		List<SalesOrgInfo> dlist = salesOrgInfoMapper.queryChildSaleByPcode(salesOrgCode);// 子部门
		if (dlist.size() > 0) {
			List<ProtocolTreeView> dhildList = new ArrayList<ProtocolTreeView>();
			for (SalesOrgInfo s : dlist) {
				ProtocolTreeView dhild = new ProtocolTreeView();
				dhild.setText(String.valueOf(s.getSalesOrgName()));
				dhild.setHref("");
				dhild.setIcon("");
				dhild.setId(s.getSalesOrgId().intValue());
				dhild.setPid(s.getParentSalesOrgCode());
				saleTree(dhild, s.getSalesOrgCode());
				dhildList.add(dhild);
			}
			child.setNodes(dhildList);
		}
		return child;
	}

	@Override
	public void productBindingProtocol(String[] list, String protocolId, String name) {
		List<InsProtocolProduct> pList = new ArrayList<InsProtocolProduct>();
		for (int i = 0; i < list.length; i++) {
			InsProtocolProduct product = new InsProtocolProduct();
			product.setCreateTime(new Date());
			product.setProtocolId(Long.valueOf(protocolId));
			product.setProductId(Long.valueOf(list[i].split(":")[0]));
			String productCode = list[i].split(":")[1];
			Integer hesitationPeriod  = Integer.valueOf(list[i].split(":")[2]);
			product.setHesitationPeriod(hesitationPeriod);
			if (StringUtil.isNotEmpty(productCode)) {
				product.setProductCode(productCode);
			}
			product.setRelationStatus("0");
			product.setUpdateBy(name);
			pList.add(product);
		}
		insProtocolProductMapper.insertBatch(pList);
	}

	@Override
	public PageModel getProductManageList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = insProtocolProductMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = insProtocolProductMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public void deleteProduct(String prorocolProductId, String name) {
		InsProtocolProduct product = new InsProtocolProduct();
		product.setProtocolProductId(Long.valueOf(prorocolProductId));
		product.setUpdateTime(new Date());
		product.setUpdateBy(name);
		product.setRelationStatus("1");
		insProtocolProductMapper.updateByPrimaryKeySelective(product);

	}

	@Override
	public PageModel getImageManageList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = insProtocolImageMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = insProtocolImageMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public void insertProtocolImage(InsProtocolImage image) {
		insProtocolImageMapper.insertSelective(image);
	}

	@Override
	public void deleteImage(String prorocolImageId, String name) {
		InsProtocolImage image = new InsProtocolImage();
		image.setProtocolImageId(Long.valueOf(prorocolImageId));
		image.setUpdateTime(new Date());
		image.setUpdateBy(name);
		image.setImageStatus("1");
		insProtocolImageMapper.updateByPrimaryKeySelective(image);
	}

	@Override
	public PageModel getProtocolServiceList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<InsServiceChargeProduct> updateData = insServiceChargeProductMapper.getUpdateProtocolServiceList(paramsCondition);
		List<Map<String, Object>> productList  = new ArrayList<Map<String, Object>>();
		if (null != updateData && updateData.size() > 0) {
			InsServiceChargeProduct firstProduct = updateData.get(0);
			productList = insProtocolProductMapper.getProtocolProductList(paramsCondition);
			for (Map<String, Object> map : productList) {
				for (InsServiceChargeProduct product : updateData) {
					if(String.valueOf(map.get("PROTOCOL_ID")).equals(String.valueOf(product.getProtocolId())) &&
							String.valueOf(map.get("PRODUCT_ID")).equals(String.valueOf(product.getProductId()))){
						map.put("FIRST_YEAR_COST", product.getFirstYearCost());
						map.put("SECOND_YEAR_COST", product.getSecondYearCost());
						map.put("THIRD_YEAR_COST",product.getThirdYearCost());
						map.put("FOUR_YEAR_COST", product.getFourYearCost());
						map.put("FIVE_YEAR_COST", product.getFiveYearCost());
						map.put("SIX_YEAR_COST", product.getSixYearCost());
					}
					map.put("RATE_TYPE",firstProduct.getRateType());
					map.put("SETTLEMENT_INTERVAL",firstProduct.getSettlementInterval());
				}
			}
			pageModel.setList(productList);
		} else {
			List<Map<String, Object>> data = insProtocolProductMapper.getProtocolProductList(paramsCondition);
			pageModel.setList(data);
		}
		Long totalRecords = insProtocolProductMapper.getProtocolProductCount(paramsCondition);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public PageModel getProtocolStandardList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> productList  = new ArrayList<Map<String, Object>>();
		List<InsStandardRateConfig> updateData = insStandardRateConfigMapper.getUpdateProtocolStandardList(paramsCondition);
		if (null != updateData && updateData.size() > 0) {
			productList = insProtocolProductMapper.getProtocolProductList(paramsCondition);
			for (Map<String, Object> map : productList) {
				for (InsStandardRateConfig config : updateData) {
					if(String.valueOf(map.get("PROTOCOL_ID")).equals(String.valueOf(config.getProtocolId())) &&
							String.valueOf(map.get("PRODUCT_ID")).equals(String.valueOf(config.getProductId()))){
						map.put("FIRST_STANDARD_RATE",config.getFirstStandardRate());
						map.put("NEXT_STANDARD_RATE", config.getNextStandardRate());
					}
				}
			}
			pageModel.setList(productList);
		} else {
			List<Map<String, Object>> data = insProtocolProductMapper.getProtocolProductList(paramsCondition);
			pageModel.setList(data);
		}
		Long totalRecords = insProtocolProductMapper.getProtocolProductCount(paramsCondition);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public PageModel getProtocolInsideStandardList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> productList  = new ArrayList<Map<String, Object>>();
		List<InsInsideStandardRateConfig> updateData = insInsideStandardRateConfigMapper.getUpdateProtocolInStandardList(paramsCondition);
		if (null != updateData && updateData.size() > 0) {
			productList = insProtocolProductMapper.getProtocolProductList(paramsCondition);
			for (Map<String, Object> map : productList) {
				for (InsInsideStandardRateConfig config : updateData) {
					if(String.valueOf(map.get("PROTOCOL_ID")).equals(String.valueOf(config.getProtocolId())) &&
							String.valueOf(map.get("PRODUCT_ID")).equals(String.valueOf(config.getProductId()))){
						map.put("FIRST_STANDARD_RATE",config.getFirstStandardRate());
						map.put("NEXT_STANDARD_RATE", config.getNextStandardRate());
					}
				}
			}
			pageModel.setList(productList);
		} else {
			List<Map<String, Object>> data = insProtocolProductMapper.getProtocolProductList(paramsCondition);
			pageModel.setList(data);
		}
		Long totalRecords = insProtocolProductMapper.getProtocolProductCount(paramsCondition);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Transactional
	@Override
	public void addProtocolService(List<InsServiceChargeProduct> list) {
		Long protocolId = list.get(0).getProtocolId();
		Map<String, Object> map = insServiceChargeProductMapper.findChargeByProtocolId(protocolId);
		if (null != map) {
			insServiceChargeProductMapper.deleteServiceByProtocolId(protocolId);
		}
		insServiceChargeProductMapper.insertServiceProductBatch(list);
	}

	@Transactional
	@Override
	public void addProtocolStandard(Map<Object, Object> map) {
		List<InsStandardRateConfig> list = buildStandardList(map);
		Long protocolId = list.get(0).getProtocolId();
		Map<String,Object> data = insStandardRateConfigMapper.findStandardByProtocolId(protocolId);
		if(null != data){
			insStandardRateConfigMapper.deleteStandardByProtocolId(protocolId);
		}
		insStandardRateConfigMapper.insertStandardBatch(list);
	}

	public List<InsStandardRateConfig> buildStandardList(Map<Object, Object> map) {
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) map.get("list");
		List<InsStandardRateConfig> list = new ArrayList<InsStandardRateConfig>();
		for (Map<String, Object> data : dataList) {
			InsStandardRateConfig config = new InsStandardRateConfig();
			config.setCreateTime(new Date());
			config.setFirstStandardRate(new BigDecimal(String.valueOf(data.get("FIRST_STANDARD_RATE"))));
			config.setNextStandardRate(new BigDecimal(String.valueOf(data.get("NEXT_STANDARD_RATE"))));
			config.setProductCode(String.valueOf(data.get("PRODUCTS_CODE")));
			config.setProtocolId(Long.valueOf(String.valueOf(map.get("protocolId"))));
			config.setProductId(Long.valueOf(String.valueOf(data.get("PRODUCT_ID"))));
			config.setUpdateBy(String.valueOf(map.get("name")));
			list.add(config);
		}
		return list;
	}

	@Transactional
	@Override
	public void addProtocolInsideStandard(Map<Object, Object> map) {
		List<InsInsideStandardRateConfig> list = buildInStandardList(map);
		Long protocolId = list.get(0).getProtocolId();
		Map<String,Object> data = insInsideStandardRateConfigMapper.findInStandardByProtocolId(protocolId);
		if(null != data){
			insInsideStandardRateConfigMapper.deleteInStandardByProtocolId(protocolId);
		}
		insInsideStandardRateConfigMapper.insertInsideStandardBatch(list);

	}

	public List<InsInsideStandardRateConfig> buildInStandardList(Map<Object, Object> map) {
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) map.get("list");
		List<InsInsideStandardRateConfig> list = new ArrayList<InsInsideStandardRateConfig>();
		for (Map<String, Object> data : dataList) {
			InsInsideStandardRateConfig config = new InsInsideStandardRateConfig();
			config.setCreateTime(new Date());
			config.setFirstStandardRate(new BigDecimal(String.valueOf(data.get("FIRST_STANDARD_RATE"))));
			config.setNextStandardRate(new BigDecimal(String.valueOf(data.get("NEXT_STANDARD_RATE"))));
			config.setProductCode(String.valueOf(data.get("PRODUCTS_CODE")));
			config.setProtocolId(Long.valueOf(String.valueOf(map.get("protocolId"))));
			config.setProductId(Long.valueOf(String.valueOf(data.get("PRODUCT_ID"))));
			config.setUpdateBy(String.valueOf(map.get("name")));
			list.add(config);
		}
		return list;
	}

	@Override
	public List<InsProtocolProduct> selectProductListByProtocolId(String protocolId) {
		return insProtocolProductMapper.queryInsProtocolProductByProtocolId(Long.valueOf(protocolId));
	}
	
	@Transactional
	@Override
	public void insertProtocolSubsidy(InsProtocolSubsidy subsidy) {
		InsProtocolSubsidy oldSubsidy  = insProtocolSubsidyMapper.findSubsidyByProtocolId(subsidy.getProtocolId());
		if(null !=oldSubsidy){
			insProtocolSubsidyMapper.deleteSubsidyByProtocolId(subsidy.getProtocolId());
			Map<String, Object> map = buildUpdateMap(subsidy);
			insProtocolSubsidyMapper.updateRateTypeByProtocolId(map);
		}
		insProtocolSubsidyMapper.insertSelective(subsidy);
	}

	

	public Map<String, Object> buildUpdateMap(InsProtocolSubsidy subsidy) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("protocolId", subsidy.getProtocolId());
		map.put("rateType", subsidy.getRateType());
		map.put("settlementInterval", subsidy.getSettlementInterval());
		return map;
	}

	@Override
	public InsProtocolSubsidy findProtocolSubsidyByProtocolId(String protocolId) {
		return insProtocolSubsidyMapper.findProtocolSubsidyByProtocolId(protocolId);
	}

	@Override
	public PageModel getAddSubsidyExceptionList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = insProtocolSubsidyMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = insProtocolSubsidyMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public void updateSubsidyStatus(List<InsProtocolSubsidy> pList) {
		insProtocolSubsidyMapper.insertEpProtocolSubsidy(pList);
	}

	@Override
	public void deleteSubsidyEp(String subsidyId) {
		insProtocolSubsidyMapper.deleteByPrimaryKey(Long.valueOf(subsidyId)); 
	}

	@Override
	public InsProtocolSubsidy getSubsidyJsonById(String subsidyId) {
		return insProtocolSubsidyMapper.selectByPrimaryKey(Long.valueOf(subsidyId));
	}

	@Override
	public void updateSubsidyConfig(Map map) {
		InsProtocolSubsidy subSidy = new InsProtocolSubsidy();
		subSidy.setSubsidyId(Long.valueOf(String.valueOf(map.get("subsidyId"))));
		subSidy.setUpdateTime(new Date());
		subSidy.setUpdateBy(String.valueOf(map.get("name")));
		subSidy.setSubsidyJson(String.valueOf(map.get("datas")));
		insProtocolSubsidyMapper.updateByPrimaryKeySelective(subSidy);
	}

	@Override
	public Map<String, Object> findProtocolInfoById(String protocolId) {
		return insProtocolMapper.findProtocolInfoById(Long.valueOf(protocolId));
	}

	@Override
	public void updateProtocolStatus(InsProtocol protocol) {
		insProtocolMapper.updateByPrimaryKeySelective(protocol);
	}

	@Override
	public Map<String, Object> checkUpdateForDuplicates(InsProtocol protocol) {
		return insProtocolMapper.checkUpdateForDuplicates(protocol);
	}

	@Override
	public void updateProtocolBasicInfo(InsProtocol protocol) {
		insProtocolMapper.updateByPrimaryKeySelective(protocol);
	}

	@Override
	public PageModel getProtocolRateAdjustList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = insProtocolRateAdjustMapper.findAllRetMapByPage(paramsCondition);
		for (Map<String, Object> map : data) {
			String result = ""; 
			String  subjsonType = (String) map.get("SUBJOIN_TYPE");
			String[] split = subjsonType.split(",");
			for (int i = 0; i < split.length; i++) {
				if(split[i].equals("0")){
					result += ProtocolConstant.S_TYPE_0+" ";
				}
				if(split[i].equals("1")){
					result += ProtocolConstant.S_TYPE_1+" ";
				}
				if(split[i].equals("2")){
					result += ProtocolConstant.S_TYPE_2+" ";
				}
				if(split[i].equals("3")){
					result += ProtocolConstant.S_TYPE_3+" ";
				}
			}
			map.put("SUBJOIN_TYPE", result);
		}
		Long totalRecords = insProtocolRateAdjustMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public long  addProtocolRateAdjustBasic(InsProtocolRateAdjust rateAdjust) {
		insProtocolRateAdjustMapper.insertSelectiveAndReturnId(rateAdjust);
		return rateAdjust.getAdjustId();
	}

	@Override
	public void addAdjustEpProduct(String[] list, String protocolId,String name, String adjustId) {
		List<InsProtocolRateAdjustParamWithBLOBs> batchList = new ArrayList<InsProtocolRateAdjustParamWithBLOBs>();
		for (int i = 0; i < list.length; i++) {
			InsProtocolRateAdjustParamWithBLOBs param = new InsProtocolRateAdjustParamWithBLOBs();
			param.setAdjustId(Long.valueOf(adjustId));
			param.setpProductId(Long.valueOf(list[i].split(":")[0]));
			param.setsProductId(Long.valueOf(list[i].split(":")[1]));
			param.setProtocolId(Long.valueOf(protocolId));
			param.setCreateTime(new Date());
			param.setUpdateBy(name);
			batchList.add(param);
		}
		insProtocolRateAdjustParamMapper.insertBatch(batchList);
	}

	@Override
	public PageModel getEpAdjustList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo(Integer.valueOf(String.valueOf(paramsCondition.get("pageNo"))));
		pageModel.setPageSize(Integer.valueOf(String.valueOf(paramsCondition.get("pageSize"))));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = insProtocolRateAdjustParamMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = insProtocolRateAdjustParamMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Transactional
	@Override
	public void updateAdjustFixedRate(List<InsProtocolRateAdjustParamWithBLOBs> list) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("protocolId",  list.get(0).getProtocolId());
		map.put("adjustId",  list.get(0).getAdjustId());
		Map<String, Object> data = insProtocolRateAdjustParamMapper.findAdjustByPidAndAid(map);
		if (null != data) {
			insProtocolRateAdjustParamMapper.deleteAdjustByPidAndAid(map);
		}
		insProtocolRateAdjustParamMapper.insertBatch(list);
		
	}

	@Override
	public Map<String,Object> findParamById(String adjustParamId) {
		return insProtocolRateAdjustParamMapper.findParamById(Long.valueOf(adjustParamId));
	}

	@Override
	public void updateAdjustChangeRate(InsProtocolRateAdjustParamWithBLOBs param) {
		insProtocolRateAdjustParamMapper.updateByPrimaryKeySelective(param);
	}

	@Override
	public void deleteChangeRate(String adjustParamId) {
		insProtocolRateAdjustParamMapper.deleteByPrimaryKey(Long.valueOf(adjustParamId));
	}

	@Override
	public Map<String, Object> findParamByAdjustId(String adjustId) {
		return insProtocolRateAdjustMapper.findParamByAdjustId(Long.valueOf(adjustId));
	}

	@Override
	public void deleteProtocolRate(String adjustId) {
		InsProtocolRateAdjust record = new InsProtocolRateAdjust();
		record.setAdjustId(Long.valueOf(adjustId));
		record.setUpdateTime(new Date());
		record.setAdjustType("1");
		insProtocolRateAdjustMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Map<String, Object>> getAdjustIsRepeat(String[] list, String protocolId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("protocolId", protocolId);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			buffer.append(list[i].split(":")[1]+",");
		};
		map.put("sProductIds", buffer.substring(0, buffer.length() -1));
		return insProtocolRateAdjustParamMapper.getAdjustIsRepeat(map);
	}

	@Override
	public List<Map<String, Object>> checkSalesProductIsRepeat(String[] list, String salesOrgId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("salesOrgId", salesOrgId);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			buffer.append(list[i].split(":")[0]+",");
		};
		System.out.println("==============="+buffer);
		map.put("productIds", buffer.substring(0, buffer.length() -1));
		return insProtocolMapper.checkSalesProductIsRepeat(map);
	}
	
	@Override
	public void changeProtocolStatusTask(String date) {
		// 到期未生效协议
		List<Map<String, Object>> effectProtocolList = insProtocolMapper.getEffectOrTerminationProtocol(date, 1);
		if (effectProtocolList != null && effectProtocolList.size() > 0) {
			insProtocolMapper.updateBatchEffectOrTerminationProtocol(effectProtocolList, 1);
		}
		// 过期协议
		List<Map<String, Object>> TerminationProtocolList = insProtocolMapper.getEffectOrTerminationProtocol(date, 2);
		if(TerminationProtocolList != null && TerminationProtocolList.size() > 0) {
			insProtocolMapper.updateBatchEffectOrTerminationProtocol(TerminationProtocolList, 2);
		}
	}

	@Override
	public void updateProrocolProduct(InsProtocolProduct insProtocolProduct) {
		insProtocolProductMapper.updateProrocolProduct(insProtocolProduct);
	}


}
