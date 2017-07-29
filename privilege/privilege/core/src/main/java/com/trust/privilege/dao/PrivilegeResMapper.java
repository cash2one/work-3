package com.trust.privilege.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.PrivilegeRes;
import com.trust.privilege.model.PrivilegeResExample;

public interface PrivilegeResMapper {
    int countByExample(PrivilegeResExample example);

    int deleteByExample(PrivilegeResExample example);

    int deleteByPrimaryKey(String privilegeResId);

    int insert(PrivilegeRes record);

    int insertSelective(PrivilegeRes record);

    List<PrivilegeRes> selectByExample(PrivilegeResExample example);

    PrivilegeRes selectByPrimaryKey(String privilegeResId);

    int updateByExampleSelective(@Param("record") PrivilegeRes record, @Param("example") PrivilegeResExample example);

    int updateByExample(@Param("record") PrivilegeRes record, @Param("example") PrivilegeResExample example);

    int updateByPrimaryKeySelective(PrivilegeRes record);

    int updateByPrimaryKey(PrivilegeRes record);
}