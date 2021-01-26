package com.hzcf.core.service.impl;

import java.util.List;
import java.util.Map;

import com.hzcf.core.mapper.SalesOrgInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.core.mapper.PartnershipCommissionSetMapper;
import com.hzcf.core.mapper.PartnershipCommissionSubMapper;
import com.hzcf.core.mapper.ProductsCommissionRatioMapper;
import com.hzcf.core.service.PartnershipCommissionSetService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.parameter.PartnershipCommissionSet;
import com.hzcf.pojo.parameter.PartnershipCommissionSub;
@Service
public class PartnershipCommissionSetServiceImpl implements PartnershipCommissionSetService {
	
    @Autowired
    private PartnershipCommissionSetMapper partnershipCommissionSetMapper;
    @Autowired
    private ProductsCommissionRatioMapper productsCommissionRatioMapper;
    @Autowired
    private PartnershipCommissionSubMapper partnershipCommissionSubMapper;
	@Autowired
	private SalesOrgInfoMapper salesOrgInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public PageModel getPartnershipCommissionSetList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());

		//数据权限相关
		String myDeptNo = "";
		if(params.get("myDeptNo") != null){
			myDeptNo = String.valueOf(params.get("myDeptNo"));
		}
		String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
		params.put("myAllOrgIds", myAllOrgIds);


		List<Map<String,Object>> list = partnershipCommissionSetMapper.getPartnershipCommissionSetList(params);
        long size = partnershipCommissionSetMapper.getPartnershipCommissionSetSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }
    
    
    @Override
  	public PageModel getPartnershipListSubByAdd(Map<String, Object> params) {
      	
  		    PageModel model = new PageModel();
  	        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
  	        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
  	        params.put("startIndex", model.getStartIndex());
  	        params.put("endIndex", model.getEndIndex());
  	        params.put("pageSize", model.getPageSize());
  	        List<Map<String,Object>> list = productsCommissionRatioMapper.getPartnershipListSub(params);
  	        long size = productsCommissionRatioMapper.getPartnershipListSize(params);
  	        model.setList(list);
  	        model.setTotalRecords(size);
  	        return model;
  	}
    @Override
	public PageModel getPartnershipListSub(Map<String, Object> params) {
    	
		    PageModel model = new PageModel();
	        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
	        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
	        params.put("startIndex", model.getStartIndex());
	        params.put("endIndex", model.getEndIndex());
	        params.put("pageSize", model.getPageSize());
	        List<Map<String,Object>> list = productsCommissionRatioMapper.getPartnershipListSub(params);
	        long size = productsCommissionRatioMapper.getPartnershipListSize(params);
	        model.setList(list);
	        model.setTotalRecords(size);
	        return model;
	}

    @Override
    public void addPartnershipCommissionSet(PartnershipCommissionSet partnershipCommissionSet) {
    	partnershipCommissionSetMapper.insertSelective(partnershipCommissionSet);
    }

   @Override
    public void updatePartnershipCommissionSet(PartnershipCommissionSet partnershipCommissionSet) {
    	partnershipCommissionSetMapper.updateByPrimaryKeySelective(partnershipCommissionSet);
    }

    @Override
	public PartnershipCommissionSet selectPartnershipCommissionSetDetail(Map<String, Object> params) {
		
		return partnershipCommissionSetMapper.selectPartnershipCommissionSetDetail(params);
	}

	@Override
	public List<PartnershipCommissionSub> getPartnershipCommissionSubList(Map<String, Object> params) {
		return partnershipCommissionSubMapper.getPartnershipCommissionSubList(params);
	}

	@Override
	public void addPartnershipCommissionSub(PartnershipCommissionSub partnershipCommissionSub) {
	 partnershipCommissionSubMapper.insertSelective(partnershipCommissionSub);
	}

	@Override
	public void updatePartnershipCommissionSub(PartnershipCommissionSub partnershipCommissionSub) {
		partnershipCommissionSubMapper.updateByPrimaryKeySelective(partnershipCommissionSub);
	}

	@Override
	public PageModel getPartnershipListSubEdit(Map<String, Object> params) {
    	
		    PageModel model = new PageModel();
	        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
	        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
	        params.put("startIndex", model.getStartIndex());
	        params.put("endIndex", model.getEndIndex());
	        params.put("pageSize", model.getPageSize());
	        String paramet=params.get("paramet").toString();
		    if(paramet.equals("1")){
		    	List<Map<String,Object>> list = partnershipCommissionSubMapper.getPartnershipListSubs(params);
		        long size = partnershipCommissionSubMapper.getPartnershipListsSize(params);
		        model.setList(list);
		        model.setTotalRecords(size);
		        return model;
		    }
	        List<Map<String,Object>> list = productsCommissionRatioMapper.getPartnershipListSub(params);
	        long size = productsCommissionRatioMapper.getPartnershipListSize(params);
	        model.setList(list);
	        model.setTotalRecords(size);
	        return model;
	}


	@Override
	public List<PartnershipCommissionSet> findPartnershipCommissionSet(Map<String, Object> params) {
		return partnershipCommissionSetMapper.findPartnershipCommissionSet(params);
	}

	@Override
	public Map<String, Object> selectCommissionRate(Map<String, Object> par) {
		return partnershipCommissionSetMapper.selectCommissionRate(par);
	}


}

