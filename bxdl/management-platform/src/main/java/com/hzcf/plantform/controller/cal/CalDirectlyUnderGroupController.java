package com.hzcf.plantform.controller.cal;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.plantform.feign.cal.CalDirectlyUnderGroupClient;
import com.hzcf.plantform.feign.cal.CalNurtureMinisterClient;
import com.hzcf.plantform.feign.cal.CalParamVersionClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.cal.CalDirectlyUnderGroup;
import com.hzcf.pojo.cal.CalHisIncreaseStaff;
import com.hzcf.pojo.cal.CalIncreaseStaff;
import com.hzcf.pojo.cal.CalPromote;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @Date: 2020/10/28/14:07
 * @Description:
 */
@Controller
@RequestMapping("/calDirectlyUnderGroup")
public class CalDirectlyUnderGroupController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CalDirectlyUnderGroupClient calDirectlyUnderGroupClient;
    @Autowired
    private CalParamVersionClient calParamVersionClient;
//    @Autowired
//    private CalNurtureMinisterClient calNurtureMinisterClient;


    @RequestMapping("/getCalDirectlyUnderGroup")
    @ResponseBody
    public DataMsg getCalDirectlyUnderGroup(HttpServletRequest request,
                                             @RequestParam("orgId") String orgId,
                                             @RequestParam("ruleType") String ruleType,
                                            DataMsg dataMsg)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", Long.parseLong(orgId));
        params.put("ruleType", ruleType);
        List<CalDirectlyUnderGroup> calDirectlyUnderGroupList = null;
        try {
            calDirectlyUnderGroupList = calDirectlyUnderGroupClient.getCalDirectlyUnderGroup(params);
            dataMsg.setMessageCode("200");
            dataMsg.setRows(calDirectlyUnderGroupList);
        }catch (Exception e){
             dataMsg.setMessageCode("400");
             logger.error("直辖组管理津贴配置基本法获取参数规则报错", e);
        }
        return dataMsg;
    }

    @RequestMapping("/underGroupqjCommit")
    @ResponseBody
    public DataMsg underGroupqjCommit(@RequestBody JSONObject object, DataMsg msg ){
        List<CalDirectlyUnderGroup> calDirectlyUnderGroupList =  JSONObject.parseArray(object.getJSONArray("list").toJSONString(), CalDirectlyUnderGroup.class);
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

            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", DefaultCal);// 是否默认
            map.put("orgId", fOrgId);// 当前使用的机构ID（这个字段还是需要~~）
            map.put("paramType", CalParamRuleEnum.PARAM_D_GROUP.getCode());// 自己的类型：直辖组
            map.put("salesOrgId", orgId);// 自己的机构主键ID
            map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
            map.put("employeeId", employeeId);// 账号的ID，示例：1
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);

            CalDirectlyUnderGroup updateUnderGroup = new CalDirectlyUnderGroup();
            CalDirectlyUnderGroup underGroupAddz = new CalDirectlyUnderGroup();
            CalDirectlyUnderGroup underGroupAdd  = new CalDirectlyUnderGroup();

//            int x=underGroupMinValue.length;
//            int y=underGroupId.length;
            if("0".equals(DefaultCal)){
                for (CalDirectlyUnderGroup underGroup : calDirectlyUnderGroupList) {
                    underGroup.setOrgId(orgId);//所有新增修改的 orgId 都是自己组织机构的Id
//                    if (underGroupRuleType[i].equals(CalParamRuleEnum.RULE_D_GROUP_2.getCode())){
//                        underGroup.setActiveSalerSign(activeSalerSign[i]);
//                        underGroup.setActiveSalerNum( Long.parseLong(activeSalerNum[i]));
//                        underGroup.setRelationType(relationType[i]);
//                    }
                    underGroup.setAllowanceRatio(underGroup.getAllowanceRatio().divide(BigDecimal.valueOf(100)));
                    underGroup.setCreateTime(new Date());
                    underGroup.setIsNormal("0"); //是否可用 0 可用 1 不可用
                    underGroup.setCreateBy(employeeId);
                    underGroup.setId(null);//去除掉Id，重新生成
                    int insertNum = calDirectlyUnderGroupClient.insertIgnoreNull(underGroup);
                }
            }else {
                for(CalDirectlyUnderGroup underGroup : calDirectlyUnderGroupList){
                    if(null!=underGroup.getId()) { //修改
//                        underGroup.setId(Long.parseLong(underGroupId[i]));//主键Id
//                        updateUnderGroup.setMinSign(underGroupMinSign[i]);
//                        updateUnderGroup.setMaxSign(underGroupMaxSign[i]);
//                        updateUnderGroup.setMinValue(new BigDecimal(underGroupMinValue[i]));
//                        updateUnderGroup.setMaxValue(new BigDecimal(underGroupMaxValue[i]));
//                        updateUnderGroup.setAllowanceRatio(new BigDecimal(underGroupAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
//                        updateUnderGroup.setRuleType(underGroupRuleType[i]);
//                        if (underGroupRuleType[i].equals(CalParamRuleEnum.RULE_D_GROUP_2.getCode())){
//                            updateUnderGroup.setActiveSalerSign(activeSalerSign[i]);
//                            updateUnderGroup.setActiveSalerNum( Long.parseLong(activeSalerNum[i]));
//                            updateUnderGroup.setRelationType(relationType[i]);
//                        }
                        underGroup.setAllowanceRatio(underGroup.getAllowanceRatio().divide(BigDecimal.valueOf(100)));
                        underGroup.setUpdateTime(new Date());
                        underGroup.setUpdateBy(employeeId);
                        int updateNum = calDirectlyUnderGroupClient.updateIncreaseState(underGroup);
                    }else {
//                         underGroupAdd.setOrgId(orgId);//所有新增修改的 orgId 都是自己组织机构的Id
//                        underGroupAdd.setMinSign(underGroupMinSign[i]);
//                        underGroupAdd.setMaxSign(underGroupMaxSign[i]);
//                        underGroupAdd.setMinValue(new BigDecimal(underGroupMinValue[i]));
//                        underGroupAdd.setMaxValue(new BigDecimal(underGroupMaxValue[i]));
//                        underGroupAdd.setAllowanceRatio(new BigDecimal(underGroupAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
//                        underGroupAdd.setRuleType(underGroupRuleType[i]);
//                        if (underGroupRuleType[i].equals(CalParamRuleEnum.RULE_D_GROUP_2.getCode())){
//                            underGroupAdd.setActiveSalerSign(activeSalerSign[i]);
//                            underGroupAdd.setActiveSalerNum( Long.parseLong(activeSalerNum[i]));
//                            underGroupAdd.setRelationType(relationType[i]);
//                        }
                        underGroup.setOrgId(orgId);
                        underGroup.setAllowanceRatio(underGroup.getAllowanceRatio().divide(BigDecimal.valueOf(100)));
                        underGroup.setCreateTime(new Date());
                        underGroup.setIsNormal("0"); //是否可用 0 可用 1 不可用
                        underGroup.setCreateBy(employeeId);
                        int insertNum= calDirectlyUnderGroupClient.insertIgnoreNull(underGroup);
                    }
                }
            }
            msg.setMessageCode("200");
        }catch (Exception e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖组管理津贴[新增]异常");
        }
        return msg;
    }

     /**
     * 增加增员津贴 固定值
     * */
    @RequestMapping("/underGroupgdCommit")
    @ResponseBody
    public DataMsg underGroupgdCommit(DataMsg msg,
                                        String[] salesOrgId,
                                        String[] underGroupAllowance,
                                        String[] underGroupId,
                                        String[] isDefaultCal ,
                                        String[] underGroupOrgId
                                                                  ){


        try{
            // 获取公用参数
            String DefaultCal = isDefaultCal[0]; // 是否默认
            Long orgId = Long.parseLong(salesOrgId[0]); // 组织机构Id
            Long fOrgId = Long.parseLong(underGroupOrgId[0]); // 默认使用分公司组织机构Id

            // 获取操作人
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();// varchar
            long employeeId = employee.getEmployeeId();//bigint

            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", DefaultCal);// 是否默认
            map.put("orgId", fOrgId);// 当前使用的机构ID（这个字段还是需要~~）
            map.put("paramType", CalParamRuleEnum.PARAM_D_GROUP.getCode());// 自己的类型：直辖组
            map.put("salesOrgId", orgId);// 自己的机构主键ID
            map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
            map.put("employeeId", employeeId);// 账号的ID，示例：1
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);

            CalDirectlyUnderGroup underGroupUpdate = new CalDirectlyUnderGroup();
            CalDirectlyUnderGroup underGroupAdd = new CalDirectlyUnderGroup();

            if("0".equals(DefaultCal)){ // 默认新增修改，增员津贴，均为增加为自己的基本法，并且得增本组织机构基本法为自己的基本法
                for(int i=0;i<underGroupId.length;i++){
                    underGroupAdd.setOrgId(orgId); //所有新增修改的 orgId 都是自己组织机构的Id
                    underGroupAdd.setMinSign("1"); //下限标记符号（0：大于，1：大于等于）
                    underGroupAdd.setMaxSign("0"); //上限标记符号（0：小于，1：小于等于）
                    underGroupAdd.setMinValue(BigDecimal.ZERO);
                    underGroupAdd.setMaxValue(new BigDecimal("9999999999"));
                    underGroupAdd.setAllowanceRatio(new BigDecimal(underGroupAllowance[i]).divide(BigDecimal.valueOf(100)));
                    underGroupAdd.setCreateTime(new Date());
                    underGroupAdd.setRuleType(CalParamRuleEnum.RULE_D_GROUP_1.getCode()); //固定区间
                    underGroupAdd.setIsNormal("0"); //是否可用 0 可用 1 不可用
                    underGroupAdd.setCreateBy(employeeId);
                    logger.info("固定规则默认:新增直辖组管理津贴数据："+underGroupAdd.toString());
                    int insertNum= calDirectlyUnderGroupClient.insertIgnoreNull(underGroupAdd);
                }
                //修改组织机构表 改为自定义，saveCalParamVersion

            } else{ //自定义，直辖组管理津贴
                for(int i=0;i<underGroupId.length;i++){
                    underGroupUpdate.setId(Long.parseLong(underGroupId[i]));// 主键Id
                    underGroupUpdate.setAllowanceRatio(new BigDecimal(underGroupAllowance[i]).divide(BigDecimal.valueOf(100)));
                    underGroupUpdate.setUpdateTime(new Date());
                    underGroupUpdate.setUpdateBy(employeeId);
                    logger.info("固定规则自定义：修改直辖组管理津贴数据："+underGroupUpdate.toString());
                    int updateNum = calDirectlyUnderGroupClient.updateIncreaseState(underGroupUpdate);
                }
            }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖组管理津贴固定规则[新增修改]异常");
        }
        return msg;
    }


    /**
     * 逻辑删除
     * @return
     */
    @RequestMapping(value = "/delUnderGroupIncreaseRow")
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
        map.put("paramType", CalParamRuleEnum.PARAM_D_GROUP.getCode());// 自己的类型：增员津贴
        map.put("salesOrgId", orgId);// 自己的机构主键ID
        map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
        map.put("employeeId", employeeId);// 账号的ID，示例：1

        CalDirectlyUnderGroup calDirectlyUnderGroup = null;
        List<CalDirectlyUnderGroup> calDirectlyUnderGroupList = null;
        Map<String, Object> params = new HashMap<>();
        try {
             // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> resultMap = calParamVersionClient.saveCalParamVersion(map);
            if("0".equals(show_isDefaultCal)) { //默认时 获取所有的配置参数
                params.put("orgId", fOrgId);
                params.put("ruleType", CalParamRuleEnum.RULE_IN_0.getCode());
                calDirectlyUnderGroupList = calDirectlyUnderGroupClient.getCalDirectlyUnderGroup(params);
                for (int i = 0; i < calDirectlyUnderGroupList.size(); i++) {
                    calDirectlyUnderGroup = new CalDirectlyUnderGroup();
                    CalDirectlyUnderGroup underGroup = calDirectlyUnderGroupList.get(i);
                    BeanUtils.copyProperties(underGroup, calDirectlyUnderGroup);
                    calDirectlyUnderGroup.setCreateBy(employeeId);
                    calDirectlyUnderGroup.setCreateTime(new Date());
                    calDirectlyUnderGroup.setId(null);
                    calDirectlyUnderGroup.setOrgId(orgId);
                    if (underGroup.getId().equals(increaseId)) { // 删除的Id
                        calDirectlyUnderGroup.setIsNormal("1");
                        calDirectlyUnderGroup.setUpdateBy(employeeId);
                        calDirectlyUnderGroup.setUpdateTime(new Date());
                    }
                    int insertNum = calDirectlyUnderGroupClient.insertIgnoreNull(calDirectlyUnderGroup);
                }
            }else{
                calDirectlyUnderGroup = new CalDirectlyUnderGroup();
                calDirectlyUnderGroup.setId(increaseId);
                calDirectlyUnderGroup.setIsNormal("1"); //删除状态
                calDirectlyUnderGroup.setUpdateBy(employeeId);
                calDirectlyUnderGroup.setUpdateTime(new Date());
                int i =calDirectlyUnderGroupClient.updateIncreaseState(calDirectlyUnderGroup); //修改整个对象
            }
            msg.setMessageCode("200");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return msg;
    }

    /**
     * 增员历史数据查询
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getCalDirectlyUnderGroupPage")
    @ResponseBody
    public DataMsg getCalDirectlyUnderGroupPage(HttpServletRequest request, DataMsg dataMsg){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            String version_id = request.getParameter("version_id");
            params.put("version_id",Long.parseLong(version_id));
            PageModel pageModel = calDirectlyUnderGroupClient.getCalDirectlyUnderGroupPage(params);
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     * 增员历史数据查询单条
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getFirstCalDirectlyUnderGroupByVersionId")
    @ResponseBody
    public DataMsg getFirstCalDirectlyUnderGroupByVersionId( @RequestParam("versionId") String versionId, DataMsg dataMsg){
        try {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("version_id",Long.parseLong(versionId));
            List<CalDirectlyUnderGroup> calDirectlyUnderGroups = calDirectlyUnderGroupClient.getFirstCalDirectlyUnderGroupByVersionId(params);
            dataMsg.setMessageCode("200");
            dataMsg.setRows(calDirectlyUnderGroups);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataMsg;
    }
}
