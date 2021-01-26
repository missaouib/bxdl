package com.hzcf.core.mapper.cal;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalChiefAchievement;

@Mapper
public interface CalChiefAchievementMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalChiefAchievement> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalChiefAchievement getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calChiefAchievement 新增的记录
     * @return 返回影响行数
     */
	int insert(CalChiefAchievement calChiefAchievement);
	
	/**
     * 新增，忽略null字段
     *
     * @param calChiefAchievement 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalChiefAchievement calChiefAchievement);
	
	/**
     * 修改，修改所有字段
     *
     * @param calChiefAchievement 修改的记录
     * @return 返回影响行数
     */
	int update(CalChiefAchievement calChiefAchievement);
	
	/**
     * 修改，忽略null字段
     *
     * @param calChiefAchievement 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalChiefAchievement calChiefAchievement);
	
	/**
     * 删除记录
     *
     * @param calChiefAchievement 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalChiefAchievement calChiefAchievement);
	
}