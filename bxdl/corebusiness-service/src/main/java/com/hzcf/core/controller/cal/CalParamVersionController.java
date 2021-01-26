package com.hzcf.core.controller.cal;

import com.hzcf.core.service.cal.CalParamVersionSevice;
import com.hzcf.core.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cal_param_version")
public class CalParamVersionController {

    @Autowired
    CalParamVersionSevice calParamVersionSevice;

    /**
     * 版本查询弹出页面，后端分页
     * （！！待定！！）
     * */
    @RequestMapping("/getCalParamVersionPage")
    @ResponseBody
    public PageModel getCalParamVersionPage(@RequestParam Map<String, Object> params) {
        params.put("pageNo", Integer.valueOf((String) params.get("pageNo")));
        params.put("pageSize", Integer.valueOf((String) params.get("pageSize")));
        return calParamVersionSevice.getCalParamVersionPage(params);
    }

    /**
     * 保存历史版本记录（总）
     * */
    @RequestMapping("/saveCalParamVersion")
    @ResponseBody
    public Map<String, Object> saveCalParamVersion(@RequestParam Map<String, Object> params){
        return calParamVersionSevice.saveCalParamVersion(params);
    }










}
