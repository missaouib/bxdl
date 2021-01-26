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

import com.hzcf.plantform.feign.parameter.IncreaseAllowanceClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.IncreaseAllowance;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/increase_allowance_manager")
public class IncreaseAllowanceController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IncreaseAllowanceClient increaseAllowanceClient;
    /**
     * 跳转到增员津贴设置页面
     * */
    @RequestMapping("to_increase_allowance")
    public String toIncreaseAllowance(Model model){
    	  List<IncreaseAllowance> increaseAllowance;
    	  increaseAllowance= increaseAllowanceClient.getIncreaseAllowanceList();
    	  model.addAttribute("increaseAllowance",increaseAllowance);
        return "parameter/IncreaseAllowanceList";
    }
    
    /**
     * 分页查询增员津贴LIst
     * */
    @RequestMapping("do_increase_allowance")
    @ResponseBody
    public DataMsg selectIncreaseAllowance(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = increaseAllowanceClient.selectIncreaseAllowance(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("增员津贴[查询]异常");
        e.printStackTrace();
    }

        return  dataMsg;
    }
    /**
     * 增加增员津贴
     * */
 /*   @RequiresPermissions("nurtureBonus:add")*/
    @RequestMapping("add_increase_allowance")
    @ResponseBody
    public DataMsg addIncreaseAllowance(DataMsg msg,
								    		String[] exhibitionAllowanceProportion,
								    		String[] settings,
								    		String[] maximum,
								    		String[] maxSign,
								    		String[] minimum,
								    		String[] minSign,
								    		String[] incId){
        try{
        	IncreaseAllowance ministerAdd=new IncreaseAllowance();
        	IncreaseAllowance ministerUpdate=new IncreaseAllowance();
        	
        	int x=exhibitionAllowanceProportion.length;
        	int y=incId.length;
        for(int i=0;i<x;i++){
        	if(y-(i+1)>=0){
        		ministerUpdate.setExhibitionAllowanceProportion(exhibitionAllowanceProportion[i]);
        		ministerUpdate.setSettings(settings[i]);
        		ministerUpdate.setMaximum(maximum[i]);
        		ministerUpdate.setMaxSign(maxSign[i]);
        		ministerUpdate.setMinimum(minimum[i]);
        		ministerUpdate.setMinSign(minSign[i]);
			    ministerUpdate.setId(Long.parseLong(incId[i]));
		
    		 logger.info("修改增员津贴数据："+ministerUpdate);
             increaseAllowanceClient.updateIncreaseAllowance(ministerUpdate);
        
            }else{            	
            	ministerAdd.setExhibitionAllowanceProportion(exhibitionAllowanceProportion[i]);
            	ministerAdd.setSettings(settings[i]);
        		ministerAdd.setMaximum(maximum[i]);
        		ministerAdd.setMaxSign(maxSign[i]);
        		ministerAdd.setMinimum(minimum[i]);
        		ministerAdd.setMinSign(minSign[i]);
        	  logger.info("新增增员津贴数据："+ministerAdd);
              increaseAllowanceClient.addIncreaseAllowance(ministerAdd);
          
        	 }
        	
        }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("增员津贴[新增]异常");
        }
            return msg;
    }
    /**
     * 修改增员津贴
     * */
   /* @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_increase_allowance")
    @ResponseBody
    public DataMsg updateIncreaseAllowance(IncreaseAllowance increaseAllowance,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+increaseAllowance);
            increaseAllowanceClient.updateIncreaseAllowance(increaseAllowance);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("增员津贴[修改]异常");
        }
       return  msg;
    }

    /**
 	 * 删除时只保留一条:共用
 	 * @return
 	 */
    @RequestMapping(value = "/check_increase_allowance_size")
    @ResponseBody
    public int checkIncreaseAllowanceSize(@RequestParam(value = "id", required = true)String id,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("id", id);
         		
            	size= increaseAllowanceClient.checkMinisterSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    /**
     * 删除时修改状态
     * */
  /*  @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_increase_allowance_status")
    @ResponseBody
    public DataMsg updateIncreaseAllowanceStatus(@RequestParam(value = "id", required = true)String id,DataMsg msg){
    	IncreaseAllowance increaseAllowance=new IncreaseAllowance();
    	try{
    		
            logger.info("增员津贴id"+increaseAllowance);
            increaseAllowance.setId(Long.parseLong(id));
            increaseAllowance.setIsNormal("1");
            increaseAllowanceClient.updateIncreaseAllowance(increaseAllowance);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("增员津贴[修改]异常");
        }
return  msg;
    }
    
}
