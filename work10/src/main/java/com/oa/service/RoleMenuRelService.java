package com.oa.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.oa.entity.Menu;
import com.oa.entity.RoleMenuRel;
import com.oa.mapper.RoleMenuRelMapper;

public interface RoleMenuRelService {
	
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int deleteByPrimaryKey(Long roleMenuRel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int insert(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int insertSelective(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    RoleMenuRel selectByPrimaryKey(Long roleMenuRel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int updateByPrimaryKeySelective(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int updateByPrimaryKey(RoleMenuRel record);
    public List<RoleMenuRel> selectMenuRoleRelByMenuId(long menuId);
}