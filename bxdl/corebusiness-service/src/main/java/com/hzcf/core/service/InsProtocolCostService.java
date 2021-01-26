package com.hzcf.core.service;

import java.util.Map;

import com.hzcf.core.util.PageModel;

/**
 * <p>寿险协议-计算协议费用接口</p>
 */
public interface InsProtocolCostService {
    /**
     * <p>计算协议费用</p>
     * @param date    计算日期
     * @param protocolType    协议类型（SERVICECHARGE:手续费，RATEADJUST:费率调节）
     * @return
     */
    void getInsProtocolCost(String date, String protocolType);

	PageModel getProtocolCostList(Map<String, Object> paramsCondition);
}
