package com.hzcf.pojo.insurance.protocol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsProtocolImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InsProtocolImageExample() {
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

        public Criteria andProtocolImageIdIsNull() {
            addCriterion("PROTOCOL_IMAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdIsNotNull() {
            addCriterion("PROTOCOL_IMAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdEqualTo(Long value) {
            addCriterion("PROTOCOL_IMAGE_ID =", value, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdNotEqualTo(Long value) {
            addCriterion("PROTOCOL_IMAGE_ID <>", value, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdGreaterThan(Long value) {
            addCriterion("PROTOCOL_IMAGE_ID >", value, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROTOCOL_IMAGE_ID >=", value, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdLessThan(Long value) {
            addCriterion("PROTOCOL_IMAGE_ID <", value, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdLessThanOrEqualTo(Long value) {
            addCriterion("PROTOCOL_IMAGE_ID <=", value, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdIn(List<Long> values) {
            addCriterion("PROTOCOL_IMAGE_ID in", values, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdNotIn(List<Long> values) {
            addCriterion("PROTOCOL_IMAGE_ID not in", values, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdBetween(Long value1, Long value2) {
            addCriterion("PROTOCOL_IMAGE_ID between", value1, value2, "protocolImageId");
            return (Criteria) this;
        }

        public Criteria andProtocolImageIdNotBetween(Long value1, Long value2) {
            addCriterion("PROTOCOL_IMAGE_ID not between", value1, value2, "protocolImageId");
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

        public Criteria andImageNameIsNull() {
            addCriterion("IMAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andImageNameIsNotNull() {
            addCriterion("IMAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andImageNameEqualTo(String value) {
            addCriterion("IMAGE_NAME =", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotEqualTo(String value) {
            addCriterion("IMAGE_NAME <>", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameGreaterThan(String value) {
            addCriterion("IMAGE_NAME >", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_NAME >=", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLessThan(String value) {
            addCriterion("IMAGE_NAME <", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_NAME <=", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLike(String value) {
            addCriterion("IMAGE_NAME like", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotLike(String value) {
            addCriterion("IMAGE_NAME not like", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameIn(List<String> values) {
            addCriterion("IMAGE_NAME in", values, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotIn(List<String> values) {
            addCriterion("IMAGE_NAME not in", values, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameBetween(String value1, String value2) {
            addCriterion("IMAGE_NAME between", value1, value2, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotBetween(String value1, String value2) {
            addCriterion("IMAGE_NAME not between", value1, value2, "imageName");
            return (Criteria) this;
        }

        public Criteria andFastUrlIsNull() {
            addCriterion("FAST_URL is null");
            return (Criteria) this;
        }

        public Criteria andFastUrlIsNotNull() {
            addCriterion("FAST_URL is not null");
            return (Criteria) this;
        }

        public Criteria andFastUrlEqualTo(String value) {
            addCriterion("FAST_URL =", value, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlNotEqualTo(String value) {
            addCriterion("FAST_URL <>", value, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlGreaterThan(String value) {
            addCriterion("FAST_URL >", value, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlGreaterThanOrEqualTo(String value) {
            addCriterion("FAST_URL >=", value, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlLessThan(String value) {
            addCriterion("FAST_URL <", value, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlLessThanOrEqualTo(String value) {
            addCriterion("FAST_URL <=", value, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlLike(String value) {
            addCriterion("FAST_URL like", value, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlNotLike(String value) {
            addCriterion("FAST_URL not like", value, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlIn(List<String> values) {
            addCriterion("FAST_URL in", values, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlNotIn(List<String> values) {
            addCriterion("FAST_URL not in", values, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlBetween(String value1, String value2) {
            addCriterion("FAST_URL between", value1, value2, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andFastUrlNotBetween(String value1, String value2) {
            addCriterion("FAST_URL not between", value1, value2, "fastUrl");
            return (Criteria) this;
        }

        public Criteria andImageStatusIsNull() {
            addCriterion("IMAGE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andImageStatusIsNotNull() {
            addCriterion("IMAGE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andImageStatusEqualTo(String value) {
            addCriterion("IMAGE_STATUS =", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusNotEqualTo(String value) {
            addCriterion("IMAGE_STATUS <>", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusGreaterThan(String value) {
            addCriterion("IMAGE_STATUS >", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_STATUS >=", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusLessThan(String value) {
            addCriterion("IMAGE_STATUS <", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_STATUS <=", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusLike(String value) {
            addCriterion("IMAGE_STATUS like", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusNotLike(String value) {
            addCriterion("IMAGE_STATUS not like", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusIn(List<String> values) {
            addCriterion("IMAGE_STATUS in", values, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusNotIn(List<String> values) {
            addCriterion("IMAGE_STATUS not in", values, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusBetween(String value1, String value2) {
            addCriterion("IMAGE_STATUS between", value1, value2, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusNotBetween(String value1, String value2) {
            addCriterion("IMAGE_STATUS not between", value1, value2, "imageStatus");
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