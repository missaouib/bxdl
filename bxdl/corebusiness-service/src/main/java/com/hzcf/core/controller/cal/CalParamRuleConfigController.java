package com.hzcf.core.controller.cal;


import com.hzcf.core.service.cal.CalParamRuleConfigService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalParamRuleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 多套基本法参数规则配置Controller
 */
@RestController
@RequestMapping("/calParamRuleConfig")
public class CalParamRuleConfigController {

    @Autowired
    CalParamRuleConfigService calParamRuleConfigService;

    /**
     * 分页查询参数规则配置
     * @param params
     * @return
     */
    @RequestMapping("/getListByPage")
    public PageModel getListByPage(@RequestParam Map<String, Object> params) {

        return calParamRuleConfigService.getListByPage(params);
    }

    /**
     * 通过条件获取对应机构的基本法参数规则
     * @param params
     * @return
     */
    @RequestMapping("/getCalParamRuleList")
    public List<CalParamRuleConfig> getCalParamRuleList(@RequestParam Map<String, Object> params) {

        return calParamRuleConfigService.getCalParamRuleList(params);
    }

    @RequestMapping("/saveCalParamRuleConfig")
    public Integer saveCalParamRuleConfig(@RequestBody CalParamRuleConfig calParamRuleConfig) {

        return calParamRuleConfigService.saveCalParamRuleConfig(calParamRuleConfig);
    }

    @RequestMapping("/getCalParamRuleCountByCondition")
    public Long getCalParamRuleCountByCondition(@RequestParam Map<String, Object> params) {

        return calParamRuleConfigService.getCalParamRuleCountByCondition(params);
    }

    /**
     * 通过主键查询参数规则
     * @param id
     * @return
     */
    @RequestMapping("/getCalParamRuleById")
    public CalParamRuleConfig getCalParamRuleById(Long  id) {

        return calParamRuleConfigService.getCalParamRuleById(id);
    }

}
