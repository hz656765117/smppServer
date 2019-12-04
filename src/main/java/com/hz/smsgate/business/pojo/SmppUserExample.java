package com.hz.smsgate.business.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmppUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmppUserExample() {
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

        public Criteria andSpUserIsNull() {
            addCriterion("sp_user is null");
            return (Criteria) this;
        }

        public Criteria andSpUserIsNotNull() {
            addCriterion("sp_user is not null");
            return (Criteria) this;
        }

        public Criteria andSpUserEqualTo(String value) {
            addCriterion("sp_user =", value, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserNotEqualTo(String value) {
            addCriterion("sp_user <>", value, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserGreaterThan(String value) {
            addCriterion("sp_user >", value, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserGreaterThanOrEqualTo(String value) {
            addCriterion("sp_user >=", value, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserLessThan(String value) {
            addCriterion("sp_user <", value, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserLessThanOrEqualTo(String value) {
            addCriterion("sp_user <=", value, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserLike(String value) {
            addCriterion("sp_user like", value, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserNotLike(String value) {
            addCriterion("sp_user not like", value, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserIn(List<String> values) {
            addCriterion("sp_user in", values, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserNotIn(List<String> values) {
            addCriterion("sp_user not in", values, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserBetween(String value1, String value2) {
            addCriterion("sp_user between", value1, value2, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpUserNotBetween(String value1, String value2) {
            addCriterion("sp_user not between", value1, value2, "spUser");
            return (Criteria) this;
        }

        public Criteria andSpPwdIsNull() {
            addCriterion("sp_pwd is null");
            return (Criteria) this;
        }

        public Criteria andSpPwdIsNotNull() {
            addCriterion("sp_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andSpPwdEqualTo(String value) {
            addCriterion("sp_pwd =", value, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdNotEqualTo(String value) {
            addCriterion("sp_pwd <>", value, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdGreaterThan(String value) {
            addCriterion("sp_pwd >", value, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdGreaterThanOrEqualTo(String value) {
            addCriterion("sp_pwd >=", value, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdLessThan(String value) {
            addCriterion("sp_pwd <", value, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdLessThanOrEqualTo(String value) {
            addCriterion("sp_pwd <=", value, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdLike(String value) {
            addCriterion("sp_pwd like", value, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdNotLike(String value) {
            addCriterion("sp_pwd not like", value, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdIn(List<String> values) {
            addCriterion("sp_pwd in", values, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdNotIn(List<String> values) {
            addCriterion("sp_pwd not in", values, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdBetween(String value1, String value2) {
            addCriterion("sp_pwd between", value1, value2, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSpPwdNotBetween(String value1, String value2) {
            addCriterion("sp_pwd not between", value1, value2, "spPwd");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdIsNull() {
            addCriterion("sys_channel_id is null");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdIsNotNull() {
            addCriterion("sys_channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdEqualTo(String value) {
            addCriterion("sys_channel_id =", value, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdNotEqualTo(String value) {
            addCriterion("sys_channel_id <>", value, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdGreaterThan(String value) {
            addCriterion("sys_channel_id >", value, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("sys_channel_id >=", value, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdLessThan(String value) {
            addCriterion("sys_channel_id <", value, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdLessThanOrEqualTo(String value) {
            addCriterion("sys_channel_id <=", value, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdLike(String value) {
            addCriterion("sys_channel_id like", value, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdNotLike(String value) {
            addCriterion("sys_channel_id not like", value, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdIn(List<String> values) {
            addCriterion("sys_channel_id in", values, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdNotIn(List<String> values) {
            addCriterion("sys_channel_id not in", values, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdBetween(String value1, String value2) {
            addCriterion("sys_channel_id between", value1, value2, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andSysChannelIdNotBetween(String value1, String value2) {
            addCriterion("sys_channel_id not between", value1, value2, "sysChannelId");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andUserIdsIsNull() {
            addCriterion("user_ids is null");
            return (Criteria) this;
        }

        public Criteria andUserIdsIsNotNull() {
            addCriterion("user_ids is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdsEqualTo(String value) {
            addCriterion("user_ids =", value, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsNotEqualTo(String value) {
            addCriterion("user_ids <>", value, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsGreaterThan(String value) {
            addCriterion("user_ids >", value, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsGreaterThanOrEqualTo(String value) {
            addCriterion("user_ids >=", value, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsLessThan(String value) {
            addCriterion("user_ids <", value, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsLessThanOrEqualTo(String value) {
            addCriterion("user_ids <=", value, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsLike(String value) {
            addCriterion("user_ids like", value, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsNotLike(String value) {
            addCriterion("user_ids not like", value, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsIn(List<String> values) {
            addCriterion("user_ids in", values, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsNotIn(List<String> values) {
            addCriterion("user_ids not in", values, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsBetween(String value1, String value2) {
            addCriterion("user_ids between", value1, value2, "userIds");
            return (Criteria) this;
        }

        public Criteria andUserIdsNotBetween(String value1, String value2) {
            addCriterion("user_ids not between", value1, value2, "userIds");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("desc is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("desc is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("desc =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("desc <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("desc >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("desc >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("desc <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("desc <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("desc like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("desc not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("desc in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("desc not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("desc between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("desc not between", value1, value2, "desc");
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