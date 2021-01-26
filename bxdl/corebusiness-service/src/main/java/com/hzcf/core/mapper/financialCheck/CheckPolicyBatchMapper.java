package com.hzcf.core.mapper.financialCheck;


import com.hzcf.pojo.financialCheck.CheckPolicyBatch;

import java.util.List;
import java.util.Map;

/**
 * Created by qin lina on 2020/12/10.
 */
public interface CheckPolicyBatchMapper {
    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CheckPolicyBatch> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CheckPolicyBatch getById(Long id);

	/**
     * 新增，插入所有字段
     *
     * @param checkPolicyBatch 新增的记录
     * @return 返回影响行数
     */
	int insert(CheckPolicyBatch checkPolicyBatch);

	/**
     * 新增，忽略null字段
     *
     * @param checkPolicyBatch 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CheckPolicyBatch checkPolicyBatch);

	/**
     * 修改，修改所有字段
     *
     * @param checkPolicyBatch 修改的记录
     * @return 返回影响行数
     */
	int update(CheckPolicyBatch checkPolicyBatch);

	/**
     * 修改，忽略null字段
     *
     * @param checkPolicyBatch 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CheckPolicyBatch checkPolicyBatch);

	/**
     * 删除记录
     *
     * @param checkPolicyBatch 待删除的记录
     * @return 返回影响行数
     */
	int delete(CheckPolicyBatch checkPolicyBatch);

	/**
	 * 分页查询批次信息
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getCheckPolicyBatchList(Map<String, Object> params);

	/**
	 * 分页查询批次信息总数
	 * @param params
	 * @return
	 */
	long getCheckPolicyBatchListSize(Map<String, Object> params);

	List<CheckPolicyBatch> getCheckPolicyBatchByCondition(Map<String, Object> params);
}
