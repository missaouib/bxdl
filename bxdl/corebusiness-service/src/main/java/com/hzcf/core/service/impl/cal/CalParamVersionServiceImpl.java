package com.hzcf.core.service.impl.cal;

import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.mapper.cal.CalParamVersionMapper;
import com.hzcf.core.service.cal.CalParamVersionSevice;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalParamVersion;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.util.CalVersionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: liuweichen
 * @Date: 2020/10/23/10:54
 * @Description:
 */
@Service
public class CalParamVersionServiceImpl implements CalParamVersionSevice {
    @Autowired
    private CalParamVersionMapper calParamVersionMapper;

    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;

    @Override
    public List<CalParamVersion> getCalParanVersion(Map<String, Object> params) {
        return calParamVersionMapper.getCalParamVersion(params);
    }

    /**
     * 版本查询弹出页面，后端分页
     * （！！待定！！）
     * */
    @Override
    public PageModel getCalParamVersionPage(Map<String, Object> params) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        pageModel.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", pageModel.getStartIndex());
        params.put("endIndex", pageModel.getEndIndex());
        // 查询数据
        List<Map<String, Object>> list = calParamVersionMapper.getCalParamVersionPage(params);
        // 查询条数
        Long count = calParamVersionMapper.getCalParamVersionPageCount(params);
        pageModel.setList(list);
        pageModel.setTotalRecords(count);
        return pageModel;
    }

    /**
     * 保存历史版本记录（总）
     * */
    @Transactional
    @Override
    public Map<String, Object> saveCalParamVersion(Map<String, Object> params) {
        String paramType = String.valueOf(params.get("paramType"));
        try{
            // 只有默认0改为1时，才会插入6个历史表记录，并且插入另外五个表
            if("0".equals(params.get("isDefaultCal"))){
                otherCalPromote(params,paramType);
                otherCalIncreaseStaff(params,paramType);
                otherCalDirectlyUnderGroup(params,paramType);
                otherCalDirectlyUnderDept(params,paramType);
                otherCalNurtureDirector(params,paramType);
                otherCalNurtureMinister(params,paramType);
                // 应该在这里，修改isDefaultCal = '1'
                SalesOrgInfo salesOrgInfo = new SalesOrgInfo();
                salesOrgInfo.setIsDefaultCal("1");
                salesOrgInfo.setSalesOrgId(Long.parseLong(String.valueOf(params.get("salesOrgId"))));
                salesOrgInfoMapper.updateIsDefaultCal(salesOrgInfo);
            }else {
                // 自定义只记录自己log
                params.put("orgId",params.get("salesOrgId"));
                String id = paramVersion(params);
                params.put("id",id);
                switch (paramType){
                    case "0":
                        calParamVersionMapper.saveOtherHisPromote(params);
                        break;
                    case "1":
                        calParamVersionMapper.saveOtherHisIncreaseStaff(params);
                        break;
                    case "4":
                        calParamVersionMapper.saveOtherHisDirectlyUnderGroup(params);
                        break;
                    case "5":
                        calParamVersionMapper.saveOtherHisDirectlyUnderDept(params);
                        break;
                    case "2":
                        calParamVersionMapper.saveOtherHisNurtureDirector(params);
                        break;
                    case "3":
                        calParamVersionMapper.saveOtherHisNurtureMinister(params);
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return params;
    }

    /**
     * 插入历史总表，返回自增ID
     */
    private String paramVersion(Map<String, Object> params){
        CalParamVersion calParamVersion = new CalParamVersion();
        if("0".equals(params.get("isDefaultCal"))){
            calParamVersion.setMajorVersion(1);
            calParamVersion.setMinorVersion(1);
            calParamVersion.setCriticalVersion(0);
        }else {
            // 查询：cal_param_version表对应的记录
            List<CalParamVersion> list = calParamVersionMapper.getCalParamVersionInfo(params);

            if(list.isEmpty()){
                calParamVersion.setMajorVersion(1);
                calParamVersion.setMinorVersion(1);
                calParamVersion.setCriticalVersion(0);
            }else {
                calParamVersion.setMajorVersion(list.get(0).getMajorVersion());
                calParamVersion.setMinorVersion(list.get(0).getMinorVersion());
                calParamVersion.setCriticalVersion(list.get(0).getCriticalVersion());
            }

        }
        // 生成版本号
        CalVersionUtil.buildVersion(calParamVersion);
        // 保存历史版本记录
        CalParamVersion add = new CalParamVersion();
        add.setOrgId(Long.valueOf(String.valueOf(params.get("salesOrgId"))));
        add.setParamType(String.valueOf(params.get("paramType")));
        String versionId = ""+calParamVersion.getMajorVersion()+"."+calParamVersion.getMinorVersion()+"."+calParamVersion.getCriticalVersion();
        add.setRemark(versionId);
        add.setCreateBy(Long.valueOf(String.valueOf(params.get("employeeId"))));
        add.setMajorVersion(calParamVersion.getMajorVersion());
        add.setMinorVersion(calParamVersion.getMinorVersion());
        add.setCriticalVersion(calParamVersion.getCriticalVersion());
        add.setCreateTime(new Date());
        calParamVersionMapper.insertIgnoreNull(add);
        String id = String.valueOf(add.getId());
        return id;
    }

    /**
     * 展业津贴参数，类型：0
     */
    private void otherCalPromote(Map<String, Object> params,String paramType){
        params.put("paramType","0");
        String id = paramVersion(params);
        params.put("id",id);
        // 1，插入日志记录表
        calParamVersionMapper.saveOtherHisPromote(params);
        // 2，保存对应的新数据，如果参数是自己类型，不在这里新增
        if(!"0".equals(paramType)){
            calParamVersionMapper.saveOtherPromote(params);
        }
    }
    /**
     * 增员津贴参数表，类型：1
     */
    private void otherCalIncreaseStaff(Map<String, Object> params,String paramType){
        params.put("paramType","1");
        String id = paramVersion(params);
        params.put("id",id);
        // 1，插入日志记录表
        calParamVersionMapper.saveOtherHisIncreaseStaff(params);
        // 2，保存对应的新数据，如果参数是自己类型，不在这里新增
        if(!"1".equals(paramType)){
            calParamVersionMapper.saveOtherIncreaseStaff(params);
        }
    }
    /**
     * 直辖组管理津贴参数表，类型：4
     */
    private void otherCalDirectlyUnderGroup(Map<String, Object> params,String paramType){
        params.put("paramType","4");
        String id = paramVersion(params);
        params.put("id",id);
        // 1，插入日志记录表
        calParamVersionMapper.saveOtherHisDirectlyUnderGroup(params);
        // 2，保存对应的新数据，如果参数是自己类型，不在这里新增
        if(!"4".equals(paramType)){
            calParamVersionMapper.saveOtherDirectlyUnderGroup(params);
        }
    }
    /**
     * 直属部管理津贴参数表，类型：5
     */
    private void otherCalDirectlyUnderDept(Map<String, Object> params,String paramType){
        params.put("paramType","5");
        String id = paramVersion(params);
        params.put("id",id);
        // 1，插入日志记录表
        calParamVersionMapper.saveOtherHisDirectlyUnderDept(params);
        // 2，保存对应的新数据，如果参数是自己类型，不在这里新增
        if(!"5".equals(paramType)){
            calParamVersionMapper.saveOtherDirectlyUnderDept(params);
        }
    }

    /**
     * 处长/经理参数表，类型：2
     */
    private void otherCalNurtureDirector(Map<String, Object> params,String paramType){
        params.put("paramType","2");
        String id = paramVersion(params);
        params.put("id",id);
        // 1，插入日志记录表
        calParamVersionMapper.saveOtherHisNurtureDirector(params);
        // 2，保存对应的新数据，如果参数是自己类型，不在这里新增
        if(!"2".equals(paramType)){
            calParamVersionMapper.saveOtherNurtureDirector(params);
        }
    }

    /**
     * 部长/总监参数表，类型：3
     */
    private void otherCalNurtureMinister(Map<String, Object> params,String paramType){
        params.put("paramType","3");
        String id = paramVersion(params);
        params.put("id",id);
        // 1，插入日志记录表
        calParamVersionMapper.saveOtherHisNurtureMinister(params);
        // 2，保存对应的新数据，如果参数是自己类型，不在这里新增
        if(!"3".equals(paramType)){
            calParamVersionMapper.saveOtherNurtureMinister(params);
        }
    }



}
