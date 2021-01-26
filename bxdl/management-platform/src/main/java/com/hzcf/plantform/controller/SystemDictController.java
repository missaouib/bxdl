package com.hzcf.plantform.controller;

import com.hzcf.plantform.feign.SystemDictFeignClient;
import com.hzcf.plantform.util.*;
import com.hzcf.pojo.basic.SystemDict;
import com.hzcf.util.StringUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SystemDictController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 RedisUtil redisUtil;
	 
	 @Autowired
	 private SystemDictFeignClient systemDictFeignClient;

	/**
	 * <p>跳转数据字典列表页面</p>
	 *
	 * @return
	 */
	// 权限管理;
	@RequiresPermissions("dictManager:list")
	@RequestMapping("/systemDictPage")
	public String goDictPage() {
		return "system/dict/dict";
	}

	/**
	 * <p>新增数据字典</p>
	 * @param systemDict	数据字典
	 */
	@RequiresPermissions("dictManager:add")
	@RequestMapping(value = "/systemDict", method = RequestMethod.POST)
	@ResponseBody
	public DataMsg saveSystemDict(SystemDict systemDict) {
		DataMsg msg = new DataMsg();
		try {
			systemDictFeignClient.saveSystemDict(systemDict);
			msg.setMessageCode("200");
		} catch (Exception e) {
			msg.setMessageCode("400");
			e.printStackTrace();
			logger.info("数据字典[新增]异常");
		}
		return msg;
	}

	/**
	 * <p>根据id查询数据字典</p>
	 * @param dictId	数据字典id
	 */
	@RequestMapping(value = "/systemDict", method = RequestMethod.GET)
	@ResponseBody
	public Map querySystemDictById(@RequestParam("dictId") int dictId) {
		Map<String, Object> map = new HashMap<>();
		try {
			SystemDict systemDict = systemDictFeignClient.querySystemDictById(dictId);
			map.put("systemDict",systemDict);
			if(null != systemDict.getParentId()){
				SystemDict parent = systemDictFeignClient.querySystemDictById(systemDict.getParentId());
				if(null != parent){
					map.put("parent",parent.getDictName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("数据字典[查询]异常");
		}
		return map;
	}

	/**
	 * <p>更新数据字典</p>
	 * @param systemDict	数据字典
	 */
	@RequiresPermissions("dictManager:update")
	@RequestMapping(value = "/systemDict", method = RequestMethod.PUT)
	@ResponseBody
	public DataMsg updateSystemDict(SystemDict systemDict) {
		DataMsg msg = new DataMsg();
		try {
			systemDictFeignClient.updateSystemDict(systemDict);
			msg.setMessageCode("200");
		} catch (Exception e) {
			msg.setMessageCode("400");
			e.printStackTrace();
			logger.info("数据字典[修改]异常");
		}
		return msg;
	}

	/**
	 * <p>根据id禁用数据字典</p>
	 * @param dictId	数据字典id
	 */
	@RequiresPermissions("dictManager:disable")
	@RequestMapping(value = "/systemDict/disable", method = RequestMethod.POST)
	@ResponseBody
	public DataMsg disableSystemDict(@RequestParam("dictId") int dictId) {
		DataMsg msg = new DataMsg();
		try {
			systemDictFeignClient.disableSystemDict(dictId);
			msg.setMessageCode("200");
		} catch (Exception e) {
			msg.setMessageCode("400");
			e.printStackTrace();
			logger.info("数据字典[禁用]异常");
		}
		return msg;
	}

	/**
	 * <p>根据id启用数据字典</p>
	 * @param dictId	数据字典id
	 */
	@RequiresPermissions("dictManager:enable")
	@RequestMapping(value = "/systemDict/enable", method = RequestMethod.POST)
	@ResponseBody
	public DataMsg enableSystemDict(@RequestParam("dictId") int dictId) {
		DataMsg msg = new DataMsg();
		try {
			systemDictFeignClient.enableSystemDict(dictId);
			msg.setMessageCode("200");
		} catch (Exception e) {
			msg.setMessageCode("400");
			e.printStackTrace();
			logger.info("数据字典[启用]异常");
		}
		return msg;
	}

	/**
	 * <p>查询数据字典</p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/systemDict/tree", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeView> findSystemDictTreeList(){
		try {
			List<TreeView> systemDictTreeList = systemDictFeignClient.findSystemDictTreeList();
			return systemDictTreeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * <p>分页查询数据字典</p>
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@RequiresPermissions("dictManager:list")
	@RequestMapping(value = "/systemDict/page", method = RequestMethod.POST)
	@ResponseBody
	public DataMsg findSystemDictPage(javax.servlet.http.HttpServletRequest request, DataMsg dataMsg){

		try {
			Map<String,Object> params = new HashMap<>();
			String pageNo = request.getParameter("pageNumber");
			if (StringUtil.isNotBlank(pageNo)) {
				params.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
			}else{
				params.put("pageNo",1);
			}
			String pageSize = request.getParameter("pageSize");
			if (StringUtil.isNotBlank(pageSize)) {
				params.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
			}else{
				params.put("pageSize",10);
			}
			String dictTypeName = request.getParameter("dictTypeName");
			if(StringUtils.isNotBlank(dictTypeName)){
				params.put("dictTypeName",dictTypeName);
			}
			String dictName = request.getParameter("dictName");
			if(StringUtils.isNotBlank(dictName)){
				params.put("dictName",dictName);
			}

			PageModel pageModel = systemDictFeignClient.findSystemDictPage(params);
			List list = pageModel.getList();
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
			dataMsg.setMessageCode("200");
		} catch (Exception e) {
			dataMsg.setMessageCode("400");
			e.printStackTrace();
			logger.error("数据字典[查询]异常");
		}
		return dataMsg;
	}

	/**
	 * 通过dictType查询字典
	 * 
	 * @return
	 */
	@RequestMapping("/systemDict/findDictNameByDictType")
	@ResponseBody
	public List<Map<String,Object>> findDictNameByDictType(HttpServletRequest request) {
		try {
			String dictType = request.getParameter("dictType");
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if(StringUtil.isNotBlank(dictType)){
				try {
					list = redisUtil.getValueByKey(dictType);
				} catch (Exception e) {
					logger.error("===========redis异常=========="+e);
				}
				if(null == list || list.size() ==0 ){
					list = systemDictFeignClient.findDictNameByDictType(dictType);
				}
			}
			return list;
		} catch (Exception e) {
			logger.info("通过dictType查询字典异常！"+e);
		}
		return null;
	}
	
	
	/**
	 * 通过dictType + dictCode查询字典
	 * 
	 * @return
	 */
	@RequestMapping("/systemDict/findSystemDictByTypeAndCode")
	@ResponseBody
	public Map<String,Object> findSystemDictByTypeAndCode(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String dictType = request.getParameter("dictType");
			String dictCode = request.getParameter("dictCode");
			if (StringUtil.isNotBlank(dictType) && StringUtil.isNotBlank(dictCode)) {
				Map<String, Object> paramsCondition = new HashMap<String, Object>();
				paramsCondition.put("dictType", dictType);
				paramsCondition.put("dictCode", dictCode);
				try {
					map = (Map<String, Object>) redisUtil.get(dictType + dictCode);
				} catch (Exception e) {
					logger.error("===========redis异常=========="+e);
				}
				if(null == map){
					map = systemDictFeignClient.findSystemDictByTypeAndCode(paramsCondition);
				}
			}
			return map;
		} catch (Exception e) {
			logger.error("==通过dictType + dictCode查询字典异常=="+e);
		}
		return null;
	}
	
	
	/**
	 * 通过parentId查询地区表
	 * 
	 * @return
	 */
	@RequestMapping("/systemDict/findDistrictByParentId")
	@ResponseBody
	public String findDistrictByParentId(HttpServletRequest request) {
			String json = null ;
		try {
			String parentId = request.getParameter("parentId");
			if (StringUtil.isNotBlank(parentId)) {
				try {
					 json = String.valueOf(redisUtil.get(parentId));
				} catch (Exception e) {
					logger.error("===========redis异常=========="+e);
				}
				if(StringUtil.isEmpty(json)){
					json = systemDictFeignClient.findDistrictByParentId(parentId);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return json;
	}
	@RequestMapping("/systemDict/getParamsData")
	@ResponseBody
	public ReturnMsgData getParamsData(){
		ReturnMsgData msg = new ReturnMsgData();
		try {

			Map<String, Object> data = systemDictFeignClient.getParamsData();
			msg.setData(data);
			msg.setReturnCode("200");
			logger.info("返回数据：" + data);
		} catch (RuntimeException e) {
			msg.setReturnCode("400");
			e.printStackTrace();
			logger.info("***selectSearchParams***异常");
		}
		return msg;
	}

	/**
	 * 通过dictType查询字典
	 *
	 * @return
	 */
	@RequestMapping("/systemDict/findDictByDictTypeRetEntity")
	@ResponseBody
	public Map<String, Object> findDictByDictTypeRetEntity(@RequestParam("dictType") String dictType) {
		Map<String, Object> resMap = null;
		List<SystemDict> list = null;
		try {
			if(StringUtil.isNotBlank(dictType)){
				list = systemDictFeignClient.findDictByDictTypeRetEntity(dictType);
				resMap = list.stream().collect(Collectors.toMap(SystemDict::getDictCode, SystemDict::getDictName));
			}
		} catch (Exception e) {
			logger.info("通过dictType查询字典异常！", e);
		}
		return resMap;
	}
}
