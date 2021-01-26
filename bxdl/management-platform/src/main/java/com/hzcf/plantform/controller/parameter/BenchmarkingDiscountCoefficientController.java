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

import com.hzcf.plantform.feign.parameter.BenchmarkingDiscountCoefficientClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.parameter.BenchmarkingDiscountCoefficient;
import com.hzcf.util.StringUtil;

@Controller
@RequestMapping("/benchmarking_discount_manager")
public class BenchmarkingDiscountCoefficientController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BenchmarkingDiscountCoefficientClient benchmarkingDiscountClient;
    /**
     * 跳转到标保折标系数设置页面
     * */
    @RequestMapping("to_bench_discount")
    public String toBenchmarkingDiscountCoefficient(Model model){
    	  List<BenchmarkingDiscountCoefficient> benchmarkingDiscount;
    	  benchmarkingDiscount= benchmarkingDiscountClient.getBenchmarkingDiscountCoefficientList();
    	  model.addAttribute("benchmarkingDiscount",benchmarkingDiscount);
        return "parameter/BenchmarkingDiscountList";
    }
    
    /**
     * 分页查询标保折标系数LIst
     * */
    @RequestMapping("do_benchmarking_discount")
    @ResponseBody
    public DataMsg selectBenchmarkingDiscountCoefficient(HttpServletRequest request,DataMsg dataMsg){
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
        PageModel pageModel = benchmarkingDiscountClient.selectBenchmarkingDiscountCoefficient(paras);
        List list = pageModel.getList();
        dataMsg.setTotal(pageModel.getTotalRecords());
        dataMsg.setRows(pageModel.getList());
        dataMsg.setMessageCode("200");
    }catch (RuntimeException e){
        dataMsg.setMessageCode("400");
        logger.info("标保折标系数[查询]异常",e);
        e.printStackTrace();
    }
        return  dataMsg;
    }
    /**
     * 增加标保折标系数
     * */
 /*   @RequiresPermissions("bench:add")*/
    @RequestMapping("add_benchmarking_discount")
    @ResponseBody
    public DataMsg addBenchmarkingDiscountCoefficient(DataMsg msg,
								    		String[] insuranceTypeId,
								    		String[] status,
								    		String[] maximum,
								    		String[] maxSign,
								    		String[] minimum,
								    		String[] minSign,
								    		String[] indexingCoefficient,
								    		
								    		String[] benId){
        try{
        	BenchmarkingDiscountCoefficient ministerAdd=new BenchmarkingDiscountCoefficient();
        	BenchmarkingDiscountCoefficient ministerUpdate=new BenchmarkingDiscountCoefficient();
        	
        	int x=status.length;
        	int y=benId.length;
        for(int i=0;i<x;i++){
        	if(y-(i+1)>=0){
        		if(status[i].equals("0")){
        			ministerUpdate.setMaximum(maximum[i]);
            		ministerUpdate.setMaxSign(maxSign[i]);
            		ministerUpdate.setMinimum(minimum[i]);
            		ministerUpdate.setMinSign(minSign[i]);
        		}
        		ministerUpdate.setStatus(status[i]);
        		ministerUpdate.setInsuranceTypeId(insuranceTypeId[i]);
			    ministerUpdate.setId(Long.parseLong(benId[i]));
			    ministerUpdate.setIndexingCoefficient(indexingCoefficient[i]);
		
    		 logger.info("修改标保折标系数数据："+ministerUpdate);
             benchmarkingDiscountClient.updateBenchmarkingDiscountCoefficient(ministerUpdate);
        
            }else{
            	if(status[i].equals("0")){
            		ministerAdd.setMaximum(maximum[i]);
            		ministerAdd.setMaxSign(maxSign[i]);
            		ministerAdd.setMinimum(minimum[i]);
            		ministerAdd.setMinSign(minSign[i]);
        		}
            	ministerAdd.setStatus(status[i]);
            	ministerAdd.setInsuranceTypeId(insuranceTypeId[i]);
            	ministerAdd.setIndexingCoefficient(indexingCoefficient[i]);
        	  logger.info("新增标保折标系数数据："+ministerAdd);
              benchmarkingDiscountClient.addBenchmarkingDiscountCoefficient(ministerAdd);
          
        	 }
        	
        }
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("标保折标系数[新增]异常");
        }
            return msg;
    }
    /**
     * 修改标保折标系数
     * */
   /* @RequiresPermissions("bench:update")*/
    @RequestMapping("/update_bench_discount")
    @ResponseBody
    public DataMsg updateDirectorAllowance(BenchmarkingDiscountCoefficient benchmarkingDiscount,DataMsg msg){
        try{
            logger.info("修改总监晋贴吧数据"+benchmarkingDiscount);
            benchmarkingDiscountClient.updateBenchmarkingDiscountCoefficient(benchmarkingDiscount);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("标保折标系数[修改]异常");
        }
       return  msg;
    }

    /**
 	 * 删除时只保留一条:共用
 	 * @return
 	 */
    @RequestMapping(value = "/check_benchmarking_discount_size")
    @ResponseBody
    public int checkBenchmarkingDiscountSize(@RequestParam(value = "id", required = true)String id,HttpServletRequest request,DataMsg dataMsg) {
    	int  size=0;
        try {
       		 Map<String, Object> params = new HashMap<String, Object>();
         		params.put("id", id);
         		
            	size= benchmarkingDiscountClient.checkBenchmarkingDiscountSize(params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }
    /**
     * 删除时修改状态
     * */
  /*  @RequiresPermissions("bench:update")*/
    @RequestMapping("/update_benchmarking_discount_status")
    @ResponseBody
    public DataMsg updateDirectorStatus(@RequestParam(value = "id", required = true)String id,DataMsg msg){
    	BenchmarkingDiscountCoefficient benchmarkingDiscount=new BenchmarkingDiscountCoefficient();
    	try{
    		
            logger.info("标保折标系数id"+benchmarkingDiscount);
            benchmarkingDiscount.setId(Long.parseLong(id));
            benchmarkingDiscount.setIsNormal("1");
            benchmarkingDiscountClient.updateBenchmarkingDiscountCoefficient(benchmarkingDiscount);
            msg.setMessageCode("200");
        }catch (RuntimeException e){
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("标保折标系数[修改]异常");
        }
return  msg;
    }
    
}
