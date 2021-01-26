package com.hzcf.plantform.feign.account;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: zxr
 * @Date: 2018/11/6 9:48
 */
@FeignClient(name = "account-center-microservice", fallback = FeignDisableHystrixConfiguration.class)
public interface IntegralAccountFeignClient {

    /**
     * 查询积分账户
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/integralAccount/getIntegralAccountList", method = RequestMethod.POST)
    public PageModel getIntegralAccountList(@RequestParam Map<String, Object> map);


    /**
     * 导出积分账户
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/integralAccount/exportIntegralAccountList", method = RequestMethod.POST)
    public List<Map<String, Object>> exportIntegralAccountList(@RequestParam Map<String, Object> map);


    /**
     * 查询积分账户明细
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/integralAccount/getIntegralAccountDetailList", method = RequestMethod.POST)
    public PageModel getIntegralAccountDetailList(@RequestParam Map<String, Object> map);

    /**
     * 导出积分账户明细
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/integralAccount/exportIntegralAccountDetailList", method = RequestMethod.POST)
    public List<Map<String, Object>> exportIntegralAccountDetailList(@RequestParam Map<String, Object> map);


}
