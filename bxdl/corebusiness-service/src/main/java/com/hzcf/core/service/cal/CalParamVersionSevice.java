package com.hzcf.core.service.cal;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalParamVersion;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: liuweichen
 * @Date: 2020/10/23/10:53
 * @Description:
 */
public interface CalParamVersionSevice {

    List<CalParamVersion> getCalParanVersion(Map<String, Object> params);
    /**
     * 版本查询弹出页面，后端分页
     * （！！待定！！）
     * */
    PageModel getCalParamVersionPage(Map<String, Object> params);
    /**
     * 保存历史版本记录（总）
     * */
    Map<String, Object> saveCalParamVersion(Map<String, Object> params);


}
