package com.hzcf.pojo.insurance.protocol;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsProtocolRateAdjustParamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InsProtocolRateAdjustParamExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAdjustParamIdIsNull() {
            addCriterion("ADJUST_PARAM_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdIsNotNull() {
            addCriterion("ADJUST_PARAM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdEqualTo(Long value) {
            addCriterion("ADJUST_PARAM_ID =", value, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdNotEqualTo(Long value) {
            addCriterion("ADJUST_PARAM_ID <>", value, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdGreaterThan(Long value) {
            addCriterion("ADJUST_PARAM_ID >", value, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ADJUST_PARAM_ID >=", value, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdLessThan(Long value) {
            addCriterion("ADJUST_PARAM_ID <", value, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdLessThanOrEqualTo(Long value) {
            addCriterion("ADJUST_PARAM_ID <=", value, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdIn(List<Long> values) {
            addCriterion("ADJUST_PARAM_ID in", values, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdNotIn(List<Long> values) {
            addCriterion("ADJUST_PARAM_ID not in", values, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdBetween(Long value1, Long value2) {
            addCriterion("ADJUST_PARAM_ID between", value1, value2, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIdNotBetween(Long value1, Long value2) {
            addCriterion("ADJUST_PARAM_ID not between", value1, value2, "adjustParamId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIsNull() {
            addCriterion("ADJUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIsNotNull() {
            addCriterion("ADJUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustIdEqualTo(Long value) {
            addCriterion("ADJUST_ID =", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotEqualTo(Long value) {
            addCriterion("ADJUST_ID <>", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdGreaterThan(Long value) {
            addCriterion("ADJUST_ID >", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ADJUST_ID >=", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdLessThan(Long value) {
            addCriterion("ADJUST_ID <", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdLessThanOrEqualTo(Long value) {
            addCriterion("ADJUST_ID <=", value, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdIn(List<Long> values) {
            addCriterion("ADJUST_ID in", values, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotIn(List<Long> values) {
            addCriterion("ADJUST_ID not in", values, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdBetween(Long value1, Long value2) {
            addCriterion("ADJUST_ID between", value1, value2, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustIdNotBetween(Long value1, Long value2) {
            addCriterion("ADJUST_ID not between", value1, value2, "adjustId");
            return (Criteria) this;
        }

        public Criteria andAdjustNameIsNull() {
            addCriterion("ADJUST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAdjustNameIsNotNull() {
            addCriterion("ADJUST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustNameEqualTo(String value) {
            addCriterion("ADJUST_NAME =", value, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameNotEqualTo(String value) {
            addCriterion("ADJUST_NAME <>", value, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameGreaterThan(String value) {
            addCriterion("ADJUST_NAME >", value, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameGreaterThanOrEqualTo(String value) {
            addCriterion("ADJUST_NAME >=", value, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameLessThan(String value) {
            addCriterion("ADJUST_NAME <", value, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameLessThanOrEqualTo(String value) {
            addCriterion("ADJUST_NAME <=", value, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameLike(String value) {
            addCriterion("ADJUST_NAME like", value, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameNotLike(String value) {
            addCriterion("ADJUST_NAME not like", value, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameIn(List<String> values) {
            addCriterion("ADJUST_NAME in", values, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameNotIn(List<String> values) {
            addCriterion("ADJUST_NAME not in", values, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameBetween(String value1, String value2) {
            addCriterion("ADJUST_NAME between", value1, value2, "adjustName");
            return (Criteria) this;
        }

        public Criteria andAdjustNameNotBetween(String value1, String value2) {
            addCriterion("ADJUST_NAME not between", value1, value2, "adjustName");
            return (Criteria) this;
        }

        public Criteria andPProductIdIsNull() {
            addCriterion("P_PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andPProductIdIsNotNull() {
            addCriterion("P_PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPProductIdEqualTo(Long value) {
            addCriterion("P_PRODUCT_ID =", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdNotEqualTo(Long value) {
            addCriterion("P_PRODUCT_ID <>", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdGreaterThan(Long value) {
            addCriterion("P_PRODUCT_ID >", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("P_PRODUCT_ID >=", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdLessThan(Long value) {
            addCriterion("P_PRODUCT_ID <", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdLessThanOrEqualTo(Long value) {
            addCriterion("P_PRODUCT_ID <=", value, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdIn(List<Long> values) {
            addCriterion("P_PRODUCT_ID in", values, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdNotIn(List<Long> values) {
            addCriterion("P_PRODUCT_ID not in", values, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdBetween(Long value1, Long value2) {
            addCriterion("P_PRODUCT_ID between", value1, value2, "pProductId");
            return (Criteria) this;
        }

        public Criteria andPProductIdNotBetween(Long value1, Long value2) {
            addCriterion("P_PRODUCT_ID not between", value1, value2, "pProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdIsNull() {
            addCriterion("S_PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSProductIdIsNotNull() {
            addCriterion("S_PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSProductIdEqualTo(Long value) {
            addCriterion("S_PRODUCT_ID =", value, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdNotEqualTo(Long value) {
            addCriterion("S_PRODUCT_ID <>", value, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdGreaterThan(Long value) {
            addCriterion("S_PRODUCT_ID >", value, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("S_PRODUCT_ID >=", value, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdLessThan(Long value) {
            addCriterion("S_PRODUCT_ID <", value, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdLessThanOrEqualTo(Long value) {
            addCriterion("S_PRODUCT_ID <=", value, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdIn(List<Long> values) {
            addCriterion("S_PRODUCT_ID in", values, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdNotIn(List<Long> values) {
            addCriterion("S_PRODUCT_ID not in", values, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdBetween(Long value1, Long value2) {
            addCriterion("S_PRODUCT_ID between", value1, value2, "sProductId");
            return (Criteria) this;
        }

        public Criteria andSProductIdNotBetween(Long value1, Long value2) {
            addCriterion("S_PRODUCT_ID not between", value1, value2, "sProductId");
            return (Criteria) this;
        }

        public Criteria andFixedRateIsNull() {
            addCriterion("FIXED_RATE is null");
            return (Criteria) this;
        }

        public Criteria andFixedRateIsNotNull() {
            addCriterion("FIXED_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andFixedRateEqualTo(BigDecimal value) {
            addCriterion("FIXED_RATE =", value, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateNotEqualTo(BigDecimal value) {
            addCriterion("FIXED_RATE <>", value, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateGreaterThan(BigDecimal value) {
            addCriterion("FIXED_RATE >", value, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FIXED_RATE >=", value, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateLessThan(BigDecimal value) {
            addCriterion("FIXED_RATE <", value, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FIXED_RATE <=", value, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateIn(List<BigDecimal> values) {
            addCriterion("FIXED_RATE in", values, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateNotIn(List<BigDecimal> values) {
            addCriterion("FIXED_RATE not in", values, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIXED_RATE between", value1, value2, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andFixedRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIXED_RATE not between", value1, value2, "fixedRate");
            return (Criteria) this;
        }

        public Criteria andIsExceptionIsNull() {
            addCriterion("IS_EXCEPTION is null");
            return (Criteria) this;
        }

        public Criteria andIsExceptionIsNotNull() {
            addCriterion("IS_EXCEPTION is not null");
            return (Criteria) this;
        }

        public Criteria andIsExceptionEqualTo(String value) {
            addCriterion("IS_EXCEPTION =", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionNotEqualTo(String value) {
            addCriterion("IS_EXCEPTION <>", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionGreaterThan(String value) {
            addCriterion("IS_EXCEPTION >", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXCEPTION >=", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionLessThan(String value) {
            addCriterion("IS_EXCEPTION <", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionLessThanOrEqualTo(String value) {
            addCriterion("IS_EXCEPTION <=", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionLike(String value) {
            addCriterion("IS_EXCEPTION like", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionNotLike(String value) {
            addCriterion("IS_EXCEPTION not like", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionIn(List<String> values) {
            addCriterion("IS_EXCEPTION in", values, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionNotIn(List<String> values) {
            addCriterion("IS_EXCEPTION not in", values, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionBetween(String value1, String value2) {
            addCriterion("IS_EXCEPTION between", value1, value2, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionNotBetween(String value1, String value2) {
            addCriterion("IS_EXCEPTION not between", value1, value2, "isException");
            return (Criteria) this;
        }

        public Criteria andChangeRateIsNull() {
            addCriterion("CHANGE_RATE is null");
            return (Criteria) this;
        }

        public Criteria andChangeRateIsNotNull() {
            addCriterion("CHANGE_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andChangeRateEqualTo(BigDecimal value) {
            addCriterion("CHANGE_RATE =", value, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateNotEqualTo(BigDecimal value) {
            addCriterion("CHANGE_RATE <>", value, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateGreaterThan(BigDecimal value) {
            addCriterion("CHANGE_RATE >", value, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CHANGE_RATE >=", value, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateLessThan(BigDecimal value) {
            addCriterion("CHANGE_RATE <", value, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CHANGE_RATE <=", value, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateIn(List<BigDecimal> values) {
            addCriterion("CHANGE_RATE in", values, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateNotIn(List<BigDecimal> values) {
            addCriterion("CHANGE_RATE not in", values, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHANGE_RATE between", value1, value2, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHANGE_RATE not between", value1, value2, "changeRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateIsNull() {
            addCriterion("CHANGE_SUBJOIN_RATE is null");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateIsNotNull() {
            addCriterion("CHANGE_SUBJOIN_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateEqualTo(BigDecimal value) {
            addCriterion("CHANGE_SUBJOIN_RATE =", value, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateNotEqualTo(BigDecimal value) {
            addCriterion("CHANGE_SUBJOIN_RATE <>", value, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateGreaterThan(BigDecimal value) {
            addCriterion("CHANGE_SUBJOIN_RATE >", value, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CHANGE_SUBJOIN_RATE >=", value, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateLessThan(BigDecimal value) {
            addCriterion("CHANGE_SUBJOIN_RATE <", value, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CHANGE_SUBJOIN_RATE <=", value, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateIn(List<BigDecimal> values) {
            addCriterion("CHANGE_SUBJOIN_RATE in", values, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateNotIn(List<BigDecimal> values) {
            addCriterion("CHANGE_SUBJOIN_RATE not in", values, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHANGE_SUBJOIN_RATE between", value1, value2, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andChangeSubjoinRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHANGE_SUBJOIN_RATE not between", value1, value2, "changeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateIsNull() {
            addCriterion("ALL_CHANGE_RATE is null");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateIsNotNull() {
            addCriterion("ALL_CHANGE_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateEqualTo(BigDecimal value) {
            addCriterion("ALL_CHANGE_RATE =", value, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateNotEqualTo(BigDecimal value) {
            addCriterion("ALL_CHANGE_RATE <>", value, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateGreaterThan(BigDecimal value) {
            addCriterion("ALL_CHANGE_RATE >", value, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ALL_CHANGE_RATE >=", value, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateLessThan(BigDecimal value) {
            addCriterion("ALL_CHANGE_RATE <", value, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ALL_CHANGE_RATE <=", value, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateIn(List<BigDecimal> values) {
            addCriterion("ALL_CHANGE_RATE in", values, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateNotIn(List<BigDecimal> values) {
            addCriterion("ALL_CHANGE_RATE not in", values, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALL_CHANGE_RATE between", value1, value2, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALL_CHANGE_RATE not between", value1, value2, "allChangeRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateIsNull() {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE is null");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateIsNotNull() {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateEqualTo(BigDecimal value) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE =", value, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateNotEqualTo(BigDecimal value) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE <>", value, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateGreaterThan(BigDecimal value) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE >", value, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE >=", value, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateLessThan(BigDecimal value) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE <", value, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE <=", value, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateIn(List<BigDecimal> values) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE in", values, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateNotIn(List<BigDecimal> values) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE not in", values, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE between", value1, value2, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andAllChangeSubjoinRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALL_CHANGE_SUBJOIN_RATE not between", value1, value2, "allChangeSubjoinRate");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("UPDATE_BY like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("UPDATE_BY not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}