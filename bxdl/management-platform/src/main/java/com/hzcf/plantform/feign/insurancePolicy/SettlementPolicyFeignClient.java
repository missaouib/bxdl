package com.hzcf.plantform.feign.insurancePolicy;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.Role;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 结算保单客户端
 */
@FeignClient(name = "corebusiness-service", configuration=FeignDisableHystrixConfiguration.class)
public interface SettlementPolicyFeignClient {

	@RequestMapping(value = "/settlementPolicy/searchSettlementPolicyList",method = RequestMethod.POST )
	PageModel searchSettlementPolicyList(@RequestParam Map<String,Object> params);
	@RequestMapping(value = "/settlementPolicy/submitAuditResult",method = RequestMethod.POST )
    int submitAuditResult(@RequestParam Map<String,Object> params);
}
