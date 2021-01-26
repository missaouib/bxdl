package com.hzcf.plantform.feign;

import com.hzcf.pojo.empWorkGroup.EmpWorkGroupPojo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface EmpWorkGroupFeignClient {
    @RequestMapping(value = "/empWorkGroup/listTreeDeptGroup", method = RequestMethod.POST)
    List<Map<String, Object>> findDepartmentGroupList(@RequestParam Map<String,Object> params);

    @RequestMapping(value = "/empWorkGroup/findDeptGroupCheckList", method = RequestMethod.POST)
    List<Map<String, Object>> findDeptGroupCheckList(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/empWorkGroup/insertWorkGroups", method = RequestMethod.POST)
    Map<String,Object> insertWorkGroups(@RequestParam(value="workGroupsStr") String workGroupsStr,@RequestParam(value="employeeId") String employeeId);

}
