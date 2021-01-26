package com.hzcf.core.service.cal;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalDirectlyUnderDept;

import java.util.List;
import java.util.Map;

/**
 *直辖部管理津贴 参数
 * Created by qin lina on 2020/10/19.
 */
public interface CalDirectlyUnderDeptService {
    List<CalDirectlyUnderDept> getCalDirectlyUnderDeptByOrgId(Map<String, Object> map);

    int updateIncreaseState(CalDirectlyUnderDept calDirectlyUnderDept);

    int insertIgnoreNull(CalDirectlyUnderDept calDirectlyUnderDept);


    PageModel getCalHisDeptPage(Map<String, Object> params);

    List<CalDirectlyUnderDept> getFirstCalHisDeptByVersionId(Map<String, Object> params);
}
