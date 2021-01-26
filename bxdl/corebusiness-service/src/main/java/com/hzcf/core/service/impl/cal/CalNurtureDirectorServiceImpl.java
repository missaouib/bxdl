package com.hzcf.core.service.impl.cal;

import com.hzcf.core.mapper.cal.CalHisNurtureDirectorMapper;
import com.hzcf.core.mapper.cal.CalNurtureDirectorMapper;
import com.hzcf.core.service.cal.CalNurtureDirectorService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalNurtureDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/10/19.
 */
@Service
public class CalNurtureDirectorServiceImpl implements CalNurtureDirectorService {
    @Autowired
    private CalNurtureDirectorMapper calNurtureDirectorMapper;
    @Autowired
    private CalHisNurtureDirectorMapper calHisNurtureDirectorMapper;

    @Override
    public List<CalNurtureDirector> getCalNurtureDirectorByOrgId(Map<String, Object> paras) {
        return calNurtureDirectorMapper.getCalNurtureDirectorByOrgId(paras);
    }

    @Override
    public void updateCalNurtureDirector(CalNurtureDirector params) {
        calNurtureDirectorMapper.updateIgnoreNull(params);
    }

    @Override
    public void addCalNurtureDirector(CalNurtureDirector params) {
        calNurtureDirectorMapper.insertIgnoreNull(params);
    }

    @Override
    public PageModel getCalHisNurtureDirectorPage(Map<String, Object> params) {
       PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        pageModel.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", pageModel.getStartIndex());
        params.put("endIndex", pageModel.getEndIndex());
        // 查询数据
        List<Map<String, Object>> list = calHisNurtureDirectorMapper.getCalHisNurtureDirectorPage(params);
        // 查询条数
        Long count = calHisNurtureDirectorMapper.getCalHisNurtureDirectorCount(params);
        pageModel.setList(list);
        pageModel.setTotalRecords(count);
        return pageModel;
    }
}
