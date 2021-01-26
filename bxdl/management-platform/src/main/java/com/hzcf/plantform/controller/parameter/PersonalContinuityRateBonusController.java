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

import com.hzcf.plantform.feign.parameter.PersonalContinuityRateBonusClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.PersonalContinuityRateAdjust;
import com.hzcf.pojo.parameter.PersonalContinuityRateBonus;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/personal_bonus_manager")
public class PersonalContinuityRateBonusController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PersonalContinuityRateBonusClient personalContinuityRateBonusClient;
    /**
     * 跳转到个人继续率奖金比页面
     * */
    @RequestMapping("to_personal_bonus")
    public String toPersonalContinuityRateBonus(Model model){
    	  List<PersonalContinuityRateBonus> personalContinuityRateBonus;
    	  personalContinuityRateBonus= personalContinuityRateBonusClient.getPersonalContinuityRateBonusList();
    	  model.addAttribute("personalContinuityRateBonus",personalContinuityRateBonus);
        return "parameter/PersonalContinuityRateBonusList";
    }
    
    

    /**
     * 分页查询个人继续率奖金比LIst
     * */
    @RequestMapping("do_personal_bonus")
    @ResponseBody
    public DataMsg selectPersonalContinuityRateBonus(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = personalContinuityRateBonusClient.selectPersonalContinuityRateBonus(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("个人继续率奖金比[查询]异常");
        e.printStackTrace();
    }

        return  dataMsg;
    }
    /**
     * 增加个人继续率奖金比
     * */
  /*  @RequiresPermissions("nurtureBonus:add")*/
    @RequestMapping("add_personal_bonus")
    @ResponseBody
    public DataMsg addPersonalContinuityRateBonus(DataMsg msg,
    		                                        String[] continuityRate,
//										    		String[] continuityRates,
										    		String[] maximum,
										    		String[] minimum,
										    		String[] minSign,
										    		String[] maxSign,
										    		String[] bonId){
    	PersonalContinuityRateBonus updateRateBonus= new PersonalContinuityRateBonus();
    	PersonalContinuityRateBonus addRateBonus= new PersonalContinuityRateBonus();
        try{
        	int x=continuityRate.length;
        	int y=bonId.length;
        for(int i=0;i<x;i++){
        	if(y-(i+1)>=0){
        		updateRateBonus.setContinuityRate(continuityRate[i]);
        		updateRateBonus.setId(Long.parseLong(bonId[i]));
        		updateRateBonus.setMaximum(maximum[i]);
        		updateRateBonus.setMaxSign(maxSign[i]);
			    updateRateBonus.setMinSign(minSign[i]);
			    updateRateBonus.setMinimum(minimum[i]);
    		 logger.info("修改部长育成奖金数据："+updateRateBonus);
    		  personalContinuityRateBonusClient.updatePersonalContinuityRateBonus(updateRateBonus);
        	}else{
        		addRateBonus.setContinuityRate(continuityRate[i]);
        		addRateBonus.setMaximum(maximum[i]);
        		addRateBonus.setMaxSign(maxSign[i]);
			    addRateBonus.setMinSign(minSign[i]);
			    addRateBonus.setMinimum(minimum[i]);
        		 logger.info("新增个人继续率奖金比数据："+addRateBonus);
                 personalContinuityRateBonusClient.addPersonalContinuityRateBonus(addRateBonus);
        	}
        }
           
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("个人继续率奖金比[新增]异常");
        }
            return msg;
    }
    /**
     * 修改个人继续率奖金比
     * */
   /* @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_personal_bonus")
    @ResponseBody
    public DataMsg updateDirectorAllowance(PersonalContinuityRateBonus personalContinuityRateBonus,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+personalContinuityRateBonus);
            personalContinuityRateBonusClient.updatePersonalContinuityRateBonus(personalContinuityRateBonus);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("个人继续率奖金比[修改]异常");
        }
return  msg;
    }
    /**
     * 删除个人继续率奖金比
     * */
    /*@RequiresPermissions("nurtureBonus:del")*/
    @RequestMapping("/del_personal_bonus")
    @ResponseBody
    public DataMsg delPersonalContinuityRateBonus(String ids,DataMsg msg){
        try{
            logger.info("个人继续率奖金比删除数据ID"+ids);
            personalContinuityRateBonusClient.delPersonalContinuityRateBonus(ids);
            msg.setMessageCode("200");

        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("个人继续率奖金比[删除]异常");
        }
        return  msg;
    }
    
    /**
 	 * 删除时只保留一条
 	 * @return
 	 */
    @RequestMapping(value = "/check_personal_bonus_size")
    @ResponseBody
    public int checkPersonalRateBonusSize(@RequestParam(value = "id", required = true)String id,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("id", id);
            	size= personalContinuityRateBonusClient.checkPersonalRateBonusSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    /**
     * 删除时修改状态
     * */
  /*  @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_personal_bonus_status")
    @ResponseBody
    public DataMsg updatePersonalRateBonusStatus(@RequestParam(value = "id", required = true)String id,DataMsg msg){
    	PersonalContinuityRateBonus personalContinuityRateBonus=new PersonalContinuityRateBonus();
    	try{
    		
            logger.info("增员津贴id"+personalContinuityRateBonus);
            personalContinuityRateBonus.setId(Long.parseLong(id));
            personalContinuityRateBonus.setIsNormal("1");
            personalContinuityRateBonusClient.updatePersonalContinuityRateBonus(personalContinuityRateBonus);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("增员津贴[修改]异常");
        }
return  msg;
    }

}
