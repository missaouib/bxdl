package com.hzcf.core.service.impl.cal;

import com.hzcf.core.mapper.cal.CalHisNurtureMinisterMapper;
import com.hzcf.core.mapper.cal.CalNurtureMinisterMapper;
import com.hzcf.core.service.cal.CalNurtureMinisterService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalNurtureMinister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/10/19.
 */
@Service
public class CalNurtureMinisterServiceImpl implements CalNurtureMinisterService {
    @Autowired
    private CalNurtureMinisterMapper calNurtureMinisterMapper;

    @Autowired
    private CalHisNurtureMinisterMapper calHisNurtureMinisterMapper;

    @Override
    public List<CalNurtureMinister> getCalNurtureMinisterByOrgId(Map<String, Object> map) {
        return calNurtureMinisterMapper.getCalNurtureMinisterByOrgId(map);
    }

    @Override
    public int updateMinisterNurturingBonus(CalNurtureMinister params) {
        return calNurtureMinisterMapper.updateIgnoreNull(params);
    }

    @Override
    public void addMinisterNurturingBonus(CalNurtureMinister params) {
        calNurtureMinisterMapper.insertIgnoreNull(params);
    }

    /**
     * 查看部长育成奖金参数记录表
     * corebusiness-service
     */
    @Override
    public PageModel getCalHisNurtureMinister(Map<String, Object> params) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        pageModel.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", pageModel.getStartIndex());
        params.put("endIndex", pageModel.getEndIndex());
        List<Map<String, Object>> list = calHisNurtureMinisterMapper.getCalHisNurtureMinister(params);
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list) {
            Map<String,Object> newMap = new HashMap<String,Object>();
            String generative_algebra = String.valueOf(map.get("generative_algebra"));
            BigDecimal fast_year = new BigDecimal(String.valueOf(map.get("fast_year")));
            BigDecimal following_year_and_beyond = new BigDecimal(String.valueOf(map.get("following_year_and_beyond")));
            if("1".equals(generative_algebra)){
                generative_algebra = "直接育成奖金率";
            }else {
                generative_algebra = "第二代育成奖金率";
            }
            StringBuffer fastYear = new StringBuffer();
            StringBuffer followingYear = new StringBuffer();
            fastYear.append(fast_year.multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString()).append("%");
            followingYear.append(following_year_and_beyond.multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString()).append("%");
            newMap.put("generative_algebra",generative_algebra);
            newMap.put("fast_year",String.valueOf(fastYear));
            newMap.put("following_year_and_beyond",String.valueOf(followingYear));
            newList.add(newMap);
        }
        pageModel.setList(newList);
        pageModel.setTotalRecords(new Long(newList.size()));
        return pageModel;
    }
}
