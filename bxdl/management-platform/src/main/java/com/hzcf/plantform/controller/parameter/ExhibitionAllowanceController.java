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

import com.hzcf.plantform.feign.parameter.ExhibitionAllowanceClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.ExhibitionAllowance;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/exhibition_allowance_manager")
public class ExhibitionAllowanceController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ExhibitionAllowanceClient exhibitionAllowanceClient;
    /**
     * 跳转到展业津贴设置页面
     * */
    @RequestMapping("to_exhibition_allowance")
    public String toExhibitionAllowance(Model model){
    	  List<ExhibitionAllowance> exhibitionAllowance;
    	  exhibitionAllowance= exhibitionAllowanceClient.getExhibitionAllowanceList();
    	  model.addAttribute("exhibitionAllowance",exhibitionAllowance);
        return "parameter/ExhibitionAllowanceList";
    }
    
    

    /**
     * 分页查询展业津贴LIst
     * */
    @RequestMapping("do_exhibition_allowance")
    @ResponseBody
    public DataMsg selectExhibitionAllowance(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = exhibitionAllowanceClient.selectExhibitionAllowance(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("展业津贴[查询]异常");
        e.printStackTrace();
    }

        return  dataMsg;
    }
    /**
     * 增加展业津贴
     * */
 /*   @RequiresPermissions("nurtureBonus:add")*/
    @RequestMapping("add_exhibition_allowance")
    @ResponseBody
    public DataMsg addExhibitionAllowance(DataMsg msg,
								    		String[] allowance,
								    		String[] step,
								    		String[] additional,
								    		String[] isNotIncrease,
								    		String[] maximum,
								    		String[] maxSign,
								    		String[] minimum,
								    		String[] minSign,
								    		String[] exhId){
        try{
        	ExhibitionAllowance ministerAdd=new ExhibitionAllowance();
        	ExhibitionAllowance ministerUpdate=new ExhibitionAllowance();
        	
        	int x=maximum.length;
        	int y=exhId.length;
        for(int i=0;i<x;i++){
        	if(y-(i+1)>=0){
        		ministerUpdate.setAllowance(allowance[i]);
//        		ministerUpdate.setAdditional(additional[i]);
        		ministerUpdate.setIsNotIncrease(isNotIncrease[i]);
        		if(isNotIncrease[i].equals("1")){
        			ministerUpdate.setAdditional(additional[i]);	
        			ministerUpdate.setStep(step[i]);
        		}
        		ministerUpdate.setMaximum(maximum[i]);
        		ministerUpdate.setMaxSign(maxSign[i]);
        		ministerUpdate.setMinimum(minimum[i]);
        		ministerUpdate.setMinSign(minSign[i]);
			    ministerUpdate.setId(Long.parseLong(exhId[i]));
		
    		 logger.info("修改展业津贴数据："+ministerUpdate);
             exhibitionAllowanceClient.updateExhibitionAllowance(ministerUpdate);
        
            }else{
            	
        		ministerAdd.setAllowance(allowance[i]);
//        		ministerAdd.setAdditional(additional[i]);
        		if(isNotIncrease[i].equals("1")){
        			ministerAdd.setAdditional(additional[i]);	
        			ministerAdd.setStep(step[i]);
        		}
       		    ministerAdd.setIsNotIncrease(isNotIncrease[i]);
        		ministerAdd.setMaximum(maximum[i]);
        		ministerAdd.setMaxSign(maxSign[i]);
        		ministerAdd.setMinimum(minimum[i]);
        		ministerAdd.setMinSign(minSign[i]);
        	  logger.info("新增展业津贴数据："+ministerAdd);
              exhibitionAllowanceClient.addExhibitionAllowance(ministerAdd);
          
        	 }
        	
        }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("展业津贴[新增]异常");
        }
            return msg;
    }
    /**
     * 修改展业津贴
     * */
   /* @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_exhibition_allowance")
    @ResponseBody
    public DataMsg updateDirectorAllowance(ExhibitionAllowance exhibitionAllowance,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+exhibitionAllowance);
            exhibitionAllowanceClient.updateExhibitionAllowance(exhibitionAllowance);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("展业津贴[修改]异常");
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
         		
            	size= exhibitionAllowanceClient.checkMinisterSize(params);
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
    	ExhibitionAllowance exhibitionAllowance=new ExhibitionAllowance();
    	try{
    		
            logger.info("展业津贴id"+exhibitionAllowance);
            exhibitionAllowance.setId(Long.parseLong(id));
            exhibitionAllowance.setIsNormal("1");
            exhibitionAllowanceClient.updateExhibitionAllowance(exhibitionAllowance);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("展业津贴[修改]异常");
        }
return  msg;
    }
    
}
