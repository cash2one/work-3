package com.trust.privilege.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.MemBer;
import com.trust.privilege.model.MemBerExample;

public interface MemBerMapper {
    int countByExample(MemBerExample example);

    int deleteByExample(MemBerExample example);

    int deleteByPrimaryKey(String memberId);

    int insert(MemBer record);

    int insertSelective(MemBer record);

    List<MemBer> selectByExample(MemBerExample example);

    MemBer selectByPrimaryKey(String memberId);

    int updateByExampleSelective(@Param("record") MemBer record, @Param("example") MemBerExample example);

    int updateByExample(@Param("record") MemBer record, @Param("example") MemBerExample example);

    int updateByPrimaryKeySelective(MemBer record);

    int updateByPrimaryKey(MemBer record);
}