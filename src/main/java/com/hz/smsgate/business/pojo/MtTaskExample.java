package com.hz.smsgate.business.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MtTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MtTaskExample() {
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

        public Criteria andSpMsgIdIsNull() {
            addCriterion("sp_msg_id is null");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdIsNotNull() {
            addCriterion("sp_msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdEqualTo(String value) {
            addCriterion("sp_msg_id =", value, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdNotEqualTo(String value) {
            addCriterion("sp_msg_id <>", value, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdGreaterThan(String value) {
            addCriterion("sp_msg_id >", value, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdGreaterThanOrEqualTo(String value) {
            addCriterion("sp_msg_id >=", value, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdLessThan(String value) {
            addCriterion("sp_msg_id <", value, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdLessThanOrEqualTo(String value) {
            addCriterion("sp_msg_id <=", value, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdLike(String value) {
            addCriterion("sp_msg_id like", value, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdNotLike(String value) {
            addCriterion("sp_msg_id not like", value, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdIn(List<String> values) {
            addCriterion("sp_msg_id in", values, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdNotIn(List<String> values) {
            addCriterion("sp_msg_id not in", values, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdBetween(String value1, String value2) {
            addCriterion("sp_msg_id between", value1, value2, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andSpMsgIdNotBetween(String value1, String value2) {
            addCriterion("sp_msg_id not between", value1, value2, "spMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdIsNull() {
            addCriterion("real_msg_id is null");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdIsNotNull() {
            addCriterion("real_msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdEqualTo(String value) {
            addCriterion("real_msg_id =", value, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdNotEqualTo(String value) {
            addCriterion("real_msg_id <>", value, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdGreaterThan(String value) {
            addCriterion("real_msg_id >", value, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdGreaterThanOrEqualTo(String value) {
            addCriterion("real_msg_id >=", value, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdLessThan(String value) {
            addCriterion("real_msg_id <", value, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdLessThanOrEqualTo(String value) {
            addCriterion("real_msg_id <=", value, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdLike(String value) {
            addCriterion("real_msg_id like", value, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdNotLike(String value) {
            addCriterion("real_msg_id not like", value, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdIn(List<String> values) {
            addCriterion("real_msg_id in", values, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdNotIn(List<String> values) {
            addCriterion("real_msg_id not in", values, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdBetween(String value1, String value2) {
            addCriterion("real_msg_id between", value1, value2, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andRealMsgIdNotBetween(String value1, String value2) {
            addCriterion("real_msg_id not between", value1, value2, "realMsgId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNull() {
            addCriterion("system_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("system_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(String value) {
            addCriterion("system_id =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(String value) {
            addCriterion("system_id <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(String value) {
            addCriterion("system_id >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(String value) {
            addCriterion("system_id >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(String value) {
            addCriterion("system_id <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(String value) {
            addCriterion("system_id <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLike(String value) {
            addCriterion("system_id like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotLike(String value) {
            addCriterion("system_id not like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<String> values) {
            addCriterion("system_id in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<String> values) {
            addCriterion("system_id not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(String value1, String value2) {
            addCriterion("system_id between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(String value1, String value2) {
            addCriterion("system_id not between", value1, value2, "systemId");
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

        public Criteria andSenderIdIsNull() {
            addCriterion("sender_id is null");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNotNull() {
            addCriterion("sender_id is not null");
            return (Criteria) this;
        }

        public Criteria andSenderIdEqualTo(String value) {
            addCriterion("sender_id =", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotEqualTo(String value) {
            addCriterion("sender_id <>", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThan(String value) {
            addCriterion("sender_id >", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThanOrEqualTo(String value) {
            addCriterion("sender_id >=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThan(String value) {
            addCriterion("sender_id <", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThanOrEqualTo(String value) {
            addCriterion("sender_id <=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLike(String value) {
            addCriterion("sender_id like", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotLike(String value) {
            addCriterion("sender_id not like", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdIn(List<String> values) {
            addCriterion("sender_id in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotIn(List<String> values) {
            addCriterion("sender_id not in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdBetween(String value1, String value2) {
            addCriterion("sender_id between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotBetween(String value1, String value2) {
            addCriterion("sender_id not between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNull() {
            addCriterion("send_type is null");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNotNull() {
            addCriterion("send_type is not null");
            return (Criteria) this;
        }

        public Criteria andSendTypeEqualTo(Integer value) {
            addCriterion("send_type =", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotEqualTo(Integer value) {
            addCriterion("send_type <>", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThan(Integer value) {
            addCriterion("send_type >", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_type >=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThan(Integer value) {
            addCriterion("send_type <", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThanOrEqualTo(Integer value) {
            addCriterion("send_type <=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIn(List<Integer> values) {
            addCriterion("send_type in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotIn(List<Integer> values) {
            addCriterion("send_type not in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeBetween(Integer value1, Integer value2) {
            addCriterion("send_type between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("send_type not between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendLevelIsNull() {
            addCriterion("send_level is null");
            return (Criteria) this;
        }

        public Criteria andSendLevelIsNotNull() {
            addCriterion("send_level is not null");
            return (Criteria) this;
        }

        public Criteria andSendLevelEqualTo(Integer value) {
            addCriterion("send_level =", value, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelNotEqualTo(Integer value) {
            addCriterion("send_level <>", value, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelGreaterThan(Integer value) {
            addCriterion("send_level >", value, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_level >=", value, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelLessThan(Integer value) {
            addCriterion("send_level <", value, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelLessThanOrEqualTo(Integer value) {
            addCriterion("send_level <=", value, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelIn(List<Integer> values) {
            addCriterion("send_level in", values, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelNotIn(List<Integer> values) {
            addCriterion("send_level not in", values, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelBetween(Integer value1, Integer value2) {
            addCriterion("send_level between", value1, value2, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("send_level not between", value1, value2, "sendLevel");
            return (Criteria) this;
        }

        public Criteria andSendFlagIsNull() {
            addCriterion("send_flag is null");
            return (Criteria) this;
        }

        public Criteria andSendFlagIsNotNull() {
            addCriterion("send_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSendFlagEqualTo(Integer value) {
            addCriterion("send_flag =", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotEqualTo(Integer value) {
            addCriterion("send_flag <>", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagGreaterThan(Integer value) {
            addCriterion("send_flag >", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_flag >=", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLessThan(Integer value) {
            addCriterion("send_flag <", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLessThanOrEqualTo(Integer value) {
            addCriterion("send_flag <=", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagIn(List<Integer> values) {
            addCriterion("send_flag in", values, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotIn(List<Integer> values) {
            addCriterion("send_flag not in", values, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagBetween(Integer value1, Integer value2) {
            addCriterion("send_flag between", value1, value2, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("send_flag not between", value1, value2, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagIsNull() {
            addCriterion("receive_flag is null");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagIsNotNull() {
            addCriterion("receive_flag is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagEqualTo(Integer value) {
            addCriterion("receive_flag =", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagNotEqualTo(Integer value) {
            addCriterion("receive_flag <>", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagGreaterThan(Integer value) {
            addCriterion("receive_flag >", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_flag >=", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagLessThan(Integer value) {
            addCriterion("receive_flag <", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagLessThanOrEqualTo(Integer value) {
            addCriterion("receive_flag <=", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagIn(List<Integer> values) {
            addCriterion("receive_flag in", values, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagNotIn(List<Integer> values) {
            addCriterion("receive_flag not in", values, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagBetween(Integer value1, Integer value2) {
            addCriterion("receive_flag between", value1, value2, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_flag not between", value1, value2, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIsNull() {
            addCriterion("error_code is null");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIsNotNull() {
            addCriterion("error_code is not null");
            return (Criteria) this;
        }

        public Criteria andErrorCodeEqualTo(String value) {
            addCriterion("error_code =", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotEqualTo(String value) {
            addCriterion("error_code <>", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThan(String value) {
            addCriterion("error_code >", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("error_code >=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThan(String value) {
            addCriterion("error_code <", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThanOrEqualTo(String value) {
            addCriterion("error_code <=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLike(String value) {
            addCriterion("error_code like", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotLike(String value) {
            addCriterion("error_code not like", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIn(List<String> values) {
            addCriterion("error_code in", values, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotIn(List<String> values) {
            addCriterion("error_code not in", values, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeBetween(String value1, String value2) {
            addCriterion("error_code between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotBetween(String value1, String value2) {
            addCriterion("error_code not between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("receive_time is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("receive_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterion("receive_time =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterion("receive_time <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterion("receive_time >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receive_time >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterion("receive_time <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("receive_time <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterion("receive_time in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterion("receive_time not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterion("receive_time between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("receive_time not between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqIsNull() {
            addCriterion("long_msg_seq is null");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqIsNotNull() {
            addCriterion("long_msg_seq is not null");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqEqualTo(Integer value) {
            addCriterion("long_msg_seq =", value, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqNotEqualTo(Integer value) {
            addCriterion("long_msg_seq <>", value, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqGreaterThan(Integer value) {
            addCriterion("long_msg_seq >", value, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("long_msg_seq >=", value, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqLessThan(Integer value) {
            addCriterion("long_msg_seq <", value, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqLessThanOrEqualTo(Integer value) {
            addCriterion("long_msg_seq <=", value, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqIn(List<Integer> values) {
            addCriterion("long_msg_seq in", values, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqNotIn(List<Integer> values) {
            addCriterion("long_msg_seq not in", values, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqBetween(Integer value1, Integer value2) {
            addCriterion("long_msg_seq between", value1, value2, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andLongMsgSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("long_msg_seq not between", value1, value2, "longMsgSeq");
            return (Criteria) this;
        }

        public Criteria andTpUdhiIsNull() {
            addCriterion("tp_udhi is null");
            return (Criteria) this;
        }

        public Criteria andTpUdhiIsNotNull() {
            addCriterion("tp_udhi is not null");
            return (Criteria) this;
        }

        public Criteria andTpUdhiEqualTo(Integer value) {
            addCriterion("tp_udhi =", value, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiNotEqualTo(Integer value) {
            addCriterion("tp_udhi <>", value, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiGreaterThan(Integer value) {
            addCriterion("tp_udhi >", value, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiGreaterThanOrEqualTo(Integer value) {
            addCriterion("tp_udhi >=", value, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiLessThan(Integer value) {
            addCriterion("tp_udhi <", value, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiLessThanOrEqualTo(Integer value) {
            addCriterion("tp_udhi <=", value, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiIn(List<Integer> values) {
            addCriterion("tp_udhi in", values, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiNotIn(List<Integer> values) {
            addCriterion("tp_udhi not in", values, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiBetween(Integer value1, Integer value2) {
            addCriterion("tp_udhi between", value1, value2, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpUdhiNotBetween(Integer value1, Integer value2) {
            addCriterion("tp_udhi not between", value1, value2, "tpUdhi");
            return (Criteria) this;
        }

        public Criteria andTpPidIsNull() {
            addCriterion("tp_pid is null");
            return (Criteria) this;
        }

        public Criteria andTpPidIsNotNull() {
            addCriterion("tp_pid is not null");
            return (Criteria) this;
        }

        public Criteria andTpPidEqualTo(Integer value) {
            addCriterion("tp_pid =", value, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidNotEqualTo(Integer value) {
            addCriterion("tp_pid <>", value, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidGreaterThan(Integer value) {
            addCriterion("tp_pid >", value, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tp_pid >=", value, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidLessThan(Integer value) {
            addCriterion("tp_pid <", value, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidLessThanOrEqualTo(Integer value) {
            addCriterion("tp_pid <=", value, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidIn(List<Integer> values) {
            addCriterion("tp_pid in", values, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidNotIn(List<Integer> values) {
            addCriterion("tp_pid not in", values, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidBetween(Integer value1, Integer value2) {
            addCriterion("tp_pid between", value1, value2, "tpPid");
            return (Criteria) this;
        }

        public Criteria andTpPidNotBetween(Integer value1, Integer value2) {
            addCriterion("tp_pid not between", value1, value2, "tpPid");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andNumSegIsNull() {
            addCriterion("num_seg is null");
            return (Criteria) this;
        }

        public Criteria andNumSegIsNotNull() {
            addCriterion("num_seg is not null");
            return (Criteria) this;
        }

        public Criteria andNumSegEqualTo(String value) {
            addCriterion("num_seg =", value, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegNotEqualTo(String value) {
            addCriterion("num_seg <>", value, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegGreaterThan(String value) {
            addCriterion("num_seg >", value, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegGreaterThanOrEqualTo(String value) {
            addCriterion("num_seg >=", value, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegLessThan(String value) {
            addCriterion("num_seg <", value, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegLessThanOrEqualTo(String value) {
            addCriterion("num_seg <=", value, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegLike(String value) {
            addCriterion("num_seg like", value, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegNotLike(String value) {
            addCriterion("num_seg not like", value, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegIn(List<String> values) {
            addCriterion("num_seg in", values, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegNotIn(List<String> values) {
            addCriterion("num_seg not in", values, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegBetween(String value1, String value2) {
            addCriterion("num_seg between", value1, value2, "numSeg");
            return (Criteria) this;
        }

        public Criteria andNumSegNotBetween(String value1, String value2) {
            addCriterion("num_seg not between", value1, value2, "numSeg");
            return (Criteria) this;
        }

        public Criteria andPkNumberIsNull() {
            addCriterion("pk_number is null");
            return (Criteria) this;
        }

        public Criteria andPkNumberIsNotNull() {
            addCriterion("pk_number is not null");
            return (Criteria) this;
        }

        public Criteria andPkNumberEqualTo(Integer value) {
            addCriterion("pk_number =", value, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberNotEqualTo(Integer value) {
            addCriterion("pk_number <>", value, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberGreaterThan(Integer value) {
            addCriterion("pk_number >", value, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("pk_number >=", value, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberLessThan(Integer value) {
            addCriterion("pk_number <", value, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberLessThanOrEqualTo(Integer value) {
            addCriterion("pk_number <=", value, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberIn(List<Integer> values) {
            addCriterion("pk_number in", values, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberNotIn(List<Integer> values) {
            addCriterion("pk_number not in", values, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberBetween(Integer value1, Integer value2) {
            addCriterion("pk_number between", value1, value2, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("pk_number not between", value1, value2, "pkNumber");
            return (Criteria) this;
        }

        public Criteria andPkTotalIsNull() {
            addCriterion("pk_total is null");
            return (Criteria) this;
        }

        public Criteria andPkTotalIsNotNull() {
            addCriterion("pk_total is not null");
            return (Criteria) this;
        }

        public Criteria andPkTotalEqualTo(Integer value) {
            addCriterion("pk_total =", value, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalNotEqualTo(Integer value) {
            addCriterion("pk_total <>", value, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalGreaterThan(Integer value) {
            addCriterion("pk_total >", value, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("pk_total >=", value, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalLessThan(Integer value) {
            addCriterion("pk_total <", value, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalLessThanOrEqualTo(Integer value) {
            addCriterion("pk_total <=", value, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalIn(List<Integer> values) {
            addCriterion("pk_total in", values, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalNotIn(List<Integer> values) {
            addCriterion("pk_total not in", values, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalBetween(Integer value1, Integer value2) {
            addCriterion("pk_total between", value1, value2, "pkTotal");
            return (Criteria) this;
        }

        public Criteria andPkTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("pk_total not between", value1, value2, "pkTotal");
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