package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.cal.CalDirectlyUnderDept;
import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalHisDirectlyUnderDept;

@Mapper
public interface CalHisDirectlyUnderDeptMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalHisDirectlyUnderDept> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalHisDirectlyUnderDept getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calHisDirectlyUnderDept 新增的记录
     * @return 返回影响行数
     */
	int insert(CalHisDirectlyUnderDept calHisDirectlyUnderDept);
	
	/**
     * 新增，忽略null字段
     *
     * @param calHisDirectlyUnderDept 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalHisDirectlyUnderDept calHisDirectlyUnderDept);
	
	/**
     * 修改，修改所有字段
     *
     * @param calHisDirectlyUnderDept 修改的记录
     * @return 返回影响行数
     */
	int update(CalHisDirectlyUnderDept calHisDirectlyUnderDept);
	
	/**
     * 修改，忽略null字段
     *
     * @param calHisDirectlyUnderDept 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalHisDirectlyUnderDept calHisDirectlyUnderDept);
	
	/**
     * 删除记录
     *
     * @param calHisDirectlyUnderDept 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalHisDirectlyUnderDept calHisDirectlyUnderDept);

    List<CalDirectlyUnderDept> getFirstCalHisDeptByVersionId(Map<String, Object> params);

	List<Map<String, Object>> getCalHisDeptByVersionId(Map<String, Object> params);

	Long getCalHisDeptCount(Map<String, Object> params);
}