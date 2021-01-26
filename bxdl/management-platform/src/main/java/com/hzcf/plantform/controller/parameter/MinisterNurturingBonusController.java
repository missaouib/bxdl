package com.hzcf.plantform.controller.parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.parameter.MinisterNurturingBonusClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.DirectorNurtureBonus;
import com.hzcf.pojo.parameter.MinisterNurturingBonus;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/minister_nurturing_manager")
public class MinisterNurturingBonusController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MinisterNurturingBonusClient ministerNurturingBonusClient;
    /**
     * 跳转到部长育成奖金页面
     * */
    @RequestMapping("to_minister_nurturing")
    public String toMinisterNurturingBonus(Model model){
    	  List<MinisterNurturingBonus> ministerNurturingBonus;
    	  ministerNurturingBonus= ministerNurturingBonusClient.getMinisterNurturingList();
    	  model.addAttribute("ministerNurturingBonus",ministerNurturingBonus);
        return "parameter/MinisterNurturingBonusList";
    }
    
    

    /**
     * 分页查询部长育成奖金LIst
     * */
    @RequestMapping("do_minister_nurturing")
    @ResponseBody
    public DataMsg selectMinisterNurturingBonus(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = ministerNurturingBonusClient.selectMinisterNurturingBonus(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("部长育成奖金[查询]异常");
        e.printStackTrace();
    }
        return  dataMsg;
    }
    /**
     * 增加部长育成奖金
     * */
 /*   @RequiresPermissions("nurtureBonus:add")*/
    @RequestMapping("add_minister_nurturing")
    @ResponseBody
    public DataMsg addMinisterNurturingBonus(DataMsg msg,
								    		String[] fastYear,
								    		String[] followingYearAndBeyond,
								    		String[] ministerId){
        try{
        	MinisterNurturingBonus ministerAdd=new MinisterNurturingBonus();
        	MinisterNurturingBonus ministerUpdate=new MinisterNurturingBonus();
        	int x=followingYearAndBeyond.length;
        	int y=ministerId.length;
        for(int i=0;i<x;i++){
        	if(y-(i+1)>=0){
			 ministerUpdate.setFastYear(fastYear[i]);
			 ministerUpdate.setId(Long.parseLong(ministerId[i]));
			 ministerUpdate.setFollowingYearAndBeyond(followingYearAndBeyond[i]);
    		 logger.info("修改部长育成奖金数据："+ministerUpdate);
             ministerNurturingBonusClient.updateMinisterNurturingBonus(ministerUpdate);
            }else{
      	      ministerAdd.setFastYear(fastYear[i]);
      	      ministerAdd.setFollowingYearAndBeyond(followingYearAndBeyond[i]);
        	  logger.info("新增部长育成奖金数据："+ministerAdd);
              ministerNurturingBonusClient.addMinisterNurturingBonus(ministerAdd);
          
        	 }
        	
        }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("部长育成奖金[新增]异常");
        }
            return msg;
    }
    /**
     * 修改部长育成奖金
     * */
   /* @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_minister_nurturing")
    @ResponseBody
    public DataMsg updateDirectorAllowance(MinisterNurturingBonus ministerNurturingBonus,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+ministerNurturingBonus);
            ministerNurturingBonusClient.updateMinisterNurturingBonus(ministerNurturingBonus);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("部长育成奖金[修改]异常");
        }
       return  msg;
    }

    /**
 	 * 删除时只保留一条:共用
 	 * @return
 	 */
    @RequestMapping(value = "/check_director_size")
    @ResponseBody
    public int checkMinisterSize(@RequestParam(value = "id", required = true)String id,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("id", id);
         		
            	size= ministerNurturingBonusClient.checkMinisterSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    /**
     * 删除时修改状态
     * */
  /*  @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_nurture_bonus_status")
    @ResponseBody
    public DataMsg updateDirectorStatus(@RequestParam(value = "id", required = true)String id,DataMsg msg){
    	MinisterNurturingBonus ministerNurturingBonus=new MinisterNurturingBonus();
    	try{
    		
            logger.info("处长育成奖金数据id"+ministerNurturingBonus);
            ministerNurturingBonus.setId(Long.parseLong(id));
            ministerNurturingBonus.setIsNormal("1");
            ministerNurturingBonusClient.updateMinisterNurturingBonus(ministerNurturingBonus);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("处长育成奖金[修改]异常");
        }
return  msg;
    }
    
}
