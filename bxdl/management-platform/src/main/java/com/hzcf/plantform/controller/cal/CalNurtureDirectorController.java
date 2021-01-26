package com.hzcf.plantform.controller.cal;

import com.alibaba.fastjson.JSON;
import com.hzcf.plantform.feign.cal.CalNurtureDirectorClient;
import com.hzcf.plantform.feign.cal.CalParamVersionClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.basic.Employee;
import com.hzcf.pojo.cal.CalNurtureDirector;
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
@RequestMapping("/cal_nurture_director")
public class CalNurtureDirectorController {

    @Autowired
    private
    CalParamVersionClient calParamVersionClient;

    @Autowired
    private
    CalNurtureDirectorClient calNurtureDirectorClient;

    /**
     * 异步获取处长/经理育成奖金参数表，返回前台信息用来展示
     */
    @RequestMapping("/queryNurtureDirector")
    @ResponseBody
    public Map<String, Object> queryNurtureDirector(String OrgId) {

        Map<String, Object> params = new HashMap<>();
        params.put("orgId", OrgId);
        List<CalNurtureDirector> list = calNurtureDirectorClient.queryNurtureDirector(params);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("listDirector", JSON.toJSONString(list));
        return resMap;
    }

    /**
     * 修改处长/经理参数表
     */
    @RequestMapping("/add_nurture_director")
    @ResponseBody
    public DataMsg addNurtureDirector(String[] directorIsDefaultCal,
                                      String[] directorSalesOrgId,
                                      String[] directorCalOrgId,
                                      DataMsg msg,
                                      String[] finalgenerativeAlgebra,
                                      BigDecimal[] directorFastYear,
                                      BigDecimal[] directorFollowingYearAndBeyond,
                                      String[] directorId) {
        try {
            String isDefaultCal = directorIsDefaultCal[0];
            Long salesOrgId = Long.parseLong(directorSalesOrgId[0]);
            Long orgId = Long.parseLong(directorCalOrgId[0]);

            // 获取操作人
            Subject subject = SecurityUtils.getSubject();
            Employee employee = (Employee) subject.getPrincipal();
            String employeeNo = employee.getEmployeeNo();// varchar
            long employeeId = employee.getEmployeeId();//bigint

            Map<String, Object> map = new HashMap<>();
            map.put("isDefaultCal", isDefaultCal);// 是否默认
            map.put("orgId", orgId);// 当前使用的机构ID（这个字段还是需要~~）
            map.put("paramType", "2");// 自己的类型：处长/经理育成奖
            map.put("salesOrgId", salesOrgId);// 自己的机构主键ID
            map.put("employeeNo", employeeNo);// 账号操作人，示例：admim
            map.put("employeeId", employeeId);// 账号的ID，示例：1
            // 保存历史版本记录（总）  and isDefaultCal = '0' 时，新增6个历史记录和其他5个记录
            Map<String, Object> params = calParamVersionClient.saveCalParamVersion(map);

            int x = directorId.length;
            for (int i = 0; i < x; i++) {
                if ("0".equals(isDefaultCal)) {
                    CalNurtureDirector add = new CalNurtureDirector();
                    add.setFastYear(directorFastYear[i].divide(BigDecimal.valueOf(100)).setScale(5, BigDecimal.ROUND_HALF_UP));
                    add.setFollowingYearAndBeyond(directorFollowingYearAndBeyond[i].divide(BigDecimal.valueOf(100)).setScale(5, BigDecimal.ROUND_HALF_UP));
                    add.setOrgId(salesOrgId);
                    add.setGenerativeAlgebra(finalgenerativeAlgebra[i]);
                    add.setCreatedTime(new Date());
                    add.setIsNormal("0");
                    add.setCreatedBy(employeeNo);
                    calNurtureDirectorClient.addNurtureDirector(add);
                } else {
                    CalNurtureDirector update = new CalNurtureDirector();
                    update.setFastYear(directorFastYear[i].divide(BigDecimal.valueOf(100)).setScale(5, BigDecimal.ROUND_HALF_UP));
                    update.setId(Long.parseLong(directorId[i]));
                    update.setFollowingYearAndBeyond(directorFollowingYearAndBeyond[i].divide(BigDecimal.valueOf(100)).setScale(5, BigDecimal.ROUND_HALF_UP));
                    calNurtureDirectorClient.updateNurtureDirector(update);
                }
            }
            msg.setMessageCode("200");
        } catch (RuntimeException e) {
            msg.setMessageCode("400");
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/getCalHisNurtureDirector")
    @ResponseBody
    public DataMsg getCalHisNurtureDirector(HttpServletRequest request, DataMsg dataMsg){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            String version_id = request.getParameter("version_id");
            params.put("version_id",Long.parseLong(version_id));
            PageModel pageModel = calNurtureDirectorClient.getCalHisNurtureDirector(params);
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataMsg;
    }
}
