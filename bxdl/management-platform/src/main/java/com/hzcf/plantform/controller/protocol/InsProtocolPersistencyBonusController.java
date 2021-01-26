package com.hzcf.plantform.controller.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzcf.plantform.feign.protocol.InsProtocolPersistencyBonusClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.pojo.insurance.protocol.InsProtocolPersistencyBonus;
import com.hzcf.pojo.insurance.protocol.PersistencyBonusRule;
import com.hzcf.util.StringUtil;

/**
 * <dl>
 * <dd>协议-继续率奖金</dd>
 * </dl>
 */
@Controller
@RequestMapping("/persistencyBonus")
public class InsProtocolPersistencyBonusController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InsProtocolPersistencyBonusClient insProtocolPersistencyBonusClient;	
	
    /**
     * 跳转到新增页面
     */
    @RequestMapping("/toPbEdit")
    public String toPbEdit(Model model, HttpServletRequest request) {
    	Map<String,Object> map = new HashMap<>();
    	String protocolId = request.getParameter("id");
    	if(protocolId.startsWith("look:")){
    		protocolId = protocolId.substring(5,protocolId.length());
    		request.setAttribute("isLook", "yes");
    	}
    	if(StringUtil.isNotBlank(protocolId)){
    		map.put("protocolId", protocolId);
    	}
    	InsProtocolPersistencyBonus ippbs = insProtocolPersistencyBonusClient.selectPb(map);   			
    	if(ippbs == null){
    		ippbs = new InsProtocolPersistencyBonus();
    		ippbs.setProtocolId(Long.valueOf(protocolId));
    		ippbs.setSettlementCycle("1");
    		Long bonusId = insProtocolPersistencyBonusClient.addPb(ippbs);
    		ippbs.setBonusId(bonusId);
    	}
    	
    	/*获取月-结算周期 奖金规则-公共*/
    	map.clear();    	
    	map.put("persistencyBonusId",ippbs.getBonusId());
    	map.put("basicFlag","0");
    	map.put("computeType","1");
    	List<PersistencyBonusRule> monthyBasic = insProtocolPersistencyBonusClient.getPbrList(map);
    	/*获取通算 奖金规则-公共*/
    	List<PersistencyBonusRule> thrMonthyBasic = new ArrayList<PersistencyBonusRule>();
    	List<PersistencyBonusRule> sixMonthyBasic = new ArrayList<PersistencyBonusRule>();
    	List<PersistencyBonusRule> twnMonthyBasic = new ArrayList<PersistencyBonusRule>();
    	
    	String addSubsidyWay = ippbs.getAddSubsidyWay();
    	if(StringUtil.isNotBlank(addSubsidyWay)){
    		String[] ways = addSubsidyWay.split(",");
    		for(int i=0;i<ways.length;i++){
    			/*季度通算*/
    			if(ways[i].equals("2")){
    		    	map.clear();    	
    		    	map.put("persistencyBonusId",ippbs.getBonusId());
    		    	map.put("basicFlag","0");
    		    	map.put("computeType","2");
    		    	thrMonthyBasic = insProtocolPersistencyBonusClient.getPbrList(map);
    			}else if(ways[i].equals("3")){
    		    	map.clear();    	
    		    	map.put("persistencyBonusId",ippbs.getBonusId());
    		    	map.put("basicFlag","0");
    		    	map.put("computeType","3");
    		    	sixMonthyBasic = insProtocolPersistencyBonusClient.getPbrList(map);   				
    			}else if(ways[i].equals("4")){
    		    	map.clear();    	
    		    	map.put("persistencyBonusId",ippbs.getBonusId());
    		    	map.put("basicFlag","0");
    		    	map.put("computeType","4");
    		    	twnMonthyBasic = insProtocolPersistencyBonusClient.getPbrList(map);   				
    			}
    		}
    	}
    	
    	/*获取例外产品列表*/
    	map.clear();    	
    	map.put("persistencyBonusId",ippbs.getBonusId());
    	map.put("basicFlag","1");
    	List<Object> outProducts = insProtocolPersistencyBonusClient.getOutProducts(map);
    	
	    model.addAttribute("ippbs",ippbs);
	    model.addAttribute("monthyBasic", JSONObject.toJSONString(monthyBasic));
	    model.addAttribute("thrMonthyBasic", JSONObject.toJSONString(thrMonthyBasic));
	    model.addAttribute("sixMonthyBasic", JSONObject.toJSONString(sixMonthyBasic));
	    model.addAttribute("twnMonthyBasic", JSONObject.toJSONString(twnMonthyBasic));
	    model.addAttribute("outProducts", JSONObject.toJSONString(outProducts));
	    model.addAttribute("persistencyBoundsLookProtocolId", request.getParameter("id"));
	    return "lifeProtocol/persistencyBonus";
    }
    
    public static List toList(String s,Class clazz){
        List<Class> list = JSONArray.parseArray(s,clazz);
        return list;
    }
    
    /**
     * 跳转到例外产品新增页面
     */
    @RequestMapping("/toPbExitProAdd")
    public String toPbExitProAdd(Model model, HttpServletRequest request) {
    	Map<String,Object> map = new HashMap<>();
    	String protocolId = request.getParameter("id");
    	if(StringUtil.isNotBlank(protocolId)){
    		map.put("protocolId", protocolId);
    	}
    	InsProtocolPersistencyBonus ippbs = insProtocolPersistencyBonusClient.selectPb(map);
    	/*获取例外产品列表*/
    	map.clear();    	
    	map.put("persistencyBonusId",ippbs.getBonusId());
    	map.put("basicFlag","1");
    	List<Object> outProducts = insProtocolPersistencyBonusClient.getOutProducts(map);
    	model.addAttribute("ippbs",ippbs);
    	model.addAttribute("outProducts", JSONObject.toJSONString(outProducts));
    	return "lifeProtocol/pbExitProAdd";
    }
    
    /**
     * 跳转到例外产品修改页面
     */
    @RequestMapping("/toPbExitProUpd")
    public String toPbExitProUpd(Model model, HttpServletRequest request) {
    	Map<String,Object> map = new HashMap<>();
    	String protId_proId = request.getParameter("id");
    	String[] ass = protId_proId.split("_");
    	String protocolId = ass[0];
    	String exitProductId = ass[1];
    	if (protId_proId.endsWith("_look")) {
    		request.setAttribute("isLook", "yes");
    	}
    	if(StringUtil.isNotBlank(protocolId)){
    		map.put("protocolId", protocolId);
    	}
    	InsProtocolPersistencyBonus ippbs = insProtocolPersistencyBonusClient.selectPb(map);
    	
    	/*获取月-结算周期 奖金规则-公共*/
    	map.clear();    	
    	map.put("persistencyBonusId",ippbs.getBonusId());
    	map.put("basicFlag","1");
    	map.put("exitProductId",exitProductId);
    	map.put("computeType","1");
    	List<PersistencyBonusRule> monthyBasic = insProtocolPersistencyBonusClient.getPbrList(map);
    	/*获取通算 奖金规则-公共*/
    	List<PersistencyBonusRule> thrMonthyBasic = new ArrayList<PersistencyBonusRule>();
    	List<PersistencyBonusRule> sixMonthyBasic = new ArrayList<PersistencyBonusRule>();
    	List<PersistencyBonusRule> twnMonthyBasic = new ArrayList<PersistencyBonusRule>();
    	
    	String addSubsidyWay = ippbs.getAddSubsidyWay();
    	if(StringUtil.isNotBlank(addSubsidyWay)){
    		String[] ways = addSubsidyWay.split(",");
    		for(int i=0;i<ways.length;i++){
    			/*季度通算*/
    			if(ways[i].equals("2")){
    		    	map.clear();    	
    		    	map.put("persistencyBonusId",ippbs.getBonusId());
    		    	map.put("basicFlag","1");
    		    	map.put("exitProductId",exitProductId);
    		    	map.put("computeType","2");
    		    	thrMonthyBasic = insProtocolPersistencyBonusClient.getPbrList(map);
    			}else if(ways[i].equals("3")){
    		    	map.clear();    	
    		    	map.put("persistencyBonusId",ippbs.getBonusId());
    		    	map.put("basicFlag","1");
    		    	map.put("exitProductId",exitProductId);
    		    	map.put("computeType","3");
    		    	sixMonthyBasic = insProtocolPersistencyBonusClient.getPbrList(map);   				
    			}else if(ways[i].equals("4")){
    		    	map.clear();    	
    		    	map.put("persistencyBonusId",ippbs.getBonusId());
    		    	map.put("basicFlag","1");
    		    	map.put("exitProductId",exitProductId);
    		    	map.put("computeType","4");
    		    	twnMonthyBasic = insProtocolPersistencyBonusClient.getPbrList(map);   				
    			}
    		}
    	}
    	
    	/*获取例外产品列表*/
    	map.clear();    	
    	map.put("persistencyBonusId",ippbs.getBonusId());
    	map.put("basicFlag","1");
    	List<Object> outProducts = insProtocolPersistencyBonusClient.getOutProducts(map);
    	model.addAttribute("ippbs",ippbs);
    	model.addAttribute("exitProductId",exitProductId);
    	model.addAttribute("monthyBasic", JSONObject.toJSONString(monthyBasic));
 	    model.addAttribute("thrMonthyBasic", JSONObject.toJSONString(thrMonthyBasic));
 	    model.addAttribute("sixMonthyBasic", JSONObject.toJSONString(sixMonthyBasic));
 	    model.addAttribute("twnMonthyBasic", JSONObject.toJSONString(twnMonthyBasic));
    	model.addAttribute("outProducts", JSONObject.toJSONString(outProducts));
    	return "lifeProtocol/pbExitProUpd";
    }
    
    /**
     * 删除继续率奖金规则
     */
    @RequestMapping("deletePbrByProId")
    @ResponseBody
    public DataMsg deletePbrByProId(DataMsg msg, HttpServletRequest request) {
    	try{
	    	Map<String,Object> map = new HashMap<>();
	    	String exitProductId = request.getParameter("exitProductId");
	    	String persistencyBonusId = request.getParameter("persistencyBonusId");
	    	if(StringUtil.isNotBlank(exitProductId)){
	    		map.put("exitProductId", exitProductId);
	    	}
	    	if(StringUtil.isNotBlank(persistencyBonusId)){
	    		map.put("persistencyBonusId",persistencyBonusId);
	    	}
	    	insProtocolPersistencyBonusClient.deletePbrs(map);
	        msg.setMessageCode("200");
	    } catch (Exception e) {
	        msg.setMessageCode("400");
	        e.printStackTrace();
	        logger.info("操作异常");
	    }
	    return msg;    	
    }
    
    /**
     * 例外产品新增
     */
    @RequestMapping("addExitProPbr")
    @ResponseBody
    public DataMsg addExitProPbr(HttpServletRequest request,DataMsg msg) {
        try {   
        	  String monthyBasic = request.getParameter("monthyBasic");
        	  String thrMonthyBasic = request.getParameter("thrMonthyBasic");
        	  String sixMonthyBasic = request.getParameter("sixMonthyBasic");
        	  String twnMonthyBasic = request.getParameter("twnMonthyBasic");
        	  String addSubsidyWay = request.getParameter("addSubsidyWay");
        	  String exitProductId = request.getParameter("exitProductId");
        	  
        	  if(StringUtil.isNotBlank(monthyBasic)){
          		List<PersistencyBonusRule> pbrs = this.toList(monthyBasic,PersistencyBonusRule.class);
          		if(pbrs.size()>0){
	          		for(PersistencyBonusRule pbr:pbrs){
	          			pbr.setExitProductId(exitProductId);
	          		}
	          		insProtocolPersistencyBonusClient.addPbr(pbrs);
          		}
	          }
        	  
        	  if(StringUtil.isNotBlank(addSubsidyWay)){
	        	  if(addSubsidyWay.equals("2")){
		              	if(StringUtil.isNotBlank(thrMonthyBasic)){
		              		List<PersistencyBonusRule> pbrs = this.toList(thrMonthyBasic,PersistencyBonusRule.class);
		              		if(pbrs.size()>0){
			              		for(PersistencyBonusRule pbr:pbrs){
			              			pbr.setExitProductId(exitProductId);
			              		}
			              		insProtocolPersistencyBonusClient.addPbr(pbrs);
		              		}
		              	}        		
		          }else if(addSubsidyWay.equals("3")){
		              	if(StringUtil.isNotBlank(sixMonthyBasic)){
		              		List<PersistencyBonusRule> pbrs = this.toList(sixMonthyBasic,PersistencyBonusRule.class);
		              		if(pbrs.size()>0){
			              		for(PersistencyBonusRule pbr:pbrs){
			              			pbr.setExitProductId(exitProductId);
			              		}
			              		insProtocolPersistencyBonusClient.addPbr(pbrs);
		              		}
		              	}         		
		          }else if(addSubsidyWay.equals("4")){
		              	if(StringUtil.isNotBlank(twnMonthyBasic)){
		              		List<PersistencyBonusRule> pbrs = this.toList(twnMonthyBasic,PersistencyBonusRule.class);
		              		if(pbrs.size()>0){
			              		for(PersistencyBonusRule pbr:pbrs){
			              			pbr.setExitProductId(exitProductId);
			              		}
			              		insProtocolPersistencyBonusClient.addPbr(pbrs);
		              		}
		              	}         		
		          }
        	  }
        	
              msg.setMessageCode("200");
          } catch (Exception e) {
              msg.setMessageCode("400");
              e.printStackTrace();
              logger.info("操作异常");
          }
          return msg;
    }
    
    /**
     * 例外产品修改
     */
    @RequestMapping("updateExitProPbr")
    @ResponseBody
    public DataMsg updateExitProPbr(HttpServletRequest request,DataMsg msg) {
        try {   
        	  String monthyBasic = request.getParameter("monthyBasic");
        	  String thrMonthyBasic = request.getParameter("thrMonthyBasic");
        	  String sixMonthyBasic = request.getParameter("sixMonthyBasic");
        	  String twnMonthyBasic = request.getParameter("twnMonthyBasic");
        	  String addSubsidyWay = request.getParameter("addSubsidyWay");
        	  String exitProductId = request.getParameter("exitProductId");
        	  String bonusId = request.getParameter("bonusId");
        	  
	          /*月结算  + 通算 */
	      	  List<PersistencyBonusRule> pbrAdd = new ArrayList<PersistencyBonusRule>();
	      	  List<PersistencyBonusRule> pbrUpd = new ArrayList<PersistencyBonusRule>();
	      	  String updateIds = "";
        	  
        	  if(StringUtil.isNotBlank(monthyBasic)){
          		List<PersistencyBonusRule> pbrs = this.toList(monthyBasic,PersistencyBonusRule.class);
          		if(pbrs.size()>0){
	          		for(PersistencyBonusRule pbr:pbrs){
	          			pbr.setExitProductId(exitProductId);
	        			if(pbr.getRuleId()!=null){
	        				pbrUpd.add(pbr);
	        				updateIds = updateIds+pbr.getRuleId()+",";
	        			}else{
	        				pbrAdd.add(pbr);
	        			}
	          		}
          		}
	          }
        	  
        	  if(StringUtil.isNotBlank(addSubsidyWay)){
	        	  if(addSubsidyWay.equals("2")){
		              	if(StringUtil.isNotBlank(thrMonthyBasic)){
		              		List<PersistencyBonusRule> pbrs = this.toList(thrMonthyBasic,PersistencyBonusRule.class);
		              		if(pbrs.size()>0){
			              		for(PersistencyBonusRule pbr:pbrs){
			              			pbr.setExitProductId(exitProductId);
			            			if(pbr.getRuleId()!=null){
			            				pbrUpd.add(pbr);
			            				updateIds = updateIds+pbr.getRuleId()+",";
			            			}else{
			            				pbrAdd.add(pbr);
			            			}
			              		}
		              		}
		              	}        		
		          }else if(addSubsidyWay.equals("3")){
		              	if(StringUtil.isNotBlank(sixMonthyBasic)){
		              		List<PersistencyBonusRule> pbrs = this.toList(sixMonthyBasic,PersistencyBonusRule.class);
		              		if(pbrs.size()>0){
			              		for(PersistencyBonusRule pbr:pbrs){
			              			pbr.setExitProductId(exitProductId);
			            			if(pbr.getRuleId()!=null){
			            				pbrUpd.add(pbr);
			            				updateIds = updateIds+pbr.getRuleId()+",";
			            			}else{
			            				pbrAdd.add(pbr);
			            			}
			              		}
		              		}
		              	}         		
		          }else if(addSubsidyWay.equals("4")){
		              	if(StringUtil.isNotBlank(twnMonthyBasic)){
		              		List<PersistencyBonusRule> pbrs = this.toList(twnMonthyBasic,PersistencyBonusRule.class);
		              		if(pbrs.size()>0){
			              		for(PersistencyBonusRule pbr:pbrs){
			              			pbr.setExitProductId(exitProductId);
			            			if(pbr.getRuleId()!=null){
			            				pbrUpd.add(pbr);
			            				updateIds = updateIds+pbr.getRuleId()+",";
			            			}else{
			            				pbrAdd.add(pbr);
			            			}
			              		}
		              		}
		              	}         		
		          }
        	  }
        	  
        	  if(pbrUpd.size()>0){
          		insProtocolPersistencyBonusClient.updatePbr(pbrUpd);
          	  }
          	
      		  //删除移除的公共计算规则
      		  Map<String,Object> paras = new HashMap<>();
      		  if(updateIds.endsWith(",")){
      			updateIds = updateIds.substring(0,updateIds.length()-1);
      		  }
  			  paras.put("updateIds", updateIds);
  			  paras.put("basicFlag", "1");
  			  paras.put("persistencyBonusId",bonusId);
  			  paras.put("exitProductId",exitProductId);
  			  insProtocolPersistencyBonusClient.deletePbrs(paras);
  			
          	  if(pbrAdd.size()>0){
          		insProtocolPersistencyBonusClient.addPbr(pbrAdd);
          	  }
        	
              msg.setMessageCode("200");
          } catch (Exception e) {
              msg.setMessageCode("400");
              e.printStackTrace();
              logger.info("操作异常");
          }
          return msg;
    } 
    
    /**
     * 维护
     */
    @RequestMapping("updatePb")
    @ResponseBody
    public DataMsg updateSalesOrgInfo(HttpServletRequest request,InsProtocolPersistencyBonus ippbs,DataMsg msg) {
        try {
        	insProtocolPersistencyBonusClient.updatePb(ippbs);
        	/*月结算  + 通算 */
    		List<PersistencyBonusRule> pbrAdd = new ArrayList<PersistencyBonusRule>();
    		List<PersistencyBonusRule> pbrUpd = new ArrayList<PersistencyBonusRule>();
    		String updateIds = "";
        	String monthyBasic = request.getParameter("monthyBasic");
        	String thrMonthyBasic = request.getParameter("thrMonthyBasic");
        	String sixMonthyBasic = request.getParameter("sixMonthyBasic");
        	String twnMonthyBasic = request.getParameter("twnMonthyBasic");
        	if(StringUtil.isNotBlank(monthyBasic)){
        		List<PersistencyBonusRule> pbrs = this.toList(monthyBasic,PersistencyBonusRule.class);
        		for(PersistencyBonusRule pbr:pbrs){
        			if(pbr.getRuleId()!=null){
        				pbrUpd.add(pbr);
        				updateIds = updateIds+pbr.getRuleId()+",";
        			}else{
        				pbrAdd.add(pbr);
        			}
        		}
        	}
        	if(ippbs.getAddSubsidyWay().equals("2")){
            	if(StringUtil.isNotBlank(thrMonthyBasic)){
            		List<PersistencyBonusRule> pbrs = this.toList(thrMonthyBasic,PersistencyBonusRule.class);
            		for(PersistencyBonusRule pbr:pbrs){
            			if(pbr.getRuleId()!=null){
            				pbrUpd.add(pbr);
            				updateIds = updateIds+pbr.getRuleId()+",";
            			}else{
            				pbrAdd.add(pbr);
            			}
            		}
            	}        		
        	}else if(ippbs.getAddSubsidyWay().equals("3")){
            	if(StringUtil.isNotBlank(sixMonthyBasic)){
            		List<PersistencyBonusRule> pbrs = this.toList(sixMonthyBasic,PersistencyBonusRule.class);
            		for(PersistencyBonusRule pbr:pbrs){
            			if(pbr.getRuleId()!=null){
            				pbrUpd.add(pbr);
            				updateIds = updateIds+pbr.getRuleId()+",";
            			}else{
            				pbrAdd.add(pbr);
            			}
            		}
            	}         		
        	}else if(ippbs.getAddSubsidyWay().equals("4")){
            	if(StringUtil.isNotBlank(twnMonthyBasic)){
            		List<PersistencyBonusRule> pbrs = this.toList(twnMonthyBasic,PersistencyBonusRule.class);
            		for(PersistencyBonusRule pbr:pbrs){
            			if(pbr.getRuleId()!=null){
            				pbrUpd.add(pbr);
            				updateIds = updateIds+pbr.getRuleId()+",";
            			}else{
            				pbrAdd.add(pbr);
            			}
            		}
            	}         		
        	}
        	
        	if(pbrUpd.size()>0){
        		insProtocolPersistencyBonusClient.updatePbr(pbrUpd);
        	}
        	
    		//删除移除的公共计算规则
    		Map<String,Object> paras = new HashMap<>();
    		if(updateIds.endsWith(",")){
    			updateIds = updateIds.substring(0,updateIds.length()-1);
    		}
			paras.put("updateIds", updateIds);
			paras.put("basicFlag", "0");
			paras.put("persistencyBonusId",ippbs.getBonusId());
			insProtocolPersistencyBonusClient.deletePbrs(paras);
			
        	if(pbrAdd.size()>0){
        		insProtocolPersistencyBonusClient.addPbr(pbrAdd);
        	}			
        	
            msg.setMessageCode("200");
        } catch (Exception e) {
            msg.setMessageCode("400");
            e.printStackTrace();
            logger.info("操作异常");
        }
        return msg;
    }
}
