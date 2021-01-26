package com.hzcf.plantform.controller.cal;

import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.plantform.feign.cal.CalIncreaseStaffClient;
import com.hzcf.plantform.feign.cal.CalParamVersionClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.cal.CalHisIncreaseStaff;
import com.hzcf.pojo.cal.CalIncreaseStaff;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 增员津贴配置Controller
 */
@Controller
@RequestMapping("/calIncreaseStaff")
public class CalIncreaseStaffController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CalIncreaseStaffClient calIncreaseStaffClient ;

    @Autowired
    private CalParamVersionClient calParamVersionClient;


    @RequestMapping("/getCalIncreaseStaffList")
    @ResponseBody
    public DataMsg getCalIncreaseStaffList(HttpServletRequest request,
                                           @RequestParam("orgId") String orgId,
                                           @RequestParam("ruleType") String ruleType,
                                           DataMsg dataMsg) {

        Map<String, Object> params = new HashMap<>();
        params.put("orgId", Long.parseLong(orgId));
        params.put("ruleType", ruleType);
        List<CalIncreaseStaff> calIncreaseStaffList = null;
        try {
            calIncreaseStaffList = calIncreaseStaffClient.getCalIncreaseStaffList(params);
            dataMsg.setRows(calIncreaseStaffList);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("增员津贴配置基本法获取参数规则报错", e);
        }
        return dataMsg;

    }


    /**
     * 逻辑删除 ： 删除均为区间配置删除
     * id ：增员配置id
     * show_salesOrgId ：当前组织机构id
     * show_calOrgId： 分公司Id
     * show_isDefaultCal ： 0 默认使用分公司Id ，1 自定义，使用当前组织机构Id
     *
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
        map.put("paramType", CalParamRuleEnum.PARAM_IN.getCode());// 自己的类型：增员津贴
        map.put("salesOrgId", orgId);// 自己的机构主键ID
        map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
        map.put("employeeId", employeeId);// 账号的ID，示例：1

        CalIncreaseStaff calIncreaseStaff = null;
        List<CalIncreaseStaff> calIncreaseStaffList = null;
        Map<String, Object> params = new HashMap<>();
        try {
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> resultMap = calParamVersionClient.saveCalParamVersion(map);
            if("0".equals(show_isDefaultCal)){ //默认时 获取所有的配置参数
                params.put("orgId", fOrgId);
                params.put("ruleType", CalParamRuleEnum.RULE_IN_0.getCode());
                calIncreaseStaffList = calIncreaseStaffClient.getCalIncreaseStaffList(params);
                for (int i = 0; i <calIncreaseStaffList.size() ; i++) {
                    calIncreaseStaff = new CalIncreaseStaff();
                    CalIncreaseStaff increase = calIncreaseStaffList.get(i);
                    BeanUtils.copyProperties(increase,calIncreaseStaff);
                    calIncreaseStaff.setCreateBy(employeeId);
                    calIncreaseStaff.setCreateTime(new Date());
                    calIncreaseStaff.setId(null);
                    calIncreaseStaff.setOrgId(orgId);
                    if(increase.getId().equals(increaseId)){ // 删除的Id
                        calIncreaseStaff.setIsNormal("1");
                        calIncreaseStaff.setUpdateBy(employeeId);
                        calIncreaseStaff.setUpdateTime(new Date());
                    }
                    int insertNum= calIncreaseStaffClient.insertIgnoreNull(calIncreaseStaff);
                }

            }else{
                calIncreaseStaff = new CalIncreaseStaff();
                calIncreaseStaff.setId(increaseId);
                calIncreaseStaff.setIsNormal("1"); //删除状态
                calIncreaseStaff.setUpdateBy(employeeId);
                calIncreaseStaff.setUpdateTime(new Date());
                int i =calIncreaseStaffClient.updateIncreaseState(calIncreaseStaff); //修改整个对象
            }
            msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            msg.setErrorMsg("增员逻辑删除异常！");
            logger.error(e.getMessage(), e);
        }
        return msg;
    }


    /**
     * 增加增员津贴 固定值
     * */
    @RequestMapping("/gdCommit")
    @ResponseBody
    public DataMsg gdCommit(DataMsg msg,
                                        String[] salesOrgId,
                                        String[] increaseAllowance,
                                        String[] increseId,
                                        String[] isDefaultCal ,
                                        String[] increseCalOrgId
                                                                  ){


        try{
            // 获取公用参数
            String DefaultCal = isDefaultCal[0]; // 是否默认
            Long orgId = Long.parseLong(salesOrgId[0]); // 组织机构Id
            Long fOrgId = Long.parseLong(increseCalOrgId[0]); // 默认使用分公司组织机构Id

            // 获取操作人
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();// varchar
            long employeeId = employee.getEmployeeId();//bigint

            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", DefaultCal);// 是否默认
            map.put("orgId", fOrgId);// 当前使用的机构ID（这个字段还是需要~~）
            map.put("paramType", "1");// 自己的类型：增员津贴
            map.put("salesOrgId", orgId);// 自己的机构主键ID
            map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
            map.put("employeeId", employeeId);// 账号的ID，示例：1
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);

            CalIncreaseStaff increaseStaffUpdate = new CalIncreaseStaff();
            CalIncreaseStaff increaseStaffAdd = new CalIncreaseStaff();

            if("0".equals(DefaultCal)){ // 默认新增修改，增员津贴，均为增加为自己的基本法，并且得增本组织机构基本法为自己的基本法
                for(int i=0;i<increseId.length;i++){
                    increaseStaffAdd.setOrgId(orgId); //所有新增修改的 orgId 都是自己组织机构的Id
                    increaseStaffAdd.setMinSign("1"); //下限标记符号（0：大于，1：大于等于）
                    increaseStaffAdd.setMaxSign("0"); //上限标记符号（0：小于，1：小于等于）
                    increaseStaffAdd.setMinValue(BigDecimal.ZERO);
                    increaseStaffAdd.setMaxValue(new BigDecimal("9999999999"));
                    increaseStaffAdd.setAllowanceRatio(new BigDecimal(increaseAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                    increaseStaffAdd.setCreateTime(new Date());
                    increaseStaffAdd.setRuleType(CalParamRuleEnum.RULE_IN_1.getCode()); //固定区间
                    increaseStaffAdd.setIsNormal("0"); //是否可用 0 可用 1 不可用
                    increaseStaffAdd.setCreateBy(employeeId);
                    logger.info("固定规则默认:新增增员津贴数据："+increaseStaffAdd.toString());
                    int insertNum= calIncreaseStaffClient.insertIgnoreNull(increaseStaffAdd);
                }
                //默认状态只要有改动，就更变为自定义，统一走saveCalParamVersion
            } else{ //自定义，增员津贴
                for(int i=0;i<increseId.length;i++){
                    increaseStaffUpdate.setId(Long.parseLong(increseId[i]));// 主键Id
                    increaseStaffUpdate.setAllowanceRatio(new BigDecimal(increaseAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                    increaseStaffUpdate.setUpdateTime(new Date());
                    increaseStaffUpdate.setUpdateBy(employeeId);
                    logger.info("固定规则自定义：修改增员津贴数据："+increaseStaffUpdate.toString());
                    int updateNum = calIncreaseStaffClient.updateIncreaseState(increaseStaffUpdate);
                }
            }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("增员津贴固定规则[新增修改]异常");
        }
        return msg;
    }

    /**
     * 增加增员津贴 区间值
     * */
    @RequestMapping("/qjCommit")
    @ResponseBody
    public DataMsg qjCommit(DataMsg msg,
                            String[] salesOrgId,
                            String[] increaseMinValue,
                            String[] increaseSelectMinSign,
                            String[] increaseSelectMaxSign,
                            String[] increaseMaxValue,
                            String[] increaseAllowance,
                            String[] increseId,
                            String[] isDefaultCal,
                            String[] increseCalOrgId
                                                         ){


        try{
            // 获取公用参数
            String DefaultCal = isDefaultCal[0]; // 是否默认
            Long orgId = Long.parseLong(salesOrgId[0]); // 组织机构Id
            Long fOrgId = Long.parseLong(increseCalOrgId[0]); // 默认使用分公司组织机构Id
            // 获取操作人
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();// varchar
            long employeeId = employee.getEmployeeId();//bigint

            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", DefaultCal);// 是否默认
            map.put("orgId", fOrgId);// 当前使用的机构ID（这个字段还是需要~~）
            map.put("paramType", "1");// 自己的类型：增员津贴
            map.put("salesOrgId", orgId);// 自己的机构主键ID
            map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
            map.put("employeeId", employeeId);// 账号的ID，示例：1
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);

            CalIncreaseStaff increaseStaffUpdate = new CalIncreaseStaff();
            CalIncreaseStaff increaseStaffAddz = new CalIncreaseStaff();
            CalIncreaseStaff increaseStaffAdd = new CalIncreaseStaff();

            int x=increaseSelectMinSign.length;
            if("0".equals(DefaultCal)){ // 默认新增修改，增员津贴，均为增加为自己的基本法，并且得增本组织机构基本法为自己的基本法
                for(int i=0;i<x;i++){
                    increaseStaffAdd.setOrgId(orgId); //所有新增修改的 orgId 都是自己组织机构的Id
                    increaseStaffAdd.setMinSign(increaseSelectMinSign[i]);
                    increaseStaffAdd.setMaxSign(increaseSelectMaxSign[i]);
                    increaseStaffAdd.setMinValue(new BigDecimal(increaseMinValue[i]));
                    increaseStaffAdd.setMaxValue(new BigDecimal(increaseMaxValue[i]));
                    increaseStaffAdd.setAllowanceRatio(new BigDecimal(increaseAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                    increaseStaffAdd.setCreateTime(new Date());
                    increaseStaffAdd.setRuleType(CalParamRuleEnum.RULE_IN_0.getCode()); //区间设定值
                    increaseStaffAdd.setIsNormal("0"); //是否可用 0 可用 1 不可用
                    increaseStaffAdd.setCreateBy(employeeId);
                    logger.info("区间设定规则默认:新增增员津贴数据："+increaseStaffAdd.toString());
                    int insertNum= calIncreaseStaffClient.insertIgnoreNull(increaseStaffAdd);
                }
                //默认状态只要有改动，就更变为自定义，统一走saveCalParamVersion
            } else{ //自定义，增员津贴
                for(int i=0;i<x;i++){
                    if(null!=increseId && increseId.length-(i+1)>=0){ //修改
                        increaseStaffUpdate.setId(Long.parseLong(increseId[i]));// 主键Id
                        increaseStaffUpdate.setMinSign(increaseSelectMinSign[i]);
                        increaseStaffUpdate.setMaxSign(increaseSelectMaxSign[i]);
                        increaseStaffUpdate.setMinValue(new BigDecimal(increaseMinValue[i]));
                        increaseStaffUpdate.setMaxValue(new BigDecimal(increaseMaxValue[i]));
                        increaseStaffUpdate.setAllowanceRatio(new BigDecimal(increaseAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                        increaseStaffUpdate.setUpdateTime(new Date());
                        increaseStaffUpdate.setUpdateBy(employeeId);
                        logger.info("区间设定规则自定义：修改增员津贴数据："+increaseStaffUpdate.toString());
                        int updateNum = calIncreaseStaffClient.updateIncreaseState(increaseStaffUpdate);
                    }else{ //新增
                        increaseStaffAddz.setOrgId(orgId); //所有新增修改的 orgId 都是自己组织机构的Id
                        increaseStaffAddz.setMinSign(increaseSelectMinSign[i]);
                        increaseStaffAddz.setMaxSign(increaseSelectMaxSign[i]);
                        increaseStaffAddz.setMinValue(new BigDecimal(increaseMinValue[i]));
                        increaseStaffAddz.setMaxValue(new BigDecimal(increaseMaxValue[i]));
                        increaseStaffAddz.setAllowanceRatio(new BigDecimal(increaseAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                        increaseStaffAddz.setCreateTime(new Date());
                        increaseStaffAddz.setRuleType(CalParamRuleEnum.RULE_IN_0.getCode()); //固定区间
                        increaseStaffAddz.setIsNormal("0"); //是否可用 0 可用 1 不可用
                        increaseStaffAddz.setCreateBy(employeeId);
                        logger.info("区间设定规则自定义：新增增员津贴数据："+increaseStaffAddz.toString());
                        int insertNum= calIncreaseStaffClient.insertIgnoreNull(increaseStaffAddz);
                    }
                }
            }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("增员津贴[新增]异常");
        }
        return msg;
    }

    /**
     * 增员历史数据查询
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getCalHisIncreasePage")
    @ResponseBody
    public DataMsg getCalHisIncreasePage(HttpServletRequest request, DataMsg dataMsg){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            String version_id = request.getParameter("version_id");
            params.put("version_id",Long.parseLong(version_id));
            PageModel pageModel = calIncreaseStaffClient.getCalHisIncreasePage(params);
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
    @RequestMapping("/getFirstCalHisIncreaseByVersionId")
    @ResponseBody
    public DataMsg getFirstCalHisIncreaseByVersionId( @RequestParam("versionId") String versionId, DataMsg dataMsg){
        try {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("version_id",Long.parseLong(versionId));
            List<CalHisIncreaseStaff> CalHisIncreaseStaff = calIncreaseStaffClient.getFirstCalHisIncreaseByVersionId(params);
            dataMsg.setMessageCode("200");
            dataMsg.setRows(CalHisIncreaseStaff);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataMsg;
    }



}
