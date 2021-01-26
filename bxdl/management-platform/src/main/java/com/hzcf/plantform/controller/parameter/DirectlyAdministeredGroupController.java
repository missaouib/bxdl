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

import com.hzcf.plantform.feign.parameter.DirectlyAdministeredGroupClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.DirectlyAdministeredGroup;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/directly_administer_manager")
public class DirectlyAdministeredGroupController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DirectlyAdministeredGroupClient directlyAdministeredGroupClient;
    /**
     * 跳转到直辖组管理津贴设置页面
     * */
    @RequestMapping("to_directly_administer")
    public String toDirectlyAdministeredGroup(Model model){
    	  List<DirectlyAdministeredGroup> directlyAdministeredGroup;
    	  directlyAdministeredGroup= directlyAdministeredGroupClient.getDirectlyAdministeredGroupList();
    	  model.addAttribute("directlyAdministeredGroup",directlyAdministeredGroup);
        return "parameter/DirectlyAdministeredGroupList";
    }
    
    /**
     * 分页查询直辖组管理津贴设置LIst
     * */
    @RequestMapping("do_directly_administer")
    @ResponseBody
    public DataMsg selectDirectlyAdministeredGroup(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = directlyAdministeredGroupClient.selectDirectlyAdministeredGroup(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("直辖组管理津贴[查询]异常");
        e.printStackTrace();
    }

        return  dataMsg;
    }
    /**
     * 增加直辖组管理津贴
     * */
 /*   @RequiresPermissions("directly:add")*/
    @RequestMapping("add_directly_administer")
    @ResponseBody
    public DataMsg addDirectlyAdministeredGroup(DataMsg msg,
								    		String[] allowance,
								    		String[] maximum,
								    		String[] maxSign,
								    		String[] minimum,
								    		String[] minSign,
								    		String[] admId){
        try{
        	DirectlyAdministeredGroup ministerAdd=new DirectlyAdministeredGroup();
        	DirectlyAdministeredGroup ministerUpdate=new DirectlyAdministeredGroup();
        	
        	int x=maximum.length;
        	int y=admId.length;
        for(int i=0;i<x;i++){
        	if(y-(i+1)>=0){
        		ministerUpdate.setAllowance(allowance[i]);
        		ministerUpdate.setMaximum(maximum[i]);
        		ministerUpdate.setMaxSign(maxSign[i]);
        		ministerUpdate.setMinimum(minimum[i]);
        		ministerUpdate.setMinSign(minSign[i]);
			    ministerUpdate.setId(Long.parseLong(admId[i]));
		
    		 logger.info("修改直辖组管理津贴数据："+ministerUpdate);
             directlyAdministeredGroupClient.updateDirectlyAdministeredGroup(ministerUpdate);
            }else{
            	
            	ministerAdd.setAllowance(allowance[i]);
        		ministerAdd.setMaximum(maximum[i]);
        		ministerAdd.setMaxSign(maxSign[i]);
        		ministerAdd.setMinimum(minimum[i]);
        		ministerAdd.setMinSign(minSign[i]);
        	  logger.info("新增直辖组管理津贴数据："+ministerAdd);
              directlyAdministeredGroupClient.addDirectlyAdministeredGroup(ministerAdd);
        	 }
        }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖组管理津贴[新增]异常");
        }
            return msg;
    }
    /**
     * 修改直辖组管理津贴
     * */
   /* @RequiresPermissions("directly:update")*/
    @RequestMapping("/update_directly_administer")
    @ResponseBody
    public DataMsg updateDirectorAllowance(DirectlyAdministeredGroup directlyAdministeredGroup,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+directlyAdministeredGroup);
            directlyAdministeredGroupClient.updateDirectlyAdministeredGroup(directlyAdministeredGroup);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖组管理津贴[修改]异常");
        }
       return  msg;
    }

    /**
 	 * 删除时只保留一条
 	 * @return
 	 */
    @RequestMapping(value = "/check_benchmarking_size")
    @ResponseBody
    public int checkDirectlyAdministerSize(@RequestParam(value = "id", required = true)String id,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("id", id);
         		
            	size= directlyAdministeredGroupClient.checkDirectlyAdministerSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    /**
     * 删除时修改状态
     * */
  /*  @RequiresPermissions("directly:update")*/
    @RequestMapping("/update_benchmarking_status")
    @ResponseBody
    public DataMsg updateDirectorStatus(@RequestParam(value = "id", required = true)String id,DataMsg msg){
    	DirectlyAdministeredGroup directlyAdministeredGroup=new DirectlyAdministeredGroup();
    	try{
    		
            logger.info("直辖组管理津贴id"+directlyAdministeredGroup);
            directlyAdministeredGroup.setId(Long.parseLong(id));
            directlyAdministeredGroup.setIsNormal("1");
            directlyAdministeredGroupClient.updateDirectlyAdministeredGroup(directlyAdministeredGroup);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("直辖组管理津贴[修改]异常");
        }
return  msg;
    }
    
}
