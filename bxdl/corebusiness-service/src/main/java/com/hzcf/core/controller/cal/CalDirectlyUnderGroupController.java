package com.hzcf.core.controller.cal;

import com.hzcf.core.service.cal.CalDirectlyUnderGroupService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.cal.CalDirectlyUnderGroup;
import com.hzcf.pojo.cal.CalHisDirectlyUnderGroup;
import com.hzcf.pojo.cal.CalHisIncreaseStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: liuweichen
 * @Date: 2020/10/28/14:17
 * @Description:
 */
@RestController
@RequestMapping("/calDirectlyUnderGroup")
public class CalDirectlyUnderGroupController {
    @Autowired
    private CalDirectlyUnderGroupService calDirectlyUnderGroupService;

    @RequestMapping("/getCalDirectlyUnderGroup")
    List<CalDirectlyUnderGroup> getCalDirectlyUnderGroup(@RequestParam Map<String, Object> params){
        return calDirectlyUnderGroupService.getCalDirectlyUnderGroupByOrgId(params);
    }

    @RequestMapping("/insertIgnoreNull")
    int insertIgnoreNull(@RequestBody CalDirectlyUnderGroup calDirectlyUnderGroup){
        return calDirectlyUnderGroupService.insertIgnoreNull(calDirectlyUnderGroup);
    }

    @RequestMapping("/updateIncreaseState")
    int updateIncreaseState(@RequestBody CalDirectlyUnderGroup calDirectlyUnderGroup){
        return calDirectlyUnderGroupService.updateIncreaseState(calDirectlyUnderGroup);
    }

    @RequestMapping("/getCalHisDirectlyUnderGroupPage")
    @ResponseBody
     public PageModel getCalHisDirectlyUnderGroupPage(@RequestParam Map<String, Object> params) {
        params.put("pageNo", Integer.valueOf((String) params.get("pageNo")));
        params.put("pageSize", Integer.valueOf((String) params.get("pageSize")));
        return calDirectlyUnderGroupService.getCalHisDirectlyUnderGroupPage(params);
    }

    @RequestMapping("/getFirstCalDirectlyUnderGroupByVersionId")
    @ResponseBody
    public List<CalHisDirectlyUnderGroup> getFirstCalDirectlyUnderGroupByVersionId(@RequestParam Map<String, Object> params){
        List<CalHisDirectlyUnderGroup> calHisDirectlyUnderGroups= calDirectlyUnderGroupService.getFirstCalDirectlyUnderGroupByVersionId(params);
        return calHisDirectlyUnderGroups;
    }
}
