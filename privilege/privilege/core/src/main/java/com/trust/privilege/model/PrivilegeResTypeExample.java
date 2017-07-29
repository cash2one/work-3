package com.trust.privilege.model;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeResTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrivilegeResTypeExample() {
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

        public Criteria andResTypeCdIsNull() {
            addCriterion("Res_Type_CD is null");
            return (Criteria) this;
        }

        public Criteria andResTypeCdIsNotNull() {
            addCriterion("Res_Type_CD is not null");
            return (Criteria) this;
        }

        public Criteria andResTypeCdEqualTo(String value) {
            addCriterion("Res_Type_CD =", value, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdNotEqualTo(String value) {
            addCriterion("Res_Type_CD <>", value, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdGreaterThan(String value) {
            addCriterion("Res_Type_CD >", value, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdGreaterThanOrEqualTo(String value) {
            addCriterion("Res_Type_CD >=", value, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdLessThan(String value) {
            addCriterion("Res_Type_CD <", value, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdLessThanOrEqualTo(String value) {
            addCriterion("Res_Type_CD <=", value, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdLike(String value) {
            addCriterion("Res_Type_CD like", value, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdNotLike(String value) {
            addCriterion("Res_Type_CD not like", value, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdIn(List<String> values) {
            addCriterion("Res_Type_CD in", values, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdNotIn(List<String> values) {
            addCriterion("Res_Type_CD not in", values, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdBetween(String value1, String value2) {
            addCriterion("Res_Type_CD between", value1, value2, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeCdNotBetween(String value1, String value2) {
            addCriterion("Res_Type_CD not between", value1, value2, "resTypeCd");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkIsNull() {
            addCriterion("Res_Type_Mark is null");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkIsNotNull() {
            addCriterion("Res_Type_Mark is not null");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkEqualTo(String value) {
            addCriterion("Res_Type_Mark =", value, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkNotEqualTo(String value) {
            addCriterion("Res_Type_Mark <>", value, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkGreaterThan(String value) {
            addCriterion("Res_Type_Mark >", value, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkGreaterThanOrEqualTo(String value) {
            addCriterion("Res_Type_Mark >=", value, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkLessThan(String value) {
            addCriterion("Res_Type_Mark <", value, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkLessThanOrEqualTo(String value) {
            addCriterion("Res_Type_Mark <=", value, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkLike(String value) {
            addCriterion("Res_Type_Mark like", value, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkNotLike(String value) {
            addCriterion("Res_Type_Mark not like", value, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkIn(List<String> values) {
            addCriterion("Res_Type_Mark in", values, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkNotIn(List<String> values) {
            addCriterion("Res_Type_Mark not in", values, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkBetween(String value1, String value2) {
            addCriterion("Res_Type_Mark between", value1, value2, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeMarkNotBetween(String value1, String value2) {
            addCriterion("Res_Type_Mark not between", value1, value2, "resTypeMark");
            return (Criteria) this;
        }

        public Criteria andResTypeNameIsNull() {
            addCriterion("Res_Type_Name is null");
            return (Criteria) this;
        }

        public Criteria andResTypeNameIsNotNull() {
            addCriterion("Res_Type_Name is not null");
            return (Criteria) this;
        }

        public Criteria andResTypeNameEqualTo(String value) {
            addCriterion("Res_Type_Name =", value, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameNotEqualTo(String value) {
            addCriterion("Res_Type_Name <>", value, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameGreaterThan(String value) {
            addCriterion("Res_Type_Name >", value, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("Res_Type_Name >=", value, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameLessThan(String value) {
            addCriterion("Res_Type_Name <", value, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameLessThanOrEqualTo(String value) {
            addCriterion("Res_Type_Name <=", value, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameLike(String value) {
            addCriterion("Res_Type_Name like", value, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameNotLike(String value) {
            addCriterion("Res_Type_Name not like", value, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameIn(List<String> values) {
            addCriterion("Res_Type_Name in", values, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameNotIn(List<String> values) {
            addCriterion("Res_Type_Name not in", values, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameBetween(String value1, String value2) {
            addCriterion("Res_Type_Name between", value1, value2, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeNameNotBetween(String value1, String value2) {
            addCriterion("Res_Type_Name not between", value1, value2, "resTypeName");
            return (Criteria) this;
        }

        public Criteria andResTypeDescIsNull() {
            addCriterion("Res_Type_Desc is null");
            return (Criteria) this;
        }

        public Criteria andResTypeDescIsNotNull() {
            addCriterion("Res_Type_Desc is not null");
            return (Criteria) this;
        }

        public Criteria andResTypeDescEqualTo(String value) {
            addCriterion("Res_Type_Desc =", value, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescNotEqualTo(String value) {
            addCriterion("Res_Type_Desc <>", value, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescGreaterThan(String value) {
            addCriterion("Res_Type_Desc >", value, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("Res_Type_Desc >=", value, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescLessThan(String value) {
            addCriterion("Res_Type_Desc <", value, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescLessThanOrEqualTo(String value) {
            addCriterion("Res_Type_Desc <=", value, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescLike(String value) {
            addCriterion("Res_Type_Desc like", value, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescNotLike(String value) {
            addCriterion("Res_Type_Desc not like", value, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescIn(List<String> values) {
            addCriterion("Res_Type_Desc in", values, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescNotIn(List<String> values) {
            addCriterion("Res_Type_Desc not in", values, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescBetween(String value1, String value2) {
            addCriterion("Res_Type_Desc between", value1, value2, "resTypeDesc");
            return (Criteria) this;
        }

        public Criteria andResTypeDescNotBetween(String value1, String value2) {
            addCriterion("Res_Type_Desc not between", value1, value2, "resTypeDesc");
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