package com.hzcf.plantform.feign.cal;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.cal.CalDirectlyUnderDept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface CalDirectlyUnderDeptClient {

    //根据条件查询获直辖部管理津贴配置
    @RequestMapping(value = "/calDept/getDeptList",method = RequestMethod.GET)
    List<CalDirectlyUnderDept> getDeptList(@RequestParam Map<String, Object> params);
    //修改直辖部管理津贴状态
    @RequestMapping(value = "/calDept/updateDeptState",method = RequestMethod.GET)
    int updateDeptState(@RequestBody CalDirectlyUnderDept calDirectlyUnderDept);
    // 新增直辖部管理津贴配置
    @RequestMapping(value = "/calDept/insertIgnoreNull",method = RequestMethod.GET)
    int insertIgnoreNull(@RequestBody CalDirectlyUnderDept calDirectlyUnderDept);

    @RequestMapping(value = "/calDept/getCalHisDeptPage",method = RequestMethod.GET)
    PageModel getCalHisDeptPage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/calDept/getFirstCalHisDeptByVersionId",method = RequestMethod.GET)
    List<CalDirectlyUnderDept> getFirstCalHisDeptByVersionId(@RequestParam Map<String, Object> params);
}
