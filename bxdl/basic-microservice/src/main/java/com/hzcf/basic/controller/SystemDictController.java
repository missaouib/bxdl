package com.hzcf.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzcf.basic.util.PageModel;
import com.hzcf.basic.util.ReturnMsgData;
import com.hzcf.basic.util.TreeView;
import com.hzcf.pojo.basic.District;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hzcf.basic.service.SystemDictService;
import com.hzcf.pojo.basic.SystemDict;

@RestController
public class SystemDictController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SystemDictService systemDictService;


	/**
	 * <p>新增数据字典</p>
	 *
	 * @param systemDict 数据字典
	 */
	@RequestMapping(value = "/systemDict", method = RequestMethod.POST)
	public void saveSystemDict(@RequestBody SystemDict systemDict) {
		systemDictService.saveSystemDict(systemDict);
	}

	/**
	 * <p>根据id查询数据字典</p>
	 *
	 * @param dictId 数据字典id
	 */
	@RequestMapping(value = "/systemDict", method = RequestMethod.GET)
	public SystemDict querySystemDictById(@RequestParam("dictId") int dictId) {
		return systemDictService.querySystemDictById(dictId);
	}

	/**
	 * <p>更新数据字典</p>
	 *
	 * @param systemDict 数据字典
	 */
	@RequestMapping(value = "/systemDict", method = RequestMethod.PUT)
	public void updateSystemDict(@RequestBody SystemDict systemDict) {
		systemDictService.updateSystemDict(systemDict);
	}

	/**
	 * <p>根据id禁用数据字典</p>
	 *
	 * @param dictId 数据字典id
	 */
	@RequestMapping(value = "/disableSystemDict", method = RequestMethod.PUT)
	public void disableSystemDict(@RequestParam("dictId") int dictId) {
		systemDictService.disableSystemDict(dictId);
	}

	/**
	 * <p>根据id启用数据字典</p>
	 *
	 * @param dictId 数据字典id
	 */
	@RequestMapping(value = "/enableSystemDict", method = RequestMethod.PUT)
	public void enableSystemDict(@RequestParam("dictId") int dictId) {
		systemDictService.enableSystemDict(dictId);
	}

	/**
	 * <p>分页查找数据字典</p>
	 *
	 * @param params 查询参数
	 * @return
	 */
	@RequestMapping(value = "/systemDictPage", method = RequestMethod.POST)
	public PageModel findSystemDictPage(@RequestParam Map<String, Object> params) {
		return systemDictService.findSystemDictPage(params);
	}

	/**
	 * <p>查找数据字典</p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/systemDict/tree", method = RequestMethod.GET)
	public List<TreeView> findSystemDictTreeList() {
		return systemDictService.findDictTree();
	}

	/**
	 * 通过dictType查询字典
	 *
	 * @return
	 */
	@RequestMapping("/systemDict/findDictNameByDictType")
	@ResponseBody
	public List<Map<String, Object>> findDictNameByDictType(@RequestParam("dictType") String dictType) {
		try {
			return systemDictService.findDictNameByDictType(dictType);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询字典数据错误！");
		}
		return null;
	}
	/**
	 * 根据字典类型返回字典实体集合
	 *
	 * @return
	 */
	@RequestMapping("/systemDict/findDictByDictTypeRetEntity")
	@ResponseBody
	public List<SystemDict> findDictByDictTypeRetEntity(@RequestParam("dictType") String dictType) {
		try {
			return systemDictService.findDictByDictTypeRetEntity(dictType);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询字典数据错误！");
		}
		return null;
	}

	/**
	 * 通过dictType + dictCode 或者 dictType+dictName查询字典
	 *
	 * @return
	 */
	@RequestMapping("/systemDict/findDictNameByTypeAndCode")
	@ResponseBody
	public Object findDictNameByTypeAndCode(@RequestParam Map<String, Object> paramsCondition) {
		try {
			logger.info("开始调用日志====="+paramsCondition.get("dictName")+","+paramsCondition.get("dictType"));
			SystemDict systemDict = null;
			if (paramsCondition != null) {
				systemDict = systemDictService.findDictNameByTypeAndCode(paramsCondition);
				return systemDict;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询字典数据错误！");
		}
		return null;
	}


	/**
	 * 通过ParentId查询地区
	 *
	 * @return
	 */
	@RequestMapping("/systemDict/findDistrictByParentId")
	@ResponseBody
	public String findDistrictByParentId(@RequestParam("parentId") String parentId) {
		try {
			return systemDictService.findDistrictByParentId(parentId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询字典数据错误！");
		}
		return null;
	}

	@RequestMapping("/systemDict/findDistrictByDistrict")
	@ResponseBody
	public District findDistrictByDistrict(@RequestParam("district") String district) {
		try {
			return systemDictService.findDistrictByDistrict(district);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询字典数据错误！");
		}
		return null;
	}

	@RequestMapping("/systemDict/findDistrictByDistrictAndParentId")
	@ResponseBody
	public District findDistrictByDistrictAndParentId(@RequestParam("district") String district,@RequestParam("parentId") String parentId) {
		try {
			return systemDictService.findDistrictByDistrictAndParentId(district,parentId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询字典数据错误！");
		}
		return null;
	}



	@RequestMapping("/systemDict/getParamsData")
	@ResponseBody
	public Map<String, Object> getParamsData() {
		ReturnMsgData msg = new ReturnMsgData();
			return systemDictService.selectSearchParams();
	}
}