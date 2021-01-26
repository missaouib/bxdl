package com.hzcf.core.quartz;

import com.google.common.collect.Maps;
import com.hzcf.core.service.InsuranceSalesInfoService;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import com.hzcf.pojo.insurance.sales.SalesAssess;
import com.hzcf.pojo.insurance.sales.SalesMoveLogs;
import com.hzcf.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 员工考核定时器
 */
public class SalesmanGradeExamineTask extends QuartzJobBean{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static long GCQKZ = 5;
    private final static long KZ = 4;
    private final static long CZ = 3;
    private final static long BZ = 2;
    private final static long ZJ = 1;
    private final static long GCQCZ = 6;
    private final static long GCQBZ = 7;

    private final static String YES = "1";
    private final static String NO = "0";

    @Autowired
    private InsuranceSalesInfoService insuranceSalesInfoService;

    /**
     * <p>员工考核</p>
     * 晋升维持
     */
      @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
          logger.info("员工晋级考核定时器开始执行========");
        Calendar instance = Calendar.getInstance();
        int month = instance.get(Calendar.MONTH) + 1;
        Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
        param.put("effective", true);
        // 查询观察期科长
        param.put("salesGradeId", GCQKZ);
        List<InsuranceSalesInfo> gcqkzList = insuranceSalesInfoService.insuranceSalesList(param);
        gcqkzList.forEach(a -> {
            int m = this.takeOfficeMonth(a.getJoinDate());
            if (m >= 3 && !promoteInsuranceSalesInfo(a)) {
                if (1 == month || 4 == month || 7 == month || 10 == month) {
                    keepInsuranceSalesInfo(a);
                }
            }
        });
        // 查询科长
        param.put("salesGradeId", KZ);
        List<InsuranceSalesInfo> kzList = insuranceSalesInfoService.insuranceSalesList(param);
        kzList.forEach(a -> {
            int m = this.takeOfficeMonth(a.getJoinDate());
            if (m >= 3 && !promoteInsuranceSalesInfo(a)) {
                if (1 == month || 4 == month || 7 == month || 10 == month) {
                    keepInsuranceSalesInfo(a);
                }
            }
        });
        // 查询观察期处长
        param.put("salesGradeId", GCQCZ);
        List<InsuranceSalesInfo> gcqczList = insuranceSalesInfoService.insuranceSalesList(param);
        gcqczList.forEach(a -> {
            int m = 0;
            String moveTime = insuranceSalesInfoService.findEmploymentPeriod(a.getInsuranceSalesId(), a.getSalesGradeId());
            if(StringUtils.isNotBlank(moveTime)){
                m = this.takeOfficeMonth(moveTime);
            } else {
                m = this.takeOfficeMonth(a.getJoinDate());
            }
            if (m >= 3 && !promoteInsuranceSalesInfo(a)) {
                if (1 == month || 4 == month || 7 == month || 10 == month) {
                    keepInsuranceSalesInfo(a);
                }
            }
        });
        // 查询处长
        param.put("salesGradeId", CZ);
        List<InsuranceSalesInfo> czList = insuranceSalesInfoService.insuranceSalesList(param);
        czList.forEach(a -> {
            int m = 0;
            String moveTime = insuranceSalesInfoService.findEmploymentPeriod(a.getInsuranceSalesId(), a.getSalesGradeId());
            if(StringUtils.isNotBlank(moveTime)){
                m = this.takeOfficeMonth(moveTime);
            } else {
                m = this.takeOfficeMonth(a.getJoinDate());
            }
            if (m >= 3 && !promoteInsuranceSalesInfo(a)) {
                if (1 == month || 4 == month || 7 == month || 10 == month) {
                    keepInsuranceSalesInfo(a);
                }
            }
        });
        // 查询观察期部长
        param.put("salesGradeId", GCQBZ);
        List<InsuranceSalesInfo> gcqbzList = insuranceSalesInfoService.insuranceSalesList(param);
        gcqbzList.forEach((InsuranceSalesInfo a) -> {
            int m = 0;
            String moveTime = insuranceSalesInfoService.findEmploymentPeriod(a.getInsuranceSalesId(), a.getSalesGradeId());
            if(StringUtils.isNotBlank(moveTime)){
                m = this.takeOfficeMonth(moveTime);
            } else {
                m = this.takeOfficeMonth(a.getJoinDate());
            }
            if (m >= 3 && (1 == month || 7 == month)) {
                keepBZAssess(a, month);
            }
        });
        // 查询部长
        param.put("salesGradeId", BZ);
        List<InsuranceSalesInfo> bzList = insuranceSalesInfoService.insuranceSalesList(param);
        bzList.forEach(a -> {
            int m = 0;
            String moveTime = insuranceSalesInfoService.findEmploymentPeriod(a.getInsuranceSalesId(), a.getSalesGradeId());
            if(StringUtils.isNotBlank(moveTime)){
                m = this.takeOfficeMonth(moveTime);
            } else {
                m = this.takeOfficeMonth(a.getJoinDate());
            }
            if (m >= 3 && (1 == month || 7 == month)) {
                keepBZAssess(a, month);
            }
        });
        logger.info("员工晋级考核定时器执行结束========");
    }


    private int takeOfficeMonth(String before) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            instance.add(Calendar.MONTH, -1);
            Map<String, Object> day = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
            String lastDay = day.get("lastDay").toString();

            Calendar bef = Calendar.getInstance();
            Calendar aft = Calendar.getInstance();
            bef.setTime(sdf.parse(before));
            aft.setTime(sdf.parse(lastDay));
            int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
            int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
            return Math.abs(month + result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private boolean promoteInsuranceSalesInfo(InsuranceSalesInfo info) {
        boolean result = false;
        if (null == info) {
            return result;
        }

        // 查询考核结果
        SalesAssess salesAssess = insuranceSalesInfoService.findNewSalesAssess(info.getInsuranceSalesId(), info.getSalesGradeId());
        if (null == salesAssess) {
            return result;
        }
        if (GCQKZ == salesAssess.getSalesGradeId()) {
            // 持有执业证 && 通过新人岗前培训并考试通过
            if (YES.equals(info.getCertificateStatus()) && YES.equals(salesAssess.getCondition1())) {
                double fyc = insuranceSalesInfoService.getFYC(info.getInsuranceSalesId(), 3, null);
                if (fyc >= 1500) {
                    result = true;
                    this.saveSalesMoveLog(info, 1);
                }
            }
        } else if (KZ == salesAssess.getSalesGradeId()) {
            // 科长是否通过代理制保险营销员品质考核以及通过公司晋级培训并考试合格
            if (YES.equals(salesAssess.getCondition8()) && YES.equals(salesAssess.getCondition3())) {
                double fyc = insuranceSalesInfoService.getFYC(info.getInsuranceSalesId(), 1, info.getJoinDate());
                if (fyc >= 1000) {
                    // 推荐科长人数
                    int tjCount = insuranceSalesInfoService.queryBTJCount(info.getInsuranceSalesId());
                    if (tjCount >= 6) {
                        double sum = 0;
                        // 通过查询增员id及季度fyc
                        List<Long> btjSalesIds = insuranceSalesInfoService.queryTJSubordinateForKZ(info.getInsuranceSalesId());
                        if (!CollectionUtils.isEmpty(btjSalesIds)) {
                            BigDecimal bigDecimal = BigDecimal.valueOf(sum);
                            //TODO 扫描出bug 先作以下修改，lambda改为增强for
                           /* btjSalesIds.forEach(a -> {
                                bigDecimal.add(BigDecimal.valueOf(insuranceSalesInfoService.getFYC(a, 3, null)));
                            });*/
                            for (Long a:btjSalesIds) {
                               bigDecimal = bigDecimal.add(BigDecimal.valueOf(insuranceSalesInfoService.getFYC(a, 3, null)));
                            }
                            fyc = insuranceSalesInfoService.getFYC(info.getInsuranceSalesId(), 3, null);
                            sum = bigDecimal.add(BigDecimal.valueOf(fyc)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                        if (sum >= 45000) {
                            // todo 个人寿险新契约第 13 个月继续率达 85%以上;
                            result = true;
                            this.saveSalesMoveLog(info, 1);
                        }
                    }
                }
            }
        } else if (GCQCZ == salesAssess.getSalesGradeId() || CZ == salesAssess.getSalesGradeId()) {
            // 处长是否通过代理制保险营销员品质考核以及通过公司晋级培训并考试合格
            if (YES.equals(salesAssess.getCondition8()) && YES.equals(salesAssess.getCondition3())) {
                double fyc = insuranceSalesInfoService.getFYC(info.getInsuranceSalesId(), 3, info.getJoinDate());
                if (fyc >= 1000) {
                    // 育成处长数量
                    int ycCount = insuranceSalesInfoService.queryBYCCount(info.getInsuranceSalesId(), 3);
                    if (ycCount >= 3) {
                        double sum = 0;
                        // 查询直辖组员工id
                        List<Long> subordinateIds = getBeRecommendedKZSalesId(Collections.EMPTY_LIST, info.getInsuranceSalesId());
                        if (!CollectionUtils.isEmpty(subordinateIds)) {
                            BigDecimal bigDecimal = new BigDecimal(0);
                            // 统计直辖组包含自己的季度fyc
                             //TODO 扫描出bug 先作以下修改，lambda改为增强for
                            /*subordinateIds.forEach(a -> {
                                bigDecimal.add(BigDecimal.valueOf(insuranceSalesInfoService.getFYC(a, 3, null)));
                            });*/
                            for (Long a:subordinateIds) {
                                bigDecimal =bigDecimal.add(BigDecimal.valueOf(insuranceSalesInfoService.getFYC(a, 3, null)));
                            }

                            double v = insuranceSalesInfoService.getFYC(info.getInsuranceSalesId(), 3, null);
                            sum = bigDecimal.add(BigDecimal.valueOf(v)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                        if (sum >= 120000) {
                            // todo 本人直辖组寿险新契约第 13 个月继续率达 85%以上; 继续率先不计算
                            result = true;
                            this.saveSalesMoveLog(info, 1);
                        }
                    }
                }
            }
        }
//        else if(BZ == salesAssess.getSalesGradeId()){
//            // 部长考核是否通过
//            if(NO.equals(salesAssess.getCondition3()) || NO.equals(salesAssess.getCondition5()) || NO.equals(salesAssess.getCondition6()) || NO.equals(salesAssess.getCondition7())){
//                return result;
//            }
//            // 育成部长数量
//            int ycCount = insuranceSalesInfoMapper.queryBYCCount(info.getInsuranceSalesId(),2);
//            if(ycCount >= 2){
//                // todo 本人直辖寿险新契约第 13 个月继续率达 85%以上
//                // 查询前六个月fyc
//                double fyc = getFYC(info.getInsuranceSalesId(), 6, null);
//                if(fyc >= 1000){
//                    double sum = 0;
//                    // 查询直辖组员工id
//                    List<Long> subordinateIds = getSubordinate(Collections.EMPTY_LIST, info.getInsuranceSalesId());
//                    if(!CollectionUtils.isEmpty(subordinateIds)){
//                        BigDecimal bigDecimal = new BigDecimal(0);
//                        // 统计团队累计
//                        subordinateIds.forEach(a->{
//                            bigDecimal.add(new BigDecimal(this.getFYC(a, 3, null)));
//                        });
//                        sum = bigDecimal.add(new BigDecimal(this.getFYC(info.getInsuranceSalesId(), 3, null))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                    }
//                    if(sum >= 1440000){
//                        result = true;
//                        this.saveSalesMoveLog(info, 1);
//                    }
//                }
//            }
//        }
        return result;
    }

    /**
     * <p>维持考核</p>
     * 科长 处长
     *
     * @param info
     */
    private void keepInsuranceSalesInfo(InsuranceSalesInfo info) {
        if (null == info) {
            return;
        }
        // 查询考核结果
        SalesAssess salesAssess = insuranceSalesInfoService.findNewSalesAssess(info.getInsuranceSalesId(), info.getSalesGradeId());
        if (null == salesAssess) {
            return;
        }
        if (GCQKZ == info.getSalesGradeId() || KZ == info.getSalesGradeId()) {
            double fyc = insuranceSalesInfoService.getFYC(info.getInsuranceSalesId(), 3, info.getJoinDate());
            if (fyc < 1000 || NO.equals(salesAssess.getCondition2())) {
                // todo 个人保费继续率 先不计算
                this.saveSalesMoveLog(info, 2);
            }
        } else if (GCQCZ == info.getSalesGradeId() || CZ == info.getSalesGradeId()) {
            if (NO.equals(salesAssess.getCondition2())) {
                this.saveSalesMoveLog(info, 2);
            }
            double fyc = insuranceSalesInfoService.getFYC(info.getInsuranceSalesId(), 3, null);
            double sum = 0;
            int count = 0;
            // 查询直辖组员工id
            List<Long> subordinateIds = getBeRecommendedKZSalesId(Collections.EMPTY_LIST, info.getInsuranceSalesId());
            if (!CollectionUtils.isEmpty(subordinateIds)) {
                BigDecimal bigDecimal = new BigDecimal(0);
                // 统计直辖组包含自己的季度fyc
                for (Long id : subordinateIds) {
                    double subordinateFyc = insuranceSalesInfoService.getFYC(id, 3, null);
                    bigDecimal = bigDecimal.add(BigDecimal.valueOf(subordinateFyc));
                    if (subordinateFyc >= 1000) {
                        count++;
                    }
                }
                sum = bigDecimal.add(BigDecimal.valueOf(fyc)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            // 季度处长直辖组累计 FYC≥45000 元 或者 季度月均有效人力不低于 5 人
            if (sum < 45000 || count < 15) {
                // todo 本人直辖组寿险新契约第 13 个月继续率达 85%以上; 继续率先不计算
                this.saveSalesMoveLog(info, 2);
            }
        }
    }

    /**
     * <p>部长维持考核</p>
     *
     * @param info  员工信息
     * @param month 月份
     */
    private void keepBZAssess(InsuranceSalesInfo info, int month) {
        double fyc = insuranceSalesInfoService.getFYC(info.getInsuranceSalesId(), 6, info.getJoinDate());
        if (fyc < 1000) {
            this.saveSalesMoveLog(info, 2);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        Date startTime = null;
        Date endTime = null;
        try {
            if (1 == month) {
                int y = year - 1;
                startTime = sdf.parse(y + "-07-01");
                endTime = sdf.parse(y + "-12-31");
            } else if (7 == month) {
                startTime = sdf.parse(year + "-01-01");
                endTime = sdf.parse(year + "-06-30");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 之前半年达成处长人数
        int count = insuranceSalesInfoService.queryDutyCount(info.getInsuranceSalesId(), 3, startTime, endTime);
        if (count < 2) {
            this.saveSalesMoveLog(info, 2);
        }
        double sum = 0;
        int i = 0;
        // 查询所辖团队员工id
        List subordinateIds = this.getSubordinate(Collections.EMPTY_LIST, info.getInsuranceSalesId(), info.getSalesGradeId());
        if (!CollectionUtils.isEmpty(subordinateIds)) {
            BigDecimal bigDecimal = new BigDecimal(0);
            // 统计直辖组包含自己的季度fyc
            for (Object id : subordinateIds) {
                BigDecimal decimal = BigDecimal.valueOf(insuranceSalesInfoService.getFYC(Long.parseLong(String.valueOf(id)), 6, null));
               bigDecimal = bigDecimal.add(decimal);
                if (decimal.doubleValue() >= 300) {
                    i++;
                }
            }
            sum = bigDecimal.add(BigDecimal.valueOf(fyc)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        // 所辖团队半年度内累计 FYC≥180000 元  所辖团队半年度累计实动人力至少达成 36 人次
        if (sum < 180000 || i < 36) {
            // todo 个人保费继续率不得低于 85%;
            this.saveSalesMoveLog(info, 2);
        }
    }

    /**
     * <p>保存员工调整</p>
     *
     * @param info  员工信息
     * @param state 状态 1 晋升 2 降级
     */
    private void saveSalesMoveLog(InsuranceSalesInfo info, int state) {
        if (GCQKZ == info.getSalesGradeId() && 2 == state) {
        } else if (ZJ == info.getSalesGradeId() && 1 == state) {
        } else {
            SalesMoveLogs logs = new SalesMoveLogs();
            logs.setSalerId(info.getInsuranceSalesId());
            logs.setSalerName(info.getInsuranceSaler());
            logs.setPreOrgId(info.getSalesOrgId().toString());
            logs.setPreTeamId(info.getSalesTeamId().toString());
            logs.setPreRankSeqId(info.getRankSequenceId().toString());
            logs.setPreSalesGradeId(info.getSalesGradeId().toString());
            logs.setNextOrgId(info.getSalesOrgId().toString());
            logs.setNextTeamId(info.getSalesTeamId().toString());
            logs.setNextRankSeqId(info.getRankSequenceId().toString());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            instance.add(Calendar.MONTH, 1);
            Map<String, Object> day = DateUtil.getMonthFirstDayAndLastDay(dateFormat.format(instance.getTime()));
            logs.setMoveDate(day.get("firstDay").toString());
            logs.setCheckStatus("0");
            Date date = new Date();
            logs.setCreatedTime(date);
            logs.setUpdatedTime(date);
            if (1 == state) {
                logs.setYcSalerId(info.getSjSalesId());
                if (GCQKZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(KZ));
                } else if (KZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(CZ));
                } else if (GCQCZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(CZ));
                } else if (CZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(BZ));
                } else if (GCQBZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(BZ));
                } else if (BZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(ZJ));
                }
            } else if (2 == state) {
                if (ZJ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(BZ));
                } else if (BZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(GCQBZ));
                } else if (GCQBZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(CZ));
                } else if (CZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(GCQCZ));
                } else if (GCQCZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(KZ));
                } else if (KZ == info.getSalesGradeId()) {
                    logs.setNextSalesGradeId(String.valueOf(GCQKZ));
                }
            }
            insuranceSalesInfoService.insertMove(logs);
        }
    }

    private List<Long> getSubordinate(List<Long> subordinateSalesIds, Long salesId, Long salesGradeId) {
        List<Long> ids = insuranceSalesInfoService.querySubordinate(salesId, salesGradeId);
        if (!CollectionUtils.isEmpty(ids)) {
            ids.forEach(a -> {
                this.getSubordinate(subordinateSalesIds, a, salesGradeId);
            });
            subordinateSalesIds.addAll(ids);
        }
        return subordinateSalesIds;
    }

    private List<Long> getBeRecommendedKZSalesId(List<Long> btjSalesIds, Long tjSalesId) {
        List<Long> ids = insuranceSalesInfoService.queryBeRecommendedKZBySalesId(tjSalesId);
        if (!CollectionUtils.isEmpty(ids)) {
            for (Long id : ids) {
                this.getBeRecommendedKZSalesId(btjSalesIds, id);
            }
            btjSalesIds.addAll(ids);
        }
        return btjSalesIds;
    }


}
