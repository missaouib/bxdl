package com.hzcf.core.service.impl;

import com.google.common.collect.Maps;
import com.hzcf.core.async.AsyncTaskService;
import com.hzcf.core.constants.Constants;
import com.hzcf.core.mapper.InsuranceSalesInfoMapper;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.service.SalesOrgInfoService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesOrgDetail;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SalesOrgInfoServiceImpl implements SalesOrgInfoService {
    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;
    @Autowired
    private InsuranceSalesInfoMapper insuranceSalesInfoMapper;
    @Autowired
    private AsyncTaskService asyncTaskService;

    @Override
	@Transactional
    public int addSalesOrgInfo(SalesOrgInfo salesOrgInfo) {
		int salesOrgInfoId = salesOrgInfoMapper.addSalesOrgInfo(salesOrgInfo);
		//保存同步日志
        List<Map> noticyDateLogs = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
         map.put("salerNo",null);
        map.put("saleOrgId",salesOrgInfo.getSalesOrgId());
        map.put("type", Constants.NOTICY_TYPE_ORG);
        map.put("isFinshed",Constants.NOTICY_RESULT_FAIL);
        noticyDateLogs.add(map);
        insuranceSalesInfoMapper.addSalesHkNoticeLog(noticyDateLogs);
        //异步通知
        List<SalesOrgInfo> orgInfos = new ArrayList<>();
        orgInfos.add(salesOrgInfo);
        asyncTaskService.asyncOrgsHkNotice(orgInfos);
        return salesOrgInfoId;
	}
    
    @Override
    public void addSalesOrgDetail(SalesOrgDetail salesOrgDetail) {
    	salesOrgInfoMapper.addSalesOrgDetail(salesOrgDetail);
    }    
    
    @Override
    public PageModel getSalesOrgInfoList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getPageSize());
        //数据权限相关
		String myDeptNo = "";
		if(params.get("myDeptNo") != null){
			myDeptNo = String.valueOf(params.get("myDeptNo"));
		}
		String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
		params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String,Object>> list = salesOrgInfoMapper.getSalesOrgInfoList(params);
        long size = salesOrgInfoMapper.getSalesOrgInfoListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

	@Override
	public int findMaxTreeCode(Map<String, Object> params) {		
		int preCode = salesOrgInfoMapper.findPreCode(params);		
		return preCode;
	}

	
    @Override
    public List<SalesOrgInfo> findSalesOrgs(Map<String, Object> params) {
		//数据权限相关
		String myDeptNo = "";
		if(params.get("myDeptNo") != null){
			myDeptNo = String.valueOf(params.get("myDeptNo"));
		}
		String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
		params.put("myAllOrgIds", myAllOrgIds);

		String salesOrgLevel = null;
		if(params.get("salesOrgLevel") != null) {
			salesOrgLevel = String.valueOf(params.get("salesOrgLevel"));
		}
		if(!"05".equals(salesOrgLevel)) {
			return salesOrgInfoMapper.findSalesOrgs(params);
		}
        return salesOrgInfoMapper.findSalesOrgsByTeamLevel(params);
    }

	@Override
	public List<SalesOrgInfo> findSalesOrgsByNames(Map<String, Object> params) {
		return salesOrgInfoMapper.findSalesOrgsByNames(params);
	}

	@Override
	public SalesOrgInfo selectById(Map<String, Object> params) {
		SalesOrgInfo salesOrgInfo = salesOrgInfoMapper.selectById(params);
		SalesOrgDetail salesOrgDetail = salesOrgInfoMapper.selectDetailById(params);
		salesOrgInfo.setSalesOrgDetail(salesOrgDetail);
		return salesOrgInfo;
	}

	@Override
	public List<SalesOrgInfo> getParents(Map<String, Object> params) {
		return salesOrgInfoMapper.getParents(params);
	}

	@Override
    @Transactional
	public void updateSalesOrgInfo(SalesOrgInfo salesOrgInfo,String isNoticy) {

		salesOrgInfoMapper.updateSalesOrgInfo(salesOrgInfo);
		if ("1".equals(isNoticy)) {
			//保存同步日志
			List<Map> noticyDateLogs = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			map.put("salerNo", null);
			map.put("saleOrgId", salesOrgInfo.getSalesOrgId());
			map.put("type", Constants.NOTICY_TYPE_ORG);
			map.put("isFinshed", Constants.NOTICY_RESULT_FAIL);
			noticyDateLogs.add(map);
			insuranceSalesInfoMapper.addSalesHkNoticeLog(noticyDateLogs);
			//异步通知
			List<SalesOrgInfo> orgInfos = new ArrayList<>();
			orgInfos.add(salesOrgInfo);
			asyncTaskService.asyncOrgsHkNotice(orgInfos);
		}
	}

	/**
	 * 通过条件查询基本机构id
	 *
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, String> getCalOrgsByCondition(Map<String, Object> params) {
		Map<String, String> calOrgsMap = new HashMap<>();
		String preCalOrgId = salesOrgInfoMapper.getCalOrgsSalesOrgId(String.valueOf(params.get("preOrgId")));
		String nextCalOrgId = salesOrgInfoMapper.getCalOrgsSalesOrgId(String.valueOf(params.get("nextOrgId")));
		calOrgsMap.put("preCalOrgId", preCalOrgId);
		calOrgsMap.put("nextCalOrgId", nextCalOrgId);
		return calOrgsMap;
	}

	@Override
	public Map<String, String> getCalOrgsBySalesOrgInfo(SalesOrgInfo salesOrgInfo) {
		Map<String, String> calOrgsMap = new HashMap<>();
		String preCalOrgId = salesOrgInfoMapper.getCalOrgsSalesOrgId(String.valueOf(salesOrgInfo.getSalesOrgId()));
		SalesOrgInfo parentOrgInfo = salesOrgInfoMapper.querySaleOrgByCode(salesOrgInfo.getParentSalesOrgCode());
		String nextCalOrgId = salesOrgInfoMapper.getCalOrgsSalesOrgId(String.valueOf(parentOrgInfo.getSalesOrgId()));
		calOrgsMap.put("preCalOrgId", preCalOrgId);
		calOrgsMap.put("nextCalOrgId", nextCalOrgId);
		return calOrgsMap;
	}

	@Override
	public void updateSalesOrgDetail(SalesOrgDetail salesOrgDetail) {
		salesOrgInfoMapper.updateSalesOrgDetail(salesOrgDetail);
	}
	/**
	 * 查看员工部门 a->b->c ...
	 * */
	@Override
	public String selectDeptNamesByOrgId(Map<String, Object> params) {
		return salesOrgInfoMapper.selectDeptNamesByOrgId(params);
	}

	@Override
	public List<SalesOrgInfo> findSubordinateOrgListById(Long salesOrgId) {
		HashMap<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("salesOrgId", salesOrgId);
		List<SalesOrgInfo> list = salesOrgInfoMapper.findSalesOrgs(param);
		if(!CollectionUtils.isEmpty(list)){
			getSubordinateOrg(list, list.get(0).getSalesOrgCode());
		}
		return list;
	}

	@Override
	public String findEmployeeAllOrgs(String deptNo) {
		return salesOrgInfoMapper.findEmployeeAllOrgs(deptNo);
	}

    @Override
    public List<Map<String, Object>> getSaleOrgInfoListBySaleOrgIds(String saleOrgIds) {
        return salesOrgInfoMapper.getSaleOrgInfoListBySaleOrgIds(saleOrgIds);
    }

	@Override
	public void getEmpAllOrgNameInfo(List list) {
		if(list == null || list.isEmpty()){
			return;
		}
		list.forEach(obj -> {
			Map<String, Object> map = (Map<String, Object>)obj;
			String deptId = map.get("dept_id") == null ? "" : String.valueOf(map.get("dept_id"));
			String allDeptName = salesOrgInfoMapper.getEmpAllOrgNameInfoByDeptId(deptId);
			map.put("allDeptName", allDeptName);
		});
	}

	@Override
	public int getTopOrgNum(Map<String, Object> params) {
		return salesOrgInfoMapper.getTopOrgNum(params);
	}

	@Override
	public List<Map<String, Object>> getTopAndSFOrgsList() {
		return salesOrgInfoMapper.getTopAndSFOrgsList();
	}

	private List<SalesOrgInfo> getSubordinateOrg(List<SalesOrgInfo> list , String salesOrgCode){
		HashMap<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("parentSalesOrgCode", salesOrgCode);
		List<SalesOrgInfo> orgInfoList = salesOrgInfoMapper.findSalesOrgs(param);
		if(!CollectionUtils.isEmpty(orgInfoList)){
			for(SalesOrgInfo info : orgInfoList){
				getSubordinateOrg(list, info.getSalesOrgCode());
			}
			list.addAll(orgInfoList);
		}
		return list;
	}

	/**
	 * 异步获取组织机构信息，以及相关的逻辑判断，用来返回前台信息
	 */
	@Override
	public List<SalesOrgInfo> queryOrgInfo(Map<String, Object> params) {
		String salesOrgId = String.valueOf(params.get("salesOrgId"));// 自己的机构ID
		String isDefaultCal = String.valueOf(params.get("isDefaultCal"));// 基本法是否默认
		String calOrgId = String.valueOf(params.get("calOrgId"));// 当前使用的基本法ID
		Map<String, Object> map = new HashMap();
		// 是否默认基本法（0：默认；1:自定义）
		if("1".equals(isDefaultCal)){
			map.put("salesOrgId",salesOrgId);
			return salesOrgInfoMapper.getParents(map);
		}else {
			map.put("salesOrgId",calOrgId);
			return salesOrgInfoMapper.getParents(map);
		}
	}

	/**
	 * 修改是否默认基本法
	 * */
	@Override
	public void updateIsDefaultCal(SalesOrgInfo salesOrgInfo) {
		salesOrgInfoMapper.updateIsDefaultCal(salesOrgInfo);
	}

	/**
	 * 通过treeCode查询销售机构信息
	 *
	 * @param treeCode
	 * @return
	 */
	@Override
	public SalesOrgInfo getSalesOrgInfoByTreeCode(String treeCode) {
		return salesOrgInfoMapper.getSalesOrgInfoByTreeCode(treeCode);
	}

}

