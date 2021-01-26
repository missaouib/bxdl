package com.hzcf.core.service.impl;

import com.hzcf.core.mapper.SalesTeamInfoMapper;
import com.hzcf.core.mapper.SalesTeamLeadersMapper;
import com.hzcf.core.service.SalesTeamLeadersService;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.SalesTeamInfo;
import com.hzcf.pojo.insurance.SalesTeamLeaders;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalesTeamLeadersServiceImpl implements SalesTeamLeadersService {

	@Autowired
    private SalesTeamLeadersMapper salesTeamLeadersMapper;

	@Autowired
	private SalesTeamInfoMapper salesTeamInfoMapper;

    @Override
    public void addSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders) {
    	salesTeamLeadersMapper.addSalesTeamLeaders(salesTeamLeaders);
    } 
    
    @Override
    public PageModel getSalesTeamLeadersPage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getPageSize());
        List<Map<String,Object>> list = salesTeamLeadersMapper.getSalesTeamLeadersPage(params);
        long size = salesTeamLeadersMapper.getSalesTeamLeadersListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

	@Override
	public SalesTeamLeaders selectById(Map<String, Object> params) {
		SalesTeamLeaders salesTeamLeaders = salesTeamLeadersMapper.selectById(params);
		return salesTeamLeaders;
	}

	@Override
	public void updateSalesTeamLeaders(SalesTeamLeaders salesTeamLeaders) {
		salesTeamLeadersMapper.updateSalesTeamLeaders(salesTeamLeaders);
	}

	@Override
	public List<SalesTeamLeaders> getSalesTeamLeadersList(Map<String, Object> params) {
		return salesTeamLeadersMapper.getSalesTeamLeadersList(params);
	}

	@Override
	public void disableLeaders(Map<String, Object> params) {
		salesTeamLeadersMapper.disableLeaders(params);
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateTeams(SalesTeamInfo salesTeamInfo, List<SalesTeamInfo> allChildTeamList, SalesTeamInfo salesTeamInfoOld) {
		salesTeamInfoMapper.updateSalesTeamInfo(salesTeamInfo);
		//重新生成下属团队treecode,更新
		if(!(allChildTeamList == null || allChildTeamList.isEmpty())){
			salesTeamInfoMapper.updateSalesTeamTreeCode(allChildTeamList);
		}
		//上级销售团队代码修改 同时修改所有直接下级的parentSaleTeamCode
		if (!salesTeamInfo.getSalesTeamCode().equals(salesTeamInfoOld.getSalesTeamCode())){
			salesTeamInfoMapper.updateparentSaleTeamCode(salesTeamInfoOld.getSalesTeamCode(),salesTeamInfo.getSalesTeamCode());
		}

	}
	
}

