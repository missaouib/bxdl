package com.hzcf.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.hzcf.core.mapper.InsuranceTypeMappingMapper;
import com.hzcf.core.service.InsuranceTypeMappingService;
import com.hzcf.core.util.AESUtil;
import com.hzcf.core.util.HttpRequestUtil;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.product.InsuranceTypeMapping;
import com.hzcf.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InsuranceTypeMappingServiceImpl implements InsuranceTypeMappingService   {

	 @Autowired
	 private InsuranceTypeMappingMapper insuranceTypeMappingMapper;
	 
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public PageModel selectInsuranceType(Map<String, Object> params) {
		
		PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = insuranceTypeMappingMapper.selectInsuranceTypeList(params);
        long size = insuranceTypeMappingMapper.selectInsuranceTypeListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
	}

	/*@Override
	public void updateInsuranceType(InsuranceTypeMapping insuranceTypeMapping) {
		insuranceTypeMappingMapper.updateByPrimaryKeySelective(insuranceTypeMapping);
	}*/

	@Override
	public void delInsuranceType(String ids) {
		String[] split = ids.split(",");
		insuranceTypeMappingMapper.delInsuranceType(split);
	}
/*
	@Override
	public void addInsuranceType(InsuranceTypeMapping insuranceTypeMapping) {

		insuranceTypeMappingMapper.insertSelective(insuranceTypeMapping);
	}*/
	
	
	/**
	 * 添加推送保险公司险种类别数据
	 */
	 //@Value("${insurance.http.url}")
	  //  private String insUrl;
	    @Override
	    public void addInsuranceType(InsuranceTypeMapping insuranceTypeMapping) {
	        Map<String,Object> map = new HashMap<String,Object>();
	        Map<String, Object>maps = new HashMap<String,Object>();
	        try{
	            SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            insuranceTypeMapping.setCreatedTime(fmat.parse(fmat.format(new Date())));
				insuranceTypeMappingMapper.insertSelectiveReturnKey(insuranceTypeMapping);
	            
//	            InsuranceTypeMapping insuranceTypeMappingMap=insuranceTypeMappingMapper.selectByPrimaryKey(insuranceTypeMapping.getInsuranceTypeId());
//	            maps = getInsuranceTypeAttribute(insuranceTypeMappingMap);
//	            map.put("insuranceTypeMapping",maps);
//	            String js = JSON.toJSONString(map);
//	            String aesu = AESUtil.enCrypt(js, "1234567890123456");
//	            logger.info("新增：同步数据到保险产品系统开始"+map.toString());
//	            String s = HttpRequestUtil.sendPostForSendMsg(insUrl, aesu);
//	            addErrorData(maps);
	         }catch (RuntimeException e){
	            e.printStackTrace();
	             addErrorData(maps);
	         }catch (Exception e){
	             e.printStackTrace();
	             addErrorData(maps);
	         }
	    }
	    
	    /**
	     * 修改推送保险公司险种类别数据
	     * @param insuranceDept
	     */
	    @Override
	    public void updateInsuranceType(InsuranceTypeMapping insuranceTypeMapping) {
	        Map<String,Object> map = new HashMap<String,Object>();
	        Map<String, Object>maps = new HashMap<String,Object>();
	        try{
	        	
	            SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            insuranceTypeMapping.setUpdatedTime(fmat.parse(fmat.format(new Date())));
				insuranceTypeMappingMapper.updateByPrimaryKeySelective(insuranceTypeMapping);
	            
	            /*InsuranceTypeMapping insuranceTypeMappingMap=insuranceTypeMappingMapper.selectByPrimaryKey(insuranceTypeMapping.getInsuranceTypeId());
	            maps = getInsuranceTypeAttribute(insuranceTypeMappingMap);
	            
	            map.put("insuranceTypeMapping",maps);
	            String js = JSON.toJSONString(map);
	            String aesu = AESUtil.enCrypt(js, "1234567890123456");
	            logger.info("修改:同步数据到保险系统开始"+map.toString());
	            String s = HttpRequestUtil.sendPostForSendMsg(insUrl, aesu);
	            addErrorData(maps);*/
	        }catch (RuntimeException e){
	            e.printStackTrace();
	            addErrorData(maps);
	        }catch (Exception e){
	            e.printStackTrace();
	            addErrorData(maps);
	        }
	    }

		public Map<String,Object> getInsuranceTypeAttribute(InsuranceTypeMapping insuranceTypeMapping){
	        Map<String,Object> map = new HashMap<>();//封装请求参数
	       String createdBy = insuranceTypeMapping.getCreatedBy();
	        map.put("createdBy", createdBy);
	        
	        String insuranceTypeName = insuranceTypeMapping.getInsuranceTypeName();
	        map.put("insuranceTypeName", insuranceTypeName);
	        
	       
	        if (insuranceTypeMapping.getInsuranceCompanyId()!=null) {
	        map.put("insuranceCompanyId", insuranceTypeMapping.getInsuranceCompanyId());
	        }
	       Date createdTime = insuranceTypeMapping.getCreatedTime();
           if (null!=createdTime&&!"".equals(createdTime)) {
				
	        	map.put("createdTime",DateUtil.getTimeNormalString(createdTime));
			}
	        
           Date updatedTime = insuranceTypeMapping.getUpdatedTime();
	        if (null!=updatedTime&&!"".equals(updatedTime)) {
				
	        	map.put("updatedTime",DateUtil.getTimeNormalString(updatedTime));
			}
	        
	        Long insuranceTypeId = insuranceTypeMapping.getInsuranceTypeId();
	        map.put("insuranceTypeId", insuranceTypeId);
	        
	        String sysParameter =insuranceTypeMapping.getSysParameter();
	        if (sysParameter!=null&&!"".equals(sysParameter)) {
	        map.put("sysParameter",sysParameter);
	        }
	        String insuranceTypeCode = insuranceTypeMapping.getInsuranceTypeCode();
	        map.put("insuranceTypeCode", insuranceTypeCode);
	        
	        if (insuranceTypeMapping.getParentInsuranceTypeId()!=null) {
	        map.put("parentInsuranceTypeId", insuranceTypeMapping.getParentInsuranceTypeId());
	        }
	        String updatedBy = insuranceTypeMapping.getCreatedBy();
	        map.put("updatedBy", updatedBy);
	        
	        map.put("directorAllowanceStandard", "");
	        return map;
	    }
	    private void addErrorData(Map<String,Object> maps){
	        final String sys="bx";
	        insuranceTypeMappingMapper.addErrorData(maps.get("insuranceTypeId").toString(),sys);
	    }

		@Override
		public InsuranceTypeMapping selectInsuranceTypeByid(Map<String, Object> params) {
			return insuranceTypeMappingMapper.selectInsuranceTypeByid(params);
		}

		@Override
		public List<InsuranceTypeMapping> findInsuranceTypeMapping(Map<String, Object> params) {
			
			return insuranceTypeMappingMapper.findInsuranceTypeMapping(params);
		}
		
}

