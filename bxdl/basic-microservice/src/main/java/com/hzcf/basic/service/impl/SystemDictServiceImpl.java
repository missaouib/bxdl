package com.hzcf.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.hzcf.basic.mapper.DistrictMapper;
import com.hzcf.basic.mapper.SystemDictMapper;
import com.hzcf.basic.service.SystemDictService;
import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.District;
import com.hzcf.pojo.basic.SystemDict;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SystemDictServiceImpl implements SystemDictService {

    @Autowired
    private SystemDictMapper systemDictMapper;
    @Autowired
    private DistrictMapper districtMapper;

    protected final Log logger = LogFactory.getLog(getClass());


    @Override
    public void saveSystemDict(SystemDict systemDict) {
        systemDict.setStatus("1");
        systemDict.setCreateTime(new Date());
        systemDictMapper.insertSelective(systemDict);
    }

    @Override
    public SystemDict querySystemDictById(int id) {
        return systemDictMapper.findSystemDictById(id);
    }

    @Override
    public void updateSystemDict(SystemDict systemDict) {
        systemDictMapper.updateByPrimaryKeySelective(systemDict);
    }

    @Override
    public void disableSystemDict(int id) {
        systemDictMapper.updateSystemDictToDisabledById(id);
    }

    @Override
    public void enableSystemDict(int id) {
        systemDictMapper.updateSystemDictToEnabledById(id);
    }

    @Override
    public PageModel findSystemDictPage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("rows", model.getPageSize());

        long totalCount = systemDictMapper.querySystemDictTotalCount(params);
        if (totalCount > 0) {
            List<Map<String, Object>> dictList = systemDictMapper.querySystemDictPage(params);
            model.setList(dictList);
            model.setTotalRecords(totalCount);
        } else {
            model.setTotalRecords(0L);
            model.setList(Collections.emptyList());
        }
        return model;
    }

    @Override
    public SystemDict findSystemById(int id) {
        return systemDictMapper.findSystemDictById(id);
    }

    @Override
    public List<TreeView> findDictTree() {
        List<TreeView> tree = new ArrayList<TreeView>();
        //查询出所有的父级部门
        List<Map<String, Object>> plist = systemDictMapper.queryParentDictList();
        for (Map<String, Object> map : plist) {
            TreeView parent = new TreeView();
            parent.setText(String.valueOf(map.get("dict_name")));
            parent.setHref(String.valueOf(""));
            parent.setIcon(String.valueOf(""));
            parent.setId(Integer.parseInt((map.get("dict_id").toString())));
            List<TreeView> childList = new ArrayList<TreeView>();
            toTree(childList, Integer.parseInt((map.get("dict_id").toString())));
            parent.setNodes(childList);
            tree.add(parent);
        }
        return tree;
    }


    private List<TreeView> toTree(List<TreeView> childList, int pid) {
        List<Map<String, Object>> mapList = systemDictMapper.queryChildDictList(pid);
        if (!mapList.isEmpty()) {
            for (Map map : mapList) {
                TreeView child = new TreeView();
                child.setText(String.valueOf(map.get("dict_name")));
                child.setHref("");
                child.setIcon("");
                child.setId(Integer.parseInt((map.get("dict_id").toString())));
                child.setPid(Integer.parseInt((map.get("parent_id").toString())));
                List<Map<String, Object>> maps = systemDictMapper.queryChildDictList(Integer.parseInt((map.get("dict_id").toString())));
                if (!maps.isEmpty()) {
                    List<TreeView> list = new ArrayList<TreeView>();
                    for (Map m : maps) {
                        TreeView treeView = new TreeView();
                        treeView.setText(String.valueOf(m.get("dict_name")));
                        treeView.setHref("");
                        treeView.setIcon("");
                        treeView.setId(Integer.parseInt((m.get("dict_id").toString())));
                        treeView.setPid(Integer.parseInt((m.get("parent_id").toString())));
                        list.add(treeView);
                    }
                    child.setNodes(list);
                }
                childList.add(child);
            }
        }
        return childList;
    }

    @Override
    public List<Map<String, Object>> findAllSystemDict() {
        return systemDictMapper.findAllSystemDict();
    }


    @Override
    public List<Map<String, Object>> findDictNameByDictType(String dictType) {
        return systemDictMapper.findDictNameByDictType(dictType);
    }


    @Override
    public SystemDict findDictNameByTypeAndCode(Map<String, Object> paramsCondition) {
        return systemDictMapper.findDictNameByTypeAndCode(paramsCondition);
    }


    @Override
    public List<District> findAllDistrict() {
        return districtMapper.findAllDistrict();
    }


    @Override
    public String findDistrictByParentId(String parentId) {
        List<District> data = districtMapper.findDistrictByParentId(parentId);
        return JSON.toJSONString(data);
    }

    @Override
    public District findDistrictByDistrict(String district){
        List<District> data = districtMapper.findDistrictByDistrict(district);
        if (null!=data && data.size()>0) {
            return data.get(0);
        }
        return null;
    }

    @Override
    public District findDistrictByDistrictAndParentId(String district, String parentId){
        List<District> data = districtMapper.findDistrictByDistrictAndParentId(district,parentId);
        if (null!=data && data.size()>0) {
            return data.get(0);
        }
        return null;
    }

    @Override
    public List<SystemDict> findDictByDictTypeRetEntity(String dictType) {
        return systemDictMapper.findDictByDictTypeRetEntity(dictType);
    }

    @Override
    public Map<String, Object> selectSearchParams() {
        Map<String, Object> re = new HashMap<String, Object>();
        //证件类型
        final String type = "CARD";
        List<Map<String, Object>> dictNameByDictType = systemDictMapper.findCardTypeByDictType(type);
        //性别
        final String sex = "SEX";
        List<Map<String, Object>> allSex = systemDictMapper.findCardTypeByDictType(sex);
        //支付方式
        final String payType = "PAY_TYPE";
        List<Map<String, Object>> allPayType = systemDictMapper.findCardTypeByDictType(payType);
        //关系
        final String relationship = "RELATIONSHIP";
        List<Map<String, Object>> allRelationship = systemDictMapper.findCardTypeByDictType(relationship);
        //是否
        final String yesOrNo = "YESORNO";
        List<Map<String, Object>> allYesOrNo = systemDictMapper.findCardTypeByDictType(yesOrNo);
        re.put("cardType", dictNameByDictType);
        re.put("sex", allSex);
        re.put("payType", allPayType);
        re.put("relationship", allRelationship);
        re.put("yesOrNo", allYesOrNo);
        return re;
    }
}
