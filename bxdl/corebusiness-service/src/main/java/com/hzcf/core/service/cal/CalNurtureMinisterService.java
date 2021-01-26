package com.hzcf.core.service.cal;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalNurtureMinister;

import java.util.List;
import java.util.Map;

/**
 * 部育成
 * Created by qin lina on 2020/10/19.
 */
public interface CalNurtureMinisterService {
    List<CalNurtureMinister> getCalNurtureMinisterByOrgId(Map<String, Object> map);

    int updateMinisterNurturingBonus(CalNurtureMinister params);

    void addMinisterNurturingBonus(CalNurtureMinister params);
    /**
     * 查看部长育成奖金参数记录表
     * corebusiness-service
     */
    PageModel getCalHisNurtureMinister(Map<String, Object> params);
}
