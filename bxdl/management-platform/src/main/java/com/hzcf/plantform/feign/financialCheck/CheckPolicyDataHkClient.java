package com.hzcf.plantform.feign.financialCheck;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.financialCheck.CheckPolicyDataHk;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface CheckPolicyDataHkClient {
   /**
     * 查询列表
     */
    @PostMapping(value = "/check_policy_data_hk/getCheckPolicyDataHkPage")
    PageModel getCheckPolicyDataHkPage(Map<String, Object> params);

    /**
     * 导入对账数据
     * @param p
     * @return
     */
    @PostMapping(value = "/check_policy_data_hk/insertImportList")
    Map<String,Object> insertImportList(Map<String, Object> p);

    @PostMapping(value = "/check_policy_data_hk/updateDataHK")
    void updateDataHK(Map<String, Object> paras);

    @PostMapping(value = "/check_policy_data_hk/getTotalCost")
    Map<String,BigDecimal> getTotalCost(Map<String, Object> paras);

    @PostMapping(value = "/check_policy_data_hk/confirmSettle")
    void confirmSettle(Map<String, Object> paras);

    /*分页*/
     @PostMapping(value = "/check_policy_data_hk/getCheckPolicyDataHkForBatchPage")
    PageModel getCheckPolicyDataHkForBatchPage(Map<String, Object> paras);
     /*不分页*/
      @PostMapping(value = "/check_policy_data_hk/getCheckPolicyDataHkForBatchAll")
    List<Map<String,Object>> getCheckPolicyDataHkForBatchAll(Map<String, Object> paras);

    @PostMapping(value = "/check_policy_data_hk/getCheckPolicyDataHkByCondition")
    List<Map<String,Object>> getCheckPolicyDataHkByCondition(Map<String, Object> paras);

     @PostMapping(value = "/check_policy_data_hk/updateImportList")
    Map<String,Object> updateImportList(Map<String, Object> p);
}
