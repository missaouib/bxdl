package com.hzcf.core.service.cal;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalPromote;

import java.util.List;
import java.util.Map;

/**
 * 展业津贴
 * Created by qin lina on 2020/10/19.
 */
public interface CalPromoteSerivce {

    List<CalPromote> getCalPromoteByOrgId(Map<String, Object> params);

    int insertIgnoreNull(CalPromote calPromote);

    int updateIgnoreNull(CalPromote calPromote);

    PageModel getCalHisPromotePage(Map<String, Object> params);
}
