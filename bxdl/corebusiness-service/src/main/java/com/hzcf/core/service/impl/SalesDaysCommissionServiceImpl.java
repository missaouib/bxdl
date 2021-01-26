package com.hzcf.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.hzcf.core.constants.Constants;
import com.hzcf.core.mapper.SalesDaysCommissionMapper;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.service.SalesDaysCommissionService;
import com.hzcf.core.service.SettlementPolicyService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.insurance.sales.DirectorAllowanceStandard;
import com.hzcf.pojo.insurance.sales.InsSalesPreGrade;
import com.hzcf.pojo.insurance.sales.SalesDaysCommission;
import com.hzcf.pojo.insurance.sales.SalesMonthlyCommission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesDaysCommissionServiceImpl implements SalesDaysCommissionService {

	@Autowired
    private SalesDaysCommissionMapper salesDaysCommissionMapper;

	@Autowired
	private SalesOrgInfoMapper salesOrgInfoMapper;
	@Autowired
    private SettlementPolicyService settlementPolicyService;

	@Override
	public void addSalesDaysCommission(List<SalesDaysCommission> salesDaysCommission) {
		salesDaysCommissionMapper.addSalesDaysCommission(salesDaysCommission);
	}
	
	@Override
	public void updateSalesDaysCommission(List<SalesDaysCommission> salesDaysCommission) {
		salesDaysCommissionMapper.updateSalesDaysCommission(salesDaysCommission);
	}

	@Override
	public PageModel getCommissionPage(Map<String, Object> params) {
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

        List<Map<String,Object>> list = salesDaysCommissionMapper.getCommissionPage(params);

        long size = salesDaysCommissionMapper.getSalesDaysCommissionListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}
	
	@Override
	public PageModel getMonthlyCommissionPage(Map<String, Object> params) {
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

        List<Map<String,Object>> list = salesDaysCommissionMapper.getMonthlyCommissionPage(params);
        long size = salesDaysCommissionMapper.getSalesMonthlyCommissionListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	@Override
	public List<Object> findMapList(Map<String, Object> params) {
		return salesDaysCommissionMapper.findMapList(params);
	}

	@Override
	public List<InsSalesPreGrade> findByTjr(Long salerId) {
		return salesDaysCommissionMapper.findByTjr(salerId);
	}
 
	@Override
	public List<InsSalesPreGrade> findByTjrs(String salerIds) {
		Map<String,Object> map = new HashMap<>();
		map.put("salerIds",salerIds);
		return salesDaysCommissionMapper.findByTjrs(map);
	}

	@Override
	public BigDecimal sumSalesFYC(String salerIds) {
		Map<String,Object> map = new HashMap<>();
		map.put("salerIds",salerIds);
		return salesDaysCommissionMapper.sumSalesFYC(map);
	}

	@Override
	public BigDecimal sumSalesZYJT(String salerIds) {
		Map<String,Object> map = new HashMap<>();
		map.put("salerIds",salerIds);
		return salesDaysCommissionMapper.sumSalesZYJT(map);
	}
	
	@Override
	public List<InsSalesPreGrade> findBySj(Long salerId) {
		return salesDaysCommissionMapper.findBySj(salerId);
	}

	@Override
	public int getNumBySj(Long salerId) {
		return salesDaysCommissionMapper.getNumBySj(salerId);
	}

	@Override
	public List<InsSalesPreGrade> findYcgx(Map<String, Object> map) {
		return salesDaysCommissionMapper.findYcgx(map);
	}

	@Override
	public List<InsSalesPreGrade> insuranceSalesList(Map<String, Object> map) {
		return salesDaysCommissionMapper.insuranceSalesList(map);
	}
	
	@Override
	public List<InsSalesPreGrade> quitInsuranceSalesList(Map<String, Object> map) {
		return salesDaysCommissionMapper.quitInsuranceSalesList(map);
	}

    @Override
    public List<SalesDaysCommission> findSaleCommission(Map<String, Object> par) {
        return salesDaysCommissionMapper.findSaleCommission(par);
    }

    @Override
    public BigDecimal sumSalesFYCByMonth(Map<String, Object> par) {
        return salesDaysCommissionMapper.sumSalesFYCByMonth(par);
    }

    @Override
	public int getZJZXBNum(Long salerId) {
		return salesDaysCommissionMapper.getZJZXBNum(salerId);
	}

	@Override
	public int getBZZXBNum(Long salerId) {
		return salesDaysCommissionMapper.getBZZXBNum(salerId);
	}

	@Override
	public List<InsSalesPreGrade> findZJZXB(Long salerId) {
		return salesDaysCommissionMapper.findZJZXB(salerId);
	}

	@Override
	public List<InsSalesPreGrade> findBZZXB(Long salerId) {
		return salesDaysCommissionMapper.findBZZXB(salerId);
	}

	@Override
	public List<DirectorAllowanceStandard> findZJJTXS(Map<String, Object> map) {
		return salesDaysCommissionMapper.findZJJTXS(map);
	}
  /**
   *@描述 保存佣金审核发放数据  并更改佣金明细状态为待发放
   *@创建人 qin lina
   *@创建时间 2020/5/9
   */
	@Transactional
	@Override
	public void addSalesMonthlyCommission(List<SalesMonthlyCommission> salesMonthlyCommission,List<SalesDaysCommission> salesDaysCommissionList) {
        if (CollUtil.isNotEmpty(salesMonthlyCommission)) {
            List<List<?>> monthlylists = PublicUtil.splitList(salesMonthlyCommission, 100);
            monthlylists.forEach(list -> {
                salesDaysCommissionMapper.addSalesMonthlyCommission((List<SalesMonthlyCommission>) list);
            });
        }


        Map<String, Object> param = new HashMap<>();
        param.put("settlementStatus", Constants.SETTLEMENT_STATUS_1);
        if (CollUtil.isNotEmpty(salesDaysCommissionList)) {
            List<List<?>> dayLists = PublicUtil.splitList(salesDaysCommissionList, 100);
            dayLists.forEach(list -> {
                param.put("salesDaysCommissions", list);
                salesDaysCommissionMapper.updateSalesDaysSettlementStatus(param);
            });
        }


	}

	@Override
	public Map<String, Object> findDetail(Map<String, Object> params) {
		return salesDaysCommissionMapper.findDetail(params);
	}
	
	@Override
	public Map<String, Object> findMDetail(Map<String, Object> params) {
		return salesDaysCommissionMapper.findMDetail(params);
	}

	@Override
	public void checkStatus(Map<String, Object> params)  {
        salesDaysCommissionMapper.checkStatus(params);
	}

	@Override
	public List<Object> findMonthlyCommissions(Map<String, Object> params) {
		return salesDaysCommissionMapper.findMonthlyCommissions(params);
	}

	@Override
	public void updateMonthlyCommissions(List<SalesMonthlyCommission> params) {
		salesDaysCommissionMapper.updateMonthlyCommissions(params);
	}
	@Override
	public List<Object> findSettleableMapList(Map<String, Object> params) {
		return salesDaysCommissionMapper.findSettleableMapList(params);
	}

	@Override
	public List<SalesDaysCommission> findDaysCommission(Map<String, Object> params) {
		return salesDaysCommissionMapper.findDaysCommission(params);
	}

	@Override
	public List<Map<String, Object>> findMonthCommission(Map<String, Object> params) {
		return salesDaysCommissionMapper.findMonthCommission(params);
	}

    @Override
    public PageModel getSalesCommissionDetail(Map<String, Object> map) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(map.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( map.get("pageSize"))));
        map.put("startIndex", model.getStartIndex());
        map.put("endIndex", model.getPageSize());
       List<Map<String,Object>> list= salesDaysCommissionMapper.getSalesCommissionDetail(map);
         long size = salesDaysCommissionMapper.getSalesCommissionDetailSize(map);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public void batchRelease(Map<String, Object> map) {
        String commissionId = (String) map.get("commissionId");
        String[] commissionIds = commissionId.split(",");
        List<String> commissionIdList = Arrays.asList(commissionIds);
        List<List<?>> lists = PublicUtil.splitList(commissionIdList, 50);
        lists.forEach(list->{
            map.clear();
            map.put("commissionIds",list);
            salesDaysCommissionMapper.batchRelease(map);
        });
    }

    @Transactional
    @Override
    public void handleFYC(List<SalesDaysCommission> salesDaysCommission_add_list, List<SalesDaysCommission> salesDaysCommission_update_list, List<Long> settlement_policy_id_list) {
            if (CollUtil.isNotEmpty(salesDaysCommission_add_list)){
                salesDaysCommissionMapper.addSalesDaysCommission(salesDaysCommission_add_list);
            }
            if (CollUtil.isNotEmpty(salesDaysCommission_update_list)){
                salesDaysCommissionMapper.updateSalesDaysCommission(salesDaysCommission_update_list);
            }
            if (CollUtil.isNotEmpty(settlement_policy_id_list)){
                settlementPolicyService.updateSettlementStatus(settlement_policy_id_list,Constants.SETTLEMENT_STATUS_1);
            }
    }

    @Override
    public List<Map<String, Object>> getSalerRelation(Map<String, Object> par) {
        return salesDaysCommissionMapper.getSalerRelation(par);
    }

	@Override
	public List<BigDecimal> sumSalesFYCByMonthForEveryOne(Map<String, Object> par) {
		 return salesDaysCommissionMapper.sumSalesFYCByMonthForEveryOne(par);
	}

	@Override
	public List<String> getGroupManpowerByMonth(Map<String, Object> par) {
		return salesDaysCommissionMapper.getGroupManpowerByMonth(par);
	}


}

