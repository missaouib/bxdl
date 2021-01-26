package com.hzcf.core.mapper.financialCheck;

import org.apache.ibatis.annotations.Mapper;

import com.hzcf.pojo.financialCheck.CheckPolicyDataHk;;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface CheckPolicyDataHkMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CheckPolicyDataHk> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CheckPolicyDataHk getById(Long id);

	/**
     * 新增，插入所有字段
     *
     * @param checkPolicyDataHk 新增的记录
     * @return 返回影响行数
     */
	int insert(CheckPolicyDataHk checkPolicyDataHk);

	/**
     * 新增，忽略null字段
     *
     * @param checkPolicyDataHk 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CheckPolicyDataHk checkPolicyDataHk);

	/**
     * 修改，修改所有字段
     *
     * @param checkPolicyDataHk 修改的记录
     * @return 返回影响行数
     */
	int update(CheckPolicyDataHk checkPolicyDataHk);

	/**
     * 修改，忽略null字段
     *
     * @param checkPolicyDataHk 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CheckPolicyDataHk checkPolicyDataHk);

	/**
     * 删除记录
     *
     * @param checkPolicyDataHk 待删除的记录
     * @return 返回影响行数
     */
	int delete(CheckPolicyDataHk checkPolicyDataHk);

	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
    List<Map<String,Object>> getCheckPolicyDataHKList(Map<String, Object> params);

	/**
	 * 分页查询总数
	 * @param params
	 * @return
	 */
	long getCheckPolicyDataHKListSize(Map<String, Object> params);

    int batchCheckPolicyHK(List<Map> list);

	void updateDataHK(Map<String, Object> paras);

    Map<String,BigDecimal> getTotalCost(Map<String, Object> paras);

	void confirmSettle(Map<String, Object> paras);

	List<Map<String,Object>> getCheckPolicyDataHkForBatchList(Map<String, Object> params);

	long getCheckPolicyDataHkForBatchListSize(Map<String, Object> params);

    List<Map<String,Object>> getCheckPolicyDataHkForBatchAll(Map<String, Object> paras);

	List<Map<String,Object>> getCheckPolicyDataHkByCondition(Map<String, Object> paras);

	int batchUpdateCheckPolicyHK(List<Map> list);

    void insertCheckResultHkBatchNum(Map<String, Object> params);
}