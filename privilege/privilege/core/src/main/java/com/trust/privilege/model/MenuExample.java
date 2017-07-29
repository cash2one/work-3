package com.trust.privilege.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MenuExample() {
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

        public Criteria andMenuIdIsNull() {
            addCriterion("Menu_Id is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("Menu_Id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(String value) {
            addCriterion("Menu_Id =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(String value) {
            addCriterion("Menu_Id <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(String value) {
            addCriterion("Menu_Id >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(String value) {
            addCriterion("Menu_Id >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(String value) {
            addCriterion("Menu_Id <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(String value) {
            addCriterion("Menu_Id <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLike(String value) {
            addCriterion("Menu_Id like", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotLike(String value) {
            addCriterion("Menu_Id not like", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<String> values) {
            addCriterion("Menu_Id in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<String> values) {
            addCriterion("Menu_Id not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(String value1, String value2) {
            addCriterion("Menu_Id between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(String value1, String value2) {
            addCriterion("Menu_Id not between", value1, value2, "menuId");
            return (Criteria) this;
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

        public Criteria andMenuNameIsNull() {
            addCriterion("Menu_Name is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("Menu_Name is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("Menu_Name =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("Menu_Name <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("Menu_Name >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("Menu_Name >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("Menu_Name <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("Menu_Name <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("Menu_Name like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("Menu_Name not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("Menu_Name in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("Menu_Name not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("Menu_Name between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("Menu_Name not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuDescIsNull() {
            addCriterion("Menu_Desc is null");
            return (Criteria) this;
        }

        public Criteria andMenuDescIsNotNull() {
            addCriterion("Menu_Desc is not null");
            return (Criteria) this;
        }

        public Criteria andMenuDescEqualTo(String value) {
            addCriterion("Menu_Desc =", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescNotEqualTo(String value) {
            addCriterion("Menu_Desc <>", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescGreaterThan(String value) {
            addCriterion("Menu_Desc >", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescGreaterThanOrEqualTo(String value) {
            addCriterion("Menu_Desc >=", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescLessThan(String value) {
            addCriterion("Menu_Desc <", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescLessThanOrEqualTo(String value) {
            addCriterion("Menu_Desc <=", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescLike(String value) {
            addCriterion("Menu_Desc like", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescNotLike(String value) {
            addCriterion("Menu_Desc not like", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescIn(List<String> values) {
            addCriterion("Menu_Desc in", values, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescNotIn(List<String> values) {
            addCriterion("Menu_Desc not in", values, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescBetween(String value1, String value2) {
            addCriterion("Menu_Desc between", value1, value2, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescNotBetween(String value1, String value2) {
            addCriterion("Menu_Desc not between", value1, value2, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedIsNull() {
            addCriterion("Reqeust_Methed is null");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedIsNotNull() {
            addCriterion("Reqeust_Methed is not null");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedEqualTo(String value) {
            addCriterion("Reqeust_Methed =", value, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedNotEqualTo(String value) {
            addCriterion("Reqeust_Methed <>", value, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedGreaterThan(String value) {
            addCriterion("Reqeust_Methed >", value, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedGreaterThanOrEqualTo(String value) {
            addCriterion("Reqeust_Methed >=", value, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedLessThan(String value) {
            addCriterion("Reqeust_Methed <", value, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedLessThanOrEqualTo(String value) {
            addCriterion("Reqeust_Methed <=", value, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedLike(String value) {
            addCriterion("Reqeust_Methed like", value, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedNotLike(String value) {
            addCriterion("Reqeust_Methed not like", value, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedIn(List<String> values) {
            addCriterion("Reqeust_Methed in", values, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedNotIn(List<String> values) {
            addCriterion("Reqeust_Methed not in", values, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedBetween(String value1, String value2) {
            addCriterion("Reqeust_Methed between", value1, value2, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andReqeustMethedNotBetween(String value1, String value2) {
            addCriterion("Reqeust_Methed not between", value1, value2, "reqeustMethed");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("Parent_Id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("Parent_Id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("Parent_Id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("Parent_Id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("Parent_Id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("Parent_Id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("Parent_Id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("Parent_Id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("Parent_Id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("Parent_Id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("Parent_Id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("Parent_Id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("Parent_Id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("Parent_Id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andMenuLevelIsNull() {
            addCriterion("Menu_Level is null");
            return (Criteria) this;
        }

        public Criteria andMenuLevelIsNotNull() {
            addCriterion("Menu_Level is not null");
            return (Criteria) this;
        }

        public Criteria andMenuLevelEqualTo(String value) {
            addCriterion("Menu_Level =", value, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelNotEqualTo(String value) {
            addCriterion("Menu_Level <>", value, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelGreaterThan(String value) {
            addCriterion("Menu_Level >", value, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelGreaterThanOrEqualTo(String value) {
            addCriterion("Menu_Level >=", value, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelLessThan(String value) {
            addCriterion("Menu_Level <", value, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelLessThanOrEqualTo(String value) {
            addCriterion("Menu_Level <=", value, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelLike(String value) {
            addCriterion("Menu_Level like", value, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelNotLike(String value) {
            addCriterion("Menu_Level not like", value, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelIn(List<String> values) {
            addCriterion("Menu_Level in", values, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelNotIn(List<String> values) {
            addCriterion("Menu_Level not in", values, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelBetween(String value1, String value2) {
            addCriterion("Menu_Level between", value1, value2, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andMenuLevelNotBetween(String value1, String value2) {
            addCriterion("Menu_Level not between", value1, value2, "menuLevel");
            return (Criteria) this;
        }

        public Criteria andUpdateDtIsNull() {
            addCriterion("Update_Dt is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDtIsNotNull() {
            addCriterion("Update_Dt is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDtEqualTo(Date value) {
            addCriterion("Update_Dt =", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtNotEqualTo(Date value) {
            addCriterion("Update_Dt <>", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtGreaterThan(Date value) {
            addCriterion("Update_Dt >", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtGreaterThanOrEqualTo(Date value) {
            addCriterion("Update_Dt >=", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtLessThan(Date value) {
            addCriterion("Update_Dt <", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtLessThanOrEqualTo(Date value) {
            addCriterion("Update_Dt <=", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtIn(List<Date> values) {
            addCriterion("Update_Dt in", values, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtNotIn(List<Date> values) {
            addCriterion("Update_Dt not in", values, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtBetween(Date value1, Date value2) {
            addCriterion("Update_Dt between", value1, value2, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtNotBetween(Date value1, Date value2) {
            addCriterion("Update_Dt not between", value1, value2, "updateDt");
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

        public Criteria andShowOrderIsNull() {
            addCriterion("Show_Order is null");
            return (Criteria) this;
        }

        public Criteria andShowOrderIsNotNull() {
            addCriterion("Show_Order is not null");
            return (Criteria) this;
        }

        public Criteria andShowOrderEqualTo(String value) {
            addCriterion("Show_Order =", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotEqualTo(String value) {
            addCriterion("Show_Order <>", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderGreaterThan(String value) {
            addCriterion("Show_Order >", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderGreaterThanOrEqualTo(String value) {
            addCriterion("Show_Order >=", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderLessThan(String value) {
            addCriterion("Show_Order <", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderLessThanOrEqualTo(String value) {
            addCriterion("Show_Order <=", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderLike(String value) {
            addCriterion("Show_Order like", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotLike(String value) {
            addCriterion("Show_Order not like", value, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderIn(List<String> values) {
            addCriterion("Show_Order in", values, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotIn(List<String> values) {
            addCriterion("Show_Order not in", values, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderBetween(String value1, String value2) {
            addCriterion("Show_Order between", value1, value2, "showOrder");
            return (Criteria) this;
        }

        public Criteria andShowOrderNotBetween(String value1, String value2) {
            addCriterion("Show_Order not between", value1, value2, "showOrder");
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