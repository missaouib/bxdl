package com.hzcf.core.service.impl.financialCheck;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzcf.core.constants.Constants;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.mapper.financialCheck.CheckPolicyDataHkMapper;
import com.hzcf.core.service.financialCheck.CheckPolicyDataHkService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.PublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
@Service
public class CheckPolicyDataHkServiceImpl implements CheckPolicyDataHkService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CheckPolicyDataHkMapper checkPolicyDataHkMapper;
    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;
    @Override
    @Transactional
    public PageModel getCheckPolicyDataHkPage(Map<String, Object> params) {
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
        // 根据cancelDelayCheck 判断是再次提取数据核对还是 展示数据 提取数据时把所有状态回归为未核对状态
        if (params.get("cancelDelayCheck") != null){
            params.put("checkStatusUpdate", "0");
            //只处理未结算的
            params.put("extractSettleStatus","0");
            checkPolicyDataHkMapper.confirmSettle(params);
            //保存该批次对应的提取的数据
              //批次结果保存（针对汇康数据  为了批次详情展示 记录每次比对结果  因为汇康数据可以重复比对）
             checkPolicyDataHkMapper.insertCheckResultHkBatchNum(params);
        }
        List<Map<String, Object>> list = checkPolicyDataHkMapper.getCheckPolicyDataHKList(params);
        long size = checkPolicyDataHkMapper.getCheckPolicyDataHKListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    /*对比后 和批次挂钩的汇康数据*/
     @Override
    public PageModel getCheckPolicyDataHkForBatchPage(Map<String, Object> params) {
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

        List<Map<String, Object>> list = checkPolicyDataHkMapper.getCheckPolicyDataHkForBatchList(params);
        long size = checkPolicyDataHkMapper.getCheckPolicyDataHkForBatchListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public List<Map<String, Object>> getCheckPolicyDataHkForBatchAll(Map<String, Object> paras) {
        //数据权限相关
        String myDeptNo = "";
        if(paras.get("myDeptNo") != null){
            myDeptNo = String.valueOf(paras.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        paras.put("myAllOrgIds", myAllOrgIds);

        List<Map<String, Object>> list = checkPolicyDataHkMapper.getCheckPolicyDataHkForBatchAll(paras);
        return list;
    }

    @Override
    public List<Map<String, Object>> getCheckPolicyDataHkByCondition(Map<String, Object> paras) {
       return checkPolicyDataHkMapper.getCheckPolicyDataHkByCondition(paras);
    }

    @Override
    public void updateImportList(Map<String, Object> p) {
          Object checkPolicy= p.get("checkPolicy_string");
       JSONArray objects = JSONObject.parseArray(checkPolicy.toString());
       List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
       lists.forEach(list -> {
           logger.info("修改汇康对账数据集：");
          int successPipSize = checkPolicyDataHkMapper.batchUpdateCheckPolicyHK(list);
            logger.info("修改汇康对账数据集成功条数："+successPipSize);
       });
    }



    @Override
    public void insertImportList(Map<String, Object> p) {
         Object checkPolicy= p.get("checkPolicy_string");
       JSONArray objects = JSONObject.parseArray(checkPolicy.toString());
       List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
       lists.forEach(list -> {
           logger.info("插入汇康对账数据集：");
          int successPipSize = checkPolicyDataHkMapper.batchCheckPolicyHK(list);
            logger.info("插入汇康对账数据集成功条数："+successPipSize);
       });
    }

    @Override
    public void updateDataHK(Map<String, Object> paras) {
        checkPolicyDataHkMapper.updateDataHK(paras);
    }

    @Override
    public Map<String, BigDecimal> getTotalCost(Map<String, Object> paras) {
         //数据权限相关
        String myDeptNo = "";
        if(paras.get("myDeptNo") != null){
            myDeptNo = String.valueOf(paras.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        paras.put("myAllOrgIds", myAllOrgIds);
        return checkPolicyDataHkMapper.getTotalCost(paras);
    }

    @Override
    public void confirmSettle(Map<String, Object> paras) {
         //数据权限相关
        String myDeptNo = "";
        if(paras.get("myDeptNo") != null){
            myDeptNo = String.valueOf(paras.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        paras.put("myAllOrgIds", myAllOrgIds);
        paras.put("settleStatusUpdate","1");
        checkPolicyDataHkMapper.confirmSettle(paras);
    }


}
