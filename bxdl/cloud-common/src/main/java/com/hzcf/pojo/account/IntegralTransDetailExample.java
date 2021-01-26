package com.hzcf.pojo.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntegralTransDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IntegralTransDetailExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoIsNull() {
            addCriterion("detail_odd_no is null");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoIsNotNull() {
            addCriterion("detail_odd_no is not null");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoEqualTo(String value) {
            addCriterion("detail_odd_no =", value, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoNotEqualTo(String value) {
            addCriterion("detail_odd_no <>", value, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoGreaterThan(String value) {
            addCriterion("detail_odd_no >", value, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoGreaterThanOrEqualTo(String value) {
            addCriterion("detail_odd_no >=", value, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoLessThan(String value) {
            addCriterion("detail_odd_no <", value, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoLessThanOrEqualTo(String value) {
            addCriterion("detail_odd_no <=", value, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoLike(String value) {
            addCriterion("detail_odd_no like", value, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoNotLike(String value) {
            addCriterion("detail_odd_no not like", value, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoIn(List<String> values) {
            addCriterion("detail_odd_no in", values, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoNotIn(List<String> values) {
            addCriterion("detail_odd_no not in", values, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoBetween(String value1, String value2) {
            addCriterion("detail_odd_no between", value1, value2, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andDetailOddNoNotBetween(String value1, String value2) {
            addCriterion("detail_odd_no not between", value1, value2, "detailOddNo");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIsNull() {
            addCriterion("build_time is null");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIsNotNull() {
            addCriterion("build_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuildTimeEqualTo(Date value) {
            addCriterion("build_time =", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotEqualTo(Date value) {
            addCriterion("build_time <>", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeGreaterThan(Date value) {
            addCriterion("build_time >", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("build_time >=", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeLessThan(Date value) {
            addCriterion("build_time <", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeLessThanOrEqualTo(Date value) {
            addCriterion("build_time <=", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIn(List<Date> values) {
            addCriterion("build_time in", values, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotIn(List<Date> values) {
            addCriterion("build_time not in", values, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeBetween(Date value1, Date value2) {
            addCriterion("build_time between", value1, value2, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotBetween(Date value1, Date value2) {
            addCriterion("build_time not between", value1, value2, "buildTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeIsNull() {
            addCriterion("integral_type is null");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeIsNotNull() {
            addCriterion("integral_type is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeEqualTo(String value) {
            addCriterion("integral_type =", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeNotEqualTo(String value) {
            addCriterion("integral_type <>", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeGreaterThan(String value) {
            addCriterion("integral_type >", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeGreaterThanOrEqualTo(String value) {
            addCriterion("integral_type >=", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeLessThan(String value) {
            addCriterion("integral_type <", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeLessThanOrEqualTo(String value) {
            addCriterion("integral_type <=", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeLike(String value) {
            addCriterion("integral_type like", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeNotLike(String value) {
            addCriterion("integral_type not like", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeIn(List<String> values) {
            addCriterion("integral_type in", values, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeNotIn(List<String> values) {
            addCriterion("integral_type not in", values, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeBetween(String value1, String value2) {
            addCriterion("integral_type between", value1, value2, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeNotBetween(String value1, String value2) {
            addCriterion("integral_type not between", value1, value2, "integralType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIsNull() {
            addCriterion("detail_type is null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIsNotNull() {
            addCriterion("detail_type is not null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeEqualTo(String value) {
            addCriterion("detail_type =", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotEqualTo(String value) {
            addCriterion("detail_type <>", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeGreaterThan(String value) {
            addCriterion("detail_type >", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeGreaterThanOrEqualTo(String value) {
            addCriterion("detail_type >=", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeLessThan(String value) {
            addCriterion("detail_type <", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeLessThanOrEqualTo(String value) {
            addCriterion("detail_type <=", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeLike(String value) {
            addCriterion("detail_type like", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotLike(String value) {
            addCriterion("detail_type not like", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIn(List<String> values) {
            addCriterion("detail_type in", values, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotIn(List<String> values) {
            addCriterion("detail_type not in", values, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeBetween(String value1, String value2) {
            addCriterion("detail_type between", value1, value2, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotBetween(String value1, String value2) {
            addCriterion("detail_type not between", value1, value2, "detailType");
            return (Criteria) this;
        }

        public Criteria andIntegralNumIsNull() {
            addCriterion("integral_num is null");
            return (Criteria) this;
        }

        public Criteria andIntegralNumIsNotNull() {
            addCriterion("integral_num is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralNumEqualTo(BigDecimal value) {
            addCriterion("integral_num =", value, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumNotEqualTo(BigDecimal value) {
            addCriterion("integral_num <>", value, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumGreaterThan(BigDecimal value) {
            addCriterion("integral_num >", value, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_num >=", value, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumLessThan(BigDecimal value) {
            addCriterion("integral_num <", value, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_num <=", value, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumIn(List<BigDecimal> values) {
            addCriterion("integral_num in", values, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumNotIn(List<BigDecimal> values) {
            addCriterion("integral_num not in", values, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_num between", value1, value2, "integralNum");
            return (Criteria) this;
        }

        public Criteria andIntegralNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_num not between", value1, value2, "integralNum");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdIsNull() {
            addCriterion("customer_info_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdIsNotNull() {
            addCriterion("customer_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdEqualTo(Integer value) {
            addCriterion("customer_info_id =", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdNotEqualTo(Integer value) {
            addCriterion("customer_info_id <>", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdGreaterThan(Integer value) {
            addCriterion("customer_info_id >", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_info_id >=", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdLessThan(Integer value) {
            addCriterion("customer_info_id <", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_info_id <=", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdIn(List<Integer> values) {
            addCriterion("customer_info_id in", values, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdNotIn(List<Integer> values) {
            addCriterion("customer_info_id not in", values, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_info_id between", value1, value2, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_info_id not between", value1, value2, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdIsNull() {
            addCriterion("real_name_info_id is null");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdIsNotNull() {
            addCriterion("real_name_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdEqualTo(Integer value) {
            addCriterion("real_name_info_id =", value, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdNotEqualTo(Integer value) {
            addCriterion("real_name_info_id <>", value, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdGreaterThan(Integer value) {
            addCriterion("real_name_info_id >", value, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_name_info_id >=", value, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdLessThan(Integer value) {
            addCriterion("real_name_info_id <", value, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("real_name_info_id <=", value, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdIn(List<Integer> values) {
            addCriterion("real_name_info_id in", values, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdNotIn(List<Integer> values) {
            addCriterion("real_name_info_id not in", values, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("real_name_info_id between", value1, value2, "realNameInfoId");
            return (Criteria) this;
        }

        public Criteria andRealNameInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("real_name_info_id not between", value1, value2, "realNameInfoId");
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