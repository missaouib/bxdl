package com.hzcf.plantform.feign;

import com.hzcf.plantform.util.PageModel;
import com.hzcf.plantform.util.TreeView;
import com.hzcf.pojo.basic.District;
import com.hzcf.pojo.basic.SystemDict;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "basic-microservice", configuration = FeignDisableHystrixConfiguration.class)
public interface SystemDictFeignClient {

    @RequestMapping(value = "/systemDict/findDictNameByDictType", method = RequestMethod.POST)
    List<Map<String, Object>> findDictNameByDictType(@RequestParam("dictType") String dictType);

    /**
     * 根据字典类型返回字典实体集合
     * @param dictType
     * @return
     */
    @RequestMapping(value = "/systemDict/findDictByDictTypeRetEntity", method = RequestMethod.POST)
    List<SystemDict> findDictByDictTypeRetEntity(@RequestParam("dictType") String dictType);

    @RequestMapping(value = "/systemDict/getParamsData", method = RequestMethod.POST)
    Map<String, Object> getParamsData();

    @RequestMapping(value = "/systemDict/findDictNameByTypeAndCode", method = RequestMethod.POST)
    Map<String, Object> findSystemDictByTypeAndCode(@RequestParam Map<String, Object> paramsCondition);

    @RequestMapping(value = "/systemDict/findDistrictByParentId", method = RequestMethod.POST)
    String findDistrictByParentId(@RequestParam("parentId") String parentId);

    @RequestMapping(value = "/systemDict/findDistrictByDistrict",method = RequestMethod.POST)
    District findDistrictByDistrict(@RequestParam("district") String district);

    @RequestMapping(value = "/systemDict/findDistrictByDistrictAndParentId",method = RequestMethod.POST)
    District findDistrictByDistrictAndParentId(@RequestParam("district") String district,@RequestParam("parentId") String parentId);

    @RequestMapping(value = "/systemDict", method = RequestMethod.POST)
    void saveSystemDict(@RequestBody SystemDict systemDict);

    @RequestMapping(value = "/systemDict", method = RequestMethod.GET)
    SystemDict querySystemDictById(@RequestParam("dictId") int id);

    @RequestMapping(value = "/systemDict", method = RequestMethod.PUT)
    void updateSystemDict(@RequestBody SystemDict systemDict);

    @RequestMapping(value = "/disableSystemDict", method = RequestMethod.PUT)
    void disableSystemDict(@RequestParam("dictId") int dictId);

    @RequestMapping(value = "/enableSystemDict", method = RequestMethod.PUT)
    void enableSystemDict(@RequestParam("dictId") int dictId);

    @RequestMapping(value = "/systemDictPage", method = RequestMethod.POST)
    PageModel findSystemDictPage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/systemDict/tree", method = RequestMethod.GET)
    List<TreeView>  findSystemDictTreeList();
}
