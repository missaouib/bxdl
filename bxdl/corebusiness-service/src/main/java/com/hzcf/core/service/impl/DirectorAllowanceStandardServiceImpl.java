package com.hzcf.core.service.impl;

import com.hzcf.core.mapper.DirectorAllowanceStandardMapper;
import com.hzcf.core.service.DirectorAllowanceStandardService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.insurance.InsuranceDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DirectorAllowanceStandardServiceImpl implements DirectorAllowanceStandardService {
    /**
     * qq
     */
    @Autowired
    private DirectorAllowanceStandardMapper directorAllowanceStandardMapper;


    @Override
    public PageModel doDirectorAllowance(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSizeInt", Integer.valueOf(params.get("pageSize").toString())) ;
        params.put("pageNoInt",Integer.valueOf(params.get("pageNo").toString()));
        List<Map<String,Object>> list = directorAllowanceStandardMapper.getAllowanceList(params);
        long size = directorAllowanceStandardMapper.getAllowanceListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public void addDirectorAllowance(DirectorAllowanceStandardPojo directorAllowanceStandardPojo) {
        directorAllowanceStandardMapper.addDirectorAllowance(directorAllowanceStandardPojo);
    }

    @Override
    public void updateDirectorAllowance(DirectorAllowanceStandardPojo directorAllowanceStandardPojo) {
        directorAllowanceStandardMapper.updateDirectorAllowance(directorAllowanceStandardPojo);
    }

    @Override
    public void delDirectorAllowance(String ids) {
        String[] split = ids.split(",");
        directorAllowanceStandardMapper.delDirectorAllowance(split);
    }
}
