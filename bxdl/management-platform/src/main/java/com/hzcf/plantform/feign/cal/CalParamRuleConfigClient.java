package com.hzcf.plantform.feign.cal;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.cal.CalParamRuleConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface CalParamRuleConfigClient {

    @RequestMapping(value = "/calParamRuleConfig/getListByPage",method = RequestMethod.GET)
    public PageModel getListByPage(@RequestParam Map<String,Object> params);

    /**
     * 通过条件获取对应机构的基本法参数规则
     * @param params
     * @return
     */
    @RequestMapping(value = "/calParamRuleConfig/getCalParamRuleList",method = RequestMethod.GET)
    List<CalParamRuleConfig> getCalParamRuleList(@RequestParam Map<String, Object> params);

    /**
     * 保存基本法参数规则配置
     * @param calParamRuleConfig
     * @return
     */
    @RequestMapping(value = "/calParamRuleConfig/saveCalParamRuleConfig",method = RequestMethod.POST)
    Integer saveCalParamRuleConfig(@RequestBody CalParamRuleConfig calParamRuleConfig);

    @RequestMapping(value = "/calParamRuleConfig/getCalParamRuleCountByCondition",method = RequestMethod.GET)
    Long getCalParamRuleCountByCondition(@RequestParam Map<String, Object> params);

    /**
     * 通过主键获取参数规则配置
     * @param id
     * @return
     */
    @RequestMapping(value = "/calParamRuleConfig/getCalParamRuleById",method = RequestMethod.GET)
    CalParamRuleConfig getCalParamRuleById(@RequestParam("id") Long id);
}
