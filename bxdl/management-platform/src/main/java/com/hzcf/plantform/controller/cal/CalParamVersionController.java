package com.hzcf.plantform.controller.cal;

import com.hzcf.plantform.feign.cal.CalParamVersionClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cal_param_version")
public class CalParamVersionController {

    @Autowired
    private CalParamVersionClient calParamVersionClient;

    /**
     * 版本查询弹出页面，后端分页
     * （！！待定！！）
     * */
    @ResponseBody
    @RequestMapping("/getCalParamVersion")
    public DataMsg getCalParamVersion(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            String org_id = request.getParameter("org_id");
            String param_type = request.getParameter("param_type");
            params.put("orgId",org_id);
            params.put("paramType",param_type);
            PageModel pageModel = calParamVersionClient.getCalParamVersionPage(params);
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataMsg;
    }






}
