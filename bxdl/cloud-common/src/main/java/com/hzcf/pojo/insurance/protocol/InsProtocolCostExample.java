package com.hzcf.pojo.insurance.protocol;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InsProtocolCostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InsProtocolCostExample() {
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

        public Criteria andProtocolCostIdIsNull() {
            addCriterion("PROTOCOL_COST_ID is null");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdIsNotNull() {
            addCriterion("PROTOCOL_COST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdEqualTo(Long value) {
            addCriterion("PROTOCOL_COST_ID =", value, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdNotEqualTo(Long value) {
            addCriterion("PROTOCOL_COST_ID <>", value, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdGreaterThan(Long value) {
            addCriterion("PROTOCOL_COST_ID >", value, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROTOCOL_COST_ID >=", value, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdLessThan(Long value) {
            addCriterion("PROTOCOL_COST_ID <", value, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdLessThanOrEqualTo(Long value) {
            addCriterion("PROTOCOL_COST_ID <=", value, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdIn(List<Long> values) {
            addCriterion("PROTOCOL_COST_ID in", values, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdNotIn(List<Long> values) {
            addCriterion("PROTOCOL_COST_ID not in", values, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdBetween(Long value1, Long value2) {
            addCriterion("PROTOCOL_COST_ID between", value1, value2, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andProtocolCostIdNotBetween(Long value1, Long value2) {
            addCriterion("PROTOCOL_COST_ID not between", value1, value2, "protocolCostId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Long value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Long value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Long value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Long value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Long value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Long> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Long> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Long value1, Long value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Long value1, Long value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
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

        public Criteria andCostTypeIsNull() {
            addCriterion("COST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCostTypeIsNotNull() {
            addCriterion("COST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCostTypeEqualTo(String value) {
            addCriterion("COST_TYPE =", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeNotEqualTo(String value) {
            addCriterion("COST_TYPE <>", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeGreaterThan(String value) {
            addCriterion("COST_TYPE >", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COST_TYPE >=", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeLessThan(String value) {
            addCriterion("COST_TYPE <", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeLessThanOrEqualTo(String value) {
            addCriterion("COST_TYPE <=", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeLike(String value) {
            addCriterion("COST_TYPE like", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeNotLike(String value) {
            addCriterion("COST_TYPE not like", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeIn(List<String> values) {
            addCriterion("COST_TYPE in", values, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeNotIn(List<String> values) {
            addCriterion("COST_TYPE not in", values, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeBetween(String value1, String value2) {
            addCriterion("COST_TYPE between", value1, value2, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeNotBetween(String value1, String value2) {
            addCriterion("COST_TYPE not between", value1, value2, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTotalIsNull() {
            addCriterion("COST_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andCostTotalIsNotNull() {
            addCriterion("COST_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andCostTotalEqualTo(BigDecimal value) {
            addCriterion("COST_TOTAL =", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalNotEqualTo(BigDecimal value) {
            addCriterion("COST_TOTAL <>", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalGreaterThan(BigDecimal value) {
            addCriterion("COST_TOTAL >", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COST_TOTAL >=", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalLessThan(BigDecimal value) {
            addCriterion("COST_TOTAL <", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COST_TOTAL <=", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalIn(List<BigDecimal> values) {
            addCriterion("COST_TOTAL in", values, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalNotIn(List<BigDecimal> values) {
            addCriterion("COST_TOTAL not in", values, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COST_TOTAL between", value1, value2, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COST_TOTAL not between", value1, value2, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateIsNull() {
            addCriterion("CALCULATION_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateIsNotNull() {
            addCriterion("CALCULATION_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("CALCULATION_END_DATE =", value, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CALCULATION_END_DATE <>", value, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("CALCULATION_END_DATE >", value, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CALCULATION_END_DATE >=", value, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateLessThan(Date value) {
            addCriterionForJDBCDate("CALCULATION_END_DATE <", value, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CALCULATION_END_DATE <=", value, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("CALCULATION_END_DATE in", values, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CALCULATION_END_DATE not in", values, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CALCULATION_END_DATE between", value1, value2, "calculationEndDate");
            return (Criteria) this;
        }

        public Criteria andCalculationEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CALCULATION_END_DATE not between", value1, value2, "calculationEndDate");
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