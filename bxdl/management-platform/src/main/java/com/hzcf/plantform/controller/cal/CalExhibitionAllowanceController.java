package com.hzcf.plantform.controller.cal;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.plantform.feign.cal.CalExhibitionAllowanceClient;
import com.hzcf.plantform.feign.cal.CalNurtureMinisterClient;
import com.hzcf.plantform.feign.cal.CalParamVersionClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.cal.CalIncreaseStaff;
import com.hzcf.pojo.cal.CalParamRuleConfig;
import com.hzcf.pojo.cal.CalParamVersion;
import com.hzcf.pojo.cal.CalPromote;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: liuweichen
 * @Date: 2020/10/20/15:44
 * @Description: 多套基本法展业津贴相关
 */
@Controller
@RequestMapping("/calExhibitionAllowance")
public class CalExhibitionAllowanceController {
     private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CalExhibitionAllowanceClient calExhibitionAllowanceClient;
    @Autowired
    private CalParamVersionClient calParamVersionClient;
    @Autowired
    private CalNurtureMinisterClient calNurtureMinisterClient;

    @RequestMapping("/showCalExhibition")
    @ResponseBody
    public DataMsg showCalExhibition(HttpServletRequest request,
                                     @RequestParam("orgId") String orgId, DataMsg dataMsg){
        Map<String, Object> params = new HashMap<>();
        int orgIdo = Integer.parseInt(orgId);
        params.put("orgId", orgIdo);
        List<CalPromote> calPromoteList = null;
        try {
            calPromoteList = calExhibitionAllowanceClient.getCalPromoteList(params);
            dataMsg.setRows(calPromoteList);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("获取展业津贴数据出错", e);
        }
        return dataMsg;
    }

    @RequestMapping("/getExCalParamVersion")
    @ResponseBody
    public DataMsg getExCalParamVersion(HttpServletRequest request,
                                        @RequestParam("orgId") String orgId,DataMsg dataMsg){
        Map<String, Object> params = new HashMap<>();
        int orgIdd = Integer.parseInt(orgId);
        params.put("orgId",orgIdd);
        params.put("paramType", CalParamRuleEnum.PARAM_ZY.getCode());
        List<CalParamVersion> calParamVersionList = null;
        try {
            calParamVersionList = calExhibitionAllowanceClient.getExCalParamVersion(params);
            dataMsg.setRows(calParamVersionList);
            dataMsg.setMessageCode("200");
        }catch (Exception e){
            dataMsg.setMessageCode("400");
            logger.error("获取展业津贴对应版本记录数据出错", e);
        }
        return dataMsg;
    }

    @RequestMapping("/saveCalPromote")
    @ResponseBody
    public DataMsg saveCalPromote(@RequestBody JSONObject object, DataMsg dataMsg){
        List<CalPromote> promoteList = JSONObject.parseArray(object.getJSONArray("list").toJSONString(), CalPromote.class);
        String salesOrgId = object.getString("salesOrgId");
        String showCalOrgId = object.getString("showCalOrgId");
        try {
            // 获取公用参数
            String DefaultCal = object.getString("isDefaultCal");// 是否默认
            Long orgId = Long.parseLong(salesOrgId); // 组织机构Id
            Long fOrgId = Long.parseLong(showCalOrgId); // 默认使用分公司组织机构Id

            // 获取操作人
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();// varchar
            long employeeId = employee.getEmployeeId();//bigint
//
            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", DefaultCal);// 是否默认
            map.put("orgId", fOrgId);
            map.put("paramType", CalParamRuleEnum.PARAM_ZY.getCode());// 展业
            map.put("salesOrgId", orgId);
            map.put("employeeNo", employeeNo);
            map.put("employeeId", employeeId);
            // 保存历史版本记录（总）
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);
            // 返回的自增id主键（）
//            long versionId = Long.valueOf(String.valueOf(params.get("id")));
//            CalPromote addCalPromote = new CalPromote();
//            CalPromote updateCalPromote = new CalPromote();
////            CalPromote addtoCalPromote = new CalPromote();
//            SalesOrgInfo salesOrgInfo = new SalesOrgInfo();
//            int x = optionsminSign.length;
//            int y = exhibitionId.length;
            if ("0".equals(DefaultCal)) {// 默认新增修改，展业津贴，均为增加为自己的基本法，并且得增本组织机构基本法为自己的基本法
                for (CalPromote calPromote : promoteList) {
                    calPromote.setOrgId(orgId);//所有新增修改的 orgId 都是自己组织机构的Id
                    if (calPromote.getRuleType().equals(CalParamRuleEnum.RULE_ZY_2.getCode())) {//只有 有额外的递增 才设置 增加金额
                            BigDecimal allowanceBai = calPromote.getAllowance();
                            allowanceBai = allowanceBai.divide(new BigDecimal(100));
                            calPromote.setAllowance(allowanceBai);
                    }
                    calPromote.setCreateTime(new Date());
                    calPromote.setIsNormal("0");
                    calPromote.setCreateBy(employeeId);
                    calPromote.setId(null);//去除掉Id，重新生成
                    int count = calExhibitionAllowanceClient.insertIgnoreNull(calPromote);
                }
            } else {
                for (CalPromote calPromote : promoteList) {
                    if (null!=calPromote.getId()) {//修改
//                        updateCalPromote.setId(Long.parseLong(exhibitionId[i]));//主键Id
//                        updateCalPromote.setMinSign(optionsminSign[i]);
//                        updateCalPromote.setMaxSign(optionsmaxSign[i]);
//                        updateCalPromote.setMinValue(new BigDecimal(minValue[i]));
//                        updateCalPromote.setMaxValue(new BigDecimal(maxValue[i]));
//                        if (!exruleType[i].equals(CalParamRuleEnum.RULE_ZY_1.getCode())) {//如果是 FYC百位取整 不设置 津贴或津贴比例
//                            updateCalPromote.setAllowance(new BigDecimal(allowance[i]));
//                        }
                        if (calPromote.getRuleType().equals(CalParamRuleEnum.RULE_ZY_2.getCode())) {//只有 有额外的递增 才设置 增加金额
                            BigDecimal allowanceBai = calPromote.getAllowance();
                            allowanceBai = allowanceBai.divide(new BigDecimal(100));
                            calPromote.setAllowance(allowanceBai);
                        }
//                        if (exruleType[i].equals(CalParamRuleEnum.RULE_ZY_3.getCode())) {//只有 有额外的递增 才设置 增加金额
//                            updateCalPromote.setStep(new BigDecimal(step[i]));
//                            updateCalPromote.setExtraAllowance(new BigDecimal(extraAllowance[i]));
//                        }
                        calPromote.setUpdateTime(new Date());
                        calPromote.setUpdateBy(employeeId);
                        int updateNum = calExhibitionAllowanceClient.updateIgnoreNull(calPromote);
                    } else {//新增
                        calPromote.setOrgId(orgId);//所有新增修改的 orgId 都是自己组织机构的Id
//                        addCalPromote.setMinSign(optionsminSign[i]);
//                        addCalPromote.setMaxSign(optionsmaxSign[i]);
//                        addCalPromote.setMinValue(new BigDecimal(minValue[i]));
//                        addCalPromote.setMaxValue(new BigDecimal(maxValue[i]));
//                        if (!exruleType[i].equals(CalParamRuleEnum.RULE_ZY_1.getCode())) {//如果是 FYC百位取整 不设置 津贴或津贴比例
//                            addCalPromote.setAllowance(new BigDecimal(allowance[i]));
//                        }
                        if (calPromote.getRuleType().equals(CalParamRuleEnum.RULE_ZY_2.getCode())) {//只有 有额外的递增 才设置 增加金额
                            BigDecimal allowanceBai = calPromote.getAllowance();
                            allowanceBai = allowanceBai.divide(new BigDecimal(100));
                            calPromote.setAllowance(allowanceBai);
                        }
//                        if (exruleType[i].equals(CalParamRuleEnum.RULE_ZY_3.getCode())) {//只有 有额外的递增 才设置 增加金额
//                            addCalPromote.setStep(new BigDecimal(step[i]));
//                            addCalPromote.setExtraAllowance(new BigDecimal(extraAllowance[i]));
//                        }
                        calPromote.setCreateTime(new Date());
//                        calPromote.setRuleType(exruleType[i]);
                        calPromote.setIsNormal("0");
                        calPromote.setCreateBy(employeeId);
                        int count = calExhibitionAllowanceClient.insertIgnoreNull(calPromote);
                    }
                }
            }
            dataMsg.setMessageCode("200");
        }catch(Exception e){
            dataMsg.setMessageCode("400");
            e.printStackTrace();
            logger.info("展业津贴[新增]异常");
        }
        return dataMsg;
    }

     /**
     * 逻辑删除
     * @return
     */
    @RequestMapping(value = "/updateIncreaseState")
    @ResponseBody
    public DataMsg updateIncreaseState(@RequestParam("id") String id,
                                       @RequestParam("show_salesOrgId") String show_salesOrgId,
                                       @RequestParam("show_calOrgId") String show_calOrgId,
                                       @RequestParam("show_isDefaultCal") String show_isDefaultCal,
                                       DataMsg msg) {
         if(StringUtils.isBlank(id) ||StringUtils.isBlank(show_salesOrgId) ||StringUtils.isBlank(show_calOrgId) || StringUtils.isBlank(show_isDefaultCal)){
            msg.setMessageCode("400");
            msg.setErrorMsg("参数缺少！");
            return msg ;
        }

        // 获取公用参数
        Long orgId = Long.parseLong(show_salesOrgId); // 组织机构Id
        Long fOrgId = Long.parseLong(show_calOrgId); // 默认使用分公司组织机构Id
        Long increaseId = Long.parseLong(id);        //要删除的 Id
        // 获取操作人
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String employeeNo = employee.getEmployeeNo();// varchar
        long employeeId = employee.getEmployeeId();//bigint

        Map<String, Object> map = new HashMap<>();
        map.put("isDefaultCal", show_isDefaultCal);// 是否默认
        map.put("orgId", fOrgId);// 当前使用的机构ID（这个字段还是需要~~）
        map.put("paramType", CalParamRuleEnum.PARAM_ZY.getCode());// 自己的类型：增员津贴
        map.put("salesOrgId", orgId);// 自己的机构主键ID
        map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
        map.put("employeeId", employeeId);// 账号的ID，示例：1

        CalPromote calPromote = null;
        List<CalPromote> calPromoteList = null;
        Map<String, Object> params = new HashMap<>();

        try {
             // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> resultMap = calParamVersionClient.saveCalParamVersion(map);
            if("0".equals(show_isDefaultCal)){ //默认时 获取所有的配置参数
                params.put("orgId", fOrgId);
                params.put("ruleType", CalParamRuleEnum.RULE_IN_0.getCode());
                calPromoteList = calExhibitionAllowanceClient.getCalPromoteList(params);
                for (int i = 0; i <calPromoteList.size() ; i++) {
                    calPromote = new CalPromote();
                    CalPromote promote = calPromoteList.get(i);
                    BeanUtils.copyProperties(promote,calPromote);
                    calPromote.setCreateBy(employeeId);
                    calPromote.setCreateTime(new Date());
                    calPromote.setId(null);
                    calPromote.setOrgId(orgId);
                    if(promote.getId().equals(increaseId)){ // 删除的Id
                        calPromote.setIsNormal("1");
                        calPromote.setUpdateBy(employeeId);
                        calPromote.setUpdateTime(new Date());
                    }
                    int insertNum= calExhibitionAllowanceClient.insertIgnoreNull(calPromote);
                }

            }else {
                calPromote = new CalPromote();
                calPromote.setId(increaseId);
                calPromote.setIsNormal("1"); //删除状态
                calPromote.setUpdateBy(employeeId);
                calPromote.setUpdateTime(new Date());
                int i =calExhibitionAllowanceClient.updateIgnoreNull(calPromote); //修改整个对象
            }
            msg.setMessageCode("200");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return msg;
    }

    @RequestMapping("/getCalHisPromotePage")
    @ResponseBody
    public DataMsg getCalHisPromotePage(HttpServletRequest request, DataMsg dataMsg){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            String version_id = request.getParameter("version_id");
            params.put("version_id",Long.parseLong(version_id));
            PageModel pageModel = calExhibitionAllowanceClient.getCalHisPromotePage(params);
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataMsg;
    }

}
