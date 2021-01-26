package com.hzcf.core.mapper.cal;

import java.util.List;
import java.util.Map;

import com.hzcf.pojo.cal.CalParamRuleConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalParamRuleConfigMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<CalParamRuleConfig> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	CalParamRuleConfig getById(Long id);
	
	/**
     * 新增，插入所有字段
     *
     * @param calParmRuleConfig 新增的记录
     * @return 返回影响行数
     */
	int insert(CalParamRuleConfig calParmRuleConfig);
	
	/**
     * 新增，忽略null字段
     *
     * @param calParmRuleConfig 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(CalParamRuleConfig calParmRuleConfig);
	
	/**
     * 修改，修改所有字段
     *
     * @param calParmRuleConfig 修改的记录
     * @return 返回影响行数
     */
	int update(CalParamRuleConfig calParmRuleConfig);
	
	/**
     * 修改，忽略null字段
     *
     * @param calParmRuleConfig 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(CalParamRuleConfig calParmRuleConfig);
	
	/**
     * 删除记录
     *
     * @param calParmRuleConfig 待删除的记录
     * @return 返回影响行数
     */
	int delete(CalParamRuleConfig calParmRuleConfig);

	/**
	 * 分页查询参数规则记录
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getListByPage(Map<String, Object> params);

	/**
	 * 根据参数查询记录数
	 * @param params
	 * @return
	 */
	Long getTotalSize(Map<String, Object> params);

    List<CalParamRuleConfig> getCalParamRuleList(Map<String, Object> params);
}