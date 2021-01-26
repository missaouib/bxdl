package com.hzcf.plantform.controller.parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.parameter.StatisticalCommissionClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.StatisticalCommission;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/statistical_commission_manager")
public class StatisticalCommissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StatisticalCommissionClient statisticalCommissionClient;
    /**
     * 跳转到统计计佣日设置页面
     * */
    @RequestMapping("to_statistical_commission")
    public String toStatisticalCommission(Model model){
    	  List<StatisticalCommission> statisticalCommission;
    	  statisticalCommission= statisticalCommissionClient.getStatisticalCommissionList();
    	  
    	  List<StatisticalCommission> commissionNow;
    	  commissionNow= statisticalCommissionClient.getStatisticalCommissionListNow();
    	  
    	  model.addAttribute("commissionNow",commissionNow);//当前
    	  model.addAttribute("statisticalCommission",statisticalCommission);//历史
        return "partnershipCommission/StatisticalCommissionList";
    }
    
    /**
     * 分页查询统计计佣日设置LIst
     * */
    @RequestMapping("do_statistical_commission")
    @ResponseBody
    public DataMsg selectStatisticalCommission(HttpServletRequest request,DataMsg dataMsg){
    try{
    	 Map<String,Object> paras = new HashMap<>();
        String pageNo = request.getParameter("pageNo");
        if (StringUtil.isNotBlank(pageNo)) {
            paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
        }
        String pageSize = request.getParameter("pageSize");
        if (StringUtil.isNotBlank(pageSize)) {
            paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
        }
        PageModel pageModel = statisticalCommissionClient.selectStatisticalCommission(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("统计计佣日[查询]异常",e);
        e.printStackTrace();
    }
        return  dataMsg;
    }
    /**
     * 增加统计计佣日设置
     * */
 /*   @RequiresPermissions("nurtureBonus:add")*/
    @RequestMapping("add_statistical_commission")
    @ResponseBody
    public DataMsg addStatisticalCommission(DataMsg msg,StatisticalCommission statisticalCommission
								    		){
        try{
              statisticalCommissionClient.addStatisticalCommission(statisticalCommission);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("统计计佣日[新增]异常",e);
        }
            return msg;
    }
    /**
     * 修改统计计佣日设置
     * */
   /* @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_statistical_commission")
    @ResponseBody
    public DataMsg updateDirectorAllowance(StatisticalCommission statisticalCommission,DataMsg msg){
        try{
            logger.info("修改数据"+statisticalCommission);
            statisticalCommissionClient.updateStatisticalCommission(statisticalCommission);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("统计计佣日设置[修改]异常",e);
        }
       return  msg;
    }

 
    
}
