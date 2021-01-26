package com.hzcf.core.mapper;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;

import java.util.List;
import java.util.Map;

public interface DirectorAllowanceStandardMapper {
    List<Map<String, Object>> getAllowanceList(Map<String, Object> params);

    long getAllowanceListSize(Map<String, Object> params);

    void addDirectorAllowance(DirectorAllowanceStandardPojo directorAllowanceStandardPojo);

    void updateDirectorAllowance(DirectorAllowanceStandardPojo directorAllowanceStandardPojo);

    void delDirectorAllowance(String[] split);
}
