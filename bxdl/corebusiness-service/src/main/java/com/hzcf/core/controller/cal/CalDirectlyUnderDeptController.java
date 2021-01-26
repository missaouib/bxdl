package com.hzcf.core.controller.cal;


import com.hzcf.core.service.cal.CalDirectlyUnderDeptService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalDirectlyUnderDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 增员津贴配置Controller
 */
@RestController
@RequestMapping("/calDept")
public class CalDirectlyUnderDeptController {

    @Autowired
    private CalDirectlyUnderDeptService calDirectlyUnderDeptService ;

    /**
     * 根据orgId获取数据
     * @param params
     * @return
     */
    @RequestMapping("/getDeptList")
    public List<CalDirectlyUnderDept> getDeptList(@RequestParam Map<String, Object> params) {
        List<CalDirectlyUnderDept> calDepts = calDirectlyUnderDeptService.getCalDirectlyUnderDeptByOrgId(params);
        return calDepts;
    }

    /**
     * 逻辑删除校验
     * @return
     */
    @RequestMapping(value = "/updateDeptState")
    public int updateDeptState(@RequestBody CalDirectlyUnderDept calDirectlyUnderDept) {
        int  size= calDirectlyUnderDeptService.updateIncreaseState(calDirectlyUnderDept);
        return size;
    }

    /**
     * 增加数据
     * @return
     */
    @RequestMapping(value = "/insertIgnoreNull")
    public int insertIgnoreNull(@RequestBody CalDirectlyUnderDept calDirectlyUnderDept) {
        int  size= calDirectlyUnderDeptService.insertIgnoreNull(calDirectlyUnderDept);
        return size;
    }


    /**
     * 查询直辖部历史版本数据
     * @param params
     * @return
     */
    @RequestMapping("/getCalHisDeptPage")
    @ResponseBody
    public PageModel getCalHisDeptPage(@RequestParam Map<String, Object> params) {
        params.put("pageNo", Integer.valueOf((String) params.get("pageNo")));
        params.put("pageSize", Integer.valueOf((String) params.get("pageSize")));
        return calDirectlyUnderDeptService.getCalHisDeptPage(params);
    }

    /**
     * 查询直辖部历史版本数据第一条数据
     * @param params
     * @return
     */
    @RequestMapping("/getFirstCalHisDeptByVersionId")
    public List<CalDirectlyUnderDept> getFirstCalHisDeptByVersionId(@RequestParam Map<String, Object> params) {
        List<CalDirectlyUnderDept> calDept= calDirectlyUnderDeptService.getFirstCalHisDeptByVersionId(params);
        return calDept;
    }






}
