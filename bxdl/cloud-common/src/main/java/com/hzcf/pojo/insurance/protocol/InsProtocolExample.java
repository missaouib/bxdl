package com.hzcf.pojo.insurance.protocol;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InsProtocolExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InsProtocolExample() {
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

        public Criteria andProtocolCodeIsNull() {
            addCriterion("PROTOCOL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeIsNotNull() {
            addCriterion("PROTOCOL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeEqualTo(String value) {
            addCriterion("PROTOCOL_CODE =", value, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeNotEqualTo(String value) {
            addCriterion("PROTOCOL_CODE <>", value, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeGreaterThan(String value) {
            addCriterion("PROTOCOL_CODE >", value, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROTOCOL_CODE >=", value, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeLessThan(String value) {
            addCriterion("PROTOCOL_CODE <", value, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeLessThanOrEqualTo(String value) {
            addCriterion("PROTOCOL_CODE <=", value, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeLike(String value) {
            addCriterion("PROTOCOL_CODE like", value, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeNotLike(String value) {
            addCriterion("PROTOCOL_CODE not like", value, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeIn(List<String> values) {
            addCriterion("PROTOCOL_CODE in", values, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeNotIn(List<String> values) {
            addCriterion("PROTOCOL_CODE not in", values, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeBetween(String value1, String value2) {
            addCriterion("PROTOCOL_CODE between", value1, value2, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolCodeNotBetween(String value1, String value2) {
            addCriterion("PROTOCOL_CODE not between", value1, value2, "protocolCode");
            return (Criteria) this;
        }

        public Criteria andProtocolNameIsNull() {
            addCriterion("PROTOCOL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProtocolNameIsNotNull() {
            addCriterion("PROTOCOL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolNameEqualTo(String value) {
            addCriterion("PROTOCOL_NAME =", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameNotEqualTo(String value) {
            addCriterion("PROTOCOL_NAME <>", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameGreaterThan(String value) {
            addCriterion("PROTOCOL_NAME >", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROTOCOL_NAME >=", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameLessThan(String value) {
            addCriterion("PROTOCOL_NAME <", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameLessThanOrEqualTo(String value) {
            addCriterion("PROTOCOL_NAME <=", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameLike(String value) {
            addCriterion("PROTOCOL_NAME like", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameNotLike(String value) {
            addCriterion("PROTOCOL_NAME not like", value, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameIn(List<String> values) {
            addCriterion("PROTOCOL_NAME in", values, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameNotIn(List<String> values) {
            addCriterion("PROTOCOL_NAME not in", values, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameBetween(String value1, String value2) {
            addCriterion("PROTOCOL_NAME between", value1, value2, "protocolName");
            return (Criteria) this;
        }

        public Criteria andProtocolNameNotBetween(String value1, String value2) {
            addCriterion("PROTOCOL_NAME not between", value1, value2, "protocolName");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdIsNull() {
            addCriterion("INSURANCE_COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdIsNotNull() {
            addCriterion("INSURANCE_COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdEqualTo(Long value) {
            addCriterion("INSURANCE_COMPANY_ID =", value, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdNotEqualTo(Long value) {
            addCriterion("INSURANCE_COMPANY_ID <>", value, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdGreaterThan(Long value) {
            addCriterion("INSURANCE_COMPANY_ID >", value, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INSURANCE_COMPANY_ID >=", value, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdLessThan(Long value) {
            addCriterion("INSURANCE_COMPANY_ID <", value, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("INSURANCE_COMPANY_ID <=", value, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdIn(List<Long> values) {
            addCriterion("INSURANCE_COMPANY_ID in", values, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdNotIn(List<Long> values) {
            addCriterion("INSURANCE_COMPANY_ID not in", values, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdBetween(Long value1, Long value2) {
            addCriterion("INSURANCE_COMPANY_ID between", value1, value2, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("INSURANCE_COMPANY_ID not between", value1, value2, "insuranceCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdIsNull() {
            addCriterion("INSURANCE_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdIsNotNull() {
            addCriterion("INSURANCE_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdEqualTo(Long value) {
            addCriterion("INSURANCE_ORG_ID =", value, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdNotEqualTo(Long value) {
            addCriterion("INSURANCE_ORG_ID <>", value, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdGreaterThan(Long value) {
            addCriterion("INSURANCE_ORG_ID >", value, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INSURANCE_ORG_ID >=", value, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdLessThan(Long value) {
            addCriterion("INSURANCE_ORG_ID <", value, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdLessThanOrEqualTo(Long value) {
            addCriterion("INSURANCE_ORG_ID <=", value, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdIn(List<Long> values) {
            addCriterion("INSURANCE_ORG_ID in", values, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdNotIn(List<Long> values) {
            addCriterion("INSURANCE_ORG_ID not in", values, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdBetween(Long value1, Long value2) {
            addCriterion("INSURANCE_ORG_ID between", value1, value2, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andInsuranceOrgIdNotBetween(Long value1, Long value2) {
            addCriterion("INSURANCE_ORG_ID not between", value1, value2, "insuranceOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdIsNull() {
            addCriterion("SALES_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdIsNotNull() {
            addCriterion("SALES_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdEqualTo(Long value) {
            addCriterion("SALES_ORG_ID =", value, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdNotEqualTo(Long value) {
            addCriterion("SALES_ORG_ID <>", value, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdGreaterThan(Long value) {
            addCriterion("SALES_ORG_ID >", value, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SALES_ORG_ID >=", value, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdLessThan(Long value) {
            addCriterion("SALES_ORG_ID <", value, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdLessThanOrEqualTo(Long value) {
            addCriterion("SALES_ORG_ID <=", value, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdIn(List<Long> values) {
            addCriterion("SALES_ORG_ID in", values, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdNotIn(List<Long> values) {
            addCriterion("SALES_ORG_ID not in", values, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdBetween(Long value1, Long value2) {
            addCriterion("SALES_ORG_ID between", value1, value2, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSalesOrgIdNotBetween(Long value1, Long value2) {
            addCriterion("SALES_ORG_ID not between", value1, value2, "salesOrgId");
            return (Criteria) this;
        }

        public Criteria andSigningDateIsNull() {
            addCriterion("SIGNING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSigningDateIsNotNull() {
            addCriterion("SIGNING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSigningDateEqualTo(Date value) {
            addCriterionForJDBCDate("SIGNING_DATE =", value, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("SIGNING_DATE <>", value, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateGreaterThan(Date value) {
            addCriterionForJDBCDate("SIGNING_DATE >", value, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SIGNING_DATE >=", value, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateLessThan(Date value) {
            addCriterionForJDBCDate("SIGNING_DATE <", value, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SIGNING_DATE <=", value, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateIn(List<Date> values) {
            addCriterionForJDBCDate("SIGNING_DATE in", values, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("SIGNING_DATE not in", values, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SIGNING_DATE between", value1, value2, "signingDate");
            return (Criteria) this;
        }

        public Criteria andSigningDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SIGNING_DATE not between", value1, value2, "signingDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIsNull() {
            addCriterion("EFFECTIVE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIsNotNull() {
            addCriterion("EFFECTIVE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateEqualTo(Date value) {
            addCriterionForJDBCDate("EFFECTIVE_DATE =", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("EFFECTIVE_DATE <>", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("EFFECTIVE_DATE >", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFFECTIVE_DATE >=", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLessThan(Date value) {
            addCriterionForJDBCDate("EFFECTIVE_DATE <", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFFECTIVE_DATE <=", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIn(List<Date> values) {
            addCriterionForJDBCDate("EFFECTIVE_DATE in", values, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("EFFECTIVE_DATE not in", values, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFFECTIVE_DATE between", value1, value2, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFFECTIVE_DATE not between", value1, value2, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateIsNull() {
            addCriterion("TERMINATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andErminationDateIsNotNull() {
            addCriterion("TERMINATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andErminationDateEqualTo(Date value) {
            addCriterionForJDBCDate("TERMINATION_DATE =", value, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("TERMINATION_DATE <>", value, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateGreaterThan(Date value) {
            addCriterionForJDBCDate("TERMINATION_DATE >", value, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TERMINATION_DATE >=", value, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateLessThan(Date value) {
            addCriterionForJDBCDate("TERMINATION_DATE <", value, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TERMINATION_DATE <=", value, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateIn(List<Date> values) {
            addCriterionForJDBCDate("TERMINATION_DATE in", values, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("TERMINATION_DATE not in", values, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TERMINATION_DATE between", value1, value2, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andErminationDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TERMINATION_DATE not between", value1, value2, "erminationDate");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusIsNull() {
            addCriterion("PROTOCOL_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusIsNotNull() {
            addCriterion("PROTOCOL_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusEqualTo(String value) {
            addCriterion("PROTOCOL_STATUS =", value, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusNotEqualTo(String value) {
            addCriterion("PROTOCOL_STATUS <>", value, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusGreaterThan(String value) {
            addCriterion("PROTOCOL_STATUS >", value, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusGreaterThanOrEqualTo(String value) {
            addCriterion("PROTOCOL_STATUS >=", value, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusLessThan(String value) {
            addCriterion("PROTOCOL_STATUS <", value, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusLessThanOrEqualTo(String value) {
            addCriterion("PROTOCOL_STATUS <=", value, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusLike(String value) {
            addCriterion("PROTOCOL_STATUS like", value, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusNotLike(String value) {
            addCriterion("PROTOCOL_STATUS not like", value, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusIn(List<String> values) {
            addCriterion("PROTOCOL_STATUS in", values, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusNotIn(List<String> values) {
            addCriterion("PROTOCOL_STATUS not in", values, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusBetween(String value1, String value2) {
            addCriterion("PROTOCOL_STATUS between", value1, value2, "protocolStatus");
            return (Criteria) this;
        }

        public Criteria andProtocolStatusNotBetween(String value1, String value2) {
            addCriterion("PROTOCOL_STATUS not between", value1, value2, "protocolStatus");
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