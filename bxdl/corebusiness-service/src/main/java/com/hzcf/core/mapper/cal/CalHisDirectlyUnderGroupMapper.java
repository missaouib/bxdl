package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalHisDirectlyUnderGroup;

@Mapper
public interface CalHisDirectlyUnderGroupMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalHisDirectlyUnderGroup> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalHisDirectlyUnderGroup getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calHisDirectlyUnderGroup 新增的记录
     * @return 返回影响行数
     */
	int insert(CalHisDirectlyUnderGroup calHisDirectlyUnderGroup);
	
	/**
     * 新增，忽略null字段
     *
     * @param calHisDirectlyUnderGroup 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalHisDirectlyUnderGroup calHisDirectlyUnderGroup);
	
	/**
     * 修改，修改所有字段
     *
     * @param calHisDirectlyUnderGroup 修改的记录
     * @return 返回影响行数
     */
	int update(CalHisDirectlyUnderGroup calHisDirectlyUnderGroup);
	
	/**
     * 修改，忽略null字段
     *
     * @param calHisDirectlyUnderGroup 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalHisDirectlyUnderGroup calHisDirectlyUnderGroup);
	
	/**
     * 删除记录
     *
     * @param calHisDirectlyUnderGroup 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalHisDirectlyUnderGroup calHisDirectlyUnderGroup);

	List<Map<String, Object>> getCalHisDirectlyUnderGroup(Map<String, Object> params);

    List<CalHisDirectlyUnderGroup> getFirstCalDirectlyUnderGroupByVersionId(Map<String, Object> params);

    Long getCalHisDirectlyUnderGroupCount(Map<String, Object> params);
}