package com.trust.privilege.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemPlatformExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemPlatformExample() {
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

        public Criteria andSystemPlatformCdIsNull() {
            addCriterion("System_Platform_CD is null");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdIsNotNull() {
            addCriterion("System_Platform_CD is not null");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdEqualTo(String value) {
            addCriterion("System_Platform_CD =", value, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdNotEqualTo(String value) {
            addCriterion("System_Platform_CD <>", value, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdGreaterThan(String value) {
            addCriterion("System_Platform_CD >", value, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdGreaterThanOrEqualTo(String value) {
            addCriterion("System_Platform_CD >=", value, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdLessThan(String value) {
            addCriterion("System_Platform_CD <", value, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdLessThanOrEqualTo(String value) {
            addCriterion("System_Platform_CD <=", value, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdLike(String value) {
            addCriterion("System_Platform_CD like", value, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdNotLike(String value) {
            addCriterion("System_Platform_CD not like", value, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdIn(List<String> values) {
            addCriterion("System_Platform_CD in", values, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdNotIn(List<String> values) {
            addCriterion("System_Platform_CD not in", values, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdBetween(String value1, String value2) {
            addCriterion("System_Platform_CD between", value1, value2, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformCdNotBetween(String value1, String value2) {
            addCriterion("System_Platform_CD not between", value1, value2, "systemPlatformCd");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameIsNull() {
            addCriterion("System_Platform_Name is null");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameIsNotNull() {
            addCriterion("System_Platform_Name is not null");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameEqualTo(String value) {
            addCriterion("System_Platform_Name =", value, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameNotEqualTo(String value) {
            addCriterion("System_Platform_Name <>", value, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameGreaterThan(String value) {
            addCriterion("System_Platform_Name >", value, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameGreaterThanOrEqualTo(String value) {
            addCriterion("System_Platform_Name >=", value, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameLessThan(String value) {
            addCriterion("System_Platform_Name <", value, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameLessThanOrEqualTo(String value) {
            addCriterion("System_Platform_Name <=", value, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameLike(String value) {
            addCriterion("System_Platform_Name like", value, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameNotLike(String value) {
            addCriterion("System_Platform_Name not like", value, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameIn(List<String> values) {
            addCriterion("System_Platform_Name in", values, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameNotIn(List<String> values) {
            addCriterion("System_Platform_Name not in", values, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameBetween(String value1, String value2) {
            addCriterion("System_Platform_Name between", value1, value2, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformNameNotBetween(String value1, String value2) {
            addCriterion("System_Platform_Name not between", value1, value2, "systemPlatformName");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescIsNull() {
            addCriterion("System_Platform_Desc is null");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescIsNotNull() {
            addCriterion("System_Platform_Desc is not null");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescEqualTo(String value) {
            addCriterion("System_Platform_Desc =", value, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescNotEqualTo(String value) {
            addCriterion("System_Platform_Desc <>", value, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescGreaterThan(String value) {
            addCriterion("System_Platform_Desc >", value, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescGreaterThanOrEqualTo(String value) {
            addCriterion("System_Platform_Desc >=", value, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescLessThan(String value) {
            addCriterion("System_Platform_Desc <", value, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescLessThanOrEqualTo(String value) {
            addCriterion("System_Platform_Desc <=", value, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescLike(String value) {
            addCriterion("System_Platform_Desc like", value, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescNotLike(String value) {
            addCriterion("System_Platform_Desc not like", value, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescIn(List<String> values) {
            addCriterion("System_Platform_Desc in", values, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescNotIn(List<String> values) {
            addCriterion("System_Platform_Desc not in", values, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescBetween(String value1, String value2) {
            addCriterion("System_Platform_Desc between", value1, value2, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andSystemPlatformDescNotBetween(String value1, String value2) {
            addCriterion("System_Platform_Desc not between", value1, value2, "systemPlatformDesc");
            return (Criteria) this;
        }

        public Criteria andStateCdIsNull() {
            addCriterion("State_CD is null");
            return (Criteria) this;
        }

        public Criteria andStateCdIsNotNull() {
            addCriterion("State_CD is not null");
            return (Criteria) this;
        }

        public Criteria andStateCdEqualTo(String value) {
            addCriterion("State_CD =", value, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdNotEqualTo(String value) {
            addCriterion("State_CD <>", value, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdGreaterThan(String value) {
            addCriterion("State_CD >", value, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdGreaterThanOrEqualTo(String value) {
            addCriterion("State_CD >=", value, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdLessThan(String value) {
            addCriterion("State_CD <", value, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdLessThanOrEqualTo(String value) {
            addCriterion("State_CD <=", value, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdLike(String value) {
            addCriterion("State_CD like", value, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdNotLike(String value) {
            addCriterion("State_CD not like", value, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdIn(List<String> values) {
            addCriterion("State_CD in", values, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdNotIn(List<String> values) {
            addCriterion("State_CD not in", values, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdBetween(String value1, String value2) {
            addCriterion("State_CD between", value1, value2, "stateCd");
            return (Criteria) this;
        }

        public Criteria andStateCdNotBetween(String value1, String value2) {
            addCriterion("State_CD not between", value1, value2, "stateCd");
            return (Criteria) this;
        }

        public Criteria andCreateDtIsNull() {
            addCriterion("Create_Dt is null");
            return (Criteria) this;
        }

        public Criteria andCreateDtIsNotNull() {
            addCriterion("Create_Dt is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDtEqualTo(Date value) {
            addCriterion("Create_Dt =", value, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtNotEqualTo(Date value) {
            addCriterion("Create_Dt <>", value, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtGreaterThan(Date value) {
            addCriterion("Create_Dt >", value, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtGreaterThanOrEqualTo(Date value) {
            addCriterion("Create_Dt >=", value, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtLessThan(Date value) {
            addCriterion("Create_Dt <", value, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtLessThanOrEqualTo(Date value) {
            addCriterion("Create_Dt <=", value, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtIn(List<Date> values) {
            addCriterion("Create_Dt in", values, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtNotIn(List<Date> values) {
            addCriterion("Create_Dt not in", values, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtBetween(Date value1, Date value2) {
            addCriterion("Create_Dt between", value1, value2, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateDtNotBetween(Date value1, Date value2) {
            addCriterion("Create_Dt not between", value1, value2, "createDt");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("Create_User_Id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("Create_User_Id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(String value) {
            addCriterion("Create_User_Id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(String value) {
            addCriterion("Create_User_Id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(String value) {
            addCriterion("Create_User_Id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("Create_User_Id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(String value) {
            addCriterion("Create_User_Id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(String value) {
            addCriterion("Create_User_Id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLike(String value) {
            addCriterion("Create_User_Id like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotLike(String value) {
            addCriterion("Create_User_Id not like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<String> values) {
            addCriterion("Create_User_Id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<String> values) {
            addCriterion("Create_User_Id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(String value1, String value2) {
            addCriterion("Create_User_Id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(String value1, String value2) {
            addCriterion("Create_User_Id not between", value1, value2, "createUserId");
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