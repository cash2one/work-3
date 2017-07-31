package com.oa.service;

import java.util.List;

import com.oa.entity.RoleUserRel;

public interface RoleUserRelService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int deleteByPrimaryKey(Long roleUserRelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int insert(RoleUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int insertSelective(RoleUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    RoleUserRel selectByPrimaryKey(Long roleUserRelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int updateByPrimaryKeySelective(RoleUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Tue Jun 06 23:47:41 CST 2017
     */
    int updateByPrimaryKey(RoleUserRel record);
    
    public String selectRoleIdByUserId(long userId);
    
    List<RoleUserRel> selectUserByRoleId(long roleId);
}