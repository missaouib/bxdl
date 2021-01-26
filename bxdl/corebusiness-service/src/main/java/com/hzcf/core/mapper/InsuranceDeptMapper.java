package com.hzcf.core.mapper;

import com.hzcf.pojo.insurance.InsuranceDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *保险部门管理
 */
public  interface InsuranceDeptMapper {
    /**
     * 分页查询部门
     * */
    List<Map<String,Object>> getInsuranceDeptList(Map<String,Object> params);
    /**
     * 查询结果size
     * */
    Long getInsuranceDeptSize (Map<String,Object> params);
    /**
     * 添加部门
     * */
    void addInsuranceDept(InsuranceDept insuranceDept);
    /**
     * 修改部门
     * */
    void updateInsuranceDept(InsuranceDept insuranceDept);
    /**
     * 删除部门
     * */
    void deleteInsuranceDept(String[] params);
    /**
     * 查看详情
     * */
    InsuranceDept selectInsuranceBasicDept(Map<String,Object> params);
    /**
     * 根据编码查询
     * */
	InsuranceDept getInsCompanyByCode(String insuranceCompanyCode);
    /**
     * 不分页查询部门
     * */
	List<InsuranceDept> findInsurCompanys(Map<String, Object> params);
    /**
     * 根据部门ID，查询该部门子集
     * */
    InsuranceDept selectSubsetById(@Param("id") String id);

    InsuranceDept selectSubsetOrgById(@Param("id") String id);

    void deleteInsuranceOrg(String[] split);

    void addErrorData(@Param("insuranceCompanyCode") String insuranceCompanyCode,@Param("sys") String sys);

    InsuranceDept selectSubsetByCode(@Param("code")String toString);
    
	List<Map<String, Object>> findFirstInsuranceCompany();
	
	Map<String, Object> findCompanyOrgByCompanyId(@Param("insuranceCompanyId") int insuranceCompanyId);
	
	List<InsuranceDept> queryChildByCompanyCode(@Param("insuranceCompanyCode") String insuranceCompanyCode);

    List<Map<String, Object>> findCardTypeByDictType(@Param("dictType") String type);

    /**
     * <p>按照名称或代码查询已经存在的保险公司</p>
     * @param name  名称
     * @param code  代码
     * @return
     */
    List<InsuranceDept> queryExistOrgByNameOrCode(@Param("id") Long id, @Param("name") String name, @Param("code") String code);

    List<Map<String, Object>> selectAllOrg();

    Map<String, Object> selectOrg(Map<String, Object> pra);

    /**
     *@描述 批量插入保险公司信息
     *@参数
     *@返回值
     *@创建人 qin lina
     *@创建时间 2020/3/5
     */
    int batchAddInsuranceDept(List<Map> list);

    /**
     * 获取该保险公司下的所有保险公司机构编号
     * @param insuranceCompanyCode
     * @return
     */
    String getAllChildInsCompIds(String insuranceCompanyCode);
}
