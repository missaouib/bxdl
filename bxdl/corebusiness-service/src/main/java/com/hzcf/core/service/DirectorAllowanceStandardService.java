package com.hzcf.core.service;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface DirectorAllowanceStandardService {

    PageModel doDirectorAllowance(Map<String, Object> paras);

    void addDirectorAllowance(DirectorAllowanceStandardPojo directorAllowanceStandardPojo);

    void updateDirectorAllowance(DirectorAllowanceStandardPojo directorAllowanceStandardPojo);

    void delDirectorAllowance(String ids);
}
