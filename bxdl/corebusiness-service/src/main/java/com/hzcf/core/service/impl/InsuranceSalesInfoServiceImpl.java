package com.hzcf.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hzcf.core.async.AsyncTaskService;
import com.hzcf.core.constants.Constants;
import com.hzcf.core.mapper.*;
import com.hzcf.core.service.InsuranceSalesInfoService;
import com.hzcf.core.service.SalesOrgInfoService;
import com.hzcf.core.util.PageModel;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.allowance.DirectorAllowanceStandardPojo;
import com.hzcf.pojo.insurance.sales.*;
import com.hzcf.util.DateUtil;
import com.hzcf.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InsuranceSalesInfoServiceImpl implements InsuranceSalesInfoService {

   private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InsuranceSalesInfoMapper insuranceSalesInfoMapper;
    @Autowired
    private SalesAssessMapper salesAssessMapper;
    @Autowired
    private InsPolicyInsuredPersonMapper insPolicyInsuredPersonMapper;

    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;

    @Autowired
    private SalerRelationChangeMapper salerRelationChangeMapper;
    @Autowired
    private SalerRelationLogMapper salerRelationLogMapper;
    @Autowired
    private AsyncTaskService asyncTaskService;
    @Autowired
    private SalesOrgInfoService salesOrgInfoService;

    @Override
    @Transactional
    public int addInsuranceSales(InsuranceSalesInfo insuranceSalesInfo) {
        int insuranceSaleId = insuranceSalesInfoMapper.addInsuranceSales(insuranceSalesInfo);
        //TODO 先注释汇康同步
        //保存汇康同步日志
        List<Map> list = new ArrayList<>();
        List<InsuranceSalesInfo> noticySales = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("salerNo",insuranceSalesInfo.getInsuranceSalerNo());
        map.put("saleOrgId",null);
        map.put("type",Constants.NOTICY_TYPE_SALES);
        map.put("isFinshed",Constants.NOTICY_RESULT_FAIL);
        list.add(map);
        insuranceSalesInfoMapper.addSalesHkNoticeLog(list);
        //发起推送
        noticySales.add(insuranceSalesInfo);
        asyncTaskService.asyncSalesHkNotice(noticySales);
        logger.info("业务逻辑线程"+Thread.currentThread().getName()+"运行完毕==============");

        return insuranceSaleId;
    }


    @Override
    public PageModel getInsuranceSalesList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex",model.getPageSize());

        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String, Object>> list = insuranceSalesInfoMapper.getInsuranceSalesList(params);
        long size = insuranceSalesInfoMapper.getInsuranceSalesListSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public PageModel getSalesMovePage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex",model.getPageSize());

        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String, Object>> list = insuranceSalesInfoMapper.getSalesMovePage(params);
        long size = insuranceSalesInfoMapper.getSalesMovePageSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public PageModel getSalerBlackPage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex",model.getPageSize());

        //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);

        List<Map<String, Object>> list = insuranceSalesInfoMapper.getSalerBlackPage(params);
        long size = insuranceSalesInfoMapper.getSalerBlackPageSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

    @Override
    public InsuranceSalesInfo selectById(Map<String, Object> params) {
        InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoMapper.selectById(params);
        return insuranceSalesInfo;
    }

    @Override
    @Transactional
    public void updateInsuranceSales(InsuranceSalesInfo insuranceSalesInfo,boolean isnoticy) {
        insuranceSalesInfoMapper.updateInsuranceSales(insuranceSalesInfo);
        if (isnoticy){
            List<InsuranceSalesInfo> noticySales = new ArrayList<>();
            //保存汇康同步日志
            List<Map> list = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("salerNo", insuranceSalesInfo.getInsuranceSalerNo());
            map.put("saleOrgId", null);
            map.put("type", Constants.NOTICY_TYPE_SALES);
            map.put("isFinshed", Constants.NOTICY_RESULT_FAIL);
            list.add(map);
            insuranceSalesInfoMapper.addSalesHkNoticeLog(list);
            //发起推送
            noticySales.add(insuranceSalesInfo);
            asyncTaskService.asyncSalesHkNotice(noticySales);
            logger.info("业务逻辑线程" + Thread.currentThread().getName() + "运行完毕==============");
        }



    }

    @Override
    public void addSalesTitle(List<SalesTitles> salesTitles) {
        insuranceSalesInfoMapper.addSalesTitle(salesTitles);
    }

    @Override
    public void addSalesEduJob(List<SalesEduJob> salesEduJob) {
        insuranceSalesInfoMapper.addSalesEduJob(salesEduJob);
    }

    @Override
    public void addSalesRelative(List<SalesRelative> salesRelative) {
        insuranceSalesInfoMapper.addSalesRelative(salesRelative);
    }

    @Override
    public void addSalesContract(List<SalesContract> salesContract) {
        insuranceSalesInfoMapper.addSalesContract(salesContract);
    }

    @Override
    public int findMaxTreeCode(Map<String, Object> params) {
        return insuranceSalesInfoMapper.findMaxTreeCode(params);
    }

    @Override
    public int findMaxSalerNo(Map<String, Object> params) {
        return insuranceSalesInfoMapper.findMaxSalerNo(params);
    }

    @Override
    public List<InsuranceSalesInfo> insuranceSalesList(Map<String, Object> params) {
        return insuranceSalesInfoMapper.insuranceSalesList(params);
    }

    @Override
    public List<SalesMoveLogs> getSalesMoveList(Map<String, Object> params) {
        return insuranceSalesInfoMapper.getSalesMoveList(params);
    }

    @Override
    public List<SalesTitles> findSalesTitles(Map<String, Object> params) {
        return insuranceSalesInfoMapper.findSalesTitles(params);
    }

    @Override
    public List<SalesEduJob> findSalesEduJobs(Map<String, Object> params) {
        return insuranceSalesInfoMapper.findSalesEduJobs(params);
    }

    @Override
    public List<SalesRelative> findSalesRelatives(Map<String, Object> params) {
        return insuranceSalesInfoMapper.findSalesRelatives(params);
    }

    @Override
    public List<SalesContract> findSalesContracts(Map<String, Object> params) {
        return insuranceSalesInfoMapper.findSalesContracts(params);
    }

    @Override
    public void updateSalesTitle(List<SalesTitles> salesTitles) {
        insuranceSalesInfoMapper.updateSalesTitle(salesTitles);
    }

    @Override
    public void deleteTitleByIds(Map<String, Object> params) {
        insuranceSalesInfoMapper.deleteTitleByIds(params);
    }

    @Override
    public void updateSalesEduJob(List<SalesEduJob> salesEduJob) {
        insuranceSalesInfoMapper.updateSalesEduJob(salesEduJob);
    }

    @Override
    public void deleteEduByIds(Map<String, Object> params) {
        insuranceSalesInfoMapper.deleteEduByIds(params);
    }

    @Override
    public void updateSalesRelative(List<SalesRelative> salesRelative) {
        insuranceSalesInfoMapper.updateSalesRelative(salesRelative);
    }

    @Override
    public void deleteShipByIds(Map<String, Object> params) {
        insuranceSalesInfoMapper.deleteShipByIds(params);
    }

    @Override
    public void updateSalesContracts(List<SalesContract> salesContract) {
        insuranceSalesInfoMapper.updateSalesContracts(salesContract);
    }

    @Override
    public void deleteHtByIds(Map<String, Object> params) {
        insuranceSalesInfoMapper.deleteHtByIds(params);
    }

    @Override
    public void addZjjt(List<DirectorAllowanceStandardPojo> directorAllowanceStandardPojos) {
        insuranceSalesInfoMapper.addZjjt(directorAllowanceStandardPojos);
    }

    @Override
    public List<DirectorAllowanceStandardPojo> findZjjt(Map<String, Object> params) {
        return insuranceSalesInfoMapper.findZjjt(params);
    }

    @Override
    public void updateZjjts(List<DirectorAllowanceStandardPojo> directorAllowanceStandardPojo) {
        insuranceSalesInfoMapper.updateZjjts(directorAllowanceStandardPojo);
    }

    @Override
    public void deleteZjjtByIds(Map<String, Object> params) {
        insuranceSalesInfoMapper.deleteZjjtByIds(params);
    }

    @Override
    @Transactional
    public void insertQuit(SalerQuitInfo salerQuitInfo) {
         Map<String,Object> paras = new HashMap<>();
		   String insuranceSalesId = salerQuitInfo.getSalerId().toString();
		   paras.put("insuranceSalesId", insuranceSalesId);
		   InsuranceSalesInfo salesInfo = insuranceSalesInfoMapper.selectById(paras);
		   salesInfo.setSalesStatus("2");
           salesInfo.setQuitDate(salerQuitInfo.getQuitDate());
		   salerQuitInfo.setCreatedTime(new Date());
            insuranceSalesInfoMapper.insertQuit(salerQuitInfo);
            boolean isNoticy = true;
		   updateInsuranceSales(salesInfo,isNoticy);
    }

    @Override
    public void insertMove(SalesMoveLogs salesMoveLogs) {
        insuranceSalesInfoMapper.insertMove(salesMoveLogs);
    }

    @Override
    public void insertBlack(SalerBlackInfo salerBlackInfo) {
        insuranceSalesInfoMapper.insertBlack(salerBlackInfo);
    }

    @Override
    public List<Object> getSalerBlackList(Map<String, Object> params) {
        return insuranceSalesInfoMapper.getSalerBlackList(params);
    }

    @Override
    public Map<String, Object> selectQuit(Map<String, Object> params) {
        Map<String,Object> map = insuranceSalesInfoMapper.selectQuit(params);
        String jobHandoverId = (String) map.get("JOB_HANDOVER_ID");
        String salerTreeHandoverId = (String) map.get("SALER_TREE_HANDOVER_ID");
        Map<String, Object> paras = new HashMap<>();
        paras.put("insuranceSalesId", jobHandoverId);
        if(StringUtil.isNotBlanks(jobHandoverId)){
            InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoMapper.selectById(paras);
            if(insuranceSalesInfo != null){
                map.put("jobHandoverId",insuranceSalesInfo.getInsuranceSaler());
                map.put("jobHandoverNo",insuranceSalesInfo.getInsuranceSalerNo());
                map.put("jobHandoverOrg",insuranceSalesInfo.getSalesOrgName());
                map.put("jobHandoverTeam",insuranceSalesInfo.getSalesTeamName());
            }
        }
//        paras.clear();
//        paras.put("insuranceSalesId",salerTreeHandoverId);
//        InsuranceSalesInfo salerTreeHandover = insuranceSalesInfoMapper.selectById(paras);
//
//        map.put("salerTreeHandoverId",salerTreeHandover.getInsuranceSaler());
//        map.put("salerTreeHandoverNo",salerTreeHandover.getInsuranceSaler());
//        map.put("tree_to_rankSequenceId",salerTreeHandover.getRankSequenceId());
//        map.put("tree_to_salesGradeId",salerTreeHandover.getSalesGradeId());
//        map.put("salerTreeHandoverOrg",salerTreeHandover.getSalesOrgName());
//        map.put("salerTreeHandoverTeam",salerTreeHandover.getSalesTeamName());
        return map;
    }

    @Override
    public InsuranceSalesInfo selectBySalerInvitation(Map<String, Object> params) {
        return insuranceSalesInfoMapper.selectBySalerInvitation(params);
    }

    @Override
    public Map<String, Object> selectInsuranceSalesOne(Map<String, Object> params) {
        return insuranceSalesInfoMapper.selectInsuranceSalesOne(params);
    }

    @Override
    public PageModel findSalesAssessPage(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf(params.get("pageSize"))));
        params.put("startRow", model.getStartIndex());
        params.put("rows", model.getPageSize());
        long count = salesAssessMapper.querySalesAssessCount(params);
        if (count > 0) {
            List<SalesAssess> list = salesAssessMapper.querySalesAssessList(params);
            model.setList(list);
        } else {
            model.setList(Collections.emptyList());
        }
        model.setTotalRecords(count);
        return model;
    }

    @Override
    public void saveSalesAssess(SalesAssess salesAssess) {
        salesAssessMapper.insertSalesAssess(salesAssess);
    }

    @Override
    public boolean updateSalesAssess(SalesAssess salesAssess) {
        return salesAssessMapper.updateSalesAssess(salesAssess) > 0;
    }

    @Override
    public SalesAssess findSalesAssessById(long id) {
        return salesAssessMapper.querySalesAssessById(id);
    }

    /**
     * <p>销售人员职级考核</p>
     */
    @Override
    public void examineSalesmanGrade() {

    }












    /**
     * <p>查询FYC</p>
     * 有入司时间时判断是否累计FYC是否需要满一千
     *
     * @param insuranceSalesId 员工id
     * @param cycle            考核周期
     * @param joinDate         入司时间
     * @return
     */
    @Override
    public double getFYC(long insuranceSalesId, int cycle, String joinDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MONTH, -1);
        String firstDay = "";
        String lastDay = "";
        if (StringUtils.isNotBlank(joinDate)) {
            firstDay = joinDate + " 00:00:00";
        } else {
            if (1 == cycle) {
                // 考核周期为一个月的查询考核日期上一个月的第一天和最后一天
                Map<String, Object> day = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
                firstDay = day.get("firstDay").toString();
                lastDay = day.get("lastDay").toString();
            } else {
                // 考核周期为三个月的查询考核日期上一个月的第一天和最后一天
                Map<String, Object> day = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
                lastDay = day.get("lastDay").toString();
                instance.add(Calendar.MONTH, -(cycle - 1));
                Map<String, Object> day2 = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
                firstDay = day2.get("firstDay").toString();
            }
            firstDay = firstDay + " 00:00:00";
        }
        lastDay = lastDay + " 23:59:59";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("insuranceSalesId", insuranceSalesId);
        try {
            params.put("startTime", format.parse(firstDay));
            params.put("endTime", format.parse(lastDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> list = insPolicyInsuredPersonMapper.queryPremiumForFYC(params);
        BigDecimal bigDecimal = new BigDecimal(0);
        for (Map map : list) {
            BigDecimal decimal = new BigDecimal(100);
            // 计算FYC
            // 价值佣金FYC = 价值佣金系数 * 标保折标系数 * 规模保费
            // 保费
            BigDecimal premium = new BigDecimal(map.get("PREMIUM").toString());
            // 价值佣金系数
            BigDecimal valueCommissionCoefficient = new BigDecimal(map.get("VALUE_COMMISSION_COEFFICIENT").toString()).divide(decimal, 2, BigDecimal.ROUND_HALF_UP);
            // 标保折标系数
            BigDecimal indexingCoefficient = new BigDecimal(map.get("INDEXING_COEFFICIENT").toString()).divide(decimal, 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal result = valueCommissionCoefficient.multiply(indexingCoefficient).multiply(premium);
            bigDecimal = bigDecimal.add(result);
            if (StringUtils.isNotBlank(joinDate)) {
                if (bigDecimal.doubleValue() >= 1000) {
                    break;
                }
            }
        }

        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }



	@Override
	public SalesMoveLogs selectSalerMoveLogs(Map<String, Object> params) {
		return insuranceSalesInfoMapper.selectSalerMoveLogs(params);
	}

	@Override
	public void updateSalerMove(SalesMoveLogs salesMoveLogs) {
		insuranceSalesInfoMapper.updateSalerMove(salesMoveLogs);
	}

    @Override
    public String findEmploymentPeriod(long salesId, long salesGradeId) {
        return insuranceSalesInfoMapper.queryEmploymentPeriod(salesId, salesGradeId);
    }

    @Override
    public SalesAssess findNewSalesAssess(long insuranceSalesId, long salesGradeId) {
        return salesAssessMapper.queryNewSalesAssess(insuranceSalesId, salesGradeId);
    }

    @Override
    public int queryBTJCount(long tjSalesId) {
        return insuranceSalesInfoMapper.queryBTJCount(tjSalesId);
    }

    @Override
    public int queryDutyCount(long salesId, long salesGradeId, Date startTime, Date endTime) {
        return insuranceSalesInfoMapper.queryDutyCount(salesId, salesGradeId, startTime, endTime);
    }

    @Override
    public List<Long> queryTJSubordinateForKZ(long tjSalesId) {
        return insuranceSalesInfoMapper.queryTJSubordinateForKZ(tjSalesId);
    }

    @Override
    public int queryBYCCount(long ycSalesId, long salesGradeId) {
        return insuranceSalesInfoMapper.queryBYCCount(ycSalesId, salesGradeId);
    }

    @Override
    public List<Long> querySubordinate(long salesId, Long salesGradeId) {
        return insuranceSalesInfoMapper.querySubordinate(salesId, salesGradeId);
    }

    @Override
    public List<Long> queryBeRecommendedKZBySalesId(long salesId) {
        return insuranceSalesInfoMapper.queryBeRecommendedKZBySalesId(salesId);
    }

    @Override
    @Transactional
    public void insertImportList(Map<String, Object> p) {
        Object salesInfo= p.get("salesInfo_string");
        Object relative =p.get("relativeRow_string");
       JSONArray objects = JSONObject.parseArray(salesInfo.toString());
       List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
        lists.forEach((List<Map> list) -> {
            //保存通知汇康数据
            List<Map> noticySalesLogs = new ArrayList<>();
            list.forEach(map -> {
                Map<String, Object> mapLog = new HashMap<>();
                mapLog.put("salerNo",map.get("insuranceSalerNo"));
                mapLog.put("saleOrgId",null);
                mapLog.put("type",Constants.NOTICY_TYPE_SALES);
                mapLog.put("isFinshed",Constants.NOTICY_RESULT_FAIL);
                noticySalesLogs.add(mapLog);
            });
           logger.info("插入销售人员数据集：");
           int successPipSize = insuranceSalesInfoMapper.batchAddSalesInfo(list);
            logger.info("插入销售人员数据集成功条数："+successPipSize);

            logger.info("插入推送日志数据集");
            int successLogSize = insuranceSalesInfoMapper.addSalesHkNoticeLog(noticySalesLogs);
            logger.info("插入推送日志数据集成功条数:"+successLogSize);

       });
       if(relative != null) {
           JSONArray robjects = JSONObject.parseArray(relative.toString());
           List<List<Map>> rlists = PublicUtil.splitListReturnListMap(robjects, 100);
           if (null != rlists && !rlists.isEmpty() && rlists.size() > 0) {
               rlists.forEach(rlist -> {
                   logger.info("插入紧急联系人员数据集：");
                   for (Map rm : rlist) {
                       Map<String, Object> map = new HashMap<String, Object>();
                       map.put("cardNo", rm.get("cardNo"));
                       map.put("reEntryFlag", "1");//再次入职
                       InsuranceSalesInfo insuranceSalesInfo = insuranceSalesInfoMapper.selectByMax(map);
                       rm.put("salesId", insuranceSalesInfo.getInsuranceSalesId());
                   }
                   int successSize = insuranceSalesInfoMapper.batchSalesRelative(rlist);
                   logger.info("插入紧急联系人员数据集成功条数：" + successSize);

               });
           }
       }

       //异步同步销售人员
        asyncTaskService.asyncSalesHkNoticeBatch(salesInfo);
    }

    @Override
    public List<InsuranceSalesInfo> insuranceSalesListForSalesNo(List<String> salesNos) {
        return insuranceSalesInfoMapper.insuranceSalesListForSalesNo(salesNos);
    }

    @Override
    @Transactional
    public void updateImportList(Map<String, Object> p) {
        Object salesInfo = p.get("salesInfo_string");
        Object relative = p.get("relativeRow_string");
        Object salesNoticy = p.get("salesNoticy_string");
        JSONArray objects = JSONObject.parseArray(salesInfo.toString());
        List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
        lists.forEach(list -> {
            logger.info("更新销售人员数据集：");
            list.forEach(map -> {
                int successPipSize = insuranceSalesInfoMapper.batchUpdateSalesInfo(map);
                logger.info("更新销售人员数据集成功条数：" + successPipSize);
            });
        });
        /*保存推送日志*/
        if (ObjectUtil.isNotEmpty(salesNoticy)) {
            JSONArray salesNoticyArray = JSONObject.parseArray(salesNoticy.toString());
            List<List<Map>> salesNoticys = PublicUtil.splitListReturnListMap(salesNoticyArray, 100);
            if (CollUtil.isNotEmpty(salesNoticys)) {
                salesNoticys.forEach((List<Map> list) -> {
                    //保存通知汇康数据
                    List<Map> noticySalesLogs = new ArrayList<>();
                    list.forEach(map -> {
                        Map<String, Object> mapLog = new HashMap<>();
                        mapLog.put("salerNo", map.get("insuranceSalerNo"));
                        mapLog.put("saleOrgId", null);
                        mapLog.put("type", Constants.NOTICY_TYPE_SALES);
                        mapLog.put("isFinshed", Constants.NOTICY_RESULT_FAIL);
                        noticySalesLogs.add(mapLog);
                    });
                    logger.info("插入推送日志数据集");
                    int successLogSize = insuranceSalesInfoMapper.addSalesHkNoticeLog(noticySalesLogs);
                    logger.info("插入推送日志数据集成功条数:" + successLogSize);
                });
            }
        }
        JSONArray robjects = JSONObject.parseArray(relative.toString());
        List<List<Map>> rlists = PublicUtil.splitListReturnListMap(robjects, 100);
        if (null!=rlists && !rlists.isEmpty() && rlists.size() > 0) {
             rlists.forEach(rlist->{
                logger.info("更新紧急联系人员数据集：");
                int size = insuranceSalesInfoMapper.deleteRelativeById(rlist);//先删除
//                if(size>0){//删除成功后
                   int successSize = insuranceSalesInfoMapper.batchSalesRelative(rlist);//插入
                   logger.info("插入紧急联系人员数据集成功条数："+successSize);
//                }
             });
        }
             //异步同步销售人员
        asyncTaskService.asyncSalesHkNoticeBatch(salesNoticy);

    }

    @Override
    public List<Map<String, Object>> insuranceSalesRelationList(Map<String, Object> params) {
          SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		   Calendar calendar = Calendar.getInstance();
		   calendar.add(Calendar.MONTH, 1);
		   calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        params.put("moveDate",dft.format(calendar.getTime()));
         //数据权限相关
        String myDeptNo = "";
        if(params.get("myDeptNo") != null){
            myDeptNo = String.valueOf(params.get("myDeptNo"));
        }
        String myAllOrgIds = salesOrgInfoMapper.findEmployeeAllOrgs(myDeptNo);
        params.put("myAllOrgIds", myAllOrgIds);
        return insuranceSalesInfoMapper.insuranceSalesRelationList(params);
    }

    @Override
    @Transactional
    public void addSalesRelation(Map<String,Object> parm) {
        Object insuranceSalesInfoObj = parm.get("insuranceSalesInfo");
        Object salerRelationLogObj  = parm.get("salerRelationLog");
        InsuranceSalesInfo insuranceSalesInfo = JSONUtil.toBean(JSONUtil.toJsonStr(insuranceSalesInfoObj), InsuranceSalesInfo.class);
        SalerRelationLog salerRelationLog = JSONUtil.toBean(JSONUtil.toJsonStr(salerRelationLogObj), SalerRelationLog.class);

        //查找是否存在下月要生效的员工关系日志
       String str = DateUtil.date2Str(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(),1)),DateUtil.TIME_FORMAT_MONTHS);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("salerId",insuranceSalesInfo.getInsuranceSalesId());
        map.put("relationMonth",str);
        SalerRelationChange relationChange = salerRelationChangeMapper.selectRelationBySalerIdAndMonth(map);
        if (relationChange != null){
            packRelationChangeUpdate(insuranceSalesInfo,relationChange);
            salerRelationChangeMapper.updateRelationChange(relationChange);
        }
         insuranceSalesInfoMapper.addSalesRelation(insuranceSalesInfo);
        salerRelationLogMapper.insert(salerRelationLog);
    }

     private void packRelationChangeUpdate( InsuranceSalesInfo salerRelation,SalerRelationChange relationChange) {
        String date =  cn.hutool.core.date.DateUtil.format(DateUtil.getFirstDateOfMonth(DateUtil.addMonths(new Date(),1)),"yyyy-MM-dd");
          if (ObjectUtil.equal(relationChange.getDbBeforeSalesId(),relationChange.getDbAfterSalesId())){
            relationChange.setDbAfterSalesId(salerRelation.getDbSalesId());
            relationChange.setDbSalesDate(salerRelation.getDbSalesDate());
        }
        if (ObjectUtil.equal(relationChange.getTjBeforeSalesId(),relationChange.getTjAfterSalesId())){
            relationChange.setTjAfterSalesId(salerRelation.getTjSalesId());
            relationChange.setTjSalesDate(salerRelation.getTjSalesDate());
        }
        if (ObjectUtil.equal(relationChange.getYcBeforeCFirstGener(),relationChange.getYcAfterCFirstGener())){
            relationChange.setYcAfterCFirstGener(salerRelation.getYcCFirstGener());
            relationChange.setEstablishTime(salerRelation.getEstablishTime());
        }
        if (ObjectUtil.equal(relationChange.getYcBeforeCSecondGener(),relationChange.getYcAfterCSecondGener())){
            relationChange.setYcAfterCSecondGener(salerRelation.getYcCSecondGener());
            relationChange.setEstablishTime1(salerRelation.getEstablishTime1());
        }
        if (ObjectUtil.equal(relationChange.getYcBeforeBFirstGener(),relationChange.getYcAfterBFirstGener())){
            relationChange.setYcAfterBFirstGener(salerRelation.getYcBFirstGener());
            relationChange.setEstablishTime2(salerRelation.getEstablishTime2());
        }
        if (ObjectUtil.equal(relationChange.getYcAfterBSecondGener(),relationChange.getYcAfterBSecondGener())){
            relationChange.setYcAfterBSecondGener(salerRelation.getYcBSecondGener());
            relationChange.setEstablishTime3(salerRelation.getEstablishTime3());
        }
        if (ObjectUtil.equal(relationChange.getDirectBeforeGroupC(),relationChange.getDirectAfterGroupC())){
            relationChange.setDirectAfterGroupC(salerRelation.getDirectGroupC());
            relationChange.setEstablishTime4(salerRelation.getEstablishTime4());
        }
        if (ObjectUtil.equal(relationChange.getDirectBeforeDeptB(),relationChange.getDirectAfterDeptB())){
            relationChange.setDirectAfterDeptB(salerRelation.getDirectDeptB());
            relationChange.setEstablishTime5(salerRelation.getEstablishTime5());
        }
        if (ObjectUtil.equal(relationChange.getSjBeforeSalesId(),relationChange.getSjAfterSalesId())){
            relationChange.setSjAfterSalesId(salerRelation.getSjSalesId());
            relationChange.setSjSalesDate(salerRelation.getSjSalesDate());
        }
        if (ObjectUtil.equal(relationChange.getJcBeforeSalesId(),relationChange.getJcAfterSalesId())){
            relationChange.setJcAfterSalesId(salerRelation.getJcSalesId());
            relationChange.setJcSalesDate(salerRelation.getJcSalesDate());
        }
        if (ObjectUtil.equal(relationChange.getFdBeforeSalesId(),relationChange.getFdAfterSalesId())){
            relationChange.setFdAfterSalesId(salerRelation.getFdSalesId());
            relationChange.setFdSalesDate(salerRelation.getFdSalesDate());
        }
        relationChange.setDbBeforeSalesId(salerRelation.getDbSalesId());
        relationChange.setTjBeforeSalesId(salerRelation.getTjSalesId());
        relationChange.setYcBeforeCFirstGener(salerRelation.getYcCFirstGener());
        relationChange.setYcBeforeCSecondGener(salerRelation.getYcCSecondGener());
        relationChange.setYcBeforeBFirstGener(salerRelation.getYcBFirstGener());
        relationChange.setYcBeforeBSecondGener(salerRelation.getYcBSecondGener());
        relationChange.setDirectBeforeGroupC(salerRelation.getDirectGroupC());
        relationChange.setDirectBeforeDeptB(salerRelation.getDirectDeptB());
        relationChange.setSjBeforeSalesId(salerRelation.getSjSalesId());
        relationChange.setJcBeforeSalesId(salerRelation.getJcSalesId());
        relationChange.setFdBeforeSalesId(salerRelation.getFdSalesId());

    }

    @Override
    public Long findMaxSalerNoForOrg(Map<String, Object> paras) {
        Long coreMaxEmpNo = insuranceSalesInfoMapper.findMaxSalerNoForOrg(paras);

        Long hkMaxEmpNo = insuranceSalesInfoMapper.findMaxSalerNoForPrefixSalerNo(paras);
        if(coreMaxEmpNo >= hkMaxEmpNo) {
            return coreMaxEmpNo;
        }
        return hkMaxEmpNo;
    }

    @Override
    @Transactional
    public void saveImportRelation(Map<String, Object> paras) {
         Object relationChange = paras.get("relationChange_string");
        Object relative = paras.get("relativeRow_string");
         Object relationChangeUpdate_string = paras.get("relationChangeUpdate_string");
          Object salerRelationLog_string = paras.get("salerRelationLog_string");
        //立即生效
       JSONArray relationObjects = JSONObject.parseArray(relative.toString());
        List<List<Map>> insuranceSales = PublicUtil.splitListReturnListMap(relationObjects, 100);
        if (CollUtil.isNotEmpty(insuranceSales)){
            insuranceSales.forEach(insuranceSaleList->{
                  logger.info("修改销售人员关系：" );
                insuranceSalesInfoMapper.batchUpdateSalesRelation(insuranceSaleList);
            });
        }
         //同步立即生效后 存在的下月生效的关系日志表
       JSONArray relationChangeUpdateObjects = JSONObject.parseArray(relationChangeUpdate_string.toString());
        List<List<Map>> relationChangeUpdates = PublicUtil.splitListReturnListMap(relationChangeUpdateObjects, 100);
        if (CollUtil.isNotEmpty(relationChangeUpdates)){
            relationChangeUpdates.forEach(relationChangeUpdate->{
                  logger.info("同步人员关系日志表：" );
                salerRelationChangeMapper.batchUpdateRelationChange(relationChangeUpdate);
            });
        }

        //次月生效
        JSONArray relationChangeObjects = JSONObject.parseArray(relationChange.toString());
        List<List<Map>> relationChanges = PublicUtil.splitListReturnListMap(relationChangeObjects, 100);
        if (CollUtil.isNotEmpty(relationChanges)){
            relationChanges.forEach(relationChangeList->{
                  deleteSalerRelationChange(relationChangeList);
                logger.info("销售人员关系日志表：" );
                int successSize = salerRelationChangeMapper.batchSalerRelationChange(relationChangeList);//批量插入
                logger.info("销售人员关系日志表条数：" + successSize);
            });
        }


         //员工关系操作日志
        JSONArray salerRelationLogObjects = JSONObject.parseArray(salerRelationLog_string.toString());
        List<List<Map>> relationLogs = PublicUtil.splitListReturnListMap(salerRelationLogObjects, 100);
        if (CollUtil.isNotEmpty(relationLogs)){
            relationLogs.forEach(relationLogsList->{
                logger.info("销售人员关系操作日志：" );
                int successSize = salerRelationLogMapper.batchSalerRelationLog(relationLogsList);//批量插入
            });
        }


    }

    @Override
    public void invalidYcCFirstGener(Long insuranceSalerId) {
        insuranceSalesInfoMapper.invalidYcCFirstGener(insuranceSalerId);
    }

    @Override
    public void invalidYcCSecondGener(Long insuranceSalerId) {
        insuranceSalesInfoMapper.invalidYcCSecondGener(insuranceSalerId);
    }

    @Override
    public void invalidYcBFirstGener(Long insuranceSalerId) {
        insuranceSalesInfoMapper.invalidYcBFirstGener(insuranceSalerId);
    }

    @Override
    public void invalidYcBSecondGener(Long insuranceSalerId) {
        insuranceSalesInfoMapper.invalidYcBSecondGener(insuranceSalerId);

    }

    @Override
    @Transactional
    public void salesMove() {
          Map<String, Object> paras = new HashMap<>();
			paras.put("currDateDo","currDateDo");//今日待执行
			paras.put("checkStatus","1");//审核状态：0 待审核  1审核通过
			paras.put("changeFlag","0");//是否执行
			List<SalesMoveLogs> smls = insuranceSalesInfoMapper.getSalesMoveList(paras);
			if(smls!=null && smls.size()>0){
                List<Map> noticySaleslog = new ArrayList<>();
                List<InsuranceSalesInfo> noticySaleslist = new ArrayList<>();
                for(SalesMoveLogs sml:smls){
					Long insuranceSalerId = sml.getSalerId();
					paras.clear();
					paras.put("insuranceSalesId",insuranceSalerId);
					InsuranceSalesInfo isi = insuranceSalesInfoMapper.selectById(paras);
					//判断是否异动组织机构
                    if (!isi.getSalesOrgId().equals(Long.valueOf(sml.getNextOrgId()))){
                        Map<String, Object> map = new HashMap<>();
                        map.put("salerNo",isi.getInsuranceSalerNo());
                        map.put("saleOrgId",null);
                        map.put("type",Constants.NOTICY_TYPE_SALES);
                        map.put("isFinshed",Constants.NOTICY_RESULT_FAIL);
                        noticySaleslog.add(map);
                        noticySaleslist.add(isi);
                    }
					//育成人与被育成人的职级发生变化时，相对应的育成关系失效
					if (Constants.SALES_GRADE_NAME_3.equals(sml.getPreSalesGradeId()) && !Constants.SALES_GRADE_NAME_3.equals(sml.getNextSalesGradeId())){
						isi.setYcCFirstGener(null);
						isi.setEstablishTime(null);
						isi.setYcCSecondGener(null);
						isi.setEstablishTime1(null);
						// 一代处育成人是这个人的育成关系失效
						logger.info("一代处育成人是这个人的育成关系失效");
						insuranceSalesInfoMapper.invalidYcCFirstGener(insuranceSalerId);
						// 二代处育成人是这个人的育成关系失效
						logger.info("二代处育成人是这个人的育成关系失效");
						insuranceSalesInfoMapper.invalidYcCSecondGener(insuranceSalerId);
					}
					if (Constants.SALES_GRADE_NAME_2.equals(sml.getPreSalesGradeId()) && !Constants.SALES_GRADE_NAME_2.equals(sml.getNextSalesGradeId())){
						isi.setYcBFirstGener(null);
						isi.setEstablishTime2(null);
						isi.setYcBSecondGener(null);
						isi.setEstablishTime3(null);
						// 一代部育成人是这个人的育成关系失效
                        logger.info("一代部育成人是这个人的育成关系失效");
						insuranceSalesInfoMapper.invalidYcBFirstGener(insuranceSalerId);
						// 二代部育成人是这个人的育成关系失效
                        logger.info("二代部育成人是这个人的育成关系失效");
						insuranceSalesInfoMapper.invalidYcBSecondGener(insuranceSalerId);
					}
					isi.setSalesOrgId(Long.valueOf(sml.getNextOrgId()));
					isi.setSalesTeamId(Long.valueOf(sml.getNextTeamId()));
					isi.setSalesGradeId(Long.valueOf(sml.getNextSalesGradeId()));
					isi.setRankSequenceId(Long.valueOf(sml.getNextRankSeqId()));
					insuranceSalesInfoMapper.updateInsuranceSalerForMove(isi);

					sml.setChangeFlag("1");
					sml.setUpdatedTime(new Date());
					insuranceSalesInfoMapper.updateSalerMove(sml);
				}
				if (CollUtil.isNotEmpty(noticySaleslog)){
                    insuranceSalesInfoMapper.addSalesHkNoticeLog(noticySaleslog);
                     asyncTaskService.asyncSalesHkNotice(noticySaleslist);
                }


			}
    }

    @Override
    public Map<String, Object> salerNextMessage(Map<String, Object> params) {
         SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		   Calendar calendar = Calendar.getInstance();
		   calendar.add(Calendar.MONTH, 1);
		   calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        params.put("moveDate",dft.format(calendar.getTime()));
        return insuranceSalesInfoMapper.salerNextMessage(params);
    }

    @Override
    public String searchHKSalerData(String mobile) {
        return insuranceSalesInfoMapper.searchHKSalerData(mobile);
    }

    @Override
    @Transactional
    public void saveImportSalerQuit(Map<String, Object> paras) {
        //需要修改状态的员工编号
        Object quitSaleNo_obj = paras.get("quitSaleNo_str");
         JSONArray objects = JSONObject.parseArray(quitSaleNo_obj.toString());
       List<List<Map>>  quitSaleNoList = PublicUtil.splitListReturnListMap(objects, 100);
        quitSaleNoList.forEach(list->{
             insuranceSalesInfoMapper.updateSalesStatus(list);
        });

        //需要插入的离职信息
        Object salerQuit_obj = paras.get("salerQuit_str");
        List<List<Map>> salerQuitList = PublicUtil.splitListReturnListMap(JSONObject.parseArray(salerQuit_obj.toString()), 100);
        salerQuitList.forEach(list -> {
            logger.info("开始插入离职信息");
             int successSize =insuranceSalesInfoMapper.batchInsertQuit(list);
             logger.info("插入成功条数："+successSize);
        });

    }

    /**
     *查询 结算佣金的所有代理人  要查询出人的员工编号 组织机构  上个月的组织机构
     * @param params
     * @return
     */
    @Override
    public List<Map<String, Object>> querySalesForCalCommission(Map<String, Object> params) {
        return insuranceSalesInfoMapper.querySalesForCalCommission(params);
    }

    @Override
    public List<Map<String, Object>> findSalesInfoMessage(Map<String, Object> paras) {
         return insuranceSalesInfoMapper.findSalesInfoMessage(paras);
    }


    private void deleteSalerRelationChange(List<Map> list) {
        list.forEach(map -> {
            String month = (String) map.get("relationMonth");
            Long salerId = Long.valueOf((int) map.get("salerId"));
            if(null!=month&&!"".equals(month)&&null!=salerId){
                salerRelationChangeMapper.deleteSalerRelationChange(map);
            }

        });
    }


}

