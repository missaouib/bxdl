package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalNurtureDirector;

@Mapper
public interface CalNurtureDirectorMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalNurtureDirector> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalNurtureDirector getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calNurtureDirector 新增的记录
     * @return 返回影响行数
     */
	int insert(CalNurtureDirector calNurtureDirector);
	
	/**
     * 新增，忽略null字段
     *
     * @param calNurtureDirector 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalNurtureDirector calNurtureDirector);
	
	/**
     * 修改，修改所有字段
     *
     * @param calNurtureDirector 修改的记录
     * @return 返回影响行数
     */
	int update(CalNurtureDirector calNurtureDirector);
	
	/**
     * 修改，忽略null字段
     *
     * @param calNurtureDirector 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalNurtureDirector calNurtureDirector);
	
	/**
     * 删除记录
     *
     * @param calNurtureDirector 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalNurtureDirector calNurtureDirector);

    List<CalNurtureDirector> getCalNurtureDirectorByOrgId(Map<String, Object> paras);
}