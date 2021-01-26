package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalDirectlyUnderDept;

@Mapper
public interface CalDirectlyUnderDeptMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalDirectlyUnderDept> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalDirectlyUnderDept getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calDirectlyUnderDept 新增的记录
     * @return 返回影响行数
     */
	int insert(CalDirectlyUnderDept calDirectlyUnderDept);
	
	/**
     * 新增，忽略null字段
     *
     * @param calDirectlyUnderDept 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalDirectlyUnderDept calDirectlyUnderDept);
	
	/**
     * 修改，修改所有字段
     *
     * @param calDirectlyUnderDept 修改的记录
     * @return 返回影响行数
     */
	int update(CalDirectlyUnderDept calDirectlyUnderDept);
	
	/**
     * 修改，忽略null字段
     *
     * @param calDirectlyUnderDept 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalDirectlyUnderDept calDirectlyUnderDept);
	
	/**
     * 删除记录
     *
     * @param calDirectlyUnderDept 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalDirectlyUnderDept calDirectlyUnderDept);

    List<CalDirectlyUnderDept> getCalDirectlyUnderDeptByOrgId(Map<String, Object> map);
}