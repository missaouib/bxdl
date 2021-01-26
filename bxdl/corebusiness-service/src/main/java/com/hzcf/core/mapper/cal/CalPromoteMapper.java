package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalPromote;

@Mapper
public interface CalPromoteMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalPromote> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalPromote getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calPromote 新增的记录
     * @return 返回影响行数
     */
	int insert(CalPromote calPromote);
	
	/**
     * 新增，忽略null字段
     *
     * @param calPromote 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalPromote calPromote);
	
	/**
     * 修改，修改所有字段
     *
     * @param calPromote 修改的记录
     * @return 返回影响行数
     */
	int update(CalPromote calPromote);
	
	/**
     * 修改，忽略null字段
     *
     * @param calPromote 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalPromote calPromote);
	
	/**
     * 删除记录
     *
     * @param calPromote 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalPromote calPromote);

	List<CalPromote> getCalPromoteByOrgId(Map<String, Object> params);
}