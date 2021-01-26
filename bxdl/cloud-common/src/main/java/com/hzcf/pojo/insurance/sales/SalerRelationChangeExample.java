package com.hzcf.pojo.insurance.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SalerRelationChangeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SalerRelationChangeExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSalerIdIsNull() {
            addCriterion("saler_id is null");
            return (Criteria) this;
        }

        public Criteria andSalerIdIsNotNull() {
            addCriterion("saler_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalerIdEqualTo(Long value) {
            addCriterion("saler_id =", value, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdNotEqualTo(Long value) {
            addCriterion("saler_id <>", value, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdGreaterThan(Long value) {
            addCriterion("saler_id >", value, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("saler_id >=", value, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdLessThan(Long value) {
            addCriterion("saler_id <", value, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdLessThanOrEqualTo(Long value) {
            addCriterion("saler_id <=", value, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdIn(List<Long> values) {
            addCriterion("saler_id in", values, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdNotIn(List<Long> values) {
            addCriterion("saler_id not in", values, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdBetween(Long value1, Long value2) {
            addCriterion("saler_id between", value1, value2, "salerId");
            return (Criteria) this;
        }

        public Criteria andSalerIdNotBetween(Long value1, Long value2) {
            addCriterion("saler_id not between", value1, value2, "salerId");
            return (Criteria) this;
        }

        public Criteria andRelationMonthIsNull() {
            addCriterion("relation_month is null");
            return (Criteria) this;
        }

        public Criteria andRelationMonthIsNotNull() {
            addCriterion("relation_month is not null");
            return (Criteria) this;
        }

        public Criteria andRelationMonthEqualTo(String value) {
            addCriterion("relation_month =", value, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthNotEqualTo(String value) {
            addCriterion("relation_month <>", value, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthGreaterThan(String value) {
            addCriterion("relation_month >", value, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthGreaterThanOrEqualTo(String value) {
            addCriterion("relation_month >=", value, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthLessThan(String value) {
            addCriterion("relation_month <", value, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthLessThanOrEqualTo(String value) {
            addCriterion("relation_month <=", value, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthLike(String value) {
            addCriterion("relation_month like", value, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthNotLike(String value) {
            addCriterion("relation_month not like", value, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthIn(List<String> values) {
            addCriterion("relation_month in", values, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthNotIn(List<String> values) {
            addCriterion("relation_month not in", values, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthBetween(String value1, String value2) {
            addCriterion("relation_month between", value1, value2, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andRelationMonthNotBetween(String value1, String value2) {
            addCriterion("relation_month not between", value1, value2, "relationMonth");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdIsNull() {
            addCriterion("db_before_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdIsNotNull() {
            addCriterion("db_before_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdEqualTo(Long value) {
            addCriterion("db_before_sales_id =", value, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdNotEqualTo(Long value) {
            addCriterion("db_before_sales_id <>", value, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdGreaterThan(Long value) {
            addCriterion("db_before_sales_id >", value, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("db_before_sales_id >=", value, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdLessThan(Long value) {
            addCriterion("db_before_sales_id <", value, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("db_before_sales_id <=", value, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdIn(List<Long> values) {
            addCriterion("db_before_sales_id in", values, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdNotIn(List<Long> values) {
            addCriterion("db_before_sales_id not in", values, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdBetween(Long value1, Long value2) {
            addCriterion("db_before_sales_id between", value1, value2, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbBeforeSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("db_before_sales_id not between", value1, value2, "dbBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdIsNull() {
            addCriterion("db_after_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdIsNotNull() {
            addCriterion("db_after_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdEqualTo(Long value) {
            addCriterion("db_after_sales_id =", value, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdNotEqualTo(Long value) {
            addCriterion("db_after_sales_id <>", value, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdGreaterThan(Long value) {
            addCriterion("db_after_sales_id >", value, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("db_after_sales_id >=", value, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdLessThan(Long value) {
            addCriterion("db_after_sales_id <", value, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("db_after_sales_id <=", value, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdIn(List<Long> values) {
            addCriterion("db_after_sales_id in", values, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdNotIn(List<Long> values) {
            addCriterion("db_after_sales_id not in", values, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdBetween(Long value1, Long value2) {
            addCriterion("db_after_sales_id between", value1, value2, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andDbAfterSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("db_after_sales_id not between", value1, value2, "dbAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdIsNull() {
            addCriterion("tj_before_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdIsNotNull() {
            addCriterion("tj_before_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdEqualTo(Long value) {
            addCriterion("tj_before_sales_id =", value, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdNotEqualTo(Long value) {
            addCriterion("tj_before_sales_id <>", value, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdGreaterThan(Long value) {
            addCriterion("tj_before_sales_id >", value, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tj_before_sales_id >=", value, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdLessThan(Long value) {
            addCriterion("tj_before_sales_id <", value, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("tj_before_sales_id <=", value, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdIn(List<Long> values) {
            addCriterion("tj_before_sales_id in", values, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdNotIn(List<Long> values) {
            addCriterion("tj_before_sales_id not in", values, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdBetween(Long value1, Long value2) {
            addCriterion("tj_before_sales_id between", value1, value2, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjBeforeSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("tj_before_sales_id not between", value1, value2, "tjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdIsNull() {
            addCriterion("tj_after_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdIsNotNull() {
            addCriterion("tj_after_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdEqualTo(Long value) {
            addCriterion("tj_after_sales_id =", value, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdNotEqualTo(Long value) {
            addCriterion("tj_after_sales_id <>", value, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdGreaterThan(Long value) {
            addCriterion("tj_after_sales_id >", value, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tj_after_sales_id >=", value, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdLessThan(Long value) {
            addCriterion("tj_after_sales_id <", value, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("tj_after_sales_id <=", value, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdIn(List<Long> values) {
            addCriterion("tj_after_sales_id in", values, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdNotIn(List<Long> values) {
            addCriterion("tj_after_sales_id not in", values, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdBetween(Long value1, Long value2) {
            addCriterion("tj_after_sales_id between", value1, value2, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andTjAfterSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("tj_after_sales_id not between", value1, value2, "tjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdIsNull() {
            addCriterion("sj_before_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdIsNotNull() {
            addCriterion("sj_before_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdEqualTo(Long value) {
            addCriterion("sj_before_sales_id =", value, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdNotEqualTo(Long value) {
            addCriterion("sj_before_sales_id <>", value, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdGreaterThan(Long value) {
            addCriterion("sj_before_sales_id >", value, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sj_before_sales_id >=", value, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdLessThan(Long value) {
            addCriterion("sj_before_sales_id <", value, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("sj_before_sales_id <=", value, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdIn(List<Long> values) {
            addCriterion("sj_before_sales_id in", values, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdNotIn(List<Long> values) {
            addCriterion("sj_before_sales_id not in", values, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdBetween(Long value1, Long value2) {
            addCriterion("sj_before_sales_id between", value1, value2, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjBeforeSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("sj_before_sales_id not between", value1, value2, "sjBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdIsNull() {
            addCriterion("sj_after_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdIsNotNull() {
            addCriterion("sj_after_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdEqualTo(Long value) {
            addCriterion("sj_after_sales_id =", value, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdNotEqualTo(Long value) {
            addCriterion("sj_after_sales_id <>", value, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdGreaterThan(Long value) {
            addCriterion("sj_after_sales_id >", value, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sj_after_sales_id >=", value, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdLessThan(Long value) {
            addCriterion("sj_after_sales_id <", value, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("sj_after_sales_id <=", value, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdIn(List<Long> values) {
            addCriterion("sj_after_sales_id in", values, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdNotIn(List<Long> values) {
            addCriterion("sj_after_sales_id not in", values, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdBetween(Long value1, Long value2) {
            addCriterion("sj_after_sales_id between", value1, value2, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andSjAfterSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("sj_after_sales_id not between", value1, value2, "sjAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdIsNull() {
            addCriterion("jc_before_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdIsNotNull() {
            addCriterion("jc_before_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdEqualTo(Long value) {
            addCriterion("jc_before_sales_id =", value, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdNotEqualTo(Long value) {
            addCriterion("jc_before_sales_id <>", value, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdGreaterThan(Long value) {
            addCriterion("jc_before_sales_id >", value, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("jc_before_sales_id >=", value, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdLessThan(Long value) {
            addCriterion("jc_before_sales_id <", value, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("jc_before_sales_id <=", value, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdIn(List<Long> values) {
            addCriterion("jc_before_sales_id in", values, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdNotIn(List<Long> values) {
            addCriterion("jc_before_sales_id not in", values, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdBetween(Long value1, Long value2) {
            addCriterion("jc_before_sales_id between", value1, value2, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcBeforeSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("jc_before_sales_id not between", value1, value2, "jcBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdIsNull() {
            addCriterion("jc_after_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdIsNotNull() {
            addCriterion("jc_after_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdEqualTo(Long value) {
            addCriterion("jc_after_sales_id =", value, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdNotEqualTo(Long value) {
            addCriterion("jc_after_sales_id <>", value, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdGreaterThan(Long value) {
            addCriterion("jc_after_sales_id >", value, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("jc_after_sales_id >=", value, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdLessThan(Long value) {
            addCriterion("jc_after_sales_id <", value, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("jc_after_sales_id <=", value, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdIn(List<Long> values) {
            addCriterion("jc_after_sales_id in", values, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdNotIn(List<Long> values) {
            addCriterion("jc_after_sales_id not in", values, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdBetween(Long value1, Long value2) {
            addCriterion("jc_after_sales_id between", value1, value2, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andJcAfterSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("jc_after_sales_id not between", value1, value2, "jcAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdIsNull() {
            addCriterion("fd_before_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdIsNotNull() {
            addCriterion("fd_before_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdEqualTo(Long value) {
            addCriterion("fd_before_sales_id =", value, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdNotEqualTo(Long value) {
            addCriterion("fd_before_sales_id <>", value, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdGreaterThan(Long value) {
            addCriterion("fd_before_sales_id >", value, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fd_before_sales_id >=", value, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdLessThan(Long value) {
            addCriterion("fd_before_sales_id <", value, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("fd_before_sales_id <=", value, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdIn(List<Long> values) {
            addCriterion("fd_before_sales_id in", values, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdNotIn(List<Long> values) {
            addCriterion("fd_before_sales_id not in", values, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdBetween(Long value1, Long value2) {
            addCriterion("fd_before_sales_id between", value1, value2, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdBeforeSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("fd_before_sales_id not between", value1, value2, "fdBeforeSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdIsNull() {
            addCriterion("fd_after_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdIsNotNull() {
            addCriterion("fd_after_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdEqualTo(Long value) {
            addCriterion("fd_after_sales_id =", value, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdNotEqualTo(Long value) {
            addCriterion("fd_after_sales_id <>", value, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdGreaterThan(Long value) {
            addCriterion("fd_after_sales_id >", value, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fd_after_sales_id >=", value, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdLessThan(Long value) {
            addCriterion("fd_after_sales_id <", value, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("fd_after_sales_id <=", value, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdIn(List<Long> values) {
            addCriterion("fd_after_sales_id in", values, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdNotIn(List<Long> values) {
            addCriterion("fd_after_sales_id not in", values, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdBetween(Long value1, Long value2) {
            addCriterion("fd_after_sales_id between", value1, value2, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andFdAfterSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("fd_after_sales_id not between", value1, value2, "fdAfterSalesId");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerIsNull() {
            addCriterion("yc_before_c_first_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerIsNotNull() {
            addCriterion("yc_before_c_first_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerEqualTo(Long value) {
            addCriterion("yc_before_c_first_gener =", value, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerNotEqualTo(Long value) {
            addCriterion("yc_before_c_first_gener <>", value, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerGreaterThan(Long value) {
            addCriterion("yc_before_c_first_gener >", value, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_before_c_first_gener >=", value, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerLessThan(Long value) {
            addCriterion("yc_before_c_first_gener <", value, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_before_c_first_gener <=", value, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerIn(List<Long> values) {
            addCriterion("yc_before_c_first_gener in", values, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerNotIn(List<Long> values) {
            addCriterion("yc_before_c_first_gener not in", values, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerBetween(Long value1, Long value2) {
            addCriterion("yc_before_c_first_gener between", value1, value2, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCFirstGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_before_c_first_gener not between", value1, value2, "ycBeforeCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerIsNull() {
            addCriterion("yc_after_c_first_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerIsNotNull() {
            addCriterion("yc_after_c_first_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerEqualTo(Long value) {
            addCriterion("yc_after_c_first_gener =", value, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerNotEqualTo(Long value) {
            addCriterion("yc_after_c_first_gener <>", value, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerGreaterThan(Long value) {
            addCriterion("yc_after_c_first_gener >", value, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_after_c_first_gener >=", value, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerLessThan(Long value) {
            addCriterion("yc_after_c_first_gener <", value, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_after_c_first_gener <=", value, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerIn(List<Long> values) {
            addCriterion("yc_after_c_first_gener in", values, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerNotIn(List<Long> values) {
            addCriterion("yc_after_c_first_gener not in", values, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerBetween(Long value1, Long value2) {
            addCriterion("yc_after_c_first_gener between", value1, value2, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCFirstGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_after_c_first_gener not between", value1, value2, "ycAfterCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerIsNull() {
            addCriterion("yc_before_c_second_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerIsNotNull() {
            addCriterion("yc_before_c_second_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerEqualTo(Long value) {
            addCriterion("yc_before_c_second_gener =", value, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerNotEqualTo(Long value) {
            addCriterion("yc_before_c_second_gener <>", value, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerGreaterThan(Long value) {
            addCriterion("yc_before_c_second_gener >", value, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_before_c_second_gener >=", value, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerLessThan(Long value) {
            addCriterion("yc_before_c_second_gener <", value, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_before_c_second_gener <=", value, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerIn(List<Long> values) {
            addCriterion("yc_before_c_second_gener in", values, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerNotIn(List<Long> values) {
            addCriterion("yc_before_c_second_gener not in", values, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerBetween(Long value1, Long value2) {
            addCriterion("yc_before_c_second_gener between", value1, value2, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeCSecondGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_before_c_second_gener not between", value1, value2, "ycBeforeCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerIsNull() {
            addCriterion("yc_after_c_second_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerIsNotNull() {
            addCriterion("yc_after_c_second_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerEqualTo(Long value) {
            addCriterion("yc_after_c_second_gener =", value, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerNotEqualTo(Long value) {
            addCriterion("yc_after_c_second_gener <>", value, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerGreaterThan(Long value) {
            addCriterion("yc_after_c_second_gener >", value, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_after_c_second_gener >=", value, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerLessThan(Long value) {
            addCriterion("yc_after_c_second_gener <", value, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_after_c_second_gener <=", value, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerIn(List<Long> values) {
            addCriterion("yc_after_c_second_gener in", values, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerNotIn(List<Long> values) {
            addCriterion("yc_after_c_second_gener not in", values, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerBetween(Long value1, Long value2) {
            addCriterion("yc_after_c_second_gener between", value1, value2, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterCSecondGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_after_c_second_gener not between", value1, value2, "ycAfterCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerIsNull() {
            addCriterion("yc_before_b_first_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerIsNotNull() {
            addCriterion("yc_before_b_first_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerEqualTo(Long value) {
            addCriterion("yc_before_b_first_gener =", value, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerNotEqualTo(Long value) {
            addCriterion("yc_before_b_first_gener <>", value, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerGreaterThan(Long value) {
            addCriterion("yc_before_b_first_gener >", value, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_before_b_first_gener >=", value, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerLessThan(Long value) {
            addCriterion("yc_before_b_first_gener <", value, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_before_b_first_gener <=", value, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerIn(List<Long> values) {
            addCriterion("yc_before_b_first_gener in", values, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerNotIn(List<Long> values) {
            addCriterion("yc_before_b_first_gener not in", values, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerBetween(Long value1, Long value2) {
            addCriterion("yc_before_b_first_gener between", value1, value2, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBFirstGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_before_b_first_gener not between", value1, value2, "ycBeforeBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerIsNull() {
            addCriterion("yc_after_b_first_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerIsNotNull() {
            addCriterion("yc_after_b_first_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerEqualTo(Long value) {
            addCriterion("yc_after_b_first_gener =", value, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerNotEqualTo(Long value) {
            addCriterion("yc_after_b_first_gener <>", value, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerGreaterThan(Long value) {
            addCriterion("yc_after_b_first_gener >", value, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_after_b_first_gener >=", value, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerLessThan(Long value) {
            addCriterion("yc_after_b_first_gener <", value, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_after_b_first_gener <=", value, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerIn(List<Long> values) {
            addCriterion("yc_after_b_first_gener in", values, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerNotIn(List<Long> values) {
            addCriterion("yc_after_b_first_gener not in", values, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerBetween(Long value1, Long value2) {
            addCriterion("yc_after_b_first_gener between", value1, value2, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBFirstGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_after_b_first_gener not between", value1, value2, "ycAfterBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerIsNull() {
            addCriterion("yc_before_b_second_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerIsNotNull() {
            addCriterion("yc_before_b_second_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerEqualTo(Long value) {
            addCriterion("yc_before_b_second_gener =", value, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerNotEqualTo(Long value) {
            addCriterion("yc_before_b_second_gener <>", value, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerGreaterThan(Long value) {
            addCriterion("yc_before_b_second_gener >", value, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_before_b_second_gener >=", value, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerLessThan(Long value) {
            addCriterion("yc_before_b_second_gener <", value, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_before_b_second_gener <=", value, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerIn(List<Long> values) {
            addCriterion("yc_before_b_second_gener in", values, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerNotIn(List<Long> values) {
            addCriterion("yc_before_b_second_gener not in", values, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerBetween(Long value1, Long value2) {
            addCriterion("yc_before_b_second_gener between", value1, value2, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBeforeBSecondGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_before_b_second_gener not between", value1, value2, "ycBeforeBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerIsNull() {
            addCriterion("yc_after_b_second_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerIsNotNull() {
            addCriterion("yc_after_b_second_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerEqualTo(Long value) {
            addCriterion("yc_after_b_second_gener =", value, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerNotEqualTo(Long value) {
            addCriterion("yc_after_b_second_gener <>", value, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerGreaterThan(Long value) {
            addCriterion("yc_after_b_second_gener >", value, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_after_b_second_gener >=", value, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerLessThan(Long value) {
            addCriterion("yc_after_b_second_gener <", value, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_after_b_second_gener <=", value, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerIn(List<Long> values) {
            addCriterion("yc_after_b_second_gener in", values, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerNotIn(List<Long> values) {
            addCriterion("yc_after_b_second_gener not in", values, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerBetween(Long value1, Long value2) {
            addCriterion("yc_after_b_second_gener between", value1, value2, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcAfterBSecondGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_after_b_second_gener not between", value1, value2, "ycAfterBSecondGener");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCIsNull() {
            addCriterion("direct_before_group_c is null");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCIsNotNull() {
            addCriterion("direct_before_group_c is not null");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCEqualTo(Long value) {
            addCriterion("direct_before_group_c =", value, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCNotEqualTo(Long value) {
            addCriterion("direct_before_group_c <>", value, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCGreaterThan(Long value) {
            addCriterion("direct_before_group_c >", value, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCGreaterThanOrEqualTo(Long value) {
            addCriterion("direct_before_group_c >=", value, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCLessThan(Long value) {
            addCriterion("direct_before_group_c <", value, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCLessThanOrEqualTo(Long value) {
            addCriterion("direct_before_group_c <=", value, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCIn(List<Long> values) {
            addCriterion("direct_before_group_c in", values, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCNotIn(List<Long> values) {
            addCriterion("direct_before_group_c not in", values, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCBetween(Long value1, Long value2) {
            addCriterion("direct_before_group_c between", value1, value2, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeGroupCNotBetween(Long value1, Long value2) {
            addCriterion("direct_before_group_c not between", value1, value2, "directBeforeGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCIsNull() {
            addCriterion("direct_after_group_c is null");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCIsNotNull() {
            addCriterion("direct_after_group_c is not null");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCEqualTo(Long value) {
            addCriterion("direct_after_group_c =", value, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCNotEqualTo(Long value) {
            addCriterion("direct_after_group_c <>", value, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCGreaterThan(Long value) {
            addCriterion("direct_after_group_c >", value, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCGreaterThanOrEqualTo(Long value) {
            addCriterion("direct_after_group_c >=", value, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCLessThan(Long value) {
            addCriterion("direct_after_group_c <", value, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCLessThanOrEqualTo(Long value) {
            addCriterion("direct_after_group_c <=", value, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCIn(List<Long> values) {
            addCriterion("direct_after_group_c in", values, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCNotIn(List<Long> values) {
            addCriterion("direct_after_group_c not in", values, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCBetween(Long value1, Long value2) {
            addCriterion("direct_after_group_c between", value1, value2, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectAfterGroupCNotBetween(Long value1, Long value2) {
            addCriterion("direct_after_group_c not between", value1, value2, "directAfterGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBIsNull() {
            addCriterion("direct_before_dept_b is null");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBIsNotNull() {
            addCriterion("direct_before_dept_b is not null");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBEqualTo(Long value) {
            addCriterion("direct_before_dept_b =", value, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBNotEqualTo(Long value) {
            addCriterion("direct_before_dept_b <>", value, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBGreaterThan(Long value) {
            addCriterion("direct_before_dept_b >", value, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBGreaterThanOrEqualTo(Long value) {
            addCriterion("direct_before_dept_b >=", value, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBLessThan(Long value) {
            addCriterion("direct_before_dept_b <", value, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBLessThanOrEqualTo(Long value) {
            addCriterion("direct_before_dept_b <=", value, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBIn(List<Long> values) {
            addCriterion("direct_before_dept_b in", values, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBNotIn(List<Long> values) {
            addCriterion("direct_before_dept_b not in", values, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBBetween(Long value1, Long value2) {
            addCriterion("direct_before_dept_b between", value1, value2, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectBeforeDeptBNotBetween(Long value1, Long value2) {
            addCriterion("direct_before_dept_b not between", value1, value2, "directBeforeDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBIsNull() {
            addCriterion("direct_after_dept_b is null");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBIsNotNull() {
            addCriterion("direct_after_dept_b is not null");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBEqualTo(Long value) {
            addCriterion("direct_after_dept_b =", value, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBNotEqualTo(Long value) {
            addCriterion("direct_after_dept_b <>", value, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBGreaterThan(Long value) {
            addCriterion("direct_after_dept_b >", value, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBGreaterThanOrEqualTo(Long value) {
            addCriterion("direct_after_dept_b >=", value, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBLessThan(Long value) {
            addCriterion("direct_after_dept_b <", value, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBLessThanOrEqualTo(Long value) {
            addCriterion("direct_after_dept_b <=", value, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBIn(List<Long> values) {
            addCriterion("direct_after_dept_b in", values, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBNotIn(List<Long> values) {
            addCriterion("direct_after_dept_b not in", values, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBBetween(Long value1, Long value2) {
            addCriterion("direct_after_dept_b between", value1, value2, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectAfterDeptBNotBetween(Long value1, Long value2) {
            addCriterion("direct_after_dept_b not between", value1, value2, "directAfterDeptB");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andIsFinishedIsNull() {
            addCriterion("is_finished is null");
            return (Criteria) this;
        }

        public Criteria andIsFinishedIsNotNull() {
            addCriterion("is_finished is not null");
            return (Criteria) this;
        }

        public Criteria andIsFinishedEqualTo(String value) {
            addCriterion("is_finished =", value, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedNotEqualTo(String value) {
            addCriterion("is_finished <>", value, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedGreaterThan(String value) {
            addCriterion("is_finished >", value, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedGreaterThanOrEqualTo(String value) {
            addCriterion("is_finished >=", value, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedLessThan(String value) {
            addCriterion("is_finished <", value, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedLessThanOrEqualTo(String value) {
            addCriterion("is_finished <=", value, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedLike(String value) {
            addCriterion("is_finished like", value, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedNotLike(String value) {
            addCriterion("is_finished not like", value, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedIn(List<String> values) {
            addCriterion("is_finished in", values, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedNotIn(List<String> values) {
            addCriterion("is_finished not in", values, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedBetween(String value1, String value2) {
            addCriterion("is_finished between", value1, value2, "isFinished");
            return (Criteria) this;
        }

        public Criteria andIsFinishedNotBetween(String value1, String value2) {
            addCriterion("is_finished not between", value1, value2, "isFinished");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateIsNull() {
            addCriterion("db_sales_date is null");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateIsNotNull() {
            addCriterion("db_sales_date is not null");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateEqualTo(Date value) {
            addCriterionForJDBCDate("db_sales_date =", value, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("db_sales_date <>", value, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateGreaterThan(Date value) {
            addCriterionForJDBCDate("db_sales_date >", value, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("db_sales_date >=", value, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateLessThan(Date value) {
            addCriterionForJDBCDate("db_sales_date <", value, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("db_sales_date <=", value, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateIn(List<Date> values) {
            addCriterionForJDBCDate("db_sales_date in", values, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("db_sales_date not in", values, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("db_sales_date between", value1, value2, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andDbSalesDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("db_sales_date not between", value1, value2, "dbSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateIsNull() {
            addCriterion("tj_sales_date is null");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateIsNotNull() {
            addCriterion("tj_sales_date is not null");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateEqualTo(Date value) {
            addCriterionForJDBCDate("tj_sales_date =", value, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("tj_sales_date <>", value, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateGreaterThan(Date value) {
            addCriterionForJDBCDate("tj_sales_date >", value, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("tj_sales_date >=", value, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateLessThan(Date value) {
            addCriterionForJDBCDate("tj_sales_date <", value, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("tj_sales_date <=", value, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateIn(List<Date> values) {
            addCriterionForJDBCDate("tj_sales_date in", values, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("tj_sales_date not in", values, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("tj_sales_date between", value1, value2, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andTjSalesDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("tj_sales_date not between", value1, value2, "tjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateIsNull() {
            addCriterion("sj_sales_date is null");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateIsNotNull() {
            addCriterion("sj_sales_date is not null");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateEqualTo(Date value) {
            addCriterionForJDBCDate("sj_sales_date =", value, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("sj_sales_date <>", value, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateGreaterThan(Date value) {
            addCriterionForJDBCDate("sj_sales_date >", value, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sj_sales_date >=", value, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateLessThan(Date value) {
            addCriterionForJDBCDate("sj_sales_date <", value, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sj_sales_date <=", value, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateIn(List<Date> values) {
            addCriterionForJDBCDate("sj_sales_date in", values, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("sj_sales_date not in", values, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sj_sales_date between", value1, value2, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andSjSalesDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sj_sales_date not between", value1, value2, "sjSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateIsNull() {
            addCriterion("jc_sales_date is null");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateIsNotNull() {
            addCriterion("jc_sales_date is not null");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateEqualTo(Date value) {
            addCriterionForJDBCDate("jc_sales_date =", value, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("jc_sales_date <>", value, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateGreaterThan(Date value) {
            addCriterionForJDBCDate("jc_sales_date >", value, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("jc_sales_date >=", value, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateLessThan(Date value) {
            addCriterionForJDBCDate("jc_sales_date <", value, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("jc_sales_date <=", value, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateIn(List<Date> values) {
            addCriterionForJDBCDate("jc_sales_date in", values, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("jc_sales_date not in", values, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("jc_sales_date between", value1, value2, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andJcSalesDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("jc_sales_date not between", value1, value2, "jcSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateIsNull() {
            addCriterion("fd_sales_date is null");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateIsNotNull() {
            addCriterion("fd_sales_date is not null");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateEqualTo(Date value) {
            addCriterionForJDBCDate("fd_sales_date =", value, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("fd_sales_date <>", value, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateGreaterThan(Date value) {
            addCriterionForJDBCDate("fd_sales_date >", value, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fd_sales_date >=", value, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateLessThan(Date value) {
            addCriterionForJDBCDate("fd_sales_date <", value, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fd_sales_date <=", value, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateIn(List<Date> values) {
            addCriterionForJDBCDate("fd_sales_date in", values, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("fd_sales_date not in", values, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fd_sales_date between", value1, value2, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andFdSalesDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fd_sales_date not between", value1, value2, "fdSalesDate");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeIsNull() {
            addCriterion("establish_time is null");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeIsNotNull() {
            addCriterion("establish_time is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time =", value, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time <>", value, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("establish_time >", value, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time >=", value, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeLessThan(Date value) {
            addCriterionForJDBCDate("establish_time <", value, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time <=", value, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeIn(List<Date> values) {
            addCriterionForJDBCDate("establish_time in", values, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("establish_time not in", values, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time between", value1, value2, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time not between", value1, value2, "establishTime");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1IsNull() {
            addCriterion("establish_time1 is null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1IsNotNull() {
            addCriterion("establish_time1 is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1EqualTo(Date value) {
            addCriterionForJDBCDate("establish_time1 =", value, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1NotEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time1 <>", value, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1GreaterThan(Date value) {
            addCriterionForJDBCDate("establish_time1 >", value, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time1 >=", value, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1LessThan(Date value) {
            addCriterionForJDBCDate("establish_time1 <", value, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time1 <=", value, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1In(List<Date> values) {
            addCriterionForJDBCDate("establish_time1 in", values, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1NotIn(List<Date> values) {
            addCriterionForJDBCDate("establish_time1 not in", values, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1Between(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time1 between", value1, value2, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime1NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time1 not between", value1, value2, "establishTime1");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2IsNull() {
            addCriterion("establish_time2 is null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2IsNotNull() {
            addCriterion("establish_time2 is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2EqualTo(Date value) {
            addCriterionForJDBCDate("establish_time2 =", value, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2NotEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time2 <>", value, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2GreaterThan(Date value) {
            addCriterionForJDBCDate("establish_time2 >", value, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time2 >=", value, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2LessThan(Date value) {
            addCriterionForJDBCDate("establish_time2 <", value, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time2 <=", value, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2In(List<Date> values) {
            addCriterionForJDBCDate("establish_time2 in", values, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2NotIn(List<Date> values) {
            addCriterionForJDBCDate("establish_time2 not in", values, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2Between(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time2 between", value1, value2, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime2NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time2 not between", value1, value2, "establishTime2");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3IsNull() {
            addCriterion("establish_time3 is null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3IsNotNull() {
            addCriterion("establish_time3 is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3EqualTo(Date value) {
            addCriterionForJDBCDate("establish_time3 =", value, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3NotEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time3 <>", value, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3GreaterThan(Date value) {
            addCriterionForJDBCDate("establish_time3 >", value, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time3 >=", value, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3LessThan(Date value) {
            addCriterionForJDBCDate("establish_time3 <", value, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time3 <=", value, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3In(List<Date> values) {
            addCriterionForJDBCDate("establish_time3 in", values, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3NotIn(List<Date> values) {
            addCriterionForJDBCDate("establish_time3 not in", values, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3Between(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time3 between", value1, value2, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime3NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time3 not between", value1, value2, "establishTime3");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4IsNull() {
            addCriterion("establish_time4 is null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4IsNotNull() {
            addCriterion("establish_time4 is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4EqualTo(Date value) {
            addCriterionForJDBCDate("establish_time4 =", value, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4NotEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time4 <>", value, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4GreaterThan(Date value) {
            addCriterionForJDBCDate("establish_time4 >", value, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time4 >=", value, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4LessThan(Date value) {
            addCriterionForJDBCDate("establish_time4 <", value, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time4 <=", value, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4In(List<Date> values) {
            addCriterionForJDBCDate("establish_time4 in", values, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4NotIn(List<Date> values) {
            addCriterionForJDBCDate("establish_time4 not in", values, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4Between(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time4 between", value1, value2, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime4NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time4 not between", value1, value2, "establishTime4");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5IsNull() {
            addCriterion("establish_time5 is null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5IsNotNull() {
            addCriterion("establish_time5 is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5EqualTo(Date value) {
            addCriterionForJDBCDate("establish_time5 =", value, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5NotEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time5 <>", value, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5GreaterThan(Date value) {
            addCriterionForJDBCDate("establish_time5 >", value, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time5 >=", value, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5LessThan(Date value) {
            addCriterionForJDBCDate("establish_time5 <", value, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("establish_time5 <=", value, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5In(List<Date> values) {
            addCriterionForJDBCDate("establish_time5 in", values, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5NotIn(List<Date> values) {
            addCriterionForJDBCDate("establish_time5 not in", values, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5Between(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time5 between", value1, value2, "establishTime5");
            return (Criteria) this;
        }

        public Criteria andEstablishTime5NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("establish_time5 not between", value1, value2, "establishTime5");
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