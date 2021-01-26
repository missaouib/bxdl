package com.hzcf.core.service.financialCheck;

import com.hzcf.core.util.PageModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
public interface CheckPolicyDataHkService {
    PageModel getCheckPolicyDataHkPage(Map<String, Object> params);

    /**
     * 上传对账数据
     * @param p
     */
    void insertImportList(Map<String, Object> p);

    void updateDataHK(Map<String, Object> paras);

    /*结算费率*/
    Map<String,BigDecimal> getTotalCost(Map<String, Object> paras);

    void confirmSettle(Map<String, Object> paras);

    PageModel getCheckPolicyDataHkForBatchPage(Map<String, Object> paras);

    List<Map<String,Object>> getCheckPolicyDataHkForBatchAll(Map<String, Object> paras);

    List<Map<String,Object>> getCheckPolicyDataHkByCondition(Map<String, Object> paras);

    void updateImportList(Map<String, Object> p);

}
