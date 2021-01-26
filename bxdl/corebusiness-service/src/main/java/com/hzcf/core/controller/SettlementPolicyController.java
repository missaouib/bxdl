package com.hzcf.core.controller;

import com.hzcf.core.service.SettlementPolicyService;
import com.hzcf.core.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 结算保单Controller
 */

@SuppressWarnings("ALL")
@Controller
@RequestMapping("/settlementPolicy")
public class SettlementPolicyController {

    @Autowired
    SettlementPolicyService settlementPolicyService;

    /**
     * 分页查询 结算保单 list
     */
    @RequestMapping(value = "/searchSettlementPolicyList", method = RequestMethod.POST)
    @ResponseBody
    public PageModel searchSettlementPolicyList(@RequestParam Map<String, Object> params) {
        return settlementPolicyService.searchSettlementPolicyList(params);
    }

    @RequestMapping(value = "/submitAuditResult", method = RequestMethod.POST)
    @ResponseBody
    public int submitAuditResult(@RequestParam Map<String, Object> params) {
        return settlementPolicyService.submitAuditResult(params);
    }
}
