package com.hzcf.core.controller.financialCheck;

import com.hzcf.core.service.financialCheck.CheckPolicyDataHkService;
import com.hzcf.core.util.PageModel;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
@RestController
@RequestMapping("check_policy_data_hk")
public class CheckPolicyDataHkController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CheckPolicyDataHkService checkPolicyDataHkService;
    @RequestMapping("/getCheckPolicyDataHkPage")
    public PageModel getCheckPolicyDataHkPage(@RequestBody Map<String, Object> params){
        return checkPolicyDataHkService.getCheckPolicyDataHkPage(params);
    }

    @RequestMapping(value = "insertImportList",method = RequestMethod.POST)
     public Map<String,Object> insertImportList(@RequestBody Map<String, Object> p){
        Map<String, Object> map = new HashMap<>();
          try {
            checkPolicyDataHkService.insertImportList(p);
            map.put("code", 200);
        } catch (RuntimeException e) {
            logger.error("批量导入异常",e);
            map.put("code", 400);
        }
        return map;
    }

    @RequestMapping(value = "/updateDataHK",method = RequestMethod.POST)
    public void  updateDataHK(@RequestBody Map<String, Object> paras){
            checkPolicyDataHkService.updateDataHK(paras);
    }

    @RequestMapping(value = "/getTotalCost",method = RequestMethod.POST)
    public  Map<String,BigDecimal> getTotalCost(@RequestBody  Map<String, Object> paras){
        return checkPolicyDataHkService.getTotalCost(paras);
    }

    @RequestMapping(value = "/confirmSettle",method = RequestMethod.POST)
    public  void confirmSettle(@RequestBody  Map<String, Object> paras){
       checkPolicyDataHkService.confirmSettle(paras);
    }

    @RequestMapping(value = "/getCheckPolicyDataHkForBatchPage")
    public PageModel getCheckPolicyDataHkForBatchPage(@RequestBody Map<String, Object> paras){
         return checkPolicyDataHkService.getCheckPolicyDataHkForBatchPage(paras);
    }



    @RequestMapping(value = "/getCheckPolicyDataHkForBatchAll")
   public List<Map<String,Object>> getCheckPolicyDataHkForBatchAll(@RequestBody Map<String, Object> paras){
         return checkPolicyDataHkService.getCheckPolicyDataHkForBatchAll(paras);
    }


     @PostMapping(value = "/getCheckPolicyDataHkByCondition")
    public List<Map<String,Object>> getCheckPolicyDataHkByCondition(@RequestBody Map<String, Object> paras){
        return checkPolicyDataHkService.getCheckPolicyDataHkByCondition(paras);
     }

     @PostMapping(value = "/updateImportList")
   public Map<String,Object> updateImportList(@RequestBody Map<String, Object> p){
       Map<String, Object> map = new HashMap<>();
          try {
            checkPolicyDataHkService.updateImportList(p);
            map.put("code", 200);
        } catch (RuntimeException e) {
            logger.error("批量修改异常",e);
            map.put("code", 400);
        }
        return map;
    }

}
