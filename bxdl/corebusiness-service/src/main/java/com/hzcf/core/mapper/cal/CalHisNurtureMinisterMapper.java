package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalHisNurtureMinister;

@Mapper
public interface CalHisNurtureMinisterMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalHisNurtureMinister> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalHisNurtureMinister getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calHisNurtureMinister 新增的记录
     * @return 返回影响行数
     */
	int insert(CalHisNurtureMinister calHisNurtureMinister);
	
	/**
     * 新增，忽略null字段
     *
     * @param calHisNurtureMinister 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalHisNurtureMinister calHisNurtureMinister);
	
	/**
     * 修改，修改所有字段
     *
     * @param calHisNurtureMinister 修改的记录
     * @return 返回影响行数
     */
	int update(CalHisNurtureMinister calHisNurtureMinister);
	
	/**
     * 修改，忽略null字段
     *
     * @param calHisNurtureMinister 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalHisNurtureMinister calHisNurtureMinister);
	
	/**
     * 删除记录
     *
     * @param calHisNurtureMinister 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalHisNurtureMinister calHisNurtureMinister);

	/**
	 * 查看部长育成奖金参数记录表
	 * corebusiness-service
	 */
	List<Map<String, Object>> getCalHisNurtureMinister(Map<String, Object> params);
}