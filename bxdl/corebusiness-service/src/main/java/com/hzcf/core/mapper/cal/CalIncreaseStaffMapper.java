package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalIncreaseStaff;

@Mapper
public interface CalIncreaseStaffMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalIncreaseStaff> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalIncreaseStaff getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calIncreaseStaff 新增的记录
     * @return 返回影响行数
     */
	int insert(CalIncreaseStaff calIncreaseStaff);
	
	/**
     * 新增，忽略null字段
     *
     * @param calIncreaseStaff 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalIncreaseStaff calIncreaseStaff);
	
	/**
     * 修改，修改所有字段
     *
     * @param calIncreaseStaff 修改的记录
     * @return 返回影响行数
     */
	int update(CalIncreaseStaff calIncreaseStaff);
	
	/**
     * 修改，忽略null字段
     *
     * @param calIncreaseStaff 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalIncreaseStaff calIncreaseStaff);
	
	/**
     * 删除记录
     *
     * @param calIncreaseStaff 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalIncreaseStaff calIncreaseStaff);

    List<CalIncreaseStaff> getCalIncreaseStaffByOrgId(Map<String, Object> par);


}