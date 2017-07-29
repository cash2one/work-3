package com.trust.privilege.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.UserRoleInstance;
import com.trust.privilege.model.UserRoleInstanceExample;

public interface UserRoleInstanceMapper {
    int countByExample(UserRoleInstanceExample example);

    int deleteByExample(UserRoleInstanceExample example);

    int deleteByPrimaryKey(String instId);

    int insert(UserRoleInstance record);

    int insertSelective(UserRoleInstance record);

    List<UserRoleInstance> selectByExample(UserRoleInstanceExample example);

    UserRoleInstance selectByPrimaryKey(String instId);

    int updateByExampleSelective(@Param("record") UserRoleInstance record, @Param("example") UserRoleInstanceExample example);

    int updateByExample(@Param("record") UserRoleInstance record, @Param("example") UserRoleInstanceExample example);

    int updateByPrimaryKeySelective(UserRoleInstance record);

    int updateByPrimaryKey(UserRoleInstance record);
}