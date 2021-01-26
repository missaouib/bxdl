package com.hzcf.plantform.controller.employeeCode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzcf.plantform.feign.SystemDictFeignClient;
import com.hzcf.plantform.feign.employeeCode.EmployeeCodeRuleClient;
import com.hzcf.plantform.feign.insurance.SalesOrgInfoClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.District;
import com.hzcf.pojo.basic.EmployeeCodeRule;
import com.hzcf.pojo.basic.EmployeeCodeRulePojo;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class EmployeeCodeRuleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeCodeRuleClient employeeCodeRuleClient;
    @Autowired
    private SystemDictFeignClient systemDictFeignClient;
    @Autowired
    private SalesOrgInfoClient salesOrgInfoClient;

    /**
     * <p>跳转编号管理列表页面</p>
     *
     * @return
     */
    // 权限管理;
    @RequiresPermissions("empCodeManager:list")
    @RequestMapping("/employee/code")
    public String toEmployeeCodePage(){
        return "system/employee/code";
    }


    /**
     * <p>机构序号管理</p>
     *
     * @param model
     * @return
     */
    @RequiresPermissions("orgCodeManager:list")//权限管理;
    @RequestMapping("/orgTree/code")
    public String getOrgTree(Model model) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("salesOrgLevel", "01");
        List<SalesOrgInfo> list = salesOrgInfoClient.findSalesOrgs(paras);
        if (list != null) {
            readTree(list);
        }
        model.addAttribute("salesOrgList", JSON.toJSONString(list));
        return "system/employee/orgCode";
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
     * <p>新增序号规则</p>
     *
     * @param employeeCodeRule
     */
    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.POST)
    @ResponseBody
    public DataMsg saveEmployeeCodeRule(EmployeeCodeRule employeeCodeRule) {
        DataMsg msg = new DataMsg();
        try {
            HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(3);
            params.put("code",employeeCodeRule.getCode());
            params.put("mapId",employeeCodeRule.getMapId());
            params.put("type",employeeCodeRule.getType());
            params.put("excludeId",0);
            if(1 == employeeCodeRule.getType()){
                employeeCodeRuleClient.saveEmployeeCodeRule(employeeCodeRule);
                msg.setMessageCode("200");
            }else {
                boolean result = employeeCodeRuleClient.findExistEmployeeCodeRuleList(params);
                if(result){
                    msg.setMessageCode("200001");
                }else {
                    employeeCodeRuleClient.saveEmployeeCodeRule(employeeCodeRule);
                    msg.setMessageCode("200");
                }
            }
        } catch (Exception e) {
            msg.setMessageCode("400");
            logger.info("编号规则[新增]异常"+e);
        }
        return msg;
    }

    /**
     * <p>更新序号规则</p>
     *
     * @param employeeCodeRule
     */
    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.PUT)
    @ResponseBody
    public DataMsg updateEmployeeCodeRule(EmployeeCodeRule employeeCodeRule) {
        DataMsg msg = new DataMsg();
        try {
            HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(4);
            params.put("code",employeeCodeRule.getCode());
            params.put("type",employeeCodeRule.getType());
            params.put("mapId",employeeCodeRule.getMapId());
            params.put("excludeId",employeeCodeRule.getId());
            if(2 == employeeCodeRule.getType()){
                if(null == employeeCodeRule.getId()){
                    employeeCodeRuleClient.saveEmployeeCodeRule(employeeCodeRule);
                    msg.setMessageCode("200");
                }else if(null != employeeCodeRule.getId()){
                    employeeCodeRuleClient.updateEmployeeCodeRule(employeeCodeRule);
                    msg.setMessageCode("200");
                }
            }else {
                boolean result = employeeCodeRuleClient.findExistEmployeeCodeRuleList(params);
                if(result){
                    msg.setMessageCode("200001");
                }else if(null == employeeCodeRule.getId()){
                    employeeCodeRuleClient.saveEmployeeCodeRule(employeeCodeRule);
                    msg.setMessageCode("200");
                }else if(null != employeeCodeRule.getId()){
                    employeeCodeRuleClient.updateEmployeeCodeRule(employeeCodeRule);
                    msg.setMessageCode("200");
                }
            }
        } catch (Exception e) {
            msg.setMessageCode("400");
            logger.info("编号规则[编辑]异常"+e);
        }
        return msg;
    }

    /**
     * <p>分页查询序号规则</p>
     *
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping(value = "/employeeCodeRule/page", method = RequestMethod.GET)
    @ResponseBody
    public DataMsg findEmployeeCodeRulePage(javax.servlet.http.HttpServletRequest request, DataMsg dataMsg) {

        try {
            Map<String, Object> params = new HashMap<>();
            String type = request.getParameter("type");
            if (StringUtils.isNotBlank(type)) {
                params.put("type", Byte.valueOf(type));
            }else {
                params.put("type", Byte.valueOf("1"));
            }

            if("1".equals(type)){
                dataMsg = getProvinceCodes(dataMsg, params);
            }else if("2".equals(type)){
                dataMsg = getOrgCodes(request, dataMsg);
            }
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("编号规则[查询]异常"+e.getMessage());
        }
        return dataMsg;
    }

    private DataMsg getOrgCodes(HttpServletRequest request, DataMsg dataMsg) {
        String salesOrgId = request.getParameter("salesOrgId");
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
        map.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
        map.put("isAdmin", "admin");
        if(StringUtils.isBlank(salesOrgId)){
            map.put("salesOrgLevel", "01");
            PageModel model = salesOrgInfoClient.getSalesOrgInfoList(map);
            if(null != model){
                dataMsg = setOrgCodeRows(dataMsg, model);
            }else {
                dataMsg.setTotal(0L);
                dataMsg.setRows(Collections.emptyList());
            }
        }else {
            map.put("salesOrgId", salesOrgId);
            SalesOrgInfo salesOrgInfo = salesOrgInfoClient.selectById(map);
            Map<String, Object> paras = new HashMap<>();
            paras.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            paras.put("parentSalesOrgCode", salesOrgInfo.getSalesOrgCode());
            paras.put("isAdmin", "admin");
            PageModel model = salesOrgInfoClient.getSalesOrgInfoList(paras);
            dataMsg = setOrgCodeRows(dataMsg, model);
        }
        return dataMsg;
    }

    private DataMsg getProvinceCodes(DataMsg dataMsg, Map<String, Object> params) {
        String json = systemDictFeignClient.findDistrictByParentId("0");
        List<District> districts = JSONObject.parseArray(json, District.class);
        List<EmployeeCodeRulePojo> employeeCodeRulePojoList = Lists.newArrayListWithExpectedSize(districts.size());
        List<EmployeeCodeRulePojo> list = employeeCodeRuleClient.findEmployeeCodeRuleList(params);

        districts.forEach(a->{
            EmployeeCodeRulePojo pojo = new EmployeeCodeRulePojo();
            pojo.setMapName(a.getDistrict());
            pojo.setType(Byte.valueOf("1"));
            pojo.setMapId(a.getSortcode());
            if(!"全国".equals(a.getDistrict())){
                list.forEach(b->{
                    if(a.getSortcode().equals(b.getMapId())){
                        pojo.setCode(b.getCode());
                        pojo.setId(b.getId());
                    }
                });
                if(null == pojo.getCode()){
                    pojo.setCode("");
                }
                employeeCodeRulePojoList.add(pojo);
            }
        });

        dataMsg.setTotal(Long.valueOf(employeeCodeRulePojoList.size()));
        dataMsg.setRows(employeeCodeRulePojoList);
        return dataMsg;
    }

    private DataMsg setOrgCodeRows(DataMsg dataMsg, PageModel model) {
        List<EmployeeCodeRulePojo> employeeCodeRulePojoList = Lists.newArrayListWithExpectedSize(model.getList().size());
        List<Map> list = model.getList();
        for (Map m: list) {
            EmployeeCodeRulePojo pojo = new EmployeeCodeRulePojo();
            pojo.setMapId(m.get("SALES_ORG_ID").toString());
            pojo.setMapName(m.get("SALES_ORG_NAME").toString());
            pojo.setType(Byte.valueOf("2"));
            EmployeeCodeRulePojo employeeCodeRule = employeeCodeRuleClient.findEmployeeCodeRule(m.get("SALES_ORG_ID").toString(), Byte.valueOf("2"));
            if(null != employeeCodeRule){
                pojo.setId(employeeCodeRule.getId());
                pojo.setCode(employeeCodeRule.getCode());
            }else {
                pojo.setCode("");
            }
            employeeCodeRulePojoList.add(pojo);
        }
        dataMsg.setTotal(model.getTotalRecords());
        dataMsg.setRows(employeeCodeRulePojoList);
        return dataMsg;
    }

    private Map mapSetDistrictName(Map map){
        String json = systemDictFeignClient.findDistrictByParentId("0");
        List<District> districts = JSONObject.parseArray(json, District.class);
        districts.forEach(a->{
            if("1".equals(map.get("type").toString()) && a.getSortcode().equals(map.get("mapId").toString())){
                map.put("mapName", (a.getDistrict()));
            }
        });
        return map;
    }

    /**
     * <p>查询序号规则List</p>
     * @param type
     * @param mapId
     * @return
     */
    @RequestMapping(value = "/employeeCodeRules", method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeCodeRulePojo> findEmployeeCodeRuleList(@RequestParam("mapId")Byte type, @RequestParam("mapId")Long mapId) {
        try {
            Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
            params.put("type", type);
            params.put("mapId", mapId);

            List<EmployeeCodeRulePojo> list = employeeCodeRuleClient.findEmployeeCodeRuleList(params);
            if(!list.isEmpty()){
                ArrayList<EmployeeCodeRulePojo> arrayList = Lists.newArrayListWithExpectedSize(list.size());
                list.forEach(a->{
                    EmployeeCodeRulePojo employeeCodeRulePojo = setDistrictName(a);
                    arrayList.add(employeeCodeRulePojo);
                });
                return arrayList;
            }
        } catch (Exception e) {
            logger.error("编号规则[查询]异常"+e);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * <p>根据id查询序号规则</p>
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/employeeCodeRule", method = RequestMethod.GET)
    @ResponseBody
    public EmployeeCodeRulePojo findEmployeeCodeRule(@RequestParam("ruleId") int id) {
        try {
            EmployeeCodeRulePojo employeeCodeRule = employeeCodeRuleClient.findEmployeeCodeRule(id);
            return setDistrictName(employeeCodeRule);
        } catch (Exception e) {
            logger.error("编号规则[查询]异常"+e);
        }
        return null;

    }

    private EmployeeCodeRulePojo setDistrictName(EmployeeCodeRulePojo pojo){
        String json = systemDictFeignClient.findDistrictByParentId("0");
        List<District> districts = JSONObject.parseArray(json, District.class);
        districts.forEach(a->{
            if(pojo.getType() == 1 && pojo.getMapId().equals(a.getSortcode())){
                pojo.setMapName(a.getDistrict());
            }
        });
        return pojo;
    }
}
