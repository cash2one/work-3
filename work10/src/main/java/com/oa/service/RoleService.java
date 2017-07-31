package com.oa.service;

import java.util.List;
import java.util.Map;

import com.oa.entity.Role;

public interface RoleService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbggenerated Tue Jun 13 21:22:57 CST 2017
     */
    int deleteByPrimaryKey(Long roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbggenerated Tue Jun 13 21:22:57 CST 2017
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbggenerated Tue Jun 13 21:22:57 CST 2017
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbggenerated Tue Jun 13 21:22:57 CST 2017
     */
    Role selectByPrimaryKey(Long roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbggenerated Tue Jun 13 21:22:57 CST 2017
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbggenerated Tue Jun 13 21:22:57 CST 2017
     */
    int updateByPrimaryKey(Role record);
    
    
//    //分页有关
//    public  List<Role> selectRoleListByPage(Map<String, Object> paramMap);
//    public long selectRoleCount(Map<String ,Object > paramMap);
    
    public Map<String, Object> selectRoleListByPage(Map<String, Object> paramMap);
    
    public List<Role> selectAllRoleList();

    
}