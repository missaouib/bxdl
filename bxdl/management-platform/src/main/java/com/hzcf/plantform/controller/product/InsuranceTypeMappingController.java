package com.hzcf.plantform.controller.product;

import com.hzcf.plantform.feign.product.InsuranceTypeMappingClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.product.InsuranceTypeMapping;
import com.hzcf.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Insurance_type_manager")
public class InsuranceTypeMappingController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InsuranceTypeMappingClient insuranceTypeMappingClient;

    /**
     * 跳转到险种类别页面
     */
    /*@RequiresPermissions("insuranceType:list")*/
    @RequestMapping("to_Insurance_type")
    public String toInsuranceTypePage() {
        return "InsuranceType/InsuranceTypeList";
    }

    /**
     * 分页查询险种类别LIst
     */
    @RequestMapping("/do_Insurance_type")
    @ResponseBody
    public DataMsg selectInsuranceType(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> paras = new HashMap<>();
            String pageNo = request.getParameter("pageNo");
            if (StringUtil.isNotBlank(pageNo)) {
                paras.put("pageNo", Integer.valueOf(request.getParameter("pageNo")));
            }
            String pageSize = request.getParameter("pageSize");
            if (StringUtil.isNotBlank(pageSize)) {
                paras.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            }

            PageModel pageModel = insuranceTypeMappingClient.selectInsuranceType(paras);
            List list = pageModel.getList();
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
            dataMsg.setMessageCode("200");
        } catch (RuntimeException e) {
            dataMsg.setMessageCode("400");
            logger.info("险种类别[查询]异常");
            e.printStackTrace();
        }

        return dataMsg;
    }

    /**
     * 跳转添加页面
     */
    @RequestMapping("/toAddPath")
    public String toAddPath() {
        return "InsuranceType/InsuranceTypeAdd";
    }

    /**
     * 增加险种类别
     */
    /*  @RequiresPermissions("insuranceType:add")*/
    @RequestMapping("/add_Insurance_type")
    @ResponseBody
    public DataMsg addInsuranceType(InsuranceTypeMapping insuranceTypeMapping, DataMsg msg) {
        try {
            logger.info("新增险种类别数据：" + insuranceTypeMapping);
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceTypeCode", insuranceTypeMapping.getInsuranceTypeCode());
            paras.put("sysParameter", insuranceTypeMapping.getSysParameter());
            paras.put("insuranceCompanyId", insuranceTypeMapping.getInsuranceCompanyId()==null?null:insuranceTypeMapping.getInsuranceCompanyId());
            List<InsuranceTypeMapping> hasList = insuranceTypeMappingClient.findInsuranceTypeMapping(paras);
            if(hasList!=null && hasList.size()>0){
            	msg.setMessageCode("500");
            }else{
            	insuranceTypeMappingClient.addInsuranceType(insuranceTypeMapping);
            	msg.setMessageCode("200");
            }           
        } catch (RuntimeException e) {
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("险种类别[新增]异常");
        }
        return msg;
    }

    /**
     * 跳转修改页面
     */
    @RequestMapping("/toEditPath")
    public String toEditPath(HttpServletRequest request, Model model) {

        String insuranceTypeId = request.getParameter("id");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceTypeId", insuranceTypeId);
        InsuranceTypeMapping insuranceTypeMapping = insuranceTypeMappingClient.selectInsuranceTypeByid(paras);
        model.addAttribute("insuranceTypeMapping", insuranceTypeMapping);
        return "InsuranceType/InsuranceTypeEdit";
    }

    /**
     * 修改险种类别
     */
    /*@RequiresPermissions("insuranceType:update")*/
    @RequestMapping("/update_Insurance_type")
    @ResponseBody
    public DataMsg updateInsuranceType(InsuranceTypeMapping insuranceTypeMapping, DataMsg msg) {
        try {
            logger.info("修改险种类别数据" + insuranceTypeMapping);
            Map<String, Object> paras = new HashMap<>();
            paras.put("insuranceTypeCode", insuranceTypeMapping.getInsuranceTypeCode());
            paras.put("sysParameter", insuranceTypeMapping.getSysParameter());
            paras.put("insuranceCompanyId", insuranceTypeMapping.getInsuranceCompanyId()==null?null:insuranceTypeMapping.getInsuranceCompanyId());
            List<InsuranceTypeMapping> hasList = insuranceTypeMappingClient.findInsuranceTypeMapping(paras);
            Boolean flag = true;
            if(hasList!=null && hasList.size()>0){
            	for(InsuranceTypeMapping hasone:hasList){
            		if(hasone.getInsuranceTypeId() != insuranceTypeMapping.getInsuranceTypeId()){
            			flag = false;
            			break;
            		}
            	}
            	if(flag){
    	            insuranceTypeMappingClient.updateInsuranceType(insuranceTypeMapping);
    	            msg.setMessageCode("200");            		
            	}else{
            		msg.setMessageCode("500");
            	}
            }else{
	            insuranceTypeMappingClient.updateInsuranceType(insuranceTypeMapping);
	            msg.setMessageCode("200");
            }
        } catch (RuntimeException e) {
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("险种类别[修改]异常");
        }
        return msg;
    }

    /**
     * 删除险种类别
     */
    @RequiresPermissions("insuranceType:del")
    @RequestMapping("/del_Insurance_type")
    @ResponseBody
    public DataMsg delInsuranceType(String ids, DataMsg msg) {
        try {
            logger.info("险种类别删除数据ID" + ids);
            insuranceTypeMappingClient.delInsuranceType(ids);
            msg.setMessageCode("200");

        } catch (RuntimeException e) {
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("险种类别[删除]异常");
        }
        return msg;
    }

    /**
     * 删除时做状态处理
     */
    @RequestMapping("/update_type_status")
    @ResponseBody
    public DataMsg updateTypeStatus(HttpServletRequest request, DataMsg msg) {
        InsuranceTypeMapping insuranceTypeMapping = new InsuranceTypeMapping();

        try {

            insuranceTypeMapping.setInsuranceTypeId(Long.parseLong(request.getParameter("id")));
            insuranceTypeMapping.setIsNormal("1");
            logger.info("险种状态" + insuranceTypeMapping.getIsNormal(), "险种id" + insuranceTypeMapping.getInsuranceTypeId());

            insuranceTypeMappingClient.updateInsuranceType(insuranceTypeMapping);
            msg.setMessageCode("200");
            logger.info("险种类别状态[更新]成功");
        } catch (RuntimeException e) {
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("险种类别状态[更新]异常");
        }
        return msg;
    }

    /**
     * 查询List
     */
    @RequestMapping("/find_types")
    @ResponseBody
    public DataMsg findInsuranceTypeMapping(HttpServletRequest request, DataMsg dataMsg) {
        try {
            Map<String, Object> paras = new HashMap<>();
            String insuranceCompanyId = request.getParameter("insuranceCompanyId");
            if (StringUtils.isNotBlank(insuranceCompanyId)) {
                paras.put("insuranceCompanyId", insuranceCompanyId);
            }
            String parentInsuranceTypeId = request.getParameter("parentInsuranceTypeId");
            if (StringUtils.isNotBlank(parentInsuranceTypeId)) {
                paras.put("parentInsuranceTypeId", parentInsuranceTypeId);
            }
            String sysParameter = request.getParameter("sysParameter");
            if (StringUtils.isNotBlank(sysParameter)) {
                paras.put("sysParameter", sysParameter);
            }
            List<InsuranceTypeMapping> list = insuranceTypeMappingClient.findInsuranceTypeMapping(paras);
            dataMsg.setRows(list);
            dataMsg.setMessageCode("200");
        } catch (Exception e) {
            dataMsg.setMessageCode("400");
            e.printStackTrace();
            logger.error("类别[查询]异常异常");
        }
        return dataMsg;
    }

}
