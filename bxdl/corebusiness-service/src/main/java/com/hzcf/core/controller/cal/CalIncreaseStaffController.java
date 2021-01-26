package com.hzcf.core.controller.cal;


import com.hzcf.core.service.cal.CalIncreaseStaffService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalHisIncreaseStaff;
import com.hzcf.pojo.cal.CalIncreaseStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 增员津贴配置Controller
 */
@RestController
@RequestMapping("/calIncreaseStaff")
public class CalIncreaseStaffController {
    @Autowired
    CalIncreaseStaffService calIncreaseStaffService ;

    /**
     * 根据orgId获取数据
     * @param params
     * @return
     */
    @RequestMapping("/getCalIncreaseStaffList")
    public List<CalIncreaseStaff> getCalIncreaseStaffList(@RequestParam Map<String, Object> params) {
        List<CalIncreaseStaff> calIncreaseStaffByOrgId = calIncreaseStaffService.getCalIncreaseStaffByOrgId(params);
        return calIncreaseStaffByOrgId;
    }


    /**
     * 删除校验
     * @return
     */
    @RequestMapping(value = "/updateIncreaseState")
    public int updateIncreaseState(@RequestBody CalIncreaseStaff calIncreaseStaff) {
        int  size= calIncreaseStaffService.updateIncreaseState(calIncreaseStaff);
        return size;
    }

    /**
     * 增加数据
     * @return
     */
    @RequestMapping(value = "/insertIgnoreNull")
    public int insertIgnoreNull(@RequestBody CalIncreaseStaff calIncreaseStaff) {
        int  size= calIncreaseStaffService.insertIgnoreNull(calIncreaseStaff);
        return size;
    }

    /**
     * 查询增员历史版本数据
     * @param params
     * @return
     */
    @RequestMapping("/getCalHisIncreasePage")
    @ResponseBody
    public PageModel getCalHisIncreasePage(@RequestParam Map<String, Object> params) {
        params.put("pageNo", Integer.valueOf((String) params.get("pageNo")));
        params.put("pageSize", Integer.valueOf((String) params.get("pageSize")));
        return calIncreaseStaffService.getCalHisIncreasePage(params);
    }

    /**
     * 查询增员历史版本数据第一条数据
     * @param params
     * @return
     */
    @RequestMapping("/getFirstCalHisIncreaseByVersionId")
    public List<CalHisIncreaseStaff> getFirstCalHisIncreaseByVersionId(@RequestParam Map<String, Object> params) {
        List<CalHisIncreaseStaff> calIncreaseStaff= calIncreaseStaffService.getFirstCalHisIncreaseByVersionId(params);
        return calIncreaseStaff;
    }

}
