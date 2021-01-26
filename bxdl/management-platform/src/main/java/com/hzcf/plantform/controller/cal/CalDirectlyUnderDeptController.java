package com.hzcf.plantform.controller.cal;

import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.plantform.feign.cal.CalDirectlyUnderDeptClient;
import com.hzcf.plantform.feign.cal.CalParamVersionClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.cal.CalDirectlyUnderDept;
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
 * 直辖部管理津贴配置Controller
 */
@Controller
@RequestMapping("/calDept")
public class CalDirectlyUnderDeptController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CalParamVersionClient calParamVersionClient;

    @Autowired
    private CalDirectlyUnderDeptClient  calDirectlyUnderDeptClient ;

    @RequestMapping("/getDeptList")
    @ResponseBody
    public DataMsg getDeptList(HttpServletRequest request,
                                           @RequestParam("orgId") String orgId,
                                           @RequestParam("ruleType") String ruleType,
                                           DataMsg dataMsg) {

        Map<String, Object> params = new HashMap<>();
        params.put("orgId", Long.parseLong(orgId));
        params.put("ruleType", ruleType);
        List<CalDirectlyUnderDept> calDeptList = null;
        try {
            calDeptList = calDirectlyUnderDeptClient.getDeptList(params);
            dataMsg.setRows(calDeptList);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            logger.error("直辖部管理津贴配置基本法获取参数规则报错", e);
        }
        return dataMsg;

    }


    /**
     * 逻辑删除
     * @return
     */
    @RequestMapping(value = "/updateDeptState")
    @ResponseBody
    public DataMsg updateDeptState(
                                    @RequestParam("id") String id,
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
        Long deptId = Long.parseLong(id);        //要删除的 Id
        // 获取操作人
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        String employeeNo = employee.getEmployeeNo();// varchar
        long employeeId = employee.getEmployeeId();//bigint

        Map<String, Object> map = new HashMap<>();
        map.put("isDefaultCal", show_isDefaultCal);// 是否默认
        map.put("orgId", fOrgId);// 当前使用的机构ID（这个字段还是需要~~）
        map.put("paramType", CalParamRuleEnum.PARAM_D_DEPT.getCode());// 自己的类型：直辖部津贴
        map.put("salesOrgId", orgId);// 自己的机构主键ID
        map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
        map.put("employeeId", employeeId);// 账号的ID，示例：1

        CalDirectlyUnderDept calDirectlyUnderDept=null;
        List<CalDirectlyUnderDept> calDeptList = null;
        Map<String, Object> params = new HashMap<>();

        try {
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> resultMap = calParamVersionClient.saveCalParamVersion(map);
            if("0".equals(show_isDefaultCal)){ //默认时 获取所有的配置参数
                params.put("orgId", fOrgId);
                params.put("ruleType", CalParamRuleEnum.RULE_D_DEPT_0.getCode());
                calDeptList =  calDirectlyUnderDeptClient.getDeptList(params);
                for (int i = 0; i <calDeptList.size() ; i++) {
                    calDirectlyUnderDept = new CalDirectlyUnderDept();
                    CalDirectlyUnderDept dept = calDeptList.get(i);
                    BeanUtils.copyProperties(dept,calDirectlyUnderDept);
                    calDirectlyUnderDept.setCreateBy(employeeId);
                    calDirectlyUnderDept.setCreateTime(new Date());
                    calDirectlyUnderDept.setId(null);
                    calDirectlyUnderDept.setOrgId(orgId);
                    if(dept.getId().equals(deptId)){ // 删除的Id
                        calDirectlyUnderDept.setIsNormal("1");
                        calDirectlyUnderDept.setUpdateBy(employeeId);
                        calDirectlyUnderDept.setUpdateTime(new Date());
                    }
                    int insertNum= calDirectlyUnderDeptClient.insertIgnoreNull(calDirectlyUnderDept);
                }

            }else{
                calDirectlyUnderDept = new CalDirectlyUnderDept();
                calDirectlyUnderDept.setId(deptId);
                calDirectlyUnderDept.setIsNormal("1"); //删除状态
                calDirectlyUnderDept.setUpdateBy(employeeId);
                calDirectlyUnderDept.setUpdateTime(new Date());
                int i =calDirectlyUnderDeptClient.updateDeptState(calDirectlyUnderDept); //修改整个对象
            }
            msg.setMessageCode("200");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return msg;
    }


    /**
     * 增加直辖部津贴 固定值 (deptExtraRatio; deptAllowance 为一条数据)
     * */
    @RequestMapping("/deptGDCommit")
    @ResponseBody
    public DataMsg gdCommit(DataMsg msg,
                            String[] deptSalesOrgId,
                            String[] deptAllowance,
                            String[] deptExtraRatio , //额外津贴比例参数
                            String[] deptId,
                            String[] deptIsDefaultCal,
                            String[] deptIncludeFlag ,  //是否包含额外津贴标识
                            String[] deptCalOrgId

                                                   ){


        boolean flag =false ; // 是否包含额外津贴标识
        if(null!=deptExtraRatio&&null!=deptIncludeFlag&&deptExtraRatio.length>0&&deptIncludeFlag.length>0){
            if("1".equals(deptIncludeFlag[0])){
                flag=true;
            }
        }
        try{
            // 获取公用参数
            String DefaultCal = deptIsDefaultCal[0]; // 是否默认
            Long orgId = Long.parseLong(deptSalesOrgId[0]); // 组织机构Id
            Long fOrgId = Long.parseLong(deptCalOrgId[0]); // 默认使用分公司组织机构Id
            // 获取操作人
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();// varchar
            long employeeId = employee.getEmployeeId();//bigint

            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", DefaultCal);// 是否默认
            map.put("orgId", fOrgId);// 当前使用的机构ID（这个字段还是需要~~）
            map.put("paramType", "5");// 自己的类型：直辖部津贴
            map.put("salesOrgId", orgId);// 自己的机构主键ID
            map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
            map.put("employeeId", employeeId);// 账号的ID，示例：1
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);

            CalDirectlyUnderDept deptUpdate = new CalDirectlyUnderDept();
            CalDirectlyUnderDept deptAdd = new CalDirectlyUnderDept();

            if("0".equals(DefaultCal)){ // 默认新增修改，直辖部津贴，均为增加为自己的基本法，并且得增本组织机构基本法为自己的基本法
                for(int i=0;i<deptId.length;i++){
                    deptAdd.setOrgId(orgId); //所有新增修改的 orgId 都是自己组织机构的Id
                    deptAdd.setMinSign("1"); //下限标记符号（0：大于，1：大于等于）
                    deptAdd.setMaxSign("0"); //上限标记符号（0：小于，1：小于等于）
                    deptAdd.setMinValue(BigDecimal.ZERO);
                    deptAdd.setMaxValue(new BigDecimal("9999999999"));
                    deptAdd.setAllowanceRatio(new BigDecimal(deptAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                    if(flag){
                         deptAdd.setExtraRatio(new BigDecimal(deptExtraRatio[0]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                    }
                    deptAdd.setCreateTime(new Date());
                    deptAdd.setRuleType(CalParamRuleEnum.RULE_D_DEPT_1.getCode()); //固定区间
                    deptAdd.setIsNormal("0"); //是否可用 0 可用 1 不可用
                    deptAdd.setCreateBy(employeeId);
                    logger.info("固定规则默认:新增直辖部管理津贴数据："+deptAdd.toString());
                    int insertNum= calDirectlyUnderDeptClient.insertIgnoreNull(deptAdd);
                }
                //修改组织机构表 改为自定义，统一使用saveCalParamVersion

            } else{ //自定义，直辖部管理津贴
                for(int i=0;i<deptId.length;i++){
                    deptUpdate.setId(Long.parseLong(deptId[i]));// 主键Id
                    deptUpdate.setAllowanceRatio(new BigDecimal(deptAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                    if(flag){
                        deptUpdate.setExtraRatio(new BigDecimal(deptExtraRatio[0]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                    }
                    deptUpdate.setUpdateTime(new Date());
                    deptUpdate.setUpdateBy(employeeId);
                    logger.info("固定规则自定义：修改直辖部管理津贴数据："+deptUpdate.toString());
                    int updateNum = calDirectlyUnderDeptClient.updateDeptState(deptUpdate);
                }
            }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖部津贴固定规则[新增修改]异常");
        }
        return msg;
    }

    /**
     * 增加直辖部津贴 区间值
     * */
    @RequestMapping("/qjCommit")
    @ResponseBody
    public DataMsg qjCommit(DataMsg msg,
                            String[] deptSalesOrgId,
                            String[] deptMinValue,
                            String[] deptSelectMinSign,
                            String[] deptSelectMaxSign,
                            String[] deptMaxValue,
                            String[] deptAllowance,
                            String[] deptId,
                            String[] deptIsDefaultCal,
                            String[] deptCalOrgId
    ){


        try{
            // 获取公用参数
            String DefaultCal = deptIsDefaultCal[0]; // 是否默认
            Long orgId = Long.parseLong(deptSalesOrgId[0]); // 组织机构Id
            Long fOrgId = Long.parseLong(deptCalOrgId[0]); // 默认使用分公司组织机构Id
            // 获取操作人
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();// varchar
            long employeeId = employee.getEmployeeId();//bigint

            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", DefaultCal);// 是否默认
            map.put("orgId", fOrgId);// 当前使用的机构ID（这个字段还是需要~~）
            map.put("paramType", "5");// 自己的类型：直辖部津贴
            map.put("salesOrgId", orgId);// 自己的机构主键ID
            map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
            map.put("employeeId", employeeId);// 账号的ID，示例：1
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);

            CalDirectlyUnderDept deptUpdate = new CalDirectlyUnderDept();
            CalDirectlyUnderDept deptAdd = new CalDirectlyUnderDept();
            CalDirectlyUnderDept deptAddz = new CalDirectlyUnderDept();

            int x=deptSelectMinSign.length;
            if("0".equals(DefaultCal)){ // 默认新增修改，直辖部津贴，均为增加为自己的基本法，并且得增本组织机构基本法为自己的基本法
                for(int i=0;i<x;i++){
                    deptAdd.setOrgId(orgId); //所有新增修改的 orgId 都是自己组织机构的Id
                    deptAdd.setMinSign(deptSelectMinSign[i]);
                    deptAdd.setMaxSign(deptSelectMaxSign[i]);
                    deptAdd.setMinValue(new BigDecimal(deptMinValue[i]));
                    deptAdd.setMaxValue(new BigDecimal(deptMaxValue[i]));
                    deptAdd.setAllowanceRatio(new BigDecimal(deptAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                    deptAdd.setCreateTime(new Date());
                    deptAdd.setRuleType(CalParamRuleEnum.RULE_D_DEPT_0.getCode()); //区间设定值
                    deptAdd.setIsNormal("0"); //是否可用 0 可用 1 不可用
                    deptAdd.setCreateBy(employeeId);
                    logger.info("区间设定规则默认:新增直辖部津贴数据："+deptAdd.toString());
                    int insertNum= calDirectlyUnderDeptClient.insertIgnoreNull(deptAdd);
                }
                //修改组织机构表 改为自定义，统一使用saveCalParamVersion

            } else{ //自定义，直辖部津贴
                for(int i=0;i<x;i++){
                    if(null !=deptId && deptId.length-(i+1)>=0){ //修改
                        deptUpdate.setId(Long.parseLong(deptId[i]));// 主键Id
                        deptUpdate.setMinSign(deptSelectMinSign[i]);
                        deptUpdate.setMaxSign(deptSelectMaxSign[i]);
                        deptUpdate.setMinValue(new BigDecimal(deptMinValue[i]));
                        deptUpdate.setMaxValue(new BigDecimal(deptMaxValue[i]));
                        deptUpdate.setAllowanceRatio(new BigDecimal(deptAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                        deptUpdate.setUpdateTime(new Date());
                        deptUpdate.setUpdateBy(employeeId);
                        logger.info("区间设定规则自定义：修改直辖部管理津贴数据："+deptUpdate.toString());
                        int updateNum = calDirectlyUnderDeptClient.updateDeptState(deptUpdate);

                    }else{ //新增
                        deptAddz.setOrgId(orgId); //所有新增修改的 orgId 都是自己组织机构的Id
                        deptAddz.setMinSign(deptSelectMinSign[i]);
                        deptAddz.setMaxSign(deptSelectMaxSign[i]);
                        deptAddz.setMinValue(new BigDecimal(deptMinValue[i]));
                        deptAddz.setMaxValue(new BigDecimal(deptMaxValue[i]));
                        deptAddz.setAllowanceRatio(new BigDecimal(deptAllowance[i]).divide(BigDecimal.valueOf(100)).setScale(5,BigDecimal.ROUND_HALF_UP));
                        deptAddz.setCreateTime(new Date());
                        deptAddz.setRuleType(CalParamRuleEnum.RULE_D_DEPT_0.getCode()); //区间标识
                        deptAddz.setIsNormal("0"); //是否可用 0 可用 1 不可用
                        deptAddz.setCreateBy(employeeId);
                        logger.info("区间设定规则自定义：新增直辖部管理津贴数据："+deptAddz.toString());
                        int insertNum= calDirectlyUnderDeptClient.insertIgnoreNull(deptAddz);
                    }
                }
            }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖部管理津贴处理异常！");
        }
        return msg;
    }


    /**
     * 直辖部历史数据查询
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getCalHisDeptPage")
    @ResponseBody
    public DataMsg getCalHisDeptPage(HttpServletRequest request, DataMsg dataMsg){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            String version_id = request.getParameter("version_id");
            params.put("version_id",Long.parseLong(version_id));
            PageModel pageModel = calDirectlyUnderDeptClient.getCalHisDeptPage(params);
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataMsg;
    }

    /**
     * 直辖部历史数据查询单条
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getFirstCalHisDeptByVersionId")
    @ResponseBody
    public DataMsg getFirstCalHisDeptByVersionId( @RequestParam("versionId") String versionId, DataMsg dataMsg){
        try {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("version_id",Long.parseLong(versionId));
            List<CalDirectlyUnderDept> CalHisIncreaseStaff = calDirectlyUnderDeptClient.getFirstCalHisDeptByVersionId(params);
            dataMsg.setMessageCode("200");
            dataMsg.setRows(CalHisIncreaseStaff);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataMsg;
    }


}
