package com.hzcf.core.controller.financialCheck;

import com.hzcf.core.service.financialCheck.CheckPolicyDataCompService;
import com.hzcf.core.util.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
@RestController
@RequestMapping("check_policy_data_comp")
public class CheckPolicyDataCompController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CheckPolicyDataCompService checkPolicyDataCompService;
    @RequestMapping("/getCheckPolicyDataCompPage")
    public PageModel getCheckPolicyDataCompPage(@RequestBody Map<String, Object> params){
        return checkPolicyDataCompService.getCheckPolicyDataCompPage(params);
    }

    @RequestMapping(value = "insertImportList",method = RequestMethod.POST)
     public Map<String,Object> insertImportList(@RequestBody Map<String, Object> p){
        Map<String, Object> map = new HashMap<>();
          try {
            checkPolicyDataCompService.insertImportList(p);
            map.put("code", 200);
        } catch (RuntimeException e) {
            logger.error("批量导入异常",e);
            map.put("code", 400);
        }
        return map;
    }

    @PostMapping(value = "/beginComparison")
   public Map<String, Object> beginComparison(@RequestBody Map<String, Object> paras){
        Map<String, Object> map = new HashMap<>();
        try {
            checkPolicyDataCompService.beginComparison(paras);
            map.put("code",200);
        } catch (Exception e) {
            map.put("code",400);
            map.put("msg",e.getMessage());
            logger.error("批次:"+paras.get("batchNum")+"比对失败",e);
        }
        return map;
    }

      /*根据条件查询比对结果 不分页*/
    @PostMapping(value = "/getCheckPolicyDataCompAll")
   public List<Map<String,Object>> getCheckPolicyDataCompAll(@RequestBody  Map<String, Object> paras){
        return checkPolicyDataCompService.getCheckPolicyDataCompAll(paras);
    }

    @PostMapping(value = "/getResultNumber")
   public Map<String,Object> getResultNumber(@RequestBody Map<String, Object> paras){
       return checkPolicyDataCompService.getResultNumber(paras);
    }

}
