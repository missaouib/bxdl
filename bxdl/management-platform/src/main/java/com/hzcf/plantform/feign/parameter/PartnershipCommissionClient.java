package com.hzcf.plantform.feign.parameter;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.parameter.PartnershipCommissionSet;
import com.hzcf.pojo.parameter.PartnershipCommissionSub;
import com.hzcf.pojo.product.ProductsCommissionRatio;

@FeignClient(name = "corebusiness-service",fallback = FeignDisableHystrixConfiguration.class)
public interface PartnershipCommissionClient {
	
    @RequestMapping(value = "/partnership_commission/do_partnership_commission",method = RequestMethod.POST)
    public PageModel getPartnershipList(@RequestParam Map<String,Object> paras);

    @RequestMapping(value = "/partnership_commission/add_partnership_commission",method = RequestMethod.POST)
	public Long addPartnershipCommission(@RequestBody PartnershipCommissionSet partnershipCommissionSet);
    
    @RequestMapping(value = "/partnership_commission/update_partnership_commission",method = RequestMethod.POST)
	public void updatePartnershipCommission(@RequestBody PartnershipCommissionSet partnershipCommissionSet);
    
    @RequestMapping(value = "/partnership_commission/select_partnership_commission_info",method = RequestMethod.POST)
	public PartnershipCommissionSet selectPartnershipCommissionSet(@RequestParam Map<String, Object> paras);
    
    @RequestMapping(value = "/partnership_commission/select_partnership_commission_list",method = RequestMethod.POST)
	public List<PartnershipCommissionSub> getPartnershipCommissionSubList(@RequestParam Map<String, Object> paras);
    
    @RequestMapping(value = "/partnership_commission/do_partnership_commission_sub",method = RequestMethod.POST)
	public PageModel getPartnershipListSub(@RequestParam Map<String, Object> paras);
    
    @RequestMapping(value = "/partnership_commission/add_partnership_commission_sub",method = RequestMethod.POST)
	public void addPartnershipCommissionSub(@RequestBody PartnershipCommissionSub partnershipCommissionSub);
    
    @RequestMapping(value = "/partnership_commission/update_partnership_commission_sub",method = RequestMethod.POST)
   	public void updatePartnershipCommissionSub(@RequestBody PartnershipCommissionSub partnershipCommissionSub);

    @RequestMapping(value = "/partnership_commission/do_partnership_commission_sub_edit",method = RequestMethod.POST)
   	public PageModel getPartnershipListSubEdit(@RequestParam Map<String, Object> paras);

    @RequestMapping(value = "/partnership_commission/do_partnership_commission",method = RequestMethod.POST)
	public PageModel getPartnershipCommissionSetList(@RequestParam Map<String, Object> paras);

    @RequestMapping(value = "/partnership_commission/findPartnershipCommissionSet",method = RequestMethod.POST)
	public List<PartnershipCommissionSet> findPartnershipCommissionSet(@RequestParam Map<String, Object> paras);

    @RequestMapping(value = "/partnership_commission/selectCommissionRate",method = RequestMethod.POST)
    Map<String,Object> selectCommissionRate(@RequestParam Map<String, Object> pare);
}
