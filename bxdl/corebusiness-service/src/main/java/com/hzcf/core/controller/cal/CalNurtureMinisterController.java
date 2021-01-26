package com.hzcf.core.controller.cal;


import com.hzcf.core.service.cal.CalNurtureMinisterService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalNurtureMinister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 部长育成奖金参数表Controller
 */
@RestController
@RequestMapping("/cal_minister_nurturing")
public class CalNurtureMinisterController {

    @Autowired
    CalNurtureMinisterService calNurtureMinisterService;

    @RequestMapping("/queryNurtureMinister")
    public List<CalNurtureMinister> queryNurtureMinister(@RequestParam Map<String, Object> params){
        return calNurtureMinisterService.getCalNurtureMinisterByOrgId(params);
    }

    @RequestMapping("/update_minister_nurturing")
    @ResponseBody
    public void updateMinisterNurturingBonus(@RequestBody CalNurtureMinister params){
        calNurtureMinisterService.updateMinisterNurturingBonus(params);
    }
    @RequestMapping("/add_minister_nurturing")
    @ResponseBody
    public void addMinisterNurturingBonus(@RequestBody CalNurtureMinister params){
        calNurtureMinisterService.addMinisterNurturingBonus(params);
    }

    /**
     * 查看部长育成奖金参数记录表
     * corebusiness-service
     */
    @RequestMapping("/getCalHisNurtureMinister")
    @ResponseBody
    public PageModel getCalHisNurtureMinister(@RequestParam Map<String, Object> params){
        params.put("pageNo", Integer.valueOf((String) params.get("pageNo")));
        params.put("pageSize", Integer.valueOf((String) params.get("pageSize")));
        return calNurtureMinisterService.getCalHisNurtureMinister(params);
    }

}
