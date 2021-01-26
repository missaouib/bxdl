package com.hzcf.core.mapper;

import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.insurance.sales.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *保险部门管理
 */
public  interface InsuranceSalesInfoMapper {
	/*
     * 添加部门
     * */
    int addInsuranceSales(InsuranceSalesInfo insuranceSalesInfo);

	List<Map<String, Object>> getInsuranceSalesList(Map<String, Object> params);

	long getInsuranceSalesListSize(Map<String, Object> params);

	InsuranceSalesInfo selectById(Map<String, Object> params);

	void updateInsuranceSales(InsuranceSalesInfo insuranceSalesInfo);

	void addSalesTitle(List<SalesTitles> salesTitles);

	void addSalesEduJob(List<SalesEduJob> salesEduJob);

	void addSalesRelative(List<SalesRelative> salesRelative);

	void addSalesContract(List<SalesContract> salesContract);

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

	List<Map<String, Object>> getSalesMovePage(Map<String, Object> params);

	long getSalesMovePageSize(Map<String, Object> params);

	void insertMove(SalesMoveLogs salesMoveLogs);

	List<SalesMoveLogs> getSalesMoveList(Map<String, Object> params);

	void insertBlack(SalerBlackInfo salerBlackInfo);

	List<Object> getSalerBlackList(Map<String, Object> params);

	List<Map<String, Object>> getSalerBlackPage(Map<String, Object> params);

	long getSalerBlackPageSize(Map<String, Object> params);

	int findMaxSalerNo(Map<String, Object> params);

	Map<String, Object> selectQuit(Map<String, Object> params);

	InsuranceSalesInfo selectBySalerInvitation(Map<String, Object> params);

    Map<String, Object> selectInsuranceSalesOne(Map<String, Object> params);

	/**
	 * <p>根据推荐人id查询推荐员工数量</p>
	 * @param tjSalesId	推荐人id
	 * @return
	 */
	int queryBTJCount(@Param("tjSalesId")long tjSalesId);

	/**
	 * <p>根据科长员工id查询增员属员</p>
	 * @param tjSalesId	推荐人id
	 * @return
	 */
	List<Long> queryTJSubordinateForKZ(@Param("tjSalesId")long tjSalesId);

	/**
	 * <p>根据员工id查询下属的科长和考察期科长</p>
	 * @param salesId	员工id
	 * @return
	 */
	List<Long> queryBeRecommendedKZBySalesId(@Param("salesId")long salesId);

	/**
	 * <p>根据育成人id查询被育成人数</p>
	 * @param ycSalesId	育成人id
	 * @param salesGradeId	被育成人职级id
	 * @return
	 */
	int queryBYCCount(@Param("ycSalesId")long ycSalesId, @Param("salesGradeId")long salesGradeId);

	/**
	 * <p>查询所辖团队</p>
	 * 包含被管理人及被推荐人 并且职级不高于本身
	 * @param salesId	员工id
	 * @param salesGradeId	职级id	观察期部长及以上
	 * @return
	 */
	List<Long> querySubordinate(@Param("salesId")long salesId, @Param("salesGradeId")Long salesGradeId);

	/**
	 * <p>查询员工当前任职时间</p>
	 * @param salesId	员工id
	 * @param salesGradeId	职级id
	 * @return
	 */
	String queryEmploymentPeriod(@Param("salesId")long salesId, @Param("salesGradeId")long salesGradeId);

	/**
	 * <p>根据员工id查询团队中某个职务人数</p>
	 * @param salesId	员工id
	 * @param salesGradeId	被查询职务id
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @return
	 */
	int queryDutyCount(@Param("salesId")long salesId, @Param("salesGradeId")long salesGradeId,
					   @Param("startTime")Date startTime, @Param("endTime")Date endTime);

	SalesMoveLogs selectSalerMoveLogs(Map<String, Object> params);

	void updateSalerMove(SalesMoveLogs salesMoveLogs);

    int batchAddSalesInfo(List<Map> list);

    int batchSalesRelative(List<Map> list);

    List<InsuranceSalesInfo> insuranceSalesListForSalesNo(List<String> salesNos);

    void updateSalfeIdForZreo(@Param("salesId")long salesId);

    InsuranceSalesInfo selectByMax(Map<String, Object> params);

    void updateDirectGroupC();

    void updatedDirectDeptB();

    int batchUpdateSalesInfo(Map map);

	int deleteRelativeById(List<Map> rlist);

    List<Map<String,Object>> insuranceSalesRelationList(Map<String, Object> params);

    void addSalesRelation(InsuranceSalesInfo insuranceSalesInfo);

    Long findMaxSalerNoForOrg(Map<String, Object> paras);

    int batchUpdateSalesRelation(List<Map> insuranceSales);

    void invalidYcCFirstGener(Long insuranceSalerId);

	void invalidYcCSecondGener(Long insuranceSalerId);

	void invalidYcBFirstGener(Long insuranceSalerId);

	void invalidYcBSecondGener(Long insuranceSalerId);

	void updateInsuranceSalerForMove(InsuranceSalesInfo isi);

    Map<String,Object> salerNextMessage(Map<String, Object> params);

	int addSalesHkNoticeLog(List<Map> list);

	int updateSalesHkNoticeLog(Map<String, Object> map);

	Long findMaxSalerNoForPrefixSalerNo(Map<String, Object> paras);

	String searchHKSalerData(String mobile);

    List<InsuranceSalesInfo> getAccessHKInsuranceSalesInfos(Map<String, Object> params);

	void updateSalesStatus(List<Map> list);

	int batchInsertQuit(List<Map> list);

    List<Map<String,Object>> querySalesForCalCommission(Map<String, Object> params);

	List<Map<String,Object>> findSalesInfoMessage(Map<String, Object> paras);
}
