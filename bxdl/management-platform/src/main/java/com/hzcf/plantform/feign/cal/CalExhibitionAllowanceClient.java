package com.hzcf.plantform.feign.cal;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.cal.CalParamVersion;
import com.hzcf.pojo.cal.CalPromote;
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
 * @Date: 2020/10/21/13:34
 * @Description:
 */

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface CalExhibitionAllowanceClient {

    @RequestMapping(value = "/calExhibitionAllowance/getCalPromoteList",method = RequestMethod.GET)
    List<CalPromote> getCalPromoteList(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/calExhibitionAllowance/getExCalParamVersion",method = RequestMethod.GET)
    List<CalParamVersion> getExCalParamVersion(Map<String, Object> params);

    @RequestMapping(value ="/calExhibitionAllowance/insertIgnoreNull",method = RequestMethod.GET)
    int insertIgnoreNull(CalPromote addtoCalPromote);

    @RequestMapping(value ="/calExhibitionAllowance/updateIgnoreNull",method = RequestMethod.GET)
    int updateIgnoreNull(CalPromote updateCalPromote);

    @RequestMapping(value = "/calExhibitionAllowance/getCalHisPromotePage",method = RequestMethod.GET)
    PageModel getCalHisPromotePage(@RequestParam Map<String, Object> params);
}
