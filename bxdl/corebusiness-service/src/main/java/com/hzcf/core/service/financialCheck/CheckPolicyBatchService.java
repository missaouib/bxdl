package com.hzcf.core.service.financialCheck;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.financialCheck.CheckPolicyBatch;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/10.
 */
public interface CheckPolicyBatchService {
    PageModel getCheckPolicyBatchPage(Map<String, Object> params);

    void addCheckPolicyBatch(CheckPolicyBatch checkPolicyBatch);

    List<CheckPolicyBatch> getCheckPolicyBatchByCondition(Map<String, Object> params);
}
