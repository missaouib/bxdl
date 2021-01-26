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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.parameter.DirectorNurtureBonusClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.DirectorNurtureBonus;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/nurture_bonus_manager")
public class DirectorNurtureBonusController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DirectorNurtureBonusClient directorNurtureBonusClient;
    /**
     * 跳转到处长育成奖金页面
     * */
    @RequestMapping("to_nurture_bonus")
    public String toDirectorNurtureBonus(Model model){
    	  List<DirectorNurtureBonus> directorNurtureBonus;
    	  directorNurtureBonus= directorNurtureBonusClient.getDirectorNurtureBonusList();
    	  model.addAttribute("directorNurtureBonus",directorNurtureBonus);
        return "parameter/DirectorNurtureBonusList";
    }
    
    /**
     * 分页查询处长育成奖金LIst
     * */
    @RequestMapping("do_nurture_bonus")
    @ResponseBody
    public DataMsg selectDirectorNurtureBonus(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = directorNurtureBonusClient.selectDirectorNurtureBonus(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("处长育成奖金[查询]异常",e);
        e.printStackTrace();
    }
        return  dataMsg;
    }
    /**
     * 增加处长育成奖金
     * */
   /* @RequiresPermissions("nurtureBonus:add")*/
    @RequestMapping("add_nurture_bonus")
    @ResponseBody
    public DataMsg addDirectorNurtureBonus(DataMsg msg,
							    		String[] fastYear,
							    		String[] followingYearAndBeyond,
							    		String[] dirId){
    	DirectorNurtureBonus directorNurtureBonusUpdate=new DirectorNurtureBonus();
        try{
        	int x=followingYearAndBeyond.length;
        	int y=dirId.length;
        	 for(int i=0;i<x;i++){
        		 if(y-(i+1)>=0){
        			 directorNurtureBonusUpdate.setFastYear(fastYear[i]);
        			 directorNurtureBonusUpdate.setId(Long.parseLong(dirId[i]));
        			 directorNurtureBonusUpdate.setFollowingYearAndBeyond(followingYearAndBeyond[i]);
        			 
          		     directorNurtureBonusClient.updateDirectorNurtureBonus(directorNurtureBonusUpdate);
          		   logger.info("修改处长育成奖金数据："+directorNurtureBonusUpdate);
          	     }else{
          	    	DirectorNurtureBonus directorNurtureBonusAdd=new DirectorNurtureBonus();
          	    	directorNurtureBonusAdd.setFastYear(fastYear[i]);
          	    	directorNurtureBonusAdd.setFollowingYearAndBeyond(followingYearAndBeyond[i]);
          	    	
          	    	logger.info("新增处长育成奖金数据："+directorNurtureBonusAdd);
                    directorNurtureBonusClient.addDirectorNurtureBonus(directorNurtureBonusAdd);
          	     }
        	 }   
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("处长育成奖金[新增]异常",e);
        }
            return msg;
    }
    /**
     * 修改处长育成奖金
     * */
  /*  @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_nurture_bonus")
    @ResponseBody
    public DataMsg updateDirectorAllowance(DirectorNurtureBonus directorNurtureBonus,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+directorNurtureBonus);
            directorNurtureBonusClient.updateDirectorNurtureBonus(directorNurtureBonus);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("处长育成奖金[修改]异常",e);
        }
return  msg;
    }
    /**
     * 删除处长育成奖金
     * */
    /*@RequiresPermissions("nurtureBonus:del")*/
    @RequestMapping("/del_nurture_bonus")
    @ResponseBody
    public DataMsg delDirectorAllowance(String ids,DataMsg msg){
        try{
            logger.info("处长育成奖金删除数据ID"+ids);
            directorNurtureBonusClient.delDirectorNurtureBonus(ids);
            msg.setMessageCode("200");

        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("处长育成奖金[删除]异常",e);
        }
        return  msg;
    }
    /**
 	 * 删除时只保留一条:共用
 	 * @return
 	 */
    @RequestMapping(value = "/check_director_size")
    @ResponseBody
    public int checkDirectorSize(@RequestParam(value = "id", required = true)String id,@RequestParam(value = "paramerName", required = true)String paramerName,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("id", id);
         		
         		params.put("paramerName", paramerName);
            	size= directorNurtureBonusClient.checkDirectorSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    
    /**
     * 修改处长育成奖金
     * */
  /*  @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_nurture_bonus_status")
    @ResponseBody
    public DataMsg updateDirectorStatus(@RequestParam(value = "id", required = true)String id,DataMsg msg){
    	DirectorNurtureBonus directorNurtureBonus=new DirectorNurtureBonus();
    	try{
    		
            logger.info("处长育成奖金数据id"+directorNurtureBonus);
            directorNurtureBonus.setId(Long.parseLong(id));
            directorNurtureBonus.setIsNormal("1");
            directorNurtureBonusClient.updateDirectorNurtureBonus(directorNurtureBonus);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("处长育成奖金[修改]异常",e);
        }
return  msg;
    }
    
}
