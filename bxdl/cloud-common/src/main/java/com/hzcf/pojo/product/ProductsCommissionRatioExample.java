package com.hzcf.pojo.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProductsCommissionRatioExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductsCommissionRatioExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andProductsRatioIdIsNull() {
            addCriterion("PRODUCTS_RATIO_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdIsNotNull() {
            addCriterion("PRODUCTS_RATIO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdEqualTo(Long value) {
            addCriterion("PRODUCTS_RATIO_ID =", value, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdNotEqualTo(Long value) {
            addCriterion("PRODUCTS_RATIO_ID <>", value, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdGreaterThan(Long value) {
            addCriterion("PRODUCTS_RATIO_ID >", value, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCTS_RATIO_ID >=", value, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdLessThan(Long value) {
            addCriterion("PRODUCTS_RATIO_ID <", value, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCTS_RATIO_ID <=", value, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdIn(List<Long> values) {
            addCriterion("PRODUCTS_RATIO_ID in", values, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdNotIn(List<Long> values) {
            addCriterion("PRODUCTS_RATIO_ID not in", values, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdBetween(Long value1, Long value2) {
            addCriterion("PRODUCTS_RATIO_ID between", value1, value2, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andProductsRatioIdNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCTS_RATIO_ID not between", value1, value2, "productsRatioId");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("CREATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("CREATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("CREATED_BY =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("CREATED_BY <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("CREATED_BY >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATED_BY >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("CREATED_BY <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("CREATED_BY <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("CREATED_BY like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("CREATED_BY not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("CREATED_BY in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("CREATED_BY not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("CREATED_BY between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("CREATED_BY not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("CREATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("CREATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATED_TIME >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATED_TIME in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATED_TIME not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATED_TIME between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATED_TIME not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNull() {
            addCriterion("UPDATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNotNull() {
            addCriterion("UPDATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByEqualTo(String value) {
            addCriterion("UPDATED_BY =", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotEqualTo(String value) {
            addCriterion("UPDATED_BY <>", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThan(String value) {
            addCriterion("UPDATED_BY >", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATED_BY >=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThan(String value) {
            addCriterion("UPDATED_BY <", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThanOrEqualTo(String value) {
            addCriterion("UPDATED_BY <=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLike(String value) {
            addCriterion("UPDATED_BY like", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotLike(String value) {
            addCriterion("UPDATED_BY not like", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIn(List<String> values) {
            addCriterion("UPDATED_BY in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotIn(List<String> values) {
            addCriterion("UPDATED_BY not in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByBetween(String value1, String value2) {
            addCriterion("UPDATED_BY between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotBetween(String value1, String value2) {
            addCriterion("UPDATED_BY not between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("UPDATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("UPDATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATED_TIME =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATED_TIME <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("UPDATED_TIME >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATED_TIME >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterionForJDBCDate("UPDATED_TIME <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATED_TIME <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATED_TIME in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATED_TIME not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATED_TIME between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATED_TIME not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdIsNull() {
            addCriterion("INSURANCE_PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdIsNotNull() {
            addCriterion("INSURANCE_PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdEqualTo(Long value) {
            addCriterion("INSURANCE_PRODUCT_ID =", value, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdNotEqualTo(Long value) {
            addCriterion("INSURANCE_PRODUCT_ID <>", value, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdGreaterThan(Long value) {
            addCriterion("INSURANCE_PRODUCT_ID >", value, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INSURANCE_PRODUCT_ID >=", value, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdLessThan(Long value) {
            addCriterion("INSURANCE_PRODUCT_ID <", value, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdLessThanOrEqualTo(Long value) {
            addCriterion("INSURANCE_PRODUCT_ID <=", value, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdIn(List<Long> values) {
            addCriterion("INSURANCE_PRODUCT_ID in", values, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdNotIn(List<Long> values) {
            addCriterion("INSURANCE_PRODUCT_ID not in", values, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdBetween(Long value1, Long value2) {
            addCriterion("INSURANCE_PRODUCT_ID between", value1, value2, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductIdNotBetween(Long value1, Long value2) {
            addCriterion("INSURANCE_PRODUCT_ID not between", value1, value2, "insuranceProductId");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeIsNull() {
            addCriterion("INSURANCE_PRODUCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeIsNotNull() {
            addCriterion("INSURANCE_PRODUCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeEqualTo(String value) {
            addCriterion("INSURANCE_PRODUCT_CODE =", value, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeNotEqualTo(String value) {
            addCriterion("INSURANCE_PRODUCT_CODE <>", value, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeGreaterThan(String value) {
            addCriterion("INSURANCE_PRODUCT_CODE >", value, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("INSURANCE_PRODUCT_CODE >=", value, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeLessThan(String value) {
            addCriterion("INSURANCE_PRODUCT_CODE <", value, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeLessThanOrEqualTo(String value) {
            addCriterion("INSURANCE_PRODUCT_CODE <=", value, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeLike(String value) {
            addCriterion("INSURANCE_PRODUCT_CODE like", value, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeNotLike(String value) {
            addCriterion("INSURANCE_PRODUCT_CODE not like", value, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeIn(List<String> values) {
            addCriterion("INSURANCE_PRODUCT_CODE in", values, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeNotIn(List<String> values) {
            addCriterion("INSURANCE_PRODUCT_CODE not in", values, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeBetween(String value1, String value2) {
            addCriterion("INSURANCE_PRODUCT_CODE between", value1, value2, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsuranceProductCodeNotBetween(String value1, String value2) {
            addCriterion("INSURANCE_PRODUCT_CODE not between", value1, value2, "insuranceProductCode");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinIsNull() {
            addCriterion("INSURANCE_PERIOD_MIN is null");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinIsNotNull() {
            addCriterion("INSURANCE_PERIOD_MIN is not null");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinEqualTo(String value) {
            addCriterion("INSURANCE_PERIOD_MIN =", value, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinNotEqualTo(String value) {
            addCriterion("INSURANCE_PERIOD_MIN <>", value, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinGreaterThan(String value) {
            addCriterion("INSURANCE_PERIOD_MIN >", value, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinGreaterThanOrEqualTo(String value) {
            addCriterion("INSURANCE_PERIOD_MIN >=", value, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinLessThan(String value) {
            addCriterion("INSURANCE_PERIOD_MIN <", value, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinLessThanOrEqualTo(String value) {
            addCriterion("INSURANCE_PERIOD_MIN <=", value, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinLike(String value) {
            addCriterion("INSURANCE_PERIOD_MIN like", value, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinNotLike(String value) {
            addCriterion("INSURANCE_PERIOD_MIN not like", value, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinIn(List<String> values) {
            addCriterion("INSURANCE_PERIOD_MIN in", values, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinNotIn(List<String> values) {
            addCriterion("INSURANCE_PERIOD_MIN not in", values, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinBetween(String value1, String value2) {
            addCriterion("INSURANCE_PERIOD_MIN between", value1, value2, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMinNotBetween(String value1, String value2) {
            addCriterion("INSURANCE_PERIOD_MIN not between", value1, value2, "insurancePeriodMin");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxIsNull() {
            addCriterion("INSURANCE_PERIOD_MAX is null");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxIsNotNull() {
            addCriterion("INSURANCE_PERIOD_MAX is not null");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxEqualTo(String value) {
            addCriterion("INSURANCE_PERIOD_MAX =", value, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxNotEqualTo(String value) {
            addCriterion("INSURANCE_PERIOD_MAX <>", value, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxGreaterThan(String value) {
            addCriterion("INSURANCE_PERIOD_MAX >", value, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxGreaterThanOrEqualTo(String value) {
            addCriterion("INSURANCE_PERIOD_MAX >=", value, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxLessThan(String value) {
            addCriterion("INSURANCE_PERIOD_MAX <", value, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxLessThanOrEqualTo(String value) {
            addCriterion("INSURANCE_PERIOD_MAX <=", value, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxLike(String value) {
            addCriterion("INSURANCE_PERIOD_MAX like", value, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxNotLike(String value) {
            addCriterion("INSURANCE_PERIOD_MAX not like", value, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxIn(List<String> values) {
            addCriterion("INSURANCE_PERIOD_MAX in", values, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxNotIn(List<String> values) {
            addCriterion("INSURANCE_PERIOD_MAX not in", values, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxBetween(String value1, String value2) {
            addCriterion("INSURANCE_PERIOD_MAX between", value1, value2, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andInsurancePeriodMaxNotBetween(String value1, String value2) {
            addCriterion("INSURANCE_PERIOD_MAX not between", value1, value2, "insurancePeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinIsNull() {
            addCriterion("RENEW_PERIOD_MIN is null");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinIsNotNull() {
            addCriterion("RENEW_PERIOD_MIN is not null");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinEqualTo(String value) {
            addCriterion("RENEW_PERIOD_MIN =", value, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinNotEqualTo(String value) {
            addCriterion("RENEW_PERIOD_MIN <>", value, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinGreaterThan(String value) {
            addCriterion("RENEW_PERIOD_MIN >", value, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinGreaterThanOrEqualTo(String value) {
            addCriterion("RENEW_PERIOD_MIN >=", value, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinLessThan(String value) {
            addCriterion("RENEW_PERIOD_MIN <", value, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinLessThanOrEqualTo(String value) {
            addCriterion("RENEW_PERIOD_MIN <=", value, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinLike(String value) {
            addCriterion("RENEW_PERIOD_MIN like", value, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinNotLike(String value) {
            addCriterion("RENEW_PERIOD_MIN not like", value, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinIn(List<String> values) {
            addCriterion("RENEW_PERIOD_MIN in", values, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinNotIn(List<String> values) {
            addCriterion("RENEW_PERIOD_MIN not in", values, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinBetween(String value1, String value2) {
            addCriterion("RENEW_PERIOD_MIN between", value1, value2, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMinNotBetween(String value1, String value2) {
            addCriterion("RENEW_PERIOD_MIN not between", value1, value2, "renewPeriodMin");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxIsNull() {
            addCriterion("RENEW_PERIOD_MAX is null");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxIsNotNull() {
            addCriterion("RENEW_PERIOD_MAX is not null");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxEqualTo(String value) {
            addCriterion("RENEW_PERIOD_MAX =", value, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxNotEqualTo(String value) {
            addCriterion("RENEW_PERIOD_MAX <>", value, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxGreaterThan(String value) {
            addCriterion("RENEW_PERIOD_MAX >", value, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxGreaterThanOrEqualTo(String value) {
            addCriterion("RENEW_PERIOD_MAX >=", value, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxLessThan(String value) {
            addCriterion("RENEW_PERIOD_MAX <", value, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxLessThanOrEqualTo(String value) {
            addCriterion("RENEW_PERIOD_MAX <=", value, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxLike(String value) {
            addCriterion("RENEW_PERIOD_MAX like", value, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxNotLike(String value) {
            addCriterion("RENEW_PERIOD_MAX not like", value, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxIn(List<String> values) {
            addCriterion("RENEW_PERIOD_MAX in", values, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxNotIn(List<String> values) {
            addCriterion("RENEW_PERIOD_MAX not in", values, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxBetween(String value1, String value2) {
            addCriterion("RENEW_PERIOD_MAX between", value1, value2, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andRenewPeriodMaxNotBetween(String value1, String value2) {
            addCriterion("RENEW_PERIOD_MAX not between", value1, value2, "renewPeriodMax");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientIsNull() {
            addCriterion("VALUE_COMMISSION_COEFFICIENT is null");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientIsNotNull() {
            addCriterion("VALUE_COMMISSION_COEFFICIENT is not null");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientEqualTo(BigDecimal value) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT =", value, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientNotEqualTo(BigDecimal value) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT <>", value, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientGreaterThan(BigDecimal value) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT >", value, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT >=", value, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientLessThan(BigDecimal value) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT <", value, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientLessThanOrEqualTo(BigDecimal value) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT <=", value, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientIn(List<BigDecimal> values) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT in", values, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientNotIn(List<BigDecimal> values) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT not in", values, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT between", value1, value2, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andValueCommissionCoefficientNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VALUE_COMMISSION_COEFFICIENT not between", value1, value2, "valueCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientIsNull() {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT is null");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientIsNotNull() {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT is not null");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientEqualTo(BigDecimal value) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT =", value, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientNotEqualTo(BigDecimal value) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT <>", value, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientGreaterThan(BigDecimal value) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT >", value, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT >=", value, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientLessThan(BigDecimal value) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT <", value, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientLessThanOrEqualTo(BigDecimal value) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT <=", value, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientIn(List<BigDecimal> values) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT in", values, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientNotIn(List<BigDecimal> values) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT not in", values, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT between", value1, value2, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andInStandardCommissionCoefficientNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IN_STANDARD_COMMISSION_COEFFICIENT not between", value1, value2, "inStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientIsNull() {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT is null");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientIsNotNull() {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT is not null");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientEqualTo(BigDecimal value) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT =", value, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientNotEqualTo(BigDecimal value) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT <>", value, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientGreaterThan(BigDecimal value) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT >", value, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT >=", value, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientLessThan(BigDecimal value) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT <", value, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT <=", value, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientIn(List<BigDecimal> values) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT in", values, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientNotIn(List<BigDecimal> values) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT not in", values, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT between", value1, value2, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andOutStandardCommissionCoefficientNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OUT_STANDARD_COMMISSION_COEFFICIENT not between", value1, value2, "outStandardCommissionCoefficient");
            return (Criteria) this;
        }

        public Criteria andProductsNameIsNull() {
            addCriterion("PRODUCTS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProductsNameIsNotNull() {
            addCriterion("PRODUCTS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductsNameEqualTo(String value) {
            addCriterion("PRODUCTS_NAME =", value, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameNotEqualTo(String value) {
            addCriterion("PRODUCTS_NAME <>", value, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameGreaterThan(String value) {
            addCriterion("PRODUCTS_NAME >", value, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCTS_NAME >=", value, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameLessThan(String value) {
            addCriterion("PRODUCTS_NAME <", value, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCTS_NAME <=", value, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameLike(String value) {
            addCriterion("PRODUCTS_NAME like", value, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameNotLike(String value) {
            addCriterion("PRODUCTS_NAME not like", value, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameIn(List<String> values) {
            addCriterion("PRODUCTS_NAME in", values, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameNotIn(List<String> values) {
            addCriterion("PRODUCTS_NAME not in", values, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameBetween(String value1, String value2) {
            addCriterion("PRODUCTS_NAME between", value1, value2, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsNameNotBetween(String value1, String value2) {
            addCriterion("PRODUCTS_NAME not between", value1, value2, "productsName");
            return (Criteria) this;
        }

        public Criteria andProductsCodeIsNull() {
            addCriterion("PRODUCTS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProductsCodeIsNotNull() {
            addCriterion("PRODUCTS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProductsCodeEqualTo(String value) {
            addCriterion("PRODUCTS_CODE =", value, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeNotEqualTo(String value) {
            addCriterion("PRODUCTS_CODE <>", value, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeGreaterThan(String value) {
            addCriterion("PRODUCTS_CODE >", value, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCTS_CODE >=", value, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeLessThan(String value) {
            addCriterion("PRODUCTS_CODE <", value, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeLessThanOrEqualTo(String value) {
            addCriterion("PRODUCTS_CODE <=", value, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeLike(String value) {
            addCriterion("PRODUCTS_CODE like", value, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeNotLike(String value) {
            addCriterion("PRODUCTS_CODE not like", value, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeIn(List<String> values) {
            addCriterion("PRODUCTS_CODE in", values, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeNotIn(List<String> values) {
            addCriterion("PRODUCTS_CODE not in", values, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeBetween(String value1, String value2) {
            addCriterion("PRODUCTS_CODE between", value1, value2, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsCodeNotBetween(String value1, String value2) {
            addCriterion("PRODUCTS_CODE not between", value1, value2, "productsCode");
            return (Criteria) this;
        }

        public Criteria andProductsStatusIsNull() {
            addCriterion("PRODUCTS_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andProductsStatusIsNotNull() {
            addCriterion("PRODUCTS_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andProductsStatusEqualTo(String value) {
            addCriterion("PRODUCTS_STATUS =", value, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusNotEqualTo(String value) {
            addCriterion("PRODUCTS_STATUS <>", value, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusGreaterThan(String value) {
            addCriterion("PRODUCTS_STATUS >", value, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCTS_STATUS >=", value, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusLessThan(String value) {
            addCriterion("PRODUCTS_STATUS <", value, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusLessThanOrEqualTo(String value) {
            addCriterion("PRODUCTS_STATUS <=", value, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusLike(String value) {
            addCriterion("PRODUCTS_STATUS like", value, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusNotLike(String value) {
            addCriterion("PRODUCTS_STATUS not like", value, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusIn(List<String> values) {
            addCriterion("PRODUCTS_STATUS in", values, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusNotIn(List<String> values) {
            addCriterion("PRODUCTS_STATUS not in", values, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusBetween(String value1, String value2) {
            addCriterion("PRODUCTS_STATUS between", value1, value2, "productsStatus");
            return (Criteria) this;
        }

        public Criteria andProductsStatusNotBetween(String value1, String value2) {
            addCriterion("PRODUCTS_STATUS not between", value1, value2, "productsStatus");
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