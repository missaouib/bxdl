package com.hzcf.plantform.controller.cal;

import com.alibaba.fastjson.JSON;
import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.plantform.feign.SystemDictFeignClient;
import com.hzcf.plantform.feign.cal.CalParamRuleConfigClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.basic.SystemDict;
import com.hzcf.pojo.cal.CalParamRuleConfig;
import com.hzcf.util.StringUtil;
import com.netflix.discovery.converters.Auto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 多套基本法参数规则配置Controller
 */
@Controller
@RequestMapping("/calParamRuleConfig")
public class CalParamRuleConfigController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CalParamRuleConfigClient calParamRuleConfigClient;
    @Autowired
    SystemDictFeignClient systemDictFeignClient;
    @Autowired
    SalesOrgInfoClient salesOrgInfoClient;

    @RequiresPermissions("empCodeManager:list")
    @RequestMapping("/toListPage")
    public String toListPage(Model model) {

        List<SystemDict> calParamTypeDictlist = systemDictFeignClient.findDictByDictTypeRetEntity(CalParamRuleEnum.DICT_PARAM.getCode());
        List<SystemDict> calRuleTypeDictlist = systemDictFeignClient.findDictByDictTypeRetEntity(CalParamRuleEnum.DICT_RULE.getCode());
        List<Map<String, Object>> saleOrgList = salesOrgInfoClient.getTopAndSFOrgsList();

        Map<String, Object> paramTypeMap = calParamTypeDictlist.stream().collect(
                Collectors.toMap(SystemDict::getDictCode, SystemDict -> SystemDict));

        Map<String, Object> ruleTypeMap = calRuleTypeDictlist.stream().collect(
                Collectors.toMap(SystemDict::getDictCode, SystemDict -> SystemDict));

        model.addAttribute("PARAMTYPE", JSON.toJSON(paramTypeMap));
        model.addAttribute("RULETYPE", JSON.toJSON(ruleTypeMap));
        model.addAttribute("calParamTypeDictlist", calParamTypeDictlist);
        model.addAttribute("saleOrgList", saleOrgList);

        return "cal/calParmRuleConfig/list";
    }

    @RequestMapping("/getListByPage")
    @ResponseBody
    public DataMsg getListByPage(HttpServletRequest request, DataMsg dataMsg,
                                 @RequestParam(value = "orgId", required = false) Integer orgId,
                                 @RequestParam(value = "paramType", required = false) String paramType) {
        Map<String, Object> params = new HashMap<>();
        String pageNo = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        if (StringUtil.isNotBlank(pageNo)) {
            params.put("pageNo", pageNo);
        } else {
            params.put("pageNo", 0);
        }

        if (StringUtil.isNotBlank(pageSize)) {
            params.put("pageSize", pageSize);
        } else {
            params.put("pageSize", 10);
        }
        if (orgId != null) {
            params.put("orgId", orgId);
        }
        if (StringUtil.isNotBlank(paramType)) {
            params.put("paramType", paramType);
        }

        try {
            PageModel pageModel = calParamRuleConfigClient.getListByPage(params);
            dataMsg.setRows(pageModel.getList());
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("查询基本机构参数规则错误", e);
        }
        return dataMsg;
    }

    /**
     * 查询参数规则配置数据
     *
     * @param request
     * @param orgId
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getCalParamRuleList")
    @ResponseBody
    public DataMsg getCalParamRuleList(HttpServletRequest request,
                                       @RequestParam("orgId") String orgId, DataMsg dataMsg) {

        Map<String, Object> params = new HashMap<>();
        params.put("orgId", orgId);
        List<CalParamRuleConfig> calParamRuleList = null;
        try {
            calParamRuleList = calParamRuleConfigClient.getCalParamRuleList(params);
            dataMsg.setRows(calParamRuleList);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("配置基本法获取参数规则报错", e);
        }
        return dataMsg;

    }

    /**
     * 保存基本法规则配置
     *
     * @param request
     * @param calParamRule
     * @param dataMsg
     * @param ruleType0    增员
     * @param ruleType1    展业
     * @param ruleType4    直辖组
     * @param ruleType5    直辖部
     * @return
     */
    @RequestMapping("/saveCalParamRuleConfig")
    @ResponseBody
    public DataMsg saveCalParamRuleConfig(HttpServletRequest request,
                                          CalParamRuleConfig calParamRule, DataMsg dataMsg,
                                          String ruleType0, String ruleType1, String ruleType4,
                                          String ruleType5) {

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> resMap = new HashMap<>();
        dataMsg.setMessageCode("200");

        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        Integer employeeId = employee.getEmployeeId();

        String paramType = calParamRule.getParamType();
        if (CalParamRuleEnum.PARAM_ZY.getCode().equals(calParamRule.getParamType())) { //展业
            calParamRule.setRuleType(ruleType0);
        }
        if (CalParamRuleEnum.PARAM_IN.getCode().equals(calParamRule.getParamType())) { //增员
            calParamRule.setRuleType(ruleType1);
        }
        if (CalParamRuleEnum.PARAM_D_GROUP.getCode().equals(calParamRule.getParamType())) { //直辖组
            calParamRule.setRuleType(ruleType4);
        }
        if (CalParamRuleEnum.PARAM_D_DEPT.getCode().equals(calParamRule.getParamType())) { //直辖部
            calParamRule.setRuleType(ruleType5);
        }
        Long id = calParamRule.getId();
        if (id == null || id == 0) {

            params.put("orgId", calParamRule.getOrgId());
            params.put("paramType", calParamRule.getParamType());
            Long count = calParamRuleConfigClient.getCalParamRuleCountByCondition(params);
            if (count >= 1) {
                dataMsg.setMessageCode("500");
                dataMsg.setErrorMsg("已对该机构配置此参数");
                return dataMsg;
            }
            calParamRule.setCreateBy(Long.valueOf(employeeId));
            calParamRule.setCreateTime(new Date());
            calParamRule.setIsNormal("1");
        } else {
            calParamRule.setUpdateBy(Long.valueOf(employeeId));
            calParamRule.setUpdateTime(new Date());
        }
        try {
            logger.info("保存参数规则配置", JSON.toJSONString(calParamRule));
            Integer flag = calParamRuleConfigClient.saveCalParamRuleConfig(calParamRule);
            if (flag <= 0) {
                dataMsg.setMessageCode("500");
                dataMsg.setErrorMsg("保存失败");
                return dataMsg;
            }

        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            dataMsg.setErrorMsg("系统错误");
            logger.error("保存参数规则配置", e);
        }
        return dataMsg;

    }

    /**
     * 通过主键获取参数规则配置
     * @param id
     * @return
     */
    @RequestMapping("/getCalParamRuleById")
    @ResponseBody
    public Map<String, Object> getCalParamRuleById(Long  id) {
        Map<String, Object> resMap = new HashMap<>();
        CalParamRuleConfig calParamRuleConfig = null;
        try{
            calParamRuleConfig = calParamRuleConfigClient.getCalParamRuleById(id);
            resMap.put("code", "200");
            resMap.put("data", calParamRuleConfig);
        } catch (Exception e) {
            resMap.put("code", "400");
            resMap.put("errorMsg", "系统错误");
            logger.error("保存参数规则配置", e);
        }
        return resMap;
    }
}
