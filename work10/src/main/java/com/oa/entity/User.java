package com.oa.entity;

import java.util.Date;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.ORG_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private Long orgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_PASSWORD
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String userPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.MOBILE_PHONE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String mobilePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.EMAIL
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_CH_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String userChName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_BIRTHDAY
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private Date userBirthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_SEX
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private Short userSex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.USER_PHOTO_URL
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String userPhotoUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.PROVINCE_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private Integer provinceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.PROVINCE_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String provinceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CITY_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private Integer cityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CITY_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.COUNTRY_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private Integer countryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CONTRY_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String contryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.HOBBY
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String hobby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.INTRODUCE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private String introduce;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CREATED_DATE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    private Date createdDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_ID
     *
     * @return the value of sys_user.USER_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.USER_ID
     *
     * @param userId the value for sys_user.USER_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.ORG_ID
     *
     * @return the value of sys_user.ORG_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.ORG_ID
     *
     * @param orgId the value for sys_user.ORG_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_NAME
     *
     * @return the value of sys_user.USER_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.USER_NAME
     *
     * @param userName the value for sys_user.USER_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_PASSWORD
     *
     * @return the value of sys_user.USER_PASSWORD
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.USER_PASSWORD
     *
     * @param userPassword the value for sys_user.USER_PASSWORD
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.MOBILE_PHONE
     *
     * @return the value of sys_user.MOBILE_PHONE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.MOBILE_PHONE
     *
     * @param mobilePhone the value for sys_user.MOBILE_PHONE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.EMAIL
     *
     * @return the value of sys_user.EMAIL
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.EMAIL
     *
     * @param email the value for sys_user.EMAIL
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_CH_NAME
     *
     * @return the value of sys_user.USER_CH_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getUserChName() {
        return userChName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.USER_CH_NAME
     *
     * @param userChName the value for sys_user.USER_CH_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setUserChName(String userChName) {
        this.userChName = userChName == null ? null : userChName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_BIRTHDAY
     *
     * @return the value of sys_user.USER_BIRTHDAY
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public Date getUserBirthday() {
        return userBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.USER_BIRTHDAY
     *
     * @param userBirthday the value for sys_user.USER_BIRTHDAY
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_SEX
     *
     * @return the value of sys_user.USER_SEX
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public Short getUserSex() {
        return userSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.USER_SEX
     *
     * @param userSex the value for sys_user.USER_SEX
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setUserSex(Short userSex) {
        this.userSex = userSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.USER_PHOTO_URL
     *
     * @return the value of sys_user.USER_PHOTO_URL
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.USER_PHOTO_URL
     *
     * @param userPhotoUrl the value for sys_user.USER_PHOTO_URL
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl == null ? null : userPhotoUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.PROVINCE_ID
     *
     * @return the value of sys_user.PROVINCE_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.PROVINCE_ID
     *
     * @param provinceId the value for sys_user.PROVINCE_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.PROVINCE_NAME
     *
     * @return the value of sys_user.PROVINCE_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.PROVINCE_NAME
     *
     * @param provinceName the value for sys_user.PROVINCE_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CITY_ID
     *
     * @return the value of sys_user.CITY_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.CITY_ID
     *
     * @param cityId the value for sys_user.CITY_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CITY_NAME
     *
     * @return the value of sys_user.CITY_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.CITY_NAME
     *
     * @param cityName the value for sys_user.CITY_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.COUNTRY_ID
     *
     * @return the value of sys_user.COUNTRY_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.COUNTRY_ID
     *
     * @param countryId the value for sys_user.COUNTRY_ID
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CONTRY_NAME
     *
     * @return the value of sys_user.CONTRY_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getContryName() {
        return contryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.CONTRY_NAME
     *
     * @param contryName the value for sys_user.CONTRY_NAME
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setContryName(String contryName) {
        this.contryName = contryName == null ? null : contryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.HOBBY
     *
     * @return the value of sys_user.HOBBY
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.HOBBY
     *
     * @param hobby the value for sys_user.HOBBY
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.INTRODUCE
     *
     * @return the value of sys_user.INTRODUCE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.INTRODUCE
     *
     * @param introduce the value for sys_user.INTRODUCE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.CREATED_DATE
     *
     * @return the value of sys_user.CREATED_DATE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.CREATED_DATE
     *
     * @param createdDate the value for sys_user.CREATED_DATE
     *
     * @mbggenerated Tue Jun 06 21:51:33 CST 2017
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}