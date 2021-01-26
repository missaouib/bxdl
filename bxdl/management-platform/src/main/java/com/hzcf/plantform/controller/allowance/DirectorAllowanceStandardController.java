package com.hzcf.plantform.controller.allowance;

import com.hzcf.plantform.feign.allowance.DirectorAllowanceStandardClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/allowance_manager")
public class DirectorAllowanceStandardController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DirectorAllowanceStandardClient directorAllowanceStandardClient;
    /**
     * 跳转到总监津贴页面
     * */
    @RequestMapping("/to_director_allowance")
    public String toDirectorAllowance(){
        return "allowance/DirectorAllowanceList";
    }

    /**
     * 分页查询总监津贴LIst
     * */
    @RequestMapping("do_director_allowance")
    @ResponseBody
    public DataMsg doDirectorAllowance(HttpServletRequest request,DataMsg dataMsg){
    try{
        Map<String,Object> paras = new HashMap<String,Object>(16);
         String pageNo = request.getParameter("pageNo");
        if (StringUtil.isNotBlank(pageNo)) {
            paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
        }else{
            paras.put("pageNo",0);
        }
        String pageSize = request.getParameter("pageSize");
        if (StringUtil.isNotBlank(pageSize)) {
            paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
        }else{
            paras.put("pageSize",10);
        }
        PageModel pageModel = directorAllowanceStandardClient.doDirectorAllowance(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("总监津贴[查询]异常");
        e.printStackTrace();
    }

        return  dataMsg;
    }
    /**
     * 增加总监津贴
     * */
    @RequiresPermissions("allowance:add")
    @RequestMapping("add_director_allowance")
    @ResponseBody
    public DataMsg addDirectorAllowance(DirectorAllowanceStandardPojo directorAllowanceStandardPojo,DataMsg msg){
        try{
            logger.info("新增总监津贴数据："+directorAllowanceStandardPojo);
            directorAllowanceStandardClient.addDirectorAllowance(directorAllowanceStandardPojo);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("总监津贴[新增]异常");
        }
            return msg;
    }
    /**
     * 修改总监津贴
     * */
    @RequiresPermissions("allowance:update")
    @RequestMapping("/update_director_allowance")
    @ResponseBody
    public DataMsg updateDirectorAllowance(DirectorAllowanceStandardPojo directorAllowanceStandardPojo,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+directorAllowanceStandardPojo);
            directorAllowanceStandardClient.updateDirectorAllowance(directorAllowanceStandardPojo);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("总监津贴[修改]异常");
        }
return  msg;
    }
    /**
     * 删除总监津贴
     * */
    @RequiresPermissions("allowance:del")
    @RequestMapping("/del_director_allowance")
    @ResponseBody
    public DataMsg delDirectorAllowance(String ids,DataMsg msg){
        try{
            logger.info("总监津贴删除数据ID"+ids);
            directorAllowanceStandardClient.delDirectorAllowance(ids);
            msg.setMessageCode("200");

        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("总监津贴[删除]异常");
        }
        return  msg;
    }

}
