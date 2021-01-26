package com.hzcf.core.controller;

import com.hzcf.core.service.ReportFormService;
import com.hzcf.core.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 报表Controller
 */
@RestController
@RequestMapping("/reportForm")
public class ReportFormController {

    @Autowired
    ReportFormService reportFormService;

    /**
     * 结算需求指标
     */
    @RequestMapping(value = "/getSettleRequireIndexData")
    @ResponseBody
    public PageModel getSettleRequireIndexData(@RequestBody Map<String, Object> params) {
        return reportFormService.getSettleRequireIndexData(params);
    }
}
