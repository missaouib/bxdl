package com.hzcf.core.controller.cal;

import com.hzcf.core.service.cal.CalParamRuleConfigService;
import com.hzcf.core.service.cal.CalParamVersionSevice;
import com.hzcf.core.service.cal.CalPromoteSerivce;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalParamVersion;
import com.hzcf.pojo.cal.CalPromote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: liuweichen
 * @Date: 2020/10/21/14:01
 * @Description:
 */
@RestController
@RequestMapping("/calExhibitionAllowance")
public class CalExhibitionAllowanceController {
    @Autowired
    CalPromoteSerivce calPromoteSerivce;
    @Autowired
    CalParamVersionSevice calParamVersionSevice;

    @RequestMapping("/getCalPromoteList")
    List<CalPromote> getCalPromoteList(@RequestParam Map<String, Object> params){
        return calPromoteSerivce.getCalPromoteByOrgId(params);
    }

    @RequestMapping("/getExCalParamVersion")
    List<CalParamVersion> getExCalParamVersion(@RequestParam Map<String, Object> params){
        return calParamVersionSevice.getCalParanVersion(params);
    }

    @RequestMapping("/insertIgnoreNull")
    int insertIgnoreNull(@RequestBody CalPromote calPromote){
        int size = calPromoteSerivce.insertIgnoreNull(calPromote);
        return size;
    }

    @RequestMapping("/updateIgnoreNull")
    int updateIgnoreNull(@RequestBody CalPromote calPromote){
        int num = calPromoteSerivce.updateIgnoreNull(calPromote);
        return num;
    }

    @RequestMapping("/getCalHisPromotePage")
    @ResponseBody
     public PageModel getCalHisPromotePage(@RequestParam Map<String, Object> params) {
        params.put("pageNo", Integer.valueOf((String) params.get("pageNo")));
        params.put("pageSize", Integer.valueOf((String) params.get("pageSize")));
        return calPromoteSerivce.getCalHisPromotePage(params);
    }
}
