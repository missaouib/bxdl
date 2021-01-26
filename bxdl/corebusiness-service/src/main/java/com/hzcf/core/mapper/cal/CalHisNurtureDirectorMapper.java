package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalHisNurtureDirector;

@Mapper
public interface CalHisNurtureDirectorMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalHisNurtureDirector> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalHisNurtureDirector getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calHisNurtureDirector 新增的记录
     * @return 返回影响行数
     */
	int insert(CalHisNurtureDirector calHisNurtureDirector);
	
	/**
     * 新增，忽略null字段
     *
     * @param calHisNurtureDirector 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalHisNurtureDirector calHisNurtureDirector);
	
	/**
     * 修改，修改所有字段
     *
     * @param calHisNurtureDirector 修改的记录
     * @return 返回影响行数
     */
	int update(CalHisNurtureDirector calHisNurtureDirector);
	
	/**
     * 修改，忽略null字段
     *
     * @param calHisNurtureDirector 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalHisNurtureDirector calHisNurtureDirector);
	
	/**
     * 删除记录
     *
     * @param calHisNurtureDirector 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalHisNurtureDirector calHisNurtureDirector);

	List<Map<String, Object>> getCalHisNurtureDirectorPage(Map<String, Object> params);

	Long getCalHisNurtureDirectorCount(Map<String, Object> params);
}