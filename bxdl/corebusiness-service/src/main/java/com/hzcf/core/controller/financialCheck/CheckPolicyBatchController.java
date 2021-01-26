package com.hzcf.core.controller.financialCheck;

import com.hzcf.core.service.financialCheck.CheckPolicyBatchService;
import com.hzcf.core.service.financialCheck.CheckPolicyDataCompService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.financialCheck.CheckPolicyBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/10.
 */
@RestController
@RequestMapping("check_policy_batch")
public class CheckPolicyBatchController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CheckPolicyBatchService checkPolicyBatchService;
    @RequestMapping("/getCheckPolicyBatchPage")
    public PageModel getCheckPolicyBatchPage(@RequestBody Map<String, Object> params){
        return checkPolicyBatchService.getCheckPolicyBatchPage(params);
    }

    @RequestMapping("/addCheckPolicyBatch")
    public void addCheckPolicyBatch(@RequestBody CheckPolicyBatch checkPolicyBatch){
        checkPolicyBatchService.addCheckPolicyBatch(checkPolicyBatch);
    }

    @RequestMapping("/getCheckPolicyBatchByCondition")
    public List<CheckPolicyBatch> getCheckPolicyBatchByCondition(@RequestBody Map<String,Object> params){
        return checkPolicyBatchService.getCheckPolicyBatchByCondition(params);
    }
}
