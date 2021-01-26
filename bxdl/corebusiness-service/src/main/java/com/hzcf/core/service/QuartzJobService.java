package com.hzcf.core.service;

import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.basic.QuartzJobModel;

import java.util.Map;

/**
 * Created by qin lina on 2020/4/20.
 */
public interface QuartzJobService {
    /**
     *@描述  新加任务
     *@创建人 qin lina
     *@创建时间 2020/4/22
     */
    void addJob(QuartzJobModel jobModel) throws Exception;

   /**
    *@描述 暂停或启动
    *@创建人 qin lina
    *@创建时间 2020/4/22
    */
    void pauseJob(int jobId, String status) throws Exception;


   /**
    *@描述  删除任务
    *@创建人 qin lina
    *@创建时间 2020/4/22
    */
    void deleteJob(int[] jobIds) throws Exception;

    /**
     *@描述 获取所有任务
     *@创建人 qin lina
     *@创建时间 2020/4/22
     */
    PageModel findList(Map<String, Object> paras);

    /**
     *@描述 根据id 获取任务
     *@创建人 qin lina
     *@创建时间 2020/4/22
     */
    QuartzJobModel   getJobById(Integer jobId);

    /**
     *@描述 修改任务
     *@创建人 qin lina
     *@创建时间 2020/4/22
     */

    void updateJob(QuartzJobModel jobModel)throws Exception;
}
