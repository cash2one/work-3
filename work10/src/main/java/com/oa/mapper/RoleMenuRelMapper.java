package com.oa.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.support.ManagedList;

import com.oa.entity.Menu;
import com.oa.entity.RoleMenuRel;

public interface RoleMenuRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Wed Jun 07 01:13:21 CST 2017
     */
    int deleteByPrimaryKey(Long roleMenuRel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Wed Jun 07 01:13:21 CST 2017
     */
    int insert(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Wed Jun 07 01:13:21 CST 2017
     */
    int insertSelective(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Wed Jun 07 01:13:21 CST 2017
     */
    RoleMenuRel selectByPrimaryKey(Long roleMenuRel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Wed Jun 07 01:13:21 CST 2017
     */
    int updateByPrimaryKeySelective(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Wed Jun 07 01:13:21 CST 2017
     */
    int updateByPrimaryKey(RoleMenuRel record);

    public List<RoleMenuRel> selectMenuRoleRelByMenuId(long menuId);

}