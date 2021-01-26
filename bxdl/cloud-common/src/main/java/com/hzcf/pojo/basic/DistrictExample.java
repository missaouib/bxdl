package com.hzcf.pojo.basic;

import java.util.ArrayList;
import java.util.List;

public class DistrictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DistrictExample() {
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

        public Criteria andDistrictidIsNull() {
            addCriterion("districtId is null");
            return (Criteria) this;
        }

        public Criteria andDistrictidIsNotNull() {
            addCriterion("districtId is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictidEqualTo(Long value) {
            addCriterion("districtId =", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidNotEqualTo(Long value) {
            addCriterion("districtId <>", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidGreaterThan(Long value) {
            addCriterion("districtId >", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidGreaterThanOrEqualTo(Long value) {
            addCriterion("districtId >=", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidLessThan(Long value) {
            addCriterion("districtId <", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidLessThanOrEqualTo(Long value) {
            addCriterion("districtId <=", value, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidIn(List<Long> values) {
            addCriterion("districtId in", values, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidNotIn(List<Long> values) {
            addCriterion("districtId not in", values, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidBetween(Long value1, Long value2) {
            addCriterion("districtId between", value1, value2, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictidNotBetween(Long value1, Long value2) {
            addCriterion("districtId not between", value1, value2, "districtid");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("district like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("district not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andSortcodeIsNull() {
            addCriterion("sortCode is null");
            return (Criteria) this;
        }

        public Criteria andSortcodeIsNotNull() {
            addCriterion("sortCode is not null");
            return (Criteria) this;
        }

        public Criteria andSortcodeEqualTo(String value) {
            addCriterion("sortCode =", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotEqualTo(String value) {
            addCriterion("sortCode <>", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThan(String value) {
            addCriterion("sortCode >", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeGreaterThanOrEqualTo(String value) {
            addCriterion("sortCode >=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThan(String value) {
            addCriterion("sortCode <", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLessThanOrEqualTo(String value) {
            addCriterion("sortCode <=", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeLike(String value) {
            addCriterion("sortCode like", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotLike(String value) {
            addCriterion("sortCode not like", value, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeIn(List<String> values) {
            addCriterion("sortCode in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotIn(List<String> values) {
            addCriterion("sortCode not in", values, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeBetween(String value1, String value2) {
            addCriterion("sortCode between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andSortcodeNotBetween(String value1, String value2) {
            addCriterion("sortCode not between", value1, value2, "sortcode");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentId is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentId is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Long value) {
            addCriterion("parentId =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Long value) {
            addCriterion("parentId <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Long value) {
            addCriterion("parentId >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Long value) {
            addCriterion("parentId >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Long value) {
            addCriterion("parentId <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Long value) {
            addCriterion("parentId <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Long> values) {
            addCriterion("parentId in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Long> values) {
            addCriterion("parentId not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Long value1, Long value2) {
            addCriterion("parentId between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Long value1, Long value2) {
            addCriterion("parentId not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andPincodeIsNull() {
            addCriterion("pinCode is null");
            return (Criteria) this;
        }

        public Criteria andPincodeIsNotNull() {
            addCriterion("pinCode is not null");
            return (Criteria) this;
        }

        public Criteria andPincodeEqualTo(String value) {
            addCriterion("pinCode =", value, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeNotEqualTo(String value) {
            addCriterion("pinCode <>", value, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeGreaterThan(String value) {
            addCriterion("pinCode >", value, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeGreaterThanOrEqualTo(String value) {
            addCriterion("pinCode >=", value, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeLessThan(String value) {
            addCriterion("pinCode <", value, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeLessThanOrEqualTo(String value) {
            addCriterion("pinCode <=", value, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeLike(String value) {
            addCriterion("pinCode like", value, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeNotLike(String value) {
            addCriterion("pinCode not like", value, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeIn(List<String> values) {
            addCriterion("pinCode in", values, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeNotIn(List<String> values) {
            addCriterion("pinCode not in", values, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeBetween(String value1, String value2) {
            addCriterion("pinCode between", value1, value2, "pincode");
            return (Criteria) this;
        }

        public Criteria andPincodeNotBetween(String value1, String value2) {
            addCriterion("pinCode not between", value1, value2, "pincode");
            return (Criteria) this;
        }

        public Criteria andAreacodeIsNull() {
            addCriterion("areaCode is null");
            return (Criteria) this;
        }

        public Criteria andAreacodeIsNotNull() {
            addCriterion("areaCode is not null");
            return (Criteria) this;
        }

        public Criteria andAreacodeEqualTo(String value) {
            addCriterion("areaCode =", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotEqualTo(String value) {
            addCriterion("areaCode <>", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeGreaterThan(String value) {
            addCriterion("areaCode >", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeGreaterThanOrEqualTo(String value) {
            addCriterion("areaCode >=", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLessThan(String value) {
            addCriterion("areaCode <", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLessThanOrEqualTo(String value) {
            addCriterion("areaCode <=", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLike(String value) {
            addCriterion("areaCode like", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotLike(String value) {
            addCriterion("areaCode not like", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeIn(List<String> values) {
            addCriterion("areaCode in", values, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotIn(List<String> values) {
            addCriterion("areaCode not in", values, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeBetween(String value1, String value2) {
            addCriterion("areaCode between", value1, value2, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotBetween(String value1, String value2) {
            addCriterion("areaCode not between", value1, value2, "areacode");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Long value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Long value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Long value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Long value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Long value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Long value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Long> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Long> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Long value1, Long value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Long value1, Long value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andChildskIsNull() {
            addCriterion("childSK is null");
            return (Criteria) this;
        }

        public Criteria andChildskIsNotNull() {
            addCriterion("childSK is not null");
            return (Criteria) this;
        }

        public Criteria andChildskEqualTo(String value) {
            addCriterion("childSK =", value, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskNotEqualTo(String value) {
            addCriterion("childSK <>", value, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskGreaterThan(String value) {
            addCriterion("childSK >", value, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskGreaterThanOrEqualTo(String value) {
            addCriterion("childSK >=", value, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskLessThan(String value) {
            addCriterion("childSK <", value, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskLessThanOrEqualTo(String value) {
            addCriterion("childSK <=", value, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskLike(String value) {
            addCriterion("childSK like", value, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskNotLike(String value) {
            addCriterion("childSK not like", value, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskIn(List<String> values) {
            addCriterion("childSK in", values, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskNotIn(List<String> values) {
            addCriterion("childSK not in", values, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskBetween(String value1, String value2) {
            addCriterion("childSK between", value1, value2, "childsk");
            return (Criteria) this;
        }

        public Criteria andChildskNotBetween(String value1, String value2) {
            addCriterion("childSK not between", value1, value2, "childsk");
            return (Criteria) this;
        }

        public Criteria andAreatypeIsNull() {
            addCriterion("areaType is null");
            return (Criteria) this;
        }

        public Criteria andAreatypeIsNotNull() {
            addCriterion("areaType is not null");
            return (Criteria) this;
        }

        public Criteria andAreatypeEqualTo(String value) {
            addCriterion("areaType =", value, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeNotEqualTo(String value) {
            addCriterion("areaType <>", value, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeGreaterThan(String value) {
            addCriterion("areaType >", value, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeGreaterThanOrEqualTo(String value) {
            addCriterion("areaType >=", value, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeLessThan(String value) {
            addCriterion("areaType <", value, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeLessThanOrEqualTo(String value) {
            addCriterion("areaType <=", value, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeLike(String value) {
            addCriterion("areaType like", value, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeNotLike(String value) {
            addCriterion("areaType not like", value, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeIn(List<String> values) {
            addCriterion("areaType in", values, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeNotIn(List<String> values) {
            addCriterion("areaType not in", values, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeBetween(String value1, String value2) {
            addCriterion("areaType between", value1, value2, "areatype");
            return (Criteria) this;
        }

        public Criteria andAreatypeNotBetween(String value1, String value2) {
            addCriterion("areaType not between", value1, value2, "areatype");
            return (Criteria) this;
        }

        public Criteria andCreatoridIsNull() {
            addCriterion("creatorId is null");
            return (Criteria) this;
        }

        public Criteria andCreatoridIsNotNull() {
            addCriterion("creatorId is not null");
            return (Criteria) this;
        }

        public Criteria andCreatoridEqualTo(Long value) {
            addCriterion("creatorId =", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotEqualTo(Long value) {
            addCriterion("creatorId <>", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridGreaterThan(Long value) {
            addCriterion("creatorId >", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridGreaterThanOrEqualTo(Long value) {
            addCriterion("creatorId >=", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridLessThan(Long value) {
            addCriterion("creatorId <", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridLessThanOrEqualTo(Long value) {
            addCriterion("creatorId <=", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridIn(List<Long> values) {
            addCriterion("creatorId in", values, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotIn(List<Long> values) {
            addCriterion("creatorId not in", values, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridBetween(Long value1, Long value2) {
            addCriterion("creatorId between", value1, value2, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotBetween(Long value1, Long value2) {
            addCriterion("creatorId not between", value1, value2, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Long value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Long value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Long value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Long value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Long value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Long> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Long> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Long value1, Long value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Long value1, Long value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
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