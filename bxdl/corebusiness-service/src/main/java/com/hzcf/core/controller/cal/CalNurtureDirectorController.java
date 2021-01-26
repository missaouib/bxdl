package com.hzcf.core.controller.cal;


import com.hzcf.core.service.cal.CalNurtureDirectorService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalNurtureDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 处长/经理：育成奖金参数表Controller
 */
@RestController
@RequestMapping("/cal_nurture_director")
public class CalNurtureDirectorController {

    @Autowired
    CalNurtureDirectorService calNurtureDirectorService;

    @RequestMapping("/queryNurtureDirector")
    public List<CalNurtureDirector> queryNurtureDirector(@RequestParam Map<String, Object> params){
        return calNurtureDirectorService.getCalNurtureDirectorByOrgId(params);
    }

    @RequestMapping("/update_nurture_director")
    @ResponseBody
    public void updateCalNurtureDirector(@RequestBody CalNurtureDirector params){
        calNurtureDirectorService.updateCalNurtureDirector(params);
    }
    @RequestMapping("/add_nurture_director")
    @ResponseBody
    public void addCalNurtureDirector(@RequestBody CalNurtureDirector params){
        calNurtureDirectorService.addCalNurtureDirector(params);
    }
    @RequestMapping("/getCalHisNurtureDirector")
    @ResponseBody
     public PageModel getCalHisNurtureDirectorPage(@RequestParam Map<String, Object> params) {
        params.put("pageNo", Integer.valueOf((String) params.get("pageNo")));
        params.put("pageSize", Integer.valueOf((String) params.get("pageSize")));
        return calNurtureDirectorService.getCalHisNurtureDirectorPage(params);
    }
}
