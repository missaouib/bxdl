package com.hzcf.core.service;

import java.util.List;
import java.util.Map;

public interface EmpWorkGroupService {
    List<Map<String, Object>> findDepartmentGroupList(Map<String, Object> params);

    List<Map<String, Object>> findDeptGroupCheckList(Map<String, Object> params);

    void insertWorkGroups(String workGroupsStr,String employeeId);

}
