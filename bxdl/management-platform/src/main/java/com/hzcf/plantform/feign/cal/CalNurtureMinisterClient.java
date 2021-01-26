package com.hzcf.plantform.feign.cal;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.cal.CalNurtureMinister;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface CalNurtureMinisterClient {

    /**
     *异步获取部长育成奖金参数表，返回前台信息用来展示
     * */
    @RequestMapping(value = "/cal_minister_nurturing/queryNurtureMinister",method = RequestMethod.POST)
    List<CalNurtureMinister> queryNurtureMinister(@RequestParam Map<String, Object> params);
    /**
     * 保存部长育成奖金参数表
     * */
    @RequestMapping(value = "/cal_minister_nurturing/update_minister_nurturing",method = RequestMethod.POST)
    void updateMinisterNurturingBonus(@RequestBody CalNurtureMinister params);
    /**
     * 新增部长育成奖金参数表
     * */
    @RequestMapping(value = "/cal_minister_nurturing/add_minister_nurturing",method = RequestMethod.POST)
    void addMinisterNurturingBonus(@RequestBody CalNurtureMinister params);
    /**
     * 修改是否默认基本法
     * */
    @RequestMapping(value = "/salesOrgInfo/updateIsDefaultCal",method = RequestMethod.POST)
    void updateSalesOrgInfo(SalesOrgInfo salesOrgInfo);
    /**
     * 查看部长育成奖金参数记录表
     * plantfrom
     * */
    @RequestMapping(value = "/cal_minister_nurturing/getCalHisNurtureMinister",method = RequestMethod.POST)
    PageModel getCalHisNurtureMinister(@RequestParam Map<String, Object> params);
}
