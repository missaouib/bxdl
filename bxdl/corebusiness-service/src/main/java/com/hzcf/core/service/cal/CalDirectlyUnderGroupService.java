package com.hzcf.core.service.cal;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalDirectlyUnderGroup;
import com.hzcf.pojo.cal.CalHisDirectlyUnderGroup;

import java.util.List;
import java.util.Map;

/**
 * 直辖组管理津贴参数
 * Created by qin lina on 2020/10/19.
 */
public interface CalDirectlyUnderGroupService {
    List<CalDirectlyUnderGroup> getCalDirectlyUnderGroupByOrgId(Map<String, Object> param);

    int insertIgnoreNull(CalDirectlyUnderGroup calDirectlyUnderGroup);

    int updateIncreaseState(CalDirectlyUnderGroup calDirectlyUnderGroup);

    PageModel getCalHisDirectlyUnderGroupPage(Map<String, Object> params);

    List<CalHisDirectlyUnderGroup> getFirstCalDirectlyUnderGroupByVersionId(Map<String, Object> params);
}
