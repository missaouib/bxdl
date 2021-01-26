package com.hzcf.core.service.impl.financialCheck;

import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.mapper.financialCheck.CheckPolicyBatchMapper;
import com.hzcf.core.service.financialCheck.CheckPolicyBatchService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.financialCheck.CheckPolicyBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/10.
 */
@Service
public class CheckPolicyBatchServiceImpl implements CheckPolicyBatchService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CheckPolicyBatchMapper checkPolicyBatchMapper;
    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;
    @Override
    public PageModel getCheckPolicyBatchPage(Map<String, Object> params) {
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

        List<Map<String, Object>> list = checkPolicyBatchMapper.getCheckPolicyBatchList(params);
        long size = checkPolicyBatchMapper.getCheckPolicyBatchListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public void addCheckPolicyBatch(CheckPolicyBatch checkPolicyBatch) {
        checkPolicyBatchMapper.insert(checkPolicyBatch);
    }

    @Override
    public List<CheckPolicyBatch> getCheckPolicyBatchByCondition(Map<String, Object> params) {
        return checkPolicyBatchMapper.getCheckPolicyBatchByCondition(params);
    }
}
