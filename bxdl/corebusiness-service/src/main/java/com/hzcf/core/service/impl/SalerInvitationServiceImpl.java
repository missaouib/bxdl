package com.hzcf.core.service.impl;

import com.hzcf.core.mapper.SalerInvitationMapper;
import com.hzcf.core.mapper.SalesOrgInfoMapper;
import com.hzcf.core.service.SalerInvitationService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalerInvitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SalerInvitationServiceImpl implements SalerInvitationService {

	@Autowired
    private SalerInvitationMapper salerInvitationMapper;

    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;

    @Override
    public void addSalerInvitation(SalerInvitation salerInvitation) {
    	salerInvitationMapper.addSalerInvitation(salerInvitation);
    } 
    
    @Override
    public PageModel getSalerInvitationPage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getPageSize());

        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String,Object>> list = salerInvitationMapper.getSalerInvitationPage(params);
        long size = salerInvitationMapper.getSalerInvitationListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

	@Override
	public SalerInvitation selectById(Map<String, Object> params) {
		SalerInvitation salerInvitation = salerInvitationMapper.selectById(params);
		return salerInvitation;
	}

	@Override
	public void updateSalerInvitation(SalerInvitation salerInvitation) {
		salerInvitationMapper.updateSalerInvitation(salerInvitation);
	}
	
}

