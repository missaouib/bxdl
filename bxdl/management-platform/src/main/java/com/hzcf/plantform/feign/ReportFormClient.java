package com.hzcf.plantform.feign;

import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.financialCheck.CheckPolicyBatch;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface ReportFormClient {
   /**
     * 查询列表
     */
    @PostMapping(value = "/reportForm/getSettleRequireIndexData")
    PageModel getSettleRequireIndexData(Map<String, Object> params);
}
