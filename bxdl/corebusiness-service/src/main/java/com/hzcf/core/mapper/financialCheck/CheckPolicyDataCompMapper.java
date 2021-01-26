package com.hzcf.core.mapper.financialCheck;

import com.hzcf.pojo.financialCheck.CheckPolicyDataComp;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/18.
 */
public interface CheckPolicyDataCompMapper {
    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CheckPolicyDataComp> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CheckPolicyDataComp getById(Long id);

	/**
     * 新增，插入所有字段
     *
     * @param checkPolicyDataComp 新增的记录
     * @return 返回影响行数
     */
	int insert(CheckPolicyDataComp checkPolicyDataComp);

	/**
     * 新增，忽略null字段
     *
     * @param checkPolicyDataComp 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CheckPolicyDataComp checkPolicyDataComp);

	/**
     * 修改，修改所有字段
     *
     * @param checkPolicyDataComp 修改的记录
     * @return 返回影响行数
     */
	int update(CheckPolicyDataComp checkPolicyDataComp);

	/**
     * 修改，忽略null字段
     *
     * @param checkPolicyDataComp 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CheckPolicyDataComp checkPolicyDataComp);

	/**
     * 删除记录
     *
     * @param checkPolicyDataComp 待删除的记录
     * @return 返回影响行数
     */
	int delete(CheckPolicyDataComp checkPolicyDataComp);

    List<Map<String,Object>> getCheckPolicyDataCompList(Map<String, Object> params);

	long getCheckPolicyDataCompListSize(Map<String, Object> params);

	int batchCheckPolicyComp(List<Map> list);

	void updateCheckStatusSame(Map<String, Object> paras);

	void updateCheckStatusComp(Map<String, Object> paras);

	void updateCheckStatusHK(Map<String, Object> paras);

	void updateCheckStatusDiff(Map<String, Object> paras);

	void updateCheckResultHkBatchNum(Map<String, Object> paras);

	List<Map<String,Object>> getCheckPolicyDataCompAllList(Map<String, Object> paras);
}
