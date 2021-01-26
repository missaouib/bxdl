package com.hzcf.core.mapper;

import com.hzcf.pojo.basic.QuartzJobModel;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/4/20.
 */
public interface QuartzJobMapper {
    List<Map<String, Object>> findList(Map<String, Object> paras);

    long getListSize(Map<String, Object> paras);

    void insert(QuartzJobModel jobModel);

    int deleteByPrimaryKey(Integer jobId);

     QuartzJobModel selectByPrimaryKey(Integer jobId);

     int updateByPrimaryKeySelective(QuartzJobModel record);

    int updateStatus(Map<String, Object> map);

     List<QuartzJobModel>  findByPrimary(Map<String, Object> map);
}
