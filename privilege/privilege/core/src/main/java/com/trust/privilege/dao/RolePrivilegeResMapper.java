package com.trust.privilege.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.RolePrivilegeRes;
import com.trust.privilege.model.RolePrivilegeResExample;

public interface RolePrivilegeResMapper {
    int countByExample(RolePrivilegeResExample example);

    int deleteByExample(RolePrivilegeResExample example);

    int insert(RolePrivilegeRes record);

    int insertSelective(RolePrivilegeRes record);

    List<RolePrivilegeRes> selectByExample(RolePrivilegeResExample example);

    int updateByExampleSelective(@Param("record") RolePrivilegeRes record, @Param("example") RolePrivilegeResExample example);

    int updateByExample(@Param("record") RolePrivilegeRes record, @Param("example") RolePrivilegeResExample example);
}