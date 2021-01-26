package com.hzcf.pojo.insurance.protocol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsProtocolRateAdjustExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InsProtocolRateAdjustExample() {
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

        public Criteria andRateTypeIsNull() {
            addCriterion("RATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRateTypeIsNotNull() {
            addCriterion("RATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRateTypeEqualTo(String value) {
            addCriterion("RATE_TYPE =", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeNotEqualTo(String value) {
            addCriterion("RATE_TYPE <>", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeGreaterThan(String value) {
            addCriterion("RATE_TYPE >", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RATE_TYPE >=", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeLessThan(String value) {
            addCriterion("RATE_TYPE <", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeLessThanOrEqualTo(String value) {
            addCriterion("RATE_TYPE <=", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeLike(String value) {
            addCriterion("RATE_TYPE like", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeNotLike(String value) {
            addCriterion("RATE_TYPE not like", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeIn(List<String> values) {
            addCriterion("RATE_TYPE in", values, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeNotIn(List<String> values) {
            addCriterion("RATE_TYPE not in", values, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeBetween(String value1, String value2) {
            addCriterion("RATE_TYPE between", value1, value2, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeNotBetween(String value1, String value2) {
            addCriterion("RATE_TYPE not between", value1, value2, "rateType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeIsNull() {
            addCriterion("SUBJOIN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeIsNotNull() {
            addCriterion("SUBJOIN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeEqualTo(String value) {
            addCriterion("SUBJOIN_TYPE =", value, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeNotEqualTo(String value) {
            addCriterion("SUBJOIN_TYPE <>", value, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeGreaterThan(String value) {
            addCriterion("SUBJOIN_TYPE >", value, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJOIN_TYPE >=", value, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeLessThan(String value) {
            addCriterion("SUBJOIN_TYPE <", value, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeLessThanOrEqualTo(String value) {
            addCriterion("SUBJOIN_TYPE <=", value, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeLike(String value) {
            addCriterion("SUBJOIN_TYPE like", value, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeNotLike(String value) {
            addCriterion("SUBJOIN_TYPE not like", value, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeIn(List<String> values) {
            addCriterion("SUBJOIN_TYPE in", values, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeNotIn(List<String> values) {
            addCriterion("SUBJOIN_TYPE not in", values, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeBetween(String value1, String value2) {
            addCriterion("SUBJOIN_TYPE between", value1, value2, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSubjoinTypeNotBetween(String value1, String value2) {
            addCriterion("SUBJOIN_TYPE not between", value1, value2, "subjoinType");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalIsNull() {
            addCriterion("SETTLEMENT_INTERVAL is null");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalIsNotNull() {
            addCriterion("SETTLEMENT_INTERVAL is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalEqualTo(String value) {
            addCriterion("SETTLEMENT_INTERVAL =", value, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalNotEqualTo(String value) {
            addCriterion("SETTLEMENT_INTERVAL <>", value, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalGreaterThan(String value) {
            addCriterion("SETTLEMENT_INTERVAL >", value, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalGreaterThanOrEqualTo(String value) {
            addCriterion("SETTLEMENT_INTERVAL >=", value, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalLessThan(String value) {
            addCriterion("SETTLEMENT_INTERVAL <", value, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalLessThanOrEqualTo(String value) {
            addCriterion("SETTLEMENT_INTERVAL <=", value, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalLike(String value) {
            addCriterion("SETTLEMENT_INTERVAL like", value, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalNotLike(String value) {
            addCriterion("SETTLEMENT_INTERVAL not like", value, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalIn(List<String> values) {
            addCriterion("SETTLEMENT_INTERVAL in", values, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalNotIn(List<String> values) {
            addCriterion("SETTLEMENT_INTERVAL not in", values, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalBetween(String value1, String value2) {
            addCriterion("SETTLEMENT_INTERVAL between", value1, value2, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andSettlementIntervalNotBetween(String value1, String value2) {
            addCriterion("SETTLEMENT_INTERVAL not between", value1, value2, "settlementInterval");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("PRODUCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("PRODUCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(String value) {
            addCriterion("PRODUCT_TYPE =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(String value) {
            addCriterion("PRODUCT_TYPE <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(String value) {
            addCriterion("PRODUCT_TYPE >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_TYPE >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(String value) {
            addCriterion("PRODUCT_TYPE <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_TYPE <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLike(String value) {
            addCriterion("PRODUCT_TYPE like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotLike(String value) {
            addCriterion("PRODUCT_TYPE not like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<String> values) {
            addCriterion("PRODUCT_TYPE in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<String> values) {
            addCriterion("PRODUCT_TYPE not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(String value1, String value2) {
            addCriterion("PRODUCT_TYPE between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_TYPE not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeIsNull() {
            addCriterion("RATE_ADJUST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeIsNotNull() {
            addCriterion("RATE_ADJUST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeEqualTo(String value) {
            addCriterion("RATE_ADJUST_TYPE =", value, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeNotEqualTo(String value) {
            addCriterion("RATE_ADJUST_TYPE <>", value, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeGreaterThan(String value) {
            addCriterion("RATE_ADJUST_TYPE >", value, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RATE_ADJUST_TYPE >=", value, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeLessThan(String value) {
            addCriterion("RATE_ADJUST_TYPE <", value, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeLessThanOrEqualTo(String value) {
            addCriterion("RATE_ADJUST_TYPE <=", value, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeLike(String value) {
            addCriterion("RATE_ADJUST_TYPE like", value, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeNotLike(String value) {
            addCriterion("RATE_ADJUST_TYPE not like", value, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeIn(List<String> values) {
            addCriterion("RATE_ADJUST_TYPE in", values, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeNotIn(List<String> values) {
            addCriterion("RATE_ADJUST_TYPE not in", values, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeBetween(String value1, String value2) {
            addCriterion("RATE_ADJUST_TYPE between", value1, value2, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andRateAdjustTypeNotBetween(String value1, String value2) {
            addCriterion("RATE_ADJUST_TYPE not between", value1, value2, "rateAdjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeIsNull() {
            addCriterion("ADJUST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeIsNotNull() {
            addCriterion("ADJUST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeEqualTo(String value) {
            addCriterion("ADJUST_TYPE =", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotEqualTo(String value) {
            addCriterion("ADJUST_TYPE <>", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeGreaterThan(String value) {
            addCriterion("ADJUST_TYPE >", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ADJUST_TYPE >=", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeLessThan(String value) {
            addCriterion("ADJUST_TYPE <", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeLessThanOrEqualTo(String value) {
            addCriterion("ADJUST_TYPE <=", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeLike(String value) {
            addCriterion("ADJUST_TYPE like", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotLike(String value) {
            addCriterion("ADJUST_TYPE not like", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeIn(List<String> values) {
            addCriterion("ADJUST_TYPE in", values, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotIn(List<String> values) {
            addCriterion("ADJUST_TYPE not in", values, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeBetween(String value1, String value2) {
            addCriterion("ADJUST_TYPE between", value1, value2, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotBetween(String value1, String value2) {
            addCriterion("ADJUST_TYPE not between", value1, value2, "adjustType");
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