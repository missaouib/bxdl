package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalHisIncreaseStaff;

@Mapper
public interface CalHisIncreaseStaffMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalHisIncreaseStaff> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalHisIncreaseStaff getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calHisIncreaseStaff 新增的记录
     * @return 返回影响行数
     */
	int insert(CalHisIncreaseStaff calHisIncreaseStaff);
	
	/**
     * 新增，忽略null字段
     *
     * @param calHisIncreaseStaff 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalHisIncreaseStaff calHisIncreaseStaff);
	
	/**
     * 修改，修改所有字段
     *
     * @param calHisIncreaseStaff 修改的记录
     * @return 返回影响行数
     */
	int update(CalHisIncreaseStaff calHisIncreaseStaff);
	
	/**
     * 修改，忽略null字段
     *
     * @param calHisIncreaseStaff 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalHisIncreaseStaff calHisIncreaseStaff);
	
	/**
     * 删除记录
     *
     * @param calHisIncreaseStaff 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalHisIncreaseStaff calHisIncreaseStaff);

    List<CalHisIncreaseStaff> getFirstCalHisIncreaseByVersionId(Map<String, Object> params);

    List<Map<String, Object>> getCalHisIncreaseByVersionId(Map<String, Object> params);

	Long getCalHisIncreaseCount(Map<String, Object> params);
}