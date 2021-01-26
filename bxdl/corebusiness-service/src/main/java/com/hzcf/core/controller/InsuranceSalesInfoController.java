package com.hzcf.core.controller;


import com.google.common.collect.Maps;
import com.hzcf.core.service.InsuranceSalesInfoService;
import com.hzcf.core.service.SalerMonthlyRelationService;
import com.hzcf.core.service.SalerRelationChangeService;
import com.hzcf.core.util.DataMsg;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.insurance.sales.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保险销售人员管理
 * @author yl
 *
 */
@Controller
@RequestMapping("/insuranceSalesInfo")
public class InsuranceSalesInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private InsuranceSalesInfoService insuranceSalesInfoService;
    @Autowired
    private SalerMonthlyRelationService salerMonthlyRelationService;
    @Autowired
    private SalerRelationChangeService salerRelationChangeService;
    
    /**
     * 添加销售人员
     * */
    @RequestMapping("/addInsuranceSales")
    @ResponseBody
    public int addInsuranceSales(@RequestBody InsuranceSalesInfo insuranceSalesInfo){
    	insuranceSalesInfoService.addInsuranceSales(insuranceSalesInfo);
    	return insuranceSalesInfo.getInsuranceSalesId().intValue();
    } 
    
    /**
     * 修改基础信息
     * */
    @RequestMapping("/updateInsuranceSales")
    @ResponseBody
    public void updateInsuranceSales(@RequestBody InsuranceSalesInfo insuranceSalesInfo,@RequestParam(value = "isNoticy")boolean isnoticy){
    	insuranceSalesInfoService.updateInsuranceSales(insuranceSalesInfo,isnoticy);
    }
    
    /**
     * 分页查询保险销售人员
     * */
    @RequestMapping("/getInsuranceSalesList")
    @ResponseBody
    public PageModel getInsuranceSalesList(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.getInsuranceSalesList(params);
    }
    
    /**
     * 分页查询调整日志
     * */
    @RequestMapping("/getSalesMovePage")
    @ResponseBody
    public PageModel getSalesMovePage(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.getSalesMovePage(params);
    }    
    
    /**
     * 查询
     * */
    @RequestMapping("/selectSalerMoveLogs")
    @ResponseBody
    public SalesMoveLogs selectSalerMoveLogs(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.selectSalerMoveLogs(params);
    } 
    
    /**
     * 分页查询调整日志
     * */
    @RequestMapping("/updateSalerMove")
    @ResponseBody
    public void updateSalerMove(@RequestBody SalesMoveLogs salesMoveLogs){
    	insuranceSalesInfoService.updateSalerMove(salesMoveLogs);
    } 
    
    /**
     * 分页黑名单
     * */
    @RequestMapping("/getSalerBlackPage")
    @ResponseBody
    public PageModel getSalerBlackPage(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.getSalerBlackPage(params);
    }     
    
    /**
     * 不分页查询保险销售人员
     * */
    @RequestMapping("/insuranceSalesList")
    @ResponseBody
    public List<InsuranceSalesInfo> insuranceSalesList(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.insuranceSalesList(params);
    }
    
    /**
     * 不分页查询异动
     * */
    @RequestMapping("/getSalesMoveList")
    @ResponseBody
    public List<SalesMoveLogs> getSalesMoveList(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.getSalesMoveList(params);
    } 
    
    /**
     * 不分页黑名单
     * */
    @RequestMapping("/getSalerBlackList")
    @ResponseBody
    public List<Object> getSalerBlackList(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.getSalerBlackList(params);
    }     
    
    /**
     * 不分页查询保险销售人员职称、证书
     * */
    @RequestMapping("/findSalesTitles")
    @ResponseBody
    public List<SalesTitles> findSalesTitles(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.findSalesTitles(params);
    }    
    
    /**
     * 不分页查询保险销售人员教育、培训、工作
     * */
    @RequestMapping("/findSalesEduJobs")
    @ResponseBody
    public List<SalesEduJob> findSalesEduJobs(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.findSalesEduJobs(params);
    }
    
    /**
     * 不分页查询保险销售人员家庭成员、紧急联系人、司外担保人
     * */
    @RequestMapping("/findSalesRelatives")
    @ResponseBody
    public List<SalesRelative> findSalesRelatives(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.findSalesRelatives(params);
    } 
    
    /**
     * 不分页查询保险销售人员合同
     * */
    @RequestMapping("/findSalesContracts")
    @ResponseBody
    public List<SalesContract> findSalesContracts(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.findSalesContracts(params);
    }
    
    /**
     * 不分页查询保险销售人员合同
     * */
    @RequestMapping("/findZjjt")
    @ResponseBody
    public List<DirectorAllowanceStandardPojo> findZjjt(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.findZjjt(params);
    }     
    
    /**
     * 不分页查询保险销售人员
     * */
    @RequestMapping("/selectById")
    @ResponseBody
    public InsuranceSalesInfo selectById(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.selectById(params);
    }  
    
    /**
     * 离职
     * */
    @RequestMapping("/insertQuit")
    @ResponseBody
    public void insertQuit(@RequestBody SalerQuitInfo salerQuitInfo){
    	insuranceSalesInfoService.insertQuit(salerQuitInfo);
    } 
    
    /**
     * 离职详情
     * */
    @RequestMapping("/selectQuit")
    @ResponseBody
    public Map<String, Object> selectQuit(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.selectQuit(params);
    }  
    
    /**
     * 异动
     * */
    @RequestMapping("/insertMove")
    @ResponseBody
    public void insertMove(@RequestBody SalesMoveLogs salesMoveLogs){
    	insuranceSalesInfoService.insertMove(salesMoveLogs);
    }  
    
    /**
     * 异动
     * */
    @RequestMapping("/insertBlack")
    @ResponseBody
    public void insertBlack(@RequestBody SalerBlackInfo salerBlackInfo){
    	insuranceSalesInfoService.insertBlack(salerBlackInfo);
    }  
    
    /**
     * 批量插入
     * */
    @RequestMapping("/addZjjt")
    @ResponseBody
    public void addZjjt(@RequestBody List<DirectorAllowanceStandardPojo> directorAllowanceStandardPojos){
    	insuranceSalesInfoService.addZjjt(directorAllowanceStandardPojos);
    }
    
    /**
     * 批量插入
     * */
    @RequestMapping("/addSalesTitle")
    @ResponseBody
    public void addSalesTitle(@RequestBody List<SalesTitles> salesTitles){
    	insuranceSalesInfoService.addSalesTitle(salesTitles);
    }
    
    /**
     * 批量插入
     * */
    @RequestMapping("/addSalesEduJob")
    @ResponseBody
    public void addSalesEduJob(@RequestBody List<SalesEduJob> salesEduJob){
    	insuranceSalesInfoService.addSalesEduJob(salesEduJob);
    }  
    
    /**
     * 批量插入
     * */
    @RequestMapping("/addSalesRelative")
    @ResponseBody
    public void addSalesRelative(@RequestBody List<SalesRelative> salesRelative){
    	insuranceSalesInfoService.addSalesRelative(salesRelative);
    } 
    
    /**
     * 批量插入
     * */
    @RequestMapping("/addSalesContract")
    @ResponseBody
    public void addSalesContract(@RequestBody List<SalesContract> salesContract){
    	insuranceSalesInfoService.addSalesContract(salesContract);
    } 
    
    /**
     * 根据推荐人编制关系网
     * */
    @RequestMapping("/findMaxTreeCode")
    @ResponseBody
    public int findMaxTreeCode(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.findMaxTreeCode(params);
    } 
    
    /**
     * 根据机构查询机构下最大的员工编号
     * */
    @RequestMapping("/findMaxSalerNo")
    @ResponseBody
    public int findMaxSalerNo(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.findMaxSalerNo(params);
    }
    
    /**
     * 批量修改
     * */
    @RequestMapping("/updateZjjts")
    @ResponseBody
    public void updateZjjts(@RequestBody List<DirectorAllowanceStandardPojo> directorAllowanceStandardPojo){
    	insuranceSalesInfoService.updateZjjts(directorAllowanceStandardPojo);
    } 
    
    /**
     * 
     * */
    @RequestMapping("/deleteZjjtByIds")
    @ResponseBody
    public void deleteZjjtByIds(@RequestParam Map<String,Object> params){
    	insuranceSalesInfoService.deleteZjjtByIds(params);
    } 
    
    /**
     * 批量修改
     * */
    @RequestMapping("/updateSalesTitle")
    @ResponseBody
    public void updateSalesTitle(@RequestBody List<SalesTitles> salesTitles){
    	insuranceSalesInfoService.updateSalesTitle(salesTitles);
    } 
    
    /**
     * 
     * */
    @RequestMapping("/deleteTitleByIds")
    @ResponseBody
    public void deleteTitleByIds(@RequestParam Map<String,Object> params){
    	insuranceSalesInfoService.deleteTitleByIds(params);
    }  
    
    /**
     * 批量修改
     * */
    @RequestMapping("/updateSalesEduJob")
    @ResponseBody
    public void updateSalesEduJob(@RequestBody List<SalesEduJob> salesEduJob){
    	insuranceSalesInfoService.updateSalesEduJob(salesEduJob);
    } 
    
    /**
     * 
     * */
    @RequestMapping("/deleteEduByIds")
    @ResponseBody
    public void deleteEduByIds(@RequestParam Map<String,Object> params){
    	insuranceSalesInfoService.deleteEduByIds(params);
    }
    
    /**
     * 批量修改
     * */
    @RequestMapping("/updateSalesRelative")
    @ResponseBody
    public void updateSalesRelative(@RequestBody List<SalesRelative> salesRelative){
    	insuranceSalesInfoService.updateSalesRelative(salesRelative);
    } 
    
    /**
     * 
     * */
    @RequestMapping("/deleteShipByIds")
    @ResponseBody
    public void deleteShipByIds(@RequestParam Map<String,Object> params){
    	insuranceSalesInfoService.deleteShipByIds(params);
    }    
    
    /**
     * 批量修改
     * */
    @RequestMapping("/updateSalesContracts")
    @ResponseBody
    public void updateSalesContracts(@RequestBody List<SalesContract> salesContract){
    	insuranceSalesInfoService.updateSalesContracts(salesContract);
    } 
    
    /**
     * 
     * */
    @RequestMapping("/deleteHtByIds")
    @ResponseBody
    public void deleteHtByIds(@RequestParam Map<String,Object> params){
    	insuranceSalesInfoService.deleteHtByIds(params);
    }  
    
    /**
     * 
     * */
    @RequestMapping("/selectBySalerInvitation")
    @ResponseBody
    public InsuranceSalesInfo selectBySalerInvitation(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.selectBySalerInvitation(params);
    }

    /**
     * 查看员工详情
     * */
    @RequestMapping("/selectInsuranceSalesOne")
    @ResponseBody
    public Map<String,Object> selectInsuranceSalesOne (@RequestParam Map<String,Object> params){
        return  insuranceSalesInfoService.selectInsuranceSalesOne(params);
    }

    /**
     * <p>根据查询条件分页员工个人考核记录</p>
     * @param insuranceSalesId  员工id
     * @param pageNo    查询页数
     * @param pageSize  每页查询数量
     * @return
     */
    @RequestMapping(value = "/salesAssessPage/{insuranceSalesId}", method = RequestMethod.GET)
    @ResponseBody
    public PageModel findSalesAssessByInsuranceSalesId(@PathVariable("insuranceSalesId")Long insuranceSalesId,
                                                     @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("insuranceSalesId", insuranceSalesId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        return insuranceSalesInfoService.findSalesAssessPage(params);
    }

    /**
     * <p>新增员工考核记录</p>
     * @param salesAssess   考核记录
     */
    @RequestMapping(value = "/salesAssess", method = RequestMethod.POST)
    @ResponseBody
    public void saveSalesAssess(@RequestBody SalesAssess salesAssess){
        insuranceSalesInfoService.saveSalesAssess(salesAssess);
    }

    /**
     * <p>根据考核记录id查询员工考核记录</p>
     * @param salesAssessId   考核记录id
     */
    @RequestMapping(value = "/salesAssess/{salesAssessId}", method = RequestMethod.GET)
    @ResponseBody
    public SalesAssess findSalesAssessById(@PathVariable("salesAssessId") Long salesAssessId){
        return insuranceSalesInfoService.findSalesAssessById(salesAssessId);
    }

    /**
     * <p>根据考核记录id更新员工考核记录</p>
     * @param salesAssess   考核记录
     */
    @RequestMapping(value = "/salesAssess", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateSalesAssess(@RequestBody SalesAssess salesAssess){
        return insuranceSalesInfoService.updateSalesAssess(salesAssess);
    }

    /**
     * <p>员工考核</p>
     * 晋升维持
     */
    @RequestMapping(value = "/examine", method = RequestMethod.GET)
    @ResponseBody
    public void examineSalesmanGrade(){
        insuranceSalesInfoService.examineSalesmanGrade();
    }

    /**
     * <p>查询员工当前任职时间</p>
     * 晋升维持
     */
    @RequestMapping(value = "/entryTime", method = RequestMethod.GET)
    @ResponseBody
    public String findEmploymentPeriod(@RequestParam("salesId") long salesId, @RequestParam("salesGradeId") long salesGradeId){
        return insuranceSalesInfoService.findEmploymentPeriod(salesId, salesGradeId);
    }

    /**
     * <p>根据员工id查询最新考核结果</p>
     * @param insuranceSalesId  员工id
     * @param salesGradeId  职级id
     * @return
     */
    @RequestMapping(value = "/salesAssess", method = RequestMethod.GET)
    @ResponseBody
    public SalesAssess findNewSalesAssess(@RequestParam("insuranceSalesId") long insuranceSalesId, @RequestParam("salesGradeId") long salesGradeId){
        return insuranceSalesInfoService.findNewSalesAssess(insuranceSalesId, salesGradeId);
    }

    /**
     * <p>获取销售人员fyc</p>
     * 有入司时间时判断是否累计FYC是否需要满一千
     * @param insuranceSalesId
     * @param cycle	不包含考核月
     * @param joinDate  入司时间
     * @return
     */
    @RequestMapping(value = "/fyc", method = RequestMethod.GET)
    @ResponseBody
    public double getFYC(@RequestParam("insuranceSalesId") long insuranceSalesId, @RequestParam("cycle") int cycle, String joinDate){
        return insuranceSalesInfoService.getFYC(insuranceSalesId, cycle, joinDate);
    }

    /**
     * <p>根据推荐人id查询推荐员工数量</p>
     * @param tjSalesId	推荐人id
     * @return
     */
    @RequestMapping(value = "/tjCount", method = RequestMethod.GET)
    @ResponseBody
    public int queryBTJCount(@RequestParam("tjSalesId") long tjSalesId){
        return insuranceSalesInfoService.queryBTJCount(tjSalesId);
    }

    /**
     * <p>根据员工id查询团队中某个职务人数</p>
     * @param salesId	员工id
     * @param salesGradeId	被查询职务id
     * @param startTime	开始时间
     * @param endTime	结束时间
     * @return
     */
    @RequestMapping(value = "/dutyCount", method = RequestMethod.GET)
    @ResponseBody
    public int queryDutyCount(@RequestParam("salesId")long salesId, @RequestParam("salesGradeId")long salesGradeId,
                              @RequestParam("startTime")Date startTime, @RequestParam("endTime")Date endTime){
        return insuranceSalesInfoService.queryDutyCount(salesId, salesGradeId, startTime, endTime);
    }

    /**
     * <p>根据科长员工id查询增员属员</p>
     * @param tjSalesId	推荐人id
     * @return
     */
    @RequestMapping(value = "/kz/tjCount", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> queryTJSubordinateForKZ(@RequestParam("tjSalesId")long tjSalesId){
        return insuranceSalesInfoService.queryTJSubordinateForKZ(tjSalesId);
    }

    /**
     * <p>根据育成人id查询被育成人数</p>
     * @param ycSalesId	育成人id
     * @param salesGradeId	被育成人职级id
     * @return
     */
    @RequestMapping(value = "/ycCount", method = RequestMethod.GET)
    @ResponseBody
    public int queryBYCCount(@RequestParam("ycSalesId")long ycSalesId, @RequestParam("salesGradeId")long salesGradeId){
        return insuranceSalesInfoService.queryBYCCount(ycSalesId, salesGradeId);
    }


    /**
     * <p>查询所辖团队</p>
     * 包含被管理人及被推荐人 并且职级不高于本身
     * @param salesId	员工id
     * @param salesGradeId	职级id	观察期部长及以上
     * @return
     */
    @RequestMapping(value = "/subordinate", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> querySubordinate(@RequestParam("salesId")long salesId, @RequestParam("salesGradeId")Long salesGradeId){
        return insuranceSalesInfoService.querySubordinate(salesId, salesGradeId);
    }

    /**
     * <p>根据员工id查询下属的科长和考察期科长</p>
     * @param salesId	员工id
     * @return
     */
    @RequestMapping(value = "/recommended", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> queryBeRecommendedKZBySalesId(@RequestParam("salesId")long salesId){
        return insuranceSalesInfoService.queryBeRecommendedKZBySalesId(salesId);
    }

     @RequestMapping(value = "/insertImportList", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object>  insertImportList(@RequestBody Map<String, Object> p){
          Map<String, Object> map = new HashMap<String, Object>();
        try {
            insuranceSalesInfoService.insertImportList(p);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
            logger.info("批量导入异常：",e);
        }
        return map;
    }

    @RequestMapping(value = "/updateImportList", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object>  updateImportList(@RequestBody Map<String, Object> p){
          Map<String, Object> map = new HashMap<String, Object>();
        try {
            insuranceSalesInfoService.updateImportList(p);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
            logger.info("批量修改导入异常：",e);
        }
        return map;
    }


    /**
     *@描述  根据指定的销售人员id  查询人员
     *@创建人 qin lina
     *@创建时间 2020/3/13
     */
    @RequestMapping(value = "/insuranceSalesListForSalesNo", method = RequestMethod.GET)
    @ResponseBody
    public List<InsuranceSalesInfo> insuranceSalesListForSalesNo(@RequestParam("salesNos") List<String> salesNos){
        return insuranceSalesInfoService.insuranceSalesListForSalesNo(salesNos);
    }

    @RequestMapping("/insertSalerMonthlyRelation")
    @ResponseBody
    public int insertSalerMonthlyRelation(@RequestBody SalerMonthlyRelation salerMonthlyRelation){
        return salerMonthlyRelationService.insertSalerMonthlyRelation(salerMonthlyRelation);
    }

    @RequestMapping("/insertSalerRelationChange")
    @ResponseBody
    public int insertSalerRelationChange(@RequestBody Map<String, Object> paras){
        return salerRelationChangeService.insertSelective(paras);
    }

    @RequestMapping("/selectRelationBySalerIdAndMonth")
    @ResponseBody
    public SalerRelationChange selectRelationBySalerIdAndMonth(@RequestParam Map<String,Object> params){
    	return salerRelationChangeService.selectRelationBySalerIdAndMonth(params);
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public void updateByPrimaryKeySelective(@RequestBody SalerRelationChange salerRelationChange){
    	salerRelationChangeService.updateByPrimaryKeySelective(salerRelationChange);
    }
    /**
     *@描述 获取销售人员的组织机构团队信息
     *@创建人 qin lina
     *@创建时间 2020/6/17
     */
    @RequestMapping("/insuranceSalesRelationList")
    @ResponseBody
    public List<Map<String,Object>> insuranceSalesRelationList(@RequestBody Map<String,Object> params){
    	return insuranceSalesInfoService.insuranceSalesRelationList(params);
    }

    /**
     *@描述  保存销售人员关系
     *@创建人 qin lina
     *@创建时间 2020/6/17
     */
     @RequestMapping("/addSalesRelation")
    @ResponseBody
    public void addSalesRelation(@RequestBody Map<String, Object> map){
             insuranceSalesInfoService.addSalesRelation(map);
     }

    /**
     *  查询指定组织机构下最大的员工编号
      * @param insuranceSalesInfo
     * @return
     */
    @RequestMapping("/findMaxSalerNoForOrg")
    @ResponseBody
    public Long findMaxSalerNoForOrg(@RequestParam Map<String, Object> paras){
          return   insuranceSalesInfoService.findMaxSalerNoForOrg(paras);
     }


    /**
     * 批量导入员工关系
     * @param paras
     */
    @RequestMapping("/saveImportRelation")
    @ResponseBody
    public Map<String, Object> saveImportRelation(@RequestBody Map<String, Object> paras){
          Map<String, Object> map = new HashMap<String, Object>();
        try {
             insuranceSalesInfoService.saveImportRelation(paras);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            e.printStackTrace();
            logger.info("批量导入员工关系：",e);
        }
        return map;

     }

    @ResponseBody
     @RequestMapping("/saveImportSalerQuit")
	public Map<String,Object> saveImportSalerQuit(@RequestBody Map<String, Object> paras){
        Map<String, Object> map = new HashMap<>();
         try {
             insuranceSalesInfoService.saveImportSalerQuit(paras);
            map.put("code", 200);
        } catch (RuntimeException e) {
            map.put("code", 400);
            logger.info("批量处理员工离职异常：",e);
        }
        return map;
    }


     @RequestMapping("/updateRelationChange")
    @ResponseBody
    public void updateRelationChange(@RequestBody Map<String, Object> paras){
        salerRelationChangeService.updateRelationChange(paras);
     }

    /**
     * 查询次月的销售人员职级和团队信息
     * @param params
     * @return
     */
      @RequestMapping("/salerNextMessage")
    @ResponseBody
    public Map<String,Object> salerNextMessage(@RequestParam Map<String,Object> params){
    	return insuranceSalesInfoService.salerNextMessage(params);
    }

    /**
     * 查询汇康数据是否存在手机号对应的员工编号
     * @param mobile
     * @return
     */
    @RequestMapping("/searchHKSalerData")
    @ResponseBody
    public String searchHKSalerData(@RequestParam String mobile){
        return insuranceSalesInfoService.searchHKSalerData(mobile);
    }


    @RequestMapping(value = "/findSalesInfoMessage")
    @ResponseBody
	public List<Map<String,Object>> findSalesInfoMessage(@RequestBody Map<String, Object> paras){
        return insuranceSalesInfoService.findSalesInfoMessage(paras);
    }

}
