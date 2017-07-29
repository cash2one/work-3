package com.trust.privilege.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.SystemPlatform;
import com.trust.privilege.model.SystemPlatformExample;

public interface SystemPlatformMapper {
    int countByExample(SystemPlatformExample example);

    int deleteByExample(SystemPlatformExample example);

    int deleteByPrimaryKey(String systemPlatformCd);

    int insert(SystemPlatform record);

    int insertSelective(SystemPlatform record);

    List<SystemPlatform> selectByExample(SystemPlatformExample example);

    SystemPlatform selectByPrimaryKey(String systemPlatformCd);

    int updateByExampleSelective(@Param("record") SystemPlatform record, @Param("example") SystemPlatformExample example);

    int updateByExample(@Param("record") SystemPlatform record, @Param("example") SystemPlatformExample example);

    int updateByPrimaryKeySelective(SystemPlatform record);

    int updateByPrimaryKey(SystemPlatform record);
}