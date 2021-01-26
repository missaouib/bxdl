package com.hzcf.core.service.cal;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalNurtureDirector;

import java.util.List;
import java.util.Map;

/**
 * 处育成系数
 * 
 * Created by qin lina on 2020/10/19.
 */
public interface CalNurtureDirectorService {
    List<CalNurtureDirector> getCalNurtureDirectorByOrgId(Map<String, Object> paras);

    void updateCalNurtureDirector(CalNurtureDirector params);

    void addCalNurtureDirector(CalNurtureDirector params);

    PageModel getCalHisNurtureDirectorPage(Map<String, Object> params);
}
