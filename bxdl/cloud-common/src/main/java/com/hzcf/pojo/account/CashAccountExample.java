package com.hzcf.pojo.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CashAccountExample() {
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

        public Criteria andBankCodeIsNull() {
            addCriterion("bank_code is null");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNotNull() {
            addCriterion("bank_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankCodeEqualTo(String value) {
            addCriterion("bank_code =", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotEqualTo(String value) {
            addCriterion("bank_code <>", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThan(String value) {
            addCriterion("bank_code >", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_code >=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThan(String value) {
            addCriterion("bank_code <", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThanOrEqualTo(String value) {
            addCriterion("bank_code <=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLike(String value) {
            addCriterion("bank_code like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotLike(String value) {
            addCriterion("bank_code not like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeIn(List<String> values) {
            addCriterion("bank_code in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotIn(List<String> values) {
            addCriterion("bank_code not in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeBetween(String value1, String value2) {
            addCriterion("bank_code between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotBetween(String value1, String value2) {
            addCriterion("bank_code not between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIsNull() {
            addCriterion("bank_card_no is null");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIsNotNull() {
            addCriterion("bank_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardNoEqualTo(String value) {
            addCriterion("bank_card_no =", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotEqualTo(String value) {
            addCriterion("bank_card_no <>", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoGreaterThan(String value) {
            addCriterion("bank_card_no >", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card_no >=", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLessThan(String value) {
            addCriterion("bank_card_no <", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLessThanOrEqualTo(String value) {
            addCriterion("bank_card_no <=", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLike(String value) {
            addCriterion("bank_card_no like", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotLike(String value) {
            addCriterion("bank_card_no not like", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIn(List<String> values) {
            addCriterion("bank_card_no in", values, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotIn(List<String> values) {
            addCriterion("bank_card_no not in", values, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoBetween(String value1, String value2) {
            addCriterion("bank_card_no between", value1, value2, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotBetween(String value1, String value2) {
            addCriterion("bank_card_no not between", value1, value2, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameIsNull() {
            addCriterion("open_bank_name is null");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameIsNotNull() {
            addCriterion("open_bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameEqualTo(String value) {
            addCriterion("open_bank_name =", value, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameNotEqualTo(String value) {
            addCriterion("open_bank_name <>", value, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameGreaterThan(String value) {
            addCriterion("open_bank_name >", value, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("open_bank_name >=", value, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameLessThan(String value) {
            addCriterion("open_bank_name <", value, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameLessThanOrEqualTo(String value) {
            addCriterion("open_bank_name <=", value, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameLike(String value) {
            addCriterion("open_bank_name like", value, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameNotLike(String value) {
            addCriterion("open_bank_name not like", value, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameIn(List<String> values) {
            addCriterion("open_bank_name in", values, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameNotIn(List<String> values) {
            addCriterion("open_bank_name not in", values, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameBetween(String value1, String value2) {
            addCriterion("open_bank_name between", value1, value2, "openBankName");
            return (Criteria) this;
        }

        public Criteria andOpenBankNameNotBetween(String value1, String value2) {
            addCriterion("open_bank_name not between", value1, value2, "openBankName");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileIsNull() {
            addCriterion("bank_reserve_mobile is null");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileIsNotNull() {
            addCriterion("bank_reserve_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileEqualTo(String value) {
            addCriterion("bank_reserve_mobile =", value, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileNotEqualTo(String value) {
            addCriterion("bank_reserve_mobile <>", value, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileGreaterThan(String value) {
            addCriterion("bank_reserve_mobile >", value, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileGreaterThanOrEqualTo(String value) {
            addCriterion("bank_reserve_mobile >=", value, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileLessThan(String value) {
            addCriterion("bank_reserve_mobile <", value, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileLessThanOrEqualTo(String value) {
            addCriterion("bank_reserve_mobile <=", value, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileLike(String value) {
            addCriterion("bank_reserve_mobile like", value, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileNotLike(String value) {
            addCriterion("bank_reserve_mobile not like", value, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileIn(List<String> values) {
            addCriterion("bank_reserve_mobile in", values, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileNotIn(List<String> values) {
            addCriterion("bank_reserve_mobile not in", values, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileBetween(String value1, String value2) {
            addCriterion("bank_reserve_mobile between", value1, value2, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andBankReserveMobileNotBetween(String value1, String value2) {
            addCriterion("bank_reserve_mobile not between", value1, value2, "bankReserveMobile");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIsNull() {
            addCriterion("total_money is null");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIsNotNull() {
            addCriterion("total_money is not null");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyEqualTo(BigDecimal value) {
            addCriterion("total_money =", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotEqualTo(BigDecimal value) {
            addCriterion("total_money <>", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyGreaterThan(BigDecimal value) {
            addCriterion("total_money >", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money >=", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyLessThan(BigDecimal value) {
            addCriterion("total_money <", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money <=", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIn(List<BigDecimal> values) {
            addCriterion("total_money in", values, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotIn(List<BigDecimal> values) {
            addCriterion("total_money not in", values, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money between", value1, value2, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money not between", value1, value2, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyIsNull() {
            addCriterion("available_money is null");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyIsNotNull() {
            addCriterion("available_money is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyEqualTo(BigDecimal value) {
            addCriterion("available_money =", value, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyNotEqualTo(BigDecimal value) {
            addCriterion("available_money <>", value, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyGreaterThan(BigDecimal value) {
            addCriterion("available_money >", value, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("available_money >=", value, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyLessThan(BigDecimal value) {
            addCriterion("available_money <", value, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("available_money <=", value, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyIn(List<BigDecimal> values) {
            addCriterion("available_money in", values, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyNotIn(List<BigDecimal> values) {
            addCriterion("available_money not in", values, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("available_money between", value1, value2, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andAvailableMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("available_money not between", value1, value2, "availableMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyIsNull() {
            addCriterion("frozen_money is null");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyIsNotNull() {
            addCriterion("frozen_money is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyEqualTo(BigDecimal value) {
            addCriterion("frozen_money =", value, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyNotEqualTo(BigDecimal value) {
            addCriterion("frozen_money <>", value, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyGreaterThan(BigDecimal value) {
            addCriterion("frozen_money >", value, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_money >=", value, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyLessThan(BigDecimal value) {
            addCriterion("frozen_money <", value, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_money <=", value, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyIn(List<BigDecimal> values) {
            addCriterion("frozen_money in", values, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyNotIn(List<BigDecimal> values) {
            addCriterion("frozen_money not in", values, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_money between", value1, value2, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_money not between", value1, value2, "frozenMoney");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNull() {
            addCriterion("account_status is null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNotNull() {
            addCriterion("account_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusEqualTo(String value) {
            addCriterion("account_status =", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotEqualTo(String value) {
            addCriterion("account_status <>", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThan(String value) {
            addCriterion("account_status >", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThanOrEqualTo(String value) {
            addCriterion("account_status >=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThan(String value) {
            addCriterion("account_status <", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThanOrEqualTo(String value) {
            addCriterion("account_status <=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLike(String value) {
            addCriterion("account_status like", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotLike(String value) {
            addCriterion("account_status not like", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIn(List<String> values) {
            addCriterion("account_status in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotIn(List<String> values) {
            addCriterion("account_status not in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusBetween(String value1, String value2) {
            addCriterion("account_status between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotBetween(String value1, String value2) {
            addCriterion("account_status not between", value1, value2, "accountStatus");
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

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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