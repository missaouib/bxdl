package com.hzcf.plantform.controller.cal;

import com.alibaba.fastjson.JSON;
import com.hzcf.plantform.feign.cal.CalNurtureMinisterClient;
import com.hzcf.plantform.feign.cal.CalParamVersionClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.cal.CalNurtureMinister;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cal_minister_nurturing")
public class CalNurtureMinisterController {

    @Autowired
    private CalNurtureMinisterClient calNurtureMinisterClient;

    @Autowired
    private
    CalParamVersionClient calParamVersionClient;

    /**
     * 异步获取部长育成奖金参数表，返回前台信息用来展示
     */
    @RequestMapping("/queryNurtureMinister")
    @ResponseBody
    public Map<String, Object> queryNurtureMinister(String OrgId, String isDefaultCal) {
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", OrgId);
        List<CalNurtureMinister> list = calNurtureMinisterClient.queryNurtureMinister(params);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list", JSON.toJSONString(list));
        resMap.put("isDefaultCalMinister", isDefaultCal);
        return resMap;
    }

    /**
     * 修改部长育成奖金参数表
     */
    @RequestMapping("/add_minister_nurturing")
    @ResponseBody
    public DataMsg addMinisterNurturingBonus(String[] isDefaultCalMinister,
                                             String[] show_salesOrgId,
                                             String[] show_calOrgId,
                                             DataMsg msg,
                                             String[] show_generativeAlgebra,
                                             BigDecimal[] fastYear,
                                             BigDecimal[] followingYearAndBeyond,
                                             String[] ministerId) {
        try {
            String isDefaultCal = isDefaultCalMinister[0];
            Long salesOrgId = Long.parseLong(show_salesOrgId[0]);
            Long orgId = Long.parseLong(show_calOrgId[0]);
            // 获取操作人
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();// varchar
            long employeeId = employee.getEmployeeId();//bigint

            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", isDefaultCal);// 是否默认
            map.put("orgId", orgId);// 当前使用的机构ID（这个字段还是需要~~）
            map.put("paramType", "3");// 自己的类型：部长/总监育成奖
            map.put("salesOrgId", salesOrgId);// 自己的机构主键ID
            map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
            map.put("employeeId", employeeId);// 账号的ID，示例：1
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);

            int x = ministerId.length;
            for (int i = 0; i < x; i++) {
                if ("0".equals(isDefaultCal)) {
                    CalNurtureMinister ministerAdd = new CalNurtureMinister();
                    ministerAdd.setFastYear(fastYear[i].divide(BigDecimal.valueOf(100)).setScale(5, BigDecimal.ROUND_HALF_UP));
                    ministerAdd.setFollowingYearAndBeyond(followingYearAndBeyond[i].divide(BigDecimal.valueOf(100)).setScale(5, BigDecimal.ROUND_HALF_UP));
                    ministerAdd.setOrgId(Integer.valueOf(String.valueOf(salesOrgId)));
                    ministerAdd.setGenerativeAlgebra(show_generativeAlgebra[i]);
                    ministerAdd.setCreatedTime(new Date());
                    ministerAdd.setIsNormal("0");
                    ministerAdd.setCreatedBy(employeeNo);
                    calNurtureMinisterClient.addMinisterNurturingBonus(ministerAdd);
                } else {
                    CalNurtureMinister ministerUpdate = new CalNurtureMinister();
                    ministerUpdate.setFastYear(fastYear[i].divide(BigDecimal.valueOf(100)).setScale(5, BigDecimal.ROUND_HALF_UP));
                    ministerUpdate.setId(Long.parseLong(ministerId[i]));
                    ministerUpdate.setFollowingYearAndBeyond(followingYearAndBeyond[i].divide(BigDecimal.valueOf(100)).setScale(5, BigDecimal.ROUND_HALF_UP));
                    calNurtureMinisterClient.updateMinisterNurturingBonus(ministerUpdate);
                }
            }
            msg.setMessageCode("200");
        } catch (RuntimeException e) {
            msg.setMessageCode("400");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 查看部长育成奖金参数记录表
     * plantfrom
     */
    @RequestMapping("/getCalHisNurtureMinister")
    @ResponseBody
    public DataMsg getCalHisNurtureMinister(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            String version_id = request.getParameter("version_id");
            params.put("versionId", Long.parseLong(version_id));
            PageModel pageModel = calNurtureMinisterClient.getCalHisNurtureMinister(params);
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMsg;
    }


}
