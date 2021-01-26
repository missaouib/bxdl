package com.hzcf.basic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hzcf.basic.service.SystemDictService;
import com.hzcf.basic.util.RedisUtil;
import com.hzcf.pojo.basic.District;

/**
*<dl>
*<dt>类名：CacheController.java</dt>
*<dd>描述: 缓存实现</dd>
*<dd>创建时间： 2018年10月29日 下午4:57:25</dd>
*<dd>创建人：tie</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2018年10月29日 下午4:57:25 tie 1.0 1.0 Version
* </pre>
*</dl>
*/
@Component 
public class CacheController implements InitializingBean {


	 @Autowired
	 RedisUtil redisUtil;
	 @Autowired
	 private SystemDictService systemDictService;
	 
	/**
	 * 系统初始化后加载
	 * 字典表
	 * 地区表
	 */
	@Override
	public void afterPropertiesSet(){
		try {
			//字典表
			List<Map<String, Object>> list = systemDictService.findAllSystemDict();
			for (Map<String, Object> map : list) {
				redisUtil.set(String.valueOf(map.get("dict_type"))+String.valueOf(map.get("dict_code")),map);
			}
			//地区表
			List<District> districtList = systemDictService.findAllDistrict();
			setRedisValue(districtList);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void setRedisValue(List<District> districtList) {
		Long pId = districtList.get(0).getParentid();
		List<District> data = new ArrayList<District>() ;
		for (District dd : districtList) {
			if(pId.longValue() != dd.getParentid().longValue()){
				pId = dd.getParentid();
				redisUtil.set(String.valueOf(data.get(0).getParentid()), JSON.toJSONString(data));
				data= new ArrayList<District>();
			}
			if(pId.longValue() == dd.getParentid().longValue()){
				data.add(dd);
			}
			if(pId.longValue() == districtList.get(districtList.size() -1).getParentid().longValue()){
				redisUtil.set(String.valueOf(data.get(0).getParentid()), JSON.toJSONString(data));
			}
		}
	}
	

}
