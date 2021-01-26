package com.hzcf.plantform.feign.cal;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.cal.CalNurtureDirector;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface CalNurtureDirectorClient {

    /**
     *异步获取处长/经理参数表，返回前台信息用来展示
     * */
    @RequestMapping(value = "/cal_nurture_director/queryNurtureDirector",method = RequestMethod.POST)
    List<CalNurtureDirector> queryNurtureDirector(@RequestParam Map<String, Object> params);
    /**
     * 保存处长/经理参数表
     * */
    @RequestMapping(value = "/cal_nurture_director/update_nurture_director",method = RequestMethod.POST)
    void updateNurtureDirector(@RequestBody CalNurtureDirector params);
    /**
     * 新增处长/经理参数表
     * */
    @RequestMapping(value = "/cal_nurture_director/add_nurture_director",method = RequestMethod.POST)
    void addNurtureDirector(@RequestBody CalNurtureDirector params);

    @RequestMapping(value = "/cal_nurture_director/getCalHisNurtureDirector")
    PageModel getCalHisNurtureDirector(@RequestParam Map<String, Object> params);
}
