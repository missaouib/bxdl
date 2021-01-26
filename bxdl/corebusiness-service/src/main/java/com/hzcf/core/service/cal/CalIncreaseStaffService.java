package com.hzcf.core.service.cal;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalHisIncreaseStaff;
import com.hzcf.pojo.cal.CalIncreaseStaff;

import java.util.List;
import java.util.Map;

/**
 * 增员津贴参数
 * Created by qin lina on 2020/10/19.
 */
public interface CalIncreaseStaffService {
    List<CalIncreaseStaff> getCalIncreaseStaffByOrgId(Map<String, Object> par);

    int updateIncreaseState(CalIncreaseStaff calIncreaseStaff);

    int insertIgnoreNull(CalIncreaseStaff calIncreaseStaff);

    PageModel getCalHisIncreasePage(Map<String, Object> params);

    List<CalHisIncreaseStaff> getFirstCalHisIncreaseByVersionId(Map<String, Object> params);
}
