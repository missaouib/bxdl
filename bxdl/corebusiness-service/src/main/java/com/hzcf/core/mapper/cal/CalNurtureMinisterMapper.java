package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalNurtureMinister;

@Mapper
public interface CalNurtureMinisterMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalNurtureMinister> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalNurtureMinister getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calNurtureMinister 新增的记录
     * @return 返回影响行数
     */
	int insert(CalNurtureMinister calNurtureMinister);
	
	/**
     * 新增，忽略null字段
     *
     * @param calNurtureMinister 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalNurtureMinister calNurtureMinister);
	
	/**
     * 修改，修改所有字段
     *
     * @param calNurtureMinister 修改的记录
     * @return 返回影响行数
     */
	int update(CalNurtureMinister calNurtureMinister);
	
	/**
     * 修改，忽略null字段
     *
     * @param calNurtureMinister 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalNurtureMinister calNurtureMinister);
	
	/**
     * 删除记录
     *
     * @param calNurtureMinister 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalNurtureMinister calNurtureMinister);

    List<CalNurtureMinister> getCalNurtureMinisterByOrgId(Map<String, Object> map);
}