package com.hzcf.core.mapper.cal;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalDriveBusiness;

@Mapper
public interface CalDriveBusinessMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalDriveBusiness> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalDriveBusiness getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calDriveBusiness 新增的记录
     * @return 返回影响行数
     */
	int insert(CalDriveBusiness calDriveBusiness);
	
	/**
     * 新增，忽略null字段
     *
     * @param calDriveBusiness 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalDriveBusiness calDriveBusiness);
	
	/**
     * 修改，修改所有字段
     *
     * @param calDriveBusiness 修改的记录
     * @return 返回影响行数
     */
	int update(CalDriveBusiness calDriveBusiness);
	
	/**
     * 修改，忽略null字段
     *
     * @param calDriveBusiness 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalDriveBusiness calDriveBusiness);
	
	/**
     * 删除记录
     *
     * @param calDriveBusiness 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalDriveBusiness calDriveBusiness);
	
}