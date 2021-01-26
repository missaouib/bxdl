package com.hzcf.core.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzcf.core.mapper.InsuranceDeptMapper;
import com.hzcf.core.service.InsuranceDeptService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.insurance.InsuranceDept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InsuranceDeptServiceImpl implements InsuranceDeptService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InsuranceDeptMapper insuranceDeptMapper;

    @Override
    public PageModel getInsuranceDeptList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSizeInt", Integer.valueOf(params.get("pageSize").toString()));
        params.put("pageNoInt", Integer.valueOf(params.get("pageNo").toString()));
        List<Map<String,Object>> list = insuranceDeptMapper.getInsuranceDeptList(params);
     /*   for(Map<String, Object> map:list){
			if(map.get("ORG_LEVEL").equals("01")){
				map.put("insuranceCompany", map.get("INSURANCE_COMPANY_NAME"));
				map.put("parentCompany", "-");
			}else{
				InsuranceDept pinsuranceDept = insuranceDeptMapper.getInsCompanyByCode(map.get("PARENT_COMPANY_CODE").toString());
				map.put("parentCompany", pinsuranceDept.getInsuranceCompanyName());
				InsuranceDept insuranceCompany = insuranceDeptMapper.getInsCompanyByCode(map.get("INSURANCE_CODE").toString());
				map.put("insuranceCompany", insuranceCompany.getInsuranceCompanyName());
			}
		}*/
        long size = insuranceDeptMapper.getInsuranceDeptSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }
    
    @Override
    public List<InsuranceDept> findInsurCompanys(Map<String, Object> params) {
        return insuranceDeptMapper.findInsurCompanys(params);
    }

    @Override
    public InsuranceDept selectSubsetById(String id) {
        return insuranceDeptMapper.selectSubsetById(id);
    }

    @Override
    public InsuranceDept selectSubsetOrgById(String id) {
        return insuranceDeptMapper.selectSubsetOrgById(id);
    }

    @Override
    public void deleteInsuranceOrg(String params) {
        String[] split = params.split(",");
        insuranceDeptMapper.deleteInsuranceOrg(split);
    }

    @Override
    public List<Map<String, Object>> selectAllOrg() {
        return insuranceDeptMapper.selectAllOrg();
    }

    @Override
    public Map<String, Object> selectOrg(Map<String, Object> pra) {
        return insuranceDeptMapper.selectOrg(pra);
    }

    //@Value("${insurance.http.url}")
    //private String insUrl;
    @Override
    public void addInsuranceDept(InsuranceDept insuranceDept) {
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String, Object>maps = new HashMap<String,Object>();
        try{
            List<InsuranceDept> deptList = insuranceDeptMapper.queryExistOrgByNameOrCode(insuranceDept.getInsuranceCompanyId(), insuranceDept.getInsuranceCompanyName(), insuranceDept.getInsuranceCompanyCode());
            if(CollectionUtils.isEmpty(deptList)){
                SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                insuranceDept.setCreatedTime(fmat.format(new Date()));
                insuranceDeptMapper.addInsuranceDept(insuranceDept);
                /*InsuranceDept dept = insuranceDeptMapper.selectSubsetByCode(insuranceDept.getInsuranceCompanyCode());
                maps = getInsuranceDeptAttribute(dept);
                map.put("insuranceCompanyOrg",maps);
                String js = JSON.toJSONString(map);
                String aesu = AESUtil.enCrypt(js, "1234567890123456");
                logger.info("新增：同步数据到保险系统开始"+map.toString());
                String s = HttpRequestUtil.sendPostForSendMsg(insUrl, aesu);
                JSONObject jsonObject = JSONObject.parseObject(s);
                String code = jsonObject.get("returnCode").toString();
                if("9999".equals(code)){
                    addErrorData(maps);
                }*/
            }else {
                logger.error("新增保险公司名称："+ insuranceDept.getInsuranceCompanyName() + ",公司代码：" + insuranceDept.getInsuranceCompanyCode() + "已存在");
            }
         }catch (RuntimeException e){
            e.printStackTrace();
             addErrorData(maps);
         }catch (Exception e){
             e.printStackTrace();
             addErrorData(maps);
         }
    }

    @Override
    public void updateInsuranceDept(InsuranceDept insuranceDept) {
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String, Object>maps = new HashMap<String,Object>();
        try{
            SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            insuranceDept.setUpdatedTime(fmat.format(new Date()));
            insuranceDeptMapper.updateInsuranceDept(insuranceDept);
            /*if(insuranceDept.getInsuranceCode()!=null){
                InsuranceDept dept = insuranceDeptMapper.selectSubsetByCode(insuranceDept.getInsuranceCode());
                maps = getInsuranceDeptAttribute(dept);
            }else {
                InsuranceDept dept = insuranceDeptMapper.selectSubsetByCode(insuranceDept.getInsuranceCompanyCode());
                maps = getInsuranceDeptAttribute(dept);
            }
            map.put("insuranceCompanyOrg",maps);
            String js = JSON.toJSONString(map);
            String aesu = AESUtil.enCrypt(js, "1234567890123456");
            logger.info("修改：同步数据到保险系统开始"+map.toString());
            String s = HttpRequestUtil.sendPostForSendMsg(insUrl, aesu);
            JSONObject jsonObject = JSONObject.parseObject(s);
            String code = jsonObject.get("returnCode").toString();
            if("9999".equals(code)){
                addErrorData(maps);
            }*/
        }catch (RuntimeException e){
            e.printStackTrace();
            addErrorData(maps);
        }catch (Exception e){
            e.printStackTrace();
            addErrorData(maps);
        }

    }

    @Override
    public void deleteInsuranceDept(String params) {
        String[] split = params.split(",");
        insuranceDeptMapper.deleteInsuranceDept(split);
    }

    @Override
    public InsuranceDept selectInsuranceBasicDept(Map<String, Object> params) {
        return insuranceDeptMapper.selectInsuranceBasicDept(params);
    }

    public Map<String,Object> getInsuranceDeptAttribute(InsuranceDept insuranceDept){
        Map<String,Object> map = new HashMap<>();//封装请求参数
        String insuranceCompanyNameLess = insuranceDept.getInsuranceCompanyNameLess();
        map.put("insuranceCompanyNameLess", insuranceCompanyNameLess);
        String insuranceCompanyEnName = insuranceDept.getInsuranceCompanyEnName();
        map.put("insuranceCompanyEnName",insuranceCompanyEnName );
        String tel = insuranceDept.getTel();
        map.put("tel",tel );
        String mainBusiness = insuranceDept.getMainBusiness();
        map.put("mainBusiness",mainBusiness );
        String address = insuranceDept.getAddress();
        map.put("address",address );
        BigDecimal registeredCapital = insuranceDept.getRegisteredCapital();
        map.put("registeredCapital",registeredCapital );
        String updatedBy = insuranceDept.getUpdatedBy();
        map.put("updatedBy",updatedBy );
        String cooperationType = insuranceDept.getCooperationType();
        map.put("cooperationType",cooperationType );
        String parentCompanyCode = insuranceDept.getParentCompanyCode();
        map.put("parentCompanyCode",parentCompanyCode );
        String insuranceCompanyEnNameLess = insuranceDept.getInsuranceCompanyEnNameLess();
        map.put("insuranceCompanyEnNameLess",insuranceCompanyEnNameLess );
        String webSite = insuranceDept.getWebSite();
        map.put("webSite",webSite );
        String updatedTime = insuranceDept.getUpdatedTime();
        map.put("updatedTime",updatedTime );
        Long insuranceCompanyId = insuranceDept.getInsuranceCompanyId();
        map.put("insuranceCompanyId",insuranceCompanyId );
        String fax = insuranceDept.getFax();
        map.put("fax",fax );
        String createdTime = insuranceDept.getCreatedTime();
        map.put("createdTime",createdTime);
        String areaCode = insuranceDept.getAreaCode();
        map.put("areaCode",areaCode );
        String dateOfEstablishment = insuranceDept.getDateOfEstablishment();
        map.put("dateOfEstablishment",dateOfEstablishment );
        String postCode = insuranceDept.getPostCode();
        map.put("postCode",postCode );
        String businessType = insuranceDept.getBusinessType();
        map.put("businessType",businessType );
        String insuranceCompanyCode = insuranceDept.getInsuranceCompanyCode();
        map.put("insuranceCompanyCode",insuranceCompanyCode );
        String createdBy = insuranceDept.getCreatedBy();
        map.put("createdBy",createdBy );
        String insuranceCompanyName = insuranceDept.getInsuranceCompanyName();
        map.put("insuranceCompanyName",insuranceCompanyName );
        String orgLevel = insuranceDept.getOrgLevel();
        map.put("orgLevel",orgLevel );
        String businessStatus = insuranceDept.getBusinessStatus();
        map.put("businessStatus",businessStatus );
        map.put("directorAllowanceStandard", "");
        return map;
    }
    private void addErrorData(Map<String,Object> map){
        final String sys="bx";
        insuranceDeptMapper.addErrorData(map.get("insuranceCompanyCode").toString(),sys);
    }

    @Override
    public boolean findExistOrgByNameOrCode(Long id, String name, String code) {
        List<InsuranceDept> deptList = insuranceDeptMapper.queryExistOrgByNameOrCode(id, name, code);
        return !deptList.isEmpty();
    }

    @Override
    public void insertImportList(Map<String, Object> p) {
       Object insurance_dept= p.get("insurance_dept_string");
       JSONArray objects = JSONObject.parseArray(insurance_dept.toString());
       List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
       lists.forEach(list -> {
           logger.info("插入保险公司数据集：");
          int successPipSize = insuranceDeptMapper.batchAddInsuranceDept(list);
            logger.info("插入保险公司数据集成功条数："+successPipSize);
       });
    }
}

