package com.hzcf.core.service.financialCheck;

import com.hzcf.core.util.PageModel;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/9.
 */
public interface CheckPolicyDataCompService {
    PageModel getCheckPolicyDataCompPage(Map<String, Object> params);

    /**
     * 上传对账数据
     * @param p
     */
    void insertImportList(Map<String, Object> p);

    void beginComparison(Map<String, Object> paras);

    List<Map<String,Object>> getCheckPolicyDataCompAll(Map<String, Object> paras);

    Map<String,Object> getResultNumber(Map<String, Object> paras);
}
