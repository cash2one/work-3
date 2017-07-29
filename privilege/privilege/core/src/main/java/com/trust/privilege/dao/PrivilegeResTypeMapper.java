package com.trust.privilege.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.PrivilegeResType;
import com.trust.privilege.model.PrivilegeResTypeExample;

public interface PrivilegeResTypeMapper {
    int countByExample(PrivilegeResTypeExample example);

    int deleteByExample(PrivilegeResTypeExample example);

    int deleteByPrimaryKey(String resTypeCd);

    int insert(PrivilegeResType record);

    int insertSelective(PrivilegeResType record);

    List<PrivilegeResType> selectByExample(PrivilegeResTypeExample example);

    PrivilegeResType selectByPrimaryKey(String resTypeCd);

    int updateByExampleSelective(@Param("record") PrivilegeResType record, @Param("example") PrivilegeResTypeExample example);

    int updateByExample(@Param("record") PrivilegeResType record, @Param("example") PrivilegeResTypeExample example);

    int updateByPrimaryKeySelective(PrivilegeResType record);

    int updateByPrimaryKey(PrivilegeResType record);
}