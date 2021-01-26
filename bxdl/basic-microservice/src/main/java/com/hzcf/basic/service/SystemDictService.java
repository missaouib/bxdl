package com.hzcf.basic.service;

import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.District;
import com.hzcf.pojo.basic.SystemDict;

import java.util.List;
import java.util.Map;

public interface SystemDictService {

    List<Map<String, Object>> findAllSystemDict();


    /**
     * <p>添加字典</p>
     *
     * @param systemDict 数据字典
     */
    void saveSystemDict(SystemDict systemDict);

    /**
     * <p>根据id查询字典</p>
     *
     * @param id 字典id
     * @return systemDict
     */
    SystemDict querySystemDictById(int id);

    /**
     * <p>修改数据字典</p>
     *
     * @param systemDict 数据字典
     */
    void updateSystemDict(SystemDict systemDict);

    /**
     * <p>根据id禁用数据字典</p>
     *
     * @param id 字典id
     */
    void disableSystemDict(int id);

    /**
     * <p>根据id启用数据字典</p>
     *
     * @param id 字典id
     */
    void enableSystemDict(int id);

    /**
     * <p>分页查找数据字典</p>
     *
     * @param params 查询参数
     * @return
     */
    PageModel findSystemDictPage(Map<String, Object> params);

    /**
     * <p>根据id查找字典</p>
     * @param id
     * @return
     */
    SystemDict findSystemById(int id);

    /**
     * <p>查找数据字典树结构</p>
     * @return
     */
    List<TreeView> findDictTree();

    /**
     * 根据字典类型 查询列表
     *
     * @param dictType
     * @return
     */
    List<Map<String, Object>> findDictNameByDictType(String dictType);

    /**
     * 根据dictType + dictCode查询字典
     *
     * @param paramsCondition
     * @return
     */

    SystemDict findDictNameByTypeAndCode(Map<String, Object> paramsCondition);

    /**
     * 查询所有的地区
     *
     * @return
     */
    List<District> findAllDistrict();

    /**
     * @param parentId
     * @return
     */
    String findDistrictByParentId(String parentId);


    Map<String, Object> selectSearchParams();

    District findDistrictByDistrict(String district);

    District findDistrictByDistrictAndParentId(String district,String parentId);

    List<SystemDict> findDictByDictTypeRetEntity(String dictType);
}
