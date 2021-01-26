package com.hzcf.basic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.pojo.basic.SystemDict;
import com.hzcf.pojo.basic.SystemDictExample;

public interface SystemDictMapper {

    int countByExample(SystemDictExample example);

    int deleteByExample(SystemDictExample example);

    int deleteByPrimaryKey(Integer dictId);

    int insert(SystemDict record);

    int insertSelective(SystemDict record);

    List<SystemDict> selectByExample(SystemDictExample example);

    SystemDict selectByPrimaryKey(Integer dictId);

    int updateByExampleSelective(@Param("record") SystemDict record, @Param("example") SystemDictExample example);

    int updateByExample(@Param("record") SystemDict record, @Param("example") SystemDictExample example);

    int updateByPrimaryKeySelective(SystemDict record);

    int updateByPrimaryKey(SystemDict record);

    /**
     * <p>根据id禁用数据字典</p>
     * @param id    数据字典id
     * @return int 操作成功个数
     */
    int updateSystemDictToDisabledById(@Param("dictId") int id);

    /**
     * <p>根据id启用数据字典</p>
     * @param id    数据字典id
     * @return int 操作成功个数
     */
    int updateSystemDictToEnabledById(@Param("dictId") int id);

    /**
     * <p>分页查找数据字典</p>
     * @param params    查询参数
     * @return
     */
    List<Map<String, Object>> querySystemDictPage(Map params);

    /**
     * <p>查询数据字典总数</p>
     * @param params    查询参数
     * @return
     */
    long querySystemDictTotalCount(Map params);

    /**
     * <p>查找父类字典</p>
     * @return
     */
    List<Map<String, Object>> queryParentDictList();

    /**
     * <p>查询子类字典</p>
     * @return
     */
    List<Map<String, Object>> queryChildDictList(@Param("pid")int pid);

    List<Map<String, Object>> findAllSystemDict();

    SystemDict findSystemDictById(@Param("dictId")int id);

	//根据字典类型查询
	List<Map<String, Object>> findDictNameByDictType(String dictType);
    // 通过dictType + dictCode查询字典
	SystemDict findDictNameByTypeAndCode(Map<String, Object> paramsCondition);


    List<Map<String, Object>> findCardTypeByDictType(String relationship);

    List<SystemDict> findDictByDictTypeRetEntity(@Param("dictType") String dictType);
}