package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.pojo.insurance.sales.SalerMonthlyRelation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: liuweichen
 * @Date: 2020/05/18/11:27
 * @Description:
 */
@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface SalerMonthlyRelationClient {

    @RequestMapping(value = "/salerMonthlyRelation/insertSalerMonthlyRelation", method = RequestMethod.POST)
    int insertSalerMonthlyRelation(SalerMonthlyRelation salerMonthlyRelation);
}
