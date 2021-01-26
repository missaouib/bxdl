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

import com.hzcf.plantform.feign.parameter.DirectlyUnderManagerClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.DirectlyUnderManager;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/directly_under_manager")
public class DirectlyUnderManagerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DirectlyUnderManagerClient directlyUnderManagerClient;
    /**
     * 跳转到直辖部管理津贴发放系数设置页面
     * */
    @RequestMapping("to_directly_under")
    public String toDirectlyUnderManager(Model model){
    	  List<DirectlyUnderManager> directlyUnderManager;
    	  directlyUnderManager= directlyUnderManagerClient.getDirectlyUnderManagerList();
    	  model.addAttribute("directlyUnderManager",directlyUnderManager);
        return "parameter/DirectlyUnderManagerList";
    }
    
    /**
     * 分页查询直辖部管理津贴发放系数设置LIst
     * */
    @RequestMapping("do_directly_under")
    @ResponseBody
    public DataMsg selectDirectlyUnderManager(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = directlyUnderManagerClient.selectDirectlyUnderManager(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("直辖部管理津贴发放系数[查询]异常");
        e.printStackTrace();
    }

        return  dataMsg;
    }
    /**
     * 增加直辖部管理津贴发放系数
     * */
 /*   @RequiresPermissions("nurtureBonus:add")*/
    @RequestMapping("add_directly_under")
    @ResponseBody
    public DataMsg addDirectlyUnderManager(DataMsg msg,
								    		String[] allowance,
								    		String[] maximum,
								    		String[] maxSign,
								    		String[] minimum,
								    		String[] minSign,
								    		String[] dirId){
        try{
        	DirectlyUnderManager ministerAdd=new DirectlyUnderManager();
        	DirectlyUnderManager ministerUpdate=new DirectlyUnderManager();
        	
        	int x=maximum.length;
        	int y=dirId.length;
        for(int i=0;i<x;i++){
        	if(y-(i+1)>=0){
        		ministerUpdate.setAllowance(allowance[i]);
        		ministerUpdate.setMaximum(maximum[i]);
        		ministerUpdate.setMaxSign(maxSign[i]);
        		ministerUpdate.setMinimum(minimum[i]);
        		ministerUpdate.setMinSign(minSign[i]);
			    ministerUpdate.setId(Long.parseLong(dirId[i]));
		
    		 logger.info("修改直辖部管理津贴发放系数数据："+ministerUpdate);
             directlyUnderManagerClient.updateDirectlyUnderManager(ministerUpdate);
        
            }else{
            	ministerAdd.setAllowance(allowance[i]);
        		ministerAdd.setMaximum(maximum[i]);
        		ministerAdd.setMaxSign(maxSign[i]);
        		ministerAdd.setMinimum(minimum[i]);
        		ministerAdd.setMinSign(minSign[i]);
        	  logger.info("新增直辖部管理津贴发放系数数据："+ministerAdd);
              directlyUnderManagerClient.addDirectlyUnderManager(ministerAdd);
          
        	 }
        	
        }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖部管理津贴发放系数[新增]异常");
        }
            return msg;
    }
    /**
     * 修改直辖部管理津贴发放系数
     * */
   /* @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_directly_under")
    @ResponseBody
    public DataMsg updateDirectorAllowance(DirectlyUnderManager directlyUnderManager,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+directlyUnderManager);
            directlyUnderManagerClient.updateDirectlyUnderManager(directlyUnderManager);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖部管理津贴发放系数[修改]异常");
        }
       return  msg;
    }

    /**
 	 * 删除时只保留一条
 	 * @return
 	 */
    @RequestMapping(value = "/check_directly_under_size")
    @ResponseBody
    public int checkDirectlyAdministerSize(@RequestParam(value = "id", required = true)String id,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("id", id);
         		
            	size= directlyUnderManagerClient.checkDirectlyUnderManagerSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    /**
     * 删除时修改状态
     * */
  /*  @RequiresPermissions("nurtureBonus:update")*/
    @RequestMapping("/update_directly_under_status")
    @ResponseBody
    public DataMsg updateDirectorStatus(@RequestParam(value = "id", required = true)String id,DataMsg msg){
    	DirectlyUnderManager directlyUnderManager=new DirectlyUnderManager();
    	try{
    		
            logger.info("直辖部管理津贴发放系数id"+directlyUnderManager);
            directlyUnderManager.setId(Long.parseLong(id));
            directlyUnderManager.setIsNormal("1");
            directlyUnderManagerClient.updateDirectlyUnderManager(directlyUnderManager);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖部管理津贴发放系数[修改]异常");
        }
return  msg;
    }
    
}
