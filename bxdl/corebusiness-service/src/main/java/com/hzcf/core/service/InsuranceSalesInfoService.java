package com.hzcf.core.service;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.insurance.sales.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface InsuranceSalesInfoService{

    /**
     *新增组织机构
     * */
    int addInsuranceSales(InsuranceSalesInfo insuranceSalesInfo);
	
    /**
     *获取列表
     * @param salesOrgInfoList 
     * */
    PageModel getInsuranceSalesList(Map<String, Object> params);

    /**
     *获取
     * @param  
     * */
    InsuranceSalesInfo selectById(Map<String, Object> params);

    /**
     * 修改详细信息
     * */
    void updateInsuranceSales(InsuranceSalesInfo insuranceSalesInfo,boolean isnoticy);
	
    /**
     * 批量插入
     * */
    void addSalesTitle(List<SalesTitles> salesTitles);
    /**
     * 批量插入
     * */
    void addSalesEduJob(List<SalesEduJob> salesEduJob);
    /**
     * 批量插入
     * */
    void addSalesRelative(List<SalesRelative> salesRelative);
    /**
     * 批量插入
     * */
    void addSalesContract(List<SalesContract> salesContract);

    /**
     * 批量插入
     * */
    int findMaxTreeCode(Map<String, Object> params);

	List<InsuranceSalesInfo> insuranceSalesList(Map<String, Object> params);

	List<SalesTitles> findSalesTitles(Map<String, Object> params);

	List<SalesEduJob> findSalesEduJobs(Map<String, Object> params);

	List<SalesRelative> findSalesRelatives(Map<String, Object> params);

	List<SalesContract> findSalesContracts(Map<String, Object> params);

	void updateSalesTitle(List<SalesTitles> salesTitles);

	void deleteTitleByIds(Map<String, Object> params);

	void updateSalesEduJob(List<SalesEduJob> salesEduJob);

	void deleteEduByIds(Map<String, Object> params);

	void updateSalesRelative(List<SalesRelative> salesRelative);

	void deleteShipByIds(Map<String, Object> params);

	void updateSalesContracts(List<SalesContract> salesContract);

	void deleteHtByIds(Map<String, Object> params);

	void addZjjt(List<DirectorAllowanceStandardPojo> directorAllowanceStandardPojos);

	List<DirectorAllowanceStandardPojo> findZjjt(Map<String, Object> params);

	void updateZjjts(List<DirectorAllowanceStandardPojo> directorAllowanceStandardPojo);

	void deleteZjjtByIds(Map<String, Object> params);

	void insertQuit(SalerQuitInfo salerQuitInfo);

	PageModel getSalesMovePage(Map<String, Object> params);

	void insertMove(SalesMoveLogs salesMoveLogs);

	List<SalesMoveLogs> getSalesMoveList(Map<String, Object> params);

	void insertBlack(SalerBlackInfo salerBlackInfo);

	List<Object> getSalerBlackList(Map<String, Object> params);

	PageModel getSalerBlackPage(Map<String, Object> params);

	int findMaxSalerNo(Map<String, Object> params);

	Map<String, Object> selectQuit(Map<String, Object> params);

	InsuranceSalesInfo selectBySalerInvitation(Map<String, Object> params);

    Map<String, Object> selectInsuranceSalesOne(Map<String, Object> params);

	/**
	 * <p>分页查询员工个人考核记录</p>
	 * @param params	查询条件
	 * @return
	 */
    PageModel findSalesAssessPage(Map<String, Object> params);

	/**
	 * <p>新增员工考核记录</p>
	 * @param salesAssess
	 */
	void saveSalesAssess(SalesAssess salesAssess);

	/**
	 * <p>根据id更新员工考核记录</p>
	 * @param salesAssess
	 */
	boolean updateSalesAssess(SalesAssess salesAssess);

	/**
	 * <p>根据id查询考核记录</p>
	 * @param id    id
	 * @return  考核记录
	 */
	SalesAssess findSalesAssessById(long id);

	/**
	 * <p>销售人员职级考核</p>
	 */
	void examineSalesmanGrade();

	/**
	 * <p>获取销售人员fyc</p>
	 * 有入司时间时判断是否累计FYC是否需要满一千
	 * @param insuranceSalesId
	 * @param cycle	不包含考核月
	 * @param joinDate  入司时间
	 * @return
	 */
	double getFYC(long insuranceSalesId, int cycle, String joinDate);

	SalesMoveLogs selectSalerMoveLogs(Map<String, Object> params);

	void updateSalerMove(SalesMoveLogs salesMoveLogs);

	/**
	 * <p>查询员工当前任职时间</p>
	 * @param salesId	员工id
	 * @param salesGradeId	职级id
	 * @return
	 */
	String findEmploymentPeriod(long salesId, long salesGradeId);

	/**
	 * <p>根据员工id查询最新考核结果</p>
	 * @param insuranceSalesId  员工id
	 * @param salesGradeId  职级id
	 * @return
	 */
	SalesAssess findNewSalesAssess(long insuranceSalesId, long salesGradeId);

	/**
	 * <p>根据推荐人id查询推荐员工数量</p>
	 * @param tjSalesId	推荐人id
	 * @return
	 */
	int queryBTJCount(long tjSalesId);

	/**
	 * <p>根据员工id查询团队中某个职务人数</p>
	 * @param salesId	员工id
	 * @param salesGradeId	被查询职务id
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @return
	 */
	int queryDutyCount(long salesId, long salesGradeId, Date startTime, Date endTime);

	/**
	 * <p>根据科长员工id查询增员属员</p>
	 * @param tjSalesId	推荐人id
	 * @return
	 */
	List<Long> queryTJSubordinateForKZ(long tjSalesId);

	/**
	 * <p>根据育成人id查询被育成人数</p>
	 * @param ycSalesId	育成人id
	 * @param salesGradeId	被育成人职级id
	 * @return
	 */
	int queryBYCCount(long ycSalesId, long salesGradeId);


	/**
	 * <p>查询所辖团队</p>
	 * 包含被管理人及被推荐人 并且职级不高于本身
	 * @param salesId	员工id
	 * @param salesGradeId	职级id	观察期部长及以上
	 * @return
	 */
	List<Long> querySubordinate(long salesId, Long salesGradeId);

	/**
	 * <p>根据员工id查询下属的科长和考察期科长</p>
	 * @param salesId	员工id
	 * @return
	 */
	List<Long> queryBeRecommendedKZBySalesId(long salesId);

    /**
     *@描述  批量插入销售人员
     *@创建人 qin lina
     *@创建时间 2020/3/10
     */
    void insertImportList(Map<String, Object> p);

   /**
    *@描述 根据指定的销售人员id  查询人员
    *@创建人 qin lina
    *@创建时间 2020/3/13
    */
    List<InsuranceSalesInfo> insuranceSalesListForSalesNo(List<String> salesNos);

    void updateImportList(Map<String, Object> p);

    List<Map<String,Object>> insuranceSalesRelationList(@RequestBody Map<String, Object> params);

	void addSalesRelation(Map<String, Object> map);


	Long findMaxSalerNoForOrg(Map<String, Object> paras);

	void saveImportRelation(Map<String, Object> paras);

	void invalidYcCFirstGener(Long insuranceSalerId);

	void invalidYcCSecondGener(Long insuranceSalerId);

	void invalidYcBFirstGener(Long insuranceSalerId);

	void invalidYcBSecondGener(Long insuranceSalerId);

	void salesMove();

    Map<String,Object> salerNextMessage(Map<String, Object> params);

	String searchHKSalerData(String mobile);

	void saveImportSalerQuit(Map<String, Object> paras);

    List<Map<String,Object>> querySalesForCalCommission(Map<String, Object> params);

	List<Map<String,Object>> findSalesInfoMessage(Map<String, Object> paras);
}
