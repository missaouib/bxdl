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

import com.hzcf.plantform.feign.parameter.PersonalContinuityRateAdjustClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.IncreaseAllowance;
import com.hzcf.pojo.parameter.PersonalContinuityRateAdjust;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/personal_adjust_manager")
public class PersonalContinuityRateAdjustController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PersonalContinuityRateAdjustClient personalContinuityRateAdjustClient;
    /**
     * 跳转到个人继续率调节页面
     * */
    @RequestMapping("to_personal_adjust")
    public String toPersonalContinuityRateAdjust(Model model){
    	  List<PersonalContinuityRateAdjust> personalContinuityRateAdjust;
    	  personalContinuityRateAdjust= personalContinuityRateAdjustClient.getPersonalContinuityRateAdjustList();
    	  model.addAttribute("personalContinuityRateAdjust",personalContinuityRateAdjust);
        return "parameter/PersonalContinuityRateAdjustList";
    }
    
    

    /**
     * 分页查询个人继续率调节LIst
     * */
    @RequestMapping("do_personal_adjust")
    @ResponseBody
    public DataMsg selectPersonalContinuityRateAdjust(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = personalContinuityRateAdjustClient.selectPersonalContinuityRateAdjust(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("个人继续率调节[查询]异常");
        e.printStackTrace();
    }

        return  dataMsg;
    }
    /**
     * 增加个人继续率调节
     * */
    /*@RequiresPermissions("nurtureBonus:add")*/
    @RequestMapping("add_personal_adjust")
    @ResponseBody
    public DataMsg addPersonalContinuityRateAdjust(DataMsg msg,
							    		String[] continuityRate,
							    		String[] maximum,
							    		String[] minimum,
							    		String[] minSign,
							    		String[] maxSign,
							    		String[] adjId){
    	PersonalContinuityRateAdjust rateAdjustUpdate= new PersonalContinuityRateAdjust();
    	PersonalContinuityRateAdjust rateAdjustadd= new PersonalContinuityRateAdjust();
        try{
        	
        	int x=continuityRate.length;
        	int y=adjId.length;
        for(int i=0;i<x;i++){
        	if(y-(i+1)>=0){
        	 rateAdjustUpdate.setContinuityRate(continuityRate[i]);
			 rateAdjustUpdate.setId(Long.parseLong(adjId[i]));
			 rateAdjustUpdate.setMaximum(maximum[i]);
			 rateAdjustUpdate.setMaxSign(maxSign[i]);
			 rateAdjustUpdate.setMinSign(minSign[i]);
			 rateAdjustUpdate.setMinimum(minimum[i]);
    		 logger.info("修改部长育成奖金数据："+rateAdjustUpdate);
    		 personalContinuityRateAdjustClient.updatePersonalContinuityRateAdjust(rateAdjustUpdate);
        
            }else{
            	
             rateAdjustadd.setContinuityRate(continuityRate[i]);
   			 rateAdjustadd.setMaximum(maximum[i]);
   			 rateAdjustadd.setMaxSign(maxSign[i]);
   			 rateAdjustadd.setMinSign(minSign[i]);
   			 rateAdjustadd.setMinimum(minimum[i]);
      	    logger.info("新增个人继续率调节数据："+rateAdjustadd);
            personalContinuityRateAdjustClient.addPersonalContinuityRateAdjust(rateAdjustadd);
          
        	 }
        }	
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("个人继续率调节[新增]异常");
        }
            return msg;
    }
    /**
     * 修改个人继续率调节
     * */
   /* @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_personal_adjust")
    @ResponseBody
    public DataMsg updatePersonalContinuityRateAdjust(PersonalContinuityRateAdjust personalContinuityRateAdjust,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+personalContinuityRateAdjust);
            personalContinuityRateAdjustClient.updatePersonalContinuityRateAdjust(personalContinuityRateAdjust);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("个人继续率调节[修改]异常");
        }
return  msg;
    }

    
    /**
 	 * 删除时只保留一条
 	 * @return
 	 */
    @RequestMapping(value = "/check_personal_adjust_size")
    @ResponseBody
    public int checkPersonalAdjustSize(@RequestParam(value = "id", required = true)String id,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("id", id);
            	size= personalContinuityRateAdjustClient.checkPersonalAdjustSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    /**
     * 删除时修改状态
     * */
  /*  @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_personal_adjust_status")
    @ResponseBody
    public DataMsg updateDirectorStatus(@RequestParam(value = "id", required = true)String id,DataMsg msg){
    	PersonalContinuityRateAdjust personalContinuityRateAdjust=new PersonalContinuityRateAdjust();
    	try{
    		
            logger.info("增员津贴id"+personalContinuityRateAdjust);
            personalContinuityRateAdjust.setId(Long.parseLong(id));
            personalContinuityRateAdjust.setIsNormal("1");
            personalContinuityRateAdjustClient.updatePersonalContinuityRateAdjust(personalContinuityRateAdjust);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("增员津贴[修改]异常");
        }
return  msg;
    }

}
