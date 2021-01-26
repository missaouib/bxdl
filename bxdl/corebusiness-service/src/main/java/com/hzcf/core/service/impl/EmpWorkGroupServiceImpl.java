package com.hzcf.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.core.mapper.EmpWorkGroupMapper;
import com.hzcf.core.service.EmpWorkGroupService;
import com.hzcf.pojo.empWorkGroup.EmpWorkGroupPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class EmpWorkGroupServiceImpl implements EmpWorkGroupService {
    @Autowired
    private EmpWorkGroupMapper empWorkGroupMapper;
    @Override
    public List<Map<String, Object>> findDepartmentGroupList(Map<String, Object> params) {
        return empWorkGroupMapper.findDepartmentGroup(params);
    }

    @Override
    public List<Map<String, Object>> findDeptGroupCheckList(Map<String, Object> params) {
        return empWorkGroupMapper.findDeptGroupCheckList(params);
    }

    @Override
    public void insertWorkGroups(String workGroupsStr,String employeeId) {
        List<EmpWorkGroupPojo> empWorkGroupPojos = JSONObject.parseArray(workGroupsStr, EmpWorkGroupPojo.class);
        empWorkGroupMapper.deleteWorkGroupInfoByEmpId(employeeId);//删除原先工作组
        empWorkGroupMapper.insertSelective(empWorkGroupPojos);
    }
}
