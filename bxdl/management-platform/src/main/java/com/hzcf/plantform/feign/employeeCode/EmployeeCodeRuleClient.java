package com.hzcf.plantform.feign.employeeCode;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.EmployeeCodeRule;
import com.hzcf.pojo.basic.EmployeeCodeRulePojo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service", configuration = FeignDisableHystrixConfiguration.class)
public interface EmployeeCodeRuleClient {

    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.POST)
    void saveEmployeeCodeRule(@RequestBody EmployeeCodeRule employeeCodeRule);

    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.PUT)
    void updateEmployeeCodeRule(@RequestBody EmployeeCodeRule employeeCodeRule);

    @RequestMapping(value = "/employeeCodeRule/page", method = RequestMethod.GET)
    PageModel findEmployeeCodeRulePage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/employeeCodeRules", method = RequestMethod.GET)
    List<EmployeeCodeRulePojo> findEmployeeCodeRuleList(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/empCodeRule", method = RequestMethod.GET)
    EmployeeCodeRulePojo findEmployeeCodeRule(@RequestParam("mapId") String mapId, @RequestParam("type") byte type);

    @RequestMapping(value = "/employeeCodeRules/exist", method = RequestMethod.GET)
    boolean findExistEmployeeCodeRuleList(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.GET)
    EmployeeCodeRulePojo findEmployeeCodeRule(@RequestParam("ruleId") int id);
}
