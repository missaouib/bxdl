package com.hzcf.core.service;

import com.hzcf.core.mapper.ReportFormMapper;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.util.PageModel;
import com.hzcf.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportFormService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReportFormMapper reportFormMapper;

    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;

    public PageModel getSettleRequireIndexData(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex",model.getPageSize());

        //数据权限相关
        String myAllOrgIds = null;
        if(StringUtil.isNotBlank((String) params.get("salesOrgId"))) {
            myAllOrgIds = (String) params.get("salesOrgId");
        } else {
            String myDeptNo = String.valueOf(params.get("myDeptNo"));
            myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        }

        params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String, Object>> list = reportFormMapper.getSettleRequireIndexData(params);
        long size = reportFormMapper.getSettleRequireIndexDataSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }
}
