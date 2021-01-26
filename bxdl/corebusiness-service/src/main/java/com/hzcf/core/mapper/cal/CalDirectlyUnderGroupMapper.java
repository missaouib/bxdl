package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalDirectlyUnderGroup;

@Mapper
public interface CalDirectlyUnderGroupMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalDirectlyUnderGroup> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalDirectlyUnderGroup getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calDirectlyUnderGroup 新增的记录
     * @return 返回影响行数
     */
	int insert(CalDirectlyUnderGroup calDirectlyUnderGroup);
	
	/**
     * 新增，忽略null字段
     *
     * @param calDirectlyUnderGroup 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalDirectlyUnderGroup calDirectlyUnderGroup);
	
	/**
     * 修改，修改所有字段
     *
     * @param calDirectlyUnderGroup 修改的记录
     * @return 返回影响行数
     */
	int update(CalDirectlyUnderGroup calDirectlyUnderGroup);
	
	/**
     * 修改，忽略null字段
     *
     * @param calDirectlyUnderGroup 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalDirectlyUnderGroup calDirectlyUnderGroup);
	
	/**
     * 删除记录
     *
     * @param calDirectlyUnderGroup 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalDirectlyUnderGroup calDirectlyUnderGroup);

    List<CalDirectlyUnderGroup> getCalDirectlyUnderGroupByOrgId(Map<String, Object> param);
}