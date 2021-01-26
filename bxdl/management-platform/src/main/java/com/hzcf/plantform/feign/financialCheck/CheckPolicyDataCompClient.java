package com.hzcf.plantform.feign.financialCheck;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface CheckPolicyDataCompClient {
   /**
     * 查询列表
     */
    @PostMapping(value = "/check_policy_data_comp/getCheckPolicyDataCompPage")
    PageModel getCheckPolicyDataCompPage(Map<String, Object> params);

    /**
     * 导入对账数据
     * @param p
     * @return
     */
    @PostMapping(value = "/check_policy_data_comp/insertImportList")
    Map<String,Object> insertImportList(Map<String, Object> p);

    /*开始比对*/
    @PostMapping(value = "/check_policy_data_comp/beginComparison")
    Map<String, Object> beginComparison(Map<String, Object> paras);

    /*根据条件查询比对结果 不分页*/
    @PostMapping(value = "/check_policy_data_comp/getCheckPolicyDataCompAll")
    List<Map<String,Object>> getCheckPolicyDataCompAll(Map<String, Object> paras);

 /**
  * 获取各个匹对结果条数
  * @param paras
  * @return
  */
 @PostMapping(value = "/check_policy_data_comp/getResultNumber")
    Map<String,Object> getResultNumber(Map<String, Object> paras);
}
