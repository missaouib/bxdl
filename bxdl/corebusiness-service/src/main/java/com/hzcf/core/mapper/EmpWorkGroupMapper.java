package com.hzcf.core.mapper;

import com.hzcf.pojo.empWorkGroup.EmpWorkGroupPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpWorkGroupMapper {
    List<Map<String, Object>> findDepartmentGroup(Map<String, Object> params);

    List<Map<String, Object>> findDeptGroupCheckList(Map<String, Object> params);

    void deleteWorkGroupInfoByEmpId(@Param("employeeId") String employeeId);

    void insertSelective(List<EmpWorkGroupPojo> empWorkGroupPojos);
}
