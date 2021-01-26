package com.hzcf.pojo.insurance.protocol;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsInsideStandardRateConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InsInsideStandardRateConfigExample() {
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

        public Criteria andInsideStandardIdIsNull() {
            addCriterion("INSIDE_STANDARD_ID is null");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdIsNotNull() {
            addCriterion("INSIDE_STANDARD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdEqualTo(Long value) {
            addCriterion("INSIDE_STANDARD_ID =", value, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdNotEqualTo(Long value) {
            addCriterion("INSIDE_STANDARD_ID <>", value, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdGreaterThan(Long value) {
            addCriterion("INSIDE_STANDARD_ID >", value, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INSIDE_STANDARD_ID >=", value, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdLessThan(Long value) {
            addCriterion("INSIDE_STANDARD_ID <", value, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdLessThanOrEqualTo(Long value) {
            addCriterion("INSIDE_STANDARD_ID <=", value, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdIn(List<Long> values) {
            addCriterion("INSIDE_STANDARD_ID in", values, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdNotIn(List<Long> values) {
            addCriterion("INSIDE_STANDARD_ID not in", values, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdBetween(Long value1, Long value2) {
            addCriterion("INSIDE_STANDARD_ID between", value1, value2, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andInsideStandardIdNotBetween(Long value1, Long value2) {
            addCriterion("INSIDE_STANDARD_ID not between", value1, value2, "insideStandardId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdIsNull() {
            addCriterion("PROTOCOL_ID is null");
            return (Criteria) this;
        }

        public Criteria andProtocolIdIsNotNull() {
            addCriterion("PROTOCOL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolIdEqualTo(Long value) {
            addCriterion("PROTOCOL_ID =", value, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdNotEqualTo(Long value) {
            addCriterion("PROTOCOL_ID <>", value, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdGreaterThan(Long value) {
            addCriterion("PROTOCOL_ID >", value, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROTOCOL_ID >=", value, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdLessThan(Long value) {
            addCriterion("PROTOCOL_ID <", value, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdLessThanOrEqualTo(Long value) {
            addCriterion("PROTOCOL_ID <=", value, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdIn(List<Long> values) {
            addCriterion("PROTOCOL_ID in", values, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdNotIn(List<Long> values) {
            addCriterion("PROTOCOL_ID not in", values, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdBetween(Long value1, Long value2) {
            addCriterion("PROTOCOL_ID between", value1, value2, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProtocolIdNotBetween(Long value1, Long value2) {
            addCriterion("PROTOCOL_ID not between", value1, value2, "protocolId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNull() {
            addCriterion("PRODUCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("PRODUCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("PRODUCT_CODE =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("PRODUCT_CODE <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("PRODUCT_CODE >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("PRODUCT_CODE <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("PRODUCT_CODE like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("PRODUCT_CODE not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("PRODUCT_CODE in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("PRODUCT_CODE not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE not between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateIsNull() {
            addCriterion("FIRST_STANDARD_RATE is null");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateIsNotNull() {
            addCriterion("FIRST_STANDARD_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateEqualTo(BigDecimal value) {
            addCriterion("FIRST_STANDARD_RATE =", value, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateNotEqualTo(BigDecimal value) {
            addCriterion("FIRST_STANDARD_RATE <>", value, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateGreaterThan(BigDecimal value) {
            addCriterion("FIRST_STANDARD_RATE >", value, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FIRST_STANDARD_RATE >=", value, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateLessThan(BigDecimal value) {
            addCriterion("FIRST_STANDARD_RATE <", value, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FIRST_STANDARD_RATE <=", value, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateIn(List<BigDecimal> values) {
            addCriterion("FIRST_STANDARD_RATE in", values, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateNotIn(List<BigDecimal> values) {
            addCriterion("FIRST_STANDARD_RATE not in", values, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIRST_STANDARD_RATE between", value1, value2, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andFirstStandardRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIRST_STANDARD_RATE not between", value1, value2, "firstStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateIsNull() {
            addCriterion("NEXT_STANDARD_RATE is null");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateIsNotNull() {
            addCriterion("NEXT_STANDARD_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateEqualTo(BigDecimal value) {
            addCriterion("NEXT_STANDARD_RATE =", value, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateNotEqualTo(BigDecimal value) {
            addCriterion("NEXT_STANDARD_RATE <>", value, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateGreaterThan(BigDecimal value) {
            addCriterion("NEXT_STANDARD_RATE >", value, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("NEXT_STANDARD_RATE >=", value, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateLessThan(BigDecimal value) {
            addCriterion("NEXT_STANDARD_RATE <", value, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("NEXT_STANDARD_RATE <=", value, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateIn(List<BigDecimal> values) {
            addCriterion("NEXT_STANDARD_RATE in", values, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateNotIn(List<BigDecimal> values) {
            addCriterion("NEXT_STANDARD_RATE not in", values, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NEXT_STANDARD_RATE between", value1, value2, "nextStandardRate");
            return (Criteria) this;
        }

        public Criteria andNextStandardRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NEXT_STANDARD_RATE not between", value1, value2, "nextStandardRate");
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