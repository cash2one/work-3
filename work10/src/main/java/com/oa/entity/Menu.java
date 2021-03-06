package com.oa.entity;

import java.util.Date;

public class Menu {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.MENU_ID
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    private Long menuId;
    
   public String getMenuParentName() {
		return menuParentName;
	}

	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
	}

private String  menuParentName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.MENU_PARENT_ID
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    private Long menuParentId;

    @Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuParentId=" + menuParentId + ", menuName=" + menuName + ", menuType="
				+ menuType + ", menuPath=" + menuPath + ", descContent=" + descContent + ", isPublish=" + isPublish
				+ ", createdDate=" + createdDate + "]";
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.MENU_NAME
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.MENU_TYPE
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    private Short menuType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.MENU_PATH
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    private String menuPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.DESC_CONTENT
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    private String descContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.IS_PUBLISH
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    private Short isPublish;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.CREATED_DATE
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    private Date createdDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.MENU_ID
     *
     * @return the value of sys_menu.MENU_ID
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.MENU_ID
     *
     * @param menuId the value for sys_menu.MENU_ID
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.MENU_PARENT_ID
     *
     * @return the value of sys_menu.MENU_PARENT_ID
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public Long getMenuParentId() {
        return menuParentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.MENU_PARENT_ID
     *
     * @param menuParentId the value for sys_menu.MENU_PARENT_ID
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public void setMenuParentId(Long menuParentId) {
        this.menuParentId = menuParentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.MENU_NAME
     *
     * @return the value of sys_menu.MENU_NAME
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.MENU_NAME
     *
     * @param menuName the value for sys_menu.MENU_NAME
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.MENU_TYPE
     *
     * @return the value of sys_menu.MENU_TYPE
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public Short getMenuType() {
        return menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.MENU_TYPE
     *
     * @param menuType the value for sys_menu.MENU_TYPE
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public void setMenuType(Short menuType) {
        this.menuType = menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.MENU_PATH
     *
     * @return the value of sys_menu.MENU_PATH
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.MENU_PATH
     *
     * @param menuPath the value for sys_menu.MENU_PATH
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath == null ? null : menuPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.DESC_CONTENT
     *
     * @return the value of sys_menu.DESC_CONTENT
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public String getDescContent() {
        return descContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.DESC_CONTENT
     *
     * @param descContent the value for sys_menu.DESC_CONTENT
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public void setDescContent(String descContent) {
        this.descContent = descContent == null ? null : descContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.IS_PUBLISH
     *
     * @return the value of sys_menu.IS_PUBLISH
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public Short getIsPublish() {
        return isPublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.IS_PUBLISH
     *
     * @param isPublish the value for sys_menu.IS_PUBLISH
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public void setIsPublish(Short isPublish) {
        this.isPublish = isPublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.CREATED_DATE
     *
     * @return the value of sys_menu.CREATED_DATE
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.CREATED_DATE
     *
     * @param createdDate the value for sys_menu.CREATED_DATE
     *
     * @mbggenerated Wed Jun 07 01:42:53 CST 2017
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}