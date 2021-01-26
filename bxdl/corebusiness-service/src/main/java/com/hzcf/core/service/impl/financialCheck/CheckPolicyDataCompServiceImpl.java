package com.hzcf.core.service.impl.financialCheck;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.mapper.financialCheck.CheckPolicyDataCompMapper;
import com.hzcf.core.mapper.financialCheck.CheckPolicyDataHkMapper;
import com.hzcf.core.service.financialCheck.CheckPolicyDataCompService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.PublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
@Service
public class CheckPolicyDataCompServiceImpl implements CheckPolicyDataCompService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CheckPolicyDataCompMapper checkPolicyDataCompMapper;
    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;
    @Autowired
    private CheckPolicyDataHkMapper checkPolicyDataHkMapper;
    @Override
    public PageModel getCheckPolicyDataCompPage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex",model.getPageSize());

        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String, Object>> list = checkPolicyDataCompMapper.getCheckPolicyDataCompList(params);
        long size = checkPolicyDataCompMapper.getCheckPolicyDataCompListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public void insertImportList(Map<String, Object> p) {
         Object checkPolicy= p.get("checkPolicy_string");
       JSONArray objects = JSONObject.parseArray(checkPolicy.toString());
       List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
       lists.forEach(list -> {
           logger.info("插入保险公司对账数据集：");
          int successPipSize = checkPolicyDataCompMapper.batchCheckPolicyComp(list);
            logger.info("插入保险公司对账数据集成功条数："+successPipSize);
       });
    }

    @Override
    @Transactional
    public void beginComparison(Map<String, Object> paras) {
        //开始比对  保险公司和汇康 数据一致的数据（批次号,保单号,保险公司,机构，产品，保费一直） 修改比对状态
        checkPolicyDataCompMapper.updateCheckStatusSame(paras);
        //保险公司存在 汇康不存在（批次号）
         checkPolicyDataCompMapper.updateCheckStatusComp(paras);
         //保险公司不存在  汇康存在（批次号）
        checkPolicyDataCompMapper.updateCheckStatusHK(paras);
        // 核对结果不一致 （保单号一直，结算月一直  产品，保费不一致）
         checkPolicyDataCompMapper.updateCheckStatusDiff(paras);
         //批次结果保存（针对汇康数据  为了批次详情展示 同步比对结果  因为汇康数据可以重复比对）
        checkPolicyDataCompMapper.updateCheckResultHkBatchNum(paras);
    }

    @Override
    public List<Map<String, Object>> getCheckPolicyDataCompAll(Map<String, Object> paras) {
        //数据权限相关
        String myDeptNo = "";
        if(paras.get("myDeptNo") != null){
            myDeptNo = String.valueOf(paras.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        paras.put("myAllOrgIds", myAllOrgIds);

        List<Map<String, Object>> list = checkPolicyDataCompMapper.getCheckPolicyDataCompAllList(paras);
        return list;
    }
    @Override
    public Map<String, Object> getResultNumber(Map<String, Object> paras) {
         Map<String, Object> map = new HashMap<>();
         //核对不一致
        paras.put("checkStatus", "4");
        long resultDiff = checkPolicyDataCompMapper.getCheckPolicyDataCompListSize(paras);
        map.put("resultDiff",resultDiff);
        //我司存在
        paras.put("checkStatus","3");
        long hkExist = checkPolicyDataHkMapper.getCheckPolicyDataHkForBatchListSize(paras);
        map.put("hkExist",hkExist);
        //保司存在
        paras.put("checkStatus", "3");
        long compExist = checkPolicyDataCompMapper.getCheckPolicyDataCompListSize(paras);
        map.put("compExist",compExist);
         //核对一致
        paras.put("checkStatus", "1");
        long resultNoDiff  = checkPolicyDataCompMapper.getCheckPolicyDataCompListSize(paras);
        map.put("resultNoDiff",resultNoDiff);
        return map;
    }
}
