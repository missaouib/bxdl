package com.hzcf.plantform.feign.insurance;

import com.hzcf.plantform.feign.FeignDisableHystrixConfiguration;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.insurance.sales.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@FeignClient(name = "corebusiness-service", fallback =FeignDisableHystrixConfiguration.class)
public interface InsuranceSalesInfoClient {
	
	/**
	 *增加销售组织
	 * @param salesOrgInfo 
	 * */
	@RequestMapping(value = "/insuranceSalesInfo/addInsuranceSales",method = RequestMethod.POST)
    int addInsuranceSales(InsuranceSalesInfo insuranceSalesInfo);

	/**
	 *销售组织列表
	 * @param salesOrgInfoList 
	 * */
	@RequestMapping(value = "/insuranceSalesInfo/getInsuranceSalesList",method = RequestMethod.POST)
    PageModel getInsuranceSalesList(@RequestParam Map<String, Object> paras);
	
	/**
	 *查
	 * @param selectById 
	 * */
	@RequestMapping(value = "/insuranceSalesInfo/selectById",method = RequestMethod.POST)
    InsuranceSalesInfo selectById(@RequestParam Map<String, Object> paras);

	 /**
    *
    *修改基础信息
    * */
	@RequestMapping(value = "/insuranceSalesInfo/updateInsuranceSales",method = RequestMethod.POST)
    void updateInsuranceSales(InsuranceSalesInfo insuranceSalesInfo,@RequestParam(value = "isNoticy")boolean isnoticy);

	 /**
    *
    *批量插入员工相关信息
    * */
	@RequestMapping(value = "/insuranceSalesInfo/addSalesTitle",method = RequestMethod.POST)
    void addSalesTitle(List<SalesTitles> salesTitles);
	
	 /**
    *
    *批量插入员工相关信息
    * */
	@RequestMapping(value = "/insuranceSalesInfo/addSalesEduJob",method = RequestMethod.POST)
    void addSalesEduJob(List<SalesEduJob> salesEduJobs);
	
	 /**
    *
    *批量插入员工相关信息
    * */
	@RequestMapping(value = "/insuranceSalesInfo/addSalesRelative",method = RequestMethod.POST)
    void addSalesRelative(List<SalesRelative> salesRelatives);
	
	 /**
    *
    *批量插入员工相关信息
    * */
	@RequestMapping(value = "/insuranceSalesInfo/addSalesContract",method = RequestMethod.POST)
    void addSalesContract(List<SalesContract> salesContracts);

	 /**
    *
    *根据推荐人编制关系网
    * */
	@RequestMapping(value = "/insuranceSalesInfo/findMaxTreeCode",method = RequestMethod.POST)
    int findMaxTreeCode(@RequestParam Map<String, Object> paras);
	
	/**
    *
    * 不分页查询
    * */
	@RequestMapping(value = "/insuranceSalesInfo/insuranceSalesList",method = RequestMethod.POST)
    List<InsuranceSalesInfo> insuranceSalesList(@RequestParam Map<String, Object> paras);

	/**
    *
    * 不分页查询
    * */
	@RequestMapping(value = "/insuranceSalesInfo/findSalesTitles",method = RequestMethod.POST)
    List<SalesTitles> findSalesTitles(@RequestParam Map<String, Object> paras);
	/**
    *
    * 不分页查询
    * */
	@RequestMapping(value = "/insuranceSalesInfo/findSalesEduJobs",method = RequestMethod.POST)
    List<SalesEduJob> findSalesEduJobs(@RequestParam Map<String, Object> paras);
	/**
    *
    * 不分页查询
    * */
	@RequestMapping(value = "/insuranceSalesInfo/findSalesRelatives",method = RequestMethod.POST)
    List<SalesRelative> findSalesRelatives(@RequestParam Map<String, Object> paras);
	/**
    *
    * 不分页查询
    * */
	@RequestMapping(value = "/insuranceSalesInfo/findSalesContracts",method = RequestMethod.POST)
    List<SalesContract> findSalesContracts(@RequestParam Map<String, Object> paras);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/updateSalesTitle",method = RequestMethod.POST)
    void updateSalesTitle(List<SalesTitles> salesTitlesUpd);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/deleteTitleByIds",method = RequestMethod.POST)
    void deleteTitleByIds(@RequestParam Map<String, Object> paras);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/updateSalesEduJob",method = RequestMethod.POST)
    void updateSalesEduJob(List<SalesEduJob> salesEduJobsUpd);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/deleteEduByIds",method = RequestMethod.POST)
    void deleteEduByIds(@RequestParam Map<String, Object> paras);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/updateSalesRelative",method = RequestMethod.POST)
    void updateSalesRelative(List<SalesRelative> salesRelativesUpd);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/deleteShipByIds",method = RequestMethod.POST)
    void deleteShipByIds(@RequestParam Map<String, Object> paras);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/updateSalesContracts",method = RequestMethod.POST)
    void updateSalesContracts(List<SalesContract> salesContractsUpd);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/deleteHtByIds",method = RequestMethod.POST)
    void deleteHtByIds(@RequestParam Map<String, Object> paras);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/addZjjt",method = RequestMethod.POST)
    void addZjjt(List<DirectorAllowanceStandardPojo> zjjtLists);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/findZjjt",method = RequestMethod.POST)
    List<DirectorAllowanceStandardPojo> findZjjt(@RequestParam Map<String, Object> paras);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/updateZjjts",method = RequestMethod.POST)
    void updateZjjts(List<DirectorAllowanceStandardPojo> zjjtListsUpd);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/deleteZjjtByIds",method = RequestMethod.POST)
    void deleteZjjtByIds(@RequestParam Map<String, Object> paras);

	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/insertQuit",method = RequestMethod.POST)
    void insertQuit(SalerQuitInfo salerQuitInfo);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/getSalesMovePage",method = RequestMethod.POST)
    PageModel getSalesMovePage(@RequestParam Map<String, Object> paras);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/getSalesMoveList",method = RequestMethod.POST)
    List<SalesMoveLogs> getSalesMoveList(@RequestParam Map<String, Object> paras);
	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/insertMove",method = RequestMethod.POST)
    void insertMove(SalesMoveLogs salesMoveLogs);

	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/insertBlack",method = RequestMethod.POST)
    void insertBlack(SalerBlackInfo salerBlackInfo);

	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/getSalerBlackList",method = RequestMethod.POST)
    List<Object> getSalerBlackList(@RequestParam Map<String, Object> paras);

	/**
    *
    * 
    * */
	@RequestMapping(value = "/insuranceSalesInfo/getSalerBlackPage",method = RequestMethod.POST)
    PageModel getSalerBlackPage(@RequestParam Map<String, Object> paras);

	 /**
    *
    *查询该机构下最大的员工编号
    * */
	@RequestMapping(value = "/insuranceSalesInfo/findMaxSalerNo",method = RequestMethod.POST)
    int findMaxSalerNo(@RequestParam Map<String, Object> paras);

	/**
   *
   *查询离职详细
   * */
	@RequestMapping(value = "/insuranceSalesInfo/selectQuit",method = RequestMethod.POST)
    Map<String, Object> selectQuit(@RequestParam Map<String, Object> paras);

	/**
   *
   *查询离职详细
   * */
	@RequestMapping(value = "/insuranceSalesInfo/selectBySalerInvitation",method = RequestMethod.POST)
    InsuranceSalesInfo selectBySalerInvitation(@RequestParam Map<String, Object> paras);

	/**
	 * <p>根据查询条件分页员工个人考核记录</p>
	 * @param insuranceSalesId  员工id
	 * @param pageNo    查询页数
	 * @param pageSize  每页查询数量
	 * @return
	 */
	@RequestMapping(value = "/insuranceSalesInfo/salesAssessPage/{insuranceSalesId}", method = RequestMethod.GET)
	PageModel findSalesAssessByInsuranceSalesId(@PathVariable("insuranceSalesId")Long insuranceSalesId,
												@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize);

	/**
	 * <p>新增员工考核记录</p>
	 * @param salesAssess   考核记录
	 */
	@RequestMapping(value = "/insuranceSalesInfo/salesAssess", method = RequestMethod.POST)
	void saveSalesAssess(@RequestBody SalesAssess salesAssess);

	/**
	 * <p>根据考核记录id查询员工考核记录</p>
	 * @param salesAssessId   考核记录id
	 */
	@RequestMapping(value = "/insuranceSalesInfo/salesAssess/{salesAssessId}", method = RequestMethod.GET)
	SalesAssess findSalesAssessById(@PathVariable("salesAssessId") Long salesAssessId);

	/**
	 * <p>根据考核记录id更新员工考核记录</p>
	 * @param salesAssess   考核记录
	 */
	@RequestMapping(value = "/insuranceSalesInfo/salesAssess", method = RequestMethod.PUT)
	boolean updateSalesAssess(@RequestBody SalesAssess salesAssess);

	/**
	 * <p>员工考核</p>
	 */
	@RequestMapping(value = "/insuranceSalesInfo/examine", method = RequestMethod.GET)
	void examineSalesmanGrade();

	@RequestMapping(value = "/insuranceSalesInfo/selectSalerMoveLogs", method = RequestMethod.GET)
	SalesMoveLogs selectSalerMoveLogs(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/insuranceSalesInfo/updateSalerMove", method = RequestMethod.GET)
	void updateSalerMove(@RequestBody SalesMoveLogs salesMove);

	/**
	 * <p>查询员工当前任职时间</p>
	 * 晋升维持
	 */
	@RequestMapping(value = "/insuranceSalesInfo/entryTime", method = RequestMethod.GET)
	@ResponseBody
	String findEmploymentPeriod(@RequestParam("salesId") long salesId, @RequestParam("salesGradeId") long salesGradeId);

    /**
     * <p>根据员工id查询最新考核结果</p>
     * @param insuranceSalesId  员工id
     * @param salesGradeId  职级id
     * @return
     */
    @RequestMapping(value = "/insuranceSalesInfo/salesAssess", method = RequestMethod.GET)
    @ResponseBody
    SalesAssess findNewSalesAssess(@RequestParam("insuranceSalesId") long insuranceSalesId, @RequestParam("salesGradeId") long salesGradeId);

    @RequestMapping(value = "/insuranceSalesInfo/fyc", method = RequestMethod.GET)
    @ResponseBody
    double getFYC(@RequestParam("insuranceSalesId") long insuranceSalesId, @RequestParam("cycle") int cycle, @RequestParam("joinDate")String joinDate);

    /**
     * <p>根据推荐人id查询推荐员工数量</p>
     * @param tjSalesId	推荐人id
     * @return
     */
    @RequestMapping(value = "/insuranceSalesInfo/tjCount", method = RequestMethod.GET)
    @ResponseBody
    int queryBTJCount(@RequestParam("tjSalesId") long tjSalesId);


    /**
     * <p>根据员工id查询团队中某个职务人数</p>
     * @param salesId	员工id
     * @param salesGradeId	被查询职务id
     * @param startTime	开始时间
     * @param endTime	结束时间
     * @return
     */
    @RequestMapping(value = "/insuranceSalesInfo/dutyCount", method = RequestMethod.GET)
    @ResponseBody
    int queryDutyCount(@RequestParam("salesId")long salesId, @RequestParam("salesGradeId")long salesGradeId,
                              @RequestParam("startTime") Date startTime, @RequestParam("endTime")Date endTime);

    /**
     * <p>根据科长员工id查询增员属员</p>
     * @param tjSalesId	推荐人id
     * @return
     */
    @RequestMapping(value = "/insuranceSalesInfo/kz/tjCount", method = RequestMethod.GET)
    @ResponseBody
    List<Long> queryTJSubordinateForKZ(@RequestParam("tjSalesId")long tjSalesId);

    /**
     * <p>根据育成人id查询被育成人数</p>
     * @param ycSalesId	育成人id
     * @param salesGradeId	被育成人职级id
     * @return
     */
    @RequestMapping(value = "/insuranceSalesInfo/ycCount", method = RequestMethod.GET)
    @ResponseBody
    int queryBYCCount(@RequestParam("ycSalesId")long ycSalesId, @RequestParam("salesGradeId")long salesGradeId);

    /**
     * <p>查询所辖团队</p>
     * 包含被管理人及被推荐人 并且职级不高于本身
     * @param salesId	员工id
     * @param salesGradeId	职级id	观察期部长及以上
     * @return
     */
    @RequestMapping(value = "/insuranceSalesInfo/subordinate", method = RequestMethod.GET)
    @ResponseBody
    List<Long> querySubordinate(@RequestParam("salesId")long salesId, @RequestParam("salesGradeId")Long salesGradeId);

    /**
     * <p>根据员工id查询下属的科长和考察期科长</p>
     * @param salesId	员工id
     * @return
     */
    @RequestMapping(value = "/insuranceSalesInfo/recommended", method = RequestMethod.GET)
    @ResponseBody
    List<Long> queryBeRecommendedKZBySalesId(@RequestParam("salesId")long salesId);

    /**
     *@描述  批量导入销售人员
     *@创建人 qin lina
     *@创建时间 2020/3/10
     */
    @RequestMapping(value = "/insuranceSalesInfo/insertImportList", method = RequestMethod.GET)
    @ResponseBody
    Map<String,Object> insertImportList(Map<String, Object> p);

   /**
    *@描述  根据指定的销售人员id  查询人员
    *@创建人 qin lina
    *@创建时间 2020/3/13
    */
     @RequestMapping(value = "/insuranceSalesInfo/insuranceSalesListForSalesNo", method = RequestMethod.GET)
     @ResponseBody
    List<InsuranceSalesInfo> insuranceSalesListForSalesNo(@RequestParam("salesNos") List<String> salesNos);

	@RequestMapping(value = "/insuranceSalesInfo/insertSalerMonthlyRelation", method = RequestMethod.POST)
    int insertSalerMonthlyRelation(@RequestBody SalerMonthlyRelation salerMonthlyRelation);

	@RequestMapping(value = "/insuranceSalesInfo/insertSalerRelationChange", method = RequestMethod.POST)
	int insertSalerRelationChange(@RequestBody Map<String, Object> paras);

	@RequestMapping(value = "/insuranceSalesInfo/selectRelationBySalerIdAndMonth",method = RequestMethod.POST)
    SalerRelationChange selectRelationBySalerIdAndMonth(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/insuranceSalesInfo/updateByPrimaryKeySelective", method = RequestMethod.POST)
	void updateByPrimaryKeySelective(@RequestBody SalerRelationChange salerRelationChange);

    @RequestMapping(value = "/insuranceSalesInfo/updateImportList", method = RequestMethod.POST)
	Map<String, Object> updateImportList(Map<String, Object> p);

     @RequestMapping(value = "/insuranceSalesInfo/insuranceSalesRelationList", method = RequestMethod.POST)
    List<Map<String,Object>> insuranceSalesRelationList(@RequestBody  Map<String, Object> paras);

      @RequestMapping(value = "/insuranceSalesInfo/addSalesRelation", method = RequestMethod.POST)
    void addSalesRelation(@RequestBody Map<String, Object> map);

      @RequestMapping(value = "/insuranceSalesInfo/findMaxSalerNoForOrg", method = RequestMethod.POST)
	Long findMaxSalerNoForOrg(@RequestParam Map<String, Object> paras);

    @RequestMapping(value = "/insuranceSalesInfo/saveImportRelation", method = RequestMethod.POST)
    Map<String,Object> saveImportRelation(@RequestBody Map<String, Object> p);

	 @RequestMapping(value = "/insuranceSalesInfo/updateRelationChange", method = RequestMethod.POST)
    void updateRelationChange(@RequestBody Map<String, Object> p);

 @RequestMapping(value = "/insuranceSalesInfo/salerNextMessage", method = RequestMethod.POST)
    Map<String,Object> salerNextMessage(@RequestParam Map<String, Object> paras);

	@RequestMapping(value = "/insuranceSalesInfo/searchHKSalerData", method = RequestMethod.POST)
    String searchHKSalerData(@RequestParam("mobile") String mobile);

	@RequestMapping(value = "/insuranceSalesInfo/saveImportSalerQuit", method = RequestMethod.POST)
	Map<String,Object> saveImportSalerQuit(Map<String, Object> map);

	@RequestMapping(value = "/insuranceSalesInfo/findSalesInfoMessage", method = RequestMethod.POST)
	List<Map<String,Object>> findSalesInfoMessage(Map<String, Object> paras);
}
