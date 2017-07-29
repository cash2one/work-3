package com.trust.privilege.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RolePrivilegeResExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RolePrivilegeResExample() {
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

        public Criteria andRoleIdIsNull() {
            addCriterion("Role_Id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("Role_Id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(String value) {
            addCriterion("Role_Id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(String value) {
            addCriterion("Role_Id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(String value) {
            addCriterion("Role_Id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("Role_Id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(String value) {
            addCriterion("Role_Id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(String value) {
            addCriterion("Role_Id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLike(String value) {
            addCriterion("Role_Id like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotLike(String value) {
            addCriterion("Role_Id not like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<String> values) {
            addCriterion("Role_Id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<String> values) {
            addCriterion("Role_Id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(String value1, String value2) {
            addCriterion("Role_Id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(String value1, String value2) {
            addCriterion("Role_Id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdIsNull() {
            addCriterion("Privilege_Res_Id is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdIsNotNull() {
            addCriterion("Privilege_Res_Id is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdEqualTo(String value) {
            addCriterion("Privilege_Res_Id =", value, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdNotEqualTo(String value) {
            addCriterion("Privilege_Res_Id <>", value, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdGreaterThan(String value) {
            addCriterion("Privilege_Res_Id >", value, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdGreaterThanOrEqualTo(String value) {
            addCriterion("Privilege_Res_Id >=", value, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdLessThan(String value) {
            addCriterion("Privilege_Res_Id <", value, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdLessThanOrEqualTo(String value) {
            addCriterion("Privilege_Res_Id <=", value, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdLike(String value) {
            addCriterion("Privilege_Res_Id like", value, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdNotLike(String value) {
            addCriterion("Privilege_Res_Id not like", value, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdIn(List<String> values) {
            addCriterion("Privilege_Res_Id in", values, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdNotIn(List<String> values) {
            addCriterion("Privilege_Res_Id not in", values, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdBetween(String value1, String value2) {
            addCriterion("Privilege_Res_Id between", value1, value2, "privilegeResId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResIdNotBetween(String value1, String value2) {
            addCriterion("Privilege_Res_Id not between", value1, value2, "privilegeResId");
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

        public Criteria andStateTypeIsNull() {
            addCriterion("State_Type is null");
            return (Criteria) this;
        }

        public Criteria andStateTypeIsNotNull() {
            addCriterion("State_Type is not null");
            return (Criteria) this;
        }

        public Criteria andStateTypeEqualTo(String value) {
            addCriterion("State_Type =", value, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeNotEqualTo(String value) {
            addCriterion("State_Type <>", value, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeGreaterThan(String value) {
            addCriterion("State_Type >", value, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("State_Type >=", value, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeLessThan(String value) {
            addCriterion("State_Type <", value, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeLessThanOrEqualTo(String value) {
            addCriterion("State_Type <=", value, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeLike(String value) {
            addCriterion("State_Type like", value, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeNotLike(String value) {
            addCriterion("State_Type not like", value, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeIn(List<String> values) {
            addCriterion("State_Type in", values, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeNotIn(List<String> values) {
            addCriterion("State_Type not in", values, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeBetween(String value1, String value2) {
            addCriterion("State_Type between", value1, value2, "stateType");
            return (Criteria) this;
        }

        public Criteria andStateTypeNotBetween(String value1, String value2) {
            addCriterion("State_Type not between", value1, value2, "stateType");
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