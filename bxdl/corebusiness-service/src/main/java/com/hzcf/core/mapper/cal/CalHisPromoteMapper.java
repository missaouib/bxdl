package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalHisPromote;

@Mapper
public interface CalHisPromoteMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalHisPromote> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalHisPromote getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calHisPromote 新增的记录
     * @return 返回影响行数
     */
	int insert(CalHisPromote calHisPromote);
	
	/**
     * 新增，忽略null字段
     *
     * @param calHisPromote 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalHisPromote calHisPromote);
	
	/**
     * 修改，修改所有字段
     *
     * @param calHisPromote 修改的记录
     * @return 返回影响行数
     */
	int update(CalHisPromote calHisPromote);
	
	/**
     * 修改，忽略null字段
     *
     * @param calHisPromote 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalHisPromote calHisPromote);
	
	/**
     * 删除记录
     *
     * @param calHisPromote 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalHisPromote calHisPromote);

	List<Map<String, Object>> getCalHisPromoteByVersionId(Map<String, Object> params);

	Long getCalHisPromoteCount(Map<String, Object> params);
	
}