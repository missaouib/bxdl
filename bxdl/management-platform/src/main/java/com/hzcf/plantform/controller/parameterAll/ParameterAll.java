package com.hzcf.plantform.controller.parameterAll;

import com.alibaba.fastjson.JSON;
import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.plantform.feign.SystemDictFeignClient;
import com.hzcf.plantform.feign.cal.CalParamRuleConfigClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.pojo.basic.SystemDict;
import com.hzcf.pojo.cal.CalParamRuleConfig;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/toParameterPage")
public class ParameterAll {

    @Autowired
    private SalesOrgInfoClient salesOrgInfoClient;
    @Autowired
    CalParamRuleConfigClient calParamRuleConfigClient;
    @Autowired
    SystemDictFeignClient systemDictFeignClient;

    /**
     * 跳转页面
     * 进入基本法参数配置// 因为前台页面tab切换暂时写死了，进入页面时，传值：RULETYPE
     */
    @RequestMapping("to_all_page")
    public String toPage(Model model, HttpServletRequest request) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("salesOrgLevel", "01");
        List<SalesOrgInfo> list = salesOrgInfoClient.findSalesOrgs(paras);
        if (list != null) {
            readTree(list);
        }
        model.addAttribute("salesOrgList", JSON.toJSONString(list));

        // 基本法规则字典
        List<SystemDict> calRuleTypeDictlist = systemDictFeignClient.findDictByDictTypeRetEntity(CalParamRuleEnum.DICT_RULE.getCode());
        Map<String, Object> ruleTypeMap = calRuleTypeDictlist.stream().collect(
                Collectors.toMap(SystemDict::getDictCode, SystemDict -> SystemDict));
        model.addAttribute("RULETYPE", JSON.toJSON(ruleTypeMap));

        return "parameterAll/parameterAll";
    }

    void readTree(List<SalesOrgInfo> lists) {
        for (SalesOrgInfo org : lists) {
            Map<String, Object> paras = new HashMap<>();
            paras.put("parentSalesOrgCode", org.getSalesOrgCode());
            List<SalesOrgInfo> childList = salesOrgInfoClient.findSalesOrgs(paras);
            if (childList != null) {
                org.setOrgChildrens(childList);
                readTree(childList);
            }
        }
    }

    /**
     * 异步获取组织机构信息，以及相关的逻辑判断，用来返回前台信息
     */
    @RequestMapping("/queryOrgInfo")
    @ResponseBody
    public Map<String, Object> queryOrgInfo(String salesOrgId) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> resMap = new HashMap<>();
        try{
            params.put("salesOrgId", salesOrgId);
            SalesOrgInfo sales = salesOrgInfoClient.selectById(params);
            params.put("salesIsDefaultCal",String.valueOf(sales.getIsDefaultCal()));
            resMap.put("sales", JSON.toJSON(params));

            params.put("isDefaultCal", String.valueOf(sales.getIsDefaultCal()));
            params.put("calOrgId", String.valueOf(sales.getCalOrgId()));
            List<SalesOrgInfo> list = salesOrgInfoClient.queryOrgInfo(params);
            params.put("orgId", String.valueOf(sales.getCalOrgId()));

            //查询对应机构的基本法参数规则
            List<CalParamRuleConfig> calParamRuleList = calParamRuleConfigClient.getCalParamRuleList(params);
            Map<String, Object> paramTypeMap = calParamRuleList.stream().collect(
                    Collectors.toMap(CalParamRuleConfig::getParamType, CalParamRuleConfig -> CalParamRuleConfig));
            resMap.put("paramTypeMap", JSON.toJSON(paramTypeMap));
            resMap.put("data", JSON.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
        }
        return resMap;
    }


}
