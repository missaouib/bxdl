package com.hzcf.plantform.feign.cal;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.cal.CalHisIncreaseStaff;
import com.hzcf.pojo.cal.CalIncreaseStaff;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface CalIncreaseStaffClient {
    //根据条件查询获取增员津贴配置
    @RequestMapping(value = "/calIncreaseStaff/getCalIncreaseStaffList",method = RequestMethod.GET)
    List<CalIncreaseStaff> getCalIncreaseStaffList(@RequestParam Map<String, Object> params);    //根据条件查询获取增员津贴配置

    @RequestMapping(value = "/calIncreaseStaff/updateIncreaseState",method = RequestMethod.GET)
    int updateIncreaseState(@RequestBody CalIncreaseStaff calIncreaseStaff);

    @RequestMapping(value = "/calIncreaseStaff/insertIgnoreNull",method = RequestMethod.GET)
    int insertIgnoreNull(@RequestBody CalIncreaseStaff calIncreaseStaff);

    @RequestMapping(value = "/calIncreaseStaff/getCalHisIncreasePage",method = RequestMethod.GET)
    PageModel getCalHisIncreasePage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/calIncreaseStaff/getFirstCalHisIncreaseByVersionId",method = RequestMethod.GET)
    List<CalHisIncreaseStaff> getFirstCalHisIncreaseByVersionId(@RequestParam Map<String, Object> params);
}
