package com.hzcf.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzcf.core.mapper.*;
import com.hzcf.core.service.InsProtocolPromotionService;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.promotion.*;
import com.hzcf.pojo.insurance.protocol.InsProtocol;
import com.hzcf.pojo.insurance.protocol.InsProtocolCost;
import com.hzcf.pojo.insurance.protocol.InsProtocolProduct;
import com.hzcf.pojo.product.ProductsCommissionRatio;
import com.hzcf.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InsProtocolPromotionServiceImpl implements InsProtocolPromotionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InsProtocolPromotionMapper insProtocolPromotionMapper;
    @Autowired
    private InsProtocolPromotionOrgMapper insProtocolPromotionOrgMapper;
    @Autowired
    private InsProtocolPromotionProductMapper insProtocolPromotionProductMapper;
    @Autowired
    private SalesOrgInfoMapper salesOrgInfoMapper;
    @Autowired
    private ProductsCommissionRatioMapper productsCommissionRatioMapper;
    @Autowired
    private InsProtocolProductMapper insProtocolProductMapper;
    @Autowired
    private InsPolicyInsuredPersonMapper insPolicyInsuredPersonMapper;
    @Autowired
    private InsProtocolMapper insProtocolMapper;
    @Autowired
    private InsProtocolCostMapper insProtocolCostMapper;

    private static final String GB = "规保";
    private static final String BB = "标保";

    @Override
    public void saveInsProtocolPromotion(InsProtocolPromotionPojo insProtocolPromotionPojo) {
        if (null != insProtocolPromotionPojo) {
            InsProtocolPromotion insProtocolPromotion = new InsProtocolPromotion();
            BeanUtils.copyProperties(insProtocolPromotionPojo, insProtocolPromotion);
            if (!insProtocolPromotionPojo.getSettlementRate().isEmpty()) {
                insProtocolPromotion.setSettlementCycleRate(JSON.toJSONString(insProtocolPromotionPojo.getSettlementRate()));
            }
            List<InsProtocolPromotionRate> commonRate = insProtocolPromotionPojo.getCommonRate();
            if (null != commonRate && !commonRate.isEmpty()) {
                insProtocolPromotion.setCommonCycleRate(JSON.toJSONString(insProtocolPromotionPojo.getCommonRate()));
            }
            List<InsProtocolPromotionOrg> orgScope = insProtocolPromotionPojo.getOrgScope();
            insProtocolPromotion.setOrgScope(Byte.valueOf(insProtocolPromotionPojo.getOrgScopeStr()));

            List<InsProtocolPromotionProduct> productScope = insProtocolPromotionPojo.getProductScope();
            if (productScope.isEmpty()) {
                insProtocolPromotion.setProductScope(Byte.valueOf("0"));
            } else {
                insProtocolPromotion.setProductScope(Byte.valueOf("1"));
            }
            insProtocolPromotion.setDeleted(Byte.valueOf("0"));
            insProtocolPromotion.setState(Byte.valueOf("1"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                insProtocolPromotion.setStartTime(format.parse(insProtocolPromotionPojo.getStartTimeStr()));
                insProtocolPromotion.setEndTime(format.parse(insProtocolPromotionPojo.getEndTimeStr()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 先插入推动奖励，之后再插入相关机构和相关产品
            insProtocolPromotionMapper.insertPromotion(insProtocolPromotion);
            try {
                if (!CollectionUtils.isEmpty(insProtocolPromotionPojo.getOrgScope())) {
                    ArrayList<InsProtocolPromotionOrg> orgList = Lists.newArrayListWithExpectedSize(orgScope.size());
                    orgScope.forEach(a -> {
                        InsProtocolPromotionOrg org = new InsProtocolPromotionOrg();
                        org.setPromotionId(insProtocolPromotion.getId());
                        org.setDeleted(Byte.valueOf("0"));
                        org.setSalesOrgId(a.getSalesOrgId());
                        orgList.add(org);
                    });
                    insProtocolPromotionOrgMapper.insertPromotionOrgList(orgList);
                }
                if (!Byte.valueOf("0").equals(insProtocolPromotion.getProductScope())) {
                    ArrayList<InsProtocolPromotionProduct> productList = Lists.newArrayListWithExpectedSize(productScope.size());
                    productScope.forEach(a -> {
                        InsProtocolPromotionProduct product = new InsProtocolPromotionProduct();
                        product.setPromotionId(insProtocolPromotion.getId());
                        product.setDeleted(Byte.valueOf("0"));
                        product.setProductId(a.getProductId());
                        productList.add(product);
                    });
                    insProtocolPromotionProductMapper.insertPromotionProductList(productList);
                }
            } catch (Exception e) {
                logger.error("新增寿险协议业务推动异常" + e);
                insProtocolPromotionMapper.deletePromotion(insProtocolPromotion.getLastModifier(), insProtocolPromotion.getId());
            }
        }
    }

    @Override
    public boolean updateInsProtocolPromotion(InsProtocolPromotionPojo insProtocolPromotionPojo) {
        boolean result = false;
        if (null != insProtocolPromotionPojo) {
            InsProtocolPromotion insProtocolPromotion = new InsProtocolPromotion();
            BeanUtils.copyProperties(insProtocolPromotionPojo, insProtocolPromotion);
            insProtocolPromotion.setSettlementCycleRate(JSON.toJSONString(insProtocolPromotionPojo.getSettlementRate()));
            insProtocolPromotion.setCommonCycleRate(JSON.toJSONString(insProtocolPromotionPojo.getCommonRate()));
            List<InsProtocolPromotionOrg> orgScope = insProtocolPromotionPojo.getOrgScope();
            insProtocolPromotion.setOrgScope(Byte.valueOf(insProtocolPromotionPojo.getOrgScopeStr()));
            List<InsProtocolPromotionProduct> productScope = insProtocolPromotionPojo.getProductScope();
            if (productScope.isEmpty()) {
                insProtocolPromotion.setProductScope(Byte.valueOf("0"));
            } else {
                insProtocolPromotion.setProductScope(Byte.valueOf("1"));
            }
            // 先更新推动奖励，之后再更新相关机构和相关产品
            if (insProtocolPromotionMapper.updatePromotion(insProtocolPromotion) > 0) {
                result = true;
            }
            if (result) {
                if (!CollectionUtils.isEmpty(insProtocolPromotionPojo.getOrgScope())) {
                    ArrayList<InsProtocolPromotionOrg> orgList = Lists.newArrayListWithExpectedSize(orgScope.size());
                    ArrayList<Long> orgTemList = Lists.newArrayListWithExpectedSize(orgScope.size());
                    List<Long> orgIds = insProtocolPromotionOrgMapper.queryPromotionOrgIds(insProtocolPromotion.getId());
                    orgScope.forEach(a -> {
                        if (null == a.getId()) {
                            InsProtocolPromotionOrg org = new InsProtocolPromotionOrg();
                            org.setPromotionId(insProtocolPromotion.getId());
                            org.setDeleted(Byte.valueOf("0"));
                            org.setSalesOrgId(a.getSalesOrgId());
                            orgList.add(org);
                        } else {
                            orgTemList.add(a.getId());
                        }
                    });
                    orgIds.removeAll(orgTemList);
                    if (!orgIds.isEmpty()) {
                        insProtocolPromotionOrgMapper.deletePromotionOrg(orgIds);
                    }
                    if (!orgList.isEmpty()) {
                        insProtocolPromotionOrgMapper.insertPromotionOrgList(orgList);
                    }
                }
                if (!Byte.valueOf("0").equals(insProtocolPromotion.getProductScope())) {
                    ArrayList<InsProtocolPromotionProduct> productList = Lists.newArrayListWithExpectedSize(productScope.size());
                    ArrayList<Long> productTemList = Lists.newArrayListWithExpectedSize(orgScope.size());
                    List<Long> longList = insProtocolPromotionProductMapper.queryPromotionProductIds(insProtocolPromotion.getId());
                    productScope.forEach(a -> {
                        if (null == a.getId()) {
                            InsProtocolPromotionProduct product = new InsProtocolPromotionProduct();
                            product.setPromotionId(insProtocolPromotion.getId());
                            product.setDeleted(Byte.valueOf("0"));
                            product.setProductId(a.getProductId());
                            productList.add(product);
                        } else {
                            productTemList.add(a.getId());
                        }
                    });
                    longList.removeAll(productTemList);
                    if (!longList.isEmpty()) {
                        insProtocolPromotionProductMapper.deletePromotionProduct(longList);
                    }
                    if (!productList.isEmpty()) {
                        insProtocolPromotionProductMapper.insertPromotionProductList(productList);
                    }
                }
            }

        }
        return result;
    }

    @Override
    public boolean updatePromotionState(Byte state, String lastModifier, Long id) {
        return insProtocolPromotionMapper.updatePromotionState(state, lastModifier, id) > 0;
    }

    @Override
    public boolean deletePromotion(String lastModifier, Long id) {
        return insProtocolPromotionMapper.deletePromotion(lastModifier, id) > 0;
    }

    @Override
    public InsProtocolPromotionPojo queryPromotion(Long id) {
        InsProtocolPromotion insProtocolPromotion = insProtocolPromotionMapper.queryPromotion(id);
        if (null == insProtocolPromotion) {
            return null;
        }
        InsProtocolPromotionPojo pojo = new InsProtocolPromotionPojo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pojo.setStartTimeStr(dateFormat.format(insProtocolPromotion.getStartTime()));
        pojo.setEndTimeStr(dateFormat.format(insProtocolPromotion.getEndTime()));
        BeanUtils.copyProperties(insProtocolPromotion, pojo);

        if (Byte.valueOf("0").equals(insProtocolPromotion.getOrgScope())) {
            pojo.setOrgScopeStr("全部");
        } else {
            List<InsProtocolPromotionOrg> orgs = insProtocolPromotionOrgMapper.queryPromotionOrgs(insProtocolPromotion.getId());
            StringBuffer buffer = new StringBuffer();
            orgs.forEach(b -> {
                Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
                map.put("salesOrgId", b.getSalesOrgId());
                SalesOrgInfo orgInfo = salesOrgInfoMapper.selectById(map);
                if (null != orgInfo) {
                    buffer.append(orgInfo.getSalesOrgName());
                    buffer.append(",");
                }
            });
            String s = buffer.toString();
            if (StringUtils.isNotBlank(s)) {
                s = s.substring(0, s.lastIndexOf(","));
            }
            pojo.setOrgScopeStr(s);
        }
        if (Byte.valueOf("0").equals(insProtocolPromotion.getProductScope())) {
            pojo.setProductScopeStr("全部");
        } else {
            List<InsProtocolPromotionProduct> productList = insProtocolPromotionProductMapper.queryPromotionProducts(insProtocolPromotion.getId());
            StringBuffer buffer = new StringBuffer();
            productList.forEach(c -> {
                ProductsCommissionRatio productsCommissionRatio = productsCommissionRatioMapper.selectByPrimaryKey(c.getProductId());
                if (null != productsCommissionRatio) {
                    buffer.append(productsCommissionRatio.getProductsName());
                    buffer.append(",");
                }
                String s = buffer.toString();
                if (StringUtils.isNotBlank(s)) {
                    s = s.substring(0, s.lastIndexOf(","));
                }
                pojo.setProductScopeStr(s);
            });
        }

        List<InsProtocolPromotionRate> settlementCycleRate = JSON.parseArray(insProtocolPromotion.getSettlementCycleRate(), InsProtocolPromotionRate.class);
        List<InsProtocolPromotionRate> commonCycleRate = JSON.parseArray(insProtocolPromotion.getCommonCycleRate(), InsProtocolPromotionRate.class);
        pojo.setSettlementRate(settlementCycleRate);
        pojo.setCommonRate(commonCycleRate);

        return pojo;
    }

    @Override
    public List<InsProtocolPromotionListPojo> queryPromotions(Long protocolId) {
        List<InsProtocolPromotion> protocolPromotionList = insProtocolPromotionMapper.queryPromotions(protocolId);
        if (protocolPromotionList.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList<InsProtocolPromotionListPojo> pojos = Lists.newArrayListWithExpectedSize(protocolPromotionList.size());
        protocolPromotionList.forEach(a -> {
            InsProtocolPromotionListPojo pojo = new InsProtocolPromotionListPojo();
            pojo.setId(a.getId());
            pojo.setPromotionName(a.getPromotionName());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pojo.setStartTime(dateFormat.format(a.getStartTime()));
            pojo.setEndTime(dateFormat.format(a.getEndTime()));
            if (1 == a.getDeleted()) {
                pojo.setState("无效");
            } else {
                if (1 == a.getState()) {
                    pojo.setState("有效");
                } else if (0 == a.getState()) {
                    pojo.setState("失效");
                }
            }
            if (Byte.valueOf("0").equals(a.getOrgScope())) {
                pojo.setOrgScope("全部");
            } else {
                List<InsProtocolPromotionOrg> orgs = insProtocolPromotionOrgMapper.queryPromotionOrgs(a.getId());
                StringBuffer buffer = new StringBuffer();
                orgs.forEach(b -> {
                    Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
                    map.put("salesOrgId", b.getSalesOrgId());
                    SalesOrgInfo orgInfo = salesOrgInfoMapper.selectById(map);
                    if (null != orgInfo) {
                        buffer.append(orgInfo.getSalesOrgName());
                        buffer.append(",");
                    }
                });
                String s = buffer.toString();
                if (StringUtils.isNotBlank(s)) {
                    s = s.substring(0, s.lastIndexOf(","));
                }
                pojo.setOrgScope(s);
            }
            if (Byte.valueOf("0").equals(a.getProductScope())) {
                pojo.setProductScope("全部");
            } else {
                List<InsProtocolPromotionProduct> productList = insProtocolPromotionProductMapper.queryPromotionProducts(a.getId());
                StringBuffer buffer = new StringBuffer();
                productList.forEach(c -> {
                    ProductsCommissionRatio productsCommissionRatio = productsCommissionRatioMapper.selectByPrimaryKey(c.getProductId());
                    if (null != productsCommissionRatio) {
                        buffer.append(productsCommissionRatio.getProductsName());
                        buffer.append(",");
                    }
                    String s = buffer.toString();
                    if (StringUtils.isNotBlank(s)) {
                        s = s.substring(0, s.lastIndexOf(","));
                    }
                    pojo.setProductScope(s);
                });
            }
            pojos.add(pojo);
        });
        return pojos;
    }

    @Override
    public List<Map> findProductsByProtocolId(Long protocolId) {
        if (null == protocolId) {
            return Collections.emptyList();
        }
        List<InsProtocolProduct> insProtocolProducts = insProtocolProductMapper.queryInsProtocolProductByProtocolId(protocolId);
        if (insProtocolProducts.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Map> resultList = Lists.newArrayListWithExpectedSize(insProtocolProducts.size());
        insProtocolProducts.forEach(a -> {
            HashMap<String, Object> map = new HashMap<>();
            ProductsCommissionRatio ratio = productsCommissionRatioMapper.selectByPrimaryKey(a.getProductId());
            if (null != ratio) {
                map.put("productCode", ratio.getProductsCode());
                map.put("productName", ratio.getProductsName());
                map.put("productId", a.getProductId());
            }
            resultList.add(map);

        });
        return resultList;
    }

    @Override
    public Long findSalesOrgIdByProtocolId(Long protocolId) {
        InsProtocol insProtocol = insProtocolMapper.selectByPrimaryKey(protocolId);
        if (null != insProtocol) {
            return insProtocol.getSalesOrgId();
        }
        return null;
    }

    @Override
    public void findPromoteCost(Long protocolId) {
        InsProtocol insProtocol = insProtocolMapper.selectByPrimaryKey(protocolId);
        double sum = 0D;
        if (null == insProtocol) {
            return;
        }
        // 寿险协议下的推动协议
        List<InsProtocolPromotion> promotions = insProtocolPromotionMapper.queryNotDeletedPromotions(protocolId);
        if (CollectionUtils.isEmpty(promotions)) {
            return;
        }
        // 当前月份
        Calendar instance = Calendar.getInstance();
        int month = instance.get(Calendar.MONTH) + 1;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            instance.setTime(new Date());
            instance.add(Calendar.MONTH, -1);
            Map<String, Object> day = DateUtil.getMonthFirstDayAndLastDay(sdf.format(instance.getTime()));
            String firstDay = day.get("firstDay").toString() + " 00:00:00";
            String lastDay = day.get("lastDay").toString() + " 23:59:59";

            for (InsProtocolPromotion promotion : promotions) {
                // 推动协议下的产品和机构
                List<Long> orgIdList = insProtocolPromotionOrgMapper.queryOrgIdsByPromotionId(promotion.getId());
                List<Long> productIdList = Collections.emptyList();

                if (1 == promotion.getProductScope()) {
                    productIdList = insProtocolPromotionProductMapper.queryProductIdsByPromotionId(promotion.getId());
                } else if (0 == promotion.getProductScope()) {
                    productIdList = insProtocolProductMapper.queryProductIdsByProtocolId(protocolId);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // 根据产品和机构查询上个月全部保费、产品、机构、外部推动费率
                List<Map<String, Object>> list = insPolicyInsuredPersonMapper.queryTotalPremiumAndOutCommissionCoefficient(dateFormat.parse(firstDay), dateFormat.parse(lastDay), promotion.getStartTime(), promotion.getEndTime(), productIdList, orgIdList);
                if (!CollectionUtils.isEmpty(list)) {
                    // 月推动费用
                    double countSum = this.countSum(promotion, list, month);
                    sum = BigDecimal.valueOf(sum).add(BigDecimal.valueOf(countSum)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
                if (null != promotion.getCommonCycle()) {
                    int year = instance.get(Calendar.YEAR);
                    String endDay = day.get("lastDay").toString() + " 23:59:59";
                    if (1 == month) {
                        // 季度通算
                        if (1 == promotion.getCommonCycle()) {
                            String startDay = (year - 1) + "-10-01 00:00:00";
                            List<Map<String, Object>> commonCycleList = insPolicyInsuredPersonMapper.queryTotalPremiumAndOutCommissionCoefficient(dateFormat.parse(startDay), dateFormat.parse(endDay), promotion.getStartTime(), promotion.getEndTime(), productIdList, orgIdList);
                            sum = BigDecimal.valueOf(sum).add(new BigDecimal(countSum(promotion, commonCycleList, null))).doubleValue();
                        }
                        // 半年通算
                        if (2 == promotion.getCommonCycle()) {
                            String startDay = (year - 1) + "-07-01 00:00:00";
                            List<Map<String, Object>> commonCycleList = insPolicyInsuredPersonMapper.queryTotalPremiumAndOutCommissionCoefficient(dateFormat.parse(startDay), dateFormat.parse(endDay), promotion.getStartTime(), promotion.getEndTime(), productIdList, orgIdList);
                            sum = BigDecimal.valueOf(sum).add(new BigDecimal(countSum(promotion, commonCycleList, null))).doubleValue();
                        }
                        // 年通算
                        if (3 == promotion.getCommonCycle()) {
                            String startDay = (year - 1) + "-01-01 00:00:00";
                            List<Map<String, Object>> commonCycleList = insPolicyInsuredPersonMapper.queryTotalPremiumAndOutCommissionCoefficient(dateFormat.parse(startDay), dateFormat.parse(endDay), promotion.getStartTime(), promotion.getEndTime(), productIdList, orgIdList);
                            sum = BigDecimal.valueOf(sum).add(new BigDecimal(countSum(promotion, commonCycleList, null))).doubleValue();
                        }
                    } else if (4 == month) {
                        // 季度通算
                        if (1 == promotion.getCommonCycle()) {
                            String startDay = year + "-01-01 00:00:00";
                            List<Map<String, Object>> commonCycleList = insPolicyInsuredPersonMapper.queryTotalPremiumAndOutCommissionCoefficient(dateFormat.parse(startDay), dateFormat.parse(endDay), promotion.getStartTime(), promotion.getEndTime(), productIdList, orgIdList);
                            sum = BigDecimal.valueOf(sum).add(new BigDecimal(countSum(promotion, commonCycleList, null))).doubleValue();
                        }
                    } else if (7 == month) {
                        // 季度通算
                        if (1 == promotion.getCommonCycle()) {
                            String startDay = year + "-04-01 00:00:00";
                            List<Map<String, Object>> commonCycleList = insPolicyInsuredPersonMapper.queryTotalPremiumAndOutCommissionCoefficient(dateFormat.parse(startDay), dateFormat.parse(endDay), promotion.getStartTime(), promotion.getEndTime(), productIdList, orgIdList);
                            sum = BigDecimal.valueOf(sum).add(new BigDecimal(countSum(promotion, commonCycleList, null))).doubleValue();
                        }
                        // 半年通算
                        if (2 == promotion.getCommonCycle()) {
                            String startDay = year + "-01-01 00:00:00";
                            List<Map<String, Object>> commonCycleList = insPolicyInsuredPersonMapper.queryTotalPremiumAndOutCommissionCoefficient(dateFormat.parse(startDay), dateFormat.parse(endDay), promotion.getStartTime(), promotion.getEndTime(), productIdList, orgIdList);
                            sum = BigDecimal.valueOf(sum).add(new BigDecimal(countSum(promotion, commonCycleList, null))).doubleValue();
                        }
                    } else if (10 == month) {
                        // 季度通算
                        if (1 == promotion.getCommonCycle()) {
                            String startDay = year + "-07-01 00:00:00";
                            List<Map<String, Object>> commonCycleList = insPolicyInsuredPersonMapper.queryTotalPremiumAndOutCommissionCoefficient(dateFormat.parse(startDay), dateFormat.parse(lastDay), promotion.getStartTime(), promotion.getEndTime(), productIdList, orgIdList);
                            sum = BigDecimal.valueOf(sum).add(new BigDecimal(countSum(promotion, commonCycleList, null))).doubleValue();
                        }
                    }
                }

            }

            InsProtocolCost insProtocolCost = new InsProtocolCost();
            insProtocolCost.setOrgId(insProtocol.getSalesOrgId());
            insProtocolCost.setProtocolId(insProtocol.getProtocolId());
            insProtocolCost.setCostType("3");
            insProtocolCost.setCostTotal(BigDecimal.valueOf(sum).multiply(new BigDecimal(100)));
            insProtocolCost.setCalculationEndDate(sdf.parse(lastDay));
            insProtocolCost.setCreateTime(new Date());
            insProtocolCostMapper.insert(insProtocolCost);

        } catch (ParseException e) {
            logger.error("日期转换异常:" + e);
        }
    }

    private double countSum(InsProtocolPromotion promotion, List<Map<String, Object>> list, Integer month) {
        double sum = 0D;
        for (Map<String, Object> map : list) {
            double premium = Double.parseDouble(map.get("TOTAL_PREMIUM").toString());
            double outStandardCommissionCoefficient = Double.parseDouble(map.get("OUT_STANDARD_COMMISSION_COEFFICIENT").toString());
            BigDecimal totalPremium = BigDecimal.valueOf(premium);
            // 计算标保
            if (1 == promotion.getRateType()) {
                totalPremium = totalPremium.multiply(BigDecimal.valueOf(outStandardCommissionCoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            // 每月结算
            if (null != month && 0 == promotion.getSettlementCycle()) {
                List<InsProtocolPromotionRate> settlementCycleRateList = JSON.parseArray(promotion.getSettlementCycleRate(), InsProtocolPromotionRate.class);
                sum = getSum(promotion, sum, totalPremium.doubleValue(), settlementCycleRateList);
            }
            // 通算
            if (null == month && null != promotion.getCommonCycle()) {
                List<InsProtocolPromotionRate> commonCycleRateList = JSON.parseArray(promotion.getSettlementCycleRate(), InsProtocolPromotionRate.class);
                sum = getSum(promotion, sum, totalPremium.doubleValue(), commonCycleRateList);
            }
        }

        return sum;
    }

    private double getSum(InsProtocolPromotion promotion, double sum, double totalPremium, List<InsProtocolPromotionRate> rateList) {
        BigDecimal decimal = new BigDecimal(100);
        for (InsProtocolPromotionRate promotionRate : rateList) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            try {
                if (0 == promotion.getRateType()) {
                    String allowanceFormula = promotionRate.getAllowanceFormula();
                    String s1 = allowanceFormula.substring(0, allowanceFormula.indexOf(GB) + 2);
                    String s2 = allowanceFormula.substring(allowanceFormula.indexOf(GB));
                    String judgement1 = engine.eval(s1.replace(GB, Double.toString(totalPremium))).toString();
                    String judgement2 = engine.eval(s2.replace(GB, Double.toString(totalPremium))).toString();
                    if (Boolean.parseBoolean(judgement1) && Boolean.parseBoolean(judgement2)) {
                        BigDecimal divide = new BigDecimal(promotionRate.getRate()).divide(decimal, 4, BigDecimal.ROUND_HALF_UP);
                        BigDecimal multiply = BigDecimal.valueOf(totalPremium).multiply(divide);
                        sum = BigDecimal.valueOf(sum).add(multiply).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                } else if (1 == promotion.getRateType()) {
                    String allowanceFormula = promotionRate.getAllowanceFormula();
                    String s1 = allowanceFormula.substring(0, allowanceFormula.indexOf(BB) + 2);
                    String s2 = allowanceFormula.substring(allowanceFormula.indexOf(BB));
                    String judgement1 = engine.eval(s1.replace(BB, Double.toString(totalPremium))).toString();
                    String judgement2 = engine.eval(s2.replace(BB, Double.toString(totalPremium))).toString();
                    if (Boolean.parseBoolean(judgement1) && Boolean.parseBoolean(judgement2)) {
                        BigDecimal divide = new BigDecimal(promotionRate.getRate()).divide(decimal, 4, BigDecimal.ROUND_HALF_UP);
                        BigDecimal multiply = BigDecimal.valueOf(totalPremium).multiply(divide);
                        sum = BigDecimal.valueOf(sum).add(multiply).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                }
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

	@Override
	public void testCalculatePromoteCost(String date) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
			c.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        c.add(Calendar.MONTH, -1);
        Date month = c.getTime();
        List<Long> protocolIds = insProtocolMapper.queryValidProtocolId(month);
        if (CollectionUtils.isEmpty(protocolIds)) {
            return;
        }
        protocolIds.forEach(a -> {
            this.findPromoteCost(a);
        });
	}

}
