package com.trust.privilege.dao.complexMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.SystemConfig;

public interface SystemConfigComplexMapper {
	/**查询常用功能列表*/
	public List<SystemConfig> getAllOffenUserOption(@Param("userName") String userName);
}
