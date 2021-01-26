package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service", fallback = FeignDisableHystrixConfiguration.class)
public interface InsuranceDeptClient {
    @RequestMapping(value = "/insurance_dept/test")
    Map<String, Object> getTest();

    /**
     * 查询列表
     */
    @RequestMapping(value = "/insurance_dept/do_insurance_dept", method = RequestMethod.POST)
    PageModel getInsuranceDeptList(@RequestParam Map<String, Object> params);

    /**
     * 增加部门
     */
    @RequestMapping(value = "/insurance_dept/add_insurance_dept", method = RequestMethod.POST)
    void addInsuranceDept(@RequestBody InsuranceDept insuranceDept);

    /**
     * 修改部门
     */
    @RequestMapping(value = "/insurance_dept/update_insurance_dept", method = RequestMethod.POST)
    void updateInsuranceDept(@RequestBody InsuranceDept params);

    /**
     * 查看详情 基本信息详情
     */
    @PostMapping(value = "/insurance_dept/select_insurance_basic_dept")
    InsuranceDept selectInsuranceBasicDept(@RequestParam Map<String, Object> params);

    /**
     * 删除部门
     */
    @PostMapping(value = "/insurance_dept/delete_insurance_dept")
    void deleteInsuranceDept(@RequestParam(value = "params") String params);

    @PostMapping(value = "/insurance_dept/delete_insurance_org")
    void deleteInsuranceOrg(@RequestParam(value = "params") String params);

    /**
     * 查询列表不分页
     */
    @PostMapping(value = "/insurance_dept/findInsurCompanys")
    List<InsuranceDept> findInsurCompanys(Map<String, Object> paras);

    @RequestMapping(value = "insurance_dept/select_subset_by_id", method = RequestMethod.POST)
    InsuranceDept selectSubsetById(@RequestBody String id);

    @RequestMapping(value = "insurance_dept/select_subset_org_by_id", method = RequestMethod.POST)
    InsuranceDept selectSubsetOrgById(@RequestBody String id);

    @RequestMapping(value = "insurance_dept/select_all_org", method = RequestMethod.POST)
    List<Map<String, Object>> selectAllOrg();
    @RequestMapping(value = "insurance_dept/selectOrg", method = RequestMethod.POST)
    Map<String, Object> selectOrg(@RequestBody Map<String, Object> pra);

    /**
     * <p>按照名称或代码查询已经存在的保险公司</p>
     */
    @RequestMapping(value = "insurance_dept/exist_org", method = RequestMethod.POST)
    boolean findExistOrgByNameOrCode(@RequestParam(value="id", required = false) Long id, @RequestParam("name")String name, @RequestParam("code")String code);

    /**
     *@描述
     *@参数 批量导入保险公司信息
     *@返回值
     *@创建人 qin lina
     *@创建时间 2020/3/5
     * @param p
     */
    @RequestMapping(value = "insurance_dept/insertImportList",method = RequestMethod.POST)
    Map<String, Object> insertImportList(@RequestBody Map<String, Object> p);

}
