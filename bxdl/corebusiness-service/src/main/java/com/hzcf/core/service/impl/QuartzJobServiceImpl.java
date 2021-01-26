package com.hzcf.core.service.impl;

import com.hzcf.core.mapper.QuartzJobMapper;
import com.hzcf.core.service.QuartzJobService;
import com.hzcf.core.util.JobUtils;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.basic.QuartzJobModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by qin lina on 2020/4/20.
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {
     @Autowired
    private QuartzJobMapper quartzJobMapper;

    private String QUARTZ_TIMER_GROUP = "bxdl-timerGroup";
    @Autowired
    private JobUtils jobUtils;


    /**
     *@描述  添加任务
     *@创建人 qin lina
     *@创建时间 2020/4/21
     */
     @Override
    public void addJob( QuartzJobModel jobModel) throws Exception {
         //判断作业名是否重复
         Map<String, Object> map = new HashMap<>();
         map.put("jobName",jobModel.getJobName());
        List<QuartzJobModel> list=  quartzJobMapper.findByPrimary(map);
        if (!CollectionUtils.isEmpty(list)){
            throw new Exception("任务名称:"+jobModel.getJobName()+"已存在,请重新命名");
        }
         jobUtils.addJob(jobModel);
         if (QuartzJobModel.STATE_PAUSE.equals(jobModel.getStatus())){
             //如果新加的定时任务  禁用
             jobUtils.pauseJob(jobModel.getJobName(), JobUtils.QUARTZ_TIMER_GROUP);
         }
         quartzJobMapper.insert(jobModel);
    }
    /**
     * 暂停，启动任务
     *@描述
     *@创建人 qin lina
     *@创建时间 2020/4/21
     */
    @Override
    public void pauseJob( int jobId, String status) throws Exception {
        QuartzJobModel quartzJobModel = quartzJobMapper.selectByPrimaryKey(jobId);
        if (QuartzJobModel.STATE_START.equals(status)){
            //启动
            jobUtils.resumeJob(quartzJobModel.getJobName(),JobUtils.QUARTZ_TIMER_GROUP);
        }else if (QuartzJobModel.STATE_PAUSE.equals(status)){
           jobUtils.pauseJob(quartzJobModel.getJobName(),JobUtils.QUARTZ_TIMER_GROUP);
        }
            //根据id 更改状态
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("jobId", jobId);
            map.put("status", status);
           quartzJobMapper.updateStatus(map);
    }


    /**
     *@描述 删除任务
     *@创建人 qin lina
     *@创建时间 2020/4/21
     */
    @Override
    public void deleteJob(int[] jobIds) throws Exception {
         QuartzJobModel quartzJobModel = null;
        for(int jobId:jobIds) {
            quartzJobModel = quartzJobMapper.selectByPrimaryKey(jobId);
            jobUtils.deleteJob(quartzJobModel);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("jobId", jobId);
            map.put("status", QuartzJobModel.STATE_DELET);
             quartzJobMapper.updateStatus(map);
        }
    }

    /**
     *@描述 获取所有任务
     *@创建人 qin lina
     *@创建时间 2020/4/21
     */
    @Override
    public PageModel findList(Map<String, Object> paras) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(paras.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( paras.get("pageSize"))));
        paras.put("startIndex", model.getStartIndex());
        paras.put("endIndex",model.getPageSize());
         List<Map<String,Object>> list = quartzJobMapper.findList(paras);
        long size = quartzJobMapper.getListSize(paras);
        model.setList(list);
        model.setTotalRecords(size);

        return model;
    }

    @Override
    public QuartzJobModel getJobById(Integer jobId) {
        return quartzJobMapper.selectByPrimaryKey(jobId);
    }

    @Override
    public void updateJob( QuartzJobModel jobModel) throws Exception {
        jobUtils.modifyJob(jobModel);
        if (QuartzJobModel.STATE_PAUSE.equals(jobModel.getStatus())){
            jobUtils.pauseJob(jobModel.getJobName(),JobUtils.QUARTZ_TIMER_GROUP);
        }
            quartzJobMapper.updateByPrimaryKeySelective(jobModel);
    }
}
