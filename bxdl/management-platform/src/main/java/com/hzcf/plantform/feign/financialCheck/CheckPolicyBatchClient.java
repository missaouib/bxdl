package com.hzcf.plantform.feign.financialCheck;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.financialCheck.CheckPolicyBatch;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface CheckPolicyBatchClient {
   /**
     * 查询列表
     */
    @PostMapping(value = "/check_policy_batch/getCheckPolicyBatchPage")
    PageModel getCheckPolicyBatchPage(Map<String, Object> params);

    /**
     * 保存批次
     * @param checkPolicyBatch
     */
    @PostMapping(value = "/check_policy_batch/addCheckPolicyBatch")
    void addCheckPolicyBatch(CheckPolicyBatch checkPolicyBatch);

    /**
     * 根据条件查询
     * @param map
     * @return
     */
    @PostMapping(value = "/check_policy_batch/getCheckPolicyBatchByCondition")
    List<CheckPolicyBatch> getCheckPolicyBatchByCondition(Map<String, Object> map);
}
