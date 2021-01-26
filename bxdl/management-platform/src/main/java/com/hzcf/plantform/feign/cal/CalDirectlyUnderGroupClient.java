package com.hzcf.plantform.feign.cal;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.cal.CalDirectlyUnderGroup;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: liuweichen
 * @Date: 2020/10/28/14:11
 * @Description:
 */
@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface CalDirectlyUnderGroupClient {

    @RequestMapping(value = "/calDirectlyUnderGroup/getCalDirectlyUnderGroup",method = RequestMethod.GET)
    List<CalDirectlyUnderGroup> getCalDirectlyUnderGroup(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/calDirectlyUnderGroup/insertIgnoreNull",method = RequestMethod.GET)
    int insertIgnoreNull(CalDirectlyUnderGroup underGroupAdd);

    @RequestMapping(value = "/calDirectlyUnderGroup/updateIncreaseState",method = RequestMethod.GET)
    int updateIncreaseState(CalDirectlyUnderGroup updateUnderGroup);

    @RequestMapping(value = "/calDirectlyUnderGroup/getCalHisDirectlyUnderGroupPage",method = RequestMethod.GET)
    PageModel getCalDirectlyUnderGroupPage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/calDirectlyUnderGroup/getFirstCalDirectlyUnderGroupByVersionId",method = RequestMethod.GET)
    List<CalDirectlyUnderGroup> getFirstCalDirectlyUnderGroupByVersionId(@RequestParam Map<String, Object> params);
}
