package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.hzcf.pojo.cal.CalParamVersion;

@Mapper
public interface CalParamVersionMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalParamVersion> listAll();

	List<CalParamVersion> getCalParamVersionInfo(Map<String, Object> params);
	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalParamVersion getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calParamVersion 新增的记录
     * @return 返回影响行数
     */
	int insert(CalParamVersion calParamVersion);
	
	/**
     * 新增，忽略null字段
     *
     * @param calParamVersion 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalParamVersion calParamVersion);
	
	/**
     * 修改，修改所有字段
     *
     * @param calParamVersion 修改的记录
     * @return 返回影响行数
     */
	int update(CalParamVersion calParamVersion);
	
	/**
     * 修改，忽略null字段
     *
     * @param calParamVersion 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalParamVersion calParamVersion);
	
	/**
     * 删除记录
     *
     * @param calParamVersion 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalParamVersion calParamVersion);

	List<CalParamVersion> getCalParamVersion(Map<String, Object> params);

	List<Map<String, Object>> getCalParamVersionPage(Map<String, Object> params);

	Long getCalParamVersionPageCount(Map<String, Object> params);




	// 展业津贴参数
	void saveOtherPromote(Map<String, Object> params);
	void saveOtherHisPromote(Map<String, Object> params);

	// 增员津贴参数表
	void saveOtherIncreaseStaff(Map<String, Object> params);
	void saveOtherHisIncreaseStaff(Map<String, Object> params);

	// 直辖组管理津贴参数表
	void saveOtherDirectlyUnderGroup(Map<String, Object> params);
	void saveOtherHisDirectlyUnderGroup(Map<String, Object> params);

	// 直属部管理津贴参数表
	void saveOtherDirectlyUnderDept(Map<String, Object> params);
	void saveOtherHisDirectlyUnderDept(Map<String, Object> params);

	// 处长/经理参数表
	void saveOtherNurtureDirector(Map<String, Object> params);
	void saveOtherHisNurtureDirector(Map<String, Object> params);

	// 部长/总监参数表
	void saveOtherNurtureMinister(Map<String, Object> params);
	void saveOtherHisNurtureMinister(Map<String, Object> params);
}