package com.trust.privilege.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.StateType;
import com.trust.privilege.model.StateTypeExample;

public interface StateTypeMapper {
    int countByExample(StateTypeExample example);

    int deleteByExample(StateTypeExample example);

    int deleteByPrimaryKey(String stateTypeCd);

    int insert(StateType record);

    int insertSelective(StateType record);

    List<StateType> selectByExample(StateTypeExample example);

    StateType selectByPrimaryKey(String stateTypeCd);

    int updateByExampleSelective(@Param("record") StateType record, @Param("example") StateTypeExample example);

    int updateByExample(@Param("record") StateType record, @Param("example") StateTypeExample example);

    int updateByPrimaryKeySelective(StateType record);

    int updateByPrimaryKey(StateType record);
}