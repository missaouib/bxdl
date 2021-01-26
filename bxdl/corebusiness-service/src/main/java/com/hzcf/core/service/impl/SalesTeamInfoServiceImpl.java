package com.hzcf.core.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.mapper.SalesTeamInfoMapper;
import com.hzcf.core.service.SalesTeamInfoService;
import com.hzcf.core.service.SalesTeamLeadersService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.insurance.SalesOrgDetail;
import com.hzcf.pojo.insurance.SalesTeamInfo;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("ALL")
@Service
public class SalesTeamInfoServiceImpl implements SalesTeamInfoService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SalesTeamInfoMapper salesTeamInfoMapper;
    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;

    @Autowired
    SalesTeamLeadersService salesTeamLeadersService;

    @Override
    public int addSalesTeamInfo(SalesTeamInfo salesTeamInfo) {
    	return salesTeamInfoMapper.addSalesTeamInfo(salesTeamInfo);
    } 
    
    @Override
    public PageModel getSalesTeamInfoList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex",model.getPageSize());
        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String,Object>> list = salesTeamInfoMapper.getSalesTeamInfoList(params);
        long size = salesTeamInfoMapper.getSalesTeamInfoListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

	@Override
	public int findMaxTreeCode(Map<String, Object> params) {		
		int preCode = salesTeamInfoMapper.findPreCode(params);		
		return preCode;
	}

	@Override
	public SalesTeamInfo selectById(Map<String, Object> params) {
		SalesTeamInfo salesTeamInfo = salesTeamInfoMapper.selectById(params);
		return salesTeamInfo;
	}

	@Override
	public List<SalesTeamInfo> getParents(Map<String, Object> params) {
		return salesTeamInfoMapper.getParents(params);
	}

	@Override
	public List<SalesTeamInfo> selectByCondition(Map<String, Object> params) {
		return salesTeamInfoMapper.selectByCondition(params);
	}

	@Override
	public void updateSalesTeamInfo(SalesTeamInfo salesTeamInfo) {
        Map<String, Object> params = new HashMap<>();
        List<SalesTeamInfo> allChildTeamList = new ArrayList<>();
        Map<String, Integer> codeMap = new HashMap<>();

        salesTeamInfo.setUpdatedTime(new Date());
        //查询待修改的父级销售团队
        String parentSaleTeamCode = salesTeamInfo.getParentSalesTeamCode();
        parentSaleTeamCode = (parentSaleTeamCode == null) ? "" : parentSaleTeamCode;
        params.put("salesTeamCode", parentSaleTeamCode);
        List<SalesTeamInfo> salesTeamInfos = salesTeamInfoMapper.getParents(params);

        String PTcode = "";
        if(salesTeamInfos != null && salesTeamInfos.size() > 0){
            PTcode = salesTeamInfos.get(0).getTreeCode();
        }

        Map<String, Object> params1 = new HashMap<>();
        params1.put("salesTeamId", salesTeamInfo.getSalesTeamId());
        SalesTeamInfo salesTeamInfoOld = salesTeamInfoMapper.selectById(params1);
        String oldParentSaleTeamCode = salesTeamInfoOld.getParentSalesTeamCode();
        oldParentSaleTeamCode = (oldParentSaleTeamCode == null) ? "" : oldParentSaleTeamCode;

        //上级销售团队修改，同时要更新所有下级团队
        if(!parentSaleTeamCode.equals(oldParentSaleTeamCode)) {
            String treeCode = getTreeCode(PTcode, codeMap);
            salesTeamInfo.setTreeCode(treeCode);
            diGuiSearchCHildList(salesTeamInfo, allChildTeamList, codeMap);
        }

        salesTeamLeadersService.updateTeams(salesTeamInfo, allChildTeamList,salesTeamInfoOld);
	}



    private void diGuiSearchCHildList(SalesTeamInfo salesTeamInfo,
                                      List<SalesTeamInfo> allChildTeamList, Map<String, Integer> codeMap) {
        List<SalesTeamInfo> salesTeamInfos = salesTeamInfoMapper.getChildSalesTeamByParentCode(salesTeamInfo.getSalesTeamCode());
        salesTeamInfos.forEach(teamInfo -> {
            String PTcode = salesTeamInfo.getTreeCode();
            teamInfo.setTreeCode(getTreeCode(PTcode, codeMap));
            allChildTeamList.add(teamInfo);
            diGuiSearchCHildList(teamInfo,allChildTeamList, codeMap);
        });
    }

    private String getTreeCode(String PTcode, Map<String, Integer> treeCodemap){
        Map<String, Object> paras = new HashMap<>();
        String treeCode = null;
        //treeCode
        int treeCodeLen = 0;
        if(PTcode == null){
            treeCodeLen = 4;
        }else {
            treeCodeLen = PTcode.length()+4;
        }

        paras.put("treeCodeLen",treeCodeLen);
        paras.put("PTcode",PTcode);

        if (treeCodemap.get(PTcode) != null ){
            treeCode = PTcode+ (treeCodemap.get(PTcode) + 1);
        }else {
            int preTreeCode = salesTeamInfoMapper.findPreCode(paras);
            if(preTreeCode==0){
                treeCode = PTcode+"1000";
            }else {
                treeCode = PTcode + (preTreeCode + 1) + "";
            }
        }
        treeCodemap.put(PTcode,Integer.valueOf(treeCode.substring(treeCode.length()-4,treeCode.length())));

        return treeCode;
    }

    @Override
	public void insertImportList(Map<String, Object> p) {
		 Object salesTeam= p.get("salesTeam_string");
       JSONArray objects = JSONObject.parseArray(salesTeam.toString());
       List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
       lists.forEach(list -> {
           logger.info("插入销售团队数据集：");
          int successPipSize = salesTeamInfoMapper.batchAddSalesTeam(list);
            logger.info("插入销售团队数据集成功条数："+successPipSize);
       });
	}

}

