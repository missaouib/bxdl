package com.hzcf.pojo.insurance.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalerMonthlyRelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SalerMonthlyRelationExample() {
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

        public Criteria andDbSalesIdIsNull() {
            addCriterion("db_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdIsNotNull() {
            addCriterion("db_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdEqualTo(Long value) {
            addCriterion("db_sales_id =", value, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdNotEqualTo(Long value) {
            addCriterion("db_sales_id <>", value, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdGreaterThan(Long value) {
            addCriterion("db_sales_id >", value, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("db_sales_id >=", value, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdLessThan(Long value) {
            addCriterion("db_sales_id <", value, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("db_sales_id <=", value, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdIn(List<Long> values) {
            addCriterion("db_sales_id in", values, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdNotIn(List<Long> values) {
            addCriterion("db_sales_id not in", values, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdBetween(Long value1, Long value2) {
            addCriterion("db_sales_id between", value1, value2, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andDbSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("db_sales_id not between", value1, value2, "dbSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdIsNull() {
            addCriterion("tj_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdIsNotNull() {
            addCriterion("tj_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdEqualTo(Long value) {
            addCriterion("tj_sales_id =", value, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdNotEqualTo(Long value) {
            addCriterion("tj_sales_id <>", value, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdGreaterThan(Long value) {
            addCriterion("tj_sales_id >", value, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tj_sales_id >=", value, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdLessThan(Long value) {
            addCriterion("tj_sales_id <", value, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("tj_sales_id <=", value, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdIn(List<Long> values) {
            addCriterion("tj_sales_id in", values, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdNotIn(List<Long> values) {
            addCriterion("tj_sales_id not in", values, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdBetween(Long value1, Long value2) {
            addCriterion("tj_sales_id between", value1, value2, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andTjSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("tj_sales_id not between", value1, value2, "tjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdIsNull() {
            addCriterion("sj_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdIsNotNull() {
            addCriterion("sj_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdEqualTo(Long value) {
            addCriterion("sj_sales_id =", value, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdNotEqualTo(Long value) {
            addCriterion("sj_sales_id <>", value, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdGreaterThan(Long value) {
            addCriterion("sj_sales_id >", value, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sj_sales_id >=", value, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdLessThan(Long value) {
            addCriterion("sj_sales_id <", value, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("sj_sales_id <=", value, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdIn(List<Long> values) {
            addCriterion("sj_sales_id in", values, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdNotIn(List<Long> values) {
            addCriterion("sj_sales_id not in", values, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdBetween(Long value1, Long value2) {
            addCriterion("sj_sales_id between", value1, value2, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andSjSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("sj_sales_id not between", value1, value2, "sjSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdIsNull() {
            addCriterion("jc_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdIsNotNull() {
            addCriterion("jc_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdEqualTo(Long value) {
            addCriterion("jc_sales_id =", value, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdNotEqualTo(Long value) {
            addCriterion("jc_sales_id <>", value, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdGreaterThan(Long value) {
            addCriterion("jc_sales_id >", value, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("jc_sales_id >=", value, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdLessThan(Long value) {
            addCriterion("jc_sales_id <", value, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("jc_sales_id <=", value, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdIn(List<Long> values) {
            addCriterion("jc_sales_id in", values, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdNotIn(List<Long> values) {
            addCriterion("jc_sales_id not in", values, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdBetween(Long value1, Long value2) {
            addCriterion("jc_sales_id between", value1, value2, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andJcSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("jc_sales_id not between", value1, value2, "jcSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdIsNull() {
            addCriterion("fd_sales_id is null");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdIsNotNull() {
            addCriterion("fd_sales_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdEqualTo(Long value) {
            addCriterion("fd_sales_id =", value, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdNotEqualTo(Long value) {
            addCriterion("fd_sales_id <>", value, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdGreaterThan(Long value) {
            addCriterion("fd_sales_id >", value, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fd_sales_id >=", value, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdLessThan(Long value) {
            addCriterion("fd_sales_id <", value, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdLessThanOrEqualTo(Long value) {
            addCriterion("fd_sales_id <=", value, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdIn(List<Long> values) {
            addCriterion("fd_sales_id in", values, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdNotIn(List<Long> values) {
            addCriterion("fd_sales_id not in", values, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdBetween(Long value1, Long value2) {
            addCriterion("fd_sales_id between", value1, value2, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andFdSalesIdNotBetween(Long value1, Long value2) {
            addCriterion("fd_sales_id not between", value1, value2, "fdSalesId");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerIsNull() {
            addCriterion("yc_c_first_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerIsNotNull() {
            addCriterion("yc_c_first_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerEqualTo(Long value) {
            addCriterion("yc_c_first_gener =", value, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerNotEqualTo(Long value) {
            addCriterion("yc_c_first_gener <>", value, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerGreaterThan(Long value) {
            addCriterion("yc_c_first_gener >", value, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_c_first_gener >=", value, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerLessThan(Long value) {
            addCriterion("yc_c_first_gener <", value, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_c_first_gener <=", value, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerIn(List<Long> values) {
            addCriterion("yc_c_first_gener in", values, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerNotIn(List<Long> values) {
            addCriterion("yc_c_first_gener not in", values, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerBetween(Long value1, Long value2) {
            addCriterion("yc_c_first_gener between", value1, value2, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCFirstGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_c_first_gener not between", value1, value2, "ycCFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerIsNull() {
            addCriterion("yc_c_second_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerIsNotNull() {
            addCriterion("yc_c_second_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerEqualTo(Long value) {
            addCriterion("yc_c_second_gener =", value, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerNotEqualTo(Long value) {
            addCriterion("yc_c_second_gener <>", value, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerGreaterThan(Long value) {
            addCriterion("yc_c_second_gener >", value, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_c_second_gener >=", value, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerLessThan(Long value) {
            addCriterion("yc_c_second_gener <", value, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_c_second_gener <=", value, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerIn(List<Long> values) {
            addCriterion("yc_c_second_gener in", values, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerNotIn(List<Long> values) {
            addCriterion("yc_c_second_gener not in", values, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerBetween(Long value1, Long value2) {
            addCriterion("yc_c_second_gener between", value1, value2, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcCSecondGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_c_second_gener not between", value1, value2, "ycCSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerIsNull() {
            addCriterion("yc_b_first_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerIsNotNull() {
            addCriterion("yc_b_first_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerEqualTo(Long value) {
            addCriterion("yc_b_first_gener =", value, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerNotEqualTo(Long value) {
            addCriterion("yc_b_first_gener <>", value, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerGreaterThan(Long value) {
            addCriterion("yc_b_first_gener >", value, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_b_first_gener >=", value, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerLessThan(Long value) {
            addCriterion("yc_b_first_gener <", value, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_b_first_gener <=", value, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerIn(List<Long> values) {
            addCriterion("yc_b_first_gener in", values, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerNotIn(List<Long> values) {
            addCriterion("yc_b_first_gener not in", values, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerBetween(Long value1, Long value2) {
            addCriterion("yc_b_first_gener between", value1, value2, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBFirstGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_b_first_gener not between", value1, value2, "ycBFirstGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerIsNull() {
            addCriterion("yc_b_second_gener is null");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerIsNotNull() {
            addCriterion("yc_b_second_gener is not null");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerEqualTo(Long value) {
            addCriterion("yc_b_second_gener =", value, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerNotEqualTo(Long value) {
            addCriterion("yc_b_second_gener <>", value, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerGreaterThan(Long value) {
            addCriterion("yc_b_second_gener >", value, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerGreaterThanOrEqualTo(Long value) {
            addCriterion("yc_b_second_gener >=", value, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerLessThan(Long value) {
            addCriterion("yc_b_second_gener <", value, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerLessThanOrEqualTo(Long value) {
            addCriterion("yc_b_second_gener <=", value, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerIn(List<Long> values) {
            addCriterion("yc_b_second_gener in", values, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerNotIn(List<Long> values) {
            addCriterion("yc_b_second_gener not in", values, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerBetween(Long value1, Long value2) {
            addCriterion("yc_b_second_gener between", value1, value2, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andYcBSecondGenerNotBetween(Long value1, Long value2) {
            addCriterion("yc_b_second_gener not between", value1, value2, "ycBSecondGener");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCIsNull() {
            addCriterion("direct_group_c is null");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCIsNotNull() {
            addCriterion("direct_group_c is not null");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCEqualTo(Long value) {
            addCriterion("direct_group_c =", value, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCNotEqualTo(Long value) {
            addCriterion("direct_group_c <>", value, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCGreaterThan(Long value) {
            addCriterion("direct_group_c >", value, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCGreaterThanOrEqualTo(Long value) {
            addCriterion("direct_group_c >=", value, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCLessThan(Long value) {
            addCriterion("direct_group_c <", value, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCLessThanOrEqualTo(Long value) {
            addCriterion("direct_group_c <=", value, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCIn(List<Long> values) {
            addCriterion("direct_group_c in", values, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCNotIn(List<Long> values) {
            addCriterion("direct_group_c not in", values, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCBetween(Long value1, Long value2) {
            addCriterion("direct_group_c between", value1, value2, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectGroupCNotBetween(Long value1, Long value2) {
            addCriterion("direct_group_c not between", value1, value2, "directGroupC");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBIsNull() {
            addCriterion("direct_dept_b is null");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBIsNotNull() {
            addCriterion("direct_dept_b is not null");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBEqualTo(Long value) {
            addCriterion("direct_dept_b =", value, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBNotEqualTo(Long value) {
            addCriterion("direct_dept_b <>", value, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBGreaterThan(Long value) {
            addCriterion("direct_dept_b >", value, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBGreaterThanOrEqualTo(Long value) {
            addCriterion("direct_dept_b >=", value, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBLessThan(Long value) {
            addCriterion("direct_dept_b <", value, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBLessThanOrEqualTo(Long value) {
            addCriterion("direct_dept_b <=", value, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBIn(List<Long> values) {
            addCriterion("direct_dept_b in", values, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBNotIn(List<Long> values) {
            addCriterion("direct_dept_b not in", values, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBBetween(Long value1, Long value2) {
            addCriterion("direct_dept_b between", value1, value2, "directDeptB");
            return (Criteria) this;
        }

        public Criteria andDirectDeptBNotBetween(Long value1, Long value2) {
            addCriterion("direct_dept_b not between", value1, value2, "directDeptB");
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