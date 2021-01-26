package com.hzcf.plantform.feign.cal;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface CalParamVersionClient {

    /**
     * 版本查询弹出页面，后端分页
     * */
    @RequestMapping(value = "/cal_param_version/getCalParamVersionPage", method = RequestMethod.POST)
    PageModel getCalParamVersionPage(@RequestParam Map<String, Object> params);
    /**
     * 保存历史版本记录（总）
     * */
    @RequestMapping(value = "/cal_param_version/saveCalParamVersion",method = RequestMethod.POST)
    Map<String, Object> saveCalParamVersion(@RequestParam Map<String, Object> params);




}
