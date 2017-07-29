package com.trust.privilege.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrivilegeResExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrivilegeResExample() {
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

        public Criteria andResNameIsNull() {
            addCriterion("Res_Name is null");
            return (Criteria) this;
        }

        public Criteria andResNameIsNotNull() {
            addCriterion("Res_Name is not null");
            return (Criteria) this;
        }

        public Criteria andResNameEqualTo(String value) {
            addCriterion("Res_Name =", value, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameNotEqualTo(String value) {
            addCriterion("Res_Name <>", value, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameGreaterThan(String value) {
            addCriterion("Res_Name >", value, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameGreaterThanOrEqualTo(String value) {
            addCriterion("Res_Name >=", value, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameLessThan(String value) {
            addCriterion("Res_Name <", value, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameLessThanOrEqualTo(String value) {
            addCriterion("Res_Name <=", value, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameLike(String value) {
            addCriterion("Res_Name like", value, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameNotLike(String value) {
            addCriterion("Res_Name not like", value, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameIn(List<String> values) {
            addCriterion("Res_Name in", values, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameNotIn(List<String> values) {
            addCriterion("Res_Name not in", values, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameBetween(String value1, String value2) {
            addCriterion("Res_Name between", value1, value2, "resName");
            return (Criteria) this;
        }

        public Criteria andResNameNotBetween(String value1, String value2) {
            addCriterion("Res_Name not between", value1, value2, "resName");
            return (Criteria) this;
        }

        public Criteria andResDescIsNull() {
            addCriterion("Res_Desc is null");
            return (Criteria) this;
        }

        public Criteria andResDescIsNotNull() {
            addCriterion("Res_Desc is not null");
            return (Criteria) this;
        }

        public Criteria andResDescEqualTo(String value) {
            addCriterion("Res_Desc =", value, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescNotEqualTo(String value) {
            addCriterion("Res_Desc <>", value, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescGreaterThan(String value) {
            addCriterion("Res_Desc >", value, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescGreaterThanOrEqualTo(String value) {
            addCriterion("Res_Desc >=", value, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescLessThan(String value) {
            addCriterion("Res_Desc <", value, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescLessThanOrEqualTo(String value) {
            addCriterion("Res_Desc <=", value, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescLike(String value) {
            addCriterion("Res_Desc like", value, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescNotLike(String value) {
            addCriterion("Res_Desc not like", value, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescIn(List<String> values) {
            addCriterion("Res_Desc in", values, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescNotIn(List<String> values) {
            addCriterion("Res_Desc not in", values, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescBetween(String value1, String value2) {
            addCriterion("Res_Desc between", value1, value2, "resDesc");
            return (Criteria) this;
        }

        public Criteria andResDescNotBetween(String value1, String value2) {
            addCriterion("Res_Desc not between", value1, value2, "resDesc");
            return (Criteria) this;
        }

        public Criteria andReqMethedIsNull() {
            addCriterion("Req_Methed is null");
            return (Criteria) this;
        }

        public Criteria andReqMethedIsNotNull() {
            addCriterion("Req_Methed is not null");
            return (Criteria) this;
        }

        public Criteria andReqMethedEqualTo(String value) {
            addCriterion("Req_Methed =", value, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedNotEqualTo(String value) {
            addCriterion("Req_Methed <>", value, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedGreaterThan(String value) {
            addCriterion("Req_Methed >", value, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedGreaterThanOrEqualTo(String value) {
            addCriterion("Req_Methed >=", value, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedLessThan(String value) {
            addCriterion("Req_Methed <", value, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedLessThanOrEqualTo(String value) {
            addCriterion("Req_Methed <=", value, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedLike(String value) {
            addCriterion("Req_Methed like", value, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedNotLike(String value) {
            addCriterion("Req_Methed not like", value, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedIn(List<String> values) {
            addCriterion("Req_Methed in", values, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedNotIn(List<String> values) {
            addCriterion("Req_Methed not in", values, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedBetween(String value1, String value2) {
            addCriterion("Req_Methed between", value1, value2, "reqMethed");
            return (Criteria) this;
        }

        public Criteria andReqMethedNotBetween(String value1, String value2) {
            addCriterion("Req_Methed not between", value1, value2, "reqMethed");
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

        public Criteria andCreateDateIsNull() {
            addCriterion("Create_Date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("Create_Date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("Create_Date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("Create_Date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("Create_Date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("Create_Date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("Create_Date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("Create_Date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("Create_Date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("Create_Date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("Create_Date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("Create_Date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("Update_Date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("Update_Date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("Update_Date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("Update_Date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("Update_Date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("Update_Date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("Update_Date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("Update_Date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("Update_Date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("Update_Date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("Update_Date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("Update_Date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("Version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("Version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Date value) {
            addCriterion("Version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Date value) {
            addCriterion("Version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Date value) {
            addCriterion("Version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Date value) {
            addCriterion("Version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Date value) {
            addCriterion("Version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Date value) {
            addCriterion("Version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Date> values) {
            addCriterion("Version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Date> values) {
            addCriterion("Version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Date value1, Date value2) {
            addCriterion("Version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Date value1, Date value2) {
            addCriterion("Version not between", value1, value2, "version");
            return (Criteria) this;
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

        public Criteria andMarkIsNull() {
            addCriterion("Mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("Mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("Mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("Mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("Mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("Mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("Mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("Mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("Mark like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("Mark not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("Mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("Mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("Mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("Mark not between", value1, value2, "mark");
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